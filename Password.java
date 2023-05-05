package v2;
import java.io.Serializable;

import javax.crypto.SecretKey;

public class Password implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String password;
	public SecretKey key;
	public boolean encrypted = false;
	
	public Password(String password) throws Exception {this.password = password; initializeKey();}
	public Password(String password, SecretKey sk) {this.password = password; key = sk;}
	
	private void initializeKey() {Encrypter e = new Encrypter(this);key = e.secretKey;}
	
	public String toString() {return password;}
	
	public Password retPassword() {return this;}
}
