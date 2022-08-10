package vc.virusclean.admin.shop.service.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jksoft.com.exception.NoResourceException;
import jksoft.com.service.XAbstractService;
import jksoft.com.util.HttpClientHelper;
import jksoft.com.util.MailSenderUtil;
import jksoft.com.util.StringUtil;
import jksoft.com.web.vo.AttachFileVO;
import vc.virusclean.admin.shop.service.ProductService;
import vc.virusclean.admin.shop.service.dao.ProductDAO;
import vc.virusclean.admin.shop.vo.BuyProductVO;
import vc.virusclean.admin.shop.vo.ProductVO;
import vc.virusclean.cmm.service.AccessLogService;
import vc.virusclean.cmm.service.AttachFileService;

/**
 * <pre>
 * 일반 게시판 공통 서비스 구현	
 * </pre>
 * 
 * @ClassName   : ProductServiceImpl.java
 * @Description : ProductService 를 구현
 */
@Service("productService")
public class ProductServiceImpl extends XAbstractService implements ProductService {

	@Resource(name="mailSenderUtil")
	private MailSenderUtil mailSenderUtil;
	
    @Resource(name="productDAO")   
    private ProductDAO productDAO;
    
    @Resource(name="httpClientHelper")
    private HttpClientHelper httpClientHelper;
    
    @Resource(name="attachFileService")
    private AttachFileService attachFileService;
    
    @Resource(name = "accessLogService")
    private AccessLogService accessLogService;
    
    
    @Value(value="#{global['npay.shop.id']}")
	private String NPAY_SHOP_ID;	
    
    @Value(value="#{global['npay.certi.key']}")
	private String NPAY_CERTI_KEY;
    
    @Value(value="#{global['npay.order.api']}")
   	private String NPAY_ORDER_API;
    
    @Value(value="#{global['npay.order.url']}")
   	private String NPAY_ORDER_URL;
    
    @Value(value="#{global['npay.order.backUrl']}")
   	private String NPAY_ORDER_BACKURL;
    
    @Value(value="#{global['npay.wish.api']}")
   	private String NPAY_WISH_API;
    
    @Value(value="#{global['npay.wish.url']}")
   	private String NPAY_WISH_URL;
    
    

    /*
     * 목록을 조회한다.
     */
    public Map<String, Object> selectProductList(ProductVO productVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        Map<String,Object> mSession = multiUtil.getSessionInfo();

        if(productVO.getIsApi()){
			if((boolean)mSession.get("isLogin")){
    			productVO.setRgstSn((int)mSession.get("userSn"));
	            productVO.setRgstId((String)mSession.get("userId"));
    		}
		}
        
        //일반글
        productVO.setTotalRow(productDAO.selectProductCount(productVO));
        if(productVO.getTotalRow() > 0){
            mResult.put("list", productDAO.selectProductList(productVO));
        }
        
        mResult.put("searchInfo", productVO);

        return mResult;

    }
    
    /*
     * 구매를 하기위해 상품정보 조회
     */
    public Map<String, Object> selectProductOrderList(BuyProductVO buyProductVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();

        try{
	        if(buyProductVO.getPrdSn() != null && !buyProductVO.getPrdSn().isEmpty()){
	        	
	        	List<ProductVO> orderList = productDAO.selectProductOrderList(buyProductVO);
	        	
	        	for(ProductVO vo : orderList){
	        		for(int i = 0; i < buyProductVO.getPrdSn().size(); i++){
	        			if(buyProductVO.getPrdCnt() != null && !buyProductVO.getPrdCnt().isEmpty()){
		        			if(vo.getPrdSn() == Integer.valueOf(buyProductVO.getPrdSn().get(i))){
		        				vo.setQuantity(Integer.valueOf(buyProductVO.getPrdCnt().get(i)));
		        				break;
		        			}
	        			}else{
	        				vo.setQuantity(1);
	        			}
	        		}
	        	}
	        	
	        	mResult.put("list", orderList);
		    }else{
		        throw new IllegalArgumentException();
		    }
              
		}catch(Exception exception){          
		    throw processException("exception.error", exception);
		}
        
        mResult.put("searchInfo", buyProductVO);

        return mResult;

    }
    
    
    /*
     * 정보를 조회한다.
     */
    public Map<String, Object> selectProduct(ProductVO productVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        Map<String,Object> mSession = multiUtil.getSessionInfo();
        
        if(productVO.getIsApi()){
			if((boolean)mSession.get("isLogin")){
    			productVO.setRgstSn((int)mSession.get("userSn"));
	            productVO.setRgstId((String)mSession.get("userId"));
    		}
		}

//        productVO.setTotalRow(productDAO.selectProductCount(productVO));
        
        ProductVO productVO2 = productDAO.selectProduct(productVO);

        mResult.put("searchInfo", productVO);
        
        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(productVO2 == null){
            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
        }else{   
        	
            mResult.put("info", productVO2);
            
            if(productVO.getIsApi()){
            	//조회수 증가
        		productDAO.updateProductByBlcRct(productVO2);
        	}
            
            //첨부파일
            mResult.put("file", attachFileService.selectAttachFileList("PRODUCT", productVO2.getPrdSn()));
        }
        
        return mResult;

    }
    
    
    /*
     * 수정페이지 정보를 조회한다.
     */
    public Map<String, Object> selectProductByMod(ProductVO productVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        Map<String,Object> mSession = multiUtil.getSessionInfo();
            
        ProductVO productVO2 = productDAO.selectProduct(productVO);
        
        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(productVO2 == null){
            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
        }else{               
            mResult.put("info", productVO2);       
            
            //첨부파일
            mResult.put("file", attachFileService.selectAttachFileList("PRODUCT", productVO2.getPrdSn()));
        }
        
        mResult.put("searchInfo", productVO);         
                        
        return mResult;

    }
    
    
    /*
     * 정보를 등록한다.
     */
    public Map<String, Object> insertProduct(ProductVO productVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{
        	Map<String,Object> mSession = multiUtil.getSessionInfo();

	            
            productVO.setRgstSn((int)mSession.get("userSn"));
            productVO.setRgstId((String)mSession.get("userId"));
            
            if(!productVO.getExpsRgstDay().isEmpty()){
            	 productVO.setExpsRgstDtm(productVO.getExpsRgstDay().replaceAll("-", "") + productVO.getExpsRgstTime() + productVO.getExpsRgstMinute() + "00");
            }
            
            if(!productVO.getExpsFnhDay().isEmpty()){
            	 productVO.setExpsFnhDtm(productVO.getExpsFnhDay().replaceAll("-", "") + productVO.getExpsFnhTime() + productVO.getExpsFnhMinute() + "59");
            }
            
            Object resultObject = productDAO.insertProduct(productVO) ;
            
            if(resultObject.getClass() == Integer.class){
                productVO.setPrdSn((Integer)resultObject);
                
                /*
                 * 첨부등록
                 */
                mResult.put("uploadInfo", attachFileService.insertAttachFile(productVO, mSession));
                
                bStatus = true;
            }
            
            //보안로그            
            accessLogService.insertAccessLog(productVO, mSession);
            
        }catch(Exception exception){
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
    }
    
    /*
     * 정보를 수정한다.
     */
    public Map<String, Object> updateProduct(ProductVO productVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{    
            Map<String,Object> mSession = multiUtil.getSessionInfo();
            
        	productVO.setMdfySn((int)mSession.get("userSn"));
        	productVO.setMdfyId((String)mSession.get("userId"));
            
	    	if(!productVO.getExpsRgstDay().isEmpty()){
	    		productVO.setExpsRgstDtm(productVO.getExpsRgstDay().replaceAll("-", "") + productVO.getExpsRgstTime() + productVO.getExpsRgstMinute() + "00");
	        }
	    	 
	    	if(!productVO.getExpsFnhDay().isEmpty()){
	    		productVO.setExpsFnhDtm(productVO.getExpsFnhDay().replaceAll("-", "") + productVO.getExpsFnhTime() + productVO.getExpsFnhMinute() + "00");
	    	}
	    	 
    		if(productDAO.updateProduct(productVO) == 1){
	            bStatus = true;
	            mResult.put("uploadInfo", attachFileService.insertAttachFile(productVO, mSession));
	        }else{
	            throw new IllegalArgumentException();
	        }
        	
        	//보안로그            
            accessLogService.insertAccessLog(productVO, mSession);
        
        }catch(Exception exception){        
            bStatus = false;
            log.error(exception.getMessage());
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;

    }


    /*
     * 정보를 삭제한다.
     */
    public Map<String, Object> deleteProduct(ProductVO productVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
  
            productVO.setMdfySn((int)mSession.get("userSn"));
            productVO.setMdfyId((String)mSession.get("userId"));
     
            if(productVO.getPrdSn() != 0 || ( productVO.getDelSeq() != null && !productVO.getDelSeq().isEmpty() )){
	            if(productDAO.deleteProduct(productVO) > 0){
	                bStatus = true;
	                
	                //TODO 보안로그 => 여러개 삭제일때 어떻게 처리할것인지.             
	                accessLogService.insertAccessLog(productVO, mSession);
	            }else{
	                throw new IllegalArgumentException();
	            }
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
    
    
    /*
     * 정렬순서 변경
     * - For문으로 하나씩 던져줄것.
     */
    public Map<String, Object> updateProductBySort(ProductVO productVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	
            Map<String,Object> mSession = multiUtil.getSessionInfo();
     
            List<String> listSn = productVO.getDelSeq();
            
            for(int i = 0; i < listSn.size(); i++)
            {
            	ProductVO productVO2 = new ProductVO();
            	productVO2.setLgrpCd(productVO.getLgrpCd());
            	productVO2.setPrdSn(Integer.valueOf(listSn.get(i)));
            	productVO2.setStNo(i+1);
            	
	            if(productDAO.updateProductByStNo(productVO2) == 1){
	                bStatus = true;
	                accessLogService.insertAccessLog(productVO2, mSession);
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

    
    public Map<String, Object> updateProductBySellYn(ProductVO productVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
  
            productVO.setMdfySn((int)mSession.get("userSn"));
            productVO.setMdfyId((String)mSession.get("userId"));
     
            if(productDAO.updateProductBySellYn(productVO) > 0){
                bStatus = true;
                
                //TODO 보안로그 => 여러개 삭제일때 어떻게 처리할것인지.             
                accessLogService.insertAccessLog(productVO, mSession);
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
    
    
    
    /*
     * 구매를 하기위해 상품정보 조회
     */
    public Map<String, Object> selectProductNPayOrder(BuyProductVO buyProductVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();

        try{
	        if(buyProductVO.getPrdSn() != null && !buyProductVO.getPrdSn().isEmpty()){
	        	
	        	List<ProductVO> orderList = productDAO.selectProductOrderList(buyProductVO);
	        	
	        	for(ProductVO vo : orderList){
	        		for(int i = 0; i < buyProductVO.getPrdSn().size(); i++){
	        			if(buyProductVO.getPrdCnt() != null && !buyProductVO.getPrdCnt().isEmpty()){
		        			if(vo.getPrdSn() == Integer.valueOf(buyProductVO.getPrdSn().get(i))){
		        				vo.setQuantity(Integer.valueOf(buyProductVO.getPrdCnt().get(i)));
		        				break;
		        			}
	        			}else{
	        				vo.setQuantity(1);
	        			}
	        		}
	        	}
	        	
	        	int iTotalPrice = 0;
	        	Map<String, String> parameter = new HashMap<String, String>();
	        	
	        	parameter.put("SHOP_ID", NPAY_SHOP_ID);
	        	parameter.put("CERTI_KEY", NPAY_CERTI_KEY);
	        	parameter.put("RESERVE1", "");
	        	parameter.put("RESERVE2", "");
	        	parameter.put("RESERVE3", "");
	        	parameter.put("RESERVE4", "");
	        	parameter.put("RESERVE5", "");
	        	parameter.put("BACK_URL", NPAY_ORDER_BACKURL);
	        	
	        	for(ProductVO vo : orderList){
	        		//TODO : 주문번호를 넣어야 하나?
	        		parameter.put("ITEM_ID", Integer.toString(vo.getPrdSn()));
	        		parameter.put("ITEM_NAME", vo.getPrdTitl());
	        		parameter.put("ITEM_COUNT", Integer.toString(vo.getQuantity()));
	        		parameter.put("ITEM_UPRICE", Integer.toString(vo.getSalePrice()));
	        		parameter.put("ITEM_TPRICE", Integer.toString(vo.getSalePrice() * vo.getQuantity()));
	        		parameter.put("ITEM_OPTION", "");
	        		
	        		iTotalPrice += vo.getSalePrice() * vo.getQuantity();
	        	}
	        	
	        	//배송비
	        	int iDeliveryPrice = iTotalPrice > 70000 ? 0 : 3000;
	        	parameter.put("SHIPPING_TYPE", iTotalPrice > 70000 ? "FREE" : "PAYED");
	        	parameter.put("SHIPPING_PRICE", Integer.toString(iDeliveryPrice));
	        	parameter.put("NAVER_INFLOW_CODE", StringUtil.replaceNull(multiUtil.getCookieValue(httpServletRequest, "NA_CO")));
	        	
	        	//결제 금액
	        	parameter.put("TOTAL_PRICE", Integer.toString(iTotalPrice + iDeliveryPrice));
	        	
	        	mResult.put("npay", httpClientHelper.post(NPAY_ORDER_API, parameter));
	        	mResult.put("orderUrl", NPAY_ORDER_URL);
	        	mResult.put("shopId", parameter.get("SHOP_ID"));
	        	mResult.put("totalPrice", parameter.get("TOTAL_PRICE"));
		    }else{
		        throw new IllegalArgumentException();
		    }
              
		}catch(Exception exception){          
		    throw processException("exception.error", exception);
		}
        
        return mResult;

    }
    
    
    /*
     * 찜 하기위해 상품정보 조회
     */
    @SuppressWarnings("unchecked")
	public Map<String, Object> selectProductNPayWish(BuyProductVO buyProductVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();

        try{
	        if(buyProductVO.getPrdSn() != null && !buyProductVO.getPrdSn().isEmpty()){
	        	
	        	List<ProductVO> orderList = productDAO.selectProductOrderList(buyProductVO);
	        	
	        	ProductVO productVO = orderList.get(0);

	        	//상품 이미지
	        	String strImageLink = "https://www.viruscleanlab.com/resources/upload/PRODUCT/";

	        	Map<String, Object> productFile = attachFileService.selectAttachFileList("PRODUCT", productVO.getPrdSn());
	        	List<AttachFileVO> lAttachFileVO = (List<AttachFileVO>)productFile.get("list");
	        	
	        	String imageUrl = "";
        		String thumbUrl = "";
	        	for(AttachFileVO vo : lAttachFileVO){
	        		if("THUMB".equals(vo.getFilScnCd())){
	        			thumbUrl = strImageLink +  vo.getFilNm();
	        		}
	        		if("THUMB1".equals(vo.getFilScnCd())){
	        			imageUrl = strImageLink +  vo.getFilNm();
	        		}
	        	}
	        	
	        	Map<String, String> parameter = new HashMap<String, String>();
	        	parameter.put("SHOP_ID", NPAY_SHOP_ID);
	        	parameter.put("CERTI_KEY", NPAY_CERTI_KEY);
        		parameter.put("ITEM_ID", Integer.toString(productVO.getPrdSn()));
        		parameter.put("ITEM_NAME", productVO.getPrdTitl());
        		parameter.put("ITEM_DESC", productVO.getPrdSbc1());
        		parameter.put("ITEM_UPRICE", Integer.toString(productVO.getSalePrice()));
        		parameter.put("ITEM_IMAGE", imageUrl);
        		parameter.put("ITEM_THUMB", thumbUrl);
        		parameter.put("ITEM_URL", "https://www.viruscleanlab.com/shop/virus-clean-lab/view.vc?prdSn=" + productVO.getPrdSn());
        		
	        	mResult.put("npay", httpClientHelper.post(NPAY_WISH_API, parameter));
	        	mResult.put("wishUrl", NPAY_WISH_URL);
	        	mResult.put("shopId", parameter.get("SHOP_ID"));
	        	mResult.put("itemId", parameter.get("ITEM_ID"));
		    }else{
		        throw new IllegalArgumentException();
		    }
              
		}catch(Exception exception){          
		    throw processException("exception.error", exception);
		}
        
        return mResult;
    }
    
    @SuppressWarnings("unchecked")
	public StringBuilder selectProductNPayItemInfo(BuyProductVO buyProductVO) throws Exception {
        
    	StringBuilder itemXml = new StringBuilder();

        try{
	        if(buyProductVO.getITEM_ID() != null && !buyProductVO.getITEM_ID().isEmpty()){
	        	
	        	buyProductVO.setPrdSn(buyProductVO.getITEM_ID());
	        	
	        	List<ProductVO> orderList = productDAO.selectProductOrderList(buyProductVO);
	        	
	        	Map<String, String> parameter = new HashMap<String, String>();
	        	
	        	//상품 이미지
	        	String strImageLink = "https://www.viruscleanlab.com/resources/upload/PRODUCT/";
	        	
	        	for(ProductVO productVO : orderList){
	        		String imageUrl = "";
	        		String thumbUrl = "";
	        		
	        		Map<String, Object> productFile = attachFileService.selectAttachFileList("PRODUCT", productVO.getPrdSn());
		        	List<AttachFileVO> lAttachFileVO = (List<AttachFileVO>)productFile.get("list");
		        	for(AttachFileVO vo : lAttachFileVO){
		        		if("THUMB".equals(vo.getFilScnCd())){
		        			thumbUrl = strImageLink +  vo.getFilNm();
		        		}
		        		if("THUMB1".equals(vo.getFilScnCd())){
		        			imageUrl = strImageLink +  vo.getFilNm();
		        		}
		        	}
		        	
		        	itemXml.append("<item id=\"" + productVO.getPrdSn() + "\">");
		    		itemXml.append("<name><![CDATA[" + new String(productVO.getPrdTitl().getBytes() , "UTF-8") + "]]></name> ");
		    		itemXml.append("<url><![CDATA[https://www.viruscleanlab.com/shop/virus-clean-lab/view.vc?prdSn=" + productVO.getPrdSn() + "]]></url>");
		    		itemXml.append("<description><![CDATA[" + new String(productVO.getPrdTitl().getBytes() , "UTF-8") + "]]></description>");
		    		itemXml.append("<image><![CDATA[" + new String(imageUrl.getBytes(), "UTF-8")  + "]]></image>");
		    		itemXml.append("<thumb><![CDATA[" + new String(thumbUrl.getBytes(), "UTF-8")  + "]]></thumb>");
		    		itemXml.append("<price>" + productVO.getSalePrice() + "</price>");
		    		itemXml.append("<quantity>" + productVO.getQuantity() + "</quantity>");
//		    		itemXml.append("<options></options>");
		    		itemXml.append("<category>");
		    		itemXml.append("<first><![CDATA[SHOP]]></first>");
		    		itemXml.append("<second><![CDATA[" + new String(productVO.getLgrpCd().getBytes(), "UTF-8") + "]]></second>");
		    		itemXml.append("</category>");
		    		itemXml.append("</item>");
	        	}
		    }else{
		        throw new IllegalArgumentException();
		    }
              
		}catch(Exception exception){          
		    throw processException("exception.error", exception);
		}
        
        return itemXml;

    }
}
