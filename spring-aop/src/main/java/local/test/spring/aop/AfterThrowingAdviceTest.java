package local.test.spring.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AfterThrowingAdviceTest {
    @AfterThrowing(
            throwing="th", pointcut="execution(* local.test.spring.aop.*.*(..))")
    public void doRecoverActions(Throwable th){
        System.out.println("####th = [" + th + "]");
        System.out.println("模拟异常处理。。。");
    }
}
