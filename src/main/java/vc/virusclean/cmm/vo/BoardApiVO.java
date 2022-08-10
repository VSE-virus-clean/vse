package vc.virusclean.cmm.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @Class Name : BoardVO.java
 * @Description : 일반 게시판 @VO 클래스
 */

@SuppressWarnings("serial")
@JsonInclude(Include.NON_EMPTY)
public class BoardApiVO implements Serializable {

    /**
     * 게시물일렬번호
     * BLC_SN : int(11)
     */
    private int blcSn = 0; 

    /**
     * 대분류코드(카테고리) 
     * LGRP_CD : varchar(6)
     */
    private String lgrpCd = ""; 

    /**
     * 중분류코드 
     * MGRP_CD : varchar(6)
     */
    private String mgrpCd = ""; 

    /**
     * 소분류코드 
     * SGRP_CD : varchar(6)
     */
    private String sgrpCd = ""; 

    /**
     * 게시물 제목 
     * BLC_TITL : nvarchar(256)
     */
    private String blcTitl = ""; 

    /**
     * 게시물 내용 
     * BLC_SBC1 : ntext(1073741823)
     */
    private String blcSbc1 = ""; 
    /**
     * 게시물 내용 2
     * - FAQ 답변 등으로 사용
     * BLC_SBC2 : ntext(1073741823)
     */
    private String blcSbc2 = ""; 
    
    /**
     * 관련링크 
     * RLTD_LK : varchar(1024)
     */
    private String rltdLk = ""; 

    /**
     * 노출등록일시
     * - 화면 표기 일자. 정렬에서도 사용
     * EXPS_RGST_DTM : varchar(1)
     */
    private String expsRgstDtm = ""; 
    private String expsFnhDtm = "";
    private String userViewDtm = ""; 
    
    /**
     * 게시물 조회수 
     * BLC_RCT : int(11)
     */
    private int blcRct = 0; 
    
    /**
     * 정렬순서
     */
    private int stNo = 0;
    
    /**
     * 비밀번호(일반회원 글입력시) 
     * BLC_PW : varchar(512)
     */
    private String blcPw = ""; 

    /**
     * 답변여부
     */
    private String asYn = "N";
    
    /**
     * 공지여부
     * - Y:공지, N:일반 
     * NOTI_YN : char(1)
     */
    private String notiYn = "N"; 

    /**
     * 비밀글여부
     * - Y:비밀글, N:일반글
     * SECRET_YN : char(1)
     */
    private String secretYn = "N"; 

	/**
     * 설명 
     */
    private String fileThumbUrl = "";
    
    private String fileAttcUrl = "";
    

	public int getBlcSn() {
		return blcSn;
	}

	public void setBlcSn(int blcSn) {
		this.blcSn = blcSn;
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

	public String getRltdLk() {
		return rltdLk;
	}

	public void setRltdLk(String rltdLk) {
		this.rltdLk = rltdLk;
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

	public String getUserViewDtm() {
		return userViewDtm;
	}

	public void setUserViewDtm(String userViewDtm) {
		this.userViewDtm = userViewDtm;
	}

	public int getBlcRct() {
		return blcRct;
	}

	public void setBlcRct(int blcRct) {
		this.blcRct = blcRct;
	}

	public int getStNo() {
		return stNo;
	}

	public void setStNo(int stNo) {
		this.stNo = stNo;
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

	public String getSecretYn() {
		return secretYn;
	}

	public void setSecretYn(String secretYn) {
		this.secretYn = secretYn;
	}

	public String getFileThumbUrl() {
		return fileThumbUrl;
	}

	public void setFileThumbUrl(String fileThumbUrl) {
		this.fileThumbUrl = fileThumbUrl;
	}

	public String getFileAttcUrl() {
		return fileAttcUrl;
	}

	public void setFileAttcUrl(String fileAttcUrl) {
		this.fileAttcUrl = fileAttcUrl;
	}
}