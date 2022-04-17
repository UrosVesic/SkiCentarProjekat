package rs.ac.bg.fon.np.sc.komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.google.gson.JsonObject;

public class Primalac {
	Socket socket;

	public Primalac(Socket socket) {
		this.socket = socket;
	}
	
	public JsonObject primi() throws Exception {
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(socket.getInputStream());
			return (JsonObject) in.readObject();
		} catch (IOException ex) {
			socket.close();
			throw ex;

		}

	}
	
}
