package vc.virusclean.admin.auth.vo;

import java.io.Serializable;

/**
 * @Class Name : LoginVO.java
 * @Description : 로그인 / 비빌번호찾기 @VO 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2017.12.25
 * @version 1.0
 * @see vc.virusclean.admin.auth.vo.LoginVO
 * @Modification Information
 * <pre>
 *   수정일         수정자                  수정내용
 *  ==========     ========    ===========================
 *  2017.12.25      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */

@SuppressWarnings("serial")
public class LoginVO implements Serializable {

    /**
     * 관리자 일렬번호 
     */
    private int mgrSn = 0; 

    /**
     * 관리자아이디 
     */
    private String mgrId = ""; 

    /**
     * 관리자이름 
     */
    private String mgrNm = ""; 

    /**
     * 비밀번호 
     */
    private String mgrPw = ""; 

    /**
     * 비밀번호 Base64로 암호화
     */
    private String mgrPwEnc = ""; 
    
    /**
     * 새로운 비밀번호
     */
    private String mgrNewPw = "";
    
    /**
     * 새로운 비밀번호 Base64로 암호화
     */
    private String mgrNewPwEnc = "";

    private String returnUrl = "";
    
    
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
     * 비밀번호 Base64로 암호화
     * @return the mgrPwEnc
     */
    public String getMgrPwEnc() {
        return mgrPwEnc;
    }

    /**
     * 비밀번호 Base64로 암호화
     * @param mgrPwEnc the mgrPwEnc to set
     */
    public void setMgrPwEnc(String mgrPwEnc) {
        this.mgrPwEnc = mgrPwEnc;
    }

    /**
     * 새로운 비밀번호
     * @return the mgrNewPw
     */
    public String getMgrNewPw() {
        return mgrNewPw;
    }

    /**
     * 새로운 비밀번호
     * @param mgrNewPw the mgrNewPw to set
     */
    public void setMgrNewPw(String mgrNewPw) {
        this.mgrNewPw = mgrNewPw;
    }

    /**
     * 새로운 비밀번호 Base64로 암호화
     * @return the mgrNewPwEnc
     */
    public String getMgrNewPwEnc() {
        return mgrNewPwEnc;
    }

    /**
     * 새로운 비밀번호 Base64로 암호화
     * @param mgrNewPwEnc the mgrNewPwEnc to set
     */
    public void setMgrNewPwEnc(String mgrNewPwEnc) {
        this.mgrNewPwEnc = mgrNewPwEnc;
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

}