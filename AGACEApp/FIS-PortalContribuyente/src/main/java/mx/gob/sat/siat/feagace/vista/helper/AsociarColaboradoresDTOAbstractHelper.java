package mx.gob.sat.siat.feagace.vista.helper;

import java.io.Serializable;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;

public class AsociarColaboradoresDTOAbstractHelper implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private FecetPromocion promocionSeleccionada;
    private FecetOficio oficioSeleccionado;
    private FecetOficio ofDependienteSeleccionado;
    private PerfilContribuyenteVO perfil;
    private AgaceOrden ordenSeleccionado;
    private AgaceOrden ordenSeleccionDescarga;
    private FecetOficioAnexos anexoOficioSeleccionado;
    private FecetAlegato pruebaAlegatoSeleccionada;
    private FecetCompulsas compulsaSeleccionada;
    private FecetContribuyente contribuyente;
    private FecetDocAsociado documentoSeleccionadoRL;
    private FecetOficio oficioSeleccionadoDescarga;
    private FecetDocAsociado documentoSeleccionadoAL;
    private FecetDocAsociado documentoSeleccionadoALRL;

    public FecetOficio getOficioSeleccionadoDescarga() {
        return oficioSeleccionadoDescarga;
    }

    public FecetDocAsociado getDocumentoSeleccionadoAL() {
        return documentoSeleccionadoAL;
    }

    public void setDocumentoSeleccionadoAL(FecetDocAsociado documentoSeleccionadoAL) {
        this.documentoSeleccionadoAL = documentoSeleccionadoAL;
    }

    public void setOficioSeleccionadoDescarga(FecetOficio oficioSeleccionadoDescarga) {
        this.oficioSeleccionadoDescarga = oficioSeleccionadoDescarga;
    }

    public void setContribuyente(FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    public void setOrdenSeleccionado(AgaceOrden ordenSeleccionado) {
        this.ordenSeleccionado = ordenSeleccionado;
    }

    public AgaceOrden getOrdenSeleccionado() {
        return ordenSeleccionado;
    }

    public void setOrdenSeleccionDescarga(AgaceOrden ordenSeleccionDescarga) {
        this.ordenSeleccionDescarga = ordenSeleccionDescarga;
    }

    public AgaceOrden getOrdenSeleccionDescarga() {
        return ordenSeleccionDescarga;
    }

    public void setPerfil(PerfilContribuyenteVO perfil) {
        this.perfil = perfil;
    }

    public PerfilContribuyenteVO getPerfil() {
        return perfil;
    }

    public void setPromocionSeleccionada(FecetPromocion promocionSeleccionada) {
        this.promocionSeleccionada = promocionSeleccionada;
    }

    public FecetPromocion getPromocionSeleccionada() {
        return promocionSeleccionada;
    }

    public void setOficioSeleccionado(FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }

    public void setPruebaAlegatoSeleccionada(FecetAlegato pruebaAlegatoSeleccionada) {
        this.pruebaAlegatoSeleccionada = pruebaAlegatoSeleccionada;
    }

    public FecetAlegato getPruebaAlegatoSeleccionada() {
        return pruebaAlegatoSeleccionada;
    }

    public void setCompulsaSeleccionada(FecetCompulsas compulsaSeleccionada) {
        this.compulsaSeleccionada = compulsaSeleccionada;
    }

    public FecetCompulsas getCompulsaSeleccionada() {
        return compulsaSeleccionada;
    }

    public void setOfDependienteSeleccionado(FecetOficio ofDependienteSeleccionado) {
        this.ofDependienteSeleccionado = ofDependienteSeleccionado;
    }

    public FecetOficio getOfDependienteSeleccionado() {
        return ofDependienteSeleccionado;
    }

    public void setAnexoOficioSeleccionado(FecetOficioAnexos anexoOficioSeleccionado) {
        this.anexoOficioSeleccionado = anexoOficioSeleccionado;
    }

    public FecetOficioAnexos getAnexoOficioSeleccionado() {
        return anexoOficioSeleccionado;
    }

    public FecetDocAsociado getDocumentoSeleccionadoRL() {
        return documentoSeleccionadoRL;
    }

    public void setDocumentoSeleccionadoRL(FecetDocAsociado documentoSeleccionadoRL) {
        this.documentoSeleccionadoRL = documentoSeleccionadoRL;
    }

    public FecetDocAsociado getDocumentoSeleccionadoALRL() {
        return documentoSeleccionadoALRL;
    }

    public void setDocumentoSeleccionadoALRL(FecetDocAsociado documentoSeleccionadoALRL) {
        this.documentoSeleccionadoALRL = documentoSeleccionadoALRL;
    }

}
