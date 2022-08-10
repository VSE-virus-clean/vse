package jksoft.com.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.google.gson.Gson;

import jksoft.com.web.vo.SessionVO;


/**
 * <pre>
 * Util성 메소드 모음.
 * 세션정보 등을 처리
 * </pre>
 *
 * @ClassName   : MultiUtil 
 * @Description : Ajax 처리를 위한 Util Class
 * @author Jeong.hyoungjea
 * @since 2013. 8. 9.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2013. 8. 9.     Jeong.hyoungjea     최초 생성
 * </pre>
 */
@Component("multiUtil")
public class MultiUtil {    
    
    private final static Logger LOG = LoggerFactory.getLogger(MultiUtil.class);
    
    public static Properties globalConfig = null;
    
    /**
     * 모바일사이트 HOST Prefix
     */
    @Value(value = "#{global['site.user.mobile.host']}")
    private String strUserMobileHost;
    
    
    /**
     * WEB Resource 확인
     *
     * @param strRequestURI
     * @return true : web resource
     */
    public boolean checkWebResource(String strRequestURI) {
        
        boolean bRtn = false;
        
        String[] aStrResource = {".js", ".css", ".html", ".ico", ".jpg", ".gif", ".png"};
        
        /*
         * 확장자 확인
         */
        for(String resource : aStrResource){
            if(strRequestURI.endsWith(resource)){
                bRtn = true;
                break;
            }
        }

        return bRtn;
    }
    
    /**
     * Request가 Ajax통신인지 확인한다.
     *
     * @param httpServletRequest
     * @return  true : Ajax통신
     */
    public boolean checkAjaxRequest(HttpServletRequest httpServletRequest) {
        
        boolean bRtn = false;
        
        try{
            if(httpServletRequest.getHeader("X-Requested-With") != null
                    && "xmlhttprequest".equals(httpServletRequest.getHeader("X-Requested-With").toLowerCase())){
                bRtn = true;
            }            
        }catch(Exception exception){
            if(LOG.isDebugEnabled()){
                LOG.debug("== MultiUtil.checkAjaxRequest :: Exception {} ", exception);
            }
        }      
        
        
        return bRtn;
    }
    
    /**
     * HttpServletRequest
     *
     * @return
     */
    public HttpServletRequest getHttpServletRequest(){
        
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();

        return servletRequestAttributes.getRequest();
    }
    
    /**
     * HttpServletResponse
     *
     * @return
     */
    public HttpServletResponse getHttpServletResponse(){
        
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();

        return servletRequestAttributes.getResponse();
    }
    
    /**
     * 세션정보 확인
     *
     * @return userId : 아이디 <br/>
     *         userNm : 이름 <br/>
     *         cryptoUserNm : 암호화처리된 이름
     *         userEml : 이메일 <br/>
     *         locale : Locale 정보
     *         isLogin : {Boolean} true-로그인 / false-비로그인 <br/>
     *         accIp : 접속아이피 <br/>
     *         accUri : 접속 URI<br/>
     *         accReferer : Referer
     */
    public Map<String,Object> getSessionInfo(){
        return this.getSessionInfo(this.getHttpServletRequest());
    }
    
    /**
     * 세션정보 확인 :: Attribute 설정
     *
     * @param strAttributeName <br/>
     *          id : 아이디 <br/>
     *          name : 이름 <br/>
     *          email : 이메일 <br/>     
     * @return Attribute 값
     */
    public String getSessionInfo(String strAttributeName){
         return this.getSessionInfo(this.getHttpServletRequest(), strAttributeName);
    }
    
    public Object getSessionInfo2(String strAttributeName){
        return this.getSessionInfo2(this.getHttpServletRequest(), strAttributeName);
    }

    /**
     * 세션정보 확인
     *
     * @param httpServletRequest
     * @return userSn : UUID <br/>
     * 		   userId : 아이디 <br/>
     *         userNm : 이름 <br/>
     *         userEml : 이메일 <br/>
     *         isLogin : {Boolean} true-로그인 / false-비로그인 <br/>
     *         accIp : 접속아이피 <br/>
     *         accUri : 접속 URI<br/>
     *         accReferer : Referer
     */
    public Map<String,Object> getSessionInfo(HttpServletRequest httpServletRequest){
        
        Map<String, Object> mResult = new HashMap<String,Object>();        
        
        try{
            //로그인 여부 확인
            if(httpServletRequest.getSession().getAttribute("sessionVO") != null){
                SessionVO sessionVO = (SessionVO)httpServletRequest.getSession().getAttribute("sessionVO");
                
                if(sessionVO == null){
                    throw new IllegalArgumentException();
                }else{
                	mResult.put("userSn", sessionVO.getSn());
                    mResult.put("userId", sessionVO.getId());
                    mResult.put("userNm", sessionVO.getName());
                    mResult.put("userEml", sessionVO.getEmail());
                    mResult.put("userNick", sessionVO.getNick());
                    mResult.put("isLogin", true);   //로그인유무      
                }
            }else{
                mResult.put("isLogin", false);   //로그인유무
            }
            
            mResult.put("siteCd", this.getSiteCode().toUpperCase());
            mResult.put("accIp", httpServletRequest.getRemoteAddr());   //접속 IP
            mResult.put("accUri", httpServletRequest.getRequestURI());  //접속 URI
            mResult.put("accReferer", httpServletRequest.getHeader("referer"));  //referer
            
        }catch(Exception exception){
            if(LOG.isDebugEnabled()){
                LOG.debug("== MultiUtil.getSessionInfo(HttpServletRequest request) :: Exception {} ", exception);
            }
        }
        
        return mResult;
        
    }
        
    /**
     * 세션정보 확인 :: Attribute 설정
     *
     * @param httpServletRequest
     * @param strAttributeName <br/>
     *          id : 아이디 <br/>
     *          name : 이름 <br/>
     *          email : 이메일 <br/>     
     * @return Attribute 값
     */
    public String getSessionInfo(HttpServletRequest httpServletRequest, String strAttributeName){
        
        String strResult = "";        
        
        try{
            //로그인 여부 확인
            if(httpServletRequest.getSession().getAttribute("loginVO") != null){
                SessionVO sessionVO = (SessionVO)httpServletRequest.getSession().getAttribute("loginVO");
                
                if(sessionVO == null){
                    throw new IllegalArgumentException();
                }else{
                	if("sn".equals(strAttributeName)){
                		strResult =  String.valueOf(sessionVO.getSn());       //고유번호
                	}else if("id".equals(strAttributeName)){
                        strResult =  sessionVO.getId();       //아이디
                    }else if("name".equals(strAttributeName)){
                        strResult =  sessionVO.getName();     //이름
                    }else if("email".equals(strAttributeName)){
                        strResult =  sessionVO.getEmail();   //이메일
                    }
                }
            }
        }catch(Exception exception){
            if(LOG.isDebugEnabled()){
                LOG.debug("== MultiUtil.getSessionInfo(HttpServletRequest request, String strAttributeName) :: Exception {} ", exception);
            }
        }
        
        return strResult;
    }
    
    /**
     * 세션정보 확인 : Object
     *
     * @param httpServletRequest
     * @param strAttributeName
     * @return
     */
    public Object getSessionInfo2(HttpServletRequest httpServletRequest, String strAttributeName){
        
        Object objectRtn = null;    
        
        try{
            if(httpServletRequest.getSession().getAttribute(strAttributeName) != null){
                objectRtn = httpServletRequest.getSession().getAttribute(strAttributeName);
            }
        }catch(Exception exception){
            if(LOG.isDebugEnabled()){
                LOG.debug("== MultiUtil.getSessionInfo2(HttpServletRequest request, String strAttributeName) :: Exception {} ", exception);
            }
        }
        
        return objectRtn;
    }
    
    
    
    /**
     * 객체를 Json형식으로 변환한다. 
     *
     * @param object
     * @return
     */
    public String toJson(Object object) {
        
        Gson gson = new Gson();
        
        return gson.toJson(object, object.getClass());
    }
    
    /**
     * Cookie 설정
     * @param httpServletResponse
     * @param strName
     * @param strValue
     * @param iTime
     */
    public void setCookieValue(HttpServletResponse httpServletResponse, String strName, String strValue, int iTime )
    {
        Cookie cookie = new Cookie(strName, strValue);
        cookie.setPath("/");
//        cookie.setDomain("*.viruscleanlab.com");
        cookie.setMaxAge(iTime);
        //cookie.setSecure(true);       //SSL 미적용
        httpServletResponse.addCookie(cookie);
    }

    /**
     * Cookie 값 가져오기
     * @param req
     * @param cookieName
     * @return
     */
    public String getCookieValue(HttpServletRequest httpServletRequest, String cookieName)
    {
        String value = null;

        Cookie[] cooks = httpServletRequest.getCookies();
        if( cooks != null )
        {
            for(int i=0; i<cooks.length; i++)
                if( cooks[i].getName().equals(cookieName) )
                {
                    value = cooks[i].getValue();
                    break;
                }
        }

        return value;
    }
    
    /**
     * 파라미터 값 출력
     *
     * @param httpServletRequest
     */
    public void printParameter(HttpServletRequest httpServletRequest)
    {
        Map<String,String[]> hmParam = httpServletRequest.getParameterMap();  
        
        //fortify 수정 :: hmParam이Null확인
        if(!(hmParam == null || hmParam.isEmpty())){
            for(String hh : hmParam.keySet()){
                String[] values = hmParam.get(hh);
                if(values != null){                        
                    for(int i = 0; i < values.length ; i++){
                        if(LOG.isDebugEnabled()){
                            LOG.debug("MultiUtil.printParameter :: [KEY] " + hh + " :: [VALUE("+ i +")] " + values[i]);
                        }
                    }
                }
                
            }
        }
    }
    
    
    /**
     * 파라미터를 문자열로  생성후에 리턴
     * URL리다이렉트시에 파라미터도 같이 넘겨주는곳에 사용
     *
     * @param httpServletRequest
     * @return
     */
    public String getParameterToString(HttpServletRequest httpServletRequest){
        
        String strRtn = "";
        StringBuilder stringBuilder = new StringBuilder();
        
        Map<String,String[]> hmParam = httpServletRequest.getParameterMap();  
        
        if(!(hmParam == null || hmParam.isEmpty())){
            for(String hh : hmParam.keySet()){
                String[] values = hmParam.get(hh);

                if(values != null){                        
                    for(int i = 0; i < values.length ; i++){
                        stringBuilder.append("&").append(hh).append("=").append(values[i]);
                    }
                }
            }
            
            strRtn = "?" + stringBuilder.substring(1);
        }
        
        return strRtn;
    }
    
    

    /**
     * REFERER 체크
     * - UERYSTRING 때문에 URL 시작부분만 검사
     * @return true : 동일
     *         false : 동일하지 않음
     */
    public boolean checkRefer(HttpServletRequest httpServletRequest, String url)
    {
        String refer = "";
        
        String uri = httpServletRequest.getHeader("host") + httpServletRequest.getContextPath() + "/" + this.getSiteCode() + url;
        
        if(httpServletRequest.getHeader("referer") != null){
            refer = httpServletRequest.getHeader("referer").split("://")[1];
        }
        
        return refer.startsWith(uri) ? true : false;
    }
    
    
    /**
     * CONTEXTPATH와 LOCALE정보를 삭제 한 RequestURI
     *
     * @return /contextpath/siteCode/Url.do => /siteCode/Url.do
     */
    public String getRequestURI(){
        return this.getRequestURI(this.getHttpServletRequest());
    }
    
    /**
     * CONTEXTPATH와 LOCALE정보를 삭제 한 RequestURI
     *
     * @return /contextpath/siteCode/Url.do => /siteCode/Url.do
     */
    public String getRequestURI(HttpServletRequest httpServletRequest){
        return httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
    }
    
    /**
     * CONTEXTPATH와 LOCALE정보를 삭제 한 RequestURI
     *
     * @return /contextpath/siteCode/Url.do => /siteCode/Url.do
     */
    public String getRequestURINotLocale(HttpServletRequest httpServletRequest){
        return httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length()).substring(3);
    }
    
    /**
     * CONTEXTPATH / SITECD 를 제거한 RefererURI
     */
    public String getRefererURI(){
        return this.getRefererURI(this.getHttpServletRequest());
    }
    
    /**
     * CONTEXTPATH / SITECD 를 제거한 RefererURI
     *
     * @param httpServletRequest
     * @return
     */
    public String getRefererURI(HttpServletRequest httpServletRequest) {
        
        String refererURI = "";
        
        if(httpServletRequest.getHeader("referer") != null){
            refererURI = httpServletRequest.getHeader("referer").split("://")[1]
                                           .substring((httpServletRequest.getHeader("host") + httpServletRequest.getContextPath() + "/" + this.getSiteCode(httpServletRequest)).length());
        }
        
        return refererURI;
    }

    /**
     *   Locale정보를 찾는다.
     *
     * @return /contextpath/siteCode/Url.do => siteCode
     */
    public String getSiteCode(){
         
        return this.getSiteCode(this.getHttpServletRequest());
    }
    
    /**
     *  Locale정보를 찾는다.
     *
     * @return /contextpath/siteCode/Url.do => siteCode
     */
    public String getSiteCode(HttpServletRequest httpServletRequest){
        
        String strRtn = "";
        
        strRtn = this.getCookieValue(httpServletRequest, "language") == null ? this.getLocale(httpServletRequest) : this.getCookieValue(httpServletRequest, "language");
        
        return strRtn;
    }
    
    /**
     * 브라우저 Locale확인
     *
     * @param httpServletRequest
     * @return
     */
    public String getLocale(HttpServletRequest httpServletRequest){
        
        String strRtn = "en";
        
        if(LocaleContextHolder.getLocale().toString().startsWith("ko") || LocaleContextHolder.getLocale().toString().startsWith("kr")){
            strRtn = "kr";
        }else if(LocaleContextHolder.getLocale().toString().startsWith("zh")){
            strRtn = "cn";
        }
        
        return strRtn;
    }
    
    /**
     * 브라우저 Locale확인
     *
     * @param httpServletRequest
     * @return
     */
    public String getCheckLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        
        String strRtn = "en";
        
        LOG.error("MultiUtil.getCheckLocale : this.getCookieValue(language) : " + this.getCookieValue(httpServletRequest, "language"));
        
        if(this.getCookieValue(httpServletRequest, "language") == null){
            
            if(LocaleContextHolder.getLocale().toString().startsWith("ko") || LocaleContextHolder.getLocale().toString().startsWith("kr")){
                strRtn = "kr";
            }else if(LocaleContextHolder.getLocale().toString().startsWith("zh")){
                strRtn = "cn"; //zh
            }
            
            //Locale locale = new Locale("cn".equals(strRtn) ? "zh" : strRtn);
            CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
            cookieLocaleResolver.setLocale(httpServletRequest, httpServletResponse,  new Locale(strRtn));
            
        }else{
            strRtn = this.getCookieValue(httpServletRequest, "language");
        }
        
        return strRtn;
    }
    
    
    /**
     * CONTEXTPATH 
     *
     * @return 
     */
    public String getContextPath(){

        return this.getContextPath(this.getHttpServletRequest());
    }
    
    /**
     * CONTEXTPATH
     *
     * @return 
     */
    public String getContextPath(HttpServletRequest httpServletRequest){
         
        return httpServletRequest.getContextPath();
    }
    
    /**
     * RequestURL을 검색용 SearchURI로 만들기
     *
     * @param strRequestURI
     * @return
     */
    public String getRequestSearchUrI(String strRequestURI){
        
        String strRequestURI2 = strRequestURI;
        
        String[] strTemp = {"Man.ket", "Reg.ket", "Mod.ket", "Det.ket", "Del.ket", "List.ket", "Xls.ket", "Detail.ket", ".ket"};
        String[] strTemp1 = {"S0","S1","S2","S3","S4","S5","/pop", "Completion" , "Compare"};
        
        
        for(int i = 0 ; i < strTemp.length; i++){
            strRequestURI2 = strRequestURI2.replace(strTemp[i], "");
        } 
        
        for(int i = 0 ; i < strTemp1.length; i++){
            strRequestURI2 = strRequestURI2.replace(strTemp1[i], "");
        } 
        
        return strRequestURI2;
    }
    
    /**
     * RequestURL 상세 / 수정페이지 인지 확인 
     * - true이면 상세 페이지이고 게시물의 로그를 확인 할수 있는 버튼이 노출되어야 한다.
     *
     * @param strRequestURI
     * @return
     */
    public boolean getRequestSearchUrI2(String strRequestURI){
        
        String[] strTemp = {"Mod.ket", "Detail.ket"};
        int index = 0;
        
        for(int i = 0 ; i < strTemp.length; i++){
            index += strRequestURI.indexOf(strTemp[i]);
        } 
        
        return index > 0;
    }
  
    

    /**
     * 사내망 접속 여부
     * 
     * - 10.216.x.x
     * - 10.247.x.x
     * - 10.248.x.x
     * - 10.249.x.x
     * @return true : 사내 / false
     */
    public boolean isIntranet() {

        boolean bRtn = false;
        
        HttpServletRequest httpServletRequest = this.getHttpServletRequest();
        
        String[] intranetIp = {"10.216", "10.247", "10.248", "10.249", "127.0.0.1"};
        
        if(LOG.isDebugEnabled()){
        	LOG.debug("== MultiUtil.isIntranet : " + httpServletRequest.getRemoteAddr());
        }
        
        for(String ip : intranetIp){
            if(httpServletRequest.getRemoteAddr().startsWith(ip)){
                bRtn = true;
                break;
            }
        }

        return bRtn;
    }
    
    /**
     * 접속 사이트가 웹인지 모바일인지 확인 하기
     *
     * @return true:모바일
     */
    public boolean checkMobileHost(){
        HttpServletRequest httpServletRequest = this.getHttpServletRequest();
        return this.checkMobileHost(httpServletRequest);
    }
    
    /**
     * 접속 사이트가 웹인지 모바일인지 확인 하기
     *
     * @param httpServletRequest
     * @return true:모바일
     */
    public boolean checkMobileHost(HttpServletRequest httpServletRequest){
        
        //String strRequestURI = this.getRequestURI();
        //return strRequestURI.startsWith("/m/") ? true : false;

        if(LOG.isDebugEnabled()){
            LOG.debug("MultiUtil.checkMobileHost :: " + "strHost : " + httpServletRequest.getHeader("host") + ", strUserMobileHost : " + strUserMobileHost);
        }
        
        String strHost = httpServletRequest.getHeader("host");
        return strHost.startsWith(strUserMobileHost) ? true : false;
        
    }
    
    /**
     * 접속 Device 종류
     * N : PC
     * T : 태블릿
     * M : 모바일
     *
     * @return N/T/M
     */
    public String checkDevice(){
        
    	String deviceCode = "N";
        
        Device device = DeviceUtils.getCurrentDevice(RequestContextHolder.currentRequestAttributes());
        
        if(device.isTablet()){
            deviceCode = "T";
        }else if(device.isMobile()){
            deviceCode = "M";
        }
        
        return deviceCode;
    }
    
    /**
     * Apple 제품 여부 확인
     * - 아이폰 / 아이팟 / 아이패드
     * 
     * @param httpServletRequest
     * @return
     */
    public boolean checkAppleDevice(){
        HttpServletRequest httpServletRequest = this.getHttpServletRequest();
        return this.checkAppleDevice(httpServletRequest);
    }
    
    /**
     * Apple 제품 여부 확인
     * - 아이폰 / 아이팟 / 아이패드
     *
     * @param httpServletRequest
     * @return
     */
    public boolean checkAppleDevice(HttpServletRequest httpServletRequest){
     
        boolean bRtn = false;
        
        // 아이폰과 아이팟인지만 구분한다.
        String[] arryMobileOs = { "iphone", "ipod", "ipad" };
        
        if (httpServletRequest.getHeader("user-agent") != null
                && !"".equals(httpServletRequest.getHeader("user-agent"))) {

            String strUserAgent = httpServletRequest.getHeader("user-agent").toLowerCase();

            for (int i = 0; i < arryMobileOs.length; i++) {
                if (strUserAgent.indexOf(arryMobileOs[i]) > -1) {
                    if(LOG.isDebugEnabled()){
                        LOG.debug("checkAppleDevice arryMobileOs :: " + arryMobileOs[i]);
                    }
                    bRtn = true;
                    break;
                }
            }
        }
        
        return bRtn;
    }
    
    /**
     * Global Config 조회
     * - global-config.xml
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public String getGlobalConfig(String key) throws Exception{
        
        if(globalConfig == null){
            LOG.error("getGlobalConfig : global-config.xml 로딩.");
            globalConfig = new Properties();
            globalConfig.loadFromXML(new ClassPathResource("/config/global-config.xml").getInputStream());
        }
        
        return (String)globalConfig.get(key);
    }
}
