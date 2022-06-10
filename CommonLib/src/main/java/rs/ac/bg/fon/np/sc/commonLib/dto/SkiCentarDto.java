/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.commonlib.dto;

import com.google.gson.annotations.Expose;
import java.util.List;
import rs.ac.bg.fon.np.sc.commonlib.domen.SkiCentar;
import rs.ac.bg.fon.np.sc.commonlib.domen.Staza;
import rs.ac.bg.fon.np.sc.commonlib.domen.Zicara;

/**
 *
 * @author UrosVesic
 */
public class SkiCentarDto {

    @Expose
    private SkiCentar skiCentar;
    @Expose
    private List<Staza> staze;
    @Expose
    private List<Zicara> zicare;

    public SkiCentarDto() {
    }

    public SkiCentarDto(SkiCentar skiCentar, List<Staza> staze, List<Zicara> zicare) {
        this.skiCentar = skiCentar;
        this.staze = staze;
        this.zicare = zicare;
    }

    public SkiCentar getSkiCentar() {
        return skiCentar;
    }

    public void setSkiCentar(SkiCentar skiCentar) {
        this.skiCentar = skiCentar;
    }

    public List<Staza> getStaze() {
        return staze;
    }

    public void setStaze(List<Staza> staze) {
        this.staze = staze;
    }

    public List<Zicara> getZicare() {
        return zicare;
    }

    public void setZicare(List<Zicara> zicare) {
        this.zicare = zicare;
    }

}
