package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper;

import java.io.Serializable;

public class DoctosOrdenAttributeHelperAbstract implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Boolean reactivarPlazo;
    private Boolean visualizarTabsOficios;
    private Boolean visualizarTabSegundoRequerimiento;
    private Boolean visualizarTabConclusionRevision;
    private Boolean visualizarTabCompulsaTercero;
    private Boolean visualizarTabCompulsaInternacional;
    private Boolean visualizarTabConclusionSinObservaciones;
    private Boolean visualizarTabObservacionesRevisionEscritorio;
    private Boolean visualizarTabRequerimientoReincidencia;
    private Boolean visualizarTabOtrasAutoridades;
    private Boolean visualizarTabResolucionDefinitiva;
    private Boolean visualizarTabPruebasDesahogo;
    private Boolean visualizarTabPruebasPericiales;
    private Boolean visualizarTab2aUnicaCartaInvitacion;
    private Boolean visualizarTab2aCartaInvitacionMasiva;
    private Boolean visualizarTabConclusionUCAMCA;
    private Boolean visualizarTabCambioMetodoUCAMCA;
    private Boolean recargarInformacion;
    private Boolean visualizarIntegrarExpediente;
    private Boolean visualizarReactivarPlazoREE;
    private Boolean visualizarReactivarPlazoAcuerdoConclusivo;
    private Boolean visualizarTabAvisoCircunstancial;
    private boolean visualizarTabMedidasApremio;
    private boolean visualizarTabAvisoContribuyente;
    private boolean visualizarTabMulta;

    public boolean isVisualizarTabMedidasApremio() {
        return visualizarTabMedidasApremio;
    }

    public void setVisualizarTabMedidasApremio(boolean visualizarTabMedidasApremio) {
        this.visualizarTabMedidasApremio = visualizarTabMedidasApremio;
    }

    public Boolean getVisualizarTabAvisoCircunstancial() {
        return visualizarTabAvisoCircunstancial;
    }

    public void setVisualizarTabAvisoCircunstancial(Boolean visualizarTabAvisoCircunstancial) {
        this.visualizarTabAvisoCircunstancial = visualizarTabAvisoCircunstancial;
    }

    public Boolean getVisualizarTabCompulsas() {
        return (visualizarTabCompulsaInternacional || visualizarTabCompulsaTercero || visualizarTabOtrasAutoridades);
    }

    public void setVisualizarIntegrarExpediente(final Boolean visualizarIntegrarExpediente) {
        this.visualizarIntegrarExpediente = visualizarIntegrarExpediente;
    }

    public Boolean getVisualizarIntegrarExpediente() {
        return visualizarIntegrarExpediente;
    }

    public void setVisualizarReactivarPlazoREE(final Boolean visualizarReactivarPlazoREE) {
        this.visualizarReactivarPlazoREE = visualizarReactivarPlazoREE;
    }

    public Boolean getVisualizarReactivarPlazoREE() {
        return visualizarReactivarPlazoREE;
    }

    public void setVisualizarReactivarPlazoAcuerdoConclusivo(final Boolean visualizarReactivarPlazoAcuerdoConclusivo) {
        this.visualizarReactivarPlazoAcuerdoConclusivo = visualizarReactivarPlazoAcuerdoConclusivo;
    }

    public Boolean getVisualizarReactivarPlazoAcuerdoConclusivo() {
        return visualizarReactivarPlazoAcuerdoConclusivo;
    }

    public void setRecargarInformacion(final Boolean recargarInformacion) {
        this.recargarInformacion = recargarInformacion;
    }

    public Boolean getRecargarInformacion() {
        return recargarInformacion;
    }

    public void setVisualizarTabsOficios(final Boolean visualizarTabsOficios) {
        this.visualizarTabsOficios = visualizarTabsOficios;
    }

    public Boolean getVisualizarTabsOficios() {
        return visualizarTabsOficios;
    }

    public void setVisualizarTabSegundoRequerimiento(Boolean visualizarTabSegundoRequerimiento) {
        this.visualizarTabSegundoRequerimiento = visualizarTabSegundoRequerimiento;
    }

    public Boolean getVisualizarTabSegundoRequerimiento() {
        return visualizarTabSegundoRequerimiento;
    }

    public void setVisualizarTabConclusionRevision(final Boolean visualizarTabConclusionRevision) {
        this.visualizarTabConclusionRevision = visualizarTabConclusionRevision;
    }

    public Boolean getVisualizarTabConclusionRevision() {
        return visualizarTabConclusionRevision;
    }

    public void setVisualizarTabCompulsaTercero(final Boolean visualizarTabCompulsaTercero) {
        this.visualizarTabCompulsaTercero = visualizarTabCompulsaTercero;
    }

    public Boolean getVisualizarTabCompulsaTercero() {
        return visualizarTabCompulsaTercero;
    }

    public void setVisualizarTabCompulsaInternacional(final Boolean visualizarTabCompulsaInternacional) {
        this.visualizarTabCompulsaInternacional = visualizarTabCompulsaInternacional;
    }

    public Boolean getVisualizarTabCompulsaInternacional() {
        return visualizarTabCompulsaInternacional;
    }

    public void setVisualizarTabConclusionSinObservaciones(final Boolean visualizarTabConclusionSinObservaciones) {
        this.visualizarTabConclusionSinObservaciones = visualizarTabConclusionSinObservaciones;
    }

    public Boolean getVisualizarTabConclusionSinObservaciones() {
        return visualizarTabConclusionSinObservaciones;
    }

    public void setVisualizarTabObservacionesRevisionEscritorio(final Boolean visualizarTabObservacionesRevisionEscritorio) {
        this.visualizarTabObservacionesRevisionEscritorio = visualizarTabObservacionesRevisionEscritorio;
    }

    public Boolean getVisualizarTabObservacionesRevisionEscritorio() {
        return visualizarTabObservacionesRevisionEscritorio;
    }

    public void setVisualizarTabRequerimientoReincidencia(final Boolean visualizarTabRequerimientoReincidencia) {
        this.visualizarTabRequerimientoReincidencia = visualizarTabRequerimientoReincidencia;
    }

    public Boolean getVisualizarTabRequerimientoReincidencia() {
        return visualizarTabRequerimientoReincidencia;
    }

    public void setVisualizarTabOtrasAutoridades(final Boolean visualizarTabOtrasAutoridades) {
        this.visualizarTabOtrasAutoridades = visualizarTabOtrasAutoridades;
    }

    public Boolean getVisualizarTabOtrasAutoridades() {
        return visualizarTabOtrasAutoridades;
    }

    public void setVisualizarTabResolucionDefinitiva(final Boolean visualizarTabResolucionDefinitiva) {
        this.visualizarTabResolucionDefinitiva = visualizarTabResolucionDefinitiva;
    }

    public Boolean getVisualizarTabResolucionDefinitiva() {
        return visualizarTabResolucionDefinitiva;
    }

    public void setVisualizarTabPruebasDesahogo(final Boolean visualizarTabPruebasDesahogo) {
        this.visualizarTabPruebasDesahogo = visualizarTabPruebasDesahogo;
    }

    public Boolean getVisualizarTabPruebasDesahogo() {
        return visualizarTabPruebasDesahogo;
    }

    public void setVisualizarTabPruebasPericiales(final Boolean visualizarTabPruebasPericiales) {
        this.visualizarTabPruebasPericiales = visualizarTabPruebasPericiales;
    }

    public Boolean getVisualizarTabPruebasPericiales() {
        return visualizarTabPruebasPericiales;
    }

    public void setVisualizarTab2aUnicaCartaInvitacion(final Boolean visualizarTab2aUnicaCartaInvitacion) {
        this.visualizarTab2aUnicaCartaInvitacion = visualizarTab2aUnicaCartaInvitacion;
    }

    public Boolean getVisualizarTab2aUnicaCartaInvitacion() {
        return visualizarTab2aUnicaCartaInvitacion;
    }

    public void setVisualizarTab2aCartaInvitacionMasiva(final Boolean visualizarTab2aCartaInvitacionMasiva) {
        this.visualizarTab2aCartaInvitacionMasiva = visualizarTab2aCartaInvitacionMasiva;
    }

    public Boolean getVisualizarTab2aCartaInvitacionMasiva() {
        return visualizarTab2aCartaInvitacionMasiva;
    }

    public void setVisualizarTabConclusionUCAMCA(final Boolean visualizarTabConclusionUCAMCA) {
        this.visualizarTabConclusionUCAMCA = visualizarTabConclusionUCAMCA;
    }

    public Boolean getVisualizarTabConclusionUCAMCA() {
        return visualizarTabConclusionUCAMCA;
    }

    public void setVisualizarTabCambioMetodoUCAMCA(final Boolean visualizarTabCambioMetodoUCAMCA) {
        this.visualizarTabCambioMetodoUCAMCA = visualizarTabCambioMetodoUCAMCA;
    }

    public Boolean getVisualizarTabCambioMetodoUCAMCA() {
        return visualizarTabCambioMetodoUCAMCA;
    }

    public boolean isVisualizarTabAvisoContribuyente() {
        return visualizarTabAvisoContribuyente;
    }

    public void setVisualizarTabAvisoContribuyente(boolean visualizarTabAvisoContribuyente) {
        this.visualizarTabAvisoContribuyente = visualizarTabAvisoContribuyente;
    }

    public boolean isVisualizarTabMulta() {
        return visualizarTabMulta;
    }

    public void setVisualizarTabMulta(boolean visualizarTabMulta) {
        this.visualizarTabMulta = visualizarTabMulta;
    }

    public Boolean getReactivarPlazo() {
        return reactivarPlazo;
    }

    public void setReactivarPlazo(Boolean reactivarPlazo) {
        this.reactivarPlazo = reactivarPlazo;
    }

}
