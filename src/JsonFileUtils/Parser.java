package JsonFileUtils;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import Objects.Patient;
import Objects.Admin;
import Objects.Doctor;

public class Parser {
	// Driver code
	/**
	public static void main(String[] args) {
		String currentDir = System.getProperty("user.dir");
	    File path = new File(currentDir);
	    
	    Parser parser = new Parser();
	    
	    File[] jsonFiles = parser.getFiles(path);
	    List<Patient> allPatients = parser.parsePatients(jsonFiles);
	    for (Patient patient : allPatients) {
			System.out.println(patient.getFirstName() + " " + patient.getLastName());
		}
	}
	**/
	
	/**
	 * Deserializes the given patient files and creates a list of Patient objects from the files.
	 * @param jsonFiles The Patient JSON files
	 * @return the list of deserialized Patient objects
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
