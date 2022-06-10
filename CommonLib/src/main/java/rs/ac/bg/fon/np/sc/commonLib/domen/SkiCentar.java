package rs.ac.bg.fon.np.sc.commonLib.domen;

import com.google.gson.annotations.Expose;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SkiCentar implements OpstiDomenskiObjekat {

    /**
     * Jedinstveni identifikator ski centra u bazi
     */
    @Expose
    private long sifraSkiCentra;
    /**
     * Ime ski centra
     */
    @Expose
    private String nazivSkiCentra;
    /**
     * Ime planine
     */
    @Expose
    private String nazivPlanine;
    /**
     * Radno vreme ski centra u formatu HH-HH
     */
    @Expose
    private String radnoVreme;

    public SkiCentar() {

    }

    public SkiCentar(long sifraSkiCentra) {
        this.sifraSkiCentra = sifraSkiCentra;
    }

    public SkiCentar(long sifraSkiCentra, String nazivSkiCentra, String nazivPlanine, String radnoVreme) {
        this.sifraSkiCentra = sifraSkiCentra;
        this.nazivSkiCentra = nazivSkiCentra;
        this.nazivPlanine = nazivPlanine;
        this.radnoVreme = radnoVreme;
    }

    /**
     * Vraca vrednost sifre ski centra
     *
     * @return sifraSkiCentra kao long
     */
    public long getSifraSkiCentra() {
        return sifraSkiCentra;
    }

    /**
     * Postavlja vrednost sifre Ski Centra na zadatu vrednost
     *
     * @param sifraSkiCentra vrednost na koju treba postaviti polje
     * sifraSkiCentra
     */
    public void setSifraSkiCentra(long sifraSkiCentra) {
        this.sifraSkiCentra = sifraSkiCentra;
    }

    /**
     * Vraca naziv ski centra
     *
     * @return naziv ski centra kao String
     */
    public String getNazivSkiCentra() {
        return nazivSkiCentra;
    }

    /**
     * Postavlja vrednost naziva ski centra na zadatu vrednost
     *
     * @param nazivSkiCentra vrednost na koju treba postaviti polje
     * nazivSkiCentra
     */
    public void setNazivSkiCentra(String nazivSkiCentra) {
        this.nazivSkiCentra = nazivSkiCentra;
    }

    /**
     * Vraca naziv planine u kojoj se nalazi ski centar
     *
     * @return naziv planine kao String
     */
    public String getNazivPlanine() {
        return nazivPlanine;
    }

    /**
     * Postavlja vrednost naziva planine na zadatu vrednost
     *
     * @param nazivPlanine vrednost na koju treba postaviti polje nazivPlanine
     */
    public void setNazivPlanine(String nazivPlanine) {
        this.nazivPlanine = nazivPlanine;
    }

    /**
     * Vraca radno vreme ski centra
     *
     * @return Radno vreme kao string u formatuu HH-HH
     */
    public String getRadnoVreme() {
        return radnoVreme;
    }
    /**
     * Postavlja vrednost radnog vremena na zadatu vrednost
     * @param radnoVreme vrednost na koju treba postaviti polje radnoVreme
     */
    public void setRadnoVreme(String radnoVreme) {
        this.radnoVreme = radnoVreme;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return (nazivSkiCentra == null ? null : "'" + nazivSkiCentra + "'") + ", "
                + (nazivPlanine == null ? null : "'" + nazivPlanine + "'") + ", "
                + (radnoVreme == null ? null : "'" + radnoVreme + "'");
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "nazivSkiCentra = "
                + (nazivSkiCentra == null ? null : "'" + nazivSkiCentra + "'") + ", " + "nazivPlanine = "
                + (nazivPlanine == null ? null : "'" + nazivPlanine + "'") + ", " + "radnoVreme = "
                + (radnoVreme == null ? null : "'" + radnoVreme + "'");
    }

    @Override
    public String vratiImeTabeleZaKlasu() {
        return "SkiCentar";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {

        return "sifraSkiCentra= " + sifraSkiCentra;
    }

    @Override
    public String vratiUslovZaNadjiSlog2() {
        return "nazivSkiCentra LIKE '%" + nazivSkiCentra + "%'";
    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        SkiCentar sc = (SkiCentar) this;
        sc.setSifraSkiCentra(rs.getLong("sifraSkiCentra"));
        sc.setNazivSkiCentra(rs.getString("nazivSkiCentra"));
        sc.setNazivPlanine(rs.getString("nazivPlanine"));
        sc.setRadnoVreme(rs.getString("radnoVreme"));
    }
    /**
     * Vraca sve podatke o ski centru u jednom Stringu
     *
     * @return String sa svim podacima o ski centru
     */
    @Override
    public String toString() {
        return nazivSkiCentra;
    }

    @Override
    public String vratiNazivPK() {
        return "sifraSkiCentra";
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        return new SkiCentar();
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 0;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        return null;
    }
     /**
     * Poredi ski centre po atributu sifra ski centra
     *
     * @param obj objekat sa kojim se poredi this objekat
     * @return
     * <ul>
     * <li>true - ako su sifre ski centra iste</li>
     * <li>false - ako sifre ski centra nisu iste</li>
     * </ul>
     */
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
        final SkiCentar other = (SkiCentar) obj;
        if (this.sifraSkiCentra != other.sifraSkiCentra) {
            return false;
        }
        return true;
    }

    @Override
    public void postaviVrednostPK(long id) {
        this.sifraSkiCentra = id;
    }

    @Override
    public String vratiImenaAtrubita() {
        return "nazivSkiCentra, nazivPlanine, radnoVreme";
    }

    @Override
    public String vratiUslovZaPromeniSlog() {
        return "sifraSkiCentra = " + sifraSkiCentra;
    }

}
