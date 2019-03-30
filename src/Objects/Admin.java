package Objects;

/** This method is used to handle administrators in the system
 * 
 *
 */
public class Admin {

	private Integer id;
	private String password = "5f4dcc3b5aa765d61d8327deb882cf99"; // "password"
	
	//Constructors
	public Admin (Integer id) { 
		setId(id);
	}
	public Admin() {
		this(null);
	}
	
	//Setters
	public void setId(Integer id) {this.id = id;}
		
	//Getters
	public Integer getId() {return id;}
	public String getPassword() {return password;}
}
