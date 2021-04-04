package local.test.spring.ioc;

import local.test.spring.ioc.annotation.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        Person user1 = (Person) context.getBean("user1");
        System.out.println(user1.getName());
    }

    @Bean("user1")
    Person createUser1(){
        return new Person("zhangsan", 10);
    }

//    @Bean
//    @Qualifier("user2")
//    Person createUser2(){
//        return new Person("lisi", 20);
//    }
}
