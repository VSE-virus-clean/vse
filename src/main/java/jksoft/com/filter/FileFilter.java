package jksoft.com.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : FileFilter.java
 * @Description : 업로드/ 다운로드 시 유효한 파일인지 확인하는 클래스
 * @author Sungjin Lee
 * @since 2013. 6. 11.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2013. 6.11.     Sungjin Lee     최초 생성
 *  2013.10.29.     Jeong.hyoungjea EXT_WHITELIST에서 TXT, ZIP파일 삭제
 *                                  FILE_NAME_BLACKLIST에 %2e, %2f, %5c 추가
 *  2013.11.12                      MIME_TYPE_WHITELIST 추가 - 파일 MIME TYPE확인   
 * </pre>
 */
public class FileFilter {
    
    private final static Logger LOG = LoggerFactory.getLogger(FileFilter.class);
        
    public final static String [] PDF_WHITELIST = {"pdf"};
    public final static String [] EXT_WHITELIST = {"doc", "docx", "ppt", "pptx", "xls", "xlsx", "hwp", "pdf", "zip"};
    public final static String [] EXT_IMAGE_WHITELIST = {"doc", "docx", "ppt", "pptx", "xls", "xlsx", "hwp", "pdf","jpg", "jpeg", "png", "gif", "zip", "mp4"};
    public final static String [] IMAGE_WHITELIST = {"jpg", "jpeg", "png", "gif", "bmp"};
    public final static String [] MEDIA_WHITELIST = {"mp4", "mp3", "wmv", "avi", "mpg"};
    public final static String [] FILE_NAME_BLACKLIST = {"%2E", "%2F", "%5C", "%2e", "%2f", "%5c", ".", "/", "\\"};
    public final static String [] MIME_TYPE_WHITELIST = {
             "application/msword"               // doc
            ,"application/vnd.openxmlformats-officedocument.wordprocessingml.document"      // docx
            ,"application/mspowerpoint"         // ppt
            ,"application/powerpoint"           // ppt
            ,"application/vnd.ms-powerpoint"    // ppt
            ,"application/x-mspowerpoint"       // ppt
            ,"application/vnd.openxmlformats-officedocument.presentationml.presentation"    // pptx
            ,"application/excel"                // xls
            ,"application/vnd.ms-excel"         // xls
            ,"application/x-excel"              // xls
            ,"application/x-msexcel"            // xls
            ,"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"            // xlsx
            ,"application/hangul"               //hwp
            ,"application/x-hwp"                //hwp
            ,"application/x-compressed"         // zip
            ,"application/x-zip-compressed"     // zip
            ,"application/zip"                  // zip
            ,"multipart/x-zip"                  // zip
            ,"application/pdf"                   // pdf
            // image
            ,"image/jpeg"                       // jpg
            ,"image/pjpeg"                      // jpg
            ,"image/gif"                        // gif
            ,"image/png"                        // png
            ,"image/x-png"                      // png
            ,"image/bmp"                        // bmp
            ,"image/x-windows-bmp"               // bmp
            //media
            ,"video/mp4"                        // mp4
            ,"audio/mpeg"                       // mp2, mp3, mpga
            ,"audio/mpeg3"                      // mp3
            ,"audio/x-mpeg-3"                   // mp3, mpga
            ,"video/x-mpeg"                     // mp3
            ,"video/mpeg"                       // mp3, mpga
            ,"video/avi"                       // avi
            ,"video/msvideo"                   // avi
            ,"video/x-msvideo"                 // avi
            ,"application/x-troff-msvideo"     // avi
            ,"video/x-ms-wmv"                 // wmv
            //,"video/x-flv"                      // flv
            //,"application/x-shockwave-flash"    // swf
            //,"application/octet-stream"         // 보안문서 MIME_TYPE이다.
    };
    
    
    /**
     * 파일 확장자 채크 (일반파일, 이미지 파일)
     *
     * @param fileExt
     * @return
     */
    public static boolean checkFileExt(String fileExt){
        boolean isFileExt = false;
        
        /* 확장자 채크  화이트리스트*/
        for(int i=0; i<EXT_WHITELIST.length; i++){
            if(EXT_WHITELIST[i].equals(fileExt.toLowerCase())){
                isFileExt = true;
                break;
            }
        }
        
        /* 이미지 확징자 채크 화이트리스트 */
        for(int i=0; i<IMAGE_WHITELIST.length; i++){
            if(IMAGE_WHITELIST[i].equals(fileExt.toLowerCase())){
                isFileExt = true;
                break;
            }
        }
        
        /* 미디어 확징자 채크 화이트리스트 */
        for(int i=0; i<MEDIA_WHITELIST.length; i++){
            if(MEDIA_WHITELIST[i].equals(fileExt.toLowerCase())){
                isFileExt = true;
                break;
            }
        }
        
        if(LOG.isDebugEnabled()){
            LOG.debug("############## FileFilter.checkFileExt : " + fileExt + " : " + isFileExt);
        }
        return isFileExt;
    }
    
    /**
     * 이미지파일 확장자 채크 
     *
     * @param fileExt
     * @return
     */
    public static boolean checkImageFileExt(String fileExt){
        boolean isFileExt = false;
        
        /* 확장자 채크  화이트리스트*/
        for(int i=0; i<IMAGE_WHITELIST.length; i++){
            if(IMAGE_WHITELIST[i].equals(fileExt.toLowerCase())){
                isFileExt = true;
                break;
            }
        }
        
        if(LOG.isDebugEnabled()){
            LOG.debug("############## FileFilter.checkImageFileExt :  " + fileExt + " : " + isFileExt);
        }
        return isFileExt;
    }
    
    /**
     * 미디어파일 확장자 채크 
     *
     * @param fileExt
     * @return
     */
    public static boolean checkMediaFileExt(String fileExt){
        boolean isFileExt = false;
        
        /* 확장자 채크  화이트리스트*/
        for(int i=0; i<MEDIA_WHITELIST.length; i++){
            if(MEDIA_WHITELIST[i].equals(fileExt.toLowerCase())){
                isFileExt = true;
                break;
            }
        }
        
//        if(LOG.isDebugEnabled()){
//            LOG.debug("############## FileFilter.checkMediaFileExt : " + isFileExt);
//        }
        
        return isFileExt;
    }
    
    /**
     * 확장자를 제외한 파일명 채크
     *
     * @param fileName
     * @return
     */
    public static boolean checkFileName(String fileName){
        boolean isFileName = true;
        
        
        /* 파일명 채크 블랙리스트*/
        for(int i=0; i<FILE_NAME_BLACKLIST.length; i++){
            if(fileName.indexOf(FILE_NAME_BLACKLIST[i]) > -1){
                isFileName = false;
                break;
            }
        }
        
        if(LOG.isDebugEnabled()){
            LOG.debug("############## FileFilter.checkFileNameAndPath : " + isFileName + " : " + isFileName);
        }
        
        return isFileName;
    }
    
    /**
     * 확장자를 포함한 파일 유효성 채크
     *
     * @param fullName
     * @return
     */
    public static boolean checkFile(String fullName){
        boolean isFile = false;
        
        String fileExt = fullName.substring(fullName.lastIndexOf(".") + 1).toLowerCase();
        String fileName = fullName.substring(0, fullName.lastIndexOf("."));
        
        if(checkFileExt(fileExt) && checkFileName(fileName)){
            isFile = true;
        }
        
        return isFile;
    }
    
    /**
     * 확장자를 포함한 이미지 파일 유효성 채크
     *
     * @param fullName
     * @return
     */
    public static boolean checkImageFile(String fullName){
        boolean isFile = false;
        
        String fileExt = fullName.substring(fullName.lastIndexOf(".") + 1);
        String fileName = fullName.substring(0, fullName.lastIndexOf("."));
        
        if(checkImageFileExt(fileExt) && checkFileName(fileName)){
            isFile = true;
        }
        
        return isFile;
    }
    
    /**
     * 파일 MIME type 유효성 채크
     *
     * @param mimeType
     * @return
     */
    public static boolean checkFileMime(String mimeType){
        boolean isFile = false;
        
        String mimeType2 = mimeType.toLowerCase();
        
        for (int i = 0; i < MIME_TYPE_WHITELIST.length; i++){
            if(mimeType2.equals(MIME_TYPE_WHITELIST[i])){
                isFile = true;
            }
        }
        
        if(LOG.isDebugEnabled()){
            LOG.debug("############## FileFilter.checkFileMime :: " + mimeType2 + " : " + isFile);
        }

        return isFile;
    }
    
    /**
     * 확장자와  MIME type 포함한 파일 유효성 채크
     *
     * @param fullName
     * @param mimeType
     * @return
     */
    public static boolean checkFileAndMime(String fullName, String mimeType){
        boolean isFile = false;
        
        String fileExt = fullName.substring(fullName.lastIndexOf(".") + 1).toLowerCase();
        String fileName = fullName.substring(0, fullName.lastIndexOf("."));
        
        if(checkFileExt(fileExt) && checkFileName(fileName) && checkFileMime(mimeType)){
            isFile = true;
        }
        
        return isFile;
    }
}