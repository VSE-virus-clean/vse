package jksoft.com.web.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * <pre>
 * 페이지 정보 VO
 * </pre>
 *
 * @ClassName   : pageVO.java
 * @Description : 페이지 정보 VO 클래스
 * @author Jeong.hyoungjea
 * @since 2013. 7. 29.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2013. 7. 29.     Jeong.hyoungjea     최초 생성
 * </pre>
 */

@SuppressWarnings("serial")
@JsonInclude(Include.NON_EMPTY)
public class PageVO implements Serializable {

    /**
     * 현재 페이지 번호
     */    
	@JsonIgnore
    private int page = 1;
    
    /**
     * 전체 컨텐츠 갯수
     */
	@JsonIgnore
    private int totalRow = 0;   
    
    /**
     * 전체 공지 컨텐츠 갯수
     */
	@JsonIgnore
    private int totalNotiRow = 0; 
    
    /**
     * 나눌 페이지 갯수
     */
	@JsonIgnore
    private int pageLimit = 10;
    
    /**
     * 페이지 노출 row 갯수
     */
	@JsonIgnore
    private int rowLimit = 10;  
    
    /**
     * 페이지 노출 시작번호
     * (page - 1) * rowLimit;
     */
	@JsonIgnore
    private int rowStart;
    
    /**
     * 페이지 노출 마지막번호
     * (MSSQL용)
     * page * rowLimit;
     */
	@JsonIgnore
    private int rowEnd;
    
    /**
     * DB 게시물 ROWNUM
     */
	@JsonIgnore
    private int rowNum = 0;
    
    
    /**********************************************************
     * GETTER / SETTER
     **********************************************************/
    
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
    
    /**
     * @return the totalNotiRow
     */
    public int getTotalNotiRow() {
        return totalNotiRow;
    }

    /**
     * @param totalNotiRow the totalNotiRow to set
     */
    public void setTotalNotiRow(int totalNotiRow) {
        this.totalNotiRow = totalNotiRow;
    }

    /**
     * @return the pageLimit
     */
    public int getPageLimit() {
        return pageLimit;
    }

    /**
     * @param pageLimit the pageLimit to set
     */
    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    /**
     * @return the rowLimit
     */
    public int getRowLimit() {
        return rowLimit;
    }

    /**
     * @param rowLimit the rowLimit to set
     */
    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * 페이지 시작 게시물의 번호를 계산한다.
     * @return the rowStart
     */
    public int getRowStart() {
        return (this.page - 1) * (this.rowLimit - this.totalNotiRow);
    }    
    
    /**
     * 페이지 마지막 게시물의 번호를 계산한다.
     * @return the rowStart
     */
    public int getRowEnd() {
        return this.page * (this.rowLimit - this.totalNotiRow);
    }  
    
    /**
     * DB 게시물 ROWNUM
     * @return the rowNum
     */
    public int getRowNum() {
        return rowNum;
    }

    /**
     * DB 게시물 ROWNUM
     * @param rowNum the rowNum to set
     */
    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

}
