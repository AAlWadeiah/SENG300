package exceptions;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class timeFormatException extends Exception {

	public timeFormatException() {
		Alert alert = new Alert(AlertType.WARNING,"Incorrect Time Format. Please fill the time in correctly using\n the format"
				+ " Hour:Minutes, appointments run every 30 minutes and are only held between the hours of 9:00 to 5:00pm.\n", ButtonType.CLOSE);
		alert.setHeaderText("Incorrect Time Format");
		alert.showAndWait();

}}
