package vc.virusclean.admin.auth.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vc.virusclean.admin.auth.service.ManagerService;
import vc.virusclean.admin.auth.vo.AuthVO;
import vc.virusclean.cmm.vo.BoardVO;
import jksoft.com.annotation.AuthCheck;
import jksoft.com.filter.FilterUtil;
import jksoft.com.web.XController;

/**
 * <pre>
 * 운영자 CONTROLLER
 * </pre>
 * 
 * @ClassName   : ManagerController.java
 * @Description : @Controller
 * @author Jeong.Hyoung.Jae 
 * @since 2017.12.25
 * @version 1.0
 * @see vc.virusclean.admin.auth.web
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017.12.25      Jeong.Hyoung.Jae        최초생성
 * </pre>
 */

@Controller
@RequestMapping(value= {"/admin/manager/info"})
public class ManagerController extends XController {
	
    @Resource(name="managerService")   
    private ManagerService managerService;
    
    public final String PAGE_PATH = "manager/info";
    
    public final String PAGE_MENUID = "manager";

    /**
     * 운영자 목록
     */
    @AuthCheck(roleCode="S")
    @RequestMapping(value= "/list.vc")
    public String infoList(@ModelAttribute AuthVO authVO, ModelMap model) throws Exception{
        
    	authVO.setSearchKey(FilterUtil.decodeHTML(authVO.getSearchKey()));
    	
    	model.addAttribute("result", managerService.selectInfoList(authVO));
    	
    	model.addAttribute("pageMenuId", PAGE_MENUID);
    	
    	model.addAttribute("requestUri", PAGE_PATH);
    	
        return PAGE_PATH + "/infoList";
    }
    
    /**
     * 운영자 상세보기
     */
    @AuthCheck(roleCode="S")
    @RequestMapping(value= "/view.vc")
    public String infoDet(@ModelAttribute AuthVO authVO, ModelMap model) throws Exception{
    	
    	model.addAttribute("result", managerService.selectInfo(authVO));
    	
    	model.addAttribute("pageMenuId", PAGE_MENUID);
    	
    	model.addAttribute("requestUri", PAGE_PATH);
    	
        return PAGE_PATH + "/infoDet";
    }
    
    /**
     * 입력폼
     */
    @AuthCheck(roleCode="S")
    @RequestMapping(value= "/register.vc", method=RequestMethod.GET)
    public String infoReg(@ModelAttribute AuthVO authVO, ModelMap model) throws Exception{
        
    	Map<String, Object> mResult = new HashMap<String, Object>();
    	mResult.put("searchInfo", authVO);
    	
    	model.addAttribute("result", mResult);
    	model.addAttribute("pageMenuId", PAGE_MENUID);
        model.addAttribute("requestUri", PAGE_PATH );
        
        return PAGE_PATH + "/infoReg";
    }
    
    /**
     * 입력
     */
    @AuthCheck(roleCode="S")
    @ResponseBody
    @RequestMapping(value= "/register.vc", method=RequestMethod.POST)
    public Map<String, Object> infoReg(@Valid @ModelAttribute AuthVO authVO, BindingResult bindingResult) throws Exception{
        
    	Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", managerService.insertInfo(authVO));
        }
        
        return mResult;
    }
    
    /**
     * 수정 폼
     * @param authVO
     * @param model
     * @return
     * @throws Exception
     */
    @AuthCheck(roleCode="S")
    @RequestMapping(value= "/modify.vc", method=RequestMethod.GET)
    public String infoMod(@ModelAttribute AuthVO authVO, ModelMap model) throws Exception{
    	
    	model.addAttribute("result", managerService.selectInfo(authVO));
    	
    	model.addAttribute("pageMenuId", PAGE_MENUID);
    	
    	model.addAttribute("requestUri", PAGE_PATH);
    	
        return PAGE_PATH + "/infoMod";
    }
    
    /**
     * 수정
     * @param authVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @AuthCheck(roleCode="S")
    @ResponseBody
    @RequestMapping(value= "/modify.vc", method=RequestMethod.POST)
    public Map<String, Object> infoMod(@Valid @ModelAttribute AuthVO authVO, BindingResult bindingResult) throws Exception{
        
    	Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", managerService.updateInfo(authVO));
        }
        
        return mResult;
    }
    
    /**
     * 삭제
     * @param serviceVO
     * @return
     * @throws Exception
     */
    @AuthCheck(roleCode="S")
    @ResponseBody
    @RequestMapping(value= "/delete.vc", method=RequestMethod.POST)
    public Map<String, Object> reservationDel(@ModelAttribute AuthVO authVO) throws Exception{
        
    	Map<String, Object> mResult = new HashMap<String, Object>();
        
    	authVO.setStCd("D");
        mResult.put("result", managerService.updateByStCd(authVO));
        
        return mResult;
    }
    
    /**
     * 로그인아이디 중복확인
     * @return
     * @throws Exception
     */
    @AuthCheck(roleCode="S")
    @ResponseBody
    @RequestMapping(value="/checkIDDuplicate.vc", method=RequestMethod.POST)
    public Map<String, Object> checkIDDuplicate(@Valid @ModelAttribute AuthVO authVO, BindingResult bindingResult) throws Exception{     
    	
    	Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
            
        }else{
            mResult.put("result", managerService.selectInfoCheckDuplicate(authVO));
        }
        
        return mResult;	
    }
    
    /**
     * 비밀번호 초기화
     * @return
     * @throws Exception
     */
    @AuthCheck(roleCode="S")
    @ResponseBody
    @RequestMapping(value="/resetPassword.vc", method=RequestMethod.POST)
    public Map<String, Object> resetPassword(@Valid @ModelAttribute AuthVO authVO, BindingResult bindingResult) throws Exception{     
    	
    	Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
            
        }else{
            mResult.put("result", managerService.updateByPassword(authVO));
        }
        
        return mResult;	
    }
    
    /**
     * 계정 상태값 변경
     *
     * @param authVO2
     * @param mSession
     * @throws Exception 
     */
    @AuthCheck(roleCode="S")
    @ResponseBody
    @RequestMapping(value="/stcdMod.vc", method=RequestMethod.POST)
	private Map<String, Object> updateByStCd(@Valid @ModelAttribute AuthVO authVO, BindingResult bindingResult) throws Exception {
    	Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
            
        }else{
            mResult.put("result", managerService.updateByStCd(authVO));
        }
        
        return mResult;	
    }
    
    /**
     * 화면 노출 세팅
     */
    @AuthCheck(roleCode="S")
    @RequestMapping(value= "/setting.vc", method=RequestMethod.GET)
    public String infoSet(ModelMap model) throws Exception{

    	BoardVO boardVO = new BoardVO();
    	model.addAttribute("result", managerService.selectInfoSettingList(boardVO));
    	model.addAttribute("pageMenuId", PAGE_MENUID);
        model.addAttribute("requestUri", PAGE_PATH );
        
        return PAGE_PATH + "/infoSetting";
    }
    
    /**
     * 화면 노출 상태값 변경
     *
     * @param authVO2
     * @param mSession
     * @throws Exception 
     */
    @AuthCheck(roleCode="S")
    @ResponseBody
    @RequestMapping(value="/useYnMod.vc", method=RequestMethod.POST)
	private Map<String, Object> updateByuseYn(@Valid @ModelAttribute BoardVO boardVO, BindingResult bindingResult) throws Exception {
    	Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
            
        }else{
            mResult.put("result", managerService.updateByuseYn(boardVO));
        }
        
        return mResult;	
    }
    
}