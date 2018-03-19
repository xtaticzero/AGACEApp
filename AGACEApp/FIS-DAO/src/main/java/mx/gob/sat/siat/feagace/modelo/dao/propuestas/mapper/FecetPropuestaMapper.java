/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;

public class FecetPropuestaMapper implements ParameterizedRowMapper<FecetPropuesta> {

    /**
     * Este atributo corresponde al nombre de la columna ID_PROPUESTA en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna ID_CONTRIBUYENTE en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_ID_CONTRIBUYENTE = "ID_CONTRIBUYENTE";

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_ARACE = "ID_ARACE";

    /**
     * Este atributo corresponde al nombre de la columna ID_SUBPROGRAMA en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_ID_SUBPROGRAMA = "ID_SUBPROGRAMA";

    /**
     * Este atributo corresponde al nombre de la columna ID_METODO en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_METODO = "ID_METODO";

    /**
     * Este atributo corresponde al nombre de la columna ID_REVISION en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_REVISION = "ID_REVISION";

    /**
     * Este atributo corresponde al nombre de la columna ID_TIPO_PROPUESTA en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_ID_TIPO_PROPUESTA = "ID_TIPO_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna ID_CAUSA_PROGRAMACION
     * en la tabla FECET_PROPUESTA
     */
    private static final String COLUMN_ID_CAUSA_PROGRAMACION = "ID_CAUSA_PROGRAMACION";

    /**
     * Este atributo corresponde al nombre de la columna ID_SECTOR en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_SECTOR = "ID_SECTOR";

    /**
     * Este atributo corresponde al nombre de la columna ID_REGISTRO en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_REGISTRO = "ID_REGISTRO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_INICIO_PERIODO en
     * la tabla FECET_PROPUESTA
     */
    private static final String COLUMN_FECHA_INICIO_PERIODO = "FECHA_INICIO_PERIODO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_FIN_PERIODO en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_FECHA_FIN_PERIODO = "FECHA_FIN_PERIODO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_PRESENTACION en
     * la tabla FECET_PROPUESTA
     */
    private static final String COLUMN_FECHA_PRESENTACION = "FECHA_PRESENTACION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_INFORME en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_FECHA_INFORME = "FECHA_INFORME";

    /**
     * Este atributo corresponde al nombre de la columna PRIORIDAD en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_PRIORIDAD = "PRIORIDAD";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_BAJA en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_FECHA_BAJA = "FECHA_BAJA";

    /**
     * Este atributo corresponde al nombre de la columna RFC_CREACION en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_RFC_CREACION = "RFC_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna RFC_ADMINISTRADOR en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_RFC_ADMINISTRADOR = "RFC_ADMINISTRADOR";

    /**
     * Este atributo corresponde al nombre de la columna RFC_SUBADMINISTRADOR en
     * la tabla FECET_PROPUESTA
     */
    private static final String COLUMN_RFC_SUBADMINISTRADOR = "RFC_SUBADMINISTRADOR";

    /**
     * Este atributo corresponde al nombre de la columna ID_ESTATUS en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_ESTATUS = "ID_ESTATUS";

    /**
     * Este atributo corresponde al nombre de la columna RFC_FIRMANTE en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_RFC_FIRMANTE = "RFC_FIRMANTE";

    /**
     * Este atributo corresponde al nombre de la columna RFC_AUDITOR en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_RFC_AUDITOR = "RFC_AUDITOR";
    /**
     * Este atributo corresponde al nombre de la columna COMITE_REGIONAL en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_INFORME_PRESENTACION = "INFORME_PRESENTACION";

    /**
     * Este atributo corresponde al nombre de la columna ID_INSUMO en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_INSUMO = "ID_INSUMO";

    /**
     * Este atributo corresponde al nombre de la columna ID_INSUMO en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ID_REGISTRO_INSUMO = "ID_REGISTRO_INSUMO";

    private static final String COLUMN_DESCRIPCION_METODO = "DESCRIPCION_METODO";

    private static final String COLUMN_DESCRIPCION_SUBPROGRAMA = "DESCRIPCION_SUBPROGRAMA";

    private static final String COLUMN_DESCRIPCION_TIPO_PROPUESTA = "DESCRIPCION_TIPO_PROPUESTA";

    private static final String COLUMN_DESCRIPCION_REVISION = "DESCRIPCION_REVISION";

    private static final String COLUMN_DESCRIPCION_CAUSA_PROGRAMACION = "DESCRIPCION_CAUSA_PROGRAMACION";

    private static final String COLUMN_DESCRIPCION_SECTOR = "SECTOR_DESCRIPCION";

    /**
     * Este metodo hace un mapeo de los datos almacenados en la tabla
     * FECET_PROPUESTA de acuerdo a su columna correspondiente
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetPropuesta mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetPropuesta dto = new FecetPropuesta();

        dto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        dto.setIdContribuyente(rs.getBigDecimal(COLUMN_ID_CONTRIBUYENTE));
        dto.setIdArace(rs.getBigDecimal(COLUMN_ID_ARACE));
        dto.setIdSubprograma(rs.getBigDecimal(COLUMN_ID_SUBPROGRAMA));
        dto.setIdMetodo(rs.getBigDecimal(COLUMN_ID_METODO));
        dto.setIdRevision(rs.getBigDecimal(COLUMN_ID_REVISION));
        dto.setIdTipoPropuesta(rs.getBigDecimal(COLUMN_ID_TIPO_PROPUESTA));
        dto.setIdCausaProgramacion(rs.getBigDecimal(COLUMN_ID_CAUSA_PROGRAMACION));
        dto.setIdSector(rs.getBigDecimal(COLUMN_ID_SECTOR));
        dto.setIdRegistro(rs.getString(COLUMN_ID_REGISTRO));
        dto.setFechaInicioPeriodo(rs.getDate(COLUMN_FECHA_INICIO_PERIODO));
        dto.setFechaFinPeriodo(rs.getDate(COLUMN_FECHA_FIN_PERIODO));
        dto.setFechaPresentacion(rs.getDate(COLUMN_FECHA_PRESENTACION));
        dto.setFechaInforme(rs.getDate(COLUMN_FECHA_INFORME));
        dto.setPrioridadSugerida(rs.getString(COLUMN_PRIORIDAD));
        dto.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        dto.setFechaBaja(rs.getDate(COLUMN_FECHA_BAJA));
        dto.setRfcCreacion(rs.getString(COLUMN_RFC_CREACION));
        dto.setRfcAdministrador(rs.getString(COLUMN_RFC_ADMINISTRADOR));
        dto.setRfcSubadministrador(rs.getString(COLUMN_RFC_SUBADMINISTRADOR));
        dto.setIdEstatus(rs.getBigDecimal(COLUMN_ID_ESTATUS));
        dto.setRfcFirmante(rs.getString(COLUMN_RFC_FIRMANTE));
        dto.setRfcAuditor(rs.getString(COLUMN_RFC_AUDITOR));
        dto.setInformePresentacion(rs.getString(COLUMN_INFORME_PRESENTACION));
        dto.setIdInsumo(rs.getBigDecimal(COLUMN_ID_INSUMO));

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_REGISTRO_INSUMO)) {
            dto.setIdRegistroInsumo(rs.getString(COLUMN_ID_REGISTRO_INSUMO));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DESCRIPCION_METODO)) {
            FececMetodo metodo = new FececMetodo();
            metodo.setNombre(rs.getString(COLUMN_DESCRIPCION_METODO));
            dto.setFeceaMetodo(metodo);
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DESCRIPCION_SUBPROGRAMA)) {
            FececSubprograma subprograma = new FececSubprograma();
            subprograma.setDescripcion(rs.getString(COLUMN_DESCRIPCION_SUBPROGRAMA));
            dto.setFececSubprograma(subprograma);
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DESCRIPCION_TIPO_PROPUESTA)) {
            FececTipoPropuesta tipoPropuesta = new FececTipoPropuesta();
            tipoPropuesta.setDescripcion(rs.getString(COLUMN_DESCRIPCION_TIPO_PROPUESTA));
            dto.setFececTipoPropuesta(tipoPropuesta);
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DESCRIPCION_REVISION)) {
            FececRevision revision = new FececRevision();
            revision.setDescripcion(rs.getString(COLUMN_DESCRIPCION_REVISION));
            dto.setFececRevision(revision);
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DESCRIPCION_CAUSA_PROGRAMACION)) {
            FececCausaProgramacion causaProgrmacion = new FececCausaProgramacion();
            causaProgrmacion.setDescripcion(rs.getString(COLUMN_DESCRIPCION_CAUSA_PROGRAMACION));
            dto.setFececCausaProgramacion(causaProgrmacion);
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DESCRIPCION_SECTOR)) {
            FececSector sector = new FececSector();
            sector.setDescripcion(rs.getString(COLUMN_DESCRIPCION_SECTOR));
            dto.setFececSector(sector);
        }

        return dto;
    }
}
