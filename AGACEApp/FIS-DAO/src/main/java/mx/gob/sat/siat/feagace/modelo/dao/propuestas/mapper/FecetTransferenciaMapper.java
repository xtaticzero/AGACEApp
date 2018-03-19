package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetTransferenciaMapper implements ParameterizedRowMapper<FecetTransferencia> {

    /**
     * Este atributo corresponde al nombre de la columna ID_TRANSFERENCIA en la
     * tabla FECET_TRANSFERENCIA
     */
    static final String COLUMN_ID_TRANSFERENCIA = "ID_TRANSFERENCIA";

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE_ORIGEN en la
     * tabla FECET_TRANSFERENCIA
     */
    static final String COLUMN_ID_ARACE_ORIGEN = "ID_ARACE_ORIGEN";

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE_DESTINO en la
     * tabla FECET_TRANSFERENCIA
     */
    static final String COLUMN_ID_ARACE_DESTINO = "ID_ARACE_DESTINO";

    /**
     * Este atributo corresponde al nombre de la columna ID_PROPUESTA en la
     * tabla FECET_TRANSFERENCIA
     */
    static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_TRASPASO en la
     * tabla FECET_TRANSFERENCIA
     */
    static final String COLUMN_FECHA_TRASPASO = "FECHA_TRASPASO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_TRASPASO en la
     * tabla FECET_TRANSFERENCIA
     */
    static final String COLUMN_ID_EMPLEADO = "ID_EMPLEADO";

    /**
     * Este atributo corresponde al nombre de la columna RFC_EMPLEADO en la
     * tabla FECET_TRANSFERENCIA
     */
    static final String COLUMN_RFC = "RFC_EMPLEADO";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE_ARCHIVO en la
     * tabla FECET_TRANSFERENCIA
     */
    static final String COLUMN_NOMBRE_ARCHIVO = "NOMBRE_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_TRANSFERENCIA
     */
    static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna OBSERVACIONES en la
     * tabla FECET_TRANSFERENCIA
     */
    static final String COLUMN_OBSERVACIONES = "OBSERVACIONES";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_RECHAZO en la
     * tabla FECET_TRANSFERENCIA
     */
    static final String COLUMN_FECHA_RECHAZO = "FECHA_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna BLN_ESTATUS en la tabla
     * FECET_TRANSFERENCIA
     */
    static final String COLUMN_BLN_ESTATUS = "BLN_ESTATUS";

    /**
     * Este atributo corresponde al nombre de la columna ESTATUS en la tabla
     * FECET_TRANSFERENCIA
     */
    static final String COLUMN_ESTATUS = "ID_ESTATUS";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_TRANSFERENCIA
     */
    static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna ID_DOC_TRANSFERENCIA en
     * la tabla FECET_DOC_TRANSFERENCIA
     */
    static final String COLUMN_ID_DOC_TRANSFERENCIA = "ID_DOC_TRANSFERENCIA";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECET_TRANSFERENCIA
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetTransferencia mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetTransferencia dto = new FecetTransferencia();

        dto.setIdTransferencia(rs.getBigDecimal(COLUMN_ID_TRANSFERENCIA));
        dto.setIdAraceOrigen(rs.getBigDecimal(COLUMN_ID_ARACE_ORIGEN));
        dto.setIdAraceDestino(rs.getBigDecimal(COLUMN_ID_ARACE_DESTINO));
        dto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        dto.setFechaTraspaso(rs.getDate(COLUMN_FECHA_TRASPASO));
        dto.setRfc(rs.getString(COLUMN_RFC));
        dto.setObservaciones(rs.getString(COLUMN_OBSERVACIONES));
        dto.setFechaRechazo(rs.getDate(COLUMN_FECHA_RECHAZO));
        dto.setEstatus(rs.getBigDecimal(COLUMN_ESTATUS));
        dto.setIdEmpleado(rs.getBigDecimal(COLUMN_ID_EMPLEADO));
        dto.setBlnEstatus(rs.getBigDecimal(COLUMN_BLN_ESTATUS));

        return dto;
    }

    public FecetTransferencia mapRowDoc(ResultSet rs, int rowNum) throws SQLException {

        FecetTransferencia dto = new FecetTransferencia();
        dto.setIdDocTransferencia(rs.getBigDecimal(COLUMN_ID_DOC_TRANSFERENCIA));
        dto.setIdTransferencia(rs.getBigDecimal(COLUMN_ID_TRANSFERENCIA));
        dto.setNombreArchivo(rs.getString(COLUMN_NOMBRE_ARCHIVO));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        dto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        dto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));

        return dto;
    }

}
