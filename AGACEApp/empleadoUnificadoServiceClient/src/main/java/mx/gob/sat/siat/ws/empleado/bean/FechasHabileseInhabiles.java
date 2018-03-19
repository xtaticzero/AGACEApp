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
public class FechasHabileseInhabiles implements Serializable {

    private static final long serialVersionUID = 7914716477528247360L;

    private Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "FechasHabileseInhabiles{" + "fecha=" + EmpleadoClientHelper.getFormattedDateDDMMYYYY(fecha) + '}';
    }

}
