package demo.ProductManagement.controller;

import demo.ProductManagement.entity.Product;
import demo.ProductManagement.entity.ProductService;
import demo.ProductManagement.entity.Role;
import demo.ProductManagement.entity.User;
import demo.ProductManagement.repository.ProductRepository;
import demo.ProductManagement.repository.RoleRepository;
import demo.ProductManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public String listProducts(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "3") int size) {
        Page<Product> productList = productService.getProducts(page, size);
        System.out.println(productList.getNumber());
        model.addAttribute("productList",productList);
        return "product/products";
    }

    @GetMapping("/product/add-product")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/add-product";
    }

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";

    @PostMapping("/product/add-product") public String uploadImage(@ModelAttribute Product product, @RequestParam("image") MultipartFile file) throws IOException, IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        product.setPhoto(fileNames.toString());
        productRepository.save(product);
        System.out.println(fileNames);
        System.out.println(fileNameAndPath);

        return "redirect:/product";
    }


    @GetMapping("/product/edit/{id}")
    public String editProductForm(@PathVariable int id, Model model) {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "product/edit-product";
    }

    @PostMapping("/product/edit/{id}")
    public String editProduct(@PathVariable int id, @ModelAttribute Product product) {
        product.setId(id);
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }

    @GetMapping(value = "/search")
    public String search(@RequestParam("searchInput") String searchInput, Model model,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "3") int size) {
        Page<Product> resultList;
        if (searchInput.isEmpty()) {
            resultList = productService.getProducts(page, size);
        } else {
            resultList = productService.getProductsByIdOrName(searchInput, Integer.parseInt(searchInput),page,size);
        }
        model.addAttribute("productList", resultList);
        return "product/products";
    }



}
