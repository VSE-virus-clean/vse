package vc.virusclean.admin.member.web;

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

import vc.virusclean.admin.auth.vo.AuthVO;
import vc.virusclean.admin.member.service.MemberService;
import vc.virusclean.admin.member.vo.MemberVO;
import jksoft.com.annotation.AuthCheck;
import jksoft.com.filter.FilterUtil;
import jksoft.com.util.MultiUtil;
import jksoft.com.web.XController;

/**
 * <pre>
 * 회원관리 CONTROLLER
 * </pre>
 * 
 * @ClassName   : MemberController.java
 * @Description : @Controller
 */

@Controller
@RequestMapping(value= {"/admin"})
public class MemberController extends XController  {

	/**
     * MultiUtil Class 선언 (QaaService Class Injection)
     */
    @Resource(name="multiUtil")
    private MultiUtil multiUtil;
    
    @Resource(name="memberService")
	private MemberService memberService;
    
    public final String PAGE_PATH = "member/info";
    
    public String checkMenuId() throws Exception{
    	
    	String returnCode = "G1";
    	
    	return returnCode;
    }
    
	/**
	 * 회원정보 : 목록 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	 @RequestMapping(value= "/member/info/list.vc")
	 public String memberList(@ModelAttribute MemberVO memberVO, ModelMap model) throws Exception {
		 
		 // search value decoding setting
		 String searchKeyVal =  FilterUtil.decodeHTML(memberVO.getSearchKey());
		 memberVO.setSearchKey(searchKeyVal);
		 
		 model.addAttribute("result", memberService.selectMemberList(memberVO));
		 model.addAttribute("requestUri", PAGE_PATH);
		 model.addAttribute("pageMenuId", this.checkMenuId());
		  
		 return "/member/info/memberList";
	 }
	
	/**
	 * 회원정보 : 상세보기
	 * @param model
	 * @return
	 * @throws Exception
	 */
	 @RequestMapping(value= "/member/info/view.vc")
	 public String memberDet(@ModelAttribute MemberVO memberVO, ModelMap model) throws Exception {
		
		 //기본 정보
		 model.addAttribute("result", memberService.selectMember(memberVO));
		 model.addAttribute("requestUri", PAGE_PATH);
		 model.addAttribute("pageMenuId", this.checkMenuId());
		 
		 return "/member/info/memberDet";
	 }
	 
	/**
     * 회원정보 : 수정
     */
    @ResponseBody
    @RequestMapping(value= "/member/info/modify.vc", method=RequestMethod.POST)
    public Map<String, Object> memberMod(@Valid @ModelAttribute MemberVO memberVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", memberService.updateMember(memberVO));
        }
        
        return mResult;
    }

	 
	 /**
	  * 전체 비밀번호 이메일로 리셋
	  * @return
	  * @throws Exception
	  */
	 @ResponseBody
	 @RequestMapping(value= "/member/info/resetMemberPw.vc")
	 public Map<String, Object> resetMemberPw() throws Exception{
		 
		 Map<String, Object> mResult = new HashMap<String, Object>();
		 
		 mResult.put("result", memberService.updateByAllPasswordReset());
		 
		 return mResult;
	 }
	 
	 /**
	  * 유저 비밀번호 이메일로 리셋
	  * @return
	  * @throws Exception
	  */
	 @ResponseBody
	 @RequestMapping(value= "/member/info/resetPassword.vc")
	 public Map<String, Object> resetPassword(@ModelAttribute MemberVO memberVO) throws Exception{
		 
		 Map<String, Object> mResult = new HashMap<String, Object>();
		 
		 mResult.put("result", memberService.updateByPasswordReset(memberVO));
		 
		 return mResult;
	 }
	 
	 
	 /**
	  * 회원정보 : 목록 조회
	  * @param model
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping(value= "/member/info/allList.vc")
	 public String memberAllList(@ModelAttribute MemberVO memberVO, ModelMap model) throws Exception {
		 
		 String[] coulumTitle = {"아이디", "이름", "닉네임", "등급", "휴대번호", "이메일", "성별", "생년월일", "나이", "우편번호", "주소", "커뮤니티 접근권한 여부", "이메일/SMS 동의여부", "가입경로", "가입일"};
		 
		 model.addAttribute("fileName", "[바이러스클린랩]회원목록"); 
		 model.addAttribute("sheetTitle", "회원목록"); 
		 model.addAttribute("documentTitle", "바이러스 클린랩 회원 목록"); 
		 model.addAttribute("columTitle", coulumTitle); 
		 model.addAttribute("list", memberService.selectMemberAllList(memberVO));
		  
		 return "reportExcelView";
	 }
	 
	 /**
      * 계정 상태값 변경
      *
      * @param authVO2
      * @param mSession
      * @throws Exception 
     */
	 @ResponseBody
	 @RequestMapping(value="/member/info/stcdMod.vc", method=RequestMethod.POST)
	 private Map<String, Object> updateByStCd(@Valid @ModelAttribute MemberVO memberVO, BindingResult bindingResult) throws Exception {
		Map<String, Object> mResult = new HashMap<String, Object>();
	    
	    if(bindingResult.hasErrors()){
	        for(FieldError f : bindingResult.getFieldErrors()){
	           log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
	        }
	        mResult.put("bindingFields", bindingResult.getFieldErrors());
	        mResult.put("bindingStatus", false);
	        mResult.put("message", xMessageSource.getMessage("exception.binding"));
	        
	    }else{
	        mResult.put("result", memberService.updateByStCd(memberVO));
	    }
	    
	    return mResult;	
	 }
	 
}
