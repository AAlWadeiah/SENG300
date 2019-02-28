package Objects;

/** This method is used for handling instances of patients
 * 
 *
 */
public class Patient {
	
	//Variables necessary for patients
	private String name;
	private String address;
	private String assignedDoctorName;
	private String email;
	private String phoneNumber;
	
	
	
	//Constructors
	public Patient(String name1, String address1, String doctor, String email1, String phone) {
		setName(name1);
		setAddress(address1);
		setDoctor(doctor);
		setEmail(email1);
		setNumber(phone);
	}
	public Patient() { }
	
	
	
	//Setters for info
	public void setName(String name1) { this.name = name1;}
	public void setAddress(String address1) { this.address = address1;}
	public void setDoctor(String doctor) { this.assignedDoctorName = doctor;}
	public void setEmail(String email1) { this.email = email1;}
	public void setNumber(String number) { this.phoneNumber = number;}
	
	
	//Getters for info
	public String getName() { return name;}
	public String getAddress() { return address;}
	public String getDoctor() { return assignedDoctorName;}
	public String getEmail() { return email;}
	public String getNumber() { return phoneNumber;}
	
}
