package gettingReadyForAlpha;

import java.io.Serializable;

public class Profile extends Database implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String username;
	public Password pin;
	
	public Profile(String username, Password pin, Database d) {super(d); this.username = username; this.pin = pin;}
	public Profile(Profile p) {username = p.username; pin = p.pin;}
	public Profile() {}
	public Database getDatabase() {return super.returning();}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Username: " + username+" | PIN: " + pin +"\n");
		
		for(PProfile p : super.data) {
			builder.append(p+"\n");
		}
		
		return builder.toString() + "\n\nEND OF PROFILE DATA";
	}
}
