package gettingReadyForAlpha;

import java.util.ArrayList;

public class Database{
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
