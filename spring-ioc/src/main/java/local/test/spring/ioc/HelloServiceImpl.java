package local.test.spring.ioc;

public class HelloServiceImpl implements HelloService {


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
