
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Cryptography service class providing encryption and decryption
 * @author Khutso
 */

public class CrypographyServices {
    //Define the encryption algorithm and key size
    private static final String ALGORITHM="AES";
    private static final int KEY_SIZE=128;
    
    /**
     * Encrypts the given plain text using the provided key
     * 
     * @param plainText the text to be encrypted
     * @param  Key the encryption key
     * @return the encrypted text as a base64 encoded string
     * @throws Exception if an error occurs  during encryption
     */
    
    public String encrypt(String plainText,String Key) throws Exception{
        //Create a secretKey objects
        SecretKeySpec secretKeySpec =new SecretKeySpec ( Key.getBytes(),ALGORITHM);
        
        //Create a cipher object
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        
        //Intialize the Cipher object for encryption
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
        
        //Encrypt the plain text
        byte[] encryptedBytes=cipher.doFinal(plainText.getBytes());
        
        //Return the encrypted text as a base64 encoded string 
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    /**
     * Decrypt the given encrypted text using the provided key
     * 
     * @param encryptedText the text to be decrypted
     * @param Key  the decryption key
     * @return the decrypted text as a string
     * @throws Exception if an error,occurs during decryption
     */
    
    public  String decrypt(String encryptedText,String Key) throws Exception{
        //Create a secretKeySpec object
         SecretKeySpec secretKeySpec =new SecretKeySpec ( Key.getBytes(),ALGORITHM);
         
        //Create a cipher object
         Cipher cipher=Cipher.getInstance(ALGORITHM);
         
        //Initilize the cipher object ffor decryption
          cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
          
        //Decrypt the encrypted test
        byte[] decryptedBytes=cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        
        //Return the decrypted text as string 
        return new String(decryptedBytes);
    }
    
    /**
     * Generate a secret Key for encryption and decryption
     * 
     * @return the secret Key as base64 encoded string
     * @throws NoSuchAlgorithException if the encryption algorithm is not supported
     */
    
    public String generateKey() throws NoSuchAlgorithmException{
       
        //Create a KeyGenerator object
        KeyGenerator keyGenerator=KeyGenerator.getInstance(ALGORITHM);
        
        //Initialize the keyGenerator object with key Size
        keyGenerator.init(KEY_SIZE);
        
        //Generte a secret key
        SecretKey secretKey= keyGenerator.generateKey();
        
        //return the secret key as a base64 encoded string return
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
    public static void main(String[] arg) throws Exception{
        CrypographyServices crypographyServices =new  CrypographyServices();
        
        //Generate a secrete key
        String Key=crypographyServices.generateKey();
        
        System.out.println("Scret key:" + Key);
        
        //Encryp a message
        String plainText="Hy, are you ok";
        String encryptedText=crypographyServices.encrypt(plainText, Key);
        System.out.println("Encrypted Text:" + encryptedText);
        
        //Decrypt the encrypted message
        String decryptedText=crypographyServices.decrypt(encryptedText, Key);
        System.out.println("Decrypted Text:" + decryptedText);
    }
}
