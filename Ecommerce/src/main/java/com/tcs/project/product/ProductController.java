package com.tcs.project.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tcs.project.category.Category;
import com.tcs.project.category.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }
    
    @GetMapping("/")
    public List<Product> getAllUsers() {
        return productService.getAllUsers();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String brand) {
        return productService.searchProducts(minPrice, maxPrice, brand);
    }

//    @GetMapping("/search")
//	public List<Product> searchProducts(@RequestParam String keyword) {
//	    System.out.println("Search keyword: " + keyword);
//	    List<Product> products = productService.searchProducts(keyword);
//	    System.out.println("Products found: " + products.size());
//	    return products;
//	}

    @PostMapping("/{categoryId}")
    public ResponseEntity<Product> createProduct(@RequestBody Product product,@PathVariable Long categoryId) {
        Category category = categoryService.getCategoryByIdForProducts(categoryId);
        product.setCategory(category);
    	Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}/{categoryId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@PathVariable Long categoryId, @RequestBody Product product) {
        Optional<Product> existingProduct = productService.getProductById(id);
    
        if (existingProduct.isPresent()) {
            product.setId(id);
            Category category = categoryService.getCategoryByIdForProducts(categoryId);
            product.setCategory(category);
            Product updatedProduct = productService.saveProduct(product);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    
    
}