/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import static mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao.existeColumna;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FecetContribuyenteMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

public class FecetPropuestaResumenMapper extends FecetPropuestaMapper implements ParameterizedRowMapper<FecetPropuesta> {

    private static final String COLUMN_DESCRIPCION_SECTOR = "DESCRIPCION_SECTOR";
    private static final String COLUMN_CLAVE = "CLAVE";
    private static final String COLUMN_ID_UNIDAD_ADMINISTRATIVA = "ID_UNIDAD_ADMINISTRATIVA";
    private static final String COLUMN_NOMBREA_UA = "NOMBREA_UA";
    private static final String COLUMN_DESCRIPCION_UA = "DESCRIPCION_UA";
    private static final String COLUMN_ABREVIATURA = "ABREVIATURA";
    private static final String COLUMN_PRESUNTIVA = "PRESUNTIVA";
    private static final String COLUMN_DESC_REVISION = "DESC_REVISION";
    private static final String COLUMN_DESCRIPCION_CAUSA_PROGRAMACION = "DESCRIPCION_CAUSA_PROGRAMACION";
    private static final String COLUMN_DESCRIPCION_TIPO_PROPUESTA = "DESCRIPCION_TIPO_PROPUESTA";
    private static final String COLUMN_DESCRIPCION_SUBPROGRAMA = "DESCRIPCION_SUBPROGRAMA";

    public FecetPropuesta mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetPropuesta propuesta = super.mapRow(rs, rowNum);
        propuesta.getFececSubprograma().setIdSubprograma(propuesta.getIdSubprograma());
        propuesta.getFeceaMetodo().setIdMetodo(propuesta.getIdMetodo());
        propuesta.getFececTipoPropuesta().setIdTipoPropuesta(propuesta.getIdTipoPropuesta());
        propuesta.getFececCausaProgramacion().setIdCausaProgramacion(propuesta.getIdCausaProgramacion());

        propuesta.setFecetContribuyente(new FecetContribuyenteMapper().mapRow(rs, rowNum));

        if (existeColumna(rs, COLUMN_ABREVIATURA)) {
            propuesta.getFeceaMetodo().setAbreviatura(rs.getString(COLUMN_ABREVIATURA));
        }
        if (existeColumna(rs, COLUMN_CLAVE)) {
            propuesta.getFececSubprograma().setClave(rs.getString(COLUMN_CLAVE));
        }
        if (existeColumna(rs, COLUMN_DESCRIPCION_SUBPROGRAMA)) {
            propuesta.getFececSubprograma().setDescripcion(rs.getString(COLUMN_DESCRIPCION_SUBPROGRAMA));
        }
        if (existeColumna(rs, COLUMN_DESCRIPCION_SECTOR)) {
            FececSector sector = new FececSector();
            sector.setIdSector(propuesta.getIdSector());
            sector.setDescripcion(rs.getString(COLUMN_DESCRIPCION_SECTOR));
            propuesta.setFececSector(sector);
        }
        if (existeColumna(rs, COLUMN_ID_UNIDAD_ADMINISTRATIVA)) {
            FececUnidadAdministrativa unidadAdministrativa = new FececUnidadAdministrativa();
            unidadAdministrativa.setIdUnidadAdministrativa(rs.getBigDecimal(COLUMN_ID_UNIDAD_ADMINISTRATIVA));
            unidadAdministrativa.setNombre(rs.getString(COLUMN_NOMBREA_UA));
            unidadAdministrativa.setDescripcion(rs.getString(COLUMN_DESCRIPCION_UA));
            propuesta.setUnidadAdministrativa(unidadAdministrativa);
        }
        if (existeColumna(rs, COLUMN_PRESUNTIVA)) {
            propuesta.setPresuntiva(rs.getBigDecimal(COLUMN_PRESUNTIVA));
        }

        if (propuesta.getIdRevision() != null && existeColumna(rs, COLUMN_DESC_REVISION)) {
            FececRevision revision = new FececRevision();
            revision.setIdRevision(propuesta.getIdRevision());
            revision.setDescripcion(rs.getString(COLUMN_DESC_REVISION));
            propuesta.setFececRevision(revision);
        }

        if (existeColumna(rs, COLUMN_DESCRIPCION_CAUSA_PROGRAMACION)) {
            propuesta.getFececCausaProgramacion().setDescripcion(rs.getString(COLUMN_DESCRIPCION_CAUSA_PROGRAMACION));
        }

        if (existeColumna(rs, COLUMN_DESCRIPCION_TIPO_PROPUESTA)) {
            propuesta.getFececTipoPropuesta().setDescripcion(rs.getString(COLUMN_DESCRIPCION_TIPO_PROPUESTA));
        }

        propuesta.setTipoComite(obtenerTipoComite(propuesta));
        return propuesta;
    }

    private TipoFechasComiteEnum obtenerTipoComite(FecetPropuesta propuesta) {
        TipoFechasComiteEnum resultado = null;
        boolean regional = true;
        if (propuesta.getIdArace() != null) {
            regional = !Constantes.ACOECE.equals(propuesta.getIdArace()) && !Constantes.ACAOCE.equals(propuesta.getIdArace());
        }
        if (propuesta.getFechaInforme() != null) {
            resultado = regional ? TipoFechasComiteEnum.FECHA_INFORME_COMITE_REGIONAL : TipoFechasComiteEnum.FECHA_INFORME_COMITE;
        } else if (propuesta.getFechaPresentacion() != null) {
            resultado = regional ? TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE_REGIONAL : TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE;
        }

        return resultado;
    }
}
