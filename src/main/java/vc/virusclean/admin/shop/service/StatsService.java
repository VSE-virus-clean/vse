package vc.virusclean.admin.shop.service;

import java.util.List;
import java.util.Map;

import jksoft.com.util.XMap;
import jksoft.com.web.vo.SearchVO;

/**
 * <pre>
 * 통계 서비스
 * </pre>
 */

public interface StatsService {

    public Map<String, Object> selectDashboard() throws Exception;
    
    public Map<String, Object> selectStatsSales(SearchVO vo) throws Exception;
    
	public List<XMap> selectStatsSalesExcel(SearchVO vo) throws Exception;
	
	public Map<String, Object> selectStatsAccounts(SearchVO vo) throws Exception;
    
	public List<XMap> selectStatsAccountsExcel(SearchVO vo) throws Exception;
	
	public Map<String, Object> selectStatsProducts(SearchVO vo) throws Exception;
    
	public List<XMap> selectStatsProductsExcel(SearchVO vo) throws Exception;
    

}
