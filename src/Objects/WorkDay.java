package Objects;

import java.util.HashMap;

/**
 * A class which represents a single work day that contains 16 TimeSlots, making up an 8 hour work day.
 * @author Abdullah
 *
 */
public class WorkDay {
	
	// Fields
	private static final Integer NUMBER_OF_TIMESLOTS = 16; // In an 8 hour work day
	private Boolean isAvailable = true;
	private HashMap<Integer, TimeSlot> workSchedule;
	
	// Constructor
	public WorkDay() {
		setWorkSchedule(new HashMap<Integer, TimeSlot>());
		for (int i = 1; i <= getNumberOfTimeslots(); i++) getWorkSchedule().put(i, new TimeSlot());
	}
	
	/**
	 * Retrieves a single specified time slot.
	 * @param timeSlotId The ID of the time slot 
	 * @return the specified time slot
	 */
	public TimeSlot getTimeSlot(Integer timeSlotId) {
		return getWorkSchedule().get(timeSlotId);
	}
	
	/**
	 * Retrieves a range of time slots specified by a beginning ID and an ending ID.
	 * @param firstTimeSlot The ID of the beginning time slot
	 * @param secondTimeSlot The ID of the ending time slot
	 * @return an array containing time slots within the specified range
	 */
	public TimeSlot[] getTimeSlotRange(Integer firstTimeSlot, Integer secondTimeSlot) {
		TimeSlot[] timeRange = new TimeSlot[secondTimeSlot - firstTimeSlot];
		int counter = firstTimeSlot - 1;
		while(counter < secondTimeSlot) {
			timeRange[counter] = getWorkSchedule().get(counter);
			counter++;
		}
		return timeRange;
	}
	
	/**
	 * Books a specified time slot by setting it's isBooked field to true.
	 * @param timeSlotId The ID of the time slot to book
	 */
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
