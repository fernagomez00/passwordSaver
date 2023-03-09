package newPasswordSaver;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class PS{
	
	
	
	public static Profile profile = new Profile(); //this class will be used to run the program | GUI implementation later if wanted	
	public static void login() throws Exception {
		String name;
		String pin;
		Scanner sc = new Scanner(System.in);
		System.out.print("Please input name: ");
		name = sc.next();
		System.out.print("Please input pin: ");
		pin = sc.next();
		profile = new Profile(name, new Password(pin), new Database());
		System.out.println(PPwizard.returningProfile(profile) + "\n" + profile);
		menu();
	}
	
	private static void menu() throws Exception {
		Scanner sc = new Scanner(System.in);
		if(PPwizard.returningProfile(profile) != true) {
			System.out.println("Welcome to PS developed by Fernando Gomez "+ profile.username +", it seems that the database associated with this profile is empty. Please save your first password now!");
			newPProfile(profile);
		}
		
		while(true) {
			System.out.print("Commands --> q (quit) | new profile/np (creates a new saved password) | p (shows saved passwords)\nPS Menu please enter command: ");
			String input = sc.next();
			if(input.equalsIgnoreCase("q")){System.out.println("Goodbye!"+"\n"+profile + "\n"+profile.getDatabase()); saveProfile(); break;}
			if(input.equalsIgnoreCase("new profile") || input.equalsIgnoreCase("np")) {newPProfile(PS.profile);}
			if(input.equalsIgnoreCase("profile") || input.equalsIgnoreCase("p")) {System.out.println(profile.getDatabase());}
		}
		
	}
	
	private static void newPProfile(Profile profile) throws Exception {
		
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
		
		Password n = new Password(password);
		
		PProfile newpprofile = new PProfile(url, username, n.password, n.key);
		profile.data.add(newpprofile);
		
		
	}
	
	
	@SuppressWarnings("static-access")
	private static void saveProfile() {
		try {
			
			Profile saved = new Profile(); 
			saved.username = profile.username;
			saved.pin = profile.pin;
			saved.getDatabase().data = profile.getDatabase().data;
			Serializer.serialize(saved);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
