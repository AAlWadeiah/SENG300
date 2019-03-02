import java.io.File;
import java.util.List;

import JsonFileUtils.Parser;
import Objects.Patient;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class newScheduleGUI extends adminGUI{
	
	private TableView table = new TableView();

	public void startSchedule(Stage scheduleStage, HBox intro, VBox adminScreen) {
		
		int minwidth = 100;

		//Labels
		final Label label = new Label("Patient List");
		label.setFont(new Font("Arial", 20));
		
		table.setEditable(true);
		
		TableColumn firstNameCol = new TableColumn("First Name");
		TableColumn lastNameCol = new TableColumn("Last Name");
		TableColumn IDCol = new TableColumn("Patient ID");
		TableColumn doctorCol = new TableColumn("Doctor");
		TableColumn addressCol = new TableColumn("Address");
		TableColumn	numCol = new TableColumn("Phone number");
		TableColumn emailCol = new TableColumn("Email");
		
		firstNameCol.setMinWidth(minwidth);
		lastNameCol.setMinWidth(minwidth);
		IDCol.setMinWidth(minwidth);
		doctorCol.setMinWidth(minwidth);
		addressCol.setMinWidth(minwidth);
		numCol.setMinWidth(minwidth);
		emailCol.setMinWidth(minwidth);
		
		
		
		String currentDir = System.getProperty("user.dir");
	    File path = new File(currentDir);
	    
	    Parser person = new Parser();
	    
	    File[] jsonFiles = person.getFiles(path);
	    List<Patient> allPatients = person.parsePatients(jsonFiles);
	    
	    for (Patient patient : allPatients) {
			System.out.println(patient.getFirstName() + " " + patient.getLastName()+" " + patient.getAddress() +" " + patient.getDoctor() + " " + patient.getEmail() + " " +patient.getNumber() + " " + patient.getId());
		}
		
	    firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
	    lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	    IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
	    doctorCol.setCellValueFactory(new PropertyValueFactory<>("doctor"));
	    addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
	    numCol.setCellValueFactory(new PropertyValueFactory<>("number"));
	    emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
	    
	    
	    ObservableList<Patient> people = FXCollections.observableArrayList(allPatients);
	    table.setItems(people);
	    
	    
	    
		
		table.getColumns().addAll(firstNameCol,lastNameCol,IDCol,doctorCol,addressCol,numCol,emailCol);
		
		
		//Buttons
		Button submit = new Button("Submit");
		Button clear = new Button("Clear");
		Button reTurn = new Button("Return");
		
		//boxes
		VBox schedulePatient = new VBox();
		schedulePatient.setStyle("-fx-background-color: #FF9966;");
		schedulePatient.setSpacing(25);
		schedulePatient.setAlignment(Pos.CENTER_LEFT);
		schedulePatient.setPadding(new Insets(50,50,50,50));
		
		((Labeled) intro.getChildren().get(0)).setText("Schedule patient: ");
		
		//Panes
		BorderPane scheduleBorder = new BorderPane();
		
		//populate box
		schedulePatient.getChildren().addAll(label,table,reTurn);
		
		setBorderpane(scheduleBorder, intro, schedulePatient);
		setScene(scheduleBorder,scheduleStage);
		
		
		
		reTurn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				startAdmin(scheduleStage);
			}
		});
		
	}

}
