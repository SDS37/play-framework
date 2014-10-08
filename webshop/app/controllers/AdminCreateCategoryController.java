package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.createcategory;
import play.data.Form;
import play.db.jpa.Transactional;
import play.db.jpa.JPA;

import models.Category;

public class AdminCreateCategoryController extends Controller {
	
	@Security.Authenticated(AdminAuthenticator.class)
	public static Result createCategoryForm() {
		return ok(createcategory.render());
	}
	
	@Transactional
	public static Result createCategory() {
		
		Category category = Form.form(Category.class).bindFromRequest().get();
				
		category.setName(category.getName());
		category.setDescription(category.getDescription());
		
		JPA.em().persist(category);
		
		return redirect(routes.AdminController.categoriesManagement());		
	}
	
}
