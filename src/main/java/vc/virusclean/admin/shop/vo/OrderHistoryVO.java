package vc.virusclean.admin.shop.vo;

import jksoft.com.web.vo.FileVO;

/**
 * @Class Name : OrderHistoryVO.java
 * @Description : 주문상태 변경 히스토리
 */

@SuppressWarnings("serial")
public class OrderHistoryVO extends FileVO {
	
	private int orderHisSn = 0;
	private int orderSn = 0;
	private String orderNo = "";
	
	//이전 - 주문진행상태/배송상태
	private String oldOrderCd = "";
	
	//현재 - 주문진행상태/배송상태
	private String nowOrderCd = "";
	
	
	private String excSbc1 = "";
	private String excSbc2 = "";
	
	//결제금액
	private int payAmt = 0;
	
	//차감금액
	private int subAmt = 0;
	
	//환불금액
	private int refAmt = 0;
	
	private int mbrSn = 0;
	private int rgstSn = 0;
	private String rgstId = "";
	private String rgstDtm = "";
	private String userViewDtm = "";
	
	
	//환불계좌 은행코드
	private String refBankCd = "";
	
	//환불계좌 은행명
	private String refBankNm = "";
	
	//환불계좌 계좌번호
	private String refBankNum = "";
	
	//환불계좌 예금주명
	private String refAcctNm = "";
	
	
	public int getOrderHisSn() {
		return orderHisSn;
	}
	public void setOrderHisSn(int orderHisSn) {
		this.orderHisSn = orderHisSn;
	}
	public int getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(int orderSn) {
		this.orderSn = orderSn;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOldOrderCd() {
		return oldOrderCd;
	}
	public void setOldOrderCd(String oldOrderCd) {
		this.oldOrderCd = oldOrderCd;
	}
	public String getNowOrderCd() {
		return nowOrderCd;
	}
	public void setNowOrderCd(String nowOrderCd) {
		this.nowOrderCd = nowOrderCd;
	}
	public String getExcSbc1() {
		return excSbc1;
	}
	public void setExcSbc1(String excSbc1) {
		this.excSbc1 = excSbc1;
	}
	public String getExcSbc2() {
		return excSbc2;
	}
	public void setExcSbc2(String excSbc2) {
		this.excSbc2 = excSbc2;
	}
	public int getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(int payAmt) {
		this.payAmt = payAmt;
	}
	public int getSubAmt() {
		return subAmt;
	}
	public void setSubAmt(int subAmt) {
		this.subAmt = subAmt;
	}
	public int getRefAmt() {
		return refAmt;
	}
	public void setRefAmt(int refAmt) {
		this.refAmt = refAmt;
	}
	public int getMbrSn() {
		return mbrSn;
	}
	public void setMbrSn(int mbrSn) {
		this.mbrSn = mbrSn;
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
	public String getUserViewDtm() {
		return userViewDtm;
	}
	public void setUserViewDtm(String userViewDtm) {
		this.userViewDtm = userViewDtm;
	}
	public String getRefBankCd() {
		return refBankCd;
	}
	public void setRefBankCd(String refBankCd) {
		this.refBankCd = refBankCd;
	}
	public String getRefBankNm() {
		return refBankNm;
	}
	public void setRefBankNm(String refBankNm) {
		this.refBankNm = refBankNm;
	}
	public String getRefBankNum() {
		return refBankNum;
	}
	public void setRefBankNum(String refBankNum) {
		this.refBankNum = refBankNum;
	}
	public String getRefAcctNm() {
		return refAcctNm;
	}
	public void setRefAcctNm(String refAcctNm) {
		this.refAcctNm = refAcctNm;
	}
}