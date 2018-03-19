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
public interface DocOrdenSQL {

    String TABLE_NAME = " FECET_DOC_ORDEN ";

    String SQL_SELECT = "SELECT ID_DOC_ORDEN, ID_ORDEN, RUTA_ARCHIVO, DOCUMENTO_PDF, FECHA_CREACION, ESTATUS, BLN_ACTIVO FROM ";

    String SQL_SELECT_PROP = "SELECT FECET_DOC_ORDEN.ID_DOC_ORDEN, \n"
            + "FECET_ORDEN.ID_PROPUESTA, \n"
            + "FECET_DOC_ORDEN.RUTA_ARCHIVO, \n"
            + "FECET_DOC_ORDEN.FECHA_CREACION, \n"
            + "FECET_DOC_ORDEN.BLN_ACTIVO \n"
            + "FROM FECET_DOC_ORDEN \n"
            + "INNER JOIN FECET_ORDEN ON (FECET_DOC_ORDEN.ID_ORDEN = FECET_ORDEN.ID_ORDEN)";

    String SQL_SELECT_PROP_HIST = "SELECT FECET_DOC_ORDEN.ID_DOC_ORDEN, \n"
            + "FECET_DOC_ORDEN.ID_ORDEN, \n"
            + " FECET_DOC_ORDEN.RUTA_ARCHIVO, \n"
            + " FECET_DOC_ORDEN.FECHA_CREACION, \n"
            + " FECET_DOC_ORDEN.BLN_ACTIVO, \n"
            + " FECET_RECHAZO_ORDEN.ID_EMPLEADO_RECHAZO as ID_EMPLEADO, \n"
            + " FECET_RECHAZO_ORDEN.DESCRIPCION as ID_TIPO_EMPLEADO , \n"
            + "  FECET_RECHAZO_ORDEN.FECHA_RECHAZO as FECHA_HORA \n"
            + " FROM FECET_DOC_ORDEN \n"
            + " INNER JOIN FECET_RECHAZO_ORDEN ON (FECET_RECHAZO_ORDEN.ID_DOC_ORDEN=FECET_DOC_ORDEN.ID_DOC_ORDEN)  \n"
            + " INNER JOIN FECET_ORDEN ON (FECET_ORDEN.ID_ORDEN=FECET_DOC_ORDEN.ID_ORDEN) ";
}
