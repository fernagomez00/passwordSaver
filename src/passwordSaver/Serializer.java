package passwordSaver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.crypto.SecretKey;

public class Serializer {

    static void serialize(Profile p) throws IOException{
        FileOutputStream fout=new FileOutputStream("Profiledata.bin");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(p);
        out.flush();
        out.close();
    }

    static void serialize(SecretKey sk) throws IOException{
        FileOutputStream fout=new FileOutputStream("SKdata.bin");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(sk);
        out.flush();
        out.close();
    }

    static void serialize(PProfile pp) throws IOException{
        FileOutputStream fout=new FileOutputStream("PPdata.bin");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(pp);
        out.flush();
        out.close();
    }

    static Profile readProfileDatabin() throws ClassNotFoundException, IOException{
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("Profiledata.bin"));
        Profile p = (Profile)in.readObject();
        System.out.println(p.name+" | "+p.pin);
        in.close();
        return p;
    }
    
    static SecretKey readSKDatabin() throws ClassNotFoundException, IOException{
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("SKdata.bin"));
        SecretKey sk = (SecretKey)in.readObject();
        System.out.println("Key: "+sk);
        in.close();
        return sk;
    }

    static PProfile readPPDatabin() throws ClassNotFoundException, IOException{
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("SKdata.bin"));
        PProfile pp = (PProfile)in.readObject();
        System.out.println(pp);
        in.close();
        return pp;
    }
}