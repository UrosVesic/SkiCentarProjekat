/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.commonLib.domen;

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
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiCentar;

/**
 *
 * @author UrosVesic
 */
public class SkiCentarTest {

    private SkiCentar odo;
    @Mock
    private ResultSet rs;

    @BeforeEach
    public void setUp() {
        odo = new SkiCentar();
    }

    @AfterEach
    public void tearDown() {
        odo = null;
    }

    //'Kopaonik', 'Kopaonik', '09-17'
    @ParameterizedTest
    @CsvFileSource(resources = "/skicentar/vrati_vrednosti_atributa.csv")
    public void vratiVrednostiAtributaTest(String nazivSkiCentra, String nazivPlanine, String radnoVreme, String rezultat) {
        odo.setNazivSkiCentra(nazivSkiCentra);
        odo.setNazivPlanine(nazivPlanine);
        odo.setRadnoVreme(radnoVreme);
        String str = odo.vratiVrednostiAtributa();
        Assertions.assertThat(str).isEqualTo(rezultat);
    }

    @Test
    public void vratiImenaAtrubitaTest() {
        Assertions.assertThat(odo.vratiImenaAtrubita()).isEqualTo("nazivSkiCentra, nazivPlanine, radnoVreme");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/skicentar/postavi_vrednosti_atributa.csv")
    public void postaviVrednostiAtributaTest(String nazivSkiCentra, String nazivPlanine, String radnoVreme, String rezultat) {
        odo.setNazivSkiCentra(nazivSkiCentra);
        odo.setNazivPlanine(nazivPlanine);
        odo.setRadnoVreme(radnoVreme);
        String str = odo.postaviVrednostiAtributa();
        Assertions.assertThat(str).isEqualTo(rezultat);
    }

    @Test
    public void vratiImeKlaseTest() {
        Assertions.assertThat(odo.vratiImeKlase()).isEqualTo("SkiCentar");
    }

    @Test
    public void vratiUslovZaNadjiSlogTest() {
        long id = 23;
        odo.setSifraSkiCentra(id);
        Assertions.assertThat(odo.vratiUslovZaNadjiSlog()).isEqualTo("sifraSkiCentra= 23");
    }

    @Test
    public void napuniTest() throws Exception {
        AutoCloseable ac = MockitoAnnotations.openMocks(this);

        BDDMockito.given(rs.getLong("sifraSkiCentra")).willReturn(1l);
        BDDMockito.given(rs.getString("nazivSkiCentra")).willReturn("Kopaonik");
        BDDMockito.given(rs.getString("nazivPlanine")).willReturn("Kopaonik");
        BDDMockito.given(rs.getString("radnoVreme")).willReturn("09-17");

        odo.napuni(rs);
        Assertions.assertThat(odo.getSifraSkiCentra()).isEqualTo(1);
        Assertions.assertThat(odo.getNazivSkiCentra()).isEqualTo("Kopaonik");
        Assertions.assertThat(odo.getNazivPlanine()).isEqualTo("Kopaonik");
        Assertions.assertThat(odo.getRadnoVreme()).isEqualTo("09-17");

        ac.close();
    }

    @Test
    public void vratiNazivPKTest() {
        Assertions.assertThat(odo.vratiNazivPK()).isEqualTo("sifraSkiCentra");
    }

    @Test
    public void kreirajInstancuTest() {
        SkiCentar sc = new SkiCentar();
        Assertions.assertThat(odo.kreirajInstancu()).isEqualTo(sc);
    }

    @Test
    public void vratiBrojVezanihObjekataTest() {
        Assertions.assertThat(odo.vratiBrojVezanihObjekata()).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource({"123", "321", "231"})
    public void postaviVrednostPKTest(long id) {
        odo.postaviVrednostPK(id);
        Assertions.assertThat(odo.getSifraSkiCentra()).isEqualTo(id);
    }

}
