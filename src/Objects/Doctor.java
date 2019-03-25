package Objects;

/** This class is used to handle doctors in the system
 *  
 *
 */
public class Doctor
{
	//Fields necessary for each doctor
	private String firstname;
	private String lastname;
	private String department;
	private String phoneNumber;
	private String email;
	private String address;
	private Integer id;
	private String password;
	private Schedule schedule;
	private Availability availability;

	//Constructors
	public Doctor(Integer id, String fname,String lname, String department, String number, 
			String email, String address, String password, Schedule schedule, Availability availability) {
		setId(id);
		setfName(fname);
		setlName(lname);
		setDepartment(department);
		setPhoneNumber(number);
		setEmail(email);
		setAddress(address);
		setPassword(password);
		setSchedule(schedule);
		setAvailability(availability);
	}
	public Doctor(Integer id, String fname,String lname, String department, String number, 
			String email, String address, String password) {
		this(id, fname, lname, department, number, email, address, password, null, null);
	}
	public Doctor() {
		this(null, null, null, null, null, null, null, null, null, null);
	}

	//setters for Doctor fields
	public void setfName(String fname) { this.firstname = fname;}
	public void setlName(String lname) {this.lastname = lname;}
	public void setPhoneNumber(String number) { this.phoneNumber = number;}
	public void setDepartment(String department) {this.department = department;}
	public void setEmail(String email){this.email = email;}
	public void setAddress(String address){this.address = address;}
	public void setSchedule(Schedule schedule) {this.schedule = schedule;}
	public void setId(Integer id) {this.id = id;}
	public void setPassword(String password) {this.password = password;}
	public void setAvailability(Availability availability) {this.availability = availability;}

	//getters for Doctor fields
	public String getFirstname() {return firstname;}
	public String getLastname() {return lastname;}
	public String getPhoneNumber() {return phoneNumber;}
	public String getDepartment() {return department;}
	public String getEmail() {return email;}
	public String getAddress() {return address;}
	public Schedule getSchedule() {return schedule;}
	public Integer getId() {return id;}
	public String getPassword() {return password;}
	public Availability getAvailability() {return availability;}
}

