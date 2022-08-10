package vc.virusclean.admin.auth.service;

import java.util.List;
import java.util.Map;

import vc.virusclean.admin.auth.vo.AuthVO;
import vc.virusclean.admin.auth.vo.LoginVO;

/**
 * <pre>
 * 로그인 / 비밀번호 변경 / 세션처리
 * </pre>
 *
 * @ClassName   : AuthService.java
 * @Description : @Service 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2017.12.25
 * @version 1.0
 * @see vc.virusclean.admin.auth.service.AuthService
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017.12.25      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */

public interface AuthService {

    /**
     * 로그인 처리를 한다.
     * 
     * - 비밀번호 5회 오류시 휴면처리
     * - 최종로그인 45일 이상시 휴면처리
     * - 비밀번호 등록일 45일 이상시 비밀번호 변경해야함. -> 로그인다시 해야함.
     * - 임시비밀번호로 로그인시 비밀번호 변경해야함. -> 로그인다시 해야함.
     * 
     * @param authVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectAuth(LoginVO loginVO) throws Exception;
	
    /**
     * 정보를 수정한다.
     *
     * @param authVO - 수정할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateNewPassword(LoginVO loginVO) throws Exception;
    
    /**
     * 개인정보를 수정한다.
     *
     * @param authVO - 수정할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateAuthInfo(LoginVO loginVO) throws Exception;
    
    /**
     * 슈퍼 관리자를 조회한다.
     *
     * @param authVO - 수정할 정보가 담긴 VO
     * @throws Exception
     */
    public List<AuthVO> selectSuperAdminList() throws Exception;

}
