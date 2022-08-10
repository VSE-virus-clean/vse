package vc.virusclean.cmm.service;

import java.util.Map;

import vc.virusclean.cmm.vo.BoardVO;

/**
 * <pre>
 * 일반 게시판 공통 서비스
 * </pre>
 *
 * @ClassName   : BoardService.java
 * @Description : @Service 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2017.12.25
 * @version 1.0
 * @see vc.virusclean.cmm.service.BoardService
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017.12.25      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */

public interface BoardService {

	public Map<String, Object> selectApiBoardList(BoardVO boardVO) throws Exception;
	
    /**
     * 목록을 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectBoardList(BoardVO boardVO) throws Exception;


    /**
     * 정보를 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectBoard(BoardVO boardVO) throws Exception;
    
    /**
     * 수정페이지 정보를 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectBoardByMod(BoardVO boardVO) throws Exception;

    /**
     * 공지글 갯수 확인
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectBoardNotiCount(BoardVO boardVO) throws Exception;
        
    /**
     * 정보를 등록한다.
     *
     * @param boardVO - 저장 할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> insertBoard(BoardVO boardVO) throws Exception;
    
    /**
     * 파일을 등록한다.
     *
     * @param boardVO - 저장 할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> insertBoardFile(BoardVO boardVO) throws Exception;

    /**
     * 정보를 수정한다.
     *
     * @param boardVO - 수정할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateBoard(BoardVO boardVO) throws Exception;


    /**
     * 정보를 삭제한다.
     *
     * @param boardVO - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> deleteBoard(BoardVO boardVO) throws Exception;


    /**
     * 정렬순서 변경
     * @param boardVO
     * @return
     * @throws Exception
     */
	public Map<String, Object> updateBoardBySort(BoardVO boardVO) throws Exception;
    
}
