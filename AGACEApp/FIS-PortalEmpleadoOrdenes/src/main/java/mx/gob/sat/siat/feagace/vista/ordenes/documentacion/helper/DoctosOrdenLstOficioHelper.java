package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper;

import java.io.Serializable;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DoctosOrdenLstOficioHelper implements Serializable {

    private static final long serialVersionUID = 7026589989099412786L;

    private List<FecetOficio> listaOficiosDependientes;
    private List<FecetOficio> listaOficiosFirmados;
    private List<FecetOficio> listaOficiosRechazados;
    private List<FecetOficio> listaOf2aCartaInv;
    private List<FecetOficio> listaOf2aCartaInvitacionMasiva;
    private List<FecetOficio> listaOfConclusionUCAMCA;
    private List<FecetOficio> listaOfSegundoRequerimiento;
    private List<FecetOficio> listaOfMulta;
    private List<FecetOficio> listaOfBajaPadron;
    private List<FecetOficio> listaOfResolucionDefinitiva;
    private List<FecetOficio> listaOfPruebasPericiales;
    private List<FecetOficio> listaOfAvisoContribuyente;
    private List<FecetOficio> listaOfPruebasDesahogo;
    private List<FecetOficio> listaOfConclusionRevision;
    private List<FecetOficio> listaOfCancelacionOrden;
    private List<FecetOficio> listaOfSinObservaciones;
    private List<FecetOficio> listaOfConObservaciones;
    private List<FecetOficio> listaOfRequerimientoReincidencia;
    private List<FecetOficio> listaOfCambioMetodoUCAMCA;
    private List<FecetOficio> listaOfCompInternacional;
    private List<FecetOficio> listaOfAvisoCircunstancial;
    private List<FecetOficio> listaOficiosAdmin;
    private List<FecetOficio> listaOfMedidasApremio;
    private List<FecetOficio> listaOfMultaOrden;
    private List<FecetOficio> listaOfAvisoContribuyenteOrden;

    public List<FecetOficio> getListaOfMedidasApremio() {
        return listaOfMedidasApremio;
    }

    public void setListaOfMedidasApremio(List<FecetOficio> listaOfMedidasApremio) {
        this.listaOfMedidasApremio = listaOfMedidasApremio;
    }

    public List<FecetOficio> getListaOficiosAdmin() {
        return listaOficiosAdmin;
    }

    public void setListaOficiosAdmin(List<FecetOficio> listaOficiosAdmin) {
        this.listaOficiosAdmin = listaOficiosAdmin;
    }

    public List<FecetOficio> getListaOfAvisoCircunstancial() {
        return listaOfAvisoCircunstancial;
    }

    public void setListaOfAvisoCircunstancial(List<FecetOficio> listaOfAvisoCircunstancial) {
        this.listaOfAvisoCircunstancial = listaOfAvisoCircunstancial;
    }

    public void setListaOficiosFirmados(final List<FecetOficio> listaOficiosFirmados) {
        this.listaOficiosFirmados = listaOficiosFirmados;
    }

    public List<FecetOficio> getListaOficiosFirmados() {
        return listaOficiosFirmados;
    }

    public void setListaOficiosRechazados(List<FecetOficio> listaOficiosRechazados) {
        this.listaOficiosRechazados = listaOficiosRechazados;
    }

    public List<FecetOficio> getListaOficiosRechazados() {
        return listaOficiosRechazados;
    }

    public void setListaOf2aCartaInv(final List<FecetOficio> listaOf2aCartaInv) {
        this.listaOf2aCartaInv = listaOf2aCartaInv;
    }

    public List<FecetOficio> getListaOf2aCartaInv() {
        return listaOf2aCartaInv;
    }

    public void setListaOfConclusionUCAMCA(final List<FecetOficio> listaOfConclusionUCAMCA) {
        this.listaOfConclusionUCAMCA = listaOfConclusionUCAMCA;
    }

    public List<FecetOficio> getListaOfConclusionUCAMCA() {
        return listaOfConclusionUCAMCA;
    }

    public void setListaOf2aCartaInvitacionMasiva(final List<FecetOficio> listaOf2aCartaInvitacionMasiva) {
        this.listaOf2aCartaInvitacionMasiva = listaOf2aCartaInvitacionMasiva;
    }

    public List<FecetOficio> getListaOf2aCartaInvitacionMasiva() {
        return listaOf2aCartaInvitacionMasiva;
    }

    public void setListaOfSegundoRequerimiento(final List<FecetOficio> listaOfSegundoRequerimiento) {
        this.listaOfSegundoRequerimiento = listaOfSegundoRequerimiento;
    }

    public List<FecetOficio> getListaOfSegundoRequerimiento() {
        return listaOfSegundoRequerimiento;
    }

    public void setListaOfMulta(final List<FecetOficio> listaOfMulta) {
        this.listaOfMulta = listaOfMulta;
    }

    public List<FecetOficio> getListaOfMulta() {
        return listaOfMulta;
    }

    public void setListaOfBajaPadron(final List<FecetOficio> listaOfBajaPadron) {
        this.listaOfBajaPadron = listaOfBajaPadron;
    }

    public List<FecetOficio> getListaOfBajaPadron() {
        return listaOfBajaPadron;
    }

    public void setListaOfResolucionDefinitiva(final List<FecetOficio> listaOfResolucionDefinitiva) {
        this.listaOfResolucionDefinitiva = listaOfResolucionDefinitiva;
    }

    public List<FecetOficio> getListaOfResolucionDefinitiva() {
        return listaOfResolucionDefinitiva;
    }

    public void setListaOfPruebasPericiales(final List<FecetOficio> listaOfPruebasPericiales) {
        this.listaOfPruebasPericiales = listaOfPruebasPericiales;
    }

    public List<FecetOficio> getListaOfPruebasPericiales() {
        return listaOfPruebasPericiales;
    }

    public void setListaOfAvisoContribuyente(final List<FecetOficio> listaOfAvisoContribuyente) {
        this.listaOfAvisoContribuyente = listaOfAvisoContribuyente;
    }

    public List<FecetOficio> getListaOfAvisoContribuyente() {
        return listaOfAvisoContribuyente;
    }

    public void setListaOfPruebasDesahogo(final List<FecetOficio> listaOfPruebasDesahogo) {
        this.listaOfPruebasDesahogo = listaOfPruebasDesahogo;
    }

    public List<FecetOficio> getListaOfPruebasDesahogo() {
        return listaOfPruebasDesahogo;
    }

    public void setListaOfConclusionRevision(final List<FecetOficio> listaOfConclusionRevision) {
        this.listaOfConclusionRevision = listaOfConclusionRevision;
    }

    public List<FecetOficio> getListaOfConclusionRevision() {
        return listaOfConclusionRevision;
    }

    public void setListaOfCancelacionOrden(final List<FecetOficio> listaOfCancelacionOrden) {
        this.listaOfCancelacionOrden = listaOfCancelacionOrden;
    }

    public List<FecetOficio> getListaOfCancelacionOrden() {
        return listaOfCancelacionOrden;
    }

    public void setListaOfSinObservaciones(final List<FecetOficio> listaOfSinObservaciones) {
        this.listaOfSinObservaciones = listaOfSinObservaciones;
    }

    public List<FecetOficio> getListaOfSinObservaciones() {
        return listaOfSinObservaciones;
    }

    public void setListaOfConObservaciones(final List<FecetOficio> listaOfConObservaciones) {
        this.listaOfConObservaciones = listaOfConObservaciones;
    }

    public List<FecetOficio> getListaOfConObservaciones() {
        return listaOfConObservaciones;
    }

    public void setListaOfRequerimientoReincidencia(final List<FecetOficio> listaOfRequerimientoReincidencia) {
        this.listaOfRequerimientoReincidencia = listaOfRequerimientoReincidencia;
    }

    public List<FecetOficio> getListaOfRequerimientoReincidencia() {
        return listaOfRequerimientoReincidencia;
    }

    public void setListaOficiosDependientes(final List<FecetOficio> listaOficiosDependientes) {
        this.listaOficiosDependientes = listaOficiosDependientes;
    }

    public List<FecetOficio> getListaOficiosDependientes() {
        return listaOficiosDependientes;
    }

    public void setListaOfCambioMetodoUCAMCA(final List<FecetOficio> listaOfCambioMetodoUCAMCA) {
        this.listaOfCambioMetodoUCAMCA = listaOfCambioMetodoUCAMCA;
    }

    public List<FecetOficio> getListaOfCambioMetodoUCAMCA() {
        return listaOfCambioMetodoUCAMCA;
    }

    public void setListaOfCompInternacional(final List<FecetOficio> listaOfCompInternacional) {
        this.listaOfCompInternacional = listaOfCompInternacional;
    }

    public List<FecetOficio> getListaOfCompInternacional() {
        return listaOfCompInternacional;
    }

    public List<FecetOficio> getListaOfMultaOrden() {
        return listaOfMultaOrden;
    }

    public void setListaOfMultaOrden(List<FecetOficio> listaOfMultaOrden) {
        this.listaOfMultaOrden = listaOfMultaOrden;
    }

    public List<FecetOficio> getListaOfAvisoContribuyenteOrden() {
        return listaOfAvisoContribuyenteOrden;
    }

    public void setListaOfAvisoContribuyenteOrden(List<FecetOficio> listaOfAvisoContribuyenteOrden) {
        this.listaOfAvisoContribuyenteOrden = listaOfAvisoContribuyenteOrden;
    }

}
