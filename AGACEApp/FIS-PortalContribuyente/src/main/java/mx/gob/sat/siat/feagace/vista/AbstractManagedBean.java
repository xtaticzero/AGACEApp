package mx.gob.sat.siat.feagace.vista;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;

import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaMediosContactoService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilContribuyente;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;
import mx.gob.sat.siat.modelo.dto.AccesoUsr;

import com.softtek.idc.constants.IDCConstants;
import com.softtek.idc.model.IdCInterno;
import com.softtek.idc.service.IDCService;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import mx.gob.sat.www.MedioComunicacion;
import mx.gob.sat.siat.base.vista.BaseManagedBean;

public class AbstractManagedBean extends BaseManagedBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * IDCService es la referencia del servicio del Idc para obtener la
     * informacion del contribuyente.
     */
    @ManagedProperty(value = "#{IDCService}")
    private transient IDCService idcService;

    private transient ConsultaMediosContactoService consultaMediosContactoService;

    private String secciones[] = {IDCConstants.IDENTIFICACION, IDCConstants.PATENTE,
        IDCConstants.DATOS_COMPLEMENTARIOS};

    /**
     * pistaAuditoriaService es la referencia del servicio de Pistas de
     * Auditoria.
     */
    public void informeErrorSession(Exception e) {
        HttpSession session;
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        session.setAttribute("mensaje", e);
    }

    private AccesoUsr getUsuario() {
        HttpSession session;
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        AccesoUsr accesoUsr = new AccesoUsr();
        try {
            accesoUsr = (AccesoUsr) session.getAttribute("acceso");
        } catch (Exception e) {
            logger.error("No se pudo obtener la sesion ", e);
        }

        return accesoUsr;
    }

    public String getRFCSession() {
        return "COZR860812PC4";
    }

    public Boolean validaArchivoCarga(final UploadedFile archivo, final Long tipoDocumento) {
        if (tipoDocumento.equals(1L)) {
            if (archivo.getFileName().endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)) {
                if (validaTamanoArchivo(archivo)) {
                    return true;
                }
            } else {
                FacesUtil.addErrorMessage(null, "Archivo invalido", "Solo se aceptan archivos docx");
            }
        } else if (tipoDocumento.equals(2L)) {
            if (archivo.getFileName().endsWith(Constantes.ARCHIVO_PDF)) {
                if (validaTamanoArchivo(archivo)) {
                    return true;
                }
            } else {
                FacesUtil.addErrorMessage(null, "Archivo invalido", "Solo se aceptan archivos pdf");
            }
        }

        return false;
    }

    private Boolean validaTamanoArchivo(final UploadedFile archivo) {
        if (archivo.getSize() > 0L && archivo.getSize() <= Constantes.FILE_SIZE) {
            return true;
        } else {
            if (archivo.getSize() >= Constantes.FILE_SIZE) {
                FacesUtil.addErrorMessage(null, "Error al cargar el archivo.", "El archivo es demasiado grande");
            } else {
                FacesUtil.addErrorMessage(null, "Error al cargar el archivo.", "El archivo es demasiado chico");
            }
        }

        return false;
    }

    public String getNombreSession() {
        String nombreUsuario = null;
        AccesoUsr accesoUsr = getUsuario();
        if (accesoUsr != null) {
            nombreUsuario = accesoUsr.getNombreCompleto().toUpperCase();
        } else {
            logger.error("No se pudo obtener el nombre de session. Modo develop: ");
        }
        return nombreUsuario;
    }

    public String getRfcContribuyente(final String rfcContribuyente,
            final String componenteForm) {
        String nombreContribuyente = null;
        try {
            nombreContribuyente = idcService
                    .obtenerInformacionContribuyente(rfcContribuyente,
                            secciones).getIdentificacion().getNombre();

            if (nombreContribuyente == null) {
                FacesUtil.addErrorMessage(componenteForm,
                        "No se encuentra registrada informaci\u00f3n para el RFC: "
                        + rfcContribuyente + "; favor de verificar.");
            }
        } catch (Exception e) {
            logger.error(
                    "No se pudo obtener la informacion del contribuyente [{}]",
                    e.getCause());
            FacesUtil
                    .addErrorMessage(componenteForm,
                            "Se presento un problema al cargar la informaci\u00f3n del contribuyente");
        }

        return nombreContribuyente;
    }

    public FecetContribuyente getContribuyenteIDC(final String rfcContribuyente, final String componenteForm) {
        IdCInterno contribuyenteIDC = null;
        FecetContribuyente contribuyente = new FecetContribuyente();

        try {
            contribuyenteIDC = idcService.obtenerInformacionContribuyente(
                    rfcContribuyente, secciones);
            logger.info(".::contribuyenteIDC [{}]", contribuyenteIDC);
            if (contribuyenteIDC.getIdentificacion() == null) {
                FacesUtil.addErrorMessage(componenteForm,
                        "No se encuentra registrada informaci\u00f3n para el RFC: "
                        + rfcContribuyente
                        + "; favor de verificar.");
                contribuyente.setRfc(rfcContribuyente.toUpperCase());
            } else {
                String nombreCompleto = contribuyenteIDC
                        .getIdentificacion().getNombre();
                String apellidoPaterno = contribuyenteIDC
                        .getIdentificacion().getAp_Paterno();
                String apellidoMaterno = contribuyenteIDC
                        .getIdentificacion().getAp_Materno();

                if (nombreCompleto == null && apellidoPaterno == null
                        && apellidoMaterno == null) {
                    contribuyente.setNombre("Sin nombre definido");
                } else {
                    contribuyente.setNombre(nombreCompleto + " "
                            + apellidoPaterno + " " + apellidoMaterno);
                }

                contribuyente.setRfc(rfcContribuyente.toUpperCase());
                if (contribuyenteIDC.getIdentificacion() != null) {
                    contribuyente.setSituacion(contribuyenteIDC
                            .getIdentificacion().getD_Sit_Cont().trim());

                    contribuyente.setSituacionDomicilio(contribuyenteIDC
                            .getIdentificacion().getD_Sit_Dom().trim());

                    contribuyente.setTipo(contribuyenteIDC.getIdentificacion().getT_persona().trim().equals("M")
                            ? "PERSONA MORAL" : "PERSONA FISICA");
                }
                contribuyente.setActividadPreponderante(contribuyenteIDC.getActividades() != null
                        ? contribuyenteIDC.getActividades().get(0).getD_Actividad().trim() : "Sin actividad preponderante");
                contribuyente.setEntidad((contribuyenteIDC.getUbicacion() != null) ? contribuyenteIDC.getUbicacion().getD_Ent_Fed() : "Sin Entidad");
                contribuyente.setRegimen(contribuyenteIDC.getRegimenes()
                        != null ? contribuyenteIDC.getRegimenes().get(0).getD_Regimen().trim() : "Sin regimen");

            }
        } catch (Exception e) {
            logger.error(
                    "No se pudo consultar la informacion del contribuyente [{}]",
                    e);
            informeErrorSession(e.getCause().toString());
        }

        return contribuyente;
    }

    public String getContextoAplicativo() {
        String contexto = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        contexto = externalContext.getRequestContextPath() + "/faces/resources/applet/AppletFirmaM.jar";
        return contexto;
    }

    public HttpSession getSession() {
        HttpSession session;
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        return session;
    }

    /**
     * Metodo getDescargaArchivo
     *
     * @param path
     * @param nombreArchivo
     * @return archivo Permite la descarga de el archivos seleccionado
     */
    public StreamedContent getDescargaArchivo(final String path, final String nombreDescarga) {
        StreamedContent archivo = null;

        try {
            archivo
                    = new DefaultStreamedContent(new FileInputStream(CargaArchivoUtilContribuyente.limpiarPathArchivo(path)), CargaArchivoUtilContribuyente.obtenContentTypeArchivo(nombreDescarga),
                            CargaArchivoUtilContribuyente.aplicarCodificacionTexto(nombreDescarga));
        } catch (FileNotFoundException e) {
            logger.error("No se pudo descargar el archivo. ", e);
            FacesUtil.addErrorMessage(null, "No se encontro el documento seleccionado", "");
        }

        return archivo;
    }

    public void informeErrorSession(final String e) {
        try {
            HttpSession session;
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

            session.setAttribute("mensaje", e);

            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            FacesContext.getCurrentInstance().getExternalContext().redirect(dir.getContextPath()
                    + "/faces/error/indexError.jsf");
        } catch (IOException f) {
            logger.error("No se pudo redireccionar a la pagina de error");
        }
    }

    public String verificaMediosContacto(final String rfcContribuyente) {
        String tipoMedioDeContacto = null;

        try {

            tipoMedioDeContacto = Constantes.CON_MEDIOS_CONTACTO_NO_AMPARADO;

        } catch (Exception e) {
            logger.error("Error [{}]", e);
            tipoMedioDeContacto = ConstantesError.ERROR_MEDIOS_CONTACTO;
        }
        return tipoMedioDeContacto;
    }

    public MedioComunicacion[] consultaMediosContacto(final String rfcContribuyente) throws NegocioException {
        WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
        this.consultaMediosContactoService
                = (ConsultaMediosContactoService) applicationContext.getBean("consultaMedioContactoService");
        MedioComunicacion[] mediosComunicacion = null;

        return mediosComunicacion;

    }

    public ValidaMediosContactoBO validaMediosContacto(String rfc) {
        ValidaMediosContactoBO validaMediosContactoBO = new ValidaMediosContactoBO();
        validaMediosContactoBO.setRfc(rfc);
        if (getConsultaMediosContactoService() == null) {
            WebApplicationContext applicationContext = ContextLoader
                    .getCurrentWebApplicationContext();
            setConsultaMediosContactoService((ConsultaMediosContactoService) applicationContext
                    .getBean("consultaMedioContactoService"));
        }

        validaMediosContactoBO.setFlag(true);

        return validaMediosContactoBO;
    }

    public void setConsultaMediosContactoService(ConsultaMediosContactoService consultaMediosContactoService) {
        this.consultaMediosContactoService = consultaMediosContactoService;
    }

    public ConsultaMediosContactoService getConsultaMediosContactoService() {
        return consultaMediosContactoService;
    }

    public void setIdcService(IDCService idcService) {
        this.idcService = idcService;
    }

    public IDCService getIdcService() {
        return idcService;
    }
}
