package vc.virusclean.cmm.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jksoft.com.annotation.AuthCheck;
import jksoft.com.util.MultiUtil;
import jksoft.com.web.XController;

/**
 * <pre>
 * Exception 처리 페이지
 * </pre>
 *
 * @ClassName   : ExceptionController.java
 * @Description : 서비스 Exception발생시 각 Exception별 사용자 안내 페이지를 연결한다.
 * @author Jeong.Hyoung.Jae
 * @since 2017.12.17.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017.12.17.     Jeong.Hyoung.Jae     최초 생성
 * </pre>
 */
@RequestMapping(value={"/admin/error", "/error"})
@Controller
public class ExceptionController extends XController {

	/**
     * MultiUtil Class 선언 (QbaService Class Injection)
     */
    @Resource(name="multiUtil")
    protected MultiUtil multiUtil;
    
    
    /**
     * 일반적이 에러 발생시 사용자에게 적정한 메세지를 보여준다.
     *
     * @return
     * @throws Exception
     */
    @AuthCheck(loginCheck=false)
    @RequestMapping(value={"/error.vc"})
    public String errorMan() throws Exception{   
        return "error/errorMan";
    }
    
    /**
     * 로그인 확인 에러
     *
     * @return
     * @throws Exception
     */
    @AuthCheck(loginCheck=false)
    @RequestMapping("/authFailure.vc")
    public String authFailure() throws Exception{       
        return "error/authFailure";
    }
    
    /**
     * 권한 확인 에러
     *
     * @return
     * @throws Exception
     */
    @AuthCheck(loginCheck=false)
    @RequestMapping("/roleFailure.vc")
    public String roleFailure() throws Exception{       
        return "error/roleFailure";
    }

}
