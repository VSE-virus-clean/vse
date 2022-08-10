package vc.virusclean.admin.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import vc.virusclean.admin.auth.vo.AuthVO;
import vc.virusclean.admin.member.service.MemberService;
import vc.virusclean.admin.member.service.dao.MemberDAO;
import vc.virusclean.admin.member.vo.MemberVO;
import jksoft.com.exception.NoResourceException;
import jksoft.com.service.XAbstractService;
import jksoft.com.util.CryptoUtil;
import jksoft.com.util.DateUtil;
import jksoft.com.util.MailSenderUtil;
import jksoft.com.util.MultiUtil;
import jksoft.com.util.StringUtil;
import jksoft.com.util.XMap;

/**
 * <pre>
 * 클래스 설명을 기술합니다.
 * </pre>
 * 
 * @ClassName   : MemberRegisteServiceImpl.java
 * @Description : MemberRegisteService 를 구현
 * </pre>
 */
@Service("memberService")
public class MemberServiceImpl extends XAbstractService implements MemberService {

	@Resource(name="mailSenderUtil")
	private MailSenderUtil mailSenderUtil;
	
    /**
     * MemberRegisteDAO class 선언 (MemberRegisteDAO Class Injection)
     * (MemberRegisteDAO)memberRegisteDAO
     */   
    @Resource(name="memberDAO")   
    private MemberDAO memberDAO;

    /**
     * MultiUtil Class 선언 (QbaService Class Injection)
     */
    @Resource(name="multiUtil")
    private MultiUtil multiUtil;
    

    /*
     * 목록을 조회한다.
     */
    public Map<String, Object> selectMemberList(MemberVO memberVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        Map<String,Object> mSession = multiUtil.getSessionInfo();
        
        memberVO.setTotalRow(memberDAO.selectMemberCount(memberVO));

        mResult.put("list", memberDAO.selectMemberList(memberVO));
        
        mResult.put("searchInfo", memberVO);

        return mResult;

    }

    
    /*
     * 정보를 조회한다.
     */
    public Map<String, Object> selectMember(MemberVO memberVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        Map<String,Object> mSession = multiUtil.getSessionInfo();
        
        memberVO.setIsAdmin(true);
        MemberVO memberVO2 = memberDAO.selectMember(memberVO);

        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(memberVO2 == null){
            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
        }else{                
            mResult.put("info", memberVO2);                  
        }
        	
        mResult.put("searchInfo", memberVO);         
        				
        return mResult;

    }


    /*
     * 정보를 수정한다.
     */
    public Map<String, Object> updateMember(MemberVO memberVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{    
        	//변경전 정보 조회
            MemberVO memberVO2 = memberDAO.selectMember(memberVO);
            
        	//회원정보 변경
        	if(memberDAO.updateMemberAdm(memberVO) == 1){
                bStatus = true;
            }else{
                throw new IllegalArgumentException();
            }
        	
        	//SMS/이메일 동의여부
        	if(!memberVO2.getSmsYn().equals(memberVO.getSmsYn())){
        		memberDAO.updateBySmsYn(memberVO);
        	}
        	
        }catch(Exception exception){        
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;

    }
    
    
    /*
     * 골드회원 등록을 요청한다.
     *
     * @see MemberRegisteService#updateBySpecialMember(memberVO)
     */
    public Map<String, Object> updateBySpecialMember(MemberVO memberVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 

        try{  	
        	
        	//회원정보 조회
//        	MemberVO memberVO3 = memberDAO.selectMember(memberVO);
//        	
//        	if("Y".equals(memberVO.getSpCd()))
//        	{
//        		//정회원 등록
//	            //회원 번호 조회
//        		if("0".equals(memberVO3.getMbrNo())){
////        			int iUserNo = memberDAO.selectMemberMaxNo();
//    	        	memberVO.setMbrNo(iUserNo + "");
//    	        	
//    	        	//정회원 등록
//    				Object resultObject = memberDAO.updateBySpecialMember(memberVO);
//    				
//    				if(resultObject.getClass() == Integer.class){
//    					
//    					MemberVO memberVO2 = memberDAO.selectMember(memberVO);
//    					
//    	        		String strMailTitle = "カン・ジファンジャパンオフィシャルファンクラブ事務局より「GOLD会員入会手続き完了」";
//    	        		String strMailFileName = "memberGoldComplete.txt";
//    	        		
//    	        		Map<String, String> mailInfo = new HashMap<String, String>();
//    	        		mailInfo.put("$$USER_NAME$$", memberVO2.getMbrNm());
//    	        		mailInfo.put("$$USER_ID$$", memberVO2.getMbrId());
//    	        		mailInfo.put("$$USER_NO$$", memberVO2.getMbrNo());
//    	        		mailInfo.put("$$START_DATE$$", memberVO2.getMbrDtm());	//시작일
//    	        		mailInfo.put("$$END_DATE$$", memberVO2.getRenewDtm());	//종료일
//    	        		
//    	        		//메일 발송
//    	                mailSenderUtil.mailSendToUser(memberVO2.getMbrEml(), strMailTitle, strMailFileName, mailInfo);
//    	                
//    	        		bStatus = true;
//    	                mResult.put("check", "Y");   
//    	        	}else{
//    		            mResult.put("check", "N");   
//    	        	}
//    				
//        		}else{
//        			//기간 연장
//    				Object resultObject = memberDAO.updateBySpecialMemberRenew(memberVO);
//    				
//    				if(resultObject.getClass() == Integer.class){
//    					
//    					MemberVO memberVO2 = memberDAO.selectMember(memberVO);
//    					
//    	        		String strMailTitle = "カン・ジファンジャパンオフィシャルファンクラブ事務局より「GOLD延長手続き完了のお知らせ」のお知らせ";
//    	        		String strMailFileName = "memberGoldExtensionComplete.txt";
//    	        		
//    	        		Map<String, String> mailInfo = new HashMap<String, String>();
//    	        		mailInfo.put("$$USER_NAME$$", memberVO2.getMbrNm());
//    	        		mailInfo.put("$$USER_ID$$", memberVO2.getMbrId());
//    	        		mailInfo.put("$$USER_NO$$", memberVO2.getMbrNo());
//    	        		mailInfo.put("$$START_DATE$$", memberVO2.getMbrDtm());	//시작일
//    	        		mailInfo.put("$$END_DATE$$", memberVO2.getRenewDtm());	//종료일
//    	        		
//    	        		//메일 발송
//    	                mailSenderUtil.mailSendToUser(memberVO2.getMbrEml(), strMailTitle, strMailFileName, mailInfo);
//    	                
//    	        		bStatus = true;
//    	                mResult.put("check", "Y");   
//    	        	}else{
//    		            mResult.put("check", "N");   
//    	        	}
//        		}
//        	}
//        	else if("P".equals(memberVO.getSpCd()))
//        	{
//        		//정회원 결제 대기
//        		Object resultObject = memberDAO.updateBySpecialMemberStandby(memberVO);
//        		
//        		if(resultObject.getClass() == Integer.class){
//        			bStatus = true;
//	                mResult.put("check", "Y");   
//	        	}else{
//		            mResult.put("check", "N");   
//	        	}
//        	}
//        	else if("E".equals(memberVO.getSpCd()))
//        	{
//        		//정회원 기간 만료
//        		Object resultObject = memberDAO.updateBySpecialMemberExpire(memberVO);
//        		
//        		if(resultObject.getClass() == Integer.class){
//        			bStatus = true;
//	                mResult.put("check", "Y");   
//	        	}else{
//		            mResult.put("check", "N");   
//	        	}
//        	}
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;

    }


    /*
     * 회원 탈퇴
     */
    @SuppressWarnings("unused")
	public Map<String, Object> deleteMember(MemberVO memberVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
  
            memberVO.setScssRson("관리자가 회원 요청에 의해 탈퇴 처리한다.");
            if(memberDAO.deleteMemberRegiste(memberVO) == 1){
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
     * 계정 상태값 변경 및 로그인 로그 기록
     *
     * @param authVO2
     * @param mSession
     * @throws Exception 
     */
    @SuppressWarnings("unused")
	private void updateByStCd(MemberVO memberVO, Map<String, Object> mSession) throws Exception {
        
        //상태값 변경
        memberDAO.updateByStCd(memberVO);
        
        //로그 기록
        //accessLogService.insertAccessLog(authVO, mSession);
    }
    
    
    /**
     * 전체 비밀번호 리셋
     */
	@Override
	public Map<String, Object> updateByAllPasswordReset() throws Exception {
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		int iTotalCount = 0;
		
		MemberVO memberVO = new MemberVO();
		memberVO.setRowLimit(10000);
		
		List<MemberVO> lMemberVO = memberDAO.selectMemberList(memberVO);
		
		if(lMemberVO.size() > 0)
		{
			for(MemberVO vo : lMemberVO)
			{
				vo.setMbrPw(CryptoUtil.encodeUserPassword(vo.getMbrEml()));
				iTotalCount += memberDAO.updateByPassword(vo);
			}
		}
		
		mResult.put("userCount", lMemberVO.size());  
		mResult.put("totalCount", iTotalCount);  
		mResult.put("status", iTotalCount == lMemberVO.size());  
		
		return mResult;
	}
	
	
	/**
     * 유저 비밀번호 리셋
     */
	@Override
	public Map<String, Object> updateByPasswordReset(MemberVO memberVO) throws Exception {
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		MemberVO memberVO2 = memberDAO.selectMember(memberVO);
		boolean bStatus = false; 
		
		if(memberVO2 == null){
			mResult.put("check", "N");
        }else{                
        	//임시비밀번호 생성
    		String tempPassword = StringUtil.randomString();
    		
    		memberVO2.setMbrPw(CryptoUtil.encodeUserPassword(tempPassword));
    		//memberVO2.setTmpPwYn("Y");
    		
    		if(memberDAO.updateByPassword(memberVO2) == 1){
    			
    			String strMailTitle = "VIRUS CLEAN LAB 비밀초기화 안내";
        		String strMailFileName = "findPassword.html";
        		
        		Map<String, String> mailInfo = new HashMap<String, String>();
        		mailInfo.put("$$PASSWORD$$", tempPassword);
        		
        		//메일 발송
                mailSenderUtil.mailSendToUser(memberVO2.getMbrEml(), strMailTitle, strMailFileName, mailInfo);
                
        		bStatus = true;
                mResult.put("check", "Y");  
    		}else{
    			mResult.put("check", "N");
    		}       
        }
		
		mResult.put("status", bStatus);  
		
		return mResult;
	}

    
	/*
     * 연장 안내 메일 발송
     *
     * @see MemberRegisteService#mailGoldExtendMailing(memberVO)
     */
    public Map<String, Object> mailGoldExtendMailing(MemberVO memberVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 

        try{  	
        	
//        	//회원정보 조회
//        	 List<MemberVO> memberList = memberDAO.selectGoldExtendList(memberVO);
//        	
//        	 int iCount = 0;
//        	 String strMailTitle = "カン・ジファンジャパンオフィシャルファンクラブ事務局よりお知らせ";
//        	 String strMailFileName = "memberGoldExtensionNoti.txt";
//        	 
//        	 for(MemberVO vo : memberList)
//        	 {
//        		 if(!vo.getMbrEml().isEmpty()){
//	        		 Map<String, String> mailInfo = new HashMap<String, String>();
//	        		 mailInfo.put("$$USER_NAME$$", vo.getMbrNm());
//	        		 mailInfo.put("$$USER_ID$$", vo.getMbrId());
//	        		 mailInfo.put("$$USER_NO$$", vo.getMbrNo());
//	        		 mailInfo.put("$$START_DATE$$", vo.getMbrDtm());	//시작일
//	        		 mailInfo.put("$$END_DATE$$", vo.getRenewDtm());	//종료일
//	        		 
//	        		 //메일 발송
//	        		 mailSenderUtil.mailSendToUser(vo.getMbrEml(), strMailTitle, strMailFileName, mailInfo);
//	        		 iCount ++;
//        		 }
//        	 }
//        	 
//        	 log.info("== 연장안내 메일 발송 : " + iCount + "==");
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
    }
    
    
    
    /*
     * 기간만료 GOLD회원 처리 : 30일이상 지난회원
     *
     * @see MemberRegisteService#updateBySpecialMemberExpire(memberVO)
     */
    public Map<String, Object> updateBySpecialMemberExpire(MemberVO memberVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 

        try{  	
        	
//        	 //회원정보 조회
//        	 List<MemberVO> memberList = memberDAO.selectSpecialMemberExpireList(memberVO);
//        	
//        	 int iCount = 0;
////        	 String strMailTitle = "カン・ジファンジャパンオフィシャルファンクラブ事務局よりお知らせ";
////        	 String strMailFileName = "memberGoldExtensionNoti.txt";
//        	 
//        	 for(MemberVO vo : memberList)
//        	 {
////        		 if(!vo.getMbrEml().isEmpty()){
////	        		 Map<String, String> mailInfo = new HashMap<String, String>();
////	        		 mailInfo.put("$$USER_NAME$$", vo.getMbrNm());
////	        		 mailInfo.put("$$USER_ID$$", vo.getMbrId());
////	        		 mailInfo.put("$$USER_NO$$", vo.getMbrNo());
////	        		 mailInfo.put("$$START_DATE$$", vo.getMbrDtm());	//시작일
////	        		 mailInfo.put("$$END_DATE$$", vo.getRenewDtm());	//종료일
////	        		 
////	        		 //메일 발송
////	        		 mailSenderUtil.mailSendToUser(vo.getMbrEml(), strMailTitle, strMailFileName, mailInfo);
////        		 }
//        		 
//        		 memberDAO.updateBySpecialMemberExpire(vo);
//        		 iCount ++;
//        	 }
//        	 
//        	 log.info("== GOLD회원 해지 : " + iCount + "==");
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
    }
    
    /*
     * 계정 상태값 변경 / 정보를 삭제한다.
     * - 상태(Y:사용, N:사용중지, D:삭제)  
     */
    public Map<String, Object> updateByStCd(MemberVO memberVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
     
            if(memberDAO.updateByStCd(memberVO) == 1){
                bStatus = true;
            }else{
                throw new IllegalArgumentException();
            }
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
        mResult.put("check", bStatus ? "Y" : "N");
            
        return mResult;
    }
    
    /*
     * 회원 전체 목록을 조회한다.
     */
    public List<XMap> selectMemberAllList(MemberVO memberVO) throws Exception {
        
        Map<String,Object> mSession = multiUtil.getSessionInfo();
//        memberVO.setSiteCd((String)mSession.get("siteCd"));

        return memberDAO.selectMemberAllList(memberVO);
    }

}
