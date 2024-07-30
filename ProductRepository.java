package com.ebazaar.product;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByCategory(String category);

	Product findByProductName(String name);

	Product findByProductPrice(Long productPrice);
	
	List<Product> findByProductBrand(String productBrand);
	
	 List<Product> findAll(Sort sort);

	List<Product> findByProductPriceBetween(Long minPrice, Long maxPrice);

}
