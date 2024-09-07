package demo.ProductManagement.entity;

import demo.ProductManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    public Page<Product> getProductsByIdOrName(String name, int id,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByNameContainingOrId(name,id,pageable);
    }
    public List<Product> listAll() {
        return (List<Product>) productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product get(int id) {
        return productRepository.findById(id).get();
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }
}
