package vc.virusclean.admin.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import vc.virusclean.admin.member.service.MemberBoardService;
import vc.virusclean.admin.member.service.dao.MemberBoardDAO;
import vc.virusclean.admin.member.service.dao.MemberBoardReplyDAO;
import vc.virusclean.admin.member.vo.MemberBoardReplyVO;
import vc.virusclean.admin.member.vo.MemberBoardVO;
import vc.virusclean.cmm.service.AttachFileService;
import jksoft.com.exception.NoResourceException;
import jksoft.com.service.XAbstractService;

/**
 * <pre>
 * 일반 게시판 공통 서비스 구현
 * 
 * - 뉴스&공지 : AAA
 * - IR게시판 : AAB
 * - 회사공고 : AAC
 * - IR자료 : AAD
 * - FAQ : AAE
 * </pre>
 * 
 * @ClassName   : BoardServiceImpl.java
 * @Description : BoardService 를 구현
 * @author Jeong.Hyoung.Jae 
 * @since 2015.12.22
 * @version 1.0
 * @see artist.cmm.service.impl.BoardServiceImpl
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2015.12.22      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */
@Service("memberBoardService")
public class MemberBoardServiceImpl extends XAbstractService implements MemberBoardService {

    @Resource(name="memberBoardDAO")   
    private MemberBoardDAO memberBoardDAO;
    
    @Resource(name="memberBoardReplyDAO") 
    private MemberBoardReplyDAO memberBoardReplyDAO;
    
    @Resource(name="attachFileService")
    private AttachFileService attachFileService;
    

    /*
     * 목록을 조회한다.
     *
     * @see artist.cmm.service.BoardService#selectBoardList(artist.cmm.vo.BoardVO)
     */
    public Map<String, Object> selectBoardList(MemberBoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        Map<String,Object> mSession = multiUtil.getSessionInfo();
        boardVO.setSiteCd((String)mSession.get("siteCd"));
        boardVO.setLangCd("JP");  
        boardVO.setTotalRow(memberBoardDAO.selectBoardCount(boardVO));
        
        if(boardVO.getTotalRow() > 0){
            mResult.put("list", memberBoardDAO.selectBoardList(boardVO));
        }
        
        mResult.put("searchInfo", boardVO);

        return mResult;

    }

	
	/*
     * 정보를 조회한다.
     *
     * @see artist.cmm.service.BoardService#selectBoard(artist.cmm.vo.BoardVO)
     */
    public Map<String, Object> selectBoard(MemberBoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        Map<String,Object> mSession = multiUtil.getSessionInfo();
        boardVO.setSiteCd((String)mSession.get("siteCd"));
        boardVO.setLangCd("JP");  
        
        MemberBoardVO boardVO2 = memberBoardDAO.selectBoard(boardVO);

        mResult.put("searchInfo", boardVO);
        
        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(boardVO2 == null){
            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
        }else{  
            mResult.put("info", boardVO2);
            
            MemberBoardReplyVO memberBoardReplyVO = new MemberBoardReplyVO();
            memberBoardReplyVO.setBlcSn(boardVO2.getBlcSn());
            mResult.put("replyList", memberBoardReplyDAO.selectBoardReplyList(memberBoardReplyVO));
        }
        
        return mResult;

    }
    

    /*
     * 정보를 삭제한다.
     *
     * @see artist.cmm.service.BoardService#deleteBoard(artist.cmm.vo.BoardVO)
     */
    public Map<String, Object> deleteBoard(MemberBoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
            boardVO.setSiteCd((String)mSession.get("siteCd"));
            boardVO.setLangCd("JP");  
            boardVO.setMdfyId((String)mSession.get("userId"));
     
            if(memberBoardDAO.deleteBoard(boardVO) == 1){
                bStatus = true;
            }else{
                throw new IllegalArgumentException();
            }
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
    }

    
	/**
	 * 댓글을 삭제한다.
	 */
	@Override
	public Map<String, Object> deleteBoardReply(MemberBoardReplyVO boardReplyVO) throws Exception {
		Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
  
            boardReplyVO.setMdfyId((String)mSession.get("userId"));
     
            if(memberBoardReplyDAO.deleteBoardReply(boardReplyVO) == 1){
                bStatus = true;
            }else{
                throw new IllegalArgumentException();
            }
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
	}

}
