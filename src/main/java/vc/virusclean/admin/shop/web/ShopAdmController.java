package vc.virusclean.admin.shop.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jksoft.com.filter.FilterUtil;
import jksoft.com.util.StringUtil;
import jksoft.com.util.XMap;
import jksoft.com.web.XController;
import vc.virusclean.admin.shop.service.OriginProductService;
import vc.virusclean.admin.shop.service.ShopService;
import vc.virusclean.admin.shop.vo.OrderHistoryVO;
import vc.virusclean.admin.shop.vo.OrderVO;
import vc.virusclean.admin.shop.vo.OriginProductVO;
import vc.virusclean.admin.shop.vo.ShopVO;
import vc.virusclean.cmm.service.BoardService;
import vc.virusclean.cmm.service.CodeService;
import vc.virusclean.cmm.vo.BoardVO;
import vc.virusclean.cmm.vo.CodeEnvVO;

/**
 * <pre>
 * Shop 관리
 * </pre>
 * 
 * @ClassName   : ShopAdmController.java
 * @Description : @Controller
 */

@Controller
@RequestMapping(value= {"/admin"})
public class ShopAdmController extends XController {

	@Resource(name="boardService")   
    private BoardService boardService;
    
	@Resource(name="shopService")
    private ShopService shopService;
	
	@Resource(name="codeService")   
    private CodeService codeService;
	
	@Resource(name="originProductService")
	private OriginProductService originProductService;
    

    public String checkMenuId(String id) throws Exception{
    	String returnCode = "D";

    	return returnCode;
    }
    
    /**********************************************************************************************************************************/
    /**		상품주문		**/
    /**********************************************************************************************************************************/
    /**
     * 목록
     */
//    @RequestMapping(value= "/shop/order/list.vc")
//    public String orderList(@ModelAttribute ShopVO shopVO, ModelMap model) throws Exception{
//    	
//    	String MENU_NAME = "shop/order";
//    	String LGRP_CD = "shop";
//    	
//    	// search value decoding setting
//    	String searchKeyVal = FilterUtil.decodeHTML(shopVO.getSearchKey());
//    	shopVO.setSearchKey(searchKeyVal);
//		
//        model.addAttribute("result", shopService.selectShopList(shopVO));
//        model.addAttribute("requestUri", MENU_NAME);
//        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));
//        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
//        
//        return MENU_NAME + "/" + "order" + "List";
//    }
    
    /**
     * 상세 보기
     *
     * @param boardVO
     * @param model
     * @return
     * @throws Exception
     */
//    @RequestMapping(value= "/shop/order/view.vc")
//    public String orderDet(@ModelAttribute ShopVO shopVO, ModelMap model) throws Exception{
//        
//    	String MENU_NAME = "shop/order";
//    	String LGRP_CD = "shop";
//    	
//        model.addAttribute("result", shopService.selectShop(shopVO));
//        model.addAttribute("requestUri", MENU_NAME);
//        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));
//        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
//        
//        return MENU_NAME + "/" + "order" + "Det";
//    }
    
    /**
     * 수정
     */
//    @RequestMapping(value= "/order/modify.vc", method=RequestMethod.GET)
//    public String orderMod(@Valid @ModelAttribute ShopVO shopVO, ModelMap model) throws Exception{        
//        
//    	String MENU_NAME = "shop/order";
//    	String LGRP_CD = "shop";
//    	
//        model.addAttribute("result", shopService.selectShop(shopVO));
//        model.addAttribute("requestUri", MENU_NAME );
//        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));
//        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
//        
//        return MENU_NAME + "/" + "order" + "Mod";
//    }
    
    /**
     * 수정
     */
//    @ResponseBody
//    @RequestMapping(value= "/shop/order/modify.vc", method=RequestMethod.POST)
//    public Map<String, Object> orderMod(@Valid @ModelAttribute ShopVO shopVO, BindingResult bindingResult) throws Exception{
//        
//        Map<String, Object> mResult = new HashMap<String, Object>();
//        
//        if(bindingResult.hasErrors()){
//            for(FieldError f : bindingResult.getFieldErrors()){
//               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
//            }
//            mResult.put("bindingFields", bindingResult.getFieldErrors());
//            mResult.put("bindingStatus", false);
//            mResult.put("message", xMessageSource.getMessage("exception.binding"));
//        }else{
//            mResult.put("result", shopService.updateShop(shopVO));
//        }
//        
//        return mResult;
//    }

    /**
     * 삭제
     */
//    @ResponseBody
//    @RequestMapping(value= "/shop/order/delete.vc" ,method=RequestMethod.POST)
//    public Map<String, Object> orderDel(@ModelAttribute ShopVO shopVO) throws Exception{
//        
//        Map<String, Object> mResult = new HashMap<String, Object>(); 
//        
//        mResult.put("result", shopService.deleteShop(shopVO));
//        
//        return mResult;
//    }
    
    /**
     * 결제완료
     */
//    @ResponseBody
//    @RequestMapping(value= "/shop/order/payComp.vc" ,method=RequestMethod.POST)
//    public Map<String, Object> orderPayComp(@Valid @ModelAttribute ShopVO shopVO) throws Exception{
//        
//        Map<String, Object> mResult = new HashMap<String, Object>(); 
//        
//        mResult.put("result", shopService.updateShopPayComp(shopVO));
//        
//        return mResult;
//    }
    
    /**
     * 배송처리
     */
//    @ResponseBody
//    @RequestMapping(value= "/order/deliveryComp.vc" ,method=RequestMethod.POST)
//    public Map<String, Object> orderDeliveryComp(@Valid @ModelAttribute ShopVO shopVO) throws Exception{
//        
//        Map<String, Object> mResult = new HashMap<String, Object>(); 
//        
//        mResult.put("result", shopService.updateShopDeliveryComp(shopVO));
//        
//        return mResult;
//    }
    
    /**********************************************************************************************************************************/
    /**		정품관리		**/
    /**********************************************************************************************************************************/
    /**
     * 목록
     */
    @RequestMapping(value= {"/shop/origin/list.vc"})
    public String originList(@ModelAttribute OriginProductVO originProductVO, ModelMap model) throws Exception{
    	
    	// search value decoding setting
    	String searchKeyVal = FilterUtil.decodeHTML(originProductVO.getSearchKey());
    	originProductVO.setSearchKey(searchKeyVal);
		
        model.addAttribute("result", originProductService.selectProductList(originProductVO));
        model.addAttribute("requestUri","shop/origin" );
        model.addAttribute("pageMenuId", "E1");
        
        return "shop/origin/originList";
    }
    
    /**
     * 게시판 : 수정
     */
    @RequestMapping(value= {"/shop/origin/modify.vc", "/shop/origin/view.vc"}, method=RequestMethod.GET)
    public String originMod(@Valid @ModelAttribute OriginProductVO originProductVO, ModelMap model) throws Exception{        
        
		model.addAttribute("result", originProductService.selectProduct(originProductVO));
    	model.addAttribute("requestUri", "shop/origin" );
        model.addAttribute("pageMenuId", "E1");
        
        return "shop/origin/originMod";
    }
    
    /**
     * 정보 수정
     */
    @ResponseBody
    @RequestMapping(value= {"/shop/origin/modify.vc"}, method=RequestMethod.POST)
    public Map<String, Object> originMod(@Valid @ModelAttribute OriginProductVO originProductVO,  BindingResult bindingResult) throws Exception{        
        
    	Map<String, Object> mResult = new HashMap<String, Object>();
    	
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", originProductService.updateProduct(originProductVO));
        }
        
        return mResult;
    }
    
    
    /**
     * 게시판 : 삭제
     */
    @ResponseBody
    @RequestMapping(value= {"/shop/origin/delete.vc"} ,method=RequestMethod.POST)
    public Map<String, Object> originDel(@ModelAttribute OriginProductVO originProductVO) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>(); 
        
        mResult.put("result", originProductService.deleteProduct(originProductVO));
        
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
	 @RequestMapping(value={"/shop/origin/stcdMod.vc"}, method=RequestMethod.POST)
	 private Map<String, Object> originUpdateByStCd(@Valid @ModelAttribute OriginProductVO originProductVO, BindingResult bindingResult) throws Exception {
		Map<String, Object> mResult = new HashMap<String, Object>();
	    
	    if(bindingResult.hasErrors()){
	        for(FieldError f : bindingResult.getFieldErrors()){
	           log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
	        }
	        mResult.put("bindingFields", bindingResult.getFieldErrors());
	        mResult.put("bindingStatus", false);
	        mResult.put("message", xMessageSource.getMessage("exception.binding"));
	        
	    }else{
	        mResult.put("result", originProductService.updateProductByAplyYn(originProductVO));
	    }
	    
	    return mResult;	
	 }
	 
	 
	 /**********************************************************************************************************************************/
	 /**		주문관리		**/
	 /**********************************************************************************************************************************/
	 
	 /**
	  * 주문현황
	  */
	 @RequestMapping(value= {"/shop/order/list.vc"})
	 public String orderList(@ModelAttribute OrderVO orderVO, ModelMap model) throws Exception{
    	
    	// search value decoding setting
    	String searchKeyVal = FilterUtil.decodeHTML(orderVO.getSearchKey());
    	orderVO.setSearchKey(searchKeyVal);
		
    	model.addAttribute("result", shopService.selectOrderList(orderVO));
        model.addAttribute("requestUri","shop/order" );
        model.addAttribute("pageMenuId", "C1");
        
        return "shop/order/orderList";
	 }
	 
	 
	 /**
	  * 주문상세
	  */
	 @RequestMapping(value= {"/shop/order/view.vc"})
	 public String orderDet(@ModelAttribute OrderVO orderVO, ModelMap model) throws Exception{
    	
    	// search value decoding setting
    	String searchKeyVal = FilterUtil.decodeHTML(orderVO.getSearchKey());
    	orderVO.setSearchKey(searchKeyVal);
		
    	model.addAttribute("result", shopService.selectOrder(orderVO));
        model.addAttribute("requestUri","shop/order" );
        model.addAttribute("pageMenuId", "C1");
        
        return "shop/order/orderMan";
	 }

	 
	 /**
      * 주문취소 / 반품 / 교환신청
      */
    @ResponseBody
    @RequestMapping(value= "/shop/order/status/modify.vc", method=RequestMethod.POST)
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
            mResult.put("result", shopService.insertOrderHistoryAdm(vo));
        }
        
        return mResult;
    }
	 
	 
	 /**
	  * 엑셀 다운로드
	  */
	 @SuppressWarnings("unchecked")
	 @RequestMapping(value= "/shop/order/allList.vc")
	 public String memberAllList(@ModelAttribute OrderVO orderVO, ModelMap model) throws Exception {
		 

		 String[] coulumTitle = {"주문번호", "주문일자", "결제수단", "상품명", "옵션", "수량", "수취인명", "수취인핸드폰", "주소", "배송메세지", "주문자명", "주문자핸드폰", "택배사",
		 							"송장번호", "변환상품명", "사은품", "출고일자", "판매금액 합계", "쿠폰", "미수금", "시리얼번호", "주문상태", "배송비", "배송시작시간", "배송완료시간"};
		 
		 model.addAttribute("fileName", "[바이러스클린랩]주문내역"); 
		 model.addAttribute("sheetTitle", "주문내역"); 
		 model.addAttribute("documentTitle", "바이러스 클린랩 주문내역"); 
		 model.addAttribute("columTitle", coulumTitle); 
		 
		 List<XMap> excelList = shopService.selectOrderExcelList(orderVO);
		 
		 if(excelList != null){
            //본문 생성
            for(Map<String, Object> row : excelList){
            	row.put("receiverHp" , StringUtil.addMinusCharHp(StringUtil.isNullToString((String)row.get("receiverHp"))));
            }
        }
		 
		 model.addAttribute("list", excelList);
		  
		 return "reportExcelView";
	 }
	 
	 /**
	  * 송장등록
	  * - 발송처리
	  */
	 @ResponseBody
	 @RequestMapping(value= "/shop/invoice/register.vc", method=RequestMethod.POST)
	 public Map<String, Object> invoiceReg(@Valid @ModelAttribute BoardVO vo, BindingResult bindingResult) throws Exception{
	    
		 Map<String, Object> mResult = new HashMap<String, Object>();
	    
		 if(bindingResult.hasErrors()){
			 for(FieldError f : bindingResult.getFieldErrors()){
				 log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
			 }
			 mResult.put("bindingFields", bindingResult.getFieldErrors());
			 mResult.put("bindingStatus", false);
			 mResult.put("message", xMessageSource.getMessage("exception.binding"));
		 }else{
			 mResult.put("result", shopService.insertInvoiceFormFile(vo));
		 }
	    
		 return mResult;
	 }
	 
    
    /**********************************************************************************************************************************/
    /**		환경설정		**/
    /**********************************************************************************************************************************/
    /**
     * 운영정책관리
     */
    @RequestMapping(value= "/shop/preferences/modify.vc",method=RequestMethod.GET)
    public String preferences(@ModelAttribute ShopVO shopVO, ModelMap model) throws Exception{
    	
    	String MENU_NAME = "shop/preferences";
    	
        model.addAttribute("result", codeService.selectCodePreferences());
        model.addAttribute("requestUri", MENU_NAME);
        model.addAttribute("pageMenuId", "I2");
        
        return MENU_NAME + "Man";
    }
    
    @ResponseBody
    @RequestMapping(value= "/shop/preferences/modify.vc", method=RequestMethod.POST)
    public Map<String, Object> preferences(@Valid @ModelAttribute CodeEnvVO codeVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", codeService.updateCodePreference(codeVO));
        }
        
        return mResult;
    }
}