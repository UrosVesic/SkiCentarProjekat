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

/**
 *
 * @author UrosVesic
 */
public class SkiPasTest {

    private SkiPas odo;
    @Mock
    private ResultSet rs;

    @BeforeEach
    public void setUp() {
        odo = new SkiPas();
    }

    @AfterEach
    public void tearDown() {
        odo = null;
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/skipas/vrati_vrednosti_atributa.csv")
    public void vratiVrednostiAtributaTest(BigDecimal ukupnaCena, String idKupca, Date datumIzdavanja, String sezona, String rezultat) {
        odo.setUkupnaCena(ukupnaCena);
        if (idKupca != null) {
            odo.setKupac(new Kupac(Long.parseLong(idKupca)));
        }
        odo.setDatumIzdavanja(datumIzdavanja);
        odo.setSezona(sezona);
        String str = odo.vratiVrednostiAtributa();
        Assertions.assertThat(str).isEqualTo(rezultat);
    }

    @Test
    public void vratiImenaAtrubitaTest() {
        Assertions.assertThat(odo.vratiImenaAtrubita()).isEqualTo("ukupnaCena, idKupca, datumIzdavanja, sezona");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/skipas/postavi_vrednosti_atributa.csv")
    public void postaviVrednostiAtributaTest(BigDecimal ukupnaCena, String idKupca, Date datumIzdavanja, String sezona, String rezultat) {
        odo.setUkupnaCena(ukupnaCena);
        if (idKupca != null) {
            odo.setKupac(new Kupac(Long.parseLong(idKupca)));
        }
        odo.setDatumIzdavanja(datumIzdavanja);
        odo.setSezona(sezona);

        String str = odo.postaviVrednostiAtributa();
        Assertions.assertThat(str).isEqualTo(rezultat);
    }

    @Test
    public void vratiImeKlaseTest() {
        Assertions.assertThat(odo.vratiImeTabeleZaKlasu()).isEqualTo("SkiPas");
    }

    @Test
    public void vratiUslovZaNadjiSlogTest() {
        long id = 23;
        odo.setSifraSkiPasa(id);
        Assertions.assertThat(odo.vratiUslovZaNadjiSlog()).isEqualTo("sifraSkiPasa = 23");
    }

    @Test
    public void vratiUslovZaNadjiSlogoveTest() {
        odo.setKupac(new Kupac(0, null, "Uros", "Vesic"));
        Assertions.assertThat(odo.vratiUslovZaNadjiSlogove()).isEqualTo("idKupca IN (SELECT idKupca FROM Kupac WHERE CONCAT(ime,' ',prezime) LIKE '%Uros Vesic%')");
    }

    @ParameterizedTest
    @CsvSource({"0", "1"})
    public void vratiVezaniObjekatTest(int i) {
        Kupac k = new Kupac(123);
        odo.setKupac(k);
        if (i == 0) {
            Assertions.assertThat(odo.vratiVezaniObjekat(i)).isEqualTo(k);
        } else {
            Assertions.assertThat(odo.vratiVezaniObjekat(i)).isEqualTo(null);
        }
    }
    
    @ParameterizedTest
    @CsvSource({"0", "1"})
    public void postaviVrednostVezanogObjektaTest(int i) {
        Kupac k = new Kupac(123);
        odo.postaviVrednostVezanogObjekta(k, i);
        if (i == 0) {
            Assertions.assertThat(odo.vratiVezaniObjekat(i)).isEqualTo(k);
        } else {
            Assertions.assertThat(odo.vratiVezaniObjekat(i)).isEqualTo(null);
        }
    }

    @Test
    public void napuniTest() throws Exception {
        AutoCloseable ac = MockitoAnnotations.openMocks(this);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        java.util.Date dat = cal.getTime();

        BDDMockito.given(rs.getLong("sifraSkiPasa")).willReturn(1l);
        BDDMockito.given(rs.getString("sezona")).willReturn("2021/2022");
        BDDMockito.given(rs.getDate("datumIzdavanja")).willReturn(new Date(0));
        BDDMockito.given(rs.getBigDecimal("ukupnaCena")).willReturn(new BigDecimal(150));
        BDDMockito.given(rs.getLong("idKupca")).willReturn(2l);

        odo.napuni(rs);
        Assertions.assertThat(odo.getSifraSkiPasa()).isEqualTo(1);
        Assertions.assertThat(odo.getSezona()).isEqualTo("2021/2022");
        Assertions.assertThat(odo.getDatumIzdavanja()).isEqualTo(dat);
        Assertions.assertThat(odo.getKupac().getIdKupca()).isEqualTo(2l);
        Assertions.assertThat(odo.getUkupnaCena()).isEqualTo(new BigDecimal(150));

        ac.close();
    }

    @Test
    public void vratiNazivPKTest() {
        Assertions.assertThat(odo.vratiNazivPK()).isEqualTo("sifraSkiPasa");
    }

    @Test
    public void kreirajInstancuTest() {
        SkiPas sc = new SkiPas();
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
        Assertions.assertThat(odo.getSifraSkiPasa()).isEqualTo(id);
    }

}
