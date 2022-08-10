package vc.virusclean.cmm.service;

import java.util.Map;

import vc.virusclean.cmm.vo.EditorPhotoVO;
import jksoft.com.web.vo.AttachFileVO;

/**
 * <pre>
 * 첨부파일 관리 
 * </pre>
 *
 * @ClassName   : AttachFileService.java
 * @Description : @Service 클래스
 * @author Jeong.Hyoung.Jae 
 * @since 2014.7.22
 * @version 1.0
 * @see vc.virusclean.cmm.service.AttachFileService
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2014.7.22      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */

public interface AttachFileService {
    
    /**
     * 게시판관리 첨부파일 정보를 등록한다.

     * @param boardVO
     * @param mSession
     * @return
     * @throws Exception
     */
    public Map<String, Object> insertAttachFile(Object object, Map<String, Object> mSession) throws Exception;
    
    /**
     * 정보를 삭제한다.
     *
     * @param attachFileVO - 삭제할 정보가 담긴 VO
     * @return Map<String, Object>  세션 정보
     * @throws Exception
     */
    public Map<String, Object> deleteAttachFile(AttachFileVO attachFileVO) throws Exception;

    /**
     * 정보를 조회한다.
     *
     * @param attachFileVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectAttachFile(AttachFileVO attachFileVO) throws Exception;

    /**
     * 목록을 조회한다.
     *
     * @param menuId    메뉴아이디(MENU_ID)
     * @param contenSeq 컨텐츠일렬번호(COTN_SN)
     * @param mSession  세션정보
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectAttachFileList(String menuId, int contenSeq) throws Exception;
    
    /**
     * 목록을 조회한다.
     *
     * @param menuId    메뉴아이디(MENU_ID)
     * @param contenSeq 컨텐츠일렬번호(COTN_SN)
     * @param mSession  세션정보
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectAttachFileList(String menuId, int contenSeq, String filScnCd) throws Exception;
    
    /**
     * 목록을 조회한다.
     *
     * @param attachFileVO - 조회할 정보가 담긴 VO
     * @throws Exception
     */
    public Map<String, Object> selectAttachFileList(AttachFileVO attachFileVO) throws Exception;

    /**
     * 첨부파일 업로드
     *
     * @param editorPhotoVO
     * @param sessionInfo
     * @return
     */
    public Map<String, Object> manageEditorUpload(EditorPhotoVO editorPhotoVO, Map<String, Object> mSession) throws Exception;

}
