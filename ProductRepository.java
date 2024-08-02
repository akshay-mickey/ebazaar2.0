package com.ebazaar.product;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByCategory(String category);

	Product findByProductName(String name);

	Product findByProductPrice(Long productPrice);
	
	List<Product> findByProductBrand(String productBrand);
	
	 List<Product> findAll(Sort sort);

	List<Product> findByProductPriceBetween(Long minPrice, Long maxPrice);
	
	@Query("SELECT p FROM Product p WHERE " +
	           "(p.category = :category OR :category IS NULL) AND " +
	           "(p.productName LIKE %:productName% OR :productName IS NULL) AND " +
	           "(p.productPrice BETWEEN :minPrice AND :maxPrice OR :minPrice IS NULL OR :maxPrice IS NULL) " +
	           "ORDER BY CASE WHEN :sort = 'productPrice' THEN p.productPrice ELSE NULL END ASC, " +
	           "CASE WHEN :sort = 'name' THEN p.productName ELSE NULL END ASC")
	    List<Product> findProducts(
	        @Param("category") String category,
	        @Param("productName") String productName,
	        @Param("minPrice") Long minPrice,
	        @Param("maxPrice") Long maxPrice,
	        @Param("sort") String sort);
	
}



