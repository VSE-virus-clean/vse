package vc.virusclean.admin.member.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import vc.virusclean.admin.member.vo.MemberVO;
import vc.virusclean.user.vo.LoginVO;
import jksoft.com.dao.XAbstractDAO;
import jksoft.com.util.XMap;

/**
 * <pre>
 * 회원정보
 * </pre>
 *
 * @Class Name  : MemberDAO.java
 * @Description : @DAO 클래스
*/
@Repository("memberDAO")
public class MemberDAO extends XAbstractDAO {
	
    /**
     * 아이디 중복체크
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectMemberCheckDuplicate(MemberVO memberVO) throws Exception {
        return (Integer)select("memberDAO.selectMemberCheckDuplicate", memberVO);
    }
    
    /**
     * 아이디 중복체크
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectMemberCheckDuplicateById(MemberVO memberVO) throws Exception {
        return (Integer)select("memberDAO.selectMemberCheckDuplicateById", memberVO);
    }
    
    /**
     * 핸드폰 중복체크
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectMemberCheckDuplicateByHP(MemberVO memberVO) throws Exception {
        return (Integer)select("memberDAO.selectMemberCheckDuplicateByHP", memberVO);
    }
    
    /**
     * 본인인증값 CI 중복체크
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectMemberCheckDuplicateByDI(MemberVO memberVO) throws Exception {
        return (Integer)select("memberDAO.selectMemberCheckDuplicateByDI", memberVO);
    }

    /**
     * 게시물의 총 갯수를 조회한다.
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectMemberCount(MemberVO memberVO) throws Exception {
        return (Integer)select("memberDAO.selectMemberCount", memberVO);
    }


    /**
     * 목록을 조회한다
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @return List<memberVO> 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<MemberVO> selectMemberList(MemberVO memberVO) throws Exception {
        return (List<MemberVO>)list("memberDAO.selectMemberList", memberVO);
    }


    /**
     * 정보를 조회한다
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @return memberVO
     * @throws Exception
     */
    public MemberVO selectMember(MemberVO memberVO) throws Exception {
        return (MemberVO)select("memberDAO.selectMember", memberVO);
    }
    
    public MemberVO selectMemberApiMod(MemberVO memberVO) throws Exception {
        return (MemberVO)select("memberDAO.selectMemberApiMod", memberVO);
    }
    
    /**
     * 아이디 비번 찾기 확인
     * @param memberVO
     * @return
     * @throws Exception
     */
    public MemberVO selectMemberFind(MemberVO memberVO) throws Exception {
        return (MemberVO)select("memberDAO.selectMemberFind", memberVO);
    }
    
    
    /**
     * 로그인 정보를 조회한다
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @return memberVO
     * @throws Exception
     */
    public MemberVO selectAuth(LoginVO loginVO) throws Exception {
        return (MemberVO)select("memberDAO.selectAuth", loginVO);
    }
    

    /**
     * 정보를 등록한다
     *
     * @param memberVO - 저장 할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public Object insertMember(MemberVO memberVO) throws Exception {
        return insert("memberDAO.insertMember", memberVO);
    }
    
    
    /**
     * 정보를 수정한다
     *
     * @param memberVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateMemberAdm(MemberVO memberVO) throws Exception {
        return update("memberDAO.updateMemberAdm", memberVO);
    }
    
    /**
     * 정보를 수정한다
     *
     * @param memberVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateMember(MemberVO memberVO) throws Exception {
        return update("memberDAO.updateMember", memberVO);
    }
    
    /**
     * 추가 본인인증으로 인한 정보 수정
     * @param memberVO
     * @return
     * @throws Exception
     */
    public int updateMemberCert(MemberVO memberVO) throws Exception {
        return update("memberDAO.updateMemberCert", memberVO);
    }

    /**
     *  SMS/이메일 동의여부
     *
     * @param memberVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateBySmsYn(MemberVO memberVO) throws Exception {
        return update("memberDAO.updateBySmsYn", memberVO);
    }
    
    /**
     *  APP PUSH 동의여부
     *
     * @param memberVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateByPushYn(MemberVO memberVO) throws Exception {
        return update("memberDAO.updateByPushYn", memberVO);
    }
    
    /**
     *  커뮤니티 사용 가능여부
     *
     * @param memberVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateByBbsYn(MemberVO memberVO) throws Exception {
        return update("memberDAO.updateByBbsYn", memberVO);
    }
 
    /**
     *  계정 상태값 변경
     *
     * @param memberVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateByStCd(MemberVO memberVO) throws Exception {
        return update("memberDAO.updateByStCd", memberVO);
    }
    
    
    /**
     *  비밀번호를 수정한다.
     *
     * @param memberVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateByPassword(MemberVO memberVO) throws Exception {
        return update("memberDAO.updateByPassword", memberVO);
    }
    
    /**
     *  본인인증 값 수정
     *
     * @param memberVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateByCertInfo(MemberVO memberVO) throws Exception {
        return update("memberDAO.updateByCertInfo", memberVO);
    }
    
    /**
     *  APP 정보 수정
     *
     * @param memberVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateByAppInfo(MemberVO memberVO) throws Exception {
        return update("memberDAO.updateByAppInfo", memberVO);
    }
    
    /**
     *  회원등급 변경
     *
     * @param memberVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateByGrade(MemberVO memberVO) throws Exception {
        return update("memberDAO.updateByGrade", memberVO);
    }
    
    
    /**
     * 회원탈퇴 -> 관리자
     *
     * @param memberVO - 삭제할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int deleteMemberRegiste(MemberVO memberVO) throws Exception {
        return update("memberDAO.deleteMemberRegiste", memberVO);
    }
    
    /**
     * 회원탈퇴 -> 사용자
     *
     * @param memberVO - 삭제할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int deleteMemberWithdraw(MemberVO memberVO) throws Exception {
        return update("memberDAO.deleteMemberWithdraw", memberVO);
    }
    
    
    /**
     * 회원 전체 목록을 조회한다.
     *
     * @param memberVO - 조회할 정보가 담긴 VO
     * @return List<memberVO> 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<XMap> selectMemberAllList(MemberVO memberVO) throws Exception {
        return (List<XMap>)list("memberDAO.selectMemberAllList", memberVO);
    }

	/**
     *  디바이스토큰 변경
     *
     * @param memberVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateMemberDeviceInfo(MemberVO memberVO) throws Exception {
        return update("memberDAO.updateMemberDeviceInfo", memberVO);
    }

    /**
     * PUSH 대상 목록을 조회한다.
     */
    @SuppressWarnings("unchecked")
    public List<MemberVO> selectMemberPushList() throws Exception {
        return (List<MemberVO>)list("memberDAO.selectMemberPushList");
    }
}
