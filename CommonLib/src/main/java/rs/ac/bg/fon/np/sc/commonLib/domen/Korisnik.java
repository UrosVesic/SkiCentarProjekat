/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.commonLib.domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Domenska klasa koja predstavlja korisnika sistema
 *
 * @author UrosVesic
 */
public class Korisnik implements OpstiDomenskiObjekat {

    /**
     * Jedinsveni identifikator korisnika u bazi podataka
     */
    private long id;
    /**
     * Ime korisnika
     */
    private String ime;
    /**
     * Prezime korisnika
     */
    private String prezime;
    /**
     * Email korisnika
     */
    private String email;
    /**
     * Sifra korisnika
     */
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

    /**
     * Vraca vrednost id-a
     *
     * @return id kao long
     */
    public long getId() {
        return id;
    }

    /**
     * Postavlja vrednost id-a na zadatu vrednost
     *
     * @param id vrednost na koju treba postaviti polje id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Vraca vrednost sifre
     *
     * @return sifru kao String
     */
    public String getSifra() {
        return sifra;
    }

    /**
     * Postavlja vrednost sifre na zadatu vrednost
     *
     * @param sifra vrednost na koju treba postaviti polje sifra
     */
    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    /**
     * Vraca ime korisnika
     *
     * @return Ime kao String
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja vrednost imena na zadatu vrednost
     *
     * @param ime vrednost na koju treba postaviti polje ime
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * Vraca prezime korisnika
     *
     * @return Prezime kao String
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja vrednost prezimena na zadatu vrednost
     *
     * @param ime vrednost na koju treba postaviti polje prezime
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     * Vraca email korisnika
     *
     * @return Email kao String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Postavlja vrednost email-a na zadatu vrednost
     *
     * @param email vrednost na koju treba postaviti polje email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Vraca sve podatke o korisniku u jednom Stringu
     * @return String sa svim podacima o korisniku
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    /**
     * Poredi korisnike po atributu emaol
     * @param obj objekat sa kojim se poredi this objekat
     * @return 
	 * <ul>
	 * <li>true - email-ovi isti</li>
	 * <li>false - ako su email-ovi razliciti</li>
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
        final Korisnik other = (Korisnik) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return (email == null ? null : "'" + email + "'") + ", " + (sifra == null ? null : "'" + sifra + "'") + ", "
                + (ime == null ? null : "'" + ime + "'") + ", " + (prezime == null ? null : "'" + prezime + "'");
    }

    @Override
    public String postaviVrednostiAtributa() {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
        // Tools | Templates.
    }

    @Override
    public String vratiImeTabeleZaKlasu() {
        return "Korisnik";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "email LIKE'" + email + "' AND sifra LIKE '" + sifra + "'";
    }

    @Override
    public void napuni(ResultSet rs) throws SQLException {
        id = rs.getLong("id");
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
