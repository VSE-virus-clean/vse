package jksoft.com.web.vo;

import java.util.List;

/**
 * <pre>
 * 멀티업로드관련 VO
 * </pre>
 *
 * @ClassName   : DextUploadVO.java
 * @Description : @VO 클래스
 * @author Jeong.hyoungjea
 * @since 2014. 7. 23.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2014. 7. 23.     Jeong.hyoungjea     최초 생성
 * </pre>
 */

public class DextUploadVO {
    
    /**
     * 메뉴아이디 또는 테이블명
     */
    private String menuCd = ""; 

    /**
     * 연결컨텐츠 일렬번호
     */
    private int cotnSn = 0;
    
    /**
     * DextUpload 첨부 파일 목록
     */
    private List<Object> DEXTUploadX = null;
    
    /**
     * 기존 등록된 파일 목록
     */
    private List<String> DEXTUploadX_Uploaded = null;
    
    /**
     * 항목삭제로 삭제한 파일 목록
     */
    private List<String> DEXTUploadX_Deleted_Uploaded = null;

    /**
     * 파일 구분코드
     * - 멀티업로드에 대한 파일 구분코드
     * - 각 파일에 대한 구분코드가 없으면 해당 코드를 구분코드로 사용하면됨.
     */
    private String filScnCd;
    
    /**
     * 복사기능 여부
     * - 복사기능이 있으면 컨포넌트에서 삭제한것을 제외한 파일을 조회해서 신규 파일로 저장을 한다.
     */
    private String copyYn = "N";
    
    /**
     * 파일 사용자 정보
     * - 각파일에 대한 구분코드.
     */
    private List<String> DEXTUploadX_UserDataA = null;
    
    /**
     * 파일 사용자 정보 
     */
    private List<String> DEXTUploadX_UserDataB = null;
    
    
    

    
    
    /**
     * @return the menuCd
     */
    public String getMenuCd() {
        return menuCd;
    }

    /**
     * @param menuCd the menuCd to set
     */
    public void setMenuCd(String menuCd) {
        this.menuCd = menuCd;
    }

    /**
     * @return the cotnSn
     */
    public int getCotnSn() {
        return cotnSn;
    }

    /**
     * @param cotnSn the cotnSn to set
     */
    public void setCotnSn(int cotnSn) {
        this.cotnSn = cotnSn;
    }

    /**
     * @return the dEXTUploadX
     */
    public List<Object> getDEXTUploadX() {
        return DEXTUploadX;
    }

    /**
     * @param dEXTUploadX the dEXTUploadX to set
     */
    public void setDEXTUploadX(List<Object> dEXTUploadX) {
        DEXTUploadX = dEXTUploadX;
    }

    /**
     * @return the dEXTUploadX_Uploaded
     */
    public List<String> getDEXTUploadX_Uploaded() {
        return DEXTUploadX_Uploaded;
    }

    /**
     * @param dEXTUploadX_Uploaded the dEXTUploadX_Uploaded to set
     */
    public void setDEXTUploadX_Uploaded(List<String> dEXTUploadX_Uploaded) {
        DEXTUploadX_Uploaded = dEXTUploadX_Uploaded;
    }

    /**
     * @return the dEXTUploadX_Deleted_Uploaded
     */
    public List<String> getDEXTUploadX_Deleted_Uploaded() {
        return DEXTUploadX_Deleted_Uploaded;
    }

    /**
     * @param dEXTUploadX_Deleted_Uploaded the dEXTUploadX_Deleted_Uploaded to set
     */
    public void setDEXTUploadX_Deleted_Uploaded(List<String> dEXTUploadX_Deleted_Uploaded) {
        DEXTUploadX_Deleted_Uploaded = dEXTUploadX_Deleted_Uploaded;
    }

    /**
     * @return the filScnCd
     */
    public String getFilScnCd() {
        return filScnCd;
    }

    /**
     * @param filScnCd the filScnCd to set
     */
    public void setFilScnCd(String filScnCd) {
        this.filScnCd = filScnCd;
    }

    /**
     * @return the dEXTUploadX_UserDataA
     */
    public List<String> getDEXTUploadX_UserDataA() {
        return DEXTUploadX_UserDataA;
    }

    /**
     * @param dEXTUploadX_UserDataA the dEXTUploadX_UserDataA to set
     */
    public void setDEXTUploadX_UserDataA(List<String> dEXTUploadX_UserDataA) {
        DEXTUploadX_UserDataA = dEXTUploadX_UserDataA;
    }

    /**
     * @return the dEXTUploadX_UserDataB
     */
    public List<String> getDEXTUploadX_UserDataB() {
        return DEXTUploadX_UserDataB;
    }

    /**
     * @param dEXTUploadX_UserDataB the dEXTUploadX_UserDataB to set
     */
    public void setDEXTUploadX_UserDataB(List<String> dEXTUploadX_UserDataB) {
        DEXTUploadX_UserDataB = dEXTUploadX_UserDataB;
    }

    /**
     * @return the copyYn
     */
    public String getCopyYn() {
        return copyYn;
    }

    /**
     * @param copyYn the copyYn to set
     */
    public void setCopyYn(String copyYn) {
        this.copyYn = copyYn;
    }
}
