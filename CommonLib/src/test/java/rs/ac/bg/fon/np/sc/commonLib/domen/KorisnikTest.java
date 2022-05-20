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
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.np.sc.commonlib.domen.Korisnik;

/**
 *
 * @author UrosVesic
 */
public class KorisnikTest {

    private Korisnik odo;
    @Mock
    private ResultSet rs;

    @BeforeEach
    public void setUp() {
        odo = new Korisnik();
    }

    @AfterEach
    public void tearDown() {
        odo = null;
    }

    @Test
    public void vratiImenaAtributaTest() {
        Assertions.assertThat(odo.vratiImenaAtrubita()).isEqualTo("ime, prezime, email, sifra");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/korisnik/vrati_vrednosti_atributa.csv")
    public void vratiVrednostiAtributaTest(String email, String sifra, String ime, String prezime, String rezultat) {
        Korisnik k = new Korisnik(ime, prezime, email, sifra);
        Assertions.assertThat(k.vratiVrednostiAtributa()).isEqualTo(rezultat);

    }

    @Test
    public void postaviVrednostiAtributaTest() {
        Assertions.assertThatThrownBy(() -> odo.postaviVrednostiAtributa()).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void vratiImeKlaseTest() {
        Assertions.assertThat(odo.vratiImeKlase()).isEqualTo("Korisnik");
    }

    @Test
    public void vratiUslovZaNadjiSlogTest() {
        odo = new Korisnik(null, null, "uros@uros.com", "uros1234");
        Assertions.assertThat(odo.vratiUslovZaNadjiSlog()).isEqualTo("email LIKE'uros@uros.com' AND sifra LIKE 'uros1234'");
    }
    
    @Test
    public void postaviVrednostPKTest() {
        odo.postaviVrednostPK(25);
        Assertions.assertThat(odo.getId()).isEqualTo(25);
    }
    
    @Test
    public void kreirajInstancuTest() {
        Korisnik k = new Korisnik();
        Assertions.assertThat(odo.kreirajInstancu()).isEqualTo(k);
    }
    
     @Test
    public void vratiImenaAtrubitaTest() {
        Assertions.assertThat(odo.vratiImenaAtrubita()).isEqualTo("ime, prezime, email, sifra");
    }
    
    @Test
    public void napuniTest() throws Exception{
         AutoCloseable ac = MockitoAnnotations.openMocks(this);

        BDDMockito.given(rs.getLong("id")).willReturn(1l);
        BDDMockito.given(rs.getString("ime")).willReturn("Uros");
        BDDMockito.given(rs.getString("prezime")).willReturn("Vesic");
        BDDMockito.given(rs.getString("email")).willReturn("uros@uros.com");
        BDDMockito.given(rs.getString("sifra")).willReturn("uros99");

        odo.napuni(rs);
        Assertions.assertThat(odo.getId()).isEqualTo(1);
        Assertions.assertThat(odo.getIme()).isEqualTo("Uros");
        Assertions.assertThat(odo.getPrezime()).isEqualTo("Vesic");
        Assertions.assertThat(odo.getEmail()).isEqualTo("uros@uros.com");
        Assertions.assertThat(odo.getSifra()).isEqualTo("uros99");

        ac.close();
    }

}
