package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class viewAppointmentGUI extends patientLoginGUI{
	public void startPatient(Stage patientStage) {
		
		//Boxes
				VBox patientScreen = new VBox();
				setVBox(patientScreen);

				HBox intro = new HBox();
				setHBox(intro);

				//Labels
				Label actor = new Label();
				actor.setText("Patient: ");
				actor.setFont(new Font("Cambria", 32));
				actor.setTextFill(Color.WHITE);

				//Buttons 
				Button logout = new Button("Logout");


				//Panes
				BorderPane patientPane = new BorderPane();
				StackPane introPane = new StackPane();
				introPane.setAlignment(Pos.CENTER_RIGHT);
				introPane.getChildren().add(logout);

				//Populate Boxes
				intro.getChildren().addAll(actor,introPane);
				intro.setHgrow(introPane, Priority.ALWAYS);
				//patientScreen.getChildren().addAll();

				//Set panes and scene
				setBorderpane(patientPane, intro, patientScreen);
				setScene(patientPane,patientStage);
				
				//Button events

				//restarts program
				logout.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent e) {
						start(patientStage);
					}
				});
		
	}

}
