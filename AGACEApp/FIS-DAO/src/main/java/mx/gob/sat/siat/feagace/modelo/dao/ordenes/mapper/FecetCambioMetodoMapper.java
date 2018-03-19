/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodo;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetCambioMetodoMapper implements ParameterizedRowMapper<FecetCambioMetodo> {

    public static final String COLUMN_ID_CAMBIO_METODO = "ID_CAMBIO_METODO";
    public static final String COLUMN_ID_ORDEN_ORIGEN = "ID_ORDEN_ORIGEN";
    public static final String COLUMN_ID_ORDEN_NUEVA = "ID_ORDEN_NUEVA";
    public static final String COLUMN_ID_METODO_NUEVO = "ID_METODO_NUEVO";
    public static final String COLUMN_ID_OFICIO = "ID_OFICIO";
    public static final String COLUMN_ID_ESTATUS = "ID_ESTATUS";
    public static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";
    public static final String COLUMN_ID_PROPUESTA_NUEVA = "ID_PROPUESTA_NUEVA";
    public static final String COLUMN_NOMBRE_METODO_NUEVO = "NOMBRE_METODO_NUEVO";

    /**
     * Metodo rowMap Hace un un mapeo y un set de los datos de la tabla
     * FECET_CAMBIO_METODO
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetCambioMetodo mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetCambioMetodo dto = new FecetCambioMetodo();
        dto.setIdCambioMetodo(rs.getBigDecimal(COLUMN_ID_CAMBIO_METODO));
        dto.setIdOrdenOrigen(rs.getBigDecimal(COLUMN_ID_ORDEN_ORIGEN));
        dto.setIdOrdenNueva(rs.getBigDecimal(COLUMN_ID_ORDEN_NUEVA));
        dto.setIdMetodoNuevo(rs.getBigDecimal(COLUMN_ID_METODO_NUEVO));
        dto.setIdOficio(rs.getBigDecimal(COLUMN_ID_OFICIO));
        dto.setIdEstatus(rs.getBigDecimal(COLUMN_ID_ESTATUS));
        dto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        dto.setIdPropuestaNueva(rs.getBigDecimal(COLUMN_ID_PROPUESTA_NUEVA));
        dto.setNombreNuevoMetodo(rs.getString(COLUMN_NOMBRE_METODO_NUEVO));
        return dto;
    }
}
