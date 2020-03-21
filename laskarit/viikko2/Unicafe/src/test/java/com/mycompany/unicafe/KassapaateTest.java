package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KassapaateTest {

    Maksukortti koyhaKortti;
    Maksukortti kortti;
    Kassapaate kassa;

    @Before
    public void setUp() {
        koyhaKortti = new Maksukortti(10);
        kortti = new Maksukortti(400);
        kassa = new Kassapaate();
    }

    @Test
    public void luodullaKassapaatteellaOikeaRahamaara() {
        assertEquals(1000, kassa.kassassaRahaa());
    }

    @Test
    public void luodullaKassapaatteellaEiMyytyjaEdullisiaLounaita() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void luodullaKassapaatteellaEiMyytyjaMaukkaitaLounaita() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    // Käteisosto
    @Test
    public void edullinenKateisostoKasvattaaRahamaaraaEdullisenHinnalla() {
        kassa.syoEdullisesti(500);
        assertEquals(1240, kassa.kassassaRahaa());
    }

    @Test
    public void maukasKateisostoKasvattaaRahamaaraaMaukkaanHinnalla() {
        kassa.syoMaukkaasti(500);
        assertEquals(1400, kassa.kassassaRahaa());
    }

    @Test
    public void edullinenKateisostoPalauttaaOnnistuessaanSopivastiVaihtorahaa() {
        int vaihtoraha = kassa.syoEdullisesti(300);
        assertEquals(60, vaihtoraha);
    }

    @Test
    public void maukasKateisostoPalauttaaOnnistuessaanSopivastiVaihtorahaa() {
        int vaihtoraha = kassa.syoMaukkaasti(500);
        assertEquals(100, vaihtoraha);
    }

    @Test
    public void edullinenKateisostoKasvattaaMyytyjenEdullistenMaaraa() {
        kassa.syoEdullisesti(300);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukasKateisostoKasvattaaMyytyjenMaukkaidenMaaraa() {
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void epaonnistunutEdullinenKateisostoEiVaikutaRahamaaraan() {
        kassa.syoEdullisesti(10);
        assertEquals(1000, kassa.kassassaRahaa());
    }

    @Test
    public void epaonnistunutMaukasKateisostoEiVaikutaRahamaaraan() {
        kassa.syoMaukkaasti(10);
        assertEquals(1000, kassa.kassassaRahaa());
    }

    @Test
    public void epaonnistunutEdullinenKateisostoPalauttaaKaikenVaihtorahana() {
        int vaihtoraha = kassa.syoEdullisesti(10);
        assertEquals(10, vaihtoraha);
    }

    @Test
    public void epaonnistunutMaukasKateisostoPalauttaaKaikenVaihtorahana() {
        int vaihtoraha = kassa.syoMaukkaasti(10);
        assertEquals(10, vaihtoraha);
    }

    @Test
    public void epaonnistunutEdullinenKateisostoEiVaikutaMyytyjenEdullistenMaaraan() {
        kassa.syoEdullisesti(10);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void epaonnistunutMaukasKateisostoEiVaikutaMyytyjenMaukkaidenMaaraan() {
        kassa.syoMaukkaasti(10);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    // Korttiosto
    @Test
    public void edullinenKorttiostoVahentaaKortiltaEdullisenHinnan() {
        kassa.syoEdullisesti(kortti);
        assertEquals(160, kortti.saldo());
    }

    @Test
    public void maukasKorttiostoVahentaaKortiltaMaukkaanHinnan() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kortti.saldo());
    }

    @Test
    public void edullinenKorttiostoPalauttaaOnnistuessaanTrue() {
        assertTrue(kassa.syoEdullisesti(kortti));
    }

    @Test
    public void maukasKorttiostoPalauttaaOnnistuessaanTrue() {
        assertTrue(kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void edullinenKorttiostoKasvattaaMyytyjenEdullistenMaaraa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukasKorttiostoKasvattaaMyytyjenMaukkaidenMaaraa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void epaonnistunutEdullinenKorttiostoEiVaikutaKortinSaldoon() {
        int saldoEnnen = koyhaKortti.saldo();
        kassa.syoEdullisesti(koyhaKortti);
        assertEquals(saldoEnnen, koyhaKortti.saldo());
    }

    @Test
    public void epaonnistunutMaukasKorttiostoEiVaikutaKortinSaldoon() {
        int saldoEnnen = koyhaKortti.saldo();
        kassa.syoMaukkaasti(koyhaKortti);
        assertEquals(saldoEnnen, koyhaKortti.saldo());
    }

    @Test
    public void epaonnistunutEdullinenKorttiostoPalauttaaFalse() {
        assertFalse(kassa.syoEdullisesti(koyhaKortti));
    }

    @Test
    public void epaonnistunutMaukasKorttiostoPalauttaaFalse() {
        assertFalse(kassa.syoMaukkaasti(koyhaKortti));
    }

    @Test
    public void epaonnistunutEdullinenKorttiostoEiVaikutaMyytyjenEdullistenMaaraan() {
        kassa.syoEdullisesti(koyhaKortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void epaonnistunutMaukasKorttiostoEiVaikutaMyytyjenMaukkaidenMaaraan() {
        kassa.syoMaukkaasti(koyhaKortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void onnistunutEdullinenKorttiostoEiVaikutaKassanRahamaaraan() {
        int rahaEnnen = kassa.kassassaRahaa();
        kassa.syoEdullisesti(kortti);
        assertEquals(rahaEnnen, kassa.kassassaRahaa());
    }

    @Test
    public void onnistunutMaukasKorttiostoEiVaikutaKassanRahamaaraan() {
        int rahaEnnen = kassa.kassassaRahaa();
        kassa.syoMaukkaasti(kortti);
        assertEquals(rahaEnnen, kassa.kassassaRahaa());
    }

    // Kortin lataus
    @Test
    public void kortinLatausMuuttaaKortinSaldoaLadatullaMaaralla() {
        int saldoEnnen = kortti.saldo();
        int ladattavaMaara = 100;
        kassa.lataaRahaaKortille(kortti, ladattavaMaara);
        assertEquals(saldoEnnen + ladattavaMaara, kortti.saldo());
    }

    @Test
    public void kortinLatausKasvattaaKassanRahamaaraaLadatullaMaaralla() {
        int maaraEnnen = kassa.kassassaRahaa();
        int ladattavaMaara = 100;
        kassa.lataaRahaaKortille(kortti, ladattavaMaara);
        assertEquals(maaraEnnen + ladattavaMaara, kassa.kassassaRahaa());
    }

    @Test
    public void kortinLatausNegatiivisellaSummallaEiVaikutaKortinSaldoon() {
        int saldoEnnen = kortti.saldo();
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(saldoEnnen, kortti.saldo());
    }

    @Test
    public void kortinLatausNegatiivisellaSummallaEiVaikutaKassanRahamaaraan() {
        int maaraEnnen = kassa.kassassaRahaa();
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(maaraEnnen, kassa.kassassaRahaa());
    }
}