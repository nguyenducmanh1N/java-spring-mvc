package vn.myproject.laptopshop.service;


import java.util.List;



import org.springframework.stereotype.Service;

import vn.myproject.laptopshop.domain.Role;
import vn.myproject.laptopshop.domain.User;
import vn.myproject.laptopshop.domain.dto.RegisterDTO;
import vn.myproject.laptopshop.repository.RoleRepository;
import vn.myproject.laptopshop.repository.UserRepositoty;

@Service
public class UserService {
    private final UserRepositoty userRepositoty;
    private final RoleRepository roleRepository;

    public UserService(UserRepositoty userRepositoty, RoleRepository roleRepository) {
        this.userRepositoty = userRepositoty;
        this.roleRepository = roleRepository;
        
    }

    public List<User> getAllUsers() {
        return this.userRepositoty.findAll();
    }
    public User getUserById(long id){
        return this.userRepositoty.findById(id);
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepositoty.findOneByEmail(email);
    }
    
    public User handleSaveUser(User user){
        User user2 =this.userRepositoty.save(user);
        System.out.println(user2);
        return user2;
    }

    public void deleteAUser(long id){
        this.userRepositoty.deleteById(id);
    }
    public Role getRoleByName(String name){
        return this.roleRepository.findByName(name);
    }

    // mapper User 
    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user  = new User();
        user.setFullName(registerDTO.getFirstName() + " " +registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        
        return user;
    
    }

    public boolean checkEmailExist(String Email){
        return this.userRepositoty.existsByEmail(Email);

    }

    public User getUserByEmail(String email){
        return this.userRepositoty.findByEmail(email);
    }
}
