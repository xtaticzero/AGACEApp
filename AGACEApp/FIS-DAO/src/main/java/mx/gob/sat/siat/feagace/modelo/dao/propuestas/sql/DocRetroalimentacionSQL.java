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
public interface DocRetroalimentacionSQL {

    String INSERT = "INSERT INTO \n"
            + "FECET_DOC_RETRO_PROPUESTA(ID_DOC_RETRO,\n"
            + "ID_RETROALIMENTACION,\n"
            + "ID_PROPUESTA,\n"
            + "RUTA_ARCHIVO,\n"
            + "FECHA_CREACION,\n"
            + "BLN_ACTIVO) \n"
            + "VALUES (FECEQ_DOC_RETRO_PROPUESTA.NEXTVAL,\n"
            + "?,\n"
            + "?,\n"
            + "?,\n"
            + "SYSDATE,\n"
            + "?\n"
            + ")";
}
