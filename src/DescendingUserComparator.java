import java.util.Comparator;

public class DescendingUserComparator implements Comparator<User> {
    public int compare(User a, User b) {
    	return b.getName().getLname().compareTo(a.getName().getLname());
    }
}