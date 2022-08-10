package vc.virusclean.cmm.web;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vc.virusclean.cmm.service.AttachFileService;
import vc.virusclean.cmm.service.CodeService;
import vc.virusclean.cmm.vo.CodeVO;
import vc.virusclean.cmm.vo.EditorPhotoVO;
import jksoft.com.annotation.AuthCheck;
import jksoft.com.util.MultiUtil;
import jksoft.com.web.XController;
import jksoft.com.web.vo.AttachFileVO;

@Controller
@RequestMapping(value= {"/admin", ""})
public class CommonController extends XController {
		
	@Resource(name = "codeService")
	private CodeService codeService;
	
	/**
     * MultiUtil Class 선언 (MultiUtil Class Injection)
     */
    @Resource(name="multiUtil")
    private MultiUtil multiUtil;
	
	/**
     * AttachFileService class 선언 ( AttachFileService Class Injection)
     */
    @Resource(name="attachFileService")
    private AttachFileService attachFileService;
    
    /**
     * 이미지 경로 - 화면 표시경로
     */
    @Value(value="#{global['file.web.path.default']}")
    private String filePath;
    

	
	
	/**
     * 공통코드 목록 조회
     *
     * @param codeVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value="/cm/caList.vc")   
    public Map<String, Object> caList(@Valid @ModelAttribute CodeVO codeVO, BindingResult bindingResult) throws Exception{
        
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
            mResult.put("result", codeService.selectCodeList(codeVO));            
        }
        
        return mResult;
    }

    /**
     * 공통코드 조회
     *
     * @param codeVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value="/cm/caDet.vc")   
    public Map<String, Object> caDet(@Valid @ModelAttribute CodeVO codeVO, BindingResult bindingResult) throws Exception{
        
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
            mResult.put("result", codeService.selectCode(codeVO));            
        }
        
        return mResult;
    }
    
    
    /**
     * 파일 다운로드
     *
     * @param fileVO
     * @param model
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @AuthCheck(loginCheck=false)
    @RequestMapping(value="/cm/fileDownMan.vc")
    public String fileDownMan(@ModelAttribute AttachFileVO attachFileVO, ModelMap model) throws Exception{
        
        Map<String, Object> mResult = attachFileService.selectAttachFile(attachFileVO);
        
        //FILE NAME
        model.addAttribute("contentFileName",  mResult.get("contentFileName"));
        model.addAttribute("contentFile", mResult.get("contentFile"));
        
        return "fileDownloadView";
    }
    
    /**
     * 파일 삭제
     *
     * @param fileVO
     * @param model
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @AuthCheck(roleCheck=false)
    @ResponseBody
    @RequestMapping(value="/cm/fileDelMan.vc")
    public Map<String,Object> fileDelMan(@Valid @ModelAttribute AttachFileVO attachFileVO, 
            BindingResult bindingResult, HttpServletRequest httpServletRequest) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            //Valid Error Log
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("== " + f.getField() + " : " + f.getObjectName()); 
            }
            //Valid Error Fields
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
            
        }else{
            mResult.put("result", attachFileService.deleteAttachFile(attachFileVO));
        }
        
        return mResult;
    }
    
    
    /**
     * 에디터 파일 업로드 폼
     *
     * @return
     * @throws Exception
     */
    @AuthCheck(roleCheck=false)
    @RequestMapping(value="/cm/pop/ccMan.vc", method=RequestMethod.GET)
    public String ccMan() throws Exception{
        return "cm/editor/pop/ccMan";
    }
    
    
    /**
     * 에디터 파일 업로드
     *
     * @param editorPhotoVO
     * @param model
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @AuthCheck(roleCheck=false)
    @RequestMapping(value="/cm/pop/ccMan.vc", method=RequestMethod.POST)
    public String ccMan(@Valid @ModelAttribute EditorPhotoVO editorPhotoVO, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws Exception{
           
        try{
            if(bindingResult.hasErrors()){
                log.debug("bindingResult.getFieldError() : " +  bindingResult.getFieldErrors());
                editorPhotoVO.setErrorCode("exception.error");                
            }else{
                Map<String, Object> mResult = attachFileService.manageEditorUpload(editorPhotoVO, multiUtil.getSessionInfo(httpServletRequest));
                
                if((Boolean)mResult.get("status")){
                    editorPhotoVO.setStatus(true);
                    editorPhotoVO.setFilePath(filePath);
                    editorPhotoVO.setFileName(URLEncoder.encode((String)mResult.get("fileName"), "UTF-8"));                 
                }else{
                    editorPhotoVO.setErrorCode((String)mResult.get("messageCode"));        
                }
            }
        }catch(Exception exception){
            editorPhotoVO.setErrorCode("exception.error");        
            exception.printStackTrace();
        }
        
        return this.createEditorCallBackUrl(editorPhotoVO);
    }
    
    
    /**
     * 에디터 파일 업로드 Callback
     * Statements
     *
     * @return
     * @throws Exception
     */
    @AuthCheck(roleCheck=false)
    @RequestMapping(value="/cm/pop/ccCallBackMan.vc")
    public String ccCallBackMan() throws Exception{
        
        return "cm/editor/pop/ccCallBackMan";
    }
    
    /**
     * Editor CallBack Url 생성
     *
     * @param editorPhotoVO
     * @return
     */
    public String createEditorCallBackUrl(EditorPhotoVO editorPhotoVO){
        
        StringBuilder sbReturnUrl = new StringBuilder("redirect:");
        
        if(editorPhotoVO.isStatus()){
            sbReturnUrl.append(editorPhotoVO.getCallback())
                       .append("?callback_func=").append(editorPhotoVO.getCallback_func())
                       .append("&sFileName=").append(editorPhotoVO.getFileName())
                       .append("&sFileURL=").append(editorPhotoVO.getFilePath())
                       .append("&bNewLine=true");
        }else{
            sbReturnUrl.append(editorPhotoVO.getCallback())
                       .append("?callback_func=").append(editorPhotoVO.getCallback_func())
                       .append("&errstr=").append(editorPhotoVO.getErrorCode());
        }
        
        return sbReturnUrl.toString();
    }
}
