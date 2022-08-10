package vc.virusclean.cmm.service;

import java.util.Map;

import vc.virusclean.cmm.vo.BoardVO;
import vc.virusclean.cmm.vo.CodeEnvVO;
import vc.virusclean.cmm.vo.CodeVO;

/**
 * <pre>
 * 공통코드 관리 서비스
 * </pre>
 *
 * @ClassName   : CodeService.java
 * @Description : @Service 클래스
 */

public interface CodeService {

    /**
     * 목록을 조회한다.
     *
     * @param strCodeCd - 조회부모코드
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectCodeList(String strCodeCd) throws Exception;
    
    /**
     * 목록을 조회한다.
     *
     * @param codeVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectCodeList(CodeVO vo) throws Exception;
    public Map<String, Object> selectCodeCateList(CodeVO vo) throws Exception;
    
    /**
     * 정보를 조회한다.
     *
     * @param codeVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public CodeVO selectCode(String strCodeCd) throws Exception;
    
    /**
     * 정보를 조회한다.
     *
     * @param codeVO
     * @return
     * @throws Exception
     */
    public CodeVO selectCode(CodeVO vo) throws Exception;
	
    /**
     * 정보를 수정한다.
     *
     * @param codeVO - 수정할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateCode(CodeVO vo) throws Exception;
    

	/**
	 * 환경설정 코드 저회
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectCodePreferences() throws Exception;
	
	/**
     * 환경설정 코드 정보를 수정한다.
     *
     * @param codeVO - 수정할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> updateCodePreference(CodeEnvVO vo) throws Exception;


    /**
     * 코드 등록
     */
    public Map<String, Object> insertCode(CodeVO vo) throws Exception;
    
    
    /**
     * 정보를 삭제한다.
     */
    public Map<String, Object> deleteCode(CodeVO vo) throws Exception;
    
    /**
     * 정렬순서 변경
     */
	public Map<String, Object> updateCodeBySort(CodeVO vo) throws Exception;

}
