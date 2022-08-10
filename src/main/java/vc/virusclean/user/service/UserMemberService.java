package vc.virusclean.user.service;

import java.util.Map;

import vc.virusclean.admin.member.vo.MemberVO;
import vc.virusclean.user.vo.LoginVO;

/**
 * <pre>
 * 클래스 설명을 기술합니다.
 * </pre>
 *
 * @ClassName   : UserMemberService.java
 * @Description : @Service 클래스
 */

public interface UserMemberService {

	/**
     * 정보를 조회한다.
     * @throws Exception
     */
    public Map<String, Object> selectMember() throws Exception;
    public Map<String, Object> selectMemberApiMod(MemberVO memberVO) throws Exception;
    
    /**
     * 본인인증용 사용자 정보 조회
     * @throws Exception
     */
    public Map<String, Object> selectMemberCert() throws Exception;
    
    /*
     * APP에서 mbrSn으로  사용자 정보 조회
     */
    public Map<String, Object> selectApiMember(LoginVO loginVO) throws Exception;
    
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
    
    public Map<String, Object> selectPrivacyAuth(LoginVO loginVO) throws Exception;
    

    /**
     * 실버회원으로 등록한다.
     *
     * @param memberVO - 저장 할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> insertMember(MemberVO memberVO) throws Exception;
    public Map<String, Object> insertAppMember(MemberVO memberVO) throws Exception;
    
    /**
     * 골드회원 등록신청을 한다.
     *
     * @param memberVO - 저장 할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateBySpecialMemberStandby(MemberVO memberVO) throws Exception;


    /**
     * 정보를 수정한다.
     *
     * @param memberVO - 수정할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateMember(MemberVO memberVO) throws Exception;
    
    /**
     * 본인인증 관련 정보 갱신한다.
     * @param memberVO
     * @return
     * @throws Exception
     */
    public Map<String, Object> updateCertInfo(MemberVO memberVO) throws Exception;


    /**
     * 정보를 삭제한다.
     *
     * @param memberVO - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> deleteMember(MemberVO memberVO) throws Exception;
    
    /**
     * 정보 중복확인
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectMemberCheckDuplicate(MemberVO memberVO) throws Exception;


    /**
     * 임시비밀번호 변경
     * @param loginVO
     * @return
     */
    public Map<String, Object> updateNewPassword(LoginVO loginVO) throws Exception;

	/**
     * 디바이스토큰 수정한다.
     *
     * @param memberVO - 수정할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateMemberDeviceInfo(MemberVO memberVO) throws Exception;

}
