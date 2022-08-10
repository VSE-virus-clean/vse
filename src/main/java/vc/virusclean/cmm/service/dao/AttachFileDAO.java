package vc.virusclean.cmm.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jksoft.com.dao.XAbstractDAO;
import jksoft.com.web.vo.AttachFileVO;

/**
 * <pre>
 * 첨부파일 DAO
 * </pre>
 *
 * @Class Name  : AttachFileDAO.java
 * @Description : @DAO 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2014.7.22
 * @version 1.0
 * @see vc.virusclean.cmm.service.dao.AttachFileDAO
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2014.7.22      Jeong.Hyoung.Jae        최초생성
 * </pre>
 * 
*/
@Repository("attachFileDAO")
public class AttachFileDAO extends XAbstractDAO {

    /**
     * Sequence 를 조회한다.
     *
     * @return int
     * @throws Exception
     */
    public int selectAttachFileMax() throws Exception {
        return (Integer)select("attachFileDAO.selectAttachFileMax", null);
    }
    
    /**
     * 게시물의 총 갯수를 조회한다.
     *
     * @param attachFileVO - 조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int selectAttachFileCount(AttachFileVO attachFileVO) throws Exception {
        return (Integer)select("attachFileDAO.selectAttachFileCount", attachFileVO);
    }


    /**
     * 정보를 등록한다
     *
     * @param attachFileVO - 저장 할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public Object insertAttachFile(AttachFileVO attachFileVO) throws Exception {
        return insert("attachFileDAO.insertAttachFile", attachFileVO);
    }


    /**
     * 정보를 삭제한다
     *
     * @param attachFileVO - 삭제할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int deleteAttachFile(AttachFileVO attachFileVO) throws Exception {
        return update("attachFileDAO.deleteAttachFile", attachFileVO);
    }


    /**
     * 정보를 조회한다
     *
     * @param attachFileVO - 조회할 정보가 담긴 VO
     * @return AttachFileVO
     * @throws Exception
     */
    public AttachFileVO selectAttachFile(AttachFileVO attachFileVO) throws Exception {
        return (AttachFileVO)select("attachFileDAO.selectAttachFile", attachFileVO);
    }


    /**
     * 목록을 조회한다
     *
     * @param attachFileVO - 조회할 정보가 담긴 VO
     * @return List<AttachFileVO> 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<AttachFileVO> selectAttachFileList(AttachFileVO attachFileVO) throws Exception {
        return (List<AttachFileVO>)list("attachFileDAO.selectAttachFileList", attachFileVO);
    }
    
    /**
     * 다운로드 횟수를 증가 한다.
     *
     * @param attachFileVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateAttachFileByDnlOft(AttachFileVO attachFileVO) throws Exception {
        return update("attachFileDAO.updateAttachFileByDnlOft", attachFileVO);
    }
    
    /**
     * 파일 연결정보를 변경한다.
     *
     * @param attachFileVO - 수정할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    public int updateAttachFile(AttachFileVO attachFileVO) throws Exception {
        return update("attachFileDAO.updateAttachFile", attachFileVO);
    }

}
