package Objects;

public class Appointment {
	private Integer patientId;
	private String date;
	private String time;
	
	public Appointment() {}
	public Appointment(Integer pId, String date, String time) {
		setPatientId(pId);
		setDate(date);
		setTime(time);
	}
	
	// Getters
	public Integer getPatientId() {return patientId;}
	public String getTime() {return time;}
	public String getDate() {return date;}
	
	// Setters
	public void setPatientId(Integer patientId) {this.patientId = patientId;}
	public void setTime(String time) {this.time = time;}
	public void setDate(String date) {this.date = date;}
	
}
