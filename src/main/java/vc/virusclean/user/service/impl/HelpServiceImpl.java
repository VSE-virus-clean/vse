package vc.virusclean.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import jksoft.com.service.XAbstractService;
import jksoft.com.util.MailSenderUtil;
import jksoft.com.util.MultiUtil;
import jksoft.com.util.SmsSenderUtil;
import jksoft.com.util.StringUtil;
import vc.virusclean.admin.member.service.dao.MemberDAO;
import vc.virusclean.admin.member.vo.MemberVO;
import vc.virusclean.cmm.service.dao.SmsDAO;
import vc.virusclean.cmm.vo.SmsCertVO;
import vc.virusclean.user.service.HelpService;

/**
 * <pre>
 * 클래스 설명을 기술합니다.
 * </pre>
 * 
 * @ClassName   : HelpServiceImpl.java
 * @Description : HelpService 를 구현
 */
@Service("helpService")
public class HelpServiceImpl extends XAbstractService implements HelpService {

	@Resource(name="mailSenderUtil")
	private MailSenderUtil mailSenderUtil;
	
	@Resource(name="smsSenderUtil")
    private SmsSenderUtil smsSenderUtil;
	
	@Resource(name="multiUtil")
	private MultiUtil multiUtil;

	@Resource(name="memberDAO")   
    private MemberDAO memberDAO;
    
    @Resource(name="smsDAO")   
    private SmsDAO smsDAO;

    
    
    

    @Override
	public Map<String, Object> findId(MemberVO memberVO, SmsCertVO smsCertVO) throws Exception {
    	
    	boolean bStatus = false; 

    	Map<String, Object> mResult = new HashMap<String, Object>();
        
    	memberVO.setSearchType("findID");
    	memberVO.setMbrHp(memberVO.getMbrHp().replaceAll("-", ""));
        MemberVO memberVO2 = memberDAO.selectMemberFind(memberVO);

        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(memberVO2 == null){
        	mResult.put("errMsg", "회원정보를 찾을수 없습니다.");   
        }else{                
        	memberVO2.setMbrPw("");
        	
        	//인증번호 확인
        	smsCertVO.setHpNo(memberVO.getMbrHp());
        	if(smsDAO.checkCertNoVerify(smsCertVO) > 0){
        		smsDAO.updateCertVrfyDtm(smsCertVO);
        		
        		mResult.put("info", memberVO2);
        		bStatus = true;
        	}else{
        		mResult.put("errMsg", "인증번호가 올바르지 않습니다. 확인 후 재입력해주시기 바랍니다");   
        	}
        	
        }
        	
        mResult.put("status", bStatus);
    	
    	return mResult;
	}


	@Override
	public Map<String, Object> findPassword(MemberVO memberVO, SmsCertVO smsCertVO) throws Exception {
		boolean bStatus = false; 

    	Map<String, Object> mResult = new HashMap<String, Object>();
        
    	memberVO.setSearchType("findPassword");
    	memberVO.setMbrHp(memberVO.getMbrHp().replaceAll("-", ""));
        MemberVO memberVO2 = memberDAO.selectMemberFind(memberVO);

        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(memberVO2 == null){
        	mResult.put("check", "N");   
        	mResult.put("errMsg", "회원정보를 찾을수 없습니다.");   
        }else{                
        	
        	//인증번호 확인
        	smsCertVO.setHpNo(memberVO.getMbrHp());
        	if(smsDAO.checkCertNoVerify(smsCertVO) > 0){
        		smsDAO.updateCertVrfyDtm(smsCertVO);
        		
//         		//임시비밀번호 생성
//         		String tempPassword = StringUtil.randomString();
//         			
//         		//패스워드 갱신
//         		memberVO2.setMbrPw(CryptoUtil.encodeUserPassword(tempPassword));
         		
//         		memberVO2.setTmpPwYn("N");
         		
//         	    if(memberDAO.updateByPassword(memberVO2) == 1){
//         			
//         		}else{
//         			mResult.put("check", "N");
//         		}
         	    
         	    HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
                httpServletRequest.getSession(false).invalidate();  
                HttpSession httpSession = httpServletRequest.getSession(true);
                 
                //임시 세션 생성
                httpSession.setAttribute("tmpMgrSn", memberVO2.getMbrSn());      
                httpSession.setAttribute("tmpMgrId", memberVO2.getMbrId());       
                httpSession.setAttribute("tmpMgrPw", memberVO2.getMbrPw());
                httpSession.setAttribute("tmpMgrNm", memberVO2.getMbrNm());
                httpSession.setAttribute("tmpLogin", "Y");
                
                memberVO2.setMbrPw("");
                mResult.put("info", memberVO2);
                
        		bStatus = true;
        	}else{
        		mResult.put("errMsg", "인증번호가 올바르지 않습니다. 확인 후 재입력해주시기 바랍니다");   
        	}
        }
        	
        mResult.put("status", bStatus);
    	
    	return mResult;
	}
	
	@Override
	public Map<String, Object> smsCertNoSend(SmsCertVO smsCertVO) throws Exception {
		boolean bStatus = false; 

    	Map<String, Object> mResult = new HashMap<String, Object>();
            
			
		//인증번호
		smsCertVO.setCertNo(StringUtil.randomInt(6));
		smsCertVO.setHpNo(smsCertVO.getHpNo().replaceAll("-", ""));
		Object resultObject = smsDAO.insertCert(smsCertVO);
		
		if(resultObject.getClass() == Integer.class){
			
			smsCertVO.setCertSn((Integer)resultObject);

			//발송 문구
			String smsContent = "[바이러스클린랩] 휴대폰본인확인 인증번호 [" + smsCertVO.getCertNo() + "]";
			
    		//SMS 발송
			smsSenderUtil.smsSend("CERT", smsCertVO.getHpNo(), smsContent);
            
    		bStatus = true;
            mResult.put("info", smsCertVO);  
		}
        	
        mResult.put("status", bStatus);
    	
    	return mResult;
	}
}
