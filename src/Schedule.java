

import Objects.Doctor;
import Objects.Patient;

/** I really don't know what to do here, thanks love you all cuties
 *  addAppointment and removeAppointment are not implemented yet
 *  
 * This class will be specific to each doctor
 * @author ali
 *
 */
public class Schedule {
	
	//Variables
	private Doctor doctor;
	
	
	//Constructors 
	public Schedule () {}
	public Schedule (Doctor doctor1) {setDoctor(doctor1);}
	
	
	//Unimplemented class for creating Schedule
	public void createSchedule() {}
	
	
	//Appointment Methods
	public void addAppointment(String time, Patient patient)
	{
		if (patient.getDoctor().equals(doctor.getName())) {}	//patient class only stores doctor Name
	}															//so we compare it to the name of the doctor who holds this schedule
	public void removeAppointment(String time, Patient patient)
	{
		if (patient.getDoctor().equals(doctor.getName())) {}
	}
	
	
	//Setter and getter for doctor
	public void setDoctor(Doctor doctor1) {}
	public Doctor getDoctor() {return doctor;}

}
