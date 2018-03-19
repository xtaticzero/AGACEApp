package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenNotificacion;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaPruebasPericialesService;
import mx.gob.sat.siat.feagace.negocio.ordenes.pruebaspericiales.firma.helper.FirmaPruebaPericialHelper;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("cargarFirmaPruebasPericialesService")
public class CargarFirmaPruebasPericialesServiceImpl extends BaseBusinessServices implements CargarFirmaPruebasPericialesService {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final BigDecimal SOLICITUD_PRUEBA_PERICIAL = BigDecimal.valueOf(63);
    private static final BigDecimal AVISO_RESOLUCION_SOLICITUD = BigDecimal.valueOf(52);
    private static final BigDecimal AVISO_RESOLUCION_SOLICITUD_FIRMANTE = BigDecimal.valueOf(56);

    @Autowired
    private transient FecetPruebasPericialesDao fecetPruebasPericialesDao;

    @Autowired
    private transient NotificacionService notificacionService;

    @Autowired
    private FirmaPruebaPericialHelper firmaPruebaPericialHelper;

    @Override
    public List<FecetPruebasPericiales> getPruebasPericialesContadorDocs(AgaceOrden orden) {
        return fecetPruebasPericialesDao.getPruebasPericialesContadorDoc(orden.getIdOrden());
    }

    @Override
    public void enviarCorreoPruebasPericiales(FecetPruebasPericiales pruebaPericial, String remitente, String tipo) {
        Set<String> destinatarios = new TreeSet<String>();
        if (remitente.equals(Constantes.FIRMANTE) || remitente.equals(Constantes.CONTRIBUYENTE)) {
            notificacionService.obtenerCorreoEmpleado(pruebaPericial.getOrden().getIdAuditor().intValue(), TipoEmpleadoEnum.AUDITOR.getBigId(), destinatarios, ClvSubModulosAgace.SEGUIMIENTO);
        } else {
            notificacionService.obtenerCorreoEmpleado(pruebaPericial.getOrden().getIdFirmante().intValue(), TipoEmpleadoEnum.FIRMANTE.getBigId(), destinatarios, ClvSubModulosAgace.SEGUIMIENTO);
        }

        enviarCorreoConfirmacion(pruebaPericial, destinatarios, remitente, tipo);
    }

    private void enviarCorreoConfirmacion(FecetPruebasPericiales pruebaPericial, Set<String> destinatarios, String remitente, String tipo) {
        OrdenNotificacion ordenSeleccionada = notificacionService.findDetalleContribuyentePruebaPericialOrden(pruebaPericial.getIdOrden(),
                pruebaPericial.getIdPruebasPericiales()).get(0);

        Map<String, String> data = null;

        if (remitente.equals(Constantes.CONTRIBUYENTE)) {
            data = notificacionService.obtenerDatosCorreo(SOLICITUD_PRUEBA_PERICIAL);
        } else if (remitente.equals(Constantes.AUDITOR)) {
            data = notificacionService.obtenerDatosCorreo(AVISO_RESOLUCION_SOLICITUD);
        } else if (remitente.equals(Constantes.FIRMANTE)) {
            data = notificacionService.obtenerDatosCorreo(AVISO_RESOLUCION_SOLICITUD_FIRMANTE);
        }

        enviarCorreo(data, destinatarios, ordenSeleccionada, remitente, ReportType.AVISOS_ORDEN_GENERICO, tipo);

    }

    private void enviarCorreo(Map<String, String> data, Set<String> destinatarios, OrdenNotificacion ordenSeleccionada, String remitente, ReportType pantalla, String tipo) {
        try {
            notificacionService.enviarNotificacionGenerico(firmaPruebaPericialHelper.armaCorreo(data, ordenSeleccionada, remitente, tipo), pantalla, destinatarios);
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(),e);
        }

    }

}
