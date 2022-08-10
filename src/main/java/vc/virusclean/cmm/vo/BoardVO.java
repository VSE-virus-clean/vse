package vc.virusclean.cmm.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jksoft.com.web.vo.FileVO;

/**
 * @Class Name : BoardVO.java
 * @Description : 일반 게시판 @VO 클래스
 */

@SuppressWarnings("serial")
@JsonInclude(Include.NON_EMPTY)
public class BoardVO extends FileVO {

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
    private String siteCd = ""; 
    
    /**
     * 언어구분 (NN:구분없음.)
     * - KR:국문, EN:영문, ZH:중문
     * SITE_CD : char(2)
     */
    private String langCd = ""; 

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
    
    //기타임시
    private String blcSbc3 = ""; 
    private String blcSbc4 = ""; 
    
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
     * 사용여부
     * - Y:공개, N:비공개, D:삭제 
     * USE_YN : char(1)
     */
    private String useYn = "Y"; 
    
    /**
     * 비밀글여부
     * - Y:비밀글, N:일반글
     * SECRET_YN : char(1)
     */
    private String secretYn = "N"; 

    /**
     * 등록자 UUID
     * RGST_SN : int
     */
    private int rgstSn = 0; 
    
    /**
     * 수정자 UUID 
     * MDFY_SN : int
     */
    private int mdfySn = 0; 
    
    /**
     * 등록자아이디 
     * RGST_ID : varchar(20)
     */
    private String rgstId = ""; 

    /**
     * 등록일시 
     * RGST_DTM : datetime(23)
     */
    private String rgstDtm = ""; 

    /**
     * 수정자아이디 
     * MDFY_ID : varchar(20)
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
    private String mgrNm = "";
    private String mbrNick = "";

    /**
     *공지대상구분 
     */
    private String userType = "";
    private String rgstTn = "";
    private String rgstEml = "";
    
    /**
     * '일반가격'
     */
    private int itemPrice = 0;
    
    /**
     * '회원가격'
     */
    private int itemFcPrice = 0;
    
    /**
     * '옵션'
     */
    private String itemOption = "";
    
    /**
     * 배송정보
     */
    private String itemDelivery = "";
    
    /**
     * 판매원
     */
    private String itemSales = "";
    
    
	/**
	 * 공지대상구분 
	 * @return
	 */
    public String getUserType() {
		return userType;
	}
    /**
     * 공지대상구분 
     * @return
     */
	public void setUserType(String userType) {
		this.userType = userType;
	}

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
     * 노출등록일시
     * @return the expsRgstDtm
     */
    public String getExpsRgstDtm() {
        return expsRgstDtm;
    }
 
    /**
     * 노출등록일시
     * @param expsRgstDtm the expsRgstDtm to set
     */    
    public void setExpsRgstDtm(String expsRgstDtm) {
        this.expsRgstDtm = expsRgstDtm;
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

    public String getBlcPw() {
		return blcPw;
	}

	public void setBlcPw(String blcPw) {
		this.blcPw = blcPw;
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
    
    public String getSecretYn() {
		return secretYn;
	}

	public void setSecretYn(String secretYn) {
		this.secretYn = secretYn;
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
    
    public int getRgstSn() {
		return rgstSn;
	}

	public void setRgstSn(int rgstSn) {
		this.rgstSn = rgstSn;
	}

	public int getMdfySn() {
		return mdfySn;
	}

	public void setMdfySn(int mdfySn) {
		this.mdfySn = mdfySn;
	}

	/**
     * 관리자이름
     * @return the mgrNm
     */
    public String getMgrNm() {
        return mgrNm;
    }

    /**
     * 관리자이름
     * @param mgrNm the mgrNm to set
     */
    public void setMgrNm(String mgrNm) {
        this.mgrNm = mgrNm;
    }

	public String getExpsRgstDay() {
		return expsRgstDay;
	}

	public void setExpsRgstDay(String expsRgstDay) {
		this.expsRgstDay = expsRgstDay;
	}

	public String getExpsRgstTime() {
		return expsRgstTime;
	}

	public void setExpsRgstTime(String expsRgstTime) {
		this.expsRgstTime = expsRgstTime;
	}

	public String getExpsRgstMinute() {
		return expsRgstMinute;
	}

	public void setExpsRgstMinute(String expsRgstMinute) {
		this.expsRgstMinute = expsRgstMinute;
	}
	/**
	 * @return the asYn
	 */
	public String getAsYn() {
		return asYn;
	}
	/**
	 * @param asYn the asYn to set
	 */
	public void setAsYn(String asYn) {
		this.asYn = asYn;
	}
	/**
	 * @return the mbrNick
	 */
	public String getMbrNick() {
		return mbrNick;
	}
	/**
	 * @param mbrNick the mbrNick to set
	 */
	public void setMbrNick(String mbrNick) {
		this.mbrNick = mbrNick;
	}
	/**
	 * @return the rgstTn
	 */
	public String getRgstTn() {
		return rgstTn;
	}
	/**
	 * @param rgstTn the rgstTn to set
	 */
	public void setRgstTn(String rgstTn) {
		this.rgstTn = rgstTn;
	}
	/**
	 * @return the rgstEml
	 */
	public String getRgstEml() {
		return rgstEml;
	}
	/**
	 * @param rgstEml the rgstEml to set
	 */
	public void setRgstEml(String rgstEml) {
		this.rgstEml = rgstEml;
	}
	/**
	 * @return the userViewDtm
	 */
	public String getUserViewDtm() {
		return userViewDtm;
	}
	/**
	 * @param userViewDtm the userViewDtm to set
	 */
	public void setUserViewDtm(String userViewDtm) {
		this.userViewDtm = userViewDtm;
	}
	/**
	 * @return the itemPrice
	 */
	public int getItemPrice() {
		return itemPrice;
	}
	/**
	 * @param itemPrice the itemPrice to set
	 */
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	/**
	 * @return the itemFcPrice
	 */
	public int getItemFcPrice() {
		return itemFcPrice;
	}
	/**
	 * @param itemFcPrice the itemFcPrice to set
	 */
	public void setItemFcPrice(int itemFcPrice) {
		this.itemFcPrice = itemFcPrice;
	}
	/**
	 * @return the itemOption
	 */
	public String getItemOption() {
		return itemOption;
	}
	/**
	 * @param itemOption the itemOption to set
	 */
	public void setItemOption(String itemOption) {
		this.itemOption = itemOption;
	}
	/**
	 * @return the itemDelivery
	 */
	public String getItemDelivery() {
		return itemDelivery;
	}
	/**
	 * @param itemDelivery the itemDelivery to set
	 */
	public void setItemDelivery(String itemDelivery) {
		this.itemDelivery = itemDelivery;
	}
	/**
	 * @return the itemSales
	 */
	public String getItemSales() {
		return itemSales;
	}
	/**
	 * @param itemSales the itemSales to set
	 */
	public void setItemSales(String itemSales) {
		this.itemSales = itemSales;
	}
	public String getExpsFnhDtm() {
		return expsFnhDtm;
	}
	public void setExpsFnhDtm(String expsFnhDtm) {
		this.expsFnhDtm = expsFnhDtm;
	}
	public String getExpsFnhDay() {
		return expsFnhDay;
	}
	public void setExpsFnhDay(String expsFnhDay) {
		this.expsFnhDay = expsFnhDay;
	}
	public String getExpsFnhTime() {
		return expsFnhTime;
	}
	public void setExpsFnhTime(String expsFnhTime) {
		this.expsFnhTime = expsFnhTime;
	}
	public String getExpsFnhMinute() {
		return expsFnhMinute;
	}
	public void setExpsFnhMinute(String expsFnhMinute) {
		this.expsFnhMinute = expsFnhMinute;
	}
	public int getStNo() {
		return stNo;
	}
	public void setStNo(int stNo) {
		this.stNo = stNo;
	}
	public String getBlcSbc3() {
		return blcSbc3;
	}
	public void setBlcSbc3(String blcSbc3) {
		this.blcSbc3 = blcSbc3;
	}
	public String getBlcSbc4() {
		return blcSbc4;
	}
	public void setBlcSbc4(String blcSbc4) {
		this.blcSbc4 = blcSbc4;
	}
	
}