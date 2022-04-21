package rs.ac.bg.fon.np.sc.commonlib.komunikacija;

import com.google.gson.JsonObject;
import java.io.Serializable;

public class Odgovor implements Serializable{

    private boolean uspesno;
    private String rezultat;
    private Exception exception;

    public Odgovor() {
    }

    public Odgovor(boolean uspesno, String rezultat, Exception exception) {
        this.uspesno = uspesno;
        this.rezultat = rezultat;
        this.exception = exception;
    }

    public boolean isUspesno() {
        return uspesno;
    }

    public void setUspesno(boolean uspesno) {
        this.uspesno = uspesno;
    }

    public String getRezultat() {
        return rezultat;
    }

    public void setRezultat(String rezultat) {
        this.rezultat = rezultat;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
