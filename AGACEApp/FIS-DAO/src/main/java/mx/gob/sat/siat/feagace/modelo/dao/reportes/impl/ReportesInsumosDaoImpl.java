package mx.gob.sat.siat.feagace.modelo.dao.reportes.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.helper.InsumoDaoHelper;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.ReportesInsumosDao;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.mapper.GraficaValoresMapper;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.mapper.ReporteInsumosDTOMapper;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.sql.ReporteInsumosSQL;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.GraficaValoresDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteInsumosDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportesVO;

import org.springframework.stereotype.Repository;

@Repository("reportesInsumosDao")
public class ReportesInsumosDaoImpl extends BaseJDBCDao<ReporteInsumosDTO> implements ReportesInsumosDao {

    @SuppressWarnings("compatibility:-2710721646157705824")
    private static final long serialVersionUID = -8131258389592651929L;

    private static final String AND_ESTATUS = " AND insumo.ID_ESTATUS=";
    private static final String AND_ENTIDAD = " AND contribuyente.ENTIDAD LIKE '%";

    /**
     * Returns all rows from the FECET_INSUMO,FECET_CONTRIBUYENTE,
     * FECET_DETALLE_CONTRIBUYENTE, FECEC_ESTATUS, FECET_RECHAZO_INSUMO,
     * FECEC_SECTOR, FECEC_SUBPROGRAMA tables that match the criteria.
     */
    @Override
    public List<ReporteInsumosDTO> findReporteInsumosGerencial(ReportesVO vo) {
        StringBuilder query = new StringBuilder();
        query.append(ReporteInsumosSQL.REPORTE_INSUMOS_GERENCIAL);
        if (vo.getLstRfcCreacion() != null && !vo.getLstRfcCreacion().isEmpty()) {
            query.append(" AND insumo.RFC_CREACION IN ({RFC_ACIACE}) ".replace(ReporteInsumosSQL.PARAM_RFC_ACIACE, InsumoDaoHelper.getSQLRFCCreacion(vo.getLstRfcCreacion())));
        }
        if (vo.getRfc() != null && !vo.getRfc().trim().isEmpty()) {
            query.append(" AND contribuyente.RFC='").append(vo.getRfc().toUpperCase()).append("'");
        }
        if (vo.getEntidad() != null && !vo.getEntidad().trim().isEmpty()) {
            query.append(AND_ENTIDAD).append(vo.getEntidad()).append("%'");
        }
        if (vo.getEstatusId() != null && vo.getEstatusId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(AND_ESTATUS).append(vo.getEstatusId().toString());
        }
        if (vo.getSubprogramaId() != null && vo.getSubprogramaId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND insumo.ID_SUBPROGRAMA=").append(vo.getSubprogramaId().toString());
        }
        if (vo.getSectorId() != null && vo.getSectorId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND insumo.ID_SECTOR=").append(vo.getSectorId().toString());
        }
        if (vo.getActividad() != null && !vo.getActividad().trim().isEmpty()) {
            query.append(" AND contribuyente.ACTIVIDAD_PREPONDERANTE LIKE '%").append(vo.getActividad()).append("%'");
        }
        return getJdbcTemplateBase().query(query.toString(), new ReporteInsumosDTOMapper(), vo.getFechaInicioPeriodo(), vo.getFechaFinPeriodo());
    }

    @Override
    public List<GraficaValoresDTO> graficaInsumosCero(ReportesVO vo) {
        StringBuilder query = new StringBuilder();
        query.append(ReporteInsumosSQL.REPORTE_INSUMOS_EJECTUTIVO_CERO.replace(ReporteInsumosSQL.PARAM_RFC_ACIACE, InsumoDaoHelper.getSQLRFCCreacion(vo.getLstRfcCreacion())));
        return getJdbcTemplateBase().query(query.toString(), new GraficaValoresMapper(), vo.getFechaInicioPeriodo(), vo.getFechaFinPeriodo());
    }

    @Override
    public List<GraficaValoresDTO> graficaInsumosUno(ReportesVO vo) {
        StringBuilder query = new StringBuilder();
        query.append(ReporteInsumosSQL.REPORTE_INSUMOS_EJECTUTIVO_UNO.replace(ReporteInsumosSQL.PARAM_RFC_ACIACE, InsumoDaoHelper.getSQLRFCCreacion(vo.getLstRfcCreacion())));
        return getJdbcTemplateBase().query(query.toString(), new GraficaValoresMapper(), vo.getFechaInicioPeriodo(), vo.getFechaFinPeriodo());
    }

    @Override
    public List<GraficaValoresDTO> graficaInsumosDos(ReportesVO vo) {
        List<GraficaValoresDTO> lista = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("estatus.DESCRIPCION NOMBRE_ESTATUS, contribuyente.ENTIDAD, COUNT(insumo.ID_REGISTRO) AS TOTAL\n");
        query.append(ReporteInsumosSQL.REPORTE_INSUMOS_EJECTUTIVO_DOS.replace(ReporteInsumosSQL.PARAM_RFC_ACIACE, InsumoDaoHelper.getSQLRFCCreacion(vo.getLstRfcCreacion())));

        if (vo.getEstatusId() != null && vo.getEstatusId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(AND_ESTATUS).append(vo.getEstatusId().toString()).append("\n");
        }
        if (vo.getEntidad() != null && !vo.getEntidad().trim().isEmpty()) {
            query.append(AND_ENTIDAD).append(vo.getEntidad()).append("%'\n");
        }

        query.append(" GROUP BY estatus.DESCRIPCION,contribuyente.ENTIDAD\n");
        query.append(" ORDER BY contribuyente.ENTIDAD, estatus.DESCRIPCION ASC\n");
        logger.error(query.toString());
        lista = getJdbcTemplateBase().query(query.toString(), new GraficaValoresMapper(), vo.getFechaInicioPeriodo(), vo.getFechaFinPeriodo());
        return lista;
    }

    @Override
    public List<GraficaValoresDTO> graficaInsumosTres(ReportesVO vo) {
        List<GraficaValoresDTO> lista = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_char(to_date(month,'yyyy-mm'),'Month yyyy','nls_date_language=spanish') mes,");
        query.append("nombre_estatus, entidad, total");
        query.append(ReporteInsumosSQL.REPORTE_INSUMOS_EJECTUTIVO_TRES);
        query.append("estatus.DESCRIPCION nombre_estatus, contribuyente.ENTIDAD entidad");
        query.append(ReporteInsumosSQL.REPORTE_INSUMOS_EJECTUTIVO_TRES_SIGUIENTE.replace(ReporteInsumosSQL.PARAM_RFC_ACIACE, InsumoDaoHelper.getSQLRFCCreacion(vo.getLstRfcCreacion())));
        if (vo.getEstatusId() != null && vo.getEstatusId().compareTo(BigDecimal.ZERO) > 0) {
            query.append(AND_ESTATUS).append(vo.getEstatusId().toString());
        }
        if (vo.getEntidad() != null && !vo.getEntidad().trim().isEmpty()) {
            query.append(AND_ENTIDAD).append(vo.getEntidad()).append("%'");

        }
        query.append(" GROUP BY estatus.DESCRIPCION,contribuyente.ENTIDAD, to_char(insumo.FECHA_CREACION,'yyyy-mm')");
        query.append(" ORDER BY to_char(insumo.FECHA_CREACION,'yyyy-mm')) mm");

        logger.error(query.toString());
        lista = getJdbcTemplateBase().query(query.toString(), new GraficaValoresMapper(), vo.getFechaInicioPeriodo(), vo.getFechaFinPeriodo());
        return lista;
    }

}
