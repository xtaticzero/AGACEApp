package mx.gob.sat.siat.feagace.vista.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.softtek.idc.constants.IDCConstants;
import com.softtek.idc.model.IdCInterno;
import com.softtek.idc.model.Ubicacion;
import com.softtek.idc.service.IDCService;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececFeriados;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececAuditor;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.common.AuditorService;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaAntecedentesInsumoService;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaAntecedentesPropuestasService;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaMediosContactoService;
import mx.gob.sat.siat.feagace.negocio.common.FestivosService;
import mx.gob.sat.siat.feagace.negocio.common.FirmanteService;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.common.SubAdministradorService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarOrdenService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilOrdenes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.NombreMes;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

public class AbstractManagedBean extends AccesoUsuarioMBAbstract {

    private static final long serialVersionUID = 1L;

    private static final long N_4196000L = 4194304L;

    private static final String SERVICE = "festivosService";
    protected static final String ERROR_LOGGEO = "No se pudo obtener la informacion del usuario logueado";
    private static final String LEYENDA = "Se encontraron: ";
    private static final String LABEL_REGISTROS_CONSULTA_ANTECEDENTES = "lbl.propuestas.consultaAntecedentesSiRegistros";
    private static final String LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES = "lbl.propuestas.consultaAntecedentesNoRegistros";
    private static final String LABEL_REGISTROS_CONSULTA_PROPUESTAS_ANTECEDENTES = "lbl.propuestas.consultaPropuestaAntecedentes";
    private static final String LABEL_REGISTROS_CONSULTA_INSUMOS_ANTECEDENTES = "lbl.propuestas.consultaInsumosAntecedentes";

    private static final String ERROR_ARCHIVO_INVALIDO = "Archivo inv\u00E1lido";
    public static final String ERROR_ARCHIVO_REPETIDO = "Archivo ya existente en la lista de documentos, verifique por favor";
    public static final String ERROR_ARCHIVO_CARGADO = "Ya se cargo un archivo de Resoluci\u00F3n";

    /**
     * festivosService es la referencia del servicio de Pistas de Auditoria.
     */
    private transient FestivosService festivosService;

    /**
     * auditorService es la referencia del servicio del Auditor.
     */
    private transient AuditorService auditorService;

    /**
     * idcService es la referencia del servicio del Idc para obtener la
     * informacion del contribuyente.
     */
    @ManagedProperty(value = "#{idcService}")
    private transient IDCService idcService;

    @ManagedProperty(value = "#{consultaMediosContactoService}")
    private transient ConsultaMediosContactoService consultaMediosContactoService;

    /**
     * notificacionService es la referencia del servicio del correo electronico
     * para enviar notificaciones a empleados SAT.
     */
    @ManagedProperty(value = "#{notificacionService}")
    private transient NotificacionService notificacionService;

    /**
     * firmanteService es la referencia del servicio del Firmante.
     */
    private transient FirmanteService firmanteService;

    /**
     * firmanteService es la referencia del servicio del SubAdministrador.
     */
    private transient SubAdministradorService subAdministradorService;

    /**
     * firmanteService es la referencia del servicio del Firmante.
     */
    private transient CargarOrdenService cargarOrdenService;

    /**
     * consultaAntecedentes es la referencia del servicio de consulta de
     * antedentes del Contribuyente.
     */
    @ManagedProperty(value = "#{consultaAntecedentesService}")
    private transient ConsultaAntecedentesPropuestasService consultaAntecedentesService;

    @ManagedProperty(value = "#{consultaAntecedentesInsumoService}")
    private transient ConsultaAntecedentesInsumoService consultaAntecedentesInsumoService;

    private String secciones[] = {IDCConstants.IDENTIFICACION, IDCConstants.UBICACION,
        IDCConstants.REPRESENTANTES_LEGALES};

    public void informeErrorSession(final String e) {
        try {
            HttpSession session;
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

            session.setAttribute("mensaje", e);

            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(dir.getContextPath() + "/faces/error/indexError.jsf");
        } catch (IOException f) {
            logger.error("No se pudo redireccionar a la pagina de error");
        }
    }

    public void informeErrorSession(Exception e) {
        try {
            HttpSession session;
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

            session.setAttribute("mensaje", e);

            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(dir.getContextPath() + "/faces/error/indexError.jsf");
        } catch (IOException f) {
            logger.error("No se pudo redireccionar a la pagina de error");
        }
    }

    public String tiempoRestantePeriodoProcesado(final Integer diasRestantesPlazo) {
        Integer totalDias = diasRestantesPlazo;
        Integer anios = totalDias / Constantes.ENTERO_DIAS_ANIO;
        Double meses = (totalDias - (anios * Constantes.ENTERO_DIAS_ANIO)) / Constantes.DIAS_MES;
        Double dias = totalDias - ((anios * Constantes.ENTERO_DIAS_ANIO) + (Math.round(meses) * Constantes.DIAS_MES));

        StringBuilder textoPlazoRestante = new StringBuilder();

        if (anios > 0) {
            textoPlazoRestante.append(anios).append(" ")
                    .append(FacesUtil.getMessageResourceString("plazo.restante.year"));
        }

        if (meses.intValue() > 0) {
            textoPlazoRestante.append(" ").append(meses.intValue()).append(" ")
                    .append(FacesUtil.getMessageResourceString("plazo.restante.months"));
        }

        if (dias > 0) {
            textoPlazoRestante.append(" ").append(Math.round(dias)).append(" ")
                    .append(FacesUtil.getMessageResourceString("plazo.restante.days"));
        }

        if (textoPlazoRestante.toString().equals("")) {
            textoPlazoRestante.append(FacesUtil.getMessageResourceString("plazo.restante.vencido"));
        }

        return textoPlazoRestante.toString();
    }

    public String obtenerImagenSemaforo(final Integer semaforo) {

        String textoImagenSemaforo;

        switch (semaforo) {
            case 1:
                textoImagenSemaforo = Constantes.SEMAFORO_VERDE;
                break;
            case 2:
                textoImagenSemaforo = Constantes.SEMAFORO_AMARILLO;
                break;
            case Constantes.ENTERO_TRES:
                textoImagenSemaforo = Constantes.SEMAFORO_NARANJA;
                break;
            case Constantes.ENTERO_CUATRO:
                textoImagenSemaforo = Constantes.SEMAFORO_ROJO;
                break;
            case Constantes.ENTERO_CINCO:
                textoImagenSemaforo = Constantes.SEMAFORO_CAFE;
                break;
            case Constantes.ENTERO_SEIS:
                textoImagenSemaforo = Constantes.SEMAFORO_AZUL;
                break;
            case Constantes.ENTERO_SIETE:
                textoImagenSemaforo = Constantes.SEMAFORO_GRIS;
                break;
            case Constantes.ENTERO_OCHO:
                textoImagenSemaforo = Constantes.SEMAFORO_NEGRO;
                break;
            default:
                textoImagenSemaforo = Constantes.SEMAFORO_VERDE;
                break;
        }

        return textoImagenSemaforo;
    }

    public String getTipoPromocionOrdenPorMetodo(final BigDecimal idMetodo) {
        String textoTipoPromocion;

        switch (idMetodo.intValueExact()) {
            case 1:
                textoTipoPromocion = Constantes.TIPO_PROMOCION_PRUEBAS_ALEGATOS;
                break;
            case 2:
                textoTipoPromocion = Constantes.TIPO_PROMOCION_DOCUMENTACION_REQUERIDA;
                break;
            case Constantes.ENTERO_TRES:
                textoTipoPromocion = Constantes.TIPO_PROMOCION_PRUEBAS_ALEGATOS;
                break;
            case Constantes.ENTERO_CUATRO:
                textoTipoPromocion = Constantes.TIPO_PROMOCION_PRUEBAS_ALEGATOS;
                break;
            default:
                textoTipoPromocion = Constantes.TIPO_PROMOCION_DOCUMENTACION_REQUERIDA;
                break;
        }

        return textoTipoPromocion;
    }

    public boolean periodoMaximoAlcanzadoOrdenContribuyente(final Date fecha, final int diasHabiles) {
        WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
        this.festivosService = (FestivosService) applicationContext.getBean(SERVICE);

        return DateUtil.periodoMaximoAlcanzado(fecha, diasHabiles,
                festivosService.getListaDiasFestivos(fecha, diasHabiles));

    }

    public List<FececFeriados> getTodosLosDiasFestivos() {
        WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
        this.festivosService = (FestivosService) applicationContext.getBean(SERVICE);

        List<FececFeriados> listaFeriados = null;

        listaFeriados = festivosService.getTodosDiasFestivos();

        return listaFeriados;
    }

    public FececAuditor getAuditorLogueado() {
        WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
        this.auditorService = (AuditorService) applicationContext.getBean("auditorService");

        return auditorService.getAuditor(this.getRFCSession());

    }

    public String getContextoAplicativo() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        return externalContext.getRequestContextPath() + "/resources/applet/AppletFirmaM.jar";
    }

    public String getRfcContribuyente(final String rfcContribuyente, final String componenteForm) {

        String nombreContribuyente = null;

        try {
            nombreContribuyente = idcService.obtenerInformacionContribuyente(rfcContribuyente, secciones)
                    .getIdentificacion().getNombre();
            /**
             * getNombreContribuyente(rfcContribuyente,
             */
            if (nombreContribuyente == null) {
                FacesUtil.addErrorMessage(componenteForm, "No se encuentra registrada informaci\u00f3n para el RFC: "
                        + rfcContribuyente + "; favor de verificar.");
            }
        } catch (Exception e) {
            logger.error("No se pudo obtener la informacion del contribuyente [{}]", e.getCause());
            FacesUtil.addErrorMessage(componenteForm,
                    "Se presento un problema al cargar la informaci\u00f3n del contribuyente");
        }

        return nombreContribuyente;
    }

    public FecetContribuyente getContribuyenteIDC(final String rfcContribuyente, final String componenteForm) {
        IdCInterno contribuyenteIDC;
        FecetContribuyente contribuyente = new FecetContribuyente();

        try {

            contribuyenteIDC = idcService.obtenerInformacionContribuyente(rfcContribuyente, secciones);

            if (contribuyenteIDC.getIdentificacion() == null) {
                FacesUtil.addErrorMessage(componenteForm, "No se encuentra registrada informaci\u00f3n para el RFC: "
                        + rfcContribuyente + "; favor de verificar.");
                contribuyente.setRfc(rfcContribuyente.toUpperCase());
            } else {
                String nombreCompleto = contribuyenteIDC.getIdentificacion().getNombre();
                String apellidoPaterno = contribuyenteIDC.getIdentificacion().getAp_Paterno();
                String apellidoMaterno = contribuyenteIDC.getIdentificacion().getAp_Materno();

                if (nombreCompleto == null && apellidoPaterno == null && apellidoMaterno == null) {
                    contribuyente.setNombre("Sin nombre definido");
                } else {
                    contribuyente.setNombre(nombreCompleto + " " + apellidoPaterno + " " + apellidoMaterno);
                }

                contribuyente.setRfc(rfcContribuyente.toUpperCase());
                if (contribuyenteIDC.getIdentificacion() != null) {
                    contribuyente.setSituacion(contribuyenteIDC.getIdentificacion().getD_Sit_Cont().trim());
                    contribuyente.setDomicilioFiscal(generaDireccionContribuyente(contribuyenteIDC.getUbicacion()));
                    contribuyente.setSituacionDomicilio(contribuyenteIDC.getIdentificacion().getD_Sit_Dom().trim());

                    contribuyente.setTipo(contribuyenteIDC.getIdentificacion().getT_persona().trim().equals("M")
                            ? "PERSONA MORAL" : "PERSONA FISICA");
                }
                contribuyente.setActividadPreponderante(contribuyenteIDC.getActividades() != null
                        ? contribuyenteIDC.getActividades().get(0).getD_Actividad().trim()
                        : "Sin actividad preponderante");
                contribuyente.setEntidad((contribuyenteIDC.getUbicacion() != null)
                        ? contribuyenteIDC.getUbicacion().getD_Ent_Fed() : "Sin Entidad");
                contribuyente.setRegimen(contribuyenteIDC.getRegimenes() != null
                        ? contribuyenteIDC.getRegimenes().get(0).getD_Regimen().trim() : "Sin regimen");
            }
        } catch (Exception e) {
            logger.error("Insumos IDC? " + e.toString());
            for (StackTraceElement er : e.getStackTrace()) {
                logger.error("Insumos IDC? " + er.getLineNumber() + " " + e.getClass());
            }

        }

        return contribuyente;
    }

    public ValidaMediosContactoBO validaMediosContacto(String rfc) {
        ValidaMediosContactoBO validaMediosContactoBO = new ValidaMediosContactoBO();
        validaMediosContactoBO.setRfc(rfc);

        validaMediosContactoBO = getConsultaMediosContactoService().validaMediosContacto(validaMediosContactoBO);

        return validaMediosContactoBO;
    }

    public String generaDireccionContribuyente(final Ubicacion ubicacion) {
        StringBuilder direccion = new StringBuilder();
        if (ubicacion != null) {
            direccion.append((ubicacion.getCalle() != null) ? ubicacion.getCalle().trim() : "").append(" # ");
            direccion.append((ubicacion.getN_Exterior() != null) ? ubicacion.getN_Exterior().trim() : "").append(", ");
            direccion.append((ubicacion.getD_Colonia() != null) ? ubicacion.getD_Colonia().trim() : "").append(", ");
            direccion.append((ubicacion.getD_Municipio() != null) ? ubicacion.getD_Municipio().trim() : "")
                    .append(", ");
            direccion.append((ubicacion.getD_Ent_Fed() != null) ? ubicacion.getD_Ent_Fed().trim() : "")
                    .append(", C.P. ");
            direccion.append((ubicacion.getCp() != null) ? ubicacion.getCp().trim() : "");
        }
        return direccion.toString();
    }

    public void enviarNotificacionInterna(Map<String, String> data, ReportType reportType, Set<String> destinatarios) {
        try {

            Calendar date = new GregorianCalendar();
            data.put(Common.YEAR.toString(), "" + date.get(Calendar.YEAR));
            data.put(Common.DAY.toString(), "" + date.get(Calendar.DAY_OF_MONTH));
            data.put(Common.MONTH.toString(), NombreMes.obtenerNombre(date.get(Calendar.MONTH)));
            data.put(Common.HOUR.toString(), new SimpleDateFormat("HH:mm").format(date.getTime()));
            getNotificacionService().enviarNotificacion(data, reportType, destinatarios);

        } catch (BusinessException ex) {
            logger.error("Error al enviar el correo [{}]", ex.getCause());
        }
    }

    private Boolean validaTamanoArchivo(final UploadedFile archivo) {
        if (archivo.getSize() > 0L && archivo.getSize() <= N_4196000L) {
            return true;
        } else {
            FacesUtil.addErrorMessage(null, "Error al cargar el archivo.", "Debe ser mayor a 1 KB y menor que 4 MB.");
        }

        return false;
    }

    public Boolean validaArchivoCargaInsumoPropuesta(final UploadedFile archivo) {
        if (archivo.getFileName().endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_PDF)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_EXCEL_DESPUES_2007)) {

            if (validaTamanoArchivo(archivo)) {
                return true;
            }
        } else {
            FacesUtil.addErrorMessage(null, ERROR_ARCHIVO_INVALIDO,
                    "Solo se aceptan archivos WORD, EXCEL o PDF versi\u00f3n 2007 o superior");
        }

        return false;
    }

    public Boolean validaArchivoCarga(final UploadedFile archivo, final Long tipoDocumento) {
        if (tipoDocumento.equals(1L)) {
            if (archivo.getFileName().endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)) {
                return validaTamanoArchivo(archivo);
            } else {
                FacesUtil.addErrorMessage(null, ERROR_ARCHIVO_INVALIDO,
                        "Solo se aceptan archivos WORD versi\u00f3n 2007 o superior");
            }
        } else if (tipoDocumento.equals(2L)) {
            if (archivo.getFileName().endsWith(Constantes.ARCHIVO_PDF)) {
                return validaTamanoArchivo(archivo);
            } else {
                FacesUtil.addErrorMessage(null, ERROR_ARCHIVO_INVALIDO, "Solo se aceptan archivos pdf");
            }
        }

        return false;
    }

    @Override
    public StreamedContent getDescargaArchivo(final String path, final String nombreDescarga) {
        StreamedContent archivo = null;
        try {
            archivo = new DefaultStreamedContent(new FileInputStream(CargaArchivoUtilOrdenes.limpiarPathArchivo(path)),
                    CargaArchivoUtilOrdenes.obtenContentTypeArchivo(nombreDescarga),
                    CargaArchivoUtilOrdenes.aplicarCodificacionTexto(nombreDescarga));
        } catch (FileNotFoundException e) {
            logger.error("No se pudo descargar el archivo. [{}]", e);
            addErrorMessage(null, "No se encontr\u00f3 el documento seleccionado", "");
        }

        return archivo;
    }

    public List<String> verificarAntecedentes(final String rfc, final Date periodoInicial, final Date periodoFinal) {
        FecetPropuesta propuesta = new FecetPropuesta();
        propuesta.setFechaInicioPeriodo(periodoInicial);
        propuesta.setFechaFinPeriodo(periodoFinal);
        return verificarAntecedentesPropuestas(rfc, propuesta);
    }

    public List<String> verificarAntecedentes(final String rfc, final Date periodoInicial, final Date periodoFinal,
            final BigDecimal subprograma, final BigDecimal unidadAdministrativa) {
        FecetPropuesta propuesta = new FecetPropuesta();
        propuesta.setFechaInicioPeriodo(periodoInicial);
        propuesta.setFechaFinPeriodo(periodoFinal);
        propuesta.setIdSubprograma(subprograma);
        propuesta.setIdArace(unidadAdministrativa);
        return verificarAntecedentesPropuestas(rfc, propuesta);
    }

    public List<String> verificarAntecedentes(final String rfc, final Date periodoInicial, final Date periodoFinal,
            final BigDecimal subprograma, final BigDecimal unidadAdministrativa, final BigDecimal idInsumo) {
        FecetPropuesta propuesta = new FecetPropuesta();
        propuesta.setFechaInicioPeriodo(periodoInicial);
        propuesta.setFechaFinPeriodo(periodoFinal);
        propuesta.setIdSubprograma(subprograma);
        propuesta.setIdArace(unidadAdministrativa);
        propuesta.setIdInsumo(idInsumo);
        return verificarAntecedentesInsumos(rfc, propuesta);
    }

    public List<String> verificarAntecedentesPropuestas(String rfc, FecetPropuesta propuesta) {
        List<String> listaDatosEncontrados = new ArrayList<String>();
        List<FecetPropuesta> propuestasSICSEP = null;
        List<FecetPropuesta> propuestasSUIEFI = null;
        List<FecetPropuesta> propuestasAGAFF = null;
        List<FecetPropuesta> propuestasAGACE = null;

        String mensaje;

        Date periodoInicial = propuesta.getFechaInicioPeriodo();
        Date periodoFinal = propuesta.getFechaFinPeriodo();
        try {
            propuestasSICSEP = consultaAntecedentesService.consultaSICSEP(rfc, periodoInicial, periodoFinal);
            if (propuestasSICSEP != null && propuestasSICSEP.size() > 0) {
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_ANTECEDENTES);
                listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.SICSEP));
            }
        } catch (NegocioException e) {
            mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES);
            listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.SICSEP));
        }

        try {
            propuestasSUIEFI = consultaAntecedentesService.consultaSUIEFI(rfc, periodoInicial, periodoFinal);
            if (propuestasSUIEFI != null && propuestasSUIEFI.size() > 0) {
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_ANTECEDENTES);
                listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.SIUIEFI));
            }
        } catch (NegocioException e) {
            mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES);
            listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.SIUIEFI));
        }

        try {
            propuestasAGAFF = consultaAntecedentesService.consultaAGAFF(rfc, periodoInicial, periodoFinal);
            if (propuestasAGAFF != null && propuestasAGAFF.size() > 0) {
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_ANTECEDENTES);
                listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.AGAFF_DESCRIPCION));
            }
        } catch (Exception e) {
            mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES);
            listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.AGAFF_DESCRIPCION));
        }

        try {
            propuestasAGACE = consultaAntecedentesService.consultaAGACEPropuestasPeriodoExacto(rfc, propuesta);
            if (propuestasAGACE != null && !propuestasAGACE.isEmpty()) {
                FecetPropuesta propuestaBD = propuestasAGACE.get(0);
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_PROPUESTAS_ANTECEDENTES);
                listaDatosEncontrados.add(String.format(mensaje, propuestaBD.getIdRegistro()));
            }

            List<FecetInsumo> insumos = consultaAntecedentesService.consultaAGACEInsumosPeriodoExacto(rfc, propuesta);
            if (insumos != null && !insumos.isEmpty()) {
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_INSUMOS_ANTECEDENTES);
                listaDatosEncontrados.add(mensaje);
            }

        } catch (NegocioException e) {
            mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES);
            listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.AGACE_DESCRIPCION));
        }

        return listaDatosEncontrados;
    }

    public List<String> verificarAntecedentesInsumos(String rfc, FecetPropuesta propuesta) {
        List<String> listaDatosEncontrados = new ArrayList<String>();
        List<FecetPropuesta> propuestasSICSEP = null;
        List<FecetPropuesta> propuestasSUIEFI = null;
        List<FecetPropuesta> propuestasAGAFF = null;
        List<FecetPropuesta> propuestasAGACE = null;

        String mensaje;

        Date periodoInicial = propuesta.getFechaInicioPeriodo();
        Date periodoFinal = propuesta.getFechaFinPeriodo();
        try {
            propuestasSICSEP = consultaAntecedentesInsumoService.consultaSICSEP(rfc, periodoInicial, periodoFinal);
            if (propuestasSICSEP != null && propuestasSICSEP.size() > 0) {
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_ANTECEDENTES);
                listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.SICSEP));
            }
        } catch (NegocioException e) {
            mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES);
            listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.SICSEP));
        }

        try {
            propuestasSUIEFI = consultaAntecedentesInsumoService.consultaSUIEFI(rfc, periodoInicial, periodoFinal);
            if (propuestasSUIEFI != null && propuestasSUIEFI.size() > 0) {
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_ANTECEDENTES);
                listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.SIUIEFI));
            }
        } catch (NegocioException e) {
            mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES);
            listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.SIUIEFI));
        }

        try {
            propuestasAGAFF = consultaAntecedentesInsumoService.consultaAGAFF(rfc, periodoInicial, periodoFinal);
            if (propuestasAGAFF != null && propuestasAGAFF.size() > 0) {
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_ANTECEDENTES);
                listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.AGAFF_DESCRIPCION));
            }
        } catch (Exception e) {
            mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES);
            listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.AGAFF_DESCRIPCION));
        }

        try {
            propuestasAGACE = consultaAntecedentesInsumoService.consultaAGACEPropuestasPeriodoExacto(rfc, propuesta);
            if (propuestasAGACE != null && !propuestasAGACE.isEmpty()) {
                FecetPropuesta propuestaBD = propuestasAGACE.get(0);
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_PROPUESTAS_ANTECEDENTES);
                listaDatosEncontrados.add(String.format(mensaje, propuestaBD.getIdRegistro()));
            }

            List<FecetInsumo> insumos = consultaAntecedentesInsumoService.consultaAGACEInsumosPeriodoExacto(rfc,
                    propuesta);
            if (insumos != null && !insumos.isEmpty()) {
                mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_INSUMOS_ANTECEDENTES);
                listaDatosEncontrados.add(mensaje);
            }

        } catch (NegocioException e) {
            mensaje = FacesUtil.getMessageResourceString(LABEL_REGISTROS_CONSULTA_NO_ANTECEDENTES);
            listaDatosEncontrados.add(String.format(mensaje, ConstantesPropuestas.AGACE_DESCRIPCION));
        }

        return listaDatosEncontrados;
    }

    public List<String> validaAntecedentes(final String rfc, final Date fechaInicial, final Date fechaFinal,
            final BigDecimal estatus) {

        WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
        this.consultaAntecedentesService = (ConsultaAntecedentesPropuestasService) applicationContext
                .getBean("consultaAntecedentesService");

        List<String> reporte = new ArrayList<String>();

        try {
            List<FecetPropuesta> antecedentesAgace = consultaAntecedentesService.consultaAGACE(rfc, fechaInicial,
                    fechaFinal);
            if (antecedentesAgace != null && antecedentesAgace.size() > 0) {
                reporte.add(LEYENDA + antecedentesAgace.size() + " antecedentes en el sistema AGACE.");
            }

        } catch (NegocioException e) {
            reporte.add("Existio un error al consultar los antecedentes en AGACE.");
        }

        try {
            List<FecetPropuesta> antecedentesAgaff = consultaAntecedentesService.consultaAGAFF(rfc, fechaInicial,
                    fechaFinal);
            if (antecedentesAgaff != null && antecedentesAgaff.size() > 0) {
                reporte.add(LEYENDA + antecedentesAgaff.size() + " antecedentes en el sistema AGAFF.");
            }

        } catch (NegocioException e) {
            reporte.add("Existio un error al consultar los antecedentes en AGAFF.");
        }

        try {
            List<FecetPropuesta> antecedentesSicsep = consultaAntecedentesService.consultaSICSEP(rfc, fechaInicial,
                    fechaFinal);
            if (antecedentesSicsep != null && antecedentesSicsep.size() > 0) {
                reporte.add(LEYENDA + antecedentesSicsep.size() + " antecedentes en el sistema SICSEP.");
            }
        } catch (NegocioException e) {
            reporte.add("Existio un error al consultar los antecedentes en SICSEP.");
        }

        try {
            List<FecetPropuesta> antecedentesSuiefi = consultaAntecedentesService.consultaSUIEFI(rfc, fechaInicial,
                    fechaFinal);
            if (antecedentesSuiefi != null && antecedentesSuiefi.size() > 0) {
                reporte.add(LEYENDA + antecedentesSuiefi.size() + " antecedentes en el sistema SUIEFI.");
            }
        } catch (NegocioException e) {
            reporte.add("Existio un error al consultar los antecedentes en SUIEFI.");
        }

        return reporte;
    }

    public Boolean validaArchivoCargaWordExcelPdf(final UploadedFile archivo) {
        if (archivo.getFileName().endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_PDF)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_EXCEL_DESPUES_2007)) {

            if (validaTamanoArchivo(archivo)) {
                return true;
            }
        } else {
            addErrorMessage(null, ERROR_ARCHIVO_INVALIDO,
                    "Solo se aceptan archivos WORD, EXCEL o PDF version 2007 o superior");
        }

        return false;
    }

    public Boolean validaArchivoCargaWordExcelPdfIdMessage(final UploadedFile archivo, String idMessage) {
        if (archivo.getFileName().endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_PDF)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_EXCEL_DESPUES_2007)) {

            if (validaTamanoArchivoIdMessage(archivo, idMessage)) {
                return true;
            }
        } else {
            addErrorMessage(idMessage, ERROR_ARCHIVO_INVALIDO,
                    "Solo se aceptan archivos WORD, EXCEL o PDF version 2007 o superior");
        }

        return false;
    }

    private Boolean validaTamanoArchivoIdMessage(final UploadedFile archivo, String idMessage) {
        if (archivo.getSize() > 0L && archivo.getSize() <= N_4196000L) {
            return true;
        } else {
            addErrorMessage(idMessage, "Error al cargar el archivo.", "Debe ser mayor a 1 KB y menor que 4 MB.");
        }
        return false;
    }

    public Boolean validaArchivoCargaWord(final UploadedFile archivo) {
        if (archivo.getFileName().endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)) {

            if (validaTamanoArchivo(archivo)) {
                return true;
            }
        } else {
            addErrorMessage(null, "Solo se aceptan archivos WORD version 2007 o superior", null);
        }

        return false;
    }

    public Boolean validaArchivoDuplicadoAnexosOficio(final List<FecetOficioAnexos> listaAnexos,
            final String nombreArchivo) {
        Boolean archivoDuplicado = false;
        for (FecetOficioAnexos documento : listaAnexos) {

            if (documento.getNombreArchivo().equals(nombreArchivo)) {
                archivoDuplicado = true;
                addErrorMessage(null, ERROR_ARCHIVO_REPETIDO, "");
                break;
            }
        }

        return archivoDuplicado;
    }

    public boolean duplicidadPapelTrabajo(PapelesTrabajo papel, List<DocumentoOrdenModel> listaPapelesTrabajo) {
        for (DocumentoOrdenModel dto : listaPapelesTrabajo) {
            if (dto.getPapelesTrabajo().getNombreArchivo().equals(papel.getNombreArchivo())) {
                return true;
            }
        }
        return false;
    }

    public Boolean validaArchivoDuplicadoAnexosProrrogaOrden(final List<FecetAnexosProrrogaOrden> listaAnexos,
            final String nombreArchivo) {
        Boolean archivoDuplicado = false;
        for (FecetAnexosProrrogaOrden documento : listaAnexos) {

            if (documento.getNombreArchivo().equals(nombreArchivo)) {
                archivoDuplicado = true;
                addErrorMessage(null, ERROR_ARCHIVO_REPETIDO, "");
                break;
            }
        }

        return archivoDuplicado;
    }

    public Boolean validaArchivoDuplicadoAnexosPruebasPericiales(final List<FecetAnexoPruebasPericiales> listaAnexos,
            final String nombreArchivo) {
        Boolean archivoDuplicado = false;
        for (FecetAnexoPruebasPericiales documento : listaAnexos) {

            if (documento.getNombreArchivo().equals(nombreArchivo)) {
                archivoDuplicado = true;
                addErrorMessage(null, ERROR_ARCHIVO_REPETIDO, "");
                break;
            }
        }

        return archivoDuplicado;
    }

    public Boolean validaArchivoResolucionProrrogaOrden(final List<FecetAnexosProrrogaOrden> listaAnexos,
            final String nombreArchivo) {
        Boolean archivoDuplicado = false;
        if (listaAnexos.size() < 1) {
            for (FecetAnexosProrrogaOrden documento : listaAnexos) {

                if (documento.getNombreArchivo().equals(nombreArchivo)) {
                    archivoDuplicado = true;
                    addErrorMessage(null, ERROR_ARCHIVO_REPETIDO, "");
                    break;
                }
            }
        } else {
            archivoDuplicado = true;
            addErrorMessage(null, ERROR_ARCHIVO_CARGADO, "");
        }
        return archivoDuplicado;
    }

    public Boolean validaArchivoResolucionPruebasPericiales(final List<FecetAnexoPruebasPericiales> listaAnexos,
            final String nombreArchivo) {
        Boolean archivoDuplicado = false;
        if (listaAnexos.size() < 1) {
            for (FecetAnexoPruebasPericiales documento : listaAnexos) {

                if (documento.getNombreArchivo().equals(nombreArchivo)) {
                    archivoDuplicado = true;
                    addErrorMessage(null, ERROR_ARCHIVO_REPETIDO, "");
                    break;
                }
            }
        } else {
            archivoDuplicado = true;
            addErrorMessage(null, ERROR_ARCHIVO_CARGADO, "");
        }
        return archivoDuplicado;
    }

    public Boolean validaArchivoResolucionProrrogaOficio(final List<FecetAnexosProrrogaOficio> listaAnexos,
            final String nombreArchivo) {
        Boolean archivoDuplicado = false;
        if (listaAnexos.size() < 1) {
            for (FecetAnexosProrrogaOficio documento : listaAnexos) {

                if (documento.getNombreArchivo().equals(nombreArchivo)) {
                    archivoDuplicado = true;
                    addErrorMessage(null, ERROR_ARCHIVO_REPETIDO, "");
                    break;
                }
            }
        } else {
            archivoDuplicado = true;
            addErrorMessage(null, ERROR_ARCHIVO_CARGADO, "");
        }
        return archivoDuplicado;
    }

    public Boolean validaArchivoDuplicadoAnexosProrrogaOficio(final List<FecetAnexosProrrogaOficio> listaAnexos,
            final String nombreArchivo) {
        Boolean archivoDuplicado = false;
        for (FecetAnexosProrrogaOficio documento : listaAnexos) {

            if (documento.getNombreArchivo().equals(nombreArchivo)) {
                archivoDuplicado = true;
                addErrorMessage(null, ERROR_ARCHIVO_REPETIDO, "");
                break;
            }
        }

        return archivoDuplicado;
    }

    public void limpiaFiltros(final String componente) {
        try {
            DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(componente);
            dataTable.setFilteredValue(null);
            dataTable.reset();
        } catch (Exception e) {
            logger.error("No existe el componente: [{}]", componente);
        }

    }

    public void setConsultaMediosContactoService(ConsultaMediosContactoService consultaMediosContactoService) {
        this.consultaMediosContactoService = consultaMediosContactoService;
    }

    public ConsultaMediosContactoService getConsultaMediosContactoService() {
        return consultaMediosContactoService;
    }

    public FestivosService getFestivosService() {
        return festivosService;
    }

    public void setFestivosService(FestivosService festivosService) {
        this.festivosService = festivosService;
    }

    public AuditorService getAuditorService() {
        return auditorService;
    }

    public void setAuditorService(AuditorService auditorService) {
        this.auditorService = auditorService;
    }

    public IDCService getIdcService() {
        return idcService;
    }

    public void setIdcService(IDCService idcService) {
        this.idcService = idcService;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    public FirmanteService getFirmanteService() {
        return firmanteService;
    }

    public void setFirmanteService(FirmanteService firmanteService) {
        this.firmanteService = firmanteService;
    }

    public SubAdministradorService getSubAdministradorService() {
        return subAdministradorService;
    }

    public void setSubAdministradorService(SubAdministradorService subAdministradorService) {
        this.subAdministradorService = subAdministradorService;
    }

    public CargarOrdenService getCargarOrdenService() {
        return cargarOrdenService;
    }

    public void setCargarOrdenService(CargarOrdenService cargarOrdenService) {
        this.cargarOrdenService = cargarOrdenService;
    }

    public void setConsultaAntecedentesService(ConsultaAntecedentesPropuestasService consultaAntecedentesService) {
        this.consultaAntecedentesService = consultaAntecedentesService;
    }

    public ConsultaAntecedentesPropuestasService getConsultaAntecedentesService() {
        return consultaAntecedentesService;
    }

    public ConsultaAntecedentesInsumoService getConsultaAntecedentesInsumoService() {
        return consultaAntecedentesInsumoService;
    }

    public void setConsultaAntecedentesInsumoService(
            ConsultaAntecedentesInsumoService consultaAntecedentesInsumoService) {
        this.consultaAntecedentesInsumoService = consultaAntecedentesInsumoService;
    }
}
