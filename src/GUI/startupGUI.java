package GUI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class startupGUI extends Application {

	int size = 800;
	String pfirstName, plastName, pAdd, pNumb, pEmail, pDoct,pId;
	String dfirstName, dlastName, dAdd, dNumb, dEmail,dDoc;
	
	/**
	 * Initializes the startup page accessible by all agents, patients, doctors, and administration.
	 * @param primaryStage The stage that displays the primary panel
	 */
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Hospital Login");

		BorderPane root = new BorderPane();

		//Boxes
		VBox openScreen = new VBox();
		setVBox(openScreen);

		HBox intro = new HBox();
		setHBox(intro);

		//Labels
		Label actor = new Label();
		actor.setText("Hospital Management System ");
		actor.setFont(new Font("Cambria", 32));

		//Buttons
		Button patient = new Button("Patient");
		Button doctor = new Button("Doctor");
		Button admin = new Button("Administration");

		patient.setPrefSize(150, 30);
		doctor.setPrefSize(150, 30);
		admin.setPrefSize(150, 30);


		//Admin button, sets up the Administration panel
		admin.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				adminLoginGUI adminLogin = new adminLoginGUI();
				adminLogin.startAdminLogin(primaryStage);

			}
		});
		
		patient.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				patientLoginGUI patientLogin = new patientLoginGUI();
				patientLogin.startPatientLogin(primaryStage);

			}
		});
		
		doctor.setOnAction(new EventHandler<ActionEvent>(){
			
				@Override
				public void handle(ActionEvent e) {
					doctorLoginGUI doctorLogin = new doctorLoginGUI();
					doctorLogin.startDoctorLogin(primaryStage);

				}
			});


		//populate boxes
		intro.getChildren().addAll(actor);
		openScreen.getChildren().addAll(patient,doctor,admin);

		//Set the borderpane and scene
		setBorderpane(root,intro,openScreen);
		setScene(root,primaryStage);



	}

	/**
	 * Sets up the BorderPane with the desired design: HBox uptop and the VBox in the center
	 * @param Pane BorderPane encompassing all the boxes
	 * @param hbox Box setup up top
	 * @param vbox Box setup center
	 */
	protected void setBorderpane(BorderPane Pane, HBox hbox, VBox vbox) {
		Pane.setTop(hbox);
		Pane.setCenter(vbox);

	}

	/**
	 * Sets the scene to be displayed
	 * @param Pane pane to be front display
	 * @param primaryStage
	 */
	protected void setScene(Pane Pane, Stage primaryStage) {
		Scene scene = new Scene(Pane,size,size);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * Sets the VBox with the design for central buttons
	 * @param name the desired VBox
	 */
	protected void setVBox(VBox name) {
		name.setStyle("-fx-background-color: #FF9966;");
		name.setAlignment(Pos.CENTER);
		name.setSpacing(80);
	}

	/**
	 * Sets the VBox with the design of filling out fields
	 * @param name the desired VBox
	 */
	protected void setformVBox(VBox name) {
		name.setStyle("-fx-background-color: #FF9966;");
		name.setAlignment(Pos.TOP_LEFT);
	}

	/**
	 * Sets the  HBox with a consistent design
	 * @param name the desired HBox
	 */
	protected void setHBox(HBox name) {
		name.setPadding(new Insets(15,12,15,12));
		name.setStyle("-fx-background-color: #5C8BE9;");
	}

}