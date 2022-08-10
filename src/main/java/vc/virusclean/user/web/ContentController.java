package vc.virusclean.user.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jksoft.com.annotation.AuthCheck;
import jksoft.com.util.MultiUtil;
import jksoft.com.web.XController;
import vc.virusclean.cmm.service.BoardService;
import vc.virusclean.cmm.vo.BoardVO;


/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : ContentController.java
 * @Description : 클래스 설명을 기술합니다.
 */
@Controller
public class ContentController extends XController {
	
	@Resource(name="multiUtil")
    protected MultiUtil multiUtil;
	
	@Resource(name="boardService")
	private BoardService boardService;
	

	/**
     * 메인페이지
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/", "/index.vc"} )
    public String index(ModelMap model) throws Exception{     
		
		//배너
		BoardVO boardVO = new BoardVO();
		boardVO.setIsApi(true);
		boardVO.setLgrpCd("BANNER"); 
		boardVO.setRowLimit(1);
		model.addAttribute("banner", boardService.selectBoardList(boardVO));
		
		//팝업
		BoardVO boardVO2 = new BoardVO();
		boardVO2.setIsApi(true);
		boardVO2.setLgrpCd("POPUP"); 
		boardVO2.setMgrpCd("WEB");
		boardVO.setRowLimit(1);
		model.addAttribute("popup", boardService.selectBoardList(boardVO2));
		
		model.addAttribute("deviceCode", multiUtil.checkDevice());
		
		model.addAttribute("pageMenuId", "KMA");
		
        return "mainMan";
    }
	
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/brand.vc"} )
    public String brand(ModelMap model) throws Exception{     
		
		model.addAttribute("pageMenuId", "KSC");
		
        return "brandMan";
    }
	
	
	/** ***********************************************************************************
	 * 이벤트
	 ** ***********************************************************************************/
	/**
     * 이벤트 > 목록
     */
	@AuthCheck(loginCheck=false)
	@RequestMapping(value={"/event.vc", "/event/list.vc"})
	public String eventList(@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{     

		boardVO.setIsApi(true);
		boardVO.setLgrpCd("EVENT");    //게시판 아이디
		boardVO.setRowLimit(9);
		model.addAttribute("result", boardService.selectBoardList(boardVO));
		model.addAttribute("requestUri", "event");
		model.addAttribute("pageMenuId", "KSDA");
		
        return "event/eventList";
    }
	
	@AuthCheck(loginCheck=false)
	@RequestMapping(value={"/event/ing/list.vc"})
	public String eventIngList(@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{     

		boardVO.setIsApi(true);
		boardVO.setLgrpCd("EVENT");    //게시판 아이디
		boardVO.setSecretYn("ING");
		boardVO.setRowLimit(9);
		model.addAttribute("result", boardService.selectBoardList(boardVO));
		model.addAttribute("requestUri", "event/ing");
		model.addAttribute("pageMenuId", "KSDB");
		
        return "event/eventList";
    }
	
	@AuthCheck(loginCheck=false)
	@RequestMapping(value={"/event/end/list.vc"})
	public String eventEndList(@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{     

		boardVO.setIsApi(true);
		boardVO.setLgrpCd("EVENT");    //게시판 아이디
		boardVO.setSecretYn("END");
		boardVO.setRowLimit(9);
		model.addAttribute("result", boardService.selectBoardList(boardVO));
		model.addAttribute("requestUri", "event/end");
		model.addAttribute("pageMenuId", "KSDC");
		
        return "event/eventList";
    }
	
	/**
     * 이벤트 > 상세
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping("/event/view.vc")
    public String eventDet(@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{
        
		boardVO.setIsApi(true);
		boardVO.setLgrpCd("EVENT"); 
        model.addAttribute("result", boardService.selectBoard(boardVO));
        model.addAttribute("requestUri", "event");
		model.addAttribute("pageMenuId", "KSDA");
        
		return "event/eventDet";
    }
	
	/**
     * 이벤트 > 상세
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping("/event/ing/view.vc")
    public String eventIngDet(@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{
        
		boardVO.setIsApi(true);
		boardVO.setLgrpCd("EVENT"); 
		boardVO.setSecretYn("ING");
        model.addAttribute("result", boardService.selectBoard(boardVO));
        model.addAttribute("requestUri", "event/ing");
		model.addAttribute("pageMenuId", "KSDB");
        
		return "event/eventDet";
    }
	
	/**
     * 이벤트 > 상세
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping("/event/end/view.vc")
    public String eventEndDet(@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{
        
		boardVO.setIsApi(true);
		boardVO.setLgrpCd("EVENT"); 
		boardVO.setSecretYn("END");
        model.addAttribute("result", boardService.selectBoard(boardVO));
        model.addAttribute("requestUri", "event/end");
		model.addAttribute("pageMenuId", "KSDC");
        
		return "event/eventDet";
    }
	
	/** ***********************************************************************************
	 * 서비스
	 ** ***********************************************************************************/
	/**
     * 서비스 > FAQ
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/service/faq/list.vc", "/service/faqList.vc", "/service/faq.vc"})
    public String faq(@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{   
		boardVO.setIsApi(true);
		boardVO.setLgrpCd("FAQ");    //게시판 아이디
		model.addAttribute("result", boardService.selectBoardList(boardVO));
		model.addAttribute("requestUri", "service/faq");
		model.addAttribute("pageMenuId", "KSED");
        
		return "service/faqList";
    }

	/**
     * 서비스 > 공지사항 > 목록
     */
	@AuthCheck(loginCheck=false)
	@RequestMapping(value={"/service/notice/list.vc", "/service/noticeList.vc"})
	public String noticeList(@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{     

		boardVO.setIsApi(true);
		boardVO.setLgrpCd("NOTICE");    //게시판 아이디
		model.addAttribute("result", boardService.selectBoardList(boardVO));
		model.addAttribute("requestUri", "service/notice");
		model.addAttribute("pageMenuId", "KSEA");
		
        return "service/noticeList";
    }
	
	/**
     * 서비스 > 공지사항 > 상세
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping("/service/notice/view")
    public String noticeDet(@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{
        
		boardVO.setIsApi(true);
		boardVO.setLgrpCd("NOTICE"); 
        model.addAttribute("result", boardService.selectBoard(boardVO));
        model.addAttribute("requestUri", "service/notice");
		model.addAttribute("pageMenuId", "KSEA");
        
		return "service/noticeDet";
    }
	
	
	/**
     * 서비스 > 고객센터
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping("/service/customer-support.vc" )
    public String about(ModelMap model) throws Exception{     
		model.addAttribute("pageMenuId", "KSEC");
        return "service/customerSupportMan";
    }
	
	/**
     * 서비스 > 고객센터
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping("/service/app-guide.vc")
    public String appGuide(ModelMap model) throws Exception{     
		model.addAttribute("pageMenuId", "KSEB");
        return "service/appGuideMan";
    }
	
	
	/** ***********************************************************************************
	 * ROLE
	 ** ***********************************************************************************/
	/**
     * 이용약관
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/role/service-rules.vc"} )
    public String serviceRules(ModelMap model) throws Exception{     
		model.addAttribute("pageMenuId", "KUA");
        return "role/serviceMan";
    }
	
	
	/**
     * 개인정보취급방침
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/role/privacy-protection-policy.vc"} )
    public String privacyPolicy(ModelMap model) throws Exception{     
		model.addAttribute("pageMenuId", "KUB");
        return "role/privacyMan";
    }
	
	/**
     * 이메일무단수집거부
     * @return
     * @throws Exception
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/role/denial-of-unauthorized-collecting-of-email-address.vc"} )
    public String emailDeny(ModelMap model) throws Exception{     
		model.addAttribute("pageMenuId", "KUC");
        return "role/emailMan";
    }
	
	
	//이용약관
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/role/registration/agree1.vc"} )
    public String registrationRole1(ModelMap model) throws Exception{     
        return "registration/inc_service1";
    }
	
	//개인정보처리방침
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/role/registration/agree2.vc"} )
    public String registrationRole2(ModelMap model) throws Exception{     
        return "registration/inc_service2";
    }
	
	//표준약관
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/role/registration/agree3.vc"} )
    public String registrationRole3(ModelMap model) throws Exception{     
        return "registration/inc_service3";
    }
	
	//이메일무단수집거부
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/role/registration/agree4.vc"} )
    public String registrationRole4(ModelMap model) throws Exception{     
        return "registration/inc_service4";
    }
	
	
	/** ***********************************************************************************
	 * 회원 게시판
	 ** ***********************************************************************************/
	//member/chat/dearjihwan
	//member/chat/staff
	//member/chat/pen
	@AuthCheck(roleCode="S")
	@RequestMapping(value= "/member/chat/list" )
	public String chatList(@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{     
		
		boardVO.setIsApi(true);
		boardVO.setLgrpCd("CHAT");    //게시판 아이디
		boardVO.setRowLimit(9);   
		model.addAttribute("result", boardService.selectBoardList(boardVO));
		model.addAttribute("requestUri", "member/chat");
		model.addAttribute("pageMenuId", "F");
		
        return "board/chatList";
    }
	
	/**
     * 상세 보기
     *
     * @param boardVO
     * @param model
     * @return
     * @throws Exception
     */
	@AuthCheck(roleCode="S")
    @RequestMapping(value= "/member/chat/view")
    public String chatDet(@ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{
    	
		boardVO.setIsApi(true);
		boardVO.setLgrpCd("CHAT"); 
        model.addAttribute("result", boardService.selectBoard(boardVO));
        model.addAttribute("requestUri", "member/chat");
		model.addAttribute("pageMenuId", "F");
        
		return "board/chatDet";
    }
	
	@AuthCheck(roleCode="S")
	@RequestMapping(value= "/member/chat/{mgrpCd}/list" )
	public String chatList(@PathVariable("mgrpCd") String MGRP_CD, @ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{     

		boardVO.setIsApi(true);
		boardVO.setLgrpCd("CHAT");    //게시판 아이디
		boardVO.setMgrpCd(MGRP_CD.toUpperCase());    //게시판 아이디
		boardVO.setRowLimit(9);   
		model.addAttribute("result", boardService.selectBoardList(boardVO));
		model.addAttribute("requestUri", "member/chat/" + MGRP_CD);
		model.addAttribute("pageMenuId", "F");
		
        return "board/chatList";
    }
	
	@AuthCheck(roleCode="S")
    @RequestMapping(value= "/member/chat/{mgrpCd}/view")
    public String chatDet(@PathVariable("mgrpCd") String MGRP_CD, @ModelAttribute BoardVO boardVO, ModelMap model) throws Exception{
        
		boardVO.setIsApi(true);
		boardVO.setLgrpCd("CHAT"); 
		boardVO.setMgrpCd(MGRP_CD.toUpperCase());    //게시판 아이디
        model.addAttribute("result", boardService.selectBoard(boardVO));
        model.addAttribute("requestUri", "member/chat/" + MGRP_CD);
		model.addAttribute("pageMenuId", "F");
        
		return "board/chatDet";
    }

}
