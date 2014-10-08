package controllers;

import java.util.List;

import javax.persistence.TypedQuery;

import models.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.usersession;

public class UserController extends Controller {

	@Transactional
	@Security.Authenticated(UserAuthenticator.class)
	public static Result userSession() {
		User user = getCurrentUser();
		String userName = user.getName().toString();
		return ok(usersession.render(userName));
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
