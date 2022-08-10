package vc.virusclean.admin.shop.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jksoft.com.dao.XAbstractDAO;
import jksoft.com.util.XMap;
import jksoft.com.web.vo.SearchVO;

/**
 * <pre>
 * 보안로그 DAO
 * </pre>
*/
@Repository("statsDAO")
public class StatsDAO extends XAbstractDAO {

    /**
     * 관리자 대쉬보드
     */
    public XMap selectDashboard() throws Exception {
        return (XMap)select("statsDAO.selectDashboard", null);
    }

    /**
     * 매출 통계
     */
    @SuppressWarnings("unchecked")
	public List<XMap> selectStatsSales(SearchVO vo) {
		return (List<XMap>)list("statsDAO.selectStatsSales", vo);
	}
    
    /**
     * 정산 통계
     */
    @SuppressWarnings("unchecked")
   	public List<XMap> selectStatsAccounts(SearchVO vo) {
   		return (List<XMap>)list("statsDAO.selectStatsAccounts", vo);
   	}
    
   	public XMap selectStatsAccountsSummary(SearchVO vo) {
   		return (XMap)select("statsDAO.selectStatsAccountsSummary", vo);
   	}
   	
    /**
     * 상품관리 통계
     */
    @SuppressWarnings("unchecked")
   	public List<XMap> selectStatsProducts(SearchVO vo) {
   		return (List<XMap>)list("statsDAO.selectStatsProducts", vo);
   	}
    
   	public XMap selectStatsProductsSummary(SearchVO vo) {
   		return (XMap)select("statsDAO.selectStatsProductsSummary", vo);
   	}
    
}
