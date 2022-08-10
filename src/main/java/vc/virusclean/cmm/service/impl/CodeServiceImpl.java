package vc.virusclean.cmm.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import jksoft.com.service.XAbstractService;
import jksoft.com.util.DateUtil;
import vc.virusclean.admin.member.vo.MemberVO;
import vc.virusclean.cmm.service.CodeService;
import vc.virusclean.cmm.service.dao.CodeDAO;
import vc.virusclean.cmm.vo.BoardVO;
import vc.virusclean.cmm.vo.CodeEnvVO;
import vc.virusclean.cmm.vo.CodeVO;

/**
 * <pre>
 * 공통코드 관리 서비스 구현
 * </pre>
 * 
 * @ClassName   : CodeServiceImpl.java
 * @Description : CodeService 를 구현
 */
@Service("codeService")
public class CodeServiceImpl extends XAbstractService implements CodeService {

    @Resource(name="codeDAO")   
    private CodeDAO codeDAO;


    /*
     * 부모공통코드로 하위 공통코드 목록을 조회한다.
     * 
     * @see vc.virusclean.cmm.service.CodeService#selectCodeList(java.lang.String)
     */
    @Override
    public Map<String, Object> selectCodeList(String strCodeCd) throws Exception {
        
        CodeVO codeVO = new CodeVO();
        
        codeVO.setPrenComCd(strCodeCd);
        
        return this.selectCodeList(codeVO);
    }
    
    /*
     * 부모공통코드로 하위 공통코드 목록을 조회한다.
     *
     * @see vc.virusclean.cmm.service.CodeService#selectCodeList(vc.virusclean.cmm.vo.CodeVO)
     */
    public Map<String, Object> selectCodeList(CodeVO codeVO) throws Exception {
        
        boolean bRtn = true;
        
        Map<String, Object> mResult = new HashMap<String, Object>();

        if(codeVO.getSelectCd() != null){
            codeVO.setlSelectCd(Arrays.asList(codeVO.getSelectCd().split(",")));
        }
        
        codeVO.setTotalRow(codeDAO.selectCodeCount(codeVO));

        if(codeVO.getTotalRow() > 0){
            bRtn = true;
            
            List<CodeVO> result = codeDAO.selectCodeList(codeVO);
            
            mResult.put("list", result);
        }
        else{
        	bRtn = false;
        }        	

        mResult.put("searchInfo", codeVO);
        
        mResult.put("status", bRtn);

        return mResult;

    }
    
    
    /*
     * 부모공통코드로 하위 공통코드 목록을 조회한다.
     */
    public Map<String, Object> selectCodeCateList(CodeVO codeVO) throws Exception {
        
        boolean bRtn = true;
        
        Map<String, Object> mResult = new HashMap<String, Object>();

            
        List<CodeVO> result = codeDAO.selectCodeCateList();
        
        codeVO.setTotalRow(result.size());
        
        mResult.put("list", result);

        mResult.put("searchInfo", codeVO);
        
        mResult.put("status", bRtn);

        return mResult;

    }
    
    
    /*
     * 코드에 대한 정보를 조회
     * @see vc.virusclean.cmm.service.CodeService#selectCode(java.lang.String)
     */
    @Override
    public CodeVO selectCode(String strCodeCd) throws Exception {
        
        CodeVO codeVO = new CodeVO();     
        codeVO.setComCd(strCodeCd);

        return this.selectCode(codeVO);
    }
    
    
    /*
     * 코드에 대한 정보를 조회
     * @see vc.virusclean.cmm.service.CodeService#selectCode(vc.virusclean.cmm.vo.CodeVO)
     */
    public CodeVO selectCode(CodeVO codeVO) throws Exception {
        
        Map<String,Object> mSession = multiUtil.getSessionInfo();
        codeVO.setSiteCd((String)mSession.get("siteCd"));
        
        CodeVO codeVO2 = codeDAO.selectCode(codeVO);
        
        return codeVO2;
    }


    /*
     * 정보를 수정한다.
     *
     * @see vc.virusclean.cmm.service.CodeService#updateCode(vc.virusclean.cmm.vo.CodeVO)
     */
    public Map<String, Object> updateCode(CodeVO codeVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{    

            Map<String,Object> mSession = multiUtil.getSessionInfo();

            codeVO.setMdfyId((String)mSession.get("userId"));

            if(codeDAO.updateCode(codeVO) == 1){
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

	@Override
	public Map<String, Object> selectCodePreferences() throws Exception {

		boolean bRtn = true;
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        mResult.put("info", codeDAO.selectCodePreferences());
 
        mResult.put("status", bRtn);

        return mResult;
	}
	
	/*
     * 정보를 수정한다.
     */
	@Override
    public Map<String, Object> updateCodePreference(CodeEnvVO codeVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = true; 
        
        try{    

            Map<String,Object> mSession = multiUtil.getSessionInfo();

            CodeVO vo = new CodeVO();
            vo.setMdfyId((String)mSession.get("userId"));
            
            if("SITE".equals(codeVO.getParentCd())){
            	vo.setComCd("SITE-NAME");
            	vo.setComCdNm(codeVO.getSiteName());
	            codeDAO.updateCode(vo);
	            
	            vo.setComCd("SITE-TITLE");
            	vo.setComCdNm(codeVO.getSiteTitle());
	            codeDAO.updateCode(vo);
	            
	            vo.setComCd("SITE-KEYWORD");
            	vo.setComCdNm(codeVO.getSiteKeyword());
	            codeDAO.updateCode(vo);
	            
	            vo.setComCd("SITE-DESC");
            	vo.setComCdNm(codeVO.getSiteDesc());
	            codeDAO.updateCode(vo);
            }
            
			if("MEMBER".equals(codeVO.getParentCd())){
				vo.setComCd("MEMBER-OUTID");
            	vo.setComCdNm(codeVO.getMemberOutid());
	            codeDAO.updateCode(vo);
	            
	            vo.setComCd("MEMBER-OUTDEL");
            	vo.setComCdNm(codeVO.getMemberOutdel());
	            codeDAO.updateCode(vo);
			}
			
			if("COUPON".equals(codeVO.getParentCd())){
            	vo.setComCd("COUPON-YN");
            	vo.setComCdNm(codeVO.getCouponYn());
	            codeDAO.updateCode(vo);
	            
	            vo.setComCd("COUPON-WELCOM");
            	vo.setComCdNm(codeVO.getCouponWelcom());
	            codeDAO.updateCode(vo);
			}
			
			if("DELIVERY".equals(codeVO.getParentCd())){
				vo.setComCd("DELIVERY-YN");
            	vo.setComCdNm(codeVO.getDeliveryYn());
	            codeDAO.updateCode(vo);
	            
	            vo.setComCd("DELIVERY-MIN");
            	vo.setComCdNm(codeVO.getDeliveryMin());
	            codeDAO.updateCode(vo);
	            
	            vo.setComCd("DELIVERY-FREE");
            	vo.setComCdNm(codeVO.getDeliveryFree());
	            codeDAO.updateCode(vo);
	            
	            vo.setComCd("DELIVERY-DATE");
            	vo.setComCdNm(codeVO.getDeliveryDate());
	            codeDAO.updateCode(vo);
			}
            
        
        }catch(Exception exception){        
            bStatus = false;
            throw processException("exception.error", exception);
        }
        
        mResult.put("status", bStatus);
            
        return mResult;

    }
	
	/**
	 * 코드를 생성한다.
	 */
	public Map<String, Object> insertCode(CodeVO codeVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = true; 
        
        try{
        	Map<String,Object> mSession = multiUtil.getSessionInfo();
        	codeVO.setRgstId((String)mSession.get("userId"));

        	if( "PRODUCT-CATE".equals(codeVO.getPrenComCd()) ){
        		Object resultObject = codeDAO.insertCodeCate1(codeVO) ;
        		
        		if(resultObject.getClass() == Integer.class){
        			bStatus = true;
        		}
        	}else{
    			Object resultObject = codeDAO.insertCodeCate2(codeVO) ;
        		
        		if(resultObject.getClass() == Integer.class){
        			bStatus = true;
        		}
	            
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
    public Map<String, Object> deleteCode(CodeVO codeVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	

            Map<String,Object> mSession = multiUtil.getSessionInfo();
            codeVO.setMdfyId((String)mSession.get("userId"));
            codeVO.setUseYn("D");
     
            if(codeVO.getComCdSn() != 0 || ( codeVO.getDelSeq() != null && !codeVO.getDelSeq().isEmpty() )){
	            if(codeDAO.updateUseYn(codeVO) > 0){
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
    
    
    public Map<String, Object> updateCodeBySort(CodeVO codeVO) throws Exception {
    	
    	Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{  	
            List<String> listSn = codeVO.getDelSeq();
            
            for(int i = 0; i < listSn.size(); i++)
            {
            	CodeVO codeVO2 = new CodeVO();
            	codeVO2.setComCdSn(Integer.valueOf(listSn.get(i)));
            	codeVO2.setStNo(i+1);
            	
	            if(codeDAO.updateCategoryCodeByStNo(codeVO2) == 1){
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
    
}
