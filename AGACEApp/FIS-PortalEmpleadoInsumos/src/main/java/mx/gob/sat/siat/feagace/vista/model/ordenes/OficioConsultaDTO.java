package mx.gob.sat.siat.feagace.vista.model.ordenes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;

public class OficioConsultaDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 2386095114509767173L;
    private BigDecimal idOficio;
    private BigDecimal idTipoOficio;
    private String tipoOficio;
    private BigDecimal idMetodo;
    private String nombreArchivo;
    private String rutaArchivo;
    private String nombreAcuseNyv;
    private String rutaAcuseNyv;
    private Date fechaNotificacion;
    private int anexos;
    private Date fechaCarga;
    private Date fechaFirma;
    private int oficiosDependientes;
    private boolean oficioConRequerimiento;
    private String presentadoPor;

    private List<OficioConsultaDTO> listOficioDependientes;
    private List<FecetOficioAnexos> listFecetOficioAnexo;
    private List<PromocionOficioConsultaDTO> listPromocionOficioConsulta;
    private List<ProrrogaOficioConsultaDTO> listProrrogaOficioConsulta;

    public BigDecimal getIdOficio() {
        return idOficio;
    }

    public void setIdOficio(BigDecimal idOficio) {
        this.idOficio = idOficio;
    }

    public String getTipoOficio() {
        return tipoOficio;
    }

    public void setTipoOficio(String tipoOficio) {
        this.tipoOficio = tipoOficio;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getNombreAcuseNyv() {
        return nombreAcuseNyv;
    }

    public void setNombreAcuseNyv(String nombreAcuseNyv) {
        this.nombreAcuseNyv = nombreAcuseNyv;
    }

    public String getRutaAcuseNyv() {
        return rutaAcuseNyv;
    }

    public void setRutaAcuseNyv(String rutaAcuseNyv) {
        this.rutaAcuseNyv = rutaAcuseNyv;
    }

    public Date getFechaNotificacion() {
        return (fechaNotificacion != null) ? (Date) fechaNotificacion.clone() : null;        
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = (fechaNotificacion != null) ? (Date) fechaNotificacion.clone() : null;        
    }

    public int getAnexos() {
        return anexos;
    }

    public void setAnexos(int anexos) {
        this.anexos = anexos;
    }

    public Date getFechaCarga() {
        return (fechaCarga != null) ? (Date) fechaCarga.clone() : null;        
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = (fechaCarga != null) ? (Date) fechaCarga.clone() : null;        
    }

    public Date getFechaFirma() {
        return (fechaFirma != null) ? (Date) fechaFirma.clone() : null;
    }

    public void setFechaFirma(Date fechaFirma) {
        this.fechaFirma = (fechaFirma != null) ? (Date) fechaFirma.clone() : null;        
    }

    public int getOficiosDependientes() {
        return oficiosDependientes;
    }

    public void setOficiosDependientes(int oficiosDependientes) {
        this.oficiosDependientes = oficiosDependientes;
    }

    public List<OficioConsultaDTO> getListOficioDependientes() {
        return listOficioDependientes;
    }

    public void setListOficioDependientes(List<OficioConsultaDTO> listOficioDependientes) {
        this.listOficioDependientes = listOficioDependientes;
    }

    public List<FecetOficioAnexos> getListFecetOficioAnexo() {
        return listFecetOficioAnexo;
    }

    public void setListFecetOficioAnexo(List<FecetOficioAnexos> listFecetOficioAnexo) {
        this.listFecetOficioAnexo = listFecetOficioAnexo;
    }

    public List<PromocionOficioConsultaDTO> getListPromocionOficioConsulta() {
        return listPromocionOficioConsulta;
    }

    public void setListPromocionOficioConsulta(List<PromocionOficioConsultaDTO> listPromocionOficioConsulta) {
        this.listPromocionOficioConsulta = listPromocionOficioConsulta;
    }

    public boolean isOficioConRequerimiento() {
        return oficioConRequerimiento;
    }

    public void setOficioConRequerimiento(boolean oficioConRequerimiento) {
        this.oficioConRequerimiento = oficioConRequerimiento;
    }

    public BigDecimal getIdMetodo() {
        return idMetodo;
    }

    public void setIdMetodo(BigDecimal idMetodo) {
        this.idMetodo = idMetodo;
    }

    public String getPresentadoPor() {
        return presentadoPor;
    }

    public void setPresentadoPor(String presentadoPor) {
        this.presentadoPor = presentadoPor;
    }

    public List<ProrrogaOficioConsultaDTO> getListProrrogaOficioConsulta() {
        return listProrrogaOficioConsulta;
    }

    public void setListProrrogaOficioConsulta(List<ProrrogaOficioConsultaDTO> listProrrogaOficioConsulta) {
        this.listProrrogaOficioConsulta = listProrrogaOficioConsulta;
    }

    public BigDecimal getIdTipoOficio() {
        return idTipoOficio;
    }

    public void setIdTipoOficio(BigDecimal idTipoOficio) {
        this.idTipoOficio = idTipoOficio;
    }

}
