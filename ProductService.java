package com.ebazaar.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;

	List<Product> getProductByCategory(String category) {

		return repo.findByCategory(category);
	}

	Product getProductById(Long id) {

		return repo.findById(id).get();
	}

	public ResponseEntity<?> addProduct( Product product) {
        Product savedProduct = repo.save(product);
        return ResponseEntity.ok(savedProduct);
    }

	Product getProductByProductName(String name) {

		return repo.findByProductName(name);
	}

	Product getProductByProductPrice(Long productPrice) {

		return repo.findByProductPrice(productPrice);
	}

	List<Product> getProductByProductBrand(String productBrand) {

		return repo.findByProductBrand(productBrand);
	}

	Product updateProductDetails(Product product) {

		return repo.save(product);

	}

	void deleteProduct(Long id) {

		repo.deleteById(id);

	}

	void lessProductQuantity(String productName) {

		Product product = repo.findByProductName(productName);

		if (product.getProductQuantity() < 0)

			System.out.println("This product is out of stock for now");

		else

			System.out.println("Product is available");

	}

	public List<Product> getByProductPriceRange(Long minPrice, Long maxPrice) {

		return repo.findByProductPriceBetween(minPrice, maxPrice);
	}

	public List<Product> getProductSortedByPrice() {

		Sort sort = Sort.by(Sort.Order.asc("productPrice"));

		return repo.findAll(sort);
	}

	public String getProductDetails(Long productId) {
		Product product = repo.findById(productId).orElse(null);
		if (product != null) {
			return product.getProductName() + " - " + product.getProductQuantity();

		}
		return "Product not found";
	}

	public void addQuantity(Long productId, Long quantityToAdd) {
		Optional<Product> optionalProduct = repo.findById(productId);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			product.setProductQuantity(product.getProductQuantity() + quantityToAdd);
			repo.save(product);
		} else {
			throw new RuntimeException("Product not found");
		}
	}

	 public List<Product> findProducts(String category, String name, Long minPrice, Long maxPrice, String sort) {
	        
	        return repo.findProducts(category, name, minPrice, maxPrice, sort);
	    }

}
