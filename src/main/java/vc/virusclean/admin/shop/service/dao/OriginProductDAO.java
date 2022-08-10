package vc.virusclean.admin.shop.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jksoft.com.dao.XAbstractDAO;
import vc.virusclean.admin.shop.vo.OriginProductVO;
import vc.virusclean.admin.shop.vo.ProductVO;

/**
 * <pre>
 * 상품정보
 * </pre>
 *
 * @Class Name  : OriginProductDAO.java
 * @Description : @DAO 클래스
*/
@Repository("originProductDAO")
public class OriginProductDAO extends XAbstractDAO {
	

    /**
     * 게시물의 총 갯수를 조회한다.
     */
    public int selectProductCount(OriginProductVO vo) throws Exception {
        return (Integer)select("originProductDAO.selectProductCount", vo);
    }


    /**
     * 목록을 조회한다
     */
    @SuppressWarnings("unchecked")
    public List<ProductVO> selectProductList(OriginProductVO vo) throws Exception {
        return (List<ProductVO>)list("originProductDAO.selectProductList", vo);
    }


    /**
     * 정보를 조회한다
     */
    public OriginProductVO selectProduct(OriginProductVO vo) throws Exception {
        return (OriginProductVO)select("originProductDAO.selectProduct", vo);
    }
    
    
    /**
     * 정보를 등록한다
     */
    public Object insertProduct(OriginProductVO vo) throws Exception {
        return insert("originProductDAO.insertProduct", vo);
    }
    
    /**
     * 정보를 수정한다
     *
     * @param vo - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateProduct(OriginProductVO vo) throws Exception {
        return update("originProductDAO.updateProduct", vo);
    }
    
    /**
     * 관리자가 승인/반려한다.
     *
     * @param vo - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateProductByAplyYn(OriginProductVO vo) throws Exception {
        return update("originProductDAO.updateProductByAplyYn", vo);
    }
    
    /**
     * 정보를 삭제한다
     *
     * @param vo - 삭제할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int deleteProduct(OriginProductVO vo) throws Exception {
        return update("originProductDAO.deleteProduct", vo);
    }
    
}
