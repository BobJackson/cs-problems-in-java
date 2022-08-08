package csproblem.injava.chapter1;

import java.util.Random;


/*
 * C = A ^ B
 * A = C ^ B
 * B = C ^ A
 */
public class UnbreakableEncryption {

    public static KeyPair encrypt(String original) {
        byte[] originalBytes = original.getBytes();
        byte[] dummyKey = randomKey(originalBytes.length);
        byte[] encryptedKey = new byte[originalBytes.length];
        for (int i = 0; i < originalBytes.length; i++) {
            encryptedKey[i] = (byte) (originalBytes[i] ^ dummyKey[i]);
        }
        return new KeyPair(dummyKey, encryptedKey);
    }

    public static String decrypt(KeyPair kp) {
        byte[] decrypted = new byte[kp.lenOfKey()];
        for (int i = 0; i < kp.lenOfKey(); i++) {
            decrypted[i] = (byte) (kp.getDummyKey()[i] ^ kp.getOriginalKey()[i]);
        }
        return new String(decrypted);
    }

    private static byte[] randomKey(int length) {
        byte[] dummy = new byte[length];
        Random random = new Random();
        random.nextBytes(dummy);
        return dummy;
    }

}
