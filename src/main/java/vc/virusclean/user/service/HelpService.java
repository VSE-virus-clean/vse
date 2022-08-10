package vc.virusclean.user.service;

import java.util.Map;

import vc.virusclean.admin.member.vo.MemberVO;
import vc.virusclean.cmm.vo.SmsCertVO;

/**
 * <pre>
 * 클래스 설명을 기술합니다.
 * </pre>
 *
 * @ClassName   : HelpService.java
 * @Description : @Service 클래스
 */

public interface HelpService {

    /**
     * ID 찾기
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> findId(MemberVO vo, SmsCertVO vo2) throws Exception;
    
    /**
     * PASSWORD 찾기
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> findPassword(MemberVO vo, SmsCertVO vo2) throws Exception;
    
    /**
     * SMS 인증 번호 발급
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> smsCertNoSend(SmsCertVO vo) throws Exception;
    
}
