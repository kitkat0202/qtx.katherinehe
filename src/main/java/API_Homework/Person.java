package API_Homework;

public class Person {
	private int id;
	private String email;
	private String firstname;
	private String lastname;
	private String avatar;
	
	public Person(int id, String email, String firstname, String lastname, String avatar) {
		super();
		this.id = id;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.avatar = avatar;
	}
	
	public int getID() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getFirstName() {
		return firstname;
	}
	
	public String getLastName() {
		return lastname;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setID(int id ) {
		this.id =id;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}
	
	public void setLastName(String lastname) {
		this.lastname = lastname;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
