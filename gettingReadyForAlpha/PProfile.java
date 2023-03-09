package gettingReadyForAlpha;

import java.io.Serializable;

import javax.crypto.SecretKey;

public class PProfile extends Password implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String name_url;
	public String username;

	public PProfile(String username, String name, String password, SecretKey sk) {
		super(password, sk);
		name_url = name;
		this.username = username; 
	}
	
	public String toString() {
		return "URL: "+name_url+ " | Username: " + username + " | Password: "+super.password;
	}
	
}
