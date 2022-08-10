package vc.virusclean.admin.shop.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import vc.virusclean.admin.shop.vo.OrderHistoryVO;
import vc.virusclean.admin.shop.vo.OrderProductVO;
import vc.virusclean.admin.shop.vo.OrderVO;
import vc.virusclean.admin.shop.vo.PaymentVO;
import vc.virusclean.admin.shop.vo.ShopVO;
import jksoft.com.dao.XAbstractDAO;
import jksoft.com.util.XMap;

/**
 * <pre>
 * 클래스 설명을 기술합니다.
 * </pre>
 *
 * @Class Name  : ShopDAO.java
 * @Description : @DAO 클래스
*/
@Repository("shopDAO")
public class ShopDAO extends XAbstractDAO {
	

    /**
     * 게시물의 총 갯수를 조회한다.
     *
     * @param shopVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectShopCount(ShopVO shopVO) throws Exception {
        return (Integer)select("shopDAO.selectShopCount", shopVO);
    }


    /**
     * 목록을 조회한다
     *
     * @param shopVO - 조회할 정보가 담긴 VO
     * @return List<ShopVO> 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<ShopVO> selectShopList(ShopVO shopVO) throws Exception {
        return (List<ShopVO>)list("shopDAO.selectShopList", shopVO);
    }


    /**
     * 정보를 조회한다
     *
     * @param shopVO - 조회할 정보가 담긴 VO
     * @return shopVO
     * @throws Exception
     */
    public ShopVO selectShop(ShopVO shopVO) throws Exception {
        return (ShopVO)select("shopDAO.selectShop", shopVO);
    }
    
    
    /**
     * 구매자 / 배송자 /결제방법 정보만 수정
     *
     * @param shopVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateShop(ShopVO shopVO) throws Exception {
        return update("shopDAO.updateShop", shopVO);
    }
    
    
    /**
     * 결제완료
     *
     * @param ShopVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateShopPayComp(ShopVO shopVO) throws Exception {
        return update("shopDAO.updateShopPayComp", shopVO);
    }
    
    
    /**
     * 배송처리
     *
     * @param ShopVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateShopDeliveryComp(ShopVO shopVO) throws Exception {
        return update("shopDAO.updateShopDeliveryComp", shopVO);
    }
    
    
    /**
     * 관리자 주문 취소
     *
     * @param ShopVO - 삭제할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int deleteShop(ShopVO shopVO) throws Exception {
        return update("shopDAO.deleteShop", shopVO);
    }
    
    /**
     * 사용자 주문 취소
     *
     * @param ShopVO - 삭제할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int deleteShopUser(ShopVO shopVO) throws Exception {
        return update("shopDAO.deleteShopUser", shopVO);
    }

    
    
    
    /**
     * 주문정보 등록
     */
    public Object insertOrder(OrderVO vo) throws Exception {
        return insert("shopDAO.insertOrder", vo);
    }
    
    /**
     * 주문상품 정보 등록
     */
    public Object insertOrderProductInfo(OrderProductVO vo) throws Exception {
        return insert("shopDAO.insertOrderProductInfo", vo);
    }
    
    /**
     * 주문의 결제 정보를 등록
     */
    public Object insertOrderPayment(PaymentVO vo) {
    	 return insert("shopDAO.insertOrderPayment", vo);
	}
    
    /**
     * 결제완료
     */
    public int updateOrderPaymentComp(OrderVO vo) throws Exception {
        return update("shopDAO.updateOrderPaymentComp", vo);
    }
    
    
    /**
     * 주문번호로 정보를 조회한다
     */
    public OrderVO selectOrdeInfo(OrderVO vo) throws Exception {
        return (OrderVO)select("shopDAO.selectOrdeInfo", vo);
    }

    /**
     * 주문 갯수
     */
    public int selectOrderCount(OrderVO vo) throws Exception {
        return (Integer)select("shopDAO.selectOrderCount", vo);
    }


    /**
     * 주문목록
     */
    @SuppressWarnings("unchecked")
    public List<OrderVO> selectOrderList(OrderVO vo) throws Exception {
        return (List<OrderVO>)list("shopDAO.selectOrderList", vo);
    }
    
    /**
     * 주문 상태변경 이력
     */
    public Object insertOrderHistory(OrderHistoryVO vo) {
    	 return insert("shopDAO.insertOrderHistory", vo);
	}
    
    /**
     * 결제/주문 이력 조회
     */
    @SuppressWarnings("unchecked")
    public List<OrderHistoryVO> selectOrderHistoryList(OrderVO vo) throws Exception {
        return (List<OrderHistoryVO>)list("shopDAO.selectOrderHistoryList", vo);
    }
    
    /**
     * 주문진행 상태변경
     */
    public int updateOrderByOrderCd(OrderHistoryVO vo) throws Exception {
        return update("shopDAO.updateOrderByOrderCd", vo);
    }
    
    /**
     * 배송정보 등록
     */
    public int updateOrderByDeliveryInfo(OrderVO vo) throws Exception {
        return update("shopDAO.updateOrderByDeliveryInfo", vo);
    }

    /**
     * 결제 목록 : 엑셀로
     */
    @SuppressWarnings("unchecked")
	public List<XMap> selectOrderExcelList(OrderVO orderVO) {
    	return (List<XMap>)list("shopDAO.selectOrderExcelList", orderVO);
	}
    
    /**
     * 환불정보 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public OrderHistoryVO selectRefBankInfo(OrderVO vo) throws Exception {
        return (OrderHistoryVO)select("shopDAO.selectRefBankInfo", vo);
    }
}
