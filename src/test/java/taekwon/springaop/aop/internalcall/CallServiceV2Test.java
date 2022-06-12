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
class CallServiceV2Test {

    @Autowired
    CallServiceV2 serviceV2;

    @Test
    void external() {
        serviceV2.external();//지연조회를 통해 실행(ObjectProvider를 주입받아 getBean을 통해 처리)
    }

}