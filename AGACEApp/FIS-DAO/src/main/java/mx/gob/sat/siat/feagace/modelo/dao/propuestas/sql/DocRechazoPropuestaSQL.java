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
public interface DocRechazoPropuestaSQL {

    String SQL_SELECT = "SELECT \n"
            + "ID_RECHAZO_PROPUESTA, \n"
            + "ID_PROPUESTA, \n"
            + "RUTA_ARCHIVO, \n"
            + "FECHA_CREACION \n"
            + "FROM FECET_DOC_RECHAZO_PROPUESTA ";
}
