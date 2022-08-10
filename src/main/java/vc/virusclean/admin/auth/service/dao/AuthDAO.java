package vc.virusclean.admin.auth.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import vc.virusclean.admin.auth.vo.AuthVO;
import vc.virusclean.admin.auth.vo.LoginVO;
import jksoft.com.dao.XAbstractDAO;

/**
 * <pre>
 * 관리자 로그인 정보 DAO
 * </pre>
 *
 * @Class Name  : AuthDAO.java
 * @Description : @DAO 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2017.12.25
 * @version 1.0
 * @see vc.virusclean.admin.auth.service.dao.AuthDAO
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017.12.25      Jeong.Hyoung.Jae        최초생성
 * </pre>
 * 
*/
@Repository("authDAO")
public class AuthDAO extends XAbstractDAO {

    /**
     * 관리자 정보를 조회한다.
     *
     * @param authVO - 조회할 정보가 담긴 VO
     * @return AuthVO
     * @throws Exception
     */
    public AuthVO selectAuth(LoginVO loginVO) throws Exception {
        return (AuthVO)select("authDAO.selectAuth", loginVO);
    }


    /**
     *  계정 상태값 변경
     *
     * @param authVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateByStCd(AuthVO authVO) throws Exception {
        return update("authDAO.updateByStCd", authVO);
    }
    
    /**
     *  비밀번호를 수정한다.
     *
     * @param authVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateByPassword(AuthVO authVO) throws Exception {
        return update("authDAO.updateByPassword", authVO);
    }
    
    /**
     *  비밀번호를 수정한다. : 비밀번호 문의 승인시 휴면계정처리도 풀어준다.
     *
     * @param authVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateByPassword2(AuthVO authVO) throws Exception {
        return update("authDAO.updateByPassword2", authVO);
    }

    /**
     * 슈퍼관리자 목록 조회
     *
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<AuthVO> selectSuperAdminList() throws Exception {
        return (List<AuthVO>)list("authDAO.selectSuperAdminList");
    }
    
}
