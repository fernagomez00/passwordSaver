import java.util.HashMap;
public class PasswordFeatures{
    
    private static final char[] upperchars = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private static final char[] lowerchars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private static final char[] numericalchars = {'1','2','3','4','5','6','7','8','9'};
    private static final char[] specialchars = {'!','@','#','$','%','^','&','*','(',')'};
    private static final HashMap<char[], Integer> passwordStrength = new HashMap<>();
    
    public static String generateStrongPassword() throws Exception{
        Password ret;
        StringBuilder sb = new StringBuilder();
        
        for(int i =0; i < 10; i++){
            
            sb.append(upperchars[(int)(Math.random()*upperchars.length)]);
            sb.append(lowerchars[(int)(Math.random()*lowerchars.length)]);
            sb.append(numericalchars[(int)(Math.random()*numericalchars.length)]);
            sb.append(specialchars[(int)(Math.random()*specialchars.length)]);
        }
        ret = new Password(sb.toString());
        return Encrypter.decrypt(ret) + " | Password Strength: " + getPasswordStrength(Encrypter.decrypt(ret));
    }
    
    public static int getPasswordStrength(String password){
        initializeHashMap();
        int rating = 0;
        
        for(int i = 0; i < password.length(); i++){
            char c = password.charAt(i);
            if(lowerchars.contains(c)==true){rating+= passwordStrength.get(lowerchars);}
            if(upperchars.contains(c)==true){rating+= passwordStrength.get(upperchars);}
            if(numericalchars.contains(c)==true){rating+= passwordStrength.get(numericalchars);}
            if(specialchars.contains(c)==true){rating+= passwordStrength.get(specialchars);}
        }
        
        return rating;
    }
    
    private static void initializeHashMap(){
        passwordStrength.put(lowerchars, 3);
        passwordStrength.put(upperchars, 5);
        passwordStrength.put(numericalchars, 7);
        passwordStrength.put(specialchars, 10);
    }
}