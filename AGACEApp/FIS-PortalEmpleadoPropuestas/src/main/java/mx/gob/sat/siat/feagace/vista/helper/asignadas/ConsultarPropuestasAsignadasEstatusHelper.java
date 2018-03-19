package mx.gob.sat.siat.feagace.vista.helper.asignadas;

import java.io.Serializable;
import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

public class ConsultarPropuestasAsignadasEstatusHelper implements Serializable {

    private static final long serialVersionUID = 9043140088402755858L;

    private BigDecimal asignadaAuditor;
    private BigDecimal retroalimentada;
    private BigDecimal noAprobadaRetroalimentacion;
    private BigDecimal adecuarAuditor;
    private BigDecimal verificacionProcedencia;
    private BigDecimal noAprobadaCancelacion;
    private BigDecimal noAprobadaTransferencia;
    private BigDecimal transferida;
    private BigDecimal noAprobadoRechazo;
    private BigDecimal retroPendienteValidacion;
    private BigDecimal pendienteValidacion;
    private BigDecimal enviadaVerificacionProcedencia;
    private BigDecimal asignadoSubadministrador;
    private BigDecimal cancelacionPendienteValidacion;
    private BigDecimal enviadoAprobarTransferido;
    private BigDecimal rechazadaPendienteValidacion;
    private BigDecimal registroEnRetroalimentacion;
    private BigDecimal concluida;
    private BigDecimal accionCancelacion;
    private BigDecimal accionRechazo;
    private BigDecimal sinEstatus2;
    private BigDecimal sinAccion;

    public ConsultarPropuestasAsignadasEstatusHelper() {
        this.asignadaAuditor = new BigDecimal(TipoEstatusEnum.PROPUESTA_REGISTRO_ASIGNADO_AUDITOR.getId());
        this.retroalimentada = new BigDecimal(TipoEstatusEnum.PROPUESTA_RETROALIMENTADA.getId());
        this.noAprobadaRetroalimentacion = new BigDecimal(TipoEstatusEnum.NO_APROBADA_LA_RETROALIMENTACION.getId());
        this.adecuarAuditor = new BigDecimal(TipoEstatusEnum.PROPUESTA_RECHAZADA_PARA_ADECUAR_POR_AUDITOR.getId());
        this.verificacionProcedencia = new BigDecimal(
                TipoEstatusEnum.PROPUESTA_RECHAZADA_PENDIENTE_VERIFICACION_PROCEDENCIA_DOCUMENTO_ELECTRONICO.getId());
        this.noAprobadaCancelacion = new BigDecimal(TipoEstatusEnum.NO_APROBADA_LA_CANCELACION.getId());
        this.noAprobadaTransferencia = new BigDecimal(TipoEstatusEnum.FIRMANTE_TRANSFERENCIA_NO_APROBADA.getId());
        this.transferida = new BigDecimal(TipoEstatusEnum.PROPUESTA_TRANSFERIDA.getId());
        this.noAprobadoRechazo = new BigDecimal(TipoEstatusEnum.PROPUESTA_NO_APROBADO_EL_RECHAZO.getId());
        this.retroPendienteValidacion = new BigDecimal(
                TipoEstatusEnum.RETROALIMENTACION_PENDIENTE_DE_VALIDACION.getId());
        this.pendienteValidacion = new BigDecimal(TipoEstatusEnum.PROPUESTA_PENDIENTE_VALIDACION.getId());
        this.enviadaVerificacionProcedencia = new BigDecimal(
                TipoEstatusEnum.PROPUESTA_ENVIADA_PARA_VERIFICACION_PROCEDENCIA.getId());
        this.asignadoSubadministrador = new BigDecimal(
                TipoEstatusEnum.PROPUESTA_REGISTRO_ASIGNADO_SUBADMINISTRADOR_EMISION_ODENES.getId());
        this.cancelacionPendienteValidacion = new BigDecimal(
                TipoEstatusEnum.CANCELACION_PENDIENTE_DE_VALIDACION.getId());
        this.enviadoAprobarTransferido = new BigDecimal(
                TipoEstatusEnum.PROPUESTA_REGISTRO_ENVIADO_APROBACION_TRANSFERIDO.getId());
        this.rechazadaPendienteValidacion = new BigDecimal(
                TipoEstatusEnum.PROPUESTA_RECHAZADA_PENDIENTE_VALIDACION.getId());
        this.registroEnRetroalimentacion = new BigDecimal(
                TipoEstatusEnum.PROPUESTA_REGISTRO_EN_RETROALIMENTACION.getId());
        this.concluida = new BigDecimal(TipoEstatusEnum.PROPUESTA_CONCLUIDA.getId());
        this.accionCancelacion = new BigDecimal(Constantes.ENTERO_QUINCE);
        this.accionRechazo = new BigDecimal(Constantes.ENTERO_DIECINUEVE);
        this.sinEstatus2 = new BigDecimal(Constantes.ENTERO_CERO);
        this.sinAccion = new BigDecimal(Constantes.ENTERO_CERO);
    }

    public BigDecimal getAsignadaAuditor() {
        return asignadaAuditor;
    }

    public void setAsignadaAuditor(BigDecimal asignadaAuditor) {
        this.asignadaAuditor = asignadaAuditor;
    }

    public BigDecimal getRetroalimentada() {
        return retroalimentada;
    }

    public void setRetroalimentada(BigDecimal retroalimentada) {
        this.retroalimentada = retroalimentada;
    }

    public BigDecimal getAdecuarAuditor() {
        return adecuarAuditor;
    }

    public void setAdecuarAuditor(BigDecimal adecuarAuditor) {
        this.adecuarAuditor = adecuarAuditor;
    }

    public BigDecimal getVerificacionProcedencia() {
        return verificacionProcedencia;
    }

    public void setVerificacionProcedencia(BigDecimal verificacionProcedencia) {
        this.verificacionProcedencia = verificacionProcedencia;
    }

    public BigDecimal getNoAprobadaCancelacion() {
        return noAprobadaCancelacion;
    }

    public void setNoAprobadaCancelacion(BigDecimal noAprobadaCancelacion) {
        this.noAprobadaCancelacion = noAprobadaCancelacion;
    }

    public BigDecimal getNoAprobadaTransferencia() {
        return noAprobadaTransferencia;
    }

    public void setNoAprobadaTransferencia(BigDecimal noAprobadaTransferencia) {
        this.noAprobadaTransferencia = noAprobadaTransferencia;
    }

    public BigDecimal getTransferida() {
        return transferida;
    }

    public void setTransferida(BigDecimal transferida) {
        this.transferida = transferida;
    }

    public BigDecimal getNoAprobadoRechazo() {
        return noAprobadoRechazo;
    }

    public void setNoAprobadoRechazo(BigDecimal noAprobadoRechazo) {
        this.noAprobadoRechazo = noAprobadoRechazo;
    }

    public BigDecimal getRetroPendienteValidacion() {
        return retroPendienteValidacion;
    }

    public void setRetroPendienteValidacion(BigDecimal retroPendienteValidacion) {
        this.retroPendienteValidacion = retroPendienteValidacion;
    }

    public BigDecimal getEnviadaVerificacionProcedencia() {
        return enviadaVerificacionProcedencia;
    }

    public void setEnviadaVerificacionProcedencia(BigDecimal enviadaVerificacionProcedencia) {
        this.enviadaVerificacionProcedencia = enviadaVerificacionProcedencia;
    }

    public BigDecimal getAsignadoSubadministrador() {
        return asignadoSubadministrador;
    }

    public void setAsignadoSubadministrador(BigDecimal asignadoSubadministrador) {
        this.asignadoSubadministrador = asignadoSubadministrador;
    }

    public BigDecimal getCancelacionPendienteValidacion() {
        return cancelacionPendienteValidacion;
    }

    public void setCancelacionPendienteValidacion(BigDecimal cancelacionPendienteValidacion) {
        this.cancelacionPendienteValidacion = cancelacionPendienteValidacion;
    }

    public BigDecimal getEnviadoAprobarTransferido() {
        return enviadoAprobarTransferido;
    }

    public void setEnviadoAprobarTransferido(BigDecimal enviadoAprobarTransferido) {
        this.enviadoAprobarTransferido = enviadoAprobarTransferido;
    }

    public BigDecimal getRechazadaPendienteValidacion() {
        return rechazadaPendienteValidacion;
    }

    public void setRechazadaPendienteValidacion(BigDecimal rechazadaPendienteValidacion) {
        this.rechazadaPendienteValidacion = rechazadaPendienteValidacion;
    }

    public BigDecimal getConcluida() {
        return concluida;
    }

    public void setConcluida(BigDecimal concluida) {
        this.concluida = concluida;
    }

    public BigDecimal getAccionCancelacion() {
        return accionCancelacion;
    }

    public void setAccionCancelacion(BigDecimal accionCancelacion) {
        this.accionCancelacion = accionCancelacion;
    }

    public BigDecimal getAccionRechazo() {
        return accionRechazo;
    }

    public void setAccionRechazo(BigDecimal accionRechazo) {
        this.accionRechazo = accionRechazo;
    }

    public BigDecimal getPendienteValidacion() {
        return pendienteValidacion;
    }

    public void setPendienteValidacion(BigDecimal pendienteValidacion) {
        this.pendienteValidacion = pendienteValidacion;
    }

    public BigDecimal getNoAprobadaRetroalimentacion() {
        return noAprobadaRetroalimentacion;
    }

    public void setNoAprobadaRetroalimentacion(BigDecimal noAprobadaRetroalimentacion) {
        this.noAprobadaRetroalimentacion = noAprobadaRetroalimentacion;
    }

    public BigDecimal getRegistroEnRetroalimentacion() {
        return registroEnRetroalimentacion;
    }

    public void setRegistroEnRetroalimentacion(BigDecimal registroEnRetroalimentacion) {
        this.registroEnRetroalimentacion = registroEnRetroalimentacion;
    }

    public BigDecimal getSinEstatus2() {
        return sinEstatus2;
    }

    public void setSinEstatus2(BigDecimal sinEstatus2) {
        this.sinEstatus2 = sinEstatus2;
    }

    public BigDecimal getSinAccion() {
        return sinAccion;
    }

    public void setSinAccion(BigDecimal sinAccion) {
        this.sinAccion = sinAccion;
    }
}
