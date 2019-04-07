package exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class emptyFieldException extends Exception {

	public emptyFieldException() {
		Alert alert = new Alert(AlertType.WARNING,"Please check the highlighted fields and ensure all fields are filled correctly.\n", ButtonType.CLOSE);
		alert.setHeaderText("Please fill all Fields");
		alert.showAndWait();
	}


}
