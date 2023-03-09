package gettingReadyForAlpha;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PPwizard {
	
	
	public static boolean returningProfile(Profile p) throws Exception {
		boolean returning = false;
		
		try {
	        for(Profile profile : PS.pd.profiles) {
	        	Profile storedProfile = profile;
	 	        System.out.println("Findprofile test: " + (p.username.equals(storedProfile.username) && Encrypter.decrypt(p.pin).equals(Encrypter.decrypt(storedProfile.pin)))+" | "+ storedProfile.getDatabase());
	 	        if (p.username.equals(storedProfile.username) && Encrypter.decrypt(p.pin).equals(Encrypter.decrypt(storedProfile.pin))) {
	 	        	p = storedProfile;
	 	            returning = true;
	 	            break;
	 	        }
	        }
	       
	    } catch (IOException | ClassNotFoundException e) {}
		
		System.out.println("RETURING PROFILE = " + returning);
		return returning;
	}
	
	public static void updateProfile(Profile oldProfile, Profile newProfile) throws IOException {
        // Create a new temporary file to hold the updated profiles
        File tempFile = new File("temp.bin");
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {
            // Open the input stream for reading the original profile data
            in = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir")+"\\Profiledata.bin"));

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
        File originalFile = new File(System.getProperty("user.dir")+"\\ProfileData.bin");
        originalFile.delete();

        // Rename the temporary file to the original file name
        tempFile.renameTo(originalFile);
        System.out.println("updated profile: " + newProfile);
    }
	
	public static void newProfile(String name, String pin) throws Exception{
		Profile profile = new Profile(name, new Password(pin), new Database());
		System.out.println("New Name: " + profile.username + " | " + profile.pin);
		PS.pd.profiles.add(profile);
        Serializer.serialize(PS.pd);
        PS.profile=profile;
        System.out.println("Successfully Serialized and Encrypted.");
}

}
