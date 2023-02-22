package passwordSaver;
import java.io.Serializable;
import java.util.ArrayList;
public class Database implements Serializable{
    
    private static final long serialVersionUID = 1L;
	public ArrayList<PProfile> database = new ArrayList<>();
    
    public Database(){}
    
    public void add(PProfile pp){database.add(pp);}
    
    public String returnPasswordforProfile(PProfile key){
    	PProfile ret = null;
    	for(PProfile p : database) {
    		if(p == key) {ret = p;}
    	}
        return ret.toString();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PProfile p : database) {
            sb.append(p);
            sb.append("\n");
        }
        return sb.toString();
    }
}