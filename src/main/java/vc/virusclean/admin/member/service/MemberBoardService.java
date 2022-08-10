package vc.virusclean.admin.member.service;

import java.util.Map;

import vc.virusclean.admin.member.vo.MemberBoardReplyVO;
import vc.virusclean.admin.member.vo.MemberBoardVO;

/**
 * <pre>
 * 일반 게시판 공통 서비스
 * </pre>
 *
 * @ClassName   : BoardService.java
 * @Description : @Service 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2015.12.22
 * @version 1.0
 * @see artist.cmm.service.BoardService
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2015.12.22      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */

public interface MemberBoardService {

    /**
     * 목록을 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectBoardList(MemberBoardVO boardVO) throws Exception;

    /**
     * 정보를 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectBoard(MemberBoardVO boardVO) throws Exception;
    
    /**
     * 정보를 삭제한다.
     *
     * @param boardVO - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> deleteBoard(MemberBoardVO boardVO) throws Exception;

    /**
     * 댓글 정보를 삭제한다.
     *
     * @param boardVO - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> deleteBoardReply(MemberBoardReplyVO boardReplyVO) throws Exception;

}
