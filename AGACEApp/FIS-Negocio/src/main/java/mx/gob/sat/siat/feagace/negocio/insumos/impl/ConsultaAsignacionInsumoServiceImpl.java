package mx.gob.sat.siat.feagace.negocio.insumos.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetDocExpInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.AdministradorReasignacion;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorInsumosSubAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumoPk;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceInsumos;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.insumos.ConsultaAsignacionInsumoService;
import mx.gob.sat.siat.feagace.negocio.insumos.ServicioInsumosAbstract;

@Service("consultaAsignacionInsumoService")
public class ConsultaAsignacionInsumoServiceImpl extends ServicioInsumosAbstract implements ConsultaAsignacionInsumoService {

    /**
     * Serial
     */
    private static final long serialVersionUID = 6436002927272079773L;

    private static final Logger LOG = Logger.getLogger(ConsultaAsignacionInsumoServiceImpl.class);

    @Autowired
    private transient FecetInsumoDao fecetInsumoDao;

    @Autowired
    private transient FecetDocExpInsumoDao fecetDocExpInsumoDao;

    @Autowired
    private transient PlazosServiceInsumos plazosServiceInsumos;

    @Override
    public List<FecetInsumo> traeInsumosSinAsignar(final String rfcAdministrador, Map<BigDecimal, GrupoUnidadesAdminXGeneral> grupoUnidadesAdminXGeneral) {
        try {
            List<FecetInsumo> insumos = fecetInsumoDao.traeInsumosSinAsignados(rfcAdministrador);
            plazosServiceInsumos.inicializarInsumoConPlazos(insumos);
            try {
                llenarDetalleGrupoUnidadAdmin(grupoUnidadesAdminXGeneral, insumos);
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            return insumos;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ArrayList<FecetInsumo>();
        }
    }

    @Override
    public List<FecetDocExpInsumo> traeExpedientesCargados(final BigDecimal idPropuesta, final int tipo) {
        if (tipo == Constantes.CERO) {
            plazosServiceInsumos.inicializarPlazo(idPropuesta);
        }
        return fecetDocExpInsumoDao.findWhereIdInsumoEquals(idPropuesta);
    }

    @Override
    @PistaAuditoria
    public List<FecetDocExpInsumo> traeExpedientesCargadosAdmon(final FecetInsumo insumo, final int tipo) {
        if (tipo == Constantes.CERO) {
            plazosServiceInsumos.inicializarPlazo(insumo.getIdInsumo());
        }
        List<FecetDocExpInsumo> documentos = fecetDocExpInsumoDao.findWhereIdInsumoEquals(insumo.getIdInsumo());
        for (FecetDocExpInsumo fecetDocExpInsumo : documentos) {
            fecetDocExpInsumo.setIdRegistroInsumo(insumo.getIdRegistro());
        }
        return documentos;
    }

    @Override
    public List<FecetDocumento> traeDocumentoJustificacionById(BigDecimal idInsumo) {
        FecetInsumo fecetInsumo = new FecetInsumo();
        fecetInsumo.setIdInsumo(idInsumo);
        return obtenerDocumentosByIdInsumo(fecetInsumo);
    }

    @Override
    public List<EmpleadoDTO> getSubAdministradoresACPPCE(EmpleadoDTO empleado) {
        List<EmpleadoDTO> subadministradores = new ArrayList<EmpleadoDTO>();
        Map<TipoEmpleadoEnum, List<EmpleadoDTO>> detalleAdmin = empleado.getSubordinados().get(TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
        try {
            EmpleadoDTO empleadoNuevo = obtenerEmpleadoAcceso(empleado.getRfc(), TipoEmpleadoEnum.VALIDADOR_INSUMOS);
            subadministradores.add(empleadoNuevo);
        } catch (NoExisteEmpleadoException e) {
            LOG.error("Ocurrio un error al consultar empleado", e);
        }
        if (detalleAdmin != null && !detalleAdmin.isEmpty()) {
            List<EmpleadoDTO> detalleSubadministradores = detalleAdmin.get(TipoEmpleadoEnum.VALIDADOR_INSUMOS);
            if (detalleSubadministradores != null && !detalleSubadministradores.isEmpty()) {
                for (EmpleadoDTO empleadoAux : detalleSubadministradores) {
                    try {
                        if (validarExistenciaTipoEmpleado(empleadoAux, TipoEmpleadoEnum.VALIDADOR_INSUMOS) && !existeEmpleado(empleadoAux, subadministradores)) {
                            subadministradores.add(empleadoAux);
                        }
                    } catch (EmpleadoServiceException ex) {
                        logger.error(ex.getMessage(), ex);
                    }
                }
            }
        }
        return subadministradores;

    }

    @Override
    @PistaAuditoria
    public String guardarAsignacionSubadministrador(final FecetInsumo fecetInsumo) {
        fecetInsumoDao.update(new FecetInsumoPk(fecetInsumo.getIdInsumo()), fecetInsumo);
        return fecetInsumo.getIdRegistro();
    }

    @Override
    public List<ContadorInsumosSubAdmin> getContadorInsumos() {
        return fecetInsumoDao.getContadorSubAdministradores();
    }

    @Override
    public List<AdministradorReasignacion> getAdministradoresACPPCE(EmpleadoDTO empleado) {
        List<AdministradorReasignacion> administradores = new ArrayList<AdministradorReasignacion>();
        try {
            List<EmpleadoDTO> empleados = getEmpleadosXUnidadAdmva(empleado.getDetalleEmpleado().get(0).getCentral().getIdArace(),
                    Constantes.USUARIO_ASIGNADOR_INSUMOS.intValue(), ClvSubModulosAgace.INSUMOS);
            if (empleados != null && !empleados.isEmpty()) {
                AdministradorReasignacion reasignacion;
                for (EmpleadoDTO empleadoDTO : empleados) {
                    completarLstDesahogo(empleadoDTO, MAP_UNIDAD_ADMINISTRATIVA);
                    if (!validarExistenciaTipoEmpleado(empleadoDTO, TipoEmpleadoEnum.ASIGNADOR_INSUMOS) || empleado.getRfc().equals(empleadoDTO.getRfc())
                            || empleadoDTO.getDetalleEmpleado().get(0).getLstAraces() == null
                            || empleadoDTO.getDetalleEmpleado().get(0).getLstAraces().isEmpty()) {
                        continue;
                    }
                    reasignacion = new AdministradorReasignacion();
                    reasignacion.setIdAdministrador(empleadoDTO.getIdEmpleado());
                    reasignacion.setNombreAdministrador(empleadoDTO.getNombreCompleto());
                    reasignacion.setRfcAdministrador(empleadoDTO.getRfc());
                    administradores.add(reasignacion);
                }
            }
        } catch (EmpleadoServiceException e) {
            logger.info("Ocurrio un error al consultar los empleados", e);
        }
        return administradores;
    }

    @Override
    public List<ContadorInsumosSubAdmin> getContadorInsumos(List<EmpleadoDTO> listSubadministadores) {
        List<ContadorInsumosSubAdmin> resultados = new ArrayList<ContadorInsumosSubAdmin>();
        if (listSubadministadores != null && !listSubadministadores.isEmpty()) {
            List<String> rfcs = new ArrayList<String>();
            Map<String, ContadorInsumosSubAdmin> conteo = new LinkedHashMap<String, ContadorInsumosSubAdmin>();
            ContadorInsumosSubAdmin actualContador;
            for (EmpleadoDTO subadministrador : listSubadministadores) {
                rfcs.add(subadministrador.getRfc());
                actualContador = new ContadorInsumosSubAdmin();
                actualContador.setNombre(subadministrador.getNombreCompleto());
                actualContador.setTotalInsumos(0L);
                conteo.put(subadministrador.getRfc(), actualContador);
            }
            List<ContadorInsumosSubAdmin> asignados = fecetInsumoDao.getContadorSubAdministradoresByAdministrador(rfcs);
            for (ContadorInsumosSubAdmin contadorInsumosSubAdmin : asignados) {
                actualContador = conteo.get(contadorInsumosSubAdmin.getNombre());
                actualContador.setTotalInsumos(contadorInsumosSubAdmin.getTotalInsumos());
                conteo.put(contadorInsumosSubAdmin.getNombre(), actualContador);
            }
            resultados.addAll(conteo.values());
        }
        return resultados;
    }

    private boolean existeEmpleado(EmpleadoDTO empleadoAux, List<EmpleadoDTO> subadministradores) {
        boolean resultado = false;
        if (!subadministradores.isEmpty()) {
            for (EmpleadoDTO empleadoDTO : subadministradores) {
                if (empleadoDTO.getRfc().equals(empleadoAux.getRfc())) {
                    resultado = true;
                    break;
                }
            }
        }
        return resultado;
    }
}
