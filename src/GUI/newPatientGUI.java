package GUI;
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
	 * @param patientStage stage that displays the new patient GUI
	 * @param intro HBox to hold consistent format
	 * @param adminScreen VBox to hold consistent format
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
		TextField password = new TextField();

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
		setpatientPane(newpatientGrid, submit, clear, reTurn, fName, lName, add, Num, Email, Doc, ID, password, actionTarget);

		//populate box
		newPatient.getChildren().add(newpatientGrid);

		//set border and scene
		setBorderpane(newpatientBorder, intro, newPatient);
		setScene(newpatientBorder,patientStage);

		//Clear all the textfields
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
				password.clear();
				actionTarget.setText(null);



			}
		});

		//Returns to the previous panel
		reTurn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				startAdmin(patientStage);
			}
		});
		//submit the filled out forms
		submit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {

				//checks if fields are empty
				if(fName.getText().isEmpty() || lName.getText().isEmpty() || add.getText().isEmpty() || Num.getText().isEmpty() || Email.getText().isEmpty() || Doc.getText().isEmpty() || ID.getText().isEmpty() || password.getText().isEmpty()) {
					actionTarget.setFill(Color.FIREBRICK);
					actionTarget.setFont(new Font("Cambra", 14));
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
					pPassword = password.getText();


					//Creates and writes to a JSON file
					Patient patient = new Patient(pfirstName, plastName, pAdd, pDoct, pEmail, pNumb, Integer.valueOf(pId), pPassword);
					Writer writer = new Writer();
					boolean success = writer.writeObjectToFile(patient);
					if (success) {
						System.out.println("Wrote to patient Json file successfully");
					}

					//transition to a confirmation panel
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
	 * @param Pane Gridpane housing all the elements
	 * @param submit button to input patient
	 * @param clear button to clear textfields
	 * @param reTurn button to return to previous slide
	 * @param fName first name textfield
	 * @param lName last name textfield
	 * @param add address textfield
	 * @param Num phone number textfield
	 * @param Email email textfield
	 * @param Doc patient/s doctor textfield
	 * @param ID patient's ID number textfield
	 * @param actionTarget warning text
	 * @param password patient's password
	 */
	private void setpatientPane(GridPane Pane, Button submit, Button clear, Button reTurn, TextField fName,TextField lName, TextField add, TextField Num, TextField Email, TextField Doc, TextField ID, TextField password, Text actionTarget) {

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
		Label pWord = new Label ("Password: ");

		//Text Prompts
		fName.setPromptText("Enter patient's first name");
		lName.setPromptText("Enter patient's last name ");
		add.setPromptText("Enter patient's address");
		Num.setPromptText("Enter patient's phone #");
		Email.setPromptText("Enter patient's email ");
		Doc.setPromptText("Enter patient's physician");
		ID.setPromptText("Enter patient's ID");
		password.setPromptText("Enter patient's password");


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
		Pane.add(pWord, xs, ys+14);

		Pane.add(fName, xs+2, ys);
		Pane.add(lName, xs+2, ys+2);
		Pane.add(add, xs+2, ys+4);
		Pane.add(Num, xs+2, ys+6);
		Pane.add(Email, xs+2, ys+8);
		Pane.add(Doc,xs+2, ys+10);
		Pane.add(ID, xs+2, ys+12);
		Pane.add(password, xs+2, ys+14);

		Pane.add(submit, xs+8, ys+14);
		Pane.add(actionTarget, xs+2, ys+14);
		Pane.add(clear, xs+8, ys);
		Pane.add(reTurn, end, start);



	}

}
