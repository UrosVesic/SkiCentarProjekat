/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.commonLib.domen;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import rs.ac.bg.fon.np.sc.commonlib.domen.OpstiDomenskiObjekat;

/**
 *
 * @author UrosVesic
 */
public class Kupac implements Serializable, OpstiDomenskiObjekat {

    @Expose
    private long idKupca;
    @Expose
    private String brojLK;
    @Expose
    private String ime;
    @Expose
    private String prezime;

    public Kupac() {
    }

    public Kupac(long idKupca, String brojLK, String ime, String prezime) {
        this.idKupca = idKupca;
        this.brojLK = brojLK;
        this.ime = ime;
        this.prezime = prezime;
    }

    @Override
    public String vratiImenaAtrubita() {
        return "brojLK, Ime, Prezime";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return (brojLK == null ? null : "'" + brojLK + "'") + ", "
                + (ime == null ? null : "'" + ime + "'") + ", "
                + (prezime == null ? null : "'" + prezime + "'");
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "brojLK = "
                + (brojLK == null ? null : "'" + brojLK + "'") + ", " + "ime = "
                + (ime == null ? null : "'" + ime + "'") + ", " + "prezime = "
                + (prezime == null ? null : "'" + prezime + "'");
    }

    @Override
    public String vratiImeKlase() {
        return "Kupac";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "idKupca= " + idKupca;
    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        this.setIdKupca(rs.getLong("idKupca"));
        this.setBrojLK(rs.getString("brojLK"));
        this.setIme(rs.getString("ime"));
        this.setPrezime(rs.getString("prezime"));
    }

    @Override
    public String vratiNazivPK() {
        return "idKupca";
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        return new Kupac();
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 0;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        return null;
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postaviVrednostPK(long id) {
        this.setIdKupca(id);
    }

    public long getIdKupca() {
        return idKupca;
    }

    public void setIdKupca(long idKupca) {
        this.idKupca = idKupca;
    }

    public String getBrojLK() {
        return brojLK;
    }

    public void setBrojLK(String brojLK) {
        this.brojLK = brojLK;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

}