package demo.ProductManagement.repository;

import demo.ProductManagement.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Integer> {
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByNameContainingOrId(String name, int id,Pageable pageable);

}
