package vn.myproject.laptopshop.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.myproject.laptopshop.domain.Cart;
import vn.myproject.laptopshop.domain.CartDetail;
import vn.myproject.laptopshop.domain.Product;
import vn.myproject.laptopshop.domain.User;
import vn.myproject.laptopshop.service.ProductService;

import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ItemController {

    private final ProductService productService;
    public ItemController(ProductService productService) {
        this.productService = productService;
        }

        // lay thong tin chi tiet nguoi dung
    @GetMapping("/product/{id}")
    public String getProductPage(Model model ,@PathVariable Long id) {
        Product pr = this.productService.fetchProductById(id).get();
        model.addAttribute("product", pr);
        model.addAttribute("id", id);
        return "client/product/detail";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String AddProductToCart(@PathVariable long id,HttpServletRequest request) {
         HttpSession session = request.getSession(false);

        long productId = id;
        String email = (String) session.getAttribute("email");
        this.productService.handleAddProductToCart(email,productId,session);
        return "redirect:/";
    }
    
    @GetMapping("/cart")
    public String getCartPage(Model model, HttpServletRequest request) {
        User currentUser = new User();
        HttpSession session = request.getSession(false);
        long id =(long) session.getAttribute("id");
        currentUser.setId(id);

        Cart cart = this.productService.fetchByUser(currentUser);
        List<CartDetail> cartDetails = cart.getCartDetails();

        double totalPrice = 0;
        for (CartDetail cartDetail : cartDetails) {
            totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();
        
        }
        model.addAttribute("cartDetail", cartDetails);
        model.addAttribute("totalPrice", totalPrice);
        return "client/cart/show";
        
        
    
    }
}
