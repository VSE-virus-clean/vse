package vc.virusclean.admin.auth.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jksoft.com.annotation.AuthCheck;
import jksoft.com.util.MultiUtil;
import jksoft.com.web.XController;
import vc.virusclean.admin.auth.service.AuthService;
import vc.virusclean.admin.auth.vo.LoginVO;
import vc.virusclean.admin.shop.service.StatsService;
import vc.virusclean.cmm.service.AccessLogService;
import vc.virusclean.cmm.service.BoardService;
import vc.virusclean.cmm.service.UserBoardService;
import vc.virusclean.cmm.vo.BoardVO;
import vc.virusclean.cmm.vo.UserBoardVO;


/**
 * <pre>
 * 관리자 로그인 및 정보관리 CONTROLLER
 * </pre>
 */

@Controller
@RequestMapping(value= {"/admin", "/api/auth"})
public class AuthController extends XController {

    @Resource(name="multiUtil")
    private MultiUtil multiUtil;
    
    @Resource(name="authService")   
    private AuthService authService;
    
    @Resource(name = "accessLogService")
    private AccessLogService accessLogService;
    
    @Resource(name="boardService")   
    private BoardService boardService;

    @Resource(name="userBoardService")
	private UserBoardService userBoardService;
    
    @Resource(name="statsService")
   	private StatsService statsService;
    
    
    /**
     *  관리자 메인 페이지
     *
     * @param authVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mainMan.vc")
    public String mainMan(ModelMap model) throws Exception{
    	
    	//공지사항
    	BoardVO boardVO = new BoardVO();
    	boardVO.setLgrpCd("NOTICE");
    	boardVO.setRowLimit(5);
        model.addAttribute("notice", boardService.selectBoardList(boardVO));
        
        //문의
        UserBoardVO boardVO2 = new UserBoardVO();
    	boardVO2.setIsApi(true);
		boardVO2.setLgrpCd("QNA");
		boardVO2.setIndividual(true);
		boardVO2.setRowLimit(5);
		model.addAttribute("resultQna", userBoardService.selectBoardList(boardVO2));
		
		model.addAttribute("dashboard", statsService.selectDashboard());
        
        return "auth/mainMan";
    }
    
    /**
     * 로그인 폼
     *
     * @param model
     * @return
     * @throws Exception
     */
    @AuthCheck(loginCheck=false)
    @RequestMapping(value={"/loginMan.vc"}, method=RequestMethod.GET)
    public String loginMan() throws Exception {
        return "auth/loginMan";
    }
    
    /**
     * 로그인 처리
     *
     * @param searchVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value={"/loginMan.vc"}, method=RequestMethod.POST)
    public Map<String, Object> loginMan(@Valid @ModelAttribute LoginVO loginVO, BindingResult bindingResult) throws Exception {
       
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
                if(log.isDebugEnabled()){
                    log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure());
                }
            }            
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", authService.selectAuth(loginVO));            
        }
        
        return mResult;
    }
    
    /**
     * 
     * 로그아웃
     *
     * @param request
     * @param authVO
     * @param model
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @AuthCheck(roleCheck=false)
    @RequestMapping(value="/logoutMan.vc", method=RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest) throws Exception {
        
        accessLogService.insertAccessLog("LOGOUT", multiUtil.getSessionInfo());
        
        httpServletRequest.getSession(false).invalidate();      
        
        return "redirect:/admin/loginMan.vc";
    }    

    
    /**
     * 비밀번호 변경
     *
     * @param authVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value="/loginNewPwMan.vc", method=RequestMethod.POST)
    public Map<String, Object> loginNewPwMan(@Valid @ModelAttribute LoginVO loginVO, BindingResult bindingResult) throws Exception {
       
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
                if(log.isDebugEnabled()){
                    log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure());
                }
            }            
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", authService.updateNewPassword(loginVO));            
        }
        
        return mResult;
    }
    
    
    /**
     * 개인정보 수정 폼
     *
     * @param uaVO
     * @param model
     * @return
     * @throws Exception
     */
    @AuthCheck(roleCheck=false)
    @RequestMapping(value="/ua/uaMod.vc", method=RequestMethod.GET)
    public String uaMod() throws Exception{        
        return "auth/uaMod";
    }
    
    /**
     * 개인정보 수정
     *
     * @param authVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @AuthCheck(roleCheck=false)
    @ResponseBody
    @RequestMapping(value="/ua/uaMod.vc", method=RequestMethod.POST)
    public Map<String, Object> uaMod(@Valid @ModelAttribute LoginVO loginVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
                if(log.isDebugEnabled()){
                    log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure());
                } 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
            
        }else{
            mResult.put("result", authService.updateAuthInfo(loginVO));
        }
        
        return mResult;
    }

}