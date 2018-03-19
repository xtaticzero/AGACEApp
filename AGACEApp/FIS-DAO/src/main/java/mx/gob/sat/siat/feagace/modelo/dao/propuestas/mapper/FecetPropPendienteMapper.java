package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropPendiente;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetPropPendienteMapper implements ParameterizedRowMapper<FecetPropPendiente> {

    /**
     * Este atributo corresponde al nombre de la columna ID_PROP_PENDIENTE en la
     * tabla FECET_PROP_PENDIENTE
     */
    private static final String COLUMN_ID_PROP_PENDIENTE = "ID_PROP_PENDIENTE";
    /**
     * Este atributo corresponde al nombre de la columna ID_PROPUESTA en la
     * tabla FECET_PROP_PENDIENTE
     */
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_PROP_PENDIENTE
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna RFC_CREACION en la
     * tabla FECET_PROP_PENDIENTE
     */
    private static final String COLUMN_RFC_CREACION = "RFC_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna OBSERVACIONES en la
     * tabla FECET_PROP_PENDIENTE
     */
    private static final String COLUMN_OBSERVACIONES = "OBSERVACIONES";

    /**
     * Este atributo corresponde al nombre de la columna ESTATUS en la tabla
     * FECET_PROP_PENDIENTE
     */
    private static final String COLUMN_ESTATUS = "ESTATUS";

    /**
     * Este atributo corresponde al nombre de la columna ID_DOC_PENDIENTE en la
     * tabla FECET_PROP_PENDIENTE
     */
    private static final String COLUM_ID_DOC_PENDIENTE = "ID_DOC_PENDIENTE";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_PROP_PENDIENTE
     */
    private static final String COLUM_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECET_PROP_PENDIENTE
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetPropPendiente mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetPropPendiente dto = new FecetPropPendiente();
        List<FecetPropPendiente> lstDocPendiente = new ArrayList<FecetPropPendiente>();

        dto.setIdPropPendiente(rs.getBigDecimal(COLUMN_ID_PROP_PENDIENTE));
        dto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        dto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        dto.setRfcCreacion(rs.getString(COLUMN_RFC_CREACION));
        dto.setObservaciones(rs.getString(COLUMN_OBSERVACIONES));
        dto.setEstatus(rs.getString(COLUMN_ESTATUS).charAt(0));

        obtenerDocumento(rs, lstDocPendiente);
        dto.setListDocPendiente(lstDocPendiente);

        return dto;
    }

    private void obtenerDocumento(ResultSet rs, List<FecetPropPendiente> lstDocPendiente) throws SQLException {

        do {
            FecetPropPendiente doc = new FecetPropPendiente();
            doc.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
            doc.setIdDocPendiente(rs.getBigDecimal(COLUM_ID_DOC_PENDIENTE));
            doc.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
            doc.setRutaArchivo(rs.getString(COLUM_RUTA_ARCHIVO));

            if (lstDocPendiente != null && !lstDocPendiente.contains(doc)) {
                doc.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath((rs.getString(COLUM_RUTA_ARCHIVO))));
                doc.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
                lstDocPendiente.add(doc);
            }
        } while (rs.next());

    }

}
