package passwordSaver;

import static org.junit.jupiter.api.Assertions.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class EncrypterTest {
	
	Encrypter e = new Encrypter();

	@Test
	void testEncryption() throws Exception {
		String test1 = "test12345";
		Assert.assertNotEquals(test1, e.encrypt(test1));
		String test2 = "test1874539";
		Assert.assertNotEquals(test2, e.encrypt(test2));
	}
	
	@Test
	void testDecryption() throws Exception {
		String test1 = "test12345";
		Password test = new Password(test1);
		String encrypted = e.encrypt(test.password);
		Assert.assertEquals(test1, e.decrypt(encrypted));
	}
	
	@Test
	void testDecryptionwithKey() throws Exception {
		SecretKey key;
		String test1 = "test12345";
		Password test = new Password(test1);
		e = new Encrypter(test);
		key = e.secretKey;
		
		Assert.assertEquals(key, e.secretKey);
		
		//getEncoded returns the actual bytes for this code to work
		byte[] teststringkey = e.secretKey.getEncoded();
		
		SecretKey originalKey = new SecretKeySpec(teststringkey, "AES"); 
        
        assertEquals(test1, e.decrypt(test.password, originalKey));
	}

}
