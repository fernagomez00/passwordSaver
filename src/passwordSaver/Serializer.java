package passwordSaver;
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
    
//    static void clearSK() throws IOException {
//    	FileOutputStream fout=new FileOutputStream("SKdata.bin", false);
//    }
    
        static void removeProfile(Profile profile) {
        try {
            File file = new File("Profiledata.bin");
            if (!file.exists()) {
                System.out.println("File does not exist.");
                return;
            }

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<Profile> profiles = new ArrayList<>();
            boolean removed = false;

            while (true) {
                try {
                    Profile p = (Profile) ois.readObject();
                    System.out.println("Check Removing: " + (p.name.equals(profile.name)));
                    if (!p.name.equalsIgnoreCase(profile.name) && !p.pin.equals(profile.pin)) {
                        profiles.add(p);
                    } else {
                        removed = true;
                    }
                } catch (EOFException e) {
                    break;
                }
            }

            ois.close();
            fis.close();

            if (removed == true) {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                for (Profile p : profiles) {
                    oos.writeObject(p);
                }

                oos.close();
                fos.close();

                System.out.println("Profile removed successfully.");
            } else {
                System.out.println("Profile not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void serialize(Database d) throws IOException{
        FileOutputStream fout=new FileOutputStream("Database.bin");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(d);
        out.flush();
        out.close();
    }
    static Profile readProfileDatabin() throws ClassNotFoundException, IOException{
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("Profiledata.bin"));
        Profile p = (Profile)in.readObject();
        System.out.println("Serializer: "+p.name+" | "+p.pin);
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

}