package rs.ac.bg.fon.np.sc.komunikacija;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Posiljalac {
	Socket socket;

	public Posiljalac(Socket socket) {
		this.socket = socket;
	}

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
