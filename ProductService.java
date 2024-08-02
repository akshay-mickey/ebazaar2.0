package com.EBazaar.Products;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService {

	@Autowired
	ProductRepository repo;

	List<Product> getAllProducts() {
		return ((List) (repo.findAll()));
	}

	Optional<Product> getProductById( Integer id) {
		return repo.findById(id);
	}

	public List<Product> getProductByProductName( String productName) {
		return repo.findByProductName(productName);
	}

	public List<Product> getProductByCategory( String category) {
		return repo.findByCategory(category);

	}

	public List<Product> getByPrice( Long price) {
		return repo.findByPrice(price);

	}

	void addNewProduct( Product product) {
		repo.save(product);
	}

	void updateProduct( Product product) {
		repo.save(product);
	}

	void deleteProduct( Integer id) {
		repo.deleteById(id);

	}

}
