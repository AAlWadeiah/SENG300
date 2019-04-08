package exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class dateRangeException extends Exception {
	
	public dateRangeException() {
		Alert alert = new Alert(AlertType.WARNING,"Invalid date range has been entered",
				ButtonType.CLOSE);
		alert.showAndWait();
	}

}