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

import vc.virusclean.admin.member.vo.MemberVO;
import vc.virusclean.cmm.service.BoardService;
import vc.virusclean.cmm.vo.BoardVO;
import vc.virusclean.cmm.vo.SmsCertVO;
import vc.virusclean.user.service.HelpService;
import jksoft.com.annotation.AuthCheck;
import jksoft.com.web.XController;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : HelpController.java
 * @Description : 클래스 설명을 기술합니다.
 */
@Controller
@RequestMapping("/help")
public class HelpController extends XController {

	/**
     * BoardService class 선언 (BoardService Class Injection)
     * (BoardService)boardService
     */   
    @Resource(name="boardService")   
    private BoardService boardService;
	
	@Resource(name="helpService")
	private HelpService helpService;
	
	
	/**
     * QNA
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping("/qna.vc")
    public String qna(ModelMap model) throws Exception{     
		model.addAttribute("pageMenuId", "LB");
        return "help/qnaMan";
	}
	
	/**
     * QNA
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value={"/qna.vc"}, method=RequestMethod.POST)
    public Map<String, Object> qna(@Valid @ModelAttribute BoardVO boardVO, BindingResult bindingResult) throws Exception{     
		
		Map<String, Object> mResult = new HashMap<String, Object>();
         
         if(bindingResult.hasErrors()){
             for(FieldError f : bindingResult.getFieldErrors()){
                 if(log.isDebugEnabled()){
                     log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure());
                 }
             }            
             mResult.put("bindingFields", bindingResult.getFieldErrors());
             mResult.put("bindingStatus", false);
             mResult.put("message", xMessageSource.getMessage("exception.binding"));
         }else{
        	 boardVO.setIsApi(true);
         	 boardVO.setLgrpCd("QNA");
             mResult.put("result", boardService.insertBoard(boardVO));   
         }
         
         return mResult;
    }
	
	
    /**
     * 아이디찾기
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
	@RequestMapping(value={"/idInquiry.vc"}, method=RequestMethod.GET)
    public String idInquiry(ModelMap model) throws Exception{     
		model.addAttribute("pageMenuId", "LC");
    	return "help/idInquiryMan";
    }
    
    /**
     * 아이디찾기
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value={"/idInquiry.vc"}, method=RequestMethod.POST)
    public Map<String, Object> idInquiry(@Valid @ModelAttribute MemberVO memberVO, @ModelAttribute SmsCertVO smsCertVO, BindingResult bindingResult) throws Exception{     
		
		Map<String, Object> mResult = new HashMap<String, Object>();
         
         if(bindingResult.hasErrors()){
             for(FieldError f : bindingResult.getFieldErrors()){
                 if(log.isDebugEnabled()){
                     log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure());
                 }
             }            
             mResult.put("bindingFields", bindingResult.getFieldErrors());
             mResult.put("bindingStatus", false);
             mResult.put("message", xMessageSource.getMessage("exception.binding"));
         }else{
             mResult.put("result", helpService.findId(memberVO, smsCertVO));            
         }
         
         return mResult;
    }
    
    /**
     * 비밀번호찾기
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/pwInquiry.vc"}, method=RequestMethod.GET)
    public String pwInquiry(ModelMap model) throws Exception{     
		model.addAttribute("pageMenuId", "LD");
    	return "help/pwInquiryMan";
    }
    
    /**
     * 비밀번호찾기
     * - 임시세션을 생성한다.
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value={"/pwInquiry.vc"}, method=RequestMethod.POST)
	public Map<String, Object> pwInquiry(@Valid @ModelAttribute MemberVO memberVO, @ModelAttribute SmsCertVO smsCertVO, BindingResult bindingResult) throws Exception{     
		
		Map<String, Object> mResult = new HashMap<String, Object>();
         
         if(bindingResult.hasErrors()){
             for(FieldError f : bindingResult.getFieldErrors()){
                 if(log.isDebugEnabled()){
                     log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure());
                 }
             }            
             mResult.put("bindingFields", bindingResult.getFieldErrors());
             mResult.put("bindingStatus", false);
             mResult.put("message", xMessageSource.getMessage("exception.binding"));
         }else{
             mResult.put("result", helpService.findPassword(memberVO, smsCertVO));            
         }
         
         return mResult;
    }
	
	
	/**
     * SMS 인증 번호 발급
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value={"/sms/certSmsSend.vc"}, method=RequestMethod.POST)
    public Map<String, Object> smsCertNoSend(@Valid @ModelAttribute SmsCertVO vo, BindingResult bindingResult) throws Exception{     
		
		Map<String, Object> mResult = new HashMap<String, Object>();
         
         if(bindingResult.hasErrors()){
             for(FieldError f : bindingResult.getFieldErrors()){
                 if(log.isDebugEnabled()){
                     log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure());
                 }
             }            
             mResult.put("bindingFields", bindingResult.getFieldErrors());
             mResult.put("bindingStatus", false);
             mResult.put("message", xMessageSource.getMessage("exception.binding"));
         }else{
             mResult.put("result", helpService.smsCertNoSend(vo));            
         }
         
         return mResult;
    }
    
}
