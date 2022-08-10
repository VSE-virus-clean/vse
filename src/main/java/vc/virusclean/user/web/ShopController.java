package vc.virusclean.user.web;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jksoft.com.annotation.AuthCheck;
import jksoft.com.util.MultiUtil;
import jksoft.com.util.StringUtil;
import jksoft.com.web.XController;
import vc.virusclean.admin.shop.service.CartService;
import vc.virusclean.admin.shop.service.OriginProductService;
import vc.virusclean.admin.shop.service.ProductService;
import vc.virusclean.admin.shop.service.ShopService;
import vc.virusclean.admin.shop.vo.BuyProductVO;
import vc.virusclean.admin.shop.vo.CartVO;
import vc.virusclean.admin.shop.vo.OrderVO;
import vc.virusclean.admin.shop.vo.ProductVO;
import vc.virusclean.admin.shop.vo.ScrapVO;
import vc.virusclean.cmm.service.BoardService;
import vc.virusclean.cmm.service.CodeService;
import vc.virusclean.cmm.service.UserBoardService;
import vc.virusclean.cmm.vo.UserBoardVO;


/**
 * <pre>
 * 상품 관련 Controller
 * </pre>
 *
 * @ClassName   : ShopController.java
 * @Description : 상품목록 / 결제
 */
@Controller
public class ShopController extends XController {
	
	@Resource(name="multiUtil")
    protected MultiUtil multiUtil;
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	@Resource(name="shopService")
	private ShopService shopService;
	
	@Resource(name="productService")
    private ProductService productService;
	
	@Resource(name="userBoardService")
	private UserBoardService userBoardService;
	
	@Resource(name="originProductService")
	private OriginProductService originProductService;
	
	@Resource(name="cartService")
	private CartService cartService;
	
	@Resource(name = "codeService")
	private CodeService codeService;
	
	
	//상품목록
	@AuthCheck(loginCheck=false)
	@RequestMapping(value= "/shop/{menuName}/list.vc" )
	public String shopList(@PathVariable("menuName") String MENU_NAME, @ModelAttribute ProductVO vo, ModelMap model) throws Exception{     

		String pageMenuId = "";
		vo.setIsApi(true);
		
		if("virus-clean-lab".equals(MENU_NAME)){
			vo.setLgrpCd("VCL");
			pageMenuId = "KSAA";
    	} else if("accessory".equals(MENU_NAME)){
    		vo.setLgrpCd("ACC");
    		pageMenuId = "KSAB";
    	}
		
		model.addAttribute("result", productService.selectProductList(vo));
		model.addAttribute("requestUri", "shop/" + MENU_NAME);
		model.addAttribute("pageMenuId", pageMenuId);
		
        return "shop/shopList";
    }
	
	
	//상품상세
	@AuthCheck(loginCheck=false)
    @RequestMapping(value= "/shop/{menuName}/view.vc")
    public String shopDet(@PathVariable("menuName") String MENU_NAME,@ModelAttribute ProductVO vo, ModelMap model) throws Exception{

		String pageMenuId = "";
		
		//상품정보
		vo.setIsApi(true);
		
		if("virus-clean-lab".equals(MENU_NAME)){
			vo.setLgrpCd("VCL");
			pageMenuId = "KSAA";
    	} else if("accessory".equals(MENU_NAME)){
    		vo.setLgrpCd("ACC");
    		pageMenuId = "KSAB";
    	}
		model.addAttribute("result", productService.selectProduct(vo));

		//문의
		UserBoardVO boardVO = new UserBoardVO();
    	boardVO.setIsApi(true);
		boardVO.setLgrpCd("QNA");
		boardVO.setPrdSn(vo.getPrdSn());
		model.addAttribute("resultQna", userBoardService.selectBoardList(boardVO));
		
		//리뷰
		UserBoardVO boardVO2 = new UserBoardVO();
    	boardVO2.setIsApi(true);
		boardVO2.setLgrpCd("REVIEW");
		boardVO2.setPrdSn(vo.getPrdSn());
		model.addAttribute("resultReview", userBoardService.selectBoardList(boardVO2));
		
		//모바일여부 확인
		model.addAttribute("deviceCode", multiUtil.checkDevice());
		model.addAttribute("requestUri", "shop/" + MENU_NAME);
		model.addAttribute("pageMenuId", pageMenuId);
		
		return "shop/shopDet";
    }
	
	/**
     * 마이페이지 > 문의 상세조회
     * @param boardVO
     * @param model
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value= "/shop/qna/view.vc", method=RequestMethod.POST)
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
    		
            mResult.put("result", userBoardService.selectBoard(boardVO));
        }
        
        return mResult;
    }
	
	//결제 정보 입력
	//가격 등을 가져온다.
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/shop/order/register.vc"})
    public String register(@ModelAttribute BuyProductVO buyProductVO, ModelMap model) throws Exception{  
		
		String strRtn = "shop/orderRegisterReg";
		
		if(buyProductVO.getPrdSn() != null && !buyProductVO.getPrdSn().isEmpty())
		{
			//모바일여부 확인
			model.addAttribute("deviceCode", multiUtil.checkDevice());
			
			//배송관련 정보 조회 : 예정일 정보 조회해서 4.01(목)형태로 만들어야함.
			model.addAttribute("resultCode", codeService.selectCodePreferences());
	
			//구매제품 정보
			model.addAttribute("result", productService.selectProductOrderList(buyProductVO));

			model.addAttribute("requestUri", "shop/order");
			model.addAttribute("pageMenuId", "H");
		}
		else
		{
			strRtn = "redirect:/index.vc";
		}
		
		
    	return strRtn;
    	
    }
	
	//결제 정보 확인 및 결제
	// - 받은 내용 그대로 처리
//	@AuthCheck(loginCheck=false)
//    @RequestMapping(value={"/shop/order/registerConfirming.vc"}, method=RequestMethod.POST)
//    public String registerConfirming(@Valid @ModelAttribute ShopVO shopVO, BindingResult bindingResult, ModelMap model) throws Exception{     
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
//        	//상품정보 조회
//        	BoardVO boardVO = new BoardVO();
//    		boardVO.setIsApi(true);
//    		boardVO.setLgrpCd("SHOP"); 
//    		boardVO.setBlcSn(shopVO.getCotnSn()); 
//    		
//            model.addAttribute("resulItem", boardService.selectBoard(boardVO));
//            
//            model.addAttribute("result",shopVO);
//        }
//        model.addAttribute("pageMenuId", "H");
//    	return "shop/orderRegisterDet";
//    }
	
	
	//결제 정보 입력
	//결제관련 정보 생성
	@AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value={"/shop/order/registerReg.vc"}, method=RequestMethod.POST)
    public Map<String, Object> shopOrderRegister(@Valid @ModelAttribute OrderVO orderVO, @ModelAttribute BuyProductVO buyProductVO, BindingResult bindingResult) throws Exception{     
        
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
            mResult.put("result", shopService.insertOrder(orderVO, buyProductVO));            
        }
        
        return mResult;
    }
	
	
	//주문 완료
	@AuthCheck(loginCheck=false)
    @RequestMapping("/shop/order/complete.vc")
    public String shopOrderComplete(@ModelAttribute OrderVO orderVO, HttpServletRequest httpServletRequest, ModelMap model) throws Exception{     
		
		HttpSession httpSession = httpServletRequest.getSession(false);
        
		orderVO.setApi(true);
		orderVO.setOrderNo((String)httpSession.getAttribute("payment_order_no"));
		model.addAttribute("result", shopService.selectInfoOrderNo(orderVO));
		
		httpSession.removeAttribute("payment_order_no");       
		
    	return "shop/orderCompleteMan";
    }
	
	
	/** ***********************************************************************************
	 * 상품 리뷰
	 ** ***********************************************************************************/
	/**
     * 리뷰작성
     */
    @ResponseBody
    @RequestMapping(value= "/shop/review/register.vc", method=RequestMethod.POST)
    public Map<String, Object> reviewReg(@Valid @ModelAttribute UserBoardVO boardVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	boardVO.setLgrpCd("REVIEW"); 
        	boardVO.setIsApi(true);
            mResult.put("result", userBoardService.insertBoard(boardVO));
        }
        
        return mResult;
    }
    
    /**
     * 리뷰수정 : 불러오기
     */
    @ResponseBody
    @RequestMapping(value= "/shop/review/view.vc", method=RequestMethod.POST)
    public Map<String, Object> reviewDet(@ModelAttribute UserBoardVO boardVO) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boardVO.setLgrpCd("REVIEW"); 
    	boardVO.setIsApi(true);
    	
        mResult.put("result", userBoardService.selectBoardByMod(boardVO));
        
        return mResult;
    }
    
    /**
     * 리뷰수정 : 변경
     */
    @ResponseBody
    @RequestMapping(value= "/shop/review/modify.vc", method=RequestMethod.POST)
    public Map<String, Object> reviewMod(@ModelAttribute UserBoardVO boardVO) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boardVO.setLgrpCd("REVIEW"); 
    	boardVO.setIsApi(true);
    	
        mResult.put("result", userBoardService.updateBoard(boardVO));
        
        return mResult;
    }
    
    
    /**
     * 리뷰작성 : 삭제하기
     */
    @ResponseBody
    @RequestMapping(value= "/shop/review/delete.vc", method=RequestMethod.POST)
    public Map<String, Object> reviewDel(@ModelAttribute UserBoardVO boardVO) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boardVO.setLgrpCd("REVIEW"); 
    	boardVO.setIsApi(true);
    	
        mResult.put("result", userBoardService.deleteBoard(boardVO));
        
        return mResult;
    }
	
	/** ***********************************************************************************
	 * 장바구니
	 ** ***********************************************************************************/
    /**
     * 장바구니  - 목록 조회
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/shop/cart.vc"}, method=RequestMethod.GET)
    public String cartList(@ModelAttribute CartVO cartVO, ModelMap model) throws Exception{     
    	
    	model.addAttribute("result", cartService.selectList(cartVO));
    	
    	//TODO : 배송관련 정보 조회 : 예정일 정보 조회해서 4.01(목)형태로 만들어야함.
    	model.addAttribute("resultCode", codeService.selectCodePreferences());
    	
    	//모바일여부 확인
		model.addAttribute("deviceCode", multiUtil.checkDevice());
    	model.addAttribute("pageMenuId", "KSAC");
    	model.addAttribute("requestUri", "shop");
    	
        return "shop/shopCartMan";
    }
    
    
    /**
     * 장바구니 - 추가
     */
	@AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value= "/shop/cart/add.vc", method=RequestMethod.POST)
    public Map<String, Object> cartAdd(@ModelAttribute CartVO cartVO) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        mResult.put("result", cartService.insertCart(cartVO));
        
        return mResult;
    }
	
	
	/**
	 * 장바구니 - 삭제
	 */
	@AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value= "/shop/cart/delete.vc", method=RequestMethod.POST)
    public Map<String, Object> cartDel(@ModelAttribute CartVO cartVO) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        mResult.put("result", cartService.deleteCart(cartVO));
        
        return mResult;
    }
    
    
    /** ***********************************************************************************
	 * 상품 / 리뷰 좋아요 !!
	 ** ***********************************************************************************/
	/**
	 * 스크랩
	 */
	@AuthCheck(roleCheck=false)
	@ResponseBody
    @RequestMapping(value= "/shop/scrap/add.vc", method=RequestMethod.POST)
    public Map<String, Object> scrapAdd(@ModelAttribute ScrapVO scrapVO) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
//        scrapVO.setScrapCd("SCRAP");
        
        mResult.put("result", shopService.insertScrap(scrapVO));
        
        return mResult;
    }
	
	/**
	 * 스크랩
	 */
	@AuthCheck(roleCheck=false)
	@ResponseBody
    @RequestMapping(value= "/shop/scrap/del.vc", method=RequestMethod.POST)
    public Map<String, Object> scrapDel(@ModelAttribute ScrapVO scrapVO) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
//        scrapVO.setScrapCd("SCRAP");
        
        mResult.put("result", shopService.deleteScrap(scrapVO));
        
        return mResult;
    }
	
	
	/** ***********************************************************************************
	 * 비회원주문내역조회 : (주문자)이름/주문번호
	 ** ***********************************************************************************/
	/*
	 * AJAX로 확인해서 맞으면 페이지 이동 : 일회용 세션 생성
	 */
	@AuthCheck(loginCheck=false)
//	@ResponseBody
	@RequestMapping(value={"/shop/guest/order/inquiry.vc"}, method=RequestMethod.POST)
    public String orderActiveView(@ModelAttribute OrderVO orderVO, ModelMap model) throws Exception{     

		String strRtn = "redirect:/member/login.vc";
		
		if(!StringUtil.isEmpty(orderVO.getBuyerNm()) && !StringUtil.isEmpty(orderVO.getOrderNo()))
		{
			orderVO.setIsIndividual(true);
			model.addAttribute("result", shopService.selectOrder(orderVO));
			strRtn = "shop/guest/orderDet";
		}
    	
        return strRtn;
    }
	
	
	/** ***********************************************************************************
	 * 네이버 주문정보 조회 : https://pay.naver.com/customer/order.nhn
	 ** ***********************************************************************************/
	@AuthCheck(loginCheck=false)
	@ResponseBody
    @RequestMapping(value={"/shop/npay/order.vc"})
    public Map<String, Object> npayOrder(@ModelAttribute BuyProductVO buyProductVO) throws Exception{  
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		if(buyProductVO.getPrdSn() != null && !buyProductVO.getPrdSn().isEmpty()){
			mResult.put("result", productService.selectProductNPayOrder(buyProductVO));
		}
		
		return mResult;
    }
	
	/** ***********************************************************************************
	 * 네이버 찜상품 등록
	 ** ***********************************************************************************/
	@AuthCheck(loginCheck=false)
	@ResponseBody
    @RequestMapping(value={"/shop/npay/wish.vc"})
    public Map<String, Object> npayWish(@ModelAttribute BuyProductVO buyProductVO) throws Exception{  
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		if(buyProductVO.getPrdSn() != null && !buyProductVO.getPrdSn().isEmpty()) {
			mResult.put("result", productService.selectProductNPayWish(buyProductVO));
		}
		
		return mResult;
    }
	
	/** ***********************************************************************************
	 * 네이버 상품정보 조회 - http://127.0.0.1:8080/shop/npay/item.vc?ITEM_ID=10
	 ** ***********************************************************************************/
	@AuthCheck(loginCheck=false)
	@ResponseBody
    @RequestMapping(value={"/shop/npay/item.vc"}, produces = {MediaType.APPLICATION_XML_VALUE})
    public void npayItemInfo(@ModelAttribute BuyProductVO buyProductVO, HttpServletResponse reHttpServletResponse) throws Exception{  
		
		StringBuilder itemXml = new StringBuilder();
		itemXml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		itemXml.append("<response>");
		itemXml.append(productService.selectProductNPayItemInfo(buyProductVO));
		itemXml.append("</response>");
		
		reHttpServletResponse.setContentType("application/xml;charset=UTF-8");
		Writer writer = reHttpServletResponse.getWriter();
		writer.write(itemXml.toString());
		writer.flush();
		writer.close();
    }

}
