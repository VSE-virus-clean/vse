package vc.virusclean.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jksoft.com.exception.LeaveaTraceException;
import jksoft.com.exception.NoResourceException;
import jksoft.com.service.XAbstractService;
import jksoft.com.util.CryptoUtil;
import jksoft.com.util.DateUtil;
import jksoft.com.util.MailSenderUtil;
import jksoft.com.util.MultiUtil;
import jksoft.com.util.StringUtil;
import jksoft.com.web.vo.SessionVO;
import vc.virusclean.admin.member.service.dao.MemberDAO;
import vc.virusclean.admin.member.vo.MemberVO;
import vc.virusclean.admin.shop.service.dao.CouponDAO;
import vc.virusclean.admin.shop.vo.CouponMetaVO;
import vc.virusclean.admin.shop.vo.CouponVO;
import vc.virusclean.cmm.service.BoardService;
import vc.virusclean.cmm.service.dao.CodeDAO;
import vc.virusclean.cmm.vo.BoardVO;
import vc.virusclean.cmm.vo.CodeEnvVO;
import vc.virusclean.user.service.UserMemberService;
import vc.virusclean.user.vo.LoginVO;
import vc.virusclean.user.vo.MemberApiVO;

/**
 * <pre>
 * 회원정보 관련 프로세스 처리 
 * </pre>
 * 
 * @ClassName   : UserMemberServiceImpl.java
 * @Description : UserMemberService 를 구현
 */
@Service("userMemberService")
public class UserMemberServiceImpl extends XAbstractService implements UserMemberService {

	@Resource(name="mailSenderUtil")
	private MailSenderUtil mailSenderUtil;
	
	@Resource(name="boardService")
	private BoardService boardService;
	
    @Resource(name="memberDAO")   
    private MemberDAO memberDAO;
    
    @Resource(name="codeDAO")   
    private CodeDAO codeDAO;
    
    @Resource(name="couponDAO")   
    private CouponDAO couponDAO;

    @Resource(name="multiUtil")
    private MultiUtil multiUtil;
    
    /**
     * 휴면처리 가능한 비로그인 일자. 
     */
    @Value(value="#{global['login.lock.dateDiff']}")
    protected int LOCK_ID_DATE_DIFF;
    
    /**
     * 비밀번호 변경 기일.
     */
    @Value(value="#{global['login.password.dateDiff']}")
    protected int PW_MOD_DATE_DIFF;

    
    /**
     * 휴면처리 가능한 비밀번호 오류 횟수
     */
    @Value(value="#{global['login.lock.errorCount']}")
    protected int LOCK_ID_PW_ERROR_COUNT;
    
    /**
     * 사용자 사이트 도메인
     */
    @Value(value="#{global['site.user.domain']}")
    private String siteUserDomain;
    

    /*
     * 정보를 조회한다.
     */
    public Map<String, Object> selectMember() throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        Map<String,Object> mSession = multiUtil.getSessionInfo();
        
        MemberVO memberVO = new MemberVO();
        memberVO.setMbrSn((Integer)mSession.get("userSn"));
        memberVO.setMbrId((String)mSession.get("userId"));
        
            
        MemberVO memberVO2 = memberDAO.selectMember(memberVO);

        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(memberVO2 == null){
            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
        }else{                
            mResult.put("info", memberVO2);                  
        }
        	
        mResult.put("searchInfo", memberVO);         
        				
        return mResult;
    }
    
    
    /*
     * 정보를 조회한다.
     */
    public Map<String, Object> selectMemberApiMod(MemberVO memberVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
            
        MemberVO memberVO2 = memberDAO.selectMemberApiMod(memberVO);

        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(memberVO2 == null){
            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
        }else{                
        	//로그인 처리
            HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
            httpServletRequest.getSession(false).invalidate();  
            HttpSession httpSession = httpServletRequest.getSession(true);
            
            //세션 생성
            SessionVO sessionVO = new SessionVO(); 
            sessionVO.setSn(memberVO2.getMbrSn());
            sessionVO.setId(memberVO2.getMbrId());     
            sessionVO.setName(memberVO2.getMbrNm());
            sessionVO.setNick(memberVO2.getMbrNick());
            sessionVO.setTn(memberVO2.getMbrTn());
            sessionVO.setHp(StringUtil.addMinusCharHp(memberVO2.getMbrHp()));
            sessionVO.setSiteCode("VC");
            sessionVO.setEmail(memberVO2.getMbrEml());
            sessionVO.setGrade(memberVO2.getMbrGrade());
            sessionVO.setZipCd(memberVO2.getZipCd());
            sessionVO.setAddr1(memberVO2.getAdrSbc1());
            sessionVO.setAddr2(memberVO2.getAdrSbc2());
            sessionVO.setIpAddress(httpServletRequest.getRemoteAddr());
            
            httpSession.setAttribute("sessionVO", sessionVO);
            httpSession.setMaxInactiveInterval(60 * 60);
            
            mResult.put("info", memberVO2);                  
        }
        	
        mResult.put("searchInfo", memberVO);         
        				
        return mResult;
    }
    
    
    /*
     * 본인인증용 정보를 조회한다.
     */
    public Map<String, Object> selectMemberCert() throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
        HttpSession httpSession = httpServletRequest.getSession(false);
        
        
        //SNS 가입인지 확인한다.
        if(httpSession.getAttribute("certReg") != null){
        	MemberVO memberVO =  (MemberVO)httpSession.getAttribute("certReg");
            
	        MemberVO memberVO2 = memberDAO.selectMember(memberVO);
	
	        /*
	         * 작업 성공여부에 따른 처리 
	         * 선택 게시물이 없으면 ResourceNotFound페이지로
	         */
	        if(memberVO2 == null){
	            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
	        }else{                
	            mResult.put("info", memberVO2);                  
	        }
        }else {
        	throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));    
        }
        	
        return mResult;
    }
    
    /*
     * APP에서 mbrSn으로  사용자 정보 조회
     */
    public Map<String, Object> selectApiMember(LoginVO loginVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        String code = "OK";
        String message = "성공";
        
        try{
        	if(loginVO.getMbrSn() > 0){
		        MemberVO memberVO = new MemberVO();
		        memberVO.setMbrSn(loginVO.getMbrSn());
		            
		        MemberVO memberVO2 = memberDAO.selectMember(memberVO);
	
		        /*
		         * 작업 성공여부에 따른 처리 
		         * 선택 게시물이 없으면 ResourceNotFound페이지로
		         */
		        if(memberVO2 == null){
		        	code = "E_WRONG";
		            message = "정보를 찾을수 없습니다." ; 
		        }else{                
		        	memberVO2.setMbrPw("");
		        	
		        	BoardVO boardVO = new BoardVO();
                	boardVO.setIsApi(true);
                	boardVO.setLgrpCd("POPUP");
                	boardVO.setMgrpCd("APP");
                	mResult.put("popup", boardService.selectApiBoardList(boardVO));
                	
                	//펌웨어목록
                	BoardVO boardVO3 = new BoardVO();
                	boardVO3.setIsApi(true);
                	boardVO3.setRowLimit(1);
                	boardVO3.setLgrpCd("APP");
                	boardVO3.setMgrpCd("FIRMWARE");
                	Map<String, Object> firmware = boardService.selectBoardList(boardVO3);
                	
                	if(firmware.containsKey("list")){
                		@SuppressWarnings("unchecked")
						BoardVO tempVO = ((List<BoardVO>)firmware.get("list")).get(0);
                		memberVO2.setAppFirmVer(tempVO.getBlcTitl());
                		memberVO2.setAppFirmUrl(siteUserDomain + StringUtil.printDownloadLink(tempVO.getFileSn()));
                	}
                	
                	//부트로더목록
                	boardVO3.setMgrpCd("BOOT");
                	Map<String, Object> boot = boardService.selectBoardList(boardVO3);
                	
                	if(boot.containsKey("list")){
                		@SuppressWarnings("unchecked")
						BoardVO tempVO = ((List<BoardVO>)boot.get("list")).get(0);
                		memberVO2.setAppBootVer(tempVO.getBlcTitl());
                		memberVO2.setAppBootUrl(siteUserDomain + StringUtil.printDownloadLink(tempVO.getFileSn()));
                	}
                    	
                    MemberApiVO userInfoVO = new MemberApiVO();
                    userInfoVO.setMbrSn(memberVO2.getMbrSn());	
                    userInfoVO.setMbrId(memberVO2.getMbrId());	
                    userInfoVO.setMbrNick(memberVO2.getMbrNick());	
                    userInfoVO.setMbrGrade(memberVO2.getMbrGrade());	
                    userInfoVO.setMbrNm(memberVO2.getMbrNm());	
                    userInfoVO.setMbrTn(memberVO2.getMbrTn());	
                    userInfoVO.setMbrHp(memberVO2.getMbrHp());	
                    userInfoVO.setMbrEml(memberVO2.getMbrEml());	
                    userInfoVO.setGenderCd(memberVO2.getGenderCd());	
                    userInfoVO.setPoint(memberVO2.getPoint());	
                    userInfoVO.setMbrBday(memberVO2.getMbrBday());	
                    userInfoVO.setMbrAge(memberVO2.getMbrAge());	
                    userInfoVO.setZipCd(memberVO2.getZipCd());	
                    userInfoVO.setAdrSbc1(memberVO2.getAdrSbc1());	
                    userInfoVO.setAdrSbc2(memberVO2.getAdrSbc2());	
                    userInfoVO.setBbsYn(memberVO2.getBbsYn());	
                    userInfoVO.setSmsYn(memberVO2.getSmsYn());	
                    userInfoVO.setSmsDtm(memberVO2.getSmsDtm());	
                    userInfoVO.setEmlYn(memberVO2.getEmlYn());	
                    userInfoVO.setEmlDtm(memberVO2.getEmlDtm());	
                    userInfoVO.setPushYn(memberVO2.getPushYn());	
                    userInfoVO.setPushDtm(memberVO2.getPushDtm());	
                    userInfoVO.setSnsCd(memberVO2.getSnsCd());	
                    userInfoVO.setAppOs(memberVO2.getAppOs());	
                    userInfoVO.setAppToken(memberVO2.getAppToken());	
                    userInfoVO.setAppTokenAltrDtm(memberVO2.getAppTokenAltrDtm());	
                    userInfoVO.setAppMac(memberVO2.getAppMac());	
                    userInfoVO.setAppBootVer(memberVO2.getAppBootVer());	
                    userInfoVO.setAppBootUrl(memberVO2.getAppBootUrl());	
                    userInfoVO.setAppFirmVer(memberVO2.getAppFirmVer());	
                    userInfoVO.setAppFirmUrl(memberVO2.getAppFirmUrl());	 
                    
                    mResult.put("info", userInfoVO);
		        }
        	}else{
        		throw new IllegalArgumentException();
        	}
        
        }catch(Exception exception){          
        	code = "E_SERVER";
            message = "작업중 에러가 발행했습니다." ; 
        }
        
        mResult.put("code", code);   
        mResult.put("message", message);   
        	
        return mResult;
    }
    
    
    /*
     * 회원으로 등록한다.
     */
    public Map<String, Object> insertMember(MemberVO memberVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 

        try{
        	HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
            HttpSession httpSession = httpServletRequest.getSession(false);
            
            //SNS 가입인지 확인한다.
            if(httpSession.getAttribute("snsLogin") != null && "Y".equals(httpSession.getAttribute("snsLogin"))){
            	memberVO.setSnsCd((String)httpSession.getAttribute("snsCd"));
            	memberVO.setMbrId((String)httpSession.getAttribute("snsMbrId"));
            }
        	
        	//등록여부
        	if(memberDAO.selectMemberCheckDuplicateById(memberVO) == 0 && memberDAO.selectMemberCheckDuplicateByDI(memberVO) == 0)
        	{
        		
        		//TODO 같은 휴대폰번호 확인로직
        		
        		if(httpSession.getAttribute("snsLogin") != null && "Y".equals(httpSession.getAttribute("snsLogin"))){
        			memberVO.setMbrPw(CryptoUtil.encodeUserPassword((String)httpSession.getAttribute("snsMbrId")));
                }else{
        			//비밀번호 암호화
        			memberVO.setMbrPw(CryptoUtil.encodeUserPassword64(memberVO.getMbrPwEnc()));
                }
    			
    			//나이계산
        		if(!StringUtil.isEmpty(memberVO.getMbrBday())){
        			memberVO.setMbrAge(DateUtil.isAge(memberVO.getMbrBday()));
        		}
        		
    			//사용자 등록
    			Object resultObject = memberDAO.insertMember(memberVO);
    			
    			if(resultObject.getClass() == Integer.class){
    				
    				memberVO.setMbrSn((Integer)resultObject);
    				
    				//환영쿠폰조회
    				CodeEnvVO codeEnvVO = codeDAO.selectCodePreferences();
    				
    				try {
	    				//환영쿠톤 발급
	    				if("Y".equals(codeEnvVO.getCouponYn()) && !StringUtil.isEmpty(codeEnvVO.getCouponWelcom()))
	    				{
	    					int iCouponSn = Integer.valueOf(codeEnvVO.getCouponWelcom().split("\\|")[0]);
	    					
	    					CouponVO couponVO = new CouponVO();
	    					couponVO.setMbrSn(memberVO.getMbrSn());
	    					couponVO.setCupMetaSn(iCouponSn);
	    					
	    					this.getWelcomCoupon(couponVO);
	    				}
    				}catch(Exception exception) {
    					//ignore
    					log.error("== 환영쿠폰 발급 오류 ==");
    				}
    				
    				//기존 세션 정보 삭제
    				httpServletRequest.getSession(false).invalidate();
    				
            		String strMailTitle = "VIRUS CLEAN LAB 회원가입 완료";
            		String strMailFileName = "memberRegComplete.html";
            		
            		Map<String, String> mailInfo = new HashMap<String, String>();
            		
            		if("HOME".equals(memberVO.getSnsCd())){
            			mailInfo.put("$$USER_ID$$", memberVO.getMbrId());
            		}else{
            			mailInfo.put("$$USER_ID$$", memberVO.getSnsCd() + " 간편로그인");
            		}
            		
            		mailInfo.put("$$NAME$$", memberVO.getMbrNm());
            		mailInfo.put("$$REG_DATE$$", DateUtil.getToday());
            		
            		//메일 발송
                    mailSenderUtil.mailSendToUser(memberVO.getMbrEml(), strMailTitle, strMailFileName, mailInfo);
                    
            		bStatus = true;
                    mResult.put("check", "Y");   
                    mResult.put("returnUrl", "/registration/complete.vc");
            	}else{
    	            throw new IllegalArgumentException();
            	}
            }else{
            	mResult.put("check", "N");   
            }
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;

    }
    
    /**
     * 회원가입 : APP용
     */
    public Map<String, Object> insertAppMember(MemberVO memberVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        

        String code = "";
        String message = "";
        
        try{
        	//등록여부
        	if(memberDAO.selectMemberCheckDuplicateById(memberVO) == 0)
        	{
        		if("HOME".equals(memberVO.getSnsCd())){
        			//비밀번호 암호화
        			memberVO.setMbrPw(CryptoUtil.encodeUserPassword64(memberVO.getMbrPwEnc()));
        			memberVO.setMbrEml(memberVO.getMbrId());
                }else{
                	memberVO.setMbrPw(CryptoUtil.encodeUserPassword(memberVO.getMbrId()));
                }
    			
    			//사용자 등록
    			Object resultObject = memberDAO.insertMember(memberVO);
    			
    			if(resultObject.getClass() == Integer.class){
    				
    				memberVO.setMbrSn((Integer)resultObject);
    				
    				//push동의시 업데이트
    				if("Y".equals(memberVO.getPushYn())){
    	        		memberDAO.updateByPushYn(memberVO);
    	        	}
    				
    				//정보 조회
    				MemberVO memberVO2 = memberDAO.selectMember(memberVO);
    				
                	//팝업목록
                	BoardVO boardVO = new BoardVO();
                	boardVO.setIsApi(true);
                	boardVO.setLgrpCd("POPUP");
                	boardVO.setMgrpCd("APP");
                	mResult.put("popup", boardService.selectApiBoardList(boardVO));
                	
                	//펌웨어목록
                	BoardVO boardVO3 = new BoardVO();
                	boardVO3.setIsApi(true);
                	boardVO3.setRowLimit(1);
                	boardVO3.setLgrpCd("APP");
                	boardVO3.setMgrpCd("FIRMWARE");
                	Map<String, Object> firmware = boardService.selectBoardList(boardVO3);
                	
                	if(firmware.containsKey("list")){
                		@SuppressWarnings("unchecked")
						BoardVO tempVO = ((List<BoardVO>)firmware.get("list")).get(0);
                		memberVO2.setAppFirmVer(tempVO.getBlcTitl());
                		memberVO2.setAppFirmUrl(siteUserDomain + StringUtil.printDownloadLink(tempVO.getFileSn()));
                	}
                	
                	//부트로더목록
                	boardVO3.setMgrpCd("BOOT");
                	Map<String, Object> boot = boardService.selectBoardList(boardVO3);
                	
                	if(boot.containsKey("list")){
                		@SuppressWarnings("unchecked")
						BoardVO tempVO = ((List<BoardVO>)boot.get("list")).get(0);
                		memberVO2.setAppBootVer(tempVO.getBlcTitl());
                		memberVO2.setAppBootUrl(siteUserDomain + StringUtil.printDownloadLink(tempVO.getFileSn()));
                	}
                
                    //비번삭제
                    memberVO2.setMbrPw("");
                    
                    MemberApiVO userInfoVO = new MemberApiVO();
                    userInfoVO.setMbrSn(memberVO2.getMbrSn());	
                    userInfoVO.setMbrId(memberVO2.getMbrId());	
                    userInfoVO.setMbrNick(memberVO2.getMbrNick());	
                    userInfoVO.setMbrGrade(memberVO2.getMbrGrade());	
                    userInfoVO.setMbrNm(memberVO2.getMbrNm());	
                    userInfoVO.setMbrTn(memberVO2.getMbrTn());	
                    userInfoVO.setMbrHp(memberVO2.getMbrHp());	
                    userInfoVO.setMbrEml(memberVO2.getMbrEml());	
                    userInfoVO.setGenderCd(memberVO2.getGenderCd());	
                    userInfoVO.setPoint(memberVO2.getPoint());	
                    userInfoVO.setMbrBday(memberVO2.getMbrBday());	
                    userInfoVO.setMbrAge(memberVO2.getMbrAge());	
                    userInfoVO.setZipCd(memberVO2.getZipCd());	
                    userInfoVO.setAdrSbc1(memberVO2.getAdrSbc1());	
                    userInfoVO.setAdrSbc2(memberVO2.getAdrSbc2());	
                    userInfoVO.setBbsYn(memberVO2.getBbsYn());	
                    userInfoVO.setSmsYn(memberVO2.getSmsYn());	
                    userInfoVO.setSmsDtm(memberVO2.getSmsDtm());	
                    userInfoVO.setEmlYn(memberVO2.getEmlYn());	
                    userInfoVO.setEmlDtm(memberVO2.getEmlDtm());	
                    userInfoVO.setPushYn(memberVO2.getPushYn());	
                    userInfoVO.setPushDtm(memberVO2.getPushDtm());	
                    userInfoVO.setSnsCd(memberVO2.getSnsCd());	
                    userInfoVO.setAppOs(memberVO2.getAppOs());	
                    userInfoVO.setAppToken(memberVO2.getAppToken());	
                    userInfoVO.setAppTokenAltrDtm(memberVO2.getAppTokenAltrDtm());	
                    userInfoVO.setAppMac(memberVO2.getAppMac());	
                    userInfoVO.setAppBootVer(memberVO2.getAppBootVer());	
                    userInfoVO.setAppBootUrl(memberVO2.getAppBootUrl());	
                    userInfoVO.setAppFirmVer(memberVO2.getAppFirmVer());	
                    userInfoVO.setAppFirmUrl(memberVO2.getAppFirmUrl());	 
                    
                    mResult.put("info", userInfoVO);
    				
    				if(!StringUtil.isEmpty(memberVO.getMbrEml())){
	            		String strMailTitle = "VIRUS CLEAN LAB 회원가입 완료";
	            		String strMailFileName = "memberRegComplete.html";
	            		
	            		Map<String, String> mailInfo = new HashMap<String, String>();
	            		
	            		if("HOME".equals(memberVO.getSnsCd())){
	            			mailInfo.put("$$USER_ID$$", memberVO.getMbrId());
	            		}else{
	            			mailInfo.put("$$USER_ID$$", memberVO.getSnsCd() + " 간편로그인");
	            		}
	            		
	            		mailInfo.put("$$NAME$$", memberVO.getMbrNm());
	            		mailInfo.put("$$REG_DATE$$", DateUtil.getToday());
	            		
	            		//메일 발송
	                    mailSenderUtil.mailSendToUser(memberVO.getMbrEml(), strMailTitle, strMailFileName, mailInfo);
    				}
    				
                    code = "OK";
                    message = "회원가입 성공";
            	}else{
            		throw new IllegalArgumentException();
            	}
            }else{
            	code = "E_WRONG_DUPL";
        		message = "중복가입 입니다.";
            }
                      
        }catch(Exception exception){       
        	code = "E_SERVER";
    		message = "작업중 에러가 발생했습니다.";
            throw processException("exception.error", exception);
        }
        
        mResult.put("code", code);
        mResult.put("message", message);
            
        return mResult;

    }
    
    
    /*
     * 골드회원 등록을 요청한다.
     */
    public Map<String, Object> updateBySpecialMemberStandby(MemberVO memberVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 

        try{  	
        	
        	Map<String,Object> mSession = multiUtil.getSessionInfo();
            
        	if((Boolean)mSession.get("isLogin")){
	            memberVO.setMbrSn((Integer)mSession.get("userSn"));
	            memberVO.setMbrId((String)mSession.get("userId"));
        	}
            
        	MemberVO memberVO2 = memberDAO.selectMember(memberVO);

            /*
             * 작업 성공여부에 따른 처리 
             * 선택 게시물이 없으면 ResourceNotFound페이지로
             */
            if(memberVO2 != null)
        	{
				//사용자 등록
//            	if("Y".equals(memberVO2.getSpCd()))
//            	{
//            		memberVO.setSpCd("R");	//재신청
//            		
//        			Object resultObject = memberDAO.updateBySpecialMemberStandby(memberVO);
//					
//					if(resultObject.getClass() == Integer.class){
//						log.info("-- 사용자 Gold회원 연장 신청 : " + memberVO2.getMbrId() + " / " + memberVO2.getMbrNo() + " ==");
						//메일 발송 없음.
//		        		String strMailTitle = "カン・ジファンジャパンオフィシャルファンクラブ事務局より「GOLD会員入会手続き完了」のお知らせ";
//		        		String strMailFileName = "memberGoldExtensionComplete.txt";
//		        		
//		        		Map<String, String> mailInfo = new HashMap<String, String>();
//		        		mailInfo.put("$$USER_NAME$$", memberVO2.getMbrNm());
//		        		mailInfo.put("$$USER_ID$$", memberVO2.getMbrId());
//		        		mailInfo.put("$$USER_NO$$", memberVO2.getMbrNo());
//		        		mailInfo.put("$$START_DATE$$", memberVO2.getMbrDtm());	//시작일
//		        		mailInfo.put("$$END_DATE$$", memberVO2.getRenewDtm());	//종료일
//		        		
//		        		//메일 발송
//		                mailSenderUtil.mailSendToUser(memberVO2.getMbrEml(), strMailTitle, strMailFileName, mailInfo);
		                
//		        		bStatus = true;
//		        	}else{
//		        		throw new IllegalArgumentException();
//		        	}
//            	}
//            	else
//            	{
//            		memberVO.setSpCd("P");	//최초결제
//            	
//					Object resultObject = memberDAO.updateBySpecialMemberStandby(memberVO);
//					
//					if(resultObject.getClass() == Integer.class){
//		        		String strMailTitle = "カン・ジファンジャパンオフィシャルファンクラブへようこそ";
//		        		String strMailFileName = "memberGoldStep5.txt";
//		        		
//		        		Map<String, String> mailInfo = new HashMap<String, String>();
//		        		mailInfo.put("$$USER_NAME$$", memberVO2.getMbrNm());
//		        		mailInfo.put("$$EMAIL$$", memberVO2.getMbrEml());
//		        		
//		        		//메일 발송
//		                mailSenderUtil.mailSendToUser(memberVO2.getMbrEml(), strMailTitle, strMailFileName, mailInfo);
//		                
//		        		bStatus = true;
//		        	}else{
//		        		throw new IllegalArgumentException();
//		        	}
//            	}
        	}else{
        		throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));
            }
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;

    }
    

    /*
     * 정보를 수정한다.
     */
    public Map<String, Object> updateMember(MemberVO memberVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = true; 
        String statusCode = "OK";
        
        try{    
            Map<String,Object> mSession = multiUtil.getSessionInfo();
            memberVO.setMbrSn((Integer)mSession.get("userSn"));
            memberVO.setMbrId((String)mSession.get("userId"));

            //나이계산
            if(!StringUtil.isEmpty(memberVO.getMbrBday())){
            	memberVO.setMbrAge(DateUtil.isAge(memberVO.getMbrBday()));
            }
            
        	//회원 기본정보 변경
        	if(memberDAO.updateMember(memberVO) == 1){
                //bStatus = true;
            }else{
                throw new IllegalArgumentException();
            }

        	//정보 조회
        	MemberVO memberVO2 = memberDAO.selectMember(memberVO);
        	
        	//SMS/이메일 동의여부
        	if(!memberVO2.getSmsYn().equals(memberVO.getSmsYn())){
        		memberDAO.updateBySmsYn(memberVO);
        	}
        	
        	//본인인증 값 수정
        	if(!memberVO2.getCertMet().isEmpty()){
        		memberDAO.updateByCertInfo(memberVO);
        	}
        	
        	//비밀번호 변경 : 로그아웃.
        	if(!memberVO.getMbrPwEnc().isEmpty())
        	{
            	memberVO.setMbrPw(CryptoUtil.encodeUserPassword64(memberVO.getMbrPwEnc()));
            	memberVO.setTmpPwYn("N");
            	
            	if((memberVO.getMbrPw()).equals(memberVO2.getMbrPw())){
            		//같으면 같다고 에러 메세지
            		statusCode = "PW_SAME";
            	}else{
	            	if(memberDAO.updateByPassword(memberVO) == 1){
	            		HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
	            		httpServletRequest.getSession(false).invalidate();
	            		statusCode = "PW_OK";
	                }else{
	                    throw new IllegalArgumentException();
	                }
            	}
            }
        	
        	//세션정보 갱신
        	if(!"PW_OK".equals(statusCode)) {
                this.updateSession(memberVO2);
            }
        	
        }catch(Exception exception){        
            bStatus = false;
            statusCode = "NG";
            throw processException("exception.error", exception);
        }
        
        mResult.put("code", statusCode);
        mResult.put("status", bStatus);
            
        return mResult;

    }
    
    
    /*
     * 간편가입한 회원의 본인인증 관련 정보 갱신한다.
     */
    public Map<String, Object> updateCertInfo(MemberVO memberVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{    
            MemberVO memberVO2 = memberDAO.selectMember(memberVO);

            if(memberVO2 == null){
            	throw new IllegalArgumentException();                            
            }else{  
            	
            	if(memberDAO.selectMemberCheckDuplicateByDI(memberVO) == 0)
            	{
		            //나이계산
					memberVO.setMbrAge(DateUtil.isAge(memberVO.getMbrBday()));
		            
		        	//회원 기본정보 변경
		        	if(memberDAO.updateMemberCert(memberVO) == 1)
		        	{
		                bStatus = true;
		                
		                //SMS/이메일 동의여부
		            	if(!memberVO2.getSmsYn().equals(memberVO.getSmsYn())){
		            		memberDAO.updateBySmsYn(memberVO);
		            	}
		            
		            	mResult.put("check", "Y");   
		            	
		            	//환영쿠폰조회
	    				CodeEnvVO codeEnvVO = codeDAO.selectCodePreferences();
	    				
	    				try {
		    				//환영쿠톤 발급
		    				if("Y".equals(codeEnvVO.getCouponYn()) && !StringUtil.isEmpty(codeEnvVO.getCouponWelcom()))
		    				{
		    					int iCouponSn = Integer.valueOf(codeEnvVO.getCouponWelcom().split("\\|")[0]);
		    					
		    					CouponVO couponVO = new CouponVO();
		    					couponVO.setMbrSn(memberVO2.getMbrSn());
		    					couponVO.setCupMetaSn(iCouponSn);
		    					
		    					this.getWelcomCoupon(couponVO);
		    				}
	    				}catch(Exception exception) {
	    					//ignore
	    					log.error("== 환영쿠폰 발급 오류 ==");
	    				}
	    				
		                /**
			                //로그인 처리
			                HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
			                httpServletRequest.getSession(false).invalidate();  
			                HttpSession httpSession = httpServletRequest.getSession(true);
			                
			                //세션 생성
			                SessionVO sessionVO = new SessionVO(); 
			                sessionVO.setSn(memberVO.getMbrSn());
			                sessionVO.setId(memberVO2.getMbrId());     
			                sessionVO.setName(memberVO.getMbrNm());
			                sessionVO.setNick(memberVO2.getMbrNick());
			                sessionVO.setTn(memberVO.getMbrTn());
			                sessionVO.setHp(StringUtil.addMinusCharHp(memberVO.getMbrHp()));
			                sessionVO.setSiteCode("VC");
			                sessionVO.setEmail(memberVO.getMbrEml());
			                sessionVO.setGrade(memberVO.getMbrGrade());
			                sessionVO.setZipCd(memberVO.getZipCd());
			                sessionVO.setAddr1(memberVO.getAdrSbc1());
			                sessionVO.setAddr2(memberVO.getAdrSbc2());
			                sessionVO.setIpAddress(httpServletRequest.getRemoteAddr());
			                
			                httpSession.setAttribute("sessionVO", sessionVO);
						*/
		        	}else{
		                throw new IllegalArgumentException();
		            }
            	}else {
            		mResult.put("check", "N");   
            	}
            }
        }catch(Exception exception){        
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;

    }


    /*
     * 회원탈퇴
     *
     * @see artist.idolmaster.service.MemberRegisteService#deleteMemberRegiste(artist.idolmaster.vo.memberVO)
     */
	public Map<String, Object> deleteMember(MemberVO memberVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
            
            if(memberVO.getIsApi()){
            	memberVO.setMbrSn((Integer)mSession.get("userSn"));
            }
            
            MemberVO memberVO2 = memberDAO.selectMember(memberVO);
            
            //TODO :: 탈퇴안내 메일 ??
            if(memberVO.getIsApi())
            {
            	memberVO2.setScssRson("회원탈퇴");
            	
            	if(memberDAO.deleteMemberWithdraw(memberVO2) == 1){
	                bStatus = true;
	                
	                HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
	        		httpServletRequest.getSession(false).invalidate();
	            }else{
	            	mResult.put("message", "회원정보가 잘못 되었습니다.");
	            }
            }
            else
            {
            	memberVO.setScssRson("관리자 회원탈퇴");
	            if(memberDAO.deleteMemberRegiste(memberVO) == 1){
	                bStatus = true;
	            }else{
	                throw new IllegalArgumentException();
	            }
            }
            
            String strMailTitle = "VIRUS CLEAN LAB 탈퇴 신청 완료";
    		String strMailFileName = "memberDelComplete.html";
    		
    		Map<String, String> mailInfo = new HashMap<String, String>();
    		mailInfo.put("$$USER_ID$$", memberVO2.getMbrId());
    		mailInfo.put("$$NAME$$", memberVO2.getMbrNm());
    		mailInfo.put("$$REG_DATE$$", DateUtil.getToday());
    		
    		//메일 발송
            mailSenderUtil.mailSendToUser(memberVO2.getMbrEml(), strMailTitle, strMailFileName, mailInfo);
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;

    }


	/**
	 * ID / 닉네임 / 이메일 중복확인
	 */
	@Override
	public Map<String, Object> selectMemberCheckDuplicate(MemberVO memberVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
        if(memberDAO.selectMemberCheckDuplicate(memberVO) > 0){
        	 mResult.put("status", false);  
             mResult.put("check", "N");   
        }else{  
        	mResult.put("status", true);  
            mResult.put("check", "Y");   
        }
        
        return mResult;
	}


	/*
     * 로그인 처리를 한다.
     * - 비밀번호 5회 오류시 휴면처리
     * - 최종로그인 45일 이상시 휴면처리
     * - 비밀번호 등록일 45일 이상시 비밀번호 변경해야함. -> 로그인다시 해야함.
     * - 임시비밀번호로 로그인시 비밀번호 변경해야함. -> 로그인다시 해야함.
     * @see ket.auth.service.AuthService#selectAuth(ket.auth.vo.LoginVO)
     */
	@Override
	public Map<String, Object> selectAuth(LoginVO loginVO) throws Exception {
		 Map<String, Object> mResult = new HashMap<String, Object>();
	        
	        Map<String,Object> mSession = multiUtil.getSessionInfo();
	        
	        String code = "";
	        String message = "";
	        String firstMenuUrl = "";
	        
	        try{
	            /**
	             * 1.사용자 정보 조회
	             * - BASE64복호화후 SHA512로 암호화
	             */
	        	
	        	if(!"HOME".equals(loginVO.getSnsCd())){
        			loginVO.setMbrPw(CryptoUtil.encodeUserPassword(loginVO.getMbrId()));
	        	}else{
	        		loginVO.setMbrPw(CryptoUtil.encodeUserPassword64(loginVO.getMbrPwEnc()));
	        	}
	            
	            //사용자 정보
	            MemberVO memberVO2 = memberDAO.selectAuth(loginVO);
	    
	            /**
	             * 2.사용자 정보가 존재하면
	             * - 휴면계정이 아니여야한다.
	             * - 비밀번호 일치 확인
	             * - 최종로그인 일자 확인 -> 90일 이상시 휴면처리
	             * - 비밀번호 5회 오류여부 확인 
	             * - 비밀번호 변경 기일 확인
	             * - 임시비밀번호 사용여부 확인
	             */
	            if(memberVO2 != null){
	                
	                /*
	                 * 휴면계정여부 확인 
	                 */
	                if("Y".equals(memberVO2.getStCd())){
	                    
	                    if(log.isDebugEnabled()){
	                        log.debug("휴면계정 아님");
	                    }
	                    
	                    /*
	                     * 비밀번호 일치 확인
	                     */
	                    log.debug("=== getMbrPwEnc : " + loginVO.getMbrPwEnc());
	                    log.debug("=== getMbrPwEnc : " + new String(CryptoUtil.getBASE64Decoder(loginVO.getMbrPwEnc())) );
	                    log.debug("=== loginVO : " + loginVO.getMbrPw());
	                    log.debug("=== memberVO2 : " + memberVO2.getMbrPw());
	                    
	                    
	                    if((loginVO.getMbrPw()).equals(memberVO2.getMbrPw()))
	                    {
	                        
	                        if(log.isDebugEnabled()){ 
	                            log.debug("비밀번호 일치"); 
	                        }
	                        
                            if("WEB".equals(loginVO.getAppOs()) && "N".equals(memberVO2.getCertYn()))
                            { 
                            	//본인확인 필요
	                            HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
			                    httpServletRequest.getSession(false).invalidate();  
			                    HttpSession httpSession = httpServletRequest.getSession(true);
			                    httpSession.setAttribute("certReg", memberVO2);       
                            	
			                    code = "E_WRONG_CERT";
	                            message = "본인인증 필요합니다.";
	                            
	                            if(log.isDebugEnabled()){
	    	                        log.error("본인인증 필요합니다.");
	    	                    }
                            }
                            else
                            {
                            	if(log.isDebugEnabled()){
                                    log.debug("로그인 성공");
                                }
                                    
                                code = "OK";
                                
                                //로그인 성공
                                mResult.put("returnUrl", ""+ loginVO.getReturnUrl().replaceAll("&amp;", "&").replaceAll("amp;", ""));
                                    

                                /**
                                 * APP에서 로그인시 처리
                                 */
                                if(!"WEB".equals(loginVO.getAppOs())){
                                	//팝업목록
                                	BoardVO boardVO = new BoardVO();
                                	boardVO.setIsApi(true);
                                	boardVO.setLgrpCd("POPUP");
                                	boardVO.setMgrpCd("APP");
                                	mResult.put("popup", boardService.selectApiBoardList(boardVO));
                                	
                                	//펌웨어목록
                                	BoardVO boardVO3 = new BoardVO();
                                	boardVO3.setIsApi(true);
                                	boardVO3.setRowLimit(1);
                                	boardVO3.setLgrpCd("APP");
                                	boardVO3.setMgrpCd("FIRMWARE");
                                	Map<String, Object> firmware = boardService.selectBoardList(boardVO3);
                                	
                                	if(firmware.containsKey("list")){
                                		@SuppressWarnings("unchecked")
    									BoardVO tempVO = ((List<BoardVO>)firmware.get("list")).get(0);
                                		memberVO2.setAppFirmVer(tempVO.getBlcTitl());
                                		memberVO2.setAppFirmUrl(siteUserDomain + StringUtil.printDownloadLink(tempVO.getFileSn()));
                                	}
                                	
                                	//부트로더목록
                                	boardVO3.setMgrpCd("BOOT");
                                	Map<String, Object> boot = boardService.selectBoardList(boardVO3);
                                	
                                	if(boot.containsKey("list")){
                                		@SuppressWarnings("unchecked")
    									BoardVO tempVO = ((List<BoardVO>)boot.get("list")).get(0);
                                		memberVO2.setAppBootVer(tempVO.getBlcTitl());
                                		memberVO2.setAppBootUrl(siteUserDomain + StringUtil.printDownloadLink(tempVO.getFileSn()));
                                	}
                	        	}
                                
	                            //비번삭제
	                            memberVO2.setMbrPw("");
	                            
	                            MemberApiVO userInfoVO = new MemberApiVO();
	                            userInfoVO.setMbrSn(memberVO2.getMbrSn());	
	                            userInfoVO.setMbrId(memberVO2.getMbrId());	
	                            userInfoVO.setMbrNick(memberVO2.getMbrNick());	
	                            userInfoVO.setMbrGrade(memberVO2.getMbrGrade());	
	                            userInfoVO.setMbrNm(memberVO2.getMbrNm());	
	                            userInfoVO.setMbrTn(memberVO2.getMbrTn());	
	                            userInfoVO.setMbrHp(memberVO2.getMbrHp());	
	                            userInfoVO.setMbrEml(memberVO2.getMbrEml());	
	                            userInfoVO.setGenderCd(memberVO2.getGenderCd());	
	                            userInfoVO.setPoint(memberVO2.getPoint());	
	                            userInfoVO.setMbrBday(memberVO2.getMbrBday());	
	                            userInfoVO.setMbrAge(memberVO2.getMbrAge());	
	                            userInfoVO.setZipCd(memberVO2.getZipCd());	
	                            userInfoVO.setAdrSbc1(memberVO2.getAdrSbc1());	
	                            userInfoVO.setAdrSbc2(memberVO2.getAdrSbc2());	
	                            userInfoVO.setBbsYn(memberVO2.getBbsYn());	
	                            userInfoVO.setSmsYn(memberVO2.getSmsYn());	
	                            userInfoVO.setSmsDtm(memberVO2.getSmsDtm());	
	                            userInfoVO.setEmlYn(memberVO2.getEmlYn());	
	                            userInfoVO.setEmlDtm(memberVO2.getEmlDtm());	
	                            userInfoVO.setPushYn(memberVO2.getPushYn());	
	                            userInfoVO.setPushDtm(memberVO2.getPushDtm());	
	                            userInfoVO.setSnsCd(memberVO2.getSnsCd());	
	                            userInfoVO.setAppOs(memberVO2.getAppOs());	
	                            userInfoVO.setAppToken(memberVO2.getAppToken());	
	                            userInfoVO.setAppTokenAltrDtm(memberVO2.getAppTokenAltrDtm());	
	                            userInfoVO.setAppMac(memberVO2.getAppMac());	
	                            userInfoVO.setAppBootVer(memberVO2.getAppBootVer());	
	                            userInfoVO.setAppBootUrl(memberVO2.getAppBootUrl());	
	                            userInfoVO.setAppFirmVer(memberVO2.getAppFirmVer());	
	                            userInfoVO.setAppFirmUrl(memberVO2.getAppFirmUrl());	 
	                            
	                            mResult.put("info", userInfoVO);
	                            firstMenuUrl = this.createSession(memberVO2, mSession, code);
                            }
	                    }
	                    else
	                    {
	                        /*
	                         * 비밀번호 오류 처리
	                         * - 비밀번호 오류 횟수 증가
	                         * - 5회 이상시 휴면처리
	                         */
	                    	memberVO2.setPwErrOft(memberVO2.getPwErrOft() + 1);
	                        
	                        if(memberVO2.getPwErrOft() >= LOCK_ID_PW_ERROR_COUNT){
	                        	memberVO2.setStCd("N");
	                        	code = "E_WRONG_LOCK";
	                            message = "입력하신 비밀번호 입력 허용횟수 " + Integer.toString(LOCK_ID_PW_ERROR_COUNT) + "회를 초과하였습니다. 비밀번호 찾기 페이지로 이동합니다.";
	                        }else{
	                            //비밀번호 오류
	                            code = "E_WRONG_PW";
	                            message = Integer.toString(memberVO2.getPwErrOft()) + "회 비밀번호 입력 오류입니다.\n" + Integer.toString(LOCK_ID_PW_ERROR_COUNT) +"회 이상 비밀번호 입력 오류 시 로그인이 불가하오니 유의하시기 바랍니다."; //xMessageSource.getMessage("{0}회 비밀번호 입력 오류입니다.\n{1}회 이상 비밀번호 입력 오류 시 로그인이 불가하오니 유의하시기 바랍니다.", new String[]{Integer.toString(memberVO2.getPwErrOft()), Integer.toString(LOCK_ID_PW_ERROR_COUNT)});
	                        }
	                        
	                        if(log.isDebugEnabled()){
	                            log.debug(message);
	                        }
	                        
	                        //비밀번호 오류횟수 증가 및 휴먼처리 
	                        this.updateByStCd(memberVO2, mSession);
	                    }
	                    
	                }else{
	                    //휴면계정 메세지 출력
	                	if(memberVO2.getPwErrOft() >= LOCK_ID_PW_ERROR_COUNT){
	                		//비밀번호 오류
	                		code = "E_WRONG_LOCK";
	                		message = "입력하신 비밀번호 입력 허용횟수 " + Integer.toString(LOCK_ID_PW_ERROR_COUNT) + "회를 초과하였습니다. 비밀번호 찾기 페이지로 이동합니다.";
	                	}else {
	                		//관리자 설정
	                		code = "E_WRONG_ADMIN_LOCK";
	                		message = "사용이 제한 되었습니다. 고객센터로 문의하시기 바랍니다.";
	                	}
	                    
	                    if(log.isDebugEnabled()){
	                        log.error("휴면계정 : " + loginVO.getMbrId());
	                    }
	                }
	                
	            }else{   
	            	
	            	code = "E_WRONG_ID";
	                message = "아이디와 비밀번호 정보가 올바르지 않습니다." ;
	                
	                /**
	                 * SNS로그인시 회원정보가 없으면.
	                 * - WEB에서는 가입페이지로 연결
	                 * - APP에서는 가입을 시켜서 로그인 정보를 전달
	                 * - 추후에 로그인지 나머지 정보를 입력받는다. : snsCd가 HOME이 아니고. appOs가 ANDROID이거나 IOS이면서. CERT_YN이 N인것들.
	                 *   => 복잡하게 하지말고. CERT_YN이 N이면 모두 입력페이지로 보낼것.
	                 */
	                if(!"HOME".equals(loginVO.getSnsCd()))
	                {
		                if("WEB".equals(loginVO.getAppOs()))
		                {
		                	HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
		                    httpServletRequest.getSession(false).invalidate();  
		                    HttpSession httpSession = httpServletRequest.getSession(true);
		                    
		                    httpSession.setAttribute("snsCd", loginVO.getSnsCd());       
		                	httpSession.setAttribute("snsMbrId", loginVO.getMbrId()); 
		                	httpSession.setAttribute("snsMbrEml", loginVO.getMbrEml()); 
		                    httpSession.setAttribute("snsLogin", "Y");
		                    
		                    code = "E_WRONG_ID";
			                message = "아이디와 비밀번호 정보가 올바르지 않습니다." ; //xMessageSource.getMessage("아이디와 비밀번호 정보가 올바르지 않습니다.");
		                }
		                else
		                {
		                	code = "E_WRONG_ID";
			                message = "회원정보가 없습니다." ; //xMessageSource.getMessage("아이디와 비밀번호 정보가 올바르지 않습니다.");
			                
            	/**
			                memberVO2 = new MemberVO();
		                	memberVO2.setMbrId(loginVO.getMbrId());
		                	memberVO2.setMbrEml(loginVO.getMbrEml());
		                	memberVO2.setSnsCd(loginVO.getSnsCd());
		                	memberVO2.setAppOs(loginVO.getAppOs());
		                	memberVO2.setCertYn("N");
		                	
		                	//닉네임 설정하기.
		                	memberVO2.setMbrNick(loginVO.getSnsCd() + "_" + loginVO.getMbrId());
		                	
		                	Map<String, Object> insertResult = this.insertAppMember(memberVO2);
		                	
		                	if("OK".equals(insertResult.get("code"))) {
		                		//로그인 처리
		                		
                            	//팝업목록
                            	BoardVO boardVO = new BoardVO();
                            	boardVO.setIsApi(true);
                            	boardVO.setLgrpCd("POPUP");
                            	boardVO.setMgrpCd("APP");
                            	mResult.put("popup", boardService.selectApiBoardList(boardVO));
                            	
                            	//펌웨어목록
                            	BoardVO boardVO3 = new BoardVO();
                            	boardVO3.setIsApi(true);
                            	boardVO3.setRowLimit(1);
                            	boardVO3.setLgrpCd("APP");
                            	boardVO3.setMgrpCd("FIRMWARE");
                            	Map<String, Object> firmware = boardService.selectBoardList(boardVO3);
                            	
                            	if(firmware.containsKey("list")){
                            		@SuppressWarnings("unchecked")
									BoardVO tempVO = ((List<BoardVO>)firmware.get("list")).get(0);
                            		memberVO2.setAppFirmVer(tempVO.getBlcTitl());
                            		memberVO2.setAppFirmUrl(siteUserDomain + StringUtil.printDownloadLink(tempVO.getFileSn()));
                            	}
                            	
                            	//부트로더목록
                            	boardVO3.setMgrpCd("BOOT");
                            	Map<String, Object> boot = boardService.selectBoardList(boardVO3);
                            	
                            	if(boot.containsKey("list")){
                            		@SuppressWarnings("unchecked")
									BoardVO tempVO = ((List<BoardVO>)boot.get("list")).get(0);
                            		memberVO2.setAppBootVer(tempVO.getBlcTitl());
                            		memberVO2.setAppBootUrl(siteUserDomain + StringUtil.printDownloadLink(tempVO.getFileSn()));
                            	}
	                            	
                            	//API 리턴 정보
	                            MemberApiVO userInfoVO = new MemberApiVO();
	                            userInfoVO.setMbrSn(memberVO2.getMbrSn());	
	                            userInfoVO.setMbrId(memberVO2.getMbrId());	
	                            userInfoVO.setMbrNick(memberVO2.getMbrNick());	
	                            userInfoVO.setMbrEml(memberVO2.getMbrEml());	
	                            userInfoVO.setSnsCd(memberVO2.getSnsCd());	
	                            userInfoVO.setAppOs(memberVO2.getAppOs());	
	                            userInfoVO.setAppBootVer(memberVO2.getAppBootVer());	
	                            userInfoVO.setAppBootUrl(memberVO2.getAppBootUrl());	
	                            userInfoVO.setAppFirmVer(memberVO2.getAppFirmVer());	
	                            userInfoVO.setAppFirmUrl(memberVO2.getAppFirmUrl());	 
	                            
	                            mResult.put("info", userInfoVO);
		                	}
		                	
		                	code = (String)insertResult.get("code");
                            message = (String)insertResult.get("message");
             **/
		                }
	                }
	                
	                if(log.isDebugEnabled()){
	                    log.debug(message);
	                }
	            }
	        }catch(Exception exception){
	        	mResult.put("result", "E_SERVER");
		        mResult.put("message", "작업중 에러가 발행했습니다.");
	            throw processException("exception.process", exception);
	        }
	        
	        mResult.put("code", code);
	        mResult.put("message", message);
	        mResult.put("status", "OK".equals(code) ? true : false);
	        mResult.put("firstMenuUrl", firstMenuUrl);
	                        
	        return mResult;
	}
	
	/**
     * 계정 상태값 변경 및 로그인 로그 기록
     *
     * @param authVO2
     * @param mSession
     * @throws Exception 
     */
    private void updateByStCd(MemberVO memberVO, Map<String, Object> mSession) throws Exception {
        
        //상태값 변경
        memberDAO.updateByStCd(memberVO);
        
        //로그 기록
        //accessLogService.insertAccessLog(authVO, mSession);
    }


    /**
     * 세션생성
     *
     * @throws Exception
     */
    private void updateSession(MemberVO authVO) throws Exception {
        HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
        
        if(httpServletRequest.getSession().getAttribute("sessionVO") != null){
        	SessionVO sessionVO = (SessionVO)httpServletRequest.getSession().getAttribute("sessionVO");
            sessionVO.setName(authVO.getMbrNm());
            sessionVO.setNick(authVO.getMbrNick());
            sessionVO.setTn(authVO.getMbrTn());
            sessionVO.setHp(StringUtil.addMinusCharHp(authVO.getMbrHp()));
            sessionVO.setEmail(authVO.getMbrEml());
            sessionVO.setGrade(authVO.getMbrGrade());
            sessionVO.setZipCd(authVO.getZipCd());
            sessionVO.setAddr1(authVO.getAdrSbc1());
            sessionVO.setAddr2(authVO.getAdrSbc2());
	        
	        httpServletRequest.getSession().setAttribute("sessionVO", sessionVO);
        }
    }
    
    /**
     * 세션생성
     *
     * @throws Exception
     */
    private String createSession(MemberVO authVO, Map<String, Object> mSession, String code) throws Exception {
        
        String strFirstMenuUrl = "/index.vc";
        
        HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
        httpServletRequest.getSession(false).invalidate();  
        HttpSession httpSession = httpServletRequest.getSession(true);
        
        //오류횟수 초기화, 마지막접속일 업데이트
        authVO.setStCd("Y");
        authVO.setPwErrOft(0);
        this.updateByStCd(authVO, mSession);
        
        if("OK".equals(code)){
            //세션 생성
            SessionVO sessionVO = new SessionVO(); 
            sessionVO.setSn(authVO.getMbrSn());
            sessionVO.setId(authVO.getMbrId());     
            sessionVO.setNo(authVO.getMbrNo());   
            sessionVO.setName(authVO.getMbrNm());
            sessionVO.setNick(authVO.getMbrNick());
            sessionVO.setTn(authVO.getMbrTn());
            sessionVO.setHp(StringUtil.addMinusCharHp(authVO.getMbrHp()));
            sessionVO.setSiteCode("VC");
            sessionVO.setEmail(authVO.getMbrEml());
            sessionVO.setGrade(authVO.getMbrGrade());
            sessionVO.setZipCd(authVO.getZipCd());
            sessionVO.setAddr1(authVO.getAdrSbc1());
            sessionVO.setAddr2(authVO.getAdrSbc2());
            sessionVO.setIpAddress(httpServletRequest.getRemoteAddr());
            sessionVO.setFirstMenuUrl(strFirstMenuUrl);
            
            httpSession.setAttribute("sessionVO", sessionVO);
            
            //세션 유지시간 180분 설정
            httpSession.setMaxInactiveInterval(180 * 60);
            
            log.debug("로그인 세션 생성 완료");
            
        }else{
            //임시 세션 생성
            httpSession.setAttribute("tmpMgrId", authVO.getMbrId());       
            httpSession.setAttribute("tmpMgrPw", authVO.getMbrPw());
            httpSession.setAttribute("tmpMgrNm", authVO.getMbrNm());
            httpSession.setAttribute("tmpLogin", "Y");
        }
        
        return strFirstMenuUrl;
    }


    /**
     *  임시비밀번호 변경
     */
	@Override
	public Map<String, Object> updateNewPassword(LoginVO loginVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false;
        
        String code = "";
        String message = "";
        
        try{
            HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
            HttpSession httpSession = httpServletRequest.getSession(false);
            
            //임시 발급 새션인지 확인한다.
            if(httpSession.getAttribute("tmpLogin") != null
                    && "Y".equals(httpSession.getAttribute("tmpLogin")))
            {
                
                @SuppressWarnings("unused")
				Map<String,Object> mSession = multiUtil.getSessionInfo();

                loginVO.setMbrId((String)httpSession.getAttribute("tmpMgrId"));
                loginVO.setMbrPw((String)httpSession.getAttribute("tmpMgrPw"));
                loginVO.setMbrNewPw(new String(CryptoUtil.encodeUserPassword64(loginVO.getMbrNewPwEnc())));
                
                //사용자 정보 조회
                MemberVO authVO2 = memberDAO.selectAuth(loginVO);
                
                if(authVO2 != null){
                
                    //입력 비밀번호 확인
                    if(loginVO.getMbrPw().equals(authVO2.getMbrPw())){
                      
                        //새로운 비밀번호 패턴 확인
                        String regex = "^.*(?=.{4,12})(?=.*[a-zA-Z])(?=.*[0-9]).*$";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(new String(CryptoUtil.getBASE64Decoder(loginVO.getMbrNewPwEnc())));
                      
                        if(matcher.matches()){
                        
                            //비밀번호 변경
                            authVO2.setTmpPwYn("N");
                            authVO2.setMbrPw(loginVO.getMbrNewPw());
                            
                            if(memberDAO.updateByPassword(authVO2) == 1){
                                //비밀번호변경로그 기록
                                //PasswordLogVO passwordLogVO = new PasswordLogVO();
                               // passwordLogVO.setMgrId(authVO2.getMgrId());
                               // passwordLogVO.setMgrPw(authVO2.getMgrPw());
                               // passwordLogVO.setMdfyId(authVO2.getMgrId());
                               // passwordLogService.insertPasswordLog(passwordLogVO);
                                
                                httpServletRequest.getSession(false).invalidate();
                                
                                bStatus = true;
                            }else{
                                throw new IllegalArgumentException();
                            }
                        }else{
                            code = "A09";
                            message = "비밀번호는 영문 대/소문자, 숫자를 조합하여 최소 12자리 이상 입력해 주세요."; //xMessageSource.getMessage("msg.A09");   //비밀번호는 영문 대/소문자, 숫자를 조합하여 최소 12자리 이상 입력해 주세요.
                            throw new LeaveaTraceException("msg.A09");
                        }
                    }else{
                        code = "A10";
                        message = "비밀번호가 일치하지 않습니다.\n다시 입력해 주세요."; //xMessageSource.getMessage("msg.A10");     //비밀번호가 일치하지 않습니다.\n다시 입력해 주세요.
                        throw new LeaveaTraceException("msg.A10");
                    }
                }else{
                    code = "A12";
                    message = "입력한 정보가 일치하지 않습니다.\n올바른 정보를 입력해 주세요."; //xMessageSource.getMessage("msg.A12");         //입력한 정보가 일치하지 않습니다.\n올바른 정보를 입력해 주세요.
                    throw new LeaveaTraceException("msg.A12");
                }
                
            }else{
                throw new IllegalArgumentException("exception.binding");
            }
            
        }catch(LeaveaTraceException leaveaTraceException){
            leaveaTrace(leaveaTraceException.getMessage());
        }catch(Exception exception){
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("code", code);
        mResult.put("errMsg", message);
        mResult.put("status", bStatus);
            
        return mResult;
	}
	
	
	
	/*
     * 개인정보 보호용 로그인 확인
     */
	@Override
	public Map<String, Object> selectPrivacyAuth(LoginVO loginVO) throws Exception {
		 Map<String, Object> mResult = new HashMap<String, Object>();
	        
	        Map<String,Object> mSession = multiUtil.getSessionInfo();
	        
	        String code = "";
	        String message = "";
	        
	        try{
	        	
	        	//로그인아이디와 같은지 확인한다.
	        	if(loginVO.getMbrId().equals(mSession.get("userId"))){
		            /**
		             * 1.사용자 정보 조회
		             * - BASE64복호화후 SHA512로 암호화
		             */
		            loginVO.setMbrPw(CryptoUtil.encodeUserPassword64(loginVO.getMbrPwEnc()));
		            
		            //사용자 정보
		            MemberVO memberVO2 = memberDAO.selectAuth(loginVO);
		    
		            /**
		             * 2.사용자 정보가 존재하면
		             * - 휴면계정이 아니여야한다.
		             * - 비밀번호 일치 확인
		             * - 최종로그인 일자 확인 -> 90일 이상시 휴면처리
		             * - 비밀번호 5회 오류여부 확인 
		             * - 비밀번호 변경 기일 확인
		             * - 임시비밀번호 사용여부 확인
		             */
		            if(memberVO2 != null){
		                
		                /*
		                 * 휴면계정여부 확인 
		                 */
		                if("Y".equals(memberVO2.getStCd())){
		                    /*
		                     * 비밀번호 일치 확인
		                     */
		                    if((loginVO.getMbrPw()).equals(memberVO2.getMbrPw())){
		                        
	                            code = "LOGIN";
	                            
	                            HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
	                            HttpSession httpSession = httpServletRequest.getSession(false);
	                            httpSession.setAttribute("privacy", "Y");
	                        
	                            //로그인 성공
	                            mResult.put("returnUrl", ""+ loginVO.getReturnUrl().replaceAll("&amp;", "&").replaceAll("amp;", ""));
	                                
		                    }else{
		                        /*
		                         * 비밀번호 오류 처리
		                         * - 비밀번호 오류 횟수 증가
		                         * - 5회 이상시 휴면처리
		                         */
		                    	memberVO2.setPwErrOft(memberVO2.getPwErrOft() + 1);
		                        
		                        code = "A07";
		                        message = "IDとパスワードが間違っています。"; //xMessageSource.getMessage("{0}회 비밀번호 입력 오류입니다.\n{1}회 이상 비밀번호 입력 오류 시 로그인이 불가하오니 유의하시기 바랍니다.", new String[]{Integer.toString(memberVO2.getPwErrOft()), Integer.toString(LOCK_ID_PW_ERROR_COUNT)});
		                        
		                        if(log.isDebugEnabled()){
		                            log.debug(message);
		                        }
		                        
		                        //비밀번호 오류횟수 증가 및 휴먼처리 
		                        this.updateByStCd(memberVO2, mSession);
		                    }
		                    
		                }else{
		                    //휴면계정 메세지 출력
		                    code = "A16";
		                    message = "休眠アカウントにログインしようとしました。\\n担当者の電子メールでお問い合わせください。"; //xMessageSource.getMessage("휴면 계정으로 로그인을 시도했습니다.\n담당자 이메일로 문의해 주십시오.");
		                    
		                    if(log.isDebugEnabled()){
		                        log.debug(message);
		                    }
		                }
		                
		            }else{             
		                code = "A05";
		                message = "IDとパスワードが間違っています。" ; //xMessageSource.getMessage("아이디와 비밀번호 정보가 올바르지 않습니다.");
		                
		                if(log.isDebugEnabled()){
		                    log.debug(message);
		                }
		            }
	        	}else{
	        		code = "A05";
	                message = "IDとパスワードが間違っています。" ; //xMessageSource.getMessage("아이디와 비밀번호 정보가 올바르지 않습니다.");
	                
	                if(log.isDebugEnabled()){
	                    log.debug(message);
	                }
	        	}
	        }catch(Exception exception){
	            throw processException("exception.process", exception);
	        }
	        
	        mResult.put("code", code);
	        mResult.put("message", message);
	        mResult.put("searchInfo", loginVO);
	        mResult.put("status", "LOGIN".equals(code) ? true : false);
	                        
	        return mResult;
	}
	
	/*
	 * 디바이스 정보 수정한다.
	 */
	@Override
	public Map<String, Object> updateMemberDeviceInfo(MemberVO memberVO) throws Exception {
	        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        String code = "OK";
        String message = "성공";
        
        try{
			if(memberVO.getMbrSn() > 0 && !memberVO.getAppOs().isEmpty() ){
				memberDAO.updateMemberDeviceInfo(memberVO);
			}else{
				code = "E_AUTH";   
				message = "인증오류";
			}
			
        }catch(Exception exception){
        	code = "E_UNKNOWN";   
        	message = "알려지지 않은 오류"; 
        }

        mResult.put("code", code);   
        mResult.put("message", message); 
            
        return mResult;
	}
	
	/**
	 * 환영쿠폰 발급
	 */
	protected boolean getWelcomCoupon(CouponVO couponVO) throws Exception {
        
        boolean bStatus = false; 
        
        try{
        	//입력한 쿠폰번호가 있는지 확인해야함.
        	CouponMetaVO couponMetaVO = new CouponMetaVO();
        	couponMetaVO.setCupMetaSn(couponVO.getCupMetaSn());
        	
        	CouponMetaVO couponMetaVO2 = couponDAO.selectCouponMeta(couponMetaVO);
        	
        	if(couponMetaVO2 != null){
        		
        		couponVO.setLgrpCd(couponMetaVO2.getLgrpCd());
        		couponVO.setMgrpCd(couponMetaVO2.getMgrpCd());
        		couponVO.setSgrpCd(couponMetaVO2.getSgrpCd());
        		couponVO.setCupTitl(couponMetaVO2.getCupTitl());
        		couponVO.setCupSbc(couponMetaVO2.getCupSbc());
        		couponVO.setPrice(couponMetaVO2.getPrice());
        		couponVO.setCupNo(RandomStringUtils.randomAlphanumeric(10) + couponMetaVO2.getCupMetaSn());
        		
        		if(couponMetaVO2.getExpsDay() == 0){
        			couponVO.setExpsFnhDtm(couponMetaVO2.getExpsFnhDtm());
        		}else{
        			couponVO.setExpsFnhDtm(DateUtil.addDay(DateUtil.getToday(), couponMetaVO2.getExpsDay()));
        		}
        		
	    		Object resultObject = couponDAO.insertCouponUser(couponVO);
	            
	            if(resultObject.getClass() == Integer.class){
	                bStatus = true;
	            }
        	}else{
    			throw new IllegalArgumentException();
    		}
        	
        }catch(Exception exception){
            throw processException("exception.error", exception);
        }
        
        return bStatus;
    }
}
