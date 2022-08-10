package vc.virusclean.admin.shop.vo;

import jksoft.com.web.vo.SearchVO;

@SuppressWarnings("serial")
public class ScrapVO extends SearchVO  {
	private int scrapSn = 0;
	
	//구분코드 : 상품찜(PRODUCT), 리뷰(REVIEW)
	private String scrapCd = "";
	private int prdSn = 0;
	private String prdNm = ""; 
	private int blcSn = 0;
	private String blcTitl = "";
	
	private int mbrSn = 0;
	private int myScrapCnt = 0;	
	private String useYn = "Y"; 
	private String rgstDtm = ""; 
	private String mdfyDtm = "";
	
	
	public int getScrapSn() {
		return scrapSn;
	}
	public void setScrapSn(int scrapSn) {
		this.scrapSn = scrapSn;
	}
	public String getScrapCd() {
		return scrapCd;
	}
	public void setScrapCd(String scrapCd) {
		this.scrapCd = scrapCd;
	}
	public int getPrdSn() {
		return prdSn;
	}
	public void setPrdSn(int prdSn) {
		this.prdSn = prdSn;
	}
	public String getPrdNm() {
		return prdNm;
	}
	public void setPrdNm(String prdNm) {
		this.prdNm = prdNm;
	}
	public int getBlcSn() {
		return blcSn;
	}
	public void setBlcSn(int blcSn) {
		this.blcSn = blcSn;
	}
	public String getBlcTitl() {
		return blcTitl;
	}
	public void setBlcTitl(String blcTitl) {
		this.blcTitl = blcTitl;
	}
	public int getMbrSn() {
		return mbrSn;
	}
	public void setMbrSn(int mbrSn) {
		this.mbrSn = mbrSn;
	}
	public int getMyScrapCnt() {
		return myScrapCnt;
	}
	public void setMyScrapCnt(int myScrapCnt) {
		this.myScrapCnt = myScrapCnt;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRgstDtm() {
		return rgstDtm;
	}
	public void setRgstDtm(String rgstDtm) {
		this.rgstDtm = rgstDtm;
	}
	public String getMdfyDtm() {
		return mdfyDtm;
	}
	public void setMdfyDtm(String mdfyDtm) {
		this.mdfyDtm = mdfyDtm;
	}
	
}
