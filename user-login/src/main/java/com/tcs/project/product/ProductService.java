package com.tcs.project.product;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }

	List<Product> getProductByCategory(String category) {

		return repo.findByCategory(category);
	}

	Product getProductById(Long id) {

		return repo.findById(id).get();
	}

	Product addNewProduct(Product product) {

		return repo.save(product);

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
}