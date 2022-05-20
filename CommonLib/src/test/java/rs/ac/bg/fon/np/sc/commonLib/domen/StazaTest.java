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
import rs.ac.bg.fon.np.sc.commonlib.domen.Staza;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiCentar;

/**
 *
 * @author UrosVesic
 */
public class StazaTest {

    private Staza odo;
    @Mock
    private ResultSet rs;

    @BeforeEach
    public void setUp() {
        odo = new Staza();
    }

    @AfterEach
    public void tearDown() {
        odo = null;
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/staza/vrati_vrednosti_atributa.csv")
    public void vratiVrednostiAtributaTest(String brojStaze, String nazivStaze, String tipStaze, Long sifraSkiCentra, String rezultat) {
        odo.setBrojStaze(brojStaze);
        odo.setNazivStaze(nazivStaze);
        odo.setTipStaze(tipStaze);
        if (sifraSkiCentra != null) {
            odo.setSkiCentar(new SkiCentar(sifraSkiCentra));
        }
        String str = odo.vratiVrednostiAtributa();
        Assertions.assertThat(str).isEqualTo(rezultat);
    }

    @Test
    public void vratiImenaAtrubitaTest() {
        Assertions.assertThat(odo.vratiImenaAtrubita()).isEqualTo("brojStaze, nazivStaze, tipStaze, sifraSkiCentra");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/staza/postavi_vrednosti_atributa.csv")
    public void postaviVrednostiAtributaTest(String brojStaze, String nazivStaze, String tipStaze, Long sifraSkiCentra, String rezultat) {
        odo.setBrojStaze(brojStaze);
        odo.setNazivStaze(nazivStaze);
        odo.setTipStaze(tipStaze);
        if (sifraSkiCentra != null) {
            odo.setSkiCentar(new SkiCentar(sifraSkiCentra));
        }
        String str = odo.postaviVrednostiAtributa();
        Assertions.assertThat(str).isEqualTo(rezultat);
    }

    @Test
    public void vratiImeKlaseTest() {
        Assertions.assertThat(odo.vratiImeKlase()).isEqualTo("Staza");
    }

    @Test
    public void vratiUslovZaNadjiSlogTest() {
        long id = 23;
        odo.setIdStaze(id);
        Assertions.assertThat(odo.vratiUslovZaNadjiSlog()).isEqualTo("idStaze = 23");
    }
    
    @Test
    public void vratiUslovZaNadjiSlogoveTest(){
        odo.setSkiCentar(new SkiCentar(0, "Kop", null, null));
        Assertions.assertThat(odo.vratiUslovZaNadjiSlogove()).isEqualTo("sifraSkiCentra = (SELECT sifraSkiCentra FROM skiCentar WHERE NazivSkiCentra LIKE '%Kop%')");
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
    public void napuniTest() throws Exception {
        AutoCloseable ac = MockitoAnnotations.openMocks(this);

        BDDMockito.given(rs.getLong("idStaze")).willReturn(1l);
        BDDMockito.given(rs.getString("brojStaze")).willReturn("5a");
        BDDMockito.given(rs.getString("nazivStaze")).willReturn("Krst");
        BDDMockito.given(rs.getString("tipStaze")).willReturn("Laka");
        BDDMockito.given(rs.getLong("sifraSkiCentra")).willReturn(2l);

        odo.napuni(rs);
        Assertions.assertThat(odo.getIdStaze()).isEqualTo(1);
        Assertions.assertThat(odo.getBrojStaze()).isEqualTo("5a");
        Assertions.assertThat(odo.getNazivStaze()).isEqualTo("Krst");
        Assertions.assertThat(odo.getTipStaze()).isEqualTo("Laka");
        Assertions.assertThat(odo.getSkiCentar().getSifraSkiCentra()).isEqualTo(2l);

        ac.close();
    }

    @Test
    public void vratiNazivPKTest() {
        Assertions.assertThat(odo.vratiNazivPK()).isEqualTo("brojStaze");
    }

    @Test
    public void kreirajInstancuTest() {
        Staza st = new Staza();
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
        Assertions.assertThat(odo.getIdStaze()).isEqualTo(id);
    }

}
