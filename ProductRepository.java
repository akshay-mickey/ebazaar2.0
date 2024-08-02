package com.EBazaar.Products;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

	List<Product> findByProductName(String productName);

	List<Product> findByCategory(String category);

	List<Product> findByPrice(Long price);

}
