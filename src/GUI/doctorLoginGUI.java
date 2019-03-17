package GUI;

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

public class doctorLoginGUI extends startupGUI
{
	public void startDoctorLogin(Stage doctorStage) {

		//Boxes
			VBox  doctorScreen = new VBox(20);		//spacing is 20 in the VBox
			setVBox(doctorScreen);

			HBox intro = new HBox();
			setHBox(intro);
		
			HBox usernameBox = new HBox();
			setHBox(usernameBox);
		
			HBox passwordBox = new HBox();
			setHBox(passwordBox);

		//Labels
			Label actor = new Label();
			actor.setText("Doctor Login");
			actor.setFont(new Font("Cambria", 32));
			actor.setTextFill(Color.WHITE);
		
			final Text actionTarget = new Text();

		//Buttons 
			Button rreturn = new Button("Return");
			Button submit = new Button("Submit");

			submit.setPrefSize(150, 30);
		
		//Panes
			BorderPane doctorPane = new BorderPane();
			StackPane introPane = new StackPane();
			StackPane submitPane = new StackPane();
		
		//Fields and Labels
			TextField userID = new TextField();
			PasswordField passID = new PasswordField();
			Label passLabel = new Label("Password:      ");
			Label userLabel = new Label("Username:      ");
		
			userLabel.setFont(new Font("Calibiri",20));
			passLabel.setFont(new Font("Calibiri",20));
		
		
		//Adjust boxes
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
		
			doctorScreen.getChildren().addAll(usernameBox, passwordBox);
			doctorScreen.getChildren().addAll(submitPane,actionTarget);
			
		//Set panes and scene
			setBorderpane(doctorPane, intro,  doctorScreen);
			setScene( doctorPane, doctorStage);


	//Button events

		//restarts program
		rreturn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				start(doctorStage);
			}
		});

		//Collect the values in the login boxes and validate their login info
		submit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				//if (new validateAccount().validate(userID.getText(),passID.getText(), "Doctor_Users.json")) 
				//This is where we will handle what happens after a doctor is signed on
				//{}
				if(false) {
					viewScheduleGUI doctorSignin = new viewScheduleGUI();
					doctorSignin.startDoctor(doctorStage);
				}
				
				else {
					actionTarget.setFill(Color.FIREBRICK);
					actionTarget.setFont(new Font("Cambra", 14));
					actionTarget.setText("*Wrong Username or Password*");
				}
				
			}
		});
		
		passID.setOnKeyReleased(event -> {
			  if (event.getCode() == KeyCode.ENTER){
				  if (new validateAccount().validate(userID.getText(),passID.getText(), "Doctor_Users.json")) //first validate their login
					{
					  /**Here is where we would reference the actual patient GUI which micheal will be making
				  		 * you would replace the two below lines with it*/
					  }
				else {
				actionTarget.setFill(Color.FIREBRICK);
				actionTarget.setFont(new Font("Cambra", 14));
				actionTarget.setText("*Wrong Username or Password*");}
			}});


	}
}
