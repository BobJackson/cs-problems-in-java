package csproblem.injava.chapter1;

public class KeyPair {
    private final byte[] dummyKey;
    private final byte[] originalKey;

    public KeyPair(byte[] dummyKey, byte[] originalKey) {
        this.originalKey = originalKey;
        this.dummyKey = dummyKey;
    }

    public int lenOfKey() {
        return dummyKey.length;
    }

    public byte[] getDummyKey() {
        return dummyKey;
    }

    public byte[] getOriginalKey() {
        return originalKey;
    }
}
