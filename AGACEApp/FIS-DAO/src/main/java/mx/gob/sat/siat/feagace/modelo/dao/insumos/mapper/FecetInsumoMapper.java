package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececTipoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;

public class FecetInsumoMapper implements ParameterizedRowMapper<FecetInsumo> {

    /**
     * Este atributo corresponde al nombre de la columna ID_INSUMO en la tabla
     * FECET_INSUMO
     */
    private static final String COLUMN_ID_INSUMO = "ID_INSUMO";

    /**
     * Este atributo corresponde al nombre de la columna ID_CONTRIBUYENTE en la
     * tabla FECET_INSUMO
     */
    private static final String COLUMN_ID_CONTRIBUYENTE = "ID_CONTRIBUYENTE";

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE en la tabla
     * FECET_INSUMO
     */
    private static final String COLUMN_ID_ARACE = "ID_UNIDAD_ADMINISTRATIVA";

    /**
     * Este atributo corresponde al nombre de la columna ID_SUBPROGRAMA en la
     * tabla FECET_INSUMO
     */
    private static final String COLUMN_ID_SUBPROGRAMA = "ID_SUBPROGRAMA";

    /**
     * Este atributo corresponde al nombre de la columna ID_SECTOR en la tabla
     * FECET_INSUMO
     */
    private static final String COLUMN_ID_SECTOR = "ID_SECTOR";

    /**
     * Este atributo corresponde al nombre de la columna ID_REGISTRO en la tabla
     * FECET_INSUMO
     */
    private static final String COLUMN_ID_REGISTRO = "ID_REGISTRO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_INICIO_PERIODO en
     * la tabla FECET_INSUMO
     */
    private static final String COLUMN_FECHA_INICIO_PERIODO = "FECHA_INICIO_PERIODO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_FIN_PERIODO en la
     * tabla FECET_INSUMO
     */
    private static final String COLUMN_FECHA_FIN_PERIODO = "FECHA_FIN_PERIODO";

    /**
     * Este atributo corresponde al nombre de la columna COLUMN_PRIORIDAD en la
     * tabla FECET_INSUMO
     */
    private static final String COLUMN_PRIORIDAD = "PRIORIDAD";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_INSUMO
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_BAJA en la tabla
     * FECET_INSUMO
     */
    private static final String COLUMN_FECHA_BAJA = "FECHA_BAJA";

    /**
     * Este atributo corresponde al nombre de la columna COLUMN_RFC_CREACION en
     * la tabla FECET_INSUMO
     */
    private static final String COLUMN_RFC_CREACION = "RFC_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna
     * COLUMN_RFC_ADMINISTRADOR en la tabla FECET_INSUMO
     */
    private static final String COLUMN_RFC_ADMINISTRADOR = "RFC_ADMINISTRADOR";

    /**
     * Este atributo corresponde al nombre de la columna
     * COLUMN_RFC_SUBAMINISTRADOR en la tabla FECET_INSUMO
     */
    private static final String COLUMN_RFC_SUBAMINISTRADOR = "RFC_SUBADMINISTRADOR";

    /**
     * Este atributo corresponde al nombre de la columna ID_ESTATUS en la tabla
     * FECET_INSU
     */
    private static final String COLUMN_ID_ESTATUS = "ID_ESTATUS";

    private static final String COLUMN_ID_PRIORIDAD = "ID_PRIORIDAD";

    private static final String COLUMN_VALOR_PRIORIDAD = "VALOR_PRIORIDAD";
    
    private static final String COLUMN_DESCRIPCION_PRIORIDAD = "DESCRIPCION_PRIORIDAD";

    private static final String COLUMN_FECHA_INICIO_PLAZO = "FECHA_INICIO_PLAZO";

    private static final String COLUMN_RFC_CONTRIBUYENTE = "RFC";

    private static final String COLUMN_DESCRIPCION_ESTATUS = "DESCRIPCION";

    private static final String COLUMN_DESCRIPCION_SUBPROGRAMA = "DESCRIPCION_SUBPROGRAMA";

    private static final String COLUMN_TIPO_INSUMO = "ID_TIPO_INSUMO";

    private static final String COLUMN_DESCRIPCION_TIPO_INSUMO = "DESCRIPCION_TIPO_INSUMO";
    
    private static final String COLUMN_JUSTIFICACION = "JUSTIFICACION";

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return FecetDocTercero
     */
    public FecetInsumo mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetInsumo dto = new FecetInsumo();
        dto.setIdInsumo(rs.getBigDecimal(COLUMN_ID_INSUMO));
        dto.setIdContribuyente(rs.getBigDecimal(COLUMN_ID_CONTRIBUYENTE));
        dto.setIdArace(rs.getBigDecimal(COLUMN_ID_ARACE));
        dto.setIdUnidadAdministrativa(rs.getBigDecimal(COLUMN_ID_ARACE));
        dto.setIdSubprograma(rs.getBigDecimal(COLUMN_ID_SUBPROGRAMA));
        dto.setIdSector(rs.getBigDecimal(COLUMN_ID_SECTOR));
        dto.setIdRegistro(rs.getString(COLUMN_ID_REGISTRO));
        dto.setFechaInicioPeriodo(rs.getTimestamp(COLUMN_FECHA_INICIO_PERIODO));
        dto.setFechaFinPeriodo(rs.getTimestamp(COLUMN_FECHA_FIN_PERIODO));
        dto.setPrioridad(rs.getBoolean(COLUMN_PRIORIDAD));
        dto.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        dto.setFechaBaja(rs.getTimestamp(COLUMN_FECHA_BAJA));
        dto.setRfcCreacion(rs.getString(COLUMN_RFC_CREACION));
        dto.setRfcAdministrador(rs.getString(COLUMN_RFC_ADMINISTRADOR));
        dto.setRfcSubadministrador(rs.getString(COLUMN_RFC_SUBAMINISTRADOR));
        dto.setIdEstatus(rs.getBigDecimal(COLUMN_ID_ESTATUS));
        dto.setIdPrioridad(rs.getBigDecimal(COLUMN_ID_PRIORIDAD));
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_FECHA_INICIO_PLAZO)) {
            dto.setFechaInicioPlazo(rs.getTimestamp(COLUMN_FECHA_INICIO_PLAZO));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_VALOR_PRIORIDAD)) {
            dto.setValorPrioridad(rs.getString(COLUMN_VALOR_PRIORIDAD));
        }
        
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DESCRIPCION_PRIORIDAD)) {
            dto.setDescripcionPrioridad(rs.getString(COLUMN_DESCRIPCION_PRIORIDAD));            
        }
        
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_RFC_CONTRIBUYENTE)) {
            FecetContribuyente contribuyente = new FecetContribuyente();
            contribuyente.setRfc(rs.getString(COLUMN_RFC_CONTRIBUYENTE));
            dto.setFecetContribuyente(contribuyente);
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DESCRIPCION_ESTATUS)) {
            FececEstatus estatus = new FececEstatus();
            estatus.setDescripcion(rs.getString(COLUMN_DESCRIPCION_ESTATUS));
            dto.setFececEstatus(estatus);
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DESCRIPCION_SUBPROGRAMA)) {
            FececSubprograma subprograma = new FececSubprograma();
            subprograma.setDescripcion(rs.getString(COLUMN_DESCRIPCION_SUBPROGRAMA));
            dto.setFececSubprograma(subprograma);
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_TIPO_INSUMO)) {
            FececTipoInsumo fececTipoInsumo = new FececTipoInsumo();
            fececTipoInsumo.setIdTipoInsumo(rs.getBigDecimal(COLUMN_TIPO_INSUMO));
            dto.setIdTipoInsumo(rs.getBigDecimal(COLUMN_TIPO_INSUMO));            
            if (UtileriasMapperDao.existeColumna(rs, COLUMN_DESCRIPCION_TIPO_INSUMO)) {
                fececTipoInsumo.setDescripcion(rs.getString(COLUMN_DESCRIPCION_TIPO_INSUMO));
            }            
            dto.setFececTipoInsumo(fececTipoInsumo);
        }        
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_JUSTIFICACION)) {
            dto.setJustificacion(rs.getString(COLUMN_JUSTIFICACION));
        }

        return dto;
    }
}
