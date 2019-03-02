package JsonFileUtils;
import java.io.*; 
import java.util.*;  
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import Objects.Patient; 

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
	public List<Patient> parsePatients(File[] jsonFiles) {
		Gson parser = new Gson();
		JsonReader reader;
		List<Patient> allPatients = new ArrayList<>();
		try {
			for (File patFile : jsonFiles) {
				reader = new JsonReader(new FileReader(patFile));
				Patient pat = parser.fromJson(reader, Patient.class);
				allPatients.add(pat);
			}
			return allPatients;
		} catch (FileNotFoundException e) {
			System.out.println("Error parsing JSON file: " + e.getMessage());
			return null;
		}
	}

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
