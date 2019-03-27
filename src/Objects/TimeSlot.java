package Objects;

public class TimeSlot {
	private static final Integer LENGTH = 30; // minutes
	private Boolean isBooked = false;

	// Getters
	public static Integer getLength() {return LENGTH;}
	public Boolean getIsBooked() {return isBooked;}

	// Setters
	public void setIsBooked(Boolean isBooked) {this.isBooked = isBooked;}
}
