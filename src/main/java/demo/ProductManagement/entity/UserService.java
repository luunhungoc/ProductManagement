package demo.ProductManagement.entity;

import demo.ProductManagement.repository.RoleRepository;
import demo.ProductManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void saveUserWithRoles(User user) {
        // Save the user and the roles will be automatically saved in the user_roles table
        userRepository.save(user);
    }
}
