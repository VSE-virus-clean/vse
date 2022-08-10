package jksoft.com.web.vo;

import java.io.Serializable;

/**
 * @Class Name : MenuVO.java
 * @Description : @VO 클래스
 * @author Jeong.HyoungJea 
 * @since 2014.11.24
 * @version 1.0
 * @see steel.common.vo.MenuVO
 * @Modification Information
 * <pre>
 *   수정일         수정자                  수정내용
 *  ==========     ========    ===========================
 *  2014.11.24      Jeong.HyoungJea        최초생성
 * </pre>
 */

@SuppressWarnings("serial")
public class MenuVO implements Serializable {

    /**
     * 메뉴코드  
     * MENU_CD : VARCHAR2(6)
     */
    private String menuCd = ""; 

    /**
     * 부모메뉴코드 
     * PREN_MENU_CD : VARCHAR2(6)
     */
    private String prenMenuCd = ""; 

    /**
     * 메뉴명 
     * MENU_NM : VARCHAR2(128)
     */
    private String menuNm = ""; 

    /**
     * 메뉴URL 
     * MENU_URL : VARCHAR2(128)
     */
    private String menuUrl = ""; 

    /**
     * 메뉴검색용URI 
     * MENU_SRCH_URI : VARCHAR2(128)
     */
    private String menuSrchUri = ""; 

    /**
     * 깊이 
     * DEPT : NUMBER(22)
     */
    private int dept = 0; 

    /**
     * 메뉴 정렬순서 
     * ST_NO : NUMBER(22)
     */
    private int stNo = 0; 

    /**
     * 노출여부(Y:사용, N:비노출 ,D:삭제) 
     * USE_YN : CHAR(1)
     */
    private String useYn = ""; 

    /**
     * 등록관리자아이디 
     * RGST_MGR_ID : VARCHAR2(20)
     */
    private String rgstMgrId = ""; 

    /**
     * 등록일시 
     * RGST_DTM : DATE(7)
     */
    private String rgstDtm = ""; 

    /**
     * 수정관리자아이디 
     * MDFY_MGR_ID : VARCHAR2(20)
     */
    private String mdfyMgrId = ""; 

    /**
     * 수정일시 
     * MDFT_DTM : DATE(7)
     */
    private String mdftDtm = ""; 
    
    /**
     * 관리자 아이디
     */
    private String mgrId = "";
    
    /**
     * 메뉴전체 경로 - 메뉴코드
     */
    private String menuPathCd = "";
    
    /**
     * 메뉴전체 경로 - 메뉴명
     */
    private String menuPathNm = "";
    
    /**
     * 메뉴전체 정렬순서
     */
    private String orderPath = "";



    /**
     * 메뉴코드 
     * @return the menuCd
     */
    public String getMenuCd() {
        return menuCd;
    }
 
    /**
     * 메뉴코드
     * @param menuCd the menuCd to set
     */    
    public void setMenuCd(String menuCd) {
        this.menuCd = menuCd;
    }

    /**
     * 메뉴명
     * @return the prenMenuCd
     */
    public String getPrenMenuCd() {
        return prenMenuCd;
    }
 
    /**
     * 메뉴URL
     * @param prenMenuCd the prenMenuCd to set
     */    
    public void setPrenMenuCd(String prenMenuCd) {
        this.prenMenuCd = prenMenuCd;
    }

    /**
     * 메뉴명
     * @return the menuNm
     */
    public String getMenuNm() {
        return menuNm;
    }
 
    /**
     * 메뉴명
     * @param menuNm the menuNm to set
     */    
    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    /**
     * 메뉴URL
     * @return the menuUrl
     */
    public String getMenuUrl() {
        return menuUrl;
    }
 
    /**
     * 메뉴URL
     * @param menuUrl the menuUrl to set
     */    
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    /**
     * 메뉴검색용URI
     * @return the menuSrchUri
     */
    public String getMenuSrchUri() {
        return menuSrchUri;
    }
 
    /**
     * 메뉴검색용URI
     * @param menuSrchUri the menuSrchUri to set
     */    
    public void setMenuSrchUri(String menuSrchUri) {
        this.menuSrchUri = menuSrchUri;
    }

    /**
     * 깊이
     * @return the dept
     */
    public int getDept() {
        return dept;
    }
 
    /**
     * 깊이
     * @param dept the dept to set
     */
    public void setDept(int dept) {
        this.dept = dept;
    }

    /**
     * 메뉴 정렬순서
     * @return the stNo
     */
    public int getStNo() {
        return stNo;
    }
 
    /**
     * 메뉴 정렬순서
     * @param stNo the stNo to set
     */
    public void setStNo(int stNo) {
        this.stNo = stNo;
    }

    /**
     * 사용여부(Y:공개, N:비공개, D:삭제)
     * @return the useYn
     */
    public String getUseYn() {
        return useYn;
    }
 
    /**
     * 사용여부(Y:공개, N:비공개, D:삭제)
     * @param useYn the useYn to set
     */    
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    /**
     * 등록관리자아이디
     * @return the rgstMgrId
     */
    public String getRgstMgrId() {
        return rgstMgrId;
    }
 
    /**
     * 등록관리자아이디
     * @param rgstMgrId the rgstMgrId to set
     */    
    public void setRgstMgrId(String rgstMgrId) {
        this.rgstMgrId = rgstMgrId;
    }

    /**
     * 등록일시
     * @return the rgstDtm
     */
    public String getRgstDtm() {
        return rgstDtm;
    }
 
    /**
     * 등록일시
     * @param rgstDtm the rgstDtm to set
     */    
    public void setRgstDtm(String rgstDtm) {
        this.rgstDtm = rgstDtm;
    }

    /**
     * 수정관리자아이디
     * @return the mdfyMgrId
     */
    public String getMdfyMgrId() {
        return mdfyMgrId;
    }
 
    /**
     * 수정관리자아이디
     * @param mdfyMgrId the mdfyMgrId to set
     */    
    public void setMdfyMgrId(String mdfyMgrId) {
        this.mdfyMgrId = mdfyMgrId;
    }

    /**
     * 수정일시
     * @return the mdftDtm
     */
    public String getMdftDtm() {
        return mdftDtm;
    }
 
    /**
     * 수정일시
     * @param mdftDtm the mdftDtm to set
     */    
    public void setMdftDtm(String mdftDtm) {
        this.mdftDtm = mdftDtm;
    }

    /**
     * 관리자 아이디
     * @return the mgrId
     */
    public String getMgrId() {
        return mgrId;
    }

    /**
     * 관리자 아이디
     * @param mgrId the mgrId to set
     */
    public void setMgrId(String mgrId) {
        this.mgrId = mgrId;
    }

    /**
     * 메뉴전체 경로 - 메뉴코드
     * @return the menuPathCd
     */
    public String getMenuPathCd() {
        return menuPathCd;
    }

    /**
     * 메뉴전체 경로 - 메뉴코드
     * @param menuPathCd the menuPathCd to set
     */
    public void setMenuPathCd(String menuPathCd) {
        this.menuPathCd = menuPathCd;
    }

    /**
     * menuPathNm
     * @return the menuPathNm
     */
    public String getMenuPathNm() {
        return menuPathNm;
    }

    /**
     * menuPathNm
     * @param menuPathNm the menuPathNm to set
     */
    public void setMenuPathNm(String menuPathNm) {
        this.menuPathNm = menuPathNm;
    }

    /**
     * 메뉴전체 정렬순서
     * @return the orderPath
     */
    public String getOrderPath() {
        return orderPath;
    }

    /**
     * 메뉴전체 정렬순서
     * @param orderPath the orderPath to set
     */
    public void setOrderPath(String orderPath) {
        this.orderPath = orderPath;
    }
    

}