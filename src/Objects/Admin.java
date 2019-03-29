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
	private String password = "5f4dcc3b5aa765d61d8327deb882cf99"; // password
	
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
	
	
//	/**
//	 * @param p The users text version password
//	 * @return The users hashed password
//	 */
//	private String setPassword(String p){
//		String password = null;
//
//		try {
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			md.update(p.getBytes());
//			byte[] bytes = md.digest();
//			StringBuilder sb = new StringBuilder();
//			for(int i = 0; i < bytes.length; i++){
//				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
//			}
//			password = sb.toString();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		return password;
//	}
//	
	
	
	
	//Getters
	public String getName() {return name;}
	public Integer getId() {return id;}
	public String getPassword() {return password;}
}
