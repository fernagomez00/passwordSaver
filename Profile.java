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
        
        
        String containsProfiles = "";
        while(myReader.readLine()!=null) {
        	
        	
        	BufferedReader myReader2 = new BufferedReader(new FileReader(keys));
            String[] keys = myReader2.readLine().split(",");
            
            for(String a : keys){
                
                try{
                	String[] bytes1 = new String[a.length()];
                	byte[] bytes = new byte[a.length()];
                	int count = 0;
                	
                	for(Character c : a.toCharArray()) {
                		System.out.print(c.toString());
                		bytes1[count] = c.toString();
                		count++;
                	}
                	count = 0;
                	for(String a1 : bytes1) {
                		
           
                		count++;
                	}
                	
                    // decode the base64 encoded string
                    // rebuild key using SecretKeySpec
                    SecretKey originalKey = new SecretKeySpec(bytes, "AES"); 
                    System.out.println("Original key:"+originalKey +"\nDecrypted: " + e.decrypt(containsProfiles, originalKey));
                    containsProfiles = e.decrypt(containsProfiles, originalKey);
                    
                }catch(Exception t){
                    System.err.println("ERROR IN CHECK FOR RETURNING PROFILES: "+t.getMessage() + " | " + t.getCause());
                }
                
            }
            
        }
        myReader.close();
        System.out.println(containsProfiles.contains("Profile: " + name + " = " + pin) + "|" + containsProfiles);
        //need to add a loop to search for multiple profiles
        if(containsProfiles.contains("Profile: " + name + " = " + pin) == true){
            System.out.println("Returning Profile");
            System.out.println(containsProfiles);
        }
        else{
            System.out.println("--New Profile--");
            try {
//            	myReader = new BufferedReader(new FileReader(profiles));
//            	while(myReader.readLine() != null) {}
                newProfile(e);
                
            } catch (IOException e1) {
                System.out.println("An error occurred.");
                e1.printStackTrace();
            }
        }


        myReader.close();

        profileDatabase = new Database();
    }

    private void newProfile(Encrypter e) throws Exception{
        		folder = new File(dir+"/Profile-" + name);
                System.out.println("Folder Creation: "+folder.mkdir());
                String key1 = "";
                File profile = new File(folder,"profile" + name + ".txt");
                FileWriter myWriter = new FileWriter("/"+profile);
                myWriter.append(e.encrypt(name+","));
                key1 += e.key().getEncoded() + ",";
                myWriter.append(e.encrypt(pin+","));
                key1 += e.key().getEncoded() + ",";
                myWriter.append(e.encrypt("passwords" + name +".txt"));
                key1 += e.key().getEncoded() + ",";
                list = new File(folder,"passwords" + name + ".txt");
                myWriter.close();
                myWriter = new FileWriter(list);
                myWriter.append("no data");
                myWriter.close();
                myWriter = new FileWriter(profiles);
                myWriter.append(e.encrypt("Profile: " + name + " = " + pin)+"\n");
                key1 += e.key().getEncoded();
                myWriter.close();
                myWriter = new FileWriter(this.keys);
                myWriter.append(key1+"\n");
                myWriter.close();
                
                System.out.println("Successfully wrote to the files.");
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