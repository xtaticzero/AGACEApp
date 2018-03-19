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
public enum TipoAccionPropuesta {

    ASIGNADAS_POR_FIRMANTE(1, "Asignadas por Firmante", 7),
    RETROALIMENTAR(2, "Retroalimentar", 6),
    EN_VALIDACION_DE_FIRMANTE(3, "En validación de Firmante", 6),
    EN_VALIDACION_DE_EMISION_DE_ORDENES_FIRMANTE(4, "En validacion de Emisión de Ordenes", 7),
    CANCELAR(5, "Cancelar", 6),
    TRANFERIR(6, "Tranferir", 6),
    RECHAZAR(7, "Rechazar", 6),
    FIRMA_DE_LA_ORDEN(8, "Firma de la orden", 7),
    EN_VALIDACION_DE_EMISION_DE_ORDENES_SUBADMINISTRADOR_EO(9, "En validacion de Emisión de Ordenes", 12),
    RETROALIMENTACION_NO_APROBADA(10, "Retroalimentacion No Aprobada", 7),
    RETROALIMENTACION_APROBADA(11, "Retroalimentacion  Aprobada", 7),
    RETROALIMENTACION_ATENDIDA(12, "Retroalimentación Atendida", 3),
    APROBACION_ORDEN(13, "Aprobación Orden", 12),
    NO_APROBACION_ORDEN(14, "No Aprobación Orden", 12),
    APROBACION_CANCELACION(15, "Aprobación Cancelación", 7),
    NO_APROBACION_CANCELACION(16, "No Aprobación Cancelación", 7),
    APROBACION_TRANSFERENCIA(17, "Aprobación Transferencia", 7),
    NO_APROBACION_TRANSFERENCIA(18, "No Aprobación Transferencia", 7),
    APROBACION_RECHAZO(19, "Aprobación Rechazo", 7),
    NO_APROBACION_RECHAZO(20, "No Aprobación Rechazo", 7),
    APROBACION_DE_VALIDACION(21, "Aprobación de Validación", 7),
    NO_APROBACION_DE_VALIDACION(22, "No Aprobación de Validación", 7),
    EN_VALIDACION_DE_EMISION_DE_ORDENES_ADMINISTRADOR_EO(23, "En validacion de Emisión de Ordenes", 11);

    private final int idAccion;
    private final String descriopcion;
    private final int idTipoEmpleado;

    private TipoAccionPropuesta(int idAccion, String descriopcion, int idTipoEmpleado) {
        this.idAccion = idAccion;
        this.descriopcion = descriopcion;
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public int getIdAccion() {
        return idAccion;
    }

    public String getDescriopcion() {
        return descriopcion;
    }

    public int getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

}
