import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(getterVisibility=Visibility.NONE)
public class Calendar {
	
	@JsonProperty("Users")
	private User[] Users;
	
	@JsonIgnore
	private ArrayList<User> users;
	
	@JsonIgnore
	private Set<String> names;
	
	@JsonCreator
	public Calendar(@JsonProperty("Users") User[] users) {
		
		Users = users;
		this.users = new ArrayList<User>(Arrays.asList(this.Users));
		names = new HashSet<String>();
	}
	
	public void sort(boolean ascending) {
		if(ascending) {
			Collections.sort(users, new AscendingUserComparator());
		} else {
			Collections.sort(users, new DescendingUserComparator());
		}
	}
	
	public boolean contains(String firstName, String lastName) {
		return names.contains(new Name(firstName, lastName).toString().toLowerCase());
	}
	
	public void display() {
		
		System.out.print("\n");
		
		for(int x = 0; x < users.size(); x++) {
			System.out.print("\t" + (x + 1) + ") ");
			System.out.println(users.get(x).toString());
		}
	}
	
	public void addUser(String firstName, String lastName) {
		User addedUser = new User(firstName, lastName);
		users.add(addedUser);
		names.add(addedUser.getName().toString().toLowerCase());
	}
	
	public void removeUser(int index) {
		
		names.remove(users.get(index - 1).getName().toString().toLowerCase());
		users.remove(index - 1);
	}
	
	public void addEvent(int index, Event event) {
		users.get(index - 1).addEvent(event);
	}
	
	public Event removeEvent(int userIndex, int eventIndex) {
		return users.get(userIndex - 1).removeEvent(eventIndex);
	}

	public User[] getUsers() {
		return Users;
	}

	public void setUsers(User[] users) {
		Users = users;
	}
	
	public ArrayList<User> getUsersList() {
		return this.users;
	}
}