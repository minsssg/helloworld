package helloworld.model;

public class User {

	private String id;
	private String password;
	private String name;
	private String gender;
	private String phoneNumber;
	
	public User(
		String id,
		String password,
		String name,
		String gender,
		String phoneNumber) {
		
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
	}				

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", gender=" + gender + ", phoneNumber="
				+ phoneNumber + "]";
	}
}
