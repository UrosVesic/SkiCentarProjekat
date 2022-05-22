/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.commonLib.domen;

import java.sql.ResultSet;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.BDDAssumptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.np.sc.commonlib.domen.Kupac;

/**
 *
 * @author UrosVesic
 */
public class KupacTest {

    @Mock
    ResultSet rs;
    Kupac odo;

    @BeforeEach
    public void setUp() {
        odo = new Kupac();
    }

    @AfterEach
    public void tearDown() {
        odo = null;
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/kupac/vrati_vrednosti_atributa.csv")
    public void vratiVrednostiAtributaTest(String brojLK, String ime, String prezime, String rezultat) {
        odo.setBrojLK(brojLK);
        odo.setIme(ime);
        odo.setPrezime(prezime);
        String str = odo.vratiVrednostiAtributa();
        Assertions.assertThat(str).isEqualTo(rezultat);
    }

    @Test
    public void vratiImenaAtrubitaTest() {
        Assertions.assertThat(odo.vratiImenaAtrubita()).isEqualTo("brojLK, Ime, Prezime");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/kupac/postavi_vrednosti_atributa.csv")
    public void postaviVrednostiAtributaTest(String brojLK, String ime, String prezime, String rezultat) {
        odo.setBrojLK(brojLK);
        odo.setIme(ime);
        odo.setPrezime(prezime);
        String str = odo.postaviVrednostiAtributa();
        Assertions.assertThat(str).isEqualTo(rezultat);
    }

    @Test
    public void vratiImeKlaseTest() {
        Assertions.assertThat(odo.vratiImeTabeleZaKlasu()).isEqualTo("Kupac");
    }

    @Test
    public void vratiUslovZaNadjiSlogTest() {
        long id = 23;
        odo.setIdKupca(id);
        Assertions.assertThat(odo.vratiUslovZaNadjiSlog()).isEqualTo("idKupca= 23");
    }

    @Test
    public void napuniTest() throws Exception {
        AutoCloseable ac = MockitoAnnotations.openMocks(this);

        BDDMockito.given(rs.getLong("idKupca")).willReturn(1l);
        BDDMockito.given(rs.getString("brojLK")).willReturn("123456");
        BDDMockito.given(rs.getString("ime")).willReturn("Uros");
        BDDMockito.given(rs.getString("prezime")).willReturn("Vesic");

        odo.napuni(rs);
        Assertions.assertThat(odo.getIdKupca()).isEqualTo(1);
        Assertions.assertThat(odo.getBrojLK()).isEqualTo("123456");
        Assertions.assertThat(odo.getIme()).isEqualTo("Uros");
        Assertions.assertThat(odo.getPrezime()).isEqualTo("Vesic");

        ac.close();
    }

    @Test
    public void vratiNazivPKTest() {
        Assertions.assertThat(odo.vratiNazivPK()).isEqualTo("idKupca");
    }

    @Test
    public void kreirajInstancuTest() {
        Kupac k = new Kupac();
        Assertions.assertThat(odo.kreirajInstancu()).isEqualTo(k);
    }

    @Test
    public void vratiBrojVezanihObjekataTest() {
        Assertions.assertThat(odo.vratiBrojVezanihObjekata()).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource({"123", "321", "231"})
    public void postaviVrednostPKTest(long id) {
        odo.postaviVrednostPK(id);
        Assertions.assertThat(odo.getIdKupca()).isEqualTo(id);
    }

    @Test
    public void toStringTest() {
        odo.setIme("Uros");
        odo.setPrezime("Vesic");
        Assertions.assertThat(odo.toString()).isEqualTo("Uros Vesic");

    }

}
