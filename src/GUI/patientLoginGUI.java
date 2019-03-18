package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class patientLoginGUI extends startupGUI {
	public void startPatientLogin(Stage patientStage) {

		//Boxes
		VBox patientScreen = new VBox(20);		//spacing is 20 in the VBox
		setVBox(patientScreen);

		HBox intro = new HBox();
		setHBox(intro);
		
		HBox usernameBox = new HBox();
		setHBox(usernameBox);
		
		HBox passwordBox = new HBox();
		setHBox(passwordBox);

		//Labels
		Label actor = new Label();
		actor.setText("Patient Login");
		actor.setFont(new Font("Cambria", 32));
		actor.setTextFill(Color.WHITE);
		
		final Text actionTarget = new Text();

		//Buttons 
		Button rreturn = new Button("Return");
		Button submit = new Button("Submit");

		submit.setPrefSize(150, 30);

		//Panes
		BorderPane patientPane = new BorderPane();
		StackPane introPane = new StackPane();
		StackPane submitPane = new StackPane();
		
		//Fields and Labels
		TextField userID = new TextField();
		PasswordField passID = new PasswordField();
		Label passLabel = new Label("Password:      ");
		Label userLabel = new Label("Username:      ");
		
		userLabel.setFont(new Font("Calibiri",20));
		passLabel.setFont(new Font("Calibiri",20));
		
		
		//Adjust Boxes
		introPane.setAlignment(Pos.CENTER_RIGHT);
		
		usernameBox.setStyle("-fx-background-color: #c5c9cc;");
		usernameBox.setAlignment(Pos.TOP_CENTER);
		
		passwordBox.setStyle("-fx-background-color: #c5c9cc;");
		passwordBox.setAlignment(Pos.TOP_CENTER);

		//Populate Boxes
		intro.getChildren().addAll(actor,introPane);
		intro.setHgrow(introPane, Priority.ALWAYS);
		
		introPane.getChildren().add(rreturn);
		submitPane.getChildren().addAll(submit);

		usernameBox.getChildren().addAll(userLabel,userID);
		passwordBox.getChildren().addAll(passLabel,passID);

		patientScreen.getChildren().addAll(usernameBox, passwordBox);
		patientScreen.getChildren().addAll(submitPane,actionTarget);

			
		//Set panes and scene
		setBorderpane(patientPane, intro, patientScreen);
		setScene(patientPane,patientStage);


	//Button events

		//restarts program
		rreturn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				start(patientStage);
			}
		});

		//Collect the values in the login boxes and validate their login info
		submit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				//if (new validateAccount().validate(userID.getText(),getHash(passID.getText()), "Patient_Users.json")) //first validate their login
				//	{
				if(true) {
						viewAppointmentGUI patientSignin = new viewAppointmentGUI();
						patientSignin.startPatient(patientStage);
				}
				//	}
				else {
				actionTarget.setFill(Color.FIREBRICK);
				actionTarget.setFont(new Font("Cambra", 14));
				actionTarget.setText("*Wrong Username or Password*");}
				}
		});
		passID.setOnKeyReleased(event -> {
			  if (event.getCode() == KeyCode.ENTER){
				  if (new validateAccount().validate(userID.getText(),getHash(passID.getText()), "Patient")) //first validate their login
					{
						viewAppointmentGUI patientSignin = new viewAppointmentGUI();
						patientSignin.startPatient(patientStage);
					  }
				else {
					actionTarget.setFill(Color.FIREBRICK);
					actionTarget.setFont(new Font("Cambra", 14));
					actionTarget.setText("*Wrong Username or Password*");
				}}
			});
	       
	}

	/**
	 * @param p The users text version password
	 * @return The users hashed password
	 */
	private String getHash(String p){
		String password = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(p.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < bytes.length; i++){
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			password = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
	}
}

