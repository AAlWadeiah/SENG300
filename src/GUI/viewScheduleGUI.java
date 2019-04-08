package GUI;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import JsonFileUtils.Parser;
import JsonFileUtils.Writer;
import Objects.Appointment;
import Objects.Availability;
import Objects.Doctor;
import exceptions.dateRangeException;
import exceptions.emptyFieldException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class viewScheduleGUI extends loginGUI{
	
	//private TableView<ArrayList<Appointment>> table = new TableView<>();	
	private TableView<Appointment> table = new TableView<>();
	private Doctor doctorUser;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	/**
	 * displays the doctors appointments on their schedule
	 * @param doctorStage stage that displays doctor
	 * @param userName current Doctor signing in
	 */
	public void startDoctor(Stage doctorStage,String userName) {
		
		//Reader
		String currentDir = System.getProperty("user.dir");
		File path = new File(currentDir);
		Parser parser = new Parser();
		List<Doctor> allDoctors = parser.parseDoctors();
		doctorUser = null;
		
		HashMap <String, ArrayList<Appointment>> docMap  = null;
		
		//gets the current doctor object
		for(Doctor doctor: allDoctors) {
			if(doctor.getId().equals(userName)) {
				 doctorUser = doctor;
				 break;
			}
		}
		
		
		docMap =  doctorUser.getSchedule().getCurrentAppointments();
		
		 Set<String> keys = docMap.keySet();
		 Collection<ArrayList<Appointment>> docVal = docMap.values();

		//Table setup
		 int minwidth = 175;
		//Labels
		final Label label = new Label("Appointment List");
		label.setFont(new Font("Arial", 20));
		
		Label startDate = new Label("Start date:");
		startDate.setFont(new Font("Arial", 16));
		
		Label endDate = new Label("End date:");
		endDate.setFont(new Font("Arial", 16));
		
		TextField sDate = new TextField();
		TextField eDate = new TextField();
		sDate.setPromptText("Enter start date");
		eDate.setPromptText("Enter end date");
		
		
		
			
		final Text actionTarget = new Text();
			
		 
		TableColumn appIDCol = new TableColumn("App ID");
		TableColumn patIDCol = new TableColumn("Patient ID");
		TableColumn dateCol = new TableColumn("Date");
		TableColumn timeCol = new TableColumn("Time");
			
		appIDCol.setMinWidth(minwidth);
		patIDCol.setMinWidth(minwidth);
		dateCol.setMinWidth(minwidth);
		timeCol.setMinWidth(minwidth);
			
		//populate cells

		appIDCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("appointmentId"));
		patIDCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("patientId"));
		dateCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("date"));
		timeCol.setCellValueFactory(new PropertyValueFactory<Appointment,String>("time"));
		
		table.getColumns().addAll(patIDCol,appIDCol,dateCol,timeCol);
		
		try {
		ObservableList<ArrayList<Appointment>> peopleArray = FXCollections.observableArrayList(docMap.values());
		ObservableList<Appointment> people = FXCollections.observableArrayList();

		for(ArrayList<Appointment> rowApp : peopleArray) {
			for( Appointment row : rowApp) {
				people.add(row);
			}
		}
		table.setItems(people);
		
		}
	
		catch(Exception e) {
			actionTarget.setFill(Color.FIREBRICK);
			actionTarget.setFont(new Font("Cambra", 14));
			actionTarget.setText("*No current appointments*");
		}
		

		//Boxes
		VBox doctorScreen = new VBox();
		setVBox(doctorScreen);
		doctorScreen.setPadding(new Insets(-50,50,50,50));
		
		HBox startBox = new HBox();
		startBox.setPadding(new Insets(10,10,10,0));
		startBox.setSpacing(30);
		startBox.setAlignment(Pos.CENTER);
		HBox endBox = new HBox();
		endBox.setPadding(new Insets(10,10,10,0));
		endBox.setSpacing(30);
		endBox.setAlignment(Pos.CENTER);

		HBox intro = new HBox();
		setHBox(intro);

		//Labels
		Label actor = new Label();
		actor.setText("Doctor: ");
		actor.setFont(new Font("Cambria", 32));
		actor.setTextFill(Color.WHITE);

		//Buttons 
		Button reTurn = new Button("Return");
		Button logout = new Button("Logout");
		Button updateAvailability= new Button("Update availability");
		Button viewSchedule = new Button("View schedule");
		Button submit = new Button("Submit");
		updateAvailability.setPrefSize(150, 30);
		viewSchedule.setPrefSize(150, 30);

		//Panes
		BorderPane patientPane = new BorderPane();
		StackPane introPane = new StackPane();
		StackPane returnPane = new StackPane();
		StackPane textPane = new StackPane();
		StackPane submitPane = new StackPane();
		StackPane startPane = new StackPane();
		StackPane endPane = new StackPane();
		introPane.setAlignment(Pos.CENTER_RIGHT);
		introPane.getChildren().add(logout);

		
		returnPane.getChildren().add(reTurn);
		returnPane.setAlignment(Pos.TOP_RIGHT);
		textPane.getChildren().add(actionTarget);
		textPane.setAlignment(Pos.BOTTOM_CENTER);
		submitPane.getChildren().add(submit);
		submitPane.setAlignment(Pos.BOTTOM_RIGHT);
		startPane.getChildren().add(startBox);
		endPane.getChildren().add(endBox);

		
		startBox.getChildren().addAll(startDate,sDate);
		endBox.getChildren().addAll(endDate,eDate);
		

		//Populate Boxes
		intro.getChildren().addAll(actor,introPane);
		intro.setHgrow(introPane, Priority.ALWAYS);
		doctorScreen.getChildren().addAll(updateAvailability,viewSchedule);

		//Set panes and scene
		setBorderpane(patientPane, intro, doctorScreen);
		setScene(patientPane,doctorStage);
				
		//Button events

		//restarts program
		logout.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				start(doctorStage);
			}
		});
		
		updateAvailability.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				doctorScreen.getChildren().clear();
				doctorScreen.setSpacing(50);
				doctorScreen.setAlignment(Pos.TOP_LEFT);
				doctorScreen.setPadding(new Insets(100,50,50,50));
				doctorScreen.getChildren().addAll(returnPane,startPane, endPane, submitPane);
			}
		});
		
		viewSchedule.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				doctorScreen.getChildren().clear();
				doctorScreen.setSpacing(25);
				doctorScreen.setAlignment(Pos.CENTER_LEFT);
				doctorScreen.setPadding(new Insets(50,50,50,50));
				doctorScreen.getChildren().addAll(returnPane, label,table,textPane);
				
				
				
			}
		});
		
		submit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				//when the submit button is pressed we must remove the past red outlines
				
				ObservableList<String> styleClass;	 //get the style classes
				List<TextField> checker = Arrays.asList(sDate,eDate); //create a list of the textboxes can iterate
				for(TextField holder : checker) 
				{
					styleClass = holder.getStyleClass();
					styleClass.removeAll(Collections.singleton("error"));	//remove the red outline
				}
				
				String docStart = sDate.getText();
				String docEnd = eDate.getText();
				
				if(sDate.getText().isEmpty() || eDate.getText().isEmpty()) {
					try {			//we are going to make an exception so we must surround with a try-catch
						for(TextField holder : checker) 
						{
							if (holder.getText().isEmpty())		//if the field is empty
							{
								styleClass = holder.getStyleClass();
								styleClass.add("error");		//make it red
							}
						}
						
						throw new emptyFieldException();
					}
					catch(emptyFieldException f) { } 
					
				}
				else {
					try {
						doctorUser.getAvailability().setWorkDayAvailabilityRange(docStart, docEnd);
						Writer writer = new Writer();
						writer.editObjectToFile(doctorUser, doctorUser.getId());
						
					} catch (dateRangeException e1) {
						e1.printStackTrace();
					}
					//confirmation page
					Label doneAvail = new Label("Doctor Availability updated");
					doneAvail.setFont(new Font("Cambria", 32));
					StackPane donePane = new StackPane();
					donePane.getChildren().add(doneAvail);
					donePane.setAlignment(Pos.CENTER);
					doctorScreen.getChildren().clear();
					doctorScreen.setPadding(new Insets(50,50,50,50));
					doctorScreen.setSpacing(100);
					doctorScreen.getChildren().addAll(returnPane,donePane);
					
				}

			}
		});
		
		reTurn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				startDoctor(doctorStage,userName);
			}
		});
		
	}

}
