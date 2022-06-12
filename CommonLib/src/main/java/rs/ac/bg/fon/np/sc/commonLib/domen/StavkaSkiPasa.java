package rs.ac.bg.fon.np.sc.commonLib.domen;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
/**
 * Domenska klasa koja predstavlja stavku ski pasa koja se odnosi na ski karte
 * @author UrosVesic
 */
public class StavkaSkiPasa implements OpstiDomenskiObjekat, Serializable {

    /**
     * SkiPas kojem pripada stavka
     */
    private SkiPas skiPas;
    /**
     * Redni broj stavke na ski pasu
     */
    @Expose
    private long redniBroj;
    /**
     * Vrednost stavke koja je jednaka ceni ski karte
     */
    @Expose
    private BigDecimal vrednostStavke;
    /**
     * Datum pocetka vazenja stavke
     */
    @Expose
    private Date pocetakVazenja;
    /**
     * Zavrsetak vazenja stavke. Zavisi od datuma pocetka vazenja i trajanja ski
     * karte
     */
    @Expose
    private Date zavrsetakVazenja;
    /**
     * Ski karta na koju se odnosi stavka
     */
    @Expose
    private SkiKarta skiKarta;

    public StavkaSkiPasa() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date dDatum = new Date();
        pocetakVazenja = java.sql.Date.valueOf(sm.format(dDatum));
        zavrsetakVazenja = java.sql.Date.valueOf(sm.format(dDatum));

    }

    public StavkaSkiPasa(long redniBroj) {
        this.redniBroj = redniBroj;
    }

    public StavkaSkiPasa(SkiPas skiPas, long redniBroj, Date pocetakVazenja) {
        this.skiPas = skiPas;
        this.redniBroj = redniBroj;
        this.pocetakVazenja = pocetakVazenja;
    }

    public StavkaSkiPasa(SkiPas skiPas, long redniBroj, BigDecimal vrednostStavke, Date pocetakVazenja,
            Date zavrsetakVazenja, SkiKarta skiKarta) {
        this.skiPas = skiPas;
        this.redniBroj = redniBroj;
        this.vrednostStavke = vrednostStavke;
        this.pocetakVazenja = pocetakVazenja;
        this.zavrsetakVazenja = zavrsetakVazenja;
        this.skiKarta = skiKarta;
    }

    /**
     * Vraca ski kartu na koju se odnosi stavka
     *
     * @return Ski kartu kao objekat klase SkiKarta
     */
    public SkiKarta getSkiKarta() {
        return skiKarta;
    }

    /**
     * Postavlja vrednost polja skiKarta na zadatu vrednost
     *
     * @param skiKarta Vrednost koju treba postaviti na polje skiKarta
     */
    public void setSkiKarta(SkiKarta skiKarta) {
        this.skiKarta = skiKarta;
    }

    /**
     * Vraca ski pas na kom se nalazi stavka
     *
     * @return ski pas kao objekat klase SkiPas
     */
    public SkiPas getSkiPas() {
        return skiPas;
    }

    /**
     * Postavlja vrednost polja skiPas na zadatu vrednost
     *
     * @param skiPas Vrednost koju treba postaviti na polje skiPas
     */
    public void setSkiPas(SkiPas skiPas) {
        this.skiPas = skiPas;
    }

    /**
     * Vraca redni broj stavke na ski pasu
     *
     * @return redniBroj kao int
     */
    public long getRedniBroj() {
        return redniBroj;
    }

    /**
     * Postavlja vrednost rednog broja na zadatu vrednost
     *
     * @param redniBroj Vrednost na koju treba postaviti polje redni broj
     */
    public void setRedniBroj(long redniBroj) {
        this.redniBroj = redniBroj;
    }

    /**
     * Vraca vrednost stavke koja je jednaka ceni ski karte
     *
     * @return vrednostStavke kao BigDecimal
     * @see rs.ac.bg.fon.np.sc.commonLib.domen.SkiKarta
     */
    public BigDecimal getVrednostStavke() {
        return vrednostStavke;
    }

    /**
     * Postavlja vrednost polja vrednostStavke na zadatu vrednost
     *
     * @param vrednostStavke Vrednost na koju treba postaviti polje
     * vrednostStavke
     */
    public void setVrednostStavke(BigDecimal vrednostStavke) {
        this.vrednostStavke = vrednostStavke;
    }

    /**
     * Vraca pocetak vazenja stavke ski pasa
     *
     * @return Pocetak vazenja kao java.util.Date
     */
    public Date getPocetakVazenja() {
        return pocetakVazenja;
    }

    /**
     * Postavlja vrednost polja pocetakVazenja na zadatu vrednost
     *
     * @param pocetakVazenja Vrednost na koju treba postaviti polje
     * pocetakVazenja
     */
    public void setPocetakVazenja(Date pocetakVazenja) {
        this.pocetakVazenja = pocetakVazenja;
    }

    /**
     * Vraca zavrsetak vazenja stavke ski pasa
     *
     * @return Zavrsetak vazenja kao java.util.Date
     */
    public Date getZavrsetakVazenja() {
        return zavrsetakVazenja;
    }

    /**
     * Postavlja vrednost polja zavrsetakVazenja na zadatu vrednost
     *
     * @param zavrsetakVazenja Vrednost na koju treba postaviti polje
     * zavrsetakVazenja
     */
    public void setZavrsetakVazenja(Date zavrsetakVazenja) {
        this.zavrsetakVazenja = zavrsetakVazenja;
    }

    @Override
    public String vratiVrednostiAtributa() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date dDatumZ = zavrsetakVazenja;
        Date dDatumP = pocetakVazenja;
        if (pocetakVazenja != null) {
            this.pocetakVazenja = java.sql.Date.valueOf(sm.format(dDatumP));
        }
        if (zavrsetakVazenja != null) {
            this.zavrsetakVazenja = java.sql.Date.valueOf(sm.format(dDatumZ));
        }
        return (skiPas == null ? null : skiPas.getSifraSkiPasa()) + ", " + redniBroj + ", " + vrednostStavke + ", " + (pocetakVazenja == null ? null : "'" + pocetakVazenja + "'")
                + ", " + (zavrsetakVazenja == null ? null : "'" + zavrsetakVazenja + "'") + ", " + (skiKarta == null ? null : skiKarta.getSifraSkiKarte());
    }

    @Override
    public String postaviVrednostiAtributa() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date dDatumZ = zavrsetakVazenja;
        Date dDatumP = pocetakVazenja;
        if (pocetakVazenja != null) {
            this.pocetakVazenja = java.sql.Date.valueOf(sm.format(dDatumP));
        }
        if (zavrsetakVazenja != null) {
            this.zavrsetakVazenja = java.sql.Date.valueOf(sm.format(dDatumZ));
        }
        return "sifraSkiPasa= " + (skiPas == null ? null : skiPas.getSifraSkiPasa()) + ", " + "rb=" + redniBroj + ", " + "vrednostStavke = "
                + vrednostStavke + ", " + "pocetakVazenja = " + (pocetakVazenja == null ? null : "'" + pocetakVazenja + "'") + ", " + "zavrsetakVazenja = "
                + (zavrsetakVazenja == null ? null : "'" + zavrsetakVazenja + "'") + ", " + "sifraSkiKarte = " + (skiKarta == null ? null : skiKarta.getSifraSkiKarte());
    }

    @Override
    public String vratiImeTabeleZaKlasu() {
        return "StavkaSkiPasa";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "sifraSkiPasa = " + skiPas.getSifraSkiPasa() + " AND " + "RB = " + redniBroj;
    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        skiPas.setSifraSkiPasa(rs.getLong("sifraSkiPasa"));
        redniBroj = rs.getLong("RB");
        vrednostStavke = rs.getBigDecimal("vrednostStavke");
        pocetakVazenja = new Date(rs.getDate("pocetakVazenja").getTime());
        zavrsetakVazenja = new Date(rs.getDate("zavrsetakVazenja").getTime());

        SkiKarta sk = new SkiKarta();
        sk.setSifraSkiKarte(rs.getLong("sifraSkiKarte"));
        skiKarta = sk;
    }

    @Override
    public String vratiNazivPK() {
        return "rb";
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        StavkaSkiPasa stavka = new StavkaSkiPasa();
        stavka.setSkiPas(skiPas);
        return stavka;
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 1;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        if (i == 0) {
            return skiKarta;
        }

        return null;
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i) {
        if (i == 0) {
            skiKarta = (SkiKarta) vezo;
        }

    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        return "sifraSkiPasa = " + skiPas.getSifraSkiPasa();
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
        final StavkaSkiPasa other = (StavkaSkiPasa) obj;
        if (this.redniBroj != other.redniBroj) {
            return false;
        }
        if (!Objects.equals(this.skiPas, other.skiPas)) {
            return false;
        }
        return true;
    }

    public Date generisiDatumZavrsetka() {
        this.setZavrsetakVazenja(new Date(this.getPocetakVazenja().getTime()
                + 1000 * 60 * 60 * 24 * getSkiKarta().getVrstaSkiKarte().getBrojDana()));
        return this.getZavrsetakVazenja();

    }

    @Override
    public void postaviVrednostPK(long id) {
        this.redniBroj = id;
    }

    @Override
    public String vratiImenaAtrubita() {
        return "sifraSkiPasa, rb, vrednostStavke, pocetakVazenja, zavrsetakVazenja, sifraSkiKarte";
    }

}
