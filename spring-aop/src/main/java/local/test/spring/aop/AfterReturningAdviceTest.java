package local.test.spring.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AfterReturningAdviceTest {
    @AfterReturning(pointcut = "execution(* local.test.spring.aop.*.*(..))",returning = "object")
    public void log(Object object){
        System.out.println("获取目标方法返回值"+object);
        System.out.println("模拟日志记录功能....");
    }
}
