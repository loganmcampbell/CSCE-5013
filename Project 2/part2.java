
import java.io.DataInputStream;

import java.io.IOException;

import java.math.BigInteger;

import java.util.Random;



public class part2

{
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;
    private int bit = 2048;
    private Random rand;

    public part2()
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
    public part2(BigInteger e, BigInteger d, BigInteger N)
    {
        this.e = e;
        this.d = d;
        this.n = n;
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException
    {
        part2 rsa = new part2();
        DataInputStream in = new DataInputStream(System.in);
        String inputstring;
        System.out.println("Enter the 18 BYTE STRING : ");
        inputstring = in.readLine();

        System.out.println("Encrypting : " + inputstring);
        System.out.println("Bytes["+ inputstring + "] : "
                + bytesToString(inputstring.getBytes()));
        // encrypt
        byte[] encrypt = rsa.encrypt(inputstring.getBytes());
        // decrypt
        byte[] decrypt = rsa.decrypt(encrypt);
        System.out.println("# of Bytes (Message) : " + inputstring.length() + " BYTES.");
        System.out.println("Bytes[" + bytesToString(decrypt) + "]");
        System.out.println("Decrypt(Encrypt): " + rsa.decrypt(encrypt));
        System.out.println("Decrypting : " + new String(decrypt));
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

    // Encrypt message
    public byte[] encrypt(byte[] message)

    {
        return (new BigInteger(message)).modPow(e, n).toByteArray();
    }
    // Decrypt message
    public byte[] decrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(d, n).toByteArray();
    }
}
