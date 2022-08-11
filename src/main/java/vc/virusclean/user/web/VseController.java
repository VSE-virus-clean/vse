package vc.virusclean.user.web;

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

import jksoft.com.annotation.AuthCheck;
import jksoft.com.util.MultiUtil;
import jksoft.com.web.XController;
import vc.virusclean.cmm.service.BoardService;
import vc.virusclean.cmm.service.UserBoardService;
import vc.virusclean.cmm.vo.BoardVO;
import vc.virusclean.cmm.vo.UserBoardVO;


/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : VseController.java
 * @Description : VSE관련 페이지 
 */
@Controller
public class VseController extends XController {
	
	@Resource(name="multiUtil")
    protected MultiUtil multiUtil;
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	@Resource(name="userBoardService")
	private UserBoardService userBoardService;
	

	/**
     * 메인페이지
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/index.vse"} )
    public String index(ModelMap model) throws Exception{     
		
		BoardVO boardVO = new BoardVO();
		boardVO.setIsApi(true);
		boardVO.setLgrpCd("VSEBUSINESS");
		model.addAttribute("result", boardService.selectBoardList(boardVO));
		
		model.addAttribute("deviceCode", multiUtil.checkDevice());
		model.addAttribute("pageMenuId", "VSEMA");
		
        return "vse/main/index";
    }
	
	/**
	 * 제품설명
	 * vse/product/product.jsp
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/product.vse"} )
    public String product(ModelMap model) throws Exception{     
		model.addAttribute("pageMenuId", "VSE-PRODUCT");
        return "vse/product/product";
    }
	
	/**
	 * 인테리어 > 사진
	 * vse/interior/interior_photo
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/interior.vse"} )
    public String interior_photo(ModelMap model) throws Exception{     
		model.addAttribute("pageMenuId", "VSE-INTERIOR-PHOTO");
        return "vse/interior/interior_photo";
    }
	
	/**
	 * 창업 > 창업시스템
	 * vse/interior/interior_photo
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/startups.vse"} )
    public String startups_system(ModelMap model) throws Exception{     
		model.addAttribute("pageMenuId", "VSE-STARTUPS-SYSTEM");
        return "vse/startups/startups_system";
    }
	
	/**
	 * 사업설명회 > 첨가첩수 > 창업상담
	 * business/business_register01.html
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/business/consult.vse"} )
    public String business_register01(ModelMap model) throws Exception{     
		model.addAttribute("pageMenuId", "VSE-BUSINESS-REGISTER01");
        return "vse/business/business_register01";
    }
	
	
	/**
	 * 사업설명회 > 첨가첩수 > 사업설명회
	 * business/business_register02.html
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/business/presentation.vse"} )
    public String business_register02(ModelMap model) throws Exception{    
		
		BoardVO boardVO = new BoardVO();
		boardVO.setIsApi(true);
		boardVO.setLgrpCd("VSEBUSINESS");
		model.addAttribute("result", boardService.selectBoardList(boardVO));
		
		model.addAttribute("pageMenuId", "VSE-BUSINESS-REGISTER02");
        return "vse/business/business_register02";
    }
	
	/**
	 * 고객지원 > FAQ
	 * customer/customer_qna.html
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/customer/faq.vse"} )
    public String customer_qna(@ModelAttribute BoardVO boardVO,ModelMap model) throws Exception{     
		
		boardVO.setIsApi(true);
		boardVO.setLgrpCd("VSEFAQ");    //게시판 아이디
		model.addAttribute("result", boardService.selectBoardList(boardVO));
		
		model.addAttribute("requestUri", "customer/faq");
		model.addAttribute("pageMenuId", "VSE-CUSTOMER-FAQ");
        return "vse/customer/customer_qna";
    }
	
	/**
	 * 고객지원 > 매창찾기
	 * customer/customer_shop
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/customer/shop-location/list.vse"} )
    public String customer_shop(@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{
		
		boardVO.setIsApi(true);
		boardVO.setLgrpCd("VSESHOP");    //게시판 아이디
		boardVO.setRowLimit(50);
		
		model.addAttribute("result", boardService.selectBoardList(boardVO));
		model.addAttribute("requestUri", "customer/shop-location");
		model.addAttribute("pageMenuId", "VSE-CUSTOMER-SHOP");
		
        return "vse/customer/customer_shop";
    }
	
	/**
	 * 고객지원 > 이벤트뉴스 > 목록
	 * customer/customer_event.html
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/customer/event/list.vse"} )
    public String customer_event_list(@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{     
		
		boardVO.setIsApi(true);
		boardVO.setLgrpCd("VSENEWS");    //게시판 아이디
		boardVO.setRowLimit(3);
		
		model.addAttribute("result", boardService.selectBoardList(boardVO));
		model.addAttribute("requestUri", "customer/event");
		model.addAttribute("pageMenuId", "VSE-CUSTOMER-EVENT");
        return "vse/customer/customer_event";
    }
	
	/**
	 * 고객지원 > 이벤트뉴스 > 상세
	 * customer/customer_board_view.html
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/customer/event/view.vse"} )
    public String customer_event_view(@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{     
		
		boardVO.setIsApi(true);
		boardVO.setLgrpCd("VSENEWS");    //게시판 아이디
		
		model.addAttribute("result", boardService.selectBoard(boardVO));
        model.addAttribute("requestUri", "customer/event");
		model.addAttribute("pageMenuId", "VSE-CUSTOMER-EVENT");
		
        return "vse/customer/customer_board_view";
    }
	
	/**
	 * VSE레슨프로 : APPLICATION
	 * teaching/teachingpro.html
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/teaching.vse"} )
    public String teachingpro(ModelMap model) throws Exception{     
		model.addAttribute("pageMenuId", "VSE-TEACHING-TEACHINGPRO");
        return "vse/teaching/teachingpro";
    }
	
	/**
	 * 회사소개
	 * vse/product/company_intro.jsp
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/company/intro.vse"} )
    public String company_intro(ModelMap model) throws Exception{     
		
		model.addAttribute("pageMenuId", "VSE-COMPANY-INTRO");
		
        return "vse/product/company_intro";
    }
	
	/**
	 * 이용약관
	 * vse/product/company_conditions.jsp
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/company/conditions.vse"} )
    public String company_conditions(ModelMap model) throws Exception{     
		model.addAttribute("pageMenuId", "VSE-COMPANY-CONDITIONS");
        return "vse/product/company_conditions";
    }
	
	/*
	 * 개인정보취급방침
	 * vse/product/company_personal.jsp
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/company/personal.vse"} )
    public String company_personal(ModelMap model) throws Exception{     
		model.addAttribute("pageMenuId", "VSE-COMPANY-PERSONAL");
        return "vse/product/company_personal";
    }
	
	
	
	/**
     * 커뮤니티 입력
     */
	@AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value= "/apply/register.vse", method=RequestMethod.POST)
    public Map<String, Object> communityReg(@Valid @ModelAttribute UserBoardVO boardVO, BindingResult bindingResult) throws Exception{
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()){
            for(FieldError f : bindingResult.getFieldErrors()){
               log.debug("[bindingResult Error] " + f.getField() + " : " + f.getObjectName() + " : " + f.getDefaultMessage() + " : " + f.isBindingFailure()); 
            }
            mResult.put("bindingFields", bindingResult.getFieldErrors());
            mResult.put("bindingStatus", false);
            mResult.put("message", xMessageSource.getMessage("exception.binding"));
        }else{
        	boardVO.setIsApi(true);
            mResult.put("result", userBoardService.insertBoardVse(boardVO));
        }
        
        return mResult;
    }

}