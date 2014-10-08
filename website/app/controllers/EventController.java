package controllers;

import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import play.db.jpa.Transactional;
import play.db.jpa.JPA;
import models.Event;

public class EventController extends Controller {

	@Transactional	
	public static Result createEvent(){
	
		Event event = Form.form(Event.class).bindFromRequest().get();
	
		boolean nameIsEmpty = "".equals(event.getName());
		boolean descriptionIsEmpty = "".equals(event.getDescription());
		boolean addressIsEmpty = "".equals(event.getAddress());
		
		if (nameIsEmpty || descriptionIsEmpty || addressIsEmpty) {
			if (nameIsEmpty) {
				flash().put("name-empty", "yes");
			}
			if (descriptionIsEmpty) {
				flash().put("description-empty", "yes");
			}
			if (addressIsEmpty) {
				flash().put("address-empty", "yes");
			}
			return redirect(routes.EventController.createEvent());
		}
		
		event.setDay(event.getDay());
		event.setMonth(event.getMonth());
		event.setYear(event.getYear());
		event.setName(event.getName());
		event.setDescription(event.getDescription());
		event.setAddress(event.getAddress());
		
		JPA.em().persist(event);
		
		return redirect(routes.AdminController.adminSession());
	}

	@Transactional
	public static Result deleteEvent() {
			
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		
		Integer eventId = Integer.parseInt(form.get("eventToErase")[0]);
		
		TypedQuery<Event> query = JPA.em().createQuery(
				"SELECT event FROM Event event "
				+ "WHERE id = :eventId", Event.class);
		query.setParameter("eventId", eventId);
		
		List<Event> matchingEvents = query.getResultList();
		
		if (matchingEvents.size() == 1) {
			Event matchingEvent = matchingEvents.get(0);		
			JPA.em().remove(matchingEvent);
		}

		return redirect(routes.EventController.deleteEvent());
	}
	
	@Transactional
	public static Result updateEvent() {
			
		Event event = new Event();
		
		Map<String, String[]> form = request().body().asFormUrlEncoded();

		Integer eventId = Integer.parseInt(form.get("eventToUpdate")[0]);
		Integer day = Integer.parseInt(form.get("day")[0]);
		Integer month = Integer.parseInt(form.get("month")[0]);
		Integer year = Integer.parseInt(form.get("year")[0]);
		String name = form.get("name")[0];
		String description = form.get("description")[0];
		String address = form.get("address")[0];
		
		boolean nameIsEmpty = "".equals(name);
		boolean descriptionIsEmpty = "".equals(description);
		boolean addressIsEmpty = "".equals(address);
		
			if (eventId == 0){			
				flash().put("eventId-empty", "yes");
				return redirect(routes.EventController.updateEvent());
			} 
			
			if (nameIsEmpty || descriptionIsEmpty || addressIsEmpty) {
				if (nameIsEmpty) {
					flash().put("name-empty", "yes");
				}
				if (descriptionIsEmpty) {
					flash().put("description-empty", "yes");
				}
				if (addressIsEmpty) {
					flash().put("address-empty", "yes");
				}
				return redirect(routes.EventController.updateEvent());
			}
			
			TypedQuery<Event> query = JPA.em().createQuery(
					"SELECT event FROM Event event "
					+ "WHERE id = :eventId", Event.class);
			query.setParameter("eventId", eventId);
				
			List<Event> matchingEvents = query.getResultList();
			
			if (matchingEvents.size() == 1) {					
				event = matchingEvents.get(0);					
				event.setDay(day);
				event.setMonth(month);
				event.setYear(year);
				event.setName(name);
				event.setDescription(description);
				event.setAddress(address);			
				
				JPA.em().persist(event);
			}
		return redirect(routes.EventController.updateEvent());
	}
	
}

