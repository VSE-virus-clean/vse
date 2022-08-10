package vc.virusclean.user.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @Class Name : memberVO.java
 * @Description : @VO 클래스
 */

@SuppressWarnings("serial")
@JsonInclude(Include.NON_EMPTY)
public class MemberApiVO implements Serializable {

    /**
     * 회원일렬번호-UUID 
     */
    private int mbrSn = 0; 

    /**
     * 회원아이디-탈퇴시에 삭제 
     */
    private String mbrId = ""; 
    

    /**
     * 회원별명 
     */
    private String mbrNick = ""; 
        
    /**
     * 성별
     * - F(Female):여성
     * - M(Male) : 남성
     */
    private String genderCd = "";
    
    /*
     * 회원등급(일반-normal/브론즈-bronze(3개)/실버-silver(10개)/골드-gold(30개))
     */
    private String mbrGrade = "NORMAL";
    
    private String mbrNm = "";
    private String mbrTn = "";
    private String mbrHp = "";
    private String mbrEml = "";
    private String mbrBday = "";
    private int mbrAge = 0;
    
    private String zipCd = "";
    private String adrSbc1 = "";
    private String adrSbc2 = "";
    
    private int point = 0;
    private String stCd = "Y";
    private String bbsYn = "Y";
    private String smsYn = "N";
    private String smsDtm = "";
    private String emlYn = "N";
    private String emlDtm = "";
    private String pushYn = "N";
    private String pushDtm = "";
    private String certYn = "N";
    private String certMet = "";
    private String certDtm = "";
    
    /*
     * 가입경로(HOME/KAKAO/NAVER/APPLE/GOOGLE)
     */
    private String snsCd = "HOME";
    
    /*
     * APP 정보
     */
    private String appOs = "";
    private String appToken = "";
    private String appTokenAltrDtm = "";
    private String appMac = "";
    private String appBootVer = "";
    private String appBootUrl = "";
    private String appFirmVer = "";
    private String appFirmUrl = "";
    private String rgstDtm = "";
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
	public String getGenderCd() {
		return genderCd;
	}
	public void setGenderCd(String genderCd) {
		this.genderCd = genderCd;
	}
	public String getMbrGrade() {
		return mbrGrade;
	}
	public void setMbrGrade(String mbrGrade) {
		this.mbrGrade = mbrGrade;
	}
	public String getMbrNm() {
		return mbrNm;
	}
	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}
	public String getMbrTn() {
		return mbrTn;
	}
	public void setMbrTn(String mbrTn) {
		this.mbrTn = mbrTn;
	}
	public String getMbrHp() {
		return mbrHp;
	}
	public void setMbrHp(String mbrHp) {
		this.mbrHp = mbrHp;
	}
	public String getMbrEml() {
		return mbrEml;
	}
	public void setMbrEml(String mbrEml) {
		this.mbrEml = mbrEml;
	}
	public String getMbrBday() {
		return mbrBday;
	}
	public void setMbrBday(String mbrBday) {
		this.mbrBday = mbrBday;
	}
	public int getMbrAge() {
		return mbrAge;
	}
	public void setMbrAge(int mbrAge) {
		this.mbrAge = mbrAge;
	}
	public String getZipCd() {
		return zipCd;
	}
	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}
	public String getAdrSbc1() {
		return adrSbc1;
	}
	public void setAdrSbc1(String adrSbc1) {
		this.adrSbc1 = adrSbc1;
	}
	public String getAdrSbc2() {
		return adrSbc2;
	}
	public void setAdrSbc2(String adrSbc2) {
		this.adrSbc2 = adrSbc2;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getStCd() {
		return stCd;
	}
	public void setStCd(String stCd) {
		this.stCd = stCd;
	}
	public String getBbsYn() {
		return bbsYn;
	}
	public void setBbsYn(String bbsYn) {
		this.bbsYn = bbsYn;
	}
	public String getSmsYn() {
		return smsYn;
	}
	public void setSmsYn(String smsYn) {
		this.smsYn = smsYn;
	}
	public String getSmsDtm() {
		return smsDtm;
	}
	public void setSmsDtm(String smsDtm) {
		this.smsDtm = smsDtm;
	}
	public String getEmlYn() {
		return emlYn;
	}
	public void setEmlYn(String emlYn) {
		this.emlYn = emlYn;
	}
	public String getEmlDtm() {
		return emlDtm;
	}
	public void setEmlDtm(String emlDtm) {
		this.emlDtm = emlDtm;
	}
	public String getPushYn() {
		return pushYn;
	}
	public void setPushYn(String pushYn) {
		this.pushYn = pushYn;
	}
	public String getPushDtm() {
		return pushDtm;
	}
	public void setPushDtm(String pushDtm) {
		this.pushDtm = pushDtm;
	}
	public String getCertYn() {
		return certYn;
	}
	public void setCertYn(String certYn) {
		this.certYn = certYn;
	}
	public String getCertMet() {
		return certMet;
	}
	public void setCertMet(String certMet) {
		this.certMet = certMet;
	}
	public String getCertDtm() {
		return certDtm;
	}
	public void setCertDtm(String certDtm) {
		this.certDtm = certDtm;
	}
	public String getSnsCd() {
		return snsCd;
	}
	public void setSnsCd(String snsCd) {
		this.snsCd = snsCd;
	}
	public String getAppOs() {
		return appOs;
	}
	public void setAppOs(String appOs) {
		this.appOs = appOs;
	}
	public String getAppToken() {
		return appToken;
	}
	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}
	public String getAppTokenAltrDtm() {
		return appTokenAltrDtm;
	}
	public void setAppTokenAltrDtm(String appTokenAltrDtm) {
		this.appTokenAltrDtm = appTokenAltrDtm;
	}
	public String getAppMac() {
		return appMac;
	}
	public void setAppMac(String appMac) {
		this.appMac = appMac;
	}
	public String getAppBootVer() {
		return appBootVer;
	}
	public void setAppBootVer(String appBootVer) {
		this.appBootVer = appBootVer;
	}
	public String getAppBootUrl() {
		return appBootUrl;
	}
	public void setAppBootUrl(String appBootUrl) {
		this.appBootUrl = appBootUrl;
	}
	public String getAppFirmVer() {
		return appFirmVer;
	}
	public void setAppFirmVer(String appFirmVer) {
		this.appFirmVer = appFirmVer;
	}
	public String getAppFirmUrl() {
		return appFirmUrl;
	}
	public void setAppFirmUrl(String appFirmUrl) {
		this.appFirmUrl = appFirmUrl;
	}
	public String getRgstDtm() {
		return rgstDtm;
	}
	public void setRgstDtm(String rgstDtm) {
		this.rgstDtm = rgstDtm;
	}
	
}