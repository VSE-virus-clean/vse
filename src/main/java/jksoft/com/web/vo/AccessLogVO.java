package jksoft.com.web.vo;

/**
 * @Class Name : AccessLogVO.java
 * @Description : @VO 클래스
 * @author Jeong.HyoungJea 
 * @since 2014.11.19
 * @version 1.0
 * @see steel.common.vo.AccessLogVO
 * @Modification Information
 * <pre>
 *   수정일         수정자                  수정내용
 *  ==========     ========    ===========================
 *  2014.11.19      Jeong.HyoungJea        최초생성
 * </pre>
 */

@SuppressWarnings("serial")
public class AccessLogVO extends SearchVO {

    /**
     * 관리자 SEQ
     * MGR_SN : VARCHAR2(20)
     */
    private String mgrSn = ""; 
    
    /**
     * 관리자아이디 
     * MGR_ID : VARCHAR2(20)
     */
    private String mgrId = ""; 
    
    /**
     * 관리자명
     * MGR_ID : VARCHAR2(20)
     */
    private String mgrNm = ""; 

    /**
     * 접속IP 
     * CNNC_IP : VARCHAR2(64)
     */
    private String cnncIp = ""; 

    /**
     * 접속메뉴URL 
     * CNNC_MENU_URL : VARCHAR2(1024)
     */
    private String cnncMenuUrl = ""; 

    /**
     * 메뉴아이디 
     * MENU_CD : VARCHAR2(10)
     */
    private String menuCd = ""; 

    /**
     * 컨텐츠 일렬번호 
     * COTN_SN : NUMBER(22)
     */
    private long cotnSn = 0L; 

    /**
     * 파일명 
     * FIL_NM : NVARCHAR2(128)
     */
    private String filNm = ""; 

    /**
     * 파일용량 
     * FIL_CPC : NUMBER(22)
     */
    private long filCpc = 0L;

    /**
     * 작업구분코드 
     * WK_SCN_CD : CHAR(2)
     */
    private String wkScnCd = "";
    
    /**
     * 작업구분설명 
     * WK_SCN_CD : CHAR(2)
     */
    private String wkScnExpl = ""; 

    /**
     * 로그일자 
     * RGST_DTM : DATE(7)
     */
    private String rgstDtm = ""; 



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
     * @return the cnncIp
     */
    public String getCnncIp() {
        return cnncIp;
    }
 
    /**
     * 설명
     * @param cnncIp the cnncIp to set
     */    
    public void setCnncIp(String cnncIp) {
        this.cnncIp = cnncIp;
    }

    /**
     * 설명
     * @return the cnncMenuUrl
     */
    public String getCnncMenuUrl() {
        return cnncMenuUrl;
    }
 
    /**
     * 설명
     * @param cnncMenuUrl the cnncMenuUrl to set
     */    
    public void setCnncMenuUrl(String cnncMenuUrl) {
        this.cnncMenuUrl = cnncMenuUrl;
    }

    /**
     * 설명
     * @return the menuCd
     */
    public String getMenuCd() {
        return menuCd;
    }
 
    /**
     * 설명
     * @param menuCd the menuCd to set
     */    
    public void setMenuCd(String menuCd) {
        this.menuCd = menuCd;
    }

    /**
     * 설명
     * @return the cotnSn
     */
    public long getCotnSn() {
        return cotnSn;
    }
 
    /**
     * 설명
     * @param cotnSn the cotnSn to set
     */
    public void setCotnSn(long cotnSn) {
        this.cotnSn = cotnSn;
    }

    /**
     * 설명
     * @return the filNm
     */
    public String getFilNm() {
        return filNm;
    }
 
    /**
     * 설명
     * @param filNm the filNm to set
     */    
    public void setFilNm(String filNm) {
        this.filNm = filNm;
    }

    /**
     * 파일 용량
     * @return the filCpc
     */
    public long getFilCpc() {
        return filCpc;
    }
 
    /**
     * 파일 용량
     * @param filCpc the filCpc to set
     */
    public void setFilCpc(long filCpc) {
        this.filCpc = filCpc;
    }

    /**
     * 설명
     * @return the wkScnCd
     */
    public String getWkScnCd() {
        return wkScnCd;
    }
 
    /**
     * 설명
     * @param wkScnCd the wkScnCd to set
     */    
    public void setWkScnCd(String wkScnCd) {
        this.wkScnCd = wkScnCd;
    }

    /**
     * 설명
     * @return the rgstDtm
     */
    public String getRgstDtm() {
        return rgstDtm;
    }
 
    /**
     * 설명
     * @param rgstDtm the rgstDtm to set
     */    
    public void setRgstDtm(String rgstDtm) {
        this.rgstDtm = rgstDtm;
    }

    /**
     * @return the mgrNm
     */
    public String getMgrNm() {
        return mgrNm;
    }

    /**
     * @param mgrNm the mgrNm to set
     */
    public void setMgrNm(String mgrNm) {
        this.mgrNm = mgrNm;
    }

    /**
     * @return the wkScnExpl
     */
    public String getWkScnExpl() {
        return wkScnExpl;
    }

    /**
     * @param wkScnExpl the wkScnExpl to set
     */
    public void setWkScnExpl(String wkScnExpl) {
        this.wkScnExpl = wkScnExpl;
    }

    /**
     * @return the mgrSn
     */
    public String getMgrSn() {
        return mgrSn;
    }

    /**
     * @param mgrSn the mgrSn to set
     */
    public void setMgrSn(String mgrSn) {
        this.mgrSn = mgrSn;
    }
    
}