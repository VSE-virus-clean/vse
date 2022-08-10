package vc.virusclean.cmm.service.dao;

import org.springframework.stereotype.Repository;

import jksoft.com.dao.XAbstractDAO;
import vc.virusclean.cmm.vo.SmsCertVO;
import vc.virusclean.cmm.vo.SmsVO;

/**
 * <pre>
 * 보안로그 DAO
 * </pre>
*/
@Repository("smsDAO")
public class SmsDAO extends XAbstractDAO {


    /**
     *  인증 번호 확인 (유효시간 RGST_DTM으로 부터 3분)
     *
     * @param accessLogVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int checkCertNoVerify(SmsCertVO vo) throws Exception {
        return (Integer)select("smsDAO.checkCertNoVerify", vo);
    }

    /**
     * 인증 번호 정보를 등록한다.
     *
     * @param accessLogVO - 저장 할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public Object insertCert(SmsCertVO vo) throws Exception {
        return insert("smsDAO.insertCert", vo);
    }
    
    /**
     * 인증 번호 확인시간을 업데이트 한다.
     *
     * @param codeVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateCertVrfyDtm(SmsCertVO vo) throws Exception {
        return update("smsDAO.updateCertVrfyDtm", vo);
    }
    
    
    /**
     * SMS 발송
     *
     * @param codeVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public Object insertSendSms(SmsVO vo) throws Exception {
        return insert("smsDAO.insertSendSms", vo);
    }
    
    /**
     * 카톡알림 발송
     * @param vo
     * @return
     * @throws Exception
     */
    public Object insertSendKakao(SmsVO vo) throws Exception {
        return insert("smsDAO.insertSendKakao", vo);
    }
    

}
