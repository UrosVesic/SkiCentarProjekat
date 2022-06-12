package rs.ac.bg.fon.np.sc.commonLib.komunikacija;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Klasa cija je funkcija da salje podatke putem socketa
 *
 * @author UrosVesic
 */
public class Posiljalac {

    /**
     * Socket kroz koji se salju podaci
     */
    Socket socket;

    public Posiljalac(Socket socket) {
        this.socket = socket;
    }

    /**
     * Metoda koja salje podatke putem socketa
     *
     * @param object Objekat koji ce biti poslat putem socketa
     */
    public void posalji(Object object) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            out.writeObject(object);
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
