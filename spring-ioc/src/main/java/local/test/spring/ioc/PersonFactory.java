package local.test.spring.ioc;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PersonFactory {
    private PersonFactory() {

    }

    private static PersonFactory INSTANCE = new PersonFactory();

    public static PersonFactory getInstance() {
        return INSTANCE;
    }

    public Person createPerson() {
        Person person = new Person("name" + new Random().nextInt(30),
                ThreadLocalRandom.current().nextInt(6, 10));
        return person;
    }
}
