package gettingReadyForAlpha;

import java.io.Serializable;
import java.util.ArrayList;

public class ProfileDatabase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public ArrayList<Profile> profiles;	
	public ProfileDatabase() { profiles = new ArrayList<>();}
	public ProfileDatabase(ProfileDatabase pd) {profiles = pd.profiles;}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		for(Profile p : profiles) {
			s.append(p.toString()+ "\n");
		}
		
		return s.toString() + "\nEND OF PROFILEDATABASE" ;
	}

}
