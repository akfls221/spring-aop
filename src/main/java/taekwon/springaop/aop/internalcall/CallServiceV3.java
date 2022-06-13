package taekwon.springaop.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV3 {

    /**
     * 구조를 변경(분리)
     * internal 부분을 별도의 클래스로 분리 (InternalService) 이후
     * 주입받아 실행
     */
    private final InternalService internalService;


    public CallServiceV3(InternalService internalService) {
        this.internalService = internalService;
    }


    public void external() {
        log.info("call external");
        internalService.internal();
    }


}
