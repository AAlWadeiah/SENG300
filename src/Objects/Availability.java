package Objects;

import java.util.HashMap;

public class Availability {
	private static final Integer NUMBER_OF_WORK_DAYS = 60;
	private HashMap<Integer, WorkDay> availability;
	
	public Availability() {
		setAvailability(new HashMap<Integer, WorkDay>());
		for (int i = 1; i <= getNumberOfWorkDays(); i++) getAvailability().put(i, new WorkDay());
	}

	// Getters
	public static Integer getNumberOfWorkDays() {return NUMBER_OF_WORK_DAYS;}
	public HashMap<Integer, WorkDay> getAvailability() {return availability;}

	// Setters
	private void setAvailability(HashMap<Integer, WorkDay> availability) {this.availability = availability;}
}