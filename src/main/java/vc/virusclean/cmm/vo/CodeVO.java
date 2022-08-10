package vc.virusclean.cmm.vo;

import java.util.List;

import jksoft.com.web.vo.SearchVO;

/**
 * @Class Name : CodeVO.java
 * @Description : 공통코드@VO 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2017.12.25
 * @version 1.0
 * @see vc.virusclean.cmm.vo.CodeVO
 * @Modification Information
 * <pre>
 *   수정일                 수정자               수정내용
 *  ==========     ========    ===========================
 *  2017.12.25      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */

@SuppressWarnings("serial")
public class CodeVO extends SearchVO {

	private int comCdSn = 0;
    /**
     * 공통코드 
     * COM_CD : varchar(6)
     */
    private String comCd = ""; 

    /**
     * 부모공통코드 
     * PREN_COM_CD : varchar(6)
     */
    private String prenComCd = ""; 

    /**
     * 코드표기명 - 사이트코드별 설정해준다.
     */
    private String comCdNm = ""; 
    
    /**
     * 공통코드표기명 - 국문 
     * COM_CD_NM_KR : nvarchar(128)
     */
    private String comCdNmKr = ""; 

    /**
     * 공통코드표기명 - 영문
     * COM_CD_NM_EN : nvarchar(128)
     */
    private String comCdNmEn = ""; 

    /**
     * 공통코드표기명 - 중문
     * COM_CD_NM_ZH : nvarchar(128)
     */
    private String comCdNmZh = ""; 

    /**
     * 공통코드설명 
     * COM_CD_EXPL : nvarchar(256)
     */
    private String comCdExpl = ""; 

    /**
     * 공통코드 정렬순서  
     * ST_NO : numeric(5)
     */
    private int stNo = 0; 

    /**
     * 사이트구분(언어구분) (NN:구분없음.) 
     * SITE_CD : char(2)
     */
    private String siteCd = ""; 

    /**
     * 사용여부 
     * USE_YN : char(1)
     */
    private String useYn = "Y"; 

    /**
     * 등록관리자아이디 
     * RGST_ID : varchar(20)
     */
    private String rgstId = ""; 

    /**
     * 등록일시 
     * RGST_DTM : datetime(23)
     */
    private String rgstDtm = ""; 

    /**
     * 수정관리자아이디 
     * MDFY_MGR_ID : varchar(20)
     */
    private String mdfyId = ""; 

    /**
     * 수정일시 
     * MDFY_DTM : datetime(23)
     */
    private String mdfyDtm = ""; 

    /**
     * 검색 예외 코드 :: 입력값
     */
    private String selectCd;
    
    /**
     * 검색 예외 코드 :: 가공값
     */
    private List<String> lSelectCd;   

    /**
     * CTE 조회 시 노드의 depth
     */
    private int depth = 0; 

    /**
     * 공통코드
     * @return the comCd
     */
    public String getComCd() {
        return comCd;
    }
 
    /**
     * 공통코드
     * @param comCd the comCd to set
     */    
    public void setComCd(String comCd) {
        this.comCd = comCd;
    }

    /**
     * 부모공통코드 
     * @return the prenComCd
     */
    public String getPrenComCd() {
        return prenComCd;
    }
 
    /**
     * 부모공통코드 
     * @param prenComCd the prenComCd to set
     */    
    public void setPrenComCd(String prenComCd) {
        this.prenComCd = prenComCd;
    }

    /**
     * 공통코드표기명 - 국문
     * @return the comCdNmKr
     */
    public String getComCdNmKr() {
        return comCdNmKr;
    }
 
    /**
     * 공통코드표기명 - 국문
     * @param comCdNmKr the comCdNmKr to set
     */    
    public void setComCdNmKr(String comCdNmKr) {
        this.comCdNmKr = comCdNmKr;
    }

    /**
     * 공통코드표기명 - 영문
     * @return the comCdNmEn
     */
    public String getComCdNmEn() {
        return comCdNmEn;
    }
 
    /**
     * 공통코드표기명 - 영문
     * @param comCdNmEn the comCdNmEn to set
     */    
    public void setComCdNmEn(String comCdNmEn) {
        this.comCdNmEn = comCdNmEn;
    }

    /**
     * 공통코드표기명 - 중문
     * @return the comCdNmZh
     */
    public String getComCdNmZh() {
        return comCdNmZh;
    }
 
    /**
     * 공통코드표기명 - 중문
     * @param comCdNmZh the comCdNmZh to set
     */    
    public void setComCdNmZh(String comCdNmZh) {
        this.comCdNmZh = comCdNmZh;
    }

    /**
     * 공통코드설명
     * @return the comCdExpl
     */
    public String getComCdExpl() {
        return comCdExpl;
    }
 
    /**
     * 공통코드설명
     * @param comCdExpl the comCdExpl to set
     */    
    public void setComCdExpl(String comCdExpl) {
        this.comCdExpl = comCdExpl;
    }

    /**
     * 공통코드 정렬순서 
     * @return the stNo
     */
    public int getStNo() {
        return stNo;
    }
 
    /**
     * 공통코드 정렬순서 
     * @param stNo the stNo to set
     */
    public void setStNo(int stNo) {
        this.stNo = stNo;
    }

    /**
     * 사이트구분(언어구분) (NN:구분없음.)
     * @return the siteCd
     */
    public String getSiteCd() {
        return siteCd;
    }
 
    /**
     * 사이트구분(언어구분) (NN:구분없음.)
     * @param siteCd the siteCd to set
     */    
    public void setSiteCd(String siteCd) {
        this.siteCd = siteCd;
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
     * 등록관리자아이디
     * @return the rgstMgrId
     */
    public String getRgstId() {
        return rgstId;
    }
 
    /**
     * 등록관리자아이디
     * @param rgstId the rgstId to set
     */    
    public void setRgstId(String rgstId) {
        this.rgstId = rgstId;
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
     * @return the mdfyDtm
     */
    public String getMdfyDtm() {
        return mdfyDtm;
    }
 
    /**
     * 수정일시
     * @param mdfyDtm the mdfyDtm to set
     */    
    public void setMdfyDtm(String mdfyDtm) {
        this.mdfyDtm = mdfyDtm;
    }

    /**
     * 검색 예외 코드 :: 입력값
     * @return the selectCd
     */
    public String getSelectCd() {
        return selectCd;
    }

    /**
     * 검색 예외 코드 :: 입력값
     * @param selectCd the selectCd to set
     */
    public void setSelectCd(String selectCd) {
        this.selectCd = selectCd;
    }

    /**
     * 검색 예외 코드 :: 가공값
     * @return the lSelectCd
     */
    public List<String> getlSelectCd() {
        return lSelectCd;
    }

    /**
     * 검색 예외 코드 :: 가공값
     * @param lSelectCd the lSelectCd to set
     */
    public void setlSelectCd(List<String> lSelectCd) {
        this.lSelectCd = lSelectCd;
    }

    /**
     * @return the comCdNm
     */
    public String getComCdNm() {
        return comCdNm;
    }

    /**
     * @param comCdNm the comCdNm to set
     */
    public void setComCdNm(String comCdNm) {
        this.comCdNm = comCdNm;
    }

    /**
     * @return the depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * @param depth the depth to set
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

	public int getComCdSn() {
		return comCdSn;
	}

	public void setComCdSn(int comCdSn) {
		this.comCdSn = comCdSn;
	}
    
}