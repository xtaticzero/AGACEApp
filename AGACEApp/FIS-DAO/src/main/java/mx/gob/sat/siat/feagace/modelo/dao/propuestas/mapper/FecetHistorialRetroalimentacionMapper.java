package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececMotivoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;

public class FecetHistorialRetroalimentacionMapper implements ParameterizedRowMapper<FecetRetroalimentacion> {

    /**
     * Este atributo corresponde al nombre de la columna ID_RETROALIMENTACION en
     * la tabla FECET_RETROALIMENTACION
     */
    public static final String COLUMN_ID_RETROALIMENTACION = "ID_RETROALIMENTACION";

    /**
     * Este atributo corresponde al nombre de la columna
     * ID_RETROALIMENTACION_ORIGEN en la tabla FECET_RETROALIMENTACION
     */
    public static final String COLUMN_ID_RETROALIMENTACION_ORIGEN = "ID_RETROALIMENTACION_ORIGEN";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la
     * FECEC_MOTIVO
     */
    public static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al nombre de la columna ID_MOTIVO en la
     * FECEC_MOTIVO
     */
    public static final String COLUMN_ID_MOTIVO = "ID_MOTIVO";

    /**
     * Este atributo corresponde al nombre de la columna TOTAL
     */
    public static final String COLUMN_TOTAL = "TOTAL";

    /**
     * Este atributo corresponde al nombre de la columna ID_retroalimentacion en
     * la tabla FECET_retroalimentacion
     */
    private static final String COLUMN_ID_PROPUESTA = "ID_Propuesta";

    /**
     * Este atributo corresponde al nombre de la columna ID_SUBPROGRAMA en la
     * tabla FECET_retroalimentacion
     */
    private static final String COLUMN_ID_SUBPROGRAMA = "ID_SUBPROGRAMA";

    /**
     * Este atributo corresponde al nombre de la columna ID_METODO en la tabla
     * FECET_retroalimentacion
     */
    private static final String COLUMN_ID_METODO = "ID_METODO";

    /**
     * Este atributo corresponde al nombre de la columna ID_REVISION en la tabla
     * FECET_retroalimentacion
     */
    private static final String COLUMN_ID_REVISION = "ID_REVISION";
    private static final String COLUMN_DESCRIPCION_REVISION = "DESCRIPCION_REVISION";

    /**
     * Este atributo corresponde al nombre de la columna
     * ID_TIPO_retroalimentacion en la tabla FECET_retroalimentacion
     */
    private static final String COLUMN_ID_TIPO_PROPUESTA = "ID_TIPO_PROPUESTA";
    private static final String COLUMN_DESCRIPCION_TIPO_PROPUESTA = "DESCRIPCION_TIPO_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna ID_CAUSA_PROGRAMACION
     * en la tabla FECET_retroalimentacion
     */
    private static final String COLUMN_ID_CAUSA_PROGRAMACION = "ID_CAUSA_PROGRAMACION";
    private static final String COLUMN_CAUSA_PROGRAMACION_DESCRIPCION = "DESCRIPCION_CAUSA_PROGRAMACION";

    /**
     * Este atributo corresponde al nombre de la columna ID_SECTOR en la tabla
     * FECET_retroalimentacion
     */
    private static final String COLUMN_ID_SECTOR = "ID_SECTOR";
    private static final String COLUMN_DESCRIPCION_SECTOR = "DESCRIPCION_SECTOR";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_INICIO_PERIODO en
     * la tabla FECET_retroalimentacion
     */
    private static final String COLUMN_FECHA_INICIO_PERIODO = "FECHA_INICIO_PERIODO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_FIN_PERIODO en la
     * tabla FECET_retroalimentacion
     */
    private static final String COLUMN_FECHA_FIN_PERIODO = "FECHA_FIN_PERIODO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_PRESENTACION en
     * la tabla FECET_retroalimentacion
     */
    private static final String COLUMN_FECHA_PRESENTACION = "FECHA_PRESENTACION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_INFORME en la
     * tabla FECET_retroalimentacion
     */
    private static final String COLUMN_FECHA_INFORME = "FECHA_INFORME";

    /**
     * Este atributo corresponde al nombre de la columna PRIORIDAD en la tabla
     * FECET_retroalimentacion
     */
    private static final String COLUMN_PRIORIDAD = "PRIORIDAD";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_retroalimentacion
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";
    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION_RETRO en la
     * tabla FECET_retroalimentacion
     */

    private static final String COLUMN_DESCRIPCION_RETRO = "DESCRIPCION_RETRO";

    /**
     * CAMPOS PARA FECEC_METODO
     */
    private static final String METODO_ID = "METODO_ID";
    private static final String METODO_ABREVIATURA = "METODO_ABREVIATURA";
    private static final String NOMBRE_METODO = "NOMBRE_METODO";

    /**
     * CAMPOS PARA FECEC_SUBPROGRAMA
     */
    private static final String SUBPROGRAMA_ID = "SUBPROGRAMA_ID";
    private static final String SUBPROGRAMA_CLAVE = "SUBPROGRAMA_CLAVE";
    private static final String DESCRIPCION_SUBPROGRAMA = "DESCRIPCION_SUBPROGRAMA";

    /**
     * CAMPOS PARA FECET_TIPO_IMPUESTO
     *
     */
    /**
     * Este metodo hace un mapeo de los datos almacenados en la tabla
     * FECET_retroalimentacion de acuerdo a su columna correspondiente
     *
     * @param rs
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public FecetRetroalimentacion mapRow(ResultSet rs, int i) throws SQLException {
        FecetRetroalimentacion retroalimentacion = new FecetRetroalimentacion();
        FececMotivo motivo = new FececMotivoMapper().mapRow(rs, i);
        retroalimentacion.setIdRetroalimentacion(rs.getBigDecimal(COLUMN_ID_RETROALIMENTACION));
        retroalimentacion.setIdRetroalimentacionOrigen(rs.getBigDecimal(COLUMN_ID_RETROALIMENTACION_ORIGEN));
        if (!retroalimentacion.getIdRetroalimentacion().equals(retroalimentacion.getIdRetroalimentacionOrigen())) {
            retroalimentacion.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
            retroalimentacion.setIdMotivo(rs.getBigDecimal(COLUMN_ID_MOTIVO));
            if (retroalimentacion.getIdMotivo() == null) {
                motivo.setDescripcion(rs.getString(COLUMN_DESCRIPCION_RETRO));
            }
            retroalimentacion.setFececMotivo(motivo);
            retroalimentacion.setNumeroDocRetroalimentacion(rs.getBigDecimal(COLUMN_TOTAL));
            retroalimentacion.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
            retroalimentacion.setIdSubprograma(rs.getBigDecimal(COLUMN_ID_SUBPROGRAMA));
            retroalimentacion.setIdMetodo(rs.getBigDecimal(COLUMN_ID_METODO));
            retroalimentacion.setIdRevision(rs.getBigDecimal(COLUMN_ID_REVISION));
            retroalimentacion.setIdTipoPropuesta(rs.getBigDecimal(COLUMN_ID_TIPO_PROPUESTA));
            retroalimentacion.setIdCausaProgramacion(rs.getBigDecimal(COLUMN_ID_CAUSA_PROGRAMACION));
            retroalimentacion.setIdSector(rs.getBigDecimal(COLUMN_ID_SECTOR));
            retroalimentacion.setFechaInicioPeriodo(rs.getDate(COLUMN_FECHA_INICIO_PERIODO));
            retroalimentacion.setFechaFinPeriodo(rs.getDate(COLUMN_FECHA_FIN_PERIODO));
            retroalimentacion.setFechaPresentacion(rs.getDate(COLUMN_FECHA_PRESENTACION));
            retroalimentacion.setFechaInforme(rs.getDate(COLUMN_FECHA_INFORME));
            retroalimentacion.setPrioridadSugerida(rs.getString(COLUMN_PRIORIDAD));
            retroalimentacion.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
            retroalimentacion.setFeceaMetodo(obtenerMetodo(rs));
            retroalimentacion.setFececSubprograma(obtenersSubprograma(rs));
            retroalimentacion.setFececArace(new FececArace());
            retroalimentacion.setFececRevision(obtenerRevision(rs));
            retroalimentacion.setFececTipoPropuesta(obtenerTipoPropuesta(rs));
            retroalimentacion.setFececCausaProgramacion(obtenerCausaProgramacion(rs));
            retroalimentacion.setFececSector(obtenerSector(rs));
        }

        return retroalimentacion;
    }

    private FececTipoPropuesta obtenerTipoPropuesta(ResultSet rs) throws SQLException {
        FececTipoPropuesta tipoPropuesta = new FececTipoPropuesta();
        tipoPropuesta.setIdTipoPropuesta(rs.getBigDecimal(COLUMN_ID_TIPO_PROPUESTA));
        tipoPropuesta.setDescripcion(rs.getString(COLUMN_DESCRIPCION_TIPO_PROPUESTA));
        return tipoPropuesta;
    }

    private FececMetodo obtenerMetodo(ResultSet rs) throws SQLException {

        FececMetodo metodo = new FececMetodo();

        metodo.setIdMetodo(rs.getBigDecimal(METODO_ID));
        metodo.setAbreviatura(rs.getString(METODO_ABREVIATURA));
        metodo.setNombre(rs.getString(NOMBRE_METODO));

        return metodo;

    }

    private FececSubprograma obtenersSubprograma(ResultSet rs) throws SQLException {

        FececSubprograma subPrograma = new FececSubprograma();

        subPrograma.setIdSubprograma(rs.getBigDecimal(SUBPROGRAMA_ID));
        subPrograma.setClave(rs.getString(SUBPROGRAMA_CLAVE));
        subPrograma.setDescripcion(rs.getString(DESCRIPCION_SUBPROGRAMA));

        return subPrograma;

    }

    private FececRevision obtenerRevision(ResultSet rs) throws SQLException {

        FececRevision revision = new FececRevision();
        revision.setIdRevision(rs.getBigDecimal(COLUMN_ID_REVISION));
        revision.setDescripcion(rs.getString(COLUMN_DESCRIPCION_REVISION));

        return revision;

    }

    private FececCausaProgramacion obtenerCausaProgramacion(ResultSet rs) throws SQLException {
        FececCausaProgramacion causaProgramacion = new FececCausaProgramacion();

        causaProgramacion.setIdCausaProgramacion(rs.getBigDecimal(COLUMN_ID_CAUSA_PROGRAMACION));
        causaProgramacion.setDescripcion(rs.getString(COLUMN_CAUSA_PROGRAMACION_DESCRIPCION));

        return causaProgramacion;
    }

    private FececSector obtenerSector(ResultSet rs) throws SQLException {
        FececSector fececSector = new FececSector();
        fececSector.setIdSector(rs.getBigDecimal(COLUMN_ID_SECTOR));
        fececSector.setDescripcion(rs.getString(COLUMN_DESCRIPCION_SECTOR));

        return fececSector;
    }

}
