package vc.virusclean.cmm.service;

import java.util.Map;

import jksoft.com.web.vo.SessionVO;

import jksoft.com.web.vo.AccessLogVO;

/**
 * <pre>
 * 보안로그 서비스
 * </pre>
 */

public interface AccessLogService {

    /**
     * 게시물 접속 로그를 조회한다.
     *
     * @param accessLogVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectAccessLogList(AccessLogVO accessLogVO) throws Exception;
    
    
    /**
     * 관리자의 접속 로그를 조회한다.
     *
     * @param accessLogVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectUserAccessLogList(AccessLogVO accessLogVO) throws Exception;


    /**
     * 세션인터셉터 정보를 등록한다.
     * @param strMethod 
     *
     * @param accessLogVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> insertAccessLog(SessionVO sessionVO, String strRequestURI, String strMethod);


    /**
     * 보안로그를 등록한다.
     *
     * @param accessLogVO - 저장 할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> insertAccessLog(Object object, Map<String,Object> mSession) throws Exception;

}
