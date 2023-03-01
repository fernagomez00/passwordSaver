package passwordSaver;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class PSTest {

	/*
	 * Testing Checklist: 
	 *  -test serializer 
	 *  -need to make sure database saves when user quits
	 *  -need to encrypt passwords at some point
	 *  
	 * 
	 */
	
	String test = "f";
	String test1 = "t";
	PProfile[] testafterclose =  new PProfile[2];
	
	
	
	@Test
	void testPPwizard() {
		System.out.println(PPwizard.profile);
		Assert.assertNotNull(PPwizard.profile);
		Assert.assertNotNull(PPwizard.profile.profileDatabase);
		Assert.assertNotNull(PPwizard.profile.pinKey);
		Assert.assertNotNull(PPwizard.profile.pin);
		Assert.assertNotNull(PPwizard.profile.name);
	}
	
	@Test
	void testDatabase() throws Exception {
		testafterclose[0] = new PProfile(test,test,test);
		testafterclose[1] = new PProfile(test1,test1,test1);
		PS.login();
		Assert.assertNotNull(PS.profile);
		Assert.assertNotNull(PS.profile.getDatabase());
		
		Assert.assertArrayEquals(testafterclose, PS.profile.getDatabase().database.toArray());
	}

}
