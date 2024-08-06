package com.tcs.project.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
    
    public List<Product> searchProducts(Double minPrice, Double maxPrice, String brand) {
        if (minPrice != null && maxPrice != null) {
            return productRepository.findByPriceBetween(minPrice, maxPrice);
        } else if (brand != null) {
            return productRepository.findByBrand(brand);
        } else {
            return productRepository.findAll(); // Default case
        }
    }
    
//    public List<Product> searchProducts(String keyword) {
//        return productRepository.searchProducts(keyword);
//    }

    
    public List<Product> getAllUsers() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}