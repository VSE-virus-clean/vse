package vc.virusclean.cmm.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jksoft.com.dao.XAbstractDAO;

import jksoft.com.web.vo.AccessLogVO;

/**
 * <pre>
 * 보안로그 DAO
 * </pre>
*/
@Repository("accessLogDAO")
public class AccessLogDAO extends XAbstractDAO {


    /**
     * 게시물의 총 갯수를 조회한다.
     *
     * @param accessLogVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectAccessLogCount(AccessLogVO accessLogVO) throws Exception {
        return (Integer)select("accessLogDAO.selectAccessLogCount", accessLogVO);
    }


    /**
     * 목록을 조회한다
     *
     * @param accessLogVO - 조회할 정보가 담긴 VO
     * @return List<AccessLogVO> 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<AccessLogVO> selectAccessLogList(AccessLogVO accessLogVO) throws Exception {
        return (List<AccessLogVO>)list("accessLogDAO.selectAccessLogList", accessLogVO);
    }
    

    /**
     * 정보를 등록한다
     *
     * @param accessLogVO - 저장 할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public Object insertAccessLog(AccessLogVO accessLogVO) throws Exception {
        return insert("accessLogDAO.insertAccessLog", accessLogVO);
    }
    
    /**
     * 관리자의 접속 로그를 조회한다.
     *
     * @param accessLogVO - 조회할 정보가 담긴 VO
     * @return List<AccessLogVO> 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<AccessLogVO> selectUserAccessLogList(AccessLogVO accessLogVO) throws Exception {
        return (List<AccessLogVO>)list("accessLogDAO.selectUserAccessLogList", accessLogVO);
    }

}
