package mx.gob.sat.siat.feagace.vista.model.insumos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CrearInsumoBasicAbstract implements Serializable {

    private static final long serialVersionUID = 2L;

    private boolean bloquearPaneles;
    private boolean bloquearRFC;
    private boolean visualizaDetalle;
    private boolean bloqueaPanelesDetalle;
    private int insumosAgregados;
    private String mensajeAntecedentes;
    private String mensajeServicios;
    private String strFechaHoy;
    private String mensajeValidacion;
    private Date fechaHoy;
    private BigDecimal contadorRegistros;
    
    public String getMensajeServicios() {
        return mensajeServicios;
    }

    public void setMensajeServicios(String mensajeServicios) {
        this.mensajeServicios = mensajeServicios;
    }

    public void setInsumosAgregados(int insumosAgregados) {
        this.insumosAgregados = insumosAgregados;
    }

    public int getInsumosAgregados() {
        return insumosAgregados;
    }

    public void setBloquearPaneles(boolean bloquearPaneles) {
        this.bloquearPaneles = bloquearPaneles;
    }

    public boolean isBloquearPaneles() {
        return bloquearPaneles;
    }

    public void setMensajeAntecedentes(String mensajeAntecedentes) {
        this.mensajeAntecedentes = mensajeAntecedentes;
    }

    public String getMensajeAntecedentes() {
        return mensajeAntecedentes;
    }

    public void setFechaHoy(Date fechaHoy) {
        this.fechaHoy = fechaHoy != null ? new Date(fechaHoy.getTime()) : null;
    }

    public Date getFechaHoy() {
        return (fechaHoy != null) ? (Date) fechaHoy.clone() : null;
    }

    public void setStrFechaHoy(String strFechaHoy) {
        this.strFechaHoy = strFechaHoy;
    }

    public String getStrFechaHoy() {
        return strFechaHoy;
    }

    public void setContadorRegistros(BigDecimal contadorRegistros) {
        this.contadorRegistros = contadorRegistros;
    }

    public BigDecimal getContadorRegistros() {
        return contadorRegistros;
    }

    public void setMensajeValidacion(String mensajeValidacion) {
        this.mensajeValidacion = mensajeValidacion;
    }

    public String getMensajeValidacion() {
        return mensajeValidacion;
    }

    public boolean isVisualizaDetalle() {
        return visualizaDetalle;
    }

    public void setVisualizaDetalle(boolean visualizaDetalle) {
        this.visualizaDetalle = visualizaDetalle;
    }

    public boolean isBloqueaPanelesDetalle() {
        return bloqueaPanelesDetalle;
    }

    public void setBloqueaPanelesDetalle(boolean bloqueaPanelesDetalle) {
        this.bloqueaPanelesDetalle = bloqueaPanelesDetalle;
    }

    public boolean isBloquearRFC() {
        return bloquearRFC;
    }

    public void setBloquearRFC(boolean bloquearRFC) {
        this.bloquearRFC = bloquearRFC;
    }
}
