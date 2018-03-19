package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenNotificacion;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaProrrogaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.prorroga.firma.helper.FirmaProrrogaHelper;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarMetodoProrrogasRule;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarOficioAdministrable;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarProrrogasEstatusRule;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("cargarFirmaProrrogaService")
public class CargarFirmaProrrogaServiceImpl extends BaseBusinessServices implements CargarFirmaProrrogaService {

    private static final long serialVersionUID = 1L;

    private static final BigDecimal SOLICITUD_PRORROGA = BigDecimal.valueOf(60);
    private static final BigDecimal AVISO_RESOLUCION_SOLICITUD = BigDecimal.valueOf(52);
    private static final BigDecimal AVISO_RESOLUCION_SOLICITUD_FIRMANTE = BigDecimal.valueOf(56);

    @Autowired
    private transient FecetProrrogaOrdenDao fecetProrrogaOrdenDao;
    @Autowired
    private transient FecetProrrogaOficioDao fecetProrrogaOficioDao;
    @Autowired
    private transient FecetDocProrrogaOficioDao fecetDocProrrogaOficioDao;
    @Autowired
    private transient ValidarMetodoProrrogasRule validarMetodoProrrogasRule;
    @Autowired
    private transient ValidarProrrogasEstatusRule validarProrrogasEstatusRule;
    @Autowired
    private transient PlazosServiceOrdenes plazosService;
    @Autowired
    private transient NotificacionService notificacionService;

    @Autowired
    private transient ValidarOficioAdministrable validarOficioAdministrable;
    @Autowired
    private FirmaProrrogaHelper firmaProrrogaHelper;

    @Override
    public List<FecetProrrogaOrden> getProrrogaContadorDocs(AgaceOrden orden) {
        return fecetProrrogaOrdenDao.getProrrogaContadorDoc(orden.getIdOrden());
    }

    @Override
    public List<FecetProrrogaOficio> getHistoricoProrrogaOficio(final BigDecimal idOficio) {
        return fecetProrrogaOficioDao.getProrrogaOficioContadorDoc(idOficio);
    }

    @Override
    public List<FecetProrrogaOficio> getHistoricoProrrogaOficioContribuyente(final BigDecimal idOficio) {
        return fecetProrrogaOficioDao.getProrrogaOficioContadorDoc(idOficio);
    }

    @Override
    public List<FecetDocProrrogaOficio> getDocsProrrogaOficio(final BigDecimal idOficio) {
        return fecetDocProrrogaOficioDao.findWhereIdProrrogaOficioEquals(idOficio);
    }

    @Override
    public boolean validaMetodoProrrogaOficio(AgaceOrden orden, FecetOficio oficio) {
        return validarMetodoProrrogasRule.validaMetodoProrrogaOficio(orden, oficio);
    }

    @Override
    public boolean validaMetodoProrrogaOrden(AgaceOrden orden) {
        return validarMetodoProrrogasRule.validaMetodoProrrogaOrden(orden);
    }

    @Override
    public boolean validaProrrogaEstatus(List<FecetProrrogaOficio> prorrogas, FecetOficio oficio) {
        return (validarProrrogasEstatusRule.validarGenerarProrrogaOficio(prorrogas) && plazosService.validarPlazoCrearProrrogaOficio(oficio));
    }

    @Override
    public void enviarCorreoProrroga(FecetProrrogaOrden prorroga, String remitente, String tipo, FecetOficio oficio, FecetProrrogaOficio prorrogaOficio) {
        Set<String> destinatarios = new TreeSet<String>();
        if (prorrogaOficio != null) {
            if (remitente.equals(Constantes.FIRMANTE) || remitente.equals(Constantes.CONTRIBUYENTE)) {
                notificacionService.obtenerCorreoEmpleado(prorrogaOficio.getOrden().getIdAuditor().intValue(), TipoEmpleadoEnum.AUDITOR.getBigId(), destinatarios, ClvSubModulosAgace.SEGUIMIENTO);
            } else {
                notificacionService.obtenerCorreoEmpleado(prorrogaOficio.getOrden().getIdFirmante().intValue(), TipoEmpleadoEnum.FIRMANTE.getBigId(), destinatarios, ClvSubModulosAgace.SEGUIMIENTO);
            }
        } else {
            if (remitente.equals(Constantes.FIRMANTE) || remitente.equals(Constantes.CONTRIBUYENTE)) {
                notificacionService.obtenerCorreoEmpleado(prorroga.getOrden().getIdAuditor().intValue(), TipoEmpleadoEnum.AUDITOR.getBigId(), destinatarios, ClvSubModulosAgace.SEGUIMIENTO);
            } else {
                notificacionService.obtenerCorreoEmpleado(prorroga.getOrden().getIdFirmante().intValue(), TipoEmpleadoEnum.FIRMANTE.getBigId(), destinatarios, ClvSubModulosAgace.SEGUIMIENTO);
            }
        }

        enviarCorreoConfirmacion(prorroga, destinatarios, remitente, tipo, oficio, prorrogaOficio);
    }

    private void enviarCorreoConfirmacion(FecetProrrogaOrden prorroga, Set<String> destinatarios, String remitente, String tipo, FecetOficio oficio, FecetProrrogaOficio prorrogaOficio) {
        OrdenNotificacion ordenSeleccionada = null;
        if (prorrogaOficio != null) {
            if (prorrogaOficio.getOrden().isBlnCompulsa()) {
                ordenSeleccionada = notificacionService.findDetalleContribuyenteProrrogaOficio(oficio.getIdOrden(),
                        prorrogaOficio.getIdProrrogaOficio()).get(0);
                OrdenNotificacion contribuyente = notificacionService.findDetalleContribuyenteProrrogaOficioCompulsa(prorrogaOficio.getOrden().getIdOrden()).get(0);
                ordenSeleccionada.setNombreContribuyente(contribuyente.getNombreContribuyente());
                ordenSeleccionada.setRfcContribuyente(contribuyente.getRfcContribuyente());
            } else {
                ordenSeleccionada = notificacionService.findDetalleContribuyenteProrrogaOficio(prorrogaOficio.getOrden().getIdOrden(),
                        prorrogaOficio.getIdProrrogaOficio()).get(0);
            }

        } else {
            ordenSeleccionada = notificacionService.findDetalleContribuyenteProrrogaOrden(prorroga.getIdOrden(),
                    prorroga.getIdProrrogaOrden()).get(0);
        }

        Map<String, String> data = null;

        if (remitente.equals(Constantes.CONTRIBUYENTE)) {
            data = notificacionService.obtenerDatosCorreo(SOLICITUD_PRORROGA);
        } else if (remitente.equals(Constantes.AUDITOR)) {
            data = notificacionService.obtenerDatosCorreo(AVISO_RESOLUCION_SOLICITUD);
        } else if (remitente.equals(Constantes.FIRMANTE)) {
            data = notificacionService.obtenerDatosCorreo(AVISO_RESOLUCION_SOLICITUD_FIRMANTE);
        }

        enviarCorreo(data, destinatarios, ordenSeleccionada, remitente, ReportType.AVISOS_ORDEN_GENERICO, tipo, oficio);

    }

    private void enviarCorreo(Map<String, String> data, Set<String> destinatarios, OrdenNotificacion ordenSeleccionada, String remitente, ReportType pantalla, String tipo, FecetOficio oficio) {

        try {
            notificacionService.enviarNotificacionGenerico(firmaProrrogaHelper.armaCorreo(data, ordenSeleccionada, remitente, tipo, oficio), pantalla, destinatarios);
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(), e);
        }
    }

    public boolean validaDocResProrroga(FecetProrrogaOficio prorroga) {
        return (validarMetodoProrrogasRule.validarDocResProrroga(prorroga));
    }

    public boolean validaOficioAdministrable(FecetOficio oficio) {
        return validarOficioAdministrable.esAdministrable(oficio);
    }

    public boolean validaOficioConProrrogas(FecetOficio oficio) {
        return validarOficioAdministrable.tieneProrrogas(oficio);
    }

    public boolean validaOficioConPlazos(FecetOficio oficio) {
        return validarOficioAdministrable.tienePlazos(oficio);
    }
}
