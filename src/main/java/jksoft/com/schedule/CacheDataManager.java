package jksoft.com.schedule;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import vc.virusclean.admin.member.service.MemberService;
import vc.virusclean.admin.member.vo.MemberVO;




/**
 * <pre>
 * DATA캐싱 구현 CLASS
 * </pre>
 *
 * @ClassName   : CacheDataManager.java
 * @Description : 정보 캐싱
 * @author Jeong.Hyoung.Jae
 * @since 2016. 1. 14.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2016. 1. 14.     Jeong.Hyoung.Jae     최초 생성
 * </pre>
 */
@Component("cacheDataManager")
public class CacheDataManager {
    
    @SuppressWarnings("unused")
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Resource(name="memberService")
    private MemberService memberService;
    
    
    /**
     * 연장 안내 메일 발송
     */
	public void mailGoldExtendMailing() throws Exception {
        synchronized (this){
        	memberService.mailGoldExtendMailing(new MemberVO());
        }
    }
	
	/**
     * 기간만료 GOLD회원 처리 : 30일이상 지난회원
     */
	public void updateBySpecialMemberExpire() throws Exception {
        synchronized (this){
        	memberService.updateBySpecialMemberExpire(new MemberVO());
        }
    }
    
}
