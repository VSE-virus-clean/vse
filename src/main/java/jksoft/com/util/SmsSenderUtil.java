package jksoft.com.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import vc.virusclean.cmm.service.dao.SmsDAO;
import vc.virusclean.cmm.vo.SmsVO;


/**
 * <pre>
 * SMS 메일 발송
 * - SMS 발송 업체에 따라서 달라진다.
 * 
 * ref :
 * @Resource(name="smsSenderUtil")
 * private SmsSenderUtil mailSenderUtil;
 * 
 * Map<String, String> mParam = new HashMap<String, String>();
 * mParam.put("$$NEW_PASSWD$$", NEW_PASSWD);        //임시 비밀번호
 * 
 * smsSenderUtil.sendMailResponsePassword(UserEmail, mParam);
 * </pre>
 *
 * @ClassName   : SmsSenderUtil.java
 * @Description : SMS 발송
 */
@Component("smsSenderUtil")
public class SmsSenderUtil {
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Resource(name="smsDAO")   
    private SmsDAO smsDAO;
    
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
     * 사용자 사이트 도메인
     */
    @Value(value="#{global['site.user.domain']}")
    private String siteUserDomain;
    
    /**
     * 개발 / 운영 모드 확인
     */
    @Value(value="#{global['service.mode']}")
    private String serviceMode;
    
    /*
     * SMS 발송 번호
     */
    @Value(value="#{global['sms.send_number']}")
    private String sendNumber;
   
    /**
     * 메세지 전송
     * @param smsType	 발송타입(SMS 0, FAX 2, PHONE 3, MMS 5, AT 6, FT 7, RCS 8, BI 11, BW 12)
     * @param destNumber	수신자 번호
     * @param smsContent	메세지 내용
     * @return
     * @throws Exception
     */
    public boolean smsSend(String smsType, String destNumber, String smsContent) throws Exception {
        
        List<Map<String, Object>> lDestInfo = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("number", destNumber);
        lDestInfo.add(map);
        
        return this.send(smsType, lDestInfo, smsContent);
    }
    
    /**
     * 메세지 발송 DB등록
     * 
     * @param smsType
     * @param lDestInfo
     * @param smsContent
     * @return
     * @throws Exception
     */
    public boolean send(String smsType, List<Map<String, Object>> lDestInfo, String smsContent) throws Exception {
        
    	for(Map<String, Object> map : lDestInfo){
    		
    		SmsVO smsVO = new SmsVO();
    		smsVO.setSendPhone(sendNumber);
    		smsVO.setMsgBody(smsContent);
    		smsVO.setCmid(smsType + System.currentTimeMillis());
    		smsVO.setDestPhone(((String)map.get("number")).replaceAll("-", ""));
    		
    		if("CERT".equals(smsType)){
    			smsDAO.insertSendSms(smsVO);
    		} else if("DELIVERY".equals(smsType)){
    			//배송
    			/*
    			안녕하세요 #{NAME} 고객님
    			상품 배송으로 안내드립니다
    			-상품명 : #{상품명}
    			-수량 : #{수량}
    			-배송번호 : #{주문번호 장운영구분}
    			*/
    			smsVO.setTemplateCode("bizp_2021030214001325563694623");
    			smsVO.setSenderKey("5ab97c458439daaae2b46a4c9cf66e36a37547f8");
    			smsDAO.insertSendKakao(smsVO);
    			
    		} else if("REVIEW".equals(smsType)){
    			//상품평
    			/*
    			안녕하세요 #{NAME} 고객님
    			#{상품명}은 잘 사용하고 계신가요?
    			#{상품명}의 평가를 부탁드려요
    			상품평 바로가기 https://~~
    			*/
    			smsVO.setMsgType(6);
    			smsVO.setTemplateCode("bizp_2021030214265025563336631");
    			smsVO.setSenderKey("5ab97c458439daaae2b46a4c9cf66e36a37547f8");
    			smsDAO.insertSendKakao(smsVO);
    		}
    	}
    	
        return true;
    }
    
}
