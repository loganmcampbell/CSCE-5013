import java.util.Arrays;

public class Part1Bob {

    public static void main(String[] args) throws Exception {
        // get Key
        String key = Utils.read("AESKey.txt");
        //get the encrypted message
        String encodeStr = Utils.read("ctext.txt");
        AESUtils utils = new AESUtils(key, AESUtils.AES_192, AESUtils.AES_CBC_PKCS5Padding);
        System.out.println("turn into hexadecimal ：" + encodeStr);
        System.out.println("encryption byte array：" + Arrays.toString(Utils.hexStringToBytes(encodeStr)));
        byte[] decrypt = utils.decrypt(Utils.hexStringToBytes(encodeStr));
        String decodeStr = new String(decrypt, "UTF-8");
        System.out.println("Your Message is:：" + decodeStr);
    }
}
