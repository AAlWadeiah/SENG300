package GUI;

import java.util.List;

import Objects.Appointment;
import Objects.Patient;
import Objects.Schedule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class appointmentGUI extends newScheduleGUI{


	/**
	 * Loads the page to either add an appointment or update an existing one
	 * @param scheduleStage stage that displays the scheduling panel
	 * @param intro HBox to hold consistent form
	 * @param person The patient for the appointment
	 * @param docID The current doctor for the appointment
	 */
	public void startApp(Stage scheduleStage, HBox intro, Patient person, String docID) {

		int pad =50;
		((Labeled) intro.getChildren().get(0)).setText("Patient Appointment: ");

		//Buttons
		Button addApp = new Button("Add appointment");
		addApp.setPrefSize(150, 30);

		Button updateApp = new Button("Update appointment");
		updateApp.setPrefSize(150, 30);
		Button reTurn = new Button("Return");

		//Boxes
		VBox scheduleApp = new VBox();		
		setformVBox(scheduleApp);
		scheduleApp.setPadding(new Insets(pad,pad,pad,pad));
		scheduleApp.setSpacing(pad+80);
		scheduleApp.setAlignment(Pos.TOP_CENTER);

		//Panes
		BorderPane appBorder = new BorderPane();

		StackPane returnPane = new StackPane();

		returnPane.getChildren().add(reTurn);
		returnPane.setAlignment(Pos.TOP_RIGHT);

		scheduleApp.getChildren().addAll(returnPane,addApp,updateApp);

		setBorderpane(appBorder, intro, scheduleApp);
		setScene(appBorder,scheduleStage);

		//ActionEvent for returning to the previous screen
		reTurn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				startSchedule(scheduleStage, intro);
			}
		});

		//ActionEvent for the adding a new Appointment Button
		addApp.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				makeAppointmentGUI makeApp = new makeAppointmentGUI();
				makeApp.startAppGUI(scheduleStage, intro, person,docID);
			}
		});

		//ActionEven for the updating Appointment Button
		updateApp.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				tableSchGUI changeApp = new tableSchGUI();
				changeApp.startAppTable(scheduleStage, intro, person,docID);
			}
		});
	}
}