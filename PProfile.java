package v2;

import java.io.Serializable;

import javax.crypto.SecretKey;

public class PProfile extends Password implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String name_url;
	public String username;
	
	/**
	 * 
	 * @param username String
	 * @param name String
	 * @param password Password
	 * @param sk SecretKey
	 */
	public PProfile(String username, String name, String password, SecretKey sk) {super(password, sk);name_url = name;this.username = username;}
	
	public String toString() {String passwordStrength = "";try {passwordStrength = Encrypter.decrypt(super.retPassword());} catch (Exception e) {e.printStackTrace();} return "Name/URL/Software/Game: "+name_url+ " | Username: " + username + " | Password (Encrypted): "+super.password + " | Password Strength: " + PasswordFeatures.getPasswordStrength(passwordStrength) + "/100";}
	
	
}
