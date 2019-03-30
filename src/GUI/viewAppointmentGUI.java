package GUI;

import java.io.File;
import java.util.List;

import JsonFileUtils.Parser;
import Objects.Doctor;
import Objects.Patient;
import Objects.Appointment;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class viewAppointmentGUI extends loginGUI{
	public void startPatient(Stage patientStage, Integer userName) {
		
		//Reader
		String currentDir = System.getProperty("user.dir");
		File path = new File(currentDir);
		Parser parser = new Parser();
		List<Patient> allPatients = parser.parsePatients();
		List<Doctor> allDoctors = parser.parseDoctors();
		List<Appointment> appUser = null;
		Patient patientUser = null;
		Doctor doctorUser = null;

		
		for(Patient patient : allPatients) {
			if(patient.getId().equals(userName)) {
				patientUser = patient;
				break;
				//patient located
			}			
		}


		
		for(Doctor doctor: allDoctors) {
			if(doctor.getSchedule().getAllAppointments(patientUser.getId())!=null) {
				 appUser = doctor.getSchedule().getAllAppointments(patientUser.getId());
				 doctorUser = doctor;
				 break;
				//doctor appointment list
			}
		}
		

		//Boxes

		HBox intro = new HBox();
		setHBox(intro);

		//Labels
		Label actor = new Label();
		actor.setText("Welcome back, "+ patientUser.getFirstName());
		actor.setFont(new Font("Cambria", 32));
		actor.setTextFill(Color.WHITE);

		//Buttons 
		Button logout = new Button("Logout");


		//Panes
		BorderPane patientPane = new BorderPane();

		StackPane introPane = new StackPane();
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		introPane.setAlignment(Pos.CENTER_RIGHT);
		introPane.getChildren().add(logout);
		
		int tabNum =1;
		
		try {
			for(Appointment apps : appUser) {
				VBox patientScreen = new VBox();
				setVBox(patientScreen);
				patientScreen.setSpacing(40);
				setTabBox(patientScreen,apps,doctorUser);
				
				Tab tab = new Tab();
				tab.setGraphic(new Label("Appointment " + tabNum));
				tab.setContent(patientScreen);
				tabPane.getTabs().add(tab);
				tabNum++;
			}
			patientPane.setCenter(tabPane);
			
		}
		catch(Exception e) {
			//no appointment yet
			VBox patientScreen = new VBox();
			setVBox(patientScreen);
			patientScreen.setAlignment(Pos.TOP_CENTER);
			patientScreen.setPadding(new Insets(200,50,50,50));
			Label noApps = new Label("No Current Appointments");
			noApps.setFont(new Font("Arial", 32));
			Label callAdmin = new Label("Please call Administration to book an appointment");
			callAdmin.setFont(new Font("Arial", 16));
			patientScreen.getChildren().addAll(noApps,callAdmin);
			patientPane.setCenter(patientScreen);
		}

		
		

		//Populate Boxes
		intro.getChildren().addAll(actor,introPane);
		intro.setHgrow(introPane, Priority.ALWAYS);
		//patientScreen.getChildren().addAll(tabPane);


		patientPane.setTop(intro);


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
	
	public void setTabBox(VBox patientScreen, Appointment apps, Doctor doctorUser){
		
		//Labels
		Label docLabel = new Label("Doctor: ");
		docLabel.setFont(new Font("Arial", 32));
		
		
		Label datenTime = new Label("Date & Time: ");
		datenTime.setFont(new Font("Arial", 32));
		
		Label docName = new Label("Dr. " + doctorUser.getFirstname() + " " + doctorUser.getLastname()+"\n\n");
		docName.setFont(new Font("Arial", 16));
		
		Label docTime = new Label(apps.getDate()+"\n\n" +apps.getTime());
		docTime.setFont(new Font("Arial", 16));
		
		patientScreen.getChildren().addAll(docLabel,docName,datenTime,docTime);
		patientScreen.setAlignment(Pos.TOP_LEFT);
		patientScreen.setPadding(new Insets(50,50,50,50));
		
	}

}
