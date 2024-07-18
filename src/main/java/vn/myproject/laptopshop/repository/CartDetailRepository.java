package vn.myproject.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.myproject.laptopshop.domain.Cart;
import vn.myproject.laptopshop.domain.CartDetail;
import vn.myproject.laptopshop.domain.Product;


@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long>{
    boolean existsByCartAndProduct(Cart cart , Product products);
    CartDetail findByCartAndProduct(Cart cart, Product products);
}
