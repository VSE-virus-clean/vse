package vc.virusclean.admin.shop.vo;

import jksoft.com.web.vo.FileVO;

/**
 * @Class Name : OriginProductVO.java
 * @Description : 정품등록 VO
 */

@SuppressWarnings("serial")
public class CartVO extends FileVO {
	
	private int cartSn = 0;
	
	/*
	 * 비회원일경우 램덤키(램덤문자(4) + 시간) 
	 * - 세션으로 저장 + 쿠키로도 저장(쿠키명 : CART_ID)
	 */
	private String cartId = "";
	private int mbrSn = 0;	

	private int prdSn = 0;
	private String prdTitl = "";
	private String prdOpt = "";
	private int salePrice = 0;
	
	//판매가능여부
	private String sellYn = "Y";
	private int prdCnt = 1;
	
	private String useYn = "Y";
	private String rgstDtm = "";
    private String userViewDtm = "";
	private String mdfyDtm = "";
	
	
	public int getCartSn() {
		return cartSn;
	}
	public void setCartSn(int cartSn) {
		this.cartSn = cartSn;
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
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
	public String getPrdTitl() {
		return prdTitl;
	}
	public void setPrdTitl(String prdTitl) {
		this.prdTitl = prdTitl;
	}
	public String getPrdOpt() {
		return prdOpt;
	}
	public void setPrdOpt(String prdOpt) {
		this.prdOpt = prdOpt;
	}
	public int getPrdCnt() {
		return prdCnt;
	}
	public void setPrdCnt(int prdCnt) {
		this.prdCnt = prdCnt;
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
	public String getUserViewDtm() {
		return userViewDtm;
	}
	public void setUserViewDtm(String userViewDtm) {
		this.userViewDtm = userViewDtm;
	}
	public String getMdfyDtm() {
		return mdfyDtm;
	}
	public void setMdfyDtm(String mdfyDtm) {
		this.mdfyDtm = mdfyDtm;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public String getSellYn() {
		return sellYn;
	}
	public void setSellYn(String sellYn) {
		this.sellYn = sellYn;
	}

}