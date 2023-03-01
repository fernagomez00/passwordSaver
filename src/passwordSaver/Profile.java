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
    public Database profileDatabase;
    public boolean newProfile = true;
    public SecretKey pinKey;
    
    public Profile() {}
    public Profile(String name, String pin) {this.name = name; this.pin = pin;}
    public Profile(String name, String pin, Database database) {this.name = name; this.pin = pin; profileDatabase = database;}
    
//    public Profile findProfile(String name, String pin) throws Exception {
//        this.name = name;
//        this.pin = pin;
//
//        Encrypter e = new Encrypter();
//
//        boolean returning = false;
//
//        try (ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream("Profiledata.bin"))) {
//        	System.out.println("test: " + ois1);
//            while (true) {
//                try {
//                    Profile containsProfiles = (Profile) ois1.readObject();
//                    System.out.println("test: " + containsProfiles);
//                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SKdata.bin"))) {
//                        while (true) {
//                        	
//                            SecretKey sk = (SecretKey) ois.readObject();
//                            System.out.println("testing!" + (containsProfiles.name.equalsIgnoreCase(name) && e.decrypt(containsProfiles.pin, sk).equals(pin)));
//                            if (sk == null) {
//                                break;
//                            }
//                            if ((containsProfiles.name.equalsIgnoreCase(name) && e.decrypt(containsProfiles.pin, sk).equals(pin))) {
//                                System.out.println("Returning Profile");
//                                System.out.println(containsProfiles);
//
//                                returning = true;
//                                profileDatabase = containsProfiles.getDatabase();
//                                break;
//                            }
//                            System.out.println(returning);
//                        }
//                    } catch (EOFException error) {
//                    	System.out.println("error1");
//                        // ignore EOFException, since it just means we've reached the end of the file
//                    } catch (IOException | ClassNotFoundException error) {
//                    	
//                        error.printStackTrace();
//                    }
//                } catch (EOFException error) {
//                    // ignore EOFException, since it just means we've reached the end of the file
//                	System.out.println("error2");
//                    break;
//                }
//            }
//        } catch (IOException error) {
//            System.out.println("An error occurred while reading profile data.");
//            error.printStackTrace();
//        }
//
//        if (returning == true) {
//            System.out.println("Welcome Back " + name + "!");
//            System.out.println("containsProfiles: " + profileDatabase);
//        } else {
//            System.out.println("--New Profile--");
//            try {
//                profileDatabase = new Database();
//                newProfile();
//            } catch (IOException error) {
//                System.out.println("An error occurred.");
//                error.printStackTrace();
//            }
//        }
//        System.out.println("test: end");
//        return this;
//    }
//
//    private void newProfile() throws Exception{
//    			System.out.println("Name: " + name + " | " + pin);
//    			Encrypter e = new Encrypter();
//    			pin = e.encrypt(pin);
//    			Serializer.serialize(e.secretKey);
//    			pinKey = e.secretKey;
//                Serializer.serialize(this);
//                System.out.println("Successfully Serialized and Encrypted.");
//    }
    
    public Database getDatabase(){
        return profileDatabase;
    }
    
    public String toString(){
        return name + ":\n"+ profileDatabase;
    }
    
}