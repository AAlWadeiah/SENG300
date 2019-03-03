

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class adminGUI extends startupGUI{

	/**
	 * Loads the main page for the administrator. Enables an administrator to add new patients, doctors, and appointments
	 * @param adminStage The stage on which the admin page is loaded
	 */
	public void startAdmin(Stage adminStage) {

		//Boxes
		VBox adminScreen = new VBox();
		setVBox(adminScreen);

		HBox intro = new HBox();
		setHBox(intro);

		//Labels
		Label actor = new Label();
		actor.setText("Admin: ");
		actor.setFont(new Font("Cambria", 32));

		//Buttons 
		Button logout = new Button("Logout");

		Button adminP = new Button("New patient");
		Button adminD = new Button("New Doctor");
		Button adminS = new Button("Schedule patient");

		adminP.setPrefSize(150, 30);
		adminD.setPrefSize(150, 30);
		adminS.setPrefSize(150, 30);

		//Panes
		BorderPane adminPane = new BorderPane();
		StackPane introPane = new StackPane();
		introPane.setAlignment(Pos.CENTER_RIGHT);
		introPane.getChildren().add(logout);

		//Populate Boxes
		intro.getChildren().addAll(actor,introPane);
		intro.setHgrow(introPane, Priority.ALWAYS);
		adminScreen.getChildren().addAll(adminP,adminD,adminS);

		//Set panes and scene
		setBorderpane(adminPane, intro, adminScreen);
		setScene(adminPane,adminStage);


		//Button events

		//restarts program
		logout.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				start(adminStage);
			}
		});

		//Create new patient
		adminP.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {

				newPatientGUI adminNewpatient = new newPatientGUI();
				adminNewpatient.startPatient(adminStage,intro,adminScreen);

			}
		});

		//Create new doctor
		adminD.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {

				newDoctorGUI adminNewdoctor = new newDoctorGUI();
				adminNewdoctor.startDoctor(adminStage,intro,adminScreen);

			}
		});

		//Schedule a patient
		adminS.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {

				newScheduleGUI adminNewschedule = new newScheduleGUI();
				adminNewschedule.startSchedule(adminStage,intro);

			}
		});


	}

}
