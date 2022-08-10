package jksoft.com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 인증/권한 확인을 위한 Annotation Class
 * </pre>
 *
 * @ClassName   : AuthCheck.java
 * @Description : 인증/권한 확인을 위한 Annotation Class
 * @author Jeong.hyoungjea
 * @since 2013. 8. 8.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2013. 8. 8.     Jeong.hyoungjea     최초 생성
 * </pre>
 */
@Target((ElementType.METHOD))
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {
    
    /**
     * roleCheck시 사용하는 속성
     * - 작성 권한 이상만 사용이 가능함.
     * A:일관회원
     * S:멤버쉽회원
     * 
     * @return
     */
    String roleCode() default "A";
    
    /**
     * 로그인 확인
     * - loginCheck을 false 명시 하지 않으면 기본적으로 로그인여부를 확인하다.
     * 
     * @return
     */
    boolean loginCheck() default true;

    /**
     * 권한 확인
     * - 각메뉴에 대한 접근권한이 있어야 한다.
     * 
     * @return
     */
    boolean roleCheck() default true;
    
    /**
     * 개인정보 페이지 접근시 비밀번호 확인해야함.
     * - privacy 세션을 생성
     * - 다른 페이지로 이동시 삭제.
     */
    boolean privacyCheck() default false;

}
