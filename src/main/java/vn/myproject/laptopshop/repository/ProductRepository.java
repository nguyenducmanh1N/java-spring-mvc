package vn.myproject.laptopshop.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.myproject.laptopshop.domain.Product;



@Repository
public interface ProductRepository  extends JpaRepository<Product , Long>{
    
    
}