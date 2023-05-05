package v2;

import java.util.UUID;

public class Profile extends Database {
	
	private static final long serialVersionUID = 1L;
	public String username;
	public Password pin;
	public UUID uuid = UUID.randomUUID();
	
	public Profile(String username, Password pin, Database d) {super(d); this.username = username; this.pin = pin;}
	public Profile(Profile p) {username = p.username; pin = p.pin; }
	public Profile() {}
	public Database getDatabase() {return super.returning();}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Username: " + username+"\nPIN: " + pin +"\nUUID: "+uuid);
		
		int count = 1;
		
		for(PProfile p : super.data) {
			builder.append(count + " = " +p+"\n");
			count++;
		}
		
		return builder.toString() + "\n\nEND OF PROFILE DATA";
	}
}
