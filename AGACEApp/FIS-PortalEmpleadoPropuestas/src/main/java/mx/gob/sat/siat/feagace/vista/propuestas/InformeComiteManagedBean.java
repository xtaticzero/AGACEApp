package mx.gob.sat.siat.feagace.vista.propuestas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaInformeComiteRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.InformeComiteService;
import mx.gob.sat.siat.feagace.vista.common.InformeRechazoManagedBean;
import mx.gob.sat.siat.feagace.vista.helper.InformeRechazoHelper;

@SessionScoped
@ManagedBean(name = "informeComiteManagedBean")
public class InformeComiteManagedBean extends InformeRechazoManagedBean {

    private List<ConsultaInformeComiteRechazoPropuesta> listaInformeComite;

    private FecetDocExpediente documento;
    private List<FecetDocExpediente> listaDocumento;

    private StreamedContent descargarArchivo;
    private StreamedContent xlsReporte;
    private String mensajeError;
    private boolean mostrarComboEntidad;

    @ManagedProperty(value = "#{informeRechazoHelper}")
    private InformeRechazoHelper helper;

    @ManagedProperty(value = "#{informeComiteService}")
    private InformeComiteService informeComiteService;

    @PostConstruct
    public void init() {
        setMostrarComboEntidad(false);
        listaInformeComite = Collections.<ConsultaInformeComiteRechazoPropuesta>emptyList();

        setListaEntidades(new ArrayList<String>());
        setMostrarExportar(true);
        setRfc("");
        setIdEntidad(Constantes.COMBO_SELECCIONA_CADENA);
        setIdActividadPreponderante(Constantes.COMBO_SELECCIONA.toString());

        if (helper.listaRolesAceptadosInformeComite().contains(getEmpleado().getFececTipoEmpleado().getIdTipoEmpleado())
                && helper.listaIdAraceInformeComite().contains(getEmpleado().getFecetDetalleEmpleado().getIdCentral())
                && !(getEmpleado().getFececTipoEmpleado().getIdTipoEmpleado()
                .equals(Constantes.USUARIO_SUBADMINISTRADOR)
                && getEmpleado().getFecetDetalleEmpleado().getIdCentral().equals(Constantes.ACPPCE))) {
            setMostrarExportar(true);
            if (!getEmpleado().getFecetDetalleEmpleado().getIdCentral().equals(Constantes.ACPPCE)) {
                setMostrarComboEntidad(true);
                cargarComboEntidad(getEmpleado());
            }
            cargarComboActividadPreponderante();
        } else {
            informeErrorSession(new NegocioException("No se encontro el usuario logueado"));
        }
    }

    public void buscarInformeComite() {

        listaInformeComite = getInformeComiteService().buscarInformeComite(getRfc(), getIdEntidad(),
                getIdActividadPreponderante(), getEmpleado(), getFechaInicio(), getFechaFin(), getIdRegistro());

        if (!listaInformeComite.isEmpty()) {
            setMostrarExportar(false);
        } else {
            setMostrarExportar(true);
        }
        validaFecha();

    }

    public void limpiarInformeComite() {
        setMostrarExportar(true);
        listaInformeComite.clear();
        setRfc("");
        setIdEntidad(Constantes.COMBO_SELECCIONA_CADENA);
        setIdActividadPreponderante(Constantes.COMBO_SELECCIONA.toString());
        setIdRegistro("");
        setFechaInicio(null);
        setFechaFin(null);
    }

    public void visualizarDocumentos() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogoDocumentos').show();");
    }

    public void validaFecha() {

        if (getFechaInicio() != null && getFechaFin() != null && getFechaFin().before(getFechaInicio())) {
            addErrorMessage(null, "La Fecha informe a comité no puede ser mayor a la Fecha fin.", "");
        }
    }

    public void setListaInformeComite(List<ConsultaInformeComiteRechazoPropuesta> listaInformeComite) {
        this.listaInformeComite = listaInformeComite;
    }

    public List<ConsultaInformeComiteRechazoPropuesta> getListaInformeComite() {
        return listaInformeComite;
    }

    public void setInformeComiteService(InformeComiteService informeComiteService) {
        this.informeComiteService = informeComiteService;
    }

    public InformeComiteService getInformeComiteService() {
        return informeComiteService;
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
        workbook = getInformeComiteService().exportarInformeComitePropuesta(listaInformeComite);
        FileOutputStream out = null;
        try {
            file = File.createTempFile("reporteGerencial", "xls");
            out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            xlsReporte = new DefaultStreamedContent(new FileInputStream(file), "application/xls",
                    "reporteInformeComite.xls");
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

    public void setMostrarComboEntidad(boolean mostrarComboEntidad) {
        this.mostrarComboEntidad = mostrarComboEntidad;
    }

    public boolean isMostrarComboEntidad() {
        return mostrarComboEntidad;
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

    public void setHelper(InformeRechazoHelper helper) {
        this.helper = helper;
    }

    public InformeRechazoHelper getHelper() {
        return helper;
    }
}
