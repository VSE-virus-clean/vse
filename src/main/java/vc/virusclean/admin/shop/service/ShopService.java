package vc.virusclean.admin.shop.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jksoft.com.util.XMap;
import jksoft.com.web.vo.FileVO;
import vc.virusclean.admin.shop.vo.BuyProductVO;
import vc.virusclean.admin.shop.vo.OrderHistoryVO;
import vc.virusclean.admin.shop.vo.OrderVO;
import vc.virusclean.admin.shop.vo.ScrapVO;
import vc.virusclean.admin.shop.vo.ShopVO;
import vc.virusclean.cmm.vo.BoardVO;

/**
 * <pre>
 * 주문정보
 * </pre>
 *
 * @ClassName   : ShopService.java
 * @Description : @Service 클래스
 */

public interface ShopService {

    /**
     * 목록을 조회한다.
     *
     * @param shopVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectShopList(ShopVO shopVO) throws Exception;

    /**
     * 정보를 조회한다.
     *
     * @param shopVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectShop(ShopVO shopVO) throws Exception;
    
    
    /**
     * 주문정보 조회
     */
    public Map<String, Object> selectOrderList(OrderVO vo) throws Exception;
    /**
     * 주문 정보 조회
     */
    public Map<String, Object> selectOrder(OrderVO vo) throws Exception;
    /**
     * 주문번호로 정보 조회
     */
    public Map<String, Object> selectInfoOrderNo(OrderVO vo) throws Exception;
    
    /**
     * 정보를 등록한다.
     *
     * @param shopVO - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> insertOrder(OrderVO orderVO, BuyProductVO buyProductVO) throws Exception;
    
    /**
     * 구매자 / 배송자 /결제방법 정보만 수정
     *
     * @param shopVO - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateShop(ShopVO shopVO) throws Exception;
    
    /**
     * 결제완료
     *
     * @param shopVO - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateShopPayComp(ShopVO shopVO) throws Exception;
    
    /**
     * 배송 처리
     *
     * @param shopVO - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateShopDeliveryComp(ShopVO shopVO) throws Exception;
    
    /**
     * 주문취소
     *
     * @param shopVO - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> deleteShop(ShopVO shopVO) throws Exception;
    
    /**
     * 결제 필요한 값 초기화
     * - 주문번호등.
     * @param price 상품가격(특수기호 제외, 가맹점에서 직접 설정)
     * @return
     * @throws Exception
     */
//    public Map<String, Object> paymentInit(int price) throws Exception;
    
    /**
     * 결제 완료
     * @param request
     * @return
     * @throws Exception
     */
    public Map<String, Object> paymentProcess(HttpServletRequest request) throws Exception;
    public Map<String, Object> paymentMobleProcess(HttpServletRequest request) throws Exception;
    
    public String paymentNotifyVbank(HttpServletRequest httpServletReques) throws Exception;
    public String paymentNotifyMobileVbank(HttpServletRequest httpServletReques) throws Exception;
    
    /*
     * 사용자 / 주문취소 / 주문취소신청(교환/반품)
     */
    public Map<String, Object> insertOrderHistory(OrderHistoryVO vo) throws Exception;
    public Map<String, Object> insertOrderHistoryAdm(OrderHistoryVO vo) throws Exception;
    
    
	
	/**
     * 관심정보 추가
     */
	public Map<String, Object> insertScrap(ScrapVO scrapVO) throws Exception;
	
	/**
	 * 관심정보 삭제
	 */
	public Map<String, Object> deleteScrap(ScrapVO scrapVO) throws Exception;

	/**
	 * 주문정보 엑셀다운로드
	 */
	public List<XMap> selectOrderExcelList(OrderVO vo) throws Exception;
	
	/**
	 * 송장엑셀파일 등록
	 */
	public Map<String, Object> insertInvoiceFormFile(BoardVO vo) throws Exception;

}
