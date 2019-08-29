package local.test.spring.ioc;

import java.util.Random;

public class UserFactory {

    public static User createInstance() {
        User user = new User();
        user.setName("name" + new Random().nextInt(30));
        return user;
    }
}
