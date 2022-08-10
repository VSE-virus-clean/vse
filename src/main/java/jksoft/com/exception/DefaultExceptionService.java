package jksoft.com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DefaultExceptionService implements ExceptionService {
	protected Logger log;
	 
    public DefaultExceptionService() {
        log = LoggerFactory.getLogger(getClass());
    }

    public void except(Exception ex) {
        log.debug("Exception Service : {} ", ex.toString());
    }
}
