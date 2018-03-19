/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.empleado.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import mx.gob.sat.siat.ws.empleado.client.impl.EmpleadoClientHelper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class INPC implements Serializable {

    private static final long serialVersionUID = 2239675442478645772L;

    private Integer anio;
    private BigDecimal indice;
    private Integer mes;
    private Date publicacion;

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public BigDecimal getIndice() {
        return indice;
    }

    public void setIndice(BigDecimal indice) {
        this.indice = indice;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Date getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Date publicacion) {
        this.publicacion = publicacion;
    }

    @Override
    public String toString() {
        return "INPC{" + "anio=" + anio + ", indice=" + indice + ", mes=" + mes + ", publicacion=" + EmpleadoClientHelper.getFormattedDateDDMMYYYY(publicacion) + '}';
    }

}
