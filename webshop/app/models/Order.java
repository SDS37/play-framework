package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Orders")
public class Order {	
	
	@Id 
	@GeneratedValue
	private int id;
	private String date;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	public List<ProductsShopped> productsShopped;
	
	public Order(){
		productsShopped = new ArrayList<>();
	}

	public int getId() {
		return id;
	}
	
	@JsonIgnore
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
	public User getUser(){
		return user;
	}

}