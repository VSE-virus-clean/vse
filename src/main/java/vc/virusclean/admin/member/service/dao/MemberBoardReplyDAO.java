package vc.virusclean.admin.member.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import vc.virusclean.admin.member.vo.MemberBoardReplyVO;
import jksoft.com.dao.XAbstractDAO;

/**
 * <pre>
 * 일반 게시판 공통 서비스 DAO
 * </pre>
 *
 * @Class Name  : MemberBoardReplyDAO.java
 * @Description : @DAO 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2015.12.22
 * @version 1.0
 * @see artist.cmm.service.dao.BoardReplyDAO
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2015.12.22      Jeong.Hyoung.Jae        최초생성
 * </pre>
 * 
*/
@Repository("memberBoardReplyDAO")
public class MemberBoardReplyDAO extends XAbstractDAO {


    /**
     * 게시물의 총 갯수를 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectBoardReplyCount(MemberBoardReplyVO boardReplyVO) throws Exception {
        return (Integer)select("memberBoardReplyDAO.selectBoardReplyCount", boardReplyVO);
    }

    /**
     * 목록을 조회한다
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return List<BoardVO> 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<MemberBoardReplyVO> selectBoardReplyList(MemberBoardReplyVO boardReplyVO) throws Exception {
        return (List<MemberBoardReplyVO>)list("memberBoardReplyDAO.selectBoardReplyList", boardReplyVO);
    }
    
    /**
     * 정보를 삭제한다
     *
     * @param boardVO - 삭제할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int deleteBoardReply(MemberBoardReplyVO boardReplyVO) throws Exception {
        return update("memberBoardReplyDAO.deleteBoardReply", boardReplyVO);
    }

}
