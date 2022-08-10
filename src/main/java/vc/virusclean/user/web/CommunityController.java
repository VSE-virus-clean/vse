package vc.virusclean.user.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jksoft.com.annotation.AuthCheck;
import jksoft.com.web.XController;
import vc.virusclean.cmm.service.UserBoardService;
import vc.virusclean.cmm.vo.UserBoardReplyVO;
import vc.virusclean.cmm.vo.UserBoardVO;


/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : CommunityController.java
 * @Description : 커뮤니티 관련  Controller
 */
@Controller
public class CommunityController extends XController {
	
	@Resource(name="userBoardService")
	private UserBoardService userBoardService;
	

	/**
	 * 커뮤니티 목록
	 * @param boardVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@AuthCheck(loginCheck=false)
	@RequestMapping(value={"/community.vc", "/community/list.vc"})
	public String communityList(@ModelAttribute UserBoardVO boardVO, ModelMap model) throws Exception{     

		boardVO.setIsApi(true);
		boardVO.setLgrpCd("BBS");    //게시판 아이디
		boardVO.setRowLimit(9);
		model.addAttribute("result", userBoardService.selectBoardList(boardVO));
		model.addAttribute("requestUri", "community");
		model.addAttribute("pageMenuId", "KSC");
		
        return "community/communityList";
    }
	
	/**
     * 커뮤니티 상세
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping("/community/view.vc")
    public String communityDet(@ModelAttribute UserBoardVO boardVO, ModelMap model) throws Exception{
        
		boardVO.setIsApi(true);
		boardVO.setLgrpCd("BBS"); 
        model.addAttribute("result", userBoardService.selectBoard(boardVO));
        model.addAttribute("requestUri", "community");
		model.addAttribute("pageMenuId", "KSC");
        
		return "community/communityDet";
    }
	
	/**
     * 커뮤니티 입력 폼
     */
    @RequestMapping(value = "/community/register.vc", method=RequestMethod.GET)
    public String communityReg(@ModelAttribute UserBoardVO boardVO, ModelMap model) throws Exception{
    	
    	boardVO.setLgrpCd("BBS"); 
    	boardVO.setIsApi(true);
    	
        model.addAttribute("result", null );
        model.addAttribute("requestUri", "community");
        model.addAttribute("pageMenuId", "KSC");
        
        return "community/communityReg";
    }
    
    /**
     * 커뮤니티 입력
     */
    @ResponseBody
    @RequestMapping(value= "/community/register.vc", method=RequestMethod.POST)
    public Map<String, Object> communityReg(@Valid @ModelAttribute UserBoardVO boardVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	boardVO.setLgrpCd("BBS"); 
        	boardVO.setIsApi(true);
            mResult.put("result", userBoardService.insertBoard(boardVO));
        }
        
        return mResult;
    }
    
    /**
     * 커뮤니티 수정
     */
    @RequestMapping(value= "/community/modify.vc", method=RequestMethod.GET)
    public String communityMod(@ModelAttribute UserBoardVO boardVO, ModelMap model) throws Exception{        
        
    	boardVO.setLgrpCd("BBS"); 
    	boardVO.setIsApi(true);
        
        model.addAttribute("result", userBoardService.selectBoardByMod(boardVO));
        model.addAttribute("requestUri", "community");
        model.addAttribute("pageMenuId", "KSC");
        
        return "community/communityMod";
    }
    
    /**
     * 커뮤니티 수정
     */
    @ResponseBody
    @RequestMapping(value= "/community/modify.vc", method=RequestMethod.POST)
    public Map<String, Object> communityMod(@Valid @ModelAttribute UserBoardVO boardVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	boardVO.setLgrpCd("BBS"); 
        	boardVO.setIsApi(true);
            mResult.put("result", userBoardService.updateBoard(boardVO));
        }
        
        return mResult;
    }

    /**
     * 커뮤니티 삭제
     */
    @ResponseBody
    @RequestMapping(value= "/community/delete.vc" ,method=RequestMethod.POST)
    public Map<String, Object> communityDel(@ModelAttribute UserBoardVO boardVO) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>(); 
        
        boardVO.setIsApi(true);
        boardVO.setLgrpCd("BBS"); 
        
        mResult.put("result", userBoardService.deleteBoard(boardVO));
        
        return mResult;
    }
    
    
    /************************************ 댓글  ************************************/
    /**
     * 댓글입력하기
     */
    @ResponseBody
    @RequestMapping(value={"/community/reply/register.vc"} ,method=RequestMethod.POST)
    public Map<String, Object> techReplyReg(@Valid @ModelAttribute UserBoardReplyVO replyVO, BindingResult bindingResult) throws Exception{
    	
    	Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	replyVO.setIsApi(true);
            mResult.put("result", userBoardService.insertBoardReply(replyVO));
        }
        
        return mResult;
    }
    
    /**
     * 댓글수정하기
     */
    @ResponseBody
    @RequestMapping(value={"/community/reply/modify.vc"} ,method=RequestMethod.POST)
    public Map<String, Object> techReplyMod(@Valid @ModelAttribute UserBoardReplyVO replyVO, BindingResult bindingResult) throws Exception{
    	
    	Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	replyVO.setIsApi(true);
            mResult.put("result", userBoardService.updateBoardReply(replyVO));
        }
        
        return mResult;
    }
    
    /**
     * 댓글삭제
     */
    @ResponseBody
    @RequestMapping(value={"/community/reply/delete.vc"} ,method=RequestMethod.POST)
    public Map<String, Object> memberBoardReplyDel(@ModelAttribute UserBoardReplyVO replyVO) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>(); 
        
        replyVO.setIsApi(true);
        mResult.put("result", userBoardService.deleteBoardReply(replyVO));
        
        return mResult;
    }

}
