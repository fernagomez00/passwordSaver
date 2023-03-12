package v1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {
	
	public static final File profiles = new File(System.getProperty("user.dir")+"\\Profiles");
	
	/**
	 * 
	 * @param p
	 * @throws IOException
	 */
    public static void serialize(Profile p) throws IOException{
    	try {profiles.mkdir();}catch(Exception e) {System.out.println(e.getMessage());}
    	
        FileOutputStream fout=new FileOutputStream(profiles+"\\"+p.username +"Profiledata.bin");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(p);
        out.flush();
        out.close();
    }
    
    /**
     * 
     * @param name (Profile name)
     * @return the Profile
     * @throws ClassNotFoundException
     * @throws IOException
     */
    static Profile readProfileDatabin(String name) throws ClassNotFoundException, IOException{
        ObjectInputStream in=new ObjectInputStream(new FileInputStream(System.getProperty("user.dir")+ "\\"+ name +"Profiledata.bin"));
        Profile p = (Profile)in.readObject();
        System.out.println("Serializer: "+p.username+" | "+p.pin);
        in.close();
        return p;
    }

}