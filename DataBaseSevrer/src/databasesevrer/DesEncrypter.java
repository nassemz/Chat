/*
 ********************************************************************************************************************
 * DesEncrypter.java                                                                                                *
 * This class encrypt and decrypt the strings bye the DES algorithim                                                *   
 * there is 2 function                                                                                              *
 * (1) encrypt : thats encrypt the strings                                                                          *
 * (2) decrypt : thats decrypt the strings                                                                          *
 *                                                                                                                  *
 * Created on January 26, 2007, 1:44 AM                                                                             *
 *                                                                                                                  *
 * To change this template, choose Tools | Template Manager                                                         *
 * and open the template in the editor.                                                                             *
 ********************************************************************************************************************
 */

package databasesevrer;
import java.io.UnsupportedEncodingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;

/**
 * @author Nissim Zo
 */

public class DesEncrypter {
    private Cipher ecipher;
    private Cipher dcipher;
    
    DesEncrypter(SecretKey key) {
        try {
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);
            
        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
            return;
        }
    }

    // the encrypt function 
    public String encrypt(String str) {
        try {
            if(str == null)
                return "";
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");
            
            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);
            
            // Encode bytes to base64 to get a string
            return new sun.misc.BASE64Encoder().encode(enc);
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }

    // the decrypt function 
    public String decrypt(String str) {
        try {
            // Decode base64 to get bytes
            if(str == null)
                return "";
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
            
            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);
            
            // Decode using utf-8
            return new String(utf8, "UTF8");
        } catch (javax.crypto.BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
