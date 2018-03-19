/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface RechazoPropuestaSQL {

    String SQL_SELECT_X_IDPROPUESTA = "SELECT \n"
            + "ID_RECHAZO_PROPUESTA, \n"
            + "ID_PROPUESTA, \n"
            + "ID_MOTIVO, \n"
            + "DESCRIPCION, \n"
            + "FECHA_RECHAZO, \n"
            + "FECHA_INFORME_RECHAZO, \n"
            + "RFC_RECHAZO \n"
            + "FROM FECET_RECHAZO_PROPUESTA, FECET_DETALLE_EMPLEADO \n"
            + "WHERE FECET_RECHAZO_PROPUESTA.ID_EMPLEADO = FECET_DETALLE_EMPLEADO.ID_EMPLEADO \n"
            + "AND ID_ARACE = ? \n"
            + "AND ID_TIPO_EMPLEADO = ?\n"
            + "AND ID_PROPUESTA = ? ORDER BY ID_RECHAZO_PROPUESTA DESC";

    String SQL_SELECT_RECHAZOS_X_IDPROPUESTA = "SELECT \n"
            + "FECET_RECHAZO_PROPUESTA.ID_RECHAZO_PROPUESTA, \n"
            + "FECET_RECHAZO_PROPUESTA.ID_PROPUESTA, \n"
            + "FECET_RECHAZO_PROPUESTA.FECHA_RECHAZO, \n"
            + "FECET_RECHAZO_PROPUESTA.ID_MOTIVO,\n"
            + "FECET_RECHAZO_PROPUESTA.DESCRIPCION, \n"
            + "FECET_RECHAZO_PROPUESTA.FECHA_INFORME_RECHAZO, \n"
            + "FECET_RECHAZO_PROPUESTA.RFC_RECHAZO, \n"
            + "FECEC_MOTIVO.DESCRIPCION AS DESCRIPCION_RECHAZO \n"
            + "FROM FECET_RECHAZO_PROPUESTA \n"
            + "INNER JOIN FECEC_MOTIVO ON FECEC_MOTIVO.ID_MOTIVO = FECET_RECHAZO_PROPUESTA.ID_MOTIVO \n"
            + "WHERE FECET_RECHAZO_PROPUESTA.ID_PROPUESTA = ? ORDER BY FECET_RECHAZO_PROPUESTA.ID_RECHAZO_PROPUESTA DESC";
}
