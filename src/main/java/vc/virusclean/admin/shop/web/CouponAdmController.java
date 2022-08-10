package vc.virusclean.admin.shop.web;

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

import jksoft.com.filter.FilterUtil;
import jksoft.com.web.XController;
import vc.virusclean.admin.member.service.MemberService;
import vc.virusclean.admin.member.vo.MemberVO;
import vc.virusclean.admin.shop.service.CouponService;
import vc.virusclean.admin.shop.vo.CouponMetaVO;
import vc.virusclean.admin.shop.vo.CouponVO;

/**
 * <pre>
 * 쿠폰관리용 CONTROLLER
 * </pre>
 * 
 * @ClassName   : CouponAdmController.java
 * @Description : @Controller
 */

@Controller
@RequestMapping(value= {"/admin"})
public class CouponAdmController extends XController {

	@Resource(name="couponService")
    private CouponService couponService;
	
	@Resource(name="memberService")
	private MemberService memberService;
    

    public String checkMenuId(String id) throws Exception{
    	String returnCode = "D1";
    	
    	if("shop/coupon/info".equals(id)){
    		returnCode = "D1";
    	} else if("shop/coupon/user".equals(id)){
    		returnCode = "D2";
    	}
    	
    	return returnCode;
    }
    
    
    /**
	 * 쿠폰지급 대상 회원 목록 조회
	 */
    @ResponseBody
    @RequestMapping(value= "/shop/coupon/member/list.vc", method=RequestMethod.POST)
	public Map<String, Object> memberList(@ModelAttribute MemberVO memberVO) throws Exception {
		 
    	Map<String, Object> mResult = new HashMap<String, Object>();
    	
		// search value decoding setting
		String searchKeyVal =  FilterUtil.decodeHTML(memberVO.getSearchKey());
		memberVO.setSearchKey(searchKeyVal);
		memberVO.setIsUse(true);
		mResult.put("result", memberService.selectMemberList(memberVO));
		  
		return mResult;
	}
	 
	 
    /**
     * 쿠폰메다데이터의 목록을 조회한다.
     */
    @RequestMapping(value= "/shop/coupon/info/list.vc")
    public String couponMetaList(@ModelAttribute CouponMetaVO vo, ModelMap model) throws Exception{
    	
    	String MENU_NAME = "shop/coupon/info";
    	String LGRP_CD = "info";
    	
    	// search value decoding setting
    	String searchKeyVal = FilterUtil.decodeHTML(vo.getSearchKey());
    	vo.setSearchKey(searchKeyVal);
		
        model.addAttribute("result", couponService.selectCouponMetaList(vo));
        model.addAttribute("requestUri", MENU_NAME);
        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));
        
        return MENU_NAME + "/" + LGRP_CD + "List";
    }
    
    /**
	 * 환영쿠폰설정을 위한 쿠폰조회
	 */
    @ResponseBody
    @RequestMapping(value= "/shop/preferences/coupon/list.vc", method=RequestMethod.POST)
    public Map<String, Object> preferencesCouponMetaList(@ModelAttribute CouponMetaVO vo, ModelMap model) throws Exception{
		 
    	Map<String, Object> mResult = new HashMap<String, Object>();
    	
		// search value decoding setting
    	String searchKeyVal = FilterUtil.decodeHTML(vo.getSearchKey());
    	vo.setSearchKey(searchKeyVal);
		vo.setIsUse(true);
		
		mResult.put("result", couponService.selectCouponMetaList(vo));
		  
		return mResult;
	}
	 
    
    /**
     * 쿠폰메다데이터의 정보를 조회한다.
     */
    @RequestMapping(value= "/shop/coupon/info/view.vc")
    public String couponMetaDet(@ModelAttribute CouponMetaVO vo, ModelMap model) throws Exception{
        
    	String MENU_NAME = "shop/coupon/info";
    	String LGRP_CD = "info";
        
        model.addAttribute("result", couponService.selectCouponMeta(vo));
        model.addAttribute("requestUri", MENU_NAME);
        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));
        
        return MENU_NAME + "/" + LGRP_CD + "Det";
    }
    
    
    /**
     * 쿠폰메다데이터의  정보를 입력폼
     */
    @RequestMapping(value = "/shop/coupon/info/register.vc", method=RequestMethod.GET)
    public String couponMetaReg(@ModelAttribute CouponMetaVO vo, ModelMap model) throws Exception{
    	
    	String MENU_NAME = "shop/coupon/info";
    	String LGRP_CD = "info";
        
        model.addAttribute("result", null );
        model.addAttribute("requestUri", MENU_NAME);
        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));
        
        return MENU_NAME + "/" + LGRP_CD + "Reg";
    }
    
    /**
     * 쿠폰메다데이터의  정보를 등록
     */
    @ResponseBody
    @RequestMapping(value= "/shop/coupon/info/register.vc", method=RequestMethod.POST)
    public Map<String, Object> couponMetaReg(@Valid @ModelAttribute CouponMetaVO vo, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
    	
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", couponService.insertCouponMeta(vo));
        }
        
        return mResult;
    }
    
    /**
     * 쿠폰메다데이터의  정보 수정 폼
     */
    @RequestMapping(value= "/shop/coupon/info/modify.vc", method=RequestMethod.GET)
    public String couponMetaMod(@Valid @ModelAttribute CouponMetaVO vo, ModelMap model) throws Exception{        
        
    	String MENU_NAME = "shop/coupon/info";
    	String LGRP_CD = "info";
    	
        model.addAttribute("result", couponService.selectCouponMetaByMod(vo));
        model.addAttribute("requestUri", MENU_NAME );
        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));
        
        return MENU_NAME + "/" + LGRP_CD + "Mod";
    }
    
    /**
     * 쿠폰메다데이터의  정보를  수정
     */
    @ResponseBody
    @RequestMapping(value= "/shop/coupon/info/modify.vc", method=RequestMethod.POST)
    public Map<String, Object> couponMetaMod(@Valid @ModelAttribute CouponMetaVO vo, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", couponService.updateCouponMeta(vo));
        }
        
        return mResult;
    }

    /**
     * 쿠폰메다데이터의  정보를 삭제
     */
    @ResponseBody
    @RequestMapping(value= "/shop/coupon/info/delete.vc" ,method=RequestMethod.POST)
    public Map<String, Object> couponMetaDel(@ModelAttribute CouponMetaVO vo) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>(); 
        
        mResult.put("result", couponService.deleteCouponMeta(vo));
        
        return mResult;
    }
    
    
    /**
     * 쿠폰메타데이터별 발급 쿠폰 목록
     */
    @RequestMapping(value= {"/shop/coupon/info/usable/list.vc"})
    public String couponMetaCouponList(@ModelAttribute CouponVO vo, ModelMap model) throws Exception{
    	
    	String MENU_NAME = "shop/coupon/info";
    	String LGRP_CD = "usable";
	    	
    	// search value decoding setting
    	String searchKeyVal = FilterUtil.decodeHTML(vo.getSearchKey());
    	vo.setSearchKey(searchKeyVal);
		
    	//쿠폰정보
    	CouponMetaVO couponMetaVO = new CouponMetaVO();
    	couponMetaVO.setCupMetaSn(vo.getCupMetaSn());
    	model.addAttribute("result", couponService.selectCouponMeta(couponMetaVO));
    	
    	//발급쿠폰목록
    	model.addAttribute("result2", couponService.selectCouponAdmList(vo));
    	
    	model.addAttribute("requestUri", MENU_NAME + "/usable" );
        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));	
        return MENU_NAME + "/" + LGRP_CD + "List";
  	}
    
    /*
     *  관리자가 사용자에게 쿠폰을 지급한다.
     */
    @ResponseBody
    @RequestMapping(value= "/shop/coupon/member/register.vc", method=RequestMethod.POST)
    public Map<String, Object> couponDownReg(@Valid @ModelAttribute CouponMetaVO vo, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", couponService.insertCouponAdmForUserRegister(vo));
        }
        
        return mResult;
    }
    
    
    /**********************************************************************************************************************************/
    /**		쿠폰 발급		**/
	/**********************************************************************************************************************************/
    /**
     * 쿠폰 발급 목록
     */
    @RequestMapping(value= {"/shop/coupon/user/list.vc"})
    public String couponUserList(@ModelAttribute CouponVO vo, ModelMap model) throws Exception{
    	
    	String MENU_NAME = "shop/coupon/user";
    	String LGRP_CD = "user";
	    	
    	// search value decoding setting
    	String searchKeyVal = FilterUtil.decodeHTML(vo.getSearchKey());
    	vo.setSearchKey(searchKeyVal);
		
    	model.addAttribute("result", couponService.selectCouponAdmList(vo));
    	model.addAttribute("requestUri", MENU_NAME );
        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));	
        
        return MENU_NAME + "/" + LGRP_CD + "List";
  	}
    
}