package rs.ac.bg.fon.np.sc.commonlib.domen;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OpstiDomenskiObjekat {

    String vratiImenaAtrubita();

    String vratiVrednostiAtributa();

    String postaviVrednostiAtributa();

    String vratiImeKlase();

    String vratiUslovZaNadjiSlog();

    void napuni(ResultSet rs) throws SQLException;

    String vratiNazivPK();

    public OpstiDomenskiObjekat kreirajInstancu();

    public int vratiBrojVezanihObjekata();

    public OpstiDomenskiObjekat vratiVezaniObjekat(int i);

    public void postaviVrednostVezanogObjekta(OpstiDomenskiObjekat vezo, int i);

    default String vratiUslovZaPromeniSlog() {
        return vratiUslovZaNadjiSlog();
    }

    default int vratiBrojSlogovaVezanogObjekta(int i) {
        return 1;
    }

    default OpstiDomenskiObjekat vratiSlogVezanogObjekta(int i, int j) {
        return null;
    }

    public default String vratiUslovZaNadjiSlogove() {
        return vratiUslovZaNadjiSlog();
    }

    public default void kreirajVezaniObjekat(int brojStavki, int i) {

    }

    public void postaviVrednostPK(long id);

}
