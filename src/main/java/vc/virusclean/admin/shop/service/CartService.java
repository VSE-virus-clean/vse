package vc.virusclean.admin.shop.service;

import java.util.Map;

import vc.virusclean.admin.shop.vo.CartVO;

/**
 * <pre>
 * 장바구니 서비스
 * </pre>
 *
 * @ClassName   : CartService.java
 * @Description : @Service 클래스
 */

public interface CartService {

    /**
     * 목록을 조회한다.
     */
    public Map<String, Object> selectList(CartVO vo) throws Exception;

    /**
     * 정보를 등록한다.
     */
    public Map<String, Object> insertCart(CartVO vo) throws Exception;
    
    /**
     * 정보를 삭제한다.
     */
    public Map<String, Object> deleteCart(CartVO vo) throws Exception;

    
}
