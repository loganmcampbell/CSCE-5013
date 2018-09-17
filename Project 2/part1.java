import java.security.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random;
import java.security.*;
import javax.crypto.spec.IvParameterSpec;
import java.util.Scanner;

public class part1
{

private static byte[] encrypt(String message) throws Exception
{
  message.getBytes();
  byte[] keyBytes = "LengthOfThisTextIs192bit".getBytes();
  Key key = new SecretKeySpec(keyBytes, "AES");
  Cipher c = Cipher.getInstance("AES");
  c.init(Cipher.ENCRYPT_MODE, key);
  try
  {
   return c.doFinal(message.getBytes());
  }
  catch(Exception e)
  {
    System.out.println(e);
  }
  return c.doFinal(message.getBytes());
}

 private static String decrypt(byte[] encryptedText) throws Exception
 {
   String decryptedValue = "";
   try
   {
      byte[] keyBytes = "LengthOfThisTextIs192bit".getBytes();
      Key key = new SecretKeySpec(keyBytes, "AES");
      Cipher c = Cipher.getInstance("AES");
      c.init(Cipher.DECRYPT_MODE, key);
      byte[] decValue = c.doFinal(encryptedText);
      decryptedValue = new String(decValue);
      return decryptedValue;
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
    return decryptedValue;
}


  public static void main(String[] args) throws Exception
  {
    String s;
    s = "";
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a 18 BYTE STRING : ");
    s = sc.nextLine();
    String message = s;

    System.out.println("You entered String: "+s);
    System.out.println("message length: " + message.length());
    System.out.println(encrypt(message));
    System.out.println(decrypt(encrypt(message)));
  }



}
