package GUI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import exceptions.emptyFieldException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/** This class is used for handling all Logins, it has a input box for userID and password,
 *  sending them to the validation class to be authenticated then responds accordingly
 * 
 */

public class loginGUI extends startupGUI {
	@SuppressWarnings("unused")
	public void startLogin(Stage stage, String user) {
		

		//Boxes
			VBox  screen = new VBox(20);		//spacing is 20 in the VBox
			setVBox(screen);

			HBox intro = new HBox();
			setHBox(intro);
			
			HBox usernameBox = new HBox();
			setHBox(usernameBox);
			
			HBox passwordBox = new HBox();
			setHBox(passwordBox);

		//Labels
			Label actor = new Label();
			actor.setText(user+" Login");
			actor.setFont(new Font("Cambria", 32));
			actor.setTextFill(Color.WHITE);
			
			final Text actionTarget = new Text();

		//Buttons 
			Button rreturn = new Button("Return");
			Button submit = new Button("Submit");

			submit.setPrefSize(150, 30);

		//Panes
			BorderPane pane = new BorderPane();
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
			
			screen.getChildren().addAll(usernameBox, passwordBox);
			screen.getChildren().addAll(submitPane,actionTarget);
				
		//Set panes and scene
			setBorderpane( pane, intro,  screen);
			setScene( pane, stage);


			//Button events

			//restarts program
			rreturn.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent e) {
					start(stage);
				}
			});

			//Collect the values in the login boxes and validate their login info
			submit.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent e) {
					
					
					try {
						if (user.equals("Admin"))
						{
							
							if (new validateAccount().validate(Integer.valueOf(userID.getText()),getHash(passID.getText()), "Admin"))
								{
								adminGUI adminSignin = new adminGUI();
								adminSignin.startAdmin(stage);	
								}
							else 
								{
								actionTarget.setFill(Color.FIREBRICK);
								actionTarget.setFont(new Font("Cambra", 14));
								actionTarget.setText("*Wrong Username or Password*");
								}
							
							
						}
						
						else if (user.equals("Patient"))
						{ 
							if (new validateAccount().validate(Integer.valueOf(userID.getText()),getHash(passID.getText()), "Patient"))
								{
								viewAppointmentGUI patientSignin = new viewAppointmentGUI();
								patientSignin.startPatient(stage, Integer.valueOf(userID.getText()));	
								}
							else 
								{
								actionTarget.setFill(Color.FIREBRICK);
								actionTarget.setFont(new Font("Cambra", 14));
								actionTarget.setText("*Wrong Username or Password*");
								}
						}
						else if (user.equals("Doctor"))
						{
							if(new validateAccount().validate(Integer.valueOf(userID.getText()),getHash(passID.getText()), "Doctor"))
								{
								viewScheduleGUI doctorSignin = new viewScheduleGUI();
								doctorSignin.startDoctor(stage,Integer.parseInt(userID.getText()));
								}
							else 
								{
								actionTarget.setFill(Color.FIREBRICK);
								actionTarget.setFont(new Font("Cambra", 14));
								actionTarget.setText("*Wrong Username or Password*");
								}
							
						}
					}
					catch(Exception e1){
						actionTarget.setFill(Color.FIREBRICK);
						actionTarget.setFont(new Font("Cambra", 14));
						actionTarget.setText("*Wrong Username or Password*");
					}
					

			}});
			
			//If the enter button is pressed while inside the password box,
			//do the validation process
			passID.setOnKeyReleased(event -> {
				  if (event.getCode() == KeyCode.ENTER){
					  
					  try {			//in order to catch any bad input
						  
						if (user.equals("Admin"))
						{						
							if (new validateAccount().validate(Integer.valueOf(userID.getText()),getHash(passID.getText()), "Admin"))
								{
								adminGUI adminSignin = new adminGUI();
								adminSignin.startAdmin(stage);	
								}
							else 
								{
								actionTarget.setFill(Color.FIREBRICK);
								actionTarget.setFont(new Font("Cambra", 14));
								actionTarget.setText("*Wrong Username or Password*");
								}
						}
						
						else if (user.equals("Patient"))
						{ 
							if (new validateAccount().validate(Integer.valueOf(userID.getText()),getHash(passID.getText()), "Patient"))
								{
								viewAppointmentGUI patientSignin = new viewAppointmentGUI();
								patientSignin.startPatient(stage, Integer.valueOf(userID.getText()));	
								}
							else 
								{
								actionTarget.setFill(Color.FIREBRICK);
								actionTarget.setFont(new Font("Cambra", 14));
								actionTarget.setText("*Wrong Username or Password*");
								}
						}
						else if (user.equals("Doctor"))
						{
							if(new validateAccount().validate(Integer.valueOf(userID.getText()),getHash(passID.getText()), "Doctor"))
								{
								viewScheduleGUI doctorSignin = new viewScheduleGUI();
								doctorSignin.startDoctor(stage,Integer.parseInt(userID.getText()));
								}
							else 
								{
								actionTarget.setFill(Color.FIREBRICK);
								actionTarget.setFont(new Font("Cambra", 14));
								actionTarget.setText("*Wrong Username or Password*");
								}
				  }
					  }
						catch(Exception e) {	// we must have gotten some bad input if we go into here
						actionTarget.setFill(Color.FIREBRICK);
						actionTarget.setFont(new Font("Cambra", 14));
						actionTarget.setText("*Wrong Username or Password*");}
						
				  }});
	}
	
	
	
	
	/** This hashes a string which is input and returns the corresponding hash
	 * 
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
