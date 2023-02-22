package passwordSaver;

import java.io.Serializable;

public class PProfile extends Password implements Serializable{
    
    private static final long serialVersionUID = 1L;
	public String website;
    public String username;

    public PProfile(String pw){
        super(pw);
    }
    
    public PProfile(String website, String username, String pw) {
        super(pw);
        this.website = website;
        this.username = username;
        
    }
    
    public String toString(){
        return "Website: " + website + " -- Username: " + username + " -->" + super.password;
    }
    
}