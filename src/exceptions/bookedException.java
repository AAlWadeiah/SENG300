package exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class bookedException extends Exception {

	public bookedException(java.lang.String string) {
		Alert alert = new Alert(AlertType.WARNING,"The time frame you have chosen is currently booked out for the doctor,"
				+ " on " + string + " the doctor is free at " , ButtonType.CLOSE); //TODO FIND THE FREE TIMES AND PROMPT THE USER
		alert.showAndWait();
	}

}
