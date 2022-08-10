package jksoft.com.exception;

import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OthersExcepHndlr implements ExceptionHandler {

	protected Logger log;
	
    public OthersExcepHndlr() {
        log = LoggerFactory.getLogger(getClass());
    }

    public void occur(Exception exception, String packageName) {
        log.debug(" JKSoftServiceExceptionHandler run...............");
        log.error(packageName, exception);
    }
}
