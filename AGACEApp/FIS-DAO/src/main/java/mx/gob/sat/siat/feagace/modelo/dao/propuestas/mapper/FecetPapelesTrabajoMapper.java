package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;

public class FecetPapelesTrabajoMapper implements ParameterizedRowMapper<PapelesTrabajo> {

    /**
     * Este atributo corresponde al nombre de la columna ID_PAPELES_TRABAJO en
     * la tabla FECET_PAPELES_TRABAJO
     */
    private static final String COLUMN_ID_PAPELES_TRABAJO = "ID_PAPELES_TRABAJO";

    /**
     * Este atributo corresponde al nombre de la columna ID_PROPUESTA en la
     * tabla FECET_PAPELES_TRABAJO
     */
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna ID_ORDEN en la tabla
     * FECET_PAPELES_TRABAJO
     */
    private static final String COLUMN_ID_ORDEN = "ID_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna ID_OFICIO en la tabla
     * FECET_PAPELES_TRABAJO
     */
    private static final String COLUMN_ID_OFICIO = "ID_OFICIO";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_PAPELES_TRABAJO
     */
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_PAPELES_TRABAJO
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna BLN_ACTIVO en la tabla
     * FECET_PAPELES_TRABAJO
     */
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_FIN en la tabla
     * FECET_PAPELES_TRABAJO
     */
    private static final String COLUMN_FECHA_FIN = "FECHA_FIN";

    @Override
    public PapelesTrabajo mapRow(ResultSet rs, int rowNum) throws SQLException {

        PapelesTrabajo dto = new PapelesTrabajo();

        dto.setIdPapelesTrabajo(rs.getBigDecimal(COLUMN_ID_PAPELES_TRABAJO));
        dto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        dto.setIdOrden(rs.getBigDecimal(COLUMN_ID_ORDEN));
        dto.setIdOficio(rs.getBigDecimal(COLUMN_ID_OFICIO));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        dto.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        dto.setBlnActivo(rs.getBigDecimal(COLUMN_BLN_ACTIVO));
        dto.setFechaFin(rs.getDate(COLUMN_FECHA_FIN));

        return dto;
    }
}
