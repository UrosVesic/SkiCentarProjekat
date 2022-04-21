/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.commonLib.domen;

/**
 *
 * @author UrosVesic
 */
public enum VrstaSkiKarte {
    JEDNODNEVNA(1), DVODNEVNA(2), TRODNEVNA(3), CETVORODNEVNA(4), PETODNEVNA(5), SESTODNEVNA(6), SEDMODNEVNA(7);
    private final int brojDana;

    private VrstaSkiKarte(int dani) {
        this.brojDana = dani;
    }

    public int getBrojDana() {
        return brojDana;
    }

}
