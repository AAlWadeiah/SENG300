package GUI;

import java.io.File;
import java.util.Collections;
import JsonFileUtils.Parser;
import JsonFileUtils.Writer;
import Objects.Appointment;
import Objects.Doctor;
import Objects.Patient;
import exceptions.*;
import Objects.next60days;
import javafx.collections.ObservableList;
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
	public void startUA(Stage scheduleStage, HBox intro, Patient person,Doctor doc, Appointment appPat, int docID) {
		
		Writer writer = new Writer();
		
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
				next60days days = new next60days();
				
				ObservableList<String> timestyleClass = newTime.getStyleClass();
				ObservableList<String> datestyleClass = newDate.getStyleClass();
				timestyleClass.removeAll(Collections.singleton("error"));
				datestyleClass.removeAll(Collections.singleton("error"));
				String[] dateArray = newDate.getText().split("/");
				String[] timeArray = newTime.getText().split(":");
				
				// Used to catch various exceptions, like putting a letter where numbers should be, or a date not
				// within the next 61 days.
				try {		
					
				//check if forms are empty
				if (newDate.getText().isEmpty() && newTime.getText().isEmpty())
				{
					ObservableList<String> styleClass = newDate.getStyleClass();
					styleClass.add("error");
					styleClass = newTime.getStyleClass();
					styleClass.add("error");
					throw new emptyFieldException();
				}
				else if (newDate.getText().isEmpty())
				{
					ObservableList<String> styleClass = newDate.getStyleClass();
					styleClass.add("error");
					throw new emptyFieldException();			
				
				}
				
				Integer.parseInt(dateArray[0]); 						
				Integer.parseInt(dateArray[1]);							
				Integer.parseInt(dateArray[2]);		
				
				if( 	dateArray.length!=3 || 
						dateArray[2].length()!=4) 
				{
					throw new dateFormatException();
				}
				
				else if (newTime.getText().isEmpty())
				{
					ObservableList<String> styleClass = newTime.getStyleClass();//WE NEED A NEW EXCEPTION FOR EMPTY DATE BOX
					styleClass.add("error");
					throw new emptyFieldException();
				}
				Integer.parseInt(timeArray[0]);
				Integer.parseInt(timeArray[1]);
				
				if (timeArray.length!=2) {throw new timeFormatException();}
				days.isDateWithinNext60Days(newDate.getText());
				days.isTimeWithinWorkday(newTime.getText());
					
					String appDate = newDate.getText();
					String appTime = newTime.getText();
					

					
					//Writer & reader
					
					//load objects from JSON files
					String currentDir = System.getProperty("user.dir");
					File path = new File(currentDir);

					Parser parser = new Parser();

					File[] jsonFiles = parser.getFiles(path);
					
					Writer writer = new Writer();
					
					doc.getSchedule().updateAppointment(appPat.getPatientId(), appPat.getAppointmentId(), appDate, appTime); //update the appointment for the doctor
					days.dateToAvailability(appDate, appTime, doc);
					writer.editObjectToFile(doc, docID);			//write to file
					
					
					BorderPane npPane = new BorderPane();
					((Labeled) intro.getChildren().get(0)).setText("Schedule Complete");

					//transitions to confirmation panel
					scheduleApp.getChildren().clear();
					setVBox(scheduleApp);
					Label done = new Label("Patient Scheduling completed");
					done.setFont(new Font("Cambria", 32));
					scheduleApp.getChildren().addAll(done,reTurn);
					setBorderpane(npPane,intro, scheduleApp);
					setScene(npPane,scheduleStage);
					

					
					
				}
				catch (Exception a)
				{
					System.out.println("\n\n\nIncorrect attempt on updating an appointment caught, printing the stack trace\n\n\n");
					a.printStackTrace(System.out);
				}
				
			}
		});
		
		
		//Returns to the previous panel
		reTurn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				startAppTable(scheduleStage, intro, person, doc.getId());
			}
		});
		
		//Clear all the textfields and the actionTarget
		clear.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				newDate.clear();
				newTime.clear();
				actionTarget.setText(null);
				ObservableList<String> timestyleClass = newTime.getStyleClass();
				ObservableList<String> datestyleClass = newDate.getStyleClass();
				timestyleClass.removeAll(Collections.singleton("error"));
				datestyleClass.removeAll(Collections.singleton("error"));
			}
		});
		
	}
	/** Sets the schedule pane in order to organize the large amounts of data on this pane. Resulting in a visually
	 *  appealing table
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

		Pane.add(oldTime, xs, ys+2);
		Pane.add(oldDate, xs, ys);

		Pane.add(timeLabel, xs, ys+6);
		Pane.add(dateLabel, xs, ys+4);
		Pane.add(clear, xs+6, ys+8);
		
		Pane.add(appTime, xs+4, ys+2);
		Pane.add(appDate, xs+4, ys);

		Pane.add(time, xs+4, ys+6);
		Pane.add(date, xs+4, ys+4);
		Pane.add(actionTarget, xs+4, ys+18);
		
	}

}
