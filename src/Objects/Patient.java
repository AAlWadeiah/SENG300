package Objects;

/** This method is used for handling instances of patients
 * 
 * @author ali
 *
 */
public class Patient {
	
	//Variables necessary for patients
	private String firstName;
	private String lastName;
	private String address;
	private String assignedDoctorName;
	private String email;
	private String phoneNumber;
//	private Integer id;
	
	//Constructors
	public Patient(String firstName, String lastName, String address, String doctor, String email, String phone, Integer id) {
		setFirstName(firstName);
		setLastName(lastName);
		setAddress(address);
		setDoctor(doctor);
		setEmail(email);
		setNumber(phone);
//		setId(id);
	}
	public Patient() { }
	
	
	
	//Setters for info
	public void setFirstName(String name) { this.firstName = name;}
	public void setLastName(String lastName) { this.lastName = lastName;}
	public void setAddress(String address) { this.address = address;}
	public void setDoctor(String doctor) { this.assignedDoctorName = doctor;}
	public void setEmail(String email1) { this.email = email1;}
	public void setNumber(String number) { this.phoneNumber = number;}
//	public void setId(Integer id) { this.id = id;}
	
	
	//Getters for info
	public String getFirstName() { return firstName;}
	public String getLastName() { return lastName;}
	public String getAddress() { return address;}
	public String getDoctor() { return assignedDoctorName;}
	public String getEmail() { return email;}
	public String getNumber() { return phoneNumber;}
//	public Integer getId() { return id;}
	
}
