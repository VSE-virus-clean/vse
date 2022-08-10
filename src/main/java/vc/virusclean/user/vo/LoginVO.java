package vc.virusclean.user.vo;

import java.io.Serializable;

/**
 * @Class Name : LoginVO.java
 * @Description : 로그인 / 비빌번호찾기 @VO 클래스
 */

@SuppressWarnings("serial")
public class LoginVO implements Serializable {

	/**
     * 로그인 SNS 타입 : (HOME/KAKAO/NAVER/APPLE/GOOGLE)
     */
    private String snsCd = "HOME"; 
    
    /**
     * WEB / ANDROID / IOS
     */
    private String appOs = "WEB";
    		
    
    /**
     * 일렬번호 
     */
    private int mbrSn = 0; 

    /**
     * 아이디 
     */
    private String mbrId = ""; 
    
    /**
     * 아이디 
     */
    private String mbrEml = ""; 

    /**
     * 이름 
     */
    private String mbrNm = ""; 
    
    /**
     * 별명
     */
    private String mbrNick = ""; 

    /**
     * 비밀번호 
     */
    private String mbrPw = ""; 

    /**
     * 비밀번호 Base64로 암호화
     */
    private String mbrPwEnc = ""; 
    
    /**
     * 새로운 비밀번호
     */
    private String mbrNewPw = "";
    
    /**
     * 새로운 비밀번호 Base64로 암호화
     */
    private String mbrNewPwEnc = "";

    private String returnUrl = "";
    

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

	public String getMbrNm() {
		return mbrNm;
	}

	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}

	public String getMbrNick() {
		return mbrNick;
	}

	public void setMbrNick(String mbrNick) {
		this.mbrNick = mbrNick;
	}

	public String getMbrPw() {
		return mbrPw;
	}

	public void setMbrPw(String mbrPw) {
		this.mbrPw = mbrPw;
	}

	public String getMbrPwEnc() {
		return mbrPwEnc;
	}

	public void setMbrPwEnc(String mbrPwEnc) {
		this.mbrPwEnc = mbrPwEnc;
	}

	public String getMbrNewPw() {
		return mbrNewPw;
	}

	public void setMbrNewPw(String mbrNewPw) {
		this.mbrNewPw = mbrNewPw;
	}

	public String getMbrNewPwEnc() {
		return mbrNewPwEnc;
	}

	public void setMbrNewPwEnc(String mbrNewPwEnc) {
		this.mbrNewPwEnc = mbrNewPwEnc;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
	public String getSnsCd() {
		return snsCd;
	}
	
	public void setSnsCd(String snsCd) {
		this.snsCd = snsCd;
	}

	public String getMbrEml() {
		return mbrEml;
	}

	public void setMbrEml(String mbrEml) {
		this.mbrEml = mbrEml;
	}

	public String getAppOs() {
		return appOs;
	}

	public void setAppOs(String appOs) {
		this.appOs = appOs;
	}
	
}