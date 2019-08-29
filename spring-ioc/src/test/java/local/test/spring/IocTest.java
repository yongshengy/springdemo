package local.test.spring;

import local.test.spring.ioc.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanIsAbstractException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocTest {
    ApplicationContext applicationContext;

    @Before
    public void beforeUp(){
        String resource = "bean.xml";
        applicationContext = new ClassPathXmlApplicationContext(resource);
    }

    /**
     * 测试基本的IOC，获取Bean和注入依赖
     */
    @Test
    public void testBeanCreation(){
        User user = (User) applicationContext.getBean("user");
        System.out.println(user);

        User user1 = (User) applicationContext.getBean("user1");
        System.out.println(user1);

        User user2 = (User) applicationContext.getBean("user2");
        System.out.println(user2);

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        Person person1 = (Person) applicationContext.getBean("person1");
        System.out.println(person1);

    }

    /**
     * 测试Bean生命周期
     */
    @Test
    public void testBeanScope() {
        // singleton : 容器启动的时候就默认初始化，不需要等调用
        Student student = (Student) applicationContext.getBean("student");
        Student student2 = (Student) applicationContext.getBean("student");
        Assert.assertEquals(student, student2);

        // prototype
        Student student3 = (Student) applicationContext.getBean("student1");
        Student student4 = (Student) applicationContext.getBean("student1");
        Assert.assertNotEquals(student3, student4);
        // Web扩展了生命周期：request, session
    }

    @Test(expected = BeanIsAbstractException.class)
    public void testAbstractBean () {
        // 抽象Bean不能被实例化bean，抛出BeanIsAbstractException异常
        AbstractProduct aProduct = (AbstractProduct) applicationContext.getBean("product");
        System.out.println(aProduct.getCompany());
    }

    @Test
    public void testProperty(){
        Sporter sporter = (Sporter) applicationContext.getBean("sporter");
        System.out.println(sporter.getAge());
        System.out.println(sporter.getName());
        System.out.println(sporter.getScores1().size());
        System.out.println(sporter.getScores2().size());
        System.out.println(sporter.getMessageService().getMessage());
        System.out.println(sporter.getNum());
    }

}
