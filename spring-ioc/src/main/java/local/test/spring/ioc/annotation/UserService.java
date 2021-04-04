package local.test.spring.ioc.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    //1.字段上
//    @Autowired
    MailService mailService;

    // 2. set
//    @Autowired
    public void setMailService(MailService mailService){
        this.mailService = mailService;
    }

    // 3.构造函数上
    public UserService(@Autowired MailService mailService){
        this.mailService = mailService;
    }
}
