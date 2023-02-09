import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;
public class Encrypter{
    
    private Password toBeEncrypted;
    private String Encrypted = "";
    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 128;
    private SecretKey secretKey;
    
    public Encrypter(Password toBeEncrypted){
        this.toBeEncrypted = toBeEncrypted;
        try{
            Encrypted = encrypt(this.toBeEncrypted.password);
            toBeEncrypted.password = Encrypted;
            toBeEncrypted.setKey(secretKey);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String encrypt(String plainText) throws Exception {
        secretKey = KeyGenerator.getInstance(ALGORITHM).generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    public String decrypt(String encryptedText) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

}