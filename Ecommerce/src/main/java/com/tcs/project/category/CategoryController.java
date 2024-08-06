package com.tcs.project.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tcs.project.product.Product;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}/products")
    public List<Product> getProductsByCategory(@PathVariable Long id) {
        return categoryService.getProductsByCategory(id);
    }
    @PostMapping("/{name}/{imageUrl}")
    public ResponseEntity<Category> createCategory(
            @PathVariable String name,
            @PathVariable String imageUrl) {
        
        Category category = new Category();
        category.setName(name);
        category.setImageUrl(imageUrl);
        
        Category savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(savedCategory);
    }

    @PutMapping("/{id}/{name}/{imageUrl}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long id,
            @PathVariable String name,
            @PathVariable String imageUrl) {
        
        Optional<Category> existingCategory = categoryService.getCategoryById(id);
        if (existingCategory.isPresent()) {
            Category category = existingCategory.get();
            category.setName(name);
            category.setImageUrl(imageUrl);
            
            Category updatedCategory = categoryService.saveCategory(category);
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }


}
