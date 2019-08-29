package local.test.spring.ioc.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("helloService")
public class HelloServiceImpl implements HelloService {


    @Resource
    public MessageService messageService;

    @Override
    public void say() {
        System.out.println(messageService.getMessage());
    }

    @Override
    public void init() {
        System.out.println("init ....");
    }

    @Override
    public void destory() {
        System.out.println("destory....");
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public HelloServiceImpl(MessageService messageService) {
        this.messageService = messageService;
    }

    public HelloServiceImpl() {
        super();
        System.out.println("HelloServiceImpl constructor");
    }
}
