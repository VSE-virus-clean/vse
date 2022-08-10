package vc.virusclean.admin.board.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jksoft.com.filter.FilterUtil;
import jksoft.com.util.StringUtil;
import jksoft.com.util.XMap;
import jksoft.com.web.XController;
import vc.virusclean.admin.shop.vo.OrderVO;
import vc.virusclean.cmm.service.BoardService;
import vc.virusclean.cmm.service.UserBoardService;
import vc.virusclean.cmm.vo.BoardVO;
import vc.virusclean.cmm.vo.UserBoardReplyVO;
import vc.virusclean.cmm.vo.UserBoardVO;

/**
 * <pre>
 * 사용자 게시판 CONTROLLER
 * </pre>
 * 
 * @ClassName   : UserBoardController.java
 * @Description : @Controller
 */

@Controller
@RequestMapping(value= {"/admin"})
public class UserBoardController extends XController {

	@Resource(name="userBoardService")
	private UserBoardService userBoardService;
	
	@Resource(name="boardService")   
    private BoardService boardService;

	
    public String checkMenuId(String id) throws Exception{
    	String returnCode = "";
    	
    	if("QNA".equals(id)){
    		returnCode = "B2";
    	} else if("REVIEW".equals(id)){
    		returnCode = "B3";
    	} else if("BBS".equals(id)){
    		returnCode = "F4";
    	} else if("BUSINESS".equals(id)){
    		returnCode = "J3";	//창업상담관리
    	} else if("PRESENTATION".equals(id)){
    		returnCode = "J4";	//사업설명회관리
    	} else if("APPLICATION".equals(id)){
    		returnCode = "J5";	//프로지원서관리
    	}
    	
    	return returnCode;
    }
    
    
    /**
     * 목록
     */
    @RequestMapping(value= {"/product/{lgrpCd}/list.vc"})
    public String boardFaqList(@PathVariable("lgrpCd") String LGRP_CD, @ModelAttribute UserBoardVO boardVO, ModelMap model) throws Exception{
    	
    	String MENU_NAME = "product";
    	
    	// search value decoding setting
    	String searchKeyVal = FilterUtil.decodeHTML(boardVO.getSearchKey());
    	boardVO.setSearchKey(searchKeyVal);
    	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
		
        model.addAttribute("result", userBoardService.selectBoardList(boardVO));
        model.addAttribute("requestUri", MENU_NAME + "/" + LGRP_CD);
        model.addAttribute("pageMenuId", this.checkMenuId(boardVO.getLgrpCd()));
        
        return MENU_NAME + "/" + LGRP_CD + "/"+ LGRP_CD + "List";
    }
    
    /**
     * 게시판 : 입력
     */
    @RequestMapping(value= {"/product/{lgrpCd}/register.vc"}, method=RequestMethod.GET)
    public String boardReg(@PathVariable("lgrpCd") String LGRP_CD, @Valid @ModelAttribute UserBoardVO boardVO, ModelMap model) throws Exception{        
        
    	String MENU_NAME = "product";
    	
    	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
        
        model.addAttribute("requestUri", MENU_NAME + "/" + LGRP_CD );
        model.addAttribute("pageMenuId", this.checkMenuId(boardVO.getLgrpCd()));
        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "/"+ LGRP_CD + "Reg";
    }
    
    /**
     * 게시판 : 입력
     */
    @ResponseBody
    @RequestMapping(value= {"/product/{lgrpCd}/register.vc"}, method=RequestMethod.POST)
    public Map<String, Object> boardReg(@PathVariable("lgrpCd") String LGRP_CD, @Valid @ModelAttribute UserBoardVO boardVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
            mResult.put("result", userBoardService.insertBoard(boardVO));
        }
        
        return mResult;
    }
    
    
    /**
     * 게시판 : 보기
     */
    @RequestMapping(value= {"/product/{lgrpCd}/view.vc"}, method=RequestMethod.GET)
    public String boardFaqDet(@PathVariable("lgrpCd") String LGRP_CD, @Valid @ModelAttribute UserBoardVO boardVO, ModelMap model) throws Exception{        
        
    	String MENU_NAME = "product";
    	
    	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
        
    	if("bbs".equals(LGRP_CD)){
    		model.addAttribute("result", userBoardService.selectBoard(boardVO));
    	}else{
    		model.addAttribute("result", userBoardService.selectBoardByMod(boardVO));
    	}
        model.addAttribute("requestUri", MENU_NAME + "/" + LGRP_CD );
        model.addAttribute("pageMenuId", this.checkMenuId(boardVO.getLgrpCd()));
        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "/"+ LGRP_CD + "Det";
    }
    
    /**
     * 게시판 : 수정
     */
    @RequestMapping(value= {"/product/{lgrpCd}/modify.vc"}, method=RequestMethod.GET)
    public String boardFaqMod(@PathVariable("lgrpCd") String LGRP_CD, @Valid @ModelAttribute UserBoardVO boardVO, ModelMap model) throws Exception{        
        
    	String MENU_NAME = "product";
    	
    	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
        
		model.addAttribute("result", userBoardService.selectBoardByMod(boardVO));
        model.addAttribute("requestUri", MENU_NAME + "/" + LGRP_CD );
        model.addAttribute("pageMenuId", this.checkMenuId(boardVO.getLgrpCd()));
        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "/"+ LGRP_CD + "Mod";
    }
    
    /**
     * 게시판 : 수정
     */
    @ResponseBody
    @RequestMapping(value= {"/product/{lgrpCd}/modify.vc"}, method=RequestMethod.POST)
    public Map<String, Object> boardFaqMod(@PathVariable("lgrpCd") String LGRP_CD, @Valid @ModelAttribute UserBoardVO boardVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
            mResult.put("result", userBoardService.updateBoard(boardVO));
        }
        
        return mResult;
    }

    /**
     * 게시판 : 삭제
     */
    @ResponseBody
    @RequestMapping(value= {"/product/{lgrpCd}/delete.vc"} ,method=RequestMethod.POST)
    public Map<String, Object> boardFaqDel(@PathVariable("lgrpCd") String LGRP_CD, @ModelAttribute UserBoardVO boardVO) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>(); 
        
        boardVO.setLgrpCd(LGRP_CD.toUpperCase());
        
        mResult.put("result", userBoardService.deleteBoard(boardVO));
        
        return mResult;
    }
    
    /**
     * 노출여부 변경
     *
     * @param authVO2
     * @param mSession
     * @throws Exception 
    */
	 @ResponseBody
	 @RequestMapping(value={"/product/{lgrpCd}/stcdMod.vc"}, method=RequestMethod.POST)
	 private Map<String, Object> updateByStCd(@PathVariable("lgrpCd") String LGRP_CD, @Valid @ModelAttribute UserBoardVO boardVO, BindingResult bindingResult) throws Exception {
		Map<String, Object> mResult = new HashMap<String, Object>();
	    
	    if(bindingResult.hasErrors()){
	        for(FieldError f : bindingResult.getFieldErrors()){
	           log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
	        }
	        mResult.put("bindingFields", bindingResult.getFieldErrors());
	        mResult.put("bindingStatus", false);
	        mResult.put("message", xMessageSource.getMessage("exception.binding"));
	        
	    }else{
	        mResult.put("result", userBoardService.updateByUseYn(boardVO));
	    }
	    
	    return mResult;	
	 }
	 
	 /**
	  * 댓글삭제
	  */
	 @ResponseBody
	 @RequestMapping(value={"/product/reply/delete.vc"} ,method=RequestMethod.POST)
	 public Map<String, Object> memberBoardReplyDel(@ModelAttribute UserBoardReplyVO replyVO) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>(); 
        
        mResult.put("result", userBoardService.deleteBoardReply(replyVO));
        
        return mResult;
	 }
	 
	 
	 
	 
	 /** 
	  * VSE 
	  */
	 
	 /**
      * 목록
      */
    @RequestMapping(value= {"/vse/{lgrpCd}/list.vc"})
    public String vseBoardList(@PathVariable("lgrpCd") String LGRP_CD, @ModelAttribute UserBoardVO boardVO, ModelMap model) throws Exception{
    	
    	String MENU_NAME = "vse";
    	
    	// search value decoding setting
    	String searchKeyVal = FilterUtil.decodeHTML(boardVO.getSearchKey());
    	boardVO.setSearchKey(searchKeyVal);
    	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
		
        model.addAttribute("result", userBoardService.selectBoardList(boardVO));
        model.addAttribute("requestUri", MENU_NAME + "/" + LGRP_CD);
        model.addAttribute("pageMenuId", this.checkMenuId(boardVO.getLgrpCd()));
        
        return MENU_NAME + "/" + LGRP_CD + "/"+ LGRP_CD + "List";
    }
    
    @RequestMapping(value= {"/vse/{lgrpCd}/view.vc"}, method=RequestMethod.GET)
    public String vseBoardDet(@PathVariable("lgrpCd") String LGRP_CD, @Valid @ModelAttribute UserBoardVO boardVO, ModelMap model) throws Exception{        
        
    	String MENU_NAME = "vse";
    	
    	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
        
    	model.addAttribute("result", userBoardService.selectBoard(boardVO));
        model.addAttribute("requestUri", MENU_NAME + "/" + LGRP_CD );
        model.addAttribute("pageMenuId", this.checkMenuId(boardVO.getLgrpCd()));
        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "/"+ LGRP_CD + "Det";
    }
    
    /**
     * 게시판 : 삭제
     */
    @ResponseBody
    @RequestMapping(value= {"/vse/{lgrpCd}/delete.vc"} ,method=RequestMethod.POST)
    public Map<String, Object> vseBoardDel(@PathVariable("lgrpCd") String LGRP_CD, @ModelAttribute UserBoardVO boardVO) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>(); 
        
        boardVO.setLgrpCd(LGRP_CD.toUpperCase());
        
        mResult.put("result", userBoardService.deleteBoard(boardVO));
        
        return mResult;
    }
    
	 
    /**
	  * 엑셀 다운로드
	  */
	 @RequestMapping(value= "/vse/{lgrpCd}/allList.vc")
	 public String memberAllList(@PathVariable("lgrpCd") String LGRP_CD, @ModelAttribute UserBoardVO boardVO, ModelMap model) throws Exception {

		 String[] coulumTitle = null;
		 String fileName = "";
		 String sheetTitle = "";
		 String documentTitle = "";
		 
		 boardVO.setLgrpCd(LGRP_CD.toUpperCase());
		 
		 if("BUSINESS".equals(boardVO.getLgrpCd())) 
		 {
			 coulumTitle = new String[] {"이름", "연락처", "직업", "창업지역", "인지경로", "창업사유", "타석수", "등록일"};
			 fileName = "[VSE]창업상담_신청자목록";
			 sheetTitle = "창업상담 신청내역";
			 documentTitle = "창업상담 신청내역";
		 } 
		 else if ("PRESENTATION".equals(boardVO.getLgrpCd())) 
		 {
			 coulumTitle = new String[] {"설명회일정", "이름", "연락처", "직업", "창업지역", "인지경로", "창업사유", "타석수", "등록일"};
			 fileName = "[VSE]사업설명회_신청자목록";
			 sheetTitle = "사업설명회관리 신청내역";
			 documentTitle = "사업설명회관리 신청내역";		 
		 }
		 else
		 {
			 coulumTitle = new String[] {"이름", "연락처", "성별", "자격증", "주요경력사항", "선호근무지역", "선호근무시간", "지원사유", "등록일"};
			 fileName = "[VSE]프로지원서_신청자목록";
			 sheetTitle = "프로지원서내역";
			 documentTitle = "프로지원서내역";	
		 }
		 
		 model.addAttribute("fileName", fileName); 
		 model.addAttribute("sheetTitle", sheetTitle); 
		 model.addAttribute("documentTitle", documentTitle); 
		 model.addAttribute("columTitle", coulumTitle); 
		 
		 List<XMap> excelList = userBoardService.selectExcelList(boardVO);
		 
		 model.addAttribute("list", excelList);
		  
		 return "reportExcelView";
	 }
	 
	 
}