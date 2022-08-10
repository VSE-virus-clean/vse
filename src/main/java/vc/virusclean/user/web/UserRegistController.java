package vc.virusclean.user.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import vc.virusclean.user.service.UserMemberService;
import jksoft.com.annotation.AuthCheck;
import jksoft.com.web.XController;

/**
 * <pre>
 * 회원 가입 관련 Controller
 * </pre>
 *
 * @ClassName   : UserRegistController.java
 * @Description : 클래스 설명을 기술합니다.
 */
@Controller
public class UserRegistController extends XController {
	
	@Resource(name="userMemberService")
	private UserMemberService userMemberService;
	
    /**
     * 약관동의 : step1
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/registration/agree.vc"}, method=RequestMethod.GET)
    public String agree(ModelMap model) throws Exception{     
		model.addAttribute("pageMenuId", "MA");
        return "registration/agreeMan";
    }
    
    
    /**
     * 정보입력  : step2
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/registration/register.vc"}, method=RequestMethod.POST)
    public String register(@ModelAttribute MemberVO memberVO, ModelMap model) throws Exception{  
		
		model.addAttribute("result", memberVO);
		model.addAttribute("pageMenuId", "MB");
		
    	return "registration/registerReg";
    }
    

//    /**
//     * 정보입력확인  : step3
//     * 
//     * - 받은 내용 그대로 처리
//     * @return
//     * @throws Exception
//     */
//	@AuthCheck(loginCheck=false)
//    @RequestMapping(value={"/registerConfirming.vc"}, method=RequestMethod.POST)
//    public String registerConfirming(@Valid @ModelAttribute MemberVO memberVO, BindingResult bindingResult, ModelMap model) throws Exception{     
//    	
//    	Map<String, Object> mResult = new HashMap<String, Object>();
//        
//        if(bindingResult.hasErrors()){
//            for(FieldError f : bindingResult.getFieldErrors()){
//                if(log.isDebugEnabled()){
//                    log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure());
//                }
//            }            
//            mResult.put("bindingFields", bindingResult.getFieldErrors());
//            mResult.put("bindingStatus", false);
//            mResult.put("message", xMessageSource.getMessage("exception.binding"));
//        }else{
//            model.addAttribute("result",memberVO);
//        }
//        model.addAttribute("pageMenuId", "MC");
//    	return "registration/registerDet";
//    }
    
    /**
     * 정보 시스템에 등록
     * - 완료페이지 링크를 내려준다.
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value={"/registration/registerReg.vc"}, method=RequestMethod.POST)
    public Map<String, Object> registerReg(@Valid @ModelAttribute MemberVO memberVO, BindingResult bindingResult) throws Exception{     
        
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
            mResult.put("result", userMemberService.insertMember(memberVO));            
        }
        
        return mResult;
    }
    
    /**
     * 신청완료  : step3
     * 
     * -메일발송
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping("/registration/complete.vc")
    public String complete(ModelMap model) throws Exception{     
    	model.addAttribute("pageMenuId", "MD");
    	return "registration/completeMan";
    }
	
	
	
	
	
	/**
     * 본인인증 약관동의 : step1
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/certification/agree.vc"}, method=RequestMethod.GET)
    public String certAgree(HttpServletRequest httpServletRequest, ModelMap model) throws Exception{  
		
		String rtn = "redirect:/index.vc";
		
		if(httpServletRequest.getSession(false).getAttribute("certReg") != null){
			 rtn = "registration/certAgreeMan";
		}
		
        return rtn;
    }
    
    
    /**
     * 본인인증 정보입력  : step2
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/certification/register.vc"}, method=RequestMethod.POST)
    public String certRegister(@ModelAttribute MemberVO memberVO, HttpServletRequest httpServletRequest, ModelMap model) throws Exception{  
		
		String rtn = "redirect:/index.vc";
		
		if(httpServletRequest.getSession(false).getAttribute("certReg") != null){
			 rtn = "registration/certRegisterReg";
			 model.addAttribute("result", userMemberService.selectMemberCert());
			 model.addAttribute("result2", memberVO);
			 model.addAttribute("pageMenuId", "MB");
		}
		
		
    	return rtn;
    }
	
	 /**
     * 본인인증 등록
     * - 완료페이지 링크를 내려준다.
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value={"/certification/registerReg.vc"}, method=RequestMethod.POST)
    public Map<String, Object> certRegisterReg(@Valid @ModelAttribute MemberVO memberVO, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws Exception{     
        
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
            mResult.put("result", userMemberService.updateCertInfo(memberVO));            
        }
        
        return mResult;
    }
    
    /**
     * 본인인증 완료  : step3
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping("/certification/complete.vc")
    public String certComplete(ModelMap model) throws Exception{     
    	model.addAttribute("pageMenuId", "MD");
    	return "registration/certCompleteMan";
    }
	
	
    /**
     * 중목체크
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value={"/api/registration/checkDuplicate.vc", "/registration/checkDuplicate.vc", "/member/checkDuplicate.vc"}, method=RequestMethod.POST)
    public Map<String, Object> checkDuplicate(@Valid @ModelAttribute MemberVO memberVO, BindingResult bindingResult) throws Exception{     
    	
    	Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
            
        }else{
            mResult.put("result", userMemberService.selectMemberCheckDuplicate(memberVO));
        }
        
        return mResult;	
    }
}
