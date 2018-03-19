/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.enums;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum AgrupadorEstatusPropuestasEnum {
    PROPUESTA_ASIGNADA_A_FIRMANTE(1, "Propuesta Asignada a Firmante"),
    PROPUESTA_ASIGNADA_A_AUDITOR(2, "Propuesta Asignada a Auditor"),
    PROPUESTA_CANCELADA_PENDIENTE_DE_VALIDACION(3, "Propuesta cancelada pendiente de validaci\u00f3n"),
    PROPUESTAS_CANCELADAS(4, "Propuesta Cancelada"),
    PROPUESTA_RECHAZADA_PENDIENTE_DE_VALIDACION(5, "Propuesta rechazada pendiente de validaci\u00f3n"),
    PROPUESTA_RECHAZADA(6, "Propuesta Rechazada"),
    PROPUESTA_RETROALIMENTADA_PENDIENTE_DE_VALIDACION(7, "Propuesta Retroalimentada Pendiente de validaci\u00f3n"),
    PROPUESTA_EN_RETROALIMENTACION_AREA_OPERATIVA(8, "Propuesta en Retroalimentaci\u00f3n \u00c1rea Operativa"),
    PROPUESTA_EN_RETROALIMENTACION_A_PROGRAMACION(9, "Propuesta en Retroalimentaci\u00f3n a Programaci\u00f3n"),
    PROPUESTA_TRANSFERIDA_PENDIENTE_DE_VALIDACION(10, "Propuesta transferida pendiente de validaci\u00f3n"),
    PROPUESTA_TRANSFERIDA(11, "Propuesta Transferida"),
    PROPUESTA_PENDIENTE_DE_VALIDACION(12, "Propuesta Pendiente de Validaci\u00f3n"),
    PROPUESTA_EN_SOLICITUD_DE_EMISION_DE_ORDEN(13, "Propuesta en Solicitud de Emisi\u00f3n de Orden"),
    ORDEN_EMITIDA(14, "Orden Emitida"),
    ORDEN_EN_PROCESO(15, "Orden en Proceso"),
    REGISTRADA(16, "Registrada"),
    PROPUESTA_PENDIENTE_POR_COMITE(17, "Propuesta Pendiente por Comit\u00e9 "),
    PROPUESTA_NO_APROBADA_COMITE(18, "Propuesta no aprobada en Comit\u00e9 "),
    PROPUESTA_EN_SELECTOR(19, "Propuesta en Selector"),
    PROPUESTA_ENVIADA_ADACE(20, "Propuesta enviada a la ADACE"),
    PROPUESTA_ABIERTA_ADACE(21, "Propuesta Abierta por la ADACE"),
    PROPUESTA_PENDIENTE_COMITE_DESCONCENTRADO(22, "Propuesta Pendiente por Comit\u00e9 Desconcentrado");
    

    private final int idGrupo;
    private final String descripcion;

    private AgrupadorEstatusPropuestasEnum(int idGrupo, String descripcion) {
        this.idGrupo = idGrupo;
        this.descripcion = descripcion;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
