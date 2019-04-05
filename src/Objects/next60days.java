/**
 * 
 * This class will handle all issues associated with relating the local date and mapping it to the next 60 
 * days.
 * 
 * 
 */

package Objects;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import exceptions.*;




public class next60days {
	
	private LocalDate today = LocalDate.now();
	
	/** 
	 * This method finds the difference between the first date of the doctors schedule and the date of a given appointment
	 *  Using this it calls the availability method to update the given doctors availability
	 * 
	 * @param date a string for the date in the form "month/day/year" all in the form of integers
	 * @param time a string for the the time of the appointment in the form "hour:minutes" all in the form of integers
	 * @param doc a doctor object to find his availability 
	 */
	//This requires that the date is given in the following format "month/day/year" with no spaces between
	public void dateToUpdateAvailability(String date, String time, Doctor doc)
	{
		LocalDate dateOfAppointment = dateToLocalDate(date);
		Long noOfDaysBetween = ChronoUnit.DAYS.between(today.plusDays(1), dateOfAppointment) + 1;	//This finds the number of days between the current day and the day of the appointment
		doc.getAvailability().getWorkDay(noOfDaysBetween.intValue()).bookTimeSlot(timeToTimeslot(time));} //get the timeslot based on the given time and book it

	
	/** This method determines if the next 60 days starting from tomorrow will overlap into the next year
	 * 
	 * @return Boolean, true if it is in the next year
	 */
	public boolean hasNextYear()
	{
		if(today.getMonthValue() == 11)
		{
			if (today.getDayOfMonth()+1 > 2) { return true;}
			else {return false;}
		}
		else if (today.getMonthValue()+1 == 12) { return true;}
		else {return false;}
	}
	
	
	/** This function finds out if a given date is within the next 60 days starting from tomorrow,
	 *  if it finds that the date is not, it throws an exception
	 * 
	 * @param date, date in the form month/day/year
	 * @throws Exception to be passed on to the called function to handle
	 */
	public void isDateWithinNext60Days(String date) throws Exception
	{
		String[] dateArray = date.split("/");
		int month = Integer.parseInt(dateArray[0]);
		int day = Integer.parseInt(dateArray[1]);
		int year = Integer.parseInt(dateArray[2]);
		
		if (!hasNextYear() && year != currentYear() || 		//If the years are not consistent
				year < currentYear() )
		{
			throw new dateFormatException();
		}
		else if ( (LocalDate.of(year, month, day).getDayOfYear() > (today.getDayOfYear() + 61) &&                  //This handles if the date is more than 61 days away
				today.getYear() == LocalDate.of(year, month, day).getYear()) 
				|| (hasNextYear() && LocalDate.of(year, month, day).getDayOfYear() > (today.getYear() - 365+ 61))	   //If the date goes into a new year but its more than 61 days away
				|| (LocalDate.of(year, month, day).getDayOfYear() == today.getDayOfYear()) 						   //If the date is today
				|| (!hasNextYear() && (LocalDate.of(year, month, day).getDayOfYear() < today.getDayOfYear())) )    //If the date is a day which has passed
		{
			throw new dateFormatException();		//We need a more specific exception
		}
	}
	
	
	/** This Function is passed a time and tells if that time is within the work-day of a doctor,
	 *  assumed as being the Hours of 9 - 5 PM. If it is not, it throws an exception which creates a popup alert box.
	 * 
	 * @param time, time passed into the function in the form of a string HH:MM
	 * @throws Exception
	 */
	public void isTimeWithinWorkday(String time) throws Exception
	{
		String hour = time.split(":")[0];
		String minute = time.split(":")[1];
		String[] possibleHours = {"9","10","11","12","1","2","3","4"};
		String[] possibleMinutes = {"30","00"};
		if (!Arrays.asList(possibleHours).contains(hour) || !Arrays.asList(possibleMinutes).contains(minute)) 
		{throw new timeFormatException();}	
	}
	/** Finds the how many days away a certain date is from today, this is essentially the workday number
	 * 
	 * @param date
	 * @return
	 */
	public int numberOfDaysAway(String date) 
	{
		LocalDate dateOfAppointment = dateToLocalDate(date);
		Long noOfDaysBetween = ChronoUnit.DAYS.between(today.plusDays(1), dateOfAppointment) + 1;	//find # of days between given date and today
		return noOfDaysBetween.intValue();
	}
	
	/** Given a time, it returns a time slot based on how the availability of a doctor is managed, being from 9 - 5 every
	 *  30 minutes accounting for one time slot.
	 * 
	 * @param time
	 * @return 
	 */
	public int timeToTimeslot(String time)
	{
		String[] timeArray = time.split(":");
		int timeSlot;
		if (timeArray[0].equals("9") && timeArray[1].equals("00")) {timeSlot = 1;}
		else if (timeArray[0].equals("9") && timeArray[1].equals("30")) {timeSlot = 2;}
		else if (timeArray[0].equals("10") && timeArray[1].equals("00")) {timeSlot = 3;}
		else if (timeArray[0].equals("10") && timeArray[1].equals("30")) {timeSlot = 4;}
		else if (timeArray[0].equals("11") && timeArray[1].equals("00")) {timeSlot = 5;}
		else if (timeArray[0].equals("11") && timeArray[1].equals("30")) {timeSlot = 6;}
		else if (timeArray[0].equals("12") && timeArray[1].equals("00")) {timeSlot = 7;}
		else if (timeArray[0].equals("12") && timeArray[1].equals("30")) {timeSlot = 8;}
		else if (timeArray[0].equals("1") && timeArray[1].equals("00")) {timeSlot = 9;}
		else if (timeArray[0].equals("1") && timeArray[1].equals("30")) {timeSlot = 10;}
		else if (timeArray[0].equals("2") && timeArray[1].equals("00")) {timeSlot = 11;}
		else if (timeArray[0].equals("2") && timeArray[1].equals("30")) {timeSlot = 12;}
		else if (timeArray[0].equals("3") && timeArray[1].equals("00")) {timeSlot = 13;}
		else if (timeArray[0].equals("3") && timeArray[1].equals("30")) {timeSlot = 14;}		
		else if (timeArray[0].equals("4") && timeArray[1].equals("00")) {timeSlot = 15;}
		else {timeSlot = 16;}
		return timeSlot;
	}
	
	/**Given a timeslot integer, it returns the corresponding time
	 * 
	 * @param timeSlot, an integer corresponding to the correct timeSlot
	 * @return time as a string in the form HH:MM
	 */
	public String timeslotToTime(int timeSlot)
	{
		String time;
		if (timeSlot ==1 ) {time = "9:00";}
		else if (timeSlot ==2 ) {time = "9:30";}
		else if (timeSlot ==3 ) {time = "10:00";}
		else if (timeSlot ==4 ) {time = "10:30";}
		else if (timeSlot ==5 ) {time = "11:00";}
		else if (timeSlot ==6 ) {time = "11:30";}
		else if (timeSlot ==7 ) {time = "12:00";}
		else if (timeSlot ==8 ) {time = "12:30";}
		else if (timeSlot ==9 ) {time = "1:00";}
		else if (timeSlot ==10 ) {time = "1:30";}
		else if (timeSlot ==11 ) {time = "2:00";}
		else if (timeSlot ==12 ) {time = "2:30";}
		else if (timeSlot ==13 ) {time = "3:00";}
		else if (timeSlot ==14 ) {time = "3:30";}		
		else if (timeSlot ==15 ) {time = "4:00";}
		else {time = "4:30";}
		return time;
	}
	
	/** Given a certain day and a doctor, this method finds out what timeslots the doctor has available
	 * 
	 * @param doc doctor object to check what timeslots he has
	 * @param dayID day to check 
	 * @return ArrayList of string times that are available
	 */
	public ArrayList<String> availableTimes(Doctor doc, int dayID)
	{
		ArrayList<String> timeArray = new ArrayList<String>();
		int i = 1;
		while (i<= 16)
		{
			if(!doc.getAvailability().getWorkDay(dayID).getTimeSlot(i).getIsBooked())
				{
					timeArray.add(timeslotToTime(i));
				};
			i++;
		}
		return timeArray;
	}
	
	/** This just formats the date to be visually appealing
	 * 
	 * @param date
	 * @return
	 */
	public String dateFormat(String date) 
	{
	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMMM yyyy");
	String formattedDate = dateToLocalDate(date).format(format);
	
	return formattedDate;}
	
	
	/** Given a date string in the format MM/DD/YYYY, it returns a LocalDate object corresponding
	 * 
	 * 
	 */
	public LocalDate dateToLocalDate(String date)
	{
		String[] dateArray = date.split("/");
		LocalDate localDate = LocalDate.of(Integer.parseInt(dateArray[2]),Month.of(Integer.parseInt(dateArray[0])),Integer.parseInt(dateArray[1]));
		return localDate;
	}
	
	
	/**
	 * 
	 * @return Integer, The current year
	 */
	public int currentYear()
	{
		return today.getYear();
	}
	
	/**
	 * @return Integer, The next year
	 */
	public int nextYear()
	{
		return today.getYear()+1;
	}
	/**
	 * 
	 * @return Integer, the current day of the month
	 */
	public int getCurrentDayOfMonth()
	{
		return today.getDayOfMonth();
	}
	/**
	 * 
	 * @return The current month number
	 */
	public int currentMonthValue()
	{
		return today.getMonthValue();
	}
	/**
	 * 
	 * @return The day of the month after 60 days, being in a new month
	 */
	public int dayOfMonthAfter60Days()
	{
		return today.plusDays(60).getDayOfMonth();
	}
	/**
	 * 
	 * @return The month number we will be in after 60 days pass
	 */
	public int monthValueAfter60Days()
	{
		return today.plusDays(60).getMonthValue();
	}
	
	
}
