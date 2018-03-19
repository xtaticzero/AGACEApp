/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common.sql;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface UnidadDesahogoSQL {

    String COLUM_ID_ARACE = "ID_ARACE";

    String SQL_SELECT_UNIDADES_DESAHOGO = "SELECT ID_ARACE \n"
            + "FROM FECEA_UNIDAD_DESAHOGO\n"
            + "WHERE 1=1\n"
            + "AND ID_EMPLEADO = ?\n"
            + "AND ID_TIPO_EMPLEADO = ? ";
}
