package mx.gob.sat.siat.feagace.vista.propuestas.subadmin.helper;

import java.io.Serializable;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaDocumentoElectronicoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;

/**
 * @author Julio Cesar Morales Miranda
 */

public class VerificarProcedenciaHelper implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3719640628718604026L;

    private List<FecetDocExpediente> listaSeleccionDocsOrden;
    private List<FecetOficio> listaSeleccionOficios;
    private CargaDocumentoElectronicoDTO adicionalContribuyente;
    private FecetRechazoPropuesta rechazoPropuesta;
    private FecetDocExpediente documentoSeleccionadoOrden;
    private FecetOficio documentoSeleccionadoOficio;
    private FecetDocOrden documentoHistOrden;

    public List<FecetDocExpediente> getListaSeleccionDocsOrden() {
        return listaSeleccionDocsOrden;
    }

    public void setListaSeleccionDocsOrden(List<FecetDocExpediente> listaSeleccionDocsOrden) {
        this.listaSeleccionDocsOrden = listaSeleccionDocsOrden;
    }

    public List<FecetOficio> getListaSeleccionOficios() {
        return listaSeleccionOficios;
    }

    public void setListaSeleccionOficios(List<FecetOficio> listaSeleccionOficios) {
        this.listaSeleccionOficios = listaSeleccionOficios;
    }

    public CargaDocumentoElectronicoDTO getAdicionalContribuyente() {
        return adicionalContribuyente;
    }

    public void setAdicionalContribuyente(CargaDocumentoElectronicoDTO adicionalContribuyente) {
        this.adicionalContribuyente = adicionalContribuyente;
    }

    public FecetRechazoPropuesta getRechazoPropuesta() {
        return rechazoPropuesta;
    }

    public void setRechazoPropuesta(FecetRechazoPropuesta rechazoPropuesta) {
        this.rechazoPropuesta = rechazoPropuesta;
    }

    public FecetDocExpediente getDocumentoSeleccionadoOrden() {
        return documentoSeleccionadoOrden;
    }

    public void setDocumentoSeleccionadoOrden(FecetDocExpediente documentoSeleccionadoOrden) {
        this.documentoSeleccionadoOrden = documentoSeleccionadoOrden;
    }

    public FecetOficio getDocumentoSeleccionadoOficio() {
        return documentoSeleccionadoOficio;
    }

    public void setDocumentoSeleccionadoOficio(FecetOficio documentoSeleccionadoOficio) {
        this.documentoSeleccionadoOficio = documentoSeleccionadoOficio;
    }

    public FecetDocOrden getDocumentoHistOrden() {
        return documentoHistOrden;
    }

    public void setDocumentoHistOrden(FecetDocOrden documentoHistOrden) {
        this.documentoHistOrden = documentoHistOrden;
    }

}
