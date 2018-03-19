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
public enum TipoEstatusEnum {

    INSUMO_REGISTRADO(1L, "Insumo Registrado"),
    ACIACE_INSUMO_POR_RETROALIMENTAR(2L, "Insumo por Retroalimentar"),
    ACIACE_INSUMO_RETROALIMENTADO(3L, "Insumo Retroalimentado"),
    ADMINISTRADOR_INSUMO_ASIGNADOS_SUBADMINISTRADOR(4L, "Insumo Asignados a Subadministrador"),
    ADMINISTRADOR_INSUMO_REASIGNADO_ADMINISTRADOR(5L, "Insumo reasignado a Administrador"),
    ADMINISTRADOR_INSUMO_REASIGNADOS_ACEPTADOS(6L, "Insumos reasignados aceptados"),
    ADMINISTRADOR_INSUMO_REASIGNADOS_NO_APROBADOS(7L, "Insumos reasignados No aprobados"),
    SUBADMINISTRADOR_INSUMO_RECHAZADO(11L, "Insumo Rechazado"),
    SUBADMINISTRADOR_INSUMO_X_ATENDER_RETROALIMENTACION(12L, "Insumos por atender Retroalimentación"),
    SUBADMINISTRADOR_RETROALIMENTACION_COMPLEMENTADA(13L, "Retroalimentacion Complementada"),
    SUBADMINISTRADOR_INSUMO_ACEPTADO(14L, "Insumo Aceptado"),
    PROGRAMACION_CREADA(21L, "Propuesta Creada"),
    PROGRAMACION_APROBADA(22L, "Propuesta Aprobada"),
    PROGRAMACION_RECHAZADA(23L, "Propuesta Rechazada"),
    PROGRAMACION_PENDIENTE(24L, "Propuesta Pendiente"),
    PROGRAMACION_RETROALIMENTADA(25L, "Propuesta Retroalimentada"),
    PROGRAMACION_PARA_RETROALIMENTACION_RECHAZADA(26L, "Propuesta para Retroalimentacion Rechazada"),
    PROGRAMACION_COMPLEMENTADA(27L, "Propuesta Complementada"),
    AUDITOR_ENVIADA_RETROALIMENTAR(31L, "Propuesta Enviada a Retroalimentar"),
    AUDITOR_ENVIADA_VALIDACION_POR_TRANSFERENCIA(32L, "Propuesta Enviada a Validacion por Transferencia"),
    AUDITOR_ENVIADA_VALIDACION_POR_RECHAZO(33L, "Propuesta Enviada a Validacion por Rechazo"),
    FIRMANTE_RECHAZO_NO_APROBADO(41L, "Rechazo no Aprobado"),
    FIRMANTE_TRANSFERENCIA_NO_APROBADA(42L, "Transferencia no Aprobada"),
    FIRMANTE_RECHAZO_APROBADO(43L, "Rechazo Aprobado"),
    FIRMANTE_TRANSFERENCIA_APROBADA(44L, "Transferencia Aprobada"),
    SUBADMINISTRADOR_PPCE_CONCLUIDA_PARA_SEGUIMIENTO_COMO_ORDEN(45L, "Propuesta Concluida para Seguimiento como Orden"),
    PROPUESTA_ASIGNADA_CENTRAL(46L, "Asignada a central"),
    FIRMANTE_CARTA_ENVIADA_PARA_NOTIFICACION_AL_CONTRIBUYENTE(47L, "Carta enviada para Notificación al Contribuyente"),
    PROGRAMACION_COMPLEMENTADA_POR_REGIONAL(48L, "Complementada por Regional"),
    PROPUESTA_CONFIRMADO_POR_REGIONAL(49L, "Confirmado por Regional"),
    PROPUESTA_ENVIADA_PARA_NOTIFICACION_AL_CONTRIBUYENTE(50L, "Enviada para Notificación al Contribuyente"),
    PROPUESTA_ENVIADA_PARA_VERIFICACION_PROCEDENCIA(51L, "Enviada para Verificación de Procedencia"),
    PROPUESTA_NO_APROBADA_RETROALIMENTACION(52L, "No aprobada la Retroalimentación"),
    PROPUESTA_NO_APROBADA_TRANSFERENCIA(53L, "No aprobada la transferencia"),
    PROPUESTA_NO_APROBADO_EL_RECHAZO(54L, "No aprobado el Rechazo"),
    AUDITOR_OFICIO_RETROALIMENTADO_PARA_VALIDACION(55L, "Oficio retroalimentado para validación"),
    PROPUESTA_PENDIENTE(56L, "Pendiente"),
    PROPUESTA_PENDIENTE_VALIDACION(57L, "Pendiente de validación"),
    PROPUESTA_ENVIADA_SOLICITUD_RETROALIMENTACION(58L, "Propuesta Enviada a Solicitud de Retroalimentación"),
    PROPUESTA_PENDIENTE_FIRMA_POR_FIRMANTE(59L, "Propuesta pendiente de Firma por Firmante"),
    AUDITOR_RECHAZADA(60L, "Propuesta Rechazada"),
    PROPUESTA_RECHAZADA_PARA_ADECUAR_POR_AUDITOR(61L, "Propuesta Rechazada para adecuar por Auditor"),
    PROPUESTA_RECHAZADA_PENDIENTE_VALIDACION(62L, "Propuesta rechazada pendiente de validación"),
    SUBADMINISTRADOR_RECHAZADA_PENDIENTE_VERIFICACION_PROCEDENCIA_DOCUMENTO_ELECTRONICO(63L, "Propuesta rechazada pendiente Verificación de Procedencia de Documento Electrónico"),
    PROPUESTA_RECHAZADA_POR_PROGRAMADOR_PENDIENTE(64L, "Rechazada por programador (pendiente)"),
    PROPUESTA_RECHAZADA_POR_PROGRAMADOR_AL_VALIDAR(65L, "Rechazada por programador (al validar)"),
    PROPUESTA_RECHAZADO_POR_ADACE(66L, "Rechazado por ADACE"),
    PROPUESTA_RECHAZADA_POR_FIRMANTE(67L, "Rechazada por Firmante"),
    PROPUESTA_REGISTRADA(68L, "Registrada"),
    PROPUESTA_REGISTRO_ASIGNADO_AUDITOR(69L, "Registro Asignado a Auditor"),
    PROPUESTA_REGISTRO_ASIGNADO_FIRMANTE(70L, "Registro Asignado a Firmante"),
    PROPUESTA_REGISTRO_ASIGNADO_SUBADMINISTRADOR_EMISION_ODENES(71L, "Registro Asignado a Subadministrador de emisión de ódenes"),
    PROPUESTA_REGISTRO_EN_RETROALIMENTACION(72L, "Registro en Retroalimentación"),
    PROPUESTA_REGISTRO_TRANSFERIDO(73L, "Registro Transferido"),
    PROPUESTA_RETROALIMENTADA(74L, "Retroalimentada"),
    PROPUESTA_TRANSFERENCIA_APROBADA(75L, "Transferencia Aprobada"),
    PROPUESTA_TRANSFERIDA(76L, "Transferida"),
    ARACE_ACEPTADA_REASIGNACION_ADMINISTRADOR(77L, "Aceptada reasignación a administrador"),
    CENTRAL_ASIGNADA_SUBADMINISTRADOR(78L, "Asignada a subadministrador"),
    ACPPCE_INSUMO_RECHAZADO(79L, "Insumo Rechazado"),
    ACIACE_INSUMO_REGISTRADO(80L, "Insumo Registrado"),
    ACIACE_INSUMO_RETROALIMENTADO_RET(81L, "Insumo Retroalimentado"),
    ARACE_REASIGNADA_ADMINISRADOR(82L, "Reasignada a Adminisrador"),
    ARACE_RECHAZO_REASIGNACION_ADMINISTRADOR(83L, "Rechazo de reasignación a administrador"),
    ACPPCE_SOLICITUD_RETROALIMENTACION(84L, "Solicitud de Retroalimentación"),
    SUBADMINISTRADOR_PPCE_ORDEN_CREADA_PENDIENTE_FIRMA(101L, "Orden Creada Pendiente de Firma"),
    FIRMANTE_REGISTRO_FIRMADO_ENVIADO_NOTIFICACION_CONTRIBUYENTE(102L, "Registro Firmado y Enviado a Notificación del Contribuyente"),
    CRON_REGISTRO_NOTIFICADO_AL_CONTRIBUYENTE(103L, "Registro notificado al contribuyente"),
    CRON_ERROR_AL_NOTIFICAR_AL_CONTRIBUYENTE(104L, "Error al notificar al contribuyente"),
    FIRMANTE_PLAZO_ORDEN_SUSPENDIDO_POR_ACUERDO_CONCLUSIVO(105L, "Plazo de la Orden Suspendido por Acuerdo Conclusivo"),
    AUDITOR_REACTIVACION_PLAZO_ACUERDO_CONCLUSIVO(106L, "Reactivacion de Plazo de Acuerdo Conclusivo"),
    AUDITOR_REACTIVACION_PLAZO_COMPULSAS(107L, "Reactivacion de Plazo de Compulsas"),
    FIRMANTE_PLAZO_ORDEN_SUSPENDIDO_POR_COMPULSA_REE(108L, "Plazo de la Orden Suspendido por Compulsa (REE)"),
    CRON_ORDEN_EN_ESPERA_EMISION_RESOLUCION_DEFINITIVA_ORG(109L, "Orden en Espera de Emision de Resolucion Definitiva(ORG)"),
    FIRMANTE_ORDEN_CONCLUIDA(110L, "Orden Concluida"),
    FIRMANTE_ORDEN_CONCLUIDA_POR_CAMBIO_METODO(111L, "Orden Concluida por Cambio de Metodo"),
    FIRMANTE_ORDEN_CREADA_POR_CAMBIO_METODO(112L, "Orden Creada por Cambio de Metodo"),
    AUDITOR_OFICIO_CREADO_PENDIENTE_FIRMA(113L, "Oficio Creado Pendiente de Firma"),
    FIRMANTE_OFICIO_FIRMADO_Y_ENVIADO_NOTIFICACION_DEL_CONTRIBUYENTE(114L, "Oficio Firmado y Enviado a Notificación del Contribuyente"),
    CRON_OFICIO_NOTIFICADO_AL_CONTRIBUYENTE(115L, "Oficio notificado al contribuyente"),
    FIRMANTE_OFICIO_RECHAZADO(116L, "Oficio Rechazado"),
    PROPUESTA_OFICIO_APROBADO(117L, "Oficio Aprobado"),
    PROPUESTA_RECHAZADA_PENDIENTE_VERIFICACION_PROCEDENCIA_DOCUMENTO_ELECTRONICO(118L, "Propuesta rechazada pendiente Verificación de Procedencia de Documento Electrónico"),
    PROPUESTA_RECHAZO_VERIFICACION_DOCUMENTO_ORDEN(119L, "Rechazo de verificación de documento orden"),
    PROPUESTA_REGISTRO_ENVIADO_APROBACION_TRANSFERIDO(120L, "Registro Enviado Aprobación de transferido"),
    PRORROGA_PENDIENTE_DE_APROBACION(121L, "Prórroga Pendiente de aprobación"),
    RESOLUCION_PRORROGA_APROBADA_POR_AUDITOR(122L, "Resolución de Prórroga Aprobada por Auditor"),
    RESOLUCION_PRORROGA_APROBADA_POR_FIRMANTE(123L, "Resolucion de prorroga aprobada por el firmante"),
    RESOLUCION_PRORROGA_DEL_AUDITOR_NO_ES_APROBADA_POR_FIRMANTE(124L, "Resolución de Prórroga del Auditor No es Aprobada por Firmante"),
    RESOLUCION_DE_PRORROGA_FIRMADA_ENVIADA_NYV(125L, "Resolucion de prorroga firmada y enviada a NyV"),
    RESOLUCION_DE_PRORROGA_CONCLUIDA_NO_APROBADA(126L, "Resolución de Prórroga Concluida No Aprobada"),
    PRORROGA_NOTIFICADA_AL_CONTRIBUYENTE(127L, "Prórroga Notificada al Contribuyente"),
    ERROR_AL_NOTIFICAR_LA_PRORROGA_AL_CONTRIBUYENTE(128L, "Error al notificar la Prórroga al contribuyente"),
    RESOLUCION_DE_PRORROGA_CONCLUIDA_APROBADA(129L, "Resolución de Prórroga Concluida Aprobada"),
    CAMBIO_METODO_REGISTRADO(130L, "Cambio de metodo registrado"),
    CAMBIO_METODO_APROBADO_POR_FIRMANTE(131L, "Cambio de metodo aprobado por firmante"),
    CAMBIO_METODO_RECHAZADO_POR_FIRMANTE(132L, "Cambio de metodo rechazado por firmante"),
    PROPUESTA_RECHAZADA_VALIDACION_FIRMANTE(133L, "Propuesta Rechazada por Validacion de Firmante"),
    COMPULSA_REGISTRADO(134L, "Compulsa registrado"),
    COMPULSA_PENDIENTE_DE_FIRMA(135L, "Compulsa pendiente de firma"),
    COMPULSA_APROBADA_POR_FIRMANTE(136L, "Compulsa aprobada por firmante"),
    COMPULSA_RECHAZADA_POR_FIRMANTE(137L, "Compulsa rechazada por firmante"),
    PROPUESTA_NO_APROBADA_LA_RETROALIMENTACION_CON_REVISION(138L, "No aprobada la Retroalimentación con Revisión"),
    PROPUESTA_NO_APROBADA_LA_TRANSFERENCIA_CON_REVISION(139L, "No aprobada la transferencia con Revisión"),
    PROPUESTA_RETROALIMENTADA_CON_REVISION(140L, "Retroalimentada con Revisión"),
    RESOLUCION_PRORROGA_NO_APROBADA(141L, "Resolución de Prórroga No Aprobada"),
    INSUMO_AGREGADO(142L, "Insumo agregado"),
    CANCELACION_PENDIENTE_DE_VALIDACION(143L, "Cancelación pendiente de valoidación"),
    PROPUESTA_CONCLUIDA(144L, "Propuesta Concluida"),
    RETROALIMENTACION_PENDIENTE_DE_VALIDACION(145L, "Retroalimentación pendiente de validación"),
    NO_APROBADA_LA_RETROALIMENTACION(146L, "No aprobada la retroalimentación"),
    NO_APROBADA_LA_CANCELACION(147L, "No aprobada la cancelación"),
    RESOLUCION_PRUEBAS_PERICIALES_APROBADA_FIRMANTE(149L, "Resolucion de pruebas periciales aprobada por el firmante"),
    RESOLUCION_PRUEBAS_PERICIALES_NO_APROBADA_FIRMANTE(150L, "Resolucion de pruebas periciales no aprobada por el firmante"),
    RESOLUCION_PRUEBAS_PERICIALES_FIRMADA_ENVIADA_NYV(151L, "Resolucion de pruebas periciales firmada y enviada a NyV"),
    RESOLUCION_PRUEBA_PERICIAL_CONCLUIDA_APROBADA(156L, "Resolucion de Prueba Pericial Concluida Aprobada"),
    PRUEBA_PERICIAL_RESUELTA_AUDITOR(158L, "Prueba Pericial resuelta por auditor");
    

    /**
     * ID_ARACE
     */
    private final long id;

    /**
     * DESCRIPCION
     */
    private final String descripcion;

    private TipoEstatusEnum(long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoEstatusEnum getById(Long id) {
        for (TipoEstatusEnum estatus : values()) {
            if (estatus.id == id) {
                return estatus;
            }
        }
        return null;
    }
}
