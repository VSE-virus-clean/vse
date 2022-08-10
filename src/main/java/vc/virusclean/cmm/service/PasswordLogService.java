package vc.virusclean.cmm.service;

import java.util.Map;

import vc.virusclean.cmm.vo.PasswordLogVO;

/**
 * <pre>
 * 비밀번호변경로그 서비스
 * </pre>
 *
 * @ClassName   : PasswordLogService.java
 * @Description : @Service 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2016.1.15
 * @version 1.0
 * @see vc.virusclean.cmm.service.PasswordLogService
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2016.1.15      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */

public interface PasswordLogService {

    /**
     * 최근 비밀번호 여부 확인
     *
     * @param passwordLogVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public int selectPasswordLogCount(PasswordLogVO passwordLogVO) throws Exception;

	
    /**
     * 비밀번호 변경 이력 등록
     *
     * @param passwordLogVO - 저장 할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> insertPasswordLog(PasswordLogVO passwordLogVO) throws Exception;

}
