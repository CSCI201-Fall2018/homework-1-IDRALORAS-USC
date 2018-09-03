import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    public int compare(Event a, Event b) {
    	Date firstDate = a.getDate();
    	Date secondDate = b.getDate();
        if(firstDate.getYear() < secondDate.getYear()) {
        	return -1;
        } else if(firstDate.getYear() > secondDate.getYear()) {
        	return 1;
        } else {
        	if(firstDate.getMonthNum() < secondDate.getMonthNum()) {
        		return -1;
        	} else if(firstDate.getMonthNum() > secondDate.getMonthNum()) {
        		return 1;
        	} else {
        		if(firstDate.getDay() < secondDate.getDay()) {
        			return -1;
        		} else if(firstDate.getDay() > secondDate.getDay()) {
        			return 1;
        		} else {
        			return 0;
        		}
        	}
        }
    }
}