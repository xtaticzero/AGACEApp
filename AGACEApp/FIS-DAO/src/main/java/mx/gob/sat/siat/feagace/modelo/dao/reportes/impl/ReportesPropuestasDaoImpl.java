package mx.gob.sat.siat.feagace.modelo.dao.reportes.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.ReportesPropuestasDao;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.mapper.GraficaValoresMapper;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.mapper.ReportePropuestasMapper;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.sql.ReportePropuestasSQL;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.GraficaValoresDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportePropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportesVO;

import org.springframework.stereotype.Repository;

@Repository("reportesPropuestasDao")
public class ReportesPropuestasDaoImpl extends BaseJDBCDao<ReportePropuestasDTO> implements ReportesPropuestasDao {

    private static final long serialVersionUID = -4489690400954074206L;

    private static final String SQL_PARAM_IDARACE = " AND propuesta.ID_ARACE=";

    @Override
    public List<ReportePropuestasDTO> findReportePropuestasGerencial(ReportesVO vo, String condicion) {
        StringBuilder query = new StringBuilder();
        query.append(ReportePropuestasSQL.REPORTE_GERENCIAL);
        query.append(condicion);

        if (vo.getAraceId() != null && vo.getAraceId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(SQL_PARAM_IDARACE).append(vo.getAraceId().toString());
        }
        if (vo.getNumeroPropuesta() != null && !vo.getNumeroPropuesta().trim().isEmpty()) {
            query.append(" AND propuesta.ID_REGISTRO LIKE '").append(vo.getNumeroPropuesta().toUpperCase()).append("%'");
        }
        if (vo.getRfc() != null && !vo.getRfc().trim().isEmpty()) {
            query.append(" AND contribuyente.RFC ='").append(vo.getRfc().toUpperCase()).append("'");
        }
        if (vo.getMetodoId() != null && vo.getMetodoId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND propuesta.ID_METODO=").append(vo.getMetodoId().toString());
        }
        if (vo.getTipoRevisionId() != null && vo.getTipoRevisionId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND propuesta.ID_REVISION=").append(vo.getTipoRevisionId().toString());
        }
        if (vo.getEstatusId() != null && vo.getEstatusId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND propuesta.ID_ESTATUS=").append(vo.getEstatusId().toString());
        }
        if (vo.getSectorId() != null && vo.getSectorId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND propuesta.ID_SECTOR=").append(vo.getSectorId().toString());
        }
        if (vo.getSubprogramaId() != null && vo.getSubprogramaId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND propuesta.ID_SUBPROGRAMA=").append(vo.getSubprogramaId().toString());
        }
        if (vo.getTipoPropuestaId() != null && vo.getTipoPropuestaId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND propuesta.ID_TIPO_PROPUESTA=").append(vo.getTipoPropuestaId().toString());
        }
        if (vo.getCausaProgramacionId() != null && vo.getCausaProgramacionId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND propuesta.ID_CAUSA_PROGRAMACION=").append(vo.getCausaProgramacionId().toString());
        }
        if (vo.getPresuntivaInicio() != null && vo.getPresuntivaFinal() != null) {
            query.append(" AND impuesto.PREPODERANTE BETWEEN ").append(vo.getPresuntivaInicio().toString());
            query.append(" AND ");
            query.append(vo.getPresuntivaFinal().toString());
        }
        logger.error(query.toString());

        return getJdbcTemplateBase().query(query.toString(), new ReportePropuestasMapper(), vo.getFechaInicioPeriodo(), vo.getFechaFinPeriodo());
    }

    @Override
    public List<GraficaValoresDTO> graficaPropuestasCero(ReportesVO vo, String condicion) {
        StringBuilder query = new StringBuilder();
        query.append(ReportePropuestasSQL.REPORTE_EJECTUTIVO_CERO);
        query.append(condicion);
        if (vo.getAraceId() != null && vo.getAraceId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(SQL_PARAM_IDARACE).append(vo.getAraceId().toString());
        }
        return getJdbcTemplateBase().query(query.toString(), new GraficaValoresMapper(), vo.getFechaInicioPeriodo(), vo.getFechaFinPeriodo());
    }

    @Override
    public List<GraficaValoresDTO> graficaPropuestasUno(ReportesVO vo, String condicion) {
        StringBuilder query = new StringBuilder();
        query.append(ReportePropuestasSQL.REPORTE_EJECTUTIVO_UNO);
        query.append(condicion);
        if (vo.getAraceId() != null && vo.getAraceId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(SQL_PARAM_IDARACE).append(vo.getAraceId().toString());
        }
        query.append(" GROUP BY to_char(propuesta.FECHA_CREACION,'yyyy-mm')\n");
        query.append(" ORDER BY to_char(propuesta.FECHA_CREACION,'yyyy-mm')) mm");
        logger.error(query.toString());
        return getJdbcTemplateBase().query(query.toString(), new GraficaValoresMapper(), vo.getFechaInicioPeriodo(), vo.getFechaFinPeriodo());
    }

    @Override
    public List<GraficaValoresDTO> graficaPropuestasDos(ReportesVO vo, String condicion) {
        List<GraficaValoresDTO> lista = null;
        StringBuilder query = new StringBuilder();
        boolean tagQuery = true;
        if (vo.isMostrarMetodo() && vo.isMostrarEntidad()) {
            query.append(ReportePropuestasSQL.SELECT_EJECTUTIVO_METODO_ENTIDAD_FECHA);
            query.append(ReportePropuestasSQL.CUERPO_REPORTE_EJECUTIVO_FECHA);
            tagQuery = false;
        }
        if (vo.isMostrarMetodo() && vo.isMostrarEstatus()) {
            query.append(ReportePropuestasSQL.SELECT_EJECTUTIVO_METODO_ESTATUS_FECHA);
            query.append(ReportePropuestasSQL.CUERPO_REPORTE_EJECUTIVO_FECHA);
            tagQuery = false;
        }
        if (vo.isMostrarEntidad() && vo.isMostrarEstatus()) {
            query.append(ReportePropuestasSQL.SELECT_EJECTUTIVO_ENTIDAD_ESTATUS_FECHA);
            query.append(ReportePropuestasSQL.CUERPO_REPORTE_EJECUTIVO_FECHA);
            tagQuery = false;
        }
        if (tagQuery) {
            query.append(ReportePropuestasSQL.REPORTE_EJECTUTIVO_DOS);
        }

        query.append(condicion);

        if (vo.getAraceId() != null && vo.getAraceId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(SQL_PARAM_IDARACE).append(vo.getAraceId().toString());
        }
        if (vo.getMetodoId() != null && vo.getMetodoId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND propuesta.ID_METODO=").append(vo.getMetodoId().toString());
        }
        if (vo.getEstatusId() != null && vo.getEstatusId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND propuesta.ID_ESTATUS=").append(vo.getEstatusId().toString());
        }
        if (vo.getEntidad() != null && !vo.getEntidad().trim().isEmpty()) {
            query.append(" AND contribuyente.ENTIDAD='").append(vo.getEntidad()).append("'");
        }

        if (vo.isMostrarMetodo() && vo.isMostrarEntidad()) {
            query.append(" GROUP BY metodo.NOMBRE ,contribuyente.ENTIDAD ORDER BY metodo.NOMBRE, contribuyente.ENTIDAD ASC");
        }
        if (vo.isMostrarMetodo() && vo.isMostrarEstatus()) {
            query.append(" GROUP BY metodo.NOMBRE, estatus.DESCRIPCION ORDER BY metodo.NOMBRE, estatus.DESCRIPCION ASC");
        }
        if (vo.isMostrarEntidad() && vo.isMostrarEstatus()) {
            query.append("GROUP BY  estatus.DESCRIPCION,contribuyente.ENTIDAD ORDER BY estatus.DESCRIPCION,contribuyente.ENTIDAD ASC");
        }
        if (tagQuery) {
            query.append(" GROUP BY metodo.NOMBRE, estatus.DESCRIPCION,contribuyente.ENTIDAD\n");
            query.append(" ORDER BY metodo.NOMBRE, estatus.DESCRIPCION,contribuyente.ENTIDAD ASC");
        }
        logger.error(query.toString());
        lista = getJdbcTemplateBase().query(query.toString(), new GraficaValoresMapper(), vo.getFechaInicioPeriodo(), vo.getFechaFinPeriodo());
        return lista;
    }

    @Override
    public List<GraficaValoresDTO> graficaPropuestasTres(ReportesVO vo, String condicion) {
        List<GraficaValoresDTO> lista = null;
        StringBuilder query = new StringBuilder();
        boolean tagQuery = true;
        if (vo.isMostrarMetodo() && vo.isMostrarEntidad()) {
            query.append(ReportePropuestasSQL.SELECT_EJECTUTIVO_METODO_ENTIDAD_MES);
            query.append(ReportePropuestasSQL.CUERPO_REPORTE_EJECUTIVO_MES);
            tagQuery = false;
        }
        if (vo.isMostrarMetodo() && vo.isMostrarEstatus()) {
            query.append(ReportePropuestasSQL.SELECT_EJECTUTIVO_METODO_ESTATUS_MES);
            query.append(ReportePropuestasSQL.CUERPO_REPORTE_EJECUTIVO_MES);
            tagQuery = false;
        }
        if (vo.isMostrarEntidad() && vo.isMostrarEstatus()) {
            query.append(ReportePropuestasSQL.SELECT_EJECTUTIVO_ENTIDAD_ESTATUS_MES);
            query.append(ReportePropuestasSQL.CUERPO_REPORTE_EJECUTIVO_MES);
            tagQuery = false;
        }
        if (tagQuery) {
            query.append(ReportePropuestasSQL.REPORTE_EJECTUTIVO_TRES);
        }
        query.append(condicion);

        if (vo.getAraceId() != null && vo.getAraceId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(SQL_PARAM_IDARACE).append(vo.getAraceId().toString());
        }
        if (vo.getMetodoId() != null && vo.getMetodoId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND propuesta.ID_METODO=").append(vo.getMetodoId().toString());
        }
        if (vo.getEstatusId() != null && vo.getEstatusId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND propuesta.ID_ESTATUS=").append(vo.getEstatusId().toString());
        }
        if (vo.getEntidad() != null && !vo.getEntidad().trim().isEmpty()) {
            query.append(" AND contribuyente.ENTIDAD='").append(vo.getEntidad()).append("'");
        }

        if (vo.isMostrarMetodo() && vo.isMostrarEntidad()) {
            query.append(" GROUP BY metodo.NOMBRE,contribuyente.ENTIDAD, to_char(propuesta.FECHA_CREACION,'yyyy-mm')");
        }
        if (vo.isMostrarMetodo() && vo.isMostrarEstatus()) {
            query.append(" GROUP BY metodo.NOMBRE,estatus.DESCRIPCION, to_char(propuesta.FECHA_CREACION,'yyyy-mm')");
        }
        if (vo.isMostrarEntidad() && vo.isMostrarEstatus()) {
            query.append(" GROUP BY estatus.DESCRIPCION,contribuyente.ENTIDAD, to_char(propuesta.FECHA_CREACION,'yyyy-mm')");
        }
        if (tagQuery) {
            query.append(" GROUP BY metodo.NOMBRE,estatus.DESCRIPCION,contribuyente.ENTIDAD, to_char(propuesta.FECHA_CREACION,'yyyy-mm')\n");
        }
        query.append(" ORDER BY to_char(propuesta.FECHA_CREACION,'yyyy-mm') ) mm");
        logger.error(query.toString());
        lista = getJdbcTemplateBase().query(query.toString(), new GraficaValoresMapper(), vo.getFechaInicioPeriodo(), vo.getFechaFinPeriodo());
        return lista;
    }

}
