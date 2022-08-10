package jksoft.com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jksoft.com.web.vo.AttachFileVO;

/**
 * <pre>
 * FTP로 파일을 업로드 한다.
 * </pre>
 *
 * @ClassName   : FTPUpLoader.java
 * @Description : FTP로 파일을 업로드 한다.
 * @author ryuys
 * @since 2014. 5. 15.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2014. 5. 15.     ryuys     최초 생성
 * </pre>
 */
@Component("ftpUpLoaderUtil")
public class FTPUpLoaderUtil {
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    /**
     * FTP설정정보
     */    
    @Value(value="#{global['ftp.ip']}")
    private String ip; 
    
    @Value(value="#{global['ftp.port']}")
    private int port; 
    
    @Value(value="#{global['ftp.id']}")
    private String id; 
    
    @Value(value="#{global['ftp.pw']}")
    private String password; 
    
    /*
     * 파일 저장 경로
     */
    @Value(value="#{global['file.system.path.default']}")
    private File fileSavePath;
    
    /**
    * 
    * @param ip ftp서버ip
    * @param port ftp서버port
    * @param id 사용자id
    * @param password 사용자password 
    * @param folder ftp서버에생성할폴더
    * @param lmSaveFileInfo 업로드list
    * @return
    */
    public List<AttachFileVO> sendFtpServer(List<AttachFileVO> lmSaveFileInfo) {
        FTPClient ftp = null;
        String folder = lmSaveFileInfo.get(0).getMenuCd();
        
        List<AttachFileVO> lmSaveFileInfo2 = new ArrayList<AttachFileVO>();
        
        try {
            ftp = new FTPClient();
            ftp.connect(ip, port);
    
            //응답코드가 비정상이면 접속을 종료한다.
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                System.exit(1); //실행중인 자바 가상머신 종료.
            }
    
            //FTP 로그인
            if(ftp.login(id, password)) {
        
                //text(plain text) 파일 업로드시 문제가 없지만 
                //binary 파일(이미지 파일 등) 업로드시 문제가 생길 경우 (이미지 파일 업로드시 이미지가 깨진다) > 바이너리 파일의 경우, setFileType(FTP.BINARY_FILE_TYPE)
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                ftp.enterLocalPassiveMode();//패시브모드로 설정
            
                ftp.makeDirectory(folder);
                
                //현재 위치에서 파일이 저장될 곳을 지정해준다.(접속한 위치를 기준으로 full path를 적어주어야 경로가 이동됨)
                ftp.changeWorkingDirectory(folder);
            
                for(AttachFileVO vo : lmSaveFileInfo){
                    
                    if(vo.getStatus() && !"".equals(vo.getFilNm())){
    
                        File uploadFile = new File(fileSavePath.getAbsolutePath() + File.separator + vo.getMenuCd() + File.separator + vo.getFilNm());
                        FileInputStream fis = null;
                    
                        try {
                            fis = new FileInputStream(uploadFile);
                            ftp.storeFile(vo.getFilNm(), fis);   //실경로에 파일저장
                            vo.setStatus(true);
                        } catch(IOException e) {
                            e.printStackTrace();
                            vo.setStatus(false);
                        } finally {
                            if (fis != null) {
                                try {fis.close(); } 
                                catch(IOException e) {
                                    if(log.isDebugEnabled()){
                                        log.debug("[FTPUpLoader.sendFtpServer] FTP 파일업로드 :: 파일닫기 실패 ", e);
                                    }
                                }
                            }
                        }
                    }
                    
                    lmSaveFileInfo2.add(vo);
                }
            }
        
            ftp.logout();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ftp != null && ftp.isConnected()) {
                try { ftp.disconnect(); } catch (IOException e) {
                    if(log.isDebugEnabled()){
                        log.debug("[FTPUpLoader.sendFtpServer] FTP 파일업로드 :: FTP연결종료 실패(ftp.disconnect failed) ", e);
                    }
                }
            }
        }
        return lmSaveFileInfo2;
    }
}