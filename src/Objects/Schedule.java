package Objects;

import java.util.*;

/** 
 *  
 * This class will be specific to each doctor
 * addAppointment and removeAppointment are not implemented yet
 */
public class Schedule {

	//Fields
	private Integer doctorId;
	private HashMap<Integer, ArrayList<Appointment>> currentAppointments;

	//Constructors 
	public Schedule () {}
	public Schedule (Integer doctorId) {
		setDoctorId(doctorId);
		setCurrentAppointments(new HashMap<Integer, ArrayList<Appointment>>());
	}	

	/**
	 * Creates a new appointment for the specified patient who is indicated by the given ID.
	 * If no appointments have been previously made for this patient, then a new entry in the currentAppointments HashMap will be created for the patient.
	 * @param patientId The ID of the patient to make the appointment for.
	 * @param date The date of the appointment
	 * @param time The time of the appointment
	 */
	public void addAppointment(Integer patientId, String date, String time) {
		if (getCurrentAppointments().containsKey(patientId)) {
			getCurrentAppointments().get(patientId).add(new Appointment(patientId, date, time));
		} else {
			ArrayList<Appointment> newApptSet = new ArrayList<>();
			newApptSet.add(new Appointment(patientId, date, time));
			getCurrentAppointments().put(patientId, newApptSet);
		}
	}
	
	public Appointment getAppointment(Integer patientId, Integer appointmentId) {
		return getCurrentAppointments().get(patientId).stream().filter(appt -> appointmentId.equals(appt.getAppointmentId())).findFirst().orElse(null);
	}
	
	// TODO: implement retrieval of appointment by date and time
	public Appointment getAppointment(Integer patientId, String date, String time) {
		return null;
	}

	/**
	 * Removes the entry of the given patient from the currentApointments HashMap.
	 * @param patientId The ID of the patient to remove from the appointments record.
	 */
	public void removePatient(Integer patientId) {
		if (getCurrentAppointments().containsKey(patientId)) {
			getCurrentAppointments().remove(patientId);
		} else {
			System.out.println("Patient " + patientId + " does not exist");
		}
	}
	
	public boolean removeAppointment(Integer patientId, Integer appointmentId) {
		boolean removed = getCurrentAppointments().get(patientId).removeIf(a -> a.getAppointmentId().equals(appointmentId));
		return removed;
	}
	
	// TODO: implement removal of appointment by date and time
	public boolean removeAppointment(Integer patientId, String date, String time) {
		return false;
	}


	//Setters
	public void setDoctorId(Integer doctorId) {this.doctorId = doctorId;}
	public void setCurrentAppointments(HashMap<Integer, ArrayList<Appointment>> currAppt) {this.currentAppointments = currAppt;}

	// Getters
	public Integer getDoctor() {return doctorId;}
	public HashMap<Integer, ArrayList<Appointment>> getCurrentAppointments() {return currentAppointments;}

}
