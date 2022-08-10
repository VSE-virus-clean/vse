package vc.virusclean.admin.auth.vo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import jksoft.com.web.vo.SearchVO;

/**
 * @Class Name : AuthVO.java
 * @Description : @VO 클래스
 */

@SuppressWarnings("serial")
public class AuthVO extends SearchVO {

    /**
     * 관리자 일렬번호 
     * MGR_SN : int(11)
     */
    private int mgrSn = 0; 

    /**
     * 관리자아이디 
     * MGR_ID : varchar(20)
     */
    private String mgrId = ""; 

    /**
     * 관리자이름 
     * MGR_NM : nvarchar(256)
     */
    private String mgrNm = ""; 

    /**
     * 비밀번호 
     * MGR_PW : varchar(256)
     */
    private String mgrPw = ""; 

    /**
     * 비밀번호 Base64로 암호화
     */
    private String mgrPwEnc = ""; 
    
    /**
     * 이메일 
     * MGR_EML : varchar(256)
     */
    private String mgrEml = ""; 

    /**
     * 연락처 
     * MGR_TN : varchar(256)
     */
    private String mgrTn = ""; 

    /**
     * 부서명 
     * MGR_OPS_NM : nvarchar(256)
     */
    private String mgrOpsNm = ""; 

    /**
     * 직위명 
     * MGR_POA_NM : nvarchar(256)
     */
    private String mgrPoaNm = ""; 

    /**
     * 관리자권한레벨(S:최고관리자, A:일반관리자) 
     * MGR_AUTH_CD : char(1)
     */
    private String mgrAuthCd = ""; 

    /**
     * 접속가능아이피 
     * CNNC_PSBL_IP : varchar(64)
     */
    private String cnncPsblIp = ""; 

    /**
     * 상태(Y:사용, N:사용중지, D:삭제)  
     * ST_CD : char(1)
     */
    private String stCd = ""; 

    /**
     * 임시비밀번호 여부(Y:임시비밀번호, N:실제비밀번호)  
     * TMP_PW_YN : char(1)
     */
    private String tmpPwYn = ""; 

    /**
     * 최종 접속일시  
     * FIN_CNNC_DTM : datetime(23)
     */
    private String finCnncDtm = ""; 

    /**
     * 최종 비밀번호 변경일시  
     * FIN_PW_ALTR_DTM : datetime(23)
     */
    private String finPwAltrDtm = ""; 

    /**
     * 비밀번호 오류 횟수(5회 이상은 로그인 안됨)  
     * PW_ERR_OFT : numeric(3)
     */
    private int pwErrOft = 0; 

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

    private String returnUrl = "";
    
    /**
     * 접근가능한 메뉴아이디
     */
    private String menu = "";
    private String menuCd = "";
    
    @JsonIgnore
    private List<String> menuCds;
    

    /**
     * 관리자 일렬번호
     * @return the mgrSn
     */
    public int getMgrSn() {
        return mgrSn;
    }
 
    /**
     * 관리자 일렬번호
     * @param mgrSn the mgrSn to set
     */
    public void setMgrSn(int mgrSn) {
        this.mgrSn = mgrSn;
    }

    /**
     * 관리자아이디
     * @return the mgrId
     */
    public String getMgrId() {
        return mgrId;
    }
 
    /**
     * 관리자아이디
     * @param mgrId the mgrId to set
     */    
    public void setMgrId(String mgrId) {
        this.mgrId = mgrId;
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

    /**
     * 비밀번호
     * @return the mgrPw
     */
    public String getMgrPw() {
        return mgrPw;
    }
 
    /**
     * 비밀번호
     * @param mgrPw the mgrPw to set
     */    
    public void setMgrPw(String mgrPw) {
        this.mgrPw = mgrPw;
    }

    /**
     * 이메일
     * @return the mgrEml
     */
    public String getMgrEml() {
        return mgrEml;
    }
 
    /**
     * 이메일
     * @param mgrEml the mgrEml to set
     */    
    public void setMgrEml(String mgrEml) {
        this.mgrEml = mgrEml;
    }

    /**
     * 사무실전화번호
     * @return the mgrTn
     */
    public String getMgrTn() {
        return mgrTn;
    }
 
    /**
     * 사무실전화번호
     * @param mgrTn the mgrTn to set
     */    
    public void setMgrTn(String mgrTn) {
        this.mgrTn = mgrTn;
    }

    /**
     * 소속부서명
     * @return the mgrOpsNm
     */
    public String getMgrOpsNm() {
        return mgrOpsNm;
    }
 
    /**
     * 소속부서명
     * @param mgrOpsNm the mgrOpsNm to set
     */    
    public void setMgrOpsNm(String mgrOpsNm) {
        this.mgrOpsNm = mgrOpsNm;
    }

    /**
     * 직위명
     * @return the mgrPoaNm
     */
    public String getMgrPoaNm() {
        return mgrPoaNm;
    }
 
    /**
     * 직위명
     * @param mgrPoaNm the mgrPoaNm to set
     */    
    public void setMgrPoaNm(String mgrPoaNm) {
        this.mgrPoaNm = mgrPoaNm;
    }

    /**
     * 관리자권한레벨(S:최고관리자, A:일반관리자)
     * @return the mgrAuthCd
     */
    public String getMgrAuthCd() {
        return mgrAuthCd;
    }
 
    /**
     * 관리자권한레벨(S:최고관리자, A:일반관리자)
     * @param mgrAuthCd the mgrAuthCd to set
     */    
    public void setMgrAuthCd(String mgrAuthCd) {
        this.mgrAuthCd = mgrAuthCd;
    }

    /**
     * 접속가능아이피
     * @return the cnncPsblIp
     */
    public String getCnncPsblIp() {
        return cnncPsblIp;
    }
 
    /**
     * 접속가능아이피
     * @param cnncPsblIp the cnncPsblIp to set
     */    
    public void setCnncPsblIp(String cnncPsblIp) {
        this.cnncPsblIp = cnncPsblIp;
    }

    /**
     * 상태(Y:사용, N:사용중지, D:삭제)
     * @return the stCd
     */
    public String getStCd() {
        return stCd;
    }
 
    /**
     * 상태(Y:사용, N:사용중지, D:삭제)
     * @param stCd the stCd to set
     */    
    public void setStCd(String stCd) {
        this.stCd = stCd;
    }

    /**
     * 임시비밀번호 여부(Y:임시비밀번호, N:실제비밀번호)
     * @return the tmpPwYn
     */
    public String getTmpPwYn() {
        return tmpPwYn;
    }
 
    /**
     * 임시비밀번호 여부(Y:임시비밀번호, N:실제비밀번호)
     * @param tmpPwYn the tmpPwYn to set
     */    
    public void setTmpPwYn(String tmpPwYn) {
        this.tmpPwYn = tmpPwYn;
    }

    /**
     * 최종 접속일시
     * @return the finCnncDtm
     */
    public String getFinCnncDtm() {
        return finCnncDtm;
    }
 
    /**
     * 최종 접속일시
     * @param finCnncDtm the finCnncDtm to set
     */    
    public void setFinCnncDtm(String finCnncDtm) {
        this.finCnncDtm = finCnncDtm;
    }

    /**
     * 최종 비밀번호 변경일시
     * @return the finPwAltrDtm
     */
    public String getFinPwAltrDtm() {
        return finPwAltrDtm;
    }
 
    /**
     * 최종 비밀번호 변경일시
     * @param finPwAltrDtm the finPwAltrDtm to set
     */    
    public void setFinPwAltrDtm(String finPwAltrDtm) {
        this.finPwAltrDtm = finPwAltrDtm;
    }

    /**
     * 비밀번호 오류 횟수(5회 이상은 로그인 안됨)
     * @return the pwErrOft
     */
    public int getPwErrOft() {
        return pwErrOft;
    }
 
    /**
     * 비밀번호 오류 횟수(5회 이상은 로그인 안됨)
     * @param pwErrOft the pwErrOft to set
     */    
    public void setPwErrOft(int pwErrOft) {
        this.pwErrOft = pwErrOft;
    }

    /**
     * 비밀번호 Base64로 암호화
     * @param mgrPwEnc the mgrPwEnc to set
     */
    public void setMgrPwEnc(String mgrPwEnc) {
        this.mgrPwEnc = mgrPwEnc;
    }

    /**
     * 비밀번호 Base64로 암호화
     * @return the mgrPwEnc
     */
    public String getMgrPwEnc() {
        return mgrPwEnc;
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
     * @return the returnUrl
     */
    public String getReturnUrl() {
        return returnUrl;
    }

    /**
     * @param returnUrl the returnUrl to set
     */
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getMenuCd() {
		return menuCd;
	}

	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}

	public List<String> getMenuCds() {
		return menuCds;
	}

	public void setMenuCds(List<String> menuCds) {
		this.menuCds = menuCds;
	}
    
}