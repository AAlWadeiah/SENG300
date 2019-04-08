package exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class usernameException extends Exception {

	public usernameException() {
		Alert alert = new Alert(AlertType.WARNING,"Leading zeroes are not permitted in usernames",
				ButtonType.CLOSE);
		alert.showAndWait();
	}

}
