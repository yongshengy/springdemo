package local.test.spring;

import local.test.spring.aop.xml.Person;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopXmlTest {
    ClassPathXmlApplicationContext context;

    @Before
    public void beforeUp(){
        String configLocation = "bean-aopxml.xml";
        context = new ClassPathXmlApplicationContext(configLocation);
    }

    @Test
    public void test1() throws Exception{
        Person chinese = (Person) context.getBean("chinese");
        chinese.eat("Fruit");
        System.out.println(chinese.sayHello("Hello...."));
        chinese.divide();
    }
}
