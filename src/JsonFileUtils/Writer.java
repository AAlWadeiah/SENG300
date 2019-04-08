package JsonFileUtils;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import Objects.Patient;
import Objects.Doctor;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * This class is used to store a Patient and Doctor object in a JSON file.
 *
 */

public class Writer {
	
	/** This is the main class for writing patients to file
	 * 
	 * @param pat The Patient object to write to file
	 * @return true if Patient was written to file, false otherwise
	 *
    */
	public boolean writeObjectToFile(Patient pat) {
		Gson allPatFile = new GsonBuilder().setPrettyPrinting().create();
		Type patientType = new TypeToken<List<Patient>>(){}.getType();

		//declared addNewPatient
		List<Patient> addNewPatient;


		try {
			FileReader fReader = new FileReader("allThePatients.json");
			addNewPatient = allPatFile.fromJson(fReader, patientType);
			fReader.close();
		}
		catch(FileNotFoundException e){
			addNewPatient = null;

		}
		catch (IOException e){
			System.out.println("Error! Writer Class cannot close allThePatients file - Big trouble...Aborting");
			return false;
		}

		//if file is empty create a new list
		if (null == addNewPatient){
			addNewPatient = new ArrayList<>();
		}
		//to append, rewrite to new file
		addNewPatient.add(pat);
		String newJsonString = allPatFile.toJson(addNewPatient);


		try (FileWriter file = new FileWriter("allThePatients.json")){
			file.write(newJsonString);
		}
		catch(IOException f){
			System.out.println("Error! Writer Class failed to create Patient JSON object file");
			return false;
		}
		return true;
	}
	/** Overloaded Method for writing doctors to file
	 * 
	 * @param doc doctor to be written to file
	 * @return True/False success/Failure
	 */
	public boolean writeObjectToFile(Doctor doc){


		//Note: source for how to append code was:
		//https://stackoverflow.com/questions/54574576/append-json-object-in-existing-json-file-using-java
		//read old file, rewrite to new file with new information to be appended
		Gson allDocFile = new GsonBuilder().setPrettyPrinting().create();
		Type doctorType = new TypeToken<List<Doctor>>(){}.getType();

		//declared addNewPatient
		List<Doctor> addNewDoctor;


		try {
			FileReader fReader = new FileReader("allTheDoctors.json");
			addNewDoctor = allDocFile.fromJson(fReader, doctorType);
			fReader.close();
		}
		catch(FileNotFoundException e){
			addNewDoctor = null;

		}
		catch (IOException e){
			System.out.println("Error! Writer Class cannot close allTheDoctors file - Big trouble...Aborting");
			return false;
		}

		//if file is empty create a new list
		if (null == addNewDoctor){
			addNewDoctor = new ArrayList<>();
		}
		//to append, rewrite to new file
		addNewDoctor.add(doc);
		String newJsonString = allDocFile.toJson(addNewDoctor);


		try (FileWriter file = new FileWriter("allTheDoctors.json")){
			file.write(newJsonString);
		}
		catch(IOException f){
			System.out.println("Error! Writer class failed to create Doctor JSON object file");
			return false;
		}
		return true;


	}
	
	/** This method is used to edit the doctor object info on the JSON file
	 * 
	 * @param doc Doctor object who would like their on file records to be editted
	 * @param index index to be changed
	 * @return True/False Success/Failure
	 */
	public boolean editObjectToFile(Doctor doc, int index) {
		Gson allDocFile = new GsonBuilder().setPrettyPrinting().create();
		Type doctorType = new TypeToken<List<Doctor>>(){}.getType();

		//declared addNewPatient
		List<Doctor> addNewDoctor;


		try {
			FileReader fReader = new FileReader("allTheDoctors.json");
			addNewDoctor = allDocFile.fromJson(fReader, doctorType);
			fReader.close();
		}
		catch(FileNotFoundException e){
			addNewDoctor = null;

		}
		catch (IOException e){
			System.out.println("Error! Writer Class cannot close allTheDoctors file - Big trouble...Aborting");
			return false;
		}

		//if file is empty create a new list
		if (null == addNewDoctor){
			addNewDoctor = new ArrayList<>();
		}
		//to append, rewrite to new file
		addNewDoctor.remove(index);
		addNewDoctor.add(index, doc);

		String newJsonString = allDocFile.toJson(addNewDoctor);



		try (FileWriter file = new FileWriter("allTheDoctors.json")){
			file.write(newJsonString);
		}
		catch(IOException f){
			System.out.println("Error! Writer class failed to create Doctor JSON object file");
			return false;
		}
		return true;
		
	}
	
	
	/** This method is used to edit the doctor object info on the JSON file
	 * 
	 * @param doc Doctor object who would like their on file records to be editted
	 * @param index index to be changed
	 * @return True/False Success/Failure
	 */
	public boolean editObjectToFile(Patient pat, int index) {
		Gson allPatFile = new GsonBuilder().setPrettyPrinting().create();
		Type patientType = new TypeToken<List<Patient>>(){}.getType();

		//declared addNewPatient
		List<Patient> addNewPatient;


		try {
			FileReader fReader = new FileReader("allThePatients.json");
			addNewPatient = allPatFile.fromJson(fReader, patientType);
			fReader.close();
		}
		catch(FileNotFoundException e){
			addNewPatient = null;

		}
		catch (IOException e){
			System.out.println("Error! Writer Class cannot close allThePatients file - Big trouble...Aborting");
			return false;
		}

		//if file is empty create a new list
		if (null == addNewPatient){
			addNewPatient = new ArrayList<>();
		}
		//to append, rewrite to new file

		int i = 0;		
		for(Patient patient : addNewPatient) {
			if((Integer.valueOf(patient.getId())).equals(index)) {
				break;
				//patient located
			}
			i++;
		}
		addNewPatient.remove(i);
		addNewPatient.add(i, pat);

		String newJsonString = allPatFile.toJson(addNewPatient);



		try (FileWriter file = new FileWriter("allThePatients.json")){
			file.write(newJsonString);
		}
		catch(IOException f){
			System.out.println("Error! Writer class failed to create Patient JSON object file");
			return false;
		}
		return true;
		
	}
}
