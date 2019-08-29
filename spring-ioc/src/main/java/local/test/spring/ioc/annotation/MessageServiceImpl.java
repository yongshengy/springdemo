package local.test.spring.ioc.annotation;

import org.springframework.stereotype.Component;

@Component("messageService")
public class MessageServiceImpl implements MessageService {
    @Override
    public String getMessage() {
        return "Hello, Spring!";
    }
}
