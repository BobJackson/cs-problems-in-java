package csproblem.injava.chapter1;

import java.util.BitSet;

public class CompressedGene {
    private BitSet bitSet;
    private int length;

    public CompressedGene(String gene) {
        compress(gene);
    }

    private void compress(String gene) {
        length = gene.length();
        bitSet = new BitSet(length * 2);
        final String upperGene = gene.toUpperCase();
        for (int i = 0; i < length; i++) {
            final int firstLocation = 2 * i;
            final int secondLocation = 2 * i + 1;
            switch (upperGene.charAt(i)) {
                case 'A' -> {
                    bitSet.set(firstLocation, false);
                    bitSet.set(secondLocation, false);
                }
                case 'C' -> {
                    bitSet.set(firstLocation, false);
                    bitSet.set(secondLocation, true);
                }
                case 'G' -> {
                    bitSet.set(firstLocation, true);
                    bitSet.set(secondLocation, false);
                }
                case 'T' -> {
                    bitSet.set(firstLocation, true);
                    bitSet.set(secondLocation, true);
                }
                default ->
                        throw new IllegalArgumentException("The provided gene String contains characters other than ACGT");
            }
        }
    }

    public String decompress() {
        if (bitSet == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < (length * 2); i += 2) {
            final int firstBit = bitSet.get(i) ? 1 : 0;
            final int secondBit = bitSet.get(i + 1) ? 1 : 0;
            final int lastBits = firstBit << 1 | secondBit;
            switch (lastBits) {
                case 0b00 -> builder.append("A");
                case 0b01 -> builder.append("C");
                case 0b10 -> builder.append("G");
                case 0b11 -> builder.append("T");
                default -> throw new IllegalStateException("in an error state");
            }
        }
        return builder.toString();
    }
}
