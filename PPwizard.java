package v2;

import java.io.IOException;
import java.util.Scanner;


public class PPwizard {
	
	
	public static boolean returningProfile(Profile p) throws Exception {
		boolean returning = false;
		
		try {
	        int count = 0;
	        while(true) {
	        	Profile storedProfile = Serializer.readProfileDatabin();
	        	try {
					if(Serializer.profiles.listFiles().length <= count || storedProfile == null) {
						break;
					}else {count++;}
					
					if (p.username.equals(storedProfile.username) && Encrypter.decrypt(p.pin).equals(Encrypter.decrypt(storedProfile.pin)) ) {
						System.out.println(storedProfile);
						p.data = storedProfile.data;
						p.uuid = storedProfile.uuid;
						System.out.println("PPwizard: "+ storedProfile.getDatabase());
					    returning = true;
					}
				} catch (Exception e) {
					return false;
				}
	        }
	    } catch (IOException | ClassNotFoundException e) {}
		System.out.println("RETURING PROFILE = " + returning);
		return returning;
	}
	
	public static void newPProfile(Profile profile, String username, String url, String n) throws Exception {
		Password password = new Password(n);
		PProfile newpprofile = new PProfile(username, url, password.password, password.key);
		profile.data.add(newpprofile);
		System.out.println("Added new data!");
	}
	
	@SuppressWarnings("static-access")
	public static void saveProfile(Profile profile) throws InterruptedException, IOException {
		try {
			Serializer.serialize(profile);
			System.out.println("Saved Profile!");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		 
	}
}
