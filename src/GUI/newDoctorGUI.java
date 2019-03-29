package GUI;
import JsonFileUtils.Writer;
import Objects.Availability;
import Objects.Doctor;
import Objects.Schedule;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class newDoctorGUI extends adminGUI{

	/**
	 * Loads the page to add a new doctor.
	 * @param doctorStage stage that displays the new Doctor GUI
	 * @param intro HBox to hold consistent format
	 * @param adminScreen VBox to hold consistent format
	 */
	public void startDoctor(Stage doctorStage, HBox intro, VBox adminScreen) {

		//Text Field
		TextField fName = new TextField();
		TextField lName = new TextField();
		TextField add = new TextField();
		TextField Num = new TextField();
		TextField Email = new TextField();
		TextField Spec = new TextField();//department
		TextField ID = new TextField();
		TextField password = new TextField();

		final Text actionTarget = new Text();

		//Buttons
		Button submit = new Button("Submit");
		Button clear = new Button("Clear");
		Button reTurn = new Button("Return");

		//Boxes
		VBox newDoctor = new VBox();
		setformVBox(newDoctor);

		((Labeled) intro.getChildren().get(0)).setText("New Doctor: ");

		//Panes
		GridPane newdoctorGrid = new GridPane();
		BorderPane newdoctorBorder = new BorderPane();
		setdoctorPane(newdoctorGrid, submit, clear, reTurn, fName, lName, add, Num, Email, Spec, ID, password, actionTarget);

		//populate box
		newDoctor.getChildren().add(newdoctorGrid);

		//set border and scene
		setBorderpane(newdoctorBorder, intro, newDoctor);
		setScene(newdoctorBorder,doctorStage);

		//Clear all the textfields
		clear.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				fName.clear();
				lName.clear();
				add.clear();
				Num.clear();
				Email.clear();
				Spec.clear();
				password.clear();
				actionTarget.setText(null);
			}
		});

		//Returns to the previous panel
		reTurn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				startAdmin(doctorStage);
			}
		});

		//Submit the filled out forms
		submit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {

				//checks if fields are empty
				if(fName.getText().isEmpty() || lName.getText().isEmpty() || add.getText().isEmpty() || Num.getText().isEmpty() || Email.getText().isEmpty() || Spec.getText().isEmpty() || ID.getText().isEmpty() || password.getText().isEmpty()) {
					actionTarget.setFill(Color.FIREBRICK);
					actionTarget.setFont(new Font("Cambra", 14));
					actionTarget.setText("*Please fill in all fields*");
				}
				else {
					dfirstName = fName.getText();
					dlastName = lName.getText();
					dAdd = add.getText();
					dNumb = Num.getText();
					dEmail = Email.getText();
					dDoc = Spec.getText();
					dId = ID.getText();
					dPassword = setPassword(password.getText());

					//user confirmation
					System.out.println("Input confirm print: " + dfirstName + " " + dlastName + " " + dAdd + " " + dNumb + " " + dEmail + " " +dDoc + " " + dId);

					//creates and writes JSON file
					//once Doctor ID has been added to GUI input, put into index 0 of constructor call below.
					Schedule schDoc = new Schedule(Integer.valueOf(dId));
					Availability avaDoc = new Availability();
					Doctor doctor = new Doctor(Integer.valueOf(dId),dfirstName, dlastName, dDoc, dNumb, dEmail, dAdd, dPassword,schDoc,avaDoc);//////////////////////////////////////////////
					Writer writer = new Writer();
					boolean success = writer.writeObjectToFile(doctor);
					if (success){
						System.out.println("Wrote to Dr Json file successfully");
					}
					else
						System.out.println("Error! doctor no write JSON :(");


					//transition to a confirmation panel
					BorderPane npPane = new BorderPane();
					((Labeled) intro.getChildren().get(0)).setText("Registration Complete");

					adminScreen.getChildren().clear();
					Label done = new Label("Doctor registration completed");
					done.setFont(new Font("Cambria", 32));
					adminScreen.getChildren().addAll(done,reTurn);
					setBorderpane(npPane,intro, adminScreen);
					setScene(npPane,doctorStage);
				}
			}
		});
	}

	/**
	 * Configures the pane where the user enters the new doctor's information.
	 * @param Pane Gridpane housing all the elements
	 * @param submit submit button to input doctor
	 * @param clear button to clear textfields
	 * @param reTurn button to return to previous slide
	 * @param fName first name textfield
	 * @param lName last name textfield
	 * @param add address textfield
	 * @param Num phone number textfield
	 * @param Email email address textfield
	 * @param spec	doctor department textfield
	 * @param actionTarget warning text
	 * @param password doctor's password
	 */
	private void setdoctorPane(GridPane Pane, Button submit, Button clear, Button reTurn, TextField fName,TextField lName, TextField add, TextField Num, TextField Email, TextField spec, TextField ID, TextField password, Text actionTarget) {

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
		Label email = new Label("Email: ");
		Label docSpec = new Label("Department: ");
		Label docID = new Label("Doctor ID: ");
		Label pWord = new Label ("Password: ");

		//Text Prompts
		fName.setPromptText("Enter doctor's first name");
		lName.setPromptText("Enter doctor's last name ");
		add.setPromptText("Enter doctor's address");
		Num.setPromptText("Enter doctor's phone #");
		Email.setPromptText("Enter doctor's email ");
		spec.setPromptText("Enter doctor's department");
		ID.setPromptText("Enter doctor's ID");
		password.setPromptText("Enter patient's password");


		Pane.setAlignment(Pos.TOP_LEFT);
		Pane.setHgap(gap);
		Pane.setVgap(gap);
		Pane.setPadding(new Insets(pad,pad,pad,pad));

		Pane.add(firstName, xs, ys);
		Pane.add(lastName, xs, ys+2);
		Pane.add(address, xs, ys+4);
		Pane.add(phoneNum, xs, ys+6);
		Pane.add(email, xs, ys+8);
		Pane.add(docSpec, xs,ys+10);
		Pane.add(docID, xs, ys+12);
		Pane.add(pWord, xs, ys+14);


		Pane.add(fName, xs+2, ys);
		Pane.add(lName, xs+2, ys+2);
		Pane.add(add, xs+2, ys+4);
		Pane.add(Num, xs+2, ys+6);
		Pane.add(Email, xs+2, ys+8);
		Pane.add(spec, xs+2, ys+10);
		Pane.add(ID, xs+2, ys+12);
		Pane.add(password, xs+2, ys+14);

		Pane.add(submit, xs+8, ys+14);
		Pane.add(actionTarget, xs+2, ys+14);
		Pane.add(clear, xs+8, ys);
		Pane.add(reTurn, end, start);
	}


	/**
	 * @param p The users text version password
	 * @return The users hashed password
	 */
	private String setPassword(String p){
		String password = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(p.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < bytes.length; i++){
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			password = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
	}
}

