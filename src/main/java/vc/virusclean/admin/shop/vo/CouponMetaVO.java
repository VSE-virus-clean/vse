package vc.virusclean.admin.shop.vo;

import jksoft.com.web.vo.FileVO;

/**
 * 쿠폰관련 VO
 * 
 * - 첨부파일은 : COUPON
 */
@SuppressWarnings("serial")
public class CouponMetaVO extends FileVO  {
	
	private int cupMetaSn = 0;
	
	/**
	 * 쿠폰타입(지급 /다운 /난수-이벤트 )
	 */
	private String lgrpCd = "";
	
	/*
	 * 할인타입(원-WON / %-PER)
	 */
	private String mgrpCd = "";
	
	/*
	 * 생일/처음가입. 쿠폰설정
	 */
	private String sgrpCd = "";
	private String cupTitl = "";
	private String cupSbc = "";
	
	/*
	 * 쿠폰번호-이벤트용으로 발급시 프로모션을 위한 코드를 생성한다.
	 */
	private String proCupNo = "";
	
	/*
	 * 사용유효시간 - 시작일시
	 */
	private String expsRgstDtm = "";
	
	/*
	 * 사용유효시간 - 만료일시
	 */
	private String expsFnhDtm = "";
	
	/*
	 * 사용가능일수(발급일로부터 사용가능한 일수:발급시 계산)
	 */
	private int expsDay = 0;
	
	/*
	 * 최대발급수
	 */
	private int maxCnt = 0;
	
	/*
	 * 할인율/가격(MGRP_CD가 PER일경우는 퍼센트)
	 */
	private int price = 0;
	
	/*
	 * 최대할인가능금액(MGRP_CD가 PER일경우)
	 */
	private int maxPrice = 0;
	
	/*
	 * 사용여부(Y:발급중, N:발급중지, D:삭제)
	 */
	private String useYn = "Y";
	
	//발급수get issue
	private int usable = 0;
	
	//사용한수
	private int unusable = 0;
	

	
	
	private int rgstSn = 0;
	private String rgstId = "";
	private String rgstDtm = "";
	private int mdfySn = 0;
	private String mdfyId = "";
	private String mdfyDtm = "";
	
	
	public int getCupMetaSn() {
		return cupMetaSn;
	}
	public void setCupMetaSn(int cupMetaSn) {
		this.cupMetaSn = cupMetaSn;
	}
	public String getLgrpCd() {
		return lgrpCd;
	}
	public void setLgrpCd(String lgrpCd) {
		this.lgrpCd = lgrpCd;
	}
	public String getMgrpCd() {
		return mgrpCd;
	}
	public void setMgrpCd(String mgrpCd) {
		this.mgrpCd = mgrpCd;
	}
	public String getSgrpCd() {
		return sgrpCd;
	}
	public void setSgrpCd(String sgrpCd) {
		this.sgrpCd = sgrpCd;
	}
	public String getCupTitl() {
		return cupTitl;
	}
	public void setCupTitl(String cupTitl) {
		this.cupTitl = cupTitl;
	}
	public String getCupSbc() {
		return cupSbc;
	}
	public String getProCupNo() {
		return proCupNo;
	}
	public void setProCupNo(String proCupNo) {
		this.proCupNo = proCupNo;
	}
	public String getExpsRgstDtm() {
		return expsRgstDtm;
	}
	public void setExpsRgstDtm(String expsRgstDtm) {
		this.expsRgstDtm = expsRgstDtm;
	}
	public String getExpsFnhDtm() {
		return expsFnhDtm;
	}
	public void setExpsFnhDtm(String expsFnhDtm) {
		this.expsFnhDtm = expsFnhDtm;
	}
	public int getExpsDay() {
		return expsDay;
	}
	public void setExpsDay(int expsDay) {
		this.expsDay = expsDay;
	}
	public int getMaxCnt() {
		return maxCnt;
	}
	public void setMaxCnt(int maxCnt) {
		this.maxCnt = maxCnt;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public int getRgstSn() {
		return rgstSn;
	}
	public void setRgstSn(int rgstSn) {
		this.rgstSn = rgstSn;
	}
	public String getRgstId() {
		return rgstId;
	}
	public void setRgstId(String rgstId) {
		this.rgstId = rgstId;
	}
	public String getRgstDtm() {
		return rgstDtm;
	}
	public void setRgstDtm(String rgstDtm) {
		this.rgstDtm = rgstDtm;
	}
	public int getMdfySn() {
		return mdfySn;
	}
	public void setMdfySn(int mdfySn) {
		this.mdfySn = mdfySn;
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
	public int getUsable() {
		return usable;
	}
	public void setUsable(int usable) {
		this.usable = usable;
	}
	public int getUnusable() {
		return unusable;
	}
	public void setUnusable(int unusable) {
		this.unusable = unusable;
	}
	public void setCupSbc(String cupSbc) {
		this.cupSbc = cupSbc;
	}
}
