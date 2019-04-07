package exceptions;

import java.util.ArrayList;

import Objects.Doctor;
import Objects.next60days;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class bookedException extends Exception {

	public bookedException(java.lang.String date, Doctor doc, ArrayList<String> availableTimes) {
		
		String times ="";
		int i =0;
		
		
		while (i < availableTimes.size())
		{
			if (i == 0) { times = availableTimes.get(i);}
			else if (i == availableTimes.size()-1) { times = times + ", " + availableTimes.get(i)+"."; }
			else {times = times + ", " + availableTimes.get(i);}
			i++;
		}
		
		
		
		String scenario; //We will have two scenarios with the times, if there are none, or if there are times which the doctor
		// is free
		
		if (times.equals("")) 
		{
			scenario = " the doctor has no free time for appointments.";
		}
		
		else 
		{
		scenario = " the doctor is free at "+ times;
		}
		
		String prettyDate = new next60days().dateFormat(date); //a visually appealing format for the date
		
		//Set the alert popup and the correct message in a visually appearing format
		Alert alert = new Alert(AlertType.WARNING,"The time frame you have chosen is currently booked out for\n Dr. "
				+ doc.getFirstname() +"," + " on " + prettyDate + scenario, ButtonType.CLOSE); 
		alert.showAndWait();
	}

}
