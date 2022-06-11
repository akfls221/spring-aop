package taekwon.springaop.aop.order;

import org.aspectj.lang.annotation.Pointcut;

//이렇게 PointCut을 외부로 모듈화 할 경우 public으로 접근 가능하도록 해야함.
public class Pointcuts {
    @Pointcut("execution(* taekwon.springaop.aop.order..*(..))")//pointCut signature
    public void allOrder() {

    }

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")//pointCut signature
    public void allService() {

    }

    //allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService() {

    }
}
