package v2;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {
	
	public static final File profiles = new File(System.getProperty("user.dir")+File.separator+"Profiles");
	
	/**
	 * 
	 * @param p
	 * @throws IOException
	 */
    public static void serialize(Profile p) throws IOException{
    	try {profiles.mkdir();}catch(Exception e) {System.out.println(e.getMessage());}
        FileOutputStream fout=new FileOutputStream(profiles+ File.separator + p.uuid +"-Profiledata.bin");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(p);
        out.flush();
        out.close();
        System.out.println("Successfully Serialized!");
    }
    
    private static int currentFileIndex = 0;

    static Profile readProfileDatabin() throws ClassNotFoundException, IOException {
        File[] files = profiles.listFiles();
        if(files == null) {
        	return null;
        }
        if (currentFileIndex >= files.length) {
        	currentFileIndex = 0;
            return null; // no more profiles to read
        }
        File file = files[currentFileIndex];
        currentFileIndex++;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Profile p = (Profile) in.readObject();
        System.out.println("Serializer: " + p.username + " | " + p.pin + " | " + p.uuid);
        in.close();
        return p;
    }

}