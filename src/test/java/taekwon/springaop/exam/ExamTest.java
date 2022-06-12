package taekwon.springaop.exam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import taekwon.springaop.aop.exam.ExamService;
import taekwon.springaop.aop.exam.aop.TraceAspect;

@Slf4j
@SpringBootTest
@Import(TraceAspect.class)
public class ExamTest {

    @Autowired
    ExamService service;

    @Test
    void test() {
        for (int i = 0; i < 5; i++) {
            log.info("client request i = {}", i);
            service.request("data" + i);
        }
    }
}
