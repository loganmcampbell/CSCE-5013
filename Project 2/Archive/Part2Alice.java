import java.util.Arrays;
import java.util.Scanner;

public class Part2Alice {
    public static void main(String[] args) throws Exception {
    	System.out.println("please enter your 18 bit message");
    	Scanner sc = new Scanner(System.in);
    	String message = sc.nextLine();
    	//Bob Generate Password
    	RSAUtils.initKey(1024);
    	//Read Public Key
    	String publicKey = Utils.read("RSAPublicKey.txt");
        //Encreption
	    byte[] encryptByPublicKey = RSAUtils.encryptByPublicKey(message,publicKey);
	    System.out.println("The byte array after Encrption： " + Arrays.toString(encryptByPublicKey));
	    String encodeStr = Utils.bytesToHexString(encryptByPublicKey);
	    System.out.println("Convert into hexadecimal：" + encodeStr);
	    Utils.save("ctext.txt", encodeStr);
    }
}
