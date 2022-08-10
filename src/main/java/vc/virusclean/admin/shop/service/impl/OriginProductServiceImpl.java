package vc.virusclean.admin.shop.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jksoft.com.exception.NoResourceException;
import jksoft.com.service.XAbstractService;
import jksoft.com.util.MailSenderUtil;
import vc.virusclean.admin.shop.service.OriginProductService;
import vc.virusclean.admin.shop.service.dao.OriginProductDAO;
import vc.virusclean.admin.shop.vo.OriginProductVO;
import vc.virusclean.cmm.service.AccessLogService;
import vc.virusclean.cmm.service.AttachFileService;

/**
 * <pre>
 * 정품등록관련	
 * </pre>
 * 
 * @ClassName   : OriginProductServiceImpl.java
 * @Description : OriginProductService 를 구현
 */
@Service("originProductService")
public class OriginProductServiceImpl extends XAbstractService implements OriginProductService {

	@Resource(name="mailSenderUtil")
	private MailSenderUtil mailSenderUtil;
	
    @Resource(name="originProductDAO")   
    private OriginProductDAO originProductDAO;
    
    @Resource(name="attachFileService")
    private AttachFileService attachFileService;
    
    @Resource(name = "accessLogService")
    private AccessLogService accessLogService;


    /*
     * 목록을 조회한다.
     */
    public Map<String, Object> selectProductList(OriginProductVO originProductVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        Map<String,Object> mSession = multiUtil.getSessionInfo();

        try{
	        if(originProductVO.getIndividual()){
		        if(originProductVO.getIsApi()){
					if((boolean)mSession.get("isLogin")){
						originProductVO.setMbrSn((int)mSession.get("userSn"));
					}else{
		                throw new IllegalArgumentException();
		            }
				}
	        }
	        
	        originProductVO.setTotalRow(originProductDAO.selectProductCount(originProductVO));
	        if(originProductVO.getTotalRow() > 0){
	            mResult.put("list", originProductDAO.selectProductList(originProductVO));
	        }
        }catch(Exception exception){        
            log.error(exception.getMessage());
            throw processException("exception.error", exception);
        }
        
        mResult.put("searchInfo", originProductVO);

        return mResult;

    }

    
    /*
     * 정보를 조회한다.
     *
     */
    public Map<String, Object> selectProduct(OriginProductVO originProductVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        Map<String,Object> mSession = multiUtil.getSessionInfo();
        
        try{
	        if(originProductVO.getIndividual()){
		        if(originProductVO.getIsApi()){
					if((boolean)mSession.get("isLogin")){
						originProductVO.setMbrSn((int)mSession.get("userSn"));
					}else{
		                throw new IllegalArgumentException();
		            }
				}
	        }
	        
	        originProductVO.setTotalRow(originProductDAO.selectProductCount(originProductVO));
	        
	        OriginProductVO originProductVO2 = originProductDAO.selectProduct(originProductVO);
	
	        mResult.put("searchInfo", originProductVO);
	        
	        /*
	         * 작업 성공여부에 따른 처리 
	         * 선택 게시물이 없으면 ResourceNotFound페이지로
	         */
	        if(originProductVO2 == null){
	            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
	        }else{   
	            mResult.put("info", originProductVO2);
	            
	            //첨부파일
	            mResult.put("file", attachFileService.selectAttachFileList("PRODUCT", originProductVO2.getPrdSn()));
	        }
        }catch(Exception exception){        
            log.error(exception.getMessage());
            throw processException("exception.error", exception);
        }
        
        return mResult;

    }
    
    
    /*
     * 수정페이지 정보를 조회한다.
     */
    public Map<String, Object> selectProductByMod(OriginProductVO originProductVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        Map<String,Object> mSession = multiUtil.getSessionInfo();
            
        OriginProductVO originProductVO2 = originProductDAO.selectProduct(originProductVO);
        
        /*
         * 작업 성공여부에 따른 처리 
         * 선택 게시물이 없으면 ResourceNotFound페이지로
         */
        if(originProductVO2 == null){
            throw new NoResourceException(xMessageSource.getMessage("exception.nodata"));                            
        }else{               
            mResult.put("info", originProductVO2);       
            
            //첨부파일
            mResult.put("file", attachFileService.selectAttachFileList("PRODUCT", originProductVO2.getPrdSn()));
        }
        
        mResult.put("searchInfo", originProductVO);         
                        
        return mResult;

    }
    
    
    /*
     * 정보를 등록한다.
     */
    public Map<String, Object> insertProduct(OriginProductVO originProductVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{
        	Map<String,Object> mSession = multiUtil.getSessionInfo();

	            
            originProductVO.setMbrSn((int)mSession.get("userSn"));
            
            Object resultObject = originProductDAO.insertProduct(originProductVO) ;
            
            if(resultObject.getClass() == Integer.class){
                originProductVO.setPrdSn((Integer)resultObject);
                
                /*
                 * 첨부등록
                 */
                mResult.put("uploadInfo", attachFileService.insertAttachFile(originProductVO, mSession));
                
                /*
                 * 승인
                 */
                originProductVO.setAplyYn("Y");
                originProductVO.setAplySbc("자동승인");
                originProductDAO.updateProductByAplyYn(originProductVO) ;
                
                bStatus = true;
            }
            
            //보안로그            
            accessLogService.insertAccessLog(originProductVO, mSession);
            
        }catch(Exception exception){
            bStatus = false;
            leaveaTrace(exception.getMessage());
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
    }
    
    /*
     * 정보를 수정한다.
     */
    public Map<String, Object> updateProduct(OriginProductVO originProductVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{    
            Map<String,Object> mSession = multiUtil.getSessionInfo();
            
        	originProductVO.setMdfySn((int)mSession.get("userSn"));
        	originProductVO.setMdfyId((String)mSession.get("userId"));
            
    		if(originProductDAO.updateProduct(originProductVO) == 1){
	            bStatus = true;
	            mResult.put("uploadInfo", attachFileService.insertAttachFile(originProductVO, mSession));
	        }else{
	            throw new IllegalArgumentException();
	        }
        	
        	//보안로그            
            accessLogService.insertAccessLog(originProductVO, mSession);
        
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
    public Map<String, Object> deleteProduct(OriginProductVO originProductVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
  
            originProductVO.setMdfySn((int)mSession.get("userSn"));
            originProductVO.setMdfyId((String)mSession.get("userId"));
     
            if(originProductDAO.deleteProduct(originProductVO) > 0){
                bStatus = true;
                
                //TODO 보안로그 => 여러개 삭제일때 어떻게 처리할것인지.             
                accessLogService.insertAccessLog(originProductVO, mSession);
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
    
    
    public Map<String, Object> updateProductByAplyYn(OriginProductVO originProductVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
  
            originProductVO.setMdfySn((int)mSession.get("userSn"));
            originProductVO.setMdfyId((String)mSession.get("userId"));
     
            if(originProductDAO.updateProductByAplyYn(originProductVO) > 0){
                bStatus = true;
                
                //TODO 보안로그 => 여러개 삭제일때 어떻게 처리할것인지.             
                accessLogService.insertAccessLog(originProductVO, mSession);
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
