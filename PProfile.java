public class PProfile extends Password{
    
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