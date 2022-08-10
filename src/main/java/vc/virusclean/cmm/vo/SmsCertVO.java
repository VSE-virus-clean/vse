package vc.virusclean.cmm.vo;

import java.io.Serializable;

/**
 * @Class Name : SmsCertVO.java
 * @Description : 휴대폰본인인증번호 @VO 클래스
 */

@SuppressWarnings("serial")
public class SmsCertVO implements Serializable {

    /*
     * SEQ 
     */
    private int certSn = 0; 

    /*
     * 휴대번호
     */
    private String hpNo = ""; 

    /*
     * 인증번호 
     */
    private String certNo = ""; 

    /*
     * 등록일시 
     */
    private String rgstDtm = ""; 
    
    /*
     * 확인일시
     */
    private String vrfyDtm = "";
    

	public int getCertSn() {
		return certSn;
	}

	public void setCertSn(int certSn) {
		this.certSn = certSn;
	}

	public String getHpNo() {
		return hpNo;
	}

	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getRgstDtm() {
		return rgstDtm;
	}

	public void setRgstDtm(String rgstDtm) {
		this.rgstDtm = rgstDtm;
	}

	public String getVrfyDtm() {
		return vrfyDtm;
	}

	public void setVrfyDtm(String vrfyDtm) {
		this.vrfyDtm = vrfyDtm;
	} 
}