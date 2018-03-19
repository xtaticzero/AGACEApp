package mx.gob.sat.siat.feagace.vista.helper.asignadas;

import java.io.Serializable;
import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropCancelada;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;

public class ConsultarPropuestasAsignadasParamHelper implements Serializable {

    private static final long serialVersionUID = 9043140088402755858L;

    private BigDecimal estatusPropuesta1;
    private BigDecimal estatusPropuesta2;
    private BigDecimal accionPropuesta;

    private DocumentoOrdenModel docOficioSeleccionado;
    private DocumentoOrdenModel docOrdenSeleccionado;
    private DocumentoOrdenModel docPapelSeleccionado;
    private FecetRechazoPropuesta docRechazoSeleccionado;
    private FecetRetroalimentacion docRetroSeleccionado;
    private FecetTransferencia docTransSeleccionado;
    private FecetPropCancelada docCancelacionSeleccionado;

    public ConsultarPropuestasAsignadasParamHelper() {
        this.docOficioSeleccionado = new DocumentoOrdenModel();
        this.docOrdenSeleccionado = new DocumentoOrdenModel();
        this.docPapelSeleccionado = new DocumentoOrdenModel();
        this.docRechazoSeleccionado = new FecetRechazoPropuesta();
        this.docRetroSeleccionado = new FecetRetroalimentacion();
        this.docTransSeleccionado = new FecetTransferencia();
        this.docCancelacionSeleccionado = new FecetPropCancelada();
    }

    public BigDecimal getEstatusPropuesta1() {
        return estatusPropuesta1;
    }

    public void setEstatusPropuesta1(BigDecimal estatusPropuesta1) {
        this.estatusPropuesta1 = estatusPropuesta1;
    }

    public BigDecimal getEstatusPropuesta2() {
        return estatusPropuesta2;
    }

    public void setEstatusPropuesta2(BigDecimal estatusPropuesta2) {
        this.estatusPropuesta2 = estatusPropuesta2;
    }

    public BigDecimal getAccionPropuesta() {
        return accionPropuesta;
    }

    public void setAccionPropuesta(BigDecimal accionPropuesta) {
        this.accionPropuesta = accionPropuesta;
    }

    public DocumentoOrdenModel getDocOficioSeleccionado() {
        return docOficioSeleccionado;
    }

    public void setDocOficioSeleccionado(DocumentoOrdenModel docOficioSeleccionado) {
        this.docOficioSeleccionado = docOficioSeleccionado;
    }

    public DocumentoOrdenModel getDocOrdenSeleccionado() {
        return docOrdenSeleccionado;
    }

    public void setDocOrdenSeleccionado(DocumentoOrdenModel docOrdenSeleccionado) {
        this.docOrdenSeleccionado = docOrdenSeleccionado;
    }

    public DocumentoOrdenModel getDocPapelSeleccionado() {
        return docPapelSeleccionado;
    }

    public void setDocPapelSeleccionado(DocumentoOrdenModel docPapelSeleccionado) {
        this.docPapelSeleccionado = docPapelSeleccionado;
    }

    public FecetRechazoPropuesta getDocRechazoSeleccionado() {
        return docRechazoSeleccionado;
    }

    public void setDocRechazoSeleccionado(FecetRechazoPropuesta docRechazoSeleccionado) {
        this.docRechazoSeleccionado = docRechazoSeleccionado;
    }

    public FecetRetroalimentacion getDocRetroSeleccionado() {
        return docRetroSeleccionado;
    }

    public void setDocRetroSeleccionado(FecetRetroalimentacion docRetroSeleccionado) {
        this.docRetroSeleccionado = docRetroSeleccionado;
    }

    public FecetTransferencia getDocTransSeleccionado() {
        return docTransSeleccionado;
    }

    public void setDocTransSeleccionado(FecetTransferencia docTransSeleccionado) {
        this.docTransSeleccionado = docTransSeleccionado;
    }

    public FecetPropCancelada getDocCancelacionSeleccionado() {
        return docCancelacionSeleccionado;
    }

    public void setDocCancelacionSeleccionado(FecetPropCancelada docCancelacionSeleccionado) {
        this.docCancelacionSeleccionado = docCancelacionSeleccionado;
    }
}
