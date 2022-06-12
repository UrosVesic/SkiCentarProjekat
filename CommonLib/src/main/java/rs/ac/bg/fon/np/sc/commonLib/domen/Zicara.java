package rs.ac.bg.fon.np.sc.commonLib.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Domenska klasa koja predstavlja zicaru u ski centru
 *
 * @author UrosVesic
 */
public class Zicara implements OpstiDomenskiObjekat, Serializable {

    /**
     * Jedinstveni identifikator zicare u bazi
     */
    private long SifraZicare;
    /**
     * Naziv zicare
     */
    private String nazivZicare;
    /**
     * Radno vreme zicare
     */
    private String radnoVreme;
    /**
     * Broj skijasa koji zicara moze da poveze do vrha u toku jednog sata
     */
    private int kapacitet;
    /**
     * Da li je zicara u funkciji
     */
    private boolean UFunkciji;
    /**
     * Ski centar u kom se nalazi zicara
     */
    private SkiCentar skiCentar;

    public Zicara() {

    }

    public Zicara(long SifraZicare, String nazivZicare) {
        this.SifraZicare = SifraZicare;
        this.nazivZicare = nazivZicare;
    }

    public Zicara(long SifraZicare, String NazivZicare, String RadnoVreme, int Kapacitet, boolean UFunkciji,
            SkiCentar skiCentar) {
        this.SifraZicare = SifraZicare;
        this.nazivZicare = NazivZicare;
        this.radnoVreme = RadnoVreme;
        this.kapacitet = Kapacitet;
        this.UFunkciji = UFunkciji;
        this.skiCentar = skiCentar;
    }

    /**
     * Vraca sifru zicare koja joj je dodeljena u bazi
     *
     * @return Sifra zicare kao long
     */
    public long getSifraZicare() {
        return SifraZicare;
    }

    /**
     * Postavlja vrednost polja sifraZicare na zadatu vrednost
     *
     * @param SifraZicare Vrednost na koju treba postaviti polje sifraZicare
     */
    public void setSifraZicare(long SifraZicare) {
        this.SifraZicare = SifraZicare;
    }

    /**
     * Vraca naziv zicare
     *
     * @return Naziv zicare kao String
     */
    public String getNazivZicare() {
        return nazivZicare;
    }

    /**
     * Postavlja vrednost polja nazivZicare na zadatu vrednost
     *
     * @param nazivZicare Vrednost na koju treba postaviti polje nazivZicare
     */
    public void setNazivZicare(String nazivZicare) {
        this.nazivZicare = nazivZicare;
    }

    /**
     * Vraca radno vreme zicare
     *
     * @return Radno vreme zicare kao String u formatu HH-HH
     */
    public String getRadnoVreme() {
        return radnoVreme;
    }

    /**
     * Postavlja vrednost polja radnoVreme na zadatu vrednost
     *
     * @param radnoVreme Vrednost na koju treba postaviti polje radnoVreme
     */
    public void setRadnoVreme(String radnoVreme) {
        this.radnoVreme = radnoVreme;
    }

    /**
     * Vraca kapacitet zicare
     *
     * @return Kapacitet kao int
     */
    public int getKapacitet() {
        return kapacitet;
    }

    /**
     * Postavlja vrednost polja kapacitet na zadatu vrednost
     *
     * @param kapacitet Vrednost na koju treba postaviti polje kapacitet
     */
    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    /**
     * Vraca boolean vrednost da li je zicara trenutno u funkciji
     *
     * @return <ul><li>true-ako je zicara u funkciji</li>
     * <li>false-ako je zicara van funkcije</li></ul>
     */
    public boolean isUFunkciji() {
        return UFunkciji;
    }

    /**
     * Postavlja vrednost polja UFunkciji na zadatu vrednost
     *
     * @param UFunkciji Vrednost na koju treba postaviti polje UFunkciji
     */
    public void setUFunkciji(boolean UFunkciji) {
        this.UFunkciji = UFunkciji;
    }

    /**
     * Vraca ski centar u kome se nalazi zicara
     *
     * @return Ski centar kao objekat klase SkiCentar
     */
    public SkiCentar getSkiCentar() {
        return skiCentar;
    }

    /**
     * Postavlja vrednost polja skiCentar na zadatu vrednost
     *
     * @param skiCentar Vrednost na koju treba postaviti polje skiCentar
     */
    public void setSkiCentar(SkiCentar skiCentar) {
        this.skiCentar = skiCentar;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return (nazivZicare == null ? null : "'" + nazivZicare + "'") + ", "
                + (radnoVreme == null ? null : "'" + radnoVreme + "'") + ", " + kapacitet + ", " + UFunkciji + ", "
                + (skiCentar == null ? null : skiCentar.getSifraSkiCentra());
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "nazivZicare = "
                + (nazivZicare == null ? null : "'" + nazivZicare + "'") + ", " + "radnoVreme = "
                + (radnoVreme == null ? null : "'" + radnoVreme + "'") + ", " + "kapacitet = " + kapacitet + ", "
                + "uFunkciji = " + UFunkciji + ", " + "sifraSkiCentra = "
                + (skiCentar == null ? null : skiCentar.getSifraSkiCentra());
    }

    @Override
    public String vratiImeTabeleZaKlasu() {
        return "Zicara";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "SifraZicare = " + SifraZicare;
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        return "sifraSkiCentra = (SELECT sifraSkiCentra FROM skiCentar WHERE NazivSkiCentra LIKE '"
                + skiCentar.getNazivSkiCentra() + "')";
    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        SifraZicare = rs.getLong("sifraZicare");
        nazivZicare = rs.getString("nazivZicare");
        radnoVreme = rs.getString("radnoVreme");
        kapacitet = rs.getInt("kapacitet");
        UFunkciji = rs.getBoolean("uFunkciji");
        skiCentar = new SkiCentar(rs.getLong("sifraSkiCentra"));

    }

    @Override
    public String vratiNazivPK() {
        return "SifraZicare";
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        return new Zicara();
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
            this.setSkiCentar((SkiCentar) vezo);
        }
    }

    @Override
    public void postaviVrednostPK(long id) {
        this.SifraZicare = id;
    }

    @Override
    public String vratiImenaAtrubita() {
        return "nazivZicare, radnoVreme, kapacitet, UFunkciji, sifraSkiCentra";
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
        final Zicara other = (Zicara) obj;
        if (!Objects.equals(this.nazivZicare, other.nazivZicare)) {
            return false;
        }
        return true;
    }

}
