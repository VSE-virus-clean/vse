package vc.virusclean.admin.board.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vc.virusclean.cmm.service.BoardService;
import vc.virusclean.cmm.vo.BoardVO;
import jksoft.com.filter.FilterUtil;
import jksoft.com.web.XController;

/**
 * <pre>
 * 게시판 공통 CONTROLLER
 * </pre>
 * 
 * @ClassName   : BoardController.java
 * @Description : @Controller
 */

@Controller
@RequestMapping(value= {"/admin"})
public class BoardController extends XController {

    @Resource(name="boardService")   
    private BoardService boardService;
    

    public String checkMenuId(String id) throws Exception{
    	String returnCode = "";
    	
    	if("banner".equals(id)){
    		returnCode = "A1";
    	} else if("popup-web".equals(id)){
    		returnCode = "A2";
    	} else if("popup-app".equals(id)){
    		returnCode = "A3";
    	} else if("push".equals(id)){
    		returnCode = "A4";
    	} else if("notice".equals(id)){
    		returnCode = "F1";
    	} else if("event".equals(id)){
    		returnCode = "F2";
    	} else if("faq".equals(id)){
    		returnCode = "F3";
    	} else if("app".equals(id)){
    		returnCode = "I1";
    	} else if("vsenews".equals(id)){
    		returnCode = id;
    	} else if("vsefaq".equals(id)){
    		returnCode = "J2";
	    } else if("vseshop".equals(id)){
			returnCode = "J6";
	    } else if("vsebusiness".equals(id)){
			returnCode = "J7";
	    }
    	
    		
    	return returnCode;
    }
    
    
    /**
     * 목록
     */
    @RequestMapping(value= "/{menuName}/{lgrpCd}/{mgrpCd}/list.vc")
    public String boardList(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@PathVariable("mgrpCd") String MGRP_CD, @ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{
    	
    	// search value decoding setting
    	String searchKeyVal = FilterUtil.decodeHTML(boardVO.getSearchKey());
    	boardVO.setSearchKey(searchKeyVal);
    	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
    	boardVO.setMgrpCd(MGRP_CD.toUpperCase());
		
        model.addAttribute("result", boardService.selectBoardList(boardVO));
        model.addAttribute("requestUri", MENU_NAME + "/" + LGRP_CD + "/" + MGRP_CD );
        model.addAttribute("pageMenuId", this.checkMenuId(LGRP_CD + "-" + MGRP_CD));
        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "/"+ MGRP_CD + "List";
    }
    
    
    /**
     * 정렬순서변경 : Form
     * - 사용자에 노출되는 것만 가져와야 한다.
     */
    @RequestMapping(value= "/{menuName}/{lgrpCd}/{mgrpCd}/sort.vc", method=RequestMethod.GET)
    public String boardSort(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@PathVariable("mgrpCd") String MGRP_CD, @ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{
    	
    	boardVO.setIsApi(true);
    	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
    	boardVO.setMgrpCd(MGRP_CD.toUpperCase());
		
        model.addAttribute("result", boardService.selectBoardList(boardVO));
        model.addAttribute("requestUri", MENU_NAME + "/" + LGRP_CD + "/" + MGRP_CD );
        model.addAttribute("pageMenuId", this.checkMenuId(LGRP_CD + "-" + MGRP_CD));
        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "/"+ MGRP_CD + "Sort";
    }
    
    /**
     * 정렬순서변경 : 수정
     */
    @ResponseBody
    @RequestMapping(value= "/{menuName}/{lgrpCd}/{mgrpCd}/sort.vc", method=RequestMethod.POST)
    public Map<String, Object> boardSort(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@PathVariable("mgrpCd") String MGRP_CD, @Valid @ModelAttribute BoardVO boardVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
        	boardVO.setMgrpCd(MGRP_CD.toUpperCase());
            mResult.put("result", boardService.updateBoardBySort(boardVO));
        }
        
        return mResult;
    }
    
    /**
     * 상세 보기
     *
     * @param boardVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value= "/{menuName}/{lgrpCd}/{mgrpCd}/view.vc")
    public String boardDet(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@PathVariable("mgrpCd") String MGRP_CD, @ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{
        
    	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
    	boardVO.setMgrpCd(MGRP_CD.toUpperCase());
        
        model.addAttribute("result", boardService.selectBoard(boardVO));
        model.addAttribute("requestUri", MENU_NAME + "/" + LGRP_CD + "/" + MGRP_CD );
        model.addAttribute("pageMenuId", this.checkMenuId(LGRP_CD + "-" + MGRP_CD));
        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "/"+ MGRP_CD + "Det";
    }
    
    
    /**
     * 개시판 : 입력
     */
    @RequestMapping(value = "/{menuName}/{lgrpCd}/{mgrpCd}/register.vc", method=RequestMethod.GET)
    public String boardReg(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@PathVariable("mgrpCd") String MGRP_CD, @ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{
    	
    	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
        
        model.addAttribute("result", null );
        model.addAttribute("requestUri", MENU_NAME + "/" + LGRP_CD + "/" + MGRP_CD );
        model.addAttribute("pageMenuId", this.checkMenuId(LGRP_CD + "-" + MGRP_CD));
         model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "/"+ MGRP_CD + "Reg";
    }
    
    /**
     * 게시판 : 입력
     */
    @ResponseBody
    @RequestMapping(value= "/{menuName}/{lgrpCd}/{mgrpCd}/register.vc", method=RequestMethod.POST)
    public Map<String, Object> boardReg(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@PathVariable("mgrpCd") String MGRP_CD, @Valid @ModelAttribute BoardVO boardVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
        	boardVO.setMgrpCd(MGRP_CD.toUpperCase());
            mResult.put("result", boardService.insertBoard(boardVO));
        }
        
        return mResult;
    }
    
    /**
     * 게시판 : 수정
     */
    @RequestMapping(value= "/{menuName}/{lgrpCd}/{mgrpCd}/modify.vc", method=RequestMethod.GET)
    public String boardMod(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@PathVariable("mgrpCd") String MGRP_CD, @ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{        
        
    	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
    	boardVO.setMgrpCd(MGRP_CD.toUpperCase());
        
        model.addAttribute("result", boardService.selectBoardByMod(boardVO));
        model.addAttribute("requestUri", MENU_NAME + "/" + LGRP_CD + "/" + MGRP_CD );
        model.addAttribute("pageMenuId", this.checkMenuId(LGRP_CD + "-" + MGRP_CD));
        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "/"+ MGRP_CD + "Mod";
    }
    
    /**
     * 게시판 : 수정
     */
    @ResponseBody
    @RequestMapping(value= "/{menuName}/{lgrpCd}/{mgrpCd}/modify.vc", method=RequestMethod.POST)
    public Map<String, Object> boardMod(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@PathVariable("mgrpCd") String MGRP_CD, @Valid @ModelAttribute BoardVO boardVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
        	boardVO.setMgrpCd(MGRP_CD.toUpperCase());
            mResult.put("result", boardService.updateBoard(boardVO));
        }
        
        return mResult;
    }

    /**
     * 게시판 : 삭제
     */
    @ResponseBody
    @RequestMapping(value= "/{menuName}/{lgrpCd}/{mgrpCd}/delete.vc" ,method=RequestMethod.POST)
    public Map<String, Object> boardDel(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@PathVariable("mgrpCd") String MGRP_CD, @ModelAttribute BoardVO boardVO) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>(); 
        
        boardVO.setLgrpCd(LGRP_CD.toUpperCase());
    	boardVO.setMgrpCd(MGRP_CD.toUpperCase());
        
        mResult.put("result", boardService.deleteBoard(boardVO));
        
        return mResult;
    }
    
    
    
    /**
     * 목록
     */
    @RequestMapping(value= "/{menuName}/{lgrpCd}/list.vc")
    public String boardList(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{
    	
    	// search value decoding setting
    	String searchKeyVal = FilterUtil.decodeHTML(boardVO.getSearchKey());
    	boardVO.setSearchKey(searchKeyVal);
    	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
		
        model.addAttribute("result", boardService.selectBoardList(boardVO));
        model.addAttribute("requestUri", MENU_NAME + "/" + LGRP_CD);
        model.addAttribute("pageMenuId", this.checkMenuId(LGRP_CD));
        
        return MENU_NAME + "/" + LGRP_CD + "/"+ LGRP_CD + "List";
    }
    
    /**
     * 정렬순서변경 : Form
     * - 사용자에 노출되는 것만 가져와야 한다.
     */
    @RequestMapping(value= "/{menuName}/{lgrpCd}/sort.vc", method=RequestMethod.GET)
    public String boardSort(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{
    	
    	boardVO.setIsApi(true);
    	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
		
        model.addAttribute("result", boardService.selectBoardList(boardVO));
        model.addAttribute("requestUri", MENU_NAME + "/" + LGRP_CD);
        model.addAttribute("pageMenuId", this.checkMenuId(LGRP_CD));
        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "/"+ LGRP_CD + "Sort";
    }
    
    /**
     * 정렬순서변경 : 수정
     */
    @ResponseBody
    @RequestMapping(value= "/{menuName}/{lgrpCd}/sort.vc", method=RequestMethod.POST)
    public Map<String, Object> boardSort(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@Valid @ModelAttribute BoardVO boardVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
            mResult.put("result", boardService.updateBoardBySort(boardVO));
        }
        
        return mResult;
    }
    
    /**
     * 상세 보기
     *
     * @param boardVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value= "/{menuName}/{lgrpCd}/view.vc")
    public String boardDet(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{
        
    	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
        
        model.addAttribute("result", boardService.selectBoard(boardVO));
        model.addAttribute("requestUri", MENU_NAME + "/" + LGRP_CD);
        model.addAttribute("pageMenuId", this.checkMenuId(LGRP_CD));
        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "/"+ LGRP_CD + "Det";
    }
    
    
    /**
     * 개시판 : 입력
     */
    @RequestMapping(value = "/{menuName}/{lgrpCd}/register.vc", method=RequestMethod.GET)
    public String boardReg(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{
    	
    	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
        
        model.addAttribute("result", null );
        model.addAttribute("requestUri", MENU_NAME + "/" + LGRP_CD);
        model.addAttribute("pageMenuId", this.checkMenuId(LGRP_CD));
         model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "/"+ LGRP_CD + "Reg";
    }
    
    /**
     * 게시판 : 입력
     */
    @ResponseBody
    @RequestMapping(value= "/{menuName}/{lgrpCd}/register.vc", method=RequestMethod.POST)
    public Map<String, Object> boardReg(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@Valid @ModelAttribute BoardVO boardVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
            mResult.put("result", boardService.insertBoard(boardVO));
        }
        
        return mResult;
    }
    
    /**
     * 게시판 : 수정
     */
    @RequestMapping(value= "/{menuName}/{lgrpCd}/modify.vc", method=RequestMethod.GET)
    public String boardMod(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@Valid @ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{        
        
    	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
        
        model.addAttribute("result", boardService.selectBoardByMod(boardVO));
        model.addAttribute("requestUri", MENU_NAME + "/" + LGRP_CD );
        model.addAttribute("pageMenuId", this.checkMenuId(LGRP_CD));
         model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "/"+ LGRP_CD + "Mod";
    }
    
    /**
     * 게시판 : 수정
     */
    @ResponseBody
    @RequestMapping(value= "/{menuName}/{lgrpCd}/modify.vc", method=RequestMethod.POST)
    public Map<String, Object> boardMod(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@Valid @ModelAttribute BoardVO boardVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	boardVO.setLgrpCd(LGRP_CD.toUpperCase());
            mResult.put("result", boardService.updateBoard(boardVO));
        }
        
        return mResult;
    }

    /**
     * 게시판 : 삭제
     */
    @ResponseBody
    @RequestMapping(value= "/{menuName}/{lgrpCd}/delete.vc" ,method=RequestMethod.POST)
    public Map<String, Object> boardDel(@PathVariable("menuName") String MENU_NAME, @PathVariable("lgrpCd") String LGRP_CD, 
    		@ModelAttribute BoardVO boardVO) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>(); 
        
        boardVO.setLgrpCd(LGRP_CD.toUpperCase());
        
        mResult.put("result", boardService.deleteBoard(boardVO));
        
        return mResult;
    }
      
}