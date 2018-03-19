package mx.gob.sat.siat.feagace.negocio.reportes.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dao.reportes.ReportesInsumosDao;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.ReportesOrdenesDao;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.ReportesPropuestasDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.GraficaValoresDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteInsumosDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteOrdenesDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportePropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportesVO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.helper.FechasReportesHelper;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.reportes.GenerarReportesService;

/**
 * Servicio para consultar los criterios de busqueda y devolver el resultado
 *
 * @author Domingo Fernandez
 * @version 1.0
 *
 */
@Service("generarReportesService")
public class GenerarReportesServiceImpl extends PropuestasServiceAbstract implements GenerarReportesService {

    private static final long serialVersionUID = -8134667244128258682L;

    @Autowired
    private transient ReportesInsumosDao reportesInsumosDao;

    @Autowired
    private transient ReportesPropuestasDao reportesPropuestasDao;

    @Autowired
    private transient ReportesOrdenesDao reportesOrdenesDao;

    @Autowired
    private FechasReportesHelper fechasReportesHelper;

    @Override
    public List<ReporteInsumosDTO> generarReporteInsumos(ReportesVO vo) {
        List<ReporteInsumosDTO> listaResultado = null;
        listaResultado = this.getReportesInsumosDao().findReporteInsumosGerencial(vo);
        return listaResultado;
    }

    @Override
    public List<GraficaValoresDTO> graficaInsumosCero(ReportesVO vo) {
        vo.setFechaInicioPeriodo(this.asignarFechaInicio(vo));
        vo.setFechaFinPeriodo(this.asignarFechaFinal(vo));
        List<GraficaValoresDTO> listaResultado = null;
        listaResultado = this.getReportesInsumosDao().graficaInsumosCero(vo);
        if (listaResultado != null && listaResultado.size() == 1 && listaResultado.get(0).getCantidad() == 0) {
            listaResultado = null;
        }
        return listaResultado;
    }

    @Override
    public List<GraficaValoresDTO> graficaInsumosUno(ReportesVO vo) {
        vo.setFechaInicioPeriodo(this.asignarFechaInicio(vo));
        vo.setFechaFinPeriodo(this.asignarFechaFinal(vo));
        List<GraficaValoresDTO> listaResultado = null;
        listaResultado = this.getReportesInsumosDao().graficaInsumosUno(vo);
        return listaResultado;
    }

    @Override
    public List<GraficaValoresDTO> graficaInsumosDos(ReportesVO vo) {
        vo.setFechaInicioPeriodo(this.asignarFechaInicio(vo));
        vo.setFechaFinPeriodo(this.asignarFechaFinal(vo));
        List<GraficaValoresDTO> listaResultado = null;
        listaResultado = this.getReportesInsumosDao().graficaInsumosDos(vo);
        return listaResultado;
    }

    @Override
    public List<GraficaValoresDTO> graficaInsumosTres(ReportesVO vo) {
        vo.setFechaInicioPeriodo(this.asignarFechaInicio(vo));
        vo.setFechaFinPeriodo(this.asignarFechaFinal(vo));
        List<GraficaValoresDTO> listaResultado = null;
        listaResultado = this.getReportesInsumosDao().graficaInsumosTres(vo);
        return listaResultado;
    }

    private Date asignarFechaInicio(ReportesVO vo) {
        return this.getFechasReportesHelper().crearFecha(vo.getFechaInicioPeriodo(), vo.getFechaInicio());
    }

    private Date asignarFechaFinal(ReportesVO vo) {
        return this.getFechasReportesHelper().crearFecha(vo.getFechaFinPeriodo(), vo.getFechaFinal());
    }

    @Override
    public List<ReportePropuestasDTO> generarReportePropuestas(ReportesVO vo, EmpleadoDTO empleado,
            List<EmpleadoDTO> listaEmpleadosCreacion, List<FececUnidadAdministrativa> lstUnidades) {
        List<ReportePropuestasDTO> listaResultado = null;
        listaResultado = this.getReportesPropuestasDao().findReportePropuestasGerencial(vo,
                condicionCentral(empleado, listaEmpleadosCreacion));

        if (listaResultado != null && !listaResultado.isEmpty()) {
            Map<String, EmpleadoDTO> listaFirmante = new HashMap<String, EmpleadoDTO>();
            Map<String, EmpleadoDTO> listaAuditor = new HashMap<String, EmpleadoDTO>();

            for (ReportePropuestasDTO reportePropuesta : listaResultado) {

                for (FececUnidadAdministrativa arace : lstUnidades) {
                    if (reportePropuesta.getIdArace().equals(arace.getIdUnidadAdministrativa())) {
                        reportePropuesta.setUnidadAdministrativa(arace.getNombre());
                    }
                }

                EmpleadoDTO firmante = null;

                if (!listaFirmante.containsKey(reportePropuesta.getRfcFirmante())) {
                    try {
                        firmante = getEmpleadoCompleto(reportePropuesta.getRfcFirmante());
                        if (firmante != null) {
                            listaFirmante.put(firmante.getRfc(), firmante);
                        }
                    } catch (EmpleadoServiceException e) {
                        logger.error(e.getMessage());
                    }
                } else {
                    firmante = listaFirmante.get(reportePropuesta.getRfcFirmante());
                }
                if (firmante != null) {
                    reportePropuesta.setFirmante(firmante.getNombreCompleto());
                    Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> suboordinados = firmante
                            .getSubordinados();
                    Map<TipoEmpleadoEnum, List<EmpleadoDTO>> sub = suboordinados.get(TipoEmpleadoEnum.FIRMANTE);
                    List<EmpleadoDTO> lstAuditores = sub.get(TipoEmpleadoEnum.AUDITOR);

                    for (EmpleadoDTO auditor : lstAuditores) {
                        if (!listaAuditor.containsKey(auditor.getRfc())) {
                            listaAuditor.put(auditor.getRfc(), auditor);
                        }
                    }

                    EmpleadoDTO auditor = null;

                    if (!listaAuditor.containsKey(reportePropuesta.getRfcAuditor())) {
                        try {
                            auditor = getEmpleadoCompleto(reportePropuesta.getRfcAuditor());
                            if (auditor != null) {
                                listaAuditor.put(auditor.getRfc(), auditor);
                            }
                        } catch (EmpleadoServiceException e) {
                            logger.error(e.getMessage());
                        }
                    } else {
                        auditor = listaAuditor.get(reportePropuesta.getRfcAuditor());
                    }
                    reportePropuesta.setAuditor(auditor != null ? auditor.getNombreCompleto() : "");
                } else {
                    reportePropuesta.setFirmante("");
                    reportePropuesta.setAuditor("");
                }
            }
        }

        return listaResultado;
    }

    @Override
    public List<GraficaValoresDTO> graficaPropuestasCero(ReportesVO vo, EmpleadoDTO empleado, List<EmpleadoDTO> listaEmpleadosCreacion) {
        vo.setFechaInicioPeriodo(this.asignarFechaInicio(vo));
        vo.setFechaFinPeriodo(this.asignarFechaFinal(vo));
        List<GraficaValoresDTO> listaResultado = null;
        listaResultado = this.getReportesPropuestasDao().graficaPropuestasCero(vo, condicionCentral(empleado, listaEmpleadosCreacion));
        if (listaResultado != null && listaResultado.size() == 1 && listaResultado.get(0).getCantidad() == 0) {
            listaResultado = null;
        }
        return listaResultado;
    }

    @Override
    public List<GraficaValoresDTO> graficaPropuestasUno(ReportesVO vo, EmpleadoDTO empleado, List<EmpleadoDTO> listaEmpleadosCreacion) {
        vo.setFechaInicioPeriodo(this.asignarFechaInicio(vo));
        vo.setFechaFinPeriodo(this.asignarFechaFinal(vo));
        List<GraficaValoresDTO> listaResultado = null;
        listaResultado = this.getReportesPropuestasDao().graficaPropuestasUno(vo, condicionCentral(empleado, listaEmpleadosCreacion));
        return listaResultado;
    }

    @Override
    public List<GraficaValoresDTO> graficaPropuestasDos(ReportesVO vo, EmpleadoDTO empleado, List<EmpleadoDTO> listaEmpleadosCreacion) {
        vo.setFechaInicioPeriodo(this.asignarFechaInicio(vo));
        vo.setFechaFinPeriodo(this.asignarFechaFinal(vo));
        List<GraficaValoresDTO> listaResultado = null;
        listaResultado = this.getReportesPropuestasDao().graficaPropuestasDos(vo, condicionCentral(empleado, listaEmpleadosCreacion));
        return listaResultado;
    }

    @Override
    public List<GraficaValoresDTO> graficaPropuestasTres(ReportesVO vo, EmpleadoDTO empleado, List<EmpleadoDTO> listaEmpleadosCreacion) {
        vo.setFechaInicioPeriodo(this.asignarFechaInicio(vo));
        vo.setFechaFinPeriodo(this.asignarFechaFinal(vo));
        List<GraficaValoresDTO> listaResultado = null;
        listaResultado = this.getReportesPropuestasDao().graficaPropuestasTres(vo, condicionCentral(empleado, listaEmpleadosCreacion));
        return listaResultado;
    }

    @Override
    public List<ReporteOrdenesDTO> generarReporteOrdenes(ReportesVO vo, EmpleadoDTO empleado, List<EmpleadoDTO> listaEmpleadosCreacion) {
        List<ReporteOrdenesDTO> listaResultado = null;
        listaResultado = this.getReportesOrdenesDao().findReporteOrdenesGerencial(vo, condicionCentral(empleado, listaEmpleadosCreacion));
        if (listaResultado != null && !listaResultado.isEmpty()) {
            Map<BigDecimal, EmpleadoDTO> listaFirmante = new HashMap<BigDecimal, EmpleadoDTO>();
            Map<BigDecimal, EmpleadoDTO> listaAuditor = new HashMap<BigDecimal, EmpleadoDTO>();
            fillUnidadReporte(listaResultado);
            for (ReporteOrdenesDTO reporte : listaResultado) {
                EmpleadoDTO firmante = null;
                if (!listaFirmante.containsKey(reporte.getIdFirmante())) {
                    try {
                        firmante = getEmpleadoCompleto(reporte.getIdFirmante().intValue());
                        listaFirmante.put(firmante.getIdEmpleado(), firmante);
                    } catch (EmpleadoServiceException e) {
                        logger.error(e.getMessage());
                    }
                } else {
                    firmante = listaFirmante.get(reporte.getIdFirmante());
                }
                if (firmante != null) {
                    reporte.setFirmante(firmante.getNombreCompleto());
                    Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> suboordinados = firmante.getSubordinados();
                    Map<TipoEmpleadoEnum, List<EmpleadoDTO>> sub = suboordinados.get(TipoEmpleadoEnum.FIRMANTE);
                    List<EmpleadoDTO> lstSubs = sub.get(TipoEmpleadoEnum.AUDITOR);
                    for (EmpleadoDTO auditor : lstSubs) {
                        if (!listaAuditor.containsKey(auditor.getIdEmpleado())) {
                            listaAuditor.put(auditor.getIdEmpleado(), auditor);
                        }
                    }
                    EmpleadoDTO auditor = null;
                    if (!listaAuditor.containsKey(reporte.getIdAuditor())) {
                        try {
                            auditor = getEmpleadoCompleto(reporte.getIdAuditor().intValue());
                            listaAuditor.put(auditor.getIdEmpleado(), auditor);
                        } catch (EmpleadoServiceException e) {
                            logger.error(e.getMessage());
                        }
                    } else {
                        auditor = listaAuditor.get(reporte.getIdAuditor());
                    }
                    reporte.setAuditor(auditor != null ? auditor.getNombreCompleto() : "");
                } else {
                    reporte.setFirmante("");
                    reporte.setAuditor("");
                }
            }
        }
        return listaResultado;
    }

    @Override
    public List<GraficaValoresDTO> graficaOrdenesCero(ReportesVO vo, EmpleadoDTO empleado, List<EmpleadoDTO> listaEmpleadosCreacion) {
        vo.setFechaInicioPeriodo(this.asignarFechaInicio(vo));
        vo.setFechaFinPeriodo(this.asignarFechaFinal(vo));
        List<GraficaValoresDTO> listaResultado = null;
        listaResultado = this.getReportesOrdenesDao().graficaOrdenesCero(vo, condicionCentral(empleado, listaEmpleadosCreacion));
        if (listaResultado != null && listaResultado.size() == 1 && listaResultado.get(0).getCantidad() == 0) {
            listaResultado = null;
        }
        return listaResultado;
    }

    @Override
    public List<GraficaValoresDTO> graficaOrdenesUno(ReportesVO vo, EmpleadoDTO empleado, List<EmpleadoDTO> listaEmpleadosCreacion) {
        vo.setFechaInicioPeriodo(this.asignarFechaInicio(vo));
        vo.setFechaFinPeriodo(this.asignarFechaFinal(vo));
        List<GraficaValoresDTO> listaResultado = null;
        listaResultado = this.getReportesOrdenesDao().graficaOrdenesUno(vo, condicionCentral(empleado, listaEmpleadosCreacion));
        return listaResultado;
    }

    @Override
    public List<GraficaValoresDTO> graficaOrdenesDos(ReportesVO vo, EmpleadoDTO empleado, List<EmpleadoDTO> listaEmpleadosCreacion) {
        vo.setFechaInicioPeriodo(this.asignarFechaInicio(vo));
        vo.setFechaFinPeriodo(this.asignarFechaFinal(vo));
        List<GraficaValoresDTO> listaResultado = null;
        listaResultado = this.getReportesOrdenesDao().graficaOrdenesDos(vo, condicionCentral(empleado, listaEmpleadosCreacion));
        if (listaResultado != null && !listaResultado.isEmpty()) {
            fillUnidad(listaResultado);
        }

        return listaResultado;
    }

    @Override
    public List<GraficaValoresDTO> graficaOrdenesTres(ReportesVO vo, EmpleadoDTO empleado, List<EmpleadoDTO> listaEmpleadosCreacion) {
        vo.setFechaInicioPeriodo(this.asignarFechaInicio(vo));
        vo.setFechaFinPeriodo(this.asignarFechaFinal(vo));
        List<GraficaValoresDTO> listaResultado = null;
        listaResultado = this.getReportesOrdenesDao().graficaOrdenesTres(vo, condicionCentral(empleado, listaEmpleadosCreacion));
        if (listaResultado != null && !listaResultado.isEmpty()) {
            fillUnidad(listaResultado);
        }
        return listaResultado;
    }

    private String condicionCentral(EmpleadoDTO empleado, List<EmpleadoDTO> listaEmpleadosCreacion) {
        StringBuilder query = new StringBuilder();
        BigDecimal idArace = new BigDecimal(empleado.getDetalleEmpleado().get(0).getCentral().getIdArace());
        if (Constantes.ACPPCE.equals(idArace)) {
            query.append(" AND ((propuesta.RFC_CREACION IN (");
            String prefix = "";
            for (EmpleadoDTO programador : listaEmpleadosCreacion) {
                query.append(prefix);
                query.append(" '").append(programador.getRfc()).append("'");
                prefix = ",";
            }
            query.append(")");
            query.append(" AND propuesta.ID_ARACE IN (");
            query.append(String.valueOf(TipoAraceEnum.ADACE_CENTRO.getId()).concat(prefix));
            query.append(String.valueOf(TipoAraceEnum.ADACE_NOROESTE.getId()).concat(prefix));
            query.append(String.valueOf(TipoAraceEnum.ADACE_NORTE_CENTRO.getId()).concat(prefix));
            query.append(String.valueOf(TipoAraceEnum.ADACE_OCCIDENTE.getId()).concat(prefix));
            query.append(String.valueOf(TipoAraceEnum.ADACE_PACIFICO_NORTE.getId()).concat(prefix));
            query.append(String.valueOf(TipoAraceEnum.ADACE_SUR.getId()).concat(prefix));
            query.append(String.valueOf(TipoAraceEnum.ACOECE.getId()).concat(prefix));
            query.append(String.valueOf(TipoAraceEnum.ACAOCE.getId()));
            query.append(")))\n");
        }
        return query.toString();
    }

    public void setReportesInsumosDao(ReportesInsumosDao reportesInsumosDao) {
        this.reportesInsumosDao = reportesInsumosDao;
    }

    public ReportesInsumosDao getReportesInsumosDao() {
        return reportesInsumosDao;
    }

    public void setFechasReportesHelper(FechasReportesHelper fechasReportesHelper) {
        this.fechasReportesHelper = fechasReportesHelper;
    }

    public FechasReportesHelper getFechasReportesHelper() {
        return fechasReportesHelper;
    }

    public void setReportesPropuestasDao(ReportesPropuestasDao reportesPropuestasDao) {
        this.reportesPropuestasDao = reportesPropuestasDao;
    }

    public ReportesPropuestasDao getReportesPropuestasDao() {
        return reportesPropuestasDao;
    }

    public void setReportesOrdenesDao(ReportesOrdenesDao reportesOrdenesDao) {
        this.reportesOrdenesDao = reportesOrdenesDao;
    }

    public ReportesOrdenesDao getReportesOrdenesDao() {
        return reportesOrdenesDao;
    }
}
