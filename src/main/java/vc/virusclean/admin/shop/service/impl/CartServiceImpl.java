package vc.virusclean.admin.shop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import jksoft.com.service.XAbstractService;
import jksoft.com.util.StringUtil;
import vc.virusclean.admin.shop.service.CartService;
import vc.virusclean.admin.shop.service.dao.CartDAO;
import vc.virusclean.admin.shop.vo.CartVO;

/**
 * <pre>
 * 정품등록관련	
 * </pre>
 * 
 * @ClassName   : CartServiceImpl.java
 * @Description : CartService 를 구현
 */
@Service("cartService")
public class CartServiceImpl extends XAbstractService implements CartService {

    @Resource(name="cartDAO")   
    private CartDAO cartDAO;

    /**
     * 비회원 카드아이디 조회
     * @return
     */
    public String getCartID(){
    	
    	String cartId = "";
    	
    	HttpServletRequest httpServletRequest = multiUtil.getHttpServletRequest();
		if(httpServletRequest.getSession().getAttribute("CART_ID") != null){   
			cartId = (String)httpServletRequest.getSession().getAttribute("CART_ID");
		}else{
			cartId = multiUtil.getCookieValue(httpServletRequest, "CART_ID");
		}
		
		if(StringUtil.isEmpty(cartId)){
			cartId = String.valueOf(StringUtil.randomString2() + "-" + System.currentTimeMillis());
		}
		
		httpServletRequest.getSession().setAttribute("CART_ID", cartId);
		
		multiUtil.setCookieValue(multiUtil.getHttpServletResponse(), "CART_ID", cartId, 7*24*60*60);
		
		
		return cartId;
    }
    
    /*
     * 목록을 조회한다.
     */
    @Override
    public Map<String, Object> selectList(CartVO cartVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        Map<String,Object> mSession = multiUtil.getSessionInfo();

        try{
        	List<CartVO> cartList = new ArrayList<CartVO>();
        	
			if((boolean)mSession.get("isLogin")){
				cartVO.setMbrSn((int)mSession.get("userSn"));
			}else{
				//세션 CART_ID 조회
				cartVO.setCartId(this.getCartID());
            }
	        
			cartList = cartDAO.selectList(cartVO);
	        cartVO.setTotalRow(cartList.size());
	        
            mResult.put("list", cartList);
            
        }catch(Exception exception){        
            log.error(exception.getMessage());
            throw processException("exception.error", exception);
        }
        
        mResult.put("searchInfo", cartVO);

        return mResult;

    }

    
    /*
     * 정보를 등록한다.
     */
    @Override
    public Map<String, Object> insertCart(CartVO cartVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{
        	Map<String,Object> mSession = multiUtil.getSessionInfo();

        	if((boolean)mSession.get("isLogin")){
				cartVO.setMbrSn((int)mSession.get("userSn"));
			}else{
				//세션 CART_ID 조회
				cartVO.setCartId(this.getCartID());
            }
            
        	/*
        	 * 중복상품이 없다면 추가
        	 * - 있다면 수량을 갱신
        	 */
        	if(cartDAO.selectProductCount(cartVO) == 0){
	            Object resultObject = cartDAO.insertCart(cartVO) ;
	            
	            if(resultObject.getClass() == Integer.class){
	                cartVO.setCartSn((Integer)resultObject);
	                bStatus = true;
	            }
        	}else {
        		cartDAO.updateProductCount(cartVO);
        		bStatus = true;
        	}
            
        }catch(Exception exception){
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
    }
    
    
    /*
     * 정보를 삭제한다.
     */
    @Override
    public Map<String, Object> deleteCart(CartVO cartVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	
            Map<String,Object> mSession = multiUtil.getSessionInfo();
  
            if((boolean)mSession.get("isLogin")){
				cartVO.setMbrSn((int)mSession.get("userSn"));
			}else{
				//세션 CART_ID 조회
				cartVO.setCartId(this.getCartID());
            }
     
            if(cartDAO.deleteCart(cartVO) > 0){
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

}
