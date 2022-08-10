package vc.virusclean.cmm.vo;

import java.io.Serializable;

//import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName   : EditorPhotoVO.java
 * @Description : 에디터 사진 첨부 @VO 클래스
 * @author Jeong.Hyoung.Jae
 * @since 2017.12.25
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017.12.25     Jeong.Hyoung.Jae     최초 생성
 * </pre>
 */

@SuppressWarnings("serial")
public class EditorPhotoVO  implements Serializable {

    /**
     * CallBack URL
     */
    //@NotEmpty
    private String callback = "";
    
    /**
     * CallBack Process ID
     */
    //@NotEmpty
    private String callback_func = "";
    
    /**
     * 첨부파일
     */
    private MultipartFile attachfile;
    
    /**
     * Error 코드
     */
    private String errorCode = "";
    
    /**
     * 성공 여부
     */
    private boolean status = false;
    
    /**
     * 파일명
     */
    private String fileName = "";
    
    /**
     * 이미지 경로
     */
    private String filePath;
    
    /**
     * 파일 메뉴 코드
     */
    private String menuCode = "SM";
    
    /**
     * 개행여부
     */
    private boolean newLine = true;
    

    
    /**
     * CallBack URL
     * @return the callback
     */
    public String getCallback() {
        return callback;
    }

    /**
     * CallBack URL
     * @param callback the callback to set
     */
    public void setCallback(String callback) {
        this.callback = callback;
    }

    /**
     * CallBack Process ID
     * @return the callback_func
     */
    public String getCallback_func() {
        return callback_func;
    }

    /**
     * CallBack Process ID
     * @param callback_func the callback_func to set
     */
    public void setCallback_func(String callback_func) {
        this.callback_func = callback_func;
    }

    /**
     * 첨부파일
     * @return the attachFile
     */
    public MultipartFile getAttachfile() {
        return attachfile;
    }

    /**
     * 첨부파일
     * @param attachFile the attachFile to set
     */
    public void setAttachfile(MultipartFile attachfile) {
        this.attachfile = attachfile;
    }

    /**
     * Error 코드
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Error 코드
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 성공 여부
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * 성공 여부
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * 파일명
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 파일명
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 이미지 경로
     * @return the filePath
     */
    public String getFilePath() {
        return filePath + "/" + menuCode + "/" + fileName;
    }

    /**
     * 이미지 경로
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 메뉴코드
     * @return the menuCode
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 메뉴코드
     * @param menuCode the menuCode to set
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    /**
     * 개행여부
     * @return the newLine
     */
    public boolean isNewLine() {
        return newLine;
    }

    /**
     * 개행여부
     * @param newLine the newLine to set
     */
    public void setNewLine(boolean newLine) {
        this.newLine = newLine;
    }
}
