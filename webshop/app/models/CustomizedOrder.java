package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class CustomizedOrder {
	
	@Id
	@GeneratedValue
	private int id;
	private int orderId;
	private String userUsername;
	private String userAddress;
	private String productName;
	private int productQuantity;
	private double productSubtotal;
	private double productsTotal;
	
	public CustomizedOrder() {}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public String getUsername() {
		return userUsername;
	}

	public void setUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public String getAddress() {
		return userAddress;
	}

	public void setAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getProduct() {
		return productName;
	}

	public void setProduct(String productName) {
		this.productName = productName;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	public double getSubtotal() {
		return productSubtotal;
	}

	public void setSubtotal(double productSubtotal) {
		this.productSubtotal = productSubtotal;
	}

	public double getProductsTotal() {
		return productsTotal;
	}

	public void setProductsTotal(double productsTotal) {
		this.productsTotal = productsTotal;
	}
	
}