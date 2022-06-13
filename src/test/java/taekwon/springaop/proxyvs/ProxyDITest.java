package taekwon.springaop.proxyvs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import taekwon.springaop.aop.member.MemberService;
import taekwon.springaop.aop.member.MemberServiceImpl;
import taekwon.springaop.proxyvs.code.ProxyDiAspect;

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"})
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"})
@Import(ProxyDiAspect.class)
public class ProxyDITest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void go() {
        /**
         * memberServiceImpl에 주입되길 기대하는 타입은 'MemberServiceImpl.class 이지만 실제 넘어온 타입은 $Proxy(JDK 동적프록시)라서 타입 예외가 발생함.
         * memberService(Interface) = JDK Proxy 가성립
         * memberServiceImpl(구체 클래스) = JDK Proxy는 MemberService 인터페이스를 기반으로 만들어지기 때문에 MemberServiceImpl 타입에 대해서는 모름. 그래서 해당 타입에 주입이 불가능함.
         * JDK를 쓰는 경우 인터페이스에만 의존관계를 주입해야 함.
         * spring.aop.proxy-target-class=true로 할 경우 테스트가 성공하는 것을 볼 수 있음.
         *
         * [정ㄹ]
         * JDK 동적 프록시는 대상 객체인 'MemberServiceImpl' 타입에 의존관계를 주입할 수 없음.
         * CGLIB 프록시는 대상 객체인 'MemberServiceImpl' 타입에 의존관계를 주입할 수 있음.(또한 부모 타입인 Interface도 알고있음.)
         */
        log.info("memberService class ={}", memberService.getClass());
        log.info("memberServiceImpl class ={}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");

    }
}
