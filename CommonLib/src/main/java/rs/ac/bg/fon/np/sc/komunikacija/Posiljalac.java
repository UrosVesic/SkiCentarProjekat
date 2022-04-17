package rs.ac.bg.fon.np.sc.komunikacija;

import java.net.Socket;

public class Posiljalac {
	Socket socket;

	public Posiljalac(Socket socket) {
		this.socket = socket;
	}
}
