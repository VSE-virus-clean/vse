package vc.virusclean.admin.shop.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jksoft.com.dao.XAbstractDAO;
import vc.virusclean.admin.shop.vo.BuyProductVO;
import vc.virusclean.admin.shop.vo.ProductVO;

/**
 * <pre>
 * 상품정보
 * </pre>
 *
 * @Class Name  : ProductDAO.java
 * @Description : @DAO 클래스
*/
@Repository("productDAO")
public class ProductDAO extends XAbstractDAO {

    /**
     * 게시물의 총 갯수를 조회한다.
     */
    public int selectProductCount(ProductVO vo) throws Exception {
        return (Integer)select("productDAO.selectProductCount", vo);
    }

    /**
     * 목록을 조회한다
     */
    @SuppressWarnings("unchecked")
    public List<ProductVO> selectProductList(ProductVO vo) throws Exception {
        return (List<ProductVO>)list("productDAO.selectProductList", vo);
    }
    
    /*
     *  구매페이지 상품 목록 조회
     */
    @SuppressWarnings("unchecked")
    public List<ProductVO> selectProductOrderList(BuyProductVO vo) throws Exception {
        return (List<ProductVO>)list("productDAO.selectProductOrderList", vo);
    }

    /**
     * 정보를 조회한다
     */
    public ProductVO selectProduct(ProductVO vo) throws Exception {
        return (ProductVO)select("productDAO.selectProduct", vo);
    }
    
    /**
     * 정보를 등록한다
     */
    public Object insertProduct(ProductVO vo) throws Exception {
        return insert("productDAO.insertProduct", vo);
    }
    
    /**
     * 정보를 수정한다
     *
     * @param vo - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateProduct(ProductVO vo) throws Exception {
        return update("productDAO.updateProduct", vo);
    }
    
    /**
     * 정보를 삭제한다
     *
     * @param vo - 삭제할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int deleteProduct(ProductVO vo) throws Exception {
        return update("productDAO.deleteProduct", vo);
    }
    
    /**
     * 조회수를 증가한다.
     *
     * @param vo - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateProductByBlcRct(ProductVO vo) throws Exception {
        return update("productDAO.updateProductByBlcRct", vo);
    }
    
    /**
     * 정렬순서 변경
     *
     * @param vo - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateProductByStNo(ProductVO vo) throws Exception {
        return update("productDAO.updateProductByStNo", vo);
    }


    /**
     * 품절처리
     *
     * @param vo - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateProductBySellYn(ProductVO vo) throws Exception {
        return update("productDAO.updateProductBySellYn", vo);
    }
    
}
