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
public interface AgaceIdcSQL {

    String SQL_GET_ID_BY_NOMBRE = "SELECT ID_UNIDAD_ADMINISTRATIVA AS ID FROM FECEC_UNIDAD_ADMINISTRATIVA WHERE DESCRIPCION = ?";
}
