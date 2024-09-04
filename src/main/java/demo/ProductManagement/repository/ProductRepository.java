package demo.ProductManagement.repository;

import demo.ProductManagement.entity.Product;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Integer> {
    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);

    List<Product> findByNameContainingOrId(String name, Long id);

}
