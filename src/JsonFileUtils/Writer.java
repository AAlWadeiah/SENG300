package JsonFileUtils;

import java.io.*; 
import com.google.gson.Gson;

import Objects.Patient;

public class Writer {
	
	/**
	 * Writes a Patient object to a JSON file.
	 * @param pat The Patient object to write to file
	 * @return true if Patient was written to file, false otherwise
	 */
	public boolean writeObjectToFile(Patient pat) {
		String jsonString = new Gson().toJson(pat);
		try (FileWriter file = new FileWriter(pat.getFirstName()+"_"+pat.getLastName()+".json")){
			file.write(jsonString);
		}
		catch(IOException f){
			System.out.println("failed to create JSON object file");
			return false;
		}
		return true;
	}
}
