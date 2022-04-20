package rs.ac.bg.fon.np.sc.commonlib.komunikacija;

import com.google.gson.JsonObject;

public class Odgovor {
	private boolean uspesno;
	private JsonObject rezultat;
	private Exception exception;
	private int izvrsenaOperacija;

	public Odgovor() {
	}

	public Odgovor(boolean uspesno, JsonObject rezultat, Exception exception, int izvrsenaOperacija) {
		this.uspesno = uspesno;
		this.rezultat = rezultat;
		this.exception = exception;
		this.izvrsenaOperacija = izvrsenaOperacija;
	}

	public int getIzvrsenaOperacija() {
		return izvrsenaOperacija;
	}

	public void setIzvrsenaOperacija(int izvrsenaOperacija) {
		this.izvrsenaOperacija = izvrsenaOperacija;
	}

	public boolean isUspesno() {
		return uspesno;
	}

	public void setUspesno(boolean uspesno) {
		this.uspesno = uspesno;
	}

	public JsonObject getRezultat() {
		return rezultat;
	}

	public void setRezultat(JsonObject rezultat) {
		this.rezultat = rezultat;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
}
