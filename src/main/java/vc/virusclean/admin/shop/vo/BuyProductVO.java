package vc.virusclean.admin.shop.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Class Name : BuyProductVO.java
 * @Description : 구매페이지용 vO
 */

@SuppressWarnings("serial")
public class BuyProductVO implements Serializable {
	
	//상품번호
	private List<String> prdSn;
	
	private List<String> ITEM_ID;
	
	//상품갯수
	private List<String> prdCnt;
	
	private List<String> orderQuantity;
	
	//상품명
	private List<String> prdTitl;
	
	//상품단가
	private List<String> prdPrice;
	
	//상품주문가격(상품가격 * 갯수 - 쿠폰적용가)
	private List<String> prdTotalPrice;
	
	//쿠폰가격
	private List<String> prdDiscountPrice;
	
	

	public List<String> getPrdSn() {
		return prdSn;
	}

	public void setPrdSn(List<String> prdSn) {
		this.prdSn = prdSn;
	}

	public List<String> getPrdCnt() {
		return prdCnt;
	}

	public void setPrdCnt(List<String> prdCnt) {
		this.prdCnt = prdCnt;
	}
	
	public List<String> getOrderQuantity() {
		return prdCnt;
	}
	
	public void setOrderQuantity(List<String> orderQuantity) {
		this.prdCnt = orderQuantity;
	}
	
	public List<String> getPrdTitl() {
		return prdTitl;
	}

	public void setPrdTitl(List<String> prdTitl) {
		this.prdTitl = prdTitl;
	}

	public List<String> getPrdPrice() {
		return prdPrice;
	}

	public void setPrdPrice(List<String> prdPrice) {
		this.prdPrice = prdPrice;
	}

	public List<String> getPrdTotalPrice() {
		return prdTotalPrice;
	}

	public void setPrdTotalPrice(List<String> prdTotalPrice) {
		this.prdTotalPrice = prdTotalPrice;
	}

	public List<String> getPrdDiscountPrice() {
		return prdDiscountPrice;
	}

	public void setPrdDiscountPrice(List<String> prdDiscountPrice) {
		this.prdDiscountPrice = prdDiscountPrice;
	}

	public List<String> getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(List<String> iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}
}