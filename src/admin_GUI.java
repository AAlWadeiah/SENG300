

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONObject;


import java.io.FileWriter;
import java.io.IOException;


public class admin_GUI extends Main{

	public void startAdmin(Stage primaryStage) {
		
		//Boxes
		VBox newPatient = new VBox();
		setpatVBox(newPatient);
		
		VBox newDoctor = new VBox();
		setpatVBox(newDoctor);
				
		VBox adminScreen = new VBox();;
		setVBox(adminScreen);
		
		HBox intro = new HBox();
		setHBox(intro);
		
		//Admin Screen
		Label actor = new Label();
		actor.setText("Admin: ");
		actor.setFont(new Font("Cambria", 32));
		Button logout = new Button("Logout");
		
		
		intro.getChildren().addAll(actor, logout);
		intro.setSpacing(400);
		
		BorderPane adminPane = new BorderPane();
		setBpane(adminPane, intro, adminScreen);
		setScene(adminPane,primaryStage);
		
		
		//Labels
		Label firstName = new Label("First name: ");
		Label lastName = new Label("Last name: ");
		Label address = new Label("Address: ");
		Label phoneNum = new Label("Phone number: ");
		Label eMail = new Label("Email: ");
		Label patDoc = new Label("Doctor: ");
		Label patID = new Label("Patient ID: ");
		Label docSpec = new Label("Specialism:");
		
		//text fields
		TextField fName = new TextField();
		TextField lName = new TextField();
		TextField add = new TextField();
		TextField pNum = new TextField();
		TextField pEmail = new TextField();
		TextField pDoc = new TextField();
		TextField pID = new TextField();
		
		TextField dfName = new TextField();
		TextField dlName = new TextField();
		TextField dadd = new TextField();
		TextField dNum = new TextField();
		TextField dEmail = new TextField();
		TextField dspec = new TextField();
		
		
		
		final Text actionTarget = new Text();
		
		//Text Prompts
		fName.setPromptText("Enter patient's first name");
		lName.setPromptText("Enter patient's last name ");
		add.setPromptText("Enter patient's address");
		pNum.setPromptText("Enter patient's phone #");
		pEmail.setPromptText("Enter patient's email ");
		pDoc.setPromptText("Enter patient's physician");
		pID.setPromptText("Enter patient's ID");
		
		dfName.setPromptText("Enter doctor's first name");
		dlName.setPromptText("Enter doctor's last name ");
		dadd.setPromptText("Enter doctor's address");
		dNum.setPromptText("Enter doctors's phone #");
		dEmail.setPromptText("Enter doctor's email ");
		dspec.setPromptText("Enter doctor's specialism");
		
		
				
		//Buttons
		Button adminP = new Button("New patient");
		Button adminD = new Button("New Doctor");
		Button adminA = new Button("Schedule patient");
		
	
		Button submitP = new Button("Submit");
		Button submitD = new Button("Submit");
		Button clearP = new Button("Clear");
		Button clearD = new Button("Clear");
		Button reTurn = new Button("Return");
		
		
		adminP.setPrefSize(150, 30);
		adminD.setPrefSize(150, 30);
		adminA.setPrefSize(150, 30);		
		
		
		
		//populate VBox
		adminScreen.getChildren().addAll(adminP,adminD,adminA);
		
		
		//Admin new Patient
		adminP.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				GridPane newpatPane = new GridPane();
				setadminpatPane(newpatPane, firstName, lastName,address,phoneNum, patDoc,fName,lName,add,pNum,pDoc,submitP,pEmail,eMail, actionTarget, clearP,reTurn,pID,patID);
				newPatient.getChildren().add(newpatPane);
				actor.setText("New Patient:");
				BorderPane npPane = new BorderPane();
				setBpane(npPane,intro, newPatient);
				setScene(npPane,primaryStage);
				

			}
		});
		
		//Admin new Doctor
		adminD.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				GridPane newdocPane = new GridPane();
				setadmindocPane(newdocPane, clearD, reTurn, submitD, actionTarget, firstName, lastName, docSpec ,address, phoneNum, eMail, dfName, dlName,dspec, dadd, dNum, dEmail);
				newDoctor.getChildren().add(newdocPane);
				actor.setText("New Doctor:");
				BorderPane ndPane = new BorderPane();
				setBpane(ndPane,intro, newDoctor);
				setScene(ndPane,primaryStage);
				

			}
		});
		
		//return
		reTurn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				 startAdmin(primaryStage);
				
				

			}
		});
		
		//restarts program
		logout.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				 start(primaryStage);
				
				

			}
		});
		
		//clear text field
		clearD.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				dfName.clear();
				dlName.clear();
				dadd.clear();
				dNum.clear();
				dEmail.clear();
				dspec.clear();
				
				

			}
		});
		
		clearP.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				fName.clear();
				lName.clear();
				add.clear();
				pNum.clear();
				pEmail.clear();
				pDoc.clear();
				
				

			}
		});
		
		//submit text field
		submitP.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {

				if(fName.getText().isEmpty() || lName.getText().isEmpty() || add.getText().isEmpty() || pNum.getText().isEmpty() || pEmail.getText().isEmpty() || pDoc.getText().isEmpty() || pID.getText().isEmpty()) {
					actionTarget.setFill(Color.FIREBRICK);
			        actionTarget.setText("*Please fill in all fields*");
				}
				else {
					
					pfirstName = fName.getText();
					plastName = lName.getText();
					pAdd = add.getText();
					pNumb = pNum.getText();
					peMail = pEmail.getText();
					pDoct = pDoc.getText();
					pId = pID.getText()
;

					//creating a JSON obj to store all input information
					JSONObject jsO = new JSONObject();

					jsO.put ("First Name: ", pfirstName);
					jsO.put ("LastName: ", plastName);
					jsO.put ("Physician: ", pDoct);
					jsO.put ("Phone: ", pNumb);
					jsO.put ("Email: ", peMail);
					jsO.put ("Address: ", pAdd);
					//jsO.put ("Patient ID", );


					System.out.println("Input confirm print: " + pfirstName + " " + plastName + " " + pAdd +" " + pNumb + " " + peMail + " " +pDoct + " " + pId);
					System.out.println("JSON obj: " + jsO);

					//try/catch to write JASON obj to file

					try (FileWriter file = new FileWriter("C:\\development\\SENG300\\"+pfirstName+"_"+plastName+".json")){


					file.write(jsO.toJSONString());
						System.out.println("JSON file name: "+pfirstName+".json");
						System.out.println("Successfully copied JSON to File...");
						System.out.println("\nJSON Object: " +jsO);
					}
					catch(IOException f){
						System.out.println("failed to create JSON object file");
					}



					BorderPane npPane = new BorderPane();
					actor.setText("Registration complete");
					
					adminScreen.getChildren().clear();
					Label done = new Label("Patient Registration completed");
					done.setFont(new Font("Cambria", 32));
					adminScreen.getChildren().addAll(done,reTurn);
					setBpane(npPane,intro, adminScreen);
					setScene(npPane,primaryStage);
					
					
					
					//System.out.println(pfirstName + plastName + pAdd + pNumb + peMail + pDoct);
				}
				

			}
		});
		
		submitD.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {

				if(dfName.getText().isEmpty() || dlName.getText().isEmpty() || dadd.getText().isEmpty() || dNum.getText().isEmpty() || dEmail.getText().isEmpty() || dspec.getText().isEmpty()) {
					actionTarget.setFill(Color.FIREBRICK);
			        actionTarget.setText("*Please fill in all fields*");
				}
				else {
					
					dfirstName = dfName.getText();
					dlastName = dlName.getText();
					dAdd = dadd.getText();
					dNumb = dNum.getText();
					deMail = dEmail.getText();
					docspec = dspec.getText();


					System.out.println("Input confirm print: " + dfirstName + " " + dlastName + " " + dAdd +" " + dNumb + " " + deMail + " " +docspec);
					




					BorderPane npPane = new BorderPane();
					actor.setText("Registration complete");
					
					adminScreen.getChildren().clear();
					Label done = new Label("Doctor registration completed");
					done.setFont(new Font("Cambria", 32));
					adminScreen.getChildren().addAll(done,reTurn);
					setBpane(npPane,intro, adminScreen);
					setScene(npPane,primaryStage);
					

				}
				

			}
		});
		
	}

	//set gridplane inside of VBox
	protected void setadminpatPane(GridPane Pane, Label firstName, Label lastName, Label address, Label phoneNum, Label patDoc, TextField fName, TextField lName, TextField add, TextField pNum, TextField pDoc, Button submit, TextField pEmail, Label eMail, Text actionTarget, Button clear, Button restart, TextField pID, Label patID) {

		
		Pane.setAlignment(Pos.TOP_LEFT);
		Pane.setHgap(10);
		Pane.setVgap(10);
		Pane.setPadding(new Insets(25,25,25,25));
		
		Pane.add(firstName, 16, 10);
		Pane.add(lastName, 16, 12);
		Pane.add(address, 16, 14);
		Pane.add(phoneNum, 16, 16);
		Pane.add(eMail, 16, 18);
		Pane.add(patDoc, 16,20);
		Pane.add(patID, 16, 22);
		
		Pane.add(fName, 18, 10);
		Pane.add(lName, 18, 12);
		Pane.add(add, 18, 14);
		Pane.add(pNum, 18, 16);
		Pane.add(pEmail, 18, 18);
		Pane.add(pDoc, 18, 20);
		Pane.add(pID, 18, 22);
		
		Pane.add(submit, 24, 24);
		Pane.add(actionTarget, 18, 24);
		Pane.add(clear, 24, 10);
		Pane.add(restart, 40, 0);
		
	}
	
	protected void setadmindocPane(GridPane Pane, Button clear, Button restart, Button submit, Text actionTarget, Label firstName, Label lastName, Label specialist, Label address, Label phoneNum, Label docMail, TextField fName, TextField lName, TextField spec, TextField add, TextField dNum, TextField dEmail) {
		Pane.setAlignment(Pos.TOP_LEFT);
		Pane.setHgap(10);
		Pane.setVgap(10);
		Pane.setPadding(new Insets(25,25,25,25));
		
		Pane.add(firstName, 16, 10);
		Pane.add(lastName, 16, 12);
		Pane.add(specialist, 16, 14);
		Pane.add(address, 16, 16);
		Pane.add(phoneNum, 16, 18);
		Pane.add(docMail, 16, 20);
		
		Pane.add(fName, 18, 10);
		Pane.add(lName, 18, 12);
		Pane.add(spec, 18, 14);
		Pane.add(add, 18, 16);
		Pane.add(dNum, 18, 18);
		Pane.add(dEmail, 18, 20);
		
		Pane.add(submit, 24, 24);
		Pane.add(actionTarget, 18, 24);
		Pane.add(clear, 24, 10);
		Pane.add(restart, 40, 0);
		
	}


}
