package vc.virusclean.cmm.vo;

import jksoft.com.web.vo.SearchVO;

/**
 * @Class Name : PasswordLogVO.java
 * @Description : 비밀번호변경로그 @VO 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2016.1.15
 * @version 1.0
 * @see vc.virusclean.cmm.vo.PasswordLogVO
 * @Modification Information
 * <pre>
 *   수정일         수정자                  수정내용
 *  ==========     ========    ===========================
 *  2016.1.15      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */

@SuppressWarnings("serial")
public class PasswordLogVO extends SearchVO {

    /**
     * 관리자아이디 
     * MGR_ID : varchar(20)
     */
    private String mgrId = ""; 

    /**
     * 비밀번호 (암호화:MD5 + SHA512) 
     * MGR_PW : varchar(256)
     */
    private String mgrPw = ""; 

    /**
     * 수정관리자아이디 
     * MDFY_MGR_ID : varchar(20)
     */
    private String mdfyId = ""; 

    /**
     * 수정일시 
     * RGST_DTM : datetime(23)
     */
    private String rgstDtm = ""; 



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
     * @return the rgstDtm
     */
    public String getRgstDtm() {
        return rgstDtm;
    }
 
    /**
     * 수정일시
     * @param rgstDtm the rgstDtm to set
     */    
    public void setRgstDtm(String rgstDtm) {
        this.rgstDtm = rgstDtm;
    }

}