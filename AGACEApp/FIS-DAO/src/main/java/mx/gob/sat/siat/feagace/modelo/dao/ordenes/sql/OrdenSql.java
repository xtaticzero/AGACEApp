/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.sql;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface OrdenSql {

    String SQL_TIPO_ASOCIADO_CONTRIBUYENTE = "SELECT DISTINCT FECET_ASOCIADO.RFC_CONTRIBUYENTE  FROM FECEC_TIPO_ASOCIADO \n"
            + "INNER JOIN FECET_ASOCIADO ON FECEC_TIPO_ASOCIADO.ID_TIPO_ASOCIADO = FECET_ASOCIADO.ID_TIPO_ASOCIADO\n"
            + "WHERE FECET_ASOCIADO.RFC = ? \n"
            + "AND FECET_ASOCIADO.ID_TIPO_ASOCIADO = ? \n"
            + "AND FECET_ASOCIADO.FECHA_BAJA IS NULL";

    String SQL_SELECT = "SELECT\n"
            + "P.ID_ARACE,\n"
            + "ORD.ID_ORDEN, \n"
            + "ORD.ID_METODO, \n"
            + "ORD.ID_REVISION, \n"
            + "ORD.NUMERO_ORDEN, \n"
            + "ORD.FECHA_CREACION, \n"
            + "ORD.FECHA_BAJA, \n"
            + "ORD.PRIORIDAD, \n"
            + "ORD.FOLIO_NYV, \n"
            + "ORD.CADENA_ORIGINAL, \n"
            + "ORD.FIRMA_ELECTRONICA,\n"
            + "ORD.FECHA_NOTIF_NYV, \n"
            + "ORD.FECHA_NOTIF_CONT, \n"
            + "ORD.FECHA_SURTE_EFECTOS, \n"
            + "ORD.DIAS_RESTANTES_PLAZO, \n"
            + "ORD.DIAS_HABILES, \n"
            + "ORD.SUSPENCION_PLAZO, \n"
            + "ORD.DIAS_RESTANTES_DOCUMENTOS, \n"
            + "ORD.SEMAFORO, \n"
            + "ORD.FECHA_INTEGRA_EXP, \n"
            + "ORD.ID_CONTRIBUYENTE ID_CONTRIBUYENTE, \n"
            + "ORD.ID_ESTATUS, \n"
            + "ORD.ID_AUDITOR, \n"
            + "ORD.ID_FIRMANTE, \n"
            + "ORD.ID_PROPUESTA, \n"
            + "ORD.ID_REGISTRO_PROPUESTA, \n"
            + "ORD.FECHA_REACTIVAR_PLAZO, \n"
            + "ORD.FECHA_SUSPENCION_PLAZO, \n"
            + "ORD.DIAS_RESOLUCION_DEFINITIVA, \n"
            + "ORD.FOLIO_OFICIO, \n"
            + "ORD.BLN_COMPULSA, \n"
            + "ORD.ID_NYV,\n"
            + "CON.RFC,\n"
            + "CON.NOMBRE,\n"
            + "CON.REGIMEN,\n"
            + "CON.SITUACION,\n"
            + "CON.TIPO,\n"
            + "CON.SITUACION_DOMICILIO,\n"
            + "CON.DOMICILIO_FISCAL,\n"
            + "CON.ACTIVIDAD_PREPONDERANTE,\n"
            + "CON.ENTIDAD\n"
            + "FROM FECET_ORDEN ORD\n"
            + "INNER JOIN FECET_PROPUESTA P ON P.ID_PROPUESTA = ORD.ID_PROPUESTA\n"
            + "LEFT JOIN FECET_CONTRIBUYENTE CON ON ORD.ID_CONTRIBUYENTE = CON.ID_CONTRIBUYENTE ";

    String SQL_WHERE_FECHA_BAJA_NULL = " WHERE ORD.FECHA_BAJA IS NULL ";

    String TABLE_NAME = "FECET_ORDEN";

    String SQL_CONSULTA_ORDEN_AC = "SELECT \n"
            + "PRO.ID_ARACE,\n"
            + "ORD.NUMERO_ORDEN, \n"
            + "CON.RFC, \n"
            + "CON.NOMBRE, \n"
            + "EST.DESCRIPCION ESTATUS, \n"
            + "NULL AS VENCIMIENTO, \n"
            + "MET.ABREVIATURA AS TIPO_ORDEN, \n"
            + "PRO.FECHA_INICIO_PERIODO, \n"
            + "PRO.FECHA_FIN_PERIODO,  \n"
            + "CON.ACTIVIDAD_PREPONDERANTE\n"
            + "FROM FECET_ORDEN ORD\n"
            + "LEFT JOIN FECET_CONTRIBUYENTE CON ON ORD.ID_CONTRIBUYENTE = CON.ID_CONTRIBUYENTE\n"
            + "LEFT JOIN FECEC_ESTATUS EST ON ORD.ID_ESTATUS = EST.ID_ESTATUS\n"
            + "LEFT JOIN FECEC_METODO MET ON ORD.ID_METODO = MET.ID_METODO\n"
            + "LEFT JOIN FECET_PROPUESTA PRO ON ORD.ID_PROPUESTA = PRO.ID_PROPUESTA\n"
            + "WHERE ORD.NUMERO_ORDEN = ?";
}
