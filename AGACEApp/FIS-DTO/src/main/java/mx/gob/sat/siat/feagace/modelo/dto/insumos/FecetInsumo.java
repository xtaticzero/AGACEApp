/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececTipoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;

public class FecetInsumo extends FecetInsumoSemaforo {

    private static final long serialVersionUID = 1L;
    /**
     * This attribute maps to the column ID_INSUMO in the FECET_INSUMO table.
     */
    private BigDecimal idInsumo;

    /**
     * This attribute maps to the column ID_CONTRIBUYENTE in the FECET_INSUMO
     * table.
     */
    private BigDecimal idContribuyente;

    /**
     * This attribute maps to the column ID_ARACE in the FECET_INSUMO table.
     */
    private BigDecimal idArace;
    private BigDecimal idUnidadAdministrativa;

    /**
     * This attribute maps to the column ID_SUBPROGRAMA in the FECET_INSUMO
     * table.
     */
    private BigDecimal idSubprograma;

    /**
     * This attribute maps to the column ID_SECTOR in the FECET_INSUMO table.
     */
    private BigDecimal idSector;

        /**
     * This attribute maps to the column ID_TIPO?INSUMO in the FECET_INSUMO table.
     */
    private BigDecimal idTipoInsumo;
    
    /**
     * This attribute maps to the column ID_REGISTRO in the FECET_INSUMO table.
     */
    private String idRegistro;

    /**
     * This attribute maps to the column FECHA_INICIO_PERIODO in the
     * FECET_INSUMO table.
     */
    private Date fechaInicioPeriodo;

    /**
     * This attribute maps to the column FECHA_FIN_PERIODO in the FECET_INSUMO
     * table.
     */
    private Date fechaFinPeriodo;

    /**
     * This attribute maps to the column PRIORIDAD in the FECET_INSUMO table.
     */
    private Boolean prioridad;

    /**
     * This attribute maps to the column FECHA_CREACION in the FECET_INSUMO
     * table.
     */
    private Date fechaCreacion;

    /**
     * This attribute maps to the column FECHA_BAJA in the FECET_INSUMO table.
     */
    private Date fechaBaja;

    /**
     * This attribute maps to the column RFC_CREACION in the FECET_INSUMO table.
     */
    private String rfcCreacion;

    /**
     * This attribute maps to the column RFC_ADMINISTRADOR in the FECET_INSUMO
     * table.
     */
    private String rfcAdministrador;

    /**
     * This attribute maps to the column RFC_SUBADMINISTRADOR in the
     * FECET_INSUMO table.
     */
    private String rfcSubadministrador;

    /**
     * This attribute maps to the column ID_ESTATUS in the FECET_INSUMO table.
     */
    private BigDecimal idEstatus;

    /**
     * This attribute maps to the column ID_PRIORIDAD in the FECET_INSUMO table.
     */
    private BigDecimal idPrioridad;

    private String valorPrioridad;
    
    private String descripcionPrioridad;

    /**
     * Este atributo contiene el archivo para ser almacenado en el service
     */
    private transient InputStream archivo;

    private int sizeListaDocumentos;

    private FececSector fececSector;

    private FececSubprograma fececSubprograma;

    private FececEstatus fececEstatus;

    private EmpleadoDTO empleado;

    private FecetReasignacionInsumo fecetReasignacionInsumo;

    private transient FececArace fececArace;

    private FececUnidadAdministrativa fececUnidadAdministrativa;

    /**
     * Este atributo almacena el objeto del contribuyente en la propuesta
     */
    private FecetContribuyente fecetContribuyente;

    /**
     * Este atributo almacena el objeto del contribuyente en la propuesta
     */
    private List<FecetDocExpInsumo> listaDocumentos;

    private List<RetroalimentacionInsumoDTO> lstRetroalimentacion;

    private FecetRechazoInsumo rechazoInsumo;

    private boolean selected;
    
    private Date fechaAprobacion;
    
    private String registradoPor;   
    
    private FececTipoInsumo fececTipoInsumo;
    
    private List<FecetDocumento> listaDocumentoJustificacion;
    
    private String justificacion;

    public FecetInsumo() {
        
    }

    /**
     * Method 'getIdInsumo'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdInsumo() {
        return idInsumo;
    }

    /**
     * Method 'setIdInsumo'
     *
     * @param idInsumo
     */
    public void setIdInsumo(BigDecimal idInsumo) {
        this.idInsumo = idInsumo;
    }

    /**
     * Method 'getIdContribuyente'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdContribuyente() {
        return idContribuyente;
    }

    /**
     * Method 'setIdContribuyente'
     *
     * @param idContribuyente
     */
    public void setIdContribuyente(BigDecimal idContribuyente) {
        this.idContribuyente = idContribuyente;
    }

    /**
     * Method 'getIdArace'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdArace() {
        return idArace;
    }

    /**
     * Method 'setIdArace'
     *
     * @param idArace
     */
    public void setIdArace(BigDecimal idArace) {
        this.idArace = idArace;
    }

    /**
     * Method 'getIdSubprograma'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdSubprograma() {
        return idSubprograma;
    }

    /**
     * Method 'setIdSubprograma'
     *
     * @param idSubprograma
     */
    public void setIdSubprograma(BigDecimal idSubprograma) {
        this.idSubprograma = idSubprograma;
    }

    /**
     * Method 'getIdSector'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdSector() {
        return idSector;
    }

    /**
     * Method 'setIdSector'
     *
     * @param idSector
     */
    public void setIdSector(BigDecimal idSector) {
        this.idSector = idSector;
    }

    /**
     * Method 'getIdRegistro'
     *
     * @return String
     */
    public String getIdRegistro() {
        return idRegistro;
    }

    /**
     * Method 'setIdRegistro'
     *
     * @param idRegistro
     */
    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    /**
     * Method 'getFechaInicioPeriodo'
     *
     * @return Date
     */
    public Date getFechaInicioPeriodo() {
        return (fechaInicioPeriodo != null) ? (Date) fechaInicioPeriodo.clone() : null;
    }

    /**
     * Method 'setFechaInicioPeriodo'
     *
     * @param fechaInicioPeriodo
     */
    public void setFechaInicioPeriodo(Date fechaInicioPeriodo) {
        this.fechaInicioPeriodo = fechaInicioPeriodo != null ? new Date(fechaInicioPeriodo.getTime())
                : null;

    }

    /**
     * Method 'getFechaFinPeriodo'
     *
     * @return Date
     */
    public Date getFechaFinPeriodo() {
        return (fechaFinPeriodo != null) ? (Date) fechaFinPeriodo.clone() : null;
    }

    /**
     * Method 'setFechaFinPeriodo'
     *
     * @param fechaFinPeriodo
     */
    public void setFechaFinPeriodo(Date fechaFinPeriodo) {
        this.fechaFinPeriodo = fechaFinPeriodo != null ? new Date(fechaFinPeriodo.getTime()) : null;
    }

    /**
     * Method 'getPrioridad'
     *
     * @return Boolean
     */
    public Boolean getPrioridad() {
        return prioridad;
    }

    /**
     * Method 'setPrioridad'
     *
     * @param prioridad
     */
    public void setPrioridad(Boolean prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * Method 'getFechaCreacion'
     *
     * @return Date
     */
    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    /**
     * Method 'setFechaCreacion'
     *
     * @param fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    /**
     * Method 'getFechaBaja'
     *
     * @return Date
     */
    public Date getFechaBaja() {
        return (fechaBaja != null) ? (Date) fechaBaja.clone() : null;
    }

    /**
     * Method 'setFechaBaja'
     *
     * @param fechaBaja
     */
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja != null ? new Date(fechaBaja.getTime()) : null;
    }

    /**
     * Method 'getRfcCreacion'
     *
     * @return String
     */
    public String getRfcCreacion() {
        return rfcCreacion;
    }

    /**
     * Method 'setRfcCreacion'
     *
     * @param rfcCreacion
     */
    public void setRfcCreacion(String rfcCreacion) {
        this.rfcCreacion = rfcCreacion;
    }

    /**
     * Method 'getRfcAdministrador'
     *
     * @return String
     */
    public String getRfcAdministrador() {
        return rfcAdministrador;
    }

    /**
     * Method 'setRfcAdministrador'
     *
     * @param rfcAdministrador
     */
    public void setRfcAdministrador(String rfcAdministrador) {
        this.rfcAdministrador = rfcAdministrador;
    }

    /**
     * Method 'getRfcSubadministrador'
     *
     * @return String
     */
    public String getRfcSubadministrador() {
        return rfcSubadministrador;
    }

    /**
     * Method 'setRfcSubadministrador'
     *
     * @param rfcSubadministrador
     */
    public void setRfcSubadministrador(String rfcSubadministrador) {
        this.rfcSubadministrador = rfcSubadministrador;
    }

    /**
     * Method 'getIdEstatus'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    /**
     * Method 'setIdEstatus'
     *
     * @param idEstatus
     */
    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    public void setArchivo(final InputStream archivo) {
        this.archivo = archivo;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    /**
     * Method 'createPk'
     *
     * @return FecetInsumoPk
     */
    public FecetInsumoPk createPk() {
        return new FecetInsumoPk(idInsumo);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetInsumo: ");
        ret.append("idInsumo=");
        ret.append(idInsumo);
        ret.append(", idContribuyente=");
        ret.append(idContribuyente);
        ret.append(", idArace=");
        ret.append(idArace);
        ret.append(", idSubprograma=");
        ret.append(idSubprograma);
        ret.append(", idSector=");
        ret.append(idSector);
        ret.append(", idRegistro=");
        ret.append(idRegistro);
        ret.append(", fechaInicioPeriodo=");
        ret.append(fechaInicioPeriodo);
        ret.append(", fechaFinPeriodo=");
        ret.append(fechaFinPeriodo);
        ret.append(", prioridad=");
        ret.append(prioridad);
        ret.append(", fechaCreacion=");
        ret.append(fechaCreacion);
        ret.append(", fechaBaja=");
        ret.append(fechaBaja);
        ret.append(", rfcCreacion=");
        ret.append(rfcCreacion);
        ret.append(", rfcAdministrador=");
        ret.append(rfcAdministrador);
        ret.append(", rfcSubadministrador=");
        ret.append(rfcSubadministrador);
        ret.append(", idEstatus=");
        ret.append(idEstatus);
        return ret.toString();
    }

    public void setSizeListaDocumentos(int sizeListaDocumentos) {
        this.sizeListaDocumentos = sizeListaDocumentos;
    }

    public int getSizeListaDocumentos() {
        return sizeListaDocumentos;
    }

    public void setFececSector(FececSector fececSector) {
        this.fececSector = fececSector;
    }

    public FececSector getFececSector() {
        return fececSector;
    }

    public void setFececSubprograma(FececSubprograma fececSubprograma) {
        this.fececSubprograma = fececSubprograma;
    }

    public FececSubprograma getFececSubprograma() {
        return fececSubprograma;
    }

    public void setFececEstatus(FececEstatus fececEstatus) {
        this.fececEstatus = fececEstatus;
    }

    public FececEstatus getFececEstatus() {
        return fececEstatus;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

    public void setFececArace(FececArace fececArace) {
        this.fececArace = fececArace;
    }

    public FececArace getFececArace() {
        return fececArace;
    }

    public void setFecetContribuyente(FecetContribuyente fecetContribuyente) {
        this.fecetContribuyente = fecetContribuyente;
    }

    public FecetContribuyente getFecetContribuyente() {
        return fecetContribuyente;
    }

    public void setListaDocumentos(List<FecetDocExpInsumo> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public List<FecetDocExpInsumo> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setFecetReasignacionInsumo(FecetReasignacionInsumo fecetReasignacionInsumo) {
        this.fecetReasignacionInsumo = fecetReasignacionInsumo;
    }

    public FecetReasignacionInsumo getFecetReasignacionInsumo() {
        return fecetReasignacionInsumo;
    }

    /**
     * @return the idPrioridad
     */
    public BigDecimal getIdPrioridad() {
        return idPrioridad;
    }

    /**
     * @param idPrioridad the idPrioridad to set
     */
    public void setIdPrioridad(BigDecimal idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public FececUnidadAdministrativa getFececUnidadAdministrativa() {
        return fececUnidadAdministrativa;
    }

    public void setFececUnidadAdministrativa(FececUnidadAdministrativa fececUnidadAdministrativa) {
        this.fececUnidadAdministrativa = fececUnidadAdministrativa;
    }

    public String getValorPrioridad() {
        return valorPrioridad;
    }

    public void setValorPrioridad(String valorPrioridad) {
        this.valorPrioridad = valorPrioridad;
    }

    public BigDecimal getIdUnidadAdministrativa() {
        return idUnidadAdministrativa;
    }

    public void setIdUnidadAdministrativa(BigDecimal idUnidadAdministrativa) {
        this.idUnidadAdministrativa = idUnidadAdministrativa;
    }

    public List<RetroalimentacionInsumoDTO> getLstRetroalimentacion() {
        return lstRetroalimentacion;
    }

    public void setLstRetroalimentacion(List<RetroalimentacionInsumoDTO> lstRetroalimentacion) {
        this.lstRetroalimentacion = lstRetroalimentacion;
    }

    public FecetRechazoInsumo getRechazoInsumo() {
        return rechazoInsumo;
    }

    public void setRechazoInsumo(FecetRechazoInsumo rechazoInsumo) {
        this.rechazoInsumo = rechazoInsumo;
    }

    public int getNoRetroalimentaciones() {
        if (lstRetroalimentacion != null && !lstRetroalimentacion.isEmpty()) {
            return lstRetroalimentacion.size();
        }
        return 0;
    }

    public int getNoRechazos() {
        if (rechazoInsumo != null) {
            return 1;
        }
        return 0;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion != null ? new Date(fechaAprobacion.getTime()) : null;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion != null ? new Date(fechaAprobacion.getTime()) : null;;
    }
    
    public String getRegistradoPor() {
		return registradoPor;
	}

	public void setRegistradoPor(String registradoPor) {
		this.registradoPor = registradoPor;
	}

    public FececTipoInsumo getFececTipoInsumo() {
        return fececTipoInsumo;
    }

    public void setFececTipoInsumo(FececTipoInsumo fececTipoInsumo) {
        this.fececTipoInsumo = fececTipoInsumo;
    }

    public BigDecimal getIdTipoInsumo() {
        return idTipoInsumo;
    }

    public void setIdTipoInsumo(BigDecimal idTipoInsumo) {
        this.idTipoInsumo = idTipoInsumo;
    }   

    public List<FecetDocumento> getListaDocumentoJustificacion() {
        return listaDocumentoJustificacion;
    }

    public void setListaDocumentoJustificacion(List<FecetDocumento> listaDocumentoJustificacion) {
        this.listaDocumentoJustificacion = listaDocumentoJustificacion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getDescripcionPrioridad() {
        return descripcionPrioridad;
    }

    public void setDescripcionPrioridad(String descripcionPrioridad) {
        this.descripcionPrioridad = descripcionPrioridad;
    }
    
    

}
