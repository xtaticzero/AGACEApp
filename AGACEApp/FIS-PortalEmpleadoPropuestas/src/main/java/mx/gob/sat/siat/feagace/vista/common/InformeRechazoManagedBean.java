package mx.gob.sat.siat.feagace.vista.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececActividadPreponderante;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.negocio.common.InformeRechazoService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilPropuestas;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public abstract class InformeRechazoManagedBean extends AccesoUsuarioMBAbstract {

    private static final long serialVersionUID = 2310672310636436539L;

    private String rfc;
    private List<String> listaEntidades;
    private List<FececActividadPreponderante> listaFececActividadPreponderante;
    private String idActividadPreponderante;
    private String idEntidad;
    private FececEmpleado empleado;
    private boolean mostrarExportar;
    private Date fechaInicio;
    private Date fechaFin;
    private String idRegistro;

    @ManagedProperty(value = "#{informeRechazoService}")
    private InformeRechazoService informeRechazoService;

    public void cargarComboEntidad(FececEmpleado empleado) {
        listaEntidades = getInformeRechazoService().construyeComboEntidad(empleado);
    }

    public void cargarComboActividadPreponderante() {
        listaFececActividadPreponderante = getInformeRechazoService().construyeComboActividadPreponderante();
    }

    public StreamedContent getDescargaArchivo(final String path,
            final String nombreDescarga) {
        StreamedContent archivo = null;
        try {
            archivo = new DefaultStreamedContent(new FileInputStream(
                    CargaArchivoUtilPropuestas.limpiarPathArchivo(path)),
                    CargaArchivoUtilPropuestas.obtenContentTypeArchivo(nombreDescarga),
                    CargaArchivoUtilPropuestas.aplicarCodificacionTexto(nombreDescarga));
        } catch (FileNotFoundException e) {
            logger.error("No se pudo descargar el archivo. [{}]", e);
            addErrorMessage(null,
                    "No se encontro el documento seleccionado", "");
        }

        return archivo;
    }

    public void informeErrorSession(Exception e) {
        try {
            HttpSession session;
            session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(true);

            session.setAttribute("mensaje", e);

            ServletContext dir = (ServletContext) FacesContext
                    .getCurrentInstance().getExternalContext().getContext();

            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            dir.getContextPath()
                            + "/faces/error/indexError.jsf");
        } catch (IOException f) {
            logger.error("No se pudo redireccionar a la pagina de error");
        }
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRfc() {
        return rfc;
    }

    public void setListaEntidades(List<String> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    public List<String> getListaEntidades() {
        return listaEntidades;
    }

    public void setListaFececActividadPreponderante(List<FececActividadPreponderante> listaFececActividadPreponderante) {
        this.listaFececActividadPreponderante = listaFececActividadPreponderante;
    }

    public List<FececActividadPreponderante> getListaFececActividadPreponderante() {
        return listaFececActividadPreponderante;
    }

    public void setIdEntidad(String idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getIdEntidad() {
        return idEntidad;
    }

    public void setInformeRechazoService(InformeRechazoService informeRechazoService) {
        this.informeRechazoService = informeRechazoService;
    }

    public InformeRechazoService getInformeRechazoService() {
        return informeRechazoService;
    }

    public void setMostrarExportar(boolean mostrarExportar) {
        this.mostrarExportar = mostrarExportar;
    }

    public boolean isMostrarExportar() {
        return mostrarExportar;
    }

    public void setIdActividadPreponderante(String idActividadPreponderante) {
        this.idActividadPreponderante = idActividadPreponderante;
    }

    public String getIdActividadPreponderante() {
        return idActividadPreponderante;
    }

    public void setEmpleado(FececEmpleado empleado) {
        this.empleado = empleado;
    }

    public FececEmpleado getEmpleado() {
        return empleado;
    }

    public Date getFechaInicio() {
        return fechaInicio != null ? (Date) fechaInicio.clone() : null;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio != null ? (Date) fechaInicio.clone() : null;
    }

    public Date getFechaFin() {
        return fechaFin != null ? (Date) fechaFin.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? (Date) fechaFin.clone() : null;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

}
