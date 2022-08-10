package csproblem.injava.chapter2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneTest {

    private Gene gene;

    @BeforeEach
    void setUp() {
        gene = new Gene("ACGTGGCTCTCTAACGTACGTACGTACGGGGTTTATATATACCCTAGGACTCCCTTT");
    }

    @Test
    void should_contains_acg() {
        Gene.Codon acg = new Gene.Codon("ACG");
        assertTrue(gene.linearContains(acg));
    }

    @Test
    void should_not_contains_gat() {
        Gene.Codon gat = new Gene.Codon("GAT");
        assertFalse(gene.linearContains(gat));
    }

}