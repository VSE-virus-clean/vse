package jksoft.com.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import egovframework.rte.fdl.filehandling.EgovFileUtil;
import jksoft.com.filter.FileFilter;
import jksoft.com.support.XMessageSource;
import jksoft.com.web.vo.AttachFileVO;

/**
 * <pre>
 * 공통 파일 업로드 처리
 * </pre>
 *
 * @ClassName   : AttachFileUploadUtil.java
 * @Description : 파일 업로드 처리
 * @author Jeong.Hyoung.Jae
 * @since 2013. 9. 9.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2013. 9. 9.     Jeong.Hyoung.Jae     최초 생성
 * </pre>
 */
@Component("attachFileUploadUtil")
public class AttachFileUploadUtil {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    /**
     * MessageSource class 선언 ( XMessageSource Class Injection)
     */ 
    @Resource(name = "xMessageSource")
    XMessageSource xMessageSource;
    
    /**
     * FTPUpLoader class 선언
     */ 
    @Resource(name = "ftpUpLoaderUtil")
    FTPUpLoaderUtil ftpUpLoaderUtil;
    
    /*
     * 파일 저장 경로
     */
    @Value(value="#{global['file.system.path.default']}")
    private File fileSavePath;
    
    /*
     * 업로드 최대 용량 
     */
    @Value(value="#{global['file.maxSize']}")
    private int fileMaxSize;
    

    /*
     * 예외처리 확장자 : .html .htm .php .php3 .php4 .phtml .phps .in .cgi .pl .shtml .jsp .asp
     */
    private final static String[] DENY_FILETYPE = {".html",".htm",".php",".php3",".php4",".phtml",".phps",".in",".cgi",".pl",".shtml",".jsp",".asp"};
    
    
    /**
     * 파일 업로드
     * 
     * 1. 확장자 확인
     * 2. 파일크기 확인
     * 3. 한글파일 확인
     * 4. 파일명 생성
     * 5. 파일 업로드
     * 6. 파일 업로드 정보
     *
     * @param lmFile        첨부파일 정보
     * @param                       
     * @return
     */    
    public List<AttachFileVO> fileUpload(List<AttachFileVO> lFile){
        
        List<AttachFileVO> lmSaveFileInfo = new ArrayList<AttachFileVO>();
        
        for(int i = 0 ; i < lFile.size() ; i++){
            
            AttachFileVO fileVO = (AttachFileVO)lFile.get(i);

            MultipartFile multipartFile = (MultipartFile)fileVO.getAttachfile();
            
            /**
             * 업로드 폴더 ::: {기본경로}/{서비스아이디}
             */
            String srtUploadDir = fileSavePath.getAbsolutePath() + File.separator + fileVO.getMenuCd().toUpperCase();
            
            if(log.isDebugEnabled()){
                log.debug("--------- 파일 업로드 경로 ---------- {}", srtUploadDir );
                log.debug("--------- 파일 업로드 ---------- {}", lFile.size() );
            }
            
            /*
             * 파일 정보 수정시 파일 정보만 있고 파일은 없을수 있음.  
             * IE8이하 수정 : 첨부 파일이 없다라도 MultipartFile에는 존재할수 있음.              
             */
            if(multipartFile != null && multipartFile.getSize() > 0){
                
                if(log.isDebugEnabled()){
                    log.debug("========== AttachFileUploadUtil.fileUpload() strOriginalFileFullName :: " + multipartFile.getOriginalFilename());
                    log.debug("========== AttachFileUploadUtil.fileUpload()  attachFile.getSize :: " +  multipartFile.getSize());
                }
                
                //원본 이름
                //파일명 중간에 공백 제거
                String strOriginalFileFullName = "";
                String strTemp = StringUtil.removeWhitespace(multipartFile.getOriginalFilename().trim());
                
                /**
                 * 파일명에 .은 _로 변경
                 */
                String fileExt = strTemp.lastIndexOf(".") < 0 ? "jpg" : strTemp.substring(strTemp.lastIndexOf(".") + 1).toLowerCase();
                String fileName = strTemp.lastIndexOf(".") < 0 ? strTemp : strTemp.substring(0, strTemp.lastIndexOf("."));
                strOriginalFileFullName = fileName.replaceAll("\\.", "_") +  "." + fileExt; 
                
                //byte
                long fileSize = multipartFile.getSize();
                    
                //정보 저장
                fileVO.setFilCpc(fileSize);                       //파일크기
                fileVO.setOgcFilNm(strOriginalFileFullName);      //원본파일명
                
                
                //파일 사이즈에 따른 제한 사항 이 있어야함.
                if(fileMaxSize > fileSize){
                    
                    /*
                     * 파일 형식 확인
                     */
                    if(this.checkFileName(fileVO.getFilScnCd(), strOriginalFileFullName, multipartFile.getContentType()))
                    {
                        //파일명 생성
                        String strFileFullName = this.createFileFullName(fileVO);
                        
                        try{
                            File uploadDir = new File(srtUploadDir);
                            if(!uploadDir.exists()){
                                if(!uploadDir.mkdirs()){
                                    throw new IOException();
                                }
                            }

                            //업로드 파일 생성
                            File newFile = new File(srtUploadDir, strFileFullName);

                            //파일 이동
                            multipartFile.transferTo(newFile);
                            
                            //newFile.setReadable(true);
                            //newFile.setReadOnly();

                            //정보 저장
                            fileVO.setStatus(true);                     //성공여부
                            fileVO.setFilNm(strFileFullName);           //저장한 파일명
                            
                        }catch(IOException ioException){
                            ioException.printStackTrace();
                            
                            fileVO.setStatus(false);
                            fileVO.setErrorCode("exception.error");
                            fileVO.setErrorMessage(xMessageSource.getMessage("exception.error"));
                        }
                    }else{
                        fileVO.setStatus(false);
                        fileVO.setErrorCode("msg.A45");
                        fileVO.setErrorMessage(xMessageSource.getMessage("msg.A45"));
                    }
                }else{
                    fileVO.setStatus(false);
                    fileVO.setErrorCode("msg.A46");
                    fileVO.setErrorMessage(xMessageSource.getMessage("msg.A46"));
                }
                
                if(log.isDebugEnabled()){
                    log.debug("== 업로드 파일 정보 : "+ (fileVO.getStatus() ? "성공"  : "실패 ")+" ==");
                    log.debug("== attachName : " + fileVO.getFilScnCd());
                    log.debug("== fileSize : " + fileVO.getFilCpc());
                    log.debug("== fileName : " + fileVO.getFilNm());
                    log.debug("== fileNameOrigin : " + fileVO.getOgcFilNm());
                    log.debug("== errorMessage : " + fileVO.getErrorMessage());
                }
                
            }else{
                fileVO.setStatus(true);
            }
            
            lmSaveFileInfo.add(fileVO);
            
        }
        
        return lmSaveFileInfo;
    }
    
    
    

    /**
     * 파일 타입별 생성규칙에 의해서 새로운 파일명을 생성한다.
     * 
     * - 썸네일 : thumb_{서비스아이디}{컨텐츠일렬번호}.jpg
     * - 게시판 + 페이지 연결 썸네일 : thumb_{서비스아이디}P_{컨텐츠일렬번호}.jpg
     * - 메뉴이미지 : breadcrumb_{컨텐츠일렬번호}{_on}.jpg
     * 
     * @param strFileType 파일 종류
     * @param strOriginalFileFullName 파일명
     * @return
     */
    private String createFileFullName(AttachFileVO fileVO) {
        
        StringBuilder sbFileName = new StringBuilder();
        
        //원본 파일명
        String strOriginalFileName = fileVO.getOgcFilNm().substring(0, fileVO.getOgcFilNm().lastIndexOf("."));
        
        //원본 파일 확장자
        String strOriginalFileType = fileVO.getOgcFilNm().substring(fileVO.getOgcFilNm().lastIndexOf(".") + 1).toLowerCase();
        
        sbFileName.append(strOriginalFileName).append("_").append(System.currentTimeMillis()).append("_").append(fileVO.getFilStNo()).append(".").append(strOriginalFileType);
        
        return sbFileName.toString();
    }



    /**
     * 파일 이름/형식 확인
     * 
     * - {"%2E", "%2F", "%5C", ".", "/", "\\"} 확인
     * - 영문대소문자/_ /-/. 확인
     * - Deny 확장자 확인
     * - Allow 확장자 확인  
     * 
     * @param strFileType   파일 종류
     * @param strOriginalFileFullName   파일명
     * @param strFileMimeYype 파일 mime type
     * @return {Boolean}
     */
    private boolean checkFileName(String strFileType, String strOriginalFileFullName, String strFileMimeYype) {
        
        //파일명 확인 성공 여부        
        boolean bResult = false;
        
        /*
         * BackList 확인
         * {"%2E", "%2F", "%5C", ".", "/", "\\"}
         */
        if(FileFilter.checkFileAndMime(strOriginalFileFullName, strFileMimeYype)){
            
            //영문대소문자/_ /-/. 만 허용한다. 
            //if(strOriginalFileFullName.matches("^[a-zA-Z0-9][a-zA-Z0-9_-]*+\\.([a-zA-Z0-9]{3,4})$")){
            //영문대소문자한글/_ /-/. 만 허용한다. 
            if(strOriginalFileFullName.matches("^[a-zA-Z0-9가-힣\\(\\)\\[\\]][a-zA-Z0-9가-힣\\(\\)\\[\\]_-]*+\\.([a-zA-Z0-9]{3,4})$")){
            //if(strOriginalFileFullName.matches("^[a-zA-Z0-9\\(\\)\\[\\]][a-zA-Z0-9\\(\\)\\[\\]_-]*+\\.([a-zA-Z0-9]{3,4})$")){
                
                boolean bDenyListCheck = true;
                
                //첨부 불가능한 확장자 확인
                for(String strDenyFile : DENY_FILETYPE){
                    if(strOriginalFileFullName.toLowerCase().indexOf(strDenyFile) > 0 ){
                        
                        if(log.isDebugEnabled()){
                            log.debug("== [AttachFileUploadUtil.checkFileName] 파일 확장자 확인 에러 :: {}", strDenyFile );
                        }
                        
                        bDenyListCheck = false;
                        break;
                    }
                }
            
                
                if(bDenyListCheck){
                    //파일 타입별 확장자 확인
                    String[] arrayAllowFileType = null;
                    
                    //H : EXT_WHITELIST + IMAGE_WHITELIST
                    //A : PDF  PDF_WHITELIST
                    //SM :: 스마트 에디터
                    /*if(strFileType.startsWith("H")){
                        arrayAllowFileType = FileFilter.EXT_IMAGE_WHITELIST;
                    }else if(strFileType.startsWith("A")){
                        arrayAllowFileType = FileFilter.PDF_WHITELIST;
                    }else{
                        arrayAllowFileType = FileFilter.IMAGE_WHITELIST;
                    }
                    */
                    if("SM".equals(strFileType)){
                        arrayAllowFileType = FileFilter.IMAGE_WHITELIST;
                    }else{
                        arrayAllowFileType = FileFilter.EXT_IMAGE_WHITELIST;
                    }
                    
                    for(String strAllowFile : arrayAllowFileType){
                        if(strOriginalFileFullName.toLowerCase().indexOf("."+strAllowFile) > 0 ){
                            bResult = true;
                            break;
                        }
                    }
                }
            }else{
                if(log.isDebugEnabled()){
                    log.debug("== [AttachFileUploadUtil.checkFileName] 파일 확장자 확인 에러 :: 영문대소문자/_ /-/. 이외문자가 포함.");
                }
            }
        }
        
        return bResult;         
        
    }

    /**
     * 파일 카피
     * @param newFile       원본
     * @param backupFile    복사
     * @return
     */
    public boolean fileCopy(File newFile, File backupFile) {
        boolean bRtn = true;

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try{
            bis = new BufferedInputStream(new FileInputStream(newFile));
            bos = new BufferedOutputStream(new FileOutputStream(backupFile));

            int i = 0;
            while((i = bis.read()) != -1){
                bos.write(i);
            }
        }catch(Exception exception){
            bRtn = false;
            
            if(log.isDebugEnabled()){
                log.debug("[AttachFileUploadUtil.fileCopy] 파일 복사중 에러 ", exception);
            }
        }finally{
            try {
                if(bis != null){
                    bis.close();
                }
                
                if(bos != null){
                    bos.close();
                }
            } catch (IOException ioException) {
                if(log.isDebugEnabled()){
                    log.debug("[AttachFileUploadUtil.fileCopy] 파일 닫기 실패 ", ioException);
                }
            }
        }

        return bRtn;
    }
    
    /**
     * 파일 삭제
     *
     * @param fileVO
     * @return
     */
    public boolean fileDelete(AttachFileVO fileVO) {
        
        boolean bRtn = false;
        
        try {
            String fileSystemPath = fileSavePath.getAbsolutePath() + File.separator +  fileVO.getMenuCd() + File.separator + fileVO.getFilNm();
            
            File file = new File(fileSystemPath);
            
            if(file.exists()){
                EgovFileUtil.delete(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return bRtn;
    }
    
    /**
     * 중복 파일명 체크
     * 
     * @param path
     * @param fileName
     * @return 
     * @throws Exception
     */
    public StringBuilder checkDuplicateFileName(String path, String fileName) throws Exception{
        
        File file = new File(path + File.separator + fileName);
        StringBuilder sbFileName = new StringBuilder();
        
        int idx = 1;
        String orginFileName = fileName.trim().replace(" ", "");
        String fileName2 = "";
        
        if(file.exists()){
            while(idx < 1000){
                int index = orginFileName.lastIndexOf(".");
                String fileExt = orginFileName.substring(index+1);
                fileName2 = orginFileName.substring(0,index)+"_"+idx+"."+fileExt;
                file = new File( path, fileName2);
                
                if(!file.exists()){
                    break; 
                }
                
                idx++;
            }
        }
        
        return sbFileName.append(fileName2);
    }
}
