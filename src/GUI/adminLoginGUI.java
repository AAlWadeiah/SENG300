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
import javafx.stage.Stage;

public class adminLoginGUI extends startupGUI {
	public void startAdminLogin(Stage AdminStage) {

		//Boxes
			VBox  AdminScreen = new VBox(20);		//spacing is 20 in the VBox
			setVBox( AdminScreen);

			HBox intro = new HBox();
			setHBox(intro);
			
			HBox usernameBox = new HBox();
			setHBox(usernameBox);
			
			HBox passwordBox = new HBox();
			setHBox(passwordBox);

		//Labels
			Label actor = new Label();
			actor.setText("Admin Login");
			actor.setFont(new Font("Cambria", 32));
			actor.setTextFill(Color.WHITE);

		//Buttons 
			Button rreturn = new Button("Return");
			Button submit = new Button("Submit");

			submit.setPrefSize(150, 30);

		//Panes
			BorderPane AdminPane = new BorderPane();
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
			
			AdminScreen.getChildren().addAll(usernameBox, passwordBox);
			AdminScreen.getChildren().addAll(submitPane);
				
		//Set panes and scene
			setBorderpane( AdminPane, intro,  AdminScreen);
			setScene( AdminPane, AdminStage);


			//Button events

			//restarts program
			rreturn.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent e) {
					start(AdminStage);
				}
			});

			//Collect the values in the login boxes and validate their login info
			submit.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent e) {
					
					//if (new validateAccount().validate(userID.getText(),passID.getText(), "Admin_Users.json")) //first validate their login
					if (true)
						{
						adminGUI signonSuccess = new adminGUI();
						signonSuccess.startAdmin(AdminStage);
						}
					
					else { /**Need to handle the scenario where the password is incorrect*/}
					}
			});
			
			passID.setOnKeyReleased(event -> {
				  if (event.getCode() == KeyCode.ENTER){
					 // if (new validateAccount().validate(userID.getText(),passID.getText(), "Admin_Users.json")) //first validate their login
						
					  	if (true)
					  	{
					  		/**Here is where we would reference the actual patient GUI which micheal will be making
					  		 * you would replace the two below lines with it*/
					  		
					  		adminGUI signonSuccess = new adminGUI();
							signonSuccess.startAdmin(AdminStage);
						}
					else {/**Need to handle the scenario where the password is incorrect*/}}
				});


		}
}
