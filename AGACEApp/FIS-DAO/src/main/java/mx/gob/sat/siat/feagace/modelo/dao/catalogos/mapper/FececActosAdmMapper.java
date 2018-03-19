/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;

public class FececActosAdmMapper implements ParameterizedRowMapper<FececActosAdm> {

    private static final String COLUMN_ID_ACTO = "ID_ACTO_ADMIN";
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";
    private static final String COLUMN_ID_METODO = "ID_METODO";
    private static final String COLUMN_ID_TIPO_OFICIO = "ID_TIPO_OFICIO";
    private static final String COLUMN_ID_UNIDAD_ADMINISTRATIVA = "ID_UNIDAD_ADMINISTRATIVA";
    private static final String COLUMN_FECHA_INICIO = "FECHA_INICIO";
    private static final String COLUMN_FECHA_FIN = "FECHA_FIN";
    private static final String COLUMN_ACTIVO = "BLN_ACTIVO";
    private static final String COLUMN_ID_NYV = "ID_NYV";
    private static final String COLUMN_ID_DOCUMENTO = "ID_DOCUMENTO";
    private static final String COLUMN_PROCESO_ORIGEN = "PROCESO_ORIGEN";
    private static final String COLUMN_ESTATUS_DOCUMENTO = "ESTATUS_DOCUMENTO";
    private static final String COLUMN_NOMBRE = "NOMBRE";
    private static final String COLUMN_PREFIJO_REFERENCIA = "PREFIJO_REFERENCIA";

    /**
     * Metodo mapRow Hacde un mapeo de los datos en la tabla FECET_PROMOCION
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececActosAdm mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececActosAdm dto = new FececActosAdm();

        dto.setIdActoAdmin(rs.getLong(COLUMN_ID_ACTO));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setIdMetodo(rs.getLong(COLUMN_ID_METODO));
        dto.setIdTipoOficio(rs.getLong(COLUMN_ID_TIPO_OFICIO));
        dto.setIdUnidadAdministrativa(rs.getLong(COLUMN_ID_UNIDAD_ADMINISTRATIVA));
        dto.setFechaInicio(rs.getDate(COLUMN_FECHA_INICIO));
        dto.setFechaFin(rs.getDate(COLUMN_FECHA_FIN));
        dto.setBlnActivo(rs.getBoolean(COLUMN_ACTIVO));
        dto.setIdNyv(rs.getLong(COLUMN_ID_NYV));
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_DOCUMENTO)) {
            dto.setIdDocumento(rs.getLong(COLUMN_ID_DOCUMENTO));
        } else {
            dto.setIdDocumento(0L);
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_PROCESO_ORIGEN)) {
            dto.setProcesoOrigen(rs.getString(COLUMN_PROCESO_ORIGEN));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ESTATUS_DOCUMENTO)) {
            dto.setEstatusDocumento(rs.getString(COLUMN_ESTATUS_DOCUMENTO));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_NOMBRE)) {
            dto.setNombre(rs.getString(COLUMN_NOMBRE));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_PREFIJO_REFERENCIA)) {
            dto.setPrefijoReferencia(rs.getString(COLUMN_PREFIJO_REFERENCIA));
        }

        return dto;
    }

}
