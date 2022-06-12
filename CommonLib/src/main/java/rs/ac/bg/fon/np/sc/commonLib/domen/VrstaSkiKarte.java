/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.commonLib.domen;

import com.google.gson.annotations.SerializedName;

/**
 * Enum koji predstavlja vrstu ski karte odnosno broj dana njenog trajanja
 *
 * @author UrosVesic
 */
public enum VrstaSkiKarte {
    @SerializedName("Jednodnevna")
    JEDNODNEVNA(1),
    @SerializedName("Dvodnevna")
    DVODNEVNA(2),
    @SerializedName("Trodnevna")
    TRODNEVNA(3),
    @SerializedName("Cetvorodnevna")
    CETVORODNEVNA(4),
    @SerializedName("Petodnevna")
    PETODNEVNA(5),
    @SerializedName("Sestodnevna")
    SESTODNEVNA(6),
    @SerializedName("Sedmodnevna")
    SEDMODNEVNA(7);
    /**
     * Broj dana trajanja ski karte
     */
    private final int brojDana;

    private VrstaSkiKarte(int dani) {
        this.brojDana = dani;
    }

    public int getBrojDana() {
        return brojDana;
    }

}
