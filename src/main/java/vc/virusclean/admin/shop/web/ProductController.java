package vc.virusclean.admin.shop.web;

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

import jksoft.com.filter.FilterUtil;
import jksoft.com.web.XController;
import vc.virusclean.admin.shop.service.ProductService;
import vc.virusclean.admin.shop.vo.ProductVO;
import vc.virusclean.cmm.service.CodeService;
import vc.virusclean.cmm.vo.CodeEnvVO;
import vc.virusclean.cmm.vo.CodeVO;

/**
 * <pre>
 * 상품정보 관리 CONTROLLER
 * </pre>
 * 
 * @ClassName   : ProductController.java
 * @Description : @Controller
 */

@Controller
@RequestMapping(value= {"/admin"})
public class ProductController extends XController {

	@Resource(name="productService")
    private ProductService productService;
    
	@Resource(name="codeService")   
    private CodeService codeService;

    public String checkMenuId(String id) throws Exception{
    	String returnCode = "B1";
    	
    	if("product/info".equals(id)){
    		returnCode = "B1";
    	} else if("product/qna".equals(id)){
    		returnCode = "B2";
    	} else if("product/review".equals(id)){
    		returnCode = "B3";
    	} else if("product/info/category".equals(id)){
    		returnCode = "B4";
    	}
    	
    	return returnCode;
    }
    
    /**
     * 상품 목록
     */
    @RequestMapping(value= "/product/info/list.vc")
    public String itemList(@ModelAttribute ProductVO vo, ModelMap model) throws Exception{
    	
    	String MENU_NAME = "product/info";
    	String LGRP_CD = "product";
    	
    	// search value decoding setting
    	String searchKeyVal = FilterUtil.decodeHTML(vo.getSearchKey());
    	vo.setSearchKey(searchKeyVal);
		
        model.addAttribute("result", productService.selectProductList(vo));
        model.addAttribute("requestUri", MENU_NAME);
        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));
        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "List";
    }
    
    /**
     * 상세 보기
     *
     * @param vo
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value= "/product/info/view.vc")
    public String itemDet(@ModelAttribute ProductVO vo, ModelMap model) throws Exception{
        
    	String MENU_NAME = "product/info";
    	String LGRP_CD = "product";
    	
//    	vo.setLgrpCd(LGRP_CD.toUpperCase());
        
        model.addAttribute("result", productService.selectProduct(vo));
        model.addAttribute("requestUri", MENU_NAME);
        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));
        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "Det";
    }
    
    
    /**
     * 상품 입력
     */
    @RequestMapping(value = "/product/info/register.vc", method=RequestMethod.GET)
    public String itemReg(@ModelAttribute ProductVO vo, ModelMap model) throws Exception{
    	
    	String MENU_NAME = "product/info";
    	String LGRP_CD = "product";
    	
//    	vo.setLgrpCd(LGRP_CD.toUpperCase());
        
        model.addAttribute("result", null );
        model.addAttribute("requestUri", MENU_NAME);
        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));
        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "Reg";
    }
    
    /**
     * 상품 입력
     */
    @ResponseBody
    @RequestMapping(value= "/product/info/register.vc", method=RequestMethod.POST)
    public Map<String, Object> itemReg(@Valid @ModelAttribute ProductVO vo, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
    	
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", productService.insertProduct(vo));
        }
        
        return mResult;
    }
    
    /**
     * 상품 수정
     */
    @RequestMapping(value= "/product/info/modify.vc", method=RequestMethod.GET)
    public String itemMod(@Valid @ModelAttribute ProductVO vo, ModelMap model) throws Exception{        
        
    	String MENU_NAME = "product/info";
    	String LGRP_CD = "product";
    	
        model.addAttribute("result", productService.selectProductByMod(vo));
        model.addAttribute("requestUri", MENU_NAME );
        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));
        model.addAttribute("boardLgrpId", LGRP_CD.toUpperCase());
        
        return MENU_NAME + "/" + LGRP_CD + "Mod";
    }
    
    /**
     * 상품 수정
     */
    @ResponseBody
    @RequestMapping(value= "/product/info/modify.vc", method=RequestMethod.POST)
    public Map<String, Object> itemMod(@Valid @ModelAttribute ProductVO vo, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", productService.updateProduct(vo));
        }
        
        return mResult;
    }

    /**
     * 상품 삭제
     */
    @ResponseBody
    @RequestMapping(value= "/product/info/delete.vc" ,method=RequestMethod.POST)
    public Map<String, Object> itemDel(@ModelAttribute ProductVO vo) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>(); 
        
        mResult.put("result", productService.deleteProduct(vo));
        
        return mResult;
    }
    
    /**
     * 상품 품절처리
     */
    @ResponseBody
    @RequestMapping(value= "/product/info/updateSellYn.vc" ,method=RequestMethod.POST)
    public Map<String, Object> updateSellYn(@ModelAttribute ProductVO vo) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>(); 
        
        mResult.put("result", productService.updateProductBySellYn(vo));
        
        return mResult;
    }
    
    
    
    /**
     * 상품 카테고리 관리
     */
    @RequestMapping(value= "/product/category/list.vc")
    public String categoryList(@ModelAttribute CodeVO codeVO, ModelMap model) throws Exception{
    	
    	String MENU_NAME = "product/info/category";
    	
        model.addAttribute("result", codeService.selectCodeCateList(codeVO));
        model.addAttribute("requestUri", MENU_NAME);
        model.addAttribute("pageMenuId", this.checkMenuId(MENU_NAME));
        
        return MENU_NAME + "List";
    }
    
    /**
     * 카테고리 1depth 등록
     * @param codeVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value= "/product/category/register.vc", method=RequestMethod.POST)
    public Map<String, Object> categoryAdd(@Valid @ModelAttribute CodeVO codeVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", codeService.insertCode(codeVO));
        }
        
        return mResult;
    }
    
    /**
     * 카테고리 1depth 수정
     * @param codeVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value= "/product/category/modify.vc", method=RequestMethod.POST)
    public Map<String, Object> categoryMod(@Valid @ModelAttribute CodeVO codeVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", codeService.updateCode(codeVO));
        }
        
        return mResult;
    }
    
    /**
     * 카테고리 1depth 삭제
     * @param codeVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value= "/product/category/delete.vc", method=RequestMethod.POST)
    public Map<String, Object> categoryDel(@Valid @ModelAttribute CodeVO codeVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", codeService.deleteCode(codeVO));
        }
        
        return mResult;
    }
    
    /**
     * 카테고리 1depth 정렬순서 변경
     * @param codeVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value= "/product/category/sort.vc", method=RequestMethod.POST)
    public Map<String, Object> categorySort(@Valid @ModelAttribute CodeVO codeVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
            mResult.put("result", codeService.updateCodeBySort(codeVO));
        }
        
        return mResult;
    }
}