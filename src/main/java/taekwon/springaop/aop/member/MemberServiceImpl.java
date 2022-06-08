package taekwon.springaop.aop.member;

import org.springframework.stereotype.Component;
import taekwon.springaop.aop.member.annotation.ClassAop;
import taekwon.springaop.aop.member.annotation.MethodAop;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService{

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
