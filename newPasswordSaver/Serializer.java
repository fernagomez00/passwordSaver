package newPasswordSaver;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.crypto.SecretKey;

public class Serializer {

    public static void serialize(Profile p) throws IOException{
        FileOutputStream fout=new FileOutputStream(System.getProperty("user.dir")+"\\Profiledata.bin");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(p);
        out.flush();
        out.close();
    }
    
    public static void serialize(SecretKey sk) throws IOException{
        FileOutputStream fout=new FileOutputStream(System.getProperty("user.dir")+"\\SKdata.bin");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(sk);
        out.flush();
        out.close();
    }
    
    
    static void serialize(Database d) throws IOException{
        FileOutputStream fout=new FileOutputStream(System.getProperty("user.dir")+"\\Database.bin");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(d);
        out.flush();
        out.close();
    }
    static Profile readProfileDatabin() throws ClassNotFoundException, IOException{
        ObjectInputStream in=new ObjectInputStream(new FileInputStream(System.getProperty("user.dir")+"\\Profiledata.bin"));
        Profile p = (Profile)in.readObject();
        System.out.println("Serializer: "+p.username+" | "+p.pin);
        in.close();
        return p;
    }
    
    static byte[] readSKDatabin() throws ClassNotFoundException, IOException{
        ObjectInputStream in=new ObjectInputStream(new FileInputStream(System.getProperty("user.dir")+"\\SKdata.bin"));
        SecretKey sk = (SecretKey)in.readObject();
        System.out.println("Key: "+sk);
        in.close();
        return sk.getEncoded();
    }

}