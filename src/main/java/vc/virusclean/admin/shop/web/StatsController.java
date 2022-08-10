package vc.virusclean.admin.shop.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jksoft.com.filter.FilterUtil;
import jksoft.com.util.StringUtil;
import jksoft.com.util.XMap;
import jksoft.com.web.XController;
import jksoft.com.web.vo.SearchVO;
import vc.virusclean.admin.shop.service.StatsService;
import vc.virusclean.admin.shop.vo.OrderVO;

/**
 * <pre>
 * 통계 관리 CONTROLLER
 * </pre>
 * 
 * @ClassName   : StatsController.java
 * @Description : @Controller
 */

@Controller
@RequestMapping(value= {"/admin"})
public class StatsController extends XController {

	@Resource(name="statsService")
    private StatsService statsService;
    

    public String checkMenuId(String id) throws Exception{
    	String returnCode = "B1";
    	
    	if("stats/sales".equals(id)){
    		returnCode = "H1";
    	} else if("stats/accounts".equals(id)){
    		returnCode = "H2";
    	} else if("stats/products".equals(id)){
    		returnCode = "H3";
    	} else if("stats/origins".equals(id)){
    		returnCode = "H4";
    	}
    	
    	return returnCode;
    }
    
    
    /**
     * 매출 통계
     */
    @RequestMapping(value= "/stats/sales.vc")
    public String salesMan(@ModelAttribute SearchVO vo, ModelMap model) throws Exception{
    	
    	String MENU_NAME = "stats/sales";
    	
    	// search value decoding setting
    	String searchKeyVal = FilterUtil.decodeHTML(vo.getSearchKey());
    	vo.setSearchKey(searchKeyVal);
		
        model.addAttribute("result", statsService.selectStatsSales(vo));
        model.addAttribute("requestUri", MENU_NAME);
        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));
        
        return MENU_NAME + "Man";
    }
    
    /**
     * 매출 통계 - 엑셀다운로드
     */
	@RequestMapping(value= "/stats/sales/excel.vc")
	public String salesExcelMan(@ModelAttribute SearchVO vo, ModelMap model) throws Exception {
		 
		 String[] coulumTitle = {"일자", "주문수", "상품구매금액", "배송비", "쿠폰", "결제합계", "환불합계", "순매출"};
		 
		 String title = "";
		 
		 if("day".equals(vo.getSearchType())){
			 title = "일별";
		 }
		 if("month".equals(vo.getSearchType())){
			 title = "월별";
		 }
		 if("year".equals(vo.getSearchType())){
			 title = "년별";
		 }
		 
		 model.addAttribute("fileName", "[바이러스클린랩]" + title + "매출통계"); 
		 model.addAttribute("sheetTitle", "매출통계"); 
		 model.addAttribute("documentTitle", title + "매출통계 " + "(" + vo.getSearchStartDate() + " ~ " + vo.getSearchEndDate() + ")"); 
		 model.addAttribute("columTitle", coulumTitle); 
		 
		 List<XMap> excelList = statsService.selectStatsSalesExcel(vo);
		 
//		 if(excelList != null){
//			 //본문 생성
//			 for(Map<String, Object> row : excelList){
//				 row.put("receiverHp" , StringUtil.addMinusCharHp(StringUtil.isNullToString((String)row.get("receiverHp"))));
//			 }
//		 }
		 
		 model.addAttribute("list", excelList);
		  
		 return "reportExcelView";
	}
    
    /**
     * 정산 통계
     */
    @RequestMapping(value= "/stats/accounts.vc")
    public String accountsMan(@ModelAttribute SearchVO vo, ModelMap model) throws Exception{
    	
    	String MENU_NAME = "stats/accounts";
    	
    	// search value decoding setting
    	String searchKeyVal = FilterUtil.decodeHTML(vo.getSearchKey());
    	vo.setSearchKey(searchKeyVal);
		
        model.addAttribute("result", statsService.selectStatsAccounts(vo));
        model.addAttribute("requestUri", MENU_NAME);
        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));
        
        return MENU_NAME + "Man";
    }
    
    /**
     * 정산 통계 - 엑셀다운로드
     */
	@RequestMapping(value= "/stats/accounts/excel.vc")
	public String accountsExcelMan(@ModelAttribute SearchVO vo, ModelMap model) throws Exception {
		 
		 String[] coulumTitle = {"월", "결제금액", "전월정산", "당월정산", "총합계"};
		 
		 
		 String title = vo.getSearchStartDate() + "년";
		 
		 model.addAttribute("fileName", "[바이러스클린랩]" + title + "_정산통계"); 
		 model.addAttribute("sheetTitle", "정산통계"); 
		 model.addAttribute("documentTitle", title + " 정산통계"); 
		 model.addAttribute("columTitle", coulumTitle); 
		 
		 List<XMap> excelList = statsService.selectStatsAccountsExcel(vo);
		 
		 model.addAttribute("list", excelList);
		  
		 return "reportExcelView";
	}
    
    /**
     * 상품관리 통계
     */
    @RequestMapping(value= "/stats/products.vc")
    public String productsMan(@ModelAttribute SearchVO vo, ModelMap model) throws Exception{
    	
    	String MENU_NAME = "stats/products";
    	
    	// search value decoding setting
    	String searchKeyVal = FilterUtil.decodeHTML(vo.getSearchKey());
    	vo.setSearchKey(searchKeyVal);
		
        model.addAttribute("result", statsService.selectStatsProducts(vo));
        model.addAttribute("requestUri", MENU_NAME);
        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));
        
        return MENU_NAME + "Man";
    }
    
    /**
     * 상품관리 통계 - 엑셀다운로드
     */
	@RequestMapping(value= "/stats/products/excel.vc")
	public String productsExcelMan(@ModelAttribute SearchVO vo, ModelMap model) throws Exception {
		 
		 String[] coulumTitle = {"순위", "상품명", "상품금액", "결제수", "취소/환불수", "결제확정수", "합계"};
		 
		 
		 model.addAttribute("fileName", "[바이러스클린랩]상품관리통계"); 
		 model.addAttribute("sheetTitle", "상품관리통계"); 
		 model.addAttribute("documentTitle", "상품관리통계 " + "(" + vo.getSearchStartDate() + " ~ " + vo.getSearchEndDate() + ")"); 
		 model.addAttribute("columTitle", coulumTitle); 
		 
		 List<XMap> excelList = statsService.selectStatsProductsExcel(vo);
		 
		 
		 if(excelList != null){
			 //본문 생성
			 for(XMap row : excelList){
				 row.remove("fileSn");
				 row.remove("filNm");
			 }
		 }
		 
		 model.addAttribute("list", excelList);
		  
		 return "reportExcelView";
	}

}