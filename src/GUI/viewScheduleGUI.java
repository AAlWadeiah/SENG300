package GUI;

import javafx.scene.input.MouseEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import Objects.WorkDay;
import Objects.next60days;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class viewScheduleGUI extends loginGUI{
	
	//private TableView<ArrayList<Appointment>> table = new TableView<>();	
	private TableView<Appointment> table = new TableView<>();
	private LocalDate date = new next60days().tomorrowDate();
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
		sDate.setPromptText("MM/DD/YYYY");
		eDate.setPromptText("MM/DD/YYYY");
		
		//docAvil
		Availability docAvail = doctorUser.getAvailability();
		
		HashMap<Integer, WorkDay> docAvailMap = docAvail.getAvailability();
		

		
		
		

		
			
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
		docMap =  doctorUser.getSchedule().getCurrentAppointments();
		Set<String> keys = docMap.keySet();
		Collection<ArrayList<Appointment>> docVal = docMap.values();
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
		Button checkAvail = new Button("Check availability");
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
		StackPane checkPane = new StackPane();
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
		checkPane.getChildren().add(checkAvail);
		checkPane.setAlignment(Pos.BOTTOM_CENTER);

		
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
				doctorScreen.getChildren().addAll(returnPane,startPane, endPane, submitPane,checkPane);
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
		
		checkAvail.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				BorderPane root = new BorderPane();
				Stage secondaryStage = new Stage();
				setScene(root,secondaryStage);
				secondaryStage.setTitle("Doctor Availability");
				
				HBox intro = new HBox();
				setHBox(intro);
				VBox openScreen = new VBox();
				setVBox(openScreen);
				setBorderpane(root,intro,openScreen);
				
				Label actor = new Label();
				actor.setText("Availability");
				actor.setTextFill(Color.WHITE);
				actor.setFont(new Font("Cambria", 32));
				intro.getChildren().addAll(actor);
				
				GridPane schedulePane = new GridPane();
				BorderPane availPane = new BorderPane();
				openScreen.getChildren().add(availPane);
				openScreen.setAlignment(Pos.CENTER);
				availPane.setCenter(schedulePane);
				schedulePane.setPadding(new Insets(20,20,20,100));
				
				

				Collection<WorkDay> availVal = docAvailMap.values();
				ObservableList<Boolean> docAvailability = FXCollections.observableArrayList();
				
				
					
				for(WorkDay docAvaiList : availVal) {
					docAvailability.add(docAvaiList.getIsAvailable());
				}			
				int count = 0;
				LocalDate date = new next60days().tomorrowDate();
				for(int row =0; row < docAvailability.size()/5; row ++) {
					for(int col = 0; col < docAvailability.size()/12;col++) {
						Rectangle rec = new Rectangle();
						DateTimeFormatter format = DateTimeFormatter.ofPattern("MMMM dd");
						Label day = new Label(date.plusDays(count).format(format));
						rec.setWidth(120);
						rec.setHeight(40);
						rec.setStroke(Color.BLACK);
						schedulePane.setRowIndex(rec, row);
						schedulePane.setColumnIndex(rec, col);
						/**
						 * 
						 * Right here we could make a popup every time someone clicks on a day showing all the
						 * appointments on that one day
						 * 
						final int count2 = count;
						rec.setOnMouseClicked(new EventHandler<MouseEvent>()
						{
							public void handle(MouseEvent e) 
							{
								final LocalDate date2 = new next60days().tomorrowDate();
								DateTimeFormatter correctFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
								System.out.println("On " + date2.plusDays(count2).format(format) + " doctor has appointments "
										+ doctorUser.getSchedule().getAppointmentsByDate(date2.plusDays(count2).format(correctFormat)));
							}
						});
								*/
						schedulePane.getChildren().addAll(rec);
						if(docAvailability.get(count).equals(true)) {
							rec.setFill(Color.GRAY);
							
						}
						else {
							rec.setFill(Color.RED);
						}
						schedulePane.add(day, col, row);
						count++;
					}
				}

				
				
				
				
				
				
				
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
						System.out.println("CHECK HERE 0");
						doctorUser.getAvailability().setWorkDayAvailabilityRange(docStart, docEnd);
						System.out.println("CHECK HERE 1");
						Writer writer = new Writer();
						writer.editObjectToFile(doctorUser, doctorUser.getId());
						
					
					
					System.out.println("CHECK HERE 3");
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
				catch (dateRangeException e1) {
							System.out.println("CHECK HERE 2"); 
							e1.printStackTrace();
				}
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
