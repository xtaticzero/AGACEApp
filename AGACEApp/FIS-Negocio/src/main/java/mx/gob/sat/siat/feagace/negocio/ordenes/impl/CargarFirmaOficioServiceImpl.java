package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenNotificacion;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaOficioService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.NombreMes;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("cargarFirmaOficioService")
public class CargarFirmaOficioServiceImpl extends OrdenesServiceBase implements CargarFirmaOficioService {

    private static final long serialVersionUID = 1456786L;

    private static final BigDecimal ID_LEYENDA_OFICIO_RECHAZO = new BigDecimal(55);

    @Autowired
    private transient NotificacionService notificacionService;

    @Override
    public void enviarCorreoOficioOrden(FecetOficio oficio) {
        AgaceOrden orden = getAgaceOrdenDao().findWhereIdOrdenEquals(oficio.getIdOrden().longValue()).get(0);
        EmpleadoDTO empleado = null;
        try {
            empleado = getEmpleadoCompleto(orden.getIdAuditor().intValue());
            enviarCorreoConfirmacion(oficio, empleado.getCorreo());
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener datos del servicio de empleado agace", e);
        }
    }

    @Override
    public void enviarCorreoOficio(BigDecimal idOrden, FecetOficio oficio) {
        AgaceOrden orden = getAgaceOrdenDao().findWhereIdOrdenEquals(oficio.getIdOrden().longValue()).get(0);
        EmpleadoDTO empleado = null;
        try {
            empleado = getEmpleadoCompleto(orden.getIdAuditor().intValue());
            enviarCorreoConfirmacion(idOrden, empleado.getCorreo());
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener datos del servicio de empleado agace", e);
        }
    }

    @Override
    public void enviarNotificacionOficioRechazo(AgaceOrden orden, FecetOficio oficio, String descripcionRechazo) {
        if (orden != null && oficio != null) {
            Set<String> destinatarios = new TreeSet<String>();
            notificacionService.obtenerCorreoEmpleado(orden.getIdAuditor().intValue(), Constantes.USUARIO_AUDITOR, destinatarios, ClvSubModulosAgace.SEGUIMIENTO);
            Map<String, String> dataCorreo = notificacionService.obtenerDatosCorreo(ID_LEYENDA_OFICIO_RECHAZO);
            dataCorreo.put("NombreOficio", oficio.getNumeroOficio());
            dataCorreo.put("NúmeroOrden", orden.getNumeroOrden());
            dataCorreo.put("numeroOrden", orden.getNumeroOrden());
            dataCorreo.put("NombreOficio", oficio.getFecetTipoOficio().getNombre());
            dataCorreo.put("metodo", orden.getFeceaMetodo().getNombre());
            dataCorreo.put("MOTIVO_RECHAZO", descripcionRechazo);
            dataCorreo.put(Constantes.DATOS_EXTRA, String.format(Constantes.CONTENIDO_EXTRA, orden.getFecetContribuyente().getNombre(),
                    orden.getFecetContribuyente().getRfc(), oficio.getFecetTipoOficio().getNombre()));
            try {
                notificacionService.enviarNotificacionGenerico(dataCorreo, ReportType.AVISOS_ORDEN_GENERICO, destinatarios);
            } catch (BusinessException e) {
                logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(), e);
            }
        }
    }

    @Override
    public void enviarCorreoOficioRechazo(FecetOficio oficio) {
        AgaceOrden orden = getAgaceOrdenDao().findWhereIdOrdenEquals(oficio.getIdOrden().longValue()).get(0);
        EmpleadoDTO empleado = null;
        try {
            empleado = getEmpleadoCompleto(orden.getIdAuditor().intValue());
            enviarCorreoConfirmacion(oficio, empleado.getCorreo());
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener datos del servicio de empleado agace", e);
        }

    }

    private void enviarCorreoConfirmacion(FecetOficio oficio, String correo) {
        Set<String> destinatarios = new TreeSet<String>();
        String tipoAviso;
        ReportType pantalla;
        Map<String, String> mapa = new HashMap<String, String>();
        List<String> listaCorreoFinal;
        /**
         * 1. Destinatarios de correo Se recorre la lista para obtener la lista
         * de correos y se colocan en el mapa para que no esten duplicados
         *
         */
        mapa.put(correo, correo);
        /**
         * Se obtienen los valores del mapa y se asignan a una lista final
         *
         */
        listaCorreoFinal = new ArrayList<String>(mapa.values());
        /**
         * Se asignan los correos del map a la lista de destinatarios
         *
         */
        for (String listaFinal : listaCorreoFinal) {
            destinatarios.add(listaFinal);
        }
        /**
         * 2. Datos ordenes Obtenemos los datos necesarios de acuerdo a lo
         * solicitado por los tipos de aviso
         *
         */
        List<OrdenNotificacion> ordenSeleccionada
                = notificacionService.findDetalleContribuyente(oficio.getIdOrden());

        tipoAviso = Constantes.ASUNTO_AVISO_SOLICITUD_PRORROGA;
        pantalla = ReportType.AVISO_SOLICITUD_PRORROGA;

        enviarCorreoOrdenes(destinatarios, ordenSeleccionada, tipoAviso, pantalla);
    }

    private void enviarCorreoConfirmacion(BigDecimal idOrden, String correo) {
        Set<String> destinatarios = new TreeSet<String>();
        String tipoAviso;
        ReportType pantalla;
        Map<String, String> mapa = new HashMap<String, String>();
        List<String> listaCorreoFinal;
        //1. Destinatarios de correo
        // Se recorre la lista para obtener la lista de correos y se colocan en el mapa para que no esten duplicados
        mapa.put(correo, correo);

        // Se obtienen los valores del mapa y se asignan a una lista final
        listaCorreoFinal = new ArrayList<String>(mapa.values());
        // Se asignan los correos del map a la lista de destinatarios
        for (String listaFinal : listaCorreoFinal) {
            destinatarios.add(listaFinal);
        }

        //2. Datos ordenes
        // Obtenemos los datos necesarios de acuerdo a lo solicitado por los tipos de aviso
        List<OrdenNotificacion> ordenSeleccionada = notificacionService.findDetalleContribuyente(idOrden);

        tipoAviso = Constantes.ASUNTO_AVISO_SOLICITUD_PRORROGA;
        pantalla = ReportType.AVISO_SOLICITUD_PRORROGA;

        enviarCorreoOrdenes(destinatarios, ordenSeleccionada, tipoAviso, pantalla);
    }

    public void enviarCorreoOrdenes(Set<String> destinatarios, List<OrdenNotificacion> ordenSeleccionada,
            String tipoAviso, ReportType pantalla) {
        logger.info("Envia correo orden documento requerido y alegatos...");
        for (OrdenNotificacion orden : ordenSeleccionada) {
            Map<String, String> data = new HashMap<String, String>();
            data.put(Common.SUBJECT.toString(), tipoAviso);
            data.put(Constantes.FOLIO_CARGA, orden.getFolioCarga().toString());
            data.put(Constantes.METODO_ORDEN, orden.getDescripcionMetodo());
            data.put(Constantes.NUMERO_ORDEN, orden.getNumeroOrden());
            data.put(Constantes.NOMBRE_CONTRIBUYENTE, orden.getNombreContribuyente());
            data.put(Constantes.RFC_CONTRIBUYENTE, orden.getRfcContribuyente());
            this.enviarNotificacionCorreos(data, pantalla, destinatarios);
        }
    }

    public void enviarNotificacionCorreos(Map<String, String> data, ReportType reportType,
            Set<String> destinatarios) {
        try {
            Calendar date = new GregorianCalendar();
            data.put(Common.YEAR.toString(), "" + date.get(Calendar.YEAR));
            data.put(Common.DAY.toString(), "" + date.get(Calendar.DAY_OF_MONTH));
            data.put(Common.MONTH.toString(), NombreMes.obtenerNombre(date.get(Calendar.MONTH)));
            data.put(Common.HOUR.toString(),
                    DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_24H_HHMM, date.getTime()));
            notificacionService.enviarNotificacion(data, reportType, destinatarios);
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(), e);
        }
    }
}
