package mx.gob.sat.siat.feagace.vista.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;

public class OrdenConsultaDTO implements Serializable{
    private static final long serialVersionUID = -1269224056891577245L;

    private BigDecimal idOrden;
    private String idRegistro;
    private String numeroOrden;
    private String rutaArchivo;
    private String nombreArchivo;
    private String metodo;
    private String metodoSiglas;
    private BigDecimal idMetodo;
    private String rfc;
    private String estatus;
    private int plazoRestante;
    private String nombreContribuyente;
    private BigDecimal idFirmante;
    private EmpleadoDTO firmante;
    private BigDecimal idAuditor;
    private BigDecimal idContribuyente;

    private List<PromocionConsultaDTO> listPromocionConsultaDTO;
    private List<OficioConsultaDTO> listOficioConsultaDTO;
    private List<ProrrogaOrdenConsultaDTO> listProrrogaOrdenConsultaDTO;

    public List<PromocionConsultaDTO> getListPromocionConsultaDTO() {
        return listPromocionConsultaDTO;
    }

    public void setListPromocionConsultaDTO(List<PromocionConsultaDTO> listPromocionConsultaDTO) {
        this.listPromocionConsultaDTO = listPromocionConsultaDTO;
    }

    public BigDecimal getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getPlazoRestante() {
        return plazoRestante;
    }

    public void setPlazoRestante(int plazoRestante) {
        this.plazoRestante = plazoRestante;
    }

    public List<OficioConsultaDTO> getListOficioConsultaDTO() {
        return listOficioConsultaDTO;
    }

    public void setListOficioConsultaDTO(List<OficioConsultaDTO> listOficioConsultaDTO) {
        this.listOficioConsultaDTO = listOficioConsultaDTO;
    }

    public List<ProrrogaOrdenConsultaDTO> getListProrrogaOrdenConsultaDTO() {
        return listProrrogaOrdenConsultaDTO;
    }

    public void setListProrrogaOrdenConsultaDTO(List<ProrrogaOrdenConsultaDTO> listProrrogaOrdenConsultaDTO) {
        this.listProrrogaOrdenConsultaDTO = listProrrogaOrdenConsultaDTO;
    }

    public BigDecimal getIdMetodo() {
        return idMetodo;
    }

    public void setIdMetodo(BigDecimal idMetodo) {
        this.idMetodo = idMetodo;
    }

    public String getMetodoSiglas() {
        return metodoSiglas;
    }

    public void setMetodoSiglas(String metodoSiglas) {
        this.metodoSiglas = metodoSiglas;
    }

    public String getNombreContribuyente() {
        return nombreContribuyente;
    }

    public void setNombreContribuyente(String nombreContribuyente) {
        this.nombreContribuyente = nombreContribuyente;
    }

    public BigDecimal getIdFirmante() {
        return idFirmante;
    }

    public void setIdFirmante(BigDecimal idFirmante) {
        this.idFirmante = idFirmante;
    }
    
    public BigDecimal getIdAuditor() {
        return idAuditor;
    }

    public EmpleadoDTO getFirmante() {
        return firmante;
    }

    public void setFirmante(EmpleadoDTO firmante) {
        this.firmante = firmante;
    }

    public void setIdAuditor(BigDecimal idAuditor) {
        this.idAuditor = idAuditor;
    }

    public BigDecimal getIdContribuyente() {
        return idContribuyente;
    }

    public void setIdContribuyente(BigDecimal idContribuyente) {
        this.idContribuyente = idContribuyente;
    }

}
