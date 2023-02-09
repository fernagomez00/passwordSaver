import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
public class Profile{
    public String name;
    private String pin;
    private Database profileDatabase;
    private File profile = new File("profile.txt");
    private File list;
    
    public Profile(String name, String pin){
        System.out.println("--New Profile--");
        this.name = name;
        this.pin = pin;
        
        try {
            File profile = new File("profile" + name + ".txt");
            FileWriter myWriter = new FileWriter("profile" + name + ".txt");
            myWriter.write(name + ",");
            myWriter.write(pin);
            
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        profileDatabase = new Database();
    }
    
    public Profile(File profile, File list){
        this.profile = profile;
        this.list = list;
    }
    
    private Database getDatabase(){
        return profileDatabase;
    }
    
    private String getPIN(){
        return pin;
    }
    
    private String getName(){
        return name;
    }
    
    public String toString(){
        return name;
    }
    
}