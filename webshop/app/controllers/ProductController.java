package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.listallproducts;
import views.html.notfound;
import views.html.singleproduct;
import play.db.jpa.Transactional;
import play.db.jpa.JPA;

import java.util.List;

import models.Product;

public class ProductController extends Controller {
	
	@Transactional
	public static Result getProducts() {

		List<Product> products = JPA.em().createQuery(""
				+ "SELECT product FROM Product product", Product.class).getResultList();

		return ok(listallproducts.render(products));
	}

	@Transactional
	public static Result getProduct(int id) {

		Product product = JPA.em().find(Product.class, id);

		if (product == null) {return notFound(notfound.render("Product"));}
		
		return ok(singleproduct.render(product));
	}
	
	@Transactional
	public static Result getProductsToJson() {

		List<Product> products = JPA.em().createQuery(""
				+ "SELECT product FROM Product product", Product.class).getResultList();

		return ok(Json.toJson(products));
	}

	@Transactional
	public static Result getProductToJson(int id) {

		Product product = JPA.em().find(Product.class, id);

		if (product == null) {return notFound(notfound.render("Product"));}
		
		return ok(Json.toJson(product));
	}
	
}