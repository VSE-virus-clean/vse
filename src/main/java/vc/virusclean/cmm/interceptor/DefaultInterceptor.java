package vc.virusclean.cmm.interceptor;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jksoft.com.filter.FilterUtil;
import jksoft.com.util.MultiUtil;


/**
 * <pre>
 * 사이트 접근 기본 인터셉터
 * 
 * 1. AJAX 요청처리
 * 2. 필요한 기본 정보 가져오기
 * 3. BREAD CRUMB 생성
 * </pre>
 *
 * @ClassName   : DefaultInterceptor.java
 * @Description : 사이트 접근 기본 인터셉터
 * @author Jeong.Hyoung.Jae
 * @since 2013. 7. 26.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2013. 7. 26.     Jeong.Hyoung.Jae     최초 생성
 * </pre>
 */

public class DefaultInterceptor extends HandlerInterceptorAdapter {
    
    @SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    /**
     * MultiUtil Class 선언 (MultiUtil Class Injection)
     */
    @Resource(name="multiUtil")
    private MultiUtil multiUtil;
    
    
    /**
     * Controller 가기전에 확인 한다.
     * 
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handlerMethod) throws Exception {

        /*
         * Multipart Filter적용하기 
         */
        this.setMulipartFilter(httpServletRequest);
        
        multiUtil.printParameter(httpServletRequest);
        
        return super.preHandle(httpServletRequest, httpServletResponse, handlerMethod);
        
    }

    
    /**
     * Controller 완료후 VIEW 호출 전에 확인 한다.
     * - BREAD CRUMB 생성
     * 
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
        /*
         * Multipart Filter적용하기 
         */
        this.setMulipartFilter(httpServletRequest);
        
        if(!multiUtil.checkAjaxRequest(httpServletRequest)){
        	//modelAndView.addObject("device", Character.toString(multiUtil.checkDevice())); 
        }
        
        super.postHandle(httpServletRequest, httpServletResponse, handler, modelAndView);   
    }

    
    /**
     * VIEW 완료후 호출 한다.
     * 
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handlerMethod, Exception ex) throws Exception {
        
    }

    
    /**
     * 파일 첨부시 Filter 적용 시키기
     *
     * @param httpServletRequest
     */
    private void setMulipartFilter(HttpServletRequest httpServletRequest) {
        
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        
        if(multipartResolver.isMultipart(httpServletRequest)){
            Map<String,String[]> hmParam = httpServletRequest.getParameterMap();  
            
            //fortify 수정 :: hmParam이Null확인
            if(!(hmParam == null || hmParam.isEmpty())){
                for(String hh : hmParam.keySet()){
                    String[] values = hmParam.get(hh);
                    if(values != null){                        
                        for(int i = 0; i < values.length ; i++){
                            //if(log.isDebugEnabled()){
                                //log.info("PARAM :: "+ hh + " -> " + values[i]);
                            //}
                            values[i] = FilterUtil.encodeXSSMultiPart(values[i]);   
                            
                            if(hh.startsWith("info")){
                                values[i] = values[i].replace(",", "");
                                //log.debug("filterParameter :: [KEY] " + hh + " :: [VALUE("+ i +")] " + values[i]);
                            }
                        }
                    }
                    
                    hmParam.put(hh, values);
                }
            }
        }
    }
    
}
