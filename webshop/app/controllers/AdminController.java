package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.adminsession;
import views.html.categoriesmanagement;
import views.html.productsmanagement;

public class AdminController extends Controller {

	@Security.Authenticated(AdminAuthenticator.class)
	public static Result adminSession() {
		return ok(adminsession.render());
	}
	
	@Security.Authenticated(AdminAuthenticator.class)
	public static Result categoriesManagement() {
		return ok(categoriesmanagement.render());
	}
	
	@Security.Authenticated(AdminAuthenticator.class)
	public static Result productsManagement() {
		return ok(productsmanagement.render());
	}
	
}