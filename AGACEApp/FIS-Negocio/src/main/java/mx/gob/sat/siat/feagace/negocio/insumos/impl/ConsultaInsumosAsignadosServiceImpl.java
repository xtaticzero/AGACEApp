package mx.gob.sat.siat.feagace.negocio.insumos.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetDocExpInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetDocretroinsumoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorAsignadosAdministrador;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorAsignadosAdministradorEstado;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.InsumosAsignadosAdministradorBO;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceInsumos;
import mx.gob.sat.siat.feagace.negocio.common.reportes.JaspertReportsService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NoSeGeneroReporteException;
import mx.gob.sat.siat.feagace.negocio.insumos.ConsultaInsumosAsignadosService;
import mx.gob.sat.siat.feagace.negocio.insumos.ServicioInsumosAbstract;
import mx.gob.sat.siat.feagace.negocio.rules.impl.InsumosAsignadosAdministradorRule;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;

@Service("consultaInsumosAsignadosService")
public class ConsultaInsumosAsignadosServiceImpl extends ServicioInsumosAbstract
        implements ConsultaInsumosAsignadosService {

    /**
     * Serial
     */
    private static final long serialVersionUID = -487351228021885303L;

    private static final int ESTATUS_BLANCO_SEMAFORO = 10;

    @Autowired
    private transient FecetInsumoDao fecetInsumoDao;

    @Autowired
    private transient FecetDocExpInsumoDao fecetDocExpInsumoDao;
    @Autowired
    private transient PlazosServiceInsumos plazosServiceInsumos;
    @Autowired
    private transient FecetDocretroinsumoDao fecetDocretroinsumoDao;
    @Autowired
    @Qualifier("jaspertReportsService")
    private JaspertReportsService jaspertReportsService;

    @Override
    @PistaAuditoria
    public List<ContadorAsignadosAdministradorEstado> getContadorInsumosAsignados(EmpleadoDTO empleadoDto) {
        try {
            List<ContadorAsignadosAdministradorEstado> resultados = new ArrayList<ContadorAsignadosAdministradorEstado>();
            List<EmpleadoDTO> lstAdministradores = getLstSubordinadosPorRelacionEmpleado(empleadoDto, TipoEmpleadoEnum.CONSULTOR_INSUMOS, TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
            List<ContadorAsignadosAdministrador> lista = fecetInsumoDao.getInsumosAsignadosAdministradores(getLstAdministradoresRfc(lstAdministradores));
            fillNombreAdministrador(lista, lstAdministradores);
            if (lista != null && !lista.isEmpty()) {
                InsumosAsignadosAdministradorBO insumosAsignadosAdministradorBO = new InsumosAsignadosAdministradorBO();
                insumosAsignadosAdministradorBO
                        .setRule(InsumosAsignadosAdministradorRule.VALIDA_EXISTENCIA_INSUMOS_ASIGNADOS);
                insumosAsignadosAdministradorBO.setIterator(lista.iterator());
                while (insumosAsignadosAdministradorBO.getRule().process(insumosAsignadosAdministradorBO)) {
                    logger.info("Procesanto contador Insumos Asignados.");
                }
                resultados.addAll(insumosAsignadosAdministradorBO.getContadorAsignadosAdministradorEstados());
            }
            return resultados;
        } catch (EmpleadoServiceException ex) {
            logger.error(ex.getMessage(), ex);
            return new ArrayList<ContadorAsignadosAdministradorEstado>();
        }

    }

    @Override
    public List<FecetInsumo> getInsumosAsignadosAuditor(final String rfcAdministrador) {
        return fecetInsumoDao.getInsumosAdministrador(rfcAdministrador);
    }

    @Override
    @PistaAuditoria
    public List<FecetInsumo> getInsumosAsignadosAuditorEntidad(String rfcAdministrador, String entidad, Map<BigDecimal, GrupoUnidadesAdminXGeneral> mapGrupos) {

        if (rfcAdministrador != null && !rfcAdministrador.isEmpty()) {
            try {
                List<FecetInsumo> insumos = fecetInsumoDao.getInsumosAdministradorEntidad(rfcAdministrador, entidad);
                plazosServiceInsumos.inicializarInsumoConPlazos(insumos);
                //Cambiar
                llenarDetalleGrupoUnidadAdmin(mapGrupos, insumos);
                return insumos;
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return new ArrayList<FecetInsumo>();
    }

    @Override
    public List<FecetDocExpInsumo> getDocumentosPropuesta(final BigDecimal idPropuesta) {
        return fecetDocExpInsumoDao.findWhereIdInsumoEquals(idPropuesta);

    }

    @Override
    public List<FecetDocretroinsumo> getDocumentosRetroalimentadosByIdRetroInsumo(BigDecimal idRetroalimentacionInsumo,
            BigDecimal idTipoEmpleado) {
        return fecetDocretroinsumoDao.findByFecetRetroalimentacionInsumo(idRetroalimentacionInsumo, idTipoEmpleado);
    }

    @Override
    public void generarReporte(List<FecetInsumo> registros, OutputStream salida) throws NoSeGeneroReporteException {
        List<Map<String, Object>> elementos = new ArrayList<Map<String, Object>>();

        Map<String, Object> elemento;
        for (final FecetInsumo insumo : registros) {
            elemento = new HashMap<String, Object>();
            elemento.put("nombreRazonSocial", insumo.getFecetContribuyente().getNombre());
            elemento.put("subPrograma", insumo.getFececSubprograma().getDescripcion());
            elemento.put("idRegistro", insumo.getIdRegistro());
            elemento.put("rfc", insumo.getFecetContribuyente().getRfc());
            elemento.put("sector", insumo.getFececSector().getDescripcion());
            elemento.put("entidad", insumo.getFecetContribuyente().getEntidad());
            elemento.put("prioridadSugerida", insumo.getValorPrioridad());
            if (insumo.getSemaforo() == ESTATUS_BLANCO_SEMAFORO) {
                elemento.put("plazoConcluir", insumo.getDiasDespuesDePlazoDescripcion());
            } else {
                elemento.put("plazoConcluir", insumo.getDescripcionPlazoRestante());
            }
            String semaforo = insumo.getImagenSemaforo() != null ? insumo.getImagenSemaforo() : "";
            semaforo = semaforo.replace("semaforo-", "").toUpperCase();
            elemento.put("semaforo", semaforo);
            elemento.put("estatus", insumo.getFececEstatus().getDescripcion());
            elementos.add(elemento);
        }
        byte[] reporte;
        try {
            String rutaAbsoluta = String.format("%s%s%s", ConstantesReportes.PATH_REPORTE,
                    ConstantesReportes.REPORTE_INSUMOS, ConstantesReportes.REPORTE_CONSULTA_ADMIN_EXCEL);
            reporte = jaspertReportsService.makeReport(rutaAbsoluta, "reporte.xlsx", null, elementos);
            salida.write(reporte);
            salida.flush();
        } catch (Exception e) {
            throw new NoSeGeneroReporteException(e.getMessage(), e);
        }
    }

    private List<String> getLstAdministradoresRfc(List<EmpleadoDTO> lstAdministradores) throws EmpleadoServiceException {
        List<String> lstRfcAdmin = new ArrayList<String>();
        if (lstAdministradores != null) {
            for (EmpleadoDTO det : lstAdministradores) {
                if (det.getRfc() != null) {
                    lstRfcAdmin.add(det.getRfc());
                }
            }
        }
        return lstRfcAdmin;
    }

    private void fillNombreAdministrador(List<ContadorAsignadosAdministrador> lstContador, List<EmpleadoDTO> lstSubordinados) {
        if (lstContador != null && lstSubordinados != null) {
            for (ContadorAsignadosAdministrador contador : lstContador) {
                for (EmpleadoDTO subordinado : lstSubordinados) {
                    if (contador.getRfcAdministrador().equals(subordinado.getRfc())) {
                        contador.setNombreAdministrador(subordinado.getNombreCompleto());
                        break;
                    }
                }
            }
        }
    }    

}
