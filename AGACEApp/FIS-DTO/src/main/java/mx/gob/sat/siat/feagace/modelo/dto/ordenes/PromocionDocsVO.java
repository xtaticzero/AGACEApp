package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import java.io.InputStream;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class PromocionDocsVO extends BaseModel implements Serializable {

    @SuppressWarnings("compatibility:-6536265299895457311")
    private static final long serialVersionUID = 1L;
    
    private PerfilContribuyenteVO perfil;
    private AgaceOrden ordenSeleccionado;
    private FecetPromocion promocionSeleccionado;
    private List<FecetAlegato> listaPruebasAlegatosCargadas;    
    private String nombreArchivo;
    private long sizeOrden;
    private String jsonFirmado;
    private transient InputStream inputStream;
    
    private List<FecetAlegatoOficio> listaPruebasAlegatosOficioCargadas;
    private FecetOficio oficioSeleccionado;
    private FecetPromocionOficio promocionOficioSeleccionado;
    private long sizeOficio;
    
    private BigDecimal idOrdenAuditadaCompulsa;

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setListaPruebasAlegatosCargadas(List<FecetAlegato> listaPruebasAlegatosCargadas) {
        this.listaPruebasAlegatosCargadas = listaPruebasAlegatosCargadas;
    }

    public List<FecetAlegato> getListaPruebasAlegatosCargadas() {
        return listaPruebasAlegatosCargadas;
    }

    public void setPerfil(PerfilContribuyenteVO perfil) {
        this.perfil = perfil;
    }

    public PerfilContribuyenteVO getPerfil() {
        return perfil;
    }

    public void setJsonFirmado(String jsonFirmado) {
        this.jsonFirmado = jsonFirmado;
    }

    public String getJsonFirmado() {
        return jsonFirmado;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setListaPruebasAlegatosOficioCargadas(List<FecetAlegatoOficio> listaPruebasAlegatosOficioCargadas) {
        this.listaPruebasAlegatosOficioCargadas = listaPruebasAlegatosOficioCargadas;
    }

    public List<FecetAlegatoOficio> getListaPruebasAlegatosOficioCargadas() {
        return listaPruebasAlegatosOficioCargadas;
    }

    public void setOficioSeleccionado(FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }

    public void setOrdenSeleccionado(AgaceOrden ordenSeleccionado) {
        this.ordenSeleccionado = ordenSeleccionado;
    }

    public AgaceOrden getOrdenSeleccionado() {
        return ordenSeleccionado;
    }

    public void setPromocionSeleccionado(FecetPromocion promocionSeleccionado) {
        this.promocionSeleccionado = promocionSeleccionado;
    }

    public FecetPromocion getPromocionSeleccionado() {
        return promocionSeleccionado;
    }

    public void setPromocionOficioSeleccionado(FecetPromocionOficio promocionOficioSeleccionado) {
        this.promocionOficioSeleccionado = promocionOficioSeleccionado;
    }

    public FecetPromocionOficio getPromocionOficioSeleccionado() {
        return promocionOficioSeleccionado;
    }

    public void setSizeOrden(long sizeOrden) {
        this.sizeOrden = sizeOrden;
    }

    public long getSizeOrden() {
        return sizeOrden;
    }

    public void setSizeOficio(long sizeOficio) {
        this.sizeOficio = sizeOficio;
    }

    public long getSizeOficio() {
        return sizeOficio;
    }

    public void setIdOrdenAuditadaCompulsa(BigDecimal idOrdenAuditadaCompulsa) {
        this.idOrdenAuditadaCompulsa = idOrdenAuditadaCompulsa;
    }

    public BigDecimal getIdOrdenAuditadaCompulsa() {
        return idOrdenAuditadaCompulsa;
    }
}
