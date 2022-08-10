package vc.virusclean.admin.shop.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jksoft.com.dao.XAbstractDAO;
import vc.virusclean.admin.shop.vo.CartVO;

/**
 * <pre>
 * 카트 DAO
 * </pre>
 *
 * @Class Name  : CartDAO.java
 * @Description : @DAO 클래스
*/
@Repository("cartDAO")
public class CartDAO extends XAbstractDAO {

	/**
     *  ROW의 수를 조회한다.
     */
    public int selectCount(CartVO vo) throws Exception {
        return (Integer)select("cartDAO.selectCount", vo);
    }
    
	/**
     * 기존 등록된 상품이 잇는지 확인
     */
    public int selectProductCount(CartVO vo) throws Exception {
        return (Integer)select("cartDAO.selectProductCount", vo);
    }

    /**
     * 목록을 조회한다
     */
    @SuppressWarnings("unchecked")
    public List<CartVO> selectList(CartVO vo) throws Exception {
        return (List<CartVO>)list("cartDAO.selectList", vo);
    }
    
    /**
     * 정보를 등록한다
     */
    public Object insertCart(CartVO vo) throws Exception {
        return insert("cartDAO.insertCart", vo);
    }
    
    /**
     * 정보를 삭제한다
     */
    public int deleteCart(CartVO vo) throws Exception {
        return update("cartDAO.deleteCart", vo);
    }
    
    /**
     * 수량을 갱신한다
     */
    public int updateProductCount(CartVO vo) throws Exception {
        return update("cartDAO.updateProductCount", vo);
    }

}
