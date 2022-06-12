package taekwon.springaop.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

    /**
     * 지연 조회
     * applicationContext 를 주입받아 getBean을 통해 처리
     * 즉 지연해서 callService2를 꺼내는 것으로 지연 조회라고 함.
     * 그러나 applicationContext의 모든 기능을 사용할 필요는 없기때문에
     * ObjectProvider를 통해 필요한 기능만 사용함.
     * ObjectProvider를 스프링에게 주입받은 다음 getObject 메서드를 사용 시 스프링 컨테이너에게 해당 빈을 요청하여 받아올 수 있다.
     * > 객체를 스프링 컨테이너에서 조회하는 것을 스프링 빈 생성 시점이 아닌 실제 객체를 사용하는 시점으로 지연이 가능함.
     * callServiceProvider.getObject(); 시점에 스프링 컨테이너에서 빈을 조회함.
     */
//    private final ApplicationContext applicationContext;

    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceProvider) {
        this.callServiceProvider = callServiceProvider;
    }

    public void external() {
        log.info("call external");
//        CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);
        CallServiceV2 callServiceV2 = callServiceProvider.getObject();
        callServiceV2.internal(); // 자기자신을 주입하여 this가 아닌 callServiceV1으로  메서드 호출(this.internal())
    }

    public void internal() {
        log.info("call internal");
    }
}
