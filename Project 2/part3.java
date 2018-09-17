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
import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class part3
{
  private static byte[] encryptAES192(String message) throws Exception
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

  private static String decryptAES192(byte[] encryptedText) throws Exception
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

  private static byte[] encryptAES128(String message) throws Exception
  {
    message.getBytes();
    byte[] keyBytes = "thisislengtis128".getBytes();
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

  private static String decryptAES128(byte[] encryptedText) throws Exception
  {
     String decryptedValue = "";
     try
     {
        byte[] keyBytes = "thisislengtis128".getBytes();
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

  private static byte[] encryptAES256(String message) throws Exception
  {
    message.getBytes();
    byte[] keyBytes = "LOGANMALACHICAMPBELLCSCE5013UARK".getBytes();
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

   private static String decryptAES256(byte[] encryptedText) throws Exception
   {
     String decryptedValue = "";
     try
     {
        byte[] keyBytes = "LOGANMALACHICAMPBELLCSCE5013UARK".getBytes();
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

  private BigInteger p;
  private BigInteger q;
  private BigInteger n;
  private BigInteger phi;
  private BigInteger e;
  private BigInteger d;
  private static int bit2048 = 2048;
  private Random rand;
  private static int bit1024 = 1024;
  private static int bit4096 = 4096;

  public part3(int bit)
  {
      rand = new Random();
      p = BigInteger.probablePrime(bit, rand);
      q = BigInteger.probablePrime(bit, rand);
      n = p.multiply(q);
      phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
      e = BigInteger.probablePrime(bit / 2, rand);
      while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)
      {
          e.add(BigInteger.ONE);
      }
      d = e.modInverse(phi);
  }
  public part3(BigInteger e, BigInteger d, BigInteger N)
  {
      this.e = e;
      this.d = d;
      this.n = n;
  }

  private static String bytesToString(byte[] encrypt)
  {
      String test = "";
      for (byte b : encrypt)
      {
          test += Byte.toString(b);
      }
      return test;

  }
  // RSA:  Encrypt message
  public byte[] encrypt(byte[] message)
  {
      return (new BigInteger(message)).modPow(e, n).toByteArray();
  }
  // RSA: Decrypt message
  public byte[] decrypt(byte[] message)
  {
      return (new BigInteger(message)).modPow(d, n).toByteArray();
  }

  public static void main(String[] args) throws Exception
  {
    String s;
    s = "";
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a 7 BYTE STRING : ");
    s = sc.nextLine();
    long a1 = 0; long a2 = 0; long a3 = 0;
    long a4 = 0; long a5 = 0; long a6 = 0;
    String message = s;
    part3 rsa2 = new part3(bit2048);
    part3 rsa1 = new part3(bit1024);
    part3 rsa3 = new part3(bit4096);
    System.out.println("You entered : "+ s);
    System.out.println("message length: " + message.length());

    for(int x = 0; x < 100; x++)
    {
      long starttime = System.currentTimeMillis();
      encryptAES192(message);
      decryptAES192(encryptAES192(message));
      long endtime = System.currentTimeMillis();
      long duration = (endtime - starttime);
      a2 += duration;


      starttime = 0; duration = 0; endtime = 0;
      starttime = System.currentTimeMillis();
      encryptAES128(message);
      decryptAES128(encryptAES128(message));
      endtime = System.currentTimeMillis();
      duration = (endtime - starttime);
      a1 += duration;


      starttime = 0; duration = 0; endtime = 0;
      starttime = System.currentTimeMillis();
      encryptAES256(message);
      decryptAES256(encryptAES256(message));
      endtime = System.currentTimeMillis();
      duration = (endtime - starttime);
      a3 += duration;
    }

    System.out.println("AES 192 : " + a2 +"ms");
    System.out.println("AES 128 : " + a1 + "ms");
    System.out.println("AES 256 : " + a3 + "ms");

    for(int x = 0; x < 100; x++)
    {
      long starttime = 0; long duration = 0; long endtime = 0;
      starttime = System.currentTimeMillis();
      byte[] encrypt = rsa1.encrypt(message.getBytes());
      byte[] decrypt = rsa1.decrypt(encrypt);
      endtime = System.currentTimeMillis();
      duration = (endtime - starttime);
      a4 += duration;

      starttime = 0; duration = 0; endtime = 0;
      starttime = System.currentTimeMillis();
      byte[] encrypt1 = rsa2.encrypt(message.getBytes());
      byte[] decrypt1 = rsa2.decrypt(encrypt);
      endtime = System.currentTimeMillis();
      duration = (endtime - starttime);
      a5 += duration;

      starttime = 0; duration = 0; endtime = 0;
      starttime = System.currentTimeMillis();
      byte[] encrypt2 = rsa3.encrypt(message.getBytes());
      byte[] decrypt2 = rsa3.decrypt(encrypt);
      endtime = System.currentTimeMillis();
      duration = (endtime - starttime);
      a6 += duration;
    }

    System.out.println("RSA 1024 : " + a2 +"ms");
    System.out.println("RSA 2048 : " + a1 + "ms");
    System.out.println("RSA 4096 : " + a3 + "ms");

  }



}
