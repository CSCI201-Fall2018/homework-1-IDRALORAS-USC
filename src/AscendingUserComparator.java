import java.util.Comparator;

public class AscendingUserComparator implements Comparator<User> {
    public int compare(User a, User b) {
    	return a.getName().getLname().compareTo(b.getName().getLname());
    }
}