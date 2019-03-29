package Objects;

import java.util.HashMap;

public class WorkDay {
	
	private static final Integer NUMBER_OF_TIMESLOTS = 16; // In an 8 hour work day
	private Boolean isAvailable = true;
	private HashMap<Integer, TimeSlot> workSchedule;
	
	public WorkDay() {
		setWorkSchedule(new HashMap<Integer, TimeSlot>());
		for (int i = 1; i <= getNumberOfTimeslots(); i++) getWorkSchedule().put(i, new TimeSlot());
	}
	
	public TimeSlot getTimeSlot(Integer timeSlotId) {
		return getWorkSchedule().get(timeSlotId);
	}
	
	public TimeSlot[] getTimeSlotRange(Integer firstTimeSlot, Integer secondTimeSlot) {
		TimeSlot[] timeRange = new TimeSlot[secondTimeSlot - firstTimeSlot];
		int counter = firstTimeSlot - 1;
		while(counter < secondTimeSlot) {
			timeRange[counter] = getWorkSchedule().get(counter);
			counter++;
		}
		return timeRange;
	}
	
	public void bookTimeSlot(Integer timeSlotId) {
		getWorkSchedule().get(timeSlotId).setIsBooked(true);
	}
	
	// Getters
	public static Integer getNumberOfTimeslots() {return NUMBER_OF_TIMESLOTS;}
	public Boolean getIsAvailable() {return isAvailable;}
	public HashMap<Integer, TimeSlot> getWorkSchedule() {return workSchedule;}
	
	// Setters
	public void setIsAvailable(Boolean isAvailable) {this.isAvailable = isAvailable;}
	private void setWorkSchedule(HashMap<Integer, TimeSlot> workSchedule) {this.workSchedule = workSchedule;}
}
