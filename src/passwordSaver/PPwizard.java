package passwordSaver;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.crypto.SecretKey;
//test serializer

//want this class to find profiles and make new ones
public class PPwizard {
	
	public static Profile profile;
	
	public static Profile findProfile(String name, String pin) throws Exception {

        Encrypter e = new Encrypter();

        boolean returning = false;
        Profile containsProfiles;

        try (ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir")+"\\Profiledata.bin"))) {
        	System.out.println("test ois1: " + ois1);
            while (true) {
                try {
                    containsProfiles = (Profile) ois1.readObject();
                    System.out.println("test containsProfiles: " + containsProfiles);
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SKdata.bin"))) {
                        while (true) {
                        	
                            SecretKey sk = (SecretKey) ois.readObject();
                            System.out.println("SK test: " + sk);
                            System.out.println("testing!" + (containsProfiles.name.equalsIgnoreCase(name) && e.decrypt(containsProfiles.pin, sk).equals(pin)));
                            if (sk == null) {
                                break;
                            }
                            if ((containsProfiles.name.equalsIgnoreCase(name) && e.decrypt(containsProfiles.pin, sk).equals(pin))) {
                                System.out.println("Returning Profile");
                                System.out.println(containsProfiles);

                                returning = true;
                                profile.profileDatabase = containsProfiles.getDatabase();
                                break;
                            }
                            System.out.println(returning);
                        }
                    } catch (EOFException error) {
                    	System.out.println("error1");
                        // ignore EOFException, since it just means we've reached the end of the file
                    } catch (IOException | ClassNotFoundException error) {
                    	System.out.println(error.getMessage());
                        error.printStackTrace();
                    }
                } catch (EOFException error) {
                    // ignore EOFException, since it just means we've reached the end of the file
                	System.out.println("error2");
                    break;
                }
            }
        } catch (IOException error) {
            System.out.println("An error occurred while reading profile data.");
            error.printStackTrace();
        }

        if (returning == true) {
            System.out.println("Welcome Back " + name + "!");
            System.out.println("containsProfiles: " + profile.profileDatabase);
        } else {
            System.out.println("--New Profile--");
            try {
            	
                newProfile(name, pin);
            } catch (IOException error) {
                System.out.println("An error occurred.");
                error.printStackTrace();
            }
        }
        System.out.println("test: end");
        return profile;
    }

    private static void newProfile(String name, String pin) throws Exception{
    			profile = new Profile(name, pin, new Database());
    			System.out.println("Name: " + profile.name + " | " + profile.pin);
    			Encrypter e = new Encrypter(pin);
    			Serializer.serialize(e.secretKey);
    			profile.pinKey = e.secretKey;
                Serializer.serialize(profile);
                PS.profile=profile;
                System.out.println("Successfully Serialized and Encrypted.");
    }
    
    static void updateProfile(Profile oldProfile, Profile newProfile) throws IOException {
        // Create a new temporary file to hold the updated profiles
        File tempFile = new File("temp.bin");
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {
            // Open the input stream for reading the original profile data
            in = new ObjectInputStream(new FileInputStream("Profiledata.bin"));

            // Open the output stream for writing the updated profile data to a temporary file
            out = new ObjectOutputStream(new FileOutputStream(tempFile));

            // Loop through all profiles in the original data
            while (true) {
                try {
                    Profile profile = (Profile) in.readObject();

                    // Check if the current profile is the one we want to update
                    if (profile.equals(oldProfile)) {
                        // Write the updated profile to the output stream
                        out.writeObject(newProfile);
                    } else {
                        // Write the original profile to the output stream
                        out.writeObject(profile);
                    }
                } catch (EOFException e) {
                    // Reached end of file
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            // Handle exception
        } finally {
            // Close input and output streams
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }

        // Delete the original profile data file
        File originalFile = new File("ProfileData.bin");
        originalFile.delete();

        // Rename the temporary file to the original file name
        tempFile.renameTo(originalFile);
        System.out.println("updated profile: " + newProfile);
    }

	
}
