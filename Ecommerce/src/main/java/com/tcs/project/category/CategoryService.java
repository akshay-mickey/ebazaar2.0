package com.tcs.project.category;

import com.tcs.project.product.Product;
import com.tcs.project.product.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
  
    public List<Product> getProductsByCategory(Long id) {
        return productRepository.findByCategoryId(id);
    }
}
