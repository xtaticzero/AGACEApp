package mx.gob.sat.siat.feagace.vista.ordenes.compulsas.managedbean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.base.excepcion.BusinessException;
import mx.gob.sat.siat.buzon.model.MedioComunicacion;
import mx.gob.sat.siat.common.archivosTemp.dto.FecetArchivoTemp;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;
import mx.gob.sat.siat.feagace.modelo.enums.RutaArchivosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.bo.ordenes.rules.ReglasNegocioOrdenesBO;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilOrdenes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilOrdenes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SeguimientoOrdenesTipoGuardadoEnum;
import mx.gob.sat.siat.feagace.vista.ordenes.compulsas.CompulsasAttributeAbstractMB;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.carga.managedbean.DocumentacionOrdenMB;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;
import mx.gob.sat.siat.feagace.vista.validador.RfcValidador;

@SessionScoped
@ManagedBean(name = "compulsasManagedBean")
public class CompulsasManagedBean extends CompulsasAttributeAbstractMB {

    @SuppressWarnings("compatibility:-964210813960958341")
    private static final long serialVersionUID = 1L;

    public static final Logger LOGGER = Logger.getLogger(CompulsasManagedBean.class);

    private static final String MENSAJE_EXITO_CARGA_OFICIO = "msg.exito.carga.oficio.validacion";
    private static final String MENSAJE_PAPELES_TRABAJO_OFICIO = "msgPapelesTrabajoOficio";

    private transient UploadedFile ofAvisoContribuyente;
    private transient UploadedFile ofCompulsaTercero;

    private static final int CASE_UNO = 1;
    private static final int CASE_DOS = 2;
    private static final int CASE_TRES = 3;

    private FecetOficioAnexos anexoSeleccionado;

    public void init() {
        if (getRecargarInformacion()) {
            setContribuyente(new FecetContribuyente());
            setRfcRepresentanteLegal(null);
            setRepresentanteLegalVO(new ColaboradorVO());
            setDeshabilitaEmailColaborador(true);
            setMostrarPanelRepLegal(false);
            setMostrarBotonBuscarRepLegal(false);
            setMostrarBotonLimpiarRepLegal(false);
            setDeshabilitaCapturaRepLegal(true);

            setOfCompulsaTercero(null);
            setListaOfCompulsaTercero(new ArrayList<FecetOficio>());
            setListaAnexosCompulsaTercero(new ArrayList<FecetOficioAnexos>());

            setRecargarInformacion(false);
            setIdTipoOficio(BigDecimal.ZERO);
            setListaPapelesTrabajoOficio(new ArrayList<DocumentoOrdenModel>());
            cargarTipoOficioSegunTab();
            setFieldsetActivoPapelTrabajo("");
            setAutoridadCompulsada("");
        }
        

    }

    public void limpiaFormulario() {
        setTipoCompulsa(0);
        setRecargarInformacion(true);
    }

    public void buscarRfcContribuyente() throws BusinessException {
        try {
            if (!getContribuyente().getRfc().equals(getOrdenSeleccionada().getFecetContribuyente().getRfc())) {

                RfcValidador validador = new RfcValidador();

                validador.validaLongitud(getContribuyente().getRfc());
                validador.validate(null, null, getContribuyente().getRfc());

                setContribuyente(
                        getContribuyenteService().getContribuyenteIDC(this.getContribuyente().getRfc().toUpperCase()));
                ValidaMediosContactoBO validaMediosContactoBO = validaMediosContacto(
                        this.getContribuyente().getRfc().toUpperCase());
                if (!validaMediosContactoBO.getMediosComunicacion().isEmpty()) {
                    for (MedioComunicacion medio : validaMediosContactoBO.getMediosComunicacion()) {
                        if (medio.getDescMedio().contains("correo") || !medio.getMedio().trim().isEmpty()) {
                            validaMediosContactoBO.setFlag(true);
                            break;
                        }
                    }
                }
                if (validaMediosContactoBO.isFlag()) {
                    setBloquearPaneles(false);
                } else {

                    setMensajeValidacion(Constantes.SIN_MEDIOS_CONTACTO_COMPULSA_TERCEROS);
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.execute("PF('confirmarValidacion').show();");
                    setBloquearPaneles(true);
                }

            } else {
                setMensajeValidacion("El RFC de la compulsa debe ser diferente al RFC de la orden");
                RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('confirmarValidacion').show();");
                setBloquearPaneles(true);
            }
        } catch (NoExisteContribuyenteException e) {
            FacesUtil.addErrorMessage("formCompulsaTercero:txtRFC",
                    "No se encuentra registrada informaci\u00f3n para el RFC: " + this.getContribuyente().getRfc()
                            + "; favor de verificar.");
            String rfc = this.getContribuyente().getRfc().toUpperCase();
            setContribuyente(new FecetContribuyente());
            getContribuyente().setRfc(rfc.toUpperCase());
            
        } catch (ValidatorException e) {
            FacesUtil.addErrorMessage("formCompulsaTercero:txtRFC", e.getFacesMessage().getDetail());
        }
    }

    public void buscarRfcRepresentanteLegal() throws BusinessException {
        getRepresentanteLegalVO().setTipoAsociado(Constantes.ID_REPRESENTANTE_LEGAL);
        getRepresentanteLegalVO().setNombreTipoAsociado(Constantes.REPRESENTANTE_LEGAL);
        buscarColaborador();
    }

    public void limpiarDatosRepLegal() {
        setRepresentanteLegalVO(new ColaboradorVO());
        setMostrarPanelRepLegal(true);
        setMostrarBotonLimpiarRepLegal(true);
        setMostrarBotonBuscarRepLegal(true);
        setDeshabilitaCapturaRepLegal(true);
        setDeshabilitaEmailColaborador(true);
    }

    /**
     *
     */
    public void llenaCamposRL() {
        if (getRfcRepresentanteLegal() == null || getRfcRepresentanteLegal().equals("")) {
            setMostrarPanelRepLegal(false);
            setMostrarBotonLimpiarRepLegal(false);
            setDeshabilitaCapturaRepLegal(true);
            setDeshabilitaEmailColaborador(true);

            setRepresentanteLegalVO(new ColaboradorVO());
        } else if (getRfcRepresentanteLegal().equals("-1")) {
            setMostrarPanelRepLegal(true);
            setMostrarBotonLimpiarRepLegal(true);
            setMostrarBotonBuscarRepLegal(true);
            setDeshabilitaCapturaRepLegal(true);
            setDeshabilitaEmailColaborador(true);

            setRepresentanteLegalVO(new ColaboradorVO());
        } else if (getRfcRepresentanteLegal().equals("-2")) {
            setMostrarPanelRepLegal(true);
            setMostrarBotonLimpiarRepLegal(true);
            setMostrarBotonBuscarRepLegal(false);
            setDeshabilitaCapturaRepLegal(false);
            setDeshabilitaEmailColaborador(false);

            setRepresentanteLegalVO(new ColaboradorVO());
        } else {
            setMostrarPanelRepLegal(true);
            setMostrarBotonLimpiarRepLegal(false);
            setMostrarBotonBuscarRepLegal(false);
            setDeshabilitaCapturaRepLegal(true);
            setDeshabilitaEmailColaborador(true);

            setRepresentanteLegalVO(new ColaboradorVO());
            getRepresentanteLegalVO().setTipoAsociado(Constantes.ID_REPRESENTANTE_LEGAL);
            getRepresentanteLegalVO().setNombreTipoAsociado(Constantes.REPRESENTANTE_LEGAL);
            getRepresentanteLegalVO().setRfc(getRfcRepresentanteLegal());
            buscarColaborador();
        }
    }

    public void buscarColaborador() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        setRepresentanteLegalVO(validaColaboradorBuscado(getRepresentanteLegalVO(), requestContext));
    }

    /**
     *
     * @param colaborador
     * @param requestContext
     */
    private ColaboradorVO validaColaboradorBuscado(ColaboradorVO colaboradorSelected, RequestContext requestContext) {
        ColaboradorVO colaborador = colaboradorSelected;
        FecetContribuyente contribuyenteIDC;
        if (colaborador != null && !colaborador.getRfc().isEmpty()) {
            try {
                contribuyenteIDC = getContribuyenteService()
                        .getContribuyenteIDC(colaboradorSelected.getRfc().toUpperCase());
                if (contribuyenteIDC != null) {
                    colaborador.setRfc(contribuyenteIDC.getRfc());
                    colaborador.setNombre(contribuyenteIDC.getNombre());

                    if (checkMediosContacto(contribuyenteIDC.getRfc())) {
                        colaborador.setMedioContactoBoolean(true);
                    } else {
                        setMensajeValidacion(
                                "No se encuentran Medios de Contacto. \u00BFDesea caputrar el E-mail del Representante Legal?");
                        requestContext.execute("PF('confirmarMediosContacto').show();");
                    }
                } else {
                    setMensajeValidacion("EL COLABORADOR NO SE ENCUENTRA EN IDC");
                    requestContext.execute("PF('noIdc').show();");
                }
            } catch (NoExisteContribuyenteException nece) {
                setMensajeValidacion("EL COLABORADOR NO SE ENCUENTRA EN IDC");
                requestContext.execute("PF('noIdc').show();");
            }
        } else {
            contribuyenteIDC = null;
            if (colaborador != null) {
                colaborador.setDeshabilitarCampos(false);
            }
            addErrorMessage(null, "Debe introducir un RFC", "");
        }

        return colaborador;
    }

    /**
     *
     * @param rfc
     * @return
     */
    private boolean checkMediosContacto(String rfc) {
        ValidaMediosContactoBO validaMediosContactoBO;
        validaMediosContactoBO = null;

        validaMediosContactoBO = getConsultaMediosContactoService().validaMediosContacto(rfc.toUpperCase());
        return validaMediosContactoBO.isFlag();
    }

    public void validaContribuyente(ValidaMediosContactoBO validaMediosContactoBO) {
        if (validaMediosContactoBO.isPPEE()) {
            logger.info("Es PPEE.");
            FecetContribuyente contribuyenteValida = new FecetContribuyente();
            contribuyenteValida.setRfc(this.getContribuyente().getRfc());
            contribuyenteValida.setNombre(this.getContribuyente().getNombre());
            setContribuyente(contribuyenteValida);
        }
    }

    public String redireccionaCompulsaSeleccion() {
        switch (getTipoCompulsa()) {
        case CASE_UNO:
            return "compulsaOtrasAutoridades";
        case CASE_DOS:
            return "compulsaTerceros";
        case CASE_TRES:
            return "compulsaInternacional";
        default:
            addErrorMessage(null, "Debe seleccionar un tipo de compulsa", "Verifique por favor");
            return null;
        }
    }

    /**
     * *********************************************************COMPULSA TERCEROS OTRAS
     * AUTORIDADES************************************************************** *****
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y regresar los datos y respuesta del
     * almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaOficioCompulsaTercero(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            setOfCompulsaTercero(event.getFile());
            cargarTablaOfCompulsaTercero();
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y regresar los datos y respuesta del
     * almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaAnexosCompulsaTercero(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getListaAnexosCompulsaTercero(),
                        event.getFile().getFileName())) {
            cargarTablaAnexosCompulsaTercero(event.getFile());
        }
    }

    public void cargarTablaOfCompulsaTercero() {
        FecetOficio oficio = new FecetOficio();

        if (getTipoCompulsa() == 1) {
            oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES));
        } else {
            oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS));
        }

        setListaOfCompulsaTercero(new ArrayList<FecetOficio>());
        setListaOfCompulsaTercero(
                cargarTablaOficio(oficio, getOfCompulsaTercero(), getOrdenSeleccionada(), getListaOfCompulsaTercero()));
    }

    public void cargarTablaAnexosCompulsaTercero(UploadedFile file) {
        setListaAnexosCompulsaTercero(cargarTablaAnexos(file, getListaAnexosCompulsaTercero()));
    }

    public void limpiarCompulsaTercero() {
        setListaOfCompulsaTercero(new ArrayList<FecetOficio>());
    }

    public void eliminarAnexoCompulsaTercero() {
        eliminarDocumentoIdenticoAnexosLista(getListaAnexosCompulsaTercero());
    }

    public void guardarCompulsaTercero() {
        try {
            if (getContribuyente().getNombre() != null) {
                if (getMostrarPanelRepLegal() && getRepresentanteLegalVO().getNombre() == null) {
                    throw new NegocioException("Debe buscar los datos del Representante Legal");
                }
                ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
                reglasNegocioOrdenesBO.setOrden(getOrdenSeleccionada());
                reglasNegocioOrdenesBO.setListaOfPrincipal(getListaOfCompulsaTercero());
                reglasNegocioOrdenesBO.setListaAnexosPrincipal(getListaAnexosCompulsaTercero());
                reglasNegocioOrdenesBO.setListaOfDependiente1(getListaOfAvisoContribuyente());
                reglasNegocioOrdenesBO.setListaAnexosDependiente1(getListaAnexosAvisoContribuyente());
                reglasNegocioOrdenesBO.setTipoCompulsa(getTipoCompulsa());
                reglasNegocioOrdenesBO.setContribuyente(getContribuyente());
                reglasNegocioOrdenesBO.setRepresentanteLegal(getRepresentanteLegalVO());

                getSeguimientoOrdenesService().guardar(
                        SeguimientoOrdenesTipoGuardadoEnum.COMPULSA_TERCERO_OTRAS_AUTORIDADES, reglasNegocioOrdenesBO);
                actualizarGuardarPapelTrabjo("fltCompulsaTerceros");
                addMessage(null, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
                this.setRecargarInformacion(true);
                init();
                DocumentacionOrdenMB doMB = (DocumentacionOrdenMB) getBean("documentacionOrdenMB");
                doMB.limpiarAvisoContribuyente();
                doMB.limpiarDocumentacionAvisoContribuyente();
            } else {
                addErrorMessage(null, "Debe buscar los datos del contribuyente", "");
            }

        } catch (NegocioException e) {
            addErrorMessage(null, e.getMessage(), "");
        }
    }

    public void guardarCompulsaOtrasAutoridades() {
        try {
            
            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getListaOfCompulsaTercero());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getListaAnexosCompulsaTercero());
            reglasNegocioOrdenesBO.setListaOfDependiente1(getListaOfAvisoContribuyente());
            reglasNegocioOrdenesBO.setListaAnexosDependiente1(getListaAnexosAvisoContribuyente());
            reglasNegocioOrdenesBO.setTipoCompulsa(getTipoCompulsa());
            reglasNegocioOrdenesBO.setContribuyente(getContribuyente());
            reglasNegocioOrdenesBO.setRepresentanteLegal(getRepresentanteLegalVO());
            reglasNegocioOrdenesBO.setAutoridadCompulsada(getAutoridadCompulsada());
            
            getSeguimientoOrdenesService().guardar(SeguimientoOrdenesTipoGuardadoEnum.COMPULSA_OTRAS_AUTORIDADES,
                    reglasNegocioOrdenesBO);
            actualizarGuardarPapelTrabjo("fltCompulsaOtrasAutoridades");
            addMessage(null, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            this.setRecargarInformacion(true);
            init();
            DocumentacionOrdenMB doMB = (DocumentacionOrdenMB) getBean("documentacionOrdenMB");
            doMB.limpiarAvisoContribuyente();
            doMB.limpiarDocumentacionAvisoContribuyente();

        } catch (Exception e) {
            addErrorMessage(null, e.getMessage(), "");
        }
    }

    public Object getBean(String beanName) {
        Object bean = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc != null) {
            ELContext elContext = fc.getELContext();
            bean = elContext.getELResolver().getValue(elContext, null, beanName);
        }
        return bean;
    }

    public void limpiarDocumentacionCompulsaTercero() {
        limpiarCompulsaTercero();
        setListaAnexosCompulsaTercero(new ArrayList<FecetOficioAnexos>());
    }

    public void habilitaEmailColaborador() {
        setDeshabilitaEmailColaborador(false);
    }

    public void inhabilitaEmailColaborador() {
        setDeshabilitaEmailColaborador(true);
    }

    public void setOfCompulsaTercero(final UploadedFile ofCompulsaTercero) {
        this.ofCompulsaTercero = ofCompulsaTercero;
    }

    public UploadedFile getOfCompulsaTercero() {
        return ofCompulsaTercero;
    }

    public void setAnexoSeleccionado(FecetOficioAnexos anexoSeleccionado) {
        this.anexoSeleccionado = anexoSeleccionado;
    }

    public FecetOficioAnexos getAnexoSeleccionado() {
        return anexoSeleccionado;
    }

    public void setOfAvisoContribuyente(final UploadedFile ofAvisoContribuyente) {
        this.ofAvisoContribuyente = ofAvisoContribuyente;
    }

    public UploadedFile getOfAvisoContribuyente() {
        return ofAvisoContribuyente;
    }

    /**
     * *********************************************************************** METODOS
     * COMUNES****************************************************************** ***********
     */
    public List<FecetOficio> cargarTablaOficio(FecetOficio oficio, UploadedFile file, final AgaceOrden orden,
            List<FecetOficio> listaOficioUnico) {
        if (file != null) {
            try {
                oficio.setFechaCreacion(new Date());
                oficio.setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(file.getFileName()));
                oficio.setArchivo(file.getInputstream());
                oficio.setIdOrden(orden.getIdOrden());
                oficio.setIdEstatus(Constantes.ESTATUS_OFICIO_PENDIENTE_FIRMA);
                listaOficioUnico.add(oficio);

                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                LOGGER.error(Constantes.FALLA_CARGA + e.getCause(), e);
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }

        return listaOficioUnico;
    }

    public final List<FecetOficioAnexos> cargarTablaAnexos(final UploadedFile file,
            final List<FecetOficioAnexos> listaAnexos) {
        FecetOficioAnexos anexo = new FecetOficioAnexos();
        final Date fecha = new Date();

        if (file != null) {
            try {
                anexo.setFechaCreacion(fecha);
                anexo.setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(file.getFileName()));
                anexo.setArchivo(file.getInputstream());
                listaAnexos.add(anexo);

                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                LOGGER.error(Constantes.FALLA_CARGA + e.getCause(), e);
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }

        return listaAnexos;
    }

    public void eliminarDocumentoIdenticoAnexosLista(final List<FecetOficioAnexos> listaAnexos) {
        for (Iterator<FecetOficioAnexos> iter = listaAnexos.iterator(); iter.hasNext();) {
            final FecetOficioAnexos anexo = iter.next();
            if (anexo == getAnexoSeleccionado()) {
                iter.remove();
            }
        }
    }
    
    
    
    public void fileUploadPapelesTrabajoOficio(FileUploadEvent event) {
        if(validaArchivoCargaWordExcelPdfIdMessage(event.getFile(),MENSAJE_PAPELES_TRABAJO_OFICIO)){
            UploadedFile archivoPapelTrabajo = event.getFile();
            cargarTablaPapelTrabajo(archivoPapelTrabajo,Constantes.PAPELTRABAJOOFICIO,MENSAJE_PAPELES_TRABAJO_OFICIO);
        }
    }
    
    public void cargarTablaPapelTrabajo(UploadedFile archivoPapelTrabajo,BigDecimal tipoPapelTrabajo,String idMensaje) {
        PapelesTrabajo papel = new PapelesTrabajo();
        llenarPapelTrabajo(papel,archivoPapelTrabajo);
        
        int numeroDocumentoPapelTrabajo=0;
        
        List<DocumentoOrdenModel> listaPapelTrabajo = getListaPapelesTrabajoOficio();
         numeroDocumentoPapelTrabajo = getNumeroDocumentoPapelTrabajoOficio();
         papel.setIdTipoOficio(getIdTipoOficio());
        
       
        if(!duplicidadPapelTrabajo(papel,listaPapelTrabajo)){            
                FecetArchivoTemp fecetArchivoTemp = new FecetArchivoTemp(); 
                fecetArchivoTemp.setArchivo(papel.getNombreArchivo());
                fecetArchivoTemp.setSessionUUID(getRFCSession());        
                try {
                    DocumentoOrdenModel papelTrabajo = new DocumentoOrdenModel();
                    papelTrabajo.setIsEliminar(true);
                    fecetArchivoTemp.setArchivoByte(IOUtils.toByteArray(archivoPapelTrabajo.getInputstream()));
                    papelTrabajo.setIdDocumentoTemporal(
                    getArchivoTempService().insertaArchivoTemp(fecetArchivoTemp));
                    papelTrabajo.setPapelesTrabajo(papel);
                    setListaPapelesTrabajoOficio(ordenarListaPapelTrabajo(papelTrabajo,listaPapelTrabajo));
                    setNumeroDocumentoPapelTrabajoOficio(numeroDocumentoPapelTrabajo+1);
                    
                    addMessage(idMensaje, Constantes.ADJUNTAR_ARCHIVO, papel.getNombreArchivo());
                } catch (IOException e) {
                    logger.error(e.getMessage());            
                }
        }else{
            addErrorMessage(idMensaje, "El archivo ya fue cargado","");
        }

    }

    private void llenarPapelTrabajo(PapelesTrabajo papel, UploadedFile archivoPapelTrabajo){
        papel.setFechaCreacion(new Date());
        papel.setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(CargaArchivoUtilOrdenes.aplicarCodificacionTexto(archivoPapelTrabajo.getFileName())));
        papel.setBlnActivo(BigDecimal.ONE);
        papel.setIdPropuesta(getOrdenSeleccionada().getIdPropuesta());
        papel.setIdOrden(getOrdenSeleccionada().getIdOrden());
    }    
    
    
    public void handleTogglePapelTrabajoOficio(ToggleEvent event){
        UIComponent tog =event.getComponent();
        BigDecimal idTipoOficio = getTipoOficioEnum().get(tog.getId());
        
        if(event.getVisibility().toString().equals("VISIBLE")){
            
                setIdTipoOficio(idTipoOficio);
                cargarPapelesTrabajoOficio(idTipoOficio);
                setNumeroDocumentoPapelTrabajoOficio(0);
                setFieldsetActivoPapelTrabajo(tog.getId());
            
        }
        else{
            setListaPapelesTrabajoOficio(new ArrayList<DocumentoOrdenModel>());
        }

    }
    
    public void cargarPapelesTrabajoOficio(BigDecimal idTipoOficio){
        setListaPapelesTrabajoOficio(new ArrayList<DocumentoOrdenModel>());
        List<PapelesTrabajo> listaPapelTrabajo = getConsultarPapelesTrabajoService().getPapelesByIdOfcio(getOrdenSeleccionada().getIdOrden(),idTipoOficio);        
        if(listaPapelTrabajo!=null ){
            for (PapelesTrabajo papel:listaPapelTrabajo){
                DocumentoOrdenModel documento = new DocumentoOrdenModel();
                documento.setPapelesTrabajo(papel);
                getListaPapelesTrabajoOficio().add(documento);
            }
        }
    }
    
    private List<DocumentoOrdenModel> ordenarListaPapelTrabajo(DocumentoOrdenModel papelTrabajo,List<DocumentoOrdenModel> listaPapelTrabajo){
        List<DocumentoOrdenModel> listaPapelTrabajoAgregado= new ArrayList<DocumentoOrdenModel>();
        listaPapelTrabajoAgregado.add(papelTrabajo);
        listaPapelTrabajoAgregado.addAll(listaPapelTrabajo);
        return listaPapelTrabajoAgregado;
    }
    
    
    public void guardarPapeleTrabajoOficio(){
        int numeroDocumentosGuardados=0;
        try {
            String carpetaPapelTrabajo = Constantes.CARPETAPAPELTRABAJO;
            String carpetaUnica = (DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));
            for(DocumentoOrdenModel papel : getListaPapelesTrabajoOficio()){
                if(papel.isIsEliminar()){            
                    
                    StringBuilder carpetaOfico = new StringBuilder();
                    carpetaOfico.append("Oficio_").append(getIdTipoOficio());
                    
                    papel.getPapelesTrabajo().setRutaArchivo(RutaArchivosUtilOrdenes.generarRutaArchivoValida(RutaArchivosEnum.RUTA_DOCUMENTOS_PAPELES_TRABAJO_ORDENES, 
                            getOrdenSeleccionada().getNumeroOrden(),carpetaPapelTrabajo,carpetaOfico.toString(),carpetaUnica)
                            + papel.getPapelesTrabajo().getNombreArchivo());                
                    
                        getConsultarPapelesTrabajoService().insertaPapelesTrabajo(papel.getPapelesTrabajo());
                        InputStream myInputStream = new ByteArrayInputStream(
                                getArchivoTempService().consultaArchivoTemp(papel.getIdDocumentoTemporal(), getRFCSession()));
                        papel.setInput(myInputStream);
                        getConsultarPapelesTrabajoService().guardarPapelTrabajo(papel);
                        numeroDocumentosGuardados++;
                }
            }
        } catch (IOException e) {
            addMessage(MENSAJE_PAPELES_TRABAJO_OFICIO, e.getMessage(), "");
        }
        if(numeroDocumentosGuardados!=0){
            addMessage(MENSAJE_PAPELES_TRABAJO_OFICIO, "Se han guardado exitosamente los papeles de trabajo en el sistema.", "");
        }
        setNumeroDocumentoPapelTrabajoOficio(0);
        cargarPapelesTrabajoOficio(getIdTipoOficio());
        
    }

    public StreamedContent getArchivoDescargaPapelTrabajo() {
        
        if(getPapelTrabajoSeleccionado().getPapelesTrabajo().getRutaArchivo()==null){        
            InputStream myInputStream = new ByteArrayInputStream(
            getArchivoTempService().consultaArchivoTemp(getPapelTrabajoSeleccionado().getIdDocumentoTemporal(), getRFCSession()));
            return new DefaultStreamedContent(myInputStream, "application/octet-stream", getPapelTrabajoSeleccionado().getPapelesTrabajo().getNombreArchivo());
        }
        
        return getArchivoGenerico(getPapelTrabajoSeleccionado().getPapelesTrabajo().getRutaArchivo(),
                getPapelTrabajoSeleccionado().getPapelesTrabajo().getNombreArchivo());
    }
    
    private StreamedContent getArchivoGenerico(final String path, final String nombre) {
        String nuevoPath = "";
        if (path != null && nombre != null) {
            nuevoPath = path.replace(nombre, "").concat(nombre);
        }
        return getDescargaArchivo(nuevoPath, nombre);
    }
    
    public void eliminarPapelTrabajoOficio(){
        DocumentoOrdenModel papelTrabajo = getPapelTrabajoSeleccionado();        
        
        
        if(papelTrabajo.isIsEliminar() && getListaPapelesTrabajoOficio().contains(papelTrabajo) ){
            getListaPapelesTrabajoOficio().remove(papelTrabajo);
            addMessage(MENSAJE_PAPELES_TRABAJO_OFICIO, "Se realiz\u00f3 la eliminaci\u00f3n del documento ", papelTrabajo.getPapelesTrabajo().getNombreArchivo());
            setNumeroDocumentoPapelTrabajoOficio(getNumeroDocumentoPapelTrabajoOficio()-1);
        }
        
        if(!papelTrabajo.isIsEliminar() && getListaPapelesTrabajoOficio().contains(papelTrabajo) ){
            getListaPapelesTrabajoOficio().remove(papelTrabajo);
            papelTrabajo.getPapelesTrabajo().setBlnActivo(BigDecimal.ZERO);
            papelTrabajo.getPapelesTrabajo().setFechaFin(new Date());
            getConsultarPapelesTrabajoService().actualizarPapelTrabajo(papelTrabajo.getPapelesTrabajo());
            addMessage(MENSAJE_PAPELES_TRABAJO_OFICIO, "Se realiz\u00f3 la eliminaci\u00f3n del documento ", papelTrabajo.getPapelesTrabajo().getNombreArchivo());
        }
        
    }

    private void actualizarGuardarPapelTrabjo(String idFieldset){
        
        
        if(getFieldsetActivoPapelTrabajo().equals(idFieldset)){
            guardarPapeleTrabajoOficio();
        }else{
            cargarPapelesTrabajoOficio(getTipoOficioEnum().get(idFieldset));  
        }

        for(DocumentoOrdenModel papel : getListaPapelesTrabajoOficio()){
            papel.getPapelesTrabajo().setIdOficio(
            getSeguimientoOrdenesService().obtenerIdOficioPadre());
                    getConsultarPapelesTrabajoService().actualizarPapelTrabajo(papel.getPapelesTrabajo());
        }
    }
    

    
}
