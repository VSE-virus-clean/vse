package vc.virusclean.cmm.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import jksoft.com.exception.NoResourceException;
import jksoft.com.service.XAbstractService;
import jksoft.com.util.AttachFileUploadUtil;
import jksoft.com.web.vo.AttachFileVO;
import jksoft.com.web.vo.FileVO;
import vc.virusclean.admin.shop.vo.CouponMetaVO;
import vc.virusclean.admin.shop.vo.OrderHistoryVO;
import vc.virusclean.admin.shop.vo.OriginProductVO;
import vc.virusclean.admin.shop.vo.ProductVO;
import vc.virusclean.cmm.service.AccessLogService;
import vc.virusclean.cmm.service.AttachFileService;
import vc.virusclean.cmm.service.dao.AttachFileDAO;
import vc.virusclean.cmm.vo.BoardVO;
import vc.virusclean.cmm.vo.EditorPhotoVO;
import vc.virusclean.cmm.vo.UserBoardVO;

/**
 * <pre>
 * 첨부파일 관리 
 * </pre>
 * 
 * @ClassName   : AttachFileServiceImpl.java
 * @Description : AttachFileService 를 구현
 */
@Service("attachFileService")
public class AttachFileServiceImpl extends XAbstractService implements AttachFileService {

    @Resource(name = "attachFileDAO")   
    private AttachFileDAO attachFileDAO;
    @Resource(name="attachFileUploadUtil")
    private AttachFileUploadUtil attachFileUploadUtil;

    @Resource(name = "accessLogService")
    private AccessLogService accessLogService;

    /*
     * 파일 저장 경로
     */
    @Value(value="#{global['file.system.path.default']}")
    private File fileSavePath;
    

    /*
     * 정보를 등록한다.
     *
     * @see vc.virusclean.cmm.service.AttachFileService#insertAttachFile(vc.virusclean.cmm.vo.AttachFileVO)
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> insertAttachFile(Object object, Map<String, Object> mSession) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        //첨부파일 목록
        List<AttachFileVO> lAttachFileVO = null;

        boolean bStatus = false; 
        
        try{
            if(object.getClass() == ArrayList.class){
                lAttachFileVO = (List<AttachFileVO>)object;
            }else if(object.getClass() == BoardVO.class){
                //일반게시판
                BoardVO boardVO = (BoardVO)object;
                
                lAttachFileVO = this.validFileCheck(boardVO);
                
                if(!lAttachFileVO.isEmpty()){
                    //공통값 설정
                    for(AttachFileVO vo : lAttachFileVO){
                        vo.setCotnSn(boardVO.getBlcSn());            //컨텐츠 일렬번호
                        vo.setMenuCd(boardVO.getLgrpCd());            //메뉴코드
                        vo.setRgstId(boardVO.getRgstId());
                        vo.setMdfyId(boardVO.getMdfyId());
                    }
                }else{
                    bStatus = true;
                }
            }else if(object.getClass() == UserBoardVO.class){
                //사용자게시판
            	UserBoardVO userBoardVO = (UserBoardVO)object;
                
                lAttachFileVO = this.validFileCheck(userBoardVO);
                
                if(!lAttachFileVO.isEmpty()){
                    //공통값 설정
                    for(AttachFileVO vo : lAttachFileVO){
                        vo.setCotnSn(userBoardVO.getBlcSn());            //컨텐츠 일렬번호
                        vo.setMenuCd(userBoardVO.getLgrpCd());            //메뉴코드
                        vo.setRgstId(userBoardVO.getMbrId());
                        vo.setMdfyId(userBoardVO.getMbrId());
                    }
                }else{
                    bStatus = true;
                }
            }else if(object.getClass() == ProductVO.class){
                //제품
            	ProductVO productVO = (ProductVO)object;
                
                lAttachFileVO = this.validFileCheck(productVO);
                
                if(!lAttachFileVO.isEmpty()){
                    //공통값 설정
                    for(AttachFileVO vo : lAttachFileVO){
                        vo.setCotnSn(productVO.getPrdSn());            //컨텐츠 일렬번호
                        vo.setMenuCd("PRODUCT");            		   //메뉴코드
                        vo.setRgstId(productVO.getRgstId());
                        vo.setMdfyId(productVO.getMdfyId());
                    }
                }else{
                    bStatus = true;
                }
            }else if(object.getClass() == OriginProductVO.class){
                //정품등록
            	OriginProductVO originProductVO = (OriginProductVO)object;
                
                lAttachFileVO = this.validFileCheck(originProductVO);
                
                if(!lAttachFileVO.isEmpty()){
                    //공통값 설정
                    for(AttachFileVO vo : lAttachFileVO){
                        vo.setCotnSn(originProductVO.getOrgSn());            //컨텐츠 일렬번호
                        vo.setMenuCd("ORG");            		   //메뉴코드
                        vo.setRgstId(Integer.toString(originProductVO.getMbrSn()));
                        vo.setMdfyId(Integer.toString(originProductVO.getMbrSn()));
                    }
                }else{
                    bStatus = true;
                }
            }else if(object.getClass() == OrderHistoryVO.class){
                //주문히스토리
            	OrderHistoryVO orderHistoryVO = (OrderHistoryVO)object;
                
                lAttachFileVO = this.validFileCheck(orderHistoryVO);
                
                if(!lAttachFileVO.isEmpty()){
                    //공통값 설정
                    for(AttachFileVO vo : lAttachFileVO){
                        vo.setCotnSn(orderHistoryVO.getOrderHisSn());            //컨텐츠 일렬번호
                        vo.setMenuCd("ORDER_HIST");            		   //메뉴코드
                        vo.setRgstId(Integer.toString(orderHistoryVO.getMbrSn()));
                        vo.setMdfyId(Integer.toString(orderHistoryVO.getMbrSn()));
                    }
                }else{
                    bStatus = true;
                }
            }else if(object.getClass() == CouponMetaVO.class){
                //주문히스토리
            	CouponMetaVO couponMetaVO = (CouponMetaVO)object;
                
                lAttachFileVO = this.validFileCheck(couponMetaVO);
                
                if(!lAttachFileVO.isEmpty()){
                    //공통값 설정
                    for(AttachFileVO vo : lAttachFileVO){
                        vo.setCotnSn(couponMetaVO.getCupMetaSn());            //컨텐츠 일렬번호
                        vo.setMenuCd("COUPON");            		   //메뉴코드
                        vo.setRgstId(couponMetaVO.getRgstId());
                        vo.setMdfyId(couponMetaVO.getMdfyId());
                    }
                }else{
                    bStatus = true;
                }
            }
            
            
            /*
             * 파일 등록
             */
            if( lAttachFileVO.size() > 0)
            {
                log.debug("== 첨부 파일 갯수 : " + lAttachFileVO.size());
                
                int iCountAll = 0;              //전체파일수
                int iCountUploadSuccess = 0;    //업로드성공 파일수
                int iCountInsertSuccess = 0;    //등록 성공 파일수
                
                //첨부파일 목록
                List<AttachFileVO> lAttachFileVO2 = attachFileUploadUtil.fileUpload(lAttachFileVO);
                
                iCountAll = lAttachFileVO2.size();
                
                
                /*
                 * AttcFilSn 0이면 입력이다.
                 * AttcFilSn 0이 아니면 기존것 삭제후에 입력이다.
                 * AttcFilSn 0이 아니고 userYn이 n이면 삭제한다. 삭제후에 파일이 잇는지 확인.
                 */
                for(AttachFileVO vo : lAttachFileVO2)
                {
                    //순수 입력 파일
                    if((Boolean)vo.getStatus() && vo.getAttcFilSn() == 0)
                    {
                        iCountUploadSuccess ++;
                        
                        //입력
                        Object resultObject = attachFileDAO.insertAttachFile(vo);
                        
                        if(resultObject.getClass() == Integer.class){
                            vo.setAttcFilSn((Integer)resultObject);
                            
                            //공통로그
                            //logService.insertLog(vo, mSession);
                            iCountInsertSuccess ++;
                        }
                    }
                    else if((Boolean)vo.getStatus() && "N".equals(vo.getUseYn()))
                    {
                        //기존번호 삭제
                        if(attachFileDAO.deleteAttachFile(vo) == 1)
                        {
                            //물리적 삭제
                            attachFileUploadUtil.fileDelete(attachFileDAO.selectAttachFile(vo));
                            
                            //삭제로그
                            //logService.insertLog(vo, mSession);
                            
                            if(vo.getFilCpc() > 0){
                                iCountUploadSuccess ++;
                                
                                //입력
                                Object resultObject = attachFileDAO.insertAttachFile(vo);
                                
                                if(resultObject.getClass() == Integer.class){
                                    vo.setAttcFilSn((Integer)resultObject);
                                    
                                    //공통로그
                                    //logService.insertLog(vo, mSession);
                                    iCountInsertSuccess ++;
                                }
                            }else{
                                iCountInsertSuccess ++;
                            }
                        }else{
                            throw new IllegalArgumentException();
                        }
                    }
                    else if((Boolean)vo.getStatus() && vo.getAttcFilSn() != 0)
                    {
                        if(vo.getFilCpc() > 0)
                        {
                            //기존번호 삭제
                            if(attachFileDAO.deleteAttachFile(vo) == 1)
                            {
                                //삭제로그
                                //logService.insertLog(vo, mSession);
                                
                                iCountUploadSuccess ++;
                                
                                //입력
                                Object resultObject = attachFileDAO.insertAttachFile(vo);
                                
                                if(resultObject.getClass() == Integer.class){
                                    vo.setAttcFilSn((Integer)resultObject);
                                    
                                    //공통로그
                                    //logService.insertLog(vo, mSession);
                                    iCountInsertSuccess ++;
                                }
                            }else{
                                throw new IllegalArgumentException();
                            }
                        }else{
                            iCountAll --;
                        }
                        
                    }else{
                        log.debug("== 업로드 실패 :: {} ", vo.getErrorMessage(), vo.getOgcFilNm());
                    }
                    
                    vo.setAttachfile(null);
                }
                
                log.debug("== 첨부파일 완료 보고 ==");
                log.debug("== 전체 :: "+ iCountAll );
                log.debug("== 업로드 성공 :: "+ iCountUploadSuccess );
                log.debug("== 등록 성공 :: "+ iCountInsertSuccess);
                
                /*
                 * 업로드와 DB입력 갯수가 같은지 확인한다.
                 * - 다르면 어떻게 처리 할것인가??
                 */
                if(iCountUploadSuccess > iCountInsertSuccess){
                    //업로드 완료 되었지만 등록은 실패한것이 있음.
                    mResult.put("message", xMessageSource.getMessage("exception.error"));
                    
                }else if(iCountAll != iCountInsertSuccess){
                    //업로드에 문제가 있음. Valid에 문제가 있을것임.
                    mResult.put("message", xMessageSource.getMessage("exception.error"));
                }else{
                    bStatus = true;
                }
                
                mResult.put("uploadFileInfo", lAttachFileVO2);
            }
        }catch(Exception exception){
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;

    }
    
    
    /*
     * 정보를 삭제한다.
     *
     * @see vc.virusclean.cmm.service.AttachFileService#deleteAttachFile(vc.virusclean.cmm.vo.AttachFileVO)
     */
    public Map<String, Object> deleteAttachFile(AttachFileVO attachFileVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        Map<String,Object> mSession = multiUtil.getSessionInfo();
        
        boolean bStatus = true; 
        
        try{  	
            attachFileVO.setMdfyId((String)mSession.get("userId"));
            
            /*
             * 삭제 - 삭제 건수 리턴               
             */  
            mResult.put("count", attachFileDAO.deleteAttachFile(attachFileVO));

        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;

    }

    
    /*
     * 정보를 조회한다.
     *
     * @see vc.virusclean.cmm.service.AttachFileService#selectAttachFile(vc.virusclean.cmm.vo.AttachFileVO)
     */
    public Map<String, Object> selectAttachFile(AttachFileVO attachFileVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        Map<String,Object> mSession = multiUtil.getSessionInfo();

        /*
         * 정보 조회               
         */         
        
        AttachFileVO attachFileVO2 = attachFileDAO.selectAttachFile(attachFileVO);

        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(attachFileVO2 == null){
            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
        }else{                
            attachFileDAO.updateAttachFileByDnlOft(attachFileVO2);
            
            mResult.put("contentFileName", attachFileVO2.getOgcFilNm());
            mResult.put("contentFile", new File(fileSavePath.getAbsolutePath() + File.separator +  attachFileVO2.getMenuCd() + File.separator + attachFileVO2.getFilNm()));
            
            //보안로그            
            accessLogService.insertAccessLog(attachFileVO2, mSession);
        }
        				
        return mResult;

    }


    /*
     * 목록을 조회한다..
     * @see vc.virusclean.cmm.service.AttachFileService#selectAttachFileList(java.lang.String, int, java.util.Map)
     */
    public Map<String, Object> selectAttachFileList(String menuCd, int contenSeq) throws Exception {
        
        AttachFileVO attachFileVO = new AttachFileVO();
        attachFileVO.setMenuCd(menuCd);
        attachFileVO.setCotnSn(contenSeq);
        
        return this.selectAttachFileList(attachFileVO);
    }

    /*
     * 목록을 조회한다..
     * @see vc.virusclean.cmm.service.AttachFileService#selectAttachFileList(java.lang.String, int, java.util.Map)
     */
    public Map<String, Object> selectAttachFileList(String menuCd, int contenSeq, String filScnCd) throws Exception {
        
        AttachFileVO attachFileVO = new AttachFileVO();
        attachFileVO.setMenuCd(menuCd);
        attachFileVO.setCotnSn(contenSeq);
        attachFileVO.setFilScnCd(filScnCd);
        
        return this.selectAttachFileList(attachFileVO);
    }
    
    
    /*
     * 목록을 조회한다.
     *
     * @see vc.virusclean.cmm.service.AttachFileService#selectAttachFileList(vc.virusclean.cmm.vo.AttachFileVO)
     */
    public Map<String, Object> selectAttachFileList(AttachFileVO attachFileVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
       
        /*
         * 전체 갯수               
         */
        attachFileVO.setTotalRow(attachFileDAO.selectAttachFileCount(attachFileVO));

        /*
         * 페이징 목록           
         */
        if(attachFileVO.getTotalRow() > 0){
            mResult.put("list", attachFileDAO.selectAttachFileList(attachFileVO));
        }
        
        //mResult.put("searchInfo", attachFileVO);
        mResult.put("status", true);

        return mResult;

    }
    
    /**
     * MultiPart로 넘어온 첨부파일을 확인 해서 AttachFileVO를 생성후 목록을 리턴한다. 
     *
     * @param fileVO
     * @return
     */
    public List<AttachFileVO> validFileCheck(FileVO fileVO) throws Exception {
        
        List<AttachFileVO> lAttachFileVO = new ArrayList<AttachFileVO>();
        
        try{
            
            if( fileVO.getAttachFileSn1() > 0 || 
                    ( fileVO.getAttachFile1() != null && fileVO.getAttachFile1().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile1()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile1(), fileVO.getAttachFileSn1(), fileVO.getAttachFileCd1(), 
                        fileVO.getAttachFileNo1(), fileVO.getAttachFileExpl1(), fileVO.getAttachFileUseYn1()));
            }
            
            if( fileVO.getAttachFileSn2() > 0 || 
                    ( fileVO.getAttachFile2() != null && fileVO.getAttachFile2().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile2()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile2(), fileVO.getAttachFileSn2(), fileVO.getAttachFileCd2(), 
                        fileVO.getAttachFileNo2(), fileVO.getAttachFileExpl2(), fileVO.getAttachFileUseYn2()));
            }
            
            if( fileVO.getAttachFileSn3() > 0 || 
                    ( fileVO.getAttachFile3() != null && fileVO.getAttachFile3().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile3()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile3(), fileVO.getAttachFileSn3(), fileVO.getAttachFileCd3(), 
                        fileVO.getAttachFileNo3(), fileVO.getAttachFileExpl3(), fileVO.getAttachFileUseYn3()));
            }
            
            if( fileVO.getAttachFileSn4() > 0 || 
                    ( fileVO.getAttachFile4() != null && fileVO.getAttachFile4().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile4()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile4(), fileVO.getAttachFileSn4(), fileVO.getAttachFileCd4(), 
                        fileVO.getAttachFileNo4(), fileVO.getAttachFileExpl4(), fileVO.getAttachFileUseYn4()));
            }
            
            if( fileVO.getAttachFileSn5() > 0 || 
                    ( fileVO.getAttachFile5() != null && fileVO.getAttachFile5().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile5()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile5(), fileVO.getAttachFileSn5(), fileVO.getAttachFileCd5(), 
                        fileVO.getAttachFileNo5(), fileVO.getAttachFileExpl5(), fileVO.getAttachFileUseYn5()));
            }
            
            if( fileVO.getAttachFileSn6() > 0 || 
                    ( fileVO.getAttachFile6() != null && fileVO.getAttachFile6().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile6()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile6(), fileVO.getAttachFileSn6(), fileVO.getAttachFileCd6(), 
                        fileVO.getAttachFileNo6(), fileVO.getAttachFileExpl6(), fileVO.getAttachFileUseYn6()));
            }
            
            if( fileVO.getAttachFileSn7() > 0 || 
                    ( fileVO.getAttachFile7() != null && fileVO.getAttachFile7().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile7()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile7(), fileVO.getAttachFileSn7(), fileVO.getAttachFileCd7(), 
                        fileVO.getAttachFileNo7(), fileVO.getAttachFileExpl7(), fileVO.getAttachFileUseYn7()));
            }
            
            if( fileVO.getAttachFileSn8() > 0 || 
                    ( fileVO.getAttachFile8() != null && fileVO.getAttachFile8().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile8()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile8(), fileVO.getAttachFileSn8(), fileVO.getAttachFileCd8(), 
                        fileVO.getAttachFileNo8(), fileVO.getAttachFileExpl8(), fileVO.getAttachFileUseYn8()));
            }
            
            if( fileVO.getAttachFileSn9() > 0 || 
                    ( fileVO.getAttachFile9() != null && fileVO.getAttachFile9().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile9()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile9(), fileVO.getAttachFileSn9(), fileVO.getAttachFileCd9(), 
                        fileVO.getAttachFileNo9(), fileVO.getAttachFileExpl9(), fileVO.getAttachFileUseYn9()));
            }
            
            if( fileVO.getAttachFileSn10() > 0 || 
                    ( fileVO.getAttachFile10() != null && fileVO.getAttachFile10().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile10()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile10(), fileVO.getAttachFileSn10(), fileVO.getAttachFileCd10(), 
                        fileVO.getAttachFileNo10(), fileVO.getAttachFileExpl10(), fileVO.getAttachFileUseYn10()));
            }
            
            if( fileVO.getAttachFileSn11() > 0 || 
                    ( fileVO.getAttachFile11() != null && fileVO.getAttachFile11().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile11()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile11(), fileVO.getAttachFileSn11(), fileVO.getAttachFileCd11(), 
                        fileVO.getAttachFileNo11(), fileVO.getAttachFileExpl11(), fileVO.getAttachFileUseYn11()));
            }
            
            if( fileVO.getAttachFileSn12() > 0 || 
                    ( fileVO.getAttachFile12() != null && fileVO.getAttachFile12().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile12()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile12(), fileVO.getAttachFileSn12(), fileVO.getAttachFileCd12(), 
                        fileVO.getAttachFileNo12(), fileVO.getAttachFileExpl12(), fileVO.getAttachFileUseYn12()));
            }
            
            if( fileVO.getAttachFileSn13() > 0 || 
                    ( fileVO.getAttachFile13() != null && fileVO.getAttachFile13().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile13()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile13(), fileVO.getAttachFileSn13(), fileVO.getAttachFileCd13(), 
                        fileVO.getAttachFileNo13(), fileVO.getAttachFileExpl13(), fileVO.getAttachFileUseYn13()));
            }
            
            if( fileVO.getAttachFileSn14() > 0 || 
                    ( fileVO.getAttachFile14() != null && fileVO.getAttachFile14().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile14()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile14(), fileVO.getAttachFileSn14(), fileVO.getAttachFileCd14(), 
                        fileVO.getAttachFileNo14(), fileVO.getAttachFileExpl14(), fileVO.getAttachFileUseYn14()));
            }
            
            if( fileVO.getAttachFileSn15() > 0 || 
                    ( fileVO.getAttachFile15() != null && fileVO.getAttachFile15().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile15()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile15(), fileVO.getAttachFileSn15(), fileVO.getAttachFileCd15(), 
                        fileVO.getAttachFileNo15(), fileVO.getAttachFileExpl15(), fileVO.getAttachFileUseYn15()));
            }
            
            if( fileVO.getAttachFileSn16() > 0 || 
                    ( fileVO.getAttachFile16() != null && fileVO.getAttachFile16().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile16()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile16(), fileVO.getAttachFileSn16(), fileVO.getAttachFileCd16(), 
                        fileVO.getAttachFileNo16(), fileVO.getAttachFileExpl16(), fileVO.getAttachFileUseYn16()));
            }
            
            if( fileVO.getAttachFileSn17() > 0 || 
                    ( fileVO.getAttachFile17() != null && fileVO.getAttachFile17().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile17()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile17(), fileVO.getAttachFileSn17(), fileVO.getAttachFileCd17(), 
                        fileVO.getAttachFileNo17(), fileVO.getAttachFileExpl17(), fileVO.getAttachFileUseYn17()));
            }
            
            if( fileVO.getAttachFileSn18() > 0 || 
                    ( fileVO.getAttachFile18() != null && fileVO.getAttachFile18().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile18()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile18(), fileVO.getAttachFileSn18(), fileVO.getAttachFileCd18(), 
                        fileVO.getAttachFileNo18(), fileVO.getAttachFileExpl18(), fileVO.getAttachFileUseYn18()));
            }
            
            if( fileVO.getAttachFileSn19() > 0 || 
                    ( fileVO.getAttachFile19() != null && fileVO.getAttachFile19().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile19()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile19(), fileVO.getAttachFileSn19(), fileVO.getAttachFileCd19(), 
                        fileVO.getAttachFileNo19(), fileVO.getAttachFileExpl19(), fileVO.getAttachFileUseYn19()));
            }
            
            if( fileVO.getAttachFileSn20() > 0 || 
                    ( fileVO.getAttachFile20() != null && fileVO.getAttachFile20().getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileVO.getAttachFile20()).getSize() > 0)
            ){
                lAttachFileVO.add(this.setAttacheFileVO(fileVO.getAttachFile20(), fileVO.getAttachFileSn20(), fileVO.getAttachFileCd20(), 
                        fileVO.getAttachFileNo20(), fileVO.getAttachFileExpl20(), fileVO.getAttachFileUseYn20()));
            }

            
        }catch(IllegalArgumentException illegalArgumentException){
            log.error("[AttachFileServiceImpl.validFileCheck] ", illegalArgumentException);
        }
        
        return lAttachFileVO;
        
    }
    
    /**
     * 업로드용 첨부파일 객체 생성
     *
     * @param fileObject
     * @param fileSn
     * @param fileCd
     * @param fileNo
     * @param fileExpl
     * @param fileUseYn
     * @return
     * @throws Exception
     */
    protected AttachFileVO setAttacheFileVO(Object fileObject, int fileSn, String fileCd, int fileNo, String fileExpl, String fileUseYn) throws Exception {
        
        AttachFileVO attachFileVO = null;
        
        if( fileSn > 0 || 
                ( fileObject != null && fileObject.getClass() == CommonsMultipartFile.class && ((MultipartFile)fileObject).getSize() > 0))
        {
            attachFileVO = new AttachFileVO();
            attachFileVO.setAttcFilSn(fileSn);      //파일일렬번호
            attachFileVO.setFilScnCd(fileCd);       //파일타입코드
            attachFileVO.setFilStNo(fileNo);        //정렬순서
            attachFileVO.setFilExpl(fileExpl);      //대체텍스트
            attachFileVO.setUseYn(fileUseYn);       //사용여부
            
            if( fileObject != null && fileObject.getClass() == CommonsMultipartFile.class
                    && ((MultipartFile)fileObject).getSize() > 0){
                attachFileVO.setAttachfile((MultipartFile)fileObject);     //파일정보
            }
        }
        
        return attachFileVO;
    }


    /*
     * 에디터에서 이미지 파일을 등록한다.
     * @see vc.virusclean.cmm.service.AttachFileService#manageEditorUpload(vc.virusclean.cmm.vo.EditorPhotoVO, java.util.Map)
     */
    public Map<String, Object> manageEditorUpload(EditorPhotoVO editorPhotoVO, Map<String, Object> mSession) throws Exception {

        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = true; 
        
        //try{
            List<AttachFileVO> lAttachFileVO = new ArrayList<AttachFileVO>();
            
            AttachFileVO attachFileVO = new AttachFileVO();
            
            if(!(editorPhotoVO.getAttachfile() == null || editorPhotoVO.getAttachfile().isEmpty())){
                attachFileVO.setMenuCd(editorPhotoVO.getMenuCode());
                attachFileVO.setFilScnCd("SM");
                attachFileVO.setAttachfile(editorPhotoVO.getAttachfile());
                attachFileVO.setRgstId((String)mSession.get("userId"));
                lAttachFileVO.add(attachFileVO);
            }else{
                throw new IllegalArgumentException("파일 업로드 정보가 없음.");
            }

            if(!lAttachFileVO.isEmpty()){
                Map<String, Object> mResult2 = this.insertAttachFile(lAttachFileVO, mSession);
                
                @SuppressWarnings("unchecked")
                AttachFileVO fileVO = ((List<AttachFileVO>)mResult2.get("uploadFileInfo")).get(0);
                
                if((Boolean)mResult2.get("status")){
                    if(fileVO.getStatus()){
                        mResult.put("fileName", fileVO.getFilNm());
                    }else{
                        mResult.put("message", fileVO.getErrorMessage());
                        mResult.put("messageCode", fileVO.getErrorCode());
                    }
                    
                    mResult.put("status", fileVO.getStatus());
                }else{
                    bStatus = false;
                }
            }
        //}catch(Exception exception){
        //    bStatus = false;
        //    throw processException("exception.error", exception);
        //}
        
        mResult.put("status", bStatus);
            
        return mResult;
    }
    
}
