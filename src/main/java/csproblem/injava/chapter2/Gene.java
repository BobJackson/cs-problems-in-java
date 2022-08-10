package csproblem.injava.chapter2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Gene {
    private final List<Codon> codons = new ArrayList<>();

    public Gene(String geneStr) {
        for (int i = 0; i < geneStr.length() - 3; i += 3) {
            codons.add(new Codon(geneStr.substring(i, i + 3)));
        }
    }

    public boolean linearContains(Codon key) {
        return GenericSearch.linearContains(codons, key);
    }

    public boolean binaryContains(Codon key) {
        return GenericSearch.binaryContains(codons, key);
    }

    public enum Nucleotide {
        A, C, G, T
    }

    public static class Codon implements Comparable<Codon> {
        public final Nucleotide first;
        public final Nucleotide second;
        public final Nucleotide third;

        private final Comparator<Codon> comparator = Comparator.comparing((Codon c) -> c.first)
                .thenComparing((Codon c) -> c.second)
                .thenComparing((Codon c) -> c.third);

        public Codon(String codonStr) {
            this.first = Nucleotide.valueOf(codonStr.substring(0, 1));
            this.second = Nucleotide.valueOf(codonStr.substring(1, 2));
            this.third = Nucleotide.valueOf(codonStr.substring(2, 3));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Codon codon = (Codon) o;
            return first == codon.first && second == codon.second && third == codon.third;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second, third);
        }

        @Override
        public int compareTo(Codon other) {
            return comparator.compare(this, other);
        }
    }
}
