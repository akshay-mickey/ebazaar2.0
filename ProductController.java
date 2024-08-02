package com.ebazaar.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/api/v1.0")
public class ProductController {

	@Autowired
	ProductService service;

	@PutMapping("/update")
	Product updateProductDetails(@RequestBody Product product) {

		return service.updateProductDetails(product);

	}

	@PostMapping("/addproduct")
	ResponseEntity<?> addNewProduct(@RequestBody Product product) {

		return service.addProduct(product);

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

	@GetMapping("/details/{productId}")
	public ResponseEntity<Product> getProductDetails(@PathVariable Long productId) {

		Product product = service.getProductById(productId);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product);
	}

	@PutMapping("/add/{id}/{quantity}")
	public void addQuantity(@PathVariable Long id, @PathVariable Long quantity) {
		
		service.addQuantity(id, quantity);
		
	}
	
	@GetMapping
	

    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "minPrice", required = false) Long minPrice,
            @RequestParam(value = "maxPrice", required = false) Long maxPrice,
            @RequestParam(value = "sort", required = false) String sort) {

        // Example service method call with optional parameters
        List<Product> products = service.findProducts(category, name, minPrice, maxPrice, sort);

        // Return the list of products wrapped in a ResponseEntity
        return ResponseEntity.ok(products);
    }


}
