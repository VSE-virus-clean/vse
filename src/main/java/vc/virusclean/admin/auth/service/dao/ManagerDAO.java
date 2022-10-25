package vc.virusclean.admin.auth.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import vc.virusclean.admin.auth.vo.AuthVO;
import vc.virusclean.cmm.vo.BoardVO;
import jksoft.com.dao.XAbstractDAO;

/**
 * <pre>
 * 관리자 정보 DAO
 * </pre>
 *
 * @Class Name  : ManagerDAO.java
 * @Description : @DAO 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2017.12.25
 * @version 1.0
 * @see vc.virusclean.admin.auth.service.dao.ManagerDAO
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017.12.25      Jeong.Hyoung.Jae        최초생성
 * </pre>
 * 
*/
@Repository("managerDAO")
public class ManagerDAO extends XAbstractDAO {

	/**
     * 관리자 정보 총 갯수를 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectInfoCount(AuthVO authVO) throws Exception {
        return (Integer)select("managerDAO.selectInfoCount", authVO);
    }
    
    /**
     * 관리자 정보 목록을 조회한다
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return List<BoardVO> 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<AuthVO> selectInfoList(AuthVO authVO) throws Exception {
        return (List<AuthVO>)list("managerDAO.selectInfoList", authVO);
    }
    
    /**
     * 관리자 정보를 조회한다.
     *
     * @param authVO - 조회할 정보가 담긴 VO
     * @return AuthVO
     * @throws Exception
     */
    public AuthVO selectInfo(AuthVO authVO) throws Exception {
        return (AuthVO)select("managerDAO.selectInfo", authVO);
    }

    /**
     * 정보를 등록한다.
     * @param authVO
     * @return
     * @throws Exception
     */
    public Object insertInfo(AuthVO authVO) throws Exception {
        return insert("managerDAO.insertInfo", authVO);
    }
    
    /**
     *  정보를 수정한다.
     *
     * @param authVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateInfo(AuthVO authVO) throws Exception {
        return update("managerDAO.updateInfo", authVO);
    }
    
    /**
     *  계정 상태값 변경
     *
     * @param authVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateByStCd(AuthVO authVO) throws Exception {
        return update("managerDAO.updateByStCd", authVO);
    }
    
    /**
     *  비밀번호를 수정한다.
     *
     * @param authVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateByPassword(AuthVO authVO) throws Exception {
        return update("managerDAO.updateByPassword", authVO);
    }
    
    /**
     * 아이디 중복체크
     * @param authVO
     * @return
     * @throws Exception
     */
    public int selectInfoCheckDuplicate(AuthVO authVO) throws Exception {
        return (Integer)select("managerDAO.selectInfoCheckDuplicate", authVO);
    }
    
    /**
     * 화면 세팅 목록을 조회한다.
     * @return List<BoardVO> 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<BoardVO> selectInfoSettingList(BoardVO boardVO) throws Exception {
        return (List<BoardVO>)list("managerDAO.selectInfoSettingList", boardVO);
    }
    
    /**
     *  화면 상태값 변경
     *
     * @param boardVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateByuseYn(BoardVO boardVO) throws Exception {
        return update("managerDAO.updateByuseYn", boardVO);
    }
    
}
