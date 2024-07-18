package vn.myproject.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.myproject.laptopshop.domain.Cart;
import vn.myproject.laptopshop.domain.User;



@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{

    Cart findByUser(User user);
    
}