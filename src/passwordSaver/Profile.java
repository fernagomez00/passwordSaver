package passwordSaver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.io.FileWriter;
public class Profile{
	private final String dir = System.getProperty("user.dir");
	private final File profiles = new File(dir + "/src/passwordSaver/profiles.txt");
	private final File keys = new File(dir + "/src/passwordSaver/keys.txt");


    public String name;
    public String pin;
    private Database profileDatabase;
    private File profile;
    private File list;
    private File folder;
    
    public Profile(String name, String pin) throws Exception{
        Encrypter e = new Encrypter();
        this.name = e.encrypt(name);
        Serializer.serialize(e.secretKey);
        this.pin = e.encrypt(pin);
        Serializer.serialize(e.secretKey);
        System.out.println(name + " : " + pin);
        //need to implement serializer to find returning profiles
        boolean returning = false;
        Profile containsProfiles;
        while(true){
            if(returning == true){break;}
            containsProfiles = Serializer.readProfileDatabin();
            if(containsProfiles == null){break;}
            while(true){
                try{
                    SecretKey sk = Serializer.readSKDatabin();  
                    if(sk == null){break;}
                    if((this.profile.equals(containsProfiles)) == true){
                        System.out.println("Returning Profile");
                        System.out.println(containsProfiles);
                        returning = true;
                        break;
                    }
                }catch(Exception error){
                    System.out.println(error.getMessage());
                }
                
            }    
        }
        //need to add a loop to search for multiple profiles
        if(returning == true){
            System.out.println("Welcome Back " + name + "!");
        }
        else{
            System.out.println("--New Profile--");
            try {
//            	myReader = new BufferedReader(new FileReader(profiles));
//            	while(myReader.readLine() != null) {}
                newProfile();
                
            } catch (IOException e1) {
                System.out.println("An error occurred.");
                e1.printStackTrace();
            }
        }


        // myReader.close();

        profileDatabase = new Database();
    }

    private void newProfile() throws Exception{
        		// folder = new File(dir+"/Profile-" + name);
                // System.out.println("Folder Creation: "+folder.mkdir());
                // String key1 = "";
                // File profile = new File(folder,"profile" + name + ".txt");
                // FileWriter myWriter = new FileWriter("/"+profile);
                // myWriter.append(e.encrypt(name+","));
                // key1 += e.key().getEncoded() + ",";
                // myWriter.append(e.encrypt(pin+","));
                // key1 += e.key().getEncoded() + ",";
                // myWriter.append(e.encrypt("passwords" + name +".txt"));
                // key1 += e.key().getEncoded() + ",";
                // list = new File(folder,"passwords" + name + ".txt");
                // myWriter.close();
                // myWriter = new FileWriter(list);
                // myWriter.append("no data");
                // myWriter.close();
                // myWriter = new FileWriter(profiles);
                // myWriter.append(e.encrypt("Profile: " + name + " = " + pin)+"\n");
                // key1 += e.key().getEncoded();
                // myWriter.close();
                // myWriter = new FileWriter(this.keys);
                // myWriter.append(key1+"\n");
                // myWriter.close();
                Serializer.serialize(this);
                

                
                System.out.println("Successfully Serialized.");
    }

    //need to use this once this classes above functions are completed for more security as
    private void readable(boolean b){
        folder.setReadable(b);
        list.setReadable(b);
        profile.setReadable(b);
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