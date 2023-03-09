package newPasswordSaver;

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
	Password[] passwords = new Password[2];
	String test1 = "t";
	PProfile[] testafterclose =  new PProfile[2];
	
	@Test
	void testPPwizard() {
		System.out.println(PS.profile);
		Assert.assertNotNull(PS.profile);
		Assert.assertNotNull(PS.profile.data);
		Assert.assertNotNull(PS.profile.pin);
		Assert.assertNotNull(PS.profile.pin.key);
		Assert.assertNotNull(PS.profile.pin.password);
		Assert.assertNotNull(PS.profile.username);
		
	}
	
//	@Test
//	void testDatabase() throws Exception {
//		passwords[0] = new Password("test1234");
//		passwords[1] = new Password("test1191108");
//		testafterclose[0] = new PProfile(test,test,test, passwords[0].key);
//		testafterclose[1] = new PProfile(test1,test1,test1, passwords[1].key);
//		PS.login();
//		Assert.assertNotNull(PS.profile);
//		Assert.assertNotNull(PS.profile.getDatabase());
//		
//		Assert.assertArrayEquals(testafterclose, PS.profile.getDatabase().data.toArray());
//	}
	
	
	@Test
	void testProfileLoad() throws Exception {
		System.out.println("testProfileLoad: " +Serializer.readProfileDatabin());
		System.out.println("testProfileLoad size: " +Serializer.readProfileDatabin().data.size());
	}

}