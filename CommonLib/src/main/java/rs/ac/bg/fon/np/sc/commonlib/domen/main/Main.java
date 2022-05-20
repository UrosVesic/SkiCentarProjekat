/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.commonlib.domen.main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import rs.ac.bg.fon.np.sc.commonlib.domen.Korisnik;
import rs.ac.bg.fon.np.sc.commonlib.domen.Kupac;
import rs.ac.bg.fon.np.sc.commonlib.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiCentar;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiKarta;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiPas;
import rs.ac.bg.fon.np.sc.commonlib.domen.StavkaSkiPasa;
import rs.ac.bg.fon.np.sc.commonlib.domen.Staza;
import rs.ac.bg.fon.np.sc.commonlib.domen.VrstaSkiKarte;
import rs.ac.bg.fon.np.sc.commonlib.domen.Zicara;

/**
 *
 * @author UrosVesic
 */
public class Main {

    public static void main(String[] args) {
        OpstiDomenskiObjekat odo = new Zicara(0, "Pancicev vrh", "09-17", 2000, true, new SkiCentar(2));
        System.out.println(odo.vratiVrednostiAtributa());
        System.out.println(odo.postaviVrednostiAtributa());
    }
}
