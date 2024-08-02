package com.ebazaar.cart;

import java.time.LocalDateTime;

import com.ebazaar.product.Product;
import com.ebazaar.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;

	@ManyToOne
	private User user;

	@ManyToOne
	private Product product;

	private Integer quantity;
	private Long price;
	private LocalDateTime addedAt;

	
	public Cart() {
		
	}
	public Cart(Long cartId, Long user, Integer product, Integer quantity, Long price, LocalDateTime addedAt) {
		super();
		this.cartId = cartId;
		this.user = new User(user);
		this.product = new Product(product);
		this.quantity = quantity;
		this.price = price;
		this.addedAt = addedAt;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", user=" + user + ", product=" + product + ", quantity=" + quantity
				+ ", price=" + price + ", addedAt=" + addedAt + "]";
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public LocalDateTime getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(LocalDateTime addedAt) {
		this.addedAt = addedAt;
	}

}
