package GUI;

import JsonFileUtils.Parser;
import Objects.Patient;
import Objects.Admin;
import Objects.Doctor;

import java.io.File;
import java.util.List;

/**
 * 
 * @author ali
 *
 */
public class validateAccount {

	/** Constructor which immediately goes to validation method
	 * 
	 * @param user userID to validate
	 * @param pass password to validate
	 * @param filename filename of entity we will be validating
	 */
	public validateAccount(Integer user, String pass, String filename) {this.validate(user, pass, filename);}
	public validateAccount() {}

	String currentDir = System.getProperty("user.dir");			//current directory
	File path = new File(currentDir);							//File path
	Parser parser = new Parser();
	List<Patient> allPatients = parser.parsePatients();			//List of all patients from parser class
	List<Doctor> allDoctors = parser.parseDoctors();			//List of all doctors from parser class
	List<Admin> allAdmins = parser.parseAdmins();				//List of all admins from parser class

	/**
	 * This is the method whic hdoes the actual validation by comparing the given values to the 
	 * values found in file
	 * 
	 * @param user, ID to validate
	 * @param pass, string password to validate
	 * @param type, are we doing patients, admins, or doctors
	 * @return boolean, true if it is validated successfully, or false otherwise
	 */
	public Boolean validate(Integer user, String pass, String type) {

		boolean authenticate = false;
		if (type.equals("Patient")) {
			for (Patient patient : allPatients) {
				if (patient.getId() == user) {
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
				if (doctor.getId() == user) {
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
				if (admin.getId() == user) {
					if(admin.getPassword().equals(pass)) {
						System.out.println("Authentication Success!");
						authenticate = true;
						break;
					}
				}
			}
		}
		return authenticate;
	}
}
