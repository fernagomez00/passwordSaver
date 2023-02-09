import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.io.File;
import java.io.FileReader;
public class Database{
    
    private HashMap<PProfile, Password> database = new HashMap<>();
    private File profile;
    private File list;
    
    public Database(){}
    
    public Database(File profile, File list){
        this.list = list;
        this.profile = profile;
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
            sb.append(entry.getValue().toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}