package vc.virusclean.cmm.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jksoft.com.web.vo.SearchVO;

/**
 * @Class Name : UserBoardReplyVO.java
 * @Description : 사용자 게시판 댓글 @VO 클래스
 */

@SuppressWarnings("serial")
@JsonInclude(Include.NON_EMPTY)
public class UserBoardReplyVO extends SearchVO {

	private int blcCmdSn = 0; 
	private int parBlcCmdSn = 0; 
	private int blcSn = 0; 
	private String blcCmdSbc = ""; 
	private String useYn = "Y";

    
    /*
     * 회원 UUID - 비회원시 0
     */
    private int mbrSn = 0; 
    private String mbrId = ""; 
    private String mbrNick = ""; 
    private String rgstDtm = ""; 
    private String userViewDtm = ""; 
    
    /*
     * 삭제이유
     */
    private String mgrRmk = ""; 
    private int mgrSn= 0; 
    private String mgrId = ""; 
    private String  mgrDelDtm = "";
    
    
	public int getBlcCmdSn() {
		return blcCmdSn;
	}
	public void setBlcCmdSn(int blcCmdSn) {
		this.blcCmdSn = blcCmdSn;
	}
	public int getParBlcCmdSn() {
		return parBlcCmdSn;
	}
	public void setParBlcCmdSn(int parBlcCmdSn) {
		this.parBlcCmdSn = parBlcCmdSn;
	}
	public int getBlcSn() {
		return blcSn;
	}
	public void setBlcSn(int blcSn) {
		this.blcSn = blcSn;
	}
	public String getBlcCmdSbc() {
		return blcCmdSbc;
	}
	public void setBlcCmdSbc(String blcCmdSbc) {
		this.blcCmdSbc = blcCmdSbc;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
}