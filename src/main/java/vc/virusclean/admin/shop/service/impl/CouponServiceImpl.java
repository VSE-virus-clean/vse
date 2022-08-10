package vc.virusclean.admin.shop.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import jksoft.com.exception.LeaveaTraceException;
import jksoft.com.exception.NoResourceException;
import jksoft.com.service.XAbstractService;
import jksoft.com.util.DateUtil;
import jksoft.com.util.MailSenderUtil;
import jksoft.com.util.StringUtil;
import vc.virusclean.admin.shop.service.CouponService;
import vc.virusclean.admin.shop.service.dao.CouponDAO;
import vc.virusclean.admin.shop.service.dao.ProductDAO;
import vc.virusclean.admin.shop.vo.CouponMetaVO;
import vc.virusclean.admin.shop.vo.CouponVO;
import vc.virusclean.cmm.service.AccessLogService;
import vc.virusclean.cmm.service.AttachFileService;

/**
 * <pre>
 * 쿠폰 서비스 구현	
 * </pre>
 * 
 * @ClassName   : CouponServiceImpl.java
 * @Description : CouponService 를 구현
 */
@Service("couponService")
public class CouponServiceImpl extends XAbstractService implements CouponService {

	@Resource(name="mailSenderUtil")
	private MailSenderUtil mailSenderUtil;
	
    @Resource(name="productDAO")   
    private ProductDAO productDAO;
    
    @Resource(name="couponDAO")   
    private CouponDAO couponDAO;
    
    @Resource(name="attachFileService")
    private AttachFileService attachFileService;
    
    @Resource(name = "accessLogService")
    private AccessLogService accessLogService;


    /*
     * 쿠폰메다데이터의 목록을 조회한다.
     */
    public Map<String, Object> selectCouponMetaList(CouponMetaVO couponMetaVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();

        couponMetaVO.setTotalRow(couponDAO.selectCouponMetaCount(couponMetaVO));
        if(couponMetaVO.getTotalRow() > 0){
            mResult.put("list", couponDAO.selectCouponMetaList(couponMetaVO));
        }
        
        mResult.put("searchInfo", couponMetaVO);

        return mResult;
    }
    
    
    /*
     * 정보를 조회한다.
     *
     */
    public Map<String, Object> selectCouponMeta(CouponMetaVO couponMetaVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();

        couponMetaVO.setTotalRow(couponDAO.selectCouponMetaCount(couponMetaVO));
        
        CouponMetaVO couponMetaVO2 = couponDAO.selectCouponMeta(couponMetaVO);

        mResult.put("searchInfo", couponMetaVO);
        
        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(couponMetaVO2 == null){
            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
        }else{   
        	
            mResult.put("info", couponMetaVO2);
            
            //첨부파일
            mResult.put("file", attachFileService.selectAttachFileList("COUPON", couponMetaVO2.getCupMetaSn()));
        }
        
        return mResult;

    }
    
    
    /*
     * 수정페이지 정보를 조회한다.
     */
    public Map<String, Object> selectCouponMetaByMod(CouponMetaVO couponMetaVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
            
        CouponMetaVO couponMetaVO2 = couponDAO.selectCouponMeta(couponMetaVO);
        
        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(couponMetaVO2 == null){
            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
        }else{               
            mResult.put("info", couponMetaVO2);       
            
            //첨부파일
            mResult.put("file", attachFileService.selectAttachFileList("COUPON", couponMetaVO2.getCupMetaSn()));
        }
        
        mResult.put("searchInfo", couponMetaVO);         
                        
        return mResult;

    }
    
    
    /*
     * 정보를 등록한다.
     */
    public Map<String, Object> insertCouponMeta(CouponMetaVO couponMetaVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{
        	Map<String,Object> mSession = multiUtil.getSessionInfo();

            couponMetaVO.setRgstSn((int)mSession.get("userSn"));
            couponMetaVO.setRgstId((String)mSession.get("userId"));
            
            Object resultObject = couponDAO.insertCouponMeta(couponMetaVO) ;
            
            if(resultObject.getClass() == Integer.class){
                couponMetaVO.setCupMetaSn((Integer)resultObject);
                
                /*
                 * 첨부등록
                 */
                mResult.put("uploadInfo", attachFileService.insertAttachFile(couponMetaVO, mSession));
                
                bStatus = true;
            }
            
            //보안로그            
            accessLogService.insertAccessLog(couponMetaVO, mSession);
            
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
    public Map<String, Object> updateCouponMeta(CouponMetaVO couponMetaVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{    
            Map<String,Object> mSession = multiUtil.getSessionInfo();
            
        	couponMetaVO.setMdfySn((int)mSession.get("userSn"));
        	couponMetaVO.setMdfyId((String)mSession.get("userId"));
            
    		if(couponDAO.updateCouponMeta(couponMetaVO) == 1){
	            bStatus = true;
	            mResult.put("uploadInfo", attachFileService.insertAttachFile(couponMetaVO, mSession));
	        }else{
	            throw new IllegalArgumentException();
	        }
        	
        	//보안로그            
            accessLogService.insertAccessLog(couponMetaVO, mSession);
        
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
    public Map<String, Object> deleteCouponMeta(CouponMetaVO couponMetaVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
  
            couponMetaVO.setMdfySn((int)mSession.get("userSn"));
            couponMetaVO.setMdfyId((String)mSession.get("userId"));
     
            if(couponMetaVO.getCupMetaSn() != 0 || ( couponMetaVO.getDelSeq() != null && !couponMetaVO.getDelSeq().isEmpty() )){
	            if(couponDAO.deleteCouponMeta(couponMetaVO) > 0){
	                bStatus = true;
	                
	                //TODO 보안로그 => 여러개 삭제일때 어떻게 처리할것인지.             
	                accessLogService.insertAccessLog(couponMetaVO, mSession);
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
    
    

    /** *********************************************************************** **
     *  사용자 발급 쿠폰
     ** *********************************************************************** **/
    /*
     * 정보를 등록한다.
     * - LIST로 넘어올수도 있음.
     */
    public Map<String, Object> insertCouponUser(CouponVO couponVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{
        	Map<String,Object> mSession = multiUtil.getSessionInfo();
        	
        	if(couponVO.getIsApi())
        	{
        		couponVO.setMbrSn((int)mSession.get("userSn"));
        		
        		/**
            	 * TODO 쿠폰번호 생성 및 유효기간 처리해야함.
            	 */
        		
        		Object resultObject = couponDAO.insertCouponUser(couponVO);
                
                if(resultObject.getClass() == Integer.class){
                	couponVO.setCupSn((Integer)resultObject);
                    bStatus = true;
                }
        	}
        	else
        	{
        		//관리자는 LIST로 넘어옴.
        		for(String mbrSn : couponVO.getDelSeq()){
        			CouponVO vo = new CouponVO();
        			vo.setMbrSn(Integer.valueOf(mbrSn));
        			
        			/**
                	 * TODO 쿠폰번호 생성 및 유효기간 처리해야함.
                	 */
        			
        			Object resultObject = couponDAO.insertCouponUser(vo);
        			if(resultObject.getClass() == Integer.class){
        				vo.setCupSn((Integer)resultObject);
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
     * 사용자 발급 쿠폰 사용
     */
    public Map<String, Object> updateCouponUserUsed(CouponVO couponVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
            couponVO.setMbrSn((int)mSession.get("userSn"));
     
            if(couponVO.getCupSn() > 0 || !StringUtil.isEmpty(couponVO.getCupNo())){
	            if(couponDAO.updateCouponUserUsed(couponVO) > 0){
	                bStatus = true;
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
     * 사용자 발급 쿠폰 갯수를 조회한다. : 관리자용
     */
    public Map<String, Object> selectCouponAdmList(CouponVO couponVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();

        couponVO.setTotalRow(couponDAO.selectCouponAdmCount(couponVO));
        if(couponVO.getTotalRow() > 0){
            mResult.put("list", couponDAO.selectCouponAdmList(couponVO));
        }
        
        mResult.put("searchInfo", couponVO);

        return mResult;
    }
    
    /*
     * 사용자 발급 쿠폰 갯수를 조회한다. : 사용자용
     */
    public Map<String, Object> selectCouponUserList(CouponVO couponVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();

        Map<String,Object> mSession = multiUtil.getSessionInfo();
        
        couponVO.setMbrSn((int)mSession.get("userSn"));
        
        mResult.put("list", couponDAO.selectCouponUserList(couponVO));
        
        mResult.put("searchInfo", couponVO);

        return mResult;
    }
    
    /*
     * 다운로드 가능한 쿠폰 목록 을 조회한다. : 사용자
     * - 이미 다운받은 쿠폰은 제외한다.
     */
    public Map<String, Object> selectEventCouponList() throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        Map<String,Object> mSession = multiUtil.getSessionInfo();
        
        CouponVO couponVO = new CouponVO();
        couponVO.setMbrSn((int)mSession.get("userSn"));

        mResult.put("list", couponDAO.selectEventCouponList(couponVO));
        
        mResult.put("searchInfo", couponVO);

        return mResult;
    }
    
    
    /*
     * 사용자가 쿠폰번호를 등록한다.
     * - 쿠폰번호 인증 해야함.
     */
    public Map<String, Object> insertCouponUserRegister(CouponVO couponVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{
        	Map<String,Object> mSession = multiUtil.getSessionInfo();
        	
        	//VC_SHOP_06 에서 쿠폰번호가 일치하고. mbr_sn이 없는것을 찾아야함.
        	CouponVO couponVO2 = couponDAO.selectCouponUserEmptyInfo(couponVO);
        	
        	if(couponVO2 != null){
        		couponVO2.setMbrSn((int)mSession.get("userSn"));
        		
	            if(couponDAO.updateCouponUserEmpty(couponVO2) == 1){
	                bStatus = true;
	            }else{
	                throw new LeaveaTraceException("업데이트 오류");
	            }
        	}else{
        		throw new LeaveaTraceException(xMessageSource.getMessage("exception.nodata"));   
    		}
        	
        }catch(Exception exception){
            bStatus = false;
            leaveaTrace(exception.getMessage());
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
    }
    
    /*
     * 사용자 다룬로드 쿠폰 등록
     */
    public Map<String, Object> insertCouponUserDownload(CouponVO couponVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{
        	Map<String,Object> mSession = multiUtil.getSessionInfo();
        	couponVO.setMbrSn((int)mSession.get("userSn"));
        	
        	//입력한 쿠폰번호가 있는지 확인해야함.
        	CouponMetaVO couponMetaVO = new CouponMetaVO();
        	couponMetaVO.setCupMetaSn(couponVO.getCupMetaSn());
        	
        	CouponMetaVO couponMetaVO2 = couponDAO.selectCouponMeta(couponMetaVO);
        	
        	if(couponMetaVO2 != null){
        		
        		couponVO.setLgrpCd(couponMetaVO2.getLgrpCd());
        		couponVO.setMgrpCd(couponMetaVO2.getMgrpCd());
        		couponVO.setSgrpCd(couponMetaVO2.getSgrpCd());
        		couponVO.setCupTitl(couponMetaVO2.getCupTitl());
        		couponVO.setCupSbc(couponMetaVO2.getCupSbc());
        		couponVO.setPrice(couponMetaVO2.getPrice());
        		couponVO.setCupNo(RandomStringUtils.randomAlphanumeric(10) + couponMetaVO2.getCupMetaSn());
        		
        		if(couponMetaVO2.getExpsDay() == 0){
        			couponVO.setExpsFnhDtm(couponMetaVO2.getExpsFnhDtm());
        		}else{
        			couponVO.setExpsFnhDtm(DateUtil.addDay(DateUtil.getToday(), couponMetaVO2.getExpsDay()));
        		}
        		
	    		Object resultObject = couponDAO.insertCouponUser(couponVO);
	            
	            if(resultObject.getClass() == Integer.class){
	            	couponVO.setCupSn((Integer)resultObject);
	                bStatus = true;
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
     *  관리자가 사용자에게 쿠폰을 지급한다.
     */
    public Map<String, Object> insertCouponAdmForUserRegister(CouponMetaVO couponMetaVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = true; 
        
        try{
        	Map<String,Object> mSession = multiUtil.getSessionInfo();
        	
        	couponMetaVO.setMdfySn((int)mSession.get("userSn"));
        	couponMetaVO.setMdfyId((String)mSession.get("userId"));
        	
        	//쿠폰정보 확인해야함.
        	CouponMetaVO couponMetaVO2 = couponDAO.selectCouponMeta(couponMetaVO);
        	
        	if(couponMetaVO2 != null){
        		
        		for(String sn : couponMetaVO.getDelSeq())
        		{
	        		CouponVO couponVO = new CouponVO();
	        		couponVO.setCupMetaSn(couponMetaVO2.getCupMetaSn());
	        		couponVO.setMbrSn(Integer.valueOf(sn));
	        		couponVO.setLgrpCd(couponMetaVO2.getLgrpCd());
	        		couponVO.setMgrpCd(couponMetaVO2.getMgrpCd());
	        		couponVO.setSgrpCd(couponMetaVO2.getSgrpCd());
	        		couponVO.setCupTitl(couponMetaVO2.getCupTitl());
	        		couponVO.setCupSbc(couponMetaVO2.getCupSbc());
	        		couponVO.setPrice(couponMetaVO2.getPrice());
	        		couponVO.setCupNo(RandomStringUtils.randomAlphanumeric(10) + couponMetaVO2.getCupMetaSn());
	        		
	        		if(couponMetaVO2.getExpsDay() == 0){
	        			couponVO.setExpsFnhDtm(couponMetaVO2.getExpsFnhDtm());
	        		}else{
	        			couponVO.setExpsFnhDtm(DateUtil.addDay(DateUtil.getToday(), couponMetaVO2.getExpsDay()));
	        		}
	        		
		    		couponDAO.insertCouponUser(couponVO);
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

}
