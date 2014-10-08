package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.adminlogin;
import play.data.Form;
import play.db.jpa.Transactional;
import play.db.jpa.JPA;

import javax.persistence.TypedQuery;

import java.util.List;

import models.Employee;

public class AdminAuthenticationController extends Controller {
	
	public static Result adminForm() {
		session().clear();
		return ok(adminlogin.render());
	}
	
	@Transactional
	public static Result adminLogin() {

		Employee employee = Form.form(Employee.class).bindFromRequest().get();
		
		boolean usernameIsEmpty = "".equals(employee.getUsername());
		boolean passwordIsEmpty = "".equals(employee.getPassword());

		if (usernameIsEmpty || passwordIsEmpty) {
			if (usernameIsEmpty) {
				flash().put("username-empty", "yes");
			}
			if (passwordIsEmpty) {
				flash().put("password-empty", "yes");
			}
			return redirect(routes.AdminAuthenticationController.adminForm());
		}

		TypedQuery<Employee> query = JPA.em().createQuery(
				"SELECT employee FROM Employee employee "
				+ "WHERE employee.username = :username "
				+ "AND employee.password = :password", Employee.class);
		query.setParameter("username", employee.getUsername());
		query.setParameter("password", employee.getPassword());

		List<Employee> matchingEmployees = query.getResultList();
		
		if(matchingEmployees.size() == 1) {
			session().put("username", employee.getUsername());
			return redirect(routes.AdminController.adminSession());
		} else {
			flash().put("no-username-password-match", "yes");
			return redirect(routes.AdminAuthenticationController.adminForm());
		} 
	} 

	public static Result adminLogout() {
		return redirect(routes.AdminAuthenticationController.adminForm());
	}
	
}
