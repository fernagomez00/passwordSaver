package passwordSaver;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class PS implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	public static Profile profile = new Profile(); //this class will be used to run the program | GUI implementation later if wanted	
	public static void login() throws Exception {
		String name;
		String pin;
		Scanner sc = new Scanner(System.in);
		System.out.print("Please input name: ");
		name = sc.next();
		System.out.print("Please input pin: ");
		pin = sc.next();
		profile = PPwizard.findProfile(name, pin);
		menu();
	}
	
	private static void menu() {
		Scanner sc = new Scanner(System.in);
		if(profile.newProfile == true) {
			System.out.println("Welcome to PS developed by Fernando Gomez "+ profile.name +", it seems that the database associated with this profile is empty. Please save your first password now!");
			newPProfile();
			profile.newProfile = false;
		}
		
		while(true) {
			System.out.print("Commands --> q (quit) | new profile/np (creates a new saved password) | p (shows saved passwords)\nPS Menu please enter command: ");
			String input = sc.next();
			if(input.equalsIgnoreCase("q")){System.out.println("Goodbye!"); saveProfile(); break;}
			if(input.equalsIgnoreCase("new profile") || input.equalsIgnoreCase("np")) {newPProfile();}
			if(input.equalsIgnoreCase("profile") || input.equalsIgnoreCase("p")) {System.out.println(profile.getDatabase());}
		}
		
	}
	
	private static void newPProfile() {
		
		Scanner sc = new Scanner(System.in);
		String username = null;
		String url = null;
		String password = null;
		
		System.out.print("\n\nEnter name of website/software/URL: ");
		url = sc.next();
		System.out.print("Enter username for account: ");
		username = sc.next();
		System.out.print("Enter password for account: ");
		password = sc.next();
		
		
		PProfile newpprofile = new PProfile(url, username, password);
		profile.getDatabase().database.add(newpprofile);
		
	}
	
	
	private static void saveProfile() {
		try {
			Encrypter e = new Encrypter();
			Profile saved = new Profile(); 
			saved.pin = e.encrypt(profile.pin);
			Serializer.serialize(e.secretKey);
			saved.name = profile.name;
			saved.getDatabase().database = PPwizard.profile.getDatabase().database;
			PPwizard.updateProfile(profile, saved);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
