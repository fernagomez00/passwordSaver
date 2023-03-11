package almostAlpha;

import java.io.IOException;
import java.util.Scanner;

public class PS{
	
	public static final String version = "v1.0"; 
	
	
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
		menu();
		sc.close();
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
	
	private static void menu() throws Exception {
		Scanner sc = new Scanner(System.in);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		if(PPwizard.returningProfile(profile) != true) {
			System.out.println("Welcome to PS developed by Fernando Gomez "+ profile.username +", it seems that the database associated with this profile is empty. Please save your first password now!");
			newPProfile(profile);
		}
		
		while(true) {
			System.out.print("PS (PasswordSaver):<"+ version +">\n\nCommands --> q (quit) | new profile/np (creates a new saved password) | p (shows saved passwords) | d (show decrypted password)\nPS Menu please enter command: ");
			
			String input = sc.next();
			if(input.equalsIgnoreCase("q")){System.out.println("Goodbye!"); Thread.sleep(1000); saveProfile(); break;}
			if(input.equalsIgnoreCase("new profile") || input.equalsIgnoreCase("np")) {newPProfile(PS.profile);}
			if(input.equalsIgnoreCase("profile") || input.equalsIgnoreCase("p")) {System.out.println(profile.getDatabase()); Thread.sleep(4000);}
			if(input.equalsIgnoreCase("d")) {
				System.out.println("\n\n\n"+profile.getDatabase());
				System.out.print("\nWhich password do you want to see (enter number): ");
			    int input1 = sc.nextInt();
			    showPassword(input1);
			}
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
	
	private static void showPassword(int input) {
		
		
		// do something with the integer
	    try {
	    	
	    	System.out.println("\nPassword for "+ profile.data.get(input-1).name_url +" --(Decryted)--> " + Encrypter.decrypt(profile.data.get(input-1).retPassword())+"\n\n\n");
	    	Thread.sleep(5000);
	    	
	    }catch(Exception error) {System.out.println("\nerror:"+error.getMessage());}
		
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
		
		PProfile newpprofile = new PProfile(username, url, n.password, n.key);
		profile.data.add(newpprofile);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//		sc.close();
	}
	
	
	@SuppressWarnings("static-access")
	private static void saveProfile() throws InterruptedException, IOException {
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
		 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
}
