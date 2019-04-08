package Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * A class which stores the availability of a Doctor.
 * @author Abdullah
 *
 */
public class Availability {
	
	// Fields
	private static final Integer NUMBER_OF_WORK_DAYS = 60;
	private HashMap<Integer, WorkDay> availability;
	private next60days dateUtils;
	
	// Constructor
	public Availability() {
		setAvailability(new HashMap<Integer, WorkDay>());
		setDateUtils(new next60days());
		for (int i = 1; i <= getNumberOfWorkDays(); i++) getAvailability().put(i, new WorkDay());
	}
	
	public void setWorkdayAvailability(String day) {
		int workdayId = getDateUtils().numberOfDaysAway(day);
		getAvailability().get(workdayId).setIsAvailable(false);
	}
	
	/**
	 * Retrieves the workday specified by the given ID.
	 * @param workDayId The ID of the workday to return
	 * @return the workday which was specified by the ID
	 */
	public WorkDay getWorkDay(Integer workDayId) {
		return getAvailability().get(workDayId);
	}
	
	/**
	 * Retrieves a range of workdays specified by a starting ID and an ending ID.
	 * @param firstWorkDay The ID of beginning workday
	 * @param secondWorkDay The ID of the ending workday
	 * @return an array containing the workdays in the specified range
	 */
	public WorkDay[] getWorkDayRange(Integer firstWorkDay, Integer secondWorkDay) {
		WorkDay[] workRange = new WorkDay[secondWorkDay - firstWorkDay];
		int counter = firstWorkDay - 1;
		while(counter < secondWorkDay) {
			workRange[counter] = getAvailability().get(counter);
			counter++;
		}
		return workRange;
	}

	/**
	 * Sets the availability for a specified workday.
	 * @param workDayId The workday to set the availability for
	 * @param isAvailable The availability to set
	 */
	public void setWorkDayAvailability(Integer workDayId, Boolean isAvailable) {
		getAvailability().get(workDayId).setIsAvailable(isAvailable);
	}
	
	/**
	 * Retrieves the full availability schedule.
	 * @return an ArrayList of ArrayLists of Booleans that corresponds to the Doctor's availability
	 */
	public ArrayList<ArrayList<Boolean>> getFullWorkSchedule() {
		ArrayList<ArrayList<Boolean>> fullSchedule = new ArrayList<>();
		
		for (Entry<Integer, WorkDay> workDay : getAvailability().entrySet()) 
		{
			ArrayList<Boolean> daySchedule = new ArrayList<>();
			for(Entry<Integer, TimeSlot> timeSlot : workDay.getValue().getWorkSchedule().entrySet()) {
				daySchedule.add(timeSlot.getValue().getIsBooked());
			}
			fullSchedule.add(daySchedule);
		}
		return fullSchedule;
	}

	// Getters
	public static Integer getNumberOfWorkDays() {return NUMBER_OF_WORK_DAYS;}
	public HashMap<Integer, WorkDay> getAvailability() {return availability;}
	public next60days getDateUtils() {return dateUtils;}

	// Setters
	private void setAvailability(HashMap<Integer, WorkDay> availability) {this.availability = availability;}
	public void setDateUtils(next60days dateUtils) {this.dateUtils = dateUtils;}
}