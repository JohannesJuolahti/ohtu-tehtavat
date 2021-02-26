package ohtu.ohtuvarasto;

import org.junit.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Before
    public void setUpWithTwo() {
        Varasto varasto = new Varasto(10, 0);
    }

    @Test
    public void tuplaKonstruktoriLuoTyhjänvarastonJaNegatiivinenAlkusaldoEiToimi() {
        Varasto testivarasto = new Varasto(0, -1);
        assertEquals(0, testivarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, testivarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldoEnemmanKuinTilavuus() {
        Varasto testivarasto = new Varasto(1, 1.1);
        assertEquals(1, testivarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otetaanNegatiivinenJaliikaa() {
        Varasto testivarasto = new Varasto(1, 1);
        testivarasto.otaVarastosta(-1);
        assertEquals(1, testivarasto.getSaldo(), vertailuTarkkuus);
        testivarasto.otaVarastosta(1.1);
        assertEquals(0, testivarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLuoTyhjän() {
        Varasto testivarasto = new Varasto(-1);
        assertEquals(0, testivarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisataanLiianPaljonJaNegatiivinen() {
        Varasto testivarasto = new Varasto(1, 0);
        testivarasto.lisaaVarastoon(1.1);
        assertEquals(1, testivarasto.getSaldo(), vertailuTarkkuus);
        testivarasto.lisaaVarastoon(-1);
        assertEquals(1, testivarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void testToString() {
        assertEquals("saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu(), varasto.toString());
    }


}