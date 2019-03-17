package GUI;

import JsonFileUtils.Parser;
import Objects.Patient;

import java.io.File;
import java.util.List;

public class validateAccount {

	public validateAccount(String user, String pass, String filename) {this.validate(user, pass, filename);}
	public validateAccount() {}

	String currentDir = System.getProperty("user.dir");
	File path = new File(currentDir);
	Parser parser = new Parser();
	File[] jsonFiles = parser.getFiles(path);
	List<Patient> allPatients = parser.parsePatients();

	public Boolean validate(String user, String pass, String filename) {

		boolean authenticate = false;
		if (filename.equals(filename)) {
			for (Patient patient : allPatients) {
				if (patient.getFirstName().equals(user)) {
					if(patient.getPassword().equals(pass)) {
						System.out.println("Authentication Success!");
						authenticate = true;
						break;
					}
				}
			}
			return authenticate;
		}
//		else if (filename.equals("Doctor_Users.json")) {System.out.println("this is a doctor signing in\n"+ user +" + "+pass); return true;}
		else {System.out.println("This is an admin signing in"+ user +" + "+ pass); return true;}
}}
