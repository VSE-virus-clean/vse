package vc.virusclean.admin.member.vo;

import jksoft.com.web.vo.SearchVO;

/**
 * @Class Name : MemberBoardReplyVO.java
 * @Description : 사용자 게시판 댓글
 */

@SuppressWarnings("serial")
public class MemberBoardReplyVO extends SearchVO {

	/**
     * 댓글일렬번호
     * BLC_CMD_SN : int(11)
     */
    private int blcCmdSn = 0;
    
    /**
     * 게시물일렬번호
     * BLC_SN : int(11)
     */
    private int blcSn = 0; 

    /**
     * 관리자일렬번호
     */
    private int mbrSn = 0;
    
    /**
     * 회원별명 
     * MBR_NICK : VARCHAR(128)
     */
    private String mbrNick = ""; 
    
    /**
     * 댓글 내용 
     * BLC_CMD_SBC TEXT
     */
    private String blcCmdSbc = ""; 
    
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

	public int getBlcCmdSn() {
		return blcCmdSn;
	}

	public void setBlcCmdSn(int blcCmdSn) {
		this.blcCmdSn = blcCmdSn;
	}

	public int getBlcSn() {
		return blcSn;
	}

	public void setBlcSn(int blcSn) {
		this.blcSn = blcSn;
	}

	public int getMbrSn() {
		return mbrSn;
	}

	public void setMbrSn(int mbrSn) {
		this.mbrSn = mbrSn;
	}

	public String getMbrNick() {
		return mbrNick;
	}

	public void setMbrNick(String mbrNick) {
		this.mbrNick = mbrNick;
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

	public String getRgstId() {
		return rgstId;
	}

	public void setRgstId(String rgstId) {
		this.rgstId = rgstId;
	}

	public String getRgstDtm() {
		return rgstDtm;
	}

	public void setRgstDtm(String rgstDtm) {
		this.rgstDtm = rgstDtm;
	}

	public String getMdfyId() {
		return mdfyId;
	}

	public void setMdfyId(String mdfyId) {
		this.mdfyId = mdfyId;
	}

	public String getMdfyDtm() {
		return mdfyDtm;
	}

	public void setMdfyDtm(String mdfyDtm) {
		this.mdfyDtm = mdfyDtm;
	} 
}