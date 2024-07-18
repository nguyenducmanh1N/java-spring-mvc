package vn.myproject.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.myproject.laptopshop.domain.User;

@Repository
public interface UserRepositoty extends JpaRepository<User,Long> {
    User save(User user);
    void deleteById(long id);
    List<User> findOneByEmail(String email);
    List<User> findAll();
    User findById(long id);
    Boolean existsByEmail(String email);

    User findByEmail(String email);
}
