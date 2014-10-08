package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import views.html.createorder;

//import views.html.orders;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import models.CustomizedOrder;
import models.ProductsShopped;
import models.Order;
import models.Cart;
import models.User;

public class OrderController extends Controller {
	
	@Transactional
	@Security.Authenticated(UserAuthenticator.class)
	public static Result createOrder() {
		
		Order currentOrder = new Order();
		
		currentOrder.setDate(getDate());	
		
		User currentUser = getCurrentUser();
		
		currentOrder.setUser(currentUser);
		
		JPA.em().persist(currentOrder);	
		
		TypedQuery<Cart> query = JPA.em().createQuery(
				"SELECT cart FROM Cart cart "
				+ "WHERE user_id = :user_id", Cart.class);
		query.setParameter("user_id", currentUser.getId());
		List<Cart> matchingCart = query.getResultList();
		
		for (Cart currentCart : matchingCart) {	
			
			ProductsShopped productsShopped = new ProductsShopped();
			
			productsShopped.setProductQuantity(currentCart.getProductQuantity());
			productsShopped.setSubtotal(currentCart.getSubtotal());
			
			productsShopped.setOrder(currentOrder);
			productsShopped.getOrder().setUser(currentUser);
			productsShopped.setProduct(currentCart.getProduct());
			
			JPA.em().persist(productsShopped);
			
			JPA.em().remove(currentCart);
		}	
		
		double total = currentUser.getTotal();
		
		getCustomizedOrdersToJson();
		
		return ok(createorder.render(currentOrder, matchingCart, currentUser, total));
	}

	public static String getDate() {
		Date date= new Date();
		Timestamp actualDate = new Timestamp(date.getTime());
		return actualDate.toString();
	}
	
	@Transactional
	public static User getCurrentUser() {
		
		String currentUsername = session().get("username");
		
		TypedQuery<User> query = JPA.em().createQuery(
				"SELECT user FROM User user "
				+ "WHERE username = :username", User.class);
		query.setParameter("username", currentUsername);
		List<User> matchingUsers = query.getResultList();
		
		if (matchingUsers.size() == 1) {
			User currentUser = matchingUsers.get(0);		
			return currentUser;
		}
		return null;
		
	}
	
	@Transactional
	public static void customizeOrder() {
				
		List<ProductsShopped> productsShopped = JPA.em().createQuery(""
				+ "SELECT productsShopped FROM ProductsShopped productsShopped", ProductsShopped.class).getResultList();
		
		User currentUser = getCurrentUser();
		
		for(ProductsShopped productsOrdered : productsShopped){
			
			CustomizedOrder customizedOrder = new CustomizedOrder();

			customizedOrder.setOrderId(productsOrdered.getOrder().getId());
			customizedOrder.setUsername(productsOrdered.getOrder().getUser().getUsername());
			customizedOrder.setAddress(productsOrdered.getOrder().getUser().getAddress());
			
			customizedOrder.setProduct(productsOrdered.getProduct().getName());
			customizedOrder.setProductQuantity(productsOrdered.getProductQuantity());
			customizedOrder.setSubtotal(productsOrdered.getSubtotal());
			
			customizedOrder.setProductsTotal(currentUser.getTotal());
			
			JPA.em().persist(customizedOrder);	
			
			JPA.em().remove(productsOrdered);			
			
		}
		
	}
	
	@Transactional
	public static Result getCustomizedOrdersToJson() {

		customizeOrder();
		
		List<CustomizedOrder> customizedOrder = JPA.em().createQuery(""
				+ "SELECT customizedOrder FROM CustomizedOrder customizedOrder", CustomizedOrder.class).getResultList();
		
		return ok(Json.toJson(customizedOrder));
	}
	
}