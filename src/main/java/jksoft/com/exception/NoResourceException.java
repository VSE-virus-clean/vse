package jksoft.com.exception;

import jksoft.com.exception.BizException;


/**
 * <pre>
 * 컨텐츠 정보가 없을때 exceptionResolver에서 ResourceNotFound 페이지로 보내기 위해
 * - ExceptionResolver에서 처리하기 위해서는 BizException을 상속 받아야함.
 * </pre>
 *
 * @ClassName   : NoResourceException.java
 * @Description : Exception 처리 Class
 * @author Jeong.hyoungjea
 * @since 2013. 8. 8.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2013. 8. 8.     Jeong.hyoungjea     최초 생성
 * </pre>
 */

@SuppressWarnings("serial")
public class NoResourceException extends BizException {
    
    public NoResourceException(){
        super();        
    }    
    
    public NoResourceException(String message) {
        super("No Resource Exception Message :: '" + message);
    }
}
