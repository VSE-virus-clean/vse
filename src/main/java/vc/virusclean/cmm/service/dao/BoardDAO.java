package vc.virusclean.cmm.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import vc.virusclean.cmm.vo.BoardApiVO;
import vc.virusclean.cmm.vo.BoardVO;
import jksoft.com.dao.XAbstractDAO;

/**
 * <pre>
 * 일반 게시판 공통 서비스 DAO
 * </pre>
 *
 * @Class Name  : BoardDAO.java
 * @Description : @DAO 클래스
 * 
*/
@Repository("boardDAO")
public class BoardDAO extends XAbstractDAO {


    /**
     * 공지 게시물의 갯수를 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectBoardNotiCount(BoardVO boardVO) throws Exception {
        return (Integer)select("boardDAO.selectBoardNotiCount", boardVO);
    }

    /**
     * 공지 게시물 목록을 조회한다
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return List<BoardVO> 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<BoardVO> selectBoardNotiList(BoardVO boardVO) throws Exception {
        return (List<BoardVO>)list("boardDAO.selectBoardNotiList", boardVO);
    }
    

    /**
     * 게시물의 총 갯수를 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectBoardCount(BoardVO boardVO) throws Exception {
        return (Integer)select("boardDAO.selectBoardCount", boardVO);
    }


    @SuppressWarnings("unchecked")
    public List<BoardApiVO> selectApiBoardList(BoardVO boardVO) throws Exception {
        return (List<BoardApiVO>)list("boardDAO.selectApiBoardList", boardVO);
    }
    
    /**
     * 목록을 조회한다
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return List<BoardVO> 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<BoardVO> selectBoardList(BoardVO boardVO) throws Exception {
        return (List<BoardVO>)list("boardDAO.selectBoardList", boardVO);
    }
    
    /**
     * 이전 다음글 조회
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return List<BoardVO> 
     * @throws Exception
     */
    public BoardVO selectBoardNextNPreInfo(BoardVO boardVO) throws Exception {
        return (BoardVO)select("boardDAO.selectBoardNextNPreInfo", boardVO);
    }


    /**
     * 정보를 조회한다
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @return BoardVO
     * @throws Exception
     */
    public BoardVO selectBoard(BoardVO boardVO) throws Exception {
        return (BoardVO)select("boardDAO.selectBoard", boardVO);
    }


    /**
     * 정보를 등록한다
     *
     * @param boardVO - 저장 할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public Object insertBoard(BoardVO boardVO) throws Exception {
        return insert("boardDAO.insertBoard", boardVO);
    }


    /**
     * 정보를 수정한다
     *
     * @param boardVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateBoard(BoardVO boardVO) throws Exception {
        return update("boardDAO.updateBoard", boardVO);
    }
    
    /**
     * Qna답변을 한다
     *
     * @param boardVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateBoardQna(BoardVO boardVO) throws Exception {
        return update("boardDAO.updateBoardQna", boardVO);
    }

 
    /**
     * 정보를 삭제한다
     *
     * @param boardVO - 삭제할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int deleteBoard(BoardVO boardVO) throws Exception {
        return update("boardDAO.deleteBoard", boardVO);
    }

    /**
     * 조회수를 증가한다.
     *
     * @param boardVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateBoardByBlcRct(BoardVO boardVO) throws Exception {
        return update("boardDAO.updateBoardByBlcRct", boardVO);
    }
    
    /**
     * 정렬순서 변경
     *
     * @param boardVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateBoardByStNo(BoardVO boardVO) throws Exception {
        return update("boardDAO.updateBoardByStNo", boardVO);
    }
    
    /**
     *  엑셀업로드 결과 저장
     */
    public int updateBoardExcelUpload(BoardVO boardVO) throws Exception {
        return update("boardDAO.updateBoardExcelUpload", boardVO);
    }
}
