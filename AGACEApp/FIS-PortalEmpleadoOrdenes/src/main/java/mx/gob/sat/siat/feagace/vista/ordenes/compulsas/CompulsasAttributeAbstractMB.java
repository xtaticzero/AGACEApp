package mx.gob.sat.siat.feagace.vista.ordenes.compulsas;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

public class CompulsasAttributeAbstractMB extends CompulsasAbstractMB {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Boolean recargarInformacion;
    private Boolean bloquearPaneles;
    private Boolean deshabilitaEmailColaborador;
    private Boolean mostrarPanelRepLegal;
    private Boolean mostrarBotonBuscarRepLegal;
    private Boolean mostrarBotonLimpiarRepLegal;
    private Boolean deshabilitaCapturaRepLegal;
    
    private Integer tipoCompulsa;
    private String mensajeValidacion;
    private String rfcRepresentanteLegal;
    private Map<String,BigDecimal> tipoOficioEnum;
    private List<DocumentoOrdenModel> listaPapelesTrabajoOficio;
    private int numeroDocumentoPapelTrabajoOficio;
    private BigDecimal idTipoOficio;
    private DocumentoOrdenModel papelTrabajoSeleccionado;
    private String fieldsetActivoPapelTrabajo;
    
    
    public void cargarTipoOficioSegunTab(){
        Map<String,BigDecimal> oficio = new HashMap<String, BigDecimal>();
        oficio.put("fltCompulsaOtrasAutoridades", new BigDecimal(Constantes.ENTERO_DIECISEIS));
        oficio.put("fltCompulsaTerceros", new BigDecimal(Constantes.ENTERO_UNO));
        setTipoOficioEnum(oficio);
    }
    
    
    public void setRecargarInformacion(final Boolean recargarInformacion) {
        this.recargarInformacion = recargarInformacion;
    }

    public Boolean getRecargarInformacion() {
        return recargarInformacion;
    }
    
    public void setMensajeValidacion(final String mensajeValidacion) {
        this.mensajeValidacion = mensajeValidacion;
    }

    public String getMensajeValidacion() {
        return mensajeValidacion;
    }

    public void setTipoCompulsa(Integer tipoCompulsa) {
        this.tipoCompulsa = tipoCompulsa;
    }

    public Integer getTipoCompulsa() {
        return tipoCompulsa;
    }

    public void setBloquearPaneles(final Boolean bloquearPaneles) {
        this.bloquearPaneles = bloquearPaneles;
    }

    public Boolean getBloquearPaneles() {
        return bloquearPaneles;
    }
    
    public void setRfcRepresentanteLegal(final String rfcRepresentanteLegal) {
        this.rfcRepresentanteLegal = rfcRepresentanteLegal;
    }

    public String getRfcRepresentanteLegal() {
        return rfcRepresentanteLegal;
    }

    public void setDeshabilitaEmailColaborador(final Boolean deshabilitaEmailColaborador) {
        this.deshabilitaEmailColaborador = deshabilitaEmailColaborador;
    }

    public Boolean getDeshabilitaEmailColaborador() {
        return deshabilitaEmailColaborador;
    }

    public void setMostrarPanelRepLegal(final Boolean mostrarPanelRepLegal) {
        this.mostrarPanelRepLegal = mostrarPanelRepLegal;
    }

    public Boolean getMostrarPanelRepLegal() {
        return mostrarPanelRepLegal;
    }

    public void setMostrarBotonLimpiarRepLegal(final Boolean mostrarBotonLimpiarRepLegal) {
        this.mostrarBotonLimpiarRepLegal = mostrarBotonLimpiarRepLegal;
    }

    public Boolean getMostrarBotonLimpiarRepLegal() {
        return mostrarBotonLimpiarRepLegal;
    }

    public void setMostrarBotonBuscarRepLegal(final Boolean mostrarBotonBuscarRepLegal) {
        this.mostrarBotonBuscarRepLegal = mostrarBotonBuscarRepLegal;
    }

    public Boolean getMostrarBotonBuscarRepLegal() {
        return mostrarBotonBuscarRepLegal;
    }

    public void setDeshabilitaCapturaRepLegal(final Boolean deshabilitaCapturaRepLegal) {
        this.deshabilitaCapturaRepLegal = deshabilitaCapturaRepLegal;
    }

    public Boolean getDeshabilitaCapturaRepLegal() {
        return deshabilitaCapturaRepLegal;
    }

    public Map<String, BigDecimal> getTipoOficioEnum() {
        return tipoOficioEnum;
    }

    public void setTipoOficioEnum(Map<String, BigDecimal> tipoOficioEnum) {
        this.tipoOficioEnum = tipoOficioEnum;
    }

    

    public List<DocumentoOrdenModel> getListaPapelesTrabajoOficio() {
        return listaPapelesTrabajoOficio;
    }

    public void setListaPapelesTrabajoOficio(List<DocumentoOrdenModel> listaPapelesTrabajoOficio) {
        this.listaPapelesTrabajoOficio = listaPapelesTrabajoOficio;
    }

    public int getNumeroDocumentoPapelTrabajoOficio() {
        return numeroDocumentoPapelTrabajoOficio;
    }

    public void setNumeroDocumentoPapelTrabajoOficio(int numeroDocumentoPapelTrabajoOficio) {
        this.numeroDocumentoPapelTrabajoOficio = numeroDocumentoPapelTrabajoOficio;
    }


    public BigDecimal getIdTipoOficio() {
        return idTipoOficio;
    }


    public void setIdTipoOficio(BigDecimal idTipoOficio) {
        this.idTipoOficio = idTipoOficio;
    }


    public DocumentoOrdenModel getPapelTrabajoSeleccionado() {
        return papelTrabajoSeleccionado;
    }


    public void setPapelTrabajoSeleccionado(DocumentoOrdenModel papelTrabajoSeleccionado) {
        this.papelTrabajoSeleccionado = papelTrabajoSeleccionado;
    }


    public String getFieldsetActivoPapelTrabajo() {
        return fieldsetActivoPapelTrabajo;
    }


    public void setFieldsetActivoPapelTrabajo(String fieldsetActivoPapelTrabajo) {
        this.fieldsetActivoPapelTrabajo = fieldsetActivoPapelTrabajo;
    }

    
    
}
