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

    @Test
    void should_contains_acg_when_binary_search() {
        Gene.Codon acg = new Gene.Codon("ACG");
        assertTrue(gene.binaryContains(acg));
    }

    @Test
    void should_not_contains_gat_when_binary_search() {
        Gene.Codon gat = new Gene.Codon("GAT");
        assertFalse(gene.binaryContains(gat));
    }

}