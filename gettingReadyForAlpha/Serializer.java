package gettingReadyForAlpha;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {

    public static void serialize(ProfileDatabase pd) throws IOException, ClassNotFoundException{
    	System.out.println("Serializing -->\n" + pd);
        FileOutputStream fout=new FileOutputStream(System.getProperty("user.dir")+"\\Profiledata.bin");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(pd);
//        System.out.println("serializer --> " + Serializer.readProfileDatabin());
        out.flush();
        out.close();
    }
    
    static ProfileDatabase readProfileDatabin() throws ClassNotFoundException, IOException{
        ObjectInputStream in=new ObjectInputStream(new FileInputStream(System.getProperty("user.dir")+"\\Profiledata.bin"));
        ProfileDatabase pd = (ProfileDatabase)in.readObject();
        in.close();
        return pd;
    }

}