package vc.virusclean.cmm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import jksoft.com.exception.NoResourceException;
import jksoft.com.service.XAbstractService;
import jksoft.com.util.DateUtil;
import jksoft.com.util.MailSenderUtil;
import jksoft.com.util.StringUtil;
import jksoft.com.util.firebase.FCMHelper;
import vc.virusclean.admin.member.service.dao.MemberDAO;
import vc.virusclean.admin.member.vo.MemberVO;
import vc.virusclean.cmm.service.AccessLogService;
import vc.virusclean.cmm.service.AttachFileService;
import vc.virusclean.cmm.service.BoardService;
import vc.virusclean.cmm.service.dao.BoardDAO;
import vc.virusclean.cmm.vo.BoardVO;

/**
 * <pre>
 * 일반 게시판 공통 서비스 구현	
 * </pre>
 * 
 * @ClassName   : BoardServiceImpl.java
 * @Description : BoardService 를 구현
 * @author Jeong.Hyoung.Jae 
 * @since 2017.12.25
 * @version 1.0
 * @see vc.virusclean.cmm.service.impl.BoardServiceImpl
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017.12.25      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */
@Service("boardService")
public class BoardServiceImpl extends XAbstractService implements BoardService {

	@Resource(name="mailSenderUtil")
	private MailSenderUtil mailSenderUtil;
	
	@Resource(name="fcmHelper")
    private FCMHelper fcmHelper;
	
    @Resource(name="boardDAO")   
    private BoardDAO boardDAO;
    
    @Resource(name="memberDAO")   
    private MemberDAO memberDAO;
    
    @Resource(name="attachFileService")
    private AttachFileService attachFileService;
    
    @Resource(name = "accessLogService")
    private AccessLogService accessLogService;

    
    
    /*
     * 목록을 조회한다.
     */
    public Map<String, Object> selectApiBoardList(BoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();

        //일반글
        boardVO.setTotalRow(boardDAO.selectBoardCount(boardVO));
        if(boardVO.getTotalRow() > 0){
            mResult.put("list", boardDAO.selectApiBoardList(boardVO));
        }
        
        return mResult;

    }

    /*
     * 목록을 조회한다.
     * - 공지글은 일반글의 결과가 있을때만 노출된다.
     *
     * @see vc.virusclean.cmm.service.BoardService#selectBoardList(vc.virusclean.cmm.vo.BoardVO)
     */
    public Map<String, Object> selectBoardList(BoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        Map<String,Object> mSession = multiUtil.getSessionInfo();

        if(boardVO.getIsApi()){
			if((boolean)mSession.get("isLogin")){
    			boardVO.setRgstSn((int)mSession.get("userSn"));
	            boardVO.setRgstId((String)mSession.get("userId"));
    		}
		}
        
        //공지글
//        if( "NOTICE".equals(boardVO.getLgrpCd()) || "VSENEWS".equals(boardVO.getLgrpCd()) ){
        if(boardVO.getIsApi()){
        	boardVO.setTotalNotiRow(boardDAO.selectBoardNotiCount(boardVO));
            
            if(boardVO.getTotalNotiRow() > 0){
                mResult.put("notiList", boardDAO.selectBoardNotiList(boardVO));
            }
        }
        
        //일반글
        boardVO.setTotalRow(boardDAO.selectBoardCount(boardVO));
        if(boardVO.getTotalRow() > 0){
            mResult.put("list", boardDAO.selectBoardList(boardVO));
        }
        
        mResult.put("searchInfo", boardVO);

        return mResult;

    }

    
    /*
     * 정보를 조회한다.
     *
     * @see vc.virusclean.cmm.service.BoardService#selectBoard(vc.virusclean.cmm.vo.BoardVO)
     */
    public Map<String, Object> selectBoard(BoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        Map<String,Object> mSession = multiUtil.getSessionInfo();

        boardVO.setTotalRow(boardDAO.selectBoardCount(boardVO));
        
        BoardVO boardVO2 = boardDAO.selectBoard(boardVO);

        mResult.put("searchInfo", boardVO);
        
        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(boardVO2 == null){
            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
        }else{   
        	
            mResult.put("info", boardVO2);
            
            if(boardVO.getIsApi()){
            	//조회수 증가
        		boardDAO.updateBoardByBlcRct(boardVO2);
        	}
            
            if("QNA".equals(boardVO2.getLgrpCd()) && boardVO2.getRgstSn() > 0){
            	MemberVO memberVO = new MemberVO();
                memberVO.setMbrSn(boardVO2.getRgstSn());
                memberVO.setMbrId(boardVO2.getRgstId());
                    
                MemberVO memberVO2 = memberDAO.selectMember(memberVO);
            	mResult.put("userInfo", memberVO2);
            }
            
            if(!"Y".equals(boardVO2.getNotiYn())){
                int iRowNum = boardVO.getRowNum();
                
                if(iRowNum > 1){
                    boardVO.setRowNum(iRowNum - 1);
                    mResult.put("preInfo", boardDAO.selectBoardNextNPreInfo(boardVO));
                    mResult.put("preSearchInfo", boardVO);
                }
                
                if(boardVO.getRowNum() <= boardVO.getTotalRow()){
                    boardVO.setRowNum(iRowNum + 1);
                    mResult.put("nextInfo", boardDAO.selectBoardNextNPreInfo(boardVO));
                    mResult.put("nextSearchInfo", boardVO);
                }
            }
            
            //첨부파일
            mResult.put("file", attachFileService.selectAttachFileList(boardVO2.getLgrpCd(), boardVO2.getBlcSn()));
        }
        
        return mResult;

    }
    
    
    /*
     * 수정페이지 정보를 조회한다.
     *
     * @see vc.virusclean.cmm.service.BoardService#selectBoardByMod(vc.virusclean.cmm.vo.BoardVO)
     */
    public Map<String, Object> selectBoardByMod(BoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        Map<String,Object> mSession = multiUtil.getSessionInfo();
            
        BoardVO boardVO2 = boardDAO.selectBoard(boardVO);
        
        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(boardVO2 == null){
            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
        }else{               
            mResult.put("info", boardVO2);       
            
            if("QNA".equals(boardVO2.getLgrpCd()) && boardVO2.getRgstSn() > 0){
            	MemberVO memberVO = new MemberVO();
                memberVO.setMbrSn(boardVO2.getRgstSn());
                memberVO.setMbrId(boardVO2.getRgstId());
                    
                MemberVO memberVO2 = memberDAO.selectMember(memberVO);
            	mResult.put("userInfo", memberVO2);
            }
            
            //if( "NEWS".equals(boardVO.getLgrpCd()) ){
            //    boardVO.setTotalNotiRow(boardDAO.selectBoardNotiCount(boardVO));
           // }
            
            //첨부파일
            mResult.put("file", attachFileService.selectAttachFileList(boardVO2.getLgrpCd(), boardVO2.getBlcSn()));
        }
        
        mResult.put("searchInfo", boardVO);         
                        
        return mResult;

    }
    
    
    /*
     * 공지글 갯수 확인
     *
     * @see vc.virusclean.cmm.service.BoardService#selectBoardNoticeCount(vc.virusclean.cmm.vo.BoardVO)
     */
    public Map<String, Object> selectBoardNotiCount(BoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        Map<String,Object> mSession = multiUtil.getSessionInfo();

        boardVO.setTotalNotiRow(boardDAO.selectBoardNotiCount(boardVO));
        
        mResult.put("searchInfo", boardVO);

        return mResult;
    }


    /*
     * 정보를 등록한다.
     *
     * @see vc.virusclean.cmm.service.BoardService#insertBoard(vc.virusclean.cmm.vo.BoardVO)
     */
    public Map<String, Object> insertBoard(BoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{
        	Map<String,Object> mSession = multiUtil.getSessionInfo();

        	if( "QNA".equals(boardVO.getLgrpCd()) ){
        		if((boolean)mSession.get("isLogin")){
        			boardVO.setRgstSn((int)mSession.get("userSn"));
    	            boardVO.setRgstId((String)mSession.get("userId"));
        		}
        		
        		boardVO.setExpsRgstDtm(DateUtil.formatDate());
        		Object resultObject = boardDAO.insertBoard(boardVO) ;
        		
        		if(resultObject.getClass() == Integer.class){
        			bStatus = true;
        		}
        	}else{
	            
	            boardVO.setRgstSn((int)mSession.get("userSn"));
	            boardVO.setRgstId((String)mSession.get("userId"));
	            
	            if("PUSH".equals(boardVO.getLgrpCd()) ){
	            	if("즉시발송".equals(boardVO.getMgrpCd())) {
	            		boardVO.setSgrpCd("발송완료");
	            	}else {
	            		boardVO.setSgrpCd("발송예정");
	            	}
	            }
	            
	            if(boardVO.getExpsRgstDay().isEmpty()){
//	                boardVO.setExpsRgstDtm(DateUtil.formatDate());
	            }else{
	            	boardVO.setExpsRgstDtm(boardVO.getExpsRgstDay().replaceAll("-", "") + boardVO.getExpsRgstTime() + boardVO.getExpsRgstMinute() + "00");
	            }
	            
	            if(!boardVO.getExpsFnhDay().isEmpty()){
	            	 boardVO.setExpsFnhDtm(boardVO.getExpsFnhDay().replaceAll("-", "") + boardVO.getExpsFnhTime() + boardVO.getExpsFnhMinute() + "59");
	            }
	            
	            Object resultObject = boardDAO.insertBoard(boardVO) ;
	            
	            if(resultObject.getClass() == Integer.class){
	                boardVO.setBlcSn((Integer)resultObject);
	                
	                /*
	                 * 첨부등록
	                 */
	                mResult.put("uploadInfo", attachFileService.insertAttachFile(boardVO, mSession));
	    			
	    			//과제별로 분류
	                if("PUSH".equals(boardVO.getLgrpCd())){
	                	List<MemberVO> notiList = memberDAO.selectMemberPushList();
	                	
	                	for(MemberVO vo : notiList){
		                	Map<String, Object> notification = new HashMap<String, Object>();
		    				notification.put("title", boardVO.getBlcTitl());
		    				notification.put("body", boardVO.getBlcSbc1());
		    				notification.put("message", boardVO.getBlcSbc1());
//		    				notification.put("badge", 0);
//		    				notification.put("sound", "default");
		    				
		    				long resultCode = -1;
		    		        try{
		    		        	JsonObject resultObject2 = fcmHelper.sendFcmMessage2(notification, notification, vo.getAppToken());
		    					resultCode = resultObject2.get("success").getAsLong();
		    					log.info("PUSH발송 : " + resultCode);
		    		        }catch(Exception exception){
		                        log.error("== firebaseResponse :: Failed \n", exception);
		                    }
		                }
	    			}
	                
	                bStatus = true;
	            }
	            
	            //보안로그            
	            accessLogService.insertAccessLog(boardVO, mSession);
        	}
        }catch(Exception exception){
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
    }
    
    /*
     * 파일을 등록한다.
     *
     * @see vc.virusclean.cmm.service.BoardService#insertBoardFile(vc.virusclean.cmm.vo.BoardVO)
     */
    public Map<String, Object> insertBoardFile(BoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{

            Map<String,Object> mSession = multiUtil.getSessionInfo();

            boardVO.setRgstSn((int)mSession.get("userSn"));
            boardVO.setRgstId((String)mSession.get("userId"));
            boardVO.setMdfySn((int)mSession.get("userSn"));
            boardVO.setMdfyId((String)mSession.get("userId"));
            
            /*
             * 파일 등록
             */
            mResult.put("uploadInfo", attachFileService.insertAttachFile(boardVO, mSession));
            
            bStatus = true;

        }catch(Exception exception){
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;

    }


    /*
     * 정보를 수정한다.
     *
     * @see vc.virusclean.cmm.service.BoardService#updateBoard(vc.virusclean.cmm.vo.BoardVO)
     */
    public Map<String, Object> updateBoard(BoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{    
            Map<String,Object> mSession = multiUtil.getSessionInfo();
            
        	boardVO.setMdfySn((int)mSession.get("userSn"));
        	boardVO.setMdfyId((String)mSession.get("userId"));
            
        	if( "QNA".equals(boardVO.getLgrpCd()) )
            {
	            if(boardDAO.updateBoardQna(boardVO) == 1){
	            	
	            	bStatus = true;
	            	BoardVO boardVO2 = boardDAO.selectBoard(boardVO);
	                
	                //답변 메일 발송
            		String strMailTitle = "KANG JIHWAN JAPAN OFFICIAL FANCLUB お問合せ内容 ";
            		String strMailFileName = "qnaAnswer.txt";
            		
            		Map<String, String> mailInfo = new HashMap<String, String>();
            		mailInfo.put("$$REQUEST$$", StringUtil.replaceNewLineCharacter(boardVO2.getBlcSbc1()));
            		mailInfo.put("$$ANSWER$$", StringUtil.replaceEditerCharacter(boardVO2.getBlcSbc2()));
            		
            		//메일 발송
                    mailSenderUtil.mailSendToUser(boardVO2.getRgstEml(), strMailTitle, strMailFileName, mailInfo);
                    
            		bStatus = true;
	            }else{
	                throw new IllegalArgumentException();
	            }
            }
            else
            {
            	 if(!boardVO.getExpsRgstDay().isEmpty()){
                 	 boardVO.setExpsRgstDtm(boardVO.getExpsRgstDay().replaceAll("-", "") + boardVO.getExpsRgstTime() + boardVO.getExpsRgstMinute() + "00");
                 }
            	 
            	 if(!boardVO.getExpsFnhDay().isEmpty()){
                  	 boardVO.setExpsFnhDtm(boardVO.getExpsFnhDay().replaceAll("-", "") + boardVO.getExpsFnhTime() + boardVO.getExpsFnhMinute() + "00");
                 }
            	 
            	if(boardDAO.updateBoard(boardVO) == 1){
	                bStatus = true;
	                mResult.put("uploadInfo", attachFileService.insertAttachFile(boardVO, mSession));
	            }else{
	                throw new IllegalArgumentException();
	            }
            }
        	
        	//보안로그            
            accessLogService.insertAccessLog(boardVO, mSession);
        
        }catch(Exception exception){        
            bStatus = false;
            log.error(exception.getMessage());
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;

    }


    /*
     * 정보를 삭제한다.
     *
     * @see vc.virusclean.cmm.service.BoardService#deleteBoard(vc.virusclean.cmm.vo.BoardVO)
     */
    public Map<String, Object> deleteBoard(BoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
  
            boardVO.setMdfySn((int)mSession.get("userSn"));
            boardVO.setMdfyId((String)mSession.get("userId"));
     
            if(boardVO.getBlcSn() != 0 || ( boardVO.getDelSeq() != null && !boardVO.getDelSeq().isEmpty() )){
	            if(boardDAO.deleteBoard(boardVO) > 0){
	                bStatus = true;
	                
	                //TODO 보안로그 => 여러개 삭제일때 어떻게 처리할것인지.             
	                accessLogService.insertAccessLog(boardVO, mSession);
	            }else{
	                throw new IllegalArgumentException();
	            }
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
    
    
    /*
     * 정렬순서 변경
     * - For문으로 하나씩 던져줄것.
     * @see vc.virusclean.cmm.service.BoardService#updateBoardBySort(vc.virusclean.cmm.vo.BoardVO)
     */
    public Map<String, Object> updateBoardBySort(BoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	
            Map<String,Object> mSession = multiUtil.getSessionInfo();
     
            List<String> listSn = boardVO.getDelSeq();
            
            for(int i = 0; i < listSn.size(); i++)
            {
            	BoardVO boardVO2 = new BoardVO();
            	boardVO2.setLgrpCd(boardVO.getLgrpCd());
            	boardVO2.setBlcSn(Integer.valueOf(listSn.get(i)));
            	boardVO2.setStNo(i+1);
            	
	            if(boardDAO.updateBoardByStNo(boardVO2) == 1){
	                bStatus = true;
	                accessLogService.insertAccessLog(boardVO2, mSession);
	            }else{
	                throw new IllegalArgumentException();
	            }
            }
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
    }


}
