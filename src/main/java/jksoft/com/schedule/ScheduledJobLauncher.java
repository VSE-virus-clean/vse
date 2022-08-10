package jksoft.com.schedule;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * 작업 스케줄정의 CLASS
 * </pre>
 *
 * @ClassName   : ScheduledJobLauncher.java
 * @Description : 작업 스케줄정의 CLASS
 * @author Jeong.Hyoung.Jae
 * @since 2016.1.1
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2016.1.1  Jeong.Hyoung.Jae     최초 생성
 * </pre>
 */

@Component("scheduledJobLauncher")
public class ScheduledJobLauncher {
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Resource(name="cacheDataManager")
    CacheDataManager cacheDataManager;
    
    /**
     * PUSH 예약발송
     * - 1분마다 실행
     *
     * @throws Exception
     */
//    @Async
//    @Scheduled(cron="0 1/1 * * * ?")
    public void syncSendPush() throws Exception{
    	log.info("====  [PUSH 예약발송]  ====");
//        cacheDataManager.mailGoldExtendMailing();
    }
    
    
    /**
     * 발송완료
     * - 오후 2시 
     * - 발송후 7일이 지나면 발송완료로 / SMS도 발송
     */
//  @Async
//  @Scheduled(cron="0 0 14 * * ?")
  public void syncDeliveryEnd() throws Exception{
  	log.info("====  [PUSH 예약발송]  ====");
//      cacheDataManager.mailGoldExtendMailing();
  }
}
