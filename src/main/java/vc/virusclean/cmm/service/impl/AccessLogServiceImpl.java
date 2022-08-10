package vc.virusclean.cmm.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import vc.virusclean.admin.auth.vo.AuthVO;
import vc.virusclean.admin.shop.vo.CouponMetaVO;
import vc.virusclean.admin.shop.vo.ProductVO;
import vc.virusclean.cmm.service.AccessLogService;
import vc.virusclean.cmm.service.dao.AccessLogDAO;
import vc.virusclean.cmm.vo.BoardVO;
import vc.virusclean.cmm.vo.UserBoardVO;
import jksoft.com.web.vo.AccessLogVO;
import jksoft.com.service.XAbstractService;
import jksoft.com.web.vo.AttachFileVO;
import jksoft.com.web.vo.SessionVO;


/**
 * <pre>
 * 보안로그 서비스 구현
 * </pre>
 */
@Service("accessLogService")
public class AccessLogServiceImpl extends XAbstractService implements AccessLogService {

    /**
     * AccessLogDAO class 선언 (AccessLogDAO Class Injection)
     * (AccessLogDAO)accessLogDAO
     */   
    @Resource(name="accessLogDAO")   
    private AccessLogDAO accessLogDAO;


    /*
     * 게시물 접속 로그를 조회한다.
     *
     * @see ket.cmm.service.AccessLogService#selectAccessLogList(ket.cmm.vo.AccessLogVO)
     */
    public Map<String, Object> selectAccessLogList(AccessLogVO accessLogVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();

        accessLogVO.setTotalRow(accessLogDAO.selectAccessLogCount(accessLogVO));

        //to-be
        mResult.put("list", accessLogDAO.selectAccessLogList(accessLogVO));
        
        mResult.put("searchInfo", accessLogVO);

        return mResult;

    }
    
    
    /*
     * 관리자의 접속 로그를 조회한다.
     *
     * @see ket.cmm.service.AccessLogService#selectUserAccessLogList(ket.cmm.vo.AccessLogVO)
     */
    public Map<String, Object> selectUserAccessLogList(AccessLogVO accessLogVO) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();

        mResult.put("list", accessLogDAO.selectUserAccessLogList(accessLogVO));
        
        mResult.put("searchInfo", accessLogVO);

        return mResult;

    }


    /*
     * 보안로그를 등록한다.
     * @see ket.cmm.service.AccessLogService#insertAccessLog(java.lang.Object, java.util.Map)
     */
    @Override
    public Map<String, Object> insertAccessLog(Object object, Map<String, Object> mSession) throws Exception {
        
        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        try{
            AccessLogVO accessLogVO = new AccessLogVO();
            
            if(object.getClass() == AuthVO.class)
            {
                //로그인
                AuthVO authVO = (AuthVO)object;
                
                accessLogVO.setMenuCd("LOGIN");
                accessLogVO.setMgrId(authVO.getMgrId());
                accessLogVO.setCnncIp((String)mSession.get("accIp"));          //아이피
                accessLogVO.setCnncMenuUrl((String)mSession.get("accUri"));     //접속URL
                
                if("Y".equals(authVO.getStCd()) && authVO.getPwErrOft() == 0){
                    accessLogVO.setWkScnCd("IS");         //작업코드
                }else{
                    accessLogVO.setWkScnCd("IF");         //작업코드
                }
            }
            else if(object.getClass() == AttachFileVO.class)
            {
                //파일 업로드/다운로드
                AttachFileVO attachFileVO = (AttachFileVO)object;
                
                accessLogVO.setCotnSn(attachFileVO.getAttcFilSn());             //파일의 시퀀스
                accessLogVO.setMgrSn((String)mSession.get("userSn"));
                accessLogVO.setCnncIp((String)mSession.get("accIp"));          //아이피
                accessLogVO.setCnncMenuUrl((String)mSession.get("accReferer") == null ? (String)mSession.get("accUri") : (String)mSession.get("accReferer"));     //접속URL
                accessLogVO.setFilCpc(attachFileVO.getFilCpc());
                accessLogVO.setFilNm(attachFileVO.getFilNm());
                accessLogVO.setWkScnCd(attachFileVO.getDnlOft() == 0 ? "FU" : "FD");         //작업코드
            }
            else if(object.getClass() == BoardVO.class || object.getClass() == ProductVO.class
            		|| object.getClass() == UserBoardVO.class)
            {
            	String strWkScnCd = "UN";            	
                String strWkScnDesc = "알수없음";
                String strMenuCd = "";
                int iSn = 0;

                if(object.getClass() == BoardVO.class){
	            	BoardVO boardVO = (BoardVO)object;
	            	iSn = boardVO.getBlcSn();
	            	strMenuCd = boardVO.getLgrpCd();
                }else if(object.getClass() == ProductVO.class){
                	ProductVO productVO = (ProductVO)object;
	            	iSn = productVO.getPrdSn();
	            	strMenuCd = "PRODUCT";
                }else if(object.getClass() == UserBoardVO.class){
                	UserBoardVO userBoardVO = (UserBoardVO)object;
	            	iSn = userBoardVO.getBlcSn();
	            	strMenuCd = userBoardVO.getLgrpCd();
                }else if(object.getClass() == CouponMetaVO.class){
                	CouponMetaVO couponMetaVO = (CouponMetaVO)object;
	            	iSn = couponMetaVO.getCupMetaSn();
	            	strMenuCd = "COUPON";
                }
            	
            	accessLogVO.setMenuCd(strMenuCd);
                accessLogVO.setCotnSn(iSn);             //파일의 시퀀스
                accessLogVO.setMgrSn((String)mSession.get("userSn"));
                accessLogVO.setCnncIp((String)mSession.get("accIp"));          //아이피
                accessLogVO.setCnncMenuUrl((String)mSession.get("accReferer") == null ? (String)mSession.get("accUri") : (String)mSession.get("accReferer"));     //접속URL
                
                if(accessLogVO.getCnncMenuUrl().indexOf("modify") > 0){
                    strWkScnCd = "DU";
                    strWkScnDesc = "데이터 수정";
                }else if(accessLogVO.getCnncMenuUrl().indexOf("sort") > 0){
                    strWkScnCd = "DS";
                    strWkScnDesc = "정렬순서 변경";
                }else if(accessLogVO.getCnncMenuUrl().indexOf("delete") > 0){
                    strWkScnCd = "DD";
                    strWkScnDesc = "데이터 삭제(비노출)";
                }else if(accessLogVO.getCnncMenuUrl().indexOf("register") > 0){
                    strWkScnCd = "DC";
                    strWkScnDesc = "데이터 생성";
                }else if(accessLogVO.getCnncMenuUrl().indexOf("xls") > 0){
                    strWkScnCd = "XL";
                    strWkScnDesc = "엑셀다운로드";
                }
                
                accessLogVO.setWkScnCd(strWkScnCd);         //작업코드
                
                log.info(accessLogVO.getMgrId() + " :: , IP : "+ accessLogVO.getCnncIp() +",  URL : "+ accessLogVO.getCnncMenuUrl() 
                			+",  MENUCODE : "+ accessLogVO.getMenuCd() +",  Content Seq : "+ accessLogVO.getCotnSn() +",  Job : "+ strWkScnDesc);
                
            }
            else if(object.getClass() == String.class && "LOGOUT".equals(object))
            {
                //로그아웃
                accessLogVO.setMenuCd("LOGOUT");
                accessLogVO.setMgrSn((String)mSession.get("userSn"));
                accessLogVO.setCnncIp((String)mSession.get("accIp"));          //아이피
                accessLogVO.setCnncMenuUrl((String)mSession.get("accUri"));     //접속URL
                accessLogVO.setWkScnCd("IO");         //작업코드
            }
            else
            {
                throw new IllegalArgumentException();
            }
            
            
            accessLogDAO.insertAccessLog(accessLogVO) ;
            
            bStatus = true;
        
        }catch(Exception exception){
            bStatus = false;
            leaveaTrace("exception.failedLog");
        }
        
        mResult.put("status", bStatus);
        
        
        return mResult;
    }

    
    /*
     * @see glovis.common.service.AccessLogService#insertAccessLog(xeogen.common.vo.SessionVO, java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unused")
    @Override
    public Map<String, Object> insertAccessLog(SessionVO sessionVO, String strRequestURI, String strMethod) {

        Map<String, Object> mResult = new HashMap<String, Object>();
        
        boolean bStatus = false; 
        
        String strWkScnCd = "UN";
        String strWkScnDesc = "알수없음";
        
        try{
            
            AccessLogVO accessLogVO = new AccessLogVO();
            accessLogVO.setMgrId(sessionVO.getId());
            accessLogVO.setCnncIp(sessionVO.getIpAddress());            //아이피
            accessLogVO.setCnncMenuUrl(strRequestURI);                  //접속URL
            accessLogVO.setMenuCd(sessionVO.getMenuCd());               //메뉴코드
            accessLogVO.setCotnSn(sessionVO.getCotnSn());               //게시물 시퀀스

            /**
             * 접속 메뉴 권한 확인
             */
            if(true){
                if(strRequestURI.indexOf("List") > 0 || strRequestURI.indexOf("Det") > 0 || (strRequestURI.indexOf("Mod") > 0 && "GET".equals(strMethod)) 
                        || (strRequestURI.indexOf("Reg") > 0 && "GET".equals(strMethod)) || (strRequestURI.indexOf("Man") > 0 && "GET".equals(strMethod))){
                    strWkScnCd = "DR";
                    strWkScnDesc = "데이터 보기";
                }else if(strRequestURI.indexOf("Mod") > 0){
                    strWkScnCd = "DU";
                    strWkScnDesc = "데이터 수정";
                }else if(strRequestURI.indexOf("Del") > 0){
                    strWkScnCd = "DD";
                    strWkScnDesc = "데이터 삭제(비노출)";
                }else if(strRequestURI.indexOf("Reg") > 0){
                    strWkScnCd = "DC";
                    strWkScnDesc = "데이터 생성";
                }else if(strRequestURI.indexOf("Xls") > 0){
                    strWkScnCd = "XL";
                    strWkScnDesc = "엑셀다운로드";
                }
                
                accessLogVO.setWkScnCd(strWkScnCd);         //작업코드
            }else{
                accessLogVO.setWkScnCd("NA");               //작업코드
                strWkScnDesc = "권한없는 메뉴 접근";
            }
            
            log.info( sessionVO.getName() + "(" + accessLogVO.getMgrId() + ") :: , IP : "+ accessLogVO.getCnncIp() +",  URL : "+ accessLogVO.getCnncMenuUrl() 
                    +",  MENUCODE : "+ accessLogVO.getMenuCd() +",  Content Seq : "+ accessLogVO.getCotnSn() +",  Job : "+ strWkScnDesc);
            
            accessLogDAO.insertAccessLog(accessLogVO) ;
            
            bStatus = true;
        
        }catch(Exception exception){
            bStatus = false;
            leaveaTrace("exception.failedLog");
        }
        
        mResult.put("status", bStatus);
            
        return mResult;
    }


}
