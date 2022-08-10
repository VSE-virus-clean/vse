package vc.virusclean.user.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.icert.comm.secu.IcertSecuManager;

import jksoft.com.annotation.AuthCheck;
import jksoft.com.util.HttpClientHelper;
import jksoft.com.web.XController;
import vc.virusclean.admin.member.vo.MemberVO;
import vc.virusclean.admin.shop.service.ShopService;
import vc.virusclean.user.service.UserMemberService;


/**
 * <pre>
 * 외부 API / 플러그인 연동 
 * </pre>
 *
 * @ClassName   : ExternalController.java
 */
@Controller
public class ExternalController extends XController {
	
	@Resource(name="httpClientHelper")
    private HttpClientHelper httpClientHelper;
	
	@Resource(name="userMemberService")
	private UserMemberService userMemberService;
    
	@Value(value="#{global['openweathermap.api.url.find']}")
	private String openweatherApiUrlFind;
	
	@Value(value="#{global['openweathermap.api.url.air']}")
	private String openweatherApiUrlAir;
	
	@Value(value="#{global['openweathermap.api.key']}")
	private String openweatherApiKey;
	
	@Resource(name="shopService")
	private ShopService shopService;
	
	
	/**
	 * APP용 회원가입 API
	 * @param memberVO
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value={"/api/registration/registerReg.vc"}, method=RequestMethod.POST)
    public Map<String, Object> register(@Valid @ModelAttribute MemberVO memberVO, BindingResult bindingResult) throws Exception{     
        
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
            mResult.put("result", userMemberService.insertAppMember(memberVO));            
        }
        
        return mResult;
    }
	
	
	/**
	 * 날씨/환경정보 API 호출 
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/weather.vc", "/api/weather.vc"} )
	@ResponseBody
    public JsonObject openweather(@RequestParam(value = "lat", required = true) String lat, @RequestParam(value = "lon", required = true) String lon, ModelMap model) throws Exception{     
		
		JsonObject resultObject = new JsonObject();;
		
        try{
        	//날씨
        	String apiUrlFind = openweatherApiUrlFind.replace("{LAT}", lat).replace("{LON}", lon).replace("{KEY}", openweatherApiKey);
        	JsonObject resultFind = httpClientHelper.get(apiUrlFind);
        	int iResultFind = resultFind.get("status").getAsInt();
        	
        	//대기정보
        	String apiUrlAir = openweatherApiUrlAir.replace("{LAT}", lat).replace("{LON}", lon).replace("{KEY}", openweatherApiKey);
        	JsonObject resultAir = httpClientHelper.get(apiUrlAir);
        	int iResultAir = resultAir.get("status").getAsInt();
        	
        	if(iResultFind == 200 && iResultAir == 200){
        		JsonObject findList = (JsonObject)resultFind.getAsJsonArray("list").get(0);
        		resultObject.addProperty("temp", findList.getAsJsonObject("main").get("temp").getAsString());	//온도
        		resultObject.addProperty("temp_min", findList.getAsJsonObject("main").get("temp_min").getAsString());	//온도
        		resultObject.addProperty("temp_max", findList.getAsJsonObject("main").get("temp_max").getAsString());	//온도
        		resultObject.addProperty("humidity", findList.getAsJsonObject("main").get("humidity").getAsString());	//습도
        		resultObject.add("weather", findList.getAsJsonArray("weather").get(0));	//날씨
        		
        		JsonObject airList = (JsonObject)resultAir.getAsJsonArray("list").get(0);
        		resultObject.addProperty("co", airList.getAsJsonObject("components").get("co").getAsString());	//CO 농도 ( 일산화탄소 ), μg / m 3
        		resultObject.addProperty("o3", airList.getAsJsonObject("components").get("o3").getAsString());	//O3 농도 ( 오존 ), μg / m 3
        		resultObject.addProperty("pm2_5", airList.getAsJsonObject("components").get("pm2_5").getAsString());	//PM 2.5 농도 ( 미립자 물질 ), μg / m 3
        		resultObject.addProperty("pm10", airList.getAsJsonObject("components").get("pm10").getAsString());	//PM 10 농도 ( 거친 입자상 물질 ), μg / m 3
        		
        		resultObject.addProperty("code", "OK");
        	}else{
        		resultObject.addProperty("code", "NG");
        	}
			
        }catch(Exception exception){
            log.error("== firebaseResponse :: Failed \n", exception);
        }
		
        return resultObject;
    }
	
    /**
     * APP 디바이스 토큰 등록/변경
     * 네이티브 APP에서는  세션공유가 되지 않기 때문에 mbrSn이 필수로 와야 한다.
     * 
     * @return
     * @throws Exception
     */
    @AuthCheck(loginCheck=false)
    @ResponseBody
    @RequestMapping(value={"/api/changeDeviceInfo.vc"} ,method=RequestMethod.POST)
    public Map<String, Object> changeDeviceToken(@Valid @ModelAttribute MemberVO memberVO, BindingResult bindingResult) throws Exception{     
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
            mResult.put("result", userMemberService.updateMemberDeviceInfo(memberVO));            
        }
        
        return mResult;
    }
    
	/**
	 * 네이버 간편가입 결과
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/api/member/callback/naver.vc"} )
    public String naver(ModelMap model) throws Exception{     
        return "external/naverMan";
    }
	
	/**
	 * 카카오 간편가입 결과 : 사용하지 않음.
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/api/member/callback/kakao.vc"} )
    public String kakao(ModelMap model) throws Exception{     
        return "mainMan";
    }
	
	/**
	 * google 간편가입 결과 : 사용하지 않음.
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/api/member/callback/google.vc"} )
    public String google(ModelMap model) throws Exception{     
        return "external/googleMan";
    }
	
	/**
	 * apple 간편가입 결과
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/api/member/callback/apple.vc"} )
    public String apple(ModelMap model) throws Exception{     
        return "external/appleMan";
    }
	
	
	/**
	 * 본인확인 초기화
	 */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/api/kmcis/init.vc"} )
    public String kmcisInit(ModelMap model) throws Exception{     
        return "external/kmcisInit";
    }
	
	/**
	 * 본인확인 결과
	 */
	@SuppressWarnings("unused")
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/api/kmcis/result.vc"} )
    public String kmcisResult(HttpServletRequest httpServletRequest, ModelMap model) throws Exception{  
		
		boolean bRtn = true;
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		String rec_cert = "";  // 결과값(암호화)
		String k_certNum = "";			    // 파라미터로 수신한 요청번호
		
	    rec_cert = httpServletRequest.getParameter("rec_cert").trim();
		k_certNum  = httpServletRequest.getParameter("certNum").trim(); 
		
//		rec_cert =  "2619AA5C2AD7CC5CF83787E1DCF4F94495A87694A9368DD99A7AA93256A1AF4FF16A3D37FD22ABDB08DB9BB6C02758812692260C2F7144E36F6DB3A7F25BA67D1AF6E2A00A6FF703EEE2270D5F340EFD2C8B16C3DFCFB3DDA8D33C759D1C1FB8473DB70DBEFE016ADFEBBCC9B364A5F2AF94631B448AF3562DBA9FAE165E4D261ED57D897A03D9A7FD340A320218A85DFF27363A94DBB52E2262EAAD2DE47E83F3E078CEA3A6CC854A0124F3743E5EAFDD41C2CF47577B82249BD35B7EEA2797490C74D254B1F8EBC3670B061A65532FFFA4D8389A40A00D19292122DDFC1622B161BF6854EA3B5176539A3E2D6DEBF1072E6CE5330775DCD9FAE4D239D2D346A1CF6B3ADE2A7B08253241D55024F65C8ED0E4818C0467F6FB27D5DD9081CED7A2C40528D1E8C762C9E05686D2073F8D63E3D1A6640E55030CA1CFE4FFE7DD5C89B9F68B6DC926476BEE0664DD516F7D7B8088F5E8590B5FCB14AF1A934B4D8BCB51DF5B83BF7B8EAA574802CD4DB3F0EE4D5AA515B1810E683A7CF76A4657B7C68CB4CB926FD66E9FE88A1152B15091DB149495F5876BD6E5483C8706E58C6CAFA580380F1995CF98742A2E6FE87339E57FB6E86DD43B781C0612D1463DA8DF5268EB404457FA270287A01AAC29ECE113AA6E7CF927C542385636FDE27BC3A851B97AF48CBDFA452422F229C4FD51FC6184B9B955A6736B28161353BE2DB905CC1C6FF73B522BFD901FAE94C15D6DC27B641BD86312F5FC66BB7411C84271610929BD3CE82C9D0A1A30D059FB3F8549354AD38AC2EF9D6EB23C5DF2C05AF75631E3CE00BF364F3EAE5CDE5FE33EA1457DB36DE22022E5B2B34534ED6DB0F4EF69CED6DCB929EFA7A97E9F3F716F367D5F6F22F19FED55EDE355475DDBC052B7608F0B40F30A45B5A4658970A50CD5E0F36DBC5023AD137614788448097A5B8AE7DE1AD13AA4D529F08D541794DCF8B2A4B09DC0C7081DF08E9C7D1E0786609A92522C198D376F223CB764E342116A476545AFB8409E33B5DC83E7002C6F6C6168B306235675136505801EE0A8725D6B182CDEDF433E3B2EA5DE4EABF30F7601404DBB2009F914E518388E78C545B31544465A2FBE5E9392AD386BBA70734E2A50B94315DEE07A3E91E53C6770A12BD5689A15A983EF19E67E5C55C6DC2EA1DFA945BFFA7AD17D0B93AFC06D2C2D3932B8F97AF31C0B250EA88E1A9E54E44B7953F9D77B4010DC17AD604FCD7B3F1F13E4C0D458A4FF08E713B8A266231A78944342DA8DFA4CE608EF2126033717842A5F6B26D7063B9B43B9464E82792372E112A7BCBF9378C995FD0CD2ACD76DF6C182278910840E612B17BE913C200D9D2A5AA56713B44002D046E4D8022B9AE428EEE174D2C7B733FDAC466819D0E53962";
//		k_certNum  = "20210223142915705960";
		
		// 파라미터 유효성 검증
		if( rec_cert.length() == 0 || k_certNum.length() == 0 ){
			bRtn = false;
			log.debug("결과값 비정상");
			model.addAttribute("errorMsg", "결과값 비정상");
		}else{
			try{
				// 변수선언 --------------------------------------------------------------------------------------------------------
				
				String certNum = "";  // certNum
			    String date			= "";			// 요청일시
				String CI	    	= "";			// 연계정보(CI)
				String DI	    	= "";			// 중복가입확인정보(DI)
			    String phoneNo		= "";			// 휴대폰번호
				String phoneCorp	= "";			// 이동통신사
				String birthDay		= "";			// 생년월일
				String gender		= "";			// 성별
				String nation		= "";			// 내국인
				String name			= "";			// 성명
				String M_name		= "";			// 미성년자 성명
				String M_birthDay	= "";			// 미성년자 생년월일
				String M_Gender		= "";			// 미성년자 성별
				String M_nation		= "";			// 미성년자 내외국인
			    String result		= "";			// 결과값
		
			    String certMet		= "";			// 인증방법
			    String ip			= "";			// ip주소
				String plusInfo		= "";
		
				String encPara		= "";
				String encMsg1		= ""; 
				String encMsg2		= "";
				String msgChk       = "";
				
				//01. 암호화 모듈 (jar) Loading
				IcertSecuManager seed = new IcertSecuManager();
				
				
				//02. 1차 복호화
		        //수신된 certNum를 이용하여 복호화
		        rec_cert  = seed.getDec(rec_cert, k_certNum);
		
		        //03. 1차 파싱
		        int inf1 = rec_cert.indexOf("/",0);
		        int inf2 = rec_cert.indexOf("/",inf1+1);
		
				encPara  = rec_cert.substring(0,inf1);         //암호화된 통합 파라미터
		        encMsg1  = rec_cert.substring(inf1+1,inf2);    //암호화된 통합 파라미터의 Hash값
		
				//04. 위변조 검증
				encMsg2  = seed.getMsg(encPara);
		
		        if(encMsg2.equals(encMsg1)){
		            msgChk="Y";
		        }
		        
		        if(msgChk.equals("N")){
		        	bRtn = false;
		        	log.debug("비정상적인 접근입니다");
		        	model.addAttribute("errorMsg", "비정상적인 접근입니다");
		        }else{
		        	//05. 2차 복호화
		    		rec_cert  = seed.getDec(encPara, k_certNum);
	
		            //06. 2차 파싱
		            int info1  = rec_cert.indexOf("/",0);
		            int info2  = rec_cert.indexOf("/",info1+1);
		            int info3  = rec_cert.indexOf("/",info2+1);
		            int info4  = rec_cert.indexOf("/",info3+1);
		            int info5  = rec_cert.indexOf("/",info4+1);
		            int info6  = rec_cert.indexOf("/",info5+1);
		            int info7  = rec_cert.indexOf("/",info6+1);
		            int info8  = rec_cert.indexOf("/",info7+1);
		    		int info9  = rec_cert.indexOf("/",info8+1);
		    		int info10 = rec_cert.indexOf("/",info9+1);
		    		int info11 = rec_cert.indexOf("/",info10+1);
		    		int info12 = rec_cert.indexOf("/",info11+1);
		    		int info13 = rec_cert.indexOf("/",info12+1);
		    		int info14 = rec_cert.indexOf("/",info13+1);
		    		int info15 = rec_cert.indexOf("/",info14+1);
		    		int info16 = rec_cert.indexOf("/",info15+1);
		    		int info17 = rec_cert.indexOf("/",info16+1);
		    		int info18 = rec_cert.indexOf("/",info17+1);
	
		            certNum		= rec_cert.substring(0,info1);
		            date		= rec_cert.substring(info1+1,info2);
		            CI			= rec_cert.substring(info2+1,info3);
		            phoneNo		= rec_cert.substring(info3+1,info4);
		            phoneCorp	= rec_cert.substring(info4+1,info5);
		            birthDay	= rec_cert.substring(info5+1,info6);
		            gender		= rec_cert.substring(info6+1,info7);
		            nation		= rec_cert.substring(info7+1,info8);
		    		name		= rec_cert.substring(info8+1,info9);
		    		result		= rec_cert.substring(info9+1,info10);
		    		certMet		= rec_cert.substring(info10+1,info11);
		    		ip			= rec_cert.substring(info11+1,info12);
		    		M_name		= rec_cert.substring(info12+1,info13);
		    		M_birthDay	= rec_cert.substring(info13+1,info14);
		    		M_Gender	= rec_cert.substring(info14+1,info15);
		    		M_nation	= rec_cert.substring(info15+1,info16);
		    		plusInfo	= rec_cert.substring(info16+1,info17);
		    		DI      	= rec_cert.substring(info17+1,info18);
	
		            //07. CI, DI 복호화
		            CI  = seed.getDec(CI, k_certNum);
		            DI  = seed.getDec(DI, k_certNum);
		            
		    		// Start - 수신내역 유효성 검증(사설망의 사설 IP로 인해 미사용, 공용망의 경우 확인 후 사용) *********************/
		    		// 1. date 값 검증
		    		SimpleDateFormat formatter	= new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREAN); // 현재 서버 시각 구하기
		    		String strCurrentTime	= formatter.format(new Date());
		    		
		    		Date toDate = formatter.parse(strCurrentTime);
		    		Date fromDate = formatter.parse(date);
		    		long timediff = toDate.getTime()-fromDate.getTime();
		    		
		    		if ( timediff < -30*60*1000 || 30*60*100 < timediff  ){
		    			bRtn = false;
		    			log.debug("비정상적인 접근입니다. (요청시간경과)");
		    			model.addAttribute("errorMsg", "비정상적인 접근입니다. (요청시간경과)");
		    		}else{
			    		// 2. ip 값 검증
//			    		String client_ip = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR"); // 사용자IP 구하기
//			    		if ( client_ip != null ){
//			    			if( client_ip.indexOf(",") != -1 )
//			    				client_ip = client_ip.substring(0,client_ip.indexOf(","));
//			    		}
//			    		if ( client_ip==null || client_ip.length()==0 ){
//			    			client_ip = httpServletRequest.getRemoteAddr();
//			    		}
//			    		
//			    		if( !client_ip.equals(ip) ){
//			    			bRtn = false;
//			    			log.debug("비정상적인 접근입니다. (IP불일치)");
//			    			model.addAttribute("errorMsg", "비정상적인 접근입니다. (IP불일치)");
//			    		}else{
				    		mResult.put("encMsg1", encMsg1);
				    		mResult.put("encMsg2", encMsg2);
				    		mResult.put("certNum", certNum);
				    		mResult.put("date", date);
				    		mResult.put("CI", CI);
				    		mResult.put("DI", DI);
				    		mResult.put("phoneNo", phoneNo);
				    		mResult.put("phoneCorp", phoneCorp);
				    		mResult.put("birthDay", birthDay);
				    		mResult.put("gender", "0".equals(gender) ? "M" : "F"); //
				    		mResult.put("name", name);
				    		mResult.put("result", result);	//Y:성공, N:실패, F:오류
				    		mResult.put("ip", ip);
				    		
				    		log.debug("encMsg1");
				    		log.debug("encMsg2");
				    		log.debug("certNum");
				    		log.debug("CI");
				    		log.debug("DI");
				    		log.debug("phoneNo");
				    		log.debug("phoneCorp");
				    		log.debug("birthDay");
				    		log.debug("name");
				    		log.debug("result");
//			    		}
		    		}
		        }
			}catch(Exception ex){
		          log.debug("[KMCIS] Receive Error -"+ex.getMessage());
		    }
		}
		
		model.addAttribute("result", bRtn);
		
		if(bRtn){
			model.addAttribute("info", mResult);
		}
		
        return "external/kmcisResult";
    }
	
	
	/**
     * 결제완료 처리 : PC
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/api/notify/payment/pc.vc", "/shop/paymentProcess.vc"} ,method=RequestMethod.POST)
    public String paymentNotify(HttpServletRequest httpServletReques ,ModelMap model) throws Exception{
		
		Map<String, Object> mResult = shopService.paymentProcess(httpServletReques);
		
		if((boolean)mResult.get("status")){
			return "redirect:/shop/order/complete.vc";
		}else{
			//TODO : 결제 실패시 어떻게 할것인지 확인 필요함.
			return "redirect:/shop/order/complete.vc";
		}
    }
	
	/**
     * 결제완료 처리 : 모바일
     */
	@AuthCheck(loginCheck=false)
    @RequestMapping(value={"/api/notify/payment/mobile.vc", "/shop/mobile/paymentProcess.vc"} ,method=RequestMethod.POST)
    public String paymentNotifyMobile(HttpServletRequest httpServletReques ,ModelMap model) throws Exception{
		
		Map<String, Object> mResult = shopService.paymentMobleProcess(httpServletReques);
		
		if((boolean)mResult.get("status")){
			return "redirect:/shop/order/complete.vc";
		}else{
			//TODO : 결제 실패시 어떻게 할것인지 확인 필요함.
			return "redirect:/shop/order/register.vc";
		}
    }
	
	/**
	 * 가상계좌 입금통보 : PC
	 * - OK 또는 FAIL 만 출력해야함
	 */
	@AuthCheck(loginCheck=false)
	@ResponseBody
    @RequestMapping(value={"/api/notify/payment/vbank/pc.vc"} ,method=RequestMethod.POST)
    public String paymentNotifyVbank(HttpServletRequest httpServletReques ,ModelMap model) throws Exception{
		
		return shopService.paymentNotifyVbank(httpServletReques);
    }
	
	/**
	 * 가상계좌 입금통보 : PC
	 * - OK 또는 FAIL 만 출력해야함
	 */
	@AuthCheck(loginCheck=false)
	@ResponseBody
    @RequestMapping(value={"/api/notify/payment/vbank/mobile.vc"} ,method=RequestMethod.POST)
    public String paymentNotifyMobileVbank(HttpServletRequest httpServletReques ,ModelMap model) throws Exception{
		
		return shopService.paymentNotifyMobileVbank(httpServletReques);
    }
}
