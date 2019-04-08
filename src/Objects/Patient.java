package Objects;

/** This method is used for handling instances of patients
 * 
 *
 */
public class Patient {
	
	//Fields necessary for patients
	private String firstName;
	private String lastName;
	private String address;
	private String assignedDoctorName;
	private String email;
	private String phoneNumber;
	private String id;
	private String password;
	
	//Constructors
	public Patient(String firstName, String lastName, String address, String doctor, String email, 
			String phone, String id, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setAddress(address);
		setDoctor(doctor);
		setEmail(email);
		setNumber(phone);
		setId(id);
		setPassword(password);
	}
	
	public Patient() {
		this(null, null, null, null, null, null, null, null);
	}
	
	//Setters for info
	public void setFirstName(String name) { this.firstName = name;}
	public void setLastName(String lastName) { this.lastName = lastName;}
	public void setAddress(String address) { this.address = address;}
	public void setDoctor(String doctor) { this.assignedDoctorName = doctor;}
	public void setEmail(String email1) { this.email = email1;}
	public void setNumber(String number) { this.phoneNumber = number;}
	public void setId(String id) { this.id = id;}
	public void setPassword(String password) {this.password = password;}
	
	
	//Getters for info
	public String getFirstName() { return firstName;}
	public String getLastName() { return lastName;}
	public String getAddress() { return address;}
	public String getDoctor() { return assignedDoctorName;}
	public String getEmail() { return email;}
	public String getNumber() { return phoneNumber;}
	public String getId() { return id;}
	public String getPassword() {return password;}
	
}
