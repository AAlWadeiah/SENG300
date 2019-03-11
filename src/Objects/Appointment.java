package Objects;

public class Appointment {
	private Integer appointmentId;
	private String date;
	private String time;
	
	// Constructors
	public Appointment() {}
	public Appointment(String date, String time) {
		setDate(date);
		setTime(time);
		setAppointmentId(date.hashCode() + time.hashCode());
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
		Integer newAppointmentId = newDate.hashCode() + newTime.hashCode();
		setAppointmentId(newAppointmentId);
		return newAppointmentId;
	}
	
	// Getters
	public String getTime() {return time;}
	public String getDate() {return date;}
	public Integer getAppointmentId() {return appointmentId;}
	
	// Setters
	public void setTime(String time) {this.time = time;}
	public void setDate(String date) {this.date = date;}
	private void setAppointmentId(Integer appointmentId) {this.appointmentId = appointmentId;}
	
}
