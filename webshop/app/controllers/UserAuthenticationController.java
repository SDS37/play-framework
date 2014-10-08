package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.userlogin;
import play.data.Form;
import play.db.jpa.Transactional;
import play.db.jpa.JPA;

import javax.persistence.TypedQuery;

import java.util.List;

import models.User;

public class UserAuthenticationController extends Controller {
	
	public static Result userForm() {
		session().clear();
		return ok(userlogin.render());
	}
	
	@Transactional
	public static Result userLogin() {

		User user = Form.form(User.class).bindFromRequest().get();

		boolean usernameIsEmpty = "".equals(user.getUsername());
		boolean passwordIsEmpty = "".equals(user.getPassword());

		if (usernameIsEmpty || passwordIsEmpty) {
			if (usernameIsEmpty) {
				flash().put("username-empty", "yes");
			}
			if (passwordIsEmpty) {
				flash().put("password-empty", "yes");
			}
			return redirect(routes.UserAuthenticationController.userForm());
		}

		TypedQuery<User> query = JPA.em().createQuery(
				"SELECT user FROM User user "
				+ "WHERE user.username = :username "
				+ "AND user.password = :password", User.class);
		query.setParameter("username", user.getUsername());
		query.setParameter("password", user.getPassword());

		List<User> matchingUsers = query.getResultList();
		
		if(matchingUsers.size() == 1) {
			session().put("username", user.getUsername());
			return redirect(routes.UserController.userSession());
		} else {
			flash().put("no-username-password-match", "yes");
			return redirect(routes.UserAuthenticationController.userForm());
		} 		
	} 
	
	public static Result userLogout() {
		return redirect(routes.UserAuthenticationController.userForm());
	}
	
}
