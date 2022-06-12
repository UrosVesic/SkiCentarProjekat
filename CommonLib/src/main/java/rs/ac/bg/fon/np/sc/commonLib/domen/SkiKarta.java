package rs.ac.bg.fon.np.sc.commonLib.domen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Domenska klasa koja predstavlja ski kartu
 *
 * @author UrosVesic
 */
public class SkiKarta implements OpstiDomenskiObjekat, Serializable {

    /**
     * Jedinstveni identifikator ski karte u bazi
     */
    @Expose
    private long sifraSkiKarte;
    /**
     * Vrsta ski karte
     */
    @SerializedName("vrstaSkiKarte")
    @Expose
    private VrstaSkiKarte vrstaSkiKarte;
    /**
     * Cena ski karte u dinarima
     */
    @Expose
    private BigDecimal cenaSkiKarte;
    /**
     * Ski centar za koji vazi ski karta
     */
    @Expose
    private SkiCentar skiCentar;

    public SkiKarta() {
    }

    public SkiKarta(long sifraSkiKarte) {
        this.sifraSkiKarte = sifraSkiKarte;
    }

    public SkiKarta(long sifraSkiKarte, VrstaSkiKarte vrstaSkiKarte, BigDecimal cenaSkiKarte, SkiCentar skiCentar) {
        this.sifraSkiKarte = sifraSkiKarte;
        this.vrstaSkiKarte = vrstaSkiKarte;
        this.cenaSkiKarte = cenaSkiKarte;
        this.skiCentar = skiCentar;
    }

    /**
     * Vraca vrednost sifre ski karte
     *
     * @return Sifru ski karte kao long
     */
    public long getSifraSkiKarte() {
        return sifraSkiKarte;
    }

    /**
     * Postavlja vrednost sifre Ski karte na zadatu vrednost
     *
     * @param sifraSkiKarte vrednost na koju treba postaviti polje sifraSkiKarte
     */
    public void setSifraSkiKarte(long sifraSkiKarte) {
        this.sifraSkiKarte = sifraSkiKarte;
    }

    /**
     * Vraca vrstu ski karte
     *
     * @return Vrstu ski karte kao Enum VrstaSkiKarte
     */
    public VrstaSkiKarte getVrstaSkiKarte() {
        return vrstaSkiKarte;
    }

    /**
     * Postavlja vrednost vrste ski karte na zadatu vrednost
     *
     * @param vrstaSkiKarte enum vrednost na koju treba posstaviti polje
     * vrstaSkiKarte
     */
    public void setVrstaSkiKarte(VrstaSkiKarte vrstaSkiKarte) {
        this.vrstaSkiKarte = vrstaSkiKarte;
    }

    /**
     * Vraca cenu ski karte u dinarima
     *
     * @return Cena ski karte kao BigDecimal
     */
    public BigDecimal getCenaSkiKarte() {
        return cenaSkiKarte;
    }

    /**
     * Postavlja cenu ski karte na zadatu vrednost
     *
     * @param cenaSkiKarte vrednost na koju treba postaviti polje cenaSkiKarteФ
     */
    public void setCenaSkiKarte(BigDecimal cenaSkiKarte) {
        this.cenaSkiKarte = cenaSkiKarte;
    }

    /**
     * Vraca ski centar za koji vazi ski karta
     *
     * @return Ski centar kao objekat klase SkiCentar
     */
    public SkiCentar getSkiCentar() {
        return skiCentar;
    }

    /**
     * Postavlja vrednost ski centra na zadatu vrednost
     *
     * @param skiCentar Vrednost na koju treba postaviti polje skiCentar
     */
    public void setSkiCentar(SkiCentar skiCentar) {
        this.skiCentar = skiCentar;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return (vrstaSkiKarte == null ? null : "'" + vrstaSkiKarte + "'") + ", " + cenaSkiKarte
                + ", " + (skiCentar == null ? null : skiCentar.getSifraSkiCentra());
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "vrstaSkiKarte = "
                + (vrstaSkiKarte == null ? null : "'" + vrstaSkiKarte + "'") + ", " + "cenaSkiKarte = " + cenaSkiKarte
                + ", " + "sifraSkiCentra  = " + (skiCentar == null ? null : skiCentar.getSifraSkiCentra());
    }

    @Override
    public String vratiImeTabeleZaKlasu() {
        return "SkiKarta";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "sifraSkiKarte=  " + sifraSkiKarte;
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        return "cenaSkiKarte <= " + cenaSkiKarte;
    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        sifraSkiKarte = rs.getLong("sifraSkiKarte");
        vrstaSkiKarte = VrstaSkiKarte.valueOf(rs.getString("vrstaSkiKarte"));
        cenaSkiKarte = rs.getBigDecimal("cenaSkiKarte");
        skiCentar = new SkiCentar();
        skiCentar.setSifraSkiCentra(rs.getLong("sifraSkiCentra"));
    }

    @Override
    public String vratiNazivPK() {
        return "sifraSkiKarte";
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        return new SkiKarta();
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 1;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        if (i == 0) {
            return skiCentar;
        }
        return null;
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i) {
        if (i == 0) {
            this.skiCentar = (SkiCentar) vezo;
        }
    }

    @Override
    public String vratiUslovZaPromeniSlog() {
        return "sifraSkiKarte= " + sifraSkiKarte;
    }

    @Override
    public String toString() {
        return skiCentar + " " + vrstaSkiKarte;
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
        final SkiKarta other = (SkiKarta) obj;
        if (this.sifraSkiKarte != other.sifraSkiKarte) {
            return false;
        }
        return true;
    }

    @Override
    public void postaviVrednostPK(long id) {
        this.sifraSkiKarte = id;
    }

    @Override
    public String vratiImenaAtrubita() {
        return "vrstaSkiKarte, cenaSkiKarte, sifraSkiCentra";
    }
}
