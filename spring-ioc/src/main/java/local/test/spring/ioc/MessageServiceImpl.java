package local.test.spring.ioc;

public class MessageServiceImpl implements MessageService{
    @Override
    public String getMessage() {
        return "Hello, Spring!";
    }
}
