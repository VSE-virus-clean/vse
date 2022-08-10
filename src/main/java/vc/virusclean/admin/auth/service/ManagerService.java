package vc.virusclean.admin.auth.service;

import java.util.Map;

import vc.virusclean.admin.auth.vo.AuthVO;

/**
 * <pre>
 * 관리자 정보 서비스
 * </pre>
 *
 * @ClassName   : ManagerService.java
 * @Description : @Service 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2017.12.25
 * @version 1.0
 * @see vc.virusclean.admin.auth.service.ManagerService
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017.12.25      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */

public interface ManagerService {

    /**
     * 목록을 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectInfoList(AuthVO authVO) throws Exception;


    /**
     * 정보를 조회한다.
     *
     * @param boardVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectInfo(AuthVO authVO) throws Exception;
    
    /**
     * 정보를 등록한다.
     *
     * @param boardVO - 저장 할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> insertInfo(AuthVO authVO) throws Exception;
    
    /**
     * 정보를 수정한다.
     *
     * @param boardVO - 수정할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateInfo(AuthVO authVO) throws Exception;

    /**
     * 계정 상태값 변경 / 정보를 삭제한다.
     * - 상태(Y:사용, N:사용중지, D:삭제)  
     *
     * @param boardVO - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateByStCd(AuthVO authVO) throws Exception;
    
    /**
     * 비밀번호를 수정한다.
     *
     * @param boardVO - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateByPassword(AuthVO authVO) throws Exception;
    
    /**
     * 아이디 중복체크
     *
     * @param boardVO - 삭제할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectInfoCheckDuplicate(AuthVO authVO) throws Exception;
    
}
