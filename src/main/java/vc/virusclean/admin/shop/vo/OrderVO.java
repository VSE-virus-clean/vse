package vc.virusclean.admin.shop.vo;

import java.util.List;

/**
 * @Class Name : OrderVO.java
 * @Description : 주문정보 VO : VC_PAY_01
 */

@SuppressWarnings("serial")
public class OrderVO extends PaymentVO {

	public OrderVO(){
		//기본생성자
	}
	
	/**
	 * 주문번호 생성자
	 */
	public OrderVO(String orderNo){
		this.orderNo = orderNo;
	}
	
	private int orderSn = 0;
	private int mbrSn = 0;
	private String buyerNm = "";
	private String buyerTn = "";
	private String buyerHp = "";
	private String buyerEml = "";
	private String buyerZipCd = "";
	private String buyerAdrSbc1 = "";
	private String buyerAdrSbc2 = "";
	private String receiverNm = "";
	private String receiverTn = "";
	private String receiverHp = "";
	private String receiverEml = "";
	private String receiverZipCd = "";
	private String receiverAdrCity = "";
	private String receiverAdrSbc1 = "";
	private String receiverAdrSbc2 = "";
	private String orderMemo = "";
	private String orderOption = "";
	private String giftSbc = "";
	
	//주문비밀번호-비회원구매시입력
	private String orderPwd = "";
	private String orderPwdEnc = "";
	
	private String cupCd = "";
	private String cupTitl = "";
	
	//주문상품명
	private String goodNm = "";
	private int orderQuantity = 0;
	private int productPrice = 0;
	private int discountPrice = 0;
	private int deliveryFee = 0;
	private int orderPrice = 0;
	private String orderNo = "";
	
	//가상계좌 은행코드
	private String vbankCd = "";
	
	//가상계좌 은행명
	private String vbankNm = "";
	
	//가상계좌 은행 계좌번호
	private String vbankNum = "";
	
	//가상계좌 입금만료일
	private String vbankFnhDtm = "";
	
	/**
	 *  입금대기
		배송준비중(카드 결제, 무통장 입금 후 확인한 상태)
		배송중(송장 등록되면 전환)
		배송완료(송장 등록 후 도착하면 완료)
		구매확정
		취소신청
		취소완료
		반품신청
		반품완료
		교환신청
		교환완료
	 */
	private String orderCd = "입금대기";
	private String payDtm = "";
	private String deliveryCorp = "";
	private String deliveryNum = "";
	private String deliveryYn = "N";
	private String deliveryStrDtm = "";
	private String deliveryFnhDtm = "";
	private String rgstDtm = "";
	private String userViewDtm = "";
	
	
	
	private List<OrderProductVO> lOrderProductVO = null;
	
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
	public String getBuyerNm() {
		return buyerNm;
	}
	public void setBuyerNm(String buyerNm) {
		this.buyerNm = buyerNm;
	}
	public String getBuyerTn() {
		return buyerTn;
	}
	public void setBuyerTn(String buyerTn) {
		this.buyerTn = buyerTn;
	}
	public String getBuyerHp() {
		return buyerHp;
	}
	public void setBuyerHp(String buyerHp) {
		this.buyerHp = buyerHp;
	}
	public String getBuyerEml() {
		return buyerEml;
	}
	public void setBuyerEml(String buyerEml) {
		this.buyerEml = buyerEml;
	}
	public String getBuyerZipCd() {
		return buyerZipCd;
	}
	public void setBuyerZipCd(String buyerZipCd) {
		this.buyerZipCd = buyerZipCd;
	}
	public String getBuyerAdrSbc1() {
		return buyerAdrSbc1;
	}
	public void setBuyerAdrSbc1(String buyerAdrSbc1) {
		this.buyerAdrSbc1 = buyerAdrSbc1;
	}
	public String getBuyerAdrSbc2() {
		return buyerAdrSbc2;
	}
	public void setBuyerAdrSbc2(String buyerAdrSbc2) {
		this.buyerAdrSbc2 = buyerAdrSbc2;
	}
	public String getReceiverNm() {
		return receiverNm;
	}
	public void setReceiverNm(String receiverNm) {
		this.receiverNm = receiverNm;
	}
	public String getReceiverTn() {
		return receiverTn;
	}
	public void setReceiverTn(String receiverTn) {
		this.receiverTn = receiverTn;
	}
	public String getReceiverHp() {
		return receiverHp;
	}
	public void setReceiverHp(String receiverHp) {
		this.receiverHp = receiverHp;
	}
	public String getReceiverEml() {
		return receiverEml;
	}
	public void setReceiverEml(String receiverEml) {
		this.receiverEml = receiverEml;
	}
	public String getReceiverZipCd() {
		return receiverZipCd;
	}
	public void setReceiverZipCd(String receiverZipCd) {
		this.receiverZipCd = receiverZipCd;
	}
	public String getReceiverAdrCity() {
		return receiverAdrCity;
	}
	public void setReceiverAdrCity(String receiverAdrCity) {
		this.receiverAdrCity = receiverAdrCity;
	}
	public String getReceiverAdrSbc1() {
		return receiverAdrSbc1;
	}
	public void setReceiverAdrSbc1(String receiverAdrSbc1) {
		this.receiverAdrSbc1 = receiverAdrSbc1;
	}
	public String getReceiverAdrSbc2() {
		return receiverAdrSbc2;
	}
	public void setReceiverAdrSbc2(String receiverAdrSbc2) {
		this.receiverAdrSbc2 = receiverAdrSbc2;
	}
	public String getOrderMemo() {
		return orderMemo;
	}
	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}
	public String getOrderOption() {
		return orderOption;
	}
	public void setOrderOption(String orderOption) {
		this.orderOption = orderOption;
	}
	public String getGiftSbc() {
		return giftSbc;
	}
	public void setGiftSbc(String giftSbc) {
		this.giftSbc = giftSbc;
	}
	public String getOrderPwd() {
		return orderPwd;
	}
	public void setOrderPwd(String orderPwd) {
		this.orderPwd = orderPwd;
	}
	public String getOrderPwdEnc() {
		return orderPwdEnc;
	}
	public void setOrderPwdEnc(String orderPwdEnc) {
		this.orderPwdEnc = orderPwdEnc;
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
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
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
	public int getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderCd() {
		return orderCd;
	}
	public void setOrderCd(String orderCd) {
		this.orderCd = orderCd;
	}
	public String getPayDtm() {
		return payDtm;
	}
	public void setPayDtm(String payDtm) {
		this.payDtm = payDtm;
	}
	public String getDeliveryCorp() {
		return deliveryCorp;
	}
	public void setDeliveryCorp(String deliveryCorp) {
		this.deliveryCorp = deliveryCorp;
	}
	public String getDeliveryNum() {
		return deliveryNum;
	}
	public void setDeliveryNum(String deliveryNum) {
		this.deliveryNum = deliveryNum;
	}
	public String getDeliveryYn() {
		return deliveryYn;
	}
	public void setDeliveryYn(String deliveryYn) {
		this.deliveryYn = deliveryYn;
	}
	public String getDeliveryStrDtm() {
		return deliveryStrDtm;
	}
	public void setDeliveryStrDtm(String deliveryStrDtm) {
		this.deliveryStrDtm = deliveryStrDtm;
	}
	public String getDeliveryFnhDtm() {
		return deliveryFnhDtm;
	}
	public void setDeliveryFnhDtm(String deliveryFnhDtm) {
		this.deliveryFnhDtm = deliveryFnhDtm;
	}
	public String getRgstDtm() {
		return rgstDtm;
	}
	public void setRgstDtm(String rgstDtm) {
		this.rgstDtm = rgstDtm;
	}
	public List<OrderProductVO> getlOrderProductVO() {
		return lOrderProductVO;
	}
	public void setlOrderProductVO(List<OrderProductVO> lOrderProductVO) {
		this.lOrderProductVO = lOrderProductVO;
	}
	public String getUserViewDtm() {
		return userViewDtm;
	}
	public void setUserViewDtm(String userViewDtm) {
		this.userViewDtm = userViewDtm;
	}
	public String getGoodNm() {
		return goodNm;
	}
	public void setGoodNm(String goodNm) {
		this.goodNm = goodNm;
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