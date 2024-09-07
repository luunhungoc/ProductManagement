package demo.ProductManagement.entity;
import demo.ProductManagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleById(Long roleId) {
        return roleRepository.findById(Math.toIntExact(roleId)).orElse(null);
    }
}
