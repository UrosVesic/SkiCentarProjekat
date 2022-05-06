package rs.ac.bg.fon.np.sc.commonlib.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Korisnik implements OpstiDomenskiObjekat, Serializable {

    private long id;
    private String ime;
    private String prezime;
    private String email;
    private String sifra;

    public Korisnik() {
    }

    public Korisnik(String ime, String prezime, String email, String sifra) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.sifra = sifra;
    }

    public Korisnik(long id, String ime, String prezime, String email, String sifra) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.sifra = sifra;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
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
        final Korisnik other = (Korisnik) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "'" + email + "', '" + sifra + "', '" + ime + "', '" + prezime + "'";
    }

    @Override
    public String postaviVrednostiAtributa() {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
        // Tools | Templates.
    }

    @Override
    public String vratiImeKlase() {
        return "Korisnik";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "email LIKE'" + email + "' AND sifra LIKE '" + sifra + "'";
    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        ime = rs.getString("ime");
        prezime = rs.getString("prezime");
        email = rs.getString("email");
        sifra = rs.getString("sifra");
    }

    @Override
    public String vratiNazivPK() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public OpstiDomenskiObjekat kreirajInstancu() {
        // TODO Auto-generated method stub
        return new Korisnik();
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public OpstiDomenskiObjekat vratiVezaniObjekat(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void postaviVrednostPK(long id) {
        this.id = id;
    }

    @Override
    public String vratiImenaAtrubita() {
        return "ime, prezime, email, sifra";
    }

}
