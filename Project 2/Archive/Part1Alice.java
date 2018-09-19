import java.util.Arrays;
import java.util.Scanner;

public class Part1Alice {
    public static void main(String[] args) throws Exception {
    	System.out.println("please enter your 18 bit message");
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        //Read the key Value
        String key = Utils.read("AESKey.txt");
        AESUtils utils = new AESUtils(key, AESUtils.AES_192, AESUtils.AES_CBC_PKCS5Padding);
        byte[] encrypt = utils.encrypt(message.getBytes());
        System.out.println("The byte array after Encrption ： " + Arrays.toString(encrypt));
        String encodeStr = Utils.bytesToHexString(encrypt);
        System.out.println("Convert into hexadecimal ：" + encodeStr);
        Utils.save("ctext.txt", encodeStr);
    }

}
