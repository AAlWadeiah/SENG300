package JsonFileUtils;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import Objects.Patient;
import Objects.Admin;
import Objects.Doctor;

/**
 *  The main purpose of this class it to deserialize the given patient, doctor and admin files and create a list of Patient
 *   doctor and admin objects from the files.
 *
 */

public class Parser {
	
	/** This method is used to parse the patient JSON files
	 * 
	 * @return returns a list containing all the patient objects which were parsed by the parser
	 */
	public List<Patient> parsePatients() {
		Gson allPatFile = new GsonBuilder().setPrettyPrinting().create();
		List<Patient> allThePatients = null;
		Type patientType = new TypeToken<List<Patient>>(){}.getType();


		try {
			FileReader fReader = new FileReader("allThePatients.json");
			allThePatients = allPatFile.fromJson(fReader, patientType);
			fReader.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error, file not found in parser class");
		}
		catch (IOException e){
			System.out.println("Error! Cannot close file in Parser class - Big trouble...Aborting");
		}

		return allThePatients;

	}
	
	/** This method is used to parse the doctor JSON files
	 * 
	 * @return returns a list containing all the doctor objects which were parsed by the parser
	 */
	public List<Doctor> parseDoctors() {
		Gson allDocFile = new GsonBuilder().setPrettyPrinting().create();
		List<Doctor> allTheDoctors = null;
		Type doctorType = new TypeToken<List<Doctor>>(){}.getType();


		try {
			FileReader fReader = new FileReader("allTheDoctors.json");
			allTheDoctors = allDocFile.fromJson(fReader, doctorType);
			fReader.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error, file not found in parser class");
		}
		catch (IOException e){
			System.out.println("Error! Cannot close file in Parser class - Big trouble...Aborting");
		}

		return allTheDoctors;

	}

	/** This method is used to parse the admin JSON files
	 * 
	 * @return returns a list containing all the admin objects which were parsed by the parser
	 */
	public List<Admin> parseAdmins() {
		Gson allAdminFile = new GsonBuilder().setPrettyPrinting().create();
		List<Admin> allTheAdmins = null;
		Type adminType = new TypeToken<List<Admin>>(){}.getType();


		try {
			FileReader fReader = new FileReader("allTheAdmins.json");
			allTheAdmins = allAdminFile.fromJson(fReader, adminType);
			fReader.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error, file not found in parser class");
		}
		catch (IOException e){
			System.out.println("Error! Cannot close file in Parser class - Big trouble...Aborting");
		}

		return allTheAdmins;

	}

	/**
	 * Grabs all JSON files from the specified directory.
	 * @param path The path to the directory to search for JSON files in
	 * @return an array containing all JSON files from the directory
	 */
	public File[] getFiles(File path) {
		File[] jsonFiles = path.listFiles(new FilenameFilter() {
	        @Override
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().endsWith(".json");
	        }
	    });
		
		return jsonFiles;
	}
}
