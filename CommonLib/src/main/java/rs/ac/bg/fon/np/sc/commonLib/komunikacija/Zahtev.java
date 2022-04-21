package rs.ac.bg.fon.np.sc.commonlib.komunikacija;

import java.io.Serializable;

public class Zahtev implements Serializable{

    private int operacija;
    private String parametar;

    public Zahtev() {
    }

    public Zahtev(int operacija, String parametar) {
        this.operacija = operacija;
        this.parametar = parametar;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public String getParametar() {
        return parametar;
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
    }

    
}
