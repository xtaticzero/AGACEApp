/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.helper;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class PropuestaIndividualAttributeHelper extends PropuestaIndividualAttributeAbstract {
    private static final long serialVersionUID = 5113738085465683851L;
    
    private BigDecimal idAraceSeleccionada;
    private BigDecimal idMetodoSeleccionado;
    private BigDecimal idPrioridadSeleccionada;
    private BigDecimal areaOrigen;
    private Date fechaActualImpuesto;
    private Date fechaActualPeriodo;
    private Calendar calendario = Calendar.getInstance();
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/M/yyyy");
    
    public void setCalendario(Calendar calendario) {
        this.calendario = calendario;
    }

    public Calendar getCalendario() {
        return calendario;
    }

    public void setFormatoFecha(SimpleDateFormat formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    public SimpleDateFormat getFormatoFecha() {
        return formatoFecha;
    }

    public void setIdAraceSeleccionada(BigDecimal idAraceSeleccionada) {
        this.idAraceSeleccionada = idAraceSeleccionada;
    }

    public BigDecimal getIdAraceSeleccionada() {
        return idAraceSeleccionada;
    }

    public void setIdMetodoSeleccionado(BigDecimal idMetodoSeleccionado) {
        this.idMetodoSeleccionado = idMetodoSeleccionado;
    }

    public BigDecimal getIdMetodoSeleccionado() {
        return idMetodoSeleccionado;
    }

    public void setFechaActualImpuesto(Date fechaActualImpuesto) {
        this.fechaActualImpuesto = fechaActualImpuesto != null ? new Date(fechaActualImpuesto.getTime()) : null;
    }

    public Date getFechaActualImpuesto() {
        return (fechaActualImpuesto != null) ? (Date) fechaActualImpuesto.clone() : null;
    }

    public void setFechaActualPeriodo(Date fechaActualPeriodo) {
        this.fechaActualPeriodo = fechaActualPeriodo != null ? new Date(fechaActualPeriodo.getTime()) : null;
    }

    public Date getFechaActualPeriodo() {
        return (fechaActualPeriodo != null) ? (Date) fechaActualPeriodo.clone() : null;
    }
    
    public void setAreaOrigen(final BigDecimal areaOrigen) {
        this.areaOrigen = areaOrigen;
    }

    public BigDecimal getAreaOrigen() {
        return areaOrigen;
    }
    
    public void setIdPrioridadSeleccionada(BigDecimal idPrioridadSeleccionada) {
        this.idPrioridadSeleccionada = idPrioridadSeleccionada;
    }

    public BigDecimal getIdPrioridadSeleccionada() {
        return idPrioridadSeleccionada;
    }
}
