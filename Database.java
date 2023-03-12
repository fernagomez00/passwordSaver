package v1;

import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable{

	private static final long serialVersionUID = 1L;
	public ArrayList<PProfile> data;
	
	public Database(Database d) { data = d.data;}
	public Database() {data = new ArrayList<>();}
	
	public Database returning() {
		return this;
	}
	
	 @Override
	 public String toString() {
	        StringBuilder sb = new StringBuilder();
	        for (PProfile p : data) {
	            sb.append(p);
	            sb.append("\n");
	        }
	        return sb.toString();
	  }
}
