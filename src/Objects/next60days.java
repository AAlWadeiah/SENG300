package Objects;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

import com.sun.org.apache.xpath.internal.operations.Bool;



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
		while(numberOfDaysThisMonth >= 0)
		{
			daysThisMonth.add(counter);
			counter++;
			numberOfDaysThisMonth--;
		}
		return daysThisMonth;
	}
	
	public ArrayList<Integer> daysInMonth(Integer MonthID)
	{
		int numberOfDaysThisMonth = today.of(today.getYear(), MonthID, today.getDayOfMonth()).lengthOfMonth();
		ArrayList<Integer> daysInMonth = new ArrayList<Integer>(); 
		int counter = 1;
		while(numberOfDaysThisMonth >= 0)
		{
			daysInMonth.add(counter);
			counter++;
			numberOfDaysThisMonth--;
		}
		return daysInMonth;
	}
	
	/** The name says it all, feed it a month integer, it gives you a month string
	 * 
	 * @param MonthID
	 * @return
	 */
	public String giveMonthIntGetMonthString(Integer MonthID)
	{
		return null;
	}
	
	/** This method determines if the next 60 days starting from tomorrow will overlap into the next year
	 * 
	 * @return true if it is in the next year
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
	
	
}
