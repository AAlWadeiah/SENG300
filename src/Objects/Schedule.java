package Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * A class which stores the appointment schedule for a single doctor. The doctor is indicated by their unique ID.
 * @author Abdullah
 *
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

	/**
	 * Retrieves all of the appointments for the given patient, if the patient has any appointments currently booked.
	 * @param patientId The ID of the patient to grab appointments for
	 * @return an ArrayList containing all of the patients appointments, and null if no record of this patient could be found
	 */
	public ArrayList<Appointment> getAllAppointments(Integer patientId){
		return getCurrentAppointments().get(patientId);
	}

	/**
	 * Retrieves the appointment indicated by an ID for the given patient, if the appointment is found.
	 * @param patientId The ID of the patient to retrieve the appointment for
	 * @param appointmentId The ID of the appointment to retrieve
	 * @return the Appointment for the patient, and null if no Appointment with the ID could be found
	 */
	public Appointment getAppointment(Integer patientId, Integer appointmentId) {
		return getCurrentAppointments().get(patientId).stream().filter(appt -> appointmentId.equals(appt.getAppointmentId())).findFirst().orElse(null);
	}

	/**
	 * Retrieves the appointment indicated by a date and time for the given patient, if the appointment is found.
	 * @param patientId The ID of the patient to retrieve the appointment for
	 * @param date The date of the appointment to retrieve
	 * @param time The time of the appointment to retrieve
	 * @return the Appointment for the patient, and null if no Appointment with the date and time could be found
	 */
	public Appointment getAppointment(Integer patientId, String date, String time) {
		Integer idToFind = Appointment.calculateId(date, time);
		return getCurrentAppointments().get(patientId).stream().filter(appt -> idToFind.equals(appt.getAppointmentId())).findFirst().orElse(null);
	}

	/**
	 * Retrieves all appointments that are on the given date.
	 * @param date The date to query for appointments
	 * @return an ArrayList containing all appointments on the specified date
	 */
	public ArrayList<Appointment> getAppointmentsByDate(String date){
		ArrayList<Appointment> apptsOnDate = new ArrayList<Appointment>();
		for (Entry<Integer, ArrayList<Appointment>> patientAppts: getCurrentAppointments().entrySet()) {
			for (Appointment appt : patientAppts.getValue()) {
				if (appt.getDate().equals(date)) apptsOnDate.add(appt);
			}
		}
		return apptsOnDate;
	}

	/**
	 * Removes the entry of the given patient from the currentApointments HashMap.
	 * @param patientId The ID of the patient to remove from the appointments record.
	 * @return true if the patient was removed, and false if the patient could not be found
	 */
	public boolean removePatient(Integer patientId) {
		if (getCurrentAppointments().containsKey(patientId)) {
			getCurrentAppointments().remove(patientId);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Removes an appointment specified by the ID for the given patient.
	 * @param patientId The ID of the patient to remove the appointment for
	 * @param appointmentId The ID of the appointment to remove
	 * @return true if the appointment was removed, and false otherwise
	 */
	public boolean removeAppointment(Integer patientId, Integer appointmentId) {
		return getCurrentAppointments().get(patientId).removeIf(a -> a.getAppointmentId().equals(appointmentId));
	}

	/**
	 * Removes an appointment specified by a date and time for the given patient.
	 * @param patientId The ID of the patient to remove the appointment for
	 * @param date The date of the appointment to remove
	 * @param time The time of the appointment to remove
	 * @return true of the appointment was removed, and false otherwise
	 */
	public boolean removeAppointment(Integer patientId, String date, String time) {
		Integer idToFind = Appointment.calculateId(date, time);
		return getCurrentAppointments().get(patientId).removeIf(a -> a.getAppointmentId().equals(idToFind));
	}

	/**
	 * Updates an appointment specified by an ID for a given patient. Sets the date and time of the appointment to the new one's provided.
	 * @param patientId The ID of the patient to update the appointment for
	 * @param appointmentId The ID of the appointment to update
	 * @param newDate The new date for the appointment
	 * @param newTime The new time for the appointment
	 * @return the new ID for the appointment
	 */
	public Integer updateAppointment(Integer patientId, Integer appointmentId, String newDate, String newTime) {
		Appointment apptToEdit = getCurrentAppointments().get(patientId).stream()
				.filter(appt -> appointmentId.equals(appt.getAppointmentId())).findFirst().orElse(null);
		return apptToEdit.updateAppointment(newDate, newTime);
	}

	/**
	 * Updates an appointment specified by the previous date and time for a given patient. Sets the date and time of the appointment to the new one's provided.
	 * @param patientId The ID of the patient to update the appointment for
	 * @param oldDate The old date of the appointment
	 * @param oldTime The old time of the appointment
	 * @param newDate The new date for the appointment
	 * @param newTime The new time for the appointment
	 * @return the new ID for the appointment
	 */
	public Integer updateAppointment(Integer patientId, String oldDate, String oldTime, String newDate, String newTime) {
		Integer idToFind = Appointment.calculateId(oldDate, oldTime);
		Appointment apptToEdit = getCurrentAppointments().get(patientId).stream()
				.filter(appt -> idToFind.equals(appt.getAppointmentId())).findFirst().orElse(null);
		return apptToEdit.updateAppointment(newDate, newTime);
	}

	//Setters
	public void setDoctorId(Integer doctorId) {this.doctorId = doctorId;}
	public void setCurrentAppointments(HashMap<Integer, ArrayList<Appointment>> currAppt) {this.currentAppointments = currAppt;}

	// Getters
	public Integer getDoctor() {return doctorId;}
	public HashMap<Integer, ArrayList<Appointment>> getCurrentAppointments() {return currentAppointments;}

}
