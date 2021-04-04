package local.test.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    @MetricTime("Register...")
    public void register() {
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
