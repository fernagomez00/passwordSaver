public class PProfile{
    
    public String website;
    public String username;
    
    public PProfile(String website, String username){
        this.website = website;
        this.username = username;
    }
    
    public String toString(){
        return "Website: " + website + " -- Username: " + username;
    }
    
}