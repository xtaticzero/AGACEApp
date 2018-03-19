/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.nyv.client.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class BitacoraVO implements Serializable {

    private static final long serialVersionUID = -5159008953412966287L;
    private String estatus;
    private Date fechaAlta;
    private String numeroEmpleado;

    public BitacoraVO() {
    }

    public BitacoraVO(mx.gob.sat.siat.ws.nyv.v1.bean.BitacoraVOXml bitacora) {
        if (bitacora != null) {
            estatus = bitacora.getEstatus();
            fechaAlta = bitacora.getFechaAlta().toGregorianCalendar().getTime();
            numeroEmpleado = bitacora.getNumeroEmpleado();
        }
    }

    public BitacoraVO(mx.gob.sat.siat.ws.nyv.v2.bean.BitacoraVOXml bitacora) {
        if (bitacora != null) {
            estatus = bitacora.getEstatus();
            fechaAlta = bitacora.getFechaAlta().toGregorianCalendar().getTime();
            numeroEmpleado = bitacora.getNumeroEmpleado();
        }
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    @Override
    public String toString() {
        return "BitacoraVO{" + "estatus=" + estatus + ", fechaAlta=" + fechaAlta + ", numeroEmpleado=" + numeroEmpleado + '}';
    }

}
