package Objects;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class next60days {
	
	LocalDate today = LocalDate.now();
	/**
	 * 
	 * @return The months which are involved in the next 60 days starting from tomorrow
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
	
	//Days involved in the month which we are currently in
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
	
	public static void main(String[] args)
	{
				next60days days = new next60days(); 
				System.out.print(days.daysInCurrentMonth());
	}
	
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
	
	//This requires that the date is given in the following format "month/day/year" with no spaces between
	//This method finds the difference between the first date of the doctors schedule and the date of a given appointment
	//It also calls the availability method to update the given doctors availability
	public void dateToAvailability(String date, String time, Doctor doc)
	{
		String[] dateArray = date.split("/");
		LocalDate dateOfAppointment = LocalDate.of(Integer.parseInt(dateArray[2]),Month.of(Integer.parseInt(dateArray[0])),Integer.parseInt(dateArray[1]));
		Long noOfDaysBetween = ChronoUnit.DAYS.between(today.plusDays(1), dateOfAppointment) + 1;	//This finds the number of days between the current day and the day of the appointment
		Long noOfDaysBetween = ChronoUnit.DAYS.between(today.plusDays(1), dateOfAppointment)+1;	//This finds the number of days between the current day and the day of the appointment
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
		System.out.println("Booking Day: "+ noOfDaysBetween);
		System.out.println("Booking Time: "+ timeSlot);
		
		doc.getAvailability().getWorkDay(noOfDaysBetween.intValue()).bookTimeSlot(timeSlot);

	}
	
	public int dayToTimeSlot(LocalDate date) 
	{
		Long noOfDaysBetween = ChronoUnit.DAYS.between(today.plusDays(1), date);	//This finds the number of days between the current day and the day of the appointment
		return noOfDaysBetween.intValue();
	}
	
	/** This method determines if the next 60 days starting from tomorrow will overlap into the next year
	 * 
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
	
	public int currentYear()
	{
		return today.getYear();
	}
	public int nextYear()
	{
		return today.getYear()+1;
	}
	
	public int getCurrentDayOfMonth()
	{
		return today.getDayOfMonth();
	}
	public int currentMonthValue()
	{
		return today.getMonthValue();
	}
	public int dayOfMonthAfter60Days()
	{
		return today.plusDays(60).getDayOfMonth();
	}
	public int monthValueAfter60Days()
	{
		return today.plusDays(60).getMonthValue();
	}
	
	
}
