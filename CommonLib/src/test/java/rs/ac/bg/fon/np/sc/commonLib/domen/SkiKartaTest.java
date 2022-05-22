/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.commonLib.domen;

import java.math.BigDecimal;
import java.sql.ResultSet;
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
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiKarta;
import rs.ac.bg.fon.np.sc.commonlib.domen.VrstaSkiKarte;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiCentar;

/**
 *
 * @author UrosVesic
 */
public class SkiKartaTest {

    private SkiKarta odo;
    @Mock
    private ResultSet rs;

    @BeforeEach
    public void setUp() {
        odo = new SkiKarta();
    }

    @AfterEach
    public void tearDown() {
        odo = null;
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/skikarta/vrati_vrednosti_atributa.csv")
    public void vratiVrednostiAtributaTest(String vrstaSkiKarte, BigDecimal cena, String sifraSkiCentra, String rezultat) {
        if (vrstaSkiKarte != null) {
            odo.setVrstaSkiKarte(VrstaSkiKarte.valueOf(vrstaSkiKarte));
        }
        if (cena != null) {
            odo.setCenaSkiKarte(cena);
        }
        if (sifraSkiCentra != null) {
            odo.setSkiCentar(new SkiCentar(Long.parseLong(sifraSkiCentra)));
        } else {
            odo.setSkiCentar(null);
        }
        String str = odo.vratiVrednostiAtributa();
        Assertions.assertThat(str).isEqualTo(rezultat);
    }

    @Test
    public void vratiImenaAtrubitaTest() {
        Assertions.assertThat(odo.vratiImenaAtrubita()).isEqualTo("vrstaSkiKarte, cenaSkiKarte, sifraSkiCentra");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/skikarta/postavi_vrednosti_atributa.csv")
    public void postaviVrednostiAtributaTest(String vrstaSkiKarte, BigDecimal cena, String sifraSkiCentra, String rezultat) {
        if (vrstaSkiKarte != null) {
            odo.setVrstaSkiKarte(VrstaSkiKarte.valueOf(vrstaSkiKarte));
        }
        if (cena != null) {
            odo.setCenaSkiKarte(cena);
        }
        if (sifraSkiCentra != null) {
            odo.setSkiCentar(new SkiCentar(Long.parseLong(sifraSkiCentra)));
        } else {
            odo.setSkiCentar(null);
        }
        String str = odo.postaviVrednostiAtributa();
        Assertions.assertThat(str).isEqualTo(rezultat);
    }

    @Test
    public void vratiImeKlaseTest() {
        Assertions.assertThat(odo.vratiImeTabeleZaKlasu()).isEqualTo("SkiKarta");
    }

    @Test
    public void vratiUslovZaNadjiSlogTest() {
        long id = 23;
        odo.setSifraSkiKarte(id);
        Assertions.assertThat(odo.vratiUslovZaNadjiSlog()).isEqualTo("sifraSkiKarte=  23");
    }

    @Test
    public void vratiUslovZaNadjiSlogoveTest() {
        odo.setCenaSkiKarte(new BigDecimal(2000));
        Assertions.assertThat(odo.vratiUslovZaNadjiSlogove()).isEqualTo("cenaSkiKarte <= 2000");
    }

    @ParameterizedTest
    @CsvSource({"0", "1"})
    public void vratiVezaniObjekatTest(int i) {
        SkiCentar sc = new SkiCentar(123);
        odo.setSkiCentar(sc);
        if (i == 0) {
            Assertions.assertThat(odo.vratiVezaniObjekat(i)).isEqualTo(sc);
        } else {
            Assertions.assertThat(odo.vratiVezaniObjekat(i)).isEqualTo(null);
        }
    }

    @ParameterizedTest
    @CsvSource({"0", "1"})
    public void postaviVrednostVezanogObjektaTest(int i) {
        SkiCentar sc = new SkiCentar(123);
        odo.postaviVrednostVezanogObjekta(sc, i);
        if (i == 0) {
            Assertions.assertThat(odo.vratiVezaniObjekat(i)).isEqualTo(sc);
        } else {
            Assertions.assertThat(odo.vratiVezaniObjekat(i)).isEqualTo(null);
        }
    }

    @Test
    public void vratiUslovZaPromeniSlogTest() {
        odo.setSifraSkiKarte(23);
        Assertions.assertThat(odo.vratiUslovZaPromeniSlog()).isEqualTo("sifraSkiKarte= 23");
    }

    @Test
    public void toStringTest() {
        odo.setSkiCentar(new SkiCentar(0, "Kopaonik", null, null));
        odo.setVrstaSkiKarte(VrstaSkiKarte.JEDNODNEVNA);
        Assertions.assertThat(odo.toString()).isEqualTo("Kopaonik JEDNODNEVNA");
    }

    @Test
    public void napuniTest() throws Exception {
        AutoCloseable ac = MockitoAnnotations.openMocks(this);

        BDDMockito.given(rs.getLong("sifraSkiKarte")).willReturn(1l);
        BDDMockito.given(rs.getString("vrstaSkiKarte")).willReturn("TRODNEVNA");
        BDDMockito.given(rs.getBigDecimal("cenaSkiKarte")).willReturn(new BigDecimal(150));
        BDDMockito.given(rs.getLong("sifraSkiCentra")).willReturn(2l);

        odo.napuni(rs);
        Assertions.assertThat(odo.getSifraSkiKarte()).isEqualTo(1);
        Assertions.assertThat(odo.getVrstaSkiKarte()).isEqualTo(VrstaSkiKarte.TRODNEVNA);
        Assertions.assertThat(odo.getCenaSkiKarte()).isEqualTo(new BigDecimal(150));
        Assertions.assertThat(odo.getSkiCentar().getSifraSkiCentra()).isEqualTo(2l);

        ac.close();
    }

    @Test
    public void vratiNazivPKTest() {
        Assertions.assertThat(odo.vratiNazivPK()).isEqualTo("sifraSkiKarte");
    }

    @Test
    public void kreirajInstancuTest() {
        SkiKarta sc = new SkiKarta();
        Assertions.assertThat(odo.kreirajInstancu()).isEqualTo(sc);
    }

    @Test
    public void vratiBrojVezanihObjekataTest() {
        Assertions.assertThat(odo.vratiBrojVezanihObjekata()).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource({"123", "321", "231"})
    public void postaviVrednostPKTest(long id) {
        odo.postaviVrednostPK(id);
        Assertions.assertThat(odo.getSifraSkiKarte()).isEqualTo(id);
    }

}
