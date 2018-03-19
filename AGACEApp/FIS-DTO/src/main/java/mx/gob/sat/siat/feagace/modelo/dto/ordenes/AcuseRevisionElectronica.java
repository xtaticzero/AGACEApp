package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class AcuseRevisionElectronica extends BaseModel {
    @SuppressWarnings("compatibility:474493270598220064")
    private static final long serialVersionUID = 1L;
    private String rfc;
    private String nombre;
    private String fechaReparacion;
    private String horaReparacion;
    private String fecheEmision;
    private String horaEmision;
    private String revision;
    private String folioRecepcion;
    private String folioRecepcionPromocion;
    private String folioRecepcionProrroga;
    private String nombreArchivoPromocion;
    private String tamanioArchivoPromocion;
    private String nombreArchivoProrroga;
    private String tamanioArchivoProrroga;
    private String emisionAcuse;
    private String cadenaOriginal;
    private String fiel;
    private String rutaAcuse;
    private String tituloGeneral;
    private String folioRecepcionPruebaPericial;

    private List<FecetAlegato> listaEnviarPruebasAlegatos;
    private List<FecetAlegatoOficio> listaEnviarPruebasAlegatosOficio;
    private List<FecetDocProrrogaOrden> listaDocProrrogaOrden;
    private List<FecetDocProrrogaOficio> listaDocProrrogaOficio;
    private List<FecetDocPruebasPericiales> listaDocPruebasPericiales;

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

    public String getFechaReparacion() {
        return fechaReparacion;
    }

    public String getRutaAcuse() {
        return rutaAcuse;
    }

    public void setRutaAcuse(String rutaAcuse) {
        this.rutaAcuse = rutaAcuse;
    }

    public void setFechaReparacion(String fechaReparacion) {
        this.fechaReparacion = fechaReparacion;
    }

    public String getHoraReparacion() {
        return horaReparacion;
    }

    public void setHoraReparacion(String horaReparacion) {
        this.horaReparacion = horaReparacion;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getFolioRecepcion() {
        return folioRecepcion;
    }

    public void setFolioRecepcion(String folioRecepcion) {
        this.folioRecepcion = folioRecepcion;
    }

    public String getEmisionAcuse() {
        return emisionAcuse;
    }

    public void setEmisionAcuse(String emisionAcuse) {
        this.emisionAcuse = emisionAcuse;
    }

    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    public void setCadenaOriginal(String cadenaOriginal) {
        this.cadenaOriginal = cadenaOriginal;
    }

    public String getFiel() {
        return fiel;
    }

    public void setFiel(String fiel) {
        this.fiel = fiel;
    }

    public List<FecetAlegato> getListaEnviarPruebasAlegatos() {
        return listaEnviarPruebasAlegatos;
    }

    public void setListaEnviarPruebasAlegatos(List<FecetAlegato> listaEnviarPruebasAlegatos) {
        this.listaEnviarPruebasAlegatos = listaEnviarPruebasAlegatos;
    }

    public String getFecheEmision() {
        return fecheEmision;
    }

    public void setFecheEmision(String fecheEmision) {
        this.fecheEmision = fecheEmision;
    }

    public String getHoraEmision() {
        return horaEmision;
    }

    public void setHoraEmision(String horaEmision) {
        this.horaEmision = horaEmision;
    }

    public void setTituloGeneral(String tituloGeneral) {
        this.tituloGeneral = tituloGeneral;
    }

    public String getTituloGeneral() {
        return tituloGeneral;
    }

    public void setNombreArchivoPromocion(String nombreArchivoPromocion) {
        this.nombreArchivoPromocion = nombreArchivoPromocion;
    }

    public String getNombreArchivoPromocion() {
        return nombreArchivoPromocion;
    }

    public void setTamanioArchivoPromocion(String tamanioArchivoPromocion) {
        this.tamanioArchivoPromocion = tamanioArchivoPromocion;
    }

    public String getTamanioArchivoPromocion() {
        return tamanioArchivoPromocion;
    }

    public void setNombreArchivoProrroga(String nombreArchivoProrroga) {
        this.nombreArchivoProrroga = nombreArchivoProrroga;
    }

    public String getNombreArchivoProrroga() {
        return nombreArchivoProrroga;
    }

    public void setTamanioArchivoProrroga(String tamanioArchivoProrroga) {
        this.tamanioArchivoProrroga = tamanioArchivoProrroga;
    }

    public String getTamanioArchivoProrroga() {
        return tamanioArchivoProrroga;
    }

    public void setFolioRecepcionPromocion(String folioRecepcionPromocion) {
        this.folioRecepcionPromocion = folioRecepcionPromocion;
    }

    public String getFolioRecepcionPromocion() {
        return folioRecepcionPromocion;
    }

    public void setFolioRecepcionProrroga(String folioRecepcionProrroga) {
        this.folioRecepcionProrroga = folioRecepcionProrroga;
    }

    public String getFolioRecepcionProrroga() {
        return folioRecepcionProrroga;
    }

    public void setListaEnviarPruebasAlegatosOficio(List<FecetAlegatoOficio> listaEnviarPruebasAlegatosOficio) {
        this.listaEnviarPruebasAlegatosOficio = listaEnviarPruebasAlegatosOficio;
    }

    public List<FecetAlegatoOficio> getListaEnviarPruebasAlegatosOficio() {
        return listaEnviarPruebasAlegatosOficio;
    }

    public void setListaDocProrrogaOrden(List<FecetDocProrrogaOrden> listaDocProrrogaOrden) {
        this.listaDocProrrogaOrden = listaDocProrrogaOrden;
    }

    public List<FecetDocProrrogaOrden> getListaDocProrrogaOrden() {
        return listaDocProrrogaOrden;
    }

    public void setListaDocProrrogaOficio(List<FecetDocProrrogaOficio> listaDocProrrogaOficio) {
        this.listaDocProrrogaOficio = listaDocProrrogaOficio;
    }

    public List<FecetDocProrrogaOficio> getListaDocProrrogaOficio() {
        return listaDocProrrogaOficio;
    }

    public void setListaDocPruebasPericiales(List<FecetDocPruebasPericiales> listaDocPruebasPericiales) {
        this.listaDocPruebasPericiales = listaDocPruebasPericiales;
    }

    public List<FecetDocPruebasPericiales> getListaDocPruebasPericiales() {
        return listaDocPruebasPericiales;
    }

    public void setFolioRecepcionPruebaPericial(String folioRecepcionPruebaPericial) {
        this.folioRecepcionPruebaPericial = folioRecepcionPruebaPericial;
    }

    public String getFolioRecepcionPruebaPericial() {
        return folioRecepcionPruebaPericial;
    }
}
