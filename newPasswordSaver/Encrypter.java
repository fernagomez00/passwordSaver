package newPasswordSaver;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
public class Encrypter{
    
    private Password toBeEncrypted;
    private String Encrypted = "";
    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 128;
    public SecretKey secretKey;
    
    public Encrypter(Password toBeEncrypted){
        this.toBeEncrypted = toBeEncrypted;
        
        try{
            Encrypted = encrypt(this.toBeEncrypted.password);
            System.out.println("Encrypted Password: " + toBeEncrypted.password + " -- Encrypted: " + Encrypted);
            toBeEncrypted.password = Encrypted;
            toBeEncrypted.key = secretKey;
            toBeEncrypted.encrypted = true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }

    public Encrypter(){}
    
    public static void encrypt(Password plainText) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
    	if(plainText.encrypted != true) {
    		KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
            kg.init(KEY_SIZE);
            SecretKey secretKey = kg.generateKey();
            plainText.key = secretKey;
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plainText.password.getBytes());
            System.out.println(secretKey + " | " + encryptedBytes);
    	}
    }

    public String encrypt(String plainText) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
        kg.init(KEY_SIZE);
        secretKey = kg.generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        System.out.println(secretKey + " | " + encryptedBytes);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    public String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
    
    public String decrypt(String encryptedText) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

	public String decrypt(Password encryptedText) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, encryptedText.key);
		byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText.password);
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		System.out.println("Decryptedbytes: "+new String(decryptedBytes));
		encryptedText.encrypted = false;
		return new String(decryptedBytes);
    }
	
	public static byte[] returnEncoded(SecretKey sk) {
		return sk.getEncoded();
	}
}