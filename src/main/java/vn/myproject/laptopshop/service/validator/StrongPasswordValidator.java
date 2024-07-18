package vn.myproject.laptopshop.service.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {
    // private static final Pattern PASSWORD_PATTERN = Pattern.compile(
    //         "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    // @Override
    // public void initialize(StrongPassword constraintAnnotation) {
    // }

    // @Override
    // public boolean isValid(String password, ConstraintValidatorContext context) {
    //     return password != null && PASSWORD_PATTERN.matcher(password).matches();
    // }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }
}
 