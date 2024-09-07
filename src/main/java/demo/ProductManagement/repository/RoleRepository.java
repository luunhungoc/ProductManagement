package demo.ProductManagement.repository;

import demo.ProductManagement.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Integer> {
    Role findByName(String name);

}
