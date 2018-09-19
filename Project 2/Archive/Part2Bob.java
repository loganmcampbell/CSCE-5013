import java.util.Arrays;

public class Part2Bob {
    public static void main(String[] args) throws Exception {
    	
        String privateKey = Utils.read("RSAPrivateKey.txt");
        String encodeStr = Utils.read("ctext.txt");
        byte[] encryptByPublicKey = Utils.hexStringToBytes(encodeStr);
        System.out.println("turn into hexadecimal ：：" + encodeStr);
        System.out.println("encryption byte array："  +Arrays.toString(encryptByPublicKey));
        
        byte[] decryptByPrivateKey = RSAUtils.decryptByPrivateKey(encryptByPublicKey,privateKey);
        System.out.println("Your message is：" + new String(decryptByPrivateKey));
    }
}
