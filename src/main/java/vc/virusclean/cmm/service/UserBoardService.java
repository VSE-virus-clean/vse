package vc.virusclean.cmm.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import jksoft.com.util.XMap;
import vc.virusclean.admin.shop.vo.OrderVO;
import vc.virusclean.cmm.vo.UserBoardReplyVO;
import vc.virusclean.cmm.vo.UserBoardVO;

/**
 * <pre>
 * 일반 게시판 공통 서비스
 * </pre>
 *
 * @ClassName   : BoardService.java
 * @Description : @Service 클래스
 */

public interface UserBoardService {

    /**
     * 목록을 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectBoardList(UserBoardVO boardVO) throws Exception;

    /**
     * 정보를 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectBoard(UserBoardVO boardVO) throws Exception;
    
    /**
     * 수정페이지 정보를 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectBoardByMod(UserBoardVO boardVO) throws Exception;

    /**
     * 정보를 등록한다.
     *
     * @param boardVO - 저장 할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> insertBoard(UserBoardVO boardVO) throws Exception;
    public Map<String, Object> insertBoardVse(UserBoardVO boardVO) throws Exception;
    

    /**
     * 정보를 수정한다.
     *
     * @param boardVO - 수정할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateBoard(UserBoardVO boardVO) throws Exception;

    /**
     * 노출여부 변경
     * @param boardVO
     * @return
     * @throws Exception
     */
    public Map<String, Object> updateByUseYn(UserBoardVO boardVO) throws Exception;

    /**
     * 정보를 삭제한다.
     *
     * @param boardVO - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> deleteBoard(UserBoardVO boardVO) throws Exception;
    
    
    /**
     * 정보를 등록한다.
     *
     * @param boardVO - 저장 할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> insertBoardReply(UserBoardReplyVO boardVO) throws Exception;
    

    /**
     * 정보를 수정한다.
     *
     * @param boardVO - 수정할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateBoardReply(UserBoardReplyVO boardVO) throws Exception;


    /**
     * 정보를 삭제한다.
     *
     * @param boardVO - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> deleteBoardReply(UserBoardReplyVO boardVO) throws Exception;
    
    public List<XMap> selectExcelList(UserBoardVO vo) throws Exception;
    
}
