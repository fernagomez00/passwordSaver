package almostAlpha;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class PPwizard {
	
	
	public static boolean returningProfile(Profile p) throws Exception {
		boolean returning = false;
		
		try {
	        ObjectInputStream in = new ObjectInputStream(new FileInputStream(Serializer.profiles+"\\"+ p.username +"Profiledata.bin"));
	        Profile storedProfile = (Profile) in.readObject();
	        in.close();
//	        System.out.println("Findprofile test: " + (p.username.equals(storedProfile.username) && Encrypter.decrypt(p.pin).equals(Encrypter.decrypt(storedProfile.pin))));
	        if (p.username.equals(storedProfile.username) && Encrypter.decrypt(p.pin).equals(Encrypter.decrypt(storedProfile.pin))) {
	        	p.data = storedProfile.data;
	            returning = true;
	        }
	    } catch (IOException | ClassNotFoundException e) {}
		
		System.out.println("RETURING PROFILE = " + returning);
		return returning;
	}
	
	public static void newProfile(String name, String pin) throws Exception{
		Profile profile = new Profile(name, new Password(pin), new Database());
		System.out.println("New Name: " + profile.username + " | " + profile.pin);
        Serializer.serialize(profile);
        PS.profile=profile;
        System.out.println("Successfully Serialized and Encrypted.");
	}

}
