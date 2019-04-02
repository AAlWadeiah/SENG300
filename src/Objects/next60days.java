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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;




public class next60days {
	
	private LocalDate today = LocalDate.now();
	
	/**
	 * NextMonths, this method should give the month values of the months associated with the next 60 days
	 * 
	 * @return The month values which are involved in the next 60 days starting from tomorrow
	 */
	public ArrayList<Integer> nextMonths() 
	{
		int thisMonth = today.getMonthValue();
		ArrayList<Integer> monthArray = new ArrayList<Integer>();
		monthArray.add(today.plusDays(1).getMonthValue());
		monthArray.add(today.plusDays(1).plusMonths(1).getMonthValue());
		
		if(today.getMonthValue()+1 != today.plusDays(1).plusDays(60).getMonthValue())
		{
			monthArray.add(today.plusDays(1).plusMonths(2).getMonthValue());
		}
			
		return monthArray;
	}
	
	/**daysInCurrentMonth
	 * 
	 * daysInCurrentMonth, finds the number of days in the current month, and puts each day as an integer
	 *  into a slot in the arrayList
	 * 
	 * @return ArrayList of integers going up linearly from 1 until the last day of the current month
	 */
	public ArrayList<Integer> daysInCurrentMonth()
	{
		int numberOfDaysThisMonth = today.plusDays(1).lengthOfMonth();
		ArrayList<Integer> daysThisMonth = new ArrayList<Integer>();
		int counter = 1;
		while(numberOfDaysThisMonth > 0)
		{
			daysThisMonth.add(counter);
			counter++;
			numberOfDaysThisMonth--;
		}
		return daysThisMonth;
	}
	
	/** This method takes a month value and returns an arrayList of integers for each of the month
	 * 
	 * @param MonthID, month to be measured
	 * @return, an ArrayList of integers going up linearly starting from 1 until the last day of the month
	 */
	public ArrayList<Integer> daysInMonth(Integer MonthID)
	{
		int numberOfDaysThisMonth = today.of(today.getYear(), MonthID, 1).lengthOfMonth();
		ArrayList<Integer> daysInMonth = new ArrayList<Integer>(); 
		int counter = 1;
		while(numberOfDaysThisMonth > 0)
		{
			daysInMonth.add(counter);
			counter++;
			numberOfDaysThisMonth--;
		}
		return daysInMonth;
	}
	/** 
	 * This method finds the difference between the first date of the doctors schedule and the date of a given appointment
	 *  Using this it calls the availability method to update the given doctors availability
	 * 
	 * @param date a string for the date in the form "month/day/year" all in the form of integers
	 * @param time a string for the the time of the appointment in the form "hour:minutes" all in the form of integers
	 * @param doc a doctor object to find his availability 
	 */
	//This requires that the date is given in the following format "month/day/year" with no spaces between
	public void dateToAvailability(String date, String time, Doctor doc)
	{
		String[] dateArray = date.split("/");
		LocalDate dateOfAppointment = LocalDate.of(Integer.parseInt(dateArray[2]),Month.of(Integer.parseInt(dateArray[0])),Integer.parseInt(dateArray[1]));
		Long noOfDaysBetween = ChronoUnit.DAYS.between(today.plusDays(1), dateOfAppointment) + 1;	//This finds the number of days between the current day and the day of the appointment
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
		doc.getAvailability().getWorkDay(noOfDaysBetween.intValue()).bookTimeSlot(timeSlot);}

	
	/** This method finds the number of days between tomorrow and a given LocalDate
	 * 
	 * @param LocalDate, date to find the number of days between
	 * @return int, the number of days between tomorrow and the given date
	 */
	public int dayToTimeSlot(LocalDate date) 
	{
		Long noOfDaysBetween = ChronoUnit.DAYS.between(today.plusDays(1), date);	//This finds the number of days between the current day and the day of the appointment
		return noOfDaysBetween.intValue();
	}
	
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
			throw new Exception();
		}
		else if ( (LocalDate.of(year, month, day).getDayOfYear() > (today.getDayOfYear() + 61) &&                  //This handles if the date is more than 61 days away
				today.getYear() == LocalDate.of(year, month, day).getYear()) || (
				hasNextYear() && LocalDate.of(year, month, day).getDayOfYear() > (today.getYear() - 365+ 61))	   //If the date goes into a new year but its more than 61 days away
				|| LocalDate.of(year, month, day).getDayOfYear() == today.getDayOfYear() ||						   //If the date is today
				   (!hasNextYear() && (LocalDate.of(year, month, day).getDayOfYear() < today.getDayOfYear())) )    //If the date is a day which has passed
		{
			throw new Exception();
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
		{throw new Exception();}
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
