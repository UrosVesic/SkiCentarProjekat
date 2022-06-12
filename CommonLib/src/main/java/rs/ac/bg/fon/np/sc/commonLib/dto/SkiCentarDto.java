/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.commonLib.dto;

import com.google.gson.annotations.Expose;
import java.util.List;
import rs.ac.bg.fon.np.sc.commonLib.domen.SkiCentar;
import rs.ac.bg.fon.np.sc.commonLib.domen.Staza;
import rs.ac.bg.fon.np.sc.commonLib.domen.Zicara;

/**
 * Klasa koja predstavlja ski centar sa prosirenim informacijama. Ima referencu
 * na domenski objekat ski centar i listu staza i zicara.
 *
 * @author UrosVesic
 * @see rs.ac.bg.fon.np.sc.commonLib.domen.SkiCentar
 */
public class SkiCentarDto {

    /**
     * Domenski objekat ski centar na koji se odnosi dto objekat
     */
    @Expose
    private SkiCentar skiCentar;
    /**
     * Lista staza u ski centru
     */
    @Expose
    private List<Staza> staze;
    /**
     * Lista zicara u ski centru
     */
    @Expose
    private List<Zicara> zicare;

    public SkiCentarDto() {
    }

    public SkiCentarDto(SkiCentar skiCentar, List<Staza> staze, List<Zicara> zicare) {
        this.skiCentar = skiCentar;
        this.staze = staze;
        this.zicare = zicare;
    }

    /**
     * Vraca domenski objekat ski centar za koji je vezan dto objekat
     *
     * @return Ski centar kao objekat klase SkiCentar
     */
    public SkiCentar getSkiCentar() {
        return skiCentar;
    }

    /**
     * Postavlja vrednost polja skiCentar na zadatu vrednost
     *
     * @param skiCentar Vrednost na koju treba postaviti polje skiCentar
     */
    public void setSkiCentar(SkiCentar skiCentar) {
        this.skiCentar = skiCentar;
    }

    /**
     * Vraca staze koje se nalaze u zadatom ski centru
     *
     * @return Staze kao java.util.List
     */
    public List<Staza> getStaze() {
        return staze;
    }

    /**
     * Postavlja vrednost polja staze na zadatu vrednost
     *
     * @param staze Vrednost na koju treba postaviti polje staze
     */
    public void setStaze(List<Staza> staze) {
        this.staze = staze;
    }

    /**
     * Vraca zicare koje se nalaze u zadatom ski centru
     *
     * @return Staze kao java.util.List
     */
    public List<Zicara> getZicare() {
        return zicare;
    }

    /**
     * Postavlja vrednost polja zicare na zadatu vrednost
     *
     * @param zicare Vrednost na koju treba postaviti polje zicare
     */
    public void setZicare(List<Zicara> zicare) {
        this.zicare = zicare;
    }

}
