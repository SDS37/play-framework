package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.createuser;
import play.data.Form;
import play.db.jpa.Transactional;
import play.db.jpa.JPA;

import models.User;

public class CreateUserController extends Controller {
	
	public static Result createUserForm() {
		return ok(createuser.render());
	}
	
	@Transactional
	public static Result createUser() {
		
		User user = Form.form(User.class).bindFromRequest().get();
	
		user.setName(user.getName());
		user.setEmail(user.getEmail());
		user.setAddress(user.getAddress());
		user.setPhoneNumber(user.getPhoneNumber());
		user.setUsername(user.getUsername());
		user.setPassword(user.getPassword());
		
		JPA.em().persist(user);
			
		return redirect(routes.UserAuthenticationController.userForm());
	}	
	
}
