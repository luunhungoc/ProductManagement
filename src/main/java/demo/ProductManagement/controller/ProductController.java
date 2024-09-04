package demo.ProductManagement.controller;


import demo.ProductManagement.entity.FileUploadUtil;
import demo.ProductManagement.entity.Product;
import demo.ProductManagement.repository.ProductRepository;
import jakarta.persistence.criteria.Path;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    public ProductController() {
    }
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String home() {
        return "user/index";
    }

    @GetMapping("/product")
    public String showProductList(Model model) {
        List<Product> productList = new ArrayList<>((Collection<Product>) productRepository.findAll());
        model.addAttribute("productList", productList);
        return "product/products";
    }

    @GetMapping("/product/add-product")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/add-product";
    }

    @PostMapping("/product/add-product")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam("img") MultipartFile[] multipartFile) throws IOException {
        String saveDirectory = "D:/static/photos/";
        if (multipartFile != null && multipartFile.length > 0) {
            for (MultipartFile aFile : multipartFile){

                System.out.println("Saving file: " + aFile.getOriginalFilename());

                if (!aFile.getOriginalFilename().equals("")) {
                    aFile.transferTo(new File(saveDirectory + aFile.getOriginalFilename()));
                    String fileName = StringUtils.cleanPath(aFile.getOriginalFilename());
                    product.setPhoto(fileName);
                    System.out.println(saveDirectory+fileName);
                }
            }
        }

        Product savedProduct = productRepository.save(product);
        return "redirect:/product";

    }

    @GetMapping("/product/edit/{id}")
    public String editProductForm(@PathVariable int id, Model model) {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "product/edit-product";
    }

    @PostMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product product) {
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
    public String search(@RequestParam("searchInput") String searchInput, Model model) {
        List<Product> resultList;
        if (searchInput.isEmpty()) {
            resultList = (List<Product>) productRepository.findAll();
        } else {
            resultList = productRepository.findByNameContainingOrId(searchInput, Long.valueOf(searchInput));
        }
        model.addAttribute("productList", resultList);
        return "product/products";


    }

}
