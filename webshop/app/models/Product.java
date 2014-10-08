package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String description;
	private double cost;
	private double RRP;
	private int quantityInStock;
	
	@ManyToMany
	public List<Category> category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	public List<Cart> cartRegisters;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	public List<ProductsShopped> productsShopped;
	
	public Product(){
		cartRegisters = new ArrayList<>();
		productsShopped = new ArrayList<>();
	}	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getRRP() {
		return RRP;
	}

	public void setRRP(double rrp) {
		RRP = rrp;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}	
	
}