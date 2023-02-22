package passwordSaver;
import java.io.Serializable;

import javax.crypto.SecretKey;
public class Password implements Serializable{
    
    private static final long serialVersionUID = 1L;
	public String password;
    private SecretKey key;
    
    public Password(String password){
        this.password = password;
    }
    
    public void setKey(SecretKey key){
        this.key = key;
    }
    
    public SecretKey getKey(){
        return key;
    }
    
    public String toString(){
        return password;   
    }
}
