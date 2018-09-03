import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(getterVisibility=Visibility.NONE)
public class User {
	
	@JsonProperty("Name")
	private Name Name;
	
	@JsonProperty("Events")
	private Event[] Events;
	
	@JsonIgnore
	private ArrayList<Event> events;
	
	@JsonCreator
	public User(@JsonProperty("Name") Name Name, 
			@JsonProperty("Events") Event[] Events) {
		this.Name = Name;
		this.Events = Events;
		
		events = new ArrayList<Event>(Arrays.asList(this.Events));
		Collections.sort(events, new EventComparator());
	}
	
	public User(String firstName, String lastName) {
		Name = new Name(firstName, lastName);
		
		events = new ArrayList<Event>();
	}
	
	public void addEvent(String title, String time, Date date) {
		events.add(new Event(title, time, date));
		Collections.sort(events, new EventComparator());
	}
	
	public void addEvent(Event event) {
		events.add(event);
		Collections.sort(events, new EventComparator());
	}
	
	public Event removeEvent(int index) {
		Event removedEvent = events.remove(index - 1);
		Collections.sort(events, new EventComparator());
		return removedEvent;
	}
	
	public String toString() {
		
		String totalString = Name.toString();
		
		for(int x = 0; x < events.size(); ++x) {
			totalString += "\n\t\t" + (char)(x + 97) + ". " + events.get(x).toString();
		}
		
		return totalString;
	}

	public Name getName() {
		return Name;
	}

	public void setName(Name name) {
		Name = name;
	}

	public Event[] getEvents() {
		return Events;
	}
	
	public ArrayList<Event> getEventsList() {
		return events;
	}

	public void setEvents(Event[] events) {
		Events = events;
	}
}
