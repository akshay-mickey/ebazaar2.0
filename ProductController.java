package com.ebazaar.product;

import java.util.List;

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
@RequestMapping("/product/api/v1.0")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@PutMapping("/update")
	Product updateProductDetails(Product product) {

		return service.updateProductDetails(product);

	}
	
	@PostMapping("/addproduct")
	void addNewProduct(@RequestBody Product product) {

		service.addNewProduct(product);

	}
	
	@DeleteMapping("/delete/{id}")
	void deleteProduct(@PathVariable Long id) {

		service.deleteProduct(id);
		

	}
	
	@GetMapping("/productcount/{productName}")
	void lessProductQuantity(@PathVariable String productName) {
		 
		service.lessProductQuantity(productName);

	}
	
	@GetMapping("/category/{category}")
	List<Product> getProductByCategory(@PathVariable String category) {

		 return service.getProductByCategory(category);
	}

	@GetMapping("/pricerange/{minprice}/{maxprice}")
	List<Product> getByProductPriceRange(Long minPrice, Long maxPrice) {

		return service.getByProductPriceRange(minPrice, maxPrice);
	}
	
	@GetMapping("/brand/{productBrand}")
	List<Product> getProductByProductBrand(String productBrand) {

		return service.getProductByProductBrand(productBrand);
	}
	
	
	@GetMapping("/sort")
	 List<Product> getProductSortedByPrice() {
			
			return service.getProductSortedByPrice();
		}


}
