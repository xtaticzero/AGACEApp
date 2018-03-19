package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.io.IOException;
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

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAlegatoOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetPromocionOficioDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenNotificacion;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaPruebasPromoService;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarMetodoOrdenAsociadoRule;
import mx.gob.sat.siat.feagace.negocio.util.constantes.BusinessUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.NombreMes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("cargarFirmaPruebasPromoService")
public class CargarFirmaPruebasPromoServiceImpl extends OrdenesServiceBase implements CargarFirmaPruebasPromoService {

    private static final long serialVersionUID = -6517451861554711482L;

    @Autowired
    private transient FecetPromocionDao fecetPromocionDao;
    @Autowired
    private transient FecetPromocionOficioDao fecetPromocionOficioDao;
    @Autowired
    private transient FecetAlegatoOficioDao fecetAlegatoOficioDao;
    @Autowired
    private transient ValidarMetodoOrdenAsociadoRule validarMetodoOrdenAsociadoRule;
    @Autowired
    private transient NotificacionService notificacionService;
    @Autowired
    private transient FecetContribuyenteDao fecetContribuyenteDao;
    @Autowired
    private transient PlazosServiceOrdenes plazosService;
    @Autowired
    private transient EmpleadoService empleadoService;
    @Autowired
    private transient FecetOficioDao fecetOficioDao;

    /**
     *
     * @param promocionOficio
     * @param listaPruebasAlegatosOficio
     * @throws NegocioException
     */
    @Override
    public void guardarPromocionOficio(final FecetPromocionOficio promocionOficio,
            List<FecetAlegatoOficio> listaPruebasAlegatosOficio) throws NegocioException {
        try {
            ArchivoOrdenUtil.guardarArchivoPromocionOficio(promocionOficio);
            fecetPromocionOficioDao.insertarRegistroGuardarArchivoPromocionOficio(promocionOficio);
            if (!listaPruebasAlegatosOficio.isEmpty()) {
                for (FecetAlegatoOficio pruebasAlegatosOficio : listaPruebasAlegatosOficio) {
                    StringBuilder rutaArchivo = new StringBuilder();
                    rutaArchivo.append(RutaArchivosUtil.armarRutaDestinoPruebasAlegatosOficio(promocionOficio));
                    rutaArchivo.append(pruebasAlegatosOficio.getNombreArchivo());
                    pruebasAlegatosOficio.setIdPromocionOficio(promocionOficio.getIdPromocionOficio());
                    pruebasAlegatosOficio.setRutaArchivo(rutaArchivo.toString());
                    ArchivoOrdenUtil.guardarArchivoPruebasAlegatosOficio(pruebasAlegatosOficio);
                    fecetAlegatoOficioDao.insert(pruebasAlegatosOficio);
                }
            }
        } catch (IOException e) {
            throw new NegocioException(ConstantesError.ERROR_GUARDAR_ARCHIVO_ESPECIFICO + e.getCause(), e);
        }
    }

    @Override
    public void guardarPromocionPruebasAlegatosOficioAuditor(List<FecetPromocionOficio> listaPromociones,
            List<FecetAlegatoOficio> listaDocumentosAlegato,
            final FecetOficio oficio) throws NegocioException {
        FecetPromocionOficio promocionOficio = null;
        StringBuilder rutaArchivo = new StringBuilder();
        if (listaPromociones == null || listaPromociones.isEmpty()) {
            throw new NegocioException("No se ha cargado un documento de promocion");
        } else {
            promocionOficio = listaPromociones.get(0);
        }
        rutaArchivo.append(RutaArchivosUtil.armarRutaDestinoPromocionOficio(ArchivoOrdenUtil.getPathFromAbsolutePath(oficio.getRutaArchivo()),
                promocionOficio.getIdPromocionOficio()));
        rutaArchivo.append(promocionOficio.getNombreArchivo());
        promocionOficio.setIdPromocionOficio(fecetPromocionDao.getIdPromocion());
        promocionOficio.setRutaArchivo(rutaArchivo.toString());
        promocionOficio.setExtemporanea("1");
        promocionOficio.setIdOficio(oficio.getIdOficio());

        guardarPromocionOficio(promocionOficio, listaDocumentosAlegato);
    }

    @Override
    public List<FecetPromocionOficio> getPromocionOficioContadorPruebasAlegatos(final AgaceOrden orden,
            final FecetOficio oficio) {
        List<FecetPromocionOficio> listaPromocionesOficio
                = fecetPromocionOficioDao.getPromocionContadorPruebasAlegatosOficio(oficio.getIdOficio());

        for (FecetPromocionOficio promocionOficio : listaPromocionesOficio) {
            List<String> leyendas = getLeyendaDoc(orden, oficio);
            promocionOficio.setDescripcionTipoPromocion(leyendas.size() == 2 ? leyendas.get(1) : "");
            promocionOficio.setExtemporanea(plazosService.esDocumentoExtemporaneoOficio(oficio,
                    promocionOficio.getFechaCarga())
                            ? "1" : "0");
        }

        return listaPromocionesOficio;
    }

    @Override
    public List<FecetPromocionOficio> getPromocionOficioContadorPruebasAlegatos(final FecetOficio oficio) {
        List<FecetPromocionOficio> listaPromocionesOficio
                = fecetPromocionOficioDao.getPromocionContadorPruebasAlegatosOficio(oficio.getIdOficio());

        for (FecetPromocionOficio promocionOficio : listaPromocionesOficio) {
            promocionOficio.setDescripcionTipoPromocion(BusinessUtil.getTipoPromocionPorTipoOficioMetodo(oficio.getFecetTipoOficio().getIdTipoOficio()));
            promocionOficio.setExtemporanea(plazosService.esDocumentoExtemporaneoOficio(oficio,
                    oficio.getFechaCreacion())
                            ? "1" : "0");
        }

        return listaPromocionesOficio;
    }

    @Override
    public List<FecetAlegatoOficio> getPruebasAlegatosPromocionOficio(final BigDecimal idPromocionOficio) {
        return fecetAlegatoOficioDao.findWhereIdPromocionEquals(idPromocionOficio);
    }

    @Override
    public List<String> getLeyendaDoc(AgaceOrden orden, FecetOficio oficio) {
        return validarMetodoOrdenAsociadoRule.validaMetodoDespliegueAlegato(orden, oficio);
    }

    @Override
    public void enviarCorreoPromocion(FecetPromocion promocionGuardar) {
        AgaceOrden orden = getAgaceOrdenDao().findWhereIdOrdenEquals(promocionGuardar.getIdOrden().longValue()).get(0);
        EmpleadoDTO empleado = null;
        try {
            empleado = empleadoService.getEmpleadoCompleto(orden.getIdAuditor().intValue());
            enviarCorreoConfirmacion(promocionGuardar, empleado.getCorreo());
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener datos del servicio de empleado agace", e);
        }
    }

    @Override
    public void enviarCorreoPromocionOficio(BigDecimal idOrden, FecetPromocionOficio promocionOficioGuardar) {
        FecetOficio oficio = fecetOficioDao.getOficioById(promocionOficioGuardar.getIdOficio());
        AgaceOrden orden = getAgaceOrdenDao().findWhereIdOrdenEquals(oficio.getIdOrden().longValue()).get(0);
        EmpleadoDTO empleado = null;
        try {
            empleado = empleadoService.getEmpleadoCompleto(orden.getIdAuditor().intValue());
            enviarCorreoConfirmacion(idOrden, promocionOficioGuardar, empleado.getCorreo());
        } catch (EmpleadoServiceException e) {
            logger.error("Error al obtener datos del servicio de empleado agace", e);
        }
    }

    private void enviarCorreoConfirmacion(FecetPromocion promocionGuardar, String correo) {
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

        if (promocionGuardar.getLeyenda().equals(Constantes.TIPO_PROMOCION_PRUEBAS_ALEGATOS)) {
            tipoAviso = Constantes.ASUNTO_AVISO_DOCUMENTACION_PRUEBAS_ALEGATOS;
            pantalla = ReportType.AVISO_CARGA_DOCUMENTACION_PROMOCION_PRUEBAS_ALEGATOS;
        } else {
            tipoAviso = Constantes.ASUNTO_AVISO_CARGA_DOCUMENTACION_REQUERIDA;
            pantalla = ReportType.AVISO_CARGA_DOCUMENTACION_PROMOCION_DOC_REQUERIDA;
        }

        //2. Datos ordenes
        // Obtenemos los datos necesarios de acuerdo a lo solicitado por los tipos de aviso
        List<OrdenNotificacion> ordenSeleccionada
                = notificacionService.findDetalleContribuyente(promocionGuardar.getIdOrden(),
                        promocionGuardar.getIdPromocion());

        enviarCorreoOrdenes(destinatarios, ordenSeleccionada, tipoAviso, pantalla);
    }

    private void enviarCorreoConfirmacion(BigDecimal idOrden, FecetPromocionOficio promocionGuardar, String correo) {
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

        if (promocionGuardar.getLeyenda().equals(Constantes.TIPO_PROMOCION_PRUEBAS_ALEGATOS)) {
            tipoAviso = Constantes.ASUNTO_AVISO_DOCUMENTACION_PRUEBAS_ALEGATOS;
            pantalla = ReportType.AVISO_CARGA_DOCUMENTACION_PROMOCION_PRUEBAS_ALEGATOS;
        } else {
            tipoAviso = Constantes.ASUNTO_AVISO_CARGA_DOCUMENTACION_REQUERIDA;
            pantalla = ReportType.AVISO_CARGA_DOCUMENTACION_PROMOCION_DOC_REQUERIDA;
        }

        //2. Datos ordenes
        // Obtenemos los datos necesarios de acuerdo a lo solicitado por los tipos de aviso
        List<OrdenNotificacion> ordenSeleccionada
                = notificacionService.findDetalleContribuyentePromocionOficio(idOrden,
                        promocionGuardar.getIdPromocionOficio());

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

    @Override
    public FecetContribuyente obtenerContribuyenteInfo(BigDecimal idOrden) {
        return fecetContribuyenteDao.obtenerContribuyenteAuditado(idOrden);

    }

}
