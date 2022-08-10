package vc.virusclean.cmm.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jksoft.com.dao.XAbstractDAO;
import vc.virusclean.cmm.vo.CodeEnvVO;
import vc.virusclean.cmm.vo.CodeVO;

/**
 * <pre>
 * 공통코드 관리 DAO
 * </pre>
 *
 * @Class Name  : CodeDAO.java
 * @Description : @DAO 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2017.12.25
 * @version 1.0
 * @see vc.virusclean.cmm.service.dao.CodeDAO
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017.12.25      Jeong.Hyoung.Jae        최초생성
 * </pre>
 * 
*/
@Repository("codeDAO")
public class CodeDAO extends XAbstractDAO {


    /**
     * Sequence 를 MAX로 조회한다.
     *
     * @return int
     * @throws Exception
     */
    public int selectCodeMax() throws Exception {
        return (Integer)select("codeDAO.selectCodeMax");
    }


    /**
     * 게시물의 총 갯수를 조회한다.
     *
     * @param codeVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectCodeCount(CodeVO codeVO) throws Exception {
        return (Integer)select("codeDAO.selectCodeCount", codeVO);
    }


    /**
     * 목록을 조회한다
     *
     * @param codeVO - 조회할 정보가 담긴 VO
     * @return List<CodeVO> 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<CodeVO> selectCodeList(CodeVO codeVO) throws Exception {
        return (List<CodeVO>)list("codeDAO.selectCodeList", codeVO);
    }
    
    @SuppressWarnings("unchecked")
    public List<CodeVO> selectCodeCateList() throws Exception {
        return (List<CodeVO>)list("codeDAO.selectCodeCateList");
    }


    /**
     * 코드 값을 조회한다
     *
     * @param codeVO - 조회할 정보가 담긴 VO
     * @return CodeVO
     * @throws Exception
     */
    public CodeVO selectCode(CodeVO codeVO) throws Exception {
        return (CodeVO)select("codeDAO.selectCode", codeVO);
    }
    
    /**
     * 정보를 수정한다
     *
     * @param codeVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateCode(CodeVO codeVO) throws Exception {
    	return update("codeDAO.updateCode", codeVO);
    }
    
    /**
     * 사이트 설정 값을 조회한다
     *
     * @param codeVO - 조회할 정보가 담긴 VO
     * @return CodeVO
     * @throws Exception
     */
    public CodeEnvVO selectCodePreferences() throws Exception {
        return (CodeEnvVO)select("codeDAO.selectCodePreferences", null);
    }
    
    
    /**
     * 상품카테고리 목록 조회
     */
    @SuppressWarnings("unchecked")
    public List<CodeVO> selectCategoryList(CodeVO codeVO) throws Exception {
        return (List<CodeVO>)list("codeDAO.selectCategoryList", codeVO);
    }
    
    /**
     * 코드 중복 확인
     */
    public int selectCategoryCodeCount(CodeVO codeVO) throws Exception {
        return (Integer)select("codeDAO.selectCategoryCodeCount", codeVO);
    }
    
    /**
     * 상태코드 변경 
     * - Y:사용, N:사용중지, D:삭제
     */
    public int updateUseYn(CodeVO codeVO) throws Exception {
    	return update("codeDAO.updateUseYn", codeVO);
    }
    
    
    /**
     * 정보를 등록한다
     */
    public Object insertCode(CodeVO codeVO) throws Exception {
        return insert("codeDAO.insertCode", codeVO);
    }
    
    public Object insertCodeCate1(CodeVO codeVO) throws Exception {
        return insert("codeDAO.insertCodeCate1", codeVO);
    }
    
    public Object insertCodeCate2(CodeVO codeVO) throws Exception {
        return insert("codeDAO.insertCodeCate2", codeVO);
    }
    
    /**
     * 정보를 수정한다.
     */
    public int updateCategoryCode(CodeVO codeVO) throws Exception {
    	return update("codeDAO.updateCategoryCode", codeVO);
    }
    
    /**
     * 정렬순서 변경
     * - Service에서 For문으로 하나씩 던져줄것.
     */
    public int updateCategoryCodeByStNo(CodeVO codeVO) throws Exception {
    	return update("codeDAO.updateCategoryCodeByStNo", codeVO);
    }
    
}
