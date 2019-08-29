package local.test.spring.aop;


/*
AOP 和AspectJ的关系
1. 是选择AOP还是Aspect
spring确实有自己的AOP。功能已经基本够用了，除非你的要在接口上动态代理或者方法拦截精确到getter和setter。这些都是写奇葩的需求，一般不使用。
2. 使用AOP时，是使用xml还是注解方式(@AspectJ)
1）如果使用xml方式，不需要任何额外的jar包。
2）如果使用@Aspect方式，你就可以在类上直接一个@Aspect就搞定，不用费事在xml里配了。但是这需要额外的jar包（ aspectjweaver.jar）。
因为spring直接使用AspectJ的注解功能，注意只是使用了它 的注解功能而已。并不是核心功能 ！！！

注意到文档上还有一句很有意思的话：文档说到 是选择spring AOP还是使用full aspectJ？
什么是full aspectJ？如果你使用"full aspectJ"。就是说你可以实现基于接口的动态代理，等等强大的功能。而不仅仅是aspectj的 注-解-功-能 ！！！

如果用full AspectJ。比如说Load-Time Weaving的方式 还 需要额外的jar包 spring-instrument.jar

当然，无论是使用spring aop还是 aspectj都需要aspectjweaver.jar spring-aop.jar这两个jar包。

 */

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect // 定义切面
public class BeforeAdviceTest {

    @Before("execution(* local.test.spring.aop.*.*(..))") //里面是切入点表达式
    public void authority(){
        System.out.println("模拟权限检查~~~~~~~");
    }

}
