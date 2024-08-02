package com.EBazaar.Products;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/api/v1.0")
public class ProductController {

		@Autowired
		ProductService service;

		@GetMapping("/allproducts")
		List< Product> getAllProducts() {
			return service.getAllProducts();
		}
		
		@GetMapping("/id/{id}")
		Optional< Product> getProductById(@PathVariable Integer id) {
			return service.getProductById(id);
		}

		@GetMapping("/productName/{productName}")
		public List< Product> getProductByProductName(@PathVariable String productName) {
			return service.getProductByProductName(productName);
		}

		@GetMapping("/category/{category}")
		public List< Product> getProductByCategory(@PathVariable String category) {
			return service.getProductByCategory(category);

		}
		
		@GetMapping("/price/{price}")
		public List<Product> getByPrice(@PathVariable Long price) {
			return service.getByPrice(price);

		}

		@PostMapping("/add")
		void addNewProduct( @RequestBody  Product product) {
			service.addNewProduct(product);
		}

		@PutMapping("update/{product}")
		void updateProduct(@RequestBody Product product) {
			service.updateProduct(product);
		}

		@DeleteMapping("/delete/{id}")
		void deleteProduct(@PathVariable Integer id) {
			service.deleteProduct(id);

		}

	}


