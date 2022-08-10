package jksoft.com.web.view;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

/**
 * <pre>
 * 파일을 다운로드 한다.
 * - PDF의 경우 화면 출력이다.
 * </pre>
 *
 * @ClassName   : FileDownloadView.java
 * @Description : 파일 다운로드
 * @author Jeong.hyoungjea
 * @since 2016. 2. 1.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2016. 2. 1.     Jeong.hyoungjea     최초 생성
 * </pre>
 */

public class FileDownloadView extends AbstractView {
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    /*
     * 
     * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        byte[] b = new byte[8192];
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        
        try{
            File downFile = (File)model.get("contentFile");
            
            // 자료는 다운로드
            if(downFile.getName().matches(".*(.pdf)")){
                httpServletResponse.setContentType("application/pdf; charset=UTF-8");
                httpServletResponse.setHeader("Content-Disposition", "inline;filename=\"" + URLEncoder.encode((String)model.get("contentFileName"), "UTF-8") + "\";");
            }else{
                httpServletResponse.setContentType("application/octet-stream; charset=UTF-8");
                httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode((String)model.get("contentFileName"), "UTF-8") + "\";");
                httpServletResponse.setHeader("Content-Transfer-Encoding", "binary");
            }
            httpServletResponse.setHeader("Pragma", "no-cache");
            httpServletResponse.setHeader("Expires", "0");
            httpServletResponse.setContentLength((int)downFile.length());   //Webtob FullBeffered방식사용시 필요.
            
            bufferedInputStream = new BufferedInputStream(new FileInputStream(downFile));
            bufferedOutputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());        
        
            int read = 0;
            while((read = bufferedInputStream.read(b)) != -1) {
                bufferedOutputStream.write(b, 0, read);
            }
        }catch(FileNotFoundException fileNotFoundException){
            log.error("=========  [FileDownloadView.fileNotFoundException() :: ERROR] : {}", fileNotFoundException.getMessage());
        }finally{
            try{
                if(bufferedOutputStream != null){
                    bufferedOutputStream.close();
                }
            }catch (Exception exception) {
                if(log.isDebugEnabled()){
                    log.debug("=========  [FileDownloadView.renderMergedOutputModel() :: ERROR] : {}", exception);
                }
            }        
        
            if(bufferedInputStream != null){
                try{ 
                    bufferedInputStream.close();
                }catch (Exception exception){
                    if(log.isDebugEnabled()){
                        log.debug("=========  [FileDownloadView.renderMergedOutputModel() :: ERROR] : {}", exception);
                    }
                }
            }
        }
    }
}
