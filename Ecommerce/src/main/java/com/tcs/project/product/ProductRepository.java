package com.tcs.project.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Product> findByBrand(String brand);
//    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
//		       " OR LOWER(p.productDescription) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
//		       " OR LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
//	List<Product> searchProducts(@Param("keyword") String keyword);


}
