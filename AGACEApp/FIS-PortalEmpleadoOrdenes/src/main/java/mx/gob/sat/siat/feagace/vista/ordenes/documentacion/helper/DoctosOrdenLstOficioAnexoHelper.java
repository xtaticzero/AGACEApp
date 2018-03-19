package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper;

import java.io.Serializable;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DoctosOrdenLstOficioAnexoHelper implements Serializable {

    private static final long serialVersionUID = -7096974867823947003L;

    private List<FecetOficioAnexos> listaOficioAnexosRechazo;
    private List<FecetOficioAnexos> listaAnexos2aCartaInv;
    private List<FecetOficioAnexos> listaAnexos2aCartaInvitacionMasiva;
    private List<FecetOficioAnexos> listaAnexosConclusionUCAMCA;
    private List<FecetOficioAnexos> listaAnexosSegundoRequerimiento;
    private List<FecetOficioAnexos> listaAnexosMulta;
    private List<FecetOficioAnexos> listaAnexosBajaPadron;
    private List<FecetOficioAnexos> listaAnexosResolucionDefinitiva;
    private List<FecetOficioAnexos> listaAnexosPruebasPericiales;
    private List<FecetOficioAnexos> listaAnexosAvisoContribuyente;
    private List<FecetOficioAnexos> listaAnexosPruebasDesahogo;
    private List<FecetOficioAnexos> listaAnexosConclusionRevision;
    private List<FecetOficioAnexos> listaAnexosCancelacionOrden;
    private List<FecetOficioAnexos> listaAnexosSinObservaciones;
    private List<FecetOficioAnexos> listaAnexosConObservaciones;
    private List<FecetOficioAnexos> listaAnexosRequerimientoReincidencia;
    private List<FecetOficioAnexos> listaAnexosCambioMetodoUCAMCA;
    private List<FecetOficioAnexos> listaAnexosCompInternacional;
    private List<FecetOficioAnexos> listaAnexosOficio;
    private List<FecetOficioAnexos> listaAnexosAvisoCircunstancial;
    private List<FecetOficioAnexos> listaAnexosMedidasApremio;
    private List<FecetOficioAnexos> listaAnexosMultaOrden;
    private List<FecetOficioAnexos> listaAnexosAvisoContribuyenteOrden;

    public List<FecetOficioAnexos> getListaAnexosMedidasApremio() {
        return listaAnexosMedidasApremio;
    }

    public void setListaAnexosMedidasApremio(List<FecetOficioAnexos> listaAnexosMedidasApremio) {
        this.listaAnexosMedidasApremio = listaAnexosMedidasApremio;
    }

    public List<FecetOficioAnexos> getListaAnexosAvisoCircunstancial() {
        return listaAnexosAvisoCircunstancial;
    }

    public void setListaAnexosAvisoCircunstancial(List<FecetOficioAnexos> listaAnexosAvisoCircunstancial) {
        this.listaAnexosAvisoCircunstancial = listaAnexosAvisoCircunstancial;
    }

    public void setListaAnexosCompInternacional(final List<FecetOficioAnexos> listaAnexosCompInternacional) {
        this.listaAnexosCompInternacional = listaAnexosCompInternacional;
    }

    public List<FecetOficioAnexos> getListaAnexosCompInternacional() {
        return listaAnexosCompInternacional;
    }

    public void setListaAnexosCambioMetodoUCAMCA(final List<FecetOficioAnexos> listaAnexosCambioMetodoUCAMCA) {
        this.listaAnexosCambioMetodoUCAMCA = listaAnexosCambioMetodoUCAMCA;
    }

    public List<FecetOficioAnexos> getListaAnexosCambioMetodoUCAMCA() {
        return listaAnexosCambioMetodoUCAMCA;
    }

    public void setListaAnexosOficio(final List<FecetOficioAnexos> listaAnexosOficio) {
        this.listaAnexosOficio = listaAnexosOficio;
    }

    public List<FecetOficioAnexos> getListaAnexosOficio() {
        return listaAnexosOficio;
    }

    public void setListaAnexosRequerimientoReincidencia(final List<FecetOficioAnexos> listaAnexosRequerimientoReincidencia) {
        this.listaAnexosRequerimientoReincidencia = listaAnexosRequerimientoReincidencia;
    }

    public List<FecetOficioAnexos> getListaAnexosRequerimientoReincidencia() {
        return listaAnexosRequerimientoReincidencia;
    }

    public void setListaAnexosConObservaciones(final List<FecetOficioAnexos> listaAnexosConObservaciones) {
        this.listaAnexosConObservaciones = listaAnexosConObservaciones;
    }

    public List<FecetOficioAnexos> getListaAnexosConObservaciones() {
        return listaAnexosConObservaciones;
    }

    public void setListaAnexosSinObservaciones(final List<FecetOficioAnexos> listaAnexosSinObservaciones) {
        this.listaAnexosSinObservaciones = listaAnexosSinObservaciones;
    }

    public List<FecetOficioAnexos> getListaAnexosSinObservaciones() {
        return listaAnexosSinObservaciones;
    }

    public void setListaAnexosCancelacionOrden(final List<FecetOficioAnexos> listaAnexosCancelacionOrden) {
        this.listaAnexosCancelacionOrden = listaAnexosCancelacionOrden;
    }

    public List<FecetOficioAnexos> getListaAnexosCancelacionOrden() {
        return listaAnexosCancelacionOrden;
    }

    public void setListaAnexosConclusionRevision(final List<FecetOficioAnexos> listaAnexosConclusionRevision) {
        this.listaAnexosConclusionRevision = listaAnexosConclusionRevision;
    }

    public List<FecetOficioAnexos> getListaAnexosConclusionRevision() {
        return listaAnexosConclusionRevision;
    }

    public void setListaAnexosPruebasDesahogo(final List<FecetOficioAnexos> listaAnexosPruebasDesahogo) {
        this.listaAnexosPruebasDesahogo = listaAnexosPruebasDesahogo;
    }

    public List<FecetOficioAnexos> getListaAnexosPruebasDesahogo() {
        return listaAnexosPruebasDesahogo;
    }

    public void setListaAnexosAvisoContribuyente(final List<FecetOficioAnexos> listaAnexosAvisoContribuyente) {
        this.listaAnexosAvisoContribuyente = listaAnexosAvisoContribuyente;
    }

    public List<FecetOficioAnexos> getListaAnexosAvisoContribuyente() {
        return listaAnexosAvisoContribuyente;
    }

    public void setListaAnexosPruebasPericiales(final List<FecetOficioAnexos> listaAnexosPruebasPericiales) {
        this.listaAnexosPruebasPericiales = listaAnexosPruebasPericiales;
    }

    public List<FecetOficioAnexos> getListaAnexosPruebasPericiales() {
        return listaAnexosPruebasPericiales;
    }

    public void setListaAnexosResolucionDefinitiva(final List<FecetOficioAnexos> listaAnexosResolucionDefinitiva) {
        this.listaAnexosResolucionDefinitiva = listaAnexosResolucionDefinitiva;
    }

    public List<FecetOficioAnexos> getListaAnexosResolucionDefinitiva() {
        return listaAnexosResolucionDefinitiva;
    }

    public void setListaAnexosBajaPadron(final List<FecetOficioAnexos> listaAnexosBajaPadron) {
        this.listaAnexosBajaPadron = listaAnexosBajaPadron;
    }

    public List<FecetOficioAnexos> getListaAnexosBajaPadron() {
        return listaAnexosBajaPadron;
    }

    public void setListaAnexosMulta(final List<FecetOficioAnexos> listaAnexosMulta) {
        this.listaAnexosMulta = listaAnexosMulta;
    }

    public List<FecetOficioAnexos> getListaAnexosMulta() {
        return listaAnexosMulta;
    }

    public void setListaAnexosSegundoRequerimiento(final List<FecetOficioAnexos> listaAnexosSegundoRequerimiento) {
        this.listaAnexosSegundoRequerimiento = listaAnexosSegundoRequerimiento;
    }

    public List<FecetOficioAnexos> getListaAnexosSegundoRequerimiento() {
        return listaAnexosSegundoRequerimiento;
    }

    public void setListaAnexos2aCartaInvitacionMasiva(final List<FecetOficioAnexos> listaAnexos2aCartaInvitacionMasiva) {
        this.listaAnexos2aCartaInvitacionMasiva = listaAnexos2aCartaInvitacionMasiva;
    }

    public List<FecetOficioAnexos> getListaAnexos2aCartaInvitacionMasiva() {
        return listaAnexos2aCartaInvitacionMasiva;
    }

    public void setListaAnexosConclusionUCAMCA(final List<FecetOficioAnexos> listaAnexosConclusionUCAMCA) {
        this.listaAnexosConclusionUCAMCA = listaAnexosConclusionUCAMCA;
    }

    public List<FecetOficioAnexos> getListaAnexosConclusionUCAMCA() {
        return listaAnexosConclusionUCAMCA;
    }

    public void setListaAnexos2aCartaInv(final List<FecetOficioAnexos> listaAnexos2aCartaInv) {
        this.listaAnexos2aCartaInv = listaAnexos2aCartaInv;
    }

    public List<FecetOficioAnexos> getListaAnexos2aCartaInv() {
        return listaAnexos2aCartaInv;
    }

    public void setListaOficioAnexosRechazo(final List<FecetOficioAnexos> listaOficioAnexosRechazo) {
        this.listaOficioAnexosRechazo = listaOficioAnexosRechazo;
    }

    public List<FecetOficioAnexos> getListaOficioAnexosRechazo() {
        return listaOficioAnexosRechazo;
    }

    public List<FecetOficioAnexos> getListaAnexosMultaOrden() {
        return listaAnexosMultaOrden;
    }

    public void setListaAnexosMultaOrden(List<FecetOficioAnexos> listaAnexosMultaOrden) {
        this.listaAnexosMultaOrden = listaAnexosMultaOrden;
    }

    public List<FecetOficioAnexos> getListaAnexosAvisoContribuyenteOrden() {
        return listaAnexosAvisoContribuyenteOrden;
    }

    public void setListaAnexosAvisoContribuyenteOrden(List<FecetOficioAnexos> listaAnexosAvisoContribuyenteOrden) {
        this.listaAnexosAvisoContribuyenteOrden = listaAnexosAvisoContribuyenteOrden;
    }

}
