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

    @Expose
    private long sifraSkiPasa;
    @Expose
    private BigDecimal ukupnaCena;
    @Expose
    private String imePrezimeKupca;
    @Expose
    private Date datumIzdavanja;
    @Expose
    private String sezona;
    @Expose
    private List<StavkaSkiPasa> stavkeSkiPasa;

    public SkiPas() {
        ukupnaCena = new BigDecimal(0);
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date dDatum = new Date();
        datumIzdavanja = java.sql.Date.valueOf(sm.format(dDatum));
        stavkeSkiPasa = new ArrayList<>();
    }

    public SkiPas(long sifraSkiPasa, BigDecimal ukupnaCena, String imePrezimeKupca, Date datumIzdavanja, String sezona, List<StavkaSkiPasa> stavkeSkiPasa) {
        this.sifraSkiPasa = sifraSkiPasa;
        this.ukupnaCena = ukupnaCena;
        this.imePrezimeKupca = imePrezimeKupca;
        this.datumIzdavanja = datumIzdavanja;
        this.sezona = sezona;
        this.stavkeSkiPasa = stavkeSkiPasa;
    }

    public List<StavkaSkiPasa> getStavkeSkiPasa() {
        return stavkeSkiPasa;
    }

    public void setStavkeSkiPasa(List<StavkaSkiPasa> stavkeSkiPasa) {
        this.stavkeSkiPasa = stavkeSkiPasa;
    }

    public long getSifraSkiPasa() {
        return sifraSkiPasa;
    }

    public void setSifraSkiPasa(long sifraSkiPasa) {
        this.sifraSkiPasa = sifraSkiPasa;
    }

    public BigDecimal getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(BigDecimal ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public String getImePrezimeKupca() {
        return imePrezimeKupca;
    }

    public void setImePrezimeKupca(String imePrezimeKupca) {
        this.imePrezimeKupca = imePrezimeKupca;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        /*SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date dDatum = datumIzdavanja;
        datumIzdavanja = java.sql.Date.valueOf(sm.format(dDatum));*/
        this.datumIzdavanja = datumIzdavanja;
    }

    @Override
    public String vratiVrednostiAtributa() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        datumIzdavanja = java.sql.Date.valueOf(sm.format(datumIzdavanja));
        return ukupnaCena + ", " + (imePrezimeKupca == null ? null : "'" + imePrezimeKupca + "'")
                + ", " + "'" + datumIzdavanja + "'" + ", " + (sezona == null ? null : "'" + sezona + "'");
    }

    @Override
    public String postaviVrednostiAtributa() {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        datumIzdavanja = java.sql.Date.valueOf(sm.format(datumIzdavanja));
        return "ukupnaCena = " + ukupnaCena + ", " + "imePrezimeKupca = "
                + (imePrezimeKupca == null ? null : "'" + imePrezimeKupca + "'") + ", " + "datumIzdavanja = '"
                + datumIzdavanja + "'" + ", sezona = "
                + (sezona == null ? null : "'" + sezona + "'");
    }

    @Override
    public String vratiImeKlase() {
        return "SkiPas";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "sifraSkiPasa = " + sifraSkiPasa;
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        return "imePrezimeKupca LIKE '%" + imePrezimeKupca + "%'";
    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        sifraSkiPasa = rs.getLong("sifraSkiPasa");
        ukupnaCena = rs.getBigDecimal("ukupnaCena");
        imePrezimeKupca = rs.getString("imePrezimeKupca");
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
        return 0;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        if (i == 0) {
            StavkaSkiPasa stavkaSkiPasa = new StavkaSkiPasa();
            stavkaSkiPasa.setSkiPas(this);
            return stavkaSkiPasa;
        }
        return null;
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int j) {
        if (j < stavkeSkiPasa.size()) {
            stavkeSkiPasa.set(j, (StavkaSkiPasa) vezo);
        }
    }

    @Override
    public int vratiBrojSlogovaVezanogObjekta(int i) {
        if (i == 0) {
            return stavkeSkiPasa.size();
        }
        return 0;
    }

    @Override
    public void kreirajVezaniObjekat(int brojStavki, int i) {
        if (i == 0) {
            stavkeSkiPasa = new ArrayList<>();
            for (int j = 0; j < brojStavki; j++) {
                StavkaSkiPasa stavkaSkiPasa = new StavkaSkiPasa();
                stavkaSkiPasa.setSkiPas(this);
                stavkeSkiPasa.add(stavkaSkiPasa);
            }
        }
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
        return "ukupnaCena, imePrezimeKupca, datumIzdavanja, sezona";
    }

    public String getSezona() {
        return sezona;
    }

    public void setSezona(String sezona) {
        this.sezona = sezona;
    }
}
