# IOC的实现

### 实现的理解

- 控制反转：对象的生命周期交给容器管理，容器负责创建对象，同时注入对象中的依赖。
- 创建对象的方式：构造函数，静态工厂和动态工厂。
- 注入：
    - constructor-arg 构造函数参数，跟创建有关；
    - property: 注入对象属性值；



### 容器

> 参考：
> - [IOC 之深入理解 Spring IoC](https://mp.weixin.qq.com/s/nCvZ9NbNQeD_FtJyho4owg)

- BeanFactory是基础容器，内部维护着BeanDefinition map，并可根据BeanDefinition的描述进行bean的创建和管理。
    - 三个子类：ListableBeanFactory, HierarchicalBeanFactory, AutowireCapableBeanFactory
    - DefaultListableBeanFactory是最终实现，实现的所有的接口
    - BeanDefinition用来描述Spring中的Bean对象
    - BeanDefinitionReader是读取Spring配置文件的内容，并将其转换为IOC容器中得数据结构BeanDefinition
- ApplicationContext体系，Spring容器，应用上下文，继承了BeanFactory.
    - 继承了MessageSource, 提供国际化的标准访问策略。
    - 继承了ApplicationEventPublisher，提供强大的事件机制。
    - 扩展了ResourceLoader，可以加载多个Resource
    - 对Web的支持
- 简单说就是将配置文件进行解析（XML或注解其他形式），然后放到一个Map中。       

### XML配置

#### 创建

1. 构造函数

```java, xml

// pojo
public class User {
    private String name;
    
    public User(){
    }
    // setter and getter
}

// bean.xml
<bean id="user" class="local.test.User">
</bean>

// inject the property
<bean id="user" class="local.test.User">
    <property name="name"  value="张三"></property>
</bean>

// pojo with constructor using parameter

public class User {
    private String name;
    
    public User(String name){
        this.name = name;
    }
    // setter and getter
}

// bean.xml
<bean id="user" class="local.test.User">
    - index： 构造方法参数下标
    <constructor-arg  index="0"  value="张三"></constructor-arg>
    - name：参数名
    <constructor-arg  name="name"  value="李四"></constructor-arg>
    - type：参数类型，不常用
    <constructor-arg  type="java.lang.String" value="李四"></constructor-arg>
</bean>
```

2. 工厂

> 静态工厂是直接写工厂class和调用的类方法，与对象无关
> 动态工厂需要先创建工厂对象，使用factoy-bean应用这个对象

```java/xml

// 静态工厂，静态方法生成对象
public class UserFactory {
    public static User  newInstance(String name){
        return  new User(name);
    }
}

// class指定工厂类，使用factory-method指定要调用的方法
<bean id="user" class="cn.sxt.factory.UserFactory"  factory-method="newInstance">
    <constructor-arg  index="0"  value="赵六"></constructor-arg>
</bean>

```

```java/xml

// 生成对象的方法不是静态的
public class UserDynamicFactory {
    public User newInstance(String name){
        return  new User(name);
    }
}

// 先得到factory对象，然后使用factory-bean and factory-method得到对象
<bean id="user" factory-bean="userFactory" factory-method="newInstance">
    <constructor-arg  index="0"  value="赵六"></constructor-arg>
</bean>
<bean id="userFactory"  class="cn.sxt.factory.UserDynamicFactory"/>
```

### 注解配置

> xml方式配置太多，而且需要java和xml之间不断切换

- @Autowired 自动装配，针对属性或构造函数参数查找bean；默认按类型匹配
- @Qualifier 指定注入Bean的名称，也是针对属性或构造函数参数查找bean，针对相同类型多个对象，使用@Autowired不知道取哪个
- @Resource 默认name匹配，找不到再找type，也是针对属性或构造函数参数查找bean。
- @Resource是J2EE中得注解，建议使用；
- @Service,@Repository,@Controller分别是业务层，数据访问层，控制层注解，针对的对象创建，而且基本都是无参构建
- 上面三个注解结合@Scope指定对象的维护生命周期；prototype, singleton
- @Component Spring管理组件的通用形式，不推荐使用
- 注解配置结合context:component-scan 指定自动扫描

#### 总结

- 使用注解之前要开启自动扫描功能，其中base-package为需要扫描的包(含子包)。 <context:component-scan base-package="cn.test"/> 
- @Configuration把一个类作为一个IoC容器，它的某个方法头上如果注册了@Bean，就会作为这个Spring容器中的Bean。
- @Scope注解 作用域
- @Lazy(true) 表示延迟初始化
- @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。
- @Repository用于标注数据访问组件，即DAO组件。
- @Service用于标注业务层组件、 
- @Controller用于标注控制层组件（如struts中的action）
- @Scope用于指定scope作用域的（用在类上）
- @Autowired 默认按类型装配，如果我们想使用按名称装配，可以结合@Qualifier注解一起使用。如下：
- @Autowired @Qualifier("personDaoBean") 存在多个实例配合使用
- @Resource默认按名称装配，当找不到与名称匹配的bean才会按类型装配。
- @PostConstruct用于指定初始化方法（用在方法上）
- @PreDestory用于指定销毁方法（用在方法上）
- @DependsOn：定义Bean初始化及销毁时的顺序
- @Primary：自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
- @PostConstruct 初始化注解
- @PreDestroy 摧毁注解 默认 单例  启动就加载
- @Async异步方法调用

