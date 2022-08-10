package vc.virusclean.admin.auth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import vc.virusclean.admin.auth.service.AuthService;
import vc.virusclean.admin.auth.service.dao.AuthDAO;
import vc.virusclean.admin.auth.vo.AuthVO;
import vc.virusclean.admin.auth.vo.LoginVO;
import vc.virusclean.cmm.service.AccessLogService;
import vc.virusclean.cmm.service.PasswordLogService;
import vc.virusclean.cmm.vo.PasswordLogVO;
import jksoft.com.exception.LeaveaTraceException;
import jksoft.com.service.XAbstractService;
import jksoft.com.util.CryptoUtil;
import jksoft.com.util.DateUtil;
import jksoft.com.web.vo.SessionVO;

/**
 * <pre>
 * 로그인 / 비밀번호 변경 / 세션처리
 * </pre>
 * 
 * @ClassName   : AuthServiceImpl.java
 * @Description : AuthService 를 구현
 * @author Jeong.Hyoung.Jae 
 * @since 2017.12.25
 * @version 1.0
 * @see vc.virusclean.admin.auth.service.impl.AuthServiceImpl
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017.12.25      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */
@Service("authService")
public class AuthServiceImpl extends XAbstractService implements AuthService {

    /**
     * AuthDAO class 선언 (AuthDAO Class Injection)
     * (AuthDAO)authDAO
     */   
    @Resource(name="authDAO")   
    private AuthDAO authDAO;

    /**
     * PasswordLogService class 선언 ( PasswordLogService Class Injection) 
     */
    @Resource(name="passwordLogService")
    private PasswordLogService passwordLogService;
    
    @Resource(name = "accessLogService")
    private AccessLogService accessLogService;
    

    /**
     * 휴면처리 가능한 비로그인 일자.
     */
    @Value(value="#{global['login.lock.dateDiff']}")
    protected int LOCK_ID_DATE_DIFF;
    
    /**
     * 휴면처리 가능한 비밀번호 오류 횟수
     */
    @Value(value="#{global['login.lock.errorCount']}")
    protected int LOCK_ID_PW_ERROR_COUNT;
    
    /**
     * 비밀번호 변경 기일.
     */
    @Value(value="#{global['login.password.dateDiff']}")
    protected int PW_MOD_DATE_DIFF;

    
    
    /*
     * 로그인 처리를 한다.
     * - 비밀번호 5회 오류시 휴면처리
     * - 최종로그인 45일 이상시 휴면처리
     * - 비밀번호 등록일 45일 이상시 비밀번호 변경해야함. -> 로그인다시 해야함.
     * - 임시비밀번호로 로그인시 비밀번호 변경해야함. -> 로그인다시 해야함.
     * @see ket.auth.service.AuthService#selectAuth(ket.auth.vo.LoginVO)
     */
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
            loginVO.setMgrPw(CryptoUtil.encodeUserPassword64(loginVO.getMgrPwEnc()));
            
            //사용자 정보
            AuthVO authVO2 = authDAO.selectAuth(loginVO);
    
            /**
             * 2.사용자 정보가 존재하면
             * - 휴면계정이 아니여야한다.
             * - 비밀번호 일치 확인
             * - 최종로그인 일자 확인 -> 90일 이상시 휴면처리
             * - 비밀번호 5회 오류여부 확인 
             * - 비밀번호 변경 기일 확인
             * - 임시비밀번호 사용여부 확인
             */
            if(authVO2 != null){
                
                /*
                 * 휴면계정여부 확인 
                 */
                if("Y".equals(authVO2.getStCd())){
                    
                    if(log.isDebugEnabled()){
                        log.debug("휴면계정 아님");
                    }
                    
                    /*
                     * 비밀번호 일치 확인
                     */
                    if((loginVO.getMgrPw()).equals(authVO2.getMgrPw())){
                        
                        if(log.isDebugEnabled()){ 
                            log.debug("비밀번호 일치"); 
                        }
                        
                        /*
                         * 최종 로그인 일자 확인
                         */
                        log.error("DateUtil.dateCompare : " +  authVO2.getFinCnncDtm() + " : " + LOCK_ID_DATE_DIFF);
                        if(DateUtil.dateCompare(authVO2.getFinCnncDtm(), LOCK_ID_DATE_DIFF)){
                            
                            if(log.isDebugEnabled()){ 
                                log.debug("최종 로그인 일자 일치"); 
                            }
                            
                            /*
                             * 비밀번호 변경 기일  & 임시비밀번호
                             */
                            if((PW_MOD_DATE_DIFF == 0 || DateUtil.dateCompare(authVO2.getFinPwAltrDtm(), PW_MOD_DATE_DIFF))
                                    && "N".equals(authVO2.getTmpPwYn())){ 
                                
                                if(log.isDebugEnabled()){
                                    log.debug("로그인 성공");
                                }
                                
                                code = "LOGIN";
                                
                                
                                //로그인 성공
                                mResult.put("returnUrl", ""+ loginVO.getReturnUrl().replaceAll("&amp;", "&").replaceAll("amp;", ""));
                                
                            }else{
                                
                                /* 비밀번호 변경해야함.
                                 * 로그인 페이지에서 비밀번호 변경 레이어 출력
                                 * 임시 로그인 상태여야함. 
                                 */
                                if(!DateUtil.dateCompare(authVO2.getFinPwAltrDtm(), PW_MOD_DATE_DIFF)){
                                    code = "A04";
                                    message = xMessageSource.getMessage("msg.A04", new String[]{Integer.toString(PW_MOD_DATE_DIFF)});
                                }
                                     
                                //임시비밀번호
                                if("Y".equals(authVO2.getTmpPwYn())){
                                    code = "A77";
                                    message = xMessageSource.getMessage("msg.A77");
                                }
                                
                                if(log.isDebugEnabled()){
                                    log.debug(message);
                                }
                            }
                            
                            //세션 생성
//                            mResult.put("sysCd", authVO2.getSysCd());
                            //로그인 성공시 가야하는 페이지
                            firstMenuUrl = this.createSession(authVO2, mSession, code);
                            
                        }else{
                            //LOCK_ID_DATE_DIFF일 이상 비로그인으로  휴면처리
                            authVO2.setStCd("N");
                            code = "A03";
                            message = xMessageSource.getMessage("msg.A03", new String[]{Integer.toString(LOCK_ID_DATE_DIFF)});
                            
                            if(log.isDebugEnabled()){
                                log.debug(message);
                            }
                            
                            this.updateByStCd(authVO2, mSession);
                        }
                        
                    }else{
                        /*
                         * 비밀번호 오류 처리
                         * - 비밀번호 오류 횟수 증가
                         * - 5회 이상시 휴면처리
                         */
                        authVO2.setPwErrOft(authVO2.getPwErrOft() + 1);
                        
                        if(authVO2.getPwErrOft() >= LOCK_ID_PW_ERROR_COUNT){
                            //휴면처리
                            authVO2.setStCd("N");
                            code = "A06";
                            message = xMessageSource.getMessage("msg.A06", new String[]{Integer.toString(LOCK_ID_PW_ERROR_COUNT)});
                        }else{
                            //비밀번호 오류
                            code = "A07";
                            message = xMessageSource.getMessage("msg.A07", new String[]{Integer.toString(authVO2.getPwErrOft()), Integer.toString(LOCK_ID_PW_ERROR_COUNT)});
                        }
                        
                        if(log.isDebugEnabled()){
                            log.debug(message);
                        }
                        
                        //비밀번호 오류횟수 증가 및 휴먼처리 
                        this.updateByStCd(authVO2, mSession);
                    }
                    
                }else{
                    //휴면계정 메세지 출력
                    code = "A16";
                    message = xMessageSource.getMessage("msg.A16");
                    
                    if(log.isDebugEnabled()){
                        log.debug(message);
                    }
                }
                
            }else{             
                code = "A05";
                message = xMessageSource.getMessage("msg.A05");
                
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
        mResult.put("firstMenuUrl", firstMenuUrl);
                        
        return mResult;

    }


    /**
     * 계정 상태값 변경 및 로그인 로그 기록
     *
     * @param authVO
     * @param mSession
     * @throws Exception 
     */
    private void updateByStCd(AuthVO authVO, Map<String, Object> mSession) throws Exception {
        
        //상태값 변경
        authDAO.updateByStCd(authVO);
        
        //로그 기록
        accessLogService.insertAccessLog(authVO, mSession);
    }


    /**
     * 세션생성
     *
     * @throws Exception
     */
    private String createSession(AuthVO authVO, Map<String, Object> mSession, String code) throws Exception {
        
        String strFirstMenuUrl = "/mainMan.vc";
        
        HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
        httpServletRequest.getSession(false).invalidate();  
        HttpSession httpSession = httpServletRequest.getSession(true);
        
        //오류횟수 초기화, 마지막접속일 업데이트
        authVO.setStCd("Y");
        authVO.setPwErrOft(0);
        this.updateByStCd(authVO, mSession);
        
        if("LOGIN".equals(code)){
            
            //세션 생성
            SessionVO sessionVO = new SessionVO(); 
            sessionVO.setSn(authVO.getMgrSn());
            sessionVO.setId(authVO.getMgrId());                                 
            //sessionVO.setName(CryptoUtil.decryptARIA(authVO.getMgrNm()));
            sessionVO.setName(authVO.getMgrNm());
            //sessionVO.setEmail(CryptoUtil.decryptARIA(authVO.getMgrEml()));
            sessionVO.setSiteCode("ADMIN");
            sessionVO.setEmail(authVO.getMgrEml());
            sessionVO.setOpsNm(authVO.getMgrOpsNm());
            sessionVO.setPoaNm(authVO.getMgrPoaNm());
            sessionVO.setAuthLevel(authVO.getMgrAuthCd());
            sessionVO.setIpAddress(httpServletRequest.getRemoteAddr());
            
            sessionVO.setMenuCd(authVO.getMenuCd());
            
            //TODO :: 메뉴정보 입력해야함.
            //sessionVO.setMenuList(menuService.selectUserMenuList(authVO.getMgrAuthCd(), authVO.getMgrId()));       //관리자 권한 메뉴 조회
            
            //첫번째 메뉴로 보내주기
/*
            for(MenuVO menuVO : sessionVO.getMenuList()){
                if(menuVO.getMenuUrl() != null && !menuVO.getMenuUrl().isEmpty()){
                    strFirstMenuUrl = menuVO.getMenuUrl();
                    
                    if(log.isDebugEnabled()){
                        log.debug("\n\n\n\nAuthServiceImpl.createSession() First Menu : Name : " + menuVO.getMenuNm() +" , URL : " + menuVO.getMenuUrl() + "\n\n\n\n");
                    }
                    
                    break;
                }
            }
*/
            sessionVO.setFirstMenuUrl(strFirstMenuUrl);
            
            httpSession.setAttribute("sessionVO", sessionVO);
            //세션 유지시간 180분 설정
            httpSession.setMaxInactiveInterval(180 * 60);
            
            log.debug("로그인 세션 생성 완료");
            
        }else{
            //임시 세션 생성
            httpSession.setAttribute("tmpMgrId", authVO.getMgrId());       
            httpSession.setAttribute("tmpMgrPw", authVO.getMgrPw());
            httpSession.setAttribute("tmpMgrNm", authVO.getMgrNm());
            httpSession.setAttribute("tmpLogin", "Y");
        }
        
        return strFirstMenuUrl;
    }
    
    
    /*
     * 비밀번호 변경
     * @see ket.auth.service.AuthService#updateNewPassword(ket.auth.vo.LoginVO)
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
                
                Map<String,Object> mSession = multiUtil.getSessionInfo();

                loginVO.setMgrId((String)httpSession.getAttribute("tmpMgrId"));
                loginVO.setMgrPw((String)httpSession.getAttribute("tmpMgrPw"));
                loginVO.setMgrNewPw(new String(CryptoUtil.encodeUserPassword64(loginVO.getMgrNewPwEnc())));
                
                //사용자 정보 조회
                AuthVO authVO2 = authDAO.selectAuth(loginVO);
                
                if(authVO2 != null){
                
                    //입력 비밀번호 확인
                    if(loginVO.getMgrPw().equals(authVO2.getMgrPw())){
                      
                        //새로운 비밀번호 패턴 확인
                        String regex = "^.*(?=.{8,20})(?=.*[a-zA-Z])(?=.*[0-9]).*$";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(new String(CryptoUtil.getBASE64Decoder(loginVO.getMgrNewPwEnc())));
                      
                        if(matcher.matches()){
                        
                            //비밀번호 변경
                            authVO2.setTmpPwYn("N");
                            authVO2.setMgrPw(loginVO.getMgrNewPw());
                            authVO2.setMdfyId((String)mSession.get("userId"));
                            
                            if(authDAO.updateByPassword(authVO2) == 1){
                                //비밀번호변경로그 기록
                                PasswordLogVO passwordLogVO = new PasswordLogVO();
                                passwordLogVO.setMgrId(authVO2.getMgrId());
                                passwordLogVO.setMgrPw(authVO2.getMgrPw());
                                passwordLogVO.setMdfyId(authVO2.getMgrId());
                                passwordLogService.insertPasswordLog(passwordLogVO);
                                
                                httpServletRequest.getSession(false).invalidate();
                                
                                bStatus = true;
                            }else{
                                throw new IllegalArgumentException();
                            }
                        }else{
                            code = "A09";
                            message = xMessageSource.getMessage("msg.A09");   //비밀번호는 영문 대/소문자, 숫자를 조합하여 최소 12자리 이상 입력해 주세요.
                            throw new LeaveaTraceException("msg.A09");
                        }
                    }else{
                        code = "A10";
                        message = xMessageSource.getMessage("msg.A10");     //비밀번호가 일치하지 않습니다.\n다시 입력해 주세요.
                        throw new LeaveaTraceException("msg.A10");
                    }
                }else{
                    code = "A12";
                    message = xMessageSource.getMessage("msg.A12");         //입력한 정보가 일치하지 않습니다.\n올바른 정보를 입력해 주세요.
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
        mResult.put("message", message);
        mResult.put("status", bStatus);
            
        return mResult;
    }


    /*
     * 개인 정보 수정
     * @see ket.auth.service.AuthService#updateAuthInfo(ket.auth.vo.LoginVO)
     */
    @Override
    public Map<String, Object> updateAuthInfo(LoginVO loginVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        String code = "";
        String message = "";
        
        try{    
            Map<String,Object> mSession = multiUtil.getSessionInfo();

            loginVO.setMgrId((String)mSession.get("userId"));
            loginVO.setMgrPw(new String(CryptoUtil.encodeUserPassword64(loginVO.getMgrPwEnc())));
            loginVO.setMgrNewPw(new String(CryptoUtil.encodeUserPassword64(loginVO.getMgrNewPwEnc())));
            
            //사용자 정보 조회
            AuthVO authVO2 = authDAO.selectAuth(loginVO);
            
            if(authVO2 != null){
            
                //입력 비밀번호 확인
                if(loginVO.getMgrPw().equals(authVO2.getMgrPw())){
                  
                    //새로운 비밀번호 패턴 확인
                    String regex = "^.*(?=.{8,20})(?=.*[a-zA-Z])(?=.*[0-9]).*$";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(new String(CryptoUtil.getBASE64Decoder(loginVO.getMgrNewPwEnc())));
                  
                    if(matcher.matches()){
                    
                        //비밀번호 변경
                        authVO2.setTmpPwYn("N");
                        authVO2.setMgrPw(loginVO.getMgrNewPw());
                        authVO2.setMdfyId((String)mSession.get("userId"));
                        
                        if(authDAO.updateByPassword(authVO2) == 1){
                            //비밀번호변경로그 기록
                            PasswordLogVO passwordLogVO = new PasswordLogVO();
                            passwordLogVO.setMgrId(authVO2.getMgrId());
                            passwordLogVO.setMgrPw(authVO2.getMgrPw());
                            passwordLogVO.setMdfyId((String)mSession.get("userId"));
                            passwordLogService.insertPasswordLog(passwordLogVO);
                            
                            bStatus = true;
                        }else{
                            throw new IllegalArgumentException();
                        }
                    }else{
                        code = "A09";
                        message = xMessageSource.getMessage("msg.A09");   //비밀번호는 영문 대/소문자, 숫자를 조합하여 최소 12자리 이상 입력해 주세요.
                        throw new LeaveaTraceException("msg.A09");
                    }
                }else{
                    code = "A10";
                    message = xMessageSource.getMessage("msg.A10");     //비밀번호가 일치하지 않습니다.\n다시 입력해 주세요.
                    throw new LeaveaTraceException("msg.A10");
                }
            }else{
                code = "A12";
                message = xMessageSource.getMessage("msg.A12");         //입력한 정보가 일치하지 않습니다.\n올바른 정보를 입력해 주세요.
                throw new LeaveaTraceException("msg.A12");
            }
        }catch(LeaveaTraceException leaveaTraceException){
            leaveaTrace(leaveaTraceException.getMessage());
        }catch(Exception exception){        
            bStatus = false;
            exception.printStackTrace();
            throw processException("exception.error", exception);
        }
        
        mResult.put("code", code);
        mResult.put("message", message);
        mResult.put("status", bStatus);
            
        return mResult;
    }
    
    public List<AuthVO> selectSuperAdminList() throws Exception {
    	
    	return authDAO.selectSuperAdminList();
    }

}
