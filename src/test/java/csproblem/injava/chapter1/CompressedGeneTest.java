package csproblem.injava.chapter1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CompressedGeneTest {

    private CompressedGene compressedGene;
    private String original;

    @BeforeEach
    void setUp() {
        original = "TACGGGCTAGCTATTACG";
        compressedGene = new CompressedGene(original);
    }

    @Test
    void should_decompressed_gene_string() {
        String decompressed = compressedGene.decompress();
        System.out.println(decompressed);

        Assertions.assertEquals(original, decompressed);

    }
}