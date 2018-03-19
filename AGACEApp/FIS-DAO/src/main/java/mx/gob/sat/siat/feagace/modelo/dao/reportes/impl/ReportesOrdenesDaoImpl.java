package mx.gob.sat.siat.feagace.modelo.dao.reportes.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.ReportesOrdenesDao;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.mapper.GraficaValoresMapper;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.mapper.ReporteOrdenesMapper;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.sql.ReporteOrdenesSQL;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.GraficaValoresDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteOrdenesDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportesVO;

import org.springframework.stereotype.Repository;

@Repository("reportesOrdenesDao")
public class ReportesOrdenesDaoImpl extends BaseJDBCDao<ReporteOrdenesDTO> implements ReportesOrdenesDao {

    private static final String SQL_PARAM_IDARACE = " AND propuesta.ID_ARACE=";

    @SuppressWarnings("compatibility:8215115415954815239")
    private static final long serialVersionUID = 1L;

    /**
     * Returns all rows from the FECET_ORDEN,
     * FECET_PROPUESTA,FECET_CONTRIBUYENTE, FECEC_ESTATUS, FECEC_METODO,
     * FECEC_SECTOR, FECEC_SUBPROGRAMA, FECEC_UNIDAD_ADMINSTRATIVA,
     * FECEC_FIRMANTE, FECEC_AUDITOR, FECET_DETALLE_CONTRIBUYENTE tables that
     * match the criteria.
     */
    @Override
    public List<ReporteOrdenesDTO> findReporteOrdenesGerencial(ReportesVO vo, String condicion) {
        StringBuilder query = new StringBuilder();
        query.append(ReporteOrdenesSQL.REPORTE_GERENCIAL);
        query.append(condicion);

        if (vo.getAraceId() != null && vo.getAraceId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(SQL_PARAM_IDARACE).append(vo.getAraceId().toString());
        }
        if (vo.getRfc() != null && !vo.getRfc().trim().isEmpty()) {
            query.append(" AND contribuyente.RFC ='").append(vo.getRfc().toUpperCase()).append("'");
        }
        if (vo.getMetodoId() != null && vo.getMetodoId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND orden.ID_METODO=").append(vo.getMetodoId().toString());
        }
        if (vo.getEstatusId() != null && vo.getEstatusId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND orden.ID_ESTATUS=").append(vo.getEstatusId().toString());
        }
        if (vo.getAuditorId() != null && vo.getAuditorId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND orden.ID_AUDITOR=").append(vo.getAuditorId().toString());
        }
        if (vo.getFirmanteId() != null && vo.getFirmanteId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND orden.ID_FIRMANTE=").append(vo.getFirmanteId().toString());
        }
        logger.error(query.toString());
        return getJdbcTemplateBase().query(query.toString(), new ReporteOrdenesMapper(), vo.getFechaInicioPeriodo(), vo.getFechaFinPeriodo());
    }

    @Override
    public List<GraficaValoresDTO> graficaOrdenesCero(ReportesVO vo, String condicion) {
        StringBuilder query = new StringBuilder();
        query.append(ReporteOrdenesSQL.REPORTE_EJECTUTIVO_CERO);
        query.append(condicion);
        if (vo.getAraceId() != null && vo.getAraceId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(SQL_PARAM_IDARACE).append(vo.getAraceId().toString());
        }
        return getJdbcTemplateBase().query(query.toString(), new GraficaValoresMapper(), vo.getFechaInicioPeriodo(), vo.getFechaFinPeriodo());
    }

    @Override
    public List<GraficaValoresDTO> graficaOrdenesUno(ReportesVO vo, String condicion) {
        StringBuilder query = new StringBuilder();
        query.append(ReporteOrdenesSQL.REPORTE_EJECTUTIVO_UNO);
        query.append(condicion);
        if (vo.getAraceId() != null && vo.getAraceId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(SQL_PARAM_IDARACE).append(vo.getAraceId().toString());
        }
        query.append(" GROUP BY to_char(orden.FECHA_CREACION,'yyyy-mm')\n");
        query.append(" ORDER BY to_char(orden.FECHA_CREACION,'yyyy-mm')) mm");
        return getJdbcTemplateBase().query(query.toString(), new GraficaValoresMapper(), vo.getFechaInicioPeriodo(), vo.getFechaFinPeriodo());
    }

    @Override
    public List<GraficaValoresDTO> graficaOrdenesDos(ReportesVO vo, String condicion) {
        List<GraficaValoresDTO> lista = null;
        StringBuilder query = new StringBuilder();
        boolean tagQuery = true;

        if (vo.isMostrarMetodo() && vo.isMostrarEntidad()) {
            query.append(ReporteOrdenesSQL.SELECT_EJECTUTIVO_METODO_ENTIDAD_FECHA);
            query.append(ReporteOrdenesSQL.CUERPO_REPORTE_EJECUTIVO_FECHA);
            tagQuery = false;
        }
        if (vo.isMostrarMetodo() && vo.isMostrarEstatus()) {
            query.append(ReporteOrdenesSQL.SELECT_EJECTUTIVO_METODO_ESTATUS_FECHA);
            query.append(ReporteOrdenesSQL.CUERPO_REPORTE_EJECUTIVO_FECHA);
            tagQuery = false;
        }
        if (vo.isMostrarMetodo() && vo.isMostrarUnidad()) {
            query.append(ReporteOrdenesSQL.SELECT_EJECTUTIVO_METODO_UNIDAD_ADMINISTRATIVA_FECHA);
            query.append(ReporteOrdenesSQL.CUERPO_REPORTE_EJECUTIVO_FECHA);
            tagQuery = false;
        }
        if (vo.isMostrarEntidad() && vo.isMostrarEstatus()) {
            query.append(ReporteOrdenesSQL.SELECT_EJECTUTIVO_ENTIDAD_ESTATUS_FECHA);
            query.append(ReporteOrdenesSQL.CUERPO_REPORTE_EJECUTIVO_FECHA);
            tagQuery = false;
        }
        if (vo.isMostrarEntidad() && vo.isMostrarUnidad()) {
            query.append(ReporteOrdenesSQL.SELECT_EJECTUTIVO_ENTIDAD_UNIDAD_ADMINISTRATIVA_FECHA);
            query.append(ReporteOrdenesSQL.CUERPO_REPORTE_EJECUTIVO_FECHA);
            tagQuery = false;
        }
        if (vo.isMostrarEstatus() && vo.isMostrarUnidad()) {
            query.append(ReporteOrdenesSQL.SELECT_EJECTUTIVO_ESTATUS_UNIDAD_ADMINISTRATIVA_FECHA);
            query.append(ReporteOrdenesSQL.CUERPO_REPORTE_EJECUTIVO_FECHA);
            tagQuery = false;
        }

        if (tagQuery) {
            query.append(ReporteOrdenesSQL.REPORTE_EJECTUTIVO_DOS);
        }
        query.append(condicion);

        if (vo.getAraceId() != null && vo.getAraceId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(SQL_PARAM_IDARACE).append(vo.getAraceId().toString());
        }
        if (vo.getMetodoId() != null && vo.getMetodoId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND orden.ID_METODO=").append(vo.getMetodoId().toString());
        }
        if (vo.getEstatusId() != null && vo.getEstatusId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND orden.ID_ESTATUS=").append(vo.getEstatusId().toString());
        }
        if (vo.getEntidad() != null && !vo.getEntidad().trim().isEmpty()) {
            query.append(" AND contribuyente.ENTIDAD='").append(vo.getEntidad()).append("'");
        }

        if (vo.isMostrarMetodo() && vo.isMostrarEntidad()) {
            query.append(" GROUP BY metodo.NOMBRE, contribuyente.ENTIDAD ORDER BY metodo.NOMBRE, contribuyente.ENTIDAD ASC\n");
        }
        if (vo.isMostrarMetodo() && vo.isMostrarEstatus()) {
            query.append(" GROUP BY metodo.NOMBRE, estatus.DESCRIPCION ORDER BY metodo.NOMBRE, estatus.DESCRIPCION ASC\n");
        }
        if (vo.isMostrarMetodo() && vo.isMostrarUnidad()) {
            query.append(" GROUP BY metodo.NOMBRE, propuesta.ID_ARACE ORDER BY metodo.NOMBRE, propuesta.ID_ARACE ASC\n");
        }
        if (vo.isMostrarEntidad() && vo.isMostrarEstatus()) {
            query.append(" GROUP BY estatus.DESCRIPCION,contribuyente.ENTIDAD ORDER BY estatus.DESCRIPCION,contribuyente.ENTIDAD ASC\n");
        }
        if (vo.isMostrarEntidad() && vo.isMostrarUnidad()) {
            query.append(" GROUP BY contribuyente.ENTIDAD,propuesta.ID_ARACE ORDER BY contribuyente.ENTIDAD,propuesta.ID_ARACE ASC\n");
        }
        if (vo.isMostrarEstatus() && vo.isMostrarUnidad()) {
            query.append("  GROUP BY estatus.DESCRIPCION, propuesta.ID_ARACE ORDER BY estatus.DESCRIPCION, propuesta.ID_ARACE ASC\n");
        }

        if (tagQuery) {
            query.append(" GROUP BY metodo.NOMBRE, estatus.DESCRIPCION,contribuyente.ENTIDAD, propuesta.ID_ARACE\n");
            query.append(" ORDER BY metodo.NOMBRE, estatus.DESCRIPCION,contribuyente.ENTIDAD ASC");
        }

        logger.error(query.toString());
        lista = getJdbcTemplateBase().query(query.toString(), new GraficaValoresMapper(), vo.getFechaInicioPeriodo(), vo.getFechaFinPeriodo());
        return lista;
    }

    @Override
    public List<GraficaValoresDTO> graficaOrdenesTres(ReportesVO vo, String condicion) {
        List<GraficaValoresDTO> lista = null;
        StringBuilder query = new StringBuilder();
        boolean tagQuery = true;

        if (vo.isMostrarMetodo() && vo.isMostrarEntidad()) {
            query.append(ReporteOrdenesSQL.SELECT_EJECTUTIVO_METODO_ENTIDAD_MES);
            query.append(ReporteOrdenesSQL.CUERPO_REPORTE_EJECUTIVO_MES);
            tagQuery = false;
        }
        if (vo.isMostrarMetodo() && vo.isMostrarEstatus()) {
            query.append(ReporteOrdenesSQL.SELECT_EJECTUTIVO_METODO_ESTATUS_MES);
            query.append(ReporteOrdenesSQL.CUERPO_REPORTE_EJECUTIVO_MES);
            tagQuery = false;
        }
        if (vo.isMostrarMetodo() && vo.isMostrarUnidad()) {
            query.append(ReporteOrdenesSQL.SELECT_EJECTUTIVO_METODO_UNIDAD_ADMINISTRATIVA_MES);
            query.append(ReporteOrdenesSQL.CUERPO_REPORTE_EJECUTIVO_MES);
            tagQuery = false;
        }
        if (vo.isMostrarEntidad() && vo.isMostrarEstatus()) {
            query.append(ReporteOrdenesSQL.SELECT_EJECTUTIVO_ENTIDAD_ESTATUS_MES);
            query.append(ReporteOrdenesSQL.CUERPO_REPORTE_EJECUTIVO_MES);
            tagQuery = false;
        }
        if (vo.isMostrarEntidad() && vo.isMostrarUnidad()) {
            query.append(ReporteOrdenesSQL.SELECT_EJECTUTIVO_ENTIDAD_UNIDAD_ADMINISTRATIVA_MES);
            query.append(ReporteOrdenesSQL.CUERPO_REPORTE_EJECUTIVO_MES);
            tagQuery = false;
        }
        if (vo.isMostrarEstatus() && vo.isMostrarUnidad()) {
            query.append(ReporteOrdenesSQL.SELECT_EJECTUTIVO_ESTATUS_UNIDAD_ADMINISTRATIVA_MES);
            query.append(ReporteOrdenesSQL.CUERPO_REPORTE_EJECUTIVO_MES);
            tagQuery = false;
        }

        if (tagQuery) {
            query.append(ReporteOrdenesSQL.REPORTE_EJECTUTIVO_TRES);
        }
        query.append(condicion);
        if (vo.getAraceId() != null && vo.getAraceId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(SQL_PARAM_IDARACE).append(vo.getAraceId().toString());
        }
        if (vo.getMetodoId() != null && vo.getMetodoId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND orden.ID_METODO=").append(vo.getMetodoId().toString());
        }
        if (vo.getEstatusId() != null && vo.getEstatusId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND orden.ID_ESTATUS=").append(vo.getEstatusId().toString());
        }
        if (vo.getEntidad() != null && !vo.getEntidad().trim().isEmpty()) {
            query.append(" AND contribuyente.ENTIDAD='").append(vo.getEntidad()).append("'");
        }

        if (vo.isMostrarMetodo() && vo.isMostrarEntidad()) {
            query.append(" GROUP BY metodo.NOMBRE,contribuyente.ENTIDAD, to_char(orden.FECHA_CREACION,'yyyy-mm') ORDER BY to_char(orden.FECHA_CREACION,'yyyy-mm') ) mm \n");
        }
        if (vo.isMostrarMetodo() && vo.isMostrarEstatus()) {
            query.append(" GROUP BY metodo.NOMBRE,estatus.DESCRIPCION, to_char(orden.FECHA_CREACION,'yyyy-mm') Order BY to_char(orden.FECHA_CREACION,'yyyy-mm') ) mm \n");
        }
        if (vo.isMostrarMetodo() && vo.isMostrarUnidad()) {
            query.append(" GROUP BY metodo.NOMBRE,propuesta.ID_ARACE, to_char(orden.FECHA_CREACION,'yyyy-mm') ORDER BY to_char(orden.FECHA_CREACION,'yyyy-mm') ) mm \n");
        }
        if (vo.isMostrarEntidad() && vo.isMostrarEstatus()) {
            query.append(" GROUP BY estatus.DESCRIPCION,contribuyente.ENTIDAD, to_char(orden.FECHA_CREACION,'yyyy-mm') ORDER BY to_char(orden.FECHA_CREACION,'yyyy-mm') ) mm \n");
        }
        if (vo.isMostrarEntidad() && vo.isMostrarUnidad()) {
            query.append(" GROUP BY contribuyente.ENTIDAD,propuesta.ID_ARACE, to_char(orden.FECHA_CREACION,'yyyy-mm') ORDER BY to_char(orden.FECHA_CREACION,'yyyy-mm') ) mm \n");
        }
        if (vo.isMostrarEstatus() && vo.isMostrarUnidad()) {
            query.append(" GROUP BY estatus.DESCRIPCION,propuesta.ID_ARACE, to_char(orden.FECHA_CREACION,'yyyy-mm') ORDER BY to_char(orden.FECHA_CREACION,'yyyy-mm') ) mm \n");
        }

        if (tagQuery) {
            query.append(" GROUP BY metodo.NOMBRE,estatus.DESCRIPCION,contribuyente.ENTIDAD,propuesta.ID_ARACE, to_char(orden.FECHA_CREACION,'yyyy-mm')\n");
            query.append(" ORDER BY to_char(orden.FECHA_CREACION,'yyyy-mm') ) mm \n");
        }

        logger.error(query.toString());

        lista = getJdbcTemplateBase().query(query.toString(), new GraficaValoresMapper(), vo.getFechaInicioPeriodo(), vo.getFechaFinPeriodo());
        return lista;
    }
}
