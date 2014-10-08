package controllers;

import java.util.List;

import models.Event;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.main;

public class Application extends Controller {
	
	@Transactional
    public static Result main() {
		
    	List<Event> events = JPA.em().createQuery(""
    			+ "SELECT event FROM Event event ORDER BY event.id DESC", Event.class).getResultList();

		return ok(main.render(events));
		
	}
	
}





