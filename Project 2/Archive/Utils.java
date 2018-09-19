import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Utils {
	/**
	 * Convert an array to a hex string
	 * @param bArray
	 * @return
	 */
	public static final String bytesToHexString(byte[] bArray) {
		if (bArray == null || bArray.length == 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2) {
				sb.append(0);
			}
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * Convert hex string to byte[]
	 *
	 * @param hexString the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 *
	 * @param c char
	 * @return byte
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	/**
	 * read data
	 * @param fileName 
	 * @return
	 */
    public static String read(String fileName){
        File file = new File(fileName);
        FileReader fr = null;
        BufferedReader br = null;
        String key = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            key = br.readLine();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(br != null)
                    br.close();
                if(fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return key;
    }

    /**
     * 
     * @param fileName 
     * @param content 
     * @throws IOException
     */
    public static void save(String fileName, String content) throws IOException {
        File file = new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
            pw.print(content);
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(pw != null)
                pw.close();
        }
    }
}
