import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
 
public class RSAUtils {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
 
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    
 
    public static byte[] decryptBASE64(String key) {
        return Base64.decodeBase64(key);
    }
 
    public static String encryptBASE64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }
 
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception{
        // encryption of the key
        byte[] keyBytes = decryptBASE64(key);
        // get the private key
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // encryption private key
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }
 
    /**
     * Decryption<br>
     * use private key to solve
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(String data, String key)
            throws Exception {
        return decryptByPrivateKey(decryptBASE64(data),key);
    }
 
    /**
     * encryption<br>
     * use public key 
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(String data, String key)
            throws Exception {
        // to encryption 
        byte[] keyBytes = decryptBASE64(key);
        // get the public key
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // to encryption the message
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data.getBytes());
    }
 
    /**
     * get the private key
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Key> keyMap)
            throws Exception {
        Key key = keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }
 
    /**
     * get the public key
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Key> keyMap)
            throws Exception {
        Key key = keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }
 
    /**
     * initialize the private key
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Key> initKey(int keySize) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(keySize);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        Map<String, Key> keyMap = new HashMap<String, Key>(2);
        keyMap.put(PUBLIC_KEY, keyPair.getPublic());// public key
        keyMap.put(PRIVATE_KEY, keyPair.getPrivate());// Private key
        Utils.save("RSAPublicKey.txt", getPublicKey(keyMap));
        Utils.save("RSAPrivateKey.txt", getPrivateKey(keyMap));
        return keyMap;
    }
}