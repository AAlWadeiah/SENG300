package GUI;

import JsonFileUtils.Parser;
import Objects.Patient;

import java.io.File;
import java.util.List;

/**
 * Ivans stuff will be in this method or something similar
 */
public class validateAccount {

	public validateAccount(String user, String pass, String filename) {this.validate(user, pass, filename);}
	public validateAccount() {}



	String currentDir = System.getProperty("user.dir");
	File path = new File(currentDir);
	Parser parser = new Parser();
	File[] jsonFiles = parser.getFiles(path);
	List<Patient> allPatients = parser.parsePatients(jsonFiles);




	public Boolean validate(String user, String pass, String filename) {

		if (filename.equals("Patient_Users.json" )) {
			for (Patient patient : allPatients) {
				//System.out.println(patient.getFirstName() + " " + patient.getLastName()+" " + patient.getAddress() +" " + patient.getDoctor() + " " + patient.getEmail() + " " +patient.getNumber() + " " + patient.getId());
				if (patient.getFirstName().equals(user)) {

					System.out.println("Found User!");
				}
				else{

					System.out.println("User Doesn't Exits!");
				}
			}

			System.out.println("This is a patient signing in:\n" + user +" + "+pass);return true;
		}
		else if (filename.equals("Doctor_Users.json")) {System.out.println("this is a doctor signing in\n"+ user +" + "+pass); return true;}
		else {System.out.println("This is an admin signing in"+ user +" + "+ pass); return true;}
}}
