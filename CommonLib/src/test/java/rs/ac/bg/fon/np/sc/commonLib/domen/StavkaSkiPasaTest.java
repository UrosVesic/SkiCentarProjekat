/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.commonLib.domen;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Calendar;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.np.sc.commonlib.domen.StavkaSkiPasa;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiPas;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiKarta;
import rs.ac.bg.fon.np.sc.commonlib.domen.VrstaSkiKarte;

/**
 *
 * @author UrosVesic
 */
public class StavkaSkiPasaTest {

    private StavkaSkiPasa odo;
    @Mock
    private ResultSet rs;

    @BeforeEach
    public void setUp() {
        odo = new StavkaSkiPasa();
    }

    @AfterEach
    public void tearDown() {
        odo = null;
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/stavkaskipasa/vrati_vrednosti_atributa.csv")
    public void vratiVrednostiAtributaTest(String sifraSkiPasa, String rb, BigDecimal cena, Date pocetakVazenja, Date zavrsetakVazenja, String sifraSkiKarte, String rezultat) {
        if (sifraSkiPasa != null) {
            odo.setSkiPas(new SkiPas(Long.parseLong(sifraSkiPasa)));
        }
        if (rb != null) {
            odo.setRedniBroj(Long.parseLong(rb));
        }
        odo.setVrednostStavke(cena);
        odo.setPocetakVazenja(pocetakVazenja);
        odo.setZavrsetakVazenja(zavrsetakVazenja);
        if (sifraSkiKarte != null) {
            odo.setSkiKarta(new SkiKarta(Long.parseLong(sifraSkiKarte)));
        }
        String str = odo.vratiVrednostiAtributa();
        Assertions.assertThat(str).isEqualTo(rezultat);
    }

    @Test
    public void vratiImenaAtrubitaTest() {
        Assertions.assertThat(odo.vratiImenaAtrubita()).isEqualTo("sifraSkiPasa, rb, vrednostStavke, pocetakVazenja, zavrsetakVazenja, sifraSkiKarte");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/stavkaskipasa/postavi_vrednosti_atributa.csv")
    public void postaviVrednostiAtributaTest(String sifraSkiPasa, String rb, BigDecimal cena, Date pocetakVazenja, Date zavrsetakVazenja, String sifraSkiKarte, String rezultat) {
        if (sifraSkiPasa != null) {
            odo.setSkiPas(new SkiPas(Long.parseLong(sifraSkiPasa)));
        }
        if (rb != null) {
            odo.setRedniBroj(Long.parseLong(rb));
        }
        odo.setVrednostStavke(cena);
        odo.setPocetakVazenja(pocetakVazenja);
        odo.setZavrsetakVazenja(zavrsetakVazenja);
        if (sifraSkiKarte != null) {
            odo.setSkiKarta(new SkiKarta(Long.parseLong(sifraSkiKarte)));
        }

        String str = odo.postaviVrednostiAtributa();
        Assertions.assertThat(str).isEqualTo(rezultat);
    }

    @Test
    public void vratiImeKlaseTest() {
        Assertions.assertThat(odo.vratiImeTabeleZaKlasu()).isEqualTo("StavkaSkiPasa");
    }

    @Test
    //"sifraSkiPasa = " + skiPas.getSifraSkiPasa() + " AND " + "RB = " + redniBroj;
    public void vratiUslovZaNadjiSlogTest() {
        long id = 23;
        long rb = 24;
        odo.setSkiPas(new SkiPas(id));
        odo.setRedniBroj(rb);
        Assertions.assertThat(odo.vratiUslovZaNadjiSlog()).isEqualTo("sifraSkiPasa = 23 AND RB = 24");
    }

    @Test
    public void vratiUslovZaNadjiSlogoveTest() {
        odo.setSkiPas(new SkiPas(23));
        Assertions.assertThat(odo.vratiUslovZaNadjiSlogove()).isEqualTo("sifraSkiPasa = 23");
    }

    @Test
    public void generisiDatumZavrsetkaTest() {
        odo.setPocetakVazenja(new java.util.Date());
        SkiKarta sk = new SkiKarta();
        sk.setVrstaSkiKarte(VrstaSkiKarte.DVODNEVNA);
        odo.setSkiKarta(sk);
        Assertions.assertThat(odo.generisiDatumZavrsetka()).isEqualTo(odo.getZavrsetakVazenja());
    }

    @ParameterizedTest
    @CsvSource({"0", "1"})
    public void vratiVezaniObjekatTest(int i) {
        SkiKarta sk = new SkiKarta(123);
        odo.setSkiKarta(sk);
        if (i == 0) {
            Assertions.assertThat(odo.vratiVezaniObjekat(i)).isEqualTo(sk);
        } else {
            Assertions.assertThat(odo.vratiVezaniObjekat(i)).isEqualTo(null);
        }
    }

    @ParameterizedTest
    @CsvSource({"0", "1"})
    public void postaviVrednostVezanogObjektaTest(int i) {
        SkiKarta sk = new SkiKarta(123);
        odo.postaviVrednostVezanogObjekta(sk, i);
        if (i == 0) {
            Assertions.assertThat(odo.vratiVezaniObjekat(i)).isEqualTo(sk);
        } else {
            Assertions.assertThat(odo.vratiVezaniObjekat(i)).isEqualTo(null);
        }
    }

    @Test
    public void napuniTest() throws Exception {
        AutoCloseable ac = MockitoAnnotations.openMocks(this);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        java.util.Date pocetakVazenja = cal.getTime();
        java.util.Date zavrsetakVazenja = cal.getTime();
        odo.setSkiPas(new SkiPas());

        BDDMockito.given(rs.getLong("sifraSkiPasa")).willReturn(1l);
        BDDMockito.given(rs.getLong("RB")).willReturn(12l);
        BDDMockito.given(rs.getDate("pocetakVazenja")).willReturn(new Date(0));
        BDDMockito.given(rs.getDate("zavrsetakVazenja")).willReturn(new Date(0));
        BDDMockito.given(rs.getBigDecimal("vrednostStavke")).willReturn(new BigDecimal(150));
        BDDMockito.given(rs.getLong("sifraSkiKarte")).willReturn(2l);

        odo.napuni(rs);
        Assertions.assertThat(odo.getSkiPas().getSifraSkiPasa()).isEqualTo(1);
        Assertions.assertThat(odo.getRedniBroj()).isEqualTo(12);
        Assertions.assertThat(odo.getPocetakVazenja()).isEqualTo(pocetakVazenja);
        Assertions.assertThat(odo.getZavrsetakVazenja()).isEqualTo(zavrsetakVazenja);
        Assertions.assertThat(odo.getSkiKarta().getSifraSkiKarte()).isEqualTo(2l);
        Assertions.assertThat(odo.getVrednostStavke()).isEqualTo(new BigDecimal(150));

        ac.close();
    }

    /*@Test
    public void vratiNazivPKTest() {
        Assertions.assertThat(odo.vratiNazivPK()).isEqualTo("sifraSkiPasa");
    }*/
    @Test
    public void kreirajInstancuTest() {
        StavkaSkiPasa st = new StavkaSkiPasa();
        Assertions.assertThat(odo.kreirajInstancu()).isEqualTo(st);
    }

    @Test
    public void vratiBrojVezanihObjekataTest() {
        Assertions.assertThat(odo.vratiBrojVezanihObjekata()).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource({"123", "321", "231"})
    public void postaviVrednostPKTest(long id) {
        odo.postaviVrednostPK(id);
        Assertions.assertThat(odo.getRedniBroj()).isEqualTo(id);
    }

}
