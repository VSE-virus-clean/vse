package jksoft.com.exception;

import jksoft.com.exception.BizException;

/**
 * <pre>
 * 비즈니스로직 처리중 Exception이지만 Exception처리를 하지 않고 
 * Exception에 대한 Log만을 남기기 위해서 사용한다.
 * </pre>
 *
 * @ClassName   : LeaveaTraceException.java
 * @Description : Exception 처리 Class
 * @author Jeong.hyoungjea
 * @since 2013. 8. 9.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2013. 8. 9.     Jeong.hyoungjea     최초 생성
 * </pre>
 */

@SuppressWarnings("serial")
public class LeaveaTraceException extends BizException  {
    
    public LeaveaTraceException(){
        super();        
    }    
    
    public LeaveaTraceException(String message) {
        super("Leave a Trace Exception Message :: '" + message);
    }
}
