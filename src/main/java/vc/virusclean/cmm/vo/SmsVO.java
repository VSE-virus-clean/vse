package vc.virusclean.cmm.vo;

import java.io.Serializable;

/**
 * @Class Name : SmsVO.java
 * @Description : SMS발송 @VO 클래스
 */

@SuppressWarnings("serial")
public class SmsVO implements Serializable {

	/**
	 * 데이터 타입 (SMS 0, FAX 2, PHONE 3, MMS 5, AT 6, FT 7, RCS 8, BI 11, BW 12)
	 */
	private int msgType = 0; 
	
	/**
	 * CMID는 고유값이다. 사용자 UUID와 시간을 하던가 할것.
	 */
	private String cmid = "";
	
	private String requestTime = "";
	private String sendTime = "";

	/**
	 * 수신자 번호/명
	 */
	private String destName = "";
	private String destPhone = "";
	
	/**
	 * 발신자 번호/명
	 */
	private String sendName = "";
	private String sendPhone = "";
	
	/**
	 * 메시지 내용(2000)
	 */
	private String msgBody = "";

	private String templateCode = "";
	private String senderKey = "";
	
	
	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public String getCmid() {
		return cmid;
	}

	public void setCmid(String cmid) {
		this.cmid = cmid;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	public String getDestPhone() {
		return destPhone;
	}

	public void setDestPhone(String destPhone) {
		this.destPhone = destPhone;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getSendPhone() {
		return sendPhone;
	}

	public void setSendPhone(String sendPhone) {
		this.sendPhone = sendPhone;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getSenderKey() {
		return senderKey;
	}

	public void setSenderKey(String senderKey) {
		this.senderKey = senderKey;
	}
	
	
}