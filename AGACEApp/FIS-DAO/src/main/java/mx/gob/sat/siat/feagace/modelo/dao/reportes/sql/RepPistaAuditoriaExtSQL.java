/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.reportes.sql;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface RepPistaAuditoriaExtSQL {

    String SQL_SELECT_INTERNO = "(select aso.rfc from fecet_asociado aso,fecec_tipo_asociado taso where aso.id_tipo_asociado = taso.id_tipo_asociado ";
    String SQL_SELECT_REP_INTERNO = "select o.fecha, con.rfc, \n"
            + SQL_SELECT_INTERNO
            + " and aso.fecha_baja is null and aso.id_tipo_asociado = 2 and aso.rfc_contribuyente = ? and rownum < 2)REP_LEGAL,\n"
            + SQL_SELECT_INTERNO
            + " and aso.fecha_baja is null and aso.id_tipo_asociado = 1 and aso.rfc_contribuyente = ? and rownum < 2)APO_LEGAL,\n"
            + SQL_SELECT_INTERNO
            + " and aso.fecha_baja is null and aso.id_tipo_asociado = 4 and aso.rfc_contribuyente = ? and rownum < 2)AGE_ADUANAL,\n"
            + SQL_SELECT_INTERNO
            + " and aso.fecha_baja is null and aso.id_tipo_asociado = 3 and aso.rfc_contribuyente = ? and rownum < 2)APO_LEGAL_REP_LEGAL,\n"
            + " b.ipmaquina, b.nombremaquina, m.nombre modulo, ac.nombre operacion\n"
            + " from \n"
            + " fecet_bitacora b,\n"
            + " FECET_CONTRIBUYENTE con,\n"
            + " fecet_asociado aso,\n"
            + " --fecec_tipo_asociado taso,\n"
            + " fecea_operaciones o, fecec_modulos m, fecec_acciones ac\n"
            + " where\n"
            + " b.id_usuario = con.rfc  --con.id_contribuyente\n"
            + " and aso.rfc_contribuyente = con.rfc \n"
            + " and aso.id_tipo_asociado = taso.id_tipo_asociado \n"
            + " and b.id_operacion = o.id_operacion \n"
            + " and o.id_modulo = m.id_modulo \n"
            + " and o.id_accion = ac.id_accion    ";
}
