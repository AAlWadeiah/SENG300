import JsonFileUtils.Writer;
import Objects.Patient;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class newPatientGUI extends adminGUI{

	/**
	 * Loads the page to add a new patient. Once the user submits the new patient's information, a new JSON file is generated for that patient.
	 * @param patientStage 
	 * @param intro
	 * @param adminScreen
	 */
	public void startPatient(Stage patientStage, HBox intro, VBox adminScreen) {

		//Text Field
		TextField fName = new TextField();
		TextField lName = new TextField();
		TextField add = new TextField();
		TextField Num = new TextField();
		TextField Email = new TextField();
		TextField Doc = new TextField();
		TextField ID = new TextField();

		final Text actionTarget = new Text();

		//Buttons
		Button submit = new Button("Submit");
		Button clear = new Button("Clear");
		Button reTurn = new Button("Return");

		//Boxes
		VBox newPatient = new VBox();
		setformVBox(newPatient);

		((Labeled) intro.getChildren().get(0)).setText("New Patient: ");

		//Panes
		GridPane newpatientGrid = new GridPane();
		BorderPane newpatientBorder = new BorderPane();
		setpatientPane(newpatientGrid, submit, clear, reTurn, fName, lName, add, Num, Email, Doc, ID, actionTarget);

		//populate box
		newPatient.getChildren().add(newpatientGrid);


		setBorderpane(newpatientBorder, intro, newPatient);
		setScene(newpatientBorder,patientStage);

		clear.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				fName.clear();
				lName.clear();
				add.clear();
				Num.clear();
				Email.clear();
				Doc.clear();
				ID.clear();
				actionTarget.setText(null);



			}
		});

		reTurn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				startAdmin(patientStage);
			}
		});
		//submit text field
		submit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {

				if(fName.getText().isEmpty() || lName.getText().isEmpty() || add.getText().isEmpty() || Num.getText().isEmpty() || Email.getText().isEmpty() || Doc.getText().isEmpty() || ID.getText().isEmpty()) {
					actionTarget.setFill(Color.FIREBRICK);
					actionTarget.setText("*Please fill in all fields*");
				}
				else {
					pfirstName = fName.getText();
					plastName = lName.getText();
					pAdd = add.getText();
					pNumb = Num.getText();
					pEmail = Email.getText();
					pDoct = Doc.getText();
					pId = ID.getText();

					Patient patient = new Patient(pfirstName, plastName, dAdd, pDoct, pEmail, pNumb, Integer.valueOf(pId));
					Writer writer = new Writer();
					boolean success = writer.writeObjectToFile(patient);
					if (success) {
						System.out.println("Wrote to file successfully");
					}

					BorderPane npPane = new BorderPane();
					((Labeled) intro.getChildren().get(0)).setText("Registration Complete");

					adminScreen.getChildren().clear();
					Label done = new Label("Patient Registration completed");
					done.setFont(new Font("Cambria", 32));
					adminScreen.getChildren().addAll(done,reTurn);
					setBorderpane(npPane,intro, adminScreen);
					setScene(npPane,patientStage);
				}
			}
		});


	}

	/**
	 * Configures the pane where the user enters the new patient's information
	 * @param Pane
	 * @param submit
	 * @param clear
	 * @param reTurn
	 * @param fName
	 * @param lName
	 * @param add
	 * @param Num
	 * @param Email
	 * @param Doc
	 * @param ID
	 * @param actionTarget
	 */
	private void setpatientPane(GridPane Pane, Button submit, Button clear, Button reTurn, TextField fName,TextField lName, TextField add, TextField Num, TextField Email, TextField Doc, TextField ID, Text actionTarget) {

		int gap = 10;
		int pad = 25;
		int xs = 16;
		int ys = 10;
		int end = 40;
		int start = 0;

		//Labels
		Label firstName = new Label("First name: ");
		Label lastName = new Label("Last name: ");
		Label address = new Label("Address: ");
		Label phoneNum = new Label("Phone number: ");
		Label eMail = new Label("Email: ");
		Label patDoc = new Label("Doctor: ");
		Label patID = new Label("Patient ID: ");

		//Text Prompts
		fName.setPromptText("Enter patient's first name");
		lName.setPromptText("Enter patient's last name ");
		add.setPromptText("Enter patient's address");
		Num.setPromptText("Enter patient's phone #");
		Email.setPromptText("Enter patient's email ");
		Doc.setPromptText("Enter patient's physician");
		ID.setPromptText("Enter patient's ID");


		Pane.setAlignment(Pos.TOP_LEFT);
		Pane.setHgap(gap);
		Pane.setVgap(gap);
		Pane.setPadding(new Insets(pad,pad,pad,pad));

		Pane.add(firstName, xs, ys);
		Pane.add(lastName, xs, ys+2);
		Pane.add(address, xs, ys+4);
		Pane.add(phoneNum, xs, ys+6);
		Pane.add(eMail, xs, ys+8);
		Pane.add(patDoc, xs,ys+10);
		Pane.add(patID, xs, ys+12);

		Pane.add(fName, xs+2, ys);
		Pane.add(lName, xs+2, ys+2);
		Pane.add(add, xs+2, ys+4);
		Pane.add(Num, xs+2, ys+6);
		Pane.add(Email, xs+2, ys+8);
		Pane.add(Doc,xs+2, ys+10);
		Pane.add(ID, xs+2, ys+12);

		Pane.add(submit, xs+8, ys+14);
		Pane.add(actionTarget, xs+2, ys+14);
		Pane.add(clear, xs+8, ys);
		Pane.add(reTurn, end, start);



	}

}
