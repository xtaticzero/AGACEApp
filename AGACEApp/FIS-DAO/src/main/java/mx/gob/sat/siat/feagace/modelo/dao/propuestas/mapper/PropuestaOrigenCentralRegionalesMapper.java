package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaOrigenCentralRegDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;

public class PropuestaOrigenCentralRegionalesMapper implements ParameterizedRowMapper<PropuestaOrigenCentralRegDTO> {

    public static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";
    public static final String COLUMN_FOLIO = "FOLIO";
    public static final String COLUMN_RFC = "RFC";
    public static final String COLUMN_PRIORIDAD = "PRIORIDAD";
    public static final String COLUMN_ABREVIATURA = "ABREVIATURA";
    public static final String COLUMN_CLAVE = "CLAVE";
    public static final String COLUMN_NOMBRE = "NOMBRE";
    public static final String COLUMN_ID_UNIDAD_ADMINISTRATIVA = "ID_UNIDAD_ADMINISTRATIVA";
    public static final String COLUMN_PRESUNTIVA = "PRESUNTIVA";
    public static final String COLUMN_DESCRIPCION = "DESCRIPCION";
    public static final String COLUMN_DESCRIPCION_TIPO = "DESCRIPCION_TIPO";
    public static final String COLUMN_ID_TIPO_PROPUESTA = "ID_TIPO_PROPUESTA";
    public static final String COLUMN_FECHA_INICIO_PERIODO = "FECHA_INICIO_PERIODO";
    public static final String COLUMN_FECHA_FIN_PERIODO = "FECHA_FIN_PERIODO";
    public static final String COLUMN_FECHA_PRESENTACION = "FECHA_PRESENTACION";
    public static final String COLUMN_FECHA_INFORME = "FECHA_INFORME";
    public static final String COLUMN_DESCRIPCION_SECTOR = "DESCRIPCION_SECTOR";
    public static final String COLUMN_ID_SECTOR = "ID_SECTOR";
    public static final String COLUMN_ID_ARACE = "ID_ARACE";

    @Override
    public PropuestaOrigenCentralRegDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        PropuestaOrigenCentralRegDTO centralRegDTO = new PropuestaOrigenCentralRegDTO();

        FecetPropuesta fecetPropuesta = new FecetPropuesta();
        fecetPropuesta.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        fecetPropuesta.setIdRegistro(rs.getString(COLUMN_FOLIO));
        fecetPropuesta.setPrioridadSugerida(rs.getString(COLUMN_PRIORIDAD));
        fecetPropuesta.setPresuntiva(rs.getBigDecimal(COLUMN_PRESUNTIVA));
        fecetPropuesta.setIdSector(rs.getBigDecimal(COLUMN_ID_SECTOR));
        fecetPropuesta.setIdArace(rs.getBigDecimal(COLUMN_ID_ARACE));
        fecetPropuesta.setFechaInicioPeriodo(rs.getDate(COLUMN_FECHA_INICIO_PERIODO));
        fecetPropuesta.setFechaFinPeriodo(rs.getDate(COLUMN_FECHA_FIN_PERIODO));
        fecetPropuesta.setFechaPresentacionRegional(rs.getDate(COLUMN_FECHA_PRESENTACION));
        fecetPropuesta.setFechaInformeRegional(rs.getDate(COLUMN_FECHA_INFORME));
        fecetPropuesta.setTipoComite(tipoComite(fecetPropuesta));

        FececSector fececSector = new FececSector();
        fececSector.setDescripcion(rs.getString(COLUMN_DESCRIPCION_SECTOR));
        fececSector.setIdSector(rs.getBigDecimal(COLUMN_ID_SECTOR));

        FecetContribuyente contribuyente = new FecetContribuyente();
        contribuyente.setRfc(rs.getString(COLUMN_RFC));

        FececMetodo metodo = new FececMetodo();
        metodo.setAbreviatura(rs.getString(COLUMN_ABREVIATURA));

        FececSubprograma subprograma = new FececSubprograma();
        subprograma.setClave(rs.getString(COLUMN_CLAVE));

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DESCRIPCION)) {
            subprograma.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        }

        FececTipoPropuesta tipoPropuesta = new FececTipoPropuesta();
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DESCRIPCION_TIPO)) {
            tipoPropuesta.setDescripcion(rs.getString(COLUMN_DESCRIPCION_TIPO));
            tipoPropuesta.setIdTipoPropuesta(rs.getBigDecimal(COLUMN_ID_TIPO_PROPUESTA));
        }

        centralRegDTO.setPropuesta(fecetPropuesta);
        centralRegDTO.setContribuyente(contribuyente);
        centralRegDTO.setMetodo(metodo);
        centralRegDTO.setSubprograma(subprograma);
        centralRegDTO.setTipoPropuesta(tipoPropuesta);
        centralRegDTO.setSector(fececSector);
        return centralRegDTO;
    }

    private TipoFechasComiteEnum tipoComite(FecetPropuesta propuesta) {
        if (propuesta.getFechaInformeRegional() != null) {
            return TipoFechasComiteEnum.FECHA_INFORME_COMITE_REGIONAL;
        }
        if (propuesta.getFechaPresentacionRegional() != null) {
            return TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE_REGIONAL;
        }

        return null;
    }

}
