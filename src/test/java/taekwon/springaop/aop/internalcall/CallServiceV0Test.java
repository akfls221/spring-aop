package taekwon.springaop.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import taekwon.springaop.aop.internalcall.aop.CallLogAspect;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Import(CallLogAspect.class)
class CallServiceV0Test {

    @Autowired
    CallServiceV0 serviceV0;

    @Test
    void external() {
        serviceV0.external(); //internal은 AOP 적용 불가
        // external()의 경우 proxy가 호출되지만, external()안에서 internal()을 호출할떄는 실제 target의 interanl을 호출함.(this.internal)이기 때문.

        /**
         * spring AOP의 한계
         * 스프링은 프록시 방식의 AOP를 사용하기 때문에 메서드의 내부호출에 프록시 적요이 불가능함.(실제 코드에 AOP를 직접 적용하는 AspectJ를 사용하면 이런 문제는 발생하지 않음)
         */
    }

    @Test
    void internal() {
        serviceV0.internal(); //이경우는 proxy에서 호출되는 것이기 때문에 AOP적용이 됨.
    }

}