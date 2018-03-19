package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FeceaMetodoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececAraceMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececCausaProgramacionMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececRevisionMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSectorMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSubprogramaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececTipoPropuestaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FecetContribuyenteMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

public class FecetPropuestaConRelacionesMapper extends FecetPropuestaMapper {

    /**
     * Metodo mapRow Hace un mapeo de los datos en el contador
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public FecetPropuesta mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetPropuesta propuesta = super.mapRow(rs, rowNum);

        if (propuesta.getIdSubprograma() != null) {
            try {
                FececSubprograma subprograma = new FececSubprogramaMapper().mapRow(rs, rowNum);
                subprograma.setDescripcion(rs.getString("DESCRIPCION_SUBPROGRAMA"));
                propuesta.setFececSubprograma(subprograma);
            } catch (Exception e) {
                propuesta.setFececSubprograma(null);
            }
        }

        if (propuesta.getIdContribuyente() != null) {
            try {
                FecetContribuyente contribuyente = new FecetContribuyenteMapper().mapRow(rs, rowNum);
                propuesta.setFecetContribuyente(contribuyente);
            } catch (Exception e) {
                propuesta.setFecetContribuyente(null);
            }
        }

        if (propuesta.getIdEstatus() != null) {
            try {
                FececEstatus estatus = new FececEstatusMapper().mapRow(rs, rowNum);
                estatus.setDescripcion(rs.getString("DESCRIPCION_ESTATUS"));
                propuesta.setFececEstatus(estatus);
            } catch (Exception e) {
                propuesta.setFececEstatus(null);
            }

        }

        if (propuesta.getIdMetodo() != null) {
            try {
                FececMetodo feceaMetodo = new FeceaMetodoMapper().mapRow(rs, rowNum);
                feceaMetodo.setNombre(rs.getString("NOMBRE_METODO"));
                propuesta.setFeceaMetodo(feceaMetodo);
            } catch (Exception e) {
                propuesta.setFeceaMetodo(null);
            }

        }

        if (propuesta.getIdSector() != null) {
            try {
                FececSector fececSector = new FececSectorMapper().mapRow(rs, rowNum);
                fececSector.setDescripcion(rs.getString("DESCRIPCION_SECTOR"));
                propuesta.setFececSector(fececSector);
            } catch (Exception e) {
                propuesta.setFececSector(null);
            }
        }

        if (propuesta.getIdArace() != null) {
            try {
                FececArace fececArace = new FececAraceMapper().mapRow(rs, rowNum);
                fececArace.setNombre(rs.getString("NOMBRE_ARACE"));
                propuesta.setFececArace(fececArace);
            } catch (Exception e) {
                propuesta.setFececArace(null);
            }
        }

        equals1(propuesta, rs, rowNum);
        propuesta.setTipoComite(obtenerTipoComite(propuesta));
        return propuesta;
    }

    private void equals1(FecetPropuesta propuesta, ResultSet rs, int rowNum) {
        if (propuesta.getIdTipoPropuesta() != null) {
            try {
                FececTipoPropuesta fececTipoPropuesta = new FececTipoPropuestaMapper().mapRow(rs, rowNum);
                fececTipoPropuesta.setDescripcion(rs.getString("DESCRIPCION_TIPO_PROPUESTA"));
                propuesta.setFececTipoPropuesta(fececTipoPropuesta);
            } catch (Exception e) {
                propuesta.setFececTipoPropuesta(null);
            }
        }

        if (propuesta.getIdCausaProgramacion() != null) {
            try {
                FececCausaProgramacion fececCausaProgramacion = new FececCausaProgramacionMapper().mapRow(rs, rowNum);
                fececCausaProgramacion.setDescripcion(rs.getString("DESCRIPCION_CAUSA_PROGRAMACION"));
                propuesta.setFececCausaProgramacion(fececCausaProgramacion);
            } catch (Exception e) {
                propuesta.setFececCausaProgramacion(null);
            }
        }

        if (propuesta.getIdRevision() != null) {
            try {
                FececRevision fececRevision = new FececRevisionMapper().mapRow(rs, rowNum);
                fececRevision.setDescripcion(rs.getString("DESCRIPCION_REVISION"));
                propuesta.setFececRevision(fececRevision);
            } catch (Exception e) {
                propuesta.setFececRevision(null);
            }
        }
        try {
            if (rs.getString("PRESUNTIVA") != null) {
                propuesta.setPresuntiva(rs.getBigDecimal("PRESUNTIVA"));
            }
        } catch (Exception e) {
            propuesta.setPresuntiva(BigDecimal.ZERO);
        }
    }

    private TipoFechasComiteEnum obtenerTipoComite(FecetPropuesta propuesta) {
        TipoFechasComiteEnum resultado = null;
        boolean regional = false;
        if (propuesta.getUnidadAdministrativa() != null) {
            FececUnidadAdministrativa unidad = propuesta.getUnidadAdministrativa();
            regional = !Constantes.ACOECE.equals(unidad.getIdUnidadAdministrativa()) && !Constantes.ACAOCE.equals(unidad.getIdUnidadAdministrativa());
        }
        if (propuesta.getFechaInforme() != null) {
            resultado = regional ? TipoFechasComiteEnum.FECHA_INFORME_COMITE_REGIONAL : TipoFechasComiteEnum.FECHA_INFORME_COMITE;
        } else if (propuesta.getFechaPresentacion() != null) {
            resultado = regional ? TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE_REGIONAL : TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE;
        }

        return resultado;
    }
}
