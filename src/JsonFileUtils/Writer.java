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

public class Writer {
	
	/**
	 * Writes a Patient object to a JSON file.
	 * @param pat The Patient object to write to file
	 * @return true if Patient was written to file, false otherwise
	 *
    */
	public boolean writeObjectToFile(Patient pat) {
		//Note: source for how to append code was:
		//https://stackoverflow.com/questions/54574576/append-json-object-in-existing-json-file-using-java
		//read old file, rewrite to new file with new information to be appended
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
}
