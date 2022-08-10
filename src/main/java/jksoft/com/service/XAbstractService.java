package jksoft.com.service;

import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import jksoft.com.util.MultiUtil;

import jksoft.com.exception.BizException;
import jksoft.com.support.XMessageSource;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

public class XAbstractService extends EgovAbstractServiceImpl {
	
	protected Logger log;
	
	/**
     * MultiUtil Class 선언 (QbaService Class Injection)
     */
    @Resource(name="multiUtil")
    protected MultiUtil multiUtil;
    
	/**
     * MessageSource class 선언 ( MessageSourceUtil Class Injection) 
     */ 
	@Resource(name="messageSource")
	protected MessageSource messageSource;
	
	/**
     * MessageSource class 선언 ( XMessageSource Class Injection) 
     */ 
    @Resource(name = "xMessageSource")
    protected XMessageSource xMessageSource;
    
    public XAbstractService() {
        log = LoggerFactory.getLogger(getClass());
    }

    protected Exception processException(final String msgKey, final String msgArgs[], final Exception e, final Locale locale, ExceptionCreator exceptionCreator)
    {
        ExceptionCreator eC = null;
        
        if(exceptionCreator == null){
            eC = new ExceptionCreator() {

                public Exception createBizException(MessageSource messageSource)
                {
                    return new BizException(messageSource, msgKey, msgArgs, locale, e);
                }
            };
        }
        
        return eC.createBizException(messageSource);
    }
}
