package mx.gob.sat.siat.feagace.vista.model.ordenes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;

public class ProrrogaOrdenConsultaDTO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8725103870400374256L;
    private BigDecimal idProrroga;
    private Date fechaSolicitud;
    private String nombreAcuse;
    private String rutaAcuse;
    private Integer totalDocumentosContribuyente;
    private String nombreResolucion;
    private String rutaResolucion;
    private String estatus;
    private String presentadoPor;
    private List<FecetDocProrrogaOrden> listFecetDocProrrogaOrden;
    private FlujoProrrogaOrdenConsultaDTO flujoProrrogaOrdenConsultaDTO;
    private BigDecimal idAuditor;
    private BigDecimal idFirmante;

    public BigDecimal getIdProrroga() {
        return idProrroga;
    }

    public void setIdProrroga(BigDecimal idProrroga) {
        this.idProrroga = idProrroga;
    }

    public Date getFechaSolicitud() {
        return (fechaSolicitud != null) ? (Date) fechaSolicitud.clone() : null;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud != null ? new Date(fechaSolicitud.getTime()):null ;
    }

    public String getNombreAcuse() {
        return nombreAcuse;
    }

    public void setNombreAcuse(String nombreAcuse) {
        this.nombreAcuse = nombreAcuse;
    }

    public String getRutaAcuse() {
        return rutaAcuse;
    }

    public void setRutaAcuse(String rutaAcuse) {
        this.rutaAcuse = rutaAcuse;
    }

    public Integer getTotalDocumentosContribuyente() {
        return totalDocumentosContribuyente;
    }

    public void setTotalDocumentosContribuyente(Integer totalDocumentosContribuyente) {
        this.totalDocumentosContribuyente = totalDocumentosContribuyente;
    }

    public String getNombreResolucion() {
        return nombreResolucion;
    }

    public void setNombreResolucion(String nombreResolucion) {
        this.nombreResolucion = nombreResolucion;
    }

    public String getRutaResolucion() {
        return rutaResolucion;
    }

    public void setRutaResolucion(String rutaResolucion) {
        this.rutaResolucion = rutaResolucion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public List<FecetDocProrrogaOrden> getListFecetDocProrrogaOrden() {
        return listFecetDocProrrogaOrden;
    }

    public void setListFecetDocProrrogaOrden(List<FecetDocProrrogaOrden> listFecetDocProrrogaOrden) {
        this.listFecetDocProrrogaOrden = listFecetDocProrrogaOrden;
    }

    public String getPresentadoPor() {
        return presentadoPor;
    }

    public void setPresentadoPor(String presentadoPor) {
        this.presentadoPor = presentadoPor;
    }

    public FlujoProrrogaOrdenConsultaDTO getFlujoProrrogaOrdenConsultaDTO() {
        return flujoProrrogaOrdenConsultaDTO;
    }

    public void setFlujoProrrogaOrdenConsultaDTO(FlujoProrrogaOrdenConsultaDTO flujoProrrogaOrdenConsultaDTO) {
        this.flujoProrrogaOrdenConsultaDTO = flujoProrrogaOrdenConsultaDTO;
    }

    public BigDecimal getIdAuditor() {
        return idAuditor;
    }

    public void setIdAuditor(BigDecimal idAuditor) {
        this.idAuditor = idAuditor;
    }

    public BigDecimal getIdFirmante() {
        return idFirmante;
    }

    public void setIdFirmante(BigDecimal idFirmante) {
        this.idFirmante = idFirmante;
    }
    

}
