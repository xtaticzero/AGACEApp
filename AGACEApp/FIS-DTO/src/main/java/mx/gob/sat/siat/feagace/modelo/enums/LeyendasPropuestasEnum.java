package mx.gob.sat.siat.feagace.modelo.enums;

import java.math.BigDecimal;

public enum LeyendasPropuestasEnum {
    
    APROBACION_PROGRAMACION_ASIGNAR_CENTRAL_FIRMANTE(BigDecimal.valueOf(18), "Aviso de Aprobacion de Programacion para Asignar de Central a Firmante."),
    ASIGNACION_DOCUMENTO_VALIDACION_FIRMANTE(BigDecimal.valueOf(19), "Aviso de Asignacion de Documento para Validacion al Firmante."),
    ASIGNACION_PROPUESTA_A_ADMINISTRADOR(BigDecimal.valueOf(20), "Aviso de Asignacion de propuesta de Firmante para su asignacion a Administrador de PPCE o ADACE"),
    ASIGNACION_VERIFICACION_PROCEDENCIA_DOCTO(BigDecimal.valueOf(21), "Aviso de Asignacion de Solicitud de Verificacion de Procedencia de Documento Electronico."),
    ATENCION_RETROALIMENTACION_AREA_PROGRAMACION(BigDecimal.valueOf(24), "Aviso de Atencion a Solicitud de Retroalimentacion por parte del Area de Programacion."),
    AVISO_ORDEN_FIRMA_DOCTO_ELECTRONICO(BigDecimal.valueOf(32), "Aviso de Orden Firma de Documento Electronico."),
    PROPUESTA_ASIGNADA_CONSULTA(BigDecimal.valueOf(33), "Aviso de Propuesta Asignada para Consulta"),
    PROPUESTA_ASIGNADA_VALIDACION(BigDecimal.valueOf(34), "Aviso de Propuesta Asignada para su validacion."),
    PROPUESTA_RECHAZADA_VERIFICACION_PROCEDENCIA_DOCTO(BigDecimal.valueOf(36), "Aviso de Propuesta Rechazada en Verificacion de Procedencia de Documento Electronico."),
    RECHAZO_PROPUESTA_POR_ADECUAR(BigDecimal.valueOf(37), "Aviso de Rechazo de Propuesta por Adecuar."),
    SOLICITUD_RETROALIMENTACION_PROPUESTA_RECHAZADA(BigDecimal.valueOf(45), "Aviso de solicitud de retroalimentacion de propuesta rechazada."),
    SOLICITUD_VALIDACION_CANCELACION_PROPUESTA(BigDecimal.valueOf(46), "Aviso de Solicitud de validacion de Cancelacion de Propuesta"),
    SOLICITUD_VAIDACION_PROPUESTA_RETROALIMENTADA(BigDecimal.valueOf(47), "Aviso de Solicitud de validacion de Propuesta Retroalimentada."),
    SOLICITUD_VALIDACION_PROPUESTA_TRANSFERIDA(BigDecimal.valueOf(48), "Aviso de Solicitud de validacion de Propuesta Transferida."),
    SOLICITUD_VALIDACION_PROPUESTA_RECHAZADA(BigDecimal.valueOf(49), "Aviso de Solicitud de validacion de Rechazo de Propuesta."),
    VALIDACION_TRANSFERENCIA_PROPUESTA_RECHAZADA(BigDecimal.valueOf(51), "Aviso de validacion de transferencia de propuesta rechazada."),
    ORDEN_FIRMADA(BigDecimal.valueOf(62), "Aviso de Orden Firmada");
    
    private BigDecimal idLeyenda;
    private String nombre;
    
    LeyendasPropuestasEnum(BigDecimal idLeyenda, String nombre) {
        this.idLeyenda = idLeyenda;
        this.nombre = nombre;
    }

    public BigDecimal getIdLeyenda() {
        return idLeyenda;
    }

    public String getNombre() {
        return nombre;
    }

}
