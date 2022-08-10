package vc.virusclean.user.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import jksoft.com.util.MultiUtil;
import jksoft.com.web.XController;
import vc.virusclean.admin.member.vo.MemberVO;
import vc.virusclean.admin.shop.service.CouponService;
import vc.virusclean.admin.shop.service.OriginProductService;
import vc.virusclean.admin.shop.service.ShopService;
import vc.virusclean.admin.shop.vo.CouponVO;
import vc.virusclean.admin.shop.vo.OrderHistoryVO;
import vc.virusclean.admin.shop.vo.OrderVO;
import vc.virusclean.admin.shop.vo.OriginProductVO;
import vc.virusclean.cmm.service.BoardService;
import vc.virusclean.cmm.service.CodeService;
import vc.virusclean.cmm.service.UserBoardService;
import vc.virusclean.cmm.vo.UserBoardVO;
import vc.virusclean.user.service.UserMemberService;
import vc.virusclean.user.vo.LoginVO;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : UserController.java
 * @Description : 클래스 설명을 기술합니다.
 */
@Controller
public class UserController extends XController {

	/**
     * MultiUtil Class 선언 (QaaService Class Injection)
     */
    @Resource(name="multiUtil")
    private MultiUtil multiUtil;
    
    @Resource(name="userMemberService")
	private UserMemberService userMemberService;
    
    @Resource(name="boardService")
	private BoardService boardService;
	
	@Resource(name="shopService")
	private ShopService shopService;
	
	@Resource(name = "codeService")
	private CodeService codeService;
	
	@Resource(name="userBoardService")
	private UserBoardService userBoardService;
	
	@Resource(name="originProductService")
	private OriginProductService originProductService;
	
	@Resource(name="couponService")
    private CouponService couponService;
    
    /**
     * LOGIN
     * @return
     * @throws Exception
     */
    @AuthCheck(loginCheck=false)
    @RequestMapping(value={"/member/login.vc"}, method=RequestMethod.GET)
    public String login(HttpServletRequest httpServletRequest, ModelMap model) throws Exception{     
    	model.addAttribute("pageMenuId", "KA");
    	
    	/*
    	 * SNS 간편 로그인 관련 세션 삭제처리
    	 */
        HttpSession httpSession = httpServletRequest.getSession(false);
        httpSession.removeAttribute("snsCd");       
        httpSession.removeAttribute("snsMbrId");       
        httpSession.removeAttribute("snsMbrEml");       
        httpSession.removeAttribute("snsLogin");       
        
        return "member/loginMan";
    }
    
    /**
     * LOGIN
     * @return
     * @throws Exception
     */
    @AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value={"/member/login.vc", "/api/member/login.vc"}, method=RequestMethod.POST)
    public Map<String, Object> login(@Valid @ModelAttribute LoginVO loginVO, BindingResult bindingResult) throws Exception{     
    	
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
             mResult.put("result", userMemberService.selectAuth(loginVO));  
         }
         
         return mResult;
    }
    
    
    /**
     * APP에서 mbrSn으로  사용자 정보 조회
     * @return
     * @throws Exception
     */
    @AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value={"/api/member/info.vc"} ,method=RequestMethod.POST)
    public Map<String, Object> apiGetMemberInfo(@Valid @ModelAttribute LoginVO loginVO, BindingResult bindingResult) throws Exception{     
    	
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
            mResult.put("result", userMemberService.selectApiMember(loginVO));  
        }
        
        return mResult;
   }
    
    /**
     * LOGOUT
     * @return
     * @throws Exception
     */
    @AuthCheck(loginCheck=false)
    @RequestMapping(value="/member/logout.vc", method=RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest) throws Exception{  
    	httpServletRequest.getSession(false).invalidate();
    	return "redirect:/index.vc";
    }
    
    
    /**
     * 비밀번호 찾가 > 비밀번호 변경
     *
     * @param authVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value="/member/changeNewPw.vc", method=RequestMethod.POST)
    public Map<String, Object> loginNewPw(@Valid @ModelAttribute LoginVO loginVO, BindingResult bindingResult) throws Exception {
       
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
            mResult.put("result", userMemberService.updateNewPassword(loginVO));            
        }
        
        return mResult;
    }
    
    /**
     * 개인정보 페이지 전에 로그인 화면.
     * @return
     * @throws Exception
     */
    @RequestMapping(value={"/member/myInfo.vc"}, method=RequestMethod.GET)
    public String myInfo(ModelMap model) throws Exception{     
    	model.addAttribute("pageMenuId", "KC");
        return "member/info/loginMan";
    }
    
    /**
     * 개인정보 페이지 전에 로그인 화면.
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value={"/member/myInfo.vc"}, method=RequestMethod.POST)
    public Map<String, Object> myInfo(@Valid @ModelAttribute LoginVO loginVO, BindingResult bindingResult) throws Exception{     
    	
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
             mResult.put("result", userMemberService.selectPrivacyAuth(loginVO));            
         }
         
         return mResult;
    }
    
    
    /**
     * 비밀번호확인 
     * @return
     * @throws Exception
     */
    //@AuthCheck(privacyCheck=true)
//    @RequestMapping(value={"/myInfo/changePassword.vc"} ,method=RequestMethod.GET)
//    public String changePassword(ModelMap model) throws Exception{     
//    	model.addAttribute("pageMenuId", "KCB");
//    	model.addAttribute("result", userMemberService.selectMember());
//        
//    	return "member/info/changePasswordMan";
//    }
    
    /**
     * 비밀번호변경 
     * @return
     * @throws Exception
     */
    //@AuthCheck(privacyCheck=true)
//    @ResponseBody
//    @RequestMapping(value={"/myInfo/changePassword.vc"} ,method=RequestMethod.POST)
//    public Map<String, Object> changePassword(@Valid @ModelAttribute MemberVO memberVO, BindingResult bindingResult) throws Exception{     
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
//            mResult.put("result", userMemberService.updateMember(memberVO));            
//        }
//        
//        return mResult;
//    }
    
    /**
     * 개인정보수정
     * @return
     * @throws Exception
     */
    @RequestMapping(value={"/member/modify-personal-data.vc"} ,method=RequestMethod.GET)
    public String changeInfo(ModelMap model) throws Exception{     
    	model.addAttribute("pageMenuId", "KMEB");
    	model.addAttribute("result", userMemberService.selectMember());
    	
        return "member/info/changeInfoMan";
    }
    
    /**
     * 개인정보수정
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value={"/member/modify-personal-data.vc"} ,method=RequestMethod.POST)
    public Map<String, Object> changeInfo(@Valid @ModelAttribute MemberVO memberVO, BindingResult bindingResult) throws Exception{     
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
            mResult.put("result", userMemberService.updateMember(memberVO));            
        }
        
        return mResult;
    }
    
    
    /**
     * 개인정보수정
     * @return
     * @throws Exception
     */
    @AuthCheck(loginCheck=false)
    @RequestMapping(value={"/api/member/modify-personal-data.vc"}, method=RequestMethod.POST)
    public String changeInfoApi(@ModelAttribute MemberVO memberVO, ModelMap model) throws Exception{     
    	model.addAttribute("result", userMemberService.selectMemberApiMod(memberVO));
    	
        return "member/info/changeInfoMan";
    }
    
    
    /**
     * 탈퇴신청
     * @return
     * @throws Exception
     */
//    @AuthCheck(privacyCheck=true)
//    @RequestMapping(value={"/member/myInfo/withdraw/apply.vc"} ,method=RequestMethod.GET)
//    public String withdrowApply(ModelMap model) throws Exception{     
//    	
//    	model.addAttribute("pageMenuId", "KDA");
//    	model.addAttribute("result", userMemberService.selectMember());
//    	
//        return "member/withdraw/applyMan";
//    }
    
    /**
     * 탈퇴신청 등록
     * @param memberVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @AuthCheck(privacyCheck=true)
    @ResponseBody
    @RequestMapping(value= "/member/membership-withdrawal.vc", method=RequestMethod.POST)
    public Map<String, Object> withdrowApply(@Valid @ModelAttribute MemberVO memberVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
            
        }else{
        	memberVO.setIsApi(true);
            mResult.put("result", userMemberService.deleteMember(memberVO));
        }
        
        return mResult;
    }
    
    
    /**
     * 탈퇴신청 완료
     * @return
     * @throws Exception
     */
//    @AuthCheck(privacyCheck=true)
//    @RequestMapping(value={"/member/myInfo/withdraw/complete.vc"} ,method=RequestMethod.GET)
//    public String withdrowComplete(HttpServletRequest httpServletRequest, ModelMap model) throws Exception{     
//    	
//    	httpServletRequest.getSession(false).invalidate();
//    	
//    	model.addAttribute("pageMenuId", "KDB");
//        return "member/withdraw/completeMan";
//    }
    
    
    /**
     * 마이페이지 메인(대쉬보드)
     * @return
     * @throws Exception
     */
    @RequestMapping(value={"/member.vc"}, method=RequestMethod.GET)
    public String memberMain(ModelMap model) throws Exception{     
    	
    	//최근 주문내역
    	OrderVO orderVO = new OrderVO();
    	orderVO.setIsApi(true);
    	orderVO.setRowLimit(3);
    	model.addAttribute("resultOrder", shopService.selectOrderList(orderVO));
    	
    	//최신 3개
    	UserBoardVO boardVO = new UserBoardVO();
    	boardVO.setIsApi(true);
		boardVO.setLgrpCd("QNA");
		boardVO.setIndividual(true);
		boardVO.setRowLimit(3);
		model.addAttribute("resultQna", userBoardService.selectBoardList(boardVO));
		
		//보유제품
		OriginProductVO originProductVO = new OriginProductVO();
		originProductVO.setIsApi(true);
		originProductVO.setIsIndividual(true);
		originProductVO.setRowLimit(3);
		model.addAttribute("resultOrigin", originProductService.selectProductList(originProductVO));
		
		model.addAttribute("memberInfo", userMemberService.selectMember());
        
    	model.addAttribute("pageMenuId", "KMEA");
        return "member/mainMan";
    }
    
    /**
     * 마이페이지 > 쿠폰함 > 사용가능
     * @return
     * @throws Exception
     */
    @RequestMapping(value={"/member/coupon/active/list.vc"}, method=RequestMethod.GET)
    public String couponActiveList(ModelMap model) throws Exception{     
    	
    	//내가 가진 쿠폰 목록 조회
    	CouponVO couponVO = new CouponVO();
    	couponVO.setUseYn("Y");
    	model.addAttribute("result", couponService.selectCouponUserList(couponVO));
    	
    	//다운로드가능 쿠폰 목록 조회
    	model.addAttribute("resultDown", couponService.selectEventCouponList());
    	
    	model.addAttribute("memberInfo", userMemberService.selectMember());
    	
    	model.addAttribute("pageMenuId", "KMEEA");
    	
        return "member/coupon/couponList";
    }
    
    /**
     * 마이페이지 > 쿠폰함 > 사용한
     * @return
     * @throws Exception
     */
    @RequestMapping(value={"/member/coupon/inactive/list.vc"}, method=RequestMethod.GET)
    public String couponInactiveList(ModelMap model) throws Exception{     
    	
    	//내가 가진 쿠폰 목록 조회
    	CouponVO couponVO = new CouponVO();
    	couponVO.setUseYn("N");
    	model.addAttribute("result", couponService.selectCouponUserList(couponVO));
    	
    	//다운로드가능 쿠폰 목록 조회
    	model.addAttribute("resultDown", couponService.selectEventCouponList());
    	
    	model.addAttribute("memberInfo", userMemberService.selectMember());
    	
    	model.addAttribute("pageMenuId", "KMEEB");
    	
        return "member/coupon/couponList";
    }
    
    
    /**
     * 주문 > 할인가능 쿠폰 목록 조회
     */
    @ResponseBody
    @RequestMapping(value={"/member/coupon/order/list.vc"}, method=RequestMethod.POST)
    public Map<String, Object> couponOrderList() throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        CouponVO couponVO = new CouponVO();
        couponVO.setUseYn("Y");
        mResult.put("result", couponService.selectCouponUserList(couponVO));
        
        return mResult;
    }

    
    /**
     * 마이페이지 > 쿠폰등록
     */
    @ResponseBody
    @RequestMapping(value= "/member/coupon/register.vc", method=RequestMethod.POST)
    public Map<String, Object> couponReg(@Valid @ModelAttribute CouponVO vo, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", couponService.insertCouponUserRegister(vo));
        }
        
        return mResult;
    }
    
    /**
     * 마이페이지 > 다운로드쿠폰 다운받기
     */
    @ResponseBody
    @RequestMapping(value= "/member/coupon/down.vc", method=RequestMethod.POST)
    public Map<String, Object> couponDownReg(@Valid @ModelAttribute CouponVO vo, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", couponService.insertCouponUserDownload(vo));
        }
        
        return mResult;
    }
    
    
    
    /**********************************************************************************************************************************/
    /**		문의		**/
    /**********************************************************************************************************************************/
    /**
     * 마이페이지 > 문의목록
     */
    @RequestMapping(value= "/member/qna/list.vc")
    public String qnaList(@ModelAttribute UserBoardVO boardVO, ModelMap model) throws Exception{
    	
    	boardVO.setIsApi(true);
		boardVO.setLgrpCd("QNA");    //게시판 아이디
		boardVO.setIndividual(true);
		
    	model.addAttribute("pageMenuId", "KMEG");
        model.addAttribute("result", userBoardService.selectBoardList(boardVO));
        
        model.addAttribute("memberInfo", userMemberService.selectMember());
        
        model.addAttribute("requestUri", "member/qna");
        
        return "member/qna/qnaList";
    }
    
    /**
     * 마이페이지 > 문의 상세조회
     * @param boardVO
     * @param model
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value= "/member/qna/view.vc", method=RequestMethod.POST)
    public Map<String, Object> qnaDet(@Valid @ModelAttribute UserBoardVO boardVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	boardVO.setIsApi(true);
    		boardVO.setLgrpCd("QNA"); 
    		boardVO.setIndividual(true);
    		
            mResult.put("result", userBoardService.selectBoard(boardVO));
        }
        
        return mResult;
    }
    
    /**
     * 마이페이지 > 문의 입력
     */
    @ResponseBody
    @RequestMapping(value= "/member/qna/register.vc", method=RequestMethod.POST)
    public Map<String, Object> qnaReg(@Valid @ModelAttribute UserBoardVO boardVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	boardVO.setLgrpCd("QNA"); 
        	boardVO.setIsApi(true);
            mResult.put("result", userBoardService.insertBoard(boardVO));
        }
        
        return mResult;
    }
    
    /**
     * 마이페이지 > 문의 수정
     */
    @ResponseBody
    @RequestMapping(value= "/member/qna/modify.vc", method=RequestMethod.POST)
    public Map<String, Object> qnaMod(@Valid @ModelAttribute UserBoardVO boardVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	boardVO.setLgrpCd("QNA"); 
        	boardVO.setIsApi(true);
            mResult.put("result", userBoardService.updateBoard(boardVO));
        }
        
        return mResult;
    }
    
    /**********************************************************************************************************************************/
    /**		정품등록/보유목록		**/
    /**********************************************************************************************************************************/
    /**
     * 마이페이지 > 보유목록
     * @return
     * @throws Exception
     */
    @RequestMapping(value={"/member/product/list.vc"}, method=RequestMethod.GET)
    public String productList(@ModelAttribute OriginProductVO originProductVO, ModelMap model) throws Exception{     
    	
    	originProductVO.setIsApi(true);
    	originProductVO.setIsIndividual(true);
    	
    	model.addAttribute("pageMenuId", "KMEF");
    	model.addAttribute("result", originProductService.selectProductList(originProductVO));
    	
    	model.addAttribute("memberInfo", userMemberService.selectMember());
    	
    	model.addAttribute("requestUri", "member/product");

        return "member/product/productList";
    }
    
    /**
     * 마이페이지 > 정품등록 폼
     * @return
     * @throws Exception
     */
    @RequestMapping(value={"/member/product/register.vc"}, method=RequestMethod.GET)
    public String productReg(ModelMap model) throws Exception{
    	
    	model.addAttribute("pageMenuId", "KMEFA");
    	model.addAttribute("requestUri", "member/product");
    	
    	model.addAttribute("memberInfo", userMemberService.selectMember());
    	
        return "member/product/productInfoReg";
    }
    
    /**
     * 마이페이지 > 정품등록 입력
     */
    @ResponseBody
    @RequestMapping(value= "/member/product/register.vc", method=RequestMethod.POST)
    public Map<String, Object> productReg(@Valid @ModelAttribute OriginProductVO originProductVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	originProductVO.setIsApi(true);
            mResult.put("result", originProductService.insertProduct(originProductVO));
        }
        
        return mResult;
    }
    
    /**
     * 마이페이지 > 정품등록 완료
     * @return
     * @throws Exception
     */
    @RequestMapping(value={"/member/product/complete.vc"}, method=RequestMethod.GET)
    public String productComplete(ModelMap model) throws Exception{    
    	
    	model.addAttribute("pageMenuId", "KMEFC");
    	model.addAttribute("requestUri", "member/product");
    	
    	model.addAttribute("memberInfo", userMemberService.selectMember());
    	
        return "member/product/productCompleteMan";
    }
    
    /**********************************************************************************************************************************/
    /**		구입목록		**/
    /**********************************************************************************************************************************/
    /**
     * 마이페이지 > 주문내역
     * @return
     * @throws Exception
     */
    @RequestMapping(value={"/member/order/active/list.vc"}, method=RequestMethod.GET)
    public String orderActiveList(@ModelAttribute OrderVO orderVO, ModelMap model) throws Exception{     
    	
    	orderVO.setIsApi(true);
    	orderVO.setSearchSubType("active");
    	model.addAttribute("resultOrder", shopService.selectOrderList(orderVO));
    	
    	
    	model.addAttribute("pageMenuId", "KMEDA");
    	model.addAttribute("requestUri", "member/order/active");
    	
    	model.addAttribute("memberInfo", userMemberService.selectMember());
    	
        return "member/order/orderList";
    }

    
    @RequestMapping(value={"/member/order/inactive/list.vc"}, method=RequestMethod.GET)
    public String orderInactiveList(@ModelAttribute OrderVO orderVO, ModelMap model) throws Exception{     
    	
    	orderVO.setIsApi(true);
    	orderVO.setSearchSubType("inactive");
    	model.addAttribute("resultOrder", shopService.selectOrderList(orderVO));
    	
    	model.addAttribute("pageMenuId", "KMEDB");
    	model.addAttribute("requestUri", "member/order/inactive");
    	
    	model.addAttribute("memberInfo", userMemberService.selectMember());
    	
        return "member/order/orderList";
    }
    
    /**
     * 마이페이지 > 주문 상세
     */
    @RequestMapping(value={"/member/order/active/view.vc"}, method=RequestMethod.GET)
    public String orderActiveView(@ModelAttribute OrderVO orderVO, ModelMap model) throws Exception{     
    	
    	orderVO.setIsApi(true);
    	model.addAttribute("result", shopService.selectOrder(orderVO));
    	
    	model.addAttribute("pageMenuId", "KMEDA");
    	model.addAttribute("requestUri", "member/order/active");
    	
    	model.addAttribute("memberInfo", userMemberService.selectMember());
    	
        return "member/order/orderDet";
    }
    
    /**
     * 마이페이지 > 주문 상세
     */
    @RequestMapping(value={"/member/order/inactive/view.vc"}, method=RequestMethod.GET)
    public String orderInactiveView(@ModelAttribute OrderVO orderVO, ModelMap model) throws Exception{     
    	
    	orderVO.setIsApi(true);
    	model.addAttribute("result", shopService.selectOrder(orderVO));
    	
    	model.addAttribute("pageMenuId", "KMEDB");
    	model.addAttribute("requestUri", "member/order/inactive");
    	
    	model.addAttribute("memberInfo", userMemberService.selectMember());
    	
        return "member/order/orderDet";
    }
    
    /**
     * 주문취소 / 반품 / 교환신청
     */
    @AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value= "/member/order/cancle.vc", method=RequestMethod.POST)
    public Map<String, Object> orderCancle(@Valid @ModelAttribute OrderHistoryVO vo, BindingResult bindingResult) throws Exception{
    	
    	Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
            
        }else{
        	vo.setIsApi(true);
            mResult.put("result", shopService.insertOrderHistory(vo));
        }
        
        return mResult;
    }
}
