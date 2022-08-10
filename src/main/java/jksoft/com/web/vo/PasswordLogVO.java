package jksoft.com.web.vo;


/**
 * @Class Name : PasswordLogVO.java
 * @Description : @VO 클래스
 * @author Jeong.HyoungJea 
 * @since 2014.11.19
 * @version 1.0
 * @see jksoft.com.web.vo.PasswordLogVO
 * @Modification Information
 * <pre>
 *   수정일         수정자                  수정내용
 *  ==========     ========    ===========================
 *  2014.11.19      Jeong.HyoungJea        최초생성
 * </pre>
 */

@SuppressWarnings("serial")
public class PasswordLogVO extends SearchVO {

    /**
     * 설명 
     * MGR_ID : VARCHAR2(20)
     */
    private String mgrId = ""; 

    /**
     * 설명 
     * MGR_PW : VARCHAR2(256)
     */
    private String mgrPw = ""; 

    /**
     * 설명 
     * MDFY_MGR_ID : VARCHAR2(20)
     */
    private String mdfyMgrId = ""; 

    /**
     * 설명 
     * MDFT_DTM : DATE(7)
     */
    private String mdftDtm = ""; 



    /**
     * 설명
     * @return the mgrId
     */
    public String getMgrId() {
        return mgrId;
    }
 
    /**
     * 설명
     * @param mgrId the mgrId to set
     */    
    public void setMgrId(String mgrId) {
        this.mgrId = mgrId;
    }

    /**
     * 설명
     * @return the mgrPw
     */
    public String getMgrPw() {
        return mgrPw;
    }
 
    /**
     * 설명
     * @param mgrPw the mgrPw to set
     */    
    public void setMgrPw(String mgrPw) {
        this.mgrPw = mgrPw;
    }

    /**
     * 설명
     * @return the mdfyMgrId
     */
    public String getMdfyMgrId() {
        return mdfyMgrId;
    }
 
    /**
     * 설명
     * @param mdfyMgrId the mdfyMgrId to set
     */    
    public void setMdfyMgrId(String mdfyMgrId) {
        this.mdfyMgrId = mdfyMgrId;
    }

    /**
     * 설명
     * @return the mdftDtm
     */
    public String getMdftDtm() {
        return mdftDtm;
    }
 
    /**
     * 설명
     * @param mdftDtm the mdftDtm to set
     */    
    public void setMdftDtm(String mdftDtm) {
        this.mdftDtm = mdftDtm;
    }

}