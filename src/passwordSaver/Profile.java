package passwordSaver;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import javax.crypto.SecretKey;

public class Profile implements Serializable{

	private static final long serialVersionUID = 1L;
	private final File skbin = new File(System.getProperty("user.dir")+"\\SKdata.bin");
	public String name;
    public String pin;
    private Database profileDatabase;
    public boolean newProfile = true;
    public SecretKey pinKey;
    
    public Profile() {profileDatabase = new Database();}
    public Profile(String name, String pin) {this.name = name; this.pin = pin;}
    
    public Profile findProfile(String name, String pin) throws Exception{this.name = name; this.pin = pin;
    	
    	Encrypter e = new Encrypter();
//        System.out.println(name + " : " + pin);
        
        boolean returning = false;
        
        Profile containsProfiles = null;
        try(ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream("Profiledata.bin"))){
        	try{
        		containsProfiles = (Profile)ois1.readObject();
        		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SKdata.bin"))) {
        		    while (true) {
        		        SecretKey sk = (SecretKey) ois.readObject();
        		        if (sk == null) {break;}
        		        if ((containsProfiles.name.equalsIgnoreCase(name) && e.decrypt(containsProfiles.pin, sk).equals(pin))) {
        		            System.out.println("Returning Profile");
        		            System.out.println(containsProfiles);
        		            
        		            returning = true;
        		            break;
        		        }
        		    }
        		} catch (EOFException error) {} catch (IOException | ClassNotFoundException error) {error.printStackTrace();}
        	}catch(Exception error){System.out.println(error.getMessage());}
        }catch(Exception error) {System.out.println("Making Profiledata.bin");}
        if(returning == true){System.out.println("Welcome Back " + name + "!");profileDatabase = containsProfiles.getDatabase(); System.out.println("containsProfiles: " + containsProfiles.getDatabase());}
        else if(returning != true){
            System.out.println("--New Profile--");
            try {
            	profileDatabase = new Database();
                newProfile();
                
                
            } catch (IOException e1) {
                System.out.println("An error occurred.");
                e1.printStackTrace();
            }
        }

        return this;
    }

    private void newProfile() throws Exception{
    			System.out.println("Name: " + name + " | " + pin);
    			Encrypter e = new Encrypter();
    			pin = e.encrypt(pin);
    			Serializer.serialize(e.secretKey);
    			pinKey = e.secretKey;
                Serializer.serialize(this);
                System.out.println("Successfully Serialized and Encrypted.");
    }
    
    public Database getDatabase(){
        return profileDatabase;
    }
    
    public String toString(){
        return name + ":\n"+ profileDatabase;
    }
    
}