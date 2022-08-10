package vc.virusclean.admin.shop.vo;

import jksoft.com.web.vo.SearchVO;

@SuppressWarnings("serial")
public class CouponVO extends SearchVO  {

	private int cupSn = 0;
	private int cupMetaSn = 0;
	private int mbrSn = 0;
	private String mbrId = ""; 
    private String mbrNm = ""; 
    private String mbrNick = ""; 
	private int prdSn = 0;


	
	/*
	 * 쿠폰타입(지급 /다운 /난수/이벤트 )
	 */
	private String lgrpCd = "";
	
	/*
	 * 할인타입(WON/PER)
	 */
	private String mgrpCd = "";
	private String sgrpCd = "";
	private String cupTitl = "";
	private String cupSbc = "";
	
	/*
	 * 쿠폰번호
	 */
	private String cupNo = "";
	
	/*
	 * 사용유효시간 - 시작일시
	 */
	private String expsRgstDtm = "";
	
	/*
	 * 사용유효시간 - 만료일시
	 */
	private String expsFnhDtm = "";
	
	/*
	 * 할인율/가격(MGRP_CD가 PER일경우는 퍼센트)
	 */
	private int price = 0;
	
	/*
	 * 최대할인가능금액(MGRP_CD가 PER일경우)
	 */
	private int maxPrice = 0;
	
	/**
	 * 쿠폰으로 할인받은 금액 : 사용후에 업데이트 해불것.
	 * - 사용은 결제가 완료된이후여야함.
	 * - 결제 완료페이지에서 한번더 업데이트가 필요할것 같음.
	 */
	private int cupPrice = 0;
	
	/*
	 * 사용여부(Y:사용가능(사용전, N:사용불가(사용후)
	 */
	private String useYn = "";
	
	/**
	 * 사용시간
	 */
	private String useDtm = "";
	
	
	private String rgstDtm = "";


	
	public int getCupSn() {
		return cupSn;
	}

	public void setCupSn(int cupSn) {
		this.cupSn = cupSn;
	}

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

	public void setCupSbc(String cupSbc) {
		this.cupSbc = cupSbc;
	}

	public String getCupNo() {
		return cupNo;
	}

	public void setCupNo(String cupNo) {
		this.cupNo = cupNo;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCupPrice() {
		return cupPrice;
	}

	public void setCupPrice(int cupPrice) {
		this.cupPrice = cupPrice;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getUseDtm() {
		return useDtm;
	}

	public void setUseDtm(String useDtm) {
		this.useDtm = useDtm;
	}

	public String getRgstDtm() {
		return rgstDtm;
	}

	public void setRgstDtm(String rgstDtm) {
		this.rgstDtm = rgstDtm;
	}

	public int getMbrSn() {
		return mbrSn;
	}

	public void setMbrSn(int mbrSn) {
		this.mbrSn = mbrSn;
	}

	public int getPrdSn() {
		return prdSn;
	}

	public void setPrdSn(int prdSn) {
		this.prdSn = prdSn;
	}

	public String getMbrId() {
		return mbrId;
	}

	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

	public String getMbrNm() {
		return mbrNm;
	}

	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}

	public String getMbrNick() {
		return mbrNick;
	}

	public void setMbrNick(String mbrNick) {
		this.mbrNick = mbrNick;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

}
