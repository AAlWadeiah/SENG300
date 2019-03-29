package Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Availability {
	private static final Integer NUMBER_OF_WORK_DAYS = 60;
	private HashMap<Integer, WorkDay> availability;
	
	public Availability() {
		setAvailability(new HashMap<Integer, WorkDay>());
		for (int i = 1; i <= getNumberOfWorkDays(); i++) getAvailability().put(i, new WorkDay());
	}
	
	public WorkDay getWorkDay(Integer workDayId) {
		return getAvailability().get(workDayId);
	}
	
	public WorkDay[] getWorkDayRange(Integer firstWorkDay, Integer secondWorkDay) {
		WorkDay[] workRange = new WorkDay[secondWorkDay - firstWorkDay];
		int counter = firstWorkDay - 1;
		while(counter < secondWorkDay) {
			workRange[counter] = getAvailability().get(counter);
			counter++;
		}
		return workRange;
	}

	public void setWorkDayAvailability(Integer workDayId, Boolean isAvailable) {
		getAvailability().get(workDayId).setIsAvailable(isAvailable);
	}
	
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

	// Setters
	private void setAvailability(HashMap<Integer, WorkDay> availability) {this.availability = availability;}
}