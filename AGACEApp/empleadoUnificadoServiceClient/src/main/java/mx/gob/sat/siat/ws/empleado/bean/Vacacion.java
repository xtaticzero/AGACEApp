/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.empleado.bean;

import java.io.Serializable;
import java.util.Date;
import mx.gob.sat.siat.ws.empleado.client.impl.EmpleadoClientHelper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class Vacacion implements Serializable {

    private static final long serialVersionUID = -7323535065113181582L;

    private String nombreResolucionMiscelanea;
    private String numeroRegla;
    private String numeroResolucionModif;
    private String diaDescripcion;
    private Date fechaFinal;
    private Date fechaInicial;
    private Date fechaPublicacion;
    private Date fechaPublicacionDof;

    public String getNombreResolucionMiscelanea() {
        return nombreResolucionMiscelanea;
    }

    public void setNombreResolucionMiscelanea(String nombreResolucionMiscelanea) {
        this.nombreResolucionMiscelanea = nombreResolucionMiscelanea;
    }

    public String getNumeroRegla() {
        return numeroRegla;
    }

    public void setNumeroRegla(String numeroRegla) {
        this.numeroRegla = numeroRegla;
    }

    public String getNumeroResolucionModif() {
        return numeroResolucionModif;
    }

    public void setNumeroResolucionModif(String numeroResolucionModif) {
        this.numeroResolucionModif = numeroResolucionModif;
    }

    public String getDiaDescripcion() {
        return diaDescripcion;
    }

    public void setDiaDescripcion(String diaDescripcion) {
        this.diaDescripcion = diaDescripcion;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaPublicacionDof() {
        return fechaPublicacionDof;
    }

    public void setFechaPublicacionDof(Date fechaPublicacionDof) {
        this.fechaPublicacionDof = fechaPublicacionDof;
    }

    @Override
    public String toString() {
        return "Vacacion{" + "nombreResolucionMiscelanea=" + nombreResolucionMiscelanea + ", numeroRegla=" + numeroRegla + ", numeroResolucionModif=" + numeroResolucionModif + ", dDescripcion=" + diaDescripcion + ", fechaFinal=" + EmpleadoClientHelper.getFormattedDateDDMMYYYY(fechaFinal) + ", fechaInicial=" + EmpleadoClientHelper.getFormattedDateDDMMYYYY(fechaInicial) + ", fechaPublicacion=" + EmpleadoClientHelper.getFormattedDateDDMMYYYY(fechaPublicacion) + ", fechaPublicacionDof=" + EmpleadoClientHelper.getFormattedDateDDMMYYYY(fechaPublicacionDof) + '}';
    }

}
