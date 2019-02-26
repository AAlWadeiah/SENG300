package Objects;


/** This method is used to handle administrators in the system
 * 
 * @author ali
 *
 */
public class Admin {

	private String name;
	
	//Constructors
	public Admin (String name1) { setName(name1);}
	public Admin() {}
	
	//Setter
	public void setName(String name1) {this.name = name1;}
	
	//Getter
	public String getName() {return name;}
}
