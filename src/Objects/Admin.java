package Objects;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** This method is used to handle administrators in the system
 * 
 *
 */
public class Admin {

	private String name;
	private Integer id;
	private String password = "5f4dcc3b5aa765d61d8327deb882cf99"; // "password"
	
	//Constructors
	public Admin (Integer id, String name) { 
		setId(id);
		setName(name);
	}
	public Admin() {
		this(null, null);
	}
	
	//Setters
	public void setName(String name1) {this.name = name1;}
	public void setId(Integer id) {this.id = id;}
		
	//Getters
	public String getName() {return name;}
	public Integer getId() {return id;}
	public String getPassword() {return password;}
}
