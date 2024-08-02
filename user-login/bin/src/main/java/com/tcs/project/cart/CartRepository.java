package com.ebazaar.cart;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface CartRepository extends CrudRepository<Cart, Integer>  {

	
	@Query(nativeQuery = true,value="Select * from cart where cart_id like :cart_id")
	List<Cart> findByCartId(Long cart_id);

	@Query(nativeQuery = true,value="Select * from cart where user_id like :user_id")
	List<Cart> findByUserId(Long user_id);

	//@Query(nativeQuery = true,value="Select * from participant where Skills like %:sname%")

//	@Query(nativeQuery = true,value="Select * from  where Skills like %:id%")
//	Object findByUserId(User id);

}
