/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.empleado.bean;

import java.io.Serializable;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DetalleEmpleado implements Serializable {

    private static final long serialVersionUID = -7605811363612962843L;

    private Integer idCentral;
    private Integer idPerfil;
    private Integer idPuesto;
    private Integer idRol;
    private Integer idUnidadAdmin;

    public Integer getIdCentral() {
        return idCentral;
    }

    public void setIdCentral(Integer idCentral) {
        this.idCentral = idCentral;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Integer getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Integer idPuesto) {
        this.idPuesto = idPuesto;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdUnidadAdmin() {
        return idUnidadAdmin;
    }

    public void setIdUnidadAdmin(Integer idUnidadAdmin) {
        this.idUnidadAdmin = idUnidadAdmin;
    }

    @Override
    public String toString() {
        return "DetalleEmpleado{" + "idCentral=" + idCentral + ", idPerfil=" + idPerfil + ", idPuesto=" + idPuesto + ", idRol=" + idRol + ", idUnidadAdmin=" + idUnidadAdmin + '}';
    }

}
