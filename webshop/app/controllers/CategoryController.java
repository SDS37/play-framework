package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.listallcategories;
import views.html.notfound;
import views.html.singlecategory;
import play.db.jpa.Transactional;
import play.db.jpa.JPA;

import java.util.List;

import models.Category;

public class CategoryController extends Controller {

	@Transactional
	public static Result getCategories() {

		List<Category> categories = JPA.em().createQuery(""
				+ "SELECT category FROM Category category", Category.class).getResultList();

		return ok(listallcategories.render(categories));
	}
	
	@Transactional
	public static Result getCategory(int id) {

		Category category = JPA.em().find(Category.class, id);
			
		if (category == null) {return notFound(notfound.render("Category"));}

		return ok(singlecategory.render(category));	
	}

	@Transactional
	public static Result getCategoriesToJson() {

		List<Category> categories = JPA.em().createQuery(""
				+ "SELECT category FROM Category category", Category.class).getResultList();

		return ok(Json.toJson(categories));
		
	}

	@Transactional
	public static Result getCategoryToJson(int id) {

		Category category = JPA.em().find(Category.class, id);
			
		if (category == null) {return notFound(notfound.render("Category"));}
		
		return ok(Json.toJson(category));	
	}
	
}
