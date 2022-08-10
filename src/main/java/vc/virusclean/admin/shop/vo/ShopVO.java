package vc.virusclean.admin.shop.vo;

import jksoft.com.web.vo.SearchVO;

/**
 * @Class Name : ShopVO.java
 * @Description : @VO 클래스
 */

@SuppressWarnings("serial")
public class ShopVO extends SearchVO {

	//아이템 이름
	private String itemNm = "";
	
	//주문일렬번호
	private int orderSn = 0;
	
	//회원일렬번호-UUID
	private int mbrSn = 0;
	
	//상품 일렬번호
	private int cotnSn = 0;
	
	//주문비밀번호-비회원구매시입력
	private String orderPw = "";
	private String orderPwEnc = "";
	
	//주문번호
	private String orderId = "";
	
	//주문자이름-한자(암호화-복호화가능)
	private String buyerNm = "";
	
	//주문자이름-발음-후리가나
	private String buyerNmPn = "";
	
	//주문자이름연락처(암호화-복호화가능)
	private String buyerTn = "";
	
	//주문자이름이메일(암호화-복호화가능)
	private String buyerEml = "";
	
	//주문자이름-한자(암호화-복호화가능)
	private String receiverNm = "";
	
	//주문자이름-발음-후리가나
	private String receiverNmPn = "";
	
	//주문자이름연락처(암호화-복호화가능)
	private String receiverTn = "";
	
	//주문자이름이메일(암호화-복호화가능)
	private String receiverEml = "";
	
	//우편번호
	private String zipCd = "";
	
	//도/현
	private String adrPref = "";
	
	//도시명
	private String adrCity = "";
	
	//상세주소 - 번지
	private String adrSbc1 = "";
	
	//상세주소2 - 건물및 다른이름
	private String adrSbc2 = "";
	
	//아이템 옵션
	private String orderOption = "";
	
	//주문수량
	private int orderQuantity = 0;
	
	//단가
	private int orderCost = 0;
	
	//총결제금액(세금포함)
	private int orderAmount = 0;
	
	//주문시 요청사항
	private String orderMemo = "";
	
	//결제수단(1:계좌이체, 2:신용카드)
	private String payType = "1";
	
	//결제완료여부
	private String payYn = "N";
	
	//배송여부(Y:배송, N:미배송)
	private String deliveryYn = "N";
	
	//사용여부(Y:공개, N:주문취소)
	private String useYn = "Y";
	
	//결제번호
	private String pgTid = "";
	
	//결제일
	private String pgDtm = "";
	
	//등록일시
	private String rgstDtm = "";
	
	//수정일시
	private String mdftDtm = "";
	
	//결제확인담당자 UUID(계좌이체)
	private int rgstAdmSn = 0;
	
	//결제확인담당자 아이디(계좌이체)
	private String rgstAdmId = "";
	
	
	
	public String getItemNm() {
		return itemNm;
	}
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	public int getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(int orderSn) {
		this.orderSn = orderSn;
	}
	public int getMbrSn() {
		return mbrSn;
	}
	public void setMbrSn(int mbrSn) {
		this.mbrSn = mbrSn;
	}
	public int getCotnSn() {
		return cotnSn;
	}
	public void setCotnSn(int cotnSn) {
		this.cotnSn = cotnSn;
	}
	public String getOrderPw() {
		return orderPw;
	}
	public void setOrderPw(String orderPw) {
		this.orderPw = orderPw;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBuyerNm() {
		return buyerNm;
	}
	public void setBuyerNm(String buyerNm) {
		this.buyerNm = buyerNm;
	}
	public String getBuyerNmPn() {
		return buyerNmPn;
	}
	public void setBuyerNmPn(String buyerNmPn) {
		this.buyerNmPn = buyerNmPn;
	}
	public String getBuyerTn() {
		return buyerTn;
	}
	public void setBuyerTn(String buyerTn) {
		this.buyerTn = buyerTn;
	}
	public String getBuyerEml() {
		return buyerEml;
	}
	public void setBuyerEml(String buyerEml) {
		this.buyerEml = buyerEml;
	}
	public String getReceiverNm() {
		return receiverNm;
	}
	public void setReceiverNm(String receiverNm) {
		this.receiverNm = receiverNm;
	}
	public String getReceiverNmPn() {
		return receiverNmPn;
	}
	public void setReceiverNmPn(String receiverNmPn) {
		this.receiverNmPn = receiverNmPn;
	}
	public String getReceiverTn() {
		return receiverTn;
	}
	public void setReceiverTn(String receiverTn) {
		this.receiverTn = receiverTn;
	}
	public String getReceiverEml() {
		return receiverEml;
	}
	public void setReceiverEml(String receiverEml) {
		this.receiverEml = receiverEml;
	}
	public String getZipCd() {
		return zipCd;
	}
	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}
	public String getAdrPref() {
		return adrPref;
	}
	public void setAdrPref(String adrPref) {
		this.adrPref = adrPref;
	}
	public String getAdrCity() {
		return adrCity;
	}
	public void setAdrCity(String adrCity) {
		this.adrCity = adrCity;
	}
	public String getAdrSbc1() {
		return adrSbc1;
	}
	public void setAdrSbc1(String adrSbc1) {
		this.adrSbc1 = adrSbc1;
	}
	public String getAdrSbc2() {
		return adrSbc2;
	}
	public void setAdrSbc2(String adrSbc2) {
		this.adrSbc2 = adrSbc2;
	}
	public String getOrderOption() {
		return orderOption;
	}
	public void setOrderOption(String orderOption) {
		this.orderOption = orderOption;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public int getOrderCost() {
		return orderCost;
	}
	public void setOrderCost(int orderCost) {
		this.orderCost = orderCost;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderMemo() {
		return orderMemo;
	}
	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayYn() {
		return payYn;
	}
	public void setPayYn(String payYn) {
		this.payYn = payYn;
	}
	public String getPgTid() {
		return pgTid;
	}
	public void setPgTid(String pgTid) {
		this.pgTid = pgTid;
	}
	public String getPgDtm() {
		return pgDtm;
	}
	public void setPgDtm(String pgDtm) {
		this.pgDtm = pgDtm;
	}
	public String getRgstDtm() {
		return rgstDtm;
	}
	public void setRgstDtm(String rgstDtm) {
		this.rgstDtm = rgstDtm;
	}
	public String getMdftDtm() {
		return mdftDtm;
	}
	public void setMdftDtm(String mdftDtm) {
		this.mdftDtm = mdftDtm;
	}
	public int getRgstAdmSn() {
		return rgstAdmSn;
	}
	public void setRgstAdmSn(int rgstAdmSn) {
		this.rgstAdmSn = rgstAdmSn;
	}
	public String getRgstAdmId() {
		return rgstAdmId;
	}
	public void setRgstAdmId(String rgstAdmId) {
		this.rgstAdmId = rgstAdmId;
	}
	public String getDeliveryYn() {
		return deliveryYn;
	}
	public void setDeliveryYn(String deliveryYn) {
		this.deliveryYn = deliveryYn;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getOrderPwEnc() {
		return orderPwEnc;
	}
	public void setOrderPwEnc(String orderPwEnc) {
		this.orderPwEnc = orderPwEnc;
	}
}