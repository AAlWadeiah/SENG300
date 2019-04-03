package GUI;
import java.io.File;
import java.util.Collections;
import java.util.List;
import JsonFileUtils.Parser;
import JsonFileUtils.Writer;
import Objects.Doctor;
import Objects.Patient;
import Objects.dateFormatException;
import Objects.timeFormatException;
import Objects.next60days;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

public class makeAppointmentGUI extends appointmentGUI{
	
	private next60days days = new next60days();
	
	/**
	 * Loads page to make a new appointment. Displays information of selected patient and allows user to enter a date and time for the appointment.
	 * @param scheduleStage stage that displays the scheduling panel
	 * @param intro HBox to hold consistent form
	 * @param person The patient to make an appointment for.
	 * @param schPatient The schedule object of the patient
	 */
	public void startAppGUI(Stage scheduleStage, HBox intro, Patient person, Integer schPatient) {
		int pad =50;
		//Buttons
		Button submit = new Button("Submit");
		Button clear = new Button("Clear");
		Button reTurn = new Button("Return");	
		
		//get doctor ID
		Integer docID = schPatient;
		

		//Labels & Text
		Label Patient = new Label("Patient: ");
		Patient.setFont(new Font("Arial", 32));
		
		TextField time = new TextField();
		TextField date = new TextField();
		final Text actionTarget = new Text();
		
		//Boxes
		VBox scheduleApp = new VBox();
		setformVBox(scheduleApp);
		
		//Panes
		
		GridPane datePane = new GridPane();
		StackPane returnPane = new StackPane();
		StackPane submitPane = new StackPane();
		StackPane patientPane = new StackPane();
		

		setschedulePane(datePane,clear, person, time, date, actionTarget);
		
		//populate panes and align
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
		
		//Button that submits the patient object and filled out date and time
		submit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				ObservableList<String> timestyleClass = time.getStyleClass();
				ObservableList<String> datestyleClass = date.getStyleClass();
				timestyleClass.removeAll(Collections.singleton("error"));
				datestyleClass.removeAll(Collections.singleton("error"));
				String[] dateArray = date.getText().split("/");
				String[] timeArray = time.getText().split(":");
				
				// Used to catch various exceptions, like putting a letter where numbers should be, or a date not
				// within the next 61 days.
				try {		
					
				//check if forms are empty
				if (date.getText().equals(""))
				{
					ObservableList<String> styleClass = date.getStyleClass();//WE NEED A NEW EXCEPTION FOR EMPTY DATE BOX
					styleClass.add("error");
					throw new Exception();			//TODO: Replace with a popup box  telling to fill the field
				
				}
				
				Integer.parseInt(dateArray[0]); 						
				Integer.parseInt(dateArray[1]);							
				Integer.parseInt(dateArray[2]);		
				
				if( 	dateArray.length!=3 || 
						dateArray[2].length()!=4) 
				{
					throw new dateFormatException();
				}
				
				else if (time.getText().isEmpty())
				{
					ObservableList<String> styleClass = time.getStyleClass();//WE NEED A NEW EXCEPTION FOR EMPTY DATE BOX
					styleClass.add("error");
					throw new Exception();//WE NEED AN EXCEPTION FOR EMPTY TIME BOX
				}
				Integer.parseInt(timeArray[0]);
				Integer.parseInt(timeArray[1]);
				
				if (timeArray.length!=2) {throw new timeFormatException();}
				days.isDateWithinNext60Days(date.getText());
				days.isTimeWithinWorkday(time.getText());
					
					String appDate = date.getText();
					String appTime = time.getText();
					

					
					//Writer & reader
					
					//load objects from JSON files
					String currentDir = System.getProperty("user.dir");
					File path = new File(currentDir);

					Parser parser = new Parser();

					File[] jsonFiles = parser.getFiles(path);
					
					Writer writer = new Writer();
					
					List<Doctor> allDoctors = parser.parseDoctors();
						for(int i =0; i< allDoctors.size();i++) {			//Iterate through all doctors
							Doctor doctorX = allDoctors.get(i);				//Check if the ID's match
							if(doctorX.getId().equals(docID)) {				//if they match

								doctorX.getSchedule().addAppointment(person.getId(), appDate, appTime);	 //Add an appointment based on the time/date input
								days.dateToAvailability(appDate, appTime, doctorX);			//Check the date relative to local Time and update availability
								writer.editObjectToFile(doctorX,i);							//Writing to file
								break;
							}
						}
						

					
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
					System.out.println("\n\n\nIncorrect attempt on making an appointment caught, printing the stack trace\n\n\n");
					a.printStackTrace(System.out);
				}
				
			}
		});
		
		//Clear all the textfields, clear the actionTarget
		clear.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				date.clear();
				time.clear();
				actionTarget.setText(null);
			}
		});
		
		//Returns to the previous panel
		reTurn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				startApp(scheduleStage, intro, person, schPatient);
			}
		});
		
	}

	/**
	 * Configures the pane where the user enters the information for the appointment.
	 * @param Pane Gridpane housing all the elements
	 * @param clear button to clear textfields
	 * @param person Patient object with the pertaining information
	 * @param time scheduled time textfield
	 * @param date scheduled date textfield
	 * @param actionTarget warning text
	 */
	private void setschedulePane(GridPane Pane,Button clear, Patient person, TextField time, TextField date, Text actionTarget) {
		
		int gap = 10;
		int pad = 50;
		int xs = 16;
		int ys = 10;
		
		Label dateLabel = new Label("Date:");
		Label timeLabel = new Label("Time:");
		time.setPromptText("Ho:mm");
		date.setPromptText("Mo/Dy/Yr");
		
		Label firstName = new Label("First Name:");
		Label lastName = new Label("Last Name:");
		Label id = new Label("ID number:");
		Label doc = new Label("Doctor:");
		Label address = new Label("Address:");
		Label phone = new Label("Phone #:");
		Label email = new Label("Email:");
		
		Label pfirstName = new Label(person.getFirstName());
		Label plastName = new Label(person.getLastName());
		Label pid = new Label(person.getId().toString());
		Label pdoc = new Label(person.getDoctor());
		Label paddress = new Label(person.getAddress());
		Label pphone = new Label(person.getNumber());
		Label pemail = new Label(person.getEmail());
		
		Pane.setAlignment(Pos.TOP_LEFT);
		Pane.setHgap(gap);
		Pane.setVgap(gap/1.5);
		Pane.setPadding(new Insets(-30,pad,pad,pad));

		Pane.add(firstName, xs, ys);
		Pane.add(lastName, xs, ys+2);
		Pane.add(id, xs, ys+4);
		Pane.add(doc, xs, ys+6);
		Pane.add(address, xs, ys+8);
		Pane.add(phone, xs,ys+10);
		Pane.add(email, xs, ys+12);
		Pane.add(dateLabel, xs, ys+14);
		Pane.add(timeLabel, xs, ys+16);
		Pane.add(clear, xs+6, ys+16);
		
		Pane.add(pfirstName, xs+4, ys);
		Pane.add(plastName, xs+4, ys+2);
		Pane.add(pid, xs+4, ys+4);
		Pane.add(pdoc, xs+4, ys+6);
		Pane.add(paddress, xs+4, ys+8);
		Pane.add(pphone, xs+4,ys+10);
		Pane.add(pemail, xs+4, ys+12);
		Pane.add(date, xs+4, ys+14);
		Pane.add(time, xs+4, ys+16);
		Pane.add(actionTarget, xs+4, ys+18);
		
	}
}
