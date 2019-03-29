package Objects;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;



public class next60days {
	
	LocalDate today = LocalDate.now();
	

	public ArrayList<Integer> next2Months() 
	{
		int thisMonth = today.getMonthValue();
		ArrayList<Integer> monthArray = new ArrayList<Integer>();
		monthArray.add(today.getMonthValue());
		monthArray.add(today.plusMonths(1).getMonthValue());
		return monthArray;
	}
	
	public ArrayList<Integer> daysInMonth()
	{
		int numberOfDaysThisMonth = today.lengthOfMonth();
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
}
