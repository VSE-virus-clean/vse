package vc.virusclean.admin.shop.vo;

import jksoft.com.web.vo.FileVO;

/**
 * @Class Name : ProductVO.java
 * @Description : 상품정보 VO
 */

@SuppressWarnings("serial")
public class ProductVO extends FileVO {
	
	private int mbrSn= 0; 
	private int prdSn = 0;
	private String langCd = "KR";
	private String lgrpCd = "";
	private String mgrpCd = "";
	private String sgrpCd = "";
	private String prdTitl = "";
	private String prdSbc1 = "";
	private String prdSbc2 = "";
	private String prdCd = "";
	private String model = "";	//prdCd의 검색용
	private int prdRct = 0;
	
	//공급가격 - supply price
	private int supplyPrice = 0;
	
	//판매가격 - sale price
	private int salePrice = 0;
	
	//재고수량 - quantity
	private int quantity = 0;
	
	//정렬순서
	private int stNo = 0;
	
	//판매여부(N은 품절)
	private String sellYn = "Y";
	private String useYn = "Y";
	private int rgstSn = 0;
	private String rgstId = "";
	private String rgstDtm = "";
	private int mdfySn = 0;
	private String mdfyId = "";
	private String mdfyDtm = "";
	
	/**
     * 노출등록일시
     * - 화면 표기 일자. 정렬에서도 사용
     * EXPS_RGST_DTM : varchar(1)
     */
    private String expsRgstDtm = ""; 
    private String expsFnhDtm = "";
    private String userViewDtm = ""; 
    
    /**
     * 노출등록날짜
     */
    private String expsRgstDay = "";
    private String expsFnhDay = "";
    
    /**
     * 노출등록시간
     */
    private String expsRgstTime = "";
    private String expsFnhTime = "";
    
    /**
     * 노출등록분
     */
    private String expsRgstMinute = "";
    private String expsFnhMinute = "";
    
    /**
     * 사용자 찜여부 확인
     */
    private int myScrapCnt = 0;
    
    //일반리뷰건수
    private int reviewCnt = 0;
    
    //포토리뷰건수
    private int reviewPhotoCnt = 0;
    
    //찜건수
    private int scrapCnt = 0;
    
    //평점5 건수
    private int grade5Cnt = 0;
    
    //평점4 건수
    private int grade4Cnt = 0;
    
    //평점3 건수
    private int grade3Cnt = 0;
    
    //평점2 건수
    private int grade2Cnt = 0;
    
    //평점1 건수
    private int grade1Cnt = 0;
    
    //종합평점
    private int gradeAvg = 0;
    
	
	public int getPrdSn() {
		return prdSn;
	}
	public void setPrdSn(int prdSn) {
		this.prdSn = prdSn;
	}
	public String getLangCd() {
		return langCd;
	}
	public void setLangCd(String langCd) {
		this.langCd = langCd;
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
	public String getPrdTitl() {
		return prdTitl;
	}
	public void setPrdTitl(String prdTitl) {
		this.prdTitl = prdTitl;
	}
	public String getPrdSbc1() {
		return prdSbc1;
	}
	public void setPrdSbc1(String prdSbc1) {
		this.prdSbc1 = prdSbc1;
	}
	public String getPrdSbc2() {
		return prdSbc2;
	}
	public void setPrdSbc2(String prdSbc2) {
		this.prdSbc2 = prdSbc2;
	}
	public String getPrdCd() {
		return prdCd;
	}
	public void setPrdCd(String prdCd) {
		this.prdCd = prdCd;
	}
	public String getModel() {
		return prdCd;
	}
	public void setModel(String model) {
		this.prdCd = model;
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
	public int getPrdRct() {
		return prdRct;
	}
	public void setPrdRct(int prdRct) {
		this.prdRct = prdRct;
	}
	public int getSupplyPrice() {
		return supplyPrice;
	}
	public void setSupplyPrice(int supplyPrice) {
		this.supplyPrice = supplyPrice;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getStNo() {
		return stNo;
	}
	public void setStNo(int stNo) {
		this.stNo = stNo;
	}
	public String getSellYn() {
		return sellYn;
	}
	public void setSellYn(String sellYn) {
		this.sellYn = sellYn;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	public String getUserViewDtm() {
		return userViewDtm;
	}
	public void setUserViewDtm(String userViewDtm) {
		this.userViewDtm = userViewDtm;
	}
	public String getExpsRgstDay() {
		return expsRgstDay;
	}
	public void setExpsRgstDay(String expsRgstDay) {
		this.expsRgstDay = expsRgstDay;
	}
	public String getExpsFnhDay() {
		return expsFnhDay;
	}
	public void setExpsFnhDay(String expsFnhDay) {
		this.expsFnhDay = expsFnhDay;
	}
	public String getExpsRgstTime() {
		return expsRgstTime;
	}
	public void setExpsRgstTime(String expsRgstTime) {
		this.expsRgstTime = expsRgstTime;
	}
	public String getExpsFnhTime() {
		return expsFnhTime;
	}
	public void setExpsFnhTime(String expsFnhTime) {
		this.expsFnhTime = expsFnhTime;
	}
	public String getExpsRgstMinute() {
		return expsRgstMinute;
	}
	public void setExpsRgstMinute(String expsRgstMinute) {
		this.expsRgstMinute = expsRgstMinute;
	}
	public String getExpsFnhMinute() {
		return expsFnhMinute;
	}
	public void setExpsFnhMinute(String expsFnhMinute) {
		this.expsFnhMinute = expsFnhMinute;
	}
	public int getReviewCnt() {
		return reviewCnt;
	}
	public void setReviewCnt(int reviewCnt) {
		this.reviewCnt = reviewCnt;
	}
	public int getReviewPhotoCnt() {
		return reviewPhotoCnt;
	}
	public void setReviewPhotoCnt(int reviewPhotoCnt) {
		this.reviewPhotoCnt = reviewPhotoCnt;
	}
	public int getScrapCnt() {
		return scrapCnt;
	}
	public void setScrapCnt(int scrapCnt) {
		this.scrapCnt = scrapCnt;
	}
	public int getGrade5Cnt() {
		return grade5Cnt;
	}
	public void setGrade5Cnt(int grade5Cnt) {
		this.grade5Cnt = grade5Cnt;
	}
	public int getGrade4Cnt() {
		return grade4Cnt;
	}
	public void setGrade4Cnt(int grade4Cnt) {
		this.grade4Cnt = grade4Cnt;
	}
	public int getGrade3Cnt() {
		return grade3Cnt;
	}
	public void setGrade3Cnt(int grade3Cnt) {
		this.grade3Cnt = grade3Cnt;
	}
	public int getGrade2Cnt() {
		return grade2Cnt;
	}
	public void setGrade2Cnt(int grade2Cnt) {
		this.grade2Cnt = grade2Cnt;
	}
	public int getGrade1Cnt() {
		return grade1Cnt;
	}
	public void setGrade1Cnt(int grade1Cnt) {
		this.grade1Cnt = grade1Cnt;
	}
	public int getGradeAvg() {
		return gradeAvg;
	}
	public void setGradeAvg(int gradeAvg) {
		this.gradeAvg = gradeAvg;
	}
	public int getMyScrapCnt() {
		return myScrapCnt;
	}
	public void setMyScrapCnt(int myScrapCnt) {
		this.myScrapCnt = myScrapCnt;
	}
	public int getMbrSn() {
		return mbrSn;
	}
	public void setMbrSn(int mbrSn) {
		this.mbrSn = mbrSn;
	}
}