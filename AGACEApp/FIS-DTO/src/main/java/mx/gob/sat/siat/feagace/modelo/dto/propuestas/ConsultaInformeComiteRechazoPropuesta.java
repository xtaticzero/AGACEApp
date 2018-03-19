package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class ConsultaInformeComiteRechazoPropuesta extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_REGISTRO in the FECET_PROPUESTA
     * table.
     */
    private String idRegistro;

    /**
     * This attribute maps to the column RFC in the FECET_CONTRIBUYENTE table.
     */
    private String rfc;

    /**
     * This attribute maps to the column NOMBRE in the FECET_CONTRIBUYENTE table.
     */
    private String nombre;

    /**
     * This attribute maps to the column DESCIPCION in the FECEC_ESTATUS table.
     */
    private String estatus;

    /**
     * This attribute maps to the column REGIMEN in the FECET_CONTRIBUYENTE table.
     */
    private String regimen;

    /**
     * This attribute maps to the column CLAVE and DESCRIPCION in the FECEC_SUBPROGRAMA table.
     */
    private String subprograma;

    /**
     * This attribute maps to the column ENTIDAD in the FECET_CONTRIBUYENTE table.
     */
    private String entidad;

    /**
     * This attribute maps to the column TIPO in the FECET_CONTRIBUYENTE table.
     */
    private String tipoContribuyente;

    /**
     * This attribute maps to the column ACTIVIDAD_PREPONDERANTE in the FECET_CONTRIBUYENTE table.
     */
    private String actividadPreponderante;

    /**
     * This attribute maps to the column FECHA_INFORME in the FECET_PROPUESTA
     * table.
     */
    private Date fechaInforme;

    /**
     * This attribute maps to the column RFC_CREACION in the FECET_RECHAZO_PROPUESTA table.
     */
    private String rfcCreacion;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_MOTIVO table.
     */
    private String causaMotivo;

    private FecetDocExpediente expediente;

    private List<FecetDocExpediente> listaExpediente;

    public ConsultaInformeComiteRechazoPropuesta() {
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRfc() {
        return rfc;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setSubprograma(String claveDescripcion) {
        this.subprograma = claveDescripcion;
    }

    public String getSubprograma() {
        return subprograma;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setTipoContribuyente(String tipo) {
        this.tipoContribuyente = tipo;
    }

    public String getTipoContribuyente() {
        return tipoContribuyente;
    }

    public void setActividadPreponderante(String actividadPreponderante) {
        this.actividadPreponderante = actividadPreponderante;
    }

    public String getActividadPreponderante() {
        return actividadPreponderante;
    }

    public void setFechaInforme(Date fechaInforme) {
        this.fechaInforme = fechaInforme != null ? new Date(fechaInforme.getTime()) : null;
    }

    public Date getFechaInforme() {
        return (fechaInforme != null) ? (Date)fechaInforme.clone() : null;
    }

    public void setRfcCreacion(String rfcCreacion) {
        this.rfcCreacion = rfcCreacion;
    }

    public String getRfcCreacion() {
        return rfcCreacion;
    }

    public void setCausaMotivo(String causaMotivo) {
        this.causaMotivo = causaMotivo;
    }

    public String getCausaMotivo() {
        return causaMotivo;
    }


    public void setExpediente(FecetDocExpediente expediente) {
        this.expediente = expediente;
    }

    public FecetDocExpediente getExpediente() {
        return expediente;
    }

    public void setListaExpediente(List<FecetDocExpediente> listaExpediente) {
        this.listaExpediente = listaExpediente;
    }

    public List<FecetDocExpediente> getListaExpediente() {
        return listaExpediente;
    }
}
