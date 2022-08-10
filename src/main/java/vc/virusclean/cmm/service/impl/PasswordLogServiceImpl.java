package vc.virusclean.cmm.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import vc.virusclean.cmm.service.PasswordLogService;
import vc.virusclean.cmm.service.dao.PasswordLogDAO;
import vc.virusclean.cmm.vo.PasswordLogVO;
import jksoft.com.service.XAbstractService;

/**
 * <pre>
 * 비밀번호변경로그 서비스 구현
 * </pre>
 * 
 * @ClassName   : PasswordLogServiceImpl.java
 * @Description : PasswordLogService 를 구현
 * @author Jeong.Hyoung.Jae 
 * @since 2016.1.15
 * @version 1.0
 * @see vc.virusclean.cmm.service.impl.PasswordLogServiceImpl
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2016.1.15      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */
@Service("passwordLogService")
public class PasswordLogServiceImpl extends XAbstractService implements PasswordLogService {

    /**
     * PasswordLogDAO class 선언 (PasswordLogDAO Class Injection)
     * (PasswordLogDAO)passwordLogDAO
     */   
    @Resource(name="passwordLogDAO")   
    private PasswordLogDAO passwordLogDAO;


    /*
     * 최근 비밀번호 여부 확인
     * @see vc.virusclean.cmm.service.PasswordLogService#selectPasswordLogCount(vc.virusclean.cmm.vo.PasswordLogVO)
     */
    @Override
    public int selectPasswordLogCount(PasswordLogVO passwordLogVO) throws Exception {
        return passwordLogDAO.selectPasswordLogCount(passwordLogVO);
    }

    
    /*
     * 비밀번호 변경 이력 등록
     * @see vc.virusclean.cmm.service.PasswordLogService#insertPasswordLog(vc.virusclean.cmm.vo.PasswordLogVO)
     */
    public Map<String, Object> insertPasswordLog(PasswordLogVO passwordLogVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{
            passwordLogDAO.insertPasswordLog(passwordLogVO) ;
            bStatus = true;
        }catch(Exception exception){
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
    }

}
