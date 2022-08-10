package vc.virusclean.cmm.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jksoft.com.web.vo.FileVO;

/**
 * @Class Name : UserBoardVO.java
 * @Description : 사용자 게시판 @VO 클래스
 */

@SuppressWarnings("serial")
@JsonInclude(Include.NON_EMPTY)
public class UserBoardVO extends FileVO {

    /**
     * 게시물일렬번호
     */
    private int blcSn = 0; 
    
    /**
     * 제품코드 
     */
    private int prdSn= 0; 
    private String prdTitl = ""; 
    
    /*
     * 대분류코드 (QNA / REVIEW)
     */
    private String lgrpCd = ""; 
    
    /*
     * 중분류코드 - 문의유형
     * - 제품, 배송, 교환/반품, 기타
     */
    private String mgrpCd = ""; 
    
    /*
     * 소분류코드 - 첨부파일 등록시 표시(포토뷰로 분류)
     */
    private String sgrpCd = ""; 
    
    /*
     * 게시물 제목
     */
    private String blcTitl = ""; 
    
    /*
     * 게시물 내용1
     */
    private String blcSbc1 = ""; 
    
    /*
     * 게시물 내용2
     */
    private String blcSbc2 = ""; 
    
    /*
     * 평점(리뷰일경우)
     */
    private int grade = 0; 
    
    /*
     * 게시물 조회수
     */
    private int blcRct= 0; 
    
    /*
     * 비밀번호(일반회원 글입력시)
     */
    private String blcPw = ""; 
    
    /*
     * 답변여부(Y:답변, N:미답변)
     */
    private String asYn = "N"; 
    
    /*
     * 공지여부(Y:공지, N:일반)
     */
    private String notiYn = "N"; 
    
    /*
     * 사용여부(Y:공개, N:비공개, D:삭제)
     */
    private String useYn = "Y"; 
    
    /*
     * 비밀글여부(Y:비밀글, N:일반글)
     */
    private String secretYn = "N"; 
    
    /*
     * 회원 UUID - 비회원시 0
     */
    private int mbrSn= 0; 
    private String mbrId = ""; 
    private String mbrNm = ""; 
    private String mbrNick = ""; 
    private String rgstDtm = ""; 
    private String userViewDtm = ""; 
    private String mdfyDtm = ""; 
    
    
    private String dist1 = "";
    private String dist2 = "";
    private String exPay = "";
    private String workTime = "";
    private String item1 = "";
    private String item2 = "";
    private String item3 = "";
    private String rgstName = "";
    private String rgstGenderCd = "";
    private String rgstHp = "";
    private String rgstEml = "";

    
    /*
     * 삭제이유
     */
    private String mgrRmk = ""; 
    private int mgrSn= 0; 
    private String mgrId = ""; 
    private String  mgrDelDtm = "";
    
    private int myScrapCnt = 0;
    private int scrapCnt = 0;
    private int replyCnt = 0;
    
	public int getBlcSn() {
		return blcSn;
	}
	public void setBlcSn(int blcSn) {
		this.blcSn = blcSn;
	}
	public int getPrdSn() {
		return prdSn;
	}
	public void setPrdSn(int prdSn) {
		this.prdSn = prdSn;
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
	public String getBlcTitl() {
		return blcTitl;
	}
	public void setBlcTitl(String blcTitl) {
		this.blcTitl = blcTitl;
	}
	public String getBlcSbc1() {
		return blcSbc1;
	}
	public void setBlcSbc1(String blcSbc1) {
		this.blcSbc1 = blcSbc1;
	}
	public String getBlcSbc2() {
		return blcSbc2;
	}
	public void setBlcSbc2(String blcSbc2) {
		this.blcSbc2 = blcSbc2;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getBlcRct() {
		return blcRct;
	}
	public void setBlcRct(int blcRct) {
		this.blcRct = blcRct;
	}
	public String getBlcPw() {
		return blcPw;
	}
	public void setBlcPw(String blcPw) {
		this.blcPw = blcPw;
	}
	public String getAsYn() {
		return asYn;
	}
	public void setAsYn(String asYn) {
		this.asYn = asYn;
	}
	public String getNotiYn() {
		return notiYn;
	}
	public void setNotiYn(String notiYn) {
		this.notiYn = notiYn;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getSecretYn() {
		return secretYn;
	}
	public void setSecretYn(String secretYn) {
		this.secretYn = secretYn;
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
	public String getMbrNick() {
		return mbrNick;
	}
	public void setMbrNick(String mbrNick) {
		this.mbrNick = mbrNick;
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
	public String getMgrRmk() {
		return mgrRmk;
	}
	public void setMgrRmk(String mgrRmk) {
		this.mgrRmk = mgrRmk;
	}
	public int getMgrSn() {
		return mgrSn;
	}
	public void setMgrSn(int mgrSn) {
		this.mgrSn = mgrSn;
	}
	public String getMgrId() {
		return mgrId;
	}
	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}
	public String getMgrDelDtm() {
		return mgrDelDtm;
	}
	public void setMgrDelDtm(String mgrDelDtm) {
		this.mgrDelDtm = mgrDelDtm;
	}
	public int getMyScrapCnt() {
		return myScrapCnt;
	}
	public void setMyScrapCnt(int myScrapCnt) {
		this.myScrapCnt = myScrapCnt;
	}
	public int getScrapCnt() {
		return scrapCnt;
	}
	public void setScrapCnt(int scrapCnt) {
		this.scrapCnt = scrapCnt;
	}
	public String getPrdTitl() {
		return prdTitl;
	}
	public void setPrdTitl(String prdTitl) {
		this.prdTitl = prdTitl;
	}
	public String getMbrNm() {
		return mbrNm;
	}
	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}
	public int getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
	public String getDist1() {
		return dist1;
	}
	public void setDist1(String dist1) {
		this.dist1 = dist1;
	}
	public String getDist2() {
		return dist2;
	}
	public void setDist2(String dist2) {
		this.dist2 = dist2;
	}
	public String getExPay() {
		return exPay;
	}
	public void setExPay(String exPay) {
		this.exPay = exPay;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getItem1() {
		return item1;
	}
	public void setItem1(String item1) {
		this.item1 = item1;
	}
	public String getItem2() {
		return item2;
	}
	public void setItem2(String item2) {
		this.item2 = item2;
	}
	public String getRgstName() {
		return rgstName;
	}
	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}
	public String getRgstGenderCd() {
		return rgstGenderCd;
	}
	public void setRgstGenderCd(String rgstGenderCd) {
		this.rgstGenderCd = rgstGenderCd;
	}
	public String getRgstHp() {
		return rgstHp;
	}
	public void setRgstHp(String rgstHp) {
		this.rgstHp = rgstHp;
	}
	public String getRgstEml() {
		return rgstEml;
	}
	public void setRgstEml(String rgstEml) {
		this.rgstEml = rgstEml;
	}
	public String getItem3() {
		return item3;
	}
	public void setItem3(String item3) {
		this.item3 = item3;
	}
}