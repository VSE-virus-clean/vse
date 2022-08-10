package vc.virusclean.admin.member.service;

import java.util.List;
import java.util.Map;

import vc.virusclean.admin.auth.vo.AuthVO;
import vc.virusclean.admin.member.vo.MemberVO;
import jksoft.com.util.XMap;

/**
 * <pre>
 * 클래스 설명을 기술합니다.
 * </pre>
 *
 * @ClassName   : MemberRegisteService.java
 * @Description : @Service 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2017.6.19
 * @version 1.0
 * @see artist.idolmaster.service.MemberService
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017.6.19      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */

public interface MemberService {

    /**
     * 목록을 조회한다.
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectMemberList(MemberVO memberVO) throws Exception;


    /**
     * 정보를 조회한다.
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectMember(MemberVO memberVO) throws Exception;
    

    /**
     * 정보를 수정한다.
     *
     * @param memberVO - 수정할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateMember(MemberVO memberVO) throws Exception;

    
    /**
     * 골드회원관리.
     *
     * @param memberVO - 저장 할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateBySpecialMember(MemberVO memberVO) throws Exception;
    

    /**
     * 정보를 삭제한다.
     *
     * @param memberVO - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> deleteMember(MemberVO memberVO) throws Exception;
    
    
    /**
     * 전체 비밀번호 리셋
     * @return
     * @throws Exception
     */
    public Map<String, Object> updateByAllPasswordReset() throws Exception;
    
    
    /**
     * 초기 비밀번호 리셋
     * @return
     * @throws Exception
     */
    public Map<String, Object> updateByPasswordReset(MemberVO memberVO) throws Exception;
    
    
    /**
     * 연장 안내 메일 발송
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> mailGoldExtendMailing(MemberVO memberVO) throws Exception;
    
    /**
     * 기간만료 GOLD회원 처리 : 30일이상 지난회원
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateBySpecialMemberExpire(MemberVO memberVO) throws Exception;
    
    /**
     * 계정 상태값 변경 / 정보를 삭제한다.
     * - 상태(Y:사용, N:사용중지, D:삭제)  
     *
     * @param boardVO - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateByStCd(MemberVO memberVO) throws Exception;
    
    
    /**
     * 회원 전체 목록을 조회한다.
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public List<XMap> selectMemberAllList(MemberVO memberVO) throws Exception;

}
