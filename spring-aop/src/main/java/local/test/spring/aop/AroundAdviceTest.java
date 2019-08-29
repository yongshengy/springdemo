package local.test.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AroundAdviceTest {
    @Around("execution(* local.test.spring.aop.*.*(..))")
    public Object processTX(ProceedingJoinPoint pre) throws Throwable{
        System.out.println("开始事务.....");
//        pre.getArgs() //获取参数，可以对其进行修改
        Object obj = pre.proceed(new String[]{"被修改的参数"});
        System.out.println("结束事务.....");
        return obj + ",新增的内容";

    }
}
