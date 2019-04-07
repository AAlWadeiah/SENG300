package exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class usernameTakenException extends Exception {

	public usernameTakenException() {
		Alert alert = new Alert(AlertType.WARNING,"The username you have chosen is already assigned to another patient, please try a different username.",
				ButtonType.CLOSE);
		alert.showAndWait();
	}

}
