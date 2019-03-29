package GUI;

import JsonFileUtils.Parser;
import Objects.Patient;
import Objects.Admin;
import Objects.Doctor;

import java.io.File;
import java.util.List;

public class validateAccount {

	public validateAccount(String user, String pass, String filename) {this.validate(user, pass, filename);}
	public validateAccount() {}

	String currentDir = System.getProperty("user.dir");
	File path = new File(currentDir);
	Parser parser = new Parser();
	//File[] jsonFiles = parser.getFiles(path);
	List<Patient> allPatients = parser.parsePatients();
	List<Doctor> allDoctors = parser.parseDoctors();
	List<Admin> allAdmins = parser.parseAdmins();

	public Boolean validate(String user, String pass, String type) {

		boolean authenticate = false;
		if (type.equals("Patient")) {
			for (Patient patient : allPatients) {
				if (patient.getFirstName().equals(user)) {
					if(patient.getPassword().equals(pass)) {
						System.out.println("Authentication Success!");
						authenticate = true;
						break;
					}
				}
			}

		}
		else if(type.equals("Doctor")){
			for (Doctor doctor : allDoctors) {
				if (doctor.getFirstname().equals(user)) {
					if(doctor.getPassword().equals(pass)) {
						System.out.println("Authentication Success!");
						authenticate = true;
						break;
					}
				}
			}
		}
		else if(type.equals("Admin")){
			for (Admin admin : allAdmins) {
				if (admin.getName().equals(user)) {
					if(admin.getPassword().equals(pass)) {
						System.out.println("Authentication Success!");
						authenticate = true;
						break;
					}
				}
			}
		}

//		else if (filename.equals("Doctor_Users.json")) {System.out.println("this is a doctor signing in\n"+ user +" + "+pass); return true;}
		//else {System.out.println("This is an admin signing in"+ user +" + "+ pass); return true;}
		return authenticate;
	}
}
