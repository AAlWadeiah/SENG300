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
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class tableSchGUI extends appointmentGUI{
	
	private TableView<Appointment> table = new TableView<Appointment>();
	private Appointment appPat;
	
	public void startAppTable(Stage scheduleStage, HBox intro, Patient person,Schedule schPatient) {
		
		int minwidth = 175;
		
		//Labels
		final Label label = new Label("Appointment List");
		label.setFont(new Font("Arial", 20));
		
		final Text actionTarget = new Text();
		
		//Table setup
		TableColumn appIDCol = new TableColumn("App ID");
		TableColumn patIDCol = new TableColumn("Patient ID");
		TableColumn dateCol = new TableColumn("Date");
		TableColumn timeCol = new TableColumn("Time");
		
		appIDCol.setMinWidth(minwidth);
		patIDCol.setMinWidth(minwidth);
		dateCol.setMinWidth(minwidth);
		timeCol.setMinWidth(minwidth);
		
		//populate cells
		appIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
		patIDCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
		
		try {
			List <Appointment> allApps = schPatient.getAllAppointments(person.getId());
			//System.out.println(allApps.size());
			ObservableList<Appointment> people = FXCollections.observableArrayList(allApps);
			table.setItems(people);
		}
		catch(Exception e) {
			actionTarget.setFill(Color.FIREBRICK);
			actionTarget.setFont(new Font("Cambra", 14));
			actionTarget.setText("*No current appointments*");
			
		}
		table.getColumns().addAll(patIDCol,appIDCol,dateCol,timeCol);
		
		//click event based on selection of appointment in the table
		table.setOnMouseClicked((MouseEvent e)->{
			appPat = table.getSelectionModel().getSelectedItem();


		});
		
		//Buttons
		Button reTurn = new Button("Return");
		Button remove = new Button("Remove");
		Button update = new Button("Update");
		
		//Boxes
		VBox schedulePatient = new VBox();
		schedulePatient.setStyle("-fx-background-color: #c5c9cc;");
		schedulePatient.setSpacing(25);
		schedulePatient.setAlignment(Pos.CENTER_LEFT);
		schedulePatient.setPadding(new Insets(50,50,50,50));
		
		HBox changePatient = new HBox();
		changePatient.setStyle("-fx-background-color: #c5c9cc;");
		changePatient.setAlignment(Pos.CENTER_RIGHT);
		changePatient.setSpacing(15);
		changePatient.getChildren().addAll(remove,update);
		
		//Panes
		StackPane returnPane = new StackPane();
		StackPane textPane = new StackPane();
		StackPane buttonPane = new StackPane();
		
		returnPane.getChildren().add(reTurn);
		returnPane.setAlignment(Pos.TOP_RIGHT);
		textPane.getChildren().add(actionTarget);
		textPane.setAlignment(Pos.BOTTOM_CENTER);
		buttonPane.getChildren().add(changePatient);
		buttonPane.setAlignment(Pos.BOTTOM_RIGHT);
		BorderPane scheduleBorder = new BorderPane();

		//populate box
		schedulePatient.getChildren().addAll(returnPane, label,table,buttonPane,textPane);

		setBorderpane(scheduleBorder, intro, schedulePatient);
		setScene(scheduleBorder,scheduleStage);
		
		reTurn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				startApp(scheduleStage, intro, person, schPatient);
			}
		});
		
		remove.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				if(appPat!=null) {
					schPatient.removeAppointment(appPat.getPatientId(),appPat.getAppointmentId());
					
					//transition to confirmation panel
					BorderPane npPane = new BorderPane();
					((Labeled) intro.getChildren().get(0)).setText("Appointment Removed");

					//transitions to confirmation panel
					schedulePatient.getChildren().clear();
					setVBox(schedulePatient);
					Label done = new Label("Appointment removed");
					done.setFont(new Font("Cambria", 32));
					schedulePatient.getChildren().addAll(done,reTurn);
					setBorderpane(npPane,intro, schedulePatient);
					setScene(npPane,scheduleStage);
				}
			}
		});
		
		update.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				if(appPat!=null) {
					updateAppointmentGUI upAppGUI = new updateAppointmentGUI();
					upAppGUI.startUA(scheduleStage, intro, person, schPatient,appPat);
					

				}
			}
		});
		
	}


}
