package mx.gob.sat.siat.feagace.vista.propuestas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstados;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaPropuestas;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusConsultaPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.helper.ConsultaPropuestasHelper;
import mx.gob.sat.siat.feagace.negocio.propuestas.ConsultaPropuestasService;

@ViewScoped
@ManagedBean(name = "consultaPropuestasMB")
public class ConsultaPropuestasMB extends BaseManagedBean {

    /**
     *
     */
    private static final long serialVersionUID = -3418116841289129648L;
    /**
     *
     */

    private List<ConsultaPropuestas> listaPropuestas;
    private List<TipoFechasComiteEnum> listaFechasComiteEnum;
    private List<TipoFechasComiteEnum> listaFechasComiteEnumBusqueda;
    private List<AraceDTO> listaUnidadAdministrativa;
    private List<FecetDocExpediente> listaDocumento;
    private List<FececEstados> listaEntidades;
    private List<TipoEstatusConsultaPropuestasEnum> listaTipoEstatus = new ArrayList<TipoEstatusConsultaPropuestasEnum>(
            EnumSet.allOf(TipoEstatusConsultaPropuestasEnum.class));

    private String rfc;
    private String entidad;
    private String idRegistro;
    private String idEstatusPropuestas;
    private String idTipoFechaComite;
    private String idUnidadAdministrativa;
    private String mensajeError;
    private Date fechaInicio;
    private Date fechaFin;

    private StreamedContent descargarArchivo;
    private StreamedContent xlsReporte;
    private EmpleadoDTO empleado;
    private FecetDocExpediente documento;
    private boolean mostrarExportar;
    private boolean esEmpleadoCentral;

    @ManagedProperty(value = "#{consultaPropuestasService}")
    private transient ConsultaPropuestasService consultaPropuestasService;

    @ManagedProperty(value = "#{consultaPropuestasHelper}")
    private ConsultaPropuestasHelper helper;

    @PostConstruct
    public void init() {

        listaPropuestas = Collections.<ConsultaPropuestas>emptyList();

        try {
            empleado = consultaPropuestasService.consultaEmpleado(getRFCSession());
            limpiaCampos();

            if (helper.listaIdAraceConsultaPropuestas().contains(
                    new BigDecimal(getEmpleado().getDetalleEmpleado().get(0).getCentral().getIdArace().intValue()))) {
                if (!getEmpleado().getDetalleEmpleado().get(0).getCentral().getIdArace()
                        .equals(Constantes.ACPPCE.intValue())) {
                    setListaEntidades(consultaPropuestasService.construyeComboEntidad(getEmpleado()));
                    setEsEmpleadoCentral(false);
                } else {
                    setListaEntidades(consultaPropuestasService.traeTodasLasEntidades());
                    setListaUnidadAdministrativa(consultaPropuestasService.getFececUnidadesAdministrativas());
                    setEsEmpleadoCentral(true);
                }
            } else {
                informeErrorSession(new NegocioException("El usuario logueado no pertenece a ningun ARACE"));
            }

            getListaPropuestas().clear();
            setListaPropuestas(
                    getConsultaPropuestasService().cargarPropuestasPorArace(empleado, isEsEmpleadoCentral()));
            setListaFechasComiteEnum(Arrays.asList(TipoFechasComiteEnum.values()));
            setListaFechasComiteEnumBusqueda(helper.obtenerListaFechasComiteSegunUsuario(getEmpleado()));
            if (!listaPropuestas.isEmpty()) {
                setMostrarExportar(false);
            }
        } catch (EmpleadoServiceException e) {

            informeErrorSession(new NegocioException("El usuario logueado no pertenece a ningun ARACE"));

        } catch (NegocioException e) {
            informeErrorSession(new NegocioException("El usuario logueado no pertenece a ningun ARACE"));

        }

    }

    public void buscarPropuestas() {

        getListaPropuestas().clear();
        setListaPropuestas(getConsultaPropuestasService().cargarPropuestasPorArace(getEmpleado(), isEsEmpleadoCentral(),
                getEntidad(), getRfc(), getIdRegistro(), getIdEstatusPropuestas(), getIdUnidadAdministrativa(),
                getIdTipoFechaComite(), getFechaInicio(), getFechaFin()));
        if (!listaPropuestas.isEmpty()) {
            setMostrarExportar(false);
        } else {
            setMostrarExportar(true);
        }
        validaFecha();
        limpiarFiltros();

    }

    public void visualizarDocumentos() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogoDocumentos').show();");
    }

    public void validaFecha() {
        if (getFechaInicio() == null && getFechaFin() == null
                && getIdTipoFechaComite().equalsIgnoreCase(Constantes.COMBO_SELECCIONA_CADENA)) {
            return;
        } else if (!getIdTipoFechaComite().equalsIgnoreCase(Constantes.COMBO_SELECCIONA_CADENA)
                && (getFechaInicio() == null || getFechaFin() == null)) {
            addErrorMessage(null, "Sí selecciona el filtro Fecha de Comité debe especificar ambas fechas. ",
                    "");
        } else if (getFechaInicio() != null && getFechaFin() != null) {
            if (getFechaFin().before(getFechaInicio())) {
                addErrorMessage(null, "La Fecha informe a comité no puede ser mayor a la Fecha fin.", "");
            } else if (getIdTipoFechaComite().equalsIgnoreCase(Constantes.COMBO_SELECCIONA_CADENA)) {
                addErrorMessage(null,
                        "Debe seleccionar la \"Fecha de Comité\" para la búsqueda por rango de fechas.", "");
            }
        }
    }

    public StreamedContent getDescargaArchivo(final String path, final String nombreDescarga) {
        StreamedContent archivo = null;
        try {
            archivo = new DefaultStreamedContent(new FileInputStream(PropuestaVistaCargaArchivosUtil.limpiarPathArchivo(path)),
                    PropuestaVistaCargaArchivosUtil.obtenContentTypeArchivo(nombreDescarga),
                    PropuestaVistaCargaArchivosUtil.aplicarCodificacionTexto(nombreDescarga));
        } catch (FileNotFoundException e) {
            logger.error("No se pudo descargar el archivo. [{}]", e);
            addErrorMessage(null, "No se encontro el documento seleccionado", "");
        }

        return archivo;
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

    public void limpiaCampos() {
        listaPropuestas.clear();
        setListaFechasComiteEnum(new ArrayList<TipoFechasComiteEnum>());
        setListaUnidadAdministrativa(new ArrayList<AraceDTO>());
        setListaDocumento(new ArrayList<FecetDocExpediente>());
        setListaEntidades(new ArrayList<FececEstados>());
        setEntidad(Constantes.COMBO_SELECCIONA_CADENA);
        setIdEstatusPropuestas(Constantes.COMBO_SELECCIONA_CADENA);
        setIdTipoFechaComite(Constantes.COMBO_SELECCIONA_CADENA);
        setIdUnidadAdministrativa(Constantes.COMBO_SELECCIONA_CADENA);
        setMostrarExportar(true);
        setRfc("");
        setIdRegistro("");
        setMensajeError("");
        setFechaInicio(null);
        setFechaFin(null);
    }

    public void limpiaCamposBusqueda() {
        setEntidad(Constantes.COMBO_SELECCIONA_CADENA);
        setIdEstatusPropuestas(Constantes.COMBO_SELECCIONA_CADENA);
        setIdTipoFechaComite(Constantes.COMBO_SELECCIONA_CADENA);
        setIdUnidadAdministrativa(Constantes.COMBO_SELECCIONA_CADENA);
        setRfc("");
        setIdRegistro("");
        setMensajeError("");
        setFechaInicio(null);
        setFechaFin(null);
        getListaPropuestas().clear();
        limpiarFiltros();

    }
    
    public void limpiarFiltros() {
        try {
            DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(":formConsultaPropuestas:tablaConsultaPropuestas");
            dataTable.reset();
            if (!dataTable.getFilters().isEmpty()) {
                logger.info("dataTable.getFilters().isEmpty() :" + dataTable.getFilters().isEmpty());
                dataTable.getFilteredValue().clear();
                dataTable.setFilteredValue(null);
                dataTable.setFilters(null);
                dataTable.setFilterMetadata(null);
                dataTable.reset();
            }
        } catch (Exception e) {
            logger.error("no pudo limpiar" + e.getMessage());
        }

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.reset(":formConsultaPropuestas:tablaConsultaPropuestas");
    }

    public void setListaPropuestas(List<ConsultaPropuestas> listaPropuestas) {
        this.listaPropuestas = listaPropuestas;
    }

    public List<ConsultaPropuestas> getListaPropuestas() {
        return listaPropuestas;
    }

    public void setDescargarArchivo(StreamedContent descargarArchivo) {
        this.descargarArchivo = descargarArchivo;
    }

    public StreamedContent getDescargarArchivo() {
        StringBuilder ruta = new StringBuilder();
        ruta.append(documento.getRutaArchivo());

        File file;
        file = new File(ruta.toString());
        StringBuilder nombre = new StringBuilder(file.getName());
        descargarArchivo = super.getDescargaArchivo(ruta.toString(), nombre.toString());
        return descargarArchivo;
    }

    public void setMensajeError(String mensaje) {
        this.mensajeError = mensaje;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setXlsReporte(StreamedContent xlsReporte) {
        this.xlsReporte = xlsReporte;
    }

    public StreamedContent getXlsReporte() {
        File file = null;
        HSSFWorkbook workbook = null;
        workbook = consultaPropuestasService.exportarConsultaPropuestas(listaPropuestas);
        FileOutputStream out = null;
        try {
            file = File.createTempFile("reporteGerencial", "xls");
            out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            xlsReporte = new DefaultStreamedContent(new FileInputStream(file), "application/xls",
                    "consultaPropuestas.xls");
            file.deleteOnExit();
        } catch (IOException e) {
            logger.error("Error: ", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("No se pudo limpiar la memoria", e);
                }
            }
        }
        return xlsReporte;
    }

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

    public void setDocumento(FecetDocExpediente documento) {
        this.documento = documento;
    }

    public FecetDocExpediente getDocumento() {
        return documento;
    }

    public void setListaDocumento(List<FecetDocExpediente> listaDocumento) {
        this.listaDocumento = listaDocumento;
    }

    public List<FecetDocExpediente> getListaDocumento() {
        return listaDocumento;
    }

    public List<TipoEstatusConsultaPropuestasEnum> getListaTipoEstatus() {
        return listaTipoEstatus;
    }

    public void setListaTipoEstatus(List<TipoEstatusConsultaPropuestasEnum> listaTipoEstatus) {
        this.listaTipoEstatus = listaTipoEstatus;
    }

    public List<TipoFechasComiteEnum> getListaFechasComiteEnum() {
        return listaFechasComiteEnum;
    }

    public void setListaFechasComiteEnum(List<TipoFechasComiteEnum> listaFechasComiteEnum) {
        this.listaFechasComiteEnum = listaFechasComiteEnum;
    }

    public List<AraceDTO> getListaUnidadAdministrativa() {
        return listaUnidadAdministrativa;
    }

    public void setListaUnidadAdministrativa(List<AraceDTO> listaUnidadAdministrativa) {
        this.listaUnidadAdministrativa = listaUnidadAdministrativa;
    }

    public List<FececEstados> getListaEntidades() {
        return listaEntidades;
    }

    public void setListaEntidades(List<FececEstados> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdEstatusPropuestas() {
        return idEstatusPropuestas;
    }

    public void setIdEstatusPropuestas(String idEstatusPropuestas) {
        this.idEstatusPropuestas = idEstatusPropuestas;
    }

    public String getIdTipoFechaComite() {
        return idTipoFechaComite;
    }

    public void setIdTipoFechaComite(String idTipoFechaComite) {
        this.idTipoFechaComite = idTipoFechaComite;
    }

    public String getIdUnidadAdministrativa() {
        return idUnidadAdministrativa;
    }

    public void setIdUnidadAdministrativa(String idUnidadAdministrativa) {
        this.idUnidadAdministrativa = idUnidadAdministrativa;
    }

    public Date getFechaInicio() {
        if (fechaInicio != null) {
            return new Date(fechaInicio.getTime());
        } else {
            return null;
        }
    }

    public void setFechaInicio(Date fechaInicio) {
        if (fechaInicio != null) {
            this.fechaInicio = new Date(fechaInicio.getTime());
        } else {
            this.fechaInicio = null;
        }
    }

    public Date getFechaFin() {
        if (fechaFin != null) {
            return new Date(fechaFin.getTime());
        } else {
            return null;
        }
    }

    public void setFechaFin(Date fechaFin) {
        if (fechaFin != null) {
            this.fechaFin = new Date(fechaFin.getTime());
        } else {
            this.fechaFin = null;
        }
    }

    public boolean isMostrarExportar() {
        return mostrarExportar;
    }

    public void setMostrarExportar(boolean mostrarExportar) {
        this.mostrarExportar = mostrarExportar;
    }

    public ConsultaPropuestasService getConsultaPropuestasService() {
        return consultaPropuestasService;
    }

    public void setConsultaPropuestasService(ConsultaPropuestasService consultaPropuestasService) {
        this.consultaPropuestasService = consultaPropuestasService;
    }

    public ConsultaPropuestasHelper getHelper() {
        return helper;
    }

    public void setHelper(ConsultaPropuestasHelper helper) {
        this.helper = helper;
    }

    public boolean isEsEmpleadoCentral() {
        return esEmpleadoCentral;
    }

    public void setEsEmpleadoCentral(boolean esEmpleadoCentral) {
        this.esEmpleadoCentral = esEmpleadoCentral;
    }

    public List<TipoFechasComiteEnum> getListaFechasComiteEnumBusqueda() {
        return listaFechasComiteEnumBusqueda;
    }

    public void setListaFechasComiteEnumBusqueda(List<TipoFechasComiteEnum> listaFechasComiteEnumBusqueda) {
        this.listaFechasComiteEnumBusqueda = listaFechasComiteEnumBusqueda;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

}
