package rs.ac.bg.fon.np.sc.commonLib.domen;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interfejs koji je na vrhu hijerarhije domenskih klasa.
 *
 * @author UrosVesic
 */
public interface OpstiDomenskiObjekat {

    /**
     * Konvertuje atribute klase u String koji predstavlja imena kolona u
     * tabeli.
     *
     * @return imena kolona u tabeli kao String
     */
    String vratiImenaAtrubita();

    /**
     * Konvertuje trenutne vrednosti atributa u format pogodan za koriscenje u
     * sql upitima
     *
     * @return vrednosti atributa kao String spojen zarezima
     */
    String vratiVrednostiAtributa();

    /**
     * Konvertuje imena atributa i njihove vrednosti u format pogodan za pisanje
     * SQL UPDATE upita
     *
     * @return imena atributa i njihove vrednosti kao String
     */
    String postaviVrednostiAtributa();

    /**
     * Vraca ime tabele koja odgovara datoj klasi
     *
     * @return ime tabele kao string
     */
    String vratiImeTabeleZaKlasu();

    /**
     * Konvertuje atrbiut po kome se trazi objekat u String pogodan za pisanje
     * WHERE klauzule SQL upita.
     *
     * @return uslov za nalazenje sloga kao String
     */
    String vratiUslovZaNadjiSlog();

    /**
     * Mapira ResultSet objekat na domenski objekat
     *
     * @param rs ResultSet dobijen iz baze izvrsavanjem odredjenog upita
     * @throws SQLException ukoliko nije naveden pravilan naziv kolone u tabeli
     * koja se mapira na odredjeni atribut
     */
    void napuni(ResultSet rs) throws SQLException;

    /**
     * Vraca naziv kolone primarnog kljuca u odredjenoj tabeli
     *
     * @return naziv kolone primarnog kljuca kao String
     */
    String vratiNazivPK();

    /**
     * Kreira novu instancu klase konkretnog domenskog objekta koji implementira
     * interfejs OpstiDomenskiObjekat
     *
     * @return novu instancu klase koja implementira interfejs
     * OpstiDomenskiObjekat
     */
    public OpstiDomenskiObjekat kreirajInstancu();

    /**
     * Vraca broj zavisnih objekata odnosno kolona koje predstavljaju spoljni
     * kljuc u odgovarajucoj tabeli baze
     *
     * @return broj vezanih objekata kao int
     */
    public int vratiBrojVezanihObjekata();

    /**
     * Vraca jedan od vezanih objekata, u zavisnosti od parametra i
     *
     * @param i redni broj vezanog objekta
     * @return vezani objekat kao OpstiDomenskiObjekat ili null ako je i vece
     * ili jednako broju vezanih objekata
     */
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i);

    /**
     * Postavlja vrednost nekog vezanog objekta na zadatu vrednost, u zavisnosti
     * od parametra i. Ako je i vece ili jednako broju vezanih objekata metoda
     * ne postavlja vrednost nijednom objektu
     *
     * @param vezo
     * @param i
     */
    default public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i) {
    }

    ;
    /**
     * Vraca uslov za promenu sloga kao String pogodan za pisanje WHERE klauzule. 
     * @return uslov za promenu sloga kao String
     */
    default String vratiUslovZaPromeniSlog() {
        return vratiUslovZaNadjiSlog();
    }

    /**
     * Vraca uslov za nalazenje vise slogova kao String pogodan za pisanje WHERE
     * klauzule.
     *
     * @return
     */
    public default String vratiUslovZaNadjiSlogove() {
        return vratiUslovZaNadjiSlog();
    }

    /**
     * Konvertuje atrbiut po kome se trazi objekat u String pogodan za pisanje
     * WHERE klauzule SQL upita.
     *
     * @return uslov za nalazenje sloga kao String
     */
    public default String vratiUslovZaNadjiSlog2() {
        return vratiUslovZaNadjiSlog();
    }

    /**
     * Postavlja vrednost atributa koji odgovara primarnom kljucu odgovarajuce
     * tabele na zadatu vrednost
     *
     * @param id vrednost primarnog kljuca
     */
    public void postaviVrednostPK(long id);

}
