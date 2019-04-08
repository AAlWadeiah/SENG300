package Objects;

/**
 * A class for storing information pertaining to an appointment. Must be used in conjunction with Objects.Schedule to keep track of patients.
 * @author Abdullah
 *
 */
public class Appointment {
	private Integer appointmentId;
	private String patientId;
	private String date;
	private String time;

	// Constructors
	public Appointment() {}
	public Appointment(String pId, String date, String time) {
		setPatientId(pId);
		setDate(date);
		setTime(time);
		setAppointmentId(calculateId(date, time));
	}

	/**
	 * Calculates the ID of an appointment based on the given date and time.
	 * @param date The date of the appointment
	 * @param time The time of the appointment
	 * @return the ID which was calculated
	 */
	public static Integer calculateId (String date, String time) {
		return date.hashCode() + time.hashCode();
	}

	/**
	 * Updates the appointment by changing the date and time to the new one's provided.
	 * @param newDate The new date for the appointment
	 * @param newTime The new time for the appointment
	 * @return the new ID for the appointment
	 */
	public Integer updateAppointment(String newDate, String newTime) {
		setDate(newDate);
		setTime(newTime);
		setAppointmentId(calculateId(newDate, newTime));
		return getAppointmentId();
	}

	// Getters
	public String getTime() {return time;}
	public String getDate() {return date;}
	public Integer getAppointmentId() {return appointmentId;}
	public String getPatientId() {return patientId;}

	// Setters
	public void setTime(String time) {this.time = time;}
	public void setDate(String date) {this.date = date;}
	private void setAppointmentId(Integer apptId) {this.appointmentId = apptId;}
	public void setPatientId(String patientId) {this.patientId = patientId;}
}
