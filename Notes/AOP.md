# AOP

### 介绍

- AOP：面向切面编程，目的是解耦合一些通用业务。通过动态代理的方式实现对目标对象的功能扩展。
- 名词：切面（Aspect），里面有Adivce（通知/增强），配置时关注JointPoint,也就是PointCut定义
- 动态代理：代理的类是动态生成的。静态代理是和实际的接口关联紧密。
- Spring的AOP是使用aspectJ框架，有两种代理实现，使用JDK的Proxy和InvocationHandler，基于接口实现，或者cglib的MethodInterceptor

> [Spring底层两种动态代理实现原理及区别](https://baijiahao.baidu.com/s?id=1598982978229870867&wfr=spider&for=pc)
> [Spring系列中的AOP部分](https://blog.csdn.net/get_more/article/category/7083314) 