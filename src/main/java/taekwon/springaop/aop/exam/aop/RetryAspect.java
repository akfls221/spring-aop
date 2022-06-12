package taekwon.springaop.aop.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import taekwon.springaop.aop.exam.annotation.Retry;

@Slf4j
@Aspect
public class RetryAspect {

    @Around("@annotation(retry)") //파라미터 형식으로 받아서 처리가능
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[retry] {} retry={}", joinPoint.getSignature(), retry);
        Object[] args = joinPoint.getArgs();

        int maxRetry = retry.value();
        Exception exceptionHolder = null;

        for(int retryCount =1 ; retryCount <= maxRetry; retryCount++){
            try {
                log.info("[retry] try count = {}/{}", retryCount, maxRetry);
                return joinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder = e;
            }
        }
        throw exceptionHolder;
    }
}
