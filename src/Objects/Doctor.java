package Objects;

import Schedule;

/** This class is used to handle doctors in the system
 *  
 * @author ali
 *
 */
public class Doctor 
{
	//Variables necessary for each doctor
	private String name;
	private String department;
	private String number;
	private Schedule schedule;
	
	//Constructors
	public Doctor(String name1, String department1, String number1, Schedule schedule1) {
		setName(name1);
		setDepartment(department1);
		setNumber(number1);
		setSchedule(schedule1);
		}
	public Doctor() { }
	
	
	//setters for Doctor variables
	public void setName(String name1) { this.name = name1;}
	public void setNumber(String number1) { this.number = number1;}
	public void setDepartment(String department1) {this.department = department1;}
	public void setSchedule(Schedule schedule1) {this.schedule = schedule1;}
	
	
	
	//getters for Doctor variables
	public String getName() {return name;}
	public String getNumber() {return number;}
	public String getDepartment() {return department;}
	public Schedule getSchedule() {return schedule;}
	
}

