package vc.virusclean.cmm.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import vc.virusclean.cmm.vo.UserBoardReplyVO;
import vc.virusclean.cmm.vo.UserBoardVO;
import jksoft.com.dao.XAbstractDAO;
import jksoft.com.util.XMap;
import jksoft.com.web.vo.SearchVO;

/**
 * <pre>
 * 일반 게시판 공통 서비스 DAO
 * </pre>
 *
 * @Class Name  : userBoardDAO.java
 * @Description : @DAO 클래스
 * 
*/
@Repository("userBoardDAO")
public class UserBoardDAO extends XAbstractDAO {


    /**
     * 공지 게시물의 갯수를 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectBoardNotiCount(UserBoardVO boardVO) throws Exception {
        return (Integer)select("userBoardDAO.selectBoardNotiCount", boardVO);
    }

    /**
     * 공지 게시물 목록을 조회한다
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return List<UserBoardVO> 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<UserBoardVO> selectBoardNotiList(UserBoardVO boardVO) throws Exception {
        return (List<UserBoardVO>)list("userBoardDAO.selectBoardNotiList", boardVO);
    }
    

    /**
     * 게시물의 총 갯수를 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectBoardCount(UserBoardVO boardVO) throws Exception {
        return (Integer)select("userBoardDAO.selectBoardCount", boardVO);
    }


    /**
     * 목록을 조회한다
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return List<UserBoardVO> 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<UserBoardVO> selectBoardList(UserBoardVO boardVO) throws Exception {
        return (List<UserBoardVO>)list("userBoardDAO.selectBoardList", boardVO);
    }
    @SuppressWarnings("unchecked")
    public List<UserBoardVO> selectBoardReviewList(UserBoardVO boardVO) throws Exception {
        return (List<UserBoardVO>)list("userBoardDAO.selectBoardReviewList", boardVO);
    }
    
    /**
     * 이전 다음글 조회
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return List<UserBoardVO> 
     * @throws Exception
     */
    public UserBoardVO selectBoardNextNPreInfo(UserBoardVO boardVO) throws Exception {
        return (UserBoardVO)select("userBoardDAO.selectBoardNextNPreInfo", boardVO);
    }


    /**
     * 정보를 조회한다
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return UserBoardVO
     * @throws Exception
     */
    public UserBoardVO selectBoard(UserBoardVO boardVO) throws Exception {
        return (UserBoardVO)select("userBoardDAO.selectBoard", boardVO);
    }


    /**
     * 정보를 등록한다
     *
     * @param boardVO - 저장 할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public Object insertBoard(UserBoardVO boardVO) throws Exception {
        return insert("userBoardDAO.insertBoard", boardVO);
    }


    /**
     * 정보를 수정한다
     *
     * @param boardVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateBoard(UserBoardVO boardVO) throws Exception {
        return update("userBoardDAO.updateBoard", boardVO);
    }
    
    /**
     * Qna답변을 한다
     *
     * @param boardVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateBoardQna(UserBoardVO boardVO) throws Exception {
        return update("userBoardDAO.updateBoardQna", boardVO);
    }

    /**
     * 노출 여부 변경
     *
     * @param boardVO - 삭제할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateByUseYn(UserBoardVO boardVO) throws Exception {
        return update("userBoardDAO.updateByUseYn", boardVO);
    }
    
    /**
     * 정보를 삭제한다
     *
     * @param boardVO - 삭제할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int deleteBoard(UserBoardVO boardVO) throws Exception {
        return update("userBoardDAO.deleteBoard", boardVO);
    }

    /**
     * 조회수를 증가한다.
     *
     * @param boardVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateBoardByBlcRct(UserBoardVO boardVO) throws Exception {
        return update("userBoardDAO.updateBoardByBlcRct", boardVO);
    }
    
    
    /**
     * 댓글
     */
    /**
     * 댓글 게시물의 총 갯수를 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectBoardReplyCount(UserBoardVO boardVO) throws Exception {
        return (Integer)select("userBoardDAO.selectBoardReplyCount", boardVO);
    }


    /**
     * 댓글 목록을 조회한다
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return List<UserBoardVO> 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<UserBoardReplyVO> selectBoardReplyList(UserBoardVO boardVO) throws Exception {
        return (List<UserBoardReplyVO>)list("userBoardDAO.selectBoardReplyList", boardVO);
    }
    

    /**
     * 댓글 정보를 등록한다
     *
     * @param boardVO - 저장 할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public Object insertBoardReply(UserBoardReplyVO replyVO) throws Exception {
        return insert("userBoardDAO.insertBoardReply", replyVO);
    }
    
    /**
     * 댓글 정보를 수정한다
     *
     * @param boardVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateBoardReply(UserBoardReplyVO replyVO) throws Exception {
        return update("userBoardDAO.updateBoardReply", replyVO);
    }
    
    /**
     * 댓글 정보를 삭제한다
     *
     * @param boardVO - 삭제할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int deleteBoardReply(UserBoardReplyVO replyVO) throws Exception {
        return update("userBoardDAO.deleteBoardReply", replyVO);
    }

    /**
     * 엑셀목록 > 창업상담
     * @param boardVO
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<XMap> selectExcelList1(UserBoardVO boardVO) {
		return (List<XMap>)list("userBoardDAO.selectExcelList1", boardVO);
	}
    
    /**
     * 엑셀목록 > 사업설명회
     * @param boardVO
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<XMap> selectExcelList2(UserBoardVO boardVO) {
		return (List<XMap>)list("userBoardDAO.selectExcelList2", boardVO);
	}
    
    /**
     * 엑셀목록 > 프로지원
     * @param boardVO
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<XMap> selectExcelList3(UserBoardVO boardVO) {
		return (List<XMap>)list("userBoardDAO.selectExcelList3", boardVO);
	}
	
}
