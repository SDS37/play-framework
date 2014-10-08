package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String email;
	private String address;
	private String phoneNumber;
	private String username;
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy= "user")
	public List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	public List<Cart> cartRegisters;
	
	public User(){
		cartRegisters = new ArrayList<>();
		orders = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@JsonIgnore
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@JsonIgnore
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonIgnore
	public double getTotal(){		
		double total= 0;
		for (Cart cart : cartRegisters) {
			total = total + cart.getSubtotal();
		}
		return total; 
	}
	
}