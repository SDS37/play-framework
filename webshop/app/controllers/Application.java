package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.main;

import play.db.jpa.Transactional;
import play.db.jpa.JPA;

import java.util.List;

import models.Category;

public class Application extends Controller {

	@Transactional
    public static Result main() {
		
    	List<Category> categories = JPA.em().createQuery(""
    			+ "SELECT category FROM Category category", Category.class).getResultList();
        
    	return ok(main.render(categories));   
	}

}
