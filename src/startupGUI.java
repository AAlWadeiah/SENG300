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

	@Override
	public void start(Stage primaryStage) {
	
			primaryStage.setTitle("Hospital Login");
			
			BorderPane root = new BorderPane();
			
			//Boxes
			VBox openScreen = new VBox();
			setVBox(openScreen);
			
			HBox intro = new HBox();
			setHBox(intro);

			Label actor = new Label();
			actor.setText("Login: ");
			actor.setFont(new Font("Cambria", 32));
			
			//Buttons
			Button patient = new Button("Patient");
			Button doctor = new Button("Doctor");
			Button admin = new Button("Administration");
			
			patient.setPrefSize(150, 30);
			doctor.setPrefSize(150, 30);
			admin.setPrefSize(150, 30);

			
			admin.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent e) {
					adminGUI adminLogin = new adminGUI();
					adminLogin.startAdmin(primaryStage);

				}
			});
			

			//populate boxes
			intro.getChildren().addAll(actor);
			openScreen.getChildren().addAll(patient,doctor,admin);
			
			
			setBorderpane(root,intro,openScreen);
			setScene(root,primaryStage);

			
	
	}
	

	
	protected void setBorderpane(BorderPane Pane, HBox hbox, VBox vbox) {
		Pane.setTop(hbox);
		Pane.setCenter(vbox);
		
	}
	
	protected void setScene(Pane Pane, Stage primaryStage) {
		Scene scene = new Scene(Pane,size,size);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	protected void setVBox(VBox name) {
		name.setStyle("-fx-background-color: #FF9966;");
		name.setAlignment(Pos.CENTER);
		name.setSpacing(80);
	}
	
	protected void setformVBox(VBox name) {
		name.setStyle("-fx-background-color: #FF9966;");
		name.setAlignment(Pos.TOP_LEFT);
	}
	
	protected void setHBox(HBox name) {
		name.setPadding(new Insets(15,12,15,12));
		name.setStyle("-fx-background-color: #5C8BE9;");
	}
	

	
}

