package jksoft.com.support;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.MessageSource;

/**
 * <pre>
 * 메시지 리소스 사용을 위한 MessageSource 인터페이스 및 ReloadableResourceBundleMessageSource 클래스의 구현체
 * </pre>
 *
 * @ClassName   : XMessageSource.java
 * @Description : 메시지 리소스 사용을 위한 MessageSource 인터페이스 및 ReloadableResourceBundleMessageSource 클래스의 구현체
 * @author Jeong.hyoungjea
 * @since 2013. 9. 10.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2013. 9. 10.     Jeong.hyoungjea     최초 생성
 * </pre>
 */

public class XMessageSource extends ReloadableResourceBundleMessageSource implements MessageSource {

    private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

    /**
     * getReloadableResourceBundleMessageSource() 
     * @param reloadableResourceBundleMessageSource - resource MessageSource
     * @return ReloadableResourceBundleMessageSource
     */ 
    public void setReloadableResourceBundleMessageSource(ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
        this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
    }
    
    /**
     * getReloadableResourceBundleMessageSource() 
     * @return ReloadableResourceBundleMessageSource
     */ 
    public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
        return reloadableResourceBundleMessageSource;
    }
    
    /**
     * 정의된 메세지 조회
     * @param code - 메세지 코드
     * @return String
     */ 
    public String getMessage(String code) {
        return getReloadableResourceBundleMessageSource().getMessage(code, null, LocaleContextHolder.getLocale());
    }
    
    /**
     * 정의된 메세지 조회
     * @param code - 메세지 코드
     * @param args - 배열 아큐먼트
     * @return String
     */ 
    public String getMessage(String code, Object[] args) {
        return getReloadableResourceBundleMessageSource().getMessage(code, args, LocaleContextHolder.getLocale());
    }

}
