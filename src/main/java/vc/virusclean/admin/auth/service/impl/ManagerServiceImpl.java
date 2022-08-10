package vc.virusclean.admin.auth.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import vc.virusclean.admin.auth.service.ManagerService;
import vc.virusclean.admin.auth.service.dao.ManagerDAO;
import vc.virusclean.admin.auth.vo.AuthVO;
import jksoft.com.exception.LeaveaTraceException;
import jksoft.com.exception.NoResourceException;
import jksoft.com.service.XAbstractService;
import jksoft.com.util.CryptoUtil;
import jksoft.com.util.StringUtil;

/**
 * <pre>
 * 관리자 정보 관리 서비스 구현
 * </pre>
 * 
 * @ClassName   : ManagerServiceImpl.java
 * @Description : ManagerService 를 구현
 * @author Jeong.Hyoung.Jae 
 * @since 2017.12.25
 * @version 1.0
 * @see vc.virusclean.admin.auth.service.impl.ManagerServiceImpl
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017.12.25      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */
@Service("managerService")
public class ManagerServiceImpl extends XAbstractService implements ManagerService {

    /**
     * ManagerDAO class 선언 (ManagerDAO Class Injection)
     * (ManagerDAO)managerDAO
     */   
    @Resource(name="managerDAO")   
    private ManagerDAO managerDAO;
    

    /*
     * 목록을 조회한다.
     * @see vc.virusclean.admin.auth.service.ManagerService#selectInfoList(vc.virusclean.admin.auth.vo.AuthVO)
     */
    public Map<String, Object> selectInfoList(AuthVO authVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();

        authVO.setTotalRow(managerDAO.selectInfoCount(authVO));
        
        mResult.put("list", managerDAO.selectInfoList(authVO));
        
        mResult.put("searchInfo", authVO);

        return mResult;

    }

    /*
     * 정보를 조회한다.
     * @see vc.virusclean.admin.auth.service.ManagerService#selectInfo(vc.virusclean.admin.auth.vo.AuthVO)
     */
    public Map<String, Object> selectInfo(AuthVO authVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();

        AuthVO authVO2 = managerDAO.selectInfo(authVO);

        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(authVO2 == null){
            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
        }else{                
            mResult.put("info", authVO2);
        }
        
        mResult.put("searchInfo", authVO);
        
        return mResult;

    }
    

    /*
     * 정보를 등록한다.
     * @see vc.virusclean.admin.auth.service.ManagerService#insertInfo(vc.virusclean.admin.auth.vo.AuthVO)
     */
    public Map<String, Object> insertInfo(AuthVO authVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        String message = "";
        
        try{

            Map<String,Object> mSession = multiUtil.getSessionInfo();

            if(managerDAO.selectInfoCheckDuplicate(authVO) == 0)
            {
            	//아이디가 이메일임.
            	authVO.setMgrEml(authVO.getMgrEml());
            	authVO.setMgrPw(CryptoUtil.encodeUserPassword64(authVO.getMgrPwEnc()));
            	authVO.setRgstId((String)mSession.get("userId"));
            	
            	if(authVO.getMenuCds() != null && !authVO.getMenuCds().isEmpty()){
            		StringBuilder code = new StringBuilder();
            		StringBuilder name = new StringBuilder();
            		
            		for(String str : authVO.getMenuCds()){
            			String[] info = str.split("\\|"); 
            			code.append(info[0]).append("|");
            			name.append(info[1]).append("|");
            		}
            		
            		authVO.setMenuCd(code.substring(0, code.length()-1));
            		authVO.setMenu(name.substring(0, name.length()-1));
            	}
                
                Object resultObject = managerDAO.insertInfo(authVO) ;
                
                if(resultObject.getClass() == Integer.class){
                    bStatus = true;
                }
            }else{
            	//아이디 중복되었습니다.
            	message = xMessageSource.getMessage("msg.A12");
                throw new LeaveaTraceException("exception.idDuplicate");
            }
        }catch(LeaveaTraceException leaveaTraceException){
            leaveaTrace(leaveaTraceException.getMessage());
        }catch(Exception exception){
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
        mResult.put("message", message);
            
        return mResult;
    }
    
    /*
     * 정보를 수정한다.
     * @see vc.virusclean.admin.auth.service.ManagerService#updateInfo(vc.virusclean.admin.auth.vo.AuthVO)
     */
    public Map<String, Object> updateInfo(AuthVO authVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{    
            Map<String,Object> mSession = multiUtil.getSessionInfo();
        	authVO.setMdfyId((String)mSession.get("userId"));
        	
        	if(authVO.getMenuCds() != null && !authVO.getMenuCds().isEmpty()){
        		StringBuilder code = new StringBuilder();
        		StringBuilder name = new StringBuilder();
        		
        		for(String str : authVO.getMenuCds()){
        			String[] info = str.split("\\|"); 
        			code.append(info[0]).append("|");
        			name.append(info[1]).append("|");
        		}
        		
        		authVO.setMenuCd(code.substring(0, code.length()-1));
        		authVO.setMenu(name.substring(0, name.length()-1));
        	}
            
        	if(managerDAO.updateInfo(authVO) == 1){
                bStatus = true;
            }else{
                throw new IllegalArgumentException();
            }
        }catch(Exception exception){        
            bStatus = false;
            log.error(exception.getMessage());
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;

    }


    /*
     * 계정 상태값 변경 / 정보를 삭제한다.
     * - 상태(Y:사용, N:사용중지, D:삭제)  
     * @see vc.virusclean.admin.auth.service.ManagerService#updateByStCd(vc.virusclean.admin.auth.vo.AuthVO)
     */
    public Map<String, Object> updateByStCd(AuthVO authVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
            authVO.setMdfyId((String)mSession.get("userId"));
     
            if(managerDAO.updateByStCd(authVO) == 1){
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
     * 비밀번호 초기화
     * @see vc.virusclean.admin.auth.service.ManagerService#updateByPassword(vc.virusclean.admin.auth.vo.AuthVO)
     */
    public Map<String, Object> updateByPassword(AuthVO authVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
            
            AuthVO authVO2 = managerDAO.selectInfo(authVO);
            
            if(authVO2 == null){
                throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
            }else{                
            	 String tempPw = StringUtil.randomString();
            	 
            	 authVO2.setMgrPw(CryptoUtil.encodeUserPassword(tempPw));
                 authVO2.setMdfyId((String)mSession.get("userId"));
          
                 if(managerDAO.updateByPassword(authVO2) == 1){
                     bStatus = true;
                     mResult.put("pwd", tempPw);
                 }else{
                     throw new IllegalArgumentException();
                 }
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
     * 아이디 중복체크
     * @see vc.virusclean.admin.auth.service.ManagerService#selectInfoCheckDuplicate(vc.virusclean.admin.auth.vo.AuthVO)
     */
    public Map<String, Object> selectInfoCheckDuplicate(AuthVO authVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(managerDAO.selectInfoCheckDuplicate(authVO) > 0){
        	 mResult.put("status", false);  
             mResult.put("check", "N");   
        }else{  
        	mResult.put("status", true);  
            mResult.put("check", "Y");   
        }
        
        return mResult;
	}

}
