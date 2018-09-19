import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
	//Password length
	public static final int AES_128 = 128;
	public static final int AES_192 = 192;
	public static final int AES_256 = 256;
	//No Padding// PKCS5Padding // Padding
	public final static String AES_CBC_NoPadding = "AES/CBC/NoPadding";
	public final static String AES_CBC_PKCS5Padding = "AES/CBC/PKCS5Padding";
	public final static String AES_CBC_ISO10126Padding = "AES/CBC/ISO10126Padding";

	//key
	String key;
	//Counter
	byte[] iv;
	//password length
	int type;
	//No Padding// PKCS5Padding // Padding
	String modeAndPadding;
	SecretKeySpec keySpec;
	//encryption
	Cipher encryptCipher;
	//decryption
	Cipher decryptCipher;

	/**
	 * 
	 * @param key 
	 * @param type
	 * @param modeAndPadding
	 * @throws Exception
	 */
	public AESUtils(String key, int type, String modeAndPadding) throws Exception {
		this.key = key;
		this.iv = getIV();
		this.type = type;
		this.modeAndPadding = modeAndPadding;
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		// if Error report
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(key.getBytes());
		kgen.init(type, random);
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		this.keySpec = new SecretKeySpec(enCodeFormat, "AES");
		this.encryptCipher = Cipher.getInstance(modeAndPadding);// create password
		if (null != iv) {
			//  (Initialization vector，IV)， IV must be 16 digits
			encryptCipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
		} else {
			encryptCipher.init(Cipher.ENCRYPT_MODE, keySpec);
		}
		this.decryptCipher = Cipher.getInstance(modeAndPadding);// create password
		if (null != iv) {
			// (Initialization vector，IV)， IV 
			decryptCipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
		} else {
			decryptCipher.init(Cipher.DECRYPT_MODE, keySpec);
		}
	}

	public byte[] encrypt(byte[] source) throws Exception{
		byte[] result = encryptCipher.doFinal(source);
		return result;
	}

	public byte[] decrypt(byte[] source) throws Exception {
		byte[] result = decryptCipher.doFinal(source);
		return result;
	}

	/**
	 *  (Initialization vector，IV)，IV must be 17 digits
	 */
	public static final byte[] getIV() throws Exception {
		return "1234567812345678".getBytes("UTF-8");
	}
}