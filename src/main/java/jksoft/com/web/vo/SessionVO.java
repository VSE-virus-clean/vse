package jksoft.com.web.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : SessionVO.java
 * @Description : 클래스 설명을 기술합니다.
 * @author Jeong.HyoungJea
 * @since 2014. 11. 10.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2014. 11. 10.     Jeong.HyoungJea     최초 생성
 * </pre>
 */
@SuppressWarnings("serial")
public class SessionVO implements Serializable {
	
	/**
     * 사용자 고유번호(SEQ)
     */	
	private int sn = 0;
	
	/**
     * 사용자 아이디
     */
    private String id = "";
    
    /**
     * 사용자 이름
     */
    private String name = "";
    
    /**
     * 회원번호 77001부터 시작한다.
     */
    private String no = "";
    
    /**
     * 사용자 별명
     */
    private String nick = "";
    
    //전화번호
    private String tn = "";
    private String hp = "";
    private String zipCd = "";
    private String addr1 = "";
    private String addr2 = "";
    
    /**
     * 암호화된 사용자 이름.
     */
    private String cryptoName = "";
    
    /**
     * 사용자 이메일
     */
    private String email = "";
    
    /**
     * 암호화된 이메일
     */
    private String cryptoEmail = "";
    
    /**
     * 로그인 아이피 주소
     */
    private String ipAddress = "";

    /**
     * 사용자 권한
     */
    private String authLevel = "A";
    
    /*
     * 회원등급(일반-normal/브론즈-bronze(3개)/실버-silver(10개)/골드-gold(30개))
     */
    private String grade = "NORMAL";
    
    /**
     * 접속 사이트 코드
     * - KR : 국문
     * - EN : 영문
     * - ZH : 중문 
     */
    private String siteCode = "USER";
    
    /**
     * 부서명
     */
    private String opsNm = "";
    
    /**
     * 직위명
     */
    private String poaNm = "";
    
    /**
     * 접속 메뉴 아이디
     */
    private String menuCd = "";
    
    /**
     * 접속 메뉴 이름
     */
    private String menuNm = "";
    
    /**
     * 접속 메뉴 Path
     */
    private String menuPathCd = "";
    
    /**
     * 접속 메뉴 Path 문자화
     */
    private String menuPathNm = "";
    
    /**
     * 관리자 권한 메뉴 목록
     */
    private List<MenuVO> menuList = new ArrayList<MenuVO>();
    
    /**
     * Qna 카테고리별 권한 목록 - 공통코드
     */
    private List<String> qnaCateList = new ArrayList<String>();
    
    /**
     * Qna 카테고리별 권한 목록 - 공통코드
     */
    private String[] qnaCateArray = new String[] {};
    
    private String saveDir = "";
    
    private String uploadPath = "";
    
    /**
     * 첫번째 메뉴 URL
     */
    private String firstMenuUrl = "";
    
    /**
     * 컨텐츠 아이디
     */
    private long cotnSn = 0L;
    
    /**
     * 로그보기 표시 여부
     */
    private boolean logView = false;
    
    private String mbrDtm = "";
    
    private String renewDtm = "";

    /**
     * @return the sn
     */
	public int getSn() {
		return sn;
	}

	/**
     * @param sn the sn to set
     */
	public void setSn(int sn) {
		this.sn = sn;
	}    
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the cryptoName
     */
    public String getCryptoName() {
        return cryptoName;
    }

    /**
     * @param cryptoName the cryptoName to set
     */
    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the cryptoEmail
     */
    public String getCryptoEmail() {
        return cryptoEmail;
    }

    /**
     * @param cryptoEmail the cryptoEmail to set
     */
    public void setCryptoEmail(String cryptoEmail) {
        this.cryptoEmail = cryptoEmail;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return the authLevel
     */
    public String getAuthLevel() {
        return authLevel;
    }

    /**
     * @param authLevel the authLevel to set
     */
    public void setAuthLevel(String authLevel) {
        this.authLevel = authLevel;
    }

    /**
     * @return the siteCode
     */
    public String getSiteCode() {
        return siteCode;
    }

    /**
     * @param siteCode the siteCode to set
     */
    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
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
     * @return the menuList
     */
    public List<MenuVO> getMenuList() {
        return menuList;
    }

    /**
     * @param menuList the menuList to set
     */
    public void setMenuList(List<MenuVO> menuList) {
        this.menuList = menuList;
    }

    /**
     * @return the opsNm
     */
    public String getOpsNm() {
        return opsNm;
    }

    /**
     * @param opsNm the opsNm to set
     */
    public void setOpsNm(String opsNm) {
        this.opsNm = opsNm;
    }

    /**
     * @return the poaNm
     */
    public String getPoaNm() {
        return poaNm;
    }

    /**
     * @param poaNm the poaNm to set
     */
    public void setPoaNm(String poaNm) {
        this.poaNm = poaNm;
    }

    /**
     * @return the qnaCateList
     */
    public List<String> getQnaCateList() {
        return qnaCateList;
    }

    /**
     * @param qnaCateList the qnaCateList to set
     */
    public void setQnaCateList(List<String> qnaCateList) {
        this.qnaCateList = qnaCateList;
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
     * @return the qnaCateArray
     */
    public String[] getQnaCateArray() {
        
        if(!this.getQnaCateList().isEmpty()){
            this.qnaCateArray = this.qnaCateList.toArray(this.qnaCateArray);
        }
        
        return qnaCateArray;
    }

    /**
     * @return the saveDir
     */
    public String getSaveDir() {
        return saveDir;
    }

    /**
     * @param saveDir the saveDir to set
     */
    public void setSaveDir(String saveDir) {
        this.saveDir = saveDir;
    }

    /**
     * @return the uploadPath
     */
    public String getUploadPath() {
        return uploadPath;
    }

    /**
     * @param uploadPath the uploadPath to set
     */
    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    /**
     * @return the menuNm
     */
    public String getMenuNm() {
        return menuNm;
    }

    /**
     * @param menuNm the menuNm to set
     */
    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    /**
     * @return the logView
     */
    public boolean isLogView() {
        return logView;
    }

    /**
     * @param logView the logView to set
     */
    public void setLogView(boolean logView) {
        this.logView = logView;
    }

    /**
     * @return the cotnSn
     */
    public long getCotnSn() {
        return cotnSn;
    }

    /**
     * @param cotnSn the cotnSn to set
     */
    public void setCotnSn(long cotnSn) {
        this.cotnSn = cotnSn;
    }

    /**
     * @return the firstMenuUrl
     */
    public String getFirstMenuUrl() {
        return firstMenuUrl;
    }

    /**
     * @param firstMenuUrl the firstMenuUrl to set
     */
    public void setFirstMenuUrl(String firstMenuUrl) {
        this.firstMenuUrl = firstMenuUrl;
    }

	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}

	/**
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * @param nick the nick to set
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * @return the mbrDtm
	 */
	public String getMbrDtm() {
		return mbrDtm;
	}

	/**
	 * @param mbrDtm the mbrDtm to set
	 */
	public void setMbrDtm(String mbrDtm) {
		this.mbrDtm = mbrDtm;
	}

	/**
	 * @return the renewDtm
	 */
	public String getRenewDtm() {
		return renewDtm;
	}

	/**
	 * @param renewDtm the renewDtm to set
	 */
	public void setRenewDtm(String renewDtm) {
		this.renewDtm = renewDtm;
	}

	/**
	 * @return the tn
	 */
	public String getTn() {
		return tn;
	}

	/**
	 * @param tn the tn to set
	 */
	public void setTn(String tn) {
		this.tn = tn;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getZipCd() {
		return zipCd;
	}

	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

}
