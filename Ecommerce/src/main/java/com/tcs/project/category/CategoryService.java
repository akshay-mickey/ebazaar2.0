package com.tcs.project.category;

import com.tcs.project.product.Product;
import com.tcs.project.product.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;
      
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
    
    public Category getCategoryByIdForProducts(Long id)
    {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
        	System.out.println("No category found");
                    }
        return category;

    }

    public List<Product> getProductsByCategory(Long id) {
        return (List<Product>) categoryRepository.findById(id)
                .map(Category::getProducts)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}
