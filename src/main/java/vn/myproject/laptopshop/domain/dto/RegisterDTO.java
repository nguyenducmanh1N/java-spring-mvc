package vn.myproject.laptopshop.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import vn.myproject.laptopshop.service.validator.RegisterChecker;

@RegisterChecker
public class RegisterDTO {
    
    @Size(min = 1, message = "toi thieu 1 ki tu")
    private String firstName;

    private String lastName;

    @Email(message = "email khong hop le " ,regexp = "^[a-zA-Z0-9_!#$&'*+/=?`{|}~^-]+@[a-zA-Z0-9.-]+$")
    private String email;

    private String password;
    
    private String confirmPassword;


    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
   

    

    
}
