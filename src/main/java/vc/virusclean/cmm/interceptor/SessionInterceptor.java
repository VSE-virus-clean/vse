package vc.virusclean.cmm.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jksoft.com.annotation.AuthCheck;
import jksoft.com.util.MultiUtil;
import jksoft.com.web.vo.SessionVO;

/**
 * <pre>
 * 로그인 / 권한 세션 확인
 * 
 * 세션명 : auth - user정보
 *             - 권한정보
 * </pre>
 */

public class SessionInterceptor extends HandlerInterceptorAdapter {
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    /**
     * MultiUtil class 선언 ( MultiUtil Class Injection)
     */
    @Resource(name="multiUtil")
    private MultiUtil multiUtil;
    
    /**
     * 로그인 / 회원가입 페이지중에서 로그인 사용자가 접근할수 없는 URL 목록
     */
    private final static String[] LOGIN_USER_DENYURL = {"/admin/login", "/member/login", "/registration", "/help/idInquiry", "/help/pwInquiry"};
    
    
    /**
     * 
     * 로그인 정보를 확인 정보가 없으면 로그인 유도 페이지로 이동 
     * 
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
          
        //세션 정보
        SessionVO sessionVO = null;
        
        //인증 성공 여부
        boolean bAuth = true;
        
        //Ajax접속 여부
        boolean bAjax = multiUtil.checkAjaxRequest(httpServletRequest);
        
        //REDIRECT URL
        String strExceptionRedirectUrl = "";
        
        /*
         * 로그인 확인 여부 상태값
         */
        boolean bAuthCheck = true;
        
        /*
         * 권한 확인 여부 상태값
         */
        boolean bRoleCheck = true;
        
        /*
         * 권한 값
         * 
         * 기본관리자 - A
         * 슈퍼관리자 - S
         * 
         * 무료회원 - A
         * 정회회원 - S
         */
        String checkRoleCode = "A";
        
        //접속 URI
        String strRequestURI = httpServletRequest.getRequestURI();
        String strNonContextpathRequestURI = multiUtil.getRequestURI(httpServletRequest);
        
        /*
         * Annotation 확인
         */
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();        
        Annotation annotation = method.getAnnotation(AuthCheck.class);
        
        //Annotaion이 존재한다면 값을 확인한다.
        if(annotation instanceof AuthCheck){
            AuthCheck authAnnotation = (AuthCheck)annotation;
            bAuthCheck = authAnnotation.loginCheck();
            bRoleCheck = authAnnotation.roleCheck();
            checkRoleCode = authAnnotation.roleCode();
        }
        
        /*
         * 임시 세션 생성
         */
//        if(httpServletRequest.getSession().getAttribute("sessionVO") == null){
//            sessionVO = new SessionVO();
//            sessionVO.setId("admin");
//            sessionVO.setSiteCode("KR");
//            sessionVO.setName("최고관리자");
//            sessionVO.setAuthLevel("S");
//            sessionVO.setIpAddress(httpServletRequest.getRemoteAddr());
//            httpServletRequest.getSession().setAttribute("sessionVO", sessionVO);
//        }
        
        boolean isAdmin = strNonContextpathRequestURI.startsWith("/admin/");
        
        /*
         * Auth 세션 존재여부 확인
         */
        if(!multiUtil.checkWebResource(strRequestURI)){
            
            if(bAuthCheck){                
                try{
                    /*
                     * Auth 세션 존재여부 확인
                     */
                    if(httpServletRequest.getSession().getAttribute("sessionVO") != null){
                        
                        sessionVO = (SessionVO)httpServletRequest.getSession().getAttribute("sessionVO");
                        
                        if(sessionVO == null){
                            throw new IllegalArgumentException();
                        }else{
                        	
                        	isAdmin = "ADMIN".equals(sessionVO.getSiteCode());
                        	
                            //세션생성된 아이피와 접속중인 아이피를 확인
                            //if(!(httpServletRequest.getRemoteAddr()).equals(sessionVO.getIpAddress())){
                            //    log.error("로그인 확인 :: IP정보 오류  :: " + sessionVO.getIpAddress() + " != " + httpServletRequest.getRemoteAddr());
                            //    throw new IllegalArgumentException("errors.login.access.ipaddress");
                            //}
                        }
                    }else{
                        throw new IllegalArgumentException();
                    }
                    
                }catch(IllegalArgumentException iEx){
                    
                    log.error("로그인 세션이 없음 : bAjax - " + bAjax);
                            
                    bAuth = false;
                    
                    if(bAjax){
                        //401 - 인증에러
                        httpServletResponse.setStatus(401);
                    }else{
                        /*
                         *  팝업 페이지인 경우의 처리
                         *  - 팝업창에서 세션로그아웃이 발생햇을경우 팝업페이지를 닫고 opener로 focus를 넘겨주기 위해서 확인한다.
                         */
                    	if(isAdmin){
                    		strExceptionRedirectUrl = "/admin/error/authFailure.vc?isPop=" + (strRequestURI.matches(".*/pop/.*") ? "Y" : "N") + "&returnUrl="+ httpServletRequest.getRequestURI() + (httpServletRequest.getQueryString() == null ? "" : "?" +httpServletRequest.getQueryString());
                    	}else{
                    		strExceptionRedirectUrl = "/error/authFailure.vc?isPop=" + (strRequestURI.matches(".*/pop/.*") ? "Y" : "N") + "&returnUrl="+ httpServletRequest.getRequestURI() + (httpServletRequest.getQueryString() == null ? "" : "?" +httpServletRequest.getQueryString());
                    	}
                    }
                    
                    //세션정보 삭제
                    //httpServletRequest.getSession().invalidate();
                }    
                
                
                /*
                 * 권한 확인 로직
                 * - 권한은 기본적으로 로그인을 확인 해야 한다.
                 */
                if(bAuth && bRoleCheck){        
                    try{
                        @SuppressWarnings("unused")
						boolean bMenuAuth = false;
                        
                        /**
                         * 권한 확인
                         * 
                         * 1.슈퍼관리자는 모든 페이지에 접근이 가능하다.
                         * 2.일반관리자는 슈퍼관리자 페이지에 접근 할 수 없다.
                         * 2.일반관리자는 권한이 없는 페이지에 접근 할 수 없다.
                         */
                        if("S".equals(sessionVO.getAuthLevel()) || checkRoleCode.equals(sessionVO.getAuthLevel()) 
                                || (checkRoleCode.equals("A") && "Q".equals(sessionVO.getAuthLevel()) )){
                            
                            log.debug("권한 확인");
                        }else{
                            throw new IllegalArgumentException();
                        }  
                        
                    }catch(IllegalArgumentException iEx){
                        bAuth = false;
                        
                        if(bAjax){
                            //403 - 권한에러
                            httpServletResponse.setStatus(403);
                        }else{
                            /*
                             *  팝업 페이지인 경우의 처리
                             *  
                             *  - 패턴이 맞는지 확인 할것.  
                             *  - 팝업창에서 세션로그아웃이 발생햇을경우 팝업페이지를 닫고 opener로 focus를 넘겨주기 위해서 확인한다.
                             **/
                        	if(isAdmin){
                        		strExceptionRedirectUrl = "/admin/error/roleFailure.vc?isPop=" + (strRequestURI.matches(".*/pop/.*") ? "Y" : "N");
                        	}else{
                        		strExceptionRedirectUrl = "/error/roleFailure.vc?isPop=" + (strRequestURI.matches(".*/pop/.*") ? "Y" : "N");
                        	}
                        }
                        
                        if(log.isErrorEnabled()){
                            log.error("[ FAILD :: ROLE CHECK ] strRequestURI :: " + strRequestURI);
                        }
                    }
                }else{
                    if(bAuth && (strNonContextpathRequestURI.startsWith("/admin/main") || strNonContextpathRequestURI.startsWith("/admin/ua"))){
                        sessionVO.setMenuNm("");
                        sessionVO.setMenuCd("");
                        sessionVO.setMenuPathCd("");
                        sessionVO.setMenuPathNm("");
                    }
                }
            }else{
                /*
                 * 사용자가 로그인 중이면 로그인 관련 메뉴 접근 하지 못하도록 한다.
                 */
                if(httpServletRequest.getSession().getAttribute("sessionVO") != null){                
                    boolean bAllowUrl = true;
                    
                    for(int i = 0 ; i < LOGIN_USER_DENYURL.length ; i++){
                        if(strRequestURI.startsWith(httpServletRequest.getContextPath() + LOGIN_USER_DENYURL[i])){
                            bAllowUrl = false;
                            break;
                        }
                    }
                    
                    if(!bAllowUrl){
                        strExceptionRedirectUrl = isAdmin ? "/admin/mainMan.vc" : "/index.vc" ;
                        bAuth = false;
                    }
                }
            }
        }
        
        /*
         * 권한이 없으면 Error페이지로 Redirect
         */
        if(!bAuth && !bAjax){
        	log.debug("권한이 없으면 Error페이지로 Redirect :: "  + strExceptionRedirectUrl);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + strExceptionRedirectUrl);
        }else if(bAuthCheck){
            httpServletRequest.getSession().setAttribute("sessionVO", sessionVO);
        }
        
        return bAuth;
    }

    /**
     * Controller 완료후 VIEW 호출 전에 확인 한다.
     * 
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handlerMethod, ModelAndView modelAndView) throws Exception {
        
    }

    /**
     * VIEW 완료후 호출 한다.
     * 
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handlerMethod, Exception ex) throws Exception {
        
    }
    
}
