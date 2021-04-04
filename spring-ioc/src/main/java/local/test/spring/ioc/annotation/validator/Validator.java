package local.test.spring.ioc.annotation.validator;

public interface Validator {
    void validate(String email, String password, String name);
}
