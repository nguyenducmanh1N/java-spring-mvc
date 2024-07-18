package vn.myproject.laptopshop.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vn.myproject.laptopshop.domain.Product;
import vn.myproject.laptopshop.domain.User;
import vn.myproject.laptopshop.domain.dto.RegisterDTO;
import vn.myproject.laptopshop.service.ProductService;
import vn.myproject.laptopshop.service.UserService;

@Controller
public class HomePageController {
    
    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public HomePageController (ProductService productService, 
        UserService userService,
        PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String getHomepage(Model model) {// HttpServletRequest request
        List<Product> products =this.productService.fetchProducts();
        model.addAttribute("products", products);

        //HttpSession session = request.getSession(false);
        //System.out.println(">>> check fullName" + session.getAttribute(""));

        return "client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }
    
    @PostMapping("/register")
    public String handleRegister(
        @ModelAttribute("registerUser") 
        @Valid RegisterDTO registerDTO , 
        BindingResult bindingResult
        ) {

        // List<FieldError> errors = bindingResult.getFieldErrors();
        // for(FieldError error : errors){
        //     System.out.println(">>>>>" + error.getField() + "  -  "
        //     + error.getDefaultMessage());
        // }
        if (bindingResult.hasErrors()) {
            return "client/auth/register";
            
        }
        User  user = this.userService.registerDTOtoUser(registerDTO);
        
        String hashPassword = this.passwordEncoder.encode(user.getPassword());

        
        user.setPassword(hashPassword);
        // save
        user.setRole(this.userService.getRoleByName("USER"));

        this.userService.handleSaveUser(user);
        return "redirect:/login";
        
    }
    
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        
        return "client/auth/login";
    }
    
    @GetMapping("/access-deny")
    public String getDenyPage(Model model) {

        return "client/auth/deny";
    }

    // @GetMapping("/cart")
    // public String getCartPage(Model model) {

    //     return "client/cart/show";
    // }
}
