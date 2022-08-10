package vc.virusclean.admin.shop.vo;

import java.io.Serializable;

/**
 * @Class Name : OrderProductVO.java
 * @Description : 주문 상품정보 VO : VC_PAY_03
 */

@SuppressWarnings("serial")
public class OrderProductVO implements Serializable {

	private int orderSn = 0;
	private int prdSn = 0;
	private String prdTitl = "";
	private String orderOpt = "";
	private String giftSbc = "";
	private int orderCost = 0;
	private int orderCnt = 0;
	private int productPrice = 0;
	private int discountPrice = 0;
	private int orderPrice = 0;
	private String cupCd = "";
	private String cupTitl = "";
	private String rgstDtm = "";
	
	
	public int getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(int orderSn) {
		this.orderSn = orderSn;
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
	public String getOrderOpt() {
		return orderOpt;
	}
	public void setOrderOpt(String orderOpt) {
		this.orderOpt = orderOpt;
	}
	public String getGiftSbc() {
		return giftSbc;
	}
	public void setGiftSbc(String giftSbc) {
		this.giftSbc = giftSbc;
	}
	public int getOrderCost() {
		return orderCost;
	}
	public void setOrderCost(int orderCost) {
		this.orderCost = orderCost;
	}
	public int getOrderCnt() {
		return orderCnt;
	}
	public void setOrderCnt(int orderCnt) {
		this.orderCnt = orderCnt;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getCupCd() {
		return cupCd;
	}
	public void setCupCd(String cupCd) {
		this.cupCd = cupCd;
	}
	public String getCupTitl() {
		return cupTitl;
	}
	public void setCupTitl(String cupTitl) {
		this.cupTitl = cupTitl;
	}
	public String getRgstDtm() {
		return rgstDtm;
	}
	public void setRgstDtm(String rgstDtm) {
		this.rgstDtm = rgstDtm;
	}
}