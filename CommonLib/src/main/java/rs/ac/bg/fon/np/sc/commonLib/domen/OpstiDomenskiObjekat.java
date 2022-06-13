package rs.ac.bg.fon.np.sc.commonLib.domen;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Metoda proverava da li su sva polja nekog objekta razlicita od null i da
     * li su String polja razlicita od praznog stringa.
     *
     * @return Listu Stringova sa porukama o tome koja su polja pogresno uneta
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public default List<String> validirajPolja() throws IllegalArgumentException, IllegalAccessException {
        List<String> nullFields = new ArrayList<>();
        Field[] polja = this.getClass().getDeclaredFields();
        for (Field polje : polja) {
            polje.setAccessible(true);
            Object o = polje.get(this);
            if (o == null) {
                nullFields.add("Polje " + polje.getName() + " je obavezno");
            }
            if (o instanceof String && o.equals("")) {
                nullFields.add("Polje " + polje.getName() + " je obavezno");
            }
        }
        return nullFields;
    }

}
