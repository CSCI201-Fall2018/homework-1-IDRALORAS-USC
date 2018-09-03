import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(getterVisibility=Visibility.NONE)
public class Event {
	
	@JsonProperty("Title")
	private String Title;
	
	@JsonProperty("Time")
	private String Time;
	
	@JsonProperty("Date")
	private Date Date;
	
	@JsonCreator
	public Event(
			@JsonProperty("Title") String title, 
			@JsonProperty("Time") String time, 
			@JsonProperty("Date") Date date) {
		Title = title;
		Time = time;
		Date = date;
	}
	
	public String toString() {
		return Title + ", " + Time + ", " + Date.toString();
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}
}