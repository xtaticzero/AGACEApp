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
public interface ContadorPropuestasDaoSql {

    String COLUM_TOTAL = "TOTAL";

    String CONDICION_RFC_CREACION = "{RFC_CREACION}";
    String CONDICION_ID_PROGRAMADOR = "{ID_PROGRAMADOR}";
    String CONDICION_ID_ESTATUS = "{ID_ESTATUS}";
    String CONDICION_ID_ACCION = "{ID_ACCION}";
    String CONDICION_ID_ARACE = "{ID_ARACE}";
    String INNER = "{INNER}";

    String SQL_INNER_ACCION_PROPUESTAS = "INNER JOIN  FECEB_ACCION_PROPUESTA BITACORA_ACCION ON ( P.ID_PROPUESTA = BITACORA_ACCION.ID_PROPUESTA )\n"
            + "INNER JOIN  FECEA_PROPUESTA_ACCION PROPUESTA_ACCION ON ( P.ID_PROPUESTA = PROPUESTA_ACCION.ID_PROPUESTA )\n"
            + "INNER JOIN  FECEC_ACCIONES_FUNCIONARIO ACCIONES_FUNCIONARIO ON ( PROPUESTA_ACCION.ID_ACCION = ACCIONES_FUNCIONARIO.ID_ACCION )";

    String SQL_CONDICION_ID_ACCION = " AND PROPUESTA_ACCION.ID_ACCION IN ( {ID_ACCION} )";

    String SQL_COUNT_PROP_X_RFCCREACION_ESTATUS = "SELECT COUNT(P.ID_PROPUESTA) TOTAL \n"
            + "FROM FECET_PROPUESTA P\n"
            + " {INNER} \n"
            + "WHERE 1=1\n"
            + "AND P.ID_ARACE IN ( {ID_ARACE} )"
            + "AND P.RFC_CREACION IN ({RFC_CREACION})\n"
            + "AND P.ID_ESTATUS IN ({ID_ESTATUS})";

    String SQL_COUNT_PROP_X_ID_PROGRAMADOR_ESTATUS = "SELECT COUNT(P.ID_PROPUESTA) TOTAL \n"
            + "FROM FECET_PROPUESTA P\n"
            + " {INNER} \n"
            + "WHERE 1=1\n"
            + "AND P.ID_ARACE IN ( {ID_ARACE} )"
            + "AND P.ID_PROGRAMADOR IN ({ID_PROGRAMADOR})\n"
            + "AND P.ID_ESTATUS IN ({ID_ESTATUS})";
}
