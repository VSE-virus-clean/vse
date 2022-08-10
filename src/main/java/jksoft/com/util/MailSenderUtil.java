package jksoft.com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


/**
 * <pre>
 * STMP 메일 발송
 * 
 * ref :
 * @Resource(name="mailSenderUtil")
 * private MailSenderUtil mailSenderUtil;
 * 
 * Map<String, String> mParam = new HashMap<String, String>();
 * mParam.put("$$NEW_PASSWD$$", NEW_PASSWD);        //임시 비밀번호
 * 
 * mailSenderUtil.sendMailResponsePassword(UserEmail, mParam);
 * </pre>
 *
 * @ClassName   : MailSenderUtil.java
 * @Description : 메일 발송
 * @author Jeong.hyoungjea
 * @since 2013. 9. 23.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2013. 9. 23.     Jeong.hyoungjea     최초 생성
 * </pre>
 */
@Component("mailSenderUtil")
public class MailSenderUtil {
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Resource(name="mailSender")
    private JavaMailSender mailSender;   
    
    @Resource(name="mailMessage")
    private SimpleMailMessage mailMessage;    
    
    
    /**
     * 서비스 대표 이메일 사용자명
     */    
    @Value(value="#{global['mail.primaryMailPersonal']}")
    private String primaryMailPersonalKR;
    
    @Value(value="#{global['mail.primaryMailPersonal']}")
    private String primaryMailPersonalEN;
    
    @Value(value="#{global['mail.primaryMailPersonal']}")
    private String primaryMailPersonalCN;
    
    /**
     * 서비스 대표 이메일  주소
     */    
    @Value(value="#{global['mail.primaryMailAdress']}")
    private String primaryMailAddress; 
    
    /**
     * 개발모드에서 사용자 이메일  주소
     */    
    @Value(value="#{global['mail.testMailAdress']}")
    private String testMailAdress;
    
    /**
     * 메일 이미지 서버의 domain또는 ip를 등록
     */
    @Value(value="#{global['mail.imageDomain']}")
    private String imageDomain;
    
    /**
     * 메일폼 html소스 위치
     */
    @Value(value="#{global['mail.htmlFilePath']}")
    private File fileHtmlPath;
    
    /**
     * 관리자 사이트 도메인
     */
    @Value(value="#{global['site.admin.domain']}")
    private String siteAdminDomain;
    
    /**
     * 사용자 사이트 도메인
     */
    @Value(value="#{global['site.user.domain']}")
    private String siteUserDomain;
    
    /**
     * 개발 / 운영 모드 확인
     */
    @Value(value="#{global['service.mode']}")
    private String serviceMode;
    
   
    /**
     * 관리자에게 메일 보내기  
     * - 발신 대표 이메일 설정
     *
     * @param strMail               수신자 메일주소
     * @param strTitle               메일제목
     * @param strAdminMailFileName   HTML 파일명
     * @param mContent               HTML내용 중 다른 텍스트로 변환이 필요한 파라미터들
     * @return true = 성공
     */
    public boolean mailSendToAdmin(String strMail, String strTitle, String strAdminMailFileName, Map<String, String> mContent){
        
        List<Map<String, Object>> lAdminList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mgrEml", strMail);
        lAdminList.add(map);
        
        return mailSendToAdmin(lAdminList, strTitle, strAdminMailFileName, mContent);
    }
    
   /**
    * 관리자에게 메일 보내기  
    * - 발신 대표 이메일 설정
    *
    * @param lAdminList             수신자 메일주소
    * @param strTitle               메일제목
    * @param strAdminMailFileName   HTML 파일명
    * @param mContent               HTML내용 중 다른 텍스트로 변환이 필요한 파라미터들
    * @return true = 성공
    */
    public boolean mailSendToAdmin(List<Map<String, Object>> lAdminList, String strTitle, String strAdminMailFileName, Map<String, String> mContent){
        return mailSendToAdmin(primaryMailAddress, lAdminList, strTitle, strAdminMailFileName, mContent);
    }
    
    
    /**
     * 관리자에게 메일 보내기
     *
     * @param strFromMailAddress    발신자 이메일주소
     * @param lAdminList            수신자 메일주소
     * @param strTitle              메일제목
     * @param strAdminMailFileName  HTML 파일명
     * @param mContent              HTML내용 중 다른 텍스트로 변환이 필요한 파라미터들
     * @return true = 성공
     */
    public boolean mailSendToAdmin(String strFromMailAddress, List<Map<String, Object>> lAdminList, String strTitle, String strAdminMailFileName, Map<String, String> mContent){
        
        boolean bResult = false; 
        
        MimeMessage mailMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper mailMessageHelper;
        
        try{
            //메일 설정
            mailMessage.setHeader( "Mime-Version", "1.0" );
            mailMessage.setHeader( "X-Mailer", "hsteel mailer" );
            mailMessage.setHeader( "Content-Type", "text/html; charset=UTF-8" );
            mailMessageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
            
            //발신자 메일주소
            mailMessageHelper.setFrom(strFromMailAddress, primaryMailPersonalKR); 
            
            //메일 제목
            mailMessageHelper.setSubject(strTitle);       
            
            //메일 본문 삽입 : HTML지원
            mailMessageHelper.setText(this.replaceContent(mContent, this.readFile(strAdminMailFileName)), true);
            
            for(Map<String, Object> map : lAdminList){
                //수신자 메일주소
                if("env".equals(serviceMode)){
                    mailMessageHelper.setTo((String)map.get("mgrEml"));     
                }else{
                    mailMessageHelper.setTo(testMailAdress);
                }
                
                //메일 발송
                this.mailSender.send(mailMessage);
                
                if(log.isErrorEnabled()){
                    log.error("== [메일 발송] 완료 :: " + (String)map.get("mgrEml") + " ==");
                }
            }
            
            bResult = true;
        }catch(Exception exception){
            if(log.isErrorEnabled()){
                log.error("== MailSenderUtil.mailSend() :: Failed SendMail \n", exception);
            }
        }
        
        return bResult;
    }
    
    /**
     * 사용자에게 메일을 발송한다.
     * - 같은 내용의 메일을 여러명에게 다수에게 전송할때 사번을 List에 담아서 보내준다.
     * - 메일주소 / 사원 / 부서명등을 mContent에 저장해서 Html의 내용을 Replace해준다.
     * - mContent에 저장되는 Key는 아래와 같다.
     *   사원아이디 : $$EE_ID$$
     *   사원이름 : $$EE_NM$$
     *   부서명이름 : $$OPS_NM$$
     *   직책명 : $$RBOF_NM$$
     *   직위명 : $$POA_NM$$
     *
     * @param eeIdList              수신자 사원아이디 리스트
     * @param strTitle              메일제목
     * @param strUserMailFileName   HTML 파일명
     * @param mContent              HTML 내용 중 다른 텍스트로 변환이 필요한 파라미터들
     * @return true = 성공
     */
    public boolean mailSendToUserId(List<String> eeIdList, String strTitle, String strUserMailFileName, Map<String, String> mContent ) throws Exception{
        
        boolean bRtn = false;
        
        if(!eeIdList.isEmpty()){
            int successCount = 0;
            
            for(String strToUserId : eeIdList){
                successCount = mailSendToUserId(strToUserId, strTitle, strUserMailFileName, mContent) ? 1 : 0;
            }
            
            bRtn = successCount == eeIdList.size() ? true : false;
        }
        
        return bRtn;
    }
    
    
    /**
     * 사용자에게 메일을 발송한다.
     * - 사번을 받아서 사원에 대한 정보를 조회후에 발송한다.
     * - 메일주소 / 사원 / 부서명등을 mContent에 저장해서 Html의 내용을 Replace해준다.
     * - mContent에 저장되는 Key는 아래와 같다.
     *   사원아이디 : $$EE_ID$$
     *   사원이름 : $$EE_NM$$
     *   부서명이름 : $$OPS_NM$$
     *   직책명 : $$RBOF_NM$$
     *   직위명 : $$POA_NM$$
     *   
     * @param strToUserId           수신자 사원아이디
     * @param strTitle              메일제목
     * @param strUserMailFileName   HTML 파일명
     * @param mContent              HTML 내용 중 다른 텍스트로 변환이 필요한 파라미터들
     * @return true = 성공
     */
    public boolean mailSendToUserId(String strToUserId, String strTitle, String strUserMailFileName, Map<String, String> mContent ) throws Exception{
        
     //   Map<String, Object> mEeResult = organizationService.selectOrganizationByEe(strToUserId);
      //  EmployeeVO employeeVO = (EmployeeVO)mEeResult.get("info");
        
//        mContent.put("$$EE_ID$$", employeeVO.getEeId());
//        mContent.put("$$EE_NM$$", employeeVO.getEeNm());
//        mContent.put("$$OPS_NM$$", employeeVO.getOpsNm());
//        mContent.put("$$RBOF_NM$$", employeeVO.getRbofNm());
//        mContent.put("$$POA_NM$$", employeeVO.getPoaNm());
//        
//        if("env".equals(serviceMode)){
//            mContent.put("$$USER_EML$$", employeeVO.getEml());
//        }else{
//            mContent.put("$$USER_EML$$", testMailAdress);
//        }
//        
        return mailSendToUser(primaryMailAddress, mContent.get("$$USER_EML$$"), strTitle, strUserMailFileName, mContent);
    }
    
    
    /**
     * 사용자에게 메일을 발송한다.
     *
     * @param userList              수신자 리스트
     * @param strTitle              메일제목
     * @param strUserMailFileName   HTML 파일명
     * @param mContent              HTML 내용 중 다른 텍스트로 변환이 필요한 파라미터들
     * @return true = 성공
     */
    public boolean mailSendToUser(String strToMailAddress, String strTitle, String strUserMailFileName, Map<String, String> mContent ){
        return mailSendToUser(primaryMailAddress, strToMailAddress, strTitle, strUserMailFileName, mContent);
    }
    
    /**
     * 사용자에게 메일을 발송한다.
     *
     * @param strFromMailAddress    발신자 이메일주소
     * @param strToMailAddress      수신자 이메일주소
     * @param strTitle              메일제목
     * @param strUserMailFileName   HTML 파일명
     * @param mContent              HTML 내용 중 다른 텍스트로 변환이 필요한 파라미터들
     * @return true = 성공
     */
    public boolean mailSendToUser(String strFromMailAddress, String strToMailAddress, String strTitle, String strUserMailFileName, Map<String, String> mContent ){
        
        boolean bResult = false; 
        
        MimeMessage mailMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper mailMessageHelper;
        
        try{
            //메일 설정
            mailMessage.setHeader( "Mime-Version", "1.0" );
            mailMessage.setHeader( "X-Mailer", "hsteel mailer" );
            mailMessage.setHeader( "Content-Type", "text/html; charset=UTF-8" );
            mailMessageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
            
            //발신자 메일주소
            mailMessageHelper.setFrom(strFromMailAddress, this.getFromPersionalName(mContent)); 
            
            //메일 제목
            mailMessageHelper.setSubject(strTitle);    
            
            //메일 본문 삽입 : HTML지원
            mailMessageHelper.setText(this.replaceContent(mContent, this.readFile(strUserMailFileName)), true);
            
            //수신자들에게 메일 발송
            mailMessageHelper.setTo(strToMailAddress);
            
            //메일 발송
            this.mailSender.send(mailMessage);
            
            if(log.isErrorEnabled()){
                log.error("== [메일 발송] 완료 :: " + strToMailAddress + " ==");
            }
            
            bResult = true;  
            
        }catch(Exception exception){
            if(log.isErrorEnabled()){
                log.error("== MailSenderUtil.mailSend() :: Failed SendMail \n", exception);
            }
        }
        
        return bResult;
    }
    
    
    /**
     * 파일 읽기
     * @param filePath  파일명
     * @return
     */
    public StringBuffer readFile(String fileName){       
        return this.readFile(new File(fileHtmlPath.getAbsolutePath() + File.separator + fileName));
    }
    
    /**
     * 파일 읽기
     * @param filePath  파일명
     * @return
     */
    public StringBuffer readFile(String fileName, String siteCode){       
        return this.readFile(new File(fileHtmlPath.getAbsolutePath() + File.separator + siteCode.toLowerCase() + File.separator + fileName));
    }
    
    
    /**
     * 파일 읽기
     * @param readFile  파일
     * @return
     */
    public StringBuffer readFile(File readFile){       
        StringBuffer fileStr = new StringBuffer();
        BufferedReader in = null;
        
        try {       
            in = new BufferedReader(new InputStreamReader(new FileInputStream(readFile), "UTF-8"));
            
            String temp_str;
            while((temp_str = in.readLine())!=null) {
                fileStr.append(temp_str).append("\r\n");
            }
        }catch(Exception exception) {
            if(log.isErrorEnabled()){
                log.error("== MailSenderUtil.readFile() :: Failed \n", exception);
            }
        }finally{
            try {
                if(in != null){
                    in.close();
                }
            } catch (IOException ioException) {
                if(log.isErrorEnabled()){
                    log.error("[MailSenderUtil.readFile] 파일 닫기 실패 ", ioException);
                }
            }
        }
        
        return fileStr;
    }
    
    
    /**
     * 단어 바꾸기
     * @param strBuf    메일 양식 파일
     * @param target    변경 대상 단어
     * @param replacement   변경 단어
     */
    public void replaceInStrBuf(StringBuffer strBuf, String target, String replacement){
        int pos = 0;

        while (pos != -1) {
            pos = strBuf.indexOf(target, pos);
            if (pos != -1) {
                strBuf.replace(pos, pos + target.length(), replacement);
                pos++;
            }
        }
    }
    
    
    /**
     * html내용 변환
     * map key에 해당하는 html 소스를 map map.get(key)로 변환
     * Statements
     *
     * @param map                   html내용중 다른 텍스트로 변환이 필요한 파라미터들    
     * @param strContent            html내용
     * @return
     * @throws Exception
     */
    public String replaceContent(Map<String, String> map , StringBuffer strContent)throws Exception{
        
        for(String key : map.keySet()){
            this.replaceInStrBuf(strContent, key, map.get(key) );
        }
        
        //관리자  도메인 변경
        this.replaceInStrBuf(strContent, "$$ADMIN_DOMAIN$$", siteAdminDomain);
        
        //사용자 도메인 변경
        this.replaceInStrBuf(strContent, "$$USER_DOMAIN$$", siteUserDomain);
        
        //이미지서버  도메인 변경
        this.replaceInStrBuf(strContent, "$$IMAGE_DOMAIN$$", imageDomain);
        
        return strContent.toString();
    }
    
    
    /**
     * 사이트 코드별 발신자 이름 반환
     *
     * @param mContent
     */
    public String getFromPersionalName(Map<String, String> mContent) throws Exception {
        
        String strRtn = primaryMailPersonalKR;
        
        if(mContent.containsKey("$$SITE_CD$$")){
            if("EN".equals(mContent.get("$$SITE_CD$$"))){
                strRtn = primaryMailPersonalEN;
            }else if("CN".equals(mContent.get("$$SITE_CD$$"))){
                strRtn = primaryMailPersonalCN;
            }
        }
        
        return strRtn;
    }
}
