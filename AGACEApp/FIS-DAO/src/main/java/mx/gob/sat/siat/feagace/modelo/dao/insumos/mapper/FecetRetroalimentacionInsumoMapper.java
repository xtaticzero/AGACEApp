/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetRetroalimentacionInsumoMapper implements
        ParameterizedRowMapper<FecetRetroalimentacionInsumo> {

    /**
     * Este atributo corresponde al nombre de la columna ID_RETROALIMENTACION en
     * la tabla FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_ID_RETROALIMENTACION_INSUMO = "ID_RETROALIMENTACION_INSUMO";

    /**
     * Este atributo corresponde al nombre de la columna ID_INSUMO en la tabla
     * FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_ID_INSUMO = "ID_INSUMO";

    /**
     * Este atributo corresponde al nombre de la columna MOTIVO_PROGRAMADOR en
     * la tabla FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_MOTIVO_ACIACE = "MOTIVO_ACIACE";

    /**
     * Este atributo corresponde al nombre de la columna MOTIVO_SUBADMINISTRADOR
     * en la tabla FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_MOTIVO_SUBADMINISTRADOR = "MOTIVO_SUBADMINISTRADOR";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna RFC_RETROALIMENTACION
     * en la tabla FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_RFC_RETROALIMENTACION = "RFC_RETROALIMENTACION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_RETROALIMENTACION
     * en la tabla FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_FECHA_RETROALIMENTACION = "FECHA_RETROALIMENTACION";

    /**
     * Este atributo corresponde al nombre de la columna ESTATUS en la tabla
     * FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_ESTATUS = "ESTATUS";

    /**
     * Este atributo corresponde al nombre de la columna RFC_RECHAZO en la tabla
     * FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_RFC_RECHAZO = "RFC_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_RECHAZO en la
     * tabla FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_FECHA_RECHAZO = "FECHA_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION_RECHAZO en
     * la tabla FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_DESCRIPCION_RECHAZO = "DESCRIPCION_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna ID_MOTIVO en la tabla
     * FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_ID_MOTIVO = "ID_MOTIVO";

    /**
     * Este atributo corresponde al nombre de la columna
     * ID_UNIDAD_ADMINISTRATIVA en la tabla FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_ID_UNIDAD_ADMINISTRATIVA = "ID_UNIDAD_ADMINISTRATIVA";

    /**
     * Este atributo corresponde al nombre de la columna ID_SUBPROGRAMA en la
     * tabla FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_ID_SUBPROGRAMA = "ID_SUBPROGRAMA";

    /**
     * Este atributo corresponde al nombre de la columna ID_SECTOR en la tabla
     * FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_ID_SECTOR = "ID_SECTOR";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_INICIO_PERIODO en
     * la tabla FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_FECHA_INICIO_PERIODO = "FECHA_INICIO_PERIODO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_FIN_PERIODO en la
     * tabla FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_FECHA_FIN_PERIODO = "FECHA_FIN_PERIODO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_FIN_PERIODO en la
     * tabla FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_ID_PRIORIDAD = "ID_PRIORIDAD";

    /**
     * Metodo mapRow
     *
     * @param rs
     * @param rowNum
     * @return FeceaMetodo
     * @throws SQLException
     */
    public FecetRetroalimentacionInsumo mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetRetroalimentacionInsumo dto = new FecetRetroalimentacionInsumo();

        dto.setIdRetroalimentacionInsumo(rs.getBigDecimal(COLUMN_ID_RETROALIMENTACION_INSUMO));
        dto.setIdInsumo(rs.getBigDecimal(COLUMN_ID_INSUMO));
        dto.setMotivoAciace(rs.getString(COLUMN_MOTIVO_ACIACE));
        dto.setMotivoSubadministrador(rs.getString(COLUMN_MOTIVO_SUBADMINISTRADOR));
        dto.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        dto.setRfcRetroalimentacion(rs.getString(COLUMN_RFC_RETROALIMENTACION));
        dto.setFechaRetroalimentacion(rs.getTimestamp(COLUMN_FECHA_RETROALIMENTACION));
        dto.setEstatus(rs.getString(COLUMN_ESTATUS));
        dto.setRfcRechazo(rs.getString(COLUMN_RFC_RECHAZO));
        dto.setFechaRechazo(rs.getTimestamp(COLUMN_FECHA_RECHAZO));
        dto.setDescripcionRechazo(rs.getString(COLUMN_DESCRIPCION_RECHAZO));
        dto.setIdMotivo(rs.getBigDecimal(COLUMN_ID_MOTIVO));
        dto.setIdUnidadAdministrativa(rs.getBigDecimal(COLUMN_ID_UNIDAD_ADMINISTRATIVA));
        dto.setIdSubprograma(rs.getBigDecimal(COLUMN_ID_SUBPROGRAMA));
        dto.setIdSector(rs.getBigDecimal(COLUMN_ID_SECTOR));
        dto.setFechaInicioPeriodo(rs.getDate(COLUMN_FECHA_INICIO_PERIODO));
        dto.setFechaFinPeriodo(rs.getDate(COLUMN_FECHA_FIN_PERIODO));
        dto.setIdPrioridad(rs.getBigDecimal(COLUMN_ID_PRIORIDAD));

        return dto;
    }
}
