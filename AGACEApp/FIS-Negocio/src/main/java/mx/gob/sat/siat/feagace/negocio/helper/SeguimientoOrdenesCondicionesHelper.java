/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.helper;

import java.io.Serializable;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class SeguimientoOrdenesCondicionesHelper implements Serializable {

    private static final long serialVersionUID = -6389077936640713406L;

    private boolean guardarSegundoRequerimiento;
    private boolean guardarConclusionRevision;
    private boolean guardarConclusionUCAMCA;
    private boolean guardarConObservaciones;
    private boolean guardarResolucionDefinitiva;
    private boolean guardarSinObservaciones;
    private boolean guardar2aCartaInvitacion;
    private boolean guardarCambioMetodoUCAMCA;
    private boolean guardarCompInternacional;
    private boolean guardarPruebasDesahogo;
    private boolean guardarPruebasPericiales;
    private boolean guardar2aCartaInvitacionMasiva;
    private boolean guardarRequerimientoReincidencia;
    private boolean guardarFlujoProrrogaOficioAprobadaAuditor;
    private boolean guardarFlujoProrrogaOficioRechazadaAuditor;
    
    private List<FecetOficio> listaOficiosEnProcesoVencidos;
    private List<String> nombreAuditor;

    public boolean isGuardarSegundoRequerimiento() {
        return guardarSegundoRequerimiento;
    }

    public void setGuardarSegundoRequerimiento(boolean guardarSegundoRequerimiento) {
        this.guardarSegundoRequerimiento = guardarSegundoRequerimiento;
    }

    public boolean isGuardarConclusionRevision() {
        return guardarConclusionRevision;
    }

    public void setGuardarConclusionRevision(boolean guardarConclusionRevision) {
        this.guardarConclusionRevision = guardarConclusionRevision;
    }

    public boolean isGuardarConclusionUCAMCA() {
        return guardarConclusionUCAMCA;
    }

    public void setGuardarConclusionUCAMCA(boolean guardarConclusionUCAMCA) {
        this.guardarConclusionUCAMCA = guardarConclusionUCAMCA;
    }

    public boolean isGuardarConObservaciones() {
        return guardarConObservaciones;
    }

    public void setGuardarConObservaciones(boolean guardarConObservaciones) {
        this.guardarConObservaciones = guardarConObservaciones;
    }

    public boolean isGuardarResolucionDefinitiva() {
        return guardarResolucionDefinitiva;
    }

    public void setGuardarResolucionDefinitiva(boolean guardarResolucionDefinitiva) {
        this.guardarResolucionDefinitiva = guardarResolucionDefinitiva;
    }

    public boolean isGuardarSinObservaciones() {
        return guardarSinObservaciones;
    }

    public void setGuardarSinObservaciones(boolean guardarSinObservaciones) {
        this.guardarSinObservaciones = guardarSinObservaciones;
    }

    public boolean isGuardar2aCartaInvitacion() {
        return guardar2aCartaInvitacion;
    }

    public void setGuardar2aCartaInvitacion(boolean guardar2aCartaInvitacion) {
        this.guardar2aCartaInvitacion = guardar2aCartaInvitacion;
    }

    public boolean isGuardarCambioMetodoUCAMCA() {
        return guardarCambioMetodoUCAMCA;
    }

    public void setGuardarCambioMetodoUCAMCA(boolean guardarCambioMetodoUCAMCA) {
        this.guardarCambioMetodoUCAMCA = guardarCambioMetodoUCAMCA;
    }

    public boolean isGuardarCompInternacional() {
        return guardarCompInternacional;
    }

    public void setGuardarCompInternacional(boolean guardarCompInternacional) {
        this.guardarCompInternacional = guardarCompInternacional;
    }

    public boolean isGuardarPruebasDesahogo() {
        return guardarPruebasDesahogo;
    }

    public void setGuardarPruebasDesahogo(boolean guardarPruebasDesahogo) {
        this.guardarPruebasDesahogo = guardarPruebasDesahogo;
    }

    public boolean isGuardarPruebasPericiales() {
        return guardarPruebasPericiales;
    }

    public void setGuardarPruebasPericiales(boolean guardarPruebasPericiales) {
        this.guardarPruebasPericiales = guardarPruebasPericiales;
    }

    public boolean isGuardar2aCartaInvitacionMasiva() {
        return guardar2aCartaInvitacionMasiva;
    }

    public void setGuardar2aCartaInvitacionMasiva(boolean guardar2aCartaInvitacionMasiva) {
        this.guardar2aCartaInvitacionMasiva = guardar2aCartaInvitacionMasiva;
    }

    public boolean isGuardarRequerimientoReincidencia() {
        return guardarRequerimientoReincidencia;
    }

    public void setGuardarRequerimientoReincidencia(boolean guardarRequerimientoReincidencia) {
        this.guardarRequerimientoReincidencia = guardarRequerimientoReincidencia;
    }

    public boolean isGuardarFlujoProrrogaOficioAprobadaAuditor() {
        return guardarFlujoProrrogaOficioAprobadaAuditor;
    }

    public void setGuardarFlujoProrrogaOficioAprobadaAuditor(boolean guardarFlujoProrrogaOficioAprobadaAuditor) {
        this.guardarFlujoProrrogaOficioAprobadaAuditor = guardarFlujoProrrogaOficioAprobadaAuditor;
    }

    public boolean isGuardarFlujoProrrogaOficioRechazadaAuditor() {
        return guardarFlujoProrrogaOficioRechazadaAuditor;
    }

    public void setGuardarFlujoProrrogaOficioRechazadaAuditor(boolean guardarFlujoProrrogaOficioRechazadaAuditor) {
        this.guardarFlujoProrrogaOficioRechazadaAuditor = guardarFlujoProrrogaOficioRechazadaAuditor;
    }
    
    public List<FecetOficio> getListaOficiosEnProcesoVencidos() {
        return listaOficiosEnProcesoVencidos;
    }

    public void setListaOficiosEnProcesoVencidos(List<FecetOficio> listaOficiosEnProcesoVencidos) {
        this.listaOficiosEnProcesoVencidos = listaOficiosEnProcesoVencidos;
    }

    public List<String> getNombreAuditor() {
        return nombreAuditor;
    }

    public void setNombreAuditor(List<String> nombreAuditor) {
        this.nombreAuditor = nombreAuditor;
    }
}
