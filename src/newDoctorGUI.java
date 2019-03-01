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

public class newDoctorGUI extends adminGUI{
	
	public void startDoctor(Stage doctorStage, HBox intro, VBox adminScreen) {

		//Text Field
		TextField fName = new TextField();
		TextField lName = new TextField();
		TextField add = new TextField();
		TextField Num = new TextField();
		TextField Email = new TextField();
		TextField Spec = new TextField();

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
		setdoctorPane(newdoctorGrid, submit, clear, reTurn, fName, lName, add, Num, Email, Spec, actionTarget);
		
		//populate box
		newDoctor.getChildren().add(newdoctorGrid);
		
		
		setBorderpane(newdoctorBorder, intro, newDoctor);
		setScene(newdoctorBorder,doctorStage);
		
		clear.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				fName.clear();
				lName.clear();
				add.clear();
				Num.clear();
				Email.clear();
				Spec.clear();
			}
		});
		
		reTurn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				startAdmin(doctorStage);
			}
		});
		
		submit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {

				if(fName.getText().isEmpty() || lName.getText().isEmpty() || add.getText().isEmpty() || Num.getText().isEmpty() || Email.getText().isEmpty() || Spec.getText().isEmpty()) {
					actionTarget.setFill(Color.FIREBRICK);
					actionTarget.setText("*Please fill in all fields*");
				}
				else {

					dfirstName = fName.getText();
					dlastName = lName.getText();
					dAdd = add.getText();
					dNumb = Num.getText();
					dEmail = Email.getText();
					dDoc = Spec.getText();
					
					System.out.println("Input confirm print: " + dfirstName + " " + dlastName + " " + dAdd + " " + dNumb + " " + dEmail + " " +dDoc);

					
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

	private void setdoctorPane(GridPane Pane, Button submit, Button clear, Button reTurn, TextField fName,TextField lName, TextField add, TextField Num, TextField Email, TextField spec, Text actionTarget) {
		
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

		//Text Prompts
		fName.setPromptText("Enter doctor's first name");
		lName.setPromptText("Enter doctor's last name ");
		add.setPromptText("Enter doctor's address");
		Num.setPromptText("Enter doctor's phone #");
		Email.setPromptText("Enter doctor's email ");
		spec.setPromptText("Enter doctor's department");
		
	
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


		Pane.add(fName, xs+2, ys);
		Pane.add(lName, xs+2, ys+2);
		Pane.add(add, xs+2, ys+4);
		Pane.add(Num, xs+2, ys+6);
		Pane.add(Email, xs+2, ys+8);
		Pane.add(spec, xs+2, ys+10);

		Pane.add(submit, xs+8, ys+14);
		Pane.add(actionTarget, xs+2, ys+14);
		Pane.add(clear, xs+8, ys);
		Pane.add(reTurn, end, start);
		}
		
	}

