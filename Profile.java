import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
public class Profile{
    private final File profiles = new File("profiles.txt");


    public String name;
    private String pin;
    private Database profileDatabase;
    private File profile;
    private File list;
    private File folder;
    
    public Profile(String name, String pin) throws Exception{
        
        this.name = name;
        this.pin = pin;
        Encrypter e = new Encrypter(this.name);
        e = new Encrypter(this.pin);

        System.out.println(name + " : " + pin);
        
        BufferedReader myReader = new BufferedReader(new FileReader(profiles)); 
        String containsProfiles = myReader.readLine();
        System.out.println(containsProfiles.contains("Profile: " + name + " = " + pin) + "|" + containsProfiles);
        //need to add a loop to search for multiple profiles
        if(containsProfiles.contains("Profile: " + name + " = " + pin) == true){
            System.out.println("Returning Profile");
            System.out.println(containsProfiles);
        }
        else{
            System.out.println("--New Profile--");
            try {

                folder = new File("Profile-" + name);
                System.out.println("Folder Creation: "+folder.mkdir());
    
                File profile = new File(folder,folder.getPath()+"\\profile" + name + ".txt");
                FileWriter myWriter = new FileWriter(profile);
                myWriter.write(e.encrypt(name+","));
                myWriter.write(e.encrypt(pin+","));
                myWriter.write(e.encrypt("passwords" + name +".txt"));
                list = new File(folder,folder.getPath()+"\\passwords" + name + ".txt");
                myWriter.close();
                myWriter = new FileWriter(list);
                myWriter.write("no data");
                myWriter.close();
                myWriter = new FileWriter(profiles);
                myWriter.write(e.encrypt("Profile: " + name + " = " + pin + "\n"));
                myWriter.close();
                
                System.out.println("Successfully wrote to the files.");
            } catch (IOException e1) {
                System.out.println("An error occurred.");
                e1.printStackTrace();
            }
        }


        myReader.close();

        profileDatabase = new Database();
    }

    private void wr(File f, boolean b){
        f.setReadable(b);
        f.setWritable(b);
    }

    private void readable(boolean b){
        folder.setReadable(b);
        list.setReadable(b);
        profile.setReadable(b);
    }

    private void writable(boolean b){
        folder.setWritable(b);
        list.setWritable(b);
        profile.setWritable(b);
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