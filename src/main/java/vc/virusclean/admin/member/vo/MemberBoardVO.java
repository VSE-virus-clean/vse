package vc.virusclean.admin.member.vo;

import jksoft.com.web.vo.FileVO;

/**
 * @Class Name : MemberBoardVO.java
 * @Description : 사용자 게시판 
 */

@SuppressWarnings("serial")
public class MemberBoardVO extends FileVO {

    /**
     * 게시물일렬번호
     * BLC_SN : int(11)
     */
    private int blcSn = 0; 

    /**
     * 사이트코드
     * - 관리자:ADM , 코코소리:COS
     * SITE_CD : char(2)
     */
    private String siteCd = "IDM"; 
    
    /**
     * 언어구분 (NN:구분없음.)
     * - KR:국문, EN:영문, ZH:중문
     * SITE_CD : char(2)
     */
    private String langCd = "JP"; 

    /**
     * 게시판아이디 
     * - 뉴스&공지 : AAA
     * BLB_ID : varchar(6)
     */
    private String blbId = ""; 

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
     * 게시물 조회수 
     * BLC_RCT : int(11)
     */
    private int blcRct = 0; 
    
    /**
     * 댓글 갯수
     */
    private int replyRct = 0;

    /**
     * 공지여부
     * - Y:공지, N:일반 
     * NOTI_YN : char(1)
     */
    private String notiYn = "N"; 

    /**
     * 사용여부
     * - Y:공개, N:비공개, D:삭제 
     * USE_YN : char(1)
     */
    private String useYn = "Y"; 

    /**
     * 등록관리자아이디 
     * RGST_MGR_ID : varchar(20)
     */
    private String rgstId = ""; 

    /**
     * 등록일시 
     * RGST_DTM : datetime(23)
     */
    private String rgstDtm = ""; 

    /**
     * 수정관리자아이디 
     * MDFY_MGR_ID : varchar(20)
     */
    private String mdfyId = ""; 

    /**
     * 수정일시 
     * MDFY_DTM : datetime(23)
     */
    private String mdfyDtm = ""; 
    
    /**
     * 관리자이름
     */
    private int mbrSn = 0;
    
    /**
     * 회원별명 
     * MBR_NICK : VARCHAR(128)
     */
    private String mbrNick = ""; 



    /**
     * 게시물일렬번호
     * @return the blcSn
     */
    public int getBlcSn() {
        return blcSn;
    }
 
    /**
     * 게시물일렬번호
     * @param blcSn the blcSn to set
     */
    public void setBlcSn(int blcSn) {
        this.blcSn = blcSn;
    }

    /**
    * 사이트코드
     * SITE_CD : char(2)
     * @return the siteCd
     */
    public String getSiteCd() {
        return siteCd.toUpperCase();
    }
 
    /**
     * 사이트코드
     * SITE_CD : char(2)
     * @param siteCd the siteCd to set
     */    
    public void setSiteCd(String siteCd) {
        this.siteCd = siteCd.toUpperCase();
    }
    
    /**
     * 언어 코드
     * LANG_CD : char(2)
     * @return the langCd
     */
    public String getLangCd() {
        return langCd.toUpperCase();
    }

    /**
     * 언어 코드
     * LANG_CD : char(2)
     * @param langCd the langCd to set
     */
    public void setLangCd(String langCd) {
        this.langCd = langCd.toUpperCase();
    }

    /**
     * 게시판아이디 
     * - 뉴스&공지 : AAA
     * - IR게시판  : AAB
     * - 회사공고   : AAC
     * @return the blbId
     */
    public String getBlbId() {
        return blbId.toUpperCase();
    }
 
    /**
     * 게시판아이디 
     * - 뉴스&공지 : AAA
     * - IR게시판  : AAB
     * - 회사공고   : AAC
     * @param blbId the blbId to set
     */    
    public void setBlbId(String blbId) {
        this.blbId = blbId.toUpperCase();
    }

    /**
     * 대분류코드
     * @return the lgrpCd
     */
    public String getLgrpCd() {
        return lgrpCd.toUpperCase();
    }
 
    /**
     * 대분류코드
     * @param lgrpCd the lgrpCd to set
     */    
    public void setLgrpCd(String lgrpCd) {
        this.lgrpCd = lgrpCd.toUpperCase();
    }

    /**
     * 중분류코드
     * @return the mgrpCd
     */
    public String getMgrpCd() {
        return mgrpCd.toUpperCase();
    }
 
    /**
     * 중분류코드
     * @param mgrpCd the mgrpCd to set
     */    
    public void setMgrpCd(String mgrpCd) {
        this.mgrpCd = mgrpCd.toUpperCase();
    }

    /**
     * 소분류코드
     * @return the sgrpCd
     */
    public String getSgrpCd() {
        return sgrpCd.toUpperCase();
    }
 
    /**
     * 소분류코드
     * @param sgrpCd the sgrpCd to set
     */    
    public void setSgrpCd(String sgrpCd) {
        this.sgrpCd = sgrpCd.toUpperCase();
    }

    /**
     * 게시물 제목
     * @return the blcTitl
     */
    public String getBlcTitl() {
        return blcTitl;
    }
 
    /**
     * 게시물 제목
     * @param blcTitl the blcTitl to set
     */    
    public void setBlcTitl(String blcTitl) {
        this.blcTitl = blcTitl;
    }

    /**
     * 게시물 내용
     * @return the blcSbc1
     */
    public String getBlcSbc1() {
        return blcSbc1;
    }
 
    /**
     * 게시물 내용
     * @param blcSbc1 the blcSbc1 to set
     */    
    public void setBlcSbc1(String blcSbc1) {
        this.blcSbc1 = blcSbc1;
    }

    /**
     * 게시물 내용
     * @return the blcSbc2
     */
    public String getBlcSbc2() {
        return blcSbc2;
    }
 
    /**
     * 게시물 내용
     * @param blcSbc2 the blcSbc2 to set
     */    
    public void setBlcSbc2(String blcSbc2) {
        this.blcSbc2 = blcSbc2;
    }
    
    /**
     * 관련링크
     * @return the rltdLk
     */
    public String getRltdLk() {
        return rltdLk;
    }
 
    /**
     * 관련링크
     * @param rltdLk the rltdLk to set
     */    
    public void setRltdLk(String rltdLk) {
        this.rltdLk = rltdLk;
    }

    /**
     * 게시물 조회수
     * @return the blcRct
     */
    public int getBlcRct() {
        return blcRct;
    }
 
    /**
     * 게시물 조회수
     * @param blcRct the blcRct to set
     */
    public void setBlcRct(int blcRct) {
        this.blcRct = blcRct;
    }

    
    public int getReplyRct() {
		return replyRct;
	}

	public void setReplyRct(int replyRct) {
		this.replyRct = replyRct;
	}

	/**
     * 공지여부(Y:공지, N:일반)
     * @return the notiYn
     */
    public String getNotiYn() {
        return notiYn;
    }
 
    /**
     * 공지여부(Y:공지, N:일반)
     * @param notiYn the notiYn to set
     */    
    public void setNotiYn(String notiYn) {
        this.notiYn = notiYn;
    }

    /**
     * 사용여부(Y:공개, N:비공개, D:삭제)
     * @return the useYn
     */
    public String getUseYn() {
        return useYn;
    }
 
    /**
     * 사용여부(Y:공개, N:비공개, D:삭제)
     * @param useYn the useYn to set
     */    
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    /**
     * 등록관리자아이디
     * @return the rgstId
     */
    public String getRgstId() {
        return rgstId;
    }
 
    /**
     * 등록관리자아이디
     * @param rgstId the rgstId to set
     */    
    public void setRgstId(String rgstId) {
        this.rgstId = rgstId;
    }

    /**
     * 등록일시
     * @return the rgstDtm
     */
    public String getRgstDtm() {
        return rgstDtm;
    }
 
    /**
     * 등록일시
     * @param rgstDtm the rgstDtm to set
     */    
    public void setRgstDtm(String rgstDtm) {
        this.rgstDtm = rgstDtm;
    }

    /**
     * 수정관리자아이디
     * @return the mdfyId
     */
    public String getMdfyId() {
        return mdfyId;
    }
 
    /**
     * 수정관리자아이디
     * @param mdfyId the mdfyId to set
     */    
    public void setMdfyId(String mdfyId) {
        this.mdfyId = mdfyId;
    }

    /**
     * 수정일시
     * @return the mdfyDtm
     */
    public String getMdfyDtm() {
        return mdfyDtm;
    }
 
    /**
     * 수정일시
     * @param mdfyDtm the mdfyDtm to set
     */    
    public void setMdfyDtm(String mdfyDtm) {
        this.mdfyDtm = mdfyDtm;
    }
    
    /**
     * 관리자이름
     * @return the mbrSn
     */
    public int getMbrSn() {
        return mbrSn;
    }

    /**
     * 관리자이름
     * @param mbrSn the mbrSn to set
     */
    public void setMbrSn(int mbrSn) {
        this.mbrSn = mbrSn;
    }

	public String getMbrNick() {
		return mbrNick;
	}

	public void setMbrNick(String mbrNick) {
		this.mbrNick = mbrNick;
	}
}