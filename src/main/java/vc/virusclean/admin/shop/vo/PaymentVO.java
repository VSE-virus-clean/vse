package vc.virusclean.admin.shop.vo;

import jksoft.com.web.vo.SearchVO;

/**
 * @Class Name : PaymentVO.java
 * @Description : 결제정보 : VC_PAY_02
 */

@SuppressWarnings("serial")
public class PaymentVO extends SearchVO {

	//UUID
	private int paySn = 0;
	
	//회원UUID
	private int mbrSn = 0;
	
	//구입/환불
	private String payCd = "구입";
	
	//결제지불수단
	private String payType = "";
	private String payTypeTxt = "";		//공통코드 : PAYMETHOD

	
	//결제금액
	private int price = 0;
	
	// oid + price + timestamp의 SHA-256한 값
	private String busiCd = "";
	
	//주문정보 테이블 UUID
	private int orderSn = 0;
	
	//주문번호-ORDER_NO
	private String orderNo = "";
	
	//거래번호-TID
	private String tidNo = "";
	
	//승인번호-카드
	private String applNo = "";
	
	//가상계좌 은행코드
	private String vbankCd = "";
	
	//가상계좌 은행명
	private String vbankNm = "";
	
	//가상계좌 은행 계좌번호
	private String vbankNum = "";
	
	//가상계좌 입금만료일
	private String vbankFnhDtm = "";
	
	//승인결과(코드)
	private String resCd = "";
	
	//승인결과(메시지)
	private String resMsg = "";
	
	//승인일시
	private String resDtm = "";
	
	//등록일시
	private String rgstDtm = "";
	
	//가맹점아아디
	private String mid = "";
	
	//가맹점 암호키
	private String signKey = "";
	
	//signKey의 SHA-256한 값
	private String mKey = "";
	

	public int getPaySn() {
		return paySn;
	}

	public void setPaySn(int paySn) {
		this.paySn = paySn;
	}

	public int getMbrSn() {
		return mbrSn;
	}

	public void setMbrSn(int mbrSn) {
		this.mbrSn = mbrSn;
	}

	public String getPayCd() {
		return payCd;
	}

	public void setPayCd(String payCd) {
		this.payCd = payCd;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBusiCd() {
		return busiCd;
	}

	public void setBusiCd(String busiCd) {
		this.busiCd = busiCd;
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

	public String getTidNo() {
		return tidNo;
	}

	public void setTidNo(String tidNo) {
		this.tidNo = tidNo;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public String getResCd() {
		return resCd;
	}

	public void setResCd(String resCd) {
		this.resCd = resCd;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getResDtm() {
		return resDtm;
	}

	public void setResDtm(String resDtm) {
		this.resDtm = resDtm;
	}

	public String getRgstDtm() {
		return rgstDtm;
	}

	public void setRgstDtm(String rgstDtm) {
		this.rgstDtm = rgstDtm;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getSignKey() {
		return signKey;
	}

	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	public String getmKey() {
		return mKey;
	}

	public void setmKey(String mKey) {
		this.mKey = mKey;
	}

	public String getPayTypeTxt() {
		return payTypeTxt;
	}

	public void setPayTypeTxt(String payTypeTxt) {
		this.payTypeTxt = payTypeTxt;
	}

	public String getVbankCd() {
		return vbankCd;
	}

	public void setVbankCd(String vbankCd) {
		this.vbankCd = vbankCd;
	}

	public String getVbankNm() {
		return vbankNm;
	}

	public void setVbankNm(String vbankNm) {
		this.vbankNm = vbankNm;
	}

	public String getVbankNum() {
		return vbankNum;
	}

	public void setVbankNum(String vbankNum) {
		this.vbankNum = vbankNum;
	}

	public String getVbankFnhDtm() {
		return vbankFnhDtm;
	}

	public void setVbankFnhDtm(String vbankFnhDtm) {
		this.vbankFnhDtm = vbankFnhDtm;
	}
}