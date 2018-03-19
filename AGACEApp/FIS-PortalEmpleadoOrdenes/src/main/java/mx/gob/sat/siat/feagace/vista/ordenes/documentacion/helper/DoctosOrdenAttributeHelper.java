/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.primefaces.component.tabview.TabView;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DoctosOrdenAttributeHelper extends DoctosOrdenBooleanHelper {

    private static final long serialVersionUID = 2502744346027223852L;

    private transient TabView tabViewOficios;

    private int numeroDocumentoPapelTrabajo;
    private int numeroDocumentoPapelTrabajoOficio;
    private int tipoMedidaApremio;
    private String tipoMetodo;
    private String rutaArchivo;
    private String nombreArchivo;
    private String fieldsetActivoPapelTrabajo;
    private String justificacion;
    private String justificacionRechazo;
    private String nombreDocumentoOrden;
    private BigDecimal idTipoOficio;
    private BigDecimal idPromocionSeleccionada;
    private BigDecimal idEmpleadoJefeDepartamento;
    private BigDecimal idEmpleadoEnlace;
    private BigDecimal idEmpleadoJefeDepartamentoAnterior;
    private BigDecimal idEmpleadoEnlaceAnterior;
    private Date fechaEnvioPromocionSeleccionada;
    private Date fechaActual;
    private Date fechaReactivarPlazo;
    private Date fechaCargaPapelTrabajo;
    private FecetDocOrden expediente;
    private List<FecetTipoOficio> listCompulsas;
    private Map<String, BigDecimal> tipoOficioEnum;

    public BigDecimal getIdEmpleadoEnlace() {
        return idEmpleadoEnlace;
    }

    public void setIdEmpleadoEnlace(BigDecimal idEmpleadoEnlace) {
        this.idEmpleadoEnlace = idEmpleadoEnlace;
    }

    public BigDecimal getIdEmpleadoJefeDepartamento() {
        return idEmpleadoJefeDepartamento;
    }

    public void setIdEmpleadoJefeDepartamento(BigDecimal idEmpleadoJefeDepartamento) {
        this.idEmpleadoJefeDepartamento = idEmpleadoJefeDepartamento;
    }

    public String getJustificacionRechazo() {
        return justificacionRechazo;
    }

    public void setJustificacionRechazo(String justificacionRechazo) {
        this.justificacionRechazo = justificacionRechazo;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    private String autoridadCompulsada;

    public int getTipoMedidaApremio() {
        return tipoMedidaApremio;
    }

    public void setTipoMedidaApremio(int tipoMedidaApremio) {
        this.tipoMedidaApremio = tipoMedidaApremio;
    }

    public String getNombreDocumentoOrden() {
        return nombreDocumentoOrden;
    }

    public void setNombreDocumentoOrden(String nombreDocumentoOrden) {
        this.nombreDocumentoOrden = nombreDocumentoOrden;
    }

    public Date getFechaReactivarPlazo() {
        return (fechaReactivarPlazo != null) ? (Date) fechaReactivarPlazo.clone() : null;
    }

    public void setFechaReactivarPlazo(Date fechaReactivarPlazo) {
        this.fechaReactivarPlazo = fechaReactivarPlazo != null ? new Date(fechaReactivarPlazo.getTime()) : null;
    }

    private BigDecimal idOficioReactivacion;

    public BigDecimal getIdOficioReactivacion() {
        return idOficioReactivacion;
    }

    public void setIdOficioReactivacion(BigDecimal idOficioReactivacion) {
        this.idOficioReactivacion = idOficioReactivacion;
    }

    public void setTipoMetodo(String tipoMetodo) {
        this.tipoMetodo = tipoMetodo;
    }

    public String getTipoMetodo() {
        return tipoMetodo;
    }

    public void setIdPromocionSeleccionada(BigDecimal idPromocionSeleccionada) {
        this.idPromocionSeleccionada = idPromocionSeleccionada;
    }

    public BigDecimal getIdPromocionSeleccionada() {
        return idPromocionSeleccionada;
    }

    public void setFechaEnvioPromocionSeleccionada(Date fechaEnvioPromocionSeleccionada) {
        this.fechaEnvioPromocionSeleccionada = (fechaEnvioPromocionSeleccionada != null)
                ? (Date) fechaEnvioPromocionSeleccionada : null;
    }

    public Date getFechaEnvioPromocionSeleccionada() {
        return (fechaEnvioPromocionSeleccionada != null) ? (Date) fechaEnvioPromocionSeleccionada : null;
    }

    public void setListCompulsas(List<FecetTipoOficio> listCompulsas) {
        this.listCompulsas = listCompulsas;
    }

    public List<FecetTipoOficio> getListCompulsas() {
        return listCompulsas;
    }

    public TabView getTabViewOficios() {
        return tabViewOficios;
    }

    public void setTabViewOficios(TabView tabViewOficios) {
        this.tabViewOficios = tabViewOficios;
    }

    public void setFechaActual(final Date fechaActual) {
        this.fechaActual = (fechaActual != null) ? (Date) fechaActual : null;
    }

    public Date getFechaActual() {
        return (fechaActual != null) ? (Date) fechaActual : null;
    }

    public Date getFechaCargaPapelTrabajo() {
        return (fechaCargaPapelTrabajo != null) ? (Date) fechaCargaPapelTrabajo : null;
    }

    public void setFechaCargaPapelTrabajo(Date fechaCargaPapelTrabajo) {
        this.fechaCargaPapelTrabajo = (fechaCargaPapelTrabajo != null) ? (Date) fechaCargaPapelTrabajo : null;
    }

    public int getNumeroDocumentoPapelTrabajo() {
        return numeroDocumentoPapelTrabajo;
    }

    public void setNumeroDocumentoPapelTrabajo(int numeroDocumentoPapelTrabajo) {
        this.numeroDocumentoPapelTrabajo = numeroDocumentoPapelTrabajo;
    }

    public int getNumeroDocumentoPapelTrabajoOficio() {
        return numeroDocumentoPapelTrabajoOficio;
    }

    public void setNumeroDocumentoPapelTrabajoOficio(int numeroDocumentoPapelTrabajoOficio) {
        this.numeroDocumentoPapelTrabajoOficio = numeroDocumentoPapelTrabajoOficio;
    }

    public Map<String, BigDecimal> getTipoOficioEnum() {
        return tipoOficioEnum;
    }

    public void setTipoOficioEnum(Map<String, BigDecimal> tipoOficioEnum) {
        this.tipoOficioEnum = tipoOficioEnum;
    }

    public BigDecimal getIdTipoOficio() {
        return idTipoOficio;
    }

    public void setIdTipoOficio(BigDecimal idTipoOficio) {
        this.idTipoOficio = idTipoOficio;
    }

    public String getFieldsetActivoPapelTrabajo() {
        return fieldsetActivoPapelTrabajo;
    }

    public void setFieldsetActivoPapelTrabajo(String fieldsetActivoPapelTrabajo) {
        this.fieldsetActivoPapelTrabajo = fieldsetActivoPapelTrabajo;
    }

    public final FecetDocOrden getExpediente() {
        return expediente;
    }

    public final void setExpediente(FecetDocOrden expediente) {
        this.expediente = expediente;
    }

    public final String getRutaArchivo() {
        return rutaArchivo;
    }

    public final void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public final String getNombreArchivo() {
        return nombreArchivo;
    }

    public final void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getAutoridadCompulsada() {
        return autoridadCompulsada;
    }

    public void setAutoridadCompulsada(String autoridadCompulsada) {
        this.autoridadCompulsada = autoridadCompulsada;
    }

    public BigDecimal getIdEmpleadoJefeDepartamentoAnterior() {
        return idEmpleadoJefeDepartamentoAnterior;
    }

    public void setIdEmpleadoJefeDepartamentoAnterior(
            BigDecimal idEmpleadoJefeDepartamentoAnterior) {
        this.idEmpleadoJefeDepartamentoAnterior = idEmpleadoJefeDepartamentoAnterior;
    }

    public BigDecimal getIdEmpleadoEnlaceAnterior() {
        return idEmpleadoEnlaceAnterior;
    }

    public void setIdEmpleadoEnlaceAnterior(BigDecimal idEmpleadoEnlaceAnterior) {
        this.idEmpleadoEnlaceAnterior = idEmpleadoEnlaceAnterior;
    }

}
