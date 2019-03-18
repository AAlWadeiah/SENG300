package GUI;

import Objects.Appointment;
import Objects.Patient;
import Objects.Schedule;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class updateAppointmentGUI extends tableSchGUI{

	/**
	 * Updates an appointment with a new date and time
	 * @param scheduleStage current stage
	 * @param intro consistent form
	 * @param person current patient
	 * @param schPatient current schedule
	 * @param appPat current appointment
	 */
	public void startUA(Stage scheduleStage, HBox intro, Patient person,Schedule schPatient, Appointment appPat) {
		
		int pad =50;

		((Labeled) intro.getChildren().get(0)).setText("Update Appointment: ");
		
		//Text Field
		Label Patient = new Label("Appointment: ");
		Patient.setFont(new Font("Arial", 32));
		TextField newTime = new TextField();
		TextField newDate = new TextField();
		final Text actionTarget = new Text();
		
		//Buttons
		Button submit = new Button("Submit");
		Button clear = new Button("Clear");
		Button reTurn = new Button("Return");
		
		//Boxes
		VBox scheduleApp = new VBox();
		setformVBox(scheduleApp);
		
		//Panes
		GridPane datePane = new GridPane();
		StackPane returnPane = new StackPane();
		StackPane submitPane = new StackPane();
		StackPane patientPane = new StackPane();
		

		setschedulePane(datePane,clear, appPat, newTime, newDate, actionTarget);
		
		patientPane.getChildren().add(Patient);
		patientPane.setAlignment(Pos.TOP_LEFT);
		
		returnPane.getChildren().add(reTurn);
		returnPane.setAlignment(Pos.TOP_RIGHT);
		
		submitPane.getChildren().add(submit);
		submitPane.setAlignment(Pos.BOTTOM_RIGHT);
		
		BorderPane appBorder = new BorderPane();

		//populate box
		scheduleApp.getChildren().addAll(returnPane,patientPane, datePane,submitPane);
		scheduleApp.setPadding(new Insets(pad,pad,pad,pad));

		setBorderpane(appBorder, intro, scheduleApp);
		setScene(appBorder,scheduleStage);
		
		//Button that submits the appointment object and filled out date and time
		submit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				//check if forms are empty
				if(newDate.getText().isEmpty() || newTime.getText().isEmpty()) {
					actionTarget.setFill(Color.FIREBRICK);
					actionTarget.setFont(new Font("Cambra", 14));
					actionTarget.setText("*Please fill in all fields*");
				}
				else {
					String appDate = newDate.getText();
					String appTime = newTime.getText();
					
					schPatient.updateAppointment(appPat.getPatientId(), appPat.getAppointmentId(), appDate, appTime);
				

					
					BorderPane npPane = new BorderPane();
					((Labeled) intro.getChildren().get(0)).setText("Update Complete");

					//transitions to confirmation panel
					scheduleApp.getChildren().clear();
					setVBox(scheduleApp);
					Label done = new Label("Appointment update completed");
					done.setFont(new Font("Cambria", 32));
					scheduleApp.getChildren().addAll(done,reTurn);
					setBorderpane(npPane,intro, scheduleApp);
					setScene(npPane,scheduleStage);
					

					
					
				}
			}
		});		
		
		
		//Returns to the previous panel
		reTurn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				startAppTable(scheduleStage, intro, person, schPatient);
			}
		});
		
		//Clear all the textfields
		clear.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				newDate.clear();
				newTime.clear();
				actionTarget.setText(null);
			}
		});
		
	}
	/**
	 * 
	 * @param Pane
	 * @param clear button
	 * @param appPat Appointment
	 * @param time
	 * @param date
	 * @param actionTarget warning
	 */
	private void setschedulePane(GridPane Pane,Button clear, Appointment appPat, TextField time, TextField date, Text actionTarget) {
		
		int gap = 10;
		int pad = 50;
		int xs = 16;
		int ys = 10;
		
		Label dateLabel = new Label("New date:");
		Label timeLabel = new Label("New time:");
		time.setPromptText("Ho:mm");
		date.setPromptText("Mo/Dy/Yr");
		
		Label oldTime = new Label("Old time:");
		Label oldDate = new Label("Old date:");

		
		Label appDate = new Label(appPat.getDate());
		Label appTime = new Label(appPat.getTime());

		
		Pane.setAlignment(Pos.TOP_LEFT);
		Pane.setHgap(gap);
		Pane.setVgap(gap/1.5);
		Pane.setPadding(new Insets(-30,pad,pad,pad));

		Pane.add(oldTime, xs, ys);
		Pane.add(oldDate, xs, ys+2);

		Pane.add(timeLabel, xs, ys+4);
		Pane.add(dateLabel, xs, ys+6);
		Pane.add(clear, xs+6, ys+8);
		
		Pane.add(appTime, xs+4, ys);
		Pane.add(appDate, xs+4, ys+2);

		Pane.add(time, xs+4, ys+4);
		Pane.add(date, xs+4, ys+6);
		Pane.add(actionTarget, xs+4, ys+18);
		
	}

}
