package vc.virusclean.admin.shop.vo;

import jksoft.com.web.vo.FileVO;

/**
 * @Class Name : OriginProductVO.java
 * @Description : 정품등록 VO
 */

@SuppressWarnings("serial")
public class OriginProductVO extends FileVO {
	
	private int orgSn = 0;
	private int mbrSn = 0;
	private String mbrId = ""; 
	private String mbrNm = ""; 
	private String mbrNick = ""; 
	
	private int prdSn = 0;
	private String prdTitl = "";
	
	private String serialNo = "";
	
	/**
	 * MARKET 구입처
	 *	MARKET-01 바이러스 클린 랩 공식몰
	 *	MARKET-02 네버스토어팜
	 *	MARKET-03 오픈마켓(11번가, G마켓, 쿠팡 등)
	 *	MARKET-04 브랜드몰(신세계, 롯데 등)
	 *	MARKET-05 기타(중고거래 등)
	 */
	private String makCd = "";
	private String orgSbc = "";
	private String wrntStrDtm = "";
	private String wrntFnhDtm = "";
	
	/*
	 * 관리자승인여부(Y승인, ,N반려, A:신청)
	 */
	private String aplyYn = "";
	private String aplySbc = "";
	private String aplyDtm = "";
	private String makDtm = "";
	private String rgstDtm = "";
    private String userViewDtm = "";
    
    private String useYn = "Y";
    private int mdfySn = 0;
	private String mdfyId = "";
	private String mdfyDtm = "";
    
    
	public int getOrgSn() {
		return orgSn;
	}
	public void setOrgSn(int orgSn) {
		this.orgSn = orgSn;
	}
	public int getMbrSn() {
		return mbrSn;
	}
	public void setMbrSn(int mbrSn) {
		this.mbrSn = mbrSn;
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
	public int getPrdSn() {
		return prdSn;
	}
	public void setPrdSn(int prdSn) {
		this.prdSn = prdSn;
	}
	public String getPrdTitl() {
		return prdTitl;
	}
	public void setPrdTitl(String prdTitl) {
		this.prdTitl = prdTitl;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getMakCd() {
		return makCd;
	}
	public void setMakCd(String makCd) {
		this.makCd = makCd;
	}
	public String getOrgSbc() {
		return orgSbc;
	}
	public void setOrgSbc(String orgSbc) {
		this.orgSbc = orgSbc;
	}
	public String getWrntStrDtm() {
		return wrntStrDtm;
	}
	public void setWrntStrDtm(String wrntStrDtm) {
		this.wrntStrDtm = wrntStrDtm;
	}
	public String getWrntFnhDtm() {
		return wrntFnhDtm;
	}
	public void setWrntFnhDtm(String wrntFnhDtm) {
		this.wrntFnhDtm = wrntFnhDtm;
	}
	public String getAplyYn() {
		return aplyYn;
	}
	public void setAplyYn(String aplyYn) {
		this.aplyYn = aplyYn;
	}
	public String getAplySbc() {
		return aplySbc;
	}
	public void setAplySbc(String aplySbc) {
		this.aplySbc = aplySbc;
	}
	public String getAplyDtm() {
		return aplyDtm;
	}
	public void setAplyDtm(String aplyDtm) {
		this.aplyDtm = aplyDtm;
	}
	public String getMakDtm() {
		return makDtm;
	}
	public void setMakDtm(String makDtm) {
		this.makDtm = makDtm;
	}
	public String getRgstDtm() {
		return rgstDtm;
	}
	public void setRgstDtm(String rgstDtm) {
		this.rgstDtm = rgstDtm;
	}
	public String getUserViewDtm() {
		return userViewDtm;
	}
	public void setUserViewDtm(String userViewDtm) {
		this.userViewDtm = userViewDtm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	
}