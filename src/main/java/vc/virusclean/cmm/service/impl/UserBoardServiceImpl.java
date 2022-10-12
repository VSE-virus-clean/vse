package vc.virusclean.cmm.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jksoft.com.exception.NoResourceException;
import jksoft.com.service.XAbstractService;
import jksoft.com.util.MailSenderUtil;
import jksoft.com.util.StringUtil;
import jksoft.com.util.XMap;
import vc.virusclean.admin.member.service.dao.MemberDAO;
import vc.virusclean.admin.member.vo.MemberVO;
import vc.virusclean.cmm.service.AccessLogService;
import vc.virusclean.cmm.service.AttachFileService;
import vc.virusclean.cmm.service.UserBoardService;
import vc.virusclean.cmm.service.dao.BoardDAO;
import vc.virusclean.cmm.service.dao.UserBoardDAO;
import vc.virusclean.cmm.vo.BoardVO;
import vc.virusclean.cmm.vo.UserBoardReplyVO;
import vc.virusclean.cmm.vo.UserBoardVO;

/**
 * <pre>
 * 일반 게시판 공통 서비스 구현	
 * </pre>
 * 
 * @ClassName   : UserBoardServiceImpl.java
 * @Description : BoardService 를 구현
 */
@Service("userBoardService")
public class UserBoardServiceImpl extends XAbstractService implements UserBoardService {

	@Resource(name="mailSenderUtil")
	private MailSenderUtil mailSenderUtil;
	
    @Resource(name="userBoardDAO")   
    private UserBoardDAO userBoardDAO;
    
    @Resource(name="boardDAO")   
    private BoardDAO boardDAO;
    
    @Resource(name="memberDAO")   
    private MemberDAO memberDAO;
    
    @Resource(name="attachFileService")
    private AttachFileService attachFileService;
    
    @Resource(name = "accessLogService")
    private AccessLogService accessLogService;
    
    @Value(value="#{global['mail.csMailAdress']}")
    private String csMailAdress; 
    
    
    /*
     * 목록을 조회한다.
     * - 공지글은 일반글의 결과가 있을때만 노출된다.
     */
    @Override
    public Map<String, Object> selectBoardList(UserBoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        Map<String,Object> mSession = multiUtil.getSessionInfo();

        try{
	        /*
	    	 * QNA는 Front에서 자기것만볼수 있어야 한다.
	    	 */
	        if(boardVO.getIsApi()){
				if((boolean)mSession.get("isLogin")){
	    			boardVO.setMbrSn((int)mSession.get("userSn"));
		            boardVO.setMbrId((String)mSession.get("userId"));
				}else{
					if(boardVO.getIndividual()){
						throw new IllegalArgumentException();
					}
	            }
			}
	        
	        //공지글
//	        if( "BBS".equals(boardVO.getLgrpCd()) ){
	            boardVO.setTotalNotiRow(userBoardDAO.selectBoardNotiCount(boardVO));
	            
	            if(boardVO.getTotalNotiRow() > 0){
	                mResult.put("notiList", userBoardDAO.selectBoardNotiList(boardVO));
	            }
//	        }
	        
	        //일반글
	        boardVO.setTotalRow(userBoardDAO.selectBoardCount(boardVO));
	        if(boardVO.getTotalRow() > 0){
	        	
	        	if("REVIEW".equals(boardVO.getLgrpCd())) {
	        		mResult.put("list", userBoardDAO.selectBoardReviewList(boardVO));
	        	}else {
	        		mResult.put("list", userBoardDAO.selectBoardList(boardVO));
	        	}
	        }
        }catch(Exception exception){        
            log.error(exception.getMessage());
            throw processException("exception.error", exception);
        }
        
        mResult.put("searchInfo", boardVO);

        return mResult;

    }

    
    /*
     * 정보를 조회한다.
     */
    @Override
    public Map<String, Object> selectBoard(UserBoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        try{
        	/*
        	 * QNA는 Front에서 자기것만볼수 있어야 한다.
        	 */
	        if(boardVO.getIndividual()){
		        Map<String,Object> mSession = multiUtil.getSessionInfo();
		        
		        if(boardVO.getIsApi()){
					if((boolean)mSession.get("isLogin")){
		    			boardVO.setMbrSn((int)mSession.get("userSn"));
			            boardVO.setMbrId((String)mSession.get("userId"));
					}else{
		                throw new IllegalArgumentException();
		            }
				}
	        }
	
	        boardVO.setTotalRow(userBoardDAO.selectBoardCount(boardVO));
	        
	        UserBoardVO boardVO2 = userBoardDAO.selectBoard(boardVO);
	
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
	        		userBoardDAO.updateBoardByBlcRct(boardVO2);
	        	}
	            
	            if(!"QNA".equals(boardVO.getLgrpCd())){
		            if(!"Y".equals(boardVO2.getNotiYn())){
		                int iRowNum = boardVO.getRowNum();
		                
		                if(iRowNum > 1){
		                    boardVO.setRowNum(iRowNum - 1);
		                    mResult.put("preInfo", userBoardDAO.selectBoardNextNPreInfo(boardVO));
		                    mResult.put("preSearchInfo", boardVO);
		                }
		                
		                if(boardVO.getRowNum() <= boardVO.getTotalRow()){
		                    boardVO.setRowNum(iRowNum + 1);
		                    mResult.put("nextInfo", userBoardDAO.selectBoardNextNPreInfo(boardVO));
		                    mResult.put("nextSearchInfo", boardVO);
		                }
		            }
		            
		            //댓글조회
		            mResult.put("replyCnt", userBoardDAO.selectBoardReplyCount(boardVO));
		        	mResult.put("replyList", userBoardDAO.selectBoardReplyList(boardVO));
		        	
		            //첨부파일
		            mResult.put("file", attachFileService.selectAttachFileList(boardVO2.getLgrpCd(), boardVO2.getBlcSn()));
	            }
	        }
        }catch(Exception exception){        
            log.error(exception.getMessage());
            throw processException("exception.error", exception);
        }
        
        return mResult;

    }
    
    
    /*
     * 수정페이지 정보를 조회한다.
     */
    @Override
    public Map<String, Object> selectBoardByMod(UserBoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        Map<String,Object> mSession = multiUtil.getSessionInfo();
            
        if(boardVO.getIsApi()){
			if((boolean)mSession.get("isLogin")){
    			boardVO.setMbrSn((int)mSession.get("userSn"));
	            boardVO.setMbrId((String)mSession.get("userId"));
    		}
			
			//TODO : 커뮤니티 사용권한 여부 확인
		}
        
        UserBoardVO boardVO2 = userBoardDAO.selectBoard(boardVO);
        
        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(boardVO2 == null){
            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
        }else{               
            mResult.put("info", boardVO2);       
            
            //첨부파일
            mResult.put("file", attachFileService.selectAttachFileList(boardVO2.getLgrpCd(), boardVO2.getBlcSn()));
        }
        
        mResult.put("searchInfo", boardVO);         
                        
        return mResult;

    }
    
    
    /*
     * 정보를 등록한다.
     */
    @Override
    public Map<String, Object> insertBoard(UserBoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{
        	Map<String,Object> mSession = multiUtil.getSessionInfo();
        	
        	if(boardVO.getIsApi()){
        		boardVO.setMbrSn((int)mSession.get("userSn"));
        		boardVO.setMbrId((String)mSession.get("userId"));
        		boardVO.setMbrNick((String)mSession.get("userNick"));
        		//TODO : 커뮤니티 사용권한 여부 확인

        	}else{
        		boardVO.setMgrSn((int)mSession.get("userSn"));
        		boardVO.setMgrId((String)mSession.get("userId"));
        	}
            
        	
            Object resultObject = userBoardDAO.insertBoard(boardVO) ;
            
            if(resultObject.getClass() == Integer.class){
                boardVO.setBlcSn((Integer)resultObject);
                
                // 첨부등록
                mResult.put("uploadInfo", attachFileService.insertAttachFile(boardVO, mSession));
                
                if("QNA".equals(boardVO.getLgrpCd())){
                	//TODO : 관리자에게 메일 발송
                	// 관리자 누구이게 ???
	            	String strMailTitle = "VIRUS CLEAN LAB 문의 등록";
            		String strMailFileName = "qnaRequest.html";
            		
            		Map<String, String> mailInfo = new HashMap<String, String>();
            		mailInfo.put("$$TITLE$$", StringUtil.replaceNewLineCharacter(boardVO.getBlcTitl()));
            		mailInfo.put("$$REQUEST$$", StringUtil.replaceEditerCharacter(boardVO.getBlcSbc1()));
            		
            		//메일 발송
                    mailSenderUtil.mailSendToUser(csMailAdress, strMailTitle, strMailFileName, mailInfo);
                }
                
                bStatus = true;
            }
            
        }catch(Exception exception){
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
    }
    
    
    /*
     * VSE 정보를 등록한다.
     */
    @Override
    public Map<String, Object> insertBoardVse(UserBoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{
        	Map<String,Object> mSession = multiUtil.getSessionInfo();
        	
            Object resultObject = userBoardDAO.insertBoard(boardVO) ;
            
            if(resultObject.getClass() == Integer.class){
                boardVO.setBlcSn((Integer)resultObject);
                
                // 첨부등록
                mResult.put("uploadInfo", attachFileService.insertAttachFile(boardVO, mSession));
                
                bStatus = true;
            }
            
        }catch(Exception exception){
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        if(boardVO.getLgrpCd().equals("BUSINESS")) {
        	
        	String message ="";
    		
    		message += "Name : " + boardVO.getRgstName();
    		message += "Phone Number : " + boardVO.getRgstHp();
    		message += "Email : " + boardVO.getRgstEml();
    		
            //답변 메일 발송
        	String strMailTitle = "Support Register";
    		String strMailFileName = "supportReg.html";
    		
    		Map<String, String> mailInfo = new HashMap<String, String>();
    		mailInfo.put("$$NAME$$", boardVO.getRgstName());
    		mailInfo.put("$$PHONE$$", boardVO.getRgstHp());
    		mailInfo.put("$$EMAIL$$", boardVO.getRgstEml());
    		
    		//메일 발송
            mailSenderUtil.mailSendToUser(csMailAdress, strMailTitle, strMailFileName, mailInfo);
    		
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
    }

    /*
     * 정보를 수정한다.
     */
    @Override
    public Map<String, Object> updateBoard(UserBoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{    
            Map<String,Object> mSession = multiUtil.getSessionInfo();
            
            
        	if( "QNA".equals(boardVO.getLgrpCd()) && !boardVO.getIsApi() )
            {
	            if(userBoardDAO.updateBoardQna(boardVO) == 1){
	            	
	            	bStatus = true;
	            	UserBoardVO boardVO2 = userBoardDAO.selectBoard(boardVO);
	            	
	            	//사용자 정보 조회
	            	MemberVO memberVO = new MemberVO();
	            	memberVO.setMbrSn(boardVO2.getMbrSn());
	            	
	            	MemberVO memberVO2 = memberDAO.selectMember(memberVO);
	                
	                //답변 메일 발송
	            	String strMailTitle = "VIRUS CLEAN LAB 문의 답변 완료";
            		String strMailFileName = "qnaAnswer.html";
            		
            		Map<String, String> mailInfo = new HashMap<String, String>();
            		mailInfo.put("$$NAME$$", memberVO2.getMbrNm());
            		mailInfo.put("$$TITLE$$", StringUtil.replaceNewLineCharacter(boardVO2.getBlcTitl()));
            		mailInfo.put("$$REQUEST$$", StringUtil.replaceNewLineCharacter(boardVO2.getBlcSbc1()));
            		mailInfo.put("$$ANSWER$$", StringUtil.replaceEditerCharacter(boardVO2.getBlcSbc2()));
            		
            		//메일 발송
                    mailSenderUtil.mailSendToUser(memberVO2.getMbrEml(), strMailTitle, strMailFileName, mailInfo);
                    
            		bStatus = true;
	            }else{
	                throw new IllegalArgumentException();
	            }
            }
            else
            {
            	if(boardVO.getIsApi()){
	            	boardVO.setMbrSn((int)mSession.get("userSn"));
	                boardVO.setMbrId((String)mSession.get("userId"));
	                
	              //TODO : 커뮤니티 사용권한 여부 확인
            	}
            	 
            	if(userBoardDAO.updateBoard(boardVO) == 1){
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

    /**
     * 노출 여부 변경
     */
    @Override
    public Map<String, Object> updateByUseYn(UserBoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
  
			boardVO.setMgrSn((int)mSession.get("userSn"));
			boardVO.setMgrId((String)mSession.get("userId"));
     
            if(userBoardDAO.updateByUseYn(boardVO) > 0){
                bStatus = true;
                
                //TODO 보안로그   
            	accessLogService.insertAccessLog(boardVO, mSession);
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
     * 정보를 삭제한다.
     */
    @Override
    public Map<String, Object> deleteBoard(UserBoardVO boardVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
  
            if(boardVO.getIsApi()){
            	boardVO.setMbrSn((int)mSession.get("userSn"));
            	boardVO.setMbrId((String)mSession.get("userId"));
            	
            	//TODO : 커뮤니티 사용권한 여부 확인
    		}else{
    			boardVO.setMgrSn((int)mSession.get("userSn"));
    			boardVO.setMgrId((String)mSession.get("userId"));
    		}
     
            if(userBoardDAO.deleteBoard(boardVO) > 0){
                bStatus = true;
                
                //TODO 보안로그   
                if(!boardVO.getIsApi()){
                	accessLogService.insertAccessLog(boardVO, mSession);
                }
            }else{
                throw new IllegalArgumentException();
            }
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("searchInfo", boardVO); 
        mResult.put("status", bStatus);
            
        return mResult;
    }


	@Override
	public Map<String, Object> insertBoardReply(UserBoardReplyVO replyVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{
        	Map<String,Object> mSession = multiUtil.getSessionInfo();
			replyVO.setMbrSn((int)mSession.get("userSn"));
			replyVO.setMbrId((String)mSession.get("userId"));
			replyVO.setMbrNick((String)mSession.get("userNick"));
			
			//TODO : 커뮤니티 사용권한 여부 확인
        
            Object resultObject = userBoardDAO.insertBoardReply(replyVO) ;
            
            if(resultObject.getClass() == Integer.class){
            	replyVO.setBlcCmdSn((Integer)resultObject);
                bStatus = true;
            }
            
        }catch(Exception exception){
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
	}


	@Override
	public Map<String, Object> updateBoardReply(UserBoardReplyVO replyVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{
        	Map<String,Object> mSession = multiUtil.getSessionInfo();

        	if(replyVO.getIsApi()){
    			replyVO.setMbrSn((int)mSession.get("userSn"));
    			replyVO.setMbrId((String)mSession.get("userId"));
    			
    			//TODO : 커뮤니티 사용권한 여부 확인
    		}else{
    			replyVO.setMgrSn((int)mSession.get("userSn"));
    			replyVO.setMgrId((String)mSession.get("userId"));
    		}
        	
            if(userBoardDAO.updateBoardReply(replyVO) == 1){
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


	@Override
	public Map<String, Object> deleteBoardReply(UserBoardReplyVO replyVO) throws Exception {
		Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{
        	Map<String,Object> mSession = multiUtil.getSessionInfo();

        	if(replyVO.getIsApi()){
    			replyVO.setMbrSn((int)mSession.get("userSn"));
    			replyVO.setMbrId((String)mSession.get("userId"));
    			
    			//TODO : 커뮤니티 사용권한 여부 확인
    		}else{
    			replyVO.setMgrSn((int)mSession.get("userSn"));
    			replyVO.setMgrId((String)mSession.get("userId"));
    		}
            
            if(userBoardDAO.deleteBoardReply(replyVO) == 1){
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
	 * 목록을 조회한다.
	 */
	@Override
	public List<XMap> selectExcelList(UserBoardVO boardVO) throws Exception {
		
		List<XMap> rtnMap = null;
				
		/**
		 * 구분값이 여러개면
		 */
		String[] searchGubunType = boardVO.getSearchGubunType().split("\\|");
		if(searchGubunType.length > 1) {
			boardVO.setDelSeq(Arrays.asList(searchGubunType));
		}
		
		if("BUSINESS".equals(boardVO.getLgrpCd())) {
			rtnMap = userBoardDAO.selectExcelList1(boardVO);
		} else if ("PRESENTATION".equals(boardVO.getLgrpCd())) {
			rtnMap = userBoardDAO.selectExcelList2(boardVO);
		} else {
			rtnMap = userBoardDAO.selectExcelList3(boardVO);
		}
		
		return rtnMap;
	}
}
