import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(getterVisibility=Visibility.NONE)
public class Date {
	
	@JsonProperty("Month")
	private String Month;
	
	@JsonProperty("Day")
	private int Day;
	
	@JsonProperty("Year")
	private int Year;
	
	@JsonIgnore
	private String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	public Date(
			@JsonProperty("Month") String m, 
			@JsonProperty("Day") int d, 
			@JsonProperty("Year") int y) {
		Month = m;
		Day = d; 
		Year = y;
	}
	
	public Date(int m, int d, int y) {
		Month = months[m - 1];
		Day = d;
		Year = y;
	}
	
	public int getMonthNum() {
		for(int x = 0; x < months.length; x++) {
			if(Month.compareTo(months[x]) == 0) {
				return x - 1;
			}
		}
		
		return -1;
	}
	
	public String toString() {
		return Month + " " + Integer.toString(Day) + ", " + Integer.toString(Year);
	}

	public String getMonth() {
		return Month;
	}

	public void setMonth(String month) {
		Month = month;
	}

	public int getDay() {
		return Day;
	}

	public void setDay(int day) {
		Day = day;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}
}
