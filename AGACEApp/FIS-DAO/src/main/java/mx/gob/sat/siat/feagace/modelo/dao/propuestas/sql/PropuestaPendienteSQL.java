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
public interface PropuestaPendienteSQL {

    String ID_DOC_PENDIENTE = "{ID_DOC_PENDIENTE}";
    String ID_PROP_PENDIENTE = "{ID_PROP_PENDIENTE}";
    String ID_PROPUESTA = "{ID_PROPUESTA}";
    String ESTATUS = "{ESTATUS}";
    String RFC_CREACION = "{RFC_CREACION}";
    String OBSERVACIONES = "{OBSERVACIONES}";
    String RUTA_ARCHIVO = "{RUTA_ARCHIVO}";
    String BLN_ARCHIVO = "{BLN_ARCHIVO}";
    String FECHA_CREACION = "{FECHA_CREACION}";

    String UPDATE_ESTADO_X_ID_PROPUESTA = "UPDATE FECET_PROP_PENDIENTE PROP_PENDIENTE \n"
            + "SET PROP_PENDIENTE.ESTATUS = '{ESTATUS}' \n" + " WHERE PROP_PENDIENTE.ID_PROPUESTA = {ID_PROPUESTA}";

    String INSERT_X_PARAMETROS = "INSERT INTO "
            + "FECET_PROP_PENDIENTE ( ID_PROP_PENDIENTE, ID_PROPUESTA, FECHA_CREACION, RFC_CREACION, OBSERVACIONES, ESTATUS ) "
            + "VALUES ({ID_PROP_PENDIENTE} , {ID_PROPUESTA}"
            + ", SYSDATE, '{RFC_CREACION}','{OBSERVACIONES}','{ESTATUS}') ";

    String INSERT_X_PARAMETROS_DOC = "INSERT INTO "
            + "FECET_DOC_PROP_PENDIENTE ( ID_DOC_PENDIENTE,ID_PROP_PENDIENTE,RUTA_ARCHIVO,BLN_ACTIVO, FECHA_CREACION) "
            + "VALUES ({ID_DOC_PENDIENTE},{ID_PROP_PENDIENTE} " + ", '{RUTA_ARCHIVO}','{BLN_ARCHIVO}',SYSDATE) ";
}
