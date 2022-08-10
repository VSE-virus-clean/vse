package vc.virusclean.admin.member.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import vc.virusclean.admin.member.vo.MemberBoardVO;
import jksoft.com.dao.XAbstractDAO;

/**
 * <pre>
 * 일반 게시판 공통 서비스 DAO
 * </pre>
 *
 * @Class Name  : BoardDAO.java
 * @Description : @DAO 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2015.12.22
 * @version 1.0
 * @see artist.cmm.service.dao.BoardDAO
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2015.12.22      Jeong.Hyoung.Jae        최초생성
 * </pre>
 * 
*/
@Repository("memberBoardDAO")
public class MemberBoardDAO extends XAbstractDAO {

    /**
     * 게시물의 총 갯수를 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectBoardCount(MemberBoardVO boardVO) throws Exception {
        return (Integer)select("memberBoardDAO.selectBoardCount", boardVO);
    }


    /**
     * 목록을 조회한다
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return List<BoardVO> 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<MemberBoardVO> selectBoardList(MemberBoardVO boardVO) throws Exception {
        return (List<MemberBoardVO>)list("memberBoardDAO.selectBoardList", boardVO);
    }
    
    
    /**
     * 정보를 조회한다
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return BoardVO
     * @throws Exception
     */
    public MemberBoardVO selectBoard(MemberBoardVO boardVO) throws Exception {
        return (MemberBoardVO)select("memberBoardDAO.selectBoard", boardVO);
    }

    /**
     * 정보를 삭제한다
     *
     * @param boardVO - 삭제할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int deleteBoard(MemberBoardVO boardVO) throws Exception {
        return update("memberBoardDAO.deleteBoard", boardVO);
    }

}
