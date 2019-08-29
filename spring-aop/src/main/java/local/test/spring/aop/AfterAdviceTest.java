package local.test.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AfterAdviceTest {
    @After("execution(* local.test.spring.aop.*.*(..))")
    public void release(){
        System.out.println("Release .... ");
    }
}
