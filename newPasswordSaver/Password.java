package newPasswordSaver;
import java.io.Serializable;

import javax.crypto.SecretKey;

public class Password implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String password;
	//this key needs to always be initialized
	public SecretKey key;
	public boolean encrypted = false;
	
	public Password(String password) throws Exception {this.password = password; initializeKey();}
	public Password(String password, SecretKey sk) {this.password = password; key = sk;}
	
	private void initializeKey() {
		Encrypter e = new Encrypter(this);
		key = e.secretKey;
	}
	
	private void setKey(SecretKey sk) {key = sk;}
	
	public String toString() {
		return password + "| key: " + key;
	}
}
