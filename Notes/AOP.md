# AOP

### 介绍

- AOP：面向切面编程，目的是解耦合一些通用业务。通过动态代理的方式实现对目标对象的功能扩展。
- 名词：切面（Aspect），里面有Adivce（通知/增强），配置时关注JointPoint,也就是PointCut定义
- 动态代理：代理的类是动态生成的。静态代理是和实际的接口关联紧密。
- Spring的AOP是使用aspectJ框架，有两种代理实现，使用JDK的Proxy和InvocationHandler，基于接口实现，或者cglib的MethodInterceptor

> [Spring底层两种动态代理实现原理及区别](https://baijiahao.baidu.com/s?id=1598982978229870867&wfr=spider&for=pc)
> [Spring系列中的AOP部分](https://blog.csdn.net/get_more/article/category/7083314) 

### 使用AOP

- OOP的主要功能是数据封装、继承和多态; 
  而AOP是一种新的编程方式，它和OOP不同，OOP把系统看作多个对象的交互，AOP把系统分解为不同的关注点，或者称之为切面（Aspect）
- 对象除了业务逻辑，还有安全检查，日志记录和事务，这些不是核心逻辑，但横跨多个业务方法；可行的方法就是使用代理，但是比较麻烦，因为对每个方法都需要处理。
- 既然如果使用Proxy样板代码，就可以看做切面，把日志，事务作为切面，然后自动化的织入到核心逻辑，实现Proxy模式

### AOP原理

- Java平台对AOP的织入方式：
    - 编译器： 在编译时，由编译器把切面调用编译进字节码，这种方式需要定义新的关键字并扩展编译器，AspectJ就扩展了Java编译器，使用关键字aspect来实现织入；
    - 类加载期： 在目标类被装载到JVM时，通过一个特殊的类加载器，对目标类的字节码重新“增强”；
    - 运行期： 目标对象和切面都是普通Java类，通过JVM的动态代理功能或者第三方库实现运行期动态织入。
- 最简单的是运行期，Spring的AOP实现就是基于JVM的动态代理。
  由于JVM的动态代理要求必须实现接口，如果一个普通类没有业务接口，就需要通过[CGLIB](https://github.com/cglib/cglib)或者[Javassist](https://www.javassist.org/)这些第三方库实现
  
### 装配AOP

- Aspect：切面，即一个横跨多个核心逻辑的功能，或者称之为系统关注点；
- Joinpoint：连接点，即定义在应用程序流程的何处插入切面的执行；
- Pointcut：切入点，即一组连接点的集合；
- Advice：增强，指特定连接点上执行的动作；
- Introduction：引介，指为一个已有的Java对象动态地增加新的接口；
- Weaving：织入，指将切面整合到程序的执行流程中；
- Interceptor：拦截器，是一种实现增强的方式；
- Target Object：目标对象，即真正执行业务的核心逻辑对象；
- AOP Proxy：AOP代理，是客户端持有的增强后的对象引用。

- 引入spring-aspects依赖，这个会带入AspectJ，@Aspect @Component； @PointCut("execution(public * local.test.UserService.update*(..)))")
- 拦截器类型： @Before/@After/@AfterThrowing/@AfterReturning/@Around
```  
@Before,@Aftter 标记的方法可选参数
JoinPoint: 获取当前织入位置的细节（被织入的方法）
@AfterReturning
Object returnValue: 当前方法的返回值
@AfterThrowing
Exception ex: 当前捕获到的异常
```
- @EnableAspectJAutoProxy

-  Spring对接口类型使用JDK动态代理，对普通类使用CGLIB创建子类。如果一个Bean的class是final，Spring将无法为其创建子类。

### 使用注解装配AOP

- 上面是全覆盖，而且会后来的不知道被装配，所以最好是显示声明，比如@Transactional
- 定义一个注解 @Target(METHOD) @Retention(RUNTIME)
- 标注到方法上
- 切入点@Around("@annotation(metricTime)")

### AOP避坑

- 无论是使用AspectJ语法，还是配合Annotation，使用AOP，实际上就是让Spring自动为我们创建一个Proxy，
  使得调用方能无感知地调用指定方法，但运行期却动态“织入”了其他逻辑，因此，AOP本质上就是一个代理模式。
- 因为Spring使用了CGLIB来实现运行期动态创建Proxy，如果我们没能深入理解其运行原理和实现机制，就极有可能遇到各种诡异的问题。
-  Spring通过CGLIB创建的代理类，不会初始化代理类自身继承的任何成员变量，包括final类型的成员变量！而是把变量转入构造函数
1. 访问被注入的Bean时，总是调用方法而非直接访问字段；
2. 编写Bean时，如果可能会被代理，就不要编写`public final`方法。

