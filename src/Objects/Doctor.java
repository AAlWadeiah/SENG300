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
	private String department;//specialty
	private String phoneNumber;
	private String email;
	private String address;
	private Integer id;
	private String password;
	private Schedule schedule;
	private Availability availability;

	//Constructors
	public Doctor(Integer id, String fname,String lname, String department, String number, 
			String email, String address, String password) {
		setId(id);
		setfName(fname);
		setlName(lname);
		setDepartment(department);
		setPhoneNumber(number);
		setEmail(email);
		setAddress (address);
		setPassword(password);
	}

	public Doctor(Integer id, String fname,String lname, String department, String number, String email, String address) {
		this(id, fname, lname, department, number, email, address, null);
	}
	public Doctor() {
		this(null, null, null, null, null, null, null, null);
	}

	//setters for Doctor fields
	public void setfName(String fname) { this.firstname = fname;}
	public void setlName(String lname) {this.lastname = lname;}
	public void setPhoneNumber(String number1) { this.phoneNumber = number1;}
	public void setDepartment(String department1) {this.department = department1;}
	public void setEmail(String email){this.email = email;}
	public void setAddress(String address){this.address = address;}
	public void setSchedule(Schedule schedule1) {this.schedule = schedule1;}
	public void setId(Integer id) {this.id = id;}
	public void setPassword(String password) {this.password = password;}

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


}

