package com.tcs.project.product;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	

	@Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
		       " OR LOWER(p.productDescription) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
		       " OR LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	List<Product> searchProducts(@Param("keyword") String keyword);

	List<Product> findByCategory(String category);

	Product findByProductName(String name);

	Product findByProductPrice(Long productPrice);
	
	List<Product> findByProductBrand(String productBrand);
	
	 List<Product> findAll(Sort sort);

	List<Product> findByProductPriceBetween(Long minPrice, Long maxPrice);

}
