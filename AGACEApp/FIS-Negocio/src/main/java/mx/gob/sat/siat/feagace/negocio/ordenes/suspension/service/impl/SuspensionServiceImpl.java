package mx.gob.sat.siat.feagace.negocio.ordenes.suspension.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.suspension.dao.SuspensionDAO;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.NotificableNyV;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetSuspensionDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.helper.TiemposHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.suspension.service.SuspensionService;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;

/**
 * Servicio para las distintas reglas de suspensiones de tiempos para las
 * ordenes
 *
 * @author eolf
 */
@Service("suspensionService")
public class SuspensionServiceImpl extends BaseBusinessServices implements SuspensionService {

    private static final long serialVersionUID = 9137508999701057581L;

    private static final int IGUAL = 0;
    private static final int REGISTRO_ACTUALIZADO = 1;

    @Autowired
    private transient SuspensionDAO suspensionDao;

    @Autowired
    private TiemposHelper tiemposHelper;

    @Autowired
    private transient NotificacionService notificacionService;

    @Override
    public boolean iniciaSuspensionOrdenXAcuerdo(AgaceOrden orden, String numeroAcuerdo, Date fechaInicio) {
        Integer result;
        boolean[] condiciones = {
            (orden != null),
            (numeroAcuerdo != null && !numeroAcuerdo.isEmpty()),
            (fechaInicio != null)
        };

        if (ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)) {
            result = suspensionDao.guardarSuspensionXAcuerdo(orden.getIdOrden(), fechaInicio, null, numeroAcuerdo);
            if ((result == REGISTRO_ACTUALIZADO)) {
                enviarNotificacion(orden, Constantes.LEYENDA_ACUERDO_INICIO);
            }
            return ((result == REGISTRO_ACTUALIZADO));
        }

        return false;
    }

    private void enviarNotificacion(AgaceOrden orden, BigDecimal idLeyenda) {
        try {
            Map<String, String> mapDatos = notificacionService.obtenerDatosCorreo(idLeyenda);
            mapDatos.put("NumeroOrden", orden.getNumeroOrden());
            Set<String> destinatarios = new TreeSet<String>();

            notificacionService.obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, orden.getIdArace(), destinatarios, ClvSubModulosAgace.SEGUIMIENTO);
            notificacionService.obtenerCorreoEmpleado(orden.getIdAuditor().intValue(), Constantes.USUARIO_AUDITOR, destinatarios, ClvSubModulosAgace.SEGUIMIENTO);
            notificacionService.obtenerCorreoEmpleado(orden.getIdFirmante().intValue(), Constantes.USUARIO_FIRMANTE, destinatarios, ClvSubModulosAgace.SEGUIMIENTO);
            notificacionService.enviarNotificacionGenerico(mapDatos, ReportType.AVISO_AVISOS_CONCLUSIVOS, destinatarios);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean finalizarSuspensionOrdenXAcuerdo(AgaceOrden orden, String numeroAcuerdo, Date fechaFinalizacion) {
        Integer result;
        boolean[] condiciones = {
            (orden != null),
            (numeroAcuerdo != null && !numeroAcuerdo.isEmpty()),
            (fechaFinalizacion != null)
        };

        if (ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)) {
            result = suspensionDao.guardarSuspensionXAcuerdo(orden.getIdOrden(), null, fechaFinalizacion, numeroAcuerdo);
            if ((result == REGISTRO_ACTUALIZADO)) {
                enviarNotificacion(orden, Constantes.LEYENDA_ACUERDO_INICIO);
            }

            return ((result == REGISTRO_ACTUALIZADO));
        }

        return false;
    }

    @Override
    public void iniciaSuspensionOrden(AgaceOrden orden, final Date fechaInicio, final Date fechaFin) {
        suspensionDao.guardarSuspension(orden.getIdOrden(), fechaInicio, fechaFin, BigDecimal.ZERO);
    }

    @Override
    public void iniciaSuspensionOrden(AgaceOrden orden, FecetOficio oficio, final Date fechaInicio, final Date fechaFin) {
        suspensionDao.guardarSuspension(orden.getIdOrden(), oficio.getFecetTipoOficio().getIdTipoOficio(), fechaInicio, fechaFin, oficio.getIdOficio());
    }

    @Override
    public void iniciarSuspensionNotificable(AgaceOrden orden, NotificableNyV notificable, final Date fechaInicio, final BigDecimal idOficio) {
        suspensionDao.guardarSuspension(orden.getIdOrden(), notificable.getId(), fechaInicio, null, idOficio);
    }

    @Override
    public boolean validarReactivacionPlazo(AgaceOrden orden) {
        List<FecetSuspensionDTO> listaSuspension = suspensionDao.buscarSuspension(orden.getIdOrden());
        if (listaSuspension != null && tiemposHelper.comparaFechasSinTiempo(new Date(), listaSuspension.get(0).getFechaFinSuspension()) >= IGUAL) {
            return true;
        }
        return false;
    }

    @Override
    public void finalizarSuspensionOrden(AgaceOrden orden, final Date fechaFinalizacion) {
        suspensionDao.guardarFechaFinSuspension(orden.getIdOrden(), fechaFinalizacion);
    }

    @Override
    public List<FecetSuspensionDTO> buscarSuspensionIdTipoOficio(final BigDecimal idOrden, final BigDecimal idObjeto) {
        return suspensionDao.buscarSuspensionPorIdTipoOficio(idOrden, idObjeto);
    }

    @Override
    public List<FecetSuspensionDTO> buscarAllSuspensionIdTipoOficio(final BigDecimal idOrden, final BigDecimal idObjeto) {
        return suspensionDao.buscarSuspensionAllPorIdTipoOficio(idOrden, idObjeto);
    }

    @Override
    public List<FecetSuspensionDTO> buscarSuspensionesOrden(final BigDecimal idOrden) {
        return suspensionDao.buscarSuspensionesPorId(idOrden);
    }

    @Override
    public List<FecetSuspensionDTO> buscarSuspensionesOrdenReactivacion(final BigDecimal idOrden) {
        return suspensionDao.buscarSuspensionesReactivacion(idOrden);
    }

    @Override
    public boolean estaSuspendida(BigDecimal idOrden) {
        List<FecetSuspensionDTO> listSuspencion = suspensionDao.buscarSuspension(idOrden);
        for (FecetSuspensionDTO suspencion : listSuspencion) {
            if (null == suspencion.getFechaFinSuspension() || tiemposHelper.comparaFechasSinTiempo(suspencion.getFechaFinSuspension(), new Date()) > IGUAL) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean estaSuspendidaReactivacion(BigDecimal idOrden) {
        List<FecetSuspensionDTO> listSuspencion = suspensionDao.buscarSuspensionReactivacion(idOrden);
        for (FecetSuspensionDTO suspencion : listSuspencion) {
            if (null == suspencion.getFechaFinSuspension() || tiemposHelper.comparaFechasSinTiempo(suspencion.getFechaFinSuspension(), new Date()) > IGUAL) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean estaSuspendidaPorPruebasPericiales(BigDecimal idOrden) {
        List<FecetSuspensionDTO> listSuspencion = suspensionDao.buscarSuspension(idOrden);
        TiposOficiosOrdenesEnum oficioPruebasPericiales = TiposOficiosOrdenesEnum.PRUEBAS_PERICIALES_DESAHOGO;
        for (FecetSuspensionDTO suspencion : listSuspencion) {
            if ((suspencion.getFechaFinSuspension() == null || tiemposHelper.comparaFechasSinTiempo(suspencion.getFechaFinSuspension(), new Date()) > IGUAL)
                    && suspencion.getIdObjeto().equals(oficioPruebasPericiales.getBigIdTipoOficio())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existeAcuerdoConclusivo(String numeroAcuerdoConclusivo) {
        return suspensionDao.countFolioAcuerdoConclusivo(numeroAcuerdoConclusivo) != 0;
    }

    @Override
    public FecetSuspensionDTO buscarSuspensionAc(BigDecimal idOrden) {
        List<FecetSuspensionDTO> listSuspencion =suspensionDao.buscarSuspensionesPorId(idOrden);
        for (FecetSuspensionDTO suspension : listSuspencion) {
            if(suspension.getIdObjeto()==null){
                return suspension;
            }
        }
        return null;
    }
    
    

}
