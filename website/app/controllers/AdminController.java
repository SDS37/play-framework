package controllers;

import java.util.List;

import models.Event;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.adminsession;
import views.html.createevent;
import views.html.deleteevent;
import views.html.updateevent;

public class AdminController extends Controller {

	@Transactional
	@Security.Authenticated(AdminAuthenticator.class)
	public static Result adminSession() {
		
		List<Event> events = JPA.em().createQuery(""
    			+ "SELECT event FROM Event event ORDER BY event.id DESC", Event.class).getResultList();
		
		return ok(adminsession.render(events));
		
	}
	
	@Security.Authenticated(AdminAuthenticator.class)
	public static Result createEventForm() {
		return ok(createevent.render());
	}
	
	@Transactional
	@Security.Authenticated(AdminAuthenticator.class)
	public static Result deleteEventForm() {
		
		List<Event> events = JPA.em().createQuery(""
    			+ "SELECT event FROM Event event ORDER BY event.id DESC", Event.class).getResultList();
		
		return ok(deleteevent.render(events));
	}
	
	@Transactional
	@Security.Authenticated(AdminAuthenticator.class)
	public static Result updateEventForm() {
		
		List<Event> events = JPA.em().createQuery(""
    			+ "SELECT event FROM Event event ORDER BY event.id DESC", Event.class).getResultList();
		
		return ok(updateevent.render(events));
	}

}


