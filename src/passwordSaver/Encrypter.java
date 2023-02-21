package passwordSaver;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.KeyGeneratorSpi;
import javax.crypto.SecretKey;
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
//            System.out.println("Encrypted Password: " + toBeEncrypted.password + " -- Encrypted: " + Encrypted);
            toBeEncrypted.password = Encrypted;
            toBeEncrypted.setKey(secretKey);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Encrypter(String toBeEncrypted){
        
        try{

            Encrypted = encrypt(toBeEncrypted);
//            System.out.println("Encrypted Password: " + toBeEncrypted + " -- Encrypted: " + Encrypted);
            toBeEncrypted = Encrypted;
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Encrypter(){}

    public String encrypt(String plainText) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
        kg.init(KEY_SIZE);
        secretKey = kg.generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
//        System.out.println(secretKey + " | " + encryptedBytes.length);
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

}