package rs.ac.bg.fon.np.sc.commonlib.domen;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SkiPas implements OpstiDomenskiObjekat, Serializable {

    /**
     * Jedinstveni identifikator ski pasa u bazi
     */
    @Expose
    private long sifraSkiPasa;
    /**
     * Ukupna cena ski pasa izracunata na osnovu cena stavki
     */
    @Expose
    private BigDecimal ukupnaCena;
    /**
     * Kupac kome se izdaje ski pas
     */
    @Expose
    private Kupac kupac;
    /**
     * Datum kada se izdaje ski pas
     */
    @Expose
    private Date datumIzdavanja;
    /**
     * Sezona u kojoj vazi ski pas u formatu yyyy/yyyy. Zavisi od datuma
     * izdavanja ski pasa.
     */
    @Expose
    private String sezona;
    /**
     * Lista stavki ski pasa
     */
    @Expose
    private List<StavkaSkiPasa> stavkeSkiPasa;

    public SkiPas() {
        ukupnaCena = new BigDecimal(0);
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date dDatum = new Date();
        datumIzdavanja = java.sql.Date.valueOf(sm.format(dDatum));
        stavkeSkiPasa = new ArrayList<>();
    }

    public SkiPas(long sifraSkiPasa) {
        this.sifraSkiPasa = sifraSkiPasa;
    }

    public SkiPas(long sifraSkiPasa, BigDecimal ukupnaCena, Kupac kupac, Date datumIzdavanja, String sezona, List<StavkaSkiPasa> stavkeSkiPasa) {
        this.sifraSkiPasa = sifraSkiPasa;
        this.ukupnaCena = ukupnaCena;
        this.kupac = kupac;
        this.datumIzdavanja = datumIzdavanja;
        this.sezona = sezona;
        this.stavkeSkiPasa = stavkeSkiPasa;
    }

    /**
     * Vraca listu stavki ski pasa
     *
     * @return Listu stavki kao List
     */
    public List<StavkaSkiPasa> getStavkeSkiPasa() {
        return stavkeSkiPasa;
    }

    /**
     * Postavlja listu stavki na zadatu vrednost
     *
     * @param stavkeSkiPasa Vrednost na koju treba postaviti polje stavke ski
     * pasa
     */
    public void setStavkeSkiPasa(List<StavkaSkiPasa> stavkeSkiPasa) {
        this.stavkeSkiPasa = stavkeSkiPasa;
    }

    /**
     * Vraca vrednost sifre ski pasa
     *
     * @return sifra ski pasa kao long
     */
    public long getSifraSkiPasa() {
        return sifraSkiPasa;
    }
     /**
     * Postavlja vrednost sifre Ski pasa na zadatu vrednost
     *
     * @param sifraSkiPasa vrednost na koju treba postaviti polje sifraSkiPasa
     */
    public void setSifraSkiPasa(long sifraSkiPasa) {
        this.sifraSkiPasa = sifraSkiPasa;
    }

    /**
     * Vraca ukupnu cenu ski pasa izracunatu na osnovu cena stavki
     *
     * @return Ukupnu cenu kao BigDecimal
     */
    public BigDecimal getUkupnaCena() {
        return ukupnaCena;
    }
    /**
     * Postavlja vrednost ukupne cene na zadatu vrednost
     * @param ukupnaCena Vrednost na koju treba postaviti polje ukupnaCena
     */
    public void setUkupnaCena(BigDecimal ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    /**
     * Vraca kupca kome se izdaje ski pas
     *
     * @return Kupca kao objekat klase Kupac
     */
    public Kupac getKupac() {
        return kupac;
    }
    /**
     * Postavlja vrednost polja Kupac na zadatu vrednost
     * @param kupac Vrednost na koju treba postaviti polje kupac
     */
    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    /**
     * Vraca datum izdavanja ski pasa
     *
     * @return Datum izdavanja kao java.util.Date
     */
    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }
    /**
     * Postavlja vrednost datuma izdavanja na zadatu vrednost
     * @param datumIzdavanja Vrednost na koju treba postaviti polje datumIzdavanja
     */
    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    @Override
    public String vratiVrednostiAtributa() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        if (datumIzdavanja != null) {
            datumIzdavanja = java.sql.Date.valueOf(sm.format(datumIzdavanja));
        }
        return ukupnaCena + ", " + (kupac == null ? null : kupac.getIdKupca())
                + ", " + (datumIzdavanja == null ? null : "'" + datumIzdavanja + "'") + ", " + (sezona == null ? null : "'" + sezona + "'");
    }

    @Override
    public String postaviVrednostiAtributa() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        if (datumIzdavanja != null) {
            datumIzdavanja = java.sql.Date.valueOf(sm.format(datumIzdavanja));
        }
        return "ukupnaCena = " + ukupnaCena + ", " + "idKupca = "
                + (kupac == null ? null : kupac.getIdKupca()) + ", " + "datumIzdavanja = "
                + (datumIzdavanja == null ? null : "'" + datumIzdavanja + "'") + ", sezona = "
                + (sezona == null ? null : "'" + sezona + "'");
    }

    @Override
    public String vratiImeTabeleZaKlasu() {
        return "SkiPas";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "sifraSkiPasa = " + sifraSkiPasa;
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        return "idKupca IN (SELECT idKupca FROM Kupac WHERE CONCAT(ime,' ',prezime) LIKE '%" + (kupac.getIme() == null ? "" : kupac.getIme()) + (kupac.getPrezime() == null ? "" : " " + kupac.getPrezime()) + "%')";
    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        sifraSkiPasa = rs.getLong("sifraSkiPasa");
        ukupnaCena = rs.getBigDecimal("ukupnaCena");
        this.setKupac(new Kupac(rs.getLong("idKupca"), null, null, null));
        datumIzdavanja = new Date(rs.getDate("datumIzdavanja").getTime());
        sezona = rs.getString("sezona");
        stavkeSkiPasa = new ArrayList<>();
    }

    @Override
    public String vratiNazivPK() {
        return "sifraSkiPasa";
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        return new SkiPas();
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 1;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        if (i == 0) {
            return kupac;
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (int) (this.sifraSkiPasa ^ (this.sifraSkiPasa >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SkiPas other = (SkiPas) obj;
        if (this.sifraSkiPasa != other.sifraSkiPasa) {
            return false;
        }
        return true;
    }

    @Override
    public void postaviVrednostPK(long id) {
        this.sifraSkiPasa = id;
    }

    @Override
    public String vratiImenaAtrubita() {
        return "ukupnaCena, idKupca, datumIzdavanja, sezona";
    }

    public String getSezona() {
        return sezona;
    }

    public void setSezona(String sezona) {
        this.sezona = sezona;
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i) {
        if (i == 0) {
            this.setKupac((Kupac) vezo);
        }
    }
}
