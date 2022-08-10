package vc.virusclean.cmm.service.dao;

import org.springframework.stereotype.Repository;

import vc.virusclean.cmm.vo.PasswordLogVO;
import jksoft.com.dao.XAbstractDAO;

/**
 * <pre>
 * 비밀번호변경로그 DAO
 * </pre>
 *
 * @Class Name  : PasswordLogDAO.java
 * @Description : @DAO 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2016.1.15
 * @version 1.0
 * @see vc.virusclean.cmm.service.dao.PasswordLogDAO
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2016.1.15      Jeong.Hyoung.Jae        최초생성
 * </pre>
 * 
*/
@Repository("passwordLogDAO")
public class PasswordLogDAO extends XAbstractDAO {


    /**
     * 게시물의 총 갯수를 조회한다.
     *
     * @param passwordLogVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectPasswordLogCount(PasswordLogVO passwordLogVO) throws Exception {
        return (Integer)select("passwordLogDAO.selectPasswordLogCount", passwordLogVO);
    }


    /**
     * 정보를 등록한다
     *
     * @param passwordLogVO - 저장 할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public Object insertPasswordLog(PasswordLogVO passwordLogVO) throws Exception {
        return insert("passwordLogDAO.insertPasswordLog", passwordLogVO);
    }

}
