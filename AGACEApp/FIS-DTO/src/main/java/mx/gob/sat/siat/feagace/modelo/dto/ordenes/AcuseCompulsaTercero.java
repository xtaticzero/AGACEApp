package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.util.List;


public class AcuseCompulsaTercero {
    private String rfc;
    private String nombre;
    private String fechaReparacion;
    private String horaReparacion;
    private String fecheEmision;
    private String horaEmision;
    private String revision;
    private String acuse;
    private String emisionAcuse;
    private String cadenaOriginal;
    private String fiel;
    private String rutaAcuse;
    private String tituloGeneral;

    private List<FecetDocTercero> listaEnviarDocumentos;

    public String getRfc() {
        return rfc;
    }

    public void setRfc(final String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getFechaReparacion() {
        return fechaReparacion;
    }

    public String getRutaAcuse() {
        return rutaAcuse;
    }

    public void setRutaAcuse(final String rutaAcuse) {
        this.rutaAcuse = rutaAcuse;
    }

    public void setFechaReparacion(final String fechaReparacion) {
        this.fechaReparacion = fechaReparacion;
    }

    public String getHoraReparacion() {
        return horaReparacion;
    }

    public void setHoraReparacion(final String horaReparacion) {
        this.horaReparacion = horaReparacion;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(final String revision) {
        this.revision = revision;
    }

    public String getAcuse() {
        return acuse;
    }

    public void setAcuse(final String acuse) {
        this.acuse = acuse;
    }

    public String getEmisionAcuse() {
        return emisionAcuse;
    }

    public void setEmisionAcuse(final String emisionAcuse) {
        this.emisionAcuse = emisionAcuse;
    }

    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    public void setCadenaOriginal(final String cadenaOriginal) {
        this.cadenaOriginal = cadenaOriginal;
    }

    public String getFiel() {
        return fiel;
    }

    public void setFiel(final String fiel) {
        this.fiel = fiel;
    }

    public String getFecheEmision() {
        return fecheEmision;
    }

    public void setFecheEmision(final String fecheEmision) {
        this.fecheEmision = fecheEmision;
    }

    public String getHoraEmision() {
        return horaEmision;
    }

    public void setHoraEmision(final String horaEmision) {
        this.horaEmision = horaEmision;
    }

    public void setTituloGeneral(final String tituloGeneral) {
        this.tituloGeneral = tituloGeneral;
    }

    public String getTituloGeneral() {
        return tituloGeneral;
    }

    public void setListaEnviarDocumentos(final List<FecetDocTercero> listaEnviarDocumentos) {
        this.listaEnviarDocumentos = listaEnviarDocumentos;
    }

    public List<FecetDocTercero> getListaEnviarDocumentos() {
        return listaEnviarDocumentos;
    }
}
