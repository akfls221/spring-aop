package taekwon.springaop.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import taekwon.springaop.aop.internalcall.aop.CallLogAspect;

@Slf4j
@SpringBootTest
@Import(CallLogAspect.class)
class CallServiceV3Test {

    @Autowired
    CallServiceV3 serviceV3;

    @Test
    void external() {
        serviceV3.external();//internal 메서드를 별도의 클래스로 분리하여 주입받은 상태이기 때문에 AOP 적용 가능
    }
}