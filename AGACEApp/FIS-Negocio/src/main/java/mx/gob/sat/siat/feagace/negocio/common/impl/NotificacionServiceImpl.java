/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT)
 * Todos los derechos reservados.
 * Este software contiene informacion confidencial propiedad de
 * la Servicio de Administracion Tributaria (SAT)
 * Por lo cual no puede ser reproducido, distribuido o
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT).
 */
package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.correo.model.MailMessage;
import mx.gob.sat.siat.common.correo.model.MailMessageAttachment;
import mx.gob.sat.siat.common.correo.services.MailServices;
import mx.gob.sat.siat.feagace.modelo.dao.common.OrdenNotificacionDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FececLeyenda;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenNotificacion;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.common.CommonServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.common.FececLeyendaService;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.CorreoException;
import mx.gob.sat.siat.feagace.negocio.exception.DocumentoException;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.util.Propiedades;
import mx.gob.sat.siat.feagace.negocio.util.constantes.NombreMes;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

/**
 * Servicios para envio de notificaciones via correo electronico.
 *
 * @author Alfredo Ramirez - 749972
 * @version 1.0
 *
 */
@Service("notificacionService")
public class NotificacionServiceImpl extends CommonServiceAbstract implements NotificacionService {

    /**
     *
     */
    @Autowired
    private MailServices mailServices;

    @Autowired
    private OrdenNotificacionDao ordenNotificacionDao;

    @Autowired
    private FececLeyendaService fececLeyendaService;

    @Value("${mail_services.from}")
    private String from;

    @Value("${mail_services.personal_from}")
    private String personalFrom;

    private static final long serialVersionUID = 1L;
    private static final String RUTA_CORREO_PLANTILLAS = Propiedades.get("ruta.correo.plantillas");
    private static final String RUTA_CORREO_PLANTILLAS_LAYOUT = Propiedades.get("ruta.correo.plantillas.layout");
    private static final String RUTA_CORREO_HEADER = Propiedades.get("ruta.correo.header");
    private static final String RUTA_CORREO_HEADER_GENERICO = Propiedades.get("ruta.correo.header.generico");
    private static final String RUTA_CORREO_FOOTER = Propiedades.get("ruta.correo.footer");
    private static final String RUTA_CORREO_ACTIVO = Propiedades.get("ruta.correo.activo");
    private static final String RUTA_CORREO_IMAGENES = Propiedades.get("ruta.correo.imagenes");

    private static final String CODIFICACION = "UTF-8";

    private Map<String, String> data;

    /**
     * Envia la Notificacion del guardado de la orden, al destinatario correspondiente
     *
     * @param data
     * @param reportType
     * @param destinatarios
     * @throws mx.gob.sat.siat.feagace.negocio.exception.DocumentoException
     * @throws mx.gob.sat.siat.feagace.negocio.exception.CorreoException
     */
    @Override
    public void enviarNotificacion(Map<String, String> data, ReportType reportType, Set<String> destinatarios) throws BusinessException {
        this.data = data == null ? new HashMap<String, String>() : data;
        logger.error("Se inicia el envio de correo. ".concat(RUTA_CORREO_ACTIVO));
        logger.error("ReportType:".concat(reportType != null ? reportType.getName() : ""));
        logger.error("::subject::".concat(this.data.get(Common.SUBJECT.toString())));
        if (Boolean.parseBoolean(RUTA_CORREO_ACTIVO)) {
            if (reportType == null) {
                throw new CorreoException("Es requerido el tipo de correo para enviar notificacion.");
            }
            logger.info("destinatarios {} ", destinatarios);
            if (destinatarios == null) {
                throw new CorreoException("Es requerido que se especifiquen los destinatarios para enviar notificacion.");
            }
            if (destinatarios.isEmpty()) {
                throw new CorreoException("Es requerido que se especifiquen los destinatatios para enviar notificacion.");
            }
            String content;
            try {
                content = getContent(reportType);
                content = proccessReport(content, this.data);
                MailMessage message = new MailMessage();
                message.setTo(destinatarios);
                message.setSubject(this.data.get(Common.SUBJECT.toString()));
                message.setBody(content);
                message.setFrom(from);
                message.setImagenesInlIne(obtenerImagenes());
                message.setPersonalFrom(personalFrom);
                mailServices.sendHTMLMessage(message);
            } catch (DocumentoException de) {
                logger.error(de.getMessage());
            } catch (Exception e) {
                logger.error("correo:" + e.toString());
                for (StackTraceElement er : e.getStackTrace()) {
                    logger.error("CO " + er.getLineNumber() + " " + e.getClass());
                }
            }

        }
    }

    @Override
    public void enviarNotificacionGenerico(Map<String, String> data, ReportType reportType, Set<String> destinatarios, MailMessageAttachment... adjunto)
            throws BusinessException {
        this.data = data == null ? new HashMap<String, String>() : data;
        logger.error("Se inicia el envio de correo. ".concat(RUTA_CORREO_ACTIVO));
        logger.error("ReportType:".concat(reportType != null ? reportType.getName() : ""));
        logger.error("::subject::" + this.data.get(Common.SUBJECT.toString()));
        if (Boolean.parseBoolean(RUTA_CORREO_ACTIVO)) {
            if (reportType == null) {
                throw new CorreoException("Es requerido el tipo de correo para enviar notificacion.");
            }
            logger.info("destinatarios {} ", destinatarios);
            if (destinatarios == null || destinatarios.isEmpty()) {
                throw new CorreoException("Es requerido que se especifiquen los destinatatios para enviar notificacion.");
            }
            String content;
            try {
                content = getContentGenerico(reportType);
                content = proccessReport(content, this.data);
                MailMessage message = new MailMessage();
                message.setTo(destinatarios);
                message.setSubject(this.data.get(Common.SUBJECT.toString()));
                if (adjunto != null && adjunto.length > 0) {
                    for (MailMessageAttachment adjuntoAux : adjunto) {
                        message.addAttachment(adjuntoAux);
                    }
                }
                message.setBody(content);
                message.setFrom(from);
                message.setPersonalFrom(personalFrom);
                message.setImagenesInlIne(obtenerImagenes());
                mailServices.sendHTMLMessage(message);
            } catch (DocumentoException de) {
                logger.error(de.getMessage());

            } catch (Exception e) {
                logger.error("correo:" + e.toString(), e);
                for (StackTraceElement er : e.getStackTrace()) {
                    logger.error("CO " + er.getLineNumber() + " " + e.getClass());
                }
            }

        }
    }

    private Map<String, String> obtenerImagenes() {
        Map<String, String> imagenes = new HashMap<String, String>();
        if (RUTA_CORREO_IMAGENES != null && !RUTA_CORREO_IMAGENES.isEmpty()) {
            File directorioImagenes = new File(RUTA_CORREO_IMAGENES);
            if (directorioImagenes.exists() && directorioImagenes.isDirectory()) {
                String nombre;
                for (File imagen : directorioImagenes.listFiles()) {
                    if (imagen.isDirectory()) {
                        continue;
                    } else if (imagen.getName().indexOf('.') != -1) {
                        nombre = imagen.getName().substring(0, imagen.getName().lastIndexOf('.'));
                    } else {
                        nombre = imagen.getName();
                    }
                    imagenes.put(nombre, imagen.getAbsolutePath());
                }
            }
        }
        return imagenes;
    }

    private String getContent(ReportType reportType) throws DocumentoException {
        String content;
        String header;
        String footer;
        try {
            header = getReportHeader();
            content = getReportContent(reportType);
            footer = getReportFooter();
            content = header + content + footer;
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            throw new DocumentoException(ConstantesError.ERROR_CODIFICACION_DOCUMENTO, e);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new DocumentoException(ConstantesError.ERROR_PLANTILLAS_NOTIFICACION, e);
        }
        return content;
    }

    private String getContentGenerico(ReportType reportType) throws DocumentoException {
        String content;
        String header;
        try {
            header = getReportContent(RUTA_CORREO_PLANTILLAS_LAYOUT, RUTA_CORREO_HEADER_GENERICO);
            content = getReportContent(reportType);
            content = header + content;
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            throw new DocumentoException(ConstantesError.ERROR_CODIFICACION_DOCUMENTO, e);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new DocumentoException(ConstantesError.ERROR_PLANTILLAS_NOTIFICACION, e);
        }

        return content;
    }

    private String getReportHeader() throws IOException {
        StringBuilder stringBuilder = new StringBuilder(Constantes.CADENA_VACIA);
        String linea;
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream(RUTA_CORREO_PLANTILLAS_LAYOUT + RUTA_CORREO_HEADER), CODIFICACION));

        while ((linea = br.readLine()) != null) {
            stringBuilder.append(linea);
        }
        br.close();
        return stringBuilder.toString();
    }

    private String getReportFooter() throws IOException {
        StringBuilder stringBuilder = new StringBuilder(Constantes.CADENA_VACIA);
        String linea;
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream(RUTA_CORREO_PLANTILLAS_LAYOUT + RUTA_CORREO_FOOTER), CODIFICACION));

        while ((linea = br.readLine()) != null) {
            stringBuilder.append(linea);
        }
        br.close();
        return stringBuilder.toString();
    }

    @SuppressWarnings("rawtypes")
    private String proccessReport(String content, Map<String, String> data) {
        Iterator<Entry<String, String>> iterator = data.entrySet().iterator();
        Map.Entry pair;
        String contenido = content;
        String key, value;
        while (iterator.hasNext()) {
            pair = iterator.next();
            key = pair.getKey() == null ? "" : pair.getKey().toString();
            value = pair.getValue() == null ? "" : pair.getValue().toString();
            contenido = contenido.replaceAll("<%" + key + "%>", value);
        }

        return contenido;
    }

    private String getReportContent(ReportType reportType) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(Constantes.CADENA_VACIA);
        String linea;
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream(RUTA_CORREO_PLANTILLAS + reportType.getName()), CODIFICACION));

        while ((linea = br.readLine()) != null) {
            stringBuilder.append(linea);
        }
        br.close();
        return stringBuilder.toString();
    }

    private String getReportContent(String ruta, String nombre) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String linea;
        Scanner scanner = new Scanner(new File(ruta + nombre), CODIFICACION);
        while (scanner.hasNextLine()) {
            linea = scanner.nextLine();
            stringBuilder.append(linea);
        }
        return stringBuilder.toString();
    }

    @Override
    public List<OrdenNotificacion> findDetalleContribuyente(BigDecimal idOrden, BigDecimal idPromocion) {
        List<OrdenNotificacion> detalleContribuyente;
        detalleContribuyente = ordenNotificacionDao.findDetalleContribuyente(idOrden, idPromocion);
        return detalleContribuyente;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public List<OrdenNotificacion> findDetalleContribuyente(BigDecimal idOrden) {
        List<OrdenNotificacion> detalleContribuyente;
        detalleContribuyente = ordenNotificacionDao.findDetalleContribuyente(idOrden);
        return detalleContribuyente;
    }

    @Override
    public List<String> getLstNombreAuditor(BigDecimal idAuditor, BigDecimal idOrden) {
        List<String> nombreAuditor;
        nombreAuditor = ordenNotificacionDao.getLstNombreAuditor(idAuditor, idOrden);
        return nombreAuditor;
    }

    @Override
    public List<String> getLstCorreoFirmante(BigDecimal idFirmante, BigDecimal idOrden) {
        List<String> lstCorreoFirmante;
        lstCorreoFirmante = ordenNotificacionDao.getLstCorreoFirmante(idFirmante, idOrden);
        return lstCorreoFirmante;
    }

    @Override
    public List<String> getLstCorreoAuditor(BigDecimal idAuditor, BigDecimal idOrden) {
        List<String> listCorreoAuditor;
        listCorreoAuditor = ordenNotificacionDao.getLstCorreoAuditor(idAuditor, idOrden);
        return listCorreoAuditor;
    }

    @Override
    public List<String> getLstIdOrdenxOficio(BigDecimal idOficio) {
        List<String> lstIdOrden;
        lstIdOrden = ordenNotificacionDao.getLstIdOrdenxOficio(idOficio);
        return lstIdOrden;
    }

    @Override
    public List<OrdenNotificacion> findDetalleContribuyentePromocionOficio(BigDecimal idOrden, BigDecimal idPromocionOficio) {
        List<OrdenNotificacion> detalleContribuyente;
        detalleContribuyente = ordenNotificacionDao.findDetalleContribuyentePromocionOficio(idOrden, idPromocionOficio);
        return detalleContribuyente;
    }

    @Override
    public List<OrdenNotificacion> findDetalleContribuyenteProrrogaOrden(BigDecimal idOrden, BigDecimal idProrrogaOrden) {
        List<OrdenNotificacion> detalleContribuyente;
        detalleContribuyente = ordenNotificacionDao.findDetalleContribuyenteProrroga(idOrden, idProrrogaOrden);
        return detalleContribuyente;
    }
    
    @Override
    public List<OrdenNotificacion> findDetalleContribuyentePruebaPericialOrden(BigDecimal idOrden, BigDecimal idProrrogaOrden) {
        List<OrdenNotificacion> detalleContribuyente;
        detalleContribuyente = ordenNotificacionDao.findDetalleContribuyentePruebaPericial(idOrden, idProrrogaOrden);
        return detalleContribuyente;
    }

    @Override
    public List<OrdenNotificacion> findDetalleContribuyenteProrrogaOficio(BigDecimal idOrden, BigDecimal idProrrogaOficio) {
        List<OrdenNotificacion> detalleContribuyente;
        detalleContribuyente = ordenNotificacionDao.findDetalleContribuyenteProrrogaOficio(idOrden, idProrrogaOficio);
        return detalleContribuyente;
    }
    
    @Override
    public List<OrdenNotificacion> findDetalleContribuyenteProrrogaOficioCompulsa(BigDecimal idOrden) {
        return  ordenNotificacionDao.findDetalleContribuyenteProrrogaOficioCompulsa(idOrden);
        
    }

    @Override
    public void obtenerCorreoEmpleado(String rfc, BigDecimal tipoEmpleado, Set<String> destinatarios, ClvSubModulosAgace clvModulo) {
        try {
            if (tipoEmpleado != null && destinatarios != null) {
                TipoEmpleadoEnum tipoEmp = getMapTipoEmpleado().get(tipoEmpleado.intValue());
                boolean flgTipoEmp = tipoEmp != null;
                if (flgTipoEmp) {
                    EmpleadoDTO empleadoDto = getEmpleadoCompleto(rfc);
                    if (empleadoDto.getDetalleEmpleado() != null && flgTipoEmp) {
                        for (DetalleEmpleadoDTO detalle : empleadoDto.getDetalleEmpleado()) {
                            if (tipoEmp.equals(detalle.getTipoEmpleado())) {
                                destinatarios.add(empleadoDto.getCorreo());
                            }
                        }
                    }
                }
            }
        } catch (EmpleadoServiceException ex) {
            logger.error("No existe empleado: ", ex);
        }
    }

    @Override
    public void obtenerCorreoEmpleado(Integer idEmpleado, BigDecimal tipoEmpleado, Set<String> destinatarios, ClvSubModulosAgace clvModulo) {
        try {
            if (tipoEmpleado != null && destinatarios != null) {
                TipoEmpleadoEnum tipoEmp = getMapTipoEmpleado().get(tipoEmpleado.intValue());
                boolean flgTipoEmp = tipoEmp != null;
                if (flgTipoEmp) {
                    EmpleadoDTO empleadoDto = getEmpleadoCompleto(idEmpleado);
                    if (empleadoDto.getDetalleEmpleado() != null && flgTipoEmp) {
                        for (DetalleEmpleadoDTO detalle : empleadoDto.getDetalleEmpleado()) {
                            if (tipoEmp.equals(detalle.getTipoEmpleado())) {
                                destinatarios.add(empleadoDto.getCorreo());
                            }
                        }
                    }
                }
            }
        } catch (EmpleadoServiceException ex) {
            logger.error("No existe empleado: ", ex);
        }
    }

    @Override
    public void obtenerCorreoEmpleado(BigDecimal tipoEmpleado, BigDecimal unidadAdministrativa, Set<String> destinatarios, ClvSubModulosAgace clvModulo) {

        if (tipoEmpleado != null) {
            try {
                TipoEmpleadoEnum tipoEmp = getMapTipoEmpleado().get(tipoEmpleado.intValue());
                boolean flgTipoEmp = tipoEmp != null;
                if (flgTipoEmp) {
                    List<String> subadministradores = getLstCorreosNotificacionXTipoEmpleadoUnidaAdmin(tipoEmp, unidadAdministrativa, clvModulo);

                    if (subadministradores != null && !subadministradores.isEmpty()) {
                        destinatarios.addAll(subadministradores);
                    }
                }
            } catch (EmpleadoServiceException ex) {
                logger.error("Error al consultar lista notificacion : ", ex);
            }
        }
    }

    @Override
    public void obtenerCorreoCentralAcppce(String rfcSesion, long tipoEmpleado, BigDecimal unidadAdministrativa, Set<String> destinatarios,
            ClvSubModulosAgace clvModulo) {
        try {

            TipoEmpleadoEnum tipoEmp = getMapTipoEmpleado().get((int) tipoEmpleado);
            boolean flgTipoEmp = tipoEmp != null;
            boolean flgACPPCE = BigDecimal.ONE.equals(unidadAdministrativa);
            if (flgTipoEmp && !flgACPPCE) {
                List<String> subadministradores = getLstCorreosNotificacionXTipoEmpleadoUnidaAdmin(TipoEmpleadoEnum.CONSULTOR_INSUMOS, unidadAdministrativa, clvModulo);
                if (subadministradores != null && !subadministradores.isEmpty()) {
                    destinatarios.addAll(subadministradores);
                }
            }

        } catch (EmpleadoServiceException ex) {
            logger.error("Error al consultar lista notificacion : ", ex);
        }
    }

    @Override
    public final Map<String, String> obtenerDatosCorreo(BigDecimal idLeyenda) {
        Map<String, String> datosCorreo = new LinkedHashMap<String, String>();
        List<FececLeyenda> leyedas = fececLeyendaService.obtenerLeyendaByIdLeyenda(Constantes.LEYENDA_CLASIFICACION, Constantes.LEYENDA_PIE_PAGINA,
                Constantes.LEYENDA_CAMPANA_MEDIO_AMBIENTE, idLeyenda);
        for (FececLeyenda leyenda : leyedas) {
            if (Constantes.LEYENDA_CLASIFICACION.equals(leyenda.getIdLeyenda())) {
                datosCorreo.put(Common.LEYENDA_CLASIFICACION.toString(), leyenda.getDescripcion());
            } else if (Constantes.LEYENDA_PIE_PAGINA.equals(leyenda.getIdLeyenda())) {
                datosCorreo.put(Common.PIE_PAGINA.toString(), leyenda.getDescripcion());
            } else if (Constantes.LEYENDA_CAMPANA_MEDIO_AMBIENTE.equals(leyenda.getIdLeyenda())) {
                datosCorreo.put(Common.CAMPANIA_MEDIO_AMBIENTE.toString(), leyenda.getDescripcion());
            } else if (idLeyenda.equals(leyenda.getIdLeyenda())) {
                datosCorreo.put(Common.DESCRIPCION.toString(), leyenda.getDescripcion());
                datosCorreo.put(Common.NOMBRE_LEYENDA.toString(), leyenda.getNombreLeyenda());
                datosCorreo.put(Common.SUBJECT.toString(), leyenda.getNombreLeyenda());
            }
        }
        Calendar date = new GregorianCalendar();
        datosCorreo.put(Common.YEAR.toString(), "" + date.get(Calendar.YEAR));
        datosCorreo.put(Common.DAY.toString(), "" + date.get(Calendar.DAY_OF_MONTH));
        datosCorreo.put(Common.MONTH.toString(), NombreMes.obtenerNombre(date.get(Calendar.MONTH)));
        datosCorreo.put(Common.HOUR.toString(), new SimpleDateFormat("HH:mm").format(date.getTime()));
        return datosCorreo;
    }
}
