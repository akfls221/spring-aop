package taekwon.springaop.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    /**
     * 자기자신 주입
     * 생성자 주입을 할 경우 순환참조 오류가 발생함.
     * 대안으로 setter를 사용하여 의존관계를 주입함.
     * 이렇게되면 Spring에서 proxy로 callserviceV1을 의존관계주입함.
     * 추가로 2.6 릴리즈 부터 순환참조가 기본적으로 금지이기 때문에 아래의 문구를 properties파일에 추가가 필요함.
     * spring.main.allow-circular-references=true
     */
    private CallServiceV1 callServiceV1;

    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        log.info("callServiceV1 setter={}", callServiceV1.getClass()); //Proxy로 주입됨.
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        callServiceV1.internal(); // 자기자신을 주입하여 this가 아닌 callServiceV1으로  메서드 호출(this.internal())
    }

    public void internal() {
        log.info("call internal");
    }
}
