package vc.virusclean.cmm.vo;

import java.io.Serializable;

/**
 * @Class Name : CodeEnvVO.java
 * @Description : 공통코드@VO 클래스
 */

@SuppressWarnings("serial")
public class CodeEnvVO implements Serializable {
	
	private String parentCd = "";

    /**
     * 환영쿠폰 설정
     */
    private String couponWelcom = ""; 
    
    /**
     * couponYn
     */
    private String couponYn = ""; 
    
    /**
     * 배송 기간안내
     */
    private String deliveryDate = ""; 
    
    /**
     * 배송비 무료정책
     */
    private String deliveryFree = ""; 
    
    /**
     * 기본 배송비
     */
    private String deliveryMin = ""; 
    
    /**
     * 배송료 사용 설정
     */
    private String deliveryYn = ""; 
    
    /**
     * 탈퇴시 정보삭제
     */
    private String memberOutdel = ""; 
    
    /**
     * 탈퇴아이디 사용
     */
    private String memberOutid = ""; 
    
    /**
     * 사이트 Description
     */
    private String siteDesc = ""; 
    
    /**
     * 사이트 Keyword
     */
    private String siteKeyword = ""; 
    
    /**
     * 사이트명 명
     */
    private String siteName = ""; 
    
    /**
     * 사이트 TITLE
     */
    private String siteTitle = ""; 
    
    
    
    /**
     * 수정관리자아이디 
     * MDFY_MGR_ID : varchar(20)
     */
    private String mdfyId = ""; 

    /**
     * 수정일시 
     * MDFY_DTM : datetime(23)
     */
    private String mdfyDtm = "";
    
    

	public String getParentCd() {
		return parentCd;
	}

	public void setParentCd(String parentCd) {
		this.parentCd = parentCd;
	}

	public String getCouponWelcom() {
		return couponWelcom;
	}

	public void setCouponWelcom(String couponWelcom) {
		this.couponWelcom = couponWelcom;
	}

	public String getCouponYn() {
		return couponYn;
	}

	public void setCouponYn(String couponYn) {
		this.couponYn = couponYn;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryFree() {
		return deliveryFree;
	}

	public void setDeliveryFree(String deliveryFree) {
		this.deliveryFree = deliveryFree;
	}

	public String getDeliveryMin() {
		return deliveryMin;
	}

	public void setDeliveryMin(String deliveryMin) {
		this.deliveryMin = deliveryMin;
	}

	public String getDeliveryYn() {
		return deliveryYn;
	}

	public void setDeliveryYn(String deliveryYn) {
		this.deliveryYn = deliveryYn;
	}

	public String getMemberOutdel() {
		return memberOutdel;
	}

	public void setMemberOutdel(String memberOutdel) {
		this.memberOutdel = memberOutdel;
	}

	public String getMemberOutid() {
		return memberOutid;
	}

	public void setMemberOutid(String memberOutid) {
		this.memberOutid = memberOutid;
	}

	public String getSiteDesc() {
		return siteDesc;
	}

	public void setSiteDesc(String siteDesc) {
		this.siteDesc = siteDesc;
	}

	public String getSiteKeyword() {
		return siteKeyword;
	}

	public void setSiteKeyword(String siteKeyword) {
		this.siteKeyword = siteKeyword;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteTitle() {
		return siteTitle;
	}

	public void setSiteTitle(String siteTitle) {
		this.siteTitle = siteTitle;
	}

	public String getMdfyId() {
		return mdfyId;
	}

	public void setMdfyId(String mdfyId) {
		this.mdfyId = mdfyId;
	}

	public String getMdfyDtm() {
		return mdfyDtm;
	}

	public void setMdfyDtm(String mdfyDtm) {
		this.mdfyDtm = mdfyDtm;
	} 
}