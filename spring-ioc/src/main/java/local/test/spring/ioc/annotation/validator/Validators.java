package local.test.spring.ioc.annotation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Validators {
    @Autowired
    List<Validator> validators;

    public void validate(String email, String password, String name) {
        for (Validator validator : this.validators) {
            validator.validate(email, password, name);
        }
    }

}
