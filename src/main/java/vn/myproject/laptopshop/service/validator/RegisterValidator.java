package vn.myproject.laptopshop.service.validator;

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.myproject.laptopshop.domain.dto.RegisterDTO;
import vn.myproject.laptopshop.service.UserService;


@Service
public class RegisterValidator implements ConstraintValidator<RegisterChecker, RegisterDTO> {
    
    private final UserService userService;
    public RegisterValidator( UserService userService){
        this.userService = userService;
    }

    @Override
    public boolean isValid(RegisterDTO user , ConstraintValidatorContext context) {
        boolean valid = true;
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            // bao loi neu co
            context.buildConstraintViolationWithTemplate("Passwords k chinh xac ")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
            }
            // additional 
        //check email
        if (this.userService.checkEmailExist(user.getEmail())) 
        {
            context.buildConstraintViolationWithTemplate("Email da ton tai")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }
        return valid;
    }
}
