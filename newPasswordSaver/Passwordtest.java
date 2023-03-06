package newPasswordSaver;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class Passwordtest {
	

	@Test
	void testPasswordNull() throws Exception {
		Password p = new Password("Test123");
		
		Assert.assertNotNull(p.password);
		Assert.assertNotNull(p.key);
		
	}
	
	@Test
	void testPasswordKeys() throws Exception {
		Password p;
		
		for(int i = 0; i < 10; i++) {
			p = new Password("Test"+i);
			Assert.assertNotNull(p.key);
		}
	}

}
