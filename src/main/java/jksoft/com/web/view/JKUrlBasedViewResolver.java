package jksoft.com.web.view;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import jksoft.com.util.MultiUtil;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : JKUrlBasedViewResolver.java
 * @Description : 접속 Device별 JSP파일의 prefix를 설정하기 위해 UrlBasedViewResolver를 상속받음.   
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

public class JKUrlBasedViewResolver extends UrlBasedViewResolver {
    
    /**
     * LoggerFactory
     */    
    private final Logger log = LoggerFactory.getLogger(JKUrlBasedViewResolver.class);
    
    @Resource(name = "multiUtil")
    private MultiUtil multiUtil;
    
    /**
     * 관리자 JSP Prefix
     */
    @Value(value = "#{global['site.jsp.prefix.admin']}")
    private String strJspAdminPrefix;
    
    /**
     * 사용자 JSP Prefix
     */
    @Value(value = "#{global['site.jsp.prefix.user']}")
    private String strJspUserPrefix;
    
    /**
     * 관리자 Servlet Path Prefix
     */
    @Value(value = "#{global['site.servlet.prefix.admin']}")
    private String strServletAdminPrefix;
    
    /**
     * 사용자 Servlet Path Prefix
     */
    @Value(value = "#{global['site.servlet.prefix.user']}")
    private String strServletUserPrefix;

    /*
     * @see org.springframework.web.servlet.view.UrlBasedViewResolver#buildView(java.lang.String)
     */
    @Override
    protected AbstractUrlBasedView buildView(String strViewName) throws Exception {

        String strRtn = "";
        
        HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
        
        log.debug("httpServletRequest.getRequestURI() : " + httpServletRequest.getRequestURI());
        log.debug("httpServletRequest.getRequestURI().matches : " + httpServletRequest.getRequestURI().matches("/("+ strServletAdminPrefix +")/.*"));
        
        if(httpServletRequest.getRequestURI().matches("/("+ strServletAdminPrefix +")/.*")){
            strRtn = getAdminViewName(strViewName);
        }else{
            strRtn = getUserViewName(strViewName);
        }
    
        return (AbstractUrlBasedView) super.buildView(strRtn);
    
    }
    
    /*
     * @see org.springframework.web.servlet.view.UrlBasedViewResolver#getCacheKey(java.lang.String, java.util.Locale)
     */
    @Override
    protected Object getCacheKey(String strViewName, Locale locale) {
        
        String strRtn = "";
        
        HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
        
        log.debug("httpServletRequest.getRequestURI() : " + httpServletRequest.getRequestURI());
        log.debug("httpServletRequest.getRequestURI().matches : " + httpServletRequest.getRequestURI().matches("/("+ strServletAdminPrefix +")/.*"));
        
        if(httpServletRequest.getRequestURI().matches("/("+ strServletAdminPrefix +")/.*")){
            strRtn = getAdminViewName(strViewName);
        }else{
            strRtn = getUserViewName(strViewName);
        }
        
        return super.getCacheKey(strRtn, locale);
        
    }
    
    /**
     * 
     * Mobile Device 접속시 Prefix 설정
     *
     * @param strViewName
     * @return
     */
    private String getAdminViewName(String strViewName) {    
        
        if(log.isDebugEnabled()){
            log.debug("MobileUrlBasedViewResolver.getAdminViewName() strJspAdminPrefix +  strViewName :: " + strJspAdminPrefix + "/" + strViewName );
        }
        
        return strJspAdminPrefix + "/" + strViewName;  
    }
    
    /**
     * 
     * 일반 Device 접속시 Prefix 설정
     *
     * @param strViewName
     * @return
     */
    private String getUserViewName(String strViewName) {   
        
        if(log.isDebugEnabled()){
            log.debug("MobileUrlBasedViewResolver.getUserViewName() strJspUserPrefix +  strViewName :: " + strJspUserPrefix + "/" + strViewName );
        }
        
        return strJspUserPrefix + "/" + strViewName;    
    }   
    
}
