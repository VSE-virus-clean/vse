package vc.virusclean.admin.shop.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jksoft.com.dao.XAbstractDAO;
import vc.virusclean.admin.shop.vo.CouponMetaVO;
import vc.virusclean.admin.shop.vo.CouponVO;

/**
 * <pre>
 * 쿠폰 DAO
 * </pre>
 *
 * @Class Name  : CouponMetaDAO.java
 * @Description : @DAO 클래스
*/
@Repository("couponDAO")
public class CouponDAO extends XAbstractDAO {
	

    /**
     * 쿠폰메다데이터의 총 갯수를 조회한다.
     */
    public int selectCouponMetaCount(CouponMetaVO vo) throws Exception {
        return (Integer)select("couponDAO.selectCouponMetaCount", vo);
    }

    /**
     * 쿠폰메다데이터의 목록을 조회한다
     */
    @SuppressWarnings("unchecked")
    public List<CouponMetaVO> selectCouponMetaList(CouponMetaVO vo) throws Exception {
        return (List<CouponMetaVO>)list("couponDAO.selectCouponMetaList", vo);
    }
    
    /**
     * 쿠폰메다데이터의 정보를 조회한다
     */
    public CouponMetaVO selectCouponMeta(CouponMetaVO vo) throws Exception {
        return (CouponMetaVO)select("couponDAO.selectCouponMeta", vo);
    }
    
    /**
     * 쿠폰메다데이터의 정보를 등록한다
     */
    public Object insertCouponMeta(CouponMetaVO vo) throws Exception {
        return insert("couponDAO.insertCouponMeta", vo);
    }
    
    /**
     * 쿠폰메다데이터의 정보를 수정한다
     */
    public int updateCouponMeta(CouponMetaVO vo) throws Exception {
        return update("couponDAO.updateCouponMeta", vo);
    }
    
    /**
     * 쿠폰메다데이터의 정보를 삭제한다
     */
    public int deleteCouponMeta(CouponMetaVO vo) throws Exception {
        return update("couponDAO.deleteCouponMeta", vo);
    }
    
    
    
    /** *********************************************************************** **
     *  사용자 발급 쿠폰
     ** *********************************************************************** **/
    /**
     * 사용자 발급 쿠폰 정보를 등록한다
     */
    public Object insertCouponUser(CouponVO vo) throws Exception {
        return insert("couponDAO.insertCouponUser", vo);
    }
    
    
    /**
     * 사용자 발급 쿠폰 사용
     */
    public int updateCouponUserUsed(CouponVO vo) throws Exception {
        return update("couponDAO.updateCouponUserUsed", vo);
    }
    
    /**
     * 사용자 발급 쿠폰 갯수를 조회한다. : 관리자용
     */
    public int selectCouponAdmCount(CouponVO vo) throws Exception {
        return (Integer)select("couponDAO.selectCouponAdmCount", vo);
    }
    
    /**
     *  사용자 발급 쿠폰 목록을 조회한다 : 관리자용
     */
    @SuppressWarnings("unchecked")
    public List<CouponVO> selectCouponAdmList(CouponVO vo) throws Exception {
        return (List<CouponVO>)list("couponDAO.selectCouponAdmList", vo);
    }
    
    /**
     * 사용자 발급 쿠폰 갯수를 조회한다. : 사용자용
     */
    public int selectCouponUserCount(CouponVO vo) throws Exception {
        return (Integer)select("couponDAO.selectCouponUserCount", vo);
    }
    
    /**
     *  사용자 발급 쿠폰 목록을 조회한다 : 사용자용
     */
    @SuppressWarnings("unchecked")
    public List<CouponVO> selectCouponUserList(CouponVO vo) throws Exception {
        return (List<CouponVO>)list("couponDAO.selectCouponUserList", vo);
    }
    
    /*
     * 다운로드 가능한 쿠폰 목록 을 조회한다. : 사용자
     */
	@SuppressWarnings("unchecked")
	public List<CouponMetaVO> selectEventCouponList(CouponVO vo) throws Exception {
        return (List<CouponMetaVO>)list("couponDAO.selectEventCouponList", vo);
    }
	
	/*
	 * 사용자가 쿠폰번호를 등록해서 사용가능한 쿠폰이 있는지 확인
	 */
	public CouponVO selectCouponUserEmptyInfo(CouponVO vo) throws Exception {
        return (CouponVO)select("couponDAO.selectCouponUserEmptyInfo", vo);
    }
	
	/*
	 * 사용자가 쿠폰번호를 등록해서 사용가능한 쿠폰이 있으면 해당 회원의 쿠폰으로 변경한다.
	 */
	public int updateCouponUserEmpty(CouponVO vo) throws Exception {
		return update("couponDAO.updateCouponUserEmpty", vo);
	}
}
