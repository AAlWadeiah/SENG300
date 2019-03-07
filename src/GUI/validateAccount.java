package GUI;

import java.io.File;

/**
 * Ivans stuff will be in this method or something similar
 */
public class validateAccount {

	public validateAccount(String user, String pass, String filename) {this.validate(user, pass, filename);}
	public validateAccount() {}
	
	public Boolean validate(String user, String pass, String filename) {
		if (filename.equals("Patient_Users.json" )) { System.out.println("This is a patient signing in:\n" + user +" + "+pass);return true;}
		else if (filename.equals("Doctor_Users.json")) {System.out.println("this is a doctor signing in\n"+ user +" + "+pass); return true;}
		else {System.out.println("This is an admin signing in"+ user +" + "+ pass); return true;}
}}
