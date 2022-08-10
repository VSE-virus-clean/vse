package vc.virusclean.admin.shop.service;

import java.util.Map;

import vc.virusclean.admin.shop.vo.OriginProductVO;

/**
 * <pre>
 * 정품등록관련	 서비스
 * </pre>
 *
 * @ClassName   : OriginProductService.java
 * @Description : @Service 클래스
 */

public interface OriginProductService {

    /**
     * 목록을 조회한다.
     *
     * @param vo - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectProductList(OriginProductVO vo) throws Exception;


    /**
     * 정보를 조회한다.
     *
     * @param vo - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectProduct(OriginProductVO vo) throws Exception;
    
    /**
     * 수정페이지 정보를 조회한다.
     *
     * @param vo - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectProductByMod(OriginProductVO vo) throws Exception;

        
    /**
     * 정보를 등록한다.
     *
     * @param vo - 저장 할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> insertProduct(OriginProductVO vo) throws Exception;
    

    /**
     * 정보를 수정한다.
     *
     * @param vo - 수정할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateProduct(OriginProductVO vo) throws Exception;


    /**
     * 정보를 삭제한다.
     *
     * @param vo - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> deleteProduct(OriginProductVO vo) throws Exception;

	/**
	 * 승인/반려 상태변경
	 * - 현재는 자동으로 승인 처리하도록 구현한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updateProductByAplyYn(OriginProductVO vo) throws Exception;
    
}
