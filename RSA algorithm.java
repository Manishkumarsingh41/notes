import java.math.BigInteger;
import java.util.Random;

public class RSA {

    private BigInteger n, d, e;
    private int bitlength = 1024;
    private Random rand;

    public RSA() {
        rand = new Random();
        BigInteger p = BigInteger.probablePrime(bitlength / 2, rand);
        BigInteger q = BigInteger.probablePrime(bitlength / 2, rand);
        n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.valueOf(65537);
        d = e.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger plaintext) {
        return plaintext.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger ciphertext) {
        return ciphertext.modPow(d, n);
    }

    public BigInteger getPublicKey() {
        return e;
    }

    public BigInteger getModulus() {
        return n;
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();

        System.out.println("Public Key: ");
        System.out.println("e: " + rsa.getPublicKey());
        System.out.println("n: " + rsa.getModulus());

        String message = "Hello RSA!";
        System.out.println("\nOriginal Message: " + message);

        BigInteger plaintext = new BigInteger(message.getBytes());
        System.out.println("\nPlaintext as BigInteger: " + plaintext);

        BigInteger ciphertext = rsa.encrypt(plaintext);
        System.out.println("\nCiphertext: " + ciphertext);

        BigInteger decryptedText = rsa.decrypt(ciphertext);
        String decryptedMessage = new String(decryptedText.toByteArray());
        System.out.println("\nDecrypted Message: " + decryptedMessage);
    }
}


