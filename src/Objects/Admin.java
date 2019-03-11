package Objects;


/** This method is used to handle administrators in the system
 * 
 *
 */
public class Admin {

	private String name;
	private Integer id;
	
	//Constructors
	public Admin (Integer id1, String name1) { 
		setId(id1);
		setName(name1);
	}
	public Admin() {}
	
	//Setters
	public void setName(String name1) {this.name = name1;}
	public void setId(Integer id) {this.id = id;}
	
	//Getters
	public String getName() {return name;}
	public Integer getId() {return id;}
}
