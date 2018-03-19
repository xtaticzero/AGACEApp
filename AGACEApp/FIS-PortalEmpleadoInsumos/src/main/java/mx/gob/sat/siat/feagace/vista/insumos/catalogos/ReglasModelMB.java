/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.catalogos;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.Regla;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ReglasModelMB extends GruposModelMB {

    private static final long serialVersionUID = 3455926466832201040L;

    private List<Regla> lstReglas;
    private Regla reglaSeleccionada;
    private Regla newRegla;

    public List<Regla> getLstReglas() {
        return lstReglas;
    }

    public void setLstReglas(List<Regla> lstReglas) {
        this.lstReglas = lstReglas;
    }

    public Regla getReglaSeleccionada() {
        return reglaSeleccionada;
    }

    public void setReglaSeleccionada(Regla reglaSeleccionada) {
        this.reglaSeleccionada = reglaSeleccionada;
    }

    public Regla getNewRegla() {
        return newRegla;
    }

    public void setNewRegla(Regla newRegla) {
        this.newRegla = newRegla;
    }

}
