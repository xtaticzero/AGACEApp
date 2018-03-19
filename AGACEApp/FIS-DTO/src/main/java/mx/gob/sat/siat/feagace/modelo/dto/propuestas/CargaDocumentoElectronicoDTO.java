package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;

public class CargaDocumentoElectronicoDTO implements java.io.Serializable{
   
    public CargaDocumentoElectronicoDTO(){ }
    
    private static final long serialVersionUID = 1;
    
    //Fields
    private FecetPropuesta fecetPropuesta;
    
    private FecetContribuyente contribuyente;
    
    private FecetContribuyente fecetContribuyente;
    
    private FecetContribuyente contribuyenteIdc;
    
    private FecetContribuyente contribuyentePropuesta;
    
    private DatosPropuestaDTO datosPreviosPropuesta;
    
    private List<ImpuestoDTO> listaImpuestos;

    /**
     * This attribute maps to the column RFC in the FECET_CONTRIBUYENTE table.
     */
    private String rfc;

    /**
     * This attribute maps to the column NOMBRE in the FECET_CONTRIBUYENTE table.
     */
    private String nombre;

    /**
     * This attribute maps to the column REGIMEN in the FECET_CONTRIBUYENTE table.
     */
    private String regimen;

    /**
     * This attribute maps to the column SITUACION in the FECET_CONTRIBUYENTE table.
     */
    private String situacion;

    /**
     * This attribute maps to the column TIPO in the FECET_CONTRIBUYENTE table.
     */
    private String tipo;

    /**
     * This attribute maps to the column SITUACION_DOMICILIO in the FECET_CONTRIBUYENTE table.
     */
    private String situacionDomicilio;

    /**
     * This attribute maps to the column DOMICILIO_FISCAL in the FECET_CONTRIBUYENTE table.
     */
    private String domicilioFiscal;

    /**
     * This attribute maps to the column ACTIVIDAD_PREPONDERANTE in the FECET_CONTRIBUYENTE table.
     */
    private String actividadPreponderante;

    /**
     * This attribute maps to the column ENTIDAD in the FECET_CONTRIBUYENTE table.
     */
    private String entidad;
    
    
       private String revision;
       
       private String subprograma;
       
       private String tipoPropuesta;
       
       private String programacion;
       
       private String tipoRevision;
       
       private String periodoInicioPropuesta;

       private String periodoFinPropuesa;
       
       private Date periodoInicio;

       private Date periodoFin;
       
       private Boolean prioridad;
       
       private String impuesto;
       
       private Integer monto;
       
       private String impuestoD;
       
       private Integer montoD;
    
       private String periodoInicioD;
       
       private String periodoFinD;
       
       private String presuntiva;
       
        private String rfcRepresent;
       
       private String nombreRepresent;
       
       private String emailRepresent;
       
       private String rfcAgAduanal;
       
       private String nombreAgAduanal;
       
       private String emailAgAduanal;
       
       private boolean existenCambios;
       
       private String idRegistro;
    //Methods


    public FecetPropuesta getFecetPropuesta() {
        return fecetPropuesta;
    }

    public void setFecetPropuesta(FecetPropuesta fecetPropuesta) {
        this.fecetPropuesta = fecetPropuesta;
    }

    public Date getPeriodoInicio() {
        return (periodoInicio != null) ? (Date) periodoInicio.clone() : null;
    }

    public void setPeriodoInicio(Date periodoInicio) {
        this.periodoInicio = periodoInicio != null ? new Date(periodoInicio.getTime()) : null;
    }

    public Date getPeriodoFin() {
        return (periodoFin != null) ? (Date) periodoFin.clone() : null;
    }

    public void setPeriodoFin(Date periodoFin) {
        this.periodoFin = periodoFin != null ? new Date(periodoFin.getTime()) : null;
    }

    public Boolean getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Boolean prioridad) {
        this.prioridad = prioridad;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public String getPeriodoInicioPropuesta() {
        return periodoInicioPropuesta;
    }

    public void setPeriodoInicioPropuesta(String periodoInicioPropuesta) {
        this.periodoInicioPropuesta = periodoInicioPropuesta;
    }

    public String getPeriodoFinPropuesa() {
        return periodoFinPropuesa;
    }

    public void setPeriodoFinPropuesa(String periodoFinPropuesa) {
        this.periodoFinPropuesa = periodoFinPropuesa;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSituacionDomicilio() {
        return situacionDomicilio;
    }

    public void setSituacionDomicilio(String situacionDomicilio) {
        this.situacionDomicilio = situacionDomicilio;
    }

    public String getDomicilioFiscal() {
        return domicilioFiscal;
    }

    public void setDomicilioFiscal(String domicilioFiscal) {
        this.domicilioFiscal = domicilioFiscal;
    }

    public String getActividadPreponderante() {
        return actividadPreponderante;
    }

    public void setActividadPreponderante(String actividadPreponderante) {
        this.actividadPreponderante = actividadPreponderante;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getSubprograma() {
        return subprograma;
    }

    public void setSubprograma(String subprograma) {
        this.subprograma = subprograma;
    }

    public String getTipoPropuesta() {
        return tipoPropuesta;
    }

    public void setTipoPropuesta(String tipoPropuesta) {
        this.tipoPropuesta = tipoPropuesta;
    }

    public String getProgramacion() {
        return programacion;
    }

    public void setProgramacion(String programacion) {
        this.programacion = programacion;
    }

    public String getTipoRevision() {
        return tipoRevision;
    }

    public void setTipoRevision(String tipoRevision) {
        this.tipoRevision = tipoRevision;
    }

    public String getImpuestoD() {
        return impuestoD;
    }

    public void setImpuestoD(String impuestoD) {
        this.impuestoD = impuestoD;
    }

    public Integer getMontoD() {
        return montoD;
    }

    public void setMontoD(Integer montoD) {
        this.montoD = montoD;
    }

    public String getPeriodoInicioD() {
        return periodoInicioD;
    }

    public void setPeriodoInicioD(String periodoInicioD) {
        this.periodoInicioD = periodoInicioD;
    }

    public String getPeriodoFinD() {
        return periodoFinD;
    }

    public void setPeriodoFinD(String periodoFinD) {
        this.periodoFinD = periodoFinD;
    }

    public String getPresuntiva() {
        return presuntiva;
    }

    public void setPresuntiva(String presuntiva) {
        this.presuntiva = presuntiva;
    }

    public String getNombreRepresent() {
        return nombreRepresent;
    }

    public void setNombreRepresent(String nombreRepresent) {
        this.nombreRepresent = nombreRepresent;
    }

    public String getEmailRepresent() {
        return emailRepresent;
    }

    public void setEmailRepresent(String emailRepresent) {
        this.emailRepresent = emailRepresent;
    }

    public String getRfcAgAduanal() {
        return rfcAgAduanal;
    }

    public void setRfcAgAduanal(String rfcAgAduanal) {
        this.rfcAgAduanal = rfcAgAduanal;
    }

    public String getNombreAgAduanal() {
        return nombreAgAduanal;
    }

    public void setNombreAgAduanal(String nombreAgAduanal) {
        this.nombreAgAduanal = nombreAgAduanal;
    }

    public String getEmailAgAduanal() {
        return emailAgAduanal;
    }

    public void setEmailAgAduanal(String emailAgAduanal) {
        this.emailAgAduanal = emailAgAduanal;
    }

    public String getRfcRepresent() {
        return rfcRepresent;
    }

    public void setRfcRepresent(String rfcRepresent) {
        this.rfcRepresent = rfcRepresent;
    }


    public void setContribuyenteIdc(FecetContribuyente contribuyenteIdc) {
        this.contribuyenteIdc = contribuyenteIdc;
    }

    public FecetContribuyente getContribuyenteIdc() {
        return contribuyenteIdc;
    }

    public void setExistenCambios(boolean existenCambios) {
        this.existenCambios = existenCambios;
    }

    public boolean isExistenCambios() {
        return existenCambios;
    }

    public void setContribuyente(FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    public void setContribuyentePropuesta(FecetContribuyente contribuyentePropuesta) {
        this.contribuyentePropuesta = contribuyentePropuesta;
    }

    public FecetContribuyente getContribuyentePropuesta() {
        return contribuyentePropuesta;
    }

    public void setDatosPreviosPropuesta(DatosPropuestaDTO datosPreviosPropuesta) {
        this.datosPreviosPropuesta = datosPreviosPropuesta;
    }

    public DatosPropuestaDTO getDatosPreviosPropuesta() {
        return datosPreviosPropuesta;
    }

    public List<ImpuestoDTO> getListaImpuestos() {
        return listaImpuestos;
    }

    public void setListaImpuestos(List<ImpuestoDTO> listaImpuestos) {
        this.listaImpuestos = listaImpuestos;
    }

    public FecetContribuyente getFecetContribuyente() {
        return fecetContribuyente;
    }

    public void setFecetContribuyente(FecetContribuyente fecetContribuyente) {
        this.fecetContribuyente = fecetContribuyente;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }
}
