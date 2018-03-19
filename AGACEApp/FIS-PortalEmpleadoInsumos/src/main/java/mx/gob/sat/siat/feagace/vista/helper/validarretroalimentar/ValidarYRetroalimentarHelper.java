/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.helper.validarretroalimentar;

import java.util.Date;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropPendiente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ValidarYRetroalimentarHelper extends AbstractValidarYRetroalimentar {

    private static final long serialVersionUID = -1218323787753517425L;

    private FecetContribuyente contribuyenteActual;
    private FecetContribuyente contribuyenteAnterior;
    private FecetTransferencia tranferenciaSeleccionada;
    private FecetRechazoPropuesta historicoRechazo;
    private FecetRetroalimentacion historicoRetroalimentar;
    private FecetRetroalimentacion retroalimentacion;
    private FecetPropuesta propuestasXValidarSeleccionada;
    private FecetPropuesta propuestaTransferida;
    private FecetDocExpediente archivoSeleccionado;
    private FecetImpuesto impuestoSeleccionado;
    private FecetRechazoPropuesta propuestaRechazo;
    private FecetPropPendiente propuestaPendiente;
    private FecetImpuesto impuestoPropuesta;
    private FecetImpuesto impuesto;

    public ValidarYRetroalimentarHelper() {
        setFechaRegistro(new Date());
        propuestaRechazo = new FecetRechazoPropuesta();
        propuestaPendiente = new FecetPropPendiente();
        impuestoPropuesta = new FecetImpuesto();
        retroalimentacion = new FecetRetroalimentacion();
    }

    public FecetPropuesta getPropuestasXValidarSeleccionada() {
        return propuestasXValidarSeleccionada;
    }

    public void setPropuestasXValidarSeleccionada(FecetPropuesta propuestasXValidarSeleccionada) {
        this.propuestasXValidarSeleccionada = propuestasXValidarSeleccionada;
    }

    public FecetDocExpediente getArchivoSeleccionado() {
        return archivoSeleccionado;
    }

    public void setArchivoSeleccionado(FecetDocExpediente archivoSeleccionado) {
        this.archivoSeleccionado = archivoSeleccionado;
    }

    public FecetRechazoPropuesta getPropuestaRechazo() {
        return propuestaRechazo;
    }

    public void setPropuestaRechazo(FecetRechazoPropuesta propuestaRechazo) {
        this.propuestaRechazo = propuestaRechazo;
    }

    public FecetPropPendiente getPropuestaPendiente() {
        return propuestaPendiente;
    }

    public void setPropuestaPendiente(FecetPropPendiente propuestaPendiente) {
        this.propuestaPendiente = propuestaPendiente;
    }

    public void setPropuestaTransferida(FecetPropuesta propuestaTransferida) {
        this.propuestaTransferida = propuestaTransferida;
    }

    public FecetPropuesta getPropuestaTransferida() {
        return propuestaTransferida;
    }

    public void setContribuyenteActual(FecetContribuyente contribuyenteActual) {
        this.contribuyenteActual = contribuyenteActual;
    }

    public FecetContribuyente getContribuyenteActual() {
        return contribuyenteActual;
    }

    public void setContribuyenteAnterior(FecetContribuyente contribuyenteAnterior) {
        this.contribuyenteAnterior = contribuyenteAnterior;
    }

    public FecetContribuyente getContribuyenteAnterior() {
        return contribuyenteAnterior;
    }

    public void setTranferenciaSeleccionada(FecetTransferencia tranferenciaSeleccionada) {
        this.tranferenciaSeleccionada = tranferenciaSeleccionada;
    }

    public FecetTransferencia getTranferenciaSeleccionada() {
        return tranferenciaSeleccionada;
    }

    public void setImpuestoPropuesta(FecetImpuesto impuestoPropuesta) {
        this.impuestoPropuesta = impuestoPropuesta;
    }

    public FecetImpuesto getImpuestoPropuesta() {
        return impuestoPropuesta;
    }

    public FecetImpuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(FecetImpuesto impuesto) {
        this.impuesto = impuesto;
    }

    public FecetRechazoPropuesta getHistoricoRechazo() {
        return historicoRechazo;
    }

    public void setHistoricoRechazo(FecetRechazoPropuesta historicoRechazo) {
        this.historicoRechazo = historicoRechazo;
    }

    public FecetRetroalimentacion getHistoricoRetroalimentar() {
        return historicoRetroalimentar;
    }

    public void setHistoricoRetroalimentar(FecetRetroalimentacion historicoRetroalimentar) {
        this.historicoRetroalimentar = historicoRetroalimentar;
    }

    public FecetRetroalimentacion getRetroalimentacion() {
        return retroalimentacion;
    }

    public void setRetroalimentacion(FecetRetroalimentacion retroalimentacion) {
        this.retroalimentacion = retroalimentacion;
    }

    public void setImpuestoSeleccionado(FecetImpuesto impuestoSeleccionado) {
        this.impuestoSeleccionado = impuestoSeleccionado;
    }

    public FecetImpuesto getImpuestoSeleccionado() {
        return impuestoSeleccionado;
    }
}
