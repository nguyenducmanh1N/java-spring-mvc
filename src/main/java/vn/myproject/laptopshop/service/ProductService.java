package vn.myproject.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.myproject.laptopshop.domain.Cart;
import vn.myproject.laptopshop.domain.CartDetail;
import vn.myproject.laptopshop.domain.Product;
import vn.myproject.laptopshop.domain.User;
import vn.myproject.laptopshop.repository.CartDetailRepository;
import vn.myproject.laptopshop.repository.CartRepository;
import vn.myproject.laptopshop.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    public ProductService (ProductRepository productRepository, 
            CartRepository cartRepository,
            CartDetailRepository cartDetailRepository,
            UserService userService){
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
    }
     public List<Product> fetchProducts() {
        return this.productRepository.findAll();

    }
    
    public Optional<Product> fetchProductById(long id) {
        return this.productRepository.findById(id);
        
    }
    public Product createProduct(Product pr){
        return this.productRepository.save(pr);
    }
    
    public void deleteAProduct(Long id){
        this.productRepository.deleteById(id);
    }
    
    public void handleAddProductToCart(String email,long productId ,HttpSession session){
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            Cart cart = this.cartRepository.findByUser(user);
            // neu chua co thi tao moi
            if (cart == null) {
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(0);

                cart = this.cartRepository.save(otherCart);
            }
            // save cart_detail
            // find product by id
            Optional<Product> producOptional = this.productRepository.findById(productId);
            if (producOptional.isPresent()) 
            {
                Product realProduct =producOptional.get();
                // kiem tra xem san pham da co trong gi hang chua 
                CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);
                // neu chua co thi them moi neu co r thi tang quantity len 1
                if (oldDetail == null) {
                    CartDetail cd = new CartDetail();
                    cd.setCart(cart);
                    cd.setProduct(realProduct);
                    cd.setPrice(realProduct.getPrice());
                    cd.setQuantity(1);

                    this.cartDetailRepository.save(cd);
                    // tang tong so luong
                    int s = cart.getSum() +1;
                    cart.setSum(s);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", s);
                }else{
                    oldDetail.setQuantity(oldDetail.getQuantity() + 1);
                    this.cartDetailRepository.save(oldDetail);
                }
                
             
            }
          
        }
    }

    public Cart fetchByUser(User user){
        return this.cartRepository.findByUser(user);
    }

}
