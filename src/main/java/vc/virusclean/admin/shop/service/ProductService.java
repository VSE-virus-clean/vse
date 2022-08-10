package vc.virusclean.admin.shop.service;

import java.util.Map;

import vc.virusclean.admin.shop.vo.BuyProductVO;
import vc.virusclean.admin.shop.vo.ProductVO;

/**
 * <pre>
 * 상품관리 서비스
 * </pre>
 *
 * @ClassName   : ProductService.java
 * @Description : @Service 클래스
 */

public interface ProductService {

    /**
     * 목록을 조회한다.
     *
     * @param vo - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectProductList(ProductVO vo) throws Exception;
    
    /**
     * 구매를 하기위해 상품정보 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectProductOrderList(BuyProductVO vo) throws Exception;
    

    /**
     * 정보를 조회한다.
     *
     * @param vo - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectProduct(ProductVO vo) throws Exception;
    
    /**
     * 수정페이지 정보를 조회한다.
     *
     * @param vo - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectProductByMod(ProductVO vo) throws Exception;

        
    /**
     * 정보를 등록한다.
     *
     * @param vo - 저장 할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> insertProduct(ProductVO vo) throws Exception;
    

    /**
     * 정보를 수정한다.
     *
     * @param vo - 수정할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateProduct(ProductVO vo) throws Exception;


    /**
     * 정보를 삭제한다.
     *
     * @param vo - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> deleteProduct(ProductVO vo) throws Exception;


    /**
     * 정렬순서 변경
     * @param vo
     * @return
     * @throws Exception
     */
	public Map<String, Object> updateProductBySort(ProductVO vo) throws Exception;
	
	/**
	 * 품절상태 변경
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updateProductBySellYn(ProductVO vo) throws Exception;
	
	
	
	/**
     * NPAY 구매를 하기위해 상품정보 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectProductNPayOrder(BuyProductVO vo) throws Exception;

    
    /**
     * NPAY 찜등록 하기위해 상품정보 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectProductNPayWish(BuyProductVO vo) throws Exception;
    
    /**
     * NPAY 아이템정보 조회 하기위해 상품정보 조회
     * @param vo
     * @return
     * @throws Exception
     */
    public StringBuilder selectProductNPayItemInfo(BuyProductVO vo) throws Exception;

    
}
