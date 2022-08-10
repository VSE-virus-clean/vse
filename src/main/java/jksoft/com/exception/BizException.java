package jksoft.com.exception;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import java.util.Locale;
import org.springframework.context.MessageSource;

public class BizException extends EgovBizException {

	private static final long serialVersionUID = 1L;
	
	public BizException() {
		
	}
	
    public BizException(String defaultMessage) {
        super(defaultMessage, (Object[])null, null);
    }

    public BizException(String defaultMessage, Exception wrappedException) {
        super(defaultMessage, (Object[])null, wrappedException);
    }

    public BizException(String defaultMessage, Object messageParameters[], Exception wrappedException) {
        super(defaultMessage, messageParameters, wrappedException);
    }

    public BizException(MessageSource messageSource, String messageKey) {
        super(messageSource, messageKey, null, null, Locale.getDefault(), null);
    }

    public BizException(MessageSource messageSource, String messageKey, Exception wrappedException) {
        super(messageSource, messageKey, null, null, Locale.getDefault(), wrappedException);
    }

    public BizException(MessageSource messageSource, String messageKey, Locale locale, Exception wrappedException) {
        super(messageSource, messageKey, null, null, locale, wrappedException);
    }

    public BizException(MessageSource messageSource, String messageKey, Object messageParameters[], Locale locale, Exception wrappedException) {
        super(messageSource, messageKey, messageParameters, null, locale, wrappedException);
    }

    public BizException(MessageSource messageSource, String messageKey, Object messageParameters[], Exception wrappedException) {
        super(messageSource, messageKey, messageParameters, null, Locale.getDefault(), wrappedException);
    }

    public BizException(MessageSource messageSource, String messageKey, Object messageParameters[], String defaultMessage, Exception wrappedException) {
        super(messageSource, messageKey, messageParameters, defaultMessage, Locale.getDefault(), wrappedException);
    }

    public BizException(MessageSource messageSource, String messageKey, Object messageParameters[], String defaultMessage, Locale locale, Exception wrappedException) {
        super(messageSource, messageKey, messageParameters, defaultMessage, locale, wrappedException);
    }
}
