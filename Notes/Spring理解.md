# Spring 理解

### 理念和优势

#### 理念

- 设计层面框架，解决业务逻辑层和其他各层的耦合问题，因此是基于面向接口的思想。
- 一栈式轻量级框架

#### 优势

- 轻量级，非侵入式： 代码污染低
- IOC和面向切面的思想：降低耦合，系统业务集中管理
- 一站式的解决方案：开放式的集成方案

#### 体系

- 核心容器由Core，Bean，Context，Expression
- 数据访问/集成包括JDBC，ORM，OXM，JMS消息服务（生产者和消费者）和事务处理（为实现特定接口的类及所有POJO支持编程式和声明式事务）模块
- Web层由Web（基本的web功能：文件上传，servlet监听器，web Context），WebMVC（Spring MVC），Web-Socket（客户端和服务器之间的Socket通信方式）
- 其他：AOP，Aspects（集成AspectJ面向切面框架），Instrumentation（操作和修改Java类），Test

### 核心思想

#### IOC

- Inversion of Control 控制反转：将对象交给容器控制，包括创建对象和依赖注入，反转意思是将这个过程交给容器处理，不需要写代码做。
- Spring中的IOC：Spring负责控制对象的生命周期和对象之间的关系。
- Spring是解决业务层和其他各层的松耦合问题，那么IOC就贯穿整个Spring生态框架。
- BeanFactory和ApplicationContext: 
    - BeanFactory是IOC容器的基本实现
    - ApplicationContext是BeanFactory的子接口，提供更多特性
    - BeanFactory是Spring框架的基础设施，面向Sprig本身，ApplicationContext面向Spring框架的开发者。
    
#### AOP

- Aspect Oriented Programming 面向切面编程，通过预编译方式和运行期动态代理实现通用功能统一维护，OOP的延续。
- 相关概念：Aspect（切面），JointPoint（连接点），PointCut（切入点），Advice（增强），Target（目标对象），Weaving（织入）
- PointCut是表达式，Advice是事务处理/监控程序
- Advice：before,after,around
- Spring通过动态代理和动态字节码实现AOP





