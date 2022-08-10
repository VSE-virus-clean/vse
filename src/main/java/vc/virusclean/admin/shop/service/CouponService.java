package vc.virusclean.admin.shop.service;

import java.util.Map;

import vc.virusclean.admin.shop.vo.CouponMetaVO;
import vc.virusclean.admin.shop.vo.CouponVO;

/**
 * <pre>
 * 쿠폰  서비스
 * </pre>
 *
 * @ClassName   : CouponService.java
 * @Description : @Service 클래스
 */

public interface CouponService {

    /**
     * 쿠폰메다데이터의 목록을 조회한다.
     */
    public Map<String, Object> selectCouponMetaList(CouponMetaVO vo) throws Exception;

    /**
     * 쿠폰메다데이터의 정보를 조회한다.
     */
    public Map<String, Object> selectCouponMeta(CouponMetaVO vo) throws Exception;
    
    /**
     * 쿠폰메다데이터의 수정페이지 정보를 조회한다.
     */
    public Map<String, Object> selectCouponMetaByMod(CouponMetaVO vo) throws Exception;

    /**
     * 쿠폰메다데이터의  정보를 등록한다.
     */
    public Map<String, Object> insertCouponMeta(CouponMetaVO vo) throws Exception;

    /**
     * 정보를 수정한다.
     */
    public Map<String, Object> updateCouponMeta(CouponMetaVO vo) throws Exception;

    /**
     * 정보를 삭제한다.
     */
    public Map<String, Object> deleteCouponMeta(CouponMetaVO vo) throws Exception;


    
    /** *********************************************************************** **
     *  사용자 발급 쿠폰
     ** *********************************************************************** **/
    
    /**
     * 사용자 발급 쿠폰 정보를 등록한다
     */
    public Map<String, Object> insertCouponUser(CouponVO vo) throws Exception;
    
	/**
	 * 사용자 발급 쿠폰 사용
	 */
	public Map<String, Object> updateCouponUserUsed(CouponVO vo) throws Exception;
	
	 /**
     * 사용자 발급 쿠폰 목록을 조회한다 : 관리자용
     */
    public Map<String, Object> selectCouponAdmList(CouponVO vo) throws Exception;

    /**
     *사용자 발급 쿠폰 목록을 조회한다 : 사용자용
     */
    public Map<String, Object> selectCouponUserList(CouponVO vo) throws Exception;
    
    /**
     * 다운로드 가능한 쿠폰 목록 을 조회한다. : 사용자
     */
    public Map<String, Object> selectEventCouponList() throws Exception;
    

    /**
     * 사용자가 쿠폰번호를 등록한다.
     * - 쿠폰번호 인증 해야함.
     */
    public Map<String, Object> insertCouponUserRegister(CouponVO vo) throws Exception;
    
    /**
     * 사용자 다룬로드 쿠폰 등록
     */
    public Map<String, Object> insertCouponUserDownload(CouponVO vo) throws Exception;
    
    /**
     * 관리자가 사용자에게 쿠폰을 지급한다.
     */
    public Map<String, Object> insertCouponAdmForUserRegister(CouponMetaVO vo) throws Exception;
    
}
