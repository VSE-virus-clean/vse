package jksoft.com.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;

import jksoft.com.support.XMessageSource;

public class XController {
	
	protected Logger log;
	
	/**
     * MessageSource class 선언 ( XMessageSource Class Injection) 
     */ 
    @Resource(name = "xMessageSource")
    protected XMessageSource xMessageSource;
    
	public static final String LOCAL_SESSION = "JKSOFT_FRAMEWORK_REQUESTHOLDER_SESSION_ATTRIBUTE";
    public static final String TARGET_URI = "JKSOFT_FRAMEWORK_REQUESTHOLDER_TARGETURI_ATTRIBUTE";
    public static final String START_URI = "JKSOFT_FRAMEWORK_REQUESTHOLDER_STARTURI_ATTRIBUTE";
    public static final String START_PATH = "JKSOFT_FRAMEWORK_REQUESTHOLDER_STARTPATH_ATTRIBUTE";
    public static final String LOG_KEY = "JKSOFT_FRAMEWORK_REQUESTHOLDER_SESSION_LOG_KEY";
    public static final String ERROR_PAGE_TAG = "JKSOFT_FRAMEWORK_ERROR_PAGE_URL_PARAMETER_NAME";
    public static final String PROC_TYPE = "JKSOFT_FRAMEWORK_PROC_TYPE";
    public static final String SEARCH_TYPE = "JKSOFT_FRAMEWORK_SEARCH_TYPE";
   

    public XController() {
        log = LoggerFactory.getLogger(getClass());
    }

    protected final WebApplicationContext getContext() {
        return ContextLoader.getCurrentWebApplicationContext();
    }

    public static final Object getSessionAttribute(String key) {
        try {
            HttpSession session = (HttpSession)RequestContextHolder.getRequestAttributes().getAttribute(LOCAL_SESSION, 1);
            return session.getAttribute(key);
        } catch(Exception e) {
            return null;
        }
    }

    public static final void setSessionAttribute(String key, Object value) {
        try {
            HttpSession session = (HttpSession)RequestContextHolder.getRequestAttributes().getAttribute(LOCAL_SESSION, 1);
            session.setAttribute(key, value);
        } catch(Exception e) { }
    }

    public static final void setLocalSession(HttpSession session) {
        try {
            RequestContextHolder.getRequestAttributes().setAttribute(LOCAL_SESSION, session, 1);
        } catch(Exception e) { }
    }

    public static final HttpSession getLocalSession() {
        try {
            return (HttpSession)RequestContextHolder.getRequestAttributes().getAttribute(LOCAL_SESSION, 1);
        } catch(Exception e) {
            return null;
        }
    }

    public static final void removeLocalSession() {
        try {
            RequestContextHolder.getRequestAttributes().removeAttribute(LOCAL_SESSION, 1);
        } catch(Exception e) { }
    }

}
