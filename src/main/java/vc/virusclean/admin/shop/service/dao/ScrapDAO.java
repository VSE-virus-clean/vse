package vc.virusclean.admin.shop.service.dao;

import org.springframework.stereotype.Repository;

import jksoft.com.dao.XAbstractDAO;
import vc.virusclean.admin.shop.vo.ScrapVO;

/**
 * <pre>
 * 제품 즐겨찾기 서비스 DAO
 * </pre>
 *
 * @Class Name  : Scrap.java
 * @Description : @DAO 클래스
*/
@Repository("scrapDAO")
public class ScrapDAO extends XAbstractDAO {

    /**
     * 게시물의 총 갯수를 조회한다.
     *
     * @param ScrapVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectScrapCount(ScrapVO scrapVO) throws Exception {
        return (Integer)select("scrapDAO.selectScrapCount", scrapVO);
    }
    
    /**
     * 정보를 등록한다
     *
     * @param ScrapVO - 저장 할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public Object insertScrap(ScrapVO scrapVO) throws Exception {
        return insert("scrapDAO.insertScrap", scrapVO);
    }
    
    /**
     * 정보를 삭제한다
     *
     * @param ScrapVO - 삭제할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int deleteScrap(ScrapVO scrapVO) throws Exception {
        return update("scrapDAO.deleteScrap", scrapVO);
    }

}
