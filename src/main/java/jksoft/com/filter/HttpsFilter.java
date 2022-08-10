package jksoft.com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * TOMCAT HTTP <-> HTTPS 세션 공유 문제 해결
 * </pre>
 *
 * @ClassName   : HttpsFilter.java
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
public class HttpsFilter implements Filter{
    
    @SuppressWarnings("unused")
    private FilterConfig config;
    
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpsRequestWrapper httpsRequest = new HttpsRequestWrapper((HttpServletRequest)request);
        httpsRequest.setResponse((HttpServletResponse)response);
        chain.doFilter(httpsRequest, response);
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;       
    }
    
    public void destroy() {
        
    }
}