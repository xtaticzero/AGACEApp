package mx.gob.sat.siat.feagace.negocio.insumos.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetReasignacionInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumoPk;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetReasignacionInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceInsumos;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.insumos.ReasignacionInsumosService;
import mx.gob.sat.siat.feagace.negocio.insumos.ServicioInsumosAbstract;

@Service("reasignacionInsumosService")
public class ReasignacionInsumosServiceImpl extends ServicioInsumosAbstract implements ReasignacionInsumosService {

    private static final long serialVersionUID = -6051283134908188025L;
    private static final Logger LOG = Logger.getLogger(ReasignacionInsumosServiceImpl.class);

    @Autowired
    private transient FecetReasignacionInsumoDao fecetReasignacionInsumoDao;

    @Autowired
    private transient FecetInsumoDao fecetInsumoDao;

    @Autowired
    private transient PlazosServiceInsumos plazosServiceInsumos;

    @Override
    public List<FecetReasignacionInsumo> getReasignacionInsumoByAdministradorEstatus(EmpleadoDTO empleado, BigDecimal estatus) {
        List<FecetReasignacionInsumo> reasignaciones = fecetReasignacionInsumoDao.findByAdministradorEstatus(empleado.getRfc(), estatus);
        if (reasignaciones != null) {
            Map<String, EmpleadoDTO> empleados = new HashMap<String, EmpleadoDTO>();
            EmpleadoDTO empleadoAux;
            for (FecetReasignacionInsumo fecetReasignacionInsumo : reasignaciones) {
                fecetReasignacionInsumo.setNombreAdministradorDestino(empleado.getNombreCompleto());
                empleadoAux = empleados.get(fecetReasignacionInsumo.getRfcAdministradorOrigen());
                if (empleadoAux == null) {
                    try {
                        empleadoAux = obtenerEmpleadoAcceso(fecetReasignacionInsumo.getRfcAdministradorOrigen(), TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
                        empleados.put(fecetReasignacionInsumo.getRfcAdministradorOrigen(), empleadoAux);
                    } catch (NoExisteEmpleadoException e) {
                        LOG.error("Error al obtener el empleado origen");
                        continue;
                    }
                }
                fecetReasignacionInsumo.setNombreAdministradorOrigen(empleadoAux.getNombreCompleto());
            }
        }
        return reasignaciones;

    }

    @Override
    public List<FecetInsumo> getReasignacionInsumoByAdministradorEstatusExt(String administradorDestino, BigDecimal estatus) {
        List<FecetInsumo> insumos = fecetReasignacionInsumoDao.findReasingacionByAdministradorEstatus(administradorDestino, estatus);
        if (insumos != null) {
            try {
                llenarDetalleGrupoUnidadAdmin(obtenerMapGruposDeUnidades(getEmpleadoCompleto(administradorDestino)), insumos);
                plazosServiceInsumos.inicializarInsumoConPlazos(insumos);
                Map<String, EmpleadoDTO> empleados = new HashMap<String, EmpleadoDTO>();
                EmpleadoDTO empleadoAux;
                for (FecetInsumo fecetInsumo : insumos) {
                    empleadoAux = empleados.get(fecetInsumo.getRfcAdministrador());
                    if (empleadoAux == null) {
                        try {
                            empleadoAux = obtenerEmpleadoAcceso(fecetInsumo.getRfcAdministrador(), TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
                            empleados.put(fecetInsumo.getRfcAdministrador(), empleadoAux);
                        } catch (NoExisteEmpleadoException e) {
                            LOG.error("Error al obtener el empleado origen");
                            continue;
                        }
                    }
                    fecetInsumo.setEmpleado(empleadoAux);
                }
            } catch (EmpleadoServiceException ex) {
                logger.error(ex.getMessage(),ex);
                return new ArrayList<FecetInsumo>();
            }
        }
        return insumos;
    }

    @Override
    public FecetInsumo getReasignacionInsumoByInsumo(BigDecimal idDecimal) {
        return fecetInsumoDao.findByPrimaryKey(idDecimal);
    }

    @Override
    @PistaAuditoria
    public String insert(FecetReasignacionInsumo fecetReasignacionInsumo) {
        List<FecetReasignacionInsumo> reasignacionInsumo;
        FecetInsumo fecetInsumo = fecetInsumoDao.findWhereIdInsumoEquals(fecetReasignacionInsumo.getIdInsumo()).get(0);
        fecetInsumo.setIdEstatus(Constantes.REASIGNADA_A_ADMINISTRADOR);
        FecetInsumoPk fecetInsumoPk = fecetInsumo.createPk();
        fecetInsumoDao.update(fecetInsumoPk, fecetInsumo);
        reasignacionInsumo = fecetReasignacionInsumoDao.findByIdMetodo(fecetInsumo.getIdInsumo());

        if (reasignacionInsumo != null && !reasignacionInsumo.isEmpty()) {
            for (FecetReasignacionInsumo insumo : reasignacionInsumo) {
                insumo.setBlnActivo(0);
                fecetReasignacionInsumoDao.update(insumo);
            }
        }

        plazosServiceInsumos.validarSuspensionPlazoReasignar(fecetInsumo);
        fecetReasignacionInsumoDao.insert(fecetReasignacionInsumo);
        return fecetInsumo.getIdRegistro();

    }

    @Override
    public void update(FecetReasignacionInsumo dto) throws NegocioException {
        fecetReasignacionInsumoDao.update(dto);
    }

    @Override
    @PistaAuditoria
    public String aceptaReasignacion(FecetReasignacionInsumo fecetReasignacionInsumo, FecetInsumo fecetInsumo) {
        fecetInsumo.setRfcAdministrador(fecetReasignacionInsumo.getRfcAdministradorDestino());
        fecetInsumo.setIdEstatus(Constantes.ACEPTACION_REASIGNACION_A_ADMINISTRADOR);
        FecetInsumoPk fecetInsumoPk = fecetInsumo.createPk();
        fecetInsumoDao.update(fecetInsumoPk, fecetInsumo);
        plazosServiceInsumos.validarReactivacionPlazo(fecetInsumo);
        fecetReasignacionInsumoDao.update(fecetReasignacionInsumo);
        return fecetInsumo.getIdRegistro();

    }

    @Override
    @PistaAuditoria
    public String rechazaReasignacionInsumo(FecetReasignacionInsumo fecetReasignacionInsumo, FecetInsumo fecetInsumo) {
        fecetInsumo.setIdEstatus(Constantes.RECHAZO_REASIGNACION_A_ADMINISTRADOR);
        FecetInsumoPk fecetInsumoPk = fecetInsumo.createPk();
        fecetInsumoDao.update(fecetInsumoPk, fecetInsumo);
        plazosServiceInsumos.validarReactivacionPlazo(fecetInsumo);
        fecetReasignacionInsumoDao.update(fecetReasignacionInsumo);
        return fecetInsumo.getIdRegistro();
    }

}
