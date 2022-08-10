package vc.virusclean.admin.shop.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.inicis.std.util.HttpUtil;
import com.inicis.std.util.ParseUtil;
import com.inicis.std.util.SignatureUtil;

import jksoft.com.exception.NoResourceException;
import jksoft.com.service.XAbstractService;
import jksoft.com.util.CryptoUtil;
import jksoft.com.util.DateUtil;
import jksoft.com.util.HttpClientHelper;
import jksoft.com.util.MailSenderUtil;
import jksoft.com.util.SmsSenderUtil;
import jksoft.com.util.StringUtil;
import jksoft.com.util.XMap;
import jksoft.com.web.vo.AttachFileVO;
import vc.virusclean.admin.shop.service.ShopService;
import vc.virusclean.admin.shop.service.dao.ScrapDAO;
import vc.virusclean.admin.shop.service.dao.ShopDAO;
import vc.virusclean.admin.shop.vo.BuyProductVO;
import vc.virusclean.admin.shop.vo.OrderHistoryVO;
import vc.virusclean.admin.shop.vo.OrderProductVO;
import vc.virusclean.admin.shop.vo.OrderVO;
import vc.virusclean.admin.shop.vo.PaymentVO;
import vc.virusclean.admin.shop.vo.ScrapVO;
import vc.virusclean.admin.shop.vo.ShopVO;
import vc.virusclean.cmm.service.AttachFileService;
import vc.virusclean.cmm.service.dao.BoardDAO;
import vc.virusclean.cmm.vo.BoardVO;

/**
 * <pre>
 * 주문정보서비스 구현
 * </pre>
 * 
 * @ClassName   : ShopServiceImpl.java
 * @Description : ShopService 를 구현
 */
@Service("shopService")
public class ShopServiceImpl extends XAbstractService implements ShopService {
	
	@Resource(name="mailSenderUtil")
	private MailSenderUtil mailSenderUtil;
	
	@Resource(name="smsSenderUtil")
    private SmsSenderUtil smsSenderUtil;
	
	@Resource(name="attachFileService")
    private AttachFileService attachFileService;
	
	@Resource(name="httpClientHelper")
    private HttpClientHelper httpClientHelper;

	@Resource(name="shopDAO")   
    private ShopDAO shopDAO;
	
	@Resource(name="boardDAO")   
    private BoardDAO boardDAO;
	
	@Resource(name="scrapDAO")   
    private ScrapDAO scrapDAO;
	
	
	/**
     * 이니시스 MID
     */
    @Value(value="#{global['inicis.mid']}")
    protected String INICIS_MID;
    
    /**
     * 이니시스 KEY
     */
    @Value(value="#{global['inicis.signKey']}")
    protected String INICIS_SIGNKEY;
    
    /**
     * 이니시스 API KEY
     */
    @Value(value="#{global['inicis.apiKey']}")
    protected String INICIS_APIKEY;
    
    /**
     * 이니시스 API IV
     */
    @Value(value="#{global['inicis.apiIV']}")
    protected String INICIS_APIIV;
	
    /*
     * 파일 저장 경로
     */
    @Value(value="#{global['file.system.path.default']}")
    private File fileSavePath;

	/**
	 * 목록을 조회한다.
	 */
	@Override
	public Map<String, Object> selectShopList(ShopVO shopVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		Map<String,Object> mSession = multiUtil.getSessionInfo();
		
		if(shopVO.getIsApi()){
			if((boolean)mSession.get("isLogin")){
        		shopVO.setMbrSn((int)mSession.get("userSn"));
        	}
		}
		
		shopVO.setTotalRow(shopDAO.selectShopCount(shopVO));
        
        if(shopVO.getTotalRow() > 0){
            mResult.put("list", shopDAO.selectShopList(shopVO));
        }
        
        mResult.put("searchInfo", shopVO);
        
		return mResult;
	}
	
	/**
	 * 목록을 조회한다.
	 */
	@Override
	public Map<String, Object> selectOrderList(OrderVO orderVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		Map<String,Object> mSession = multiUtil.getSessionInfo();
		
		if(orderVO.getIsApi()){
			if((boolean)mSession.get("isLogin")){
				orderVO.setMbrSn((int)mSession.get("userSn"));
        	}
		}
		
		/**
		 * 구분값이 여러개면
		 */
		String[] searchGubunType = orderVO.getSearchGubunType().split("\\|");
		if(searchGubunType.length > 1) {
			orderVO.setDelSeq(Arrays.asList(searchGubunType));
		}
		
		orderVO.setTotalRow(shopDAO.selectOrderCount(orderVO));
        
        if(orderVO.getTotalRow() > 0){
            mResult.put("list", shopDAO.selectOrderList(orderVO));
        }
        
        mResult.put("searchInfo", orderVO);
        
		return mResult;
	}
	
	
	/**
	 * 목록을 조회한다.
	 */
	@Override
	public List<XMap> selectOrderExcelList(OrderVO orderVO) throws Exception {
		
		/**
		 * 구분값이 여러개면
		 */
		String[] searchGubunType = orderVO.getSearchGubunType().split("\\|");
		if(searchGubunType.length > 1) {
			orderVO.setDelSeq(Arrays.asList(searchGubunType));
		}
		
		return shopDAO.selectOrderExcelList(orderVO);
	}
	

	/**
	 * 정보를 조회한다
	 */
	@Override
	public Map<String, Object> selectShop(ShopVO shopVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		Map<String,Object> mSession = multiUtil.getSessionInfo();
        
		if(shopVO.getIsApi()){
			if((boolean)mSession.get("isLogin")){
        		shopVO.setMbrSn((int)mSession.get("userSn"));
        	}
		}
		
		ShopVO shopVO2 = shopDAO.selectShop(shopVO);

        mResult.put("searchInfo", shopVO);
        
        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(shopVO2 == null){
            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
        }else{  
            mResult.put("info", shopVO2);
        }
        
        
		return mResult;
	}
	
	/**
	 * 정보를 조회한다
	 */
	@Override
	public Map<String, Object> selectOrder(OrderVO orderVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		Map<String,Object> mSession = multiUtil.getSessionInfo();
        
		if(orderVO.getIsApi()){
			if((boolean)mSession.get("isLogin")){
				orderVO.setMbrSn((int)mSession.get("userSn"));
        	}else{
        		throw new IllegalArgumentException();
        	}
		}
		
		OrderVO orderVO2 = shopDAO.selectOrdeInfo(orderVO);

        if(orderVO2 != null){
            mResult.put("info", orderVO2);
            mResult.put("history", shopDAO.selectOrderHistoryList(orderVO2));
        }
        
        mResult.put("searchInfo", orderVO);
        
		return mResult;
	}
	
	/**
	 * 정보를 조회한다
	 */
	@Override
	public Map<String, Object> selectInfoOrderNo(OrderVO orderVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		Map<String,Object> mSession = multiUtil.getSessionInfo();
        
		if(orderVO.getIsApi()){
			if((boolean)mSession.get("isLogin")){
				orderVO.setMbrSn((int)mSession.get("userSn"));
        	}
		}
		
		OrderVO orderVO2 = shopDAO.selectOrdeInfo(orderVO);

        mResult.put("searchInfo", orderVO);
        
        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(orderVO2 == null){
            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
        }else{  
            mResult.put("info", orderVO2);
        }
        
        
		return mResult;
	}
	

	/**
	 * 사용자 주문 접수
	 */
	@Override
	public Map<String, Object> insertOrder(OrderVO orderVO, BuyProductVO buyProductVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
            
            if((boolean)mSession.get("isLogin")){
            	orderVO.setMbrSn((int)mSession.get("userSn"));
            }
            
            
            //주문번호 생성
            Map<String,Object> paymentInfo = this.paymentInit(orderVO.getOrderPrice());
            
            
            HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
            HttpSession httpSession = httpServletRequest.getSession(false);
            httpSession.setAttribute("payment_order_no", (String)paymentInfo.get("orderNo"));
            
            String goodName = buyProductVO.getPrdTitl().get(0);
			if(buyProductVO.getPrdSn().size() > 1){
				goodName += "외 " + (buyProductVO.getPrdSn().size() - 1) + "개 상품";
			}
			
			//주문상품명
			orderVO.setGoodNm(goodName);
			//주문번호
            orderVO.setOrderNo((String)paymentInfo.get("orderNo"));
            orderVO.setOrderCd("결제요청");
            
            
            Object resultObject = shopDAO.insertOrder(orderVO) ;
    		
    		if(resultObject.getClass() == Integer.class){
    			
    			orderVO.setOrderSn((Integer)resultObject);
    			
    			for(int i = 0; i < buyProductVO.getPrdSn().size(); i++){
    				OrderProductVO orderProductVO = new OrderProductVO();
    				
    				orderProductVO.setOrderSn(orderVO.getOrderSn());
    				orderProductVO.setPrdSn(Integer.valueOf(buyProductVO.getPrdSn().get(i)));
    				orderProductVO.setPrdTitl(buyProductVO.getPrdTitl().get(i));
    				orderProductVO.setOrderCost(Integer.valueOf(buyProductVO.getPrdPrice().get(i)));	//상품단가
    				orderProductVO.setOrderCnt(Integer.valueOf(buyProductVO.getPrdCnt().get(i)));		//상품갯수
    				orderProductVO.setProductPrice(Integer.valueOf(buyProductVO.getPrdTotalPrice().get(i)));	//상품단가합
    				orderProductVO.setDiscountPrice(Integer.valueOf(buyProductVO.getPrdDiscountPrice().get(i)));	//할인금액
    				orderProductVO.setOrderPrice(Integer.valueOf(buyProductVO.getPrdTotalPrice().get(i)) - Integer.valueOf(buyProductVO.getPrdDiscountPrice().get(i)));		//결제금액
    				
    				//상품정보 등록
        			shopDAO.insertOrderProductInfo(orderProductVO);
        		}
    			
                
                //구입상품명 : 여러개면 -> 첫번째 상품명 + 외 몇개
                paymentInfo.put("goodName", goodName);
                
                bStatus = true;
                
                mResult.put("paymentInfo", paymentInfo);
                
                //주문이역
                OrderHistoryVO historyVO = new OrderHistoryVO();
                historyVO.setNowOrderCd(orderVO.getOrderCd());
                historyVO.setOrderNo(orderVO.getOrderNo());
                historyVO.setPayAmt(orderVO.getOrderPrice());
                historyVO.setExcSbc2("결제모듈실행");
                shopDAO.insertOrderHistory(historyVO);
                
//                mResult.put("returnUrl", "/shop/order/complete?orderId=" + shopVO.getOrderId());
    			
    		}else{
    			throw new IllegalArgumentException();
    		}
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        
        mResult.put("status", bStatus);
		
		return mResult;
	}

	/**
	 * 구매자 / 배송자 /결제방법 정보만 수정
	 */
	@Override
	public Map<String, Object> updateShop(ShopVO shopVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
            
            shopVO.setRgstAdmSn((int)mSession.get("userSn"));
            shopVO.setRgstAdmId((String)mSession.get("userId"));
            
            if(shopDAO.updateShop(shopVO) == 1){
                bStatus = true;
            }else{
                throw new IllegalArgumentException();
            }
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
		
		return mResult;
	}

	/**
	 * 결제완료 - 관리자 처리
	 */
	@Override
	public Map<String, Object> updateShopPayComp(ShopVO shopVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
            
            shopVO.setRgstAdmSn((int)mSession.get("userSn"));
            shopVO.setRgstAdmId((String)mSession.get("userId"));
            
            if(shopDAO.updateShopPayComp(shopVO) == 1){
                bStatus = true;
                
                ShopVO shopVO2 = shopDAO.selectShop(shopVO);
                
                //TODO :: 결제 확인처리 메일
                BoardVO boardVO = new BoardVO();
    			boardVO.setLgrpCd("SHOP"); 
    			boardVO.setBlcSn(shopVO2.getCotnSn());
    			BoardVO boardVO2 = boardDAO.selectBoard(boardVO);
    			
    			//결제 완료 안내 메일
        		String strMailTitle = "KANG JIHWAN JAPAN OFFICIAL FANCLUB 商品 決済完了";
        		String strMailFileName = "shopPayComplete.txt";
        		
        		Map<String, String> mailInfo = new HashMap<String, String>();
        		mailInfo.put("$$ITEM_NAME$$", boardVO2.getBlcTitl());
        		mailInfo.put("$$ITEM_BUY_DATE$$", DateUtil.getToday());
        		mailInfo.put("$$ITEM_ORDER_NO$$", shopVO2.getOrderId());
        		mailInfo.put("$$ITEM_QUANTITY$$", Integer.toString(shopVO2.getOrderQuantity()));
        		mailInfo.put("$$ITEM_AMOUNT$$", StringUtil.commaNumber(shopVO2.getOrderAmount()));
        		
        		//메일 발송
                mailSenderUtil.mailSendToUser(shopVO2.getBuyerEml(), strMailTitle, strMailFileName, mailInfo);
            }else{
                throw new IllegalArgumentException();
            }
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
		
		return mResult;
	}

	/**
	 * 배송 처리
	 */
	@Override
	public Map<String, Object> updateShopDeliveryComp(ShopVO shopVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
            
            shopVO.setRgstAdmSn((int)mSession.get("userSn"));
            shopVO.setRgstAdmId((String)mSession.get("userId"));
            
            
            if(shopDAO.updateShopDeliveryComp(shopVO) == 1){
                bStatus = true;
                
                //TODO :: 배송 메일
                
            }else{
                throw new IllegalArgumentException();
            }
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
		
		return mResult;
	}

	/**
	 * 결제 취소
	 * - 배송이 안된것만.
	 * - 결제 완료된 경우는 결제 취소해야한다.
	 */
	@Override
	public Map<String, Object> deleteShop(ShopVO shopVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
            
            if(!shopVO.getIsApi()){
	            //관리자
	            shopVO.setRgstAdmSn((int)mSession.get("userSn"));
	            shopVO.setRgstAdmId((String)mSession.get("userId"));
	     
	            if(shopDAO.deleteShop(shopVO) == 1){
	                bStatus = true;
	            }else{
	                throw new IllegalArgumentException();
	            }
            }else{
            	//FC여부 확인
            	if((boolean)mSession.get("isLogin")){
            		shopVO.setMbrSn((int)mSession.get("userSn"));
            	}
            	
            	if(shopDAO.deleteShopUser(shopVO) == 1){
	                bStatus = true;
	            }else{
	                throw new IllegalArgumentException();
	            }
            	
            }
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
		
		return mResult;
	}
	
	
	/**
	 * 결제 필요한 값 초기화
     * - 주문번호등.
     * - 주문번호 양식은 {YYYYMMDD}{밀리초} 로
     * @param price 상품가격(특수기호 제외, 가맹점에서 직접 설정)
	 */
	public Map<String, Object> paymentInit(int price) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		String timestamp = SignatureUtil.getTimestamp();
	
		//가맹점 주문번호(가맹점에서 직접 설정)
//		String orderNo = RandomStringUtils.randomAlphanumeric(10) + "_" + timestamp;
		String orderNo = DateUtil.getToday() + "" + timestamp;
		
		//signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
		Map<String, String> signParam = new HashMap<String, String>();
		signParam.put("oid", orderNo);
		signParam.put("price", Integer.toString(price));
		signParam.put("timestamp", timestamp);
		
		
		mResult.put("signature", SignatureUtil.makeSignature(signParam));
		mResult.put("mid", INICIS_MID);
		mResult.put("mKey", SignatureUtil.hash(INICIS_SIGNKEY, "SHA-256"));
		mResult.put("orderNo", orderNo);
		mResult.put("price", Integer.toString(price));
		mResult.put("timestamp", timestamp);
		
		return mResult;
	}
	
	/**
	 * 결제 완료
	 */
	@SuppressWarnings({ "unused" })
	@Override
	public Map<String, Object> paymentProcess(HttpServletRequest httpServletReques)  throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		boolean bStatus = false; 
		
		try{
			
			Map<String,String> paramMap = this.getReqeustParameter(httpServletReques);
			
			// 인증이 성공일 경우만
			if("0000".equals(paramMap.get("resultCode"))){

				// 1.전문 필드 값 설정(***가맹점 개발수정***)
				String mid 		= INICIS_MID;						// 가맹점 ID 수신 받은 데이터로 설정
				String signKey	= INICIS_SIGNKEY;							// 가맹점에 제공된 키(이니라이트키) (가맹점 수정후 고정) !!!절대!! 전문 데이터로 설정금지
				String timestamp= SignatureUtil.getTimestamp();				// util에 의해서 자동생성
				String charset 	= "UTF-8";								    // 리턴형식[UTF-8,EUC-KR](가맹점 수정후 고정)
				String format 	= "JSON";								    // 리턴형식[XML,JSON,NVP](가맹점 수정후 고정)
				String authToken= paramMap.get("authToken");			    // 취소 요청 tid에 따라서 유동적(가맹점 수정후 고정)
				String authUrl	= paramMap.get("authUrl");				    // 승인요청 API url(수신 받은 값으로 설정, 임의 세팅 금지)
				String netCancel= paramMap.get("netCancelUrl");			 	// 망취소 API url(수신 받은 값으로 설정, 임의 세팅 금지)
				String ackUrl 	= paramMap.get("checkAckUrl");			    // 가맹점 내부 로직 처리후 최종 확인 API URL(수신 받은 값으로 설정, 임의 세팅 금지)		
				String merchantData = paramMap.get("merchantData");			// 가맹점 관리데이터 수신
				
				// 2.signature 생성
				Map<String, String> signParam = new HashMap<String, String>();

				signParam.put("authToken",	authToken);		// 필수
				signParam.put("timestamp",	timestamp);		// 필수

				// signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
				String signature = SignatureUtil.makeSignature(signParam);

//	      		String price = "";  // 가맹점에서 최종 결제 가격 표기 (필수입력아님)
	      		
	      		
	      		// 3.API 요청 전문 생성
	      		Map<String, String> authMap = new Hashtable<String, String>();

				authMap.put("mid"			    ,mid);			  // 필수
				authMap.put("authToken"		,authToken);	// 필수
				authMap.put("signature"		,signature);	// 필수
				authMap.put("timestamp"		,timestamp);	// 필수
				authMap.put("charset"		  ,charset);		// default=UTF-8
				authMap.put("format"		  ,format);		  // default=XML
	      		//authMap.put("price" 		,price);		    // 가격위변조체크기능 (선택사용)
	      
				log.debug("##승인요청 API 요청##");

				HttpUtil httpUtil = new HttpUtil();
				
				try{
					// 4.API 통신 시작
					String authResultString = "";

					authResultString = httpUtil.processHTTP(authMap, authUrl);
					
					//5.API 통신결과 처리(***가맹점 개발수정***)
					String test = authResultString.replace(",", "&").replace(":", "=").replace("\"", "").replace(" ","").replace("\n", "").replace("}", "").replace("{", "");
					
					Map<String, String> resultMap = new HashMap<String, String>();
					resultMap = ParseUtil.parseStringToMap(test); //문자열을 MAP형식으로 파싱
									
					
					/*************************  결제보안 강화 2016-05-18 START ****************************/ 
					Map<String , String> secureMap = new HashMap<String, String>();
					secureMap.put("mid"			, mid);								//mid
					secureMap.put("tstamp"		, timestamp);						//timestemp
					secureMap.put("MOID"		, resultMap.get("MOID"));			//MOID
					secureMap.put("TotPrice"	, resultMap.get("TotPrice"));		//TotPrice
					
					// signature 데이터 생성 
					String secureSignature = SignatureUtil.makeSignatureAuth(secureMap);
					/*************************  결제보안 강화 2016-05-18 END ****************************/
					
					PaymentVO paymentVO = new PaymentVO();
					
					//결과 코드 / 내용
					paymentVO.setResCd(resultMap.get("resultCode"));
					paymentVO.setResMsg(resultMap.get("resultMsg"));

					//거래 번호
					paymentVO.setTidNo(resultMap.get("tid"));
					
					//결제방법(지불수단)
					paymentVO.setPayType(resultMap.get("payMethod").toUpperCase());
					
					//결제완료금액
					paymentVO.setPrice(Integer.parseInt(resultMap.get("TotPrice")));
					
					//주문 번호
					paymentVO.setOrderNo(resultMap.get("MOID"));
					
					//승인번호
					paymentVO.setApplNo(resultMap.get("applNum"));
					
					if("VBANK".equals(paymentVO.getPayType())){
						//가상계좌 은행코드
						paymentVO.setVbankCd(resultMap.get("VACT_BankCode"));
						
						//가상계좌 은행명
						paymentVO.setVbankNm(resultMap.get("vactBankName"));
						
						//가상계좌 은행 계좌번호
						paymentVO.setVbankNum(resultMap.get("VACT_Num"));
						
						//가상계좌 입금만료일
						if(!StringUtil.isEmpty(resultMap.get("VACT_Date"))) {
							paymentVO.setVbankFnhDtm(resultMap.get("VACT_Date") + "" + resultMap.get("VACT_Time"));
						}else {
							paymentVO.setVbankFnhDtm(null);
						}
					}
					
					log.info("===============================================================================");
					log.info("secureSignature : " + secureSignature);
					log.info("authSignature : " + resultMap.get("authSignature"));
					log.info("===============================================================================");
					
					if("0000".equals(resultMap.get("resultCode")) && secureSignature.equals(resultMap.get("authSignature")) ){	//결제보안 강화 2016-05-18

						log.debug("== 결제보안키가 인증완료 ==");
						
						this.paymentSuccess(paymentVO);
						
		                bStatus = true;
		                
						mResult.put("info", paymentVO);
						
					} else {
						log.error("== 결제보안키가 다른 경우 ==");
						//결제보안키가 다른 경우
						if (!secureSignature.equals(resultMap.get("authSignature")) && "0000".equals(resultMap.get("resultCode"))) {
							
							paymentVO.setResCd(resultMap.get("resultCode"));
							paymentVO.setResMsg("데이터 위변조 체크 실패");
							
							this.paymentFail(paymentVO);
							
							//망취소
							throw new Exception("데이터 위변조 체크 실패");
							
						}else{
							//결제 실패
							this.paymentFail(paymentVO);
						}
					}

				} catch (Exception ex) {
					//#####################
					// 망취소 API
					//#####################
					log.error("=========== 망취소 API =========");
					ex.printStackTrace();
					log.error("==============================");

					String netcancelResultString = httpUtil.processHTTP(authMap, netCancel);	// 망취소 요청 API url(고정, 임의 세팅 금지)

					// 취소 결과 확인
					//log.debug("<p>"+netcancelResultString.replaceAll("<", "&lt;").replaceAll(">", "&gt;")+"</p>");
				}
			}else{
				//인증 실패
			}
			
		}catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
		
		mResult.put("status", bStatus);
		
		return mResult;
		
	}
	
	
	/**
	 * 결제완료 : 모바일
	 */
	@SuppressWarnings({ "unused" })
	@Override
	public Map<String, Object> paymentMobleProcess(HttpServletRequest httpServletReques)  throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		boolean bStatus = false; 
		
		try{
			
			Map<String,String> paramMap = this.getReqeustParameter(httpServletReques);
			
			// 인증이 성공일 경우만
			if("00".equals(paramMap.get("P_STATUS"))){

				// 1.전문 필드 값 설정(***가맹점 개발수정***)
				String mid 		= INICIS_MID;						// 가맹점 ID 수신 받은 데이터로 설정
				String signKey	= INICIS_SIGNKEY;							// 가맹점에 제공된 키(이니라이트키) (가맹점 수정후 고정) !!!절대!! 전문 데이터로 설정금지
				String authUrl	= paramMap.get("P_REQ_URL");
	      		
	      		// 3.API 요청 전문 생성
	      		Map<String, String> authMap = new Hashtable<String, String>();
				authMap.put("P_MID", mid);			  // 필수
				authMap.put("P_TID", paramMap.get("P_TID"));	// 필수
	      
				System.out.println("##승인요청 API 요청##");

				HttpUtil httpUtil = new HttpUtil();
				
				try{
					// 4.API 통신 시작
					String authResultString = "";

					authResultString = httpUtil.processHTTP(authMap, authUrl);
					
					//5.API 통신결과 처리(***가맹점 개발수정***)
					String test = authResultString.replace(",", "&").replace(":", "=").replace("\"", "").replace(" ","").replace("\n", "").replace("}", "").replace("{", "");
					
					Map<String, String> resultMap = new HashMap<String, String>();
					resultMap = ParseUtil.parseStringToMap(test); //문자열을 MAP형식으로 파싱
									
					log.info("[모바일 결제 실패] resultMap : "+ resultMap.toString());
					
					PaymentVO paymentVO = new PaymentVO();
					
					//결과 코드 / 내용
					paymentVO.setResCd(resultMap.get("P_STATUS"));
					paymentVO.setResMsg(resultMap.get("P_RMESG1"));
					
					//거래 번호
					paymentVO.setTidNo(resultMap.get("P_TID"));
					
					//결제방법(지불수단)
					paymentVO.setPayType(resultMap.get("P_TYPE").toUpperCase());
					
					//결제완료금액
					paymentVO.setPrice(Integer.parseInt(resultMap.get("P_AMT")));
					
					//주문 번호
					paymentVO.setOrderNo(resultMap.get("P_OID"));
					
					//승인번호
					paymentVO.setApplNo(resultMap.get("P_AUTH_NO"));
					
					if("VBANK".equals(paymentVO.getPayType())){
						//가상계좌 은행코드
						paymentVO.setVbankCd(resultMap.get("P_VACT_BANK_CODE"));
						
						//가상계좌 은행명
						paymentVO.setVbankNm(resultMap.get("P_FN_NM"));
						
						//가상계좌 은행 계좌번호
						paymentVO.setVbankNum(resultMap.get("P_VACT_NUM"));
						
						//가상계좌 입금만료일
						if(!StringUtil.isEmpty(resultMap.get("P_VACT_DATE"))) {
							paymentVO.setVbankFnhDtm(resultMap.get("P_VACT_DATE") + "" + resultMap.get("P_VACT_TIME"));
						}else {
							paymentVO.setVbankFnhDtm(null);
						}
					}	
					
					
					if("00".equals(resultMap.get("P_STATUS"))){	
		                
						//결제 완료 처리
						this.paymentSuccess(paymentVO);
						
		                bStatus = true;
		                          
						mResult.put("info", paymentVO);
						
					} else {
						//승인실패
						log.info("[모바일 결제 실패] " + resultMap.get("P_STATUS") + " : "+ resultMap.get("P_RMESG1"));
						
						this.paymentFail(paymentVO);
					}
				} catch (Exception ex) {
					
				}
			}else{
				//인증 실패
				log.info("[모바일 인증 실패] " + paramMap.get("P_STATUS") + " : "+ paramMap.get("P_RMESG1"));
			}
			
		}catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
		
		mResult.put("status", bStatus);
		
		return mResult;
	}
	
	
	/**
	 * 결제취소 
	 */
	public boolean paymentCancle(OrderVO orderVO)  throws Exception {
		
		boolean bStatus = false; 
		
		Date date_now = new Date(System.currentTimeMillis());
		SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss"); 
		
		//전송 URL
		String APIURL="https://iniapi.inicis.com/api/v1/refund"; 
		
		//부분취소
		String type = "PartialRefund";
		
		//환불수수료 - 왕복배송료 : 6000원
		int refundFee = 6000;			
		
		String paymethod = orderVO.getPayType();
		String orderCd = orderVO.getOrderCd();					//취소사유
		String timestamp = fourteen_format.format(date_now);
		String clientIp = "52.78.32.163";
		String tid = orderVO.getTidNo();  						// TID 입력
		String msg = "거래취소요청";
		int orderPrice = orderVO.getOrderPrice();				//거래 가격
		int canclePrice = 0;									//취소가격
		int confirmPrice = 0;									//취소후 남은 가격
		
		//환불계좌번호 1> AES암호화는 128-cbc 방식입니다.  2> INIAPI key 와 iv 를 사용합니다.
		String refundAcctNum = "";
		//환불계좌 은행코드
		String refundBankCode = "";
		//환불계좌 예금주명
		String refundAcctName = "";
		
		String data_hash = "";
		
		if("VCARD".equals(paymethod)) {
			paymethod = "Card";
		}
		
		if("VBANK".equals(paymethod)) {
			paymethod = "Vacct";
			
			//가상계좌 (입금전, 채번취소 시 사용)	GVacct
		}
		
		if("DIRECTBANK".equals(paymethod)) {
			paymethod = "Acct";
		}
		
		//'입금대기', '결제완료', '배송준비중', '배송중', '배송완료', '구매확정'
		//'주문취소', '주문취소신청', '주문취소승인', '주문취소완료' '반품신청', '반품승인', '반품완료', '교환신청', '교환승인', '교환완료'
		if("주문취소".equals(orderCd)) {
			msg = "사용자 주문취소";
		}else if("주문취소완료".equals(orderCd)) {
			msg = "사용자 주문취소요청에 따른 환불";
		}else if("반품완료".equals(orderCd)) {
			msg = "사용자 반품요청에 따른 환불";
		}else if("교환완료".equals(orderCd)) {
			msg = "사용자 교환요청에 따른 환불";
		}
		
		//환불수수료 - 왕복배송료 : 6000원
		if("주문취소".equals(orderCd)) {
			canclePrice = orderPrice;
		}else {
			//환불수수료가 더 많으면 취소 금액은 0원
			//0원을 취소 할수 있나??? 화면에서 처리해야할것 같음.
			if(orderPrice > refundFee) {
				canclePrice = orderPrice - refundFee;
			}
		}
		
		confirmPrice = orderPrice - canclePrice;
		
		
		/*
		 * Hash 암호화
		 */
		//가상계좌 환불
		if("Vacct".equals(paymethod)){
			//history에서 환불계좌정보 가져와야함.
			OrderHistoryVO refInfo = shopDAO.selectRefBankInfo(orderVO);
			
			if(refInfo == null){
	            throw new IllegalArgumentException("환불계좌 정보가 없습니다.");                            
	        }
			
			refundAcctNum = CryptoUtil.encryptAES_CBC(refInfo.getRefBankNum(), INICIS_APIKEY, INICIS_APIIV);
			refundBankCode = refInfo.getRefBankCd();
			refundAcctName = refInfo.getRefAcctNm();
			
			data_hash = INICIS_APIKEY + type + paymethod + timestamp + clientIp + INICIS_MID + tid + canclePrice + confirmPrice + refundAcctNum;
		}else{
			data_hash = INICIS_APIKEY + type + paymethod + timestamp + clientIp + INICIS_MID + tid + canclePrice + confirmPrice;
		}
		
		
		String hashData = CryptoUtil.encryptSHA512(data_hash); // SHA_512_Util 을 이용하여 hash암호화(해당 LIB는 직접구현 필요)
		      
		log.info("[거래 취소] data_hash : " + data_hash + " , hashData : " + hashData);

		Map<String, String> CashReceiptsMap = new HashMap<String, String>();
		CashReceiptsMap.put("type"       , type);
		CashReceiptsMap.put("paymethod"  , paymethod);
		CashReceiptsMap.put("timestamp"  , timestamp);
		CashReceiptsMap.put("clientIp"   , clientIp);
		CashReceiptsMap.put("mid"        , INICIS_MID);
		CashReceiptsMap.put("tid"        , tid);
		CashReceiptsMap.put("msg"        , msg);
		CashReceiptsMap.put("hashData"   , hashData);
		CashReceiptsMap.put("price"   , Integer.toString(canclePrice));							//price	취소요청금액
		CashReceiptsMap.put("confirmPrice"   , Integer.toString(confirmPrice));		//confirmPrice	부분취소 후 남은금액
		
		//가상계좌일경우
		if("Vacct".equals(paymethod)){
			CashReceiptsMap.put("refundAcctNum", refundAcctNum);
			CashReceiptsMap.put("refundBankCode", refundBankCode);
			CashReceiptsMap.put("refundAcctName", refundAcctName);
		}

		try{
			HttpUtil httpUtil = new HttpUtil();
			String authResultString = httpUtil.processHTTP(CashReceiptsMap, APIURL);
			String result = authResultString.replace(",", "&").replace(":", "=").replace("\"", "").replace(" ","").replace("\n", "").replace("}", "").replace("{", "");
			
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap = ParseUtil.parseStringToMap(result); //문자열을 MAP형식으로 파싱
							
			log.info("[거래 취소] resultMap : "+ resultMap.toString());
			
			if("00".equals(resultMap.get("resultCode"))) 
			{
				PaymentVO paymentVO = new PaymentVO();
				paymentVO.setPayCd("환불");
				paymentVO.setResCd(resultMap.get("resultCode"));
				paymentVO.setResMsg(resultMap.get("resultMsg"));
				paymentVO.setPrice(Integer.parseInt(resultMap.get("prtcPrice")));
				paymentVO.setOrderNo(orderVO.getOrderNo());
				paymentVO.setTidNo(resultMap.get("tid"));
				
				//결과 기록
				shopDAO.insertOrderPayment(paymentVO);
				
				//상태이력
				OrderHistoryVO historyVO = new OrderHistoryVO();
				historyVO.setOldOrderCd(orderVO.getOrderCd());
				historyVO.setNowOrderCd(orderVO.getOrderCd());
				historyVO.setOrderNo(orderVO.getOrderNo());
				historyVO.setPayAmt(paymentVO.getPrice());
				historyVO.setExcSbc2("환불완료 / 환불금액 : " + StringUtil.commaNumber(paymentVO.getPrice()) + "원 ");
				shopDAO.insertOrderHistory(historyVO);
				
				bStatus = true;
			}
			else
			{
				throw new IllegalArgumentException();
			}
	 	}catch (Exception ex) {
	 		 log.error("=========== ERROR - 환불 API =========");
	 		throw processException("exception.error", ex);
	 	}
		
		return bStatus;
	}
	
	/**
	 * 사용자 주문 접수 / 취소
	 */
	@Override
	public Map<String, Object> insertOrderHistory(OrderHistoryVO orderHistoryVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
            
            if(orderHistoryVO.getIsApi()){
	            if((boolean)mSession.get("isLogin")){
	            	orderHistoryVO.setMbrSn((int)mSession.get("userSn"));
	            }
            }
            
            OrderVO orderVO = new  OrderVO();
            orderVO.setOrderNo(orderHistoryVO.getOrderNo());
            
            //이전 진행상태 
//            OrderVO preOrderVO = shopDAO.selectOrdeInfo(orderVO);
//            orderHistoryVO.setOldOrderCd(orderVO2.getOrderCd());
            
            //if("결제완료".equals(orderHistoryVO.getOldOrderCd()) || "배송준비중".equals(orderHistoryVO.getOldOrderCd())){
            //	orderHistoryVO.setNowOrderCd("주문취소");
            //}
            
            Object resultObject = shopDAO.insertOrderHistory(orderHistoryVO) ;
    		
    		if(resultObject.getClass() == Integer.class){
    			
    			orderHistoryVO.setOrderHisSn((Integer)resultObject);
    			
    			//첨부등록
                mResult.put("uploadInfo", attachFileService.insertAttachFile(orderHistoryVO, mSession));
                
                //주문상태 변경
                shopDAO.updateOrderByOrderCd(orderHistoryVO);
                
                /*
                 * 가상계좌의 입금대기는 취소처리 할필요 없음.
                 */
                if(!"입금대기".equals(orderHistoryVO.getOldOrderCd()))
                {
	    			//주문취소.상태에 따라서. 변경
	                if("주문취소".equals(orderHistoryVO.getNowOrderCd()))
	                {
	                	//주문정보 조회
	    				OrderVO orderVO2 = shopDAO.selectOrdeInfo(orderVO);
	    				
	    				//결제취소
	    				this.paymentCancle(orderVO2);
	                }
                }
                
                bStatus = true;
                
    		}else{
    			throw new IllegalArgumentException();
    		}
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        
        mResult.put("status", bStatus);
		
		return mResult;
	}
	
	
	/**
	 * 사용자 주문 접수
	 */
	@Override
	public Map<String, Object> insertOrderHistoryAdm(OrderHistoryVO orderHistoryVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		boolean bStatus = true; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
            
            if((boolean)mSession.get("isLogin")){
            	orderHistoryVO.setRgstSn((int)mSession.get("userSn"));
            	orderHistoryVO.setRgstId((String)mSession.get("userId"));
            }
            
            
    		if(!orderHistoryVO.getOrderNo().isEmpty() || ( orderHistoryVO.getDelSeq() != null && !orderHistoryVO.getDelSeq().isEmpty() )){
    			
    			if( orderHistoryVO.getDelSeq() != null && !orderHistoryVO.getDelSeq().isEmpty() ){
    				
    				for(String orderNo : orderHistoryVO.getDelSeq()){
    					
    					OrderHistoryVO tmp = new OrderHistoryVO();
    					tmp.setOrderNo(orderNo);
    					tmp.setOldOrderCd(orderHistoryVO.getOldOrderCd());
    					tmp.setNowOrderCd(orderHistoryVO.getNowOrderCd());
    					tmp.setExcSbc2(orderHistoryVO.getExcSbc2());
    					
	    				Object resultObject = shopDAO.insertOrderHistory(tmp) ;
			    		if(resultObject.getClass() == Integer.class){
			    			tmp.setOrderHisSn((Integer)resultObject);
			    			shopDAO.updateOrderByOrderCd(tmp);
			    			
			    			//OrderVO orderTempVO = new OrderVO();
			    			//shopDAO.updateOrderByDeliveryInfo(orderTempVO);
			    			
			    			//카톡 발송
			    			if("배송중".equals(orderHistoryVO.getNowOrderCd())){
			    				//주문정보 조회
			    				OrderVO orderVO = shopDAO.selectOrdeInfo(new OrderVO(orderNo));
			    				
				    			//카카오톡 발송
				    			String smsContent = "안녕하세요 #NAME# 고객님\n\r상품 배송으로 안내드립니다\n\r-상품명 : #상품명#\n\r-수량 : #수량#\n\r-배송번호 : #배송번호#";
				    			smsContent = smsContent.replaceAll("#NAME#", orderVO.getReceiverNm());
				    			smsContent = smsContent.replaceAll("#상품명#", orderVO.getGoodNm());
				    			smsContent = smsContent.replaceAll("#수량#", Integer.toString(orderVO.getOrderQuantity()));
			    				smsContent = smsContent.replaceAll("#배송번호#", StringUtil.isNullToString(orderVO.getDeliveryNum()));
				    			
				    			//SMS 발송
				    			smsSenderUtil.smsSend("DELIVERY", orderVO.getReceiverHp(), smsContent);
			    			}
			    		}else{
			    			throw new IllegalArgumentException();
			    		}
    				}
    				
    			}else{
		            Object resultObject = shopDAO.insertOrderHistory(orderHistoryVO) ;
		    		
		    		if(resultObject.getClass() == Integer.class){
		    			orderHistoryVO.setOrderHisSn((Integer)resultObject);
		    			shopDAO.updateOrderByOrderCd(orderHistoryVO);
		    			
		    			if("배송중".equals(orderHistoryVO.getNowOrderCd()))
		    			{
		    				//주문정보 조회
		    				OrderVO orderVO = shopDAO.selectOrdeInfo(new OrderVO(orderHistoryVO.getOrderNo()));
		    				
			    			//카카오톡 발송
			    			String smsContent = "안녕하세요 #NAME# 고객님\n\r상품 배송으로 안내드립니다\n\r-상품명 : #상품명#\n\r-수량 : #수량#\n\r-배송번호 : #배송번호#";
			    			smsContent = smsContent.replaceAll("#NAME#", orderVO.getReceiverNm());
			    			smsContent = smsContent.replaceAll("#상품명#", orderVO.getGoodNm());
			    			smsContent = smsContent.replaceAll("#수량#", Integer.toString(orderVO.getOrderQuantity()));
		    				smsContent = smsContent.replaceAll("#배송번호#", StringUtil.isNullToString(orderVO.getDeliveryNum()));
			    			
			    			//SMS 발송
			    			smsSenderUtil.smsSend("DELIVERY", orderVO.getReceiverHp(), smsContent);
		    			}
		    			else if( "취소완료".equals(orderHistoryVO.getNowOrderCd()) || "반품완료".equals(orderHistoryVO.getNowOrderCd())
		    						|| "주문취소완료".equals(orderHistoryVO.getNowOrderCd()) || "교환완료".equals(orderHistoryVO.getNowOrderCd()) )
		    			{
		    				//주문정보 조회
		    				OrderVO orderVO = shopDAO.selectOrdeInfo(new OrderVO(orderHistoryVO.getOrderNo()));
		    				
		    				//결제취소
		    				this.paymentCancle(orderVO);
		    			}
		    		}else{
		    			throw new IllegalArgumentException();
		    		}
    			}
    		}
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        
        mResult.put("status", bStatus);
		
		return mResult;
	}
	
	/**
	 * 송장엑셀파일 등록
	 * - 발송처리
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> insertInvoiceFormFile(BoardVO boardVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
		
		boolean bStatus = true; 
		
		try{
			Map<String,Object> mSession = multiUtil.getSessionInfo();
			
			//파일 등록
			boardVO.setRgstSn((int)mSession.get("userSn"));
	        boardVO.setRgstId((String)mSession.get("userId"));
	        
	        Object resultObject = boardDAO.insertBoard(boardVO) ;
	        
	        if(resultObject.getClass() == Integer.class){
                boardVO.setBlcSn((Integer)resultObject);
                
                /*
                 * 첨부등록
                 */
                Map<String, Object> mResultFile = attachFileService.insertAttachFile(boardVO, mSession);
                
                AttachFileVO attachFileVO = ((List<AttachFileVO>)mResultFile.get("uploadFileInfo")).get(0);
    			
    			log.debug("파일명 : " + attachFileVO.getFilNm());
    			
    			File csvFile = new File(fileSavePath.getAbsolutePath() + File.separator + boardVO.getLgrpCd().toUpperCase() + File.separator +  attachFileVO.getFilNm());
    			
                String extension = attachFileVO.getFilNm().substring(attachFileVO.getFilNm().lastIndexOf(".") + 1).toLowerCase();
            	
    		    if (!extension.equals("xlsx") && !extension.equals("xls")) {
    		    	throw new IOException("엑셀파일만 업로드 해주세요.");
    		    }
    	
    		    Workbook workbook = null;
    	
    		    if (extension.equals("xlsx")) {
    		      workbook = new XSSFWorkbook(new FileInputStream(csvFile));
    		    } else if (extension.equals("xls")) {
    		      workbook = new HSSFWorkbook(new FileInputStream(csvFile));
    		    }
    	
    		    Sheet worksheet = workbook.getSheetAt(0);
    		    
    		    List<OrderVO> dataList = new ArrayList<OrderVO>();
    	
    		    //3번 Row부터 시작
    		    for (int i = 1; i <= worksheet.getLastRowNum(); i++) {
    	
    		      Row row = worksheet.getRow(i);
    	
    		      OrderVO data = new OrderVO();
    	
    		      if(row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC) {
    		    	  row.getCell(0).setCellType(Cell.CELL_TYPE_STRING );
    		    	  data.setOrderNo(row.getCell(0).getStringCellValue());
    		      } else {
    		    	  data.setOrderNo(row.getCell(0).getStringCellValue());
    		      }
    		      
    		      data.setDeliveryCorp(row.getCell(1).getStringCellValue());
    		      
    		      if(row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC) {
    		    	  row.getCell(2).setCellType(Cell.CELL_TYPE_STRING );
    		    	  data.setDeliveryNum(row.getCell(2).getStringCellValue());
    		      } else {
    		    	  data.setDeliveryNum(row.getCell(2).getStringCellValue());
    		      }
    		      
    		      if(row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
    		    	  row.getCell(0).setCellType(Cell.CELL_TYPE_STRING );
    		    	  data.setDeliveryStrDtm(row.getCell(3).getStringCellValue());
    		      } else {
    		    	  data.setDeliveryStrDtm(row.getCell(3).getStringCellValue());
    		      }
    		      
    		      data.setOrderCd("배송중");
    		      
    		      dataList.add(data);
    		    }
    		    
    		    //엑셀 내용 확인
    		    if(!dataList.isEmpty() || dataList.size() > 0) 
    		    {
    		    	int dataTotCnt = dataList.size();
    		    	int dataSuccessCnt = 0;
    		    	int dataFailCnt = 0;
    		    	String errorMsg = "";
    		    	
		    		for(OrderVO order : dataList)
		    		{
		    			//정보 조회
		    	        OrderVO orderVO2 = shopDAO.selectOrdeInfo(order);
		    	        
		    	        if(orderVO2 != null){
			    	        if("N".equals(orderVO2.getDeliveryYn())) {
				    			//상태이력
			    	        	try {
					    			OrderHistoryVO historyVO = new OrderHistoryVO();
					    			historyVO.setOldOrderCd(orderVO2.getOrderCd());
					    	        historyVO.setNowOrderCd(order.getOrderCd());
					    	        historyVO.setOrderNo(order.getOrderNo());
					    	        historyVO.setExcSbc2("[송장등록]" + order.getDeliveryCorp() + " | " + order.getDeliveryNum());
					    	        shopDAO.insertOrderHistory(historyVO);
					    	        
						    		//진행상태 변경
					    			shopDAO.updateOrderByDeliveryInfo(order);
					    			
					    			//카카오톡 발송
					    			String smsContent = "안녕하세요 #NAME# 고객님\n\r상품 배송으로 안내드립니다\n\r-상품명 : #상품명#\n\r-수량 : #수량#\n\r-배송번호 : #배송번호#";
					    			smsContent = smsContent.replaceAll("#NAME#", orderVO2.getReceiverNm());
					    			smsContent = smsContent.replaceAll("#상품명#", orderVO2.getGoodNm());
					    			smsContent = smsContent.replaceAll("#수량#", Integer.toString(orderVO2.getOrderQuantity()));
				    				smsContent = smsContent.replaceAll("#배송번호#", StringUtil.isNullToString(order.getDeliveryNum()));
					    			smsSenderUtil.smsSend("DELIVERY", orderVO2.getReceiverHp(), smsContent);
					    			
				    				String msg = "\n[송장등록 완료] " + order.getDeliveryNum();
			    	        		errorMsg += msg;
			    	        		
					    			dataSuccessCnt ++;
					    			
			    	        	}catch (Exception e) {
			    	        		String msg = "\n[송장등록 에러] " + order.getDeliveryNum() + " - " + e.getMessage();
			    	        		errorMsg += msg;
			    	        		log.error(msg);
			    	        		dataFailCnt ++;
								}
			    	        }else {
			    	        	String msg = "\n[송장등록 실패] " + order.getDeliveryNum() + " - 배송이 완료된 건입니다.";
		    	        		errorMsg += msg;
		    	        		log.error(msg);
			    	        	dataFailCnt ++;
			    	        }
		    	        }else {
		    	        	String msg = "\n[송장등록 실패] " + order.getDeliveryNum() + " - 주문정보가 없습니다.";
		    	        	errorMsg += msg;
		    	        	log.error(msg);
	    	        		dataFailCnt ++;       
		    	        }
    				}
		    		
		    		//결과 저장
		    		boardVO.setBlcSbc1(dataTotCnt + "|" + dataSuccessCnt + "|" + dataFailCnt);
		    		boardVO.setBlcSbc2(errorMsg);
		    		boardDAO.updateBoardExcelUpload(boardVO);
    		    }
            }
			
		}catch(Exception exception){
            bStatus = false;
            throw processException("exception.error", exception);
        }
		
		mResult.put("status", bStatus);
		
		return mResult;
		
	}
	
	
	@Override
	public Map<String, Object> insertScrap(ScrapVO scrapVO) throws Exception {
		 
		Map<String, Object> mResult = new HashMap<String, Object>();
	        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
  
            scrapVO.setMbrSn((int)mSession.get("userSn"));
            
            /*
             * 중복확인
             */
            if(scrapDAO.selectScrapCount(scrapVO) == 0){
            	scrapDAO.insertScrap(scrapVO);
            	bStatus = true;
            }
            
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
	}


	@Override
	public Map<String, Object> deleteScrap(ScrapVO scrapVO) throws Exception {
		
		Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
  
            scrapVO.setMbrSn((int)mSession.get("userSn"));
     
            scrapDAO.deleteScrap(scrapVO);
            
            bStatus = true;
                      
        }catch(Exception exception){          
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
	}

	
	@Override
	public String paymentNotifyVbank(HttpServletRequest httpServletRequest) throws Exception {
		
		String strRtn = "FAIL";
		
		try{
			
			httpServletRequest.setCharacterEncoding("UTF-8");
			
			Map<String,String> resultMap = this.getReqeustParameter(httpServletRequest);
			
			String ipAddr = httpServletRequest.getRemoteAddr();
			
//			if(ipAddr.startsWith("203.238.37") || ipAddr.startsWith("39.115.212") || ipAddr.startsWith("183.109.71"))
//			{
				
				if("0200".equals(resultMap.get("type_msg")))
				{
					PaymentVO paymentVO = new PaymentVO();
					
					//거래구분[0200:정상, 0400:취소]
					paymentVO.setResCd(resultMap.get("type_msg"));
					
					//입금은행명 : 입금자명
					paymentVO.setResMsg(resultMap.get("nm_inputbank") + " | " + resultMap.get("nm_input"));
					
					//지불수단[VBANK: 가상계좌]
					paymentVO.setPayType("VBANK");
					
					//거래 번호
					paymentVO.setTidNo(resultMap.get("no_tid"));
					
					//주문 번호
					paymentVO.setOrderNo(resultMap.get("no_oid"));

					//결제완료금액
					paymentVO.setPrice(Integer.parseInt(resultMap.get("amt_input")));
					
					/*
					 * 결제 완료 처리
					 */
					this.paymentVBankSuccess(paymentVO);
					
					strRtn = "OK";
					
				}
				else
				{
					log.info("[가상계좌입금통지 : 거래상태 오류] " + resultMap.get("type_msg") + " : "+ resultMap.get("no_tid"));
					throw new IllegalArgumentException();
				}
//			}
//			else
//			{
//				log.info("[가상계좌입금통지 : IP주소 인증 실패] " + ipAddr + " : "+ resultMap.get("type_msg") + " : "+ resultMap.get("no_tid"));
//				throw new IllegalArgumentException();
//			}
		}catch(Exception exception){          
            throw processException("exception.error", exception);
        }
			
		
		return strRtn;
	}

	
	@Override
	public String paymentNotifyMobileVbank(HttpServletRequest httpServletRequest) throws Exception {
		
		String strRtn = "FAIL";
		
		try{
			
			httpServletRequest.setCharacterEncoding("UTF-8");
			
			Map<String,String> resultMap = this.getReqeustParameter(httpServletRequest);
			
			String ipAddr = httpServletRequest.getRemoteAddr();
			
//			if(ipAddr.startsWith("118.129.210") || ipAddr.startsWith("183.109.71") || ipAddr.startsWith("203.238.37"))
//			{
				
				if("02".equals(resultMap.get("P_STATUS")))
				{
					PaymentVO paymentVO = new PaymentVO();
					
					//거래상태 [00:가상계좌 채번, 02:가상계좌입금통보]
					paymentVO.setResCd(resultMap.get("P_STATUS"));
					
					//채번된 가상계좌번호|입금기한] 은행명 : 주문자명
					paymentVO.setResMsg(resultMap.get("P_RMESG1") + " | " + resultMap.get("P_FN_NM") + " | " + resultMap.get("P_UNAME"));
					
					//지불수단[VBANK: 가상계좌]
					paymentVO.setPayType("VBANK");
					
					//거래 번호
					paymentVO.setTidNo(resultMap.get("P_TID"));
					
					//주문 번호
					paymentVO.setOrderNo(resultMap.get("P_OID"));

					//결제완료금액
					paymentVO.setPrice(Integer.parseInt(resultMap.get("P_AMT")));
					
					/*
					 * 결제 완료 처리
					 */
					this.paymentVBankSuccess(paymentVO);
					
					strRtn = "OK";
				}
				else
				{
					log.info("[가상계좌입금통지 : 거래상태 오류] " + resultMap.get("P_STATUS") + " : "+ resultMap.get("P_TID"));
					throw new IllegalArgumentException();
				}
//			}
//			else
//			{
//				log.info("[가상계좌입금통지 : IP주소 인증 실패] " + ipAddr + " : "+ resultMap.get("P_STATUS") + " : "+ resultMap.get("P_TID"));
//				throw new IllegalArgumentException();
//			}
		}catch(Exception exception){          
            throw processException("exception.error", exception);
        }
		
		return strRtn;
	}

	
	/**
	 * 결제관련 파라미터를 Map으로 반환하기
	 * 
	 * @param httpServletReques
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	protected Map<String,String> getReqeustParameter(HttpServletRequest httpServletReques) throws Exception {
		
		String temp = "";
		Map<String,String> paramMap = new Hashtable<String,String>();
		
		httpServletReques.setCharacterEncoding("UTF-8");
		
		Enumeration elems = httpServletReques.getParameterNames();

		while(elems.hasMoreElements()) {
			temp = (String) elems.nextElement();
			paramMap.put(temp, httpServletReques.getParameter(temp));
		}
		
		log.debug("paramMap : "+ paramMap.toString());
		
		return paramMap;
	}
	
	
	/**
	 * 결제 완료 처리한다.
	 * @param paymentVO
	 * @throws Exception
	 */
	protected void paymentSuccess(PaymentVO paymentVO) throws Exception {
		
		OrderVO orderVO = new OrderVO();
		
		if("VBANK".equals(paymentVO.getPayType()))
		{
			//결제 정보 저장
			shopDAO.insertOrderPayment(paymentVO);
			orderVO.setOrderCd("입금대기");
			orderVO.setOrderNo(paymentVO.getOrderNo());
			
			//주문정보 변경
			shopDAO.updateOrderPaymentComp(orderVO);
			
			//상태이력
			OrderHistoryVO historyVO = new OrderHistoryVO();
			historyVO.setOldOrderCd("결제요청");
	        historyVO.setNowOrderCd(orderVO.getOrderCd());
	        historyVO.setOrderNo(orderVO.getOrderNo());
	        historyVO.setPayAmt(paymentVO.getPrice());
	        historyVO.setExcSbc2(paymentVO.getResCd() + " / " + paymentVO.getResMsg());
	        shopDAO.insertOrderHistory(historyVO);
	        
	        //주문완료 메일 발송
	        OrderVO orderVO2 = shopDAO.selectOrdeInfo(orderVO);
	        String strMailTitle = "VIRUS CLEAN LAB 주문 완료 및 입금 안내";
			String strMailFileName = "shopOrderCompleteVbank.html";
			
			Map<String, String> mailInfo = new HashMap<String, String>();
			mailInfo.put("$$ORDER_NO$$", orderVO2.getOrderNo());
			mailInfo.put("$$PRD_NAME$$", orderVO2.getGoodNm());
			mailInfo.put("$$NAME$$", orderVO2.getBuyerNm());
			mailInfo.put("$$REG_DATE$$", orderVO2.getRgstDtm());
			mailInfo.put("$$VBANK_NM$$", orderVO2.getVbankNm());
			mailInfo.put("$$VBANK_NUM$$", orderVO2.getVbankNum());
			mailInfo.put("$$VBANK_FNH_DATE$$", orderVO2.getVbankFnhDtm());
			
			//메일 발송
	        mailSenderUtil.mailSendToUser(orderVO2.getBuyerEml(), strMailTitle, strMailFileName, mailInfo);

		}
		else
		{
			//결제 정보 저장
			shopDAO.insertOrderPayment(paymentVO);
			
			orderVO.setOrderCd("결제완료");
			orderVO.setOrderNo(paymentVO.getOrderNo());
			
			//주문정보 변경
			shopDAO.updateOrderPaymentComp(orderVO);
			
			//상태이력
			OrderHistoryVO historyVO = new OrderHistoryVO();
			historyVO.setOldOrderCd("결제요청");
			historyVO.setNowOrderCd(orderVO.getOrderCd());
			historyVO.setOrderNo(orderVO.getOrderNo());
			historyVO.setPayAmt(paymentVO.getPrice());
			historyVO.setExcSbc2(paymentVO.getResCd() + " / " + paymentVO.getResMsg());
			shopDAO.insertOrderHistory(historyVO);
			
			//주문완료 메일 발송
	        OrderVO orderVO2 = shopDAO.selectOrdeInfo(orderVO);
	        String strMailTitle = "VIRUS CLEAN LAB 주문 완료";
			String strMailFileName = "shopOrderComplete.html";
			
			Map<String, String> mailInfo = new HashMap<String, String>();
			mailInfo.put("$$ORDER_NO$$", orderVO2.getOrderNo());
			mailInfo.put("$$PRD_NAME$$", orderVO2.getGoodNm());
			mailInfo.put("$$NAME$$", orderVO2.getBuyerNm());
			mailInfo.put("$$REG_DATE$$", orderVO2.getRgstDtm());
			
			//메일 발송
	        mailSenderUtil.mailSendToUser(orderVO2.getBuyerEml(), strMailTitle, strMailFileName, mailInfo);
		}
        
        //카카오톡 발송
		//String smsContent = "[바이러스클린랩] 휴대폰본인확인 인증번호 [" + smsCertVO.getCertNo() + "]";
		
		//SMS 발송
		//smsSenderUtil.smsSend("CERT", smsCertVO.getHpNo(), smsContent);
	}
	
	
	/**
	 * 가상결제 입금통지 결제 완료 처리한다.
	 * @param paymentVO
	 * @throws Exception
	 */
	protected void paymentVBankSuccess(PaymentVO paymentVO) throws Exception {
		
		OrderVO orderVO = new OrderVO();
		
		//결제 정보 저장
		paymentVO.setPayCd("가상계좌입금");
		shopDAO.insertOrderPayment(paymentVO);
		
		orderVO.setOrderCd("결제완료");
		orderVO.setOrderNo(paymentVO.getOrderNo());
		
		//주문정보 변경
		shopDAO.updateOrderPaymentComp(orderVO);
		
		//상태이력
		OrderHistoryVO historyVO = new OrderHistoryVO();
		historyVO.setOldOrderCd("입금대기");
		historyVO.setNowOrderCd(orderVO.getOrderCd());
		historyVO.setOrderNo(orderVO.getOrderNo());
		historyVO.setPayAmt(paymentVO.getPrice());
		historyVO.setExcSbc2(paymentVO.getResCd() + " / " + paymentVO.getResMsg());
		shopDAO.insertOrderHistory(historyVO);
		
		//주문완료 메일 발송
        OrderVO orderVO2 = shopDAO.selectOrdeInfo(orderVO);
        String strMailTitle = "VIRUS CLEAN LAB 주문 완료";
		String strMailFileName = "shopOrderComplete.html";
		
		Map<String, String> mailInfo = new HashMap<String, String>();
		mailInfo.put("$$ORDER_NO$$", orderVO2.getOrderNo());
		mailInfo.put("$$PRD_NAME$$", orderVO2.getGoodNm());
		mailInfo.put("$$NAME$$", orderVO2.getBuyerNm());
		mailInfo.put("$$REG_DATE$$", orderVO2.getRgstDtm());
		
		//메일 발송
        mailSenderUtil.mailSendToUser(orderVO2.getBuyerEml(), strMailTitle, strMailFileName, mailInfo);
        
        //카카오톡 발송
		//String smsContent = "[바이러스클린랩] 휴대폰본인확인 인증번호 [" + smsCertVO.getCertNo() + "]";
		
		//SMS 발송
		//smsSenderUtil.smsSend("CERT", smsCertVO.getHpNo(), smsContent);
	}
	
	
	/**
	 * 결제실패 처리한다.
	 * @param paymentVO
	 * @throws Exception
	 */
	protected void paymentFail(PaymentVO paymentVO) throws Exception {
		//결제 정보 저장
		shopDAO.insertOrderPayment(paymentVO);
		
		if(!paymentVO.getOrderNo().isEmpty()){
			OrderVO orderVO = new OrderVO();
			orderVO.setOrderCd("결제실패");
			shopDAO.updateOrderPaymentComp(orderVO);
		}
	}
	
}
