package local.test.spring.ioc.annotation.validator;

import org.springframework.stereotype.Component;

@Component
public class NameValidator implements Validator {
    @Override
    public void validate(String email, String password, String name) {
        if (name == null || name.trim().equals("") || name.length() > 20) {
            throw new IllegalArgumentException("invalid name: " + name);
        }
    }
}
