package jksoft.com.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <pre>
 * TOMCAT HTTP <-> HTTPS 세션 공유 문제 해결
 * </pre>
 *
 * @ClassName   : HttpsRequestWrapper.java
 * @Description : TOMCAT HTTP <-> HTTPS 세션 공유 문제 해결
 * @author Jeong.hyoungjea
 * @since 2016. 2. 22.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2016. 2. 22.     Jeong.hyoungjea     최초 생성
 * </pre>
 */

public class HttpsRequestWrapper extends HttpServletRequestWrapper{
    
    private HttpServletResponse response = null;
    
    public HttpsRequestWrapper (HttpServletRequest request) {
        super(request);
    }
    
    public void setResponse (HttpServletResponse response) {
        this.response = response;
    }
    
    public HttpSession getSession () {
        HttpSession session = super.getSession();
        processSessionCookie(session);
        return session;
    }
    
    public HttpSession getSession (boolean create) {
        HttpSession session = super.getSession(create);
        processSessionCookie(session);
        return session;
    }
    
    private void processSessionCookie (HttpSession session) {
        if (null == response || null == session)  return;
        
        Object cookieOverWritten = getAttribute("COOKIE_OVERWRITTEN_FLAG");
        if (null == cookieOverWritten && isSecure() && isRequestedSessionIdFromCookie() && session.isNew())  {
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setMaxAge(-1);
            String contextPath = getContextPath();
            if ((contextPath != null) && (contextPath.length() > 0)){
                cookie.setPath(contextPath);
            }else{
                cookie.setPath("/");
            }   
            response.addCookie(cookie);
            setAttribute("COOKIE_OVERWRITTEN_FLAG", "true");
        }
    }
}
