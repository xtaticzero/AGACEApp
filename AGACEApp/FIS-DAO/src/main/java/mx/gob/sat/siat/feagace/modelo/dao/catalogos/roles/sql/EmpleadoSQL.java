/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.sql;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface EmpleadoSQL {

    String COLUM_CORREO = "CORREO";

    String COLUM_ARACE = "ID_ARACE";

    String COLUM_NOMBRE = "NOMBRE";

    String COLUM_ORDEN = "ID_ORDEN";

    String GET_CORREO_EMPLEADO_X_TIPO_IDARACE = "SELECT \n"
            + "FECEC_EMPLEADO.RFC,\n"
            + "FECEC_EMPLEADO.CORREO,\n"
            + "FECEC_TIPO_EMPLEADO.ID_TIPO_EMPLEADO,\n"
            + "FECEC_TIPO_EMPLEADO.DESCRIPCION\n"
            + "FROM FECEC_EMPLEADO\n"
            + "INNER JOIN FECET_DETALLE_EMPLEADO ON FECET_DETALLE_EMPLEADO.ID_EMPLEADO = FECEC_EMPLEADO.ID_EMPLEADO\n"
            + "INNER JOIN FECEC_TIPO_EMPLEADO ON FECET_DETALLE_EMPLEADO.ID_TIPO_EMPLEADO = FECEC_TIPO_EMPLEADO.ID_TIPO_EMPLEADO\n"
            + "WHERE FECET_DETALLE_EMPLEADO.ID_CENTRAL = ?\n"
            + "AND FECEC_TIPO_EMPLEADO.ID_TIPO_EMPLEADO = ?";

    String GET_IDARACE_XIDORDEN = "SELECT ID_ARACE FROM FECET_ORDEN INNER JOIN FECET_PROPUESTA ON FECET_ORDEN.ID_PROPUESTA = FECET_PROPUESTA.ID_PROPUESTA\n"
            + " WHERE ID_ORDEN = ?";

    String GET_AUDITOR = "SELECT FECEC_EMPLEADO.NOMBRE FROM FECEC_EMPLEADO INNER JOIN FECET_ORDEN ON FECEC_EMPLEADO.ID_EMPLEADO = FECET_ORDEN.ID_AUDITOR\n "
            + " WHERE FECET_ORDEN.ID_AUDITOR = ? AND FECET_ORDEN.ID_ORDEN = ?";

    String GET_CORREO_FIRMANTE = "SELECT FECEC_EMPLEADO.CORREO FROM FECEC_EMPLEADO INNER JOIN FECET_ORDEN ON FECEC_EMPLEADO.ID_EMPLEADO = FECET_ORDEN.ID_FIRMANTE\n"
            + "  WHERE FECET_ORDEN.ID_FIRMANTE = ? AND FECET_ORDEN.ID_ORDEN = ?";

    String GET_CORREO_AUDITOR = "SELECT FECEC_EMPLEADO.CORREO FROM FECEC_EMPLEADO INNER JOIN FECET_ORDEN ON FECEC_EMPLEADO.ID_EMPLEADO = FECET_ORDEN.ID_AUDITOR\n"
            + "    WHERE FECET_ORDEN.ID_AUDITOR = ? AND FECET_ORDEN.ID_ORDEN = ?";

    String GET_IDOFICIO_XIDOFICIO = "SELECT FECET_ORDEN.ID_ORDEN FROM FECET_ORDEN INNER JOIN FECET_OFICIO ON FECET_ORDEN.ID_ORDEN = FECET_OFICIO.ID_ORDEN\n"
            + "    WHERE FECET_OFICIO.ID_OFICIO = ? AND ROWNUM = 1";
}
