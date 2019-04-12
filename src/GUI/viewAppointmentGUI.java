package GUI;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import JsonFileUtils.Parser;
import JsonFileUtils.Writer;
import Objects.Doctor;
import Objects.Patient;
import exceptions.emptyFieldException;
import Objects.Appointment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class viewAppointmentGUI extends loginGUI{
	/**
	 * Patient views their upcoming appointments in a tabbed window
	 * @param patientStage
	 * @param userName patients user name to sign in
	 */
	public void startPatient(Stage patientStage, String userName) {

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
			if((patient.getId()).equals(userName)) {
				patientUser = patient;
				break;
				//patient located
			}			
		}

		//Boxes
		HBox intro = new HBox();
		setHBox(intro);

		//Labels
		Label actor = new Label();
		actor.setText("Patient: ");
		actor.setFont(new Font("Cambria", 32));
		actor.setTextFill(Color.WHITE);

		//Changing Password Stuff
		VBox pScreen = new VBox();
		setVBox(pScreen);
		pScreen.setPadding(new Insets(-50,50,50,50));

		final Text actionTarget = new Text();

		Label oldPassword = new Label("Old Password:");
		oldPassword.setFont(new Font("Arial", 16));

		Label newPassword = new Label("New Password:");
		newPassword.setFont(new Font("Arial", 16));

		TextField oPWord = new TextField();
		TextField nPWord = new TextField();

		oPWord.setPromptText("Enter Old Password");
		nPWord.setPromptText("Enter New Password");

		Button reTurn = new Button("Return");
		Button submit = new Button("Submit");

		HBox startBox = new HBox();
		startBox.setPadding(new Insets(10,10,10,0));
		startBox.setSpacing(30);
		startBox.setAlignment(Pos.CENTER);

		HBox endBox = new HBox();
		endBox.setPadding(new Insets(10,10,10,0));
		endBox.setSpacing(30);
		endBox.setAlignment(Pos.CENTER);

		startBox.getChildren().addAll(oldPassword,oPWord);
		endBox.getChildren().addAll(newPassword,nPWord);

		StackPane returnPane = new StackPane();
		StackPane startPane = new StackPane();
		StackPane endPane = new StackPane();
		StackPane submitPane = new StackPane();
		StackPane textPane = new StackPane();

		textPane.getChildren().add(actionTarget);
		returnPane.getChildren().add(reTurn);
		startPane.getChildren().add(startBox);
		endPane.getChildren().add(endBox);
		submitPane.getChildren().add(submit);

		returnPane.setAlignment(Pos.TOP_RIGHT);
		submitPane.setAlignment(Pos.BOTTOM_RIGHT);
		textPane.setAlignment(Pos.BOTTOM_CENTER);

		//Buttons 
		Button logout = new Button("Logout");
		Button changePassword = new Button("Change Password");

		//Panes
		BorderPane patientPane = new BorderPane();

		StackPane introPane = new StackPane();
		StackPane test = new StackPane();
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		test.setAlignment(Pos.BOTTOM_RIGHT);
		test.getChildren().add(changePassword);
		introPane.setAlignment(Pos.TOP_RIGHT);
		introPane.getChildren().add(logout);

		int tabNum =1;

		try {

			for(Doctor doctor: allDoctors) {
				if(doctor.getSchedule().getAllAppointments(patientUser.getId())!=null) {
					appUser = doctor.getSchedule().getAllAppointments(patientUser.getId());
					doctorUser = doctor;
					break;
				}
			}
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
		intro.getChildren().addAll(actor,introPane, test);
		intro.setHgrow(introPane, Priority.ALWAYS);
		intro.setHgrow(test, Priority.ALWAYS);

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

		//changes password
		changePassword.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				//start(patientStage);

				pScreen.getChildren().clear();
				pScreen.setSpacing(50);
				pScreen.setAlignment(Pos.TOP_LEFT);
				pScreen.setPadding(new Insets(100,50,50,50));
				pScreen.getChildren().addAll(returnPane,startPane, endPane, submitPane, textPane);
				setBorderpane(patientPane, intro, pScreen);
			}
		});

		submit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				//when the submit button is pressed we must remove the past red outlines

				ObservableList<String> styleClass;	 //get the style classes
				List<TextField> checker = Arrays.asList(oPWord,nPWord); //create a list of the textboxes can iterate
				for(TextField holder : checker) 
				{
					styleClass = holder.getStyleClass();
					styleClass.removeAll(Collections.singleton("error"));	//remove the red outline
				}

				String oldPWord = oPWord.getText();
				String newPWord = nPWord.getText();

				if(oPWord.getText().isEmpty() || nPWord.getText().isEmpty()) {
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
					//handle
					if (new validateAccount().validate(userName,getHash(oPWord.getText()), "Patient"))
					{
						String newPasswordHash = setPassword(newPWord);
						for(Patient patient : allPatients) {
							if((patient.getId()).equals(userName)) {
								Patient patientUser = patient;
								patientUser.setPassword(newPasswordHash);
								Writer writer = new Writer();
								writer.editObjectToFile(patientUser, patientUser.getId());
								break;
								//patient located
							}			
						}

						//confirmation page
						Label doneAvail = new Label("Password Changed!");
						doneAvail.setFont(new Font("Cambria", 32));
						StackPane donePane = new StackPane();
						donePane.getChildren().add(doneAvail);
						donePane.setAlignment(Pos.CENTER);
						pScreen.getChildren().clear();
						pScreen.setPadding(new Insets(50,50,50,50));
						pScreen.setSpacing(100);
						pScreen.getChildren().addAll(returnPane,donePane);
					}
					else 
					{
						actionTarget.setFill(Color.FIREBRICK);
						actionTarget.setFont(new Font("Cambra", 14));
						actionTarget.setText("*Wrong Username or Password*");
					}
				}
			}
		});

		reTurn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				startPatient(patientStage,userName);
			}
		});
	}

	/**
	 * sets up the tab pane
	 * @param patientScreen
	 * @param apps
	 * @param doctorUser
	 */
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

	/** This method sets the actual attribute of the hash
	 * 
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

	/** This hashes a string which is input and returns the corresponding hash
	 * 
	 * @param p The users text version password
	 * @return The users hashed password
	 */
	private String getHash(String p){
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
