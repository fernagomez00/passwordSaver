import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
public class Profile{
    public String name;
    private String pin;
    private Database profileDatabase;
    private File profile;
    private File list;
    private File folder;
    
    public Profile(String name, String pin){
        System.out.println("--New Profile--");
        this.name = name;
        this.pin = pin;
        
        try {
            folder = new File("Profile-" + name);
            System.out.println("Folder Creation: "+folder.mkdir());

            File profile = new File(folder,"profile" + name + ".txt");
            FileWriter myWriter = new FileWriter(profile);
            myWriter.write(name + ",");
            myWriter.write(pin + ",");
            myWriter.write("passwords" + name +".txt");
            list = new File(folder,"passwords" + name + ".txt");
            myWriter = new FileWriter(list);
            myWriter.write("no data");
            myWriter.close();
            System.out.println("Successfully wrote to the files.");
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