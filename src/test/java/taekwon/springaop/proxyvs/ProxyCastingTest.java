package taekwon.springaop.proxyvs;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import taekwon.springaop.aop.member.MemberService;
import taekwon.springaop.aop.member.MemberServiceImpl;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl(); // 구체클래스와 인터페이스 모두 있음.
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // JDK 동적 프록시

        //프록시를 인터페이스로 캐스팅 성공
        Object memberServiceProxy = (MemberService) proxyFactory.getProxy();

        //JDK 동적 프록시를 구현 클래스로 캐스팅 시도 실패, ClassCastException 예외 발생
        //위의 memberServiceProxy는 인터페이스를 기반으로 생성된 프록시이기 때문에 구현체로 캐스팅 불가(Impl에 대한 존재를 모름)
        Assertions.assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
        });

    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl(); // 구체클래스와 인터페이스 모두 있음.
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // CGLIB 동적 프록시

        //프록시를 인터페이스로 캐스팅 성공
        Object memberServiceProxy = (MemberService) proxyFactory.getProxy();

        //JDK 동적 프록시를 구현 클래스로 캐스팅 성공
        //위의 memberServiceProxy는 실제 구현클래스를 기반으로 생성된 프록시이기 때문에 구현체로 캐스팅 가능, 인터페이스로 캐스팅 또한 가능
        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;

    }
}
