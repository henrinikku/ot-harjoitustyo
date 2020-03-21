package com.mycompany.unicafe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertNotNull(kortti);
    }

    @Test
    public void toStringPalauttaaMerkkijonon() {
        // Käytin testeissä suoraan kortin saldotietoa, .toString jäi testaamatta
        assertNotNull(kortti.toString());
    }

    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(5);
        assertEquals(15, kortti.saldo());
    }

    @Test
    public void saldoVaheneeOikeinJosRahaaRiittavasti() {
        kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());
    }

    @Test
    public void saldoEiMuutuJosRahaaEiRiittavasti() {
        kortti.otaRahaa(15);
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void onnistunutOttoPalauttaaTrue() {
        assertTrue(kortti.otaRahaa(5));
    }

    @Test
    public void epannistunutOttoPalauttaaFalse() {
        assertFalse(kortti.otaRahaa(15));
    }
}
