package rs.ac.bg.fon.np.sc.commonLib.komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Klasa cija je funkcija da prima podatke putem socketa
 *
 * @author UrosVesic
 */
public class Primalac {

    /**
     * Socket kroz koji se primaju podaci
     */
    Socket socket;

    public Primalac(Socket socket) {
        this.socket = socket;
    }

    /**
     * Metoda koja prima podatke putem socketa
     *
     * @return Objekat koji je primljen kao Object
     * @throws java.lang.Exception ukoliko dodje do greske prilikom citanja iz
     * socketa
     */
    public Object primi() throws Exception {
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (IOException ex) {
            socket.close();
            throw ex;

        }

    }

}
