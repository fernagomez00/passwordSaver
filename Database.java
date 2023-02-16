package passwordSaver;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Database{
    
    private File keys = new File("keys.txt");
    private HashMap<PProfile, Password> database = new HashMap<>();
    private File profile;
    private File list;
    
    public Database(){}
    
    public Database(File profile, File list) throws FileNotFoundException{
        this.list = list;
        this.profile = profile;
        readandSetDatabase();
    }
    
    public void readandSetDatabase() throws FileNotFoundException{
        BufferedReader myReader = new BufferedReader(new FileReader(keys));
         try {
            // File profile = new File("profile" + name + ".txt");
            // myWriter.write(name + ",");
            // myWriter.write(pin);
            String keys;
            String line = myReader.readLine();
            
            System.out.println(line);
            
            
            myReader.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public void add(PProfile pp, Password p){
        database.put(pp,p);
    }
    
    public String returnPasswordProfile(PProfile key){
        return key + " Password--> " + database.get(key);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PProfile p : database.keySet()) {
            sb.append(p);
            sb.append(" Password: ");
            sb.append(database.get(p));
            sb.append("\n");
        }
        return sb.toString();
    }
}