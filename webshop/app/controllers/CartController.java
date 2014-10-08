package controllers;

import java.util.List;

import javax.persistence.TypedQuery;

import play.data.Form;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.cart;
import models.Cart;
import models.Product;
import models.User;

public class CartController extends Controller {
	
	@Transactional
	public static Result addProduct(int productId) {
		
		if (session().isEmpty()){
			
			return redirect(routes.UserAuthenticationController.userForm());
			
		} else {
		
		Cart cart = Form.form(Cart.class).bindFromRequest().get();		
		
		Product product = JPA.em().find(Product.class, productId);
		
		cart.setProduct(product);
			
		User currentUser = getCurrentUser();
		
		cart.setUser(currentUser);
				
		JPA.em().persist(cart);
		
		product.setQuantityInStock(product.getQuantityInStock() - cart.getProductQuantity());		
		
		return redirect(routes.ProductController.getProduct(productId));
		
		}
		
	}

	@Transactional
	@Security.Authenticated(UserAuthenticator.class)
	public static Result getCart() {	
		
		if (session().isEmpty()){
			
			return redirect(routes.UserAuthenticationController.userForm());
			
		} else {
		
		User currentUser = getCurrentUser();
			
		TypedQuery<Cart> query = JPA.em().createQuery(
				"SELECT cart FROM Cart cart "
				+ "WHERE user_id = :user_id", Cart.class);
		query.setParameter("user_id", currentUser.getId());
		List<Cart> matchingCart = query.getResultList();
		
		return ok(cart.render(matchingCart, currentUser));
		
		}
		
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

}
