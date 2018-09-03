import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(getterVisibility=Visibility.NONE)
public class Name {
	@JsonProperty("Fname")
	private String Fname;

	@JsonProperty("Lname")
	private String Lname;

	@JsonCreator
	public Name(@JsonProperty("Fname") String first, @JsonProperty("Lname") String last) {
		Fname = first;
		Lname = last;
	}

	public String toString() {
		return Lname + ", " + Fname;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	public String getLname() {
		return Lname;
	}

	public void setLname(String lname) {
		Lname = lname;
	}
}
