package jksoft.com.web.vo;
import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @Class Name : AttachFileVO.java
 * @Description : 첨부파일 @VO 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2014.7.22
 * @version 1.0
 * @see euphoria.common.vo.AttachFileVO
 * @Modification Information
 * <pre>
 *   수정일         수정자                  수정내용
 *  ==========     ========    ===========================
 *  2014.7.22      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */

@SuppressWarnings("serial")
public class AttachFileVO implements Serializable {

    /**
     * 첨부파일일렬번호 
     * ATTC_FIL_SN : int(11)
     */
	@JsonInclude(Include.NON_EMPTY)
    private int attcFilSn = 0; 

    /**
     * 메뉴아이디 또는 테이블명
     * MENU_CD : nvarchar(10)
     */
	@JsonInclude(Include.NON_EMPTY)
    private String menuCd = ""; 

    /**
     * 연결컨텐츠 일렬번호
     * COTN_SN : int(11)
     */
	@JsonInclude(Include.NON_EMPTY)
    private int cotnSn = 0; 

    /**
     * 저장 파일명
     * FIL_NM : nvarchar(100)
     */
	@JsonInclude(Include.NON_EMPTY)
    private String filNm = ""; 

    /**
     * 원본 파일명
     * - 화면에 보여줄때는 해당 원본 파일명을 노출한다.
     * OGC_FIL_NM : nvarchar(100)
     */
	@JsonInclude(Include.NON_EMPTY)
    private String ogcFilNm = ""; 

    /**
     * 파일용량
     * FIL_CPC : int(11)
     */
	@JsonInclude(Include.NON_EMPTY)
    private long filCpc = 0L; 

    /**
     * 파일 구분코드(공통코드)
     * FIL_SCN_CD : nvarchar(6)
     */
	@JsonInclude(Include.NON_EMPTY)
    private String filScnCd = ""; 

    /**
     * 파일 정렬순서
     * FIL_ST_NO : tinyint(3)
     */
	@JsonInclude(Include.NON_EMPTY)
    private int filStNo = 1; 

    /**
     * 파일 설명 
     * FIL_EXPL : nvarchar(512)
     */
	@JsonInclude(Include.NON_EMPTY)
    private String filExpl = ""; 

    /**
     * 파일 다운로드 횟수
     * DNL_OFT : int(11)
     */
	@JsonInclude(Include.NON_EMPTY)
    private int dnlOft = 0; 

    /**
     * 사용여부
     * USE_YN : char(1)
     */
	@JsonIgnore
    private String useYn = "Y"; 

    /**
     * 등록자 아이디
     * RGST_MGR_ID : nvarchar(20)
     */
	@JsonIgnore
    private String rgstId = ""; 

    /**
     * 등록일자
     * RGST_DTM : datetime(23)
     */
	@JsonIgnore
    private String rgstDtm = ""; 

    /**
     * 수정자 아이디
     * MDFY_MGR_ID : nvarchar(20)
     */
	@JsonIgnore
    private String mdfyId = ""; 

    /**
     * 수정일자
     * MDFY_DTM : datetime(23)
     */
	@JsonIgnore
    private String mdfyDtm = ""; 
    
    /**
     * 삭제용 고유번호
     */
	@JsonIgnore
    private List<String> delSeq;
    
    /**
     * DextUpload로 입력한 파일 Seq
     * - ,를 구분자로 한다.
     */
	@JsonIgnore
    private String fileUploadSeq = "";
    
    /**
     * 컨텐츠 연결정보 변경을 위한 파일 일렬번호 목록
     */
	@JsonIgnore
    private List<String> updateFileSeq = null;
    
    /**
     * 첨부파일
     */
	@JsonIgnore
    private MultipartFile attachfile;
    
    /**
     * 파일 업로드 성공 유무
     */
    private boolean status = false;
    
    /**
     * 파일 업로드 실패시 오류 메세지 코드
     */
    @JsonInclude(Include.NON_EMPTY)
    private String errorCode = "";
    
    /**
     * 파일 업로드 실패시 오류 메세지
     */
    @JsonInclude(Include.NON_EMPTY)
    private String errorMessage = "";

    /**
     * 전체 컨텐츠 갯수
     */
    private int totalRow = 0;  
    
    
    /**
     * 첨부파일일렬번호
     * @return the attcFilSn
     */
    public int getAttcFilSn() {
        return attcFilSn;
    }
 
    /**
     * 첨부파일일렬번호
     * @param attcFilSn the attcFilSn to set
     */
    public void setAttcFilSn(int attcFilSn) {
        this.attcFilSn = attcFilSn;
    }

   
    /**
     * 메뉴아이디
     * @return the menuCd
     */
    public String getMenuCd() {
        return menuCd;
    }
 
    /**
     * 메뉴아이디
     * @param menuCd the menuCd to set
     */    
    public void setMenuCd(String menuCd) {
        this.menuCd = menuCd;
    }

    /**
     * 연결컨텐츠 일렬번호
     * @return the cotnSn
     */
    public int getCotnSn() {
        return cotnSn;
    }
 
    /**
     * 연결컨텐츠 일렬번호
     * @param cotnSn the cotnSn to set
     */
    public void setCotnSn(int cotnSn) {
        this.cotnSn = cotnSn;
    }

    /**
     * 저장 파일명
     * @return the filNm
     */
    public String getFilNm() {
        return filNm;
    }
 
    /**
     * 저장 파일명
     * @param filNm the filNm to set
     */    
    public void setFilNm(String filNm) {
        this.filNm = filNm;
    }

    /**
     * 원본 파일명
     * @return the ogcFilNm
     */
    public String getOgcFilNm() {
        return ogcFilNm;
    }
 
    /**
     * 원본 파일명
     * @param ogcFilNm the ogcFilNm to set
     */    
    public void setOgcFilNm(String ogcFilNm) {
        this.ogcFilNm = ogcFilNm;
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
     * 파일구분코드
     * @return the filScnCd
     */
    public String getFilScnCd() {
        return filScnCd;
    }
 
    /**
     * 파일구분코드
     * @param filScnCd the filScnCd to set
     */    
    public void setFilScnCd(String filScnCd) {
        this.filScnCd = filScnCd;
    }

    /**
     * 정렬순서
     * @return the filStNo
     */
    public int getFilStNo() {
        return filStNo;
    }
 
    /**
     * 정렬순서
     * @param filStNo the filStNo to set
     */
    public void setFilStNo(int filStNo) {
        this.filStNo = filStNo;
    }

    /**
     * 설명
     * @return the filExpl
     */
    public String getFilExpl() {
        return filExpl;
    }
 
    /**
     * 설명
     * @param filExpl the filExpl to set
     */    
    public void setFilExpl(String filExpl) {
        this.filExpl = filExpl;
    }

    /**
     * 파일 다운로드 횟수
     * @return the dnlOft
     */
    public int getDnlOft() {
        return dnlOft;
    }
 
    /**
     * 파일 다운로드 횟수
     * @param dnlOft the dnlOft to set
     */
    public void setDnlOft(int dnlOft) {
        this.dnlOft = dnlOft;
    }

    /**
     * 사용여부
     * @return the useYn
     */
    public String getUseYn() {
        return useYn;
    }
 
    /**
     * 사용여부
     * @param useYn the useYn to set
     */    
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    /**
     * 등록 아이디
     * @return the rgstId
     */
    public String getRgstId() {
        return rgstId;
    }
 
    /**
     * 등록 아이디
     * @param rgstId the rgstId to set
     */    
    public void setRgstId(String rgstId) {
        this.rgstId = rgstId;
    }

    /**
     * 등록일자
     * @return the rgstDtm
     */
    public String getRgstDtm() {
        return rgstDtm;
    }
 
    /**
     * 등록일자
     * @param rgstDtm the rgstDtm to set
     */    
    public void setRgstDtm(String rgstDtm) {
        this.rgstDtm = rgstDtm;
    }

    /**
     * 수정 아이디
     * @return the mdfyId
     */
    public String getMdfyId() {
        return mdfyId;
    }
 
    /**
     * 수정 아이디
     * @param mdfyId the mdfyId to set
     */    
    public void setMdfyId(String mdfyId) {
        this.mdfyId = mdfyId;
    }

    /**
     * 수정일자
     * @return the mdfyDtm
     */
    public String getMdfyDtm() {
        return mdfyDtm;
    }
  
    /**
     * 수정일자
     * @param mdfyDtm the mdfyDtm to set
     */    
    public void setMdfyDtm(String mdfyDtm) {
        this.mdfyDtm = mdfyDtm;
    }

    /**
     * @return the attachfile
     */
    public MultipartFile getAttachfile() {
        return attachfile;
    }

    /**
     * @param attachfile the attachfile to set
     */
    public void setAttachfile(MultipartFile attachfile) {
        this.attachfile = attachfile;
    }

    /**
     * @return the status
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the updateFileSeq
     */
    public List<String> getUpdateFileSeq() {
        return updateFileSeq;
    }

    /**
     * @param updateFileSeq the updateFileSeq to set
     */
    public void setUpdateFileSeq(List<String> updateFileSeq) {
        this.updateFileSeq = updateFileSeq;
    }

    /**
     * @return the delSeq
     */
    public List<String> getDelSeq() {
        return delSeq;
    }

    /**
     * @param delSeq the delSeq to set
     */
    public void setDelSeq(List<String> delSeq) {
        this.delSeq = delSeq;
    }

    /**
     * @return the fileUploadSeq
     */
    public String getFileUploadSeq() {
        return fileUploadSeq;
    }

    /**
     * @param fileUploadSeq the fileUploadSeq to set
     */
    public void setFileUploadSeq(String fileUploadSeq) {
        this.fileUploadSeq = fileUploadSeq;
    }

    /**
     * @return the totalRow
     */
    public int getTotalRow() {
        return totalRow;
    }

    /**
     * @param totalRow the totalRow to set
     */
    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }
    
}