package vc.virusclean.admin.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jksoft.com.service.XAbstractService;
import jksoft.com.util.DateUtil;
import jksoft.com.util.StringUtil;
import jksoft.com.util.XMap;
import jksoft.com.web.vo.SearchVO;
import vc.virusclean.admin.shop.service.StatsService;
import vc.virusclean.admin.shop.service.dao.StatsDAO;


/**
 * <pre>
 * 보안로그 서비스 구현
 * </pre>
 */
@Service("statsService")
public class StatsServiceImpl extends XAbstractService implements StatsService {

    @Resource(name="statsDAO")   
    private StatsDAO statsDAO;


    /*
     * 게시물 접속 로그를 조회한다.
     */
    public Map<String, Object> selectDashboard() throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();

        mResult.put("info", statsDAO.selectDashboard());

        return mResult;

    }
    
    /**
     * 매출 통계
     */
    public Map<String, Object> selectStatsSales(SearchVO vo) throws Exception {
    	
    	 Map<String, Object> mResult = new HashMap<String, Object>();
         
         mResult.put("list", this.selectStatsSalesExcel(vo));
         
         mResult.put("searchInfo", vo);

         return mResult;
    }
    
    /**
     * 매출 통계
     */
    public List<XMap> selectStatsSalesExcel(SearchVO vo) throws Exception {
    	
	   	 if(StringUtil.isEmpty(vo.getSearchType())){
        	vo.setSearchType("day");
         }

	   	 if("day".equals(vo.getSearchType())){
        	if(StringUtil.isEmpty(vo.getSearchEndDate())){
            	vo.setSearchEndDate(DateUtil.formatDate());
            }
            
            if(StringUtil.isEmpty(vo.getSearchStartDate())){
            	vo.setSearchStartDate(StringUtil.addMinusChar(DateUtil.addDay(vo.getSearchEndDate().replaceAll("-", ""), -7)));
            }
	   	 }else if("month".equals(vo.getSearchType())){
        	if(StringUtil.isEmpty(vo.getSearchEndDate())){
            	vo.setSearchEndDate(DateUtil.formatDate("yyyy-MM"));
            }
            
            if(StringUtil.isEmpty(vo.getSearchStartDate())){
            	vo.setSearchStartDate(StringUtil.addMinusChar(DateUtil.addYear((vo.getSearchEndDate() + "-01").replaceAll("-", ""), -1)).substring(0, 7));
            }
	   	 }else if("year".equals(vo.getSearchType())){
        	
        	if(StringUtil.isEmpty(vo.getSearchEndDate())){
            	vo.setSearchEndDate(DateUtil.formatDate("yyyy"));
            }
            
            if(StringUtil.isEmpty(vo.getSearchStartDate())){
            	vo.setSearchStartDate(StringUtil.addMinusChar(DateUtil.addYear((vo.getSearchEndDate() + "-01-01").replaceAll("-", ""), -1)).substring(0, 4));
            }
	   	 }
        
	   	return statsDAO.selectStatsSales(vo);
	        
    }
    
    /**
     * 정산 통계
     */
    public Map<String, Object> selectStatsAccounts(SearchVO vo) throws Exception {
    	
    	 Map<String, Object> mResult = new HashMap<String, Object>();
         
         mResult.put("list", this.selectStatsAccountsExcel(vo));
         
         mResult.put("summary", statsDAO.selectStatsAccountsSummary(vo));
         
         mResult.put("searchInfo", vo);

         return mResult;
    }
    
    /**
     * 정산 통계
     */
    public List<XMap> selectStatsAccountsExcel(SearchVO vo) throws Exception {

    	if(StringUtil.isEmpty(vo.getSearchStartDate())){
        	vo.setSearchStartDate(DateUtil.formatDate("yyyy"));
        }
        
	   	return statsDAO.selectStatsAccounts(vo);
	        
    }
    
    
    /**
     * 상품관리 통계
     */
    public Map<String, Object> selectStatsProducts(SearchVO vo) throws Exception {
    	
    	 Map<String, Object> mResult = new HashMap<String, Object>();
         
         mResult.put("list", this.selectStatsProductsExcel(vo));
         
         mResult.put("summary", statsDAO.selectStatsProductsSummary(vo));
         
         mResult.put("searchInfo", vo);

         return mResult;
    }
    
    /**
     * 상품관리 통계
     */
    public List<XMap> selectStatsProductsExcel(SearchVO vo) throws Exception {

    	if(StringUtil.isEmpty(vo.getSearchEndDate())){
        	vo.setSearchEndDate(DateUtil.formatDate());
        }
        
        if(StringUtil.isEmpty(vo.getSearchStartDate())){
        	vo.setSearchStartDate(StringUtil.addMinusChar(DateUtil.addDay(vo.getSearchEndDate().replaceAll("-", ""), -7)));
        }
        
	   	return statsDAO.selectStatsProducts(vo);
    }
}
