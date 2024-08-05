package com.tcs.project.checkoutlist;

import com.tcs.project.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CheckOutList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String shippingAdress;
	private String billingAdress;
	private String paymentMethod;

	@ManyToOne
	@JoinColumn(name = "user_id") // This column will be used as a foreign key in the CheckOutList table
	private User user;

	@Override
	public String toString() {
		return "CheckOutList [id=" + id + ", shippingAdress=" + shippingAdress + ", billingAdress=" + billingAdress
				+ ", paymentMethod=" + paymentMethod + ", user=" + user + "]";
	}

	public CheckOutList(Long id, String shippingAdress, String billingAdress, String paymentMethod, User user) {
		super();
		this.id = id;
		this.shippingAdress = shippingAdress;
		this.billingAdress = billingAdress;
		this.paymentMethod = paymentMethod;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShippingAdress() {
		return shippingAdress;
	}

	public void setShippingAdress(String shippingAdress) {
		this.shippingAdress = shippingAdress;
	}

	public String getBillingAdress() {
		return billingAdress;
	}

	public void setBillingAdress(String billingAdress) {
		this.billingAdress = billingAdress;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CheckOutList() {

	}

}
