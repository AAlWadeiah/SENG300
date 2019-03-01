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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class newScheduleGUI extends adminGUI{
	
	private TableView table = new TableView();

	public void startSchedule(Stage scheduleStage, HBox intro, VBox adminScreen) {
		
		//Test Field
		
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
		
		firstNameCol.setMinWidth(100);
		lastNameCol.setMinWidth(100);
		IDCol.setMinWidth(100);
		doctorCol.setMinWidth(100);
		addressCol.setMinWidth(100);
		numCol.setMinWidth(100);
		emailCol.setMinWidth(100);
		
		
		
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
		//GridPane scheduleGrid = new GridPane();
		BorderPane scheduleBorder = new BorderPane();
		
		//populate box
		schedulePatient.getChildren().addAll(label,table);
		
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
