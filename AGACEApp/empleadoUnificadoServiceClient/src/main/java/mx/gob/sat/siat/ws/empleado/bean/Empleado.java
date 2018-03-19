/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.empleado.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class Empleado implements Serializable {

    private static final long serialVersionUID = 8744132454378135989L;

    private String rfc;
    private String nombre;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String correo;
    private String descripcionPerfil;
    private String estatusEmpleado;
    private Date fechaBaja;
    private Date fechaCreacion;
    private Integer idSuplencia;
    private Integer numeroEmpleado;
    private Integer numeroJefeInmediato;

    private Integer idArea;
    private String area;
    private Integer idSede;
    private String sede;
    private Integer idAdmCentral;
    private String admCentral;
    private Integer idAdmGral;
    private String admGral;

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDescripcionPerfil() {
        return descripcionPerfil;
    }

    public void setDescripcionPerfil(String descripcionPerfil) {
        this.descripcionPerfil = descripcionPerfil;
    }

    public String getEstatusEmpleado() {
        return estatusEmpleado;
    }

    public void setEstatusEmpleado(String estatusEmpleado) {
        this.estatusEmpleado = estatusEmpleado;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdSuplencia() {
        return idSuplencia;
    }

    public void setIdSuplencia(Integer idSuplencia) {
        this.idSuplencia = idSuplencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(Integer numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public Integer getNumeroJefeInmediato() {
        return numeroJefeInmediato;
    }

    public void setNumeroJefeInmediato(Integer numeroJefeInmediato) {
        this.numeroJefeInmediato = numeroJefeInmediato;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.numeroEmpleado != null ? this.numeroEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        if (this.numeroEmpleado != other.numeroEmpleado && (this.numeroEmpleado == null || !this.numeroEmpleado.equals(other.numeroEmpleado))) {
            return false;
        }
        return true;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Integer getIdAdmCentral() {
        return idAdmCentral;
    }

    public void setIdAdmCentral(Integer idAdmCentral) {
        this.idAdmCentral = idAdmCentral;
    }

    public String getAdmCentral() {
        return admCentral;
    }

    public void setAdmCentral(String admCentral) {
        this.admCentral = admCentral;
    }

    public Integer getIdAdmGral() {
        return idAdmGral;
    }

    public void setIdAdmGral(Integer idAdmGral) {
        this.idAdmGral = idAdmGral;
    }

    public String getAdmGral() {
        return admGral;
    }

    public void setAdmGral(String admGral) {
        this.admGral = admGral;
    }

    @Override
    public String toString() {
        return "Empleado{" + "rfc=" + rfc + ", nombre=" + nombre + ", apellidoMaterno=" + apellidoMaterno + ", apellidoPaterno=" + apellidoPaterno + ", correo=" + correo + ", descripcionPerfil=" + descripcionPerfil + ", estatusEmpleado=" + estatusEmpleado + ", fechaBaja=" + fechaBaja + ", fechaCreacion=" + fechaCreacion + ", idSuplencia=" + idSuplencia + ", numeroEmpleado=" + numeroEmpleado + ", numeroJefeInmediato=" + numeroJefeInmediato + ", idArea=" + idArea + ", area=" + area + ", idSede=" + idSede + ", sede=" + sede + ", idAdmCentral=" + idAdmCentral + ", admCentral=" + admCentral + ", idAdmGral=" + idAdmGral + ", admGral=" + admGral + '}';
    }

}
