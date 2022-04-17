package rs.ac.bg.fon.np.sc.komunikacija;

import com.google.gson.JsonObject;

public class Zahtev {
	private int operacija;
	private JsonObject parametar;

	public Zahtev() {
	}

	public Zahtev(int operacija, JsonObject parametar) {
		this.operacija = operacija;
		this.parametar = parametar;
	}

	public int getOperacija() {
		return operacija;
	}

	public void setOperacija(int operacija) {
		this.operacija = operacija;
	}

	public JsonObject getParametar() {
		return parametar;
	}

	public void setParametar(JsonObject parametar) {
		this.parametar = parametar;
	}
}
