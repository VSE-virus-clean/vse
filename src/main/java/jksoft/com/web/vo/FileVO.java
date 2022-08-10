package jksoft.com.web.vo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * <pre>
 * 첨부파일 정보
 * </pre>
 *
 * @ClassName   : FileVO.java
 * @Description : 첨부파일 @VO 클래스
 * @author Jeong.hyoungjea
 * @since 2014. 2. 25.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2014. 2. 25.     Jeong.hyoungjea     최초 생성
 * </pre>
 */
@SuppressWarnings("serial")
@JsonInclude(Include.NON_EMPTY)
public class FileVO extends SearchVO {
    
    /**
     * 첨부파일 메뉴 아이디
     * - DIR NAME
     */
	@JsonIgnore
    protected String menuCd = "";
    
    /**
     * 첨부파일 COUNT
     */
	@JsonIgnore
    protected int fileCount = 0;
    
    /**
     * 첨부파일 SEQ
     */
	@JsonIgnore
    protected int fileSn = 0;
	@JsonIgnore
    protected int fileSn2 = 0;
	@JsonIgnore
    protected int fileSn3 = 0;
    
    /**
     * 설명 
     */
	@JsonIgnore
    protected String ogcFilNm = ""; 
    
    /**
     * 설명 
     */
    protected String filNm = "";
    protected String filNm2 = "";
    protected String filNm3 = "";
    
    @JsonIgnore
    protected List<String> filDelSeq = null;
    @JsonIgnore
    protected Object filObject = null;
    @JsonIgnore
    protected List<String> filDesc = null;
    
    /**
	 * @return the filDelSeq
	 */
	public List<String> getFilDelSeq() {
		return filDelSeq;
	}
	/**
	 * @param filDelSeq the filDelSeq to set
	 */
	public void setFilDelSeq(List<String> filDelSeq) {
		this.filDelSeq = filDelSeq;
	}
	/**
	 * @return the filObject
	 */
	public Object getFilObject() {
		return filObject;
	}
	/**
	 * @param filObject the filObject to set
	 */
	public void setFilObject(Object filObject) {
		this.filObject = filObject;
	}
	/**
	 * @return the filDesc
	 */
	public List<String> getFilDesc() {
		return filDesc;
	}
	/**
	 * @param filDesc the filDesc to set
	 */
	public void setFilDesc(List<String> filDesc) {
		this.filDesc = filDesc;
	}
	/** *******************************************
     * 썸네일
     ** *********************************************/
    /*
     * 첨부파일 일렬번호
     */
    @JsonIgnore
    protected int attachFileSn1 = 0;
    @JsonIgnore
    protected int attachFileSn2 = 0;
    @JsonIgnore
    protected int attachFileSn3 = 0;
    @JsonIgnore
    protected int attachFileSn4 = 0;
    @JsonIgnore
    protected int attachFileSn5 = 0;
    @JsonIgnore
    protected int attachFileSn6 = 0;
    @JsonIgnore
    protected int attachFileSn7 = 0;
    @JsonIgnore
    protected int attachFileSn8 = 0;
    @JsonIgnore
    protected int attachFileSn9 = 0;
    @JsonIgnore
    protected int attachFileSn10 = 0;
    @JsonIgnore
    protected int attachFileSn11 = 0;
    @JsonIgnore
    protected int attachFileSn12 = 0;
    @JsonIgnore
    protected int attachFileSn13 = 0;
    @JsonIgnore
    protected int attachFileSn14 = 0;
    @JsonIgnore
    protected int attachFileSn15 = 0;
    @JsonIgnore
    protected int attachFileSn16 = 0;
    @JsonIgnore
    protected int attachFileSn17 = 0;
    @JsonIgnore
    protected int attachFileSn18 = 0;
    @JsonIgnore
    protected int attachFileSn19 = 0;
    @JsonIgnore
    protected int attachFileSn20 = 0;
    
    /*
     * 첨부파일 정렬순서
     */
    @JsonIgnore
    protected int attachFileNo1 = 0;
    @JsonIgnore
    protected int attachFileNo2 = 0;
    @JsonIgnore
    protected int attachFileNo3 = 0;
    @JsonIgnore
    protected int attachFileNo4 = 0;
    @JsonIgnore
    protected int attachFileNo5 = 0;
    @JsonIgnore
    protected int attachFileNo6 = 0;
    @JsonIgnore
    protected int attachFileNo7 = 0;
    @JsonIgnore
    protected int attachFileNo8 = 0;
    @JsonIgnore
    protected int attachFileNo9 = 0;
    @JsonIgnore
    protected int attachFileNo10 = 0;
    @JsonIgnore
    protected int attachFileNo11 = 0;
    @JsonIgnore
    protected int attachFileNo12 = 0;
    @JsonIgnore
    protected int attachFileNo13 = 0;
    @JsonIgnore
    protected int attachFileNo14 = 0;
    @JsonIgnore
    protected int attachFileNo15 = 0;
    @JsonIgnore
    protected int attachFileNo16 = 0;
    @JsonIgnore
    protected int attachFileNo17 = 0;
    @JsonIgnore
    protected int attachFileNo18 = 0;
    @JsonIgnore
    protected int attachFileNo19 = 0;
    @JsonIgnore
    protected int attachFileNo20 = 0;
    
    /*
     * 첨부파일 구분코드
     * - 썸네일                   THUM
     * - 일반문서                DOC
     * - 타입없음                ALL
     * - 일반이미지             IMG
     */
    @JsonIgnore
    protected String attachFileCd1 = "ALL";
    @JsonIgnore
    protected String attachFileCd2 = "ALL";
    @JsonIgnore
    protected String attachFileCd3 = "ALL";
    @JsonIgnore
    protected String attachFileCd4 = "ALL";
    @JsonIgnore
    protected String attachFileCd5 = "ALL";
    @JsonIgnore
    protected String attachFileCd6 = "ALL";
    @JsonIgnore
    protected String attachFileCd7 = "ALL";
    @JsonIgnore
    protected String attachFileCd8 = "ALL";
    @JsonIgnore
    protected String attachFileCd9 = "ALL";
    @JsonIgnore
    protected String attachFileCd10 = "ALL";
    @JsonIgnore
    protected String attachFileCd11 = "ALL";
    @JsonIgnore
    protected String attachFileCd12 = "ALL";
    @JsonIgnore
    protected String attachFileCd13 = "ALL";
    @JsonIgnore
    protected String attachFileCd14 = "ALL";
    @JsonIgnore
    protected String attachFileCd15 = "ALL";
    @JsonIgnore
    protected String attachFileCd16 = "ALL";
    @JsonIgnore
    protected String attachFileCd17 = "ALL";
    @JsonIgnore
    protected String attachFileCd18 = "ALL";
    @JsonIgnore
    protected String attachFileCd19 = "ALL";
    @JsonIgnore
    protected String attachFileCd20 = "ALL";
    
    /*
     * 첨부파일 추가 설명
     */
    @JsonIgnore
    protected String attachFileExpl1 = "";
    @JsonIgnore
    protected String attachFileExpl2 = "";
    @JsonIgnore
    protected String attachFileExpl3 = "";
    @JsonIgnore
    protected String attachFileExpl4 = "";
    @JsonIgnore
    protected String attachFileExpl5 = "";
    @JsonIgnore
    protected String attachFileExpl6 = "";
    @JsonIgnore
    protected String attachFileExpl7 = "";
    @JsonIgnore
    protected String attachFileExpl8 = "";
    @JsonIgnore
    protected String attachFileExpl9 = "";
    @JsonIgnore
    protected String attachFileExpl10 = "";
    @JsonIgnore
    protected String attachFileExpl11 = "";
    @JsonIgnore
    protected String attachFileExpl12 = "";
    @JsonIgnore
    protected String attachFileExpl13 = "";
    @JsonIgnore
    protected String attachFileExpl14 = "";
    @JsonIgnore
    protected String attachFileExpl15 = "";
    @JsonIgnore
    protected String attachFileExpl16 = "";
    @JsonIgnore
    protected String attachFileExpl17 = "";
    @JsonIgnore
    protected String attachFileExpl18 = "";
    @JsonIgnore
    protected String attachFileExpl19 = "";
    @JsonIgnore
    protected String attachFileExpl20 = "";
    
    /*
     * 첨부파일 삭제여부
     */
    @JsonIgnore
    protected String attachFileUseYn1 = "Y";
    @JsonIgnore
    protected String attachFileUseYn2 = "Y";
    @JsonIgnore
    protected String attachFileUseYn3 = "Y";
    @JsonIgnore
    protected String attachFileUseYn4 = "Y";
    @JsonIgnore
    protected String attachFileUseYn5 = "Y";
    @JsonIgnore
    protected String attachFileUseYn6 = "Y";
    @JsonIgnore
    protected String attachFileUseYn7 = "Y";
    @JsonIgnore
    protected String attachFileUseYn8 = "Y";
    @JsonIgnore
    protected String attachFileUseYn9 = "Y";
    @JsonIgnore
    protected String attachFileUseYn10 = "Y";
    @JsonIgnore
    protected String attachFileUseYn11 = "Y";
    @JsonIgnore
    protected String attachFileUseYn12 = "Y";
    @JsonIgnore
    protected String attachFileUseYn13 = "Y";
    @JsonIgnore
    protected String attachFileUseYn14 = "Y";
    @JsonIgnore
    protected String attachFileUseYn15 = "Y";
    @JsonIgnore
    protected String attachFileUseYn16 = "Y";
    @JsonIgnore
    protected String attachFileUseYn17 = "Y";
    @JsonIgnore
    protected String attachFileUseYn18 = "Y";
    @JsonIgnore
    protected String attachFileUseYn19 = "Y";
    @JsonIgnore
    protected String attachFileUseYn20 = "Y";
    
    /*
     * 첨부파일 
     */
    @JsonIgnore
    protected Object attachFile1;
    @JsonIgnore
    protected Object attachFile2;
    @JsonIgnore
    protected Object attachFile3;
    @JsonIgnore
    protected Object attachFile4;
    @JsonIgnore
    protected Object attachFile5;
    @JsonIgnore
    protected Object attachFile6;
    @JsonIgnore
    protected Object attachFile7;
    @JsonIgnore
    protected Object attachFile8;
    @JsonIgnore
    protected Object attachFile9;
    @JsonIgnore
    protected Object attachFile10;
    @JsonIgnore
    protected Object attachFile11;
    @JsonIgnore
    protected Object attachFile12;
    @JsonIgnore
    protected Object attachFile13;
    @JsonIgnore
    protected Object attachFile14;
    @JsonIgnore
    protected Object attachFile15;
    @JsonIgnore
    protected Object attachFile16;
    @JsonIgnore
    protected Object attachFile17;
    @JsonIgnore
    protected Object attachFile18;
    @JsonIgnore
    protected Object attachFile19;
    @JsonIgnore
    protected Object attachFile20;
    
    
    
    
    /**
     * @return the fileSn
     */
    public int getFileSn() {
        return fileSn;
    }
    /**
     * @param fileSn the fileSn to set
     */
    public void setFileSn(int fileSn) {
        this.fileSn = fileSn;
    }
    /**
     * @return the fileCount
     */
    public int getFileCount() {
        return fileCount;
    }
    /**
     * @param fileCount the fileCount to set
     */
    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }
    /**
     * @return the attachFileSn1
     */
    public int getAttachFileSn1() {
        return attachFileSn1;
    }
    /**
     * @param attachFileSn1 the attachFileSn1 to set
     */
    public void setAttachFileSn1(int attachFileSn1) {
        this.attachFileSn1 = attachFileSn1;
    }
    /**
     * @return the attachFileSn2
     */
    public int getAttachFileSn2() {
        return attachFileSn2;
    }
    /**
     * @param attachFileSn2 the attachFileSn2 to set
     */
    public void setAttachFileSn2(int attachFileSn2) {
        this.attachFileSn2 = attachFileSn2;
    }
    /**
     * @return the attachFileSn3
     */
    public int getAttachFileSn3() {
        return attachFileSn3;
    }
    /**
     * @param attachFileSn3 the attachFileSn3 to set
     */
    public void setAttachFileSn3(int attachFileSn3) {
        this.attachFileSn3 = attachFileSn3;
    }
    /**
     * @return the attachFileSn4
     */
    public int getAttachFileSn4() {
        return attachFileSn4;
    }
    /**
     * @param attachFileSn4 the attachFileSn4 to set
     */
    public void setAttachFileSn4(int attachFileSn4) {
        this.attachFileSn4 = attachFileSn4;
    }
    /**
     * @return the attachFileSn5
     */
    public int getAttachFileSn5() {
        return attachFileSn5;
    }
    /**
     * @param attachFileSn5 the attachFileSn5 to set
     */
    public void setAttachFileSn5(int attachFileSn5) {
        this.attachFileSn5 = attachFileSn5;
    }
    /**
     * @return the attachFileNo1
     */
    public int getAttachFileNo1() {
        return attachFileNo1;
    }
    /**
     * @param attachFileNo1 the attachFileNo1 to set
     */
    public void setAttachFileNo1(int attachFileNo1) {
        this.attachFileNo1 = attachFileNo1;
    }
    /**
     * @return the attachFileNo2
     */
    public int getAttachFileNo2() {
        return attachFileNo2;
    }
    /**
     * @param attachFileNo2 the attachFileNo2 to set
     */
    public void setAttachFileNo2(int attachFileNo2) {
        this.attachFileNo2 = attachFileNo2;
    }
    /**
     * @return the attachFileNo3
     */
    public int getAttachFileNo3() {
        return attachFileNo3;
    }
    /**
     * @param attachFileNo3 the attachFileNo3 to set
     */
    public void setAttachFileNo3(int attachFileNo3) {
        this.attachFileNo3 = attachFileNo3;
    }
    /**
     * @return the attachFileNo4
     */
    public int getAttachFileNo4() {
        return attachFileNo4;
    }
    /**
     * @param attachFileNo4 the attachFileNo4 to set
     */
    public void setAttachFileNo4(int attachFileNo4) {
        this.attachFileNo4 = attachFileNo4;
    }
    /**
     * @return the attachFileNo5
     */
    public int getAttachFileNo5() {
        return attachFileNo5;
    }
    /**
     * @param attachFileNo5 the attachFileNo5 to set
     */
    public void setAttachFileNo5(int attachFileNo5) {
        this.attachFileNo5 = attachFileNo5;
    }
    /**
     * @return the attachFileCd1
     */
    public String getAttachFileCd1() {
        return attachFileCd1;
    }
    /**
     * @param attachFileCd1 the attachFileCd1 to set
     */
    public void setAttachFileCd1(String attachFileCd1) {
        this.attachFileCd1 = attachFileCd1;
    }
    /**
     * @return the attachFileCd2
     */
    public String getAttachFileCd2() {
        return attachFileCd2;
    }
    /**
     * @param attachFileCd2 the attachFileCd2 to set
     */
    public void setAttachFileCd2(String attachFileCd2) {
        this.attachFileCd2 = attachFileCd2;
    }
    /**
     * @return the attachFileCd3
     */
    public String getAttachFileCd3() {
        return attachFileCd3;
    }
    /**
     * @param attachFileCd3 the attachFileCd3 to set
     */
    public void setAttachFileCd3(String attachFileCd3) {
        this.attachFileCd3 = attachFileCd3;
    }
    /**
     * @return the attachFileCd4
     */
    public String getAttachFileCd4() {
        return attachFileCd4;
    }
    /**
     * @param attachFileCd4 the attachFileCd4 to set
     */
    public void setAttachFileCd4(String attachFileCd4) {
        this.attachFileCd4 = attachFileCd4;
    }
    /**
     * @return the attachFileCd5
     */
    public String getAttachFileCd5() {
        return attachFileCd5;
    }
    /**
     * @param attachFileCd5 the attachFileCd5 to set
     */
    public void setAttachFileCd5(String attachFileCd5) {
        this.attachFileCd5 = attachFileCd5;
    }
    /**
     * @return the attachFileExpl1
     */
    public String getAttachFileExpl1() {
        return attachFileExpl1;
    }
    /**
     * @param attachFileExpl1 the attachFileExpl1 to set
     */
    public void setAttachFileExpl1(String attachFileExpl1) {
        this.attachFileExpl1 = attachFileExpl1;
    }
    /**
     * @return the attachFileExpl2
     */
    public String getAttachFileExpl2() {
        return attachFileExpl2;
    }
    /**
     * @param attachFileExpl2 the attachFileExpl2 to set
     */
    public void setAttachFileExpl2(String attachFileExpl2) {
        this.attachFileExpl2 = attachFileExpl2;
    }
    /**
     * @return the attachFileExpl3
     */
    public String getAttachFileExpl3() {
        return attachFileExpl3;
    }
    /**
     * @param attachFileExpl3 the attachFileExpl3 to set
     */
    public void setAttachFileExpl3(String attachFileExpl3) {
        this.attachFileExpl3 = attachFileExpl3;
    }
    /**
     * @return the attachFileExpl4
     */
    public String getAttachFileExpl4() {
        return attachFileExpl4;
    }
    /**
     * @param attachFileExpl4 the attachFileExpl4 to set
     */
    public void setAttachFileExpl4(String attachFileExpl4) {
        this.attachFileExpl4 = attachFileExpl4;
    }
    /**
     * @return the attachFileExpl5
     */
    public String getAttachFileExpl5() {
        return attachFileExpl5;
    }
    /**
     * @param attachFileExpl5 the attachFileExpl5 to set
     */
    public void setAttachFileExpl5(String attachFileExpl5) {
        this.attachFileExpl5 = attachFileExpl5;
    }
    /**
     * @return the attachFile1
     */
    public Object getAttachFile1() {
        return attachFile1;
    }
    /**
     * @param attachFile1 the attachFile1 to set
     */
    public void setAttachFile1(Object attachFile1) {
        this.attachFile1 = attachFile1;
    }
    /**
     * @return the attachFile2
     */
    public Object getAttachFile2() {
        return attachFile2;
    }
    /**
     * @param attachFile2 the attachFile2 to set
     */
    public void setAttachFile2(Object attachFile2) {
        this.attachFile2 = attachFile2;
    }
    /**
     * @return the attachFile3
     */
    public Object getAttachFile3() {
        return attachFile3;
    }
    /**
     * @param attachFile3 the attachFile3 to set
     */
    public void setAttachFile3(Object attachFile3) {
        this.attachFile3 = attachFile3;
    }
    /**
     * @return the attachFile4
     */
    public Object getAttachFile4() {
        return attachFile4;
    }
    /**
     * @param attachFile4 the attachFile4 to set
     */
    public void setAttachFile4(Object attachFile4) {
        this.attachFile4 = attachFile4;
    }
    /**
     * @return the attachFile5
     */
    public Object getAttachFile5() {
        return attachFile5;
    }
    /**
     * @param attachFile5 the attachFile5 to set
     */
    public void setAttachFile5(Object attachFile5) {
        this.attachFile5 = attachFile5;
    }
    /**
     * @return the attachFileUseYn1
     */
    public String getAttachFileUseYn1() {
        return attachFileUseYn1;
    }
    /**
     * @param attachFileUseYn1 the attachFileUseYn1 to set
     */
    public void setAttachFileUseYn1(String attachFileUseYn1) {
        this.attachFileUseYn1 = attachFileUseYn1;
    }
    /**
     * @return the attachFileUseYn2
     */
    public String getAttachFileUseYn2() {
        return attachFileUseYn2;
    }
    /**
     * @param attachFileUseYn2 the attachFileUseYn2 to set
     */
    public void setAttachFileUseYn2(String attachFileUseYn2) {
        this.attachFileUseYn2 = attachFileUseYn2;
    }
    /**
     * @return the attachFileUseYn3
     */
    public String getAttachFileUseYn3() {
        return attachFileUseYn3;
    }
    /**
     * @param attachFileUseYn3 the attachFileUseYn3 to set
     */
    public void setAttachFileUseYn3(String attachFileUseYn3) {
        this.attachFileUseYn3 = attachFileUseYn3;
    }
    /**
     * @return the attachFileUseYn4
     */
    public String getAttachFileUseYn4() {
        return attachFileUseYn4;
    }
    /**
     * @param attachFileUseYn4 the attachFileUseYn4 to set
     */
    public void setAttachFileUseYn4(String attachFileUseYn4) {
        this.attachFileUseYn4 = attachFileUseYn4;
    }
    /**
     * @return the attachFileUseYn5
     */
    public String getAttachFileUseYn5() {
        return attachFileUseYn5;
    }
    /**
     * @param attachFileUseYn5 the attachFileUseYn5 to set
     */
    public void setAttachFileUseYn5(String attachFileUseYn5) {
        this.attachFileUseYn5 = attachFileUseYn5;
    }
    /**
     * @return the attachFileSn6
     */
    public int getAttachFileSn6() {
        return attachFileSn6;
    }
    /**
     * @param attachFileSn6 the attachFileSn6 to set
     */
    public void setAttachFileSn6(int attachFileSn6) {
        this.attachFileSn6 = attachFileSn6;
    }
    /**
     * @return the attachFileSn7
     */
    public int getAttachFileSn7() {
        return attachFileSn7;
    }
    /**
     * @param attachFileSn7 the attachFileSn7 to set
     */
    public void setAttachFileSn7(int attachFileSn7) {
        this.attachFileSn7 = attachFileSn7;
    }
    /**
     * @return the attachFileSn8
     */
    public int getAttachFileSn8() {
        return attachFileSn8;
    }
    /**
     * @param attachFileSn8 the attachFileSn8 to set
     */
    public void setAttachFileSn8(int attachFileSn8) {
        this.attachFileSn8 = attachFileSn8;
    }
    /**
     * @return the attachFileSn9
     */
    public int getAttachFileSn9() {
        return attachFileSn9;
    }
    /**
     * @param attachFileSn9 the attachFileSn9 to set
     */
    public void setAttachFileSn9(int attachFileSn9) {
        this.attachFileSn9 = attachFileSn9;
    }
    /**
     * @return the attachFileSn10
     */
    public int getAttachFileSn10() {
        return attachFileSn10;
    }
    /**
     * @param attachFileSn10 the attachFileSn10 to set
     */
    public void setAttachFileSn10(int attachFileSn10) {
        this.attachFileSn10 = attachFileSn10;
    }
    /**
     * @return the attachFileNo6
     */
    public int getAttachFileNo6() {
        return attachFileNo6;
    }
    /**
     * @param attachFileNo6 the attachFileNo6 to set
     */
    public void setAttachFileNo6(int attachFileNo6) {
        this.attachFileNo6 = attachFileNo6;
    }
    /**
     * @return the attachFileNo7
     */
    public int getAttachFileNo7() {
        return attachFileNo7;
    }
    /**
     * @param attachFileNo7 the attachFileNo7 to set
     */
    public void setAttachFileNo7(int attachFileNo7) {
        this.attachFileNo7 = attachFileNo7;
    }
    /**
     * @return the attachFileNo8
     */
    public int getAttachFileNo8() {
        return attachFileNo8;
    }
    /**
     * @param attachFileNo8 the attachFileNo8 to set
     */
    public void setAttachFileNo8(int attachFileNo8) {
        this.attachFileNo8 = attachFileNo8;
    }
    /**
     * @return the attachFileNo9
     */
    public int getAttachFileNo9() {
        return attachFileNo9;
    }
    /**
     * @param attachFileNo9 the attachFileNo9 to set
     */
    public void setAttachFileNo9(int attachFileNo9) {
        this.attachFileNo9 = attachFileNo9;
    }
    /**
     * @return the attachFileNo10
     */
    public int getAttachFileNo10() {
        return attachFileNo10;
    }
    /**
     * @param attachFileNo10 the attachFileNo10 to set
     */
    public void setAttachFileNo10(int attachFileNo10) {
        this.attachFileNo10 = attachFileNo10;
    }
    /**
     * @return the attachFileCd6
     */
    public String getAttachFileCd6() {
        return attachFileCd6;
    }
    /**
     * @param attachFileCd6 the attachFileCd6 to set
     */
    public void setAttachFileCd6(String attachFileCd6) {
        this.attachFileCd6 = attachFileCd6;
    }
    /**
     * @return the attachFileCd7
     */
    public String getAttachFileCd7() {
        return attachFileCd7;
    }
    /**
     * @param attachFileCd7 the attachFileCd7 to set
     */
    public void setAttachFileCd7(String attachFileCd7) {
        this.attachFileCd7 = attachFileCd7;
    }
    /**
     * @return the attachFileCd8
     */
    public String getAttachFileCd8() {
        return attachFileCd8;
    }
    /**
     * @param attachFileCd8 the attachFileCd8 to set
     */
    public void setAttachFileCd8(String attachFileCd8) {
        this.attachFileCd8 = attachFileCd8;
    }
    /**
     * @return the attachFileCd9
     */
    public String getAttachFileCd9() {
        return attachFileCd9;
    }
    /**
     * @param attachFileCd9 the attachFileCd9 to set
     */
    public void setAttachFileCd9(String attachFileCd9) {
        this.attachFileCd9 = attachFileCd9;
    }
    /**
     * @return the attachFileCd10
     */
    public String getAttachFileCd10() {
        return attachFileCd10;
    }
    /**
     * @param attachFileCd10 the attachFileCd10 to set
     */
    public void setAttachFileCd10(String attachFileCd10) {
        this.attachFileCd10 = attachFileCd10;
    }
    /**
     * @return the attachFileExpl6
     */
    public String getAttachFileExpl6() {
        return attachFileExpl6;
    }
    /**
     * @param attachFileExpl6 the attachFileExpl6 to set
     */
    public void setAttachFileExpl6(String attachFileExpl6) {
        this.attachFileExpl6 = attachFileExpl6;
    }
    /**
     * @return the attachFileExpl7
     */
    public String getAttachFileExpl7() {
        return attachFileExpl7;
    }
    /**
     * @param attachFileExpl7 the attachFileExpl7 to set
     */
    public void setAttachFileExpl7(String attachFileExpl7) {
        this.attachFileExpl7 = attachFileExpl7;
    }
    /**
     * @return the attachFileExpl8
     */
    public String getAttachFileExpl8() {
        return attachFileExpl8;
    }
    /**
     * @param attachFileExpl8 the attachFileExpl8 to set
     */
    public void setAttachFileExpl8(String attachFileExpl8) {
        this.attachFileExpl8 = attachFileExpl8;
    }
    /**
     * @return the attachFileExpl9
     */
    public String getAttachFileExpl9() {
        return attachFileExpl9;
    }
    /**
     * @param attachFileExpl9 the attachFileExpl9 to set
     */
    public void setAttachFileExpl9(String attachFileExpl9) {
        this.attachFileExpl9 = attachFileExpl9;
    }
    /**
     * @return the attachFileExpl10
     */
    public String getAttachFileExpl10() {
        return attachFileExpl10;
    }
    /**
     * @param attachFileExpl10 the attachFileExpl10 to set
     */
    public void setAttachFileExpl10(String attachFileExpl10) {
        this.attachFileExpl10 = attachFileExpl10;
    }
    /**
     * @return the attachFileUseYn6
     */
    public String getAttachFileUseYn6() {
        return attachFileUseYn6;
    }
    /**
     * @param attachFileUseYn6 the attachFileUseYn6 to set
     */
    public void setAttachFileUseYn6(String attachFileUseYn6) {
        this.attachFileUseYn6 = attachFileUseYn6;
    }
    /**
     * @return the attachFileUseYn7
     */
    public String getAttachFileUseYn7() {
        return attachFileUseYn7;
    }
    /**
     * @param attachFileUseYn7 the attachFileUseYn7 to set
     */
    public void setAttachFileUseYn7(String attachFileUseYn7) {
        this.attachFileUseYn7 = attachFileUseYn7;
    }
    /**
     * @return the attachFileUseYn8
     */
    public String getAttachFileUseYn8() {
        return attachFileUseYn8;
    }
    /**
     * @param attachFileUseYn8 the attachFileUseYn8 to set
     */
    public void setAttachFileUseYn8(String attachFileUseYn8) {
        this.attachFileUseYn8 = attachFileUseYn8;
    }
    /**
     * @return the attachFileUseYn9
     */
    public String getAttachFileUseYn9() {
        return attachFileUseYn9;
    }
    /**
     * @param attachFileUseYn9 the attachFileUseYn9 to set
     */
    public void setAttachFileUseYn9(String attachFileUseYn9) {
        this.attachFileUseYn9 = attachFileUseYn9;
    }
    /**
     * @return the attachFileUseYn10
     */
    public String getAttachFileUseYn10() {
        return attachFileUseYn10;
    }
    /**
     * @param attachFileUseYn10 the attachFileUseYn10 to set
     */
    public void setAttachFileUseYn10(String attachFileUseYn10) {
        this.attachFileUseYn10 = attachFileUseYn10;
    }
    /**
     * @return the attachFile6
     */
    public Object getAttachFile6() {
        return attachFile6;
    }
    /**
     * @param attachFile6 the attachFile6 to set
     */
    public void setAttachFile6(Object attachFile6) {
        this.attachFile6 = attachFile6;
    }
    /**
     * @return the attachFile7
     */
    public Object getAttachFile7() {
        return attachFile7;
    }
    /**
     * @param attachFile7 the attachFile7 to set
     */
    public void setAttachFile7(Object attachFile7) {
        this.attachFile7 = attachFile7;
    }
    /**
     * @return the attachFile8
     */
    public Object getAttachFile8() {
        return attachFile8;
    }
    /**
     * @param attachFile8 the attachFile8 to set
     */
    public void setAttachFile8(Object attachFile8) {
        this.attachFile8 = attachFile8;
    }
    /**
     * @return the attachFile9
     */
    public Object getAttachFile9() {
        return attachFile9;
    }
    /**
     * @param attachFile9 the attachFile9 to set
     */
    public void setAttachFile9(Object attachFile9) {
        this.attachFile9 = attachFile9;
    }
    /**
     * @return the attachFile10
     */
    public Object getAttachFile10() {
        return attachFile10;
    }
    /**
     * @param attachFile10 the attachFile10 to set
     */
    public void setAttachFile10(Object attachFile10) {
        this.attachFile10 = attachFile10;
    }
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
     * @return the ogcFilNm
     */
    public String getOgcFilNm() {
        return ogcFilNm;
    }
    /**
     * @param ogcFilNm the ogcFilNm to set
     */
    public void setOgcFilNm(String ogcFilNm) {
        this.ogcFilNm = ogcFilNm;
    }
    /**
     * @return the filNm
     */
    public String getFilNm() {
        return filNm;
    }
    /**
     * @param filNm the filNm to set
     */
    public void setFilNm(String filNm) {
        this.filNm = filNm;
    }
	public int getAttachFileSn11() {
		return attachFileSn11;
	}
	public void setAttachFileSn11(int attachFileSn11) {
		this.attachFileSn11 = attachFileSn11;
	}
	public int getAttachFileSn12() {
		return attachFileSn12;
	}
	public void setAttachFileSn12(int attachFileSn12) {
		this.attachFileSn12 = attachFileSn12;
	}
	public int getAttachFileSn13() {
		return attachFileSn13;
	}
	public void setAttachFileSn13(int attachFileSn13) {
		this.attachFileSn13 = attachFileSn13;
	}
	public int getAttachFileSn14() {
		return attachFileSn14;
	}
	public void setAttachFileSn14(int attachFileSn14) {
		this.attachFileSn14 = attachFileSn14;
	}
	public int getAttachFileSn15() {
		return attachFileSn15;
	}
	public void setAttachFileSn15(int attachFileSn15) {
		this.attachFileSn15 = attachFileSn15;
	}
	public int getAttachFileSn16() {
		return attachFileSn16;
	}
	public void setAttachFileSn16(int attachFileSn16) {
		this.attachFileSn16 = attachFileSn16;
	}
	public int getAttachFileSn17() {
		return attachFileSn17;
	}
	public void setAttachFileSn17(int attachFileSn17) {
		this.attachFileSn17 = attachFileSn17;
	}
	public int getAttachFileSn18() {
		return attachFileSn18;
	}
	public void setAttachFileSn18(int attachFileSn18) {
		this.attachFileSn18 = attachFileSn18;
	}
	public int getAttachFileSn19() {
		return attachFileSn19;
	}
	public void setAttachFileSn19(int attachFileSn19) {
		this.attachFileSn19 = attachFileSn19;
	}
	public int getAttachFileSn20() {
		return attachFileSn20;
	}
	public void setAttachFileSn20(int attachFileSn20) {
		this.attachFileSn20 = attachFileSn20;
	}
	public int getAttachFileNo11() {
		return attachFileNo11;
	}
	public void setAttachFileNo11(int attachFileNo11) {
		this.attachFileNo11 = attachFileNo11;
	}
	public int getAttachFileNo12() {
		return attachFileNo12;
	}
	public void setAttachFileNo12(int attachFileNo12) {
		this.attachFileNo12 = attachFileNo12;
	}
	public int getAttachFileNo13() {
		return attachFileNo13;
	}
	public void setAttachFileNo13(int attachFileNo13) {
		this.attachFileNo13 = attachFileNo13;
	}
	public int getAttachFileNo14() {
		return attachFileNo14;
	}
	public void setAttachFileNo14(int attachFileNo14) {
		this.attachFileNo14 = attachFileNo14;
	}
	public int getAttachFileNo15() {
		return attachFileNo15;
	}
	public void setAttachFileNo15(int attachFileNo15) {
		this.attachFileNo15 = attachFileNo15;
	}
	public int getAttachFileNo16() {
		return attachFileNo16;
	}
	public void setAttachFileNo16(int attachFileNo16) {
		this.attachFileNo16 = attachFileNo16;
	}
	public int getAttachFileNo17() {
		return attachFileNo17;
	}
	public void setAttachFileNo17(int attachFileNo17) {
		this.attachFileNo17 = attachFileNo17;
	}
	public int getAttachFileNo18() {
		return attachFileNo18;
	}
	public void setAttachFileNo18(int attachFileNo18) {
		this.attachFileNo18 = attachFileNo18;
	}
	public int getAttachFileNo19() {
		return attachFileNo19;
	}
	public void setAttachFileNo19(int attachFileNo19) {
		this.attachFileNo19 = attachFileNo19;
	}
	public int getAttachFileNo20() {
		return attachFileNo20;
	}
	public void setAttachFileNo20(int attachFileNo20) {
		this.attachFileNo20 = attachFileNo20;
	}
	public String getAttachFileCd11() {
		return attachFileCd11;
	}
	public void setAttachFileCd11(String attachFileCd11) {
		this.attachFileCd11 = attachFileCd11;
	}
	public String getAttachFileCd12() {
		return attachFileCd12;
	}
	public void setAttachFileCd12(String attachFileCd12) {
		this.attachFileCd12 = attachFileCd12;
	}
	public String getAttachFileCd13() {
		return attachFileCd13;
	}
	public void setAttachFileCd13(String attachFileCd13) {
		this.attachFileCd13 = attachFileCd13;
	}
	public String getAttachFileCd14() {
		return attachFileCd14;
	}
	public void setAttachFileCd14(String attachFileCd14) {
		this.attachFileCd14 = attachFileCd14;
	}
	public String getAttachFileCd15() {
		return attachFileCd15;
	}
	public void setAttachFileCd15(String attachFileCd15) {
		this.attachFileCd15 = attachFileCd15;
	}
	public String getAttachFileCd16() {
		return attachFileCd16;
	}
	public void setAttachFileCd16(String attachFileCd16) {
		this.attachFileCd16 = attachFileCd16;
	}
	public String getAttachFileCd17() {
		return attachFileCd17;
	}
	public void setAttachFileCd17(String attachFileCd17) {
		this.attachFileCd17 = attachFileCd17;
	}
	public String getAttachFileCd18() {
		return attachFileCd18;
	}
	public void setAttachFileCd18(String attachFileCd18) {
		this.attachFileCd18 = attachFileCd18;
	}
	public String getAttachFileCd19() {
		return attachFileCd19;
	}
	public void setAttachFileCd19(String attachFileCd19) {
		this.attachFileCd19 = attachFileCd19;
	}
	public String getAttachFileCd20() {
		return attachFileCd20;
	}
	public void setAttachFileCd20(String attachFileCd20) {
		this.attachFileCd20 = attachFileCd20;
	}
	public String getAttachFileExpl11() {
		return attachFileExpl11;
	}
	public void setAttachFileExpl11(String attachFileExpl11) {
		this.attachFileExpl11 = attachFileExpl11;
	}
	public String getAttachFileExpl12() {
		return attachFileExpl12;
	}
	public void setAttachFileExpl12(String attachFileExpl12) {
		this.attachFileExpl12 = attachFileExpl12;
	}
	public String getAttachFileExpl13() {
		return attachFileExpl13;
	}
	public void setAttachFileExpl13(String attachFileExpl13) {
		this.attachFileExpl13 = attachFileExpl13;
	}
	public String getAttachFileExpl14() {
		return attachFileExpl14;
	}
	public void setAttachFileExpl14(String attachFileExpl14) {
		this.attachFileExpl14 = attachFileExpl14;
	}
	public String getAttachFileExpl15() {
		return attachFileExpl15;
	}
	public void setAttachFileExpl15(String attachFileExpl15) {
		this.attachFileExpl15 = attachFileExpl15;
	}
	public String getAttachFileExpl16() {
		return attachFileExpl16;
	}
	public void setAttachFileExpl16(String attachFileExpl16) {
		this.attachFileExpl16 = attachFileExpl16;
	}
	public String getAttachFileExpl17() {
		return attachFileExpl17;
	}
	public void setAttachFileExpl17(String attachFileExpl17) {
		this.attachFileExpl17 = attachFileExpl17;
	}
	public String getAttachFileExpl18() {
		return attachFileExpl18;
	}
	public void setAttachFileExpl18(String attachFileExpl18) {
		this.attachFileExpl18 = attachFileExpl18;
	}
	public String getAttachFileExpl19() {
		return attachFileExpl19;
	}
	public void setAttachFileExpl19(String attachFileExpl19) {
		this.attachFileExpl19 = attachFileExpl19;
	}
	public String getAttachFileExpl20() {
		return attachFileExpl20;
	}
	public void setAttachFileExpl20(String attachFileExpl20) {
		this.attachFileExpl20 = attachFileExpl20;
	}
	public String getAttachFileUseYn11() {
		return attachFileUseYn11;
	}
	public void setAttachFileUseYn11(String attachFileUseYn11) {
		this.attachFileUseYn11 = attachFileUseYn11;
	}
	public String getAttachFileUseYn12() {
		return attachFileUseYn12;
	}
	public void setAttachFileUseYn12(String attachFileUseYn12) {
		this.attachFileUseYn12 = attachFileUseYn12;
	}
	public String getAttachFileUseYn13() {
		return attachFileUseYn13;
	}
	public void setAttachFileUseYn13(String attachFileUseYn13) {
		this.attachFileUseYn13 = attachFileUseYn13;
	}
	public String getAttachFileUseYn14() {
		return attachFileUseYn14;
	}
	public void setAttachFileUseYn14(String attachFileUseYn14) {
		this.attachFileUseYn14 = attachFileUseYn14;
	}
	public String getAttachFileUseYn15() {
		return attachFileUseYn15;
	}
	public void setAttachFileUseYn15(String attachFileUseYn15) {
		this.attachFileUseYn15 = attachFileUseYn15;
	}
	public String getAttachFileUseYn16() {
		return attachFileUseYn16;
	}
	public void setAttachFileUseYn16(String attachFileUseYn16) {
		this.attachFileUseYn16 = attachFileUseYn16;
	}
	public String getAttachFileUseYn17() {
		return attachFileUseYn17;
	}
	public void setAttachFileUseYn17(String attachFileUseYn17) {
		this.attachFileUseYn17 = attachFileUseYn17;
	}
	public String getAttachFileUseYn18() {
		return attachFileUseYn18;
	}
	public void setAttachFileUseYn18(String attachFileUseYn18) {
		this.attachFileUseYn18 = attachFileUseYn18;
	}
	public String getAttachFileUseYn19() {
		return attachFileUseYn19;
	}
	public void setAttachFileUseYn19(String attachFileUseYn19) {
		this.attachFileUseYn19 = attachFileUseYn19;
	}
	public String getAttachFileUseYn20() {
		return attachFileUseYn20;
	}
	public void setAttachFileUseYn20(String attachFileUseYn20) {
		this.attachFileUseYn20 = attachFileUseYn20;
	}
	public Object getAttachFile11() {
		return attachFile11;
	}
	public void setAttachFile11(Object attachFile11) {
		this.attachFile11 = attachFile11;
	}
	public Object getAttachFile12() {
		return attachFile12;
	}
	public void setAttachFile12(Object attachFile12) {
		this.attachFile12 = attachFile12;
	}
	public Object getAttachFile13() {
		return attachFile13;
	}
	public void setAttachFile13(Object attachFile13) {
		this.attachFile13 = attachFile13;
	}
	public Object getAttachFile14() {
		return attachFile14;
	}
	public void setAttachFile14(Object attachFile14) {
		this.attachFile14 = attachFile14;
	}
	public Object getAttachFile15() {
		return attachFile15;
	}
	public void setAttachFile15(Object attachFile15) {
		this.attachFile15 = attachFile15;
	}
	public Object getAttachFile16() {
		return attachFile16;
	}
	public void setAttachFile16(Object attachFile16) {
		this.attachFile16 = attachFile16;
	}
	public Object getAttachFile17() {
		return attachFile17;
	}
	public void setAttachFile17(Object attachFile17) {
		this.attachFile17 = attachFile17;
	}
	public Object getAttachFile18() {
		return attachFile18;
	}
	public void setAttachFile18(Object attachFile18) {
		this.attachFile18 = attachFile18;
	}
	public Object getAttachFile19() {
		return attachFile19;
	}
	public void setAttachFile19(Object attachFile19) {
		this.attachFile19 = attachFile19;
	}
	public Object getAttachFile20() {
		return attachFile20;
	}
	public void setAttachFile20(Object attachFile20) {
		this.attachFile20 = attachFile20;
	}
	public int getFileSn2() {
		return fileSn2;
	}
	public void setFileSn2(int fileSn2) {
		this.fileSn2 = fileSn2;
	}
	public int getFileSn3() {
		return fileSn3;
	}
	public void setFileSn3(int fileSn3) {
		this.fileSn3 = fileSn3;
	}
	public String getFilNm2() {
		return filNm2;
	}
	public void setFilNm2(String filNm2) {
		this.filNm2 = filNm2;
	}
	public String getFilNm3() {
		return filNm3;
	}
	public void setFilNm3(String filNm3) {
		this.filNm3 = filNm3;
	}
}
