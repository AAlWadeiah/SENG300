/**
 * 
 */
package exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class dateFormatException extends Exception {

	public dateFormatException() 
	{
		Alert alert = new Alert(AlertType.WARNING,"Incorrect Date Format. Please fill the time in correctly using\n the format"
				+ " Month/Day/Year in numbers. Appointments may only be booked 61 days in advance, and cannot be booked\n the day of.", ButtonType.CLOSE);
		alert.setHeaderText("Incorrect Date Format");
		alert.showAndWait();
	}
}
