package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.createproduct;
import play.db.jpa.Transactional;
import play.db.jpa.JPA;

import javax.persistence.TypedQuery;

import java.util.List;
import java.util.Map;

//	import play.data.Form;

import models.Category;
import models.Product;

public class AdminCreateProductController extends Controller {
	
	@Transactional
	@Security.Authenticated(AdminAuthenticator.class)
	public static Result createProductForm(){	
		List<Category> categories = JPA.em().createQuery(""
    			+ "SELECT category FROM Category category", Category.class).getResultList();
		return ok(createproduct.render(categories));
	}

	@Transactional	
	public static Result createProduct(){
	
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		
		String name = form.get("name")[0];
		String description = form.get("description")[0];
		Double cost = Double.parseDouble(form.get("cost")[0]);
		Double RRP = Double.parseDouble(form.get("RRP")[0]);
		Integer quantityInStock = Integer.parseInt(form.get("quantityInStock")[0]);
		Integer categoryId = Integer.parseInt(form.get("category-id")[0]);
		
		TypedQuery<Category> query = JPA.em().createQuery(
				"SELECT category FROM Category category "
				+ "WHERE category.id = :categoryId", Category.class);
		query.setParameter("categoryId", categoryId);

		List<Category> matchingCategories = query.getResultList();
				
		Product product = new Product();
	
		product.setName(name);
		product.setDescription(description);
		product.setCost(cost);
		product.setRRP(RRP);
		product.setQuantityInStock(quantityInStock);
		product.category = matchingCategories;
		JPA.em().persist(product);
		
		return redirect(routes.AdminController.productsManagement());
	}
	
}