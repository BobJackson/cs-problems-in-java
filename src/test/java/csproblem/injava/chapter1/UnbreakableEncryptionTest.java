package csproblem.injava.chapter1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnbreakableEncryptionTest {

    @Test
    void should_encrypt_original_string() {
        String original = "Hello World";
        KeyPair keyPair = UnbreakableEncryption.encrypt(original);
        String decrypted = UnbreakableEncryption.decrypt(keyPair);
        Assertions.assertEquals(original, decrypted);
    }
}