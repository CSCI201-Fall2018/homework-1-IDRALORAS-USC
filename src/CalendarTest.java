import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonAutoDetect(getterVisibility=Visibility.NONE)
public class CalendarTest {
	
	private static Calendar calendar;
	private static Scanner in;
	private static int[] numDays = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static boolean updated = false;
	private static String fileName = "";
	
	private static void addUser() {
		
		System.out.print("What is the user's name? ");
		
		String fullName = in.nextLine();
		
		String[] parts = fullName.split(" ");
		
		if(parts.length <= 1) {
			System.out.println("Invalid, must have first and last name.");
			addUser();
		} else if(parts.length >= 2) {
			
			String lastName = "";
			for(int x = 1; x < parts.length; x++) {
				lastName += parts[x];
				if(x != parts.length - 1) {
					lastName += " ";
				}
			}
			
			if(calendar.contains(parts[0], lastName)) {
				System.out.println("Invalid, user name already exists!");
				addUser();
			} else {
				calendar.addUser(parts[0], lastName);
				updated = true;
			}
		}
	}
	
	private static void removeUser() {
		ArrayList<User> users = calendar.getUsersList();
		
		int count = 1;
		
		for(User user: users) {
			System.out.println("\t" + count + ") " + user.getName().toString());
			count++;
		}
		
		System.out.print("\nWho would you like to remove? ");
		
		int index = Integer.parseInt(in.nextLine());
		
		while(index >= count || index <= 0) {
			System.out.println("Invalid option, please choose another one\n");
			System.out.print("Who would you like to remove? ");
			index = Integer.parseInt(in.nextLine());
		}
		
		calendar.removeUser(index);
		updated = true;
	}
	
	private static void addEvent() {
		
		ArrayList<User> users = calendar.getUsersList();
		
		int count = 1;
		
		System.out.print("\n");
		
		for(User user: users) {
			System.out.println("\t" + count + ") " + user.getName().toString());
			count++;
		}
		
		System.out.println("\nTo which user would you like to add an event?");
		
		int index = Integer.parseInt(in.nextLine());
		
		while(index >= count || index <= 0) {
			System.out.println("\nInvalid option, please choose another one.");
			System.out.println("\nTo which user would you like to add an event?");
			index = Integer.parseInt(in.nextLine());
		}
		
		System.out.print("\nWhat is the title of the event? ");
		String title = in.nextLine();
		
		System.out.print("\nWhat time is the event? ");
		String time = in.nextLine();
		
		System.out.print("\nWhat month? ");
		int month = Integer.parseInt(in.nextLine());
		
		while(month > 12 || month < 1) {
			System.out.println("\nInvalid month, please choose a number between 1 - 12");
			System.out.print("\nWhat month? ");
			month = Integer.parseInt(in.nextLine());
		}
		
		System.out.print("\nWhat day? ");
		int day = Integer.parseInt(in.nextLine());
		
		while(day > numDays[month - 1] || day < 1) {
			System.out.println("\nInvalid day, please choose a number between 1 - " + numDays[month - 1]);
			System.out.print("\nWhat day? ");
			day = Integer.parseInt(in.nextLine());
		}
		
		@SuppressWarnings("deprecation")
		int currentYear = (new java.util.Date()).getYear() + 1900;
		
		System.out.print("\nWhat year? ");
		int year = Integer.parseInt(in.nextLine());
		
		while(year < currentYear) {
			System.out.println("\nInvalid year, please choose a year from " + currentYear + " onward.");
			System.out.print("\nWhat year? ");
			year = Integer.parseInt(in.nextLine());
		}
		
		Event newEvent = new Event(title, time, new Date(month, day, year));
		
		calendar.addEvent(index, newEvent);
		updated = true;
	}
	
	private static void removeEvent() {
		
		ArrayList<User> users = calendar.getUsersList();
		
		int count = 1;
		
		for(User user: users) {
			System.out.print("\n\t" + count + ") " + user.getName().toString());
			count++;
		}
		
		System.out.print("\n\nFrom which user would you like to delete an event? ");
		
		int pickedUser = Integer.parseInt(in.nextLine());
		
		while(pickedUser < 1 || pickedUser >= count) {
			System.out.println("\nInvalid option, please a valid number.");
			System.out.print("From which user would you like to delete an event? ");
			pickedUser = Integer.parseInt(in.nextLine());
		}
		
		ArrayList<Event> events = users.get(pickedUser - 1).getEventsList();
		
		if(events == null || events.size() == 0) {
			System.out.println("\nCalendar is empty."); return;
		}
		
		for(int x = 0; x < events.size(); x++) {
			System.out.print("\n\t" + (x + 1) + ") " + events.get(x).toString());
		}
		
		System.out.print("\n\nWhich event would you like to delete? ");
		
		int deletedEvent = Integer.parseInt(in.nextLine());
		
		while(deletedEvent < 1 || deletedEvent > events.size()) {
			System.out.println("\nInvalid event, please choose a valid event.");
			System.out.print("Which event would you like to delete? ");
			deletedEvent = Integer.parseInt(in.nextLine());
		}
		
		Event removedEvent = calendar.removeEvent(pickedUser, deletedEvent);
		System.out.println(removedEvent.getTitle() + " has been deleted.");
		updated = true;
	}
	
	private static void sortUsers() {
		
		System.out.println("\n\t1) Ascending (A - Z)\n\t2) Descending (Z - A)\n");
		System.out.print("How would you like to sort? ");
		
		int sortingMethod = Integer.parseInt(in.nextLine());
		
		while(sortingMethod < 1 || sortingMethod > 2) {
			System.out.println("\nInvalid sorting method, please choose a valid method.");
			System.out.print("How would you like to sort? ");
			sortingMethod = Integer.parseInt(in.nextLine());
		}
		
		calendar.sort(sortingMethod == 1);
		
		ArrayList<User> users = calendar.getUsersList();
		
		System.out.print("\n");
		
		for(int x = 0; x < users.size(); x++) {
			System.out.print("\t" + (x + 1) + ") ");
			System.out.println(users.get(x).getName().toString());
		}
		
		updated = true;
	}
	
	private static void saveFile() {
		
		ObjectMapper mapper = new ObjectMapper();
		
		calendar.setUsers(calendar.getUsersList().toArray(calendar.getUsers()));
		
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), calendar);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		updated = false;
	}
	
	private static void runChoice(int choice) {
		
		switch(choice) {
			case 1:
				calendar.display();
				break;
				
			case 2:
				addUser();
				break;
				
			case 3:
				removeUser();
				break;
				
			case 4:
				addEvent();
				break;
				
			case 5:
				removeEvent();
				break;
				
			case 6:
				sortUsers();
				break;
				
			case 7:
				saveFile();
				break;
				
			default: break;
		}
	}
	
	private static void displayOptions() {
		System.out.println("\n\t1) Display User's Calendar");
		System.out.println("\t2) Add User");
		System.out.println("\t3) Remove User");
		System.out.println("\t4) Add Event");
		System.out.println("\t5) Delete Event");
		System.out.println("\t6) Sort Users");
		System.out.println("\t7) Write File");
		System.out.println("\t8) Exit\n");
		System.out.print("What would you like to do? ");
	}
	
	private static void openFile() {
		
		String inputFile;
		
		while(true) {
			
			System.out.print("What is the name of the input file? ");
			
			inputFile = in.nextLine();
			
			try {
				FileReader fr = new FileReader(inputFile);
				BufferedReader br = new BufferedReader(fr);
				
				br.close(); fr.close();
				break;
				
			} catch(FileNotFoundException fnfe) {
				System.out.println("That file could not be found.");
			} catch(IOException ioe) {
				System.out.println("That file is not a well-formed JSON file.");
			}
			
		}
		
		fileName = inputFile;
		File jsonFile = new File(inputFile);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
		try {
		    calendar = mapper.readValue(jsonFile, Calendar.class);
		} catch (JsonGenerationException e) {
		    e.printStackTrace();
		} catch (JsonMappingException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
				
	}
	
	private static void preExit() {
		if(updated) {
			System.out.println("\nChanges have been made since the file was last saved.\n\t1) Yes\n\t2) No");
			System.out.print("Would you like to save the file before exiting? ");
			
			int save = Integer.parseInt(in.nextLine());
			
			if(save != 1 && save != 2) {
				System.out.println("Please enter a valid value.");
				System.out.print("Would you like to save the file before exiting? ");
				save = Integer.parseInt(in.nextLine());
			}
			
			if(save == 1) {
				saveFile();
				System.out.println("\nFile was saved.\n");
			} else {
				System.out.println("\nFile was not saved.\n");
			}
		}
	}
	
	public static void main(String[] args) {
				
		in = new Scanner(System.in);
		
		openFile();
		displayOptions();
		
		int choice = Integer.parseInt(in.nextLine());
		
		while(choice != 8) {
			
			runChoice(choice);
			
			displayOptions();
			choice = Integer.parseInt(in.nextLine());
		}
		
		preExit();
		
		in.close();
		System.out.println("Thank you for using my program!");
	}
}