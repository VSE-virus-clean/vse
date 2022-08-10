package jksoft.com.web.vo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jksoft.com.util.CryptoUtil;
import jksoft.com.util.DateUtil;

/**
 * <pre>
 * 검색정보 VO
 * </pre>
 *
 * @ClassName   : SearchVO.java
 * @Description : 검색정보 VO 클래스
 */

@SuppressWarnings("serial")
@JsonInclude(Include.NON_EMPTY)
public class SearchVO extends PageVO {
    
	/**
	 * API여부
	 */
	@JsonIgnore
	protected boolean isApi = false;
	
	@JsonIgnore
	protected boolean isAdmin = false;
	
	@JsonIgnore
	protected boolean isUse = false;
	
	/**
     * 개인DATA만 조회
     */
	@JsonIgnore
	protected boolean isIndividual = false;
	
    /**
     * 검색타입
     * - 검색어 구분값
     * - 제목 : T
     * - 이름 : N
     */
	@JsonIgnore
    protected String searchType = "";

    /**
     * 검색어
     */
	@JsonIgnore
    protected String searchKey = "";
    
    /**
     * 검색어 (암호화)
     */
	@JsonIgnore
    protected String searchKeyEnc = "";
    
    /**
     * 검색 서브 타입
     */
	@JsonIgnore
    protected String searchSubType = "";
    
    /**
     * 서브 검색어
     */
	@JsonIgnore
    protected String searchSubKey = "";

    /**
     * 검색 시작일
     */
	@JsonIgnore
    protected String searchStartDate = "";

    /**
     * 검색 만료일
     */
	@JsonIgnore
    protected String searchEndDate = "";
    
    /**
     * 구분 검색타입
     */
	@JsonIgnore
    protected String searchGubunType = "";
    
    /**
     * 구분 검색타입2
     */
	@JsonIgnore
    protected String searchGubunType2 = "";
    
    /**
     * 구분 검색타입3
     */
	@JsonIgnore
    protected String searchGubunType3 = "";
    
    /**
     * 구분 검색타입4
     */
	@JsonIgnore
    protected String searchGubunType4 = "";
    
    /**
     * 구분 검색타입5
     */
	@JsonIgnore
    protected String searchGubunType5 = "";
	
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
     * 파일 대체텍스트
     */
	@JsonIgnore
    private String filExpl = "";
    
    /**
     * 메뉴코드
     */
	@JsonIgnore
    private String fileMenuCd = "";
	
	@JsonIgnore
	private String search_year = "";
	
	@JsonIgnore
    private String search_month = "";
    
	@JsonIgnore
	private String search_day = "";
	
	@JsonIgnore
	private String orderText = "RGST_DTM DESC";
    
    
    
    /**********************************************************
     * GETTER / SETTER
     **********************************************************/
    
    /**
     * 검색타입
     * @return the searchType
     */
    public String getSearchType() {
        return searchType;
    }

    /**
     * 검색타입
     * @param searchType the searchType to set
     */
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
    
	public boolean getIsUse() {
		return isUse;
	}

	public void setIsUse(boolean isUse) {
		this.isUse = isUse;
	}

	public boolean getIsApi() {
		return isApi;
	}

	public void setIsApi(boolean isApi) {
		this.isApi = isApi;
	}
	
	public void setApi(boolean isApi) {
		this.isApi = isApi;
	}

	public boolean getIndividual() {
		return isIndividual;
	}

	public void setIndividual(boolean isIndividual) {
		this.isIndividual = isIndividual;
	}
	
	public boolean getIsIndividual() {
		return isIndividual;
	}

	public void setIsIndividual(boolean isIndividual) {
		this.isIndividual = isIndividual;
	}


	/**
     * 검색어
     * @return the searchKey
     */
    public String getSearchKey() {
        return searchKey;
    }
    
    /**
     * 검색어
     * @param searchKey the searchKey to set
     */
    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
        this.searchKeyEnc = CryptoUtil.encryptARIA(searchKey);
    }
    
    /**
     * @return the getSearchKeyEnc
     */
    public String getSearchKeyEnc() {
        return searchKeyEnc;
    }

    /**
     * @param searchKeyEnc the searchKeyEnc to set
     */
    public void setSearchKeyEnc(String searchKeyEnc) {
        this.searchKeyEnc = searchKeyEnc;
    }
    
    /**
     * 검색 시작일
     * @return the searchStartDate
     */
    public String getSearchStartDate() {
        DateUtil.getCurrentDate("");
        return searchStartDate;
    }

    /**
     * 검색 시작일
     * @param searchStartDate the searchStartDate to set
     */
    public void setSearchStartDate(String searchStartDate) {
        this.searchStartDate = searchStartDate;
    }

    /**
     * 검색 만료일
     * @return the searchEndDate
     */
    public String getSearchEndDate() {
        return searchEndDate;
    }

    /**
     * 검색 만료일
     * @param searchEndDate the searchEndDate to set
     */
    public void setSearchEndDate(String searchEndDate) {
        this.searchEndDate = searchEndDate;
    }

    /**
     * 구분 검색타입
     * @return the searchGubunType
     */
    public String getSearchGubunType() {
        return searchGubunType;
    }

    /**
     * 구분 검색타입
     * @param searchGubunType the searchGubunType to set
     */
    public void setSearchGubunType(String searchGubunType) {
        this.searchGubunType = searchGubunType;
    }
    
    /**
     * 구분 검색타입2
     * @return the searchGubunType2
     */
    public String getSearchGubunType2() {
        return searchGubunType2;
    }

    /**
     * 구분 검색타입2
     * @param searchGubunType2 the searchGubunType2 to set
     */
    public void setSearchGubunType2(String searchGubunType2) {
        this.searchGubunType2 = searchGubunType2;
    }

    /**
     * 구분 검색타입3
     * @return the searchGubunType3
     */
    public String getSearchGubunType3() {
        return searchGubunType3;
    }

    /**
     * 구분 검색타입3
     * @param searchGubunType3 the searchGubunType3 to set
     */
    public void setSearchGubunType3(String searchGubunType3) {
        this.searchGubunType3 = searchGubunType3;
    }
    
    /**
     * 구분 검색타입4
     * @return the searchGubunType4
     */
    public String getSearchGubunType4() {
        return searchGubunType4;
    }

    /**
     * 구분 검색타입4
     * @param searchGubunType4 the searchGubunType4 to set
     */
    public void setSearchGubunType4(String searchGubunType4) {
        this.searchGubunType4 = searchGubunType4;
    }

    /**
     * 검색 서브 타입
     * @return the searchSubType
     */
    public String getSearchSubType() {
        return searchSubType;
    }

    /**
     * 검색 서브 타입
     * @param searchSubType the searchSubType to set
     */
    public void setSearchSubType(String searchSubType) {
        this.searchSubType = searchSubType;
    }

    /**
     * 삭제용 고유번호
     * @return the delSeq
     */
    public List<String> getDelSeq() {
        return delSeq;
    }

    /**
     * 삭제용 고유번호
     * @param delSeq the delSeq to set
     */
    public void setDelSeq(List<String> delSeq) {
        this.delSeq = delSeq;
    }

    /**
     * 서브 검색어
     * @return the searchSubKey
     */
    public String getSearchSubKey() {
        return searchSubKey;
    }

    /**
     * 서브 검색어
     * @param searchSubKey the searchSubKey to set
     */
    public void setSearchSubKey(String searchSubKey) {
        this.searchSubKey = searchSubKey;
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
     * @return the filExpl
     */
    public String getFilExpl() {
        return filExpl;
    }

    /**
     * @param fileExpl the fileExpl to set
     */
    public void setFilExpl(String filExpl) {
        this.filExpl = filExpl;
    }

    /**
     * @return the fileMenuCd
     */
    public String getFileMenuCd() {
        return fileMenuCd;
    }

    /**
     * @param fileMenuCd the fileMenuCd to set
     */
    public void setFileMenuCd(String fileMenuCd) {
        this.fileMenuCd = fileMenuCd;
    }

    /**
     * @return the searchGubunType5
     */
    public String getSearchGubunType5() {
        return searchGubunType5;
    }

    /**
     * @param searchGubunType5 the searchGubunType5 to set
     */
    public void setSearchGubunType5(String searchGubunType5) {
        this.searchGubunType5 = searchGubunType5;
    }
    
    public String getSearch_year() {
		return search_year;
	}

	public void setSearch_year(String search_year) {
		this.search_year = search_year;
	}

	public String getSearch_month() {
		return search_month;
	}

	public void setSearch_month(String search_month) {
		this.search_month = search_month;
	}

	public String getSearch_day() {
		return search_day;
	}

	public void setSearch_day(String search_day) {
		this.search_day = search_day;
	}

	public String getOrderText() {
		return orderText;
	}

	public void setOrderText(String orderText) {
		this.orderText = orderText;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
