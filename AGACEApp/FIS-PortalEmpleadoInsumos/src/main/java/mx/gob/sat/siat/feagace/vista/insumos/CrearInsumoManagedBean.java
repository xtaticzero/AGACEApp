package mx.gob.sat.siat.feagace.vista.insumos;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.gob.sat.siat.base.constante.ConstantesSesion;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.base.excepcion.BusinessException;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececTipoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.RegistroInsumosDto;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.vista.model.insumos.CrearInsumoDTO;
import mx.gob.sat.siat.feagace.vista.model.insumos.InsumoExpedienteDTO;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;
import mx.gob.sat.siat.feagace.vista.util.MetodosGenericos;

@ViewScoped
@ManagedBean(name = "crearInsumoManagedBean")
public class CrearInsumoManagedBean extends AbstractCrearInsumoMB {

    /**
     * Serial
     */
    private static final long serialVersionUID = 1L;
    private static final String TEXT_RFC = "formContribuyente:txtRFC";
    private static final String LEYENDA_CAMPO_OBLIGATORIO = "Campo Obligatorio *";
    private static final String MSG_NUMERO_DOCTOS = "El n\u00FAmero de documentos por registro debe ser de 1 a 50.";

    //quitar se pone temporalmente, se tiene que modificar DTO o crear uno nuevo
    private String justificacion;

    public CrearInsumoManagedBean() {
        super();
    }

    @PostConstruct
    public void init() {

        getAccesoEmpleado(getRFCSession());
        
        setCrearInsumoDTO(new CrearInsumoDTO());
        getCrearInsumoDTO().setDesplegarDialogo(Constantes.ENTERO_CERO);
        getCrearInsumoDTO().setContribuyente(new FecetContribuyente());
        getCrearInsumoDTO().setPropuesta(new FecetInsumo());
        getCrearInsumoDTO().setListaDocumentosExpediente(new ArrayList<FecetDocExpInsumo>());
        getCrearInsumoDTO().setListaDocumentosBorrar(new ArrayList<FecetDocExpInsumo>());
        getCrearInsumoDTO().setInsumosExpedienteDTO(new ArrayList<InsumoExpedienteDTO>());
        List<FecetInsumo> lista = getSeguimientoInsumosService().getListaSeguimientoInsumoEstatusRFCCreacion(Constantes.INSUMO_AGREGADO, getRFCSession(), obtenerGrupoUnidadesAdminXGeneral((EmpleadoDTO) getSession().getAttribute(ConstantesSesion.EMPLEADO_FIS_INTEGRAL)));
        getCrearInsumoDTO().setJustificacion(null);
        getCrearInsumoDTO().setJustificacionDetalle(null);
        for (FecetInsumo fecetInsumo : lista) {
            InsumoExpedienteDTO insumoExpedienteDTO = new InsumoExpedienteDTO();
            insumoExpedienteDTO.setFecetInsumo(fecetInsumo);
            insumoExpedienteDTO.setFecetDocExpInsumos(getSeguimientoInsumosService().obtenerDocumentos(fecetInsumo.getIdInsumo()));
            insumoExpedienteDTO.setNumeroExpedientes(insumoExpedienteDTO.getFecetDocExpInsumos().size());
            getCrearInsumoDTO().getInsumosExpedienteDTO().add(insumoExpedienteDTO);

        }
        getCrearInsumoDTO().setInsumosAgregados(getCrearInsumoDTO().getInsumosExpedienteDTO().size());
        getCrearInsumoDTO().setInsumosProcesados(new RegistroInsumosDto());
        getCrearInsumoDTO().setListaInsumosRegistrados(new ArrayList<FecetInsumo>());
        getCrearInsumoDTO().setListaInsumosNoRegistrados(new ArrayList<FecetInsumo>());
        getCrearInsumoDTO().setBloquearPaneles(true);

        getCrearInsumoDTO().setMensajeAntecedentes(null);

        getCrearInsumoDTO().setInsumosSeleccionados(new ArrayList<FecetInsumo>());
        getCrearInsumoDTO().setContadorRegistros(BigDecimal.ZERO);
        getCrearInsumoDTO().setFechaHoy(new Date());
        getCrearInsumoDTO().setStrFechaHoy(new SimpleDateFormat("dd/MM/yyyy").format(getCrearInsumoDTO().getFechaHoy()));

        try {
            getCrearInsumoDTO().setEmpleadoSesion(obtenerEmpleadoAcceso(getRFCSession(), TipoEmpleadoEnum.USUARIO_INSUMOS));
            //Id General
            getCrearInsumoDTO().setListaSubprograma(getCrearInsumoService().getCatalogoSubprograma(new BigDecimal(getCrearInsumoDTO().getEmpleadoSesion().getIdAdmGral())));
            getCrearInsumoDTO().setListaSector(getCrearInsumoService().getCatalogoSector(new BigDecimal(getCrearInsumoDTO().getEmpleadoSesion().getIdAdmGral())));
            getCrearInsumoDTO().setVisualizaDetalle(false);
            getCrearInsumoDTO().setListaFececPrioridad(getConsultaPrioridadService().findActivos(new BigDecimal(getCrearInsumoDTO().getEmpleadoSesion().getIdAdmGral())));
            getCrearInsumoDTO().setUnidadesAdministrativas(getCrearInsumoService().getUnidadesAdministritativas(getCrearInsumoDTO().getEmpleadoSesion()));
            getCrearInsumoDTO().setListTipoInsumo(getCrearInsumoService().getCatalogoTipoInsumo(new BigDecimal(getCrearInsumoDTO().getEmpleadoSesion().getIdAdmGral())));
            limpiar();
        } catch (NoExisteEmpleadoException e) {
            logger.error("No se pudo cargar la informacion basica de la pagina", e);
            informeErrorSession(e);
        }

    }

    public void limpiar() {
        getCrearInsumoDTO().getPropuesta().setIdArace(new BigDecimal(-1));
        getCrearInsumoDTO().getPropuesta().setIdSubprograma(new BigDecimal(-1));
        getCrearInsumoDTO().getPropuesta().setIdSector(new BigDecimal(-1));
        getCrearInsumoDTO().getPropuesta().setFechaInicioPeriodo(null);
        getCrearInsumoDTO().getPropuesta().setFechaFinPeriodo(null);

        getCrearInsumoDTO().setContribuyente(new FecetContribuyente());
        getCrearInsumoDTO().setListaDocumentosExpediente(new ArrayList<FecetDocExpInsumo>());
        getCrearInsumoDTO().setListaDocumentosJustificacion(new ArrayList<FecetDocumento>());
        getCrearInsumoDTO().setInsumosProcesados(new RegistroInsumosDto());
        getCrearInsumoDTO().setListaInsumosRegistrados(new ArrayList<FecetInsumo>());
        getCrearInsumoDTO().setListaInsumosNoRegistrados(new ArrayList<FecetInsumo>());
        getCrearInsumoDTO().setInsumosAgregados(getCrearInsumoDTO().getInsumosExpedienteDTO() != null ? getCrearInsumoDTO().getInsumosExpedienteDTO().size() : 0);
        getCrearInsumoDTO().setBloquearPaneles(true);

        getCrearInsumoDTO().setMensajeAntecedentes(null);

        getCrearInsumoDTO().setInsumosSeleccionados(new ArrayList<FecetInsumo>());
        getCrearInsumoDTO().setContadorRegistros(BigDecimal.ZERO);
        getCrearInsumoDTO().setFechaHoy(new Date());
        getCrearInsumoDTO().setStrFechaHoy(new SimpleDateFormat("dd/MM/yyyy").format(getCrearInsumoDTO().getFechaHoy()));
        getCrearInsumoDTO().setBloquearRFC(false);
    }

    public void buscarRfcContribuyente() throws BusinessException {
        logger.info("Entrando al metodo buscarRfcContribuyente()");
        getCrearInsumoDTO().setMensajeServicios("");

        try {
            getCrearInsumoDTO().setContribuyente(getContribuyenteService().getContribuyenteIDC(getCrearInsumoDTO().getContribuyente().getRfc().toUpperCase()));
            getCrearInsumoDTO().setBloquearPaneles(true);
            if (getCrearInsumoDTO().getContribuyente().getNombre() == null) {
                getCrearInsumoDTO().setBloquearPaneles(false);
            }

            ValidaMediosContactoBO validaMediosContactoBO;
            validaMediosContactoBO = null;
            logger.info("Validando medios de contacto.");
            validaMediosContactoBO = checarMediosDeContacto(getCrearInsumoDTO().getContribuyente().getRfc().toUpperCase());

            if (!validaMediosContactoBO.isFlag()) {
                getCrearInsumoDTO().setBloquearPaneles(false);
            } else {
                if (!validaMediosContactoBO.getMessage().equals(Constantes.MESANJE_ERROR_MEDIOS_CONTACTO)) {
                    logger.info("No cumple con los permisos necesarios para registrar un Insumo.");
                    if (!validaMediosContactoBO.getMediosComunicacion().isEmpty() && validaMediosContactoBO.getMediosComunicacion().get(Constantes.CERO).getTipoMedio() == 1) {
                        logger.info("No cuenta con Medios de Contacto.");
                        enviarCorreoPPEEAmparado(getCrearInsumoDTO().getContribuyente(), validaMediosContactoBO);
                        getCrearInsumoDTO().setBloquearPaneles(true);
                        validaContribuyente(validaMediosContactoBO);
                    }
                    getCrearInsumoDTO().setMensajeValidacion(validaMediosContactoBO.getMessage());
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.execute("PF('confirmarValidacion').show();");
                } else {
                    getCrearInsumoDTO().setMensajeServicios(Constantes.MESANJE_ERROR_MEDIOS_CONTACTO);
                }
            }
        } catch (NoExisteContribuyenteException nece) {
            FacesUtil.addErrorMessage(TEXT_RFC,
                    "No se encuentra registrada informaci\u00f3n para el RFC: "
                    + getCrearInsumoDTO().getContribuyente().getRfc().toUpperCase()
                    + "; favor de verificar.");
            getCrearInsumoDTO().getContribuyente().setRfc(getCrearInsumoDTO().getContribuyente().getRfc().toUpperCase());
        } catch (Exception ex) {
            FacesUtil.addErrorMessage(TEXT_RFC,
                    "No se encuentra registrada informaci\u00f3n para el RFC: "
                    + getCrearInsumoDTO().getContribuyente().getRfc().toUpperCase()
                    + "; favor de verificar.");
            getCrearInsumoDTO().getContribuyente().setRfc(getCrearInsumoDTO().getContribuyente().getRfc().toUpperCase());
        }
    }

    public void validaContribuyente(ValidaMediosContactoBO validaMediosContactoBO) {
        if (validaMediosContactoBO.isPPEE()) {
            logger.info("Es PPEE.");
            FecetContribuyente contribuyenteValida = new FecetContribuyente();
            contribuyenteValida.setRfc(getCrearInsumoDTO().getContribuyente().getRfc());
            contribuyenteValida.setNombre(getCrearInsumoDTO().getContribuyente().getNombre());
            getCrearInsumoDTO().setContribuyente(contribuyenteValida);
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del file system y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaArchivoExpediente(final FileUploadEvent event) {
        if (validaArchivoCargaInsumoPropuesta(event.getFile())
                && !validaArchivoDuplicadoExpedienteInsumo(getCrearInsumoDTO().getListaDocumentosExpediente(), VistaArchivoInsumoUtil.aplicarCodificacionTexto(VistaArchivoInsumoUtil.limpiarPathArchivo(event.getFile().getFileName())))
                && validaTamanoExpedienteInsumo(getCrearInsumoDTO().getListaDocumentosExpediente())) {
            try {
                getCrearInsumoDTO().setArchivoCarga(event.getFile());
                FecetDocExpInsumo documento = new FecetDocExpInsumo();
                documento.setFechaCreacion(new Date());
                documento.setNombre(VistaArchivoInsumoUtil.aplicarCodificacionTexto(VistaArchivoInsumoUtil.limpiarPathArchivo(getCrearInsumoDTO().getArchivoCarga().getFileName())));
                documento.setArchivo(getCrearInsumoDTO().getArchivoCarga().getInputstream());
                getCrearInsumoDTO().getListaDocumentosExpediente().add(documento);
                addMessage(null, Constantes.ARCHIVO_CARGADO, documento.getNombre());
            } catch (IOException e) {
                logger.error(MetodosGenericos.acentuar(getCrearInsumoDTO().getArchivoCarga().getFileName()));
                logger.error("No se pudo adjuntar el documento  [{}]", e);
            }

        }
    }

    public void cargaArchivoJustificacion(final FileUploadEvent event) {
        if (validaArchivoCargaInsumoPropuesta(event.getFile())
                && !validaArchivoDuplicadoJustificacionInsumo(getCrearInsumoDTO().getListaDocumentosJustificacion(), VistaArchivoInsumoUtil.aplicarCodificacionTexto(VistaArchivoInsumoUtil.limpiarPathArchivo(event.getFile().getFileName())))
                && validaTamanoJustificacionInsumo(getCrearInsumoDTO().getListaDocumentosJustificacion())) {
            try {
                getCrearInsumoDTO().setArchivoCarga(event.getFile());
                FecetDocumento documento = new FecetDocumento();
                documento.setFechaCreacion(new Date());
                documento.setNombre(VistaArchivoInsumoUtil.aplicarCodificacionTexto(VistaArchivoInsumoUtil.limpiarPathArchivo(getCrearInsumoDTO().getArchivoCarga().getFileName())));
                documento.setArchivo(getCrearInsumoDTO().getArchivoCarga().getInputstream());
                documento.setIdTipoDocumento(BigDecimal.ONE);
                getCrearInsumoDTO().getListaDocumentosJustificacion().add(documento);
                addMessage(null, Constantes.ARCHIVO_CARGADO, documento.getNombre());
            } catch (IOException e) {
                logger.error(MetodosGenericos.acentuar(getCrearInsumoDTO().getArchivoCarga().getFileName()));
                logger.error("No se pudo adjuntar el documento  [{}]", e);
            }
        }
    }

    public void validarEliminarInsumo() {
        Boolean insumoSeleccionadoBoolean = false;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        for (InsumoExpedienteDTO dto : getCrearInsumoDTO().getInsumosExpedienteDTO()) {
            if (dto.getFecetInsumo().isSelected()) {
                insumoSeleccionadoBoolean = true;
            }
        }
        if (insumoSeleccionadoBoolean) {
            requestContext.execute("PF('confirmarEliminarInsumos').show();");
        } else {
            addErrorMessage(null, "Debe seleccionar por lo menos un insumo", Constantes.CADENA_VACIA);
        }
    }

    public void eliminarInsumo() {
        List<FecetInsumo> lista = new ArrayList<FecetInsumo>();
        for (InsumoExpedienteDTO dto : getCrearInsumoDTO().getInsumosExpedienteDTO()) {
            if (dto.getFecetInsumo().isSelected()) {
                dto.getFecetInsumo().setListaDocumentos(dto.getFecetDocExpInsumos());
                lista.add(dto.getFecetInsumo());
            }
        }
        for (FecetInsumo insumo : lista) {
            insumo.setFechaBaja(new Date());
            getCrearInsumoService().eliminarInsumo(insumo);
        }
        getCrearInsumoDTO().setListaPropuesta(lista);
        getCrearInsumoDTO().setInsumosAgregados(getCrearInsumoDTO().getListaPropuesta().size());
        init();
    }

    public void descartarDocumento() {
        for (int i = 0; i < getCrearInsumoDTO().getListaDocumentosExpediente().size(); i++) {
            if (getCrearInsumoDTO().getDocSeleccionado().getNombre().equals(getCrearInsumoDTO().getListaDocumentosExpediente().get(i).getNombre())) {
                getCrearInsumoDTO().getListaDocumentosBorrar().add(getCrearInsumoDTO().getListaDocumentosExpediente().get(i));
                getCrearInsumoDTO().getListaDocumentosExpediente().remove(i);
                break;
            }
        }
        addMessage(null, "Se descart\u00f3 el documento correctamente.", "");
    }

    public void descartarDocumentoJustificacion() {

        if (getCrearInsumoDTO().getListaDocumentosJustificacion() != null && !getCrearInsumoDTO().getListaDocumentosJustificacion().isEmpty()) {
            for (FecetDocumento documento : getCrearInsumoDTO().getListaDocumentosJustificacion()) {
                if (documento.getNombre().equals(getCrearInsumoDTO().getDocSeleccionadoJustificacion().getNombre())) {
                    getCrearInsumoDTO().getListaDocumentosJustificacion().remove(documento);
                    break;
                }

            }
        }
        addMessage(null, "Se descart\u00f3 el documento correctamente.", "");
    }

    public void asignarInsumo() {
        if (validaCargaInsumo()) {
            if (validaDuplicidadAntecedentes()) {
                validarDuplicidadAntecedentes();
            } else {
                addErrorMessage(null, ConstantesError.ERROR_REGISTRO_DUPLICADO_TEMPORAL);
            }
        }
    }

    private boolean validaDuplicidadAntecedentes() {
        FecetInsumo fecetInsumo = new FecetInsumo();

        fecetInsumo.setFecetContribuyente(getCrearInsumoDTO().getContribuyente());
        fecetInsumo.setFececSubprograma(buscarSubprogramaSeleccionado(getCrearInsumoDTO().getPropuesta().getIdSubprograma()));
        fecetInsumo.setFechaInicioPeriodo(getCrearInsumoDTO().getPropuesta().getFechaInicioPeriodo());
        fecetInsumo.setFechaFinPeriodo(getCrearInsumoDTO().getPropuesta().getFechaFinPeriodo());

        boolean valida = true;
        for (InsumoExpedienteDTO insumoExpedienteDTO : getCrearInsumoDTO().getInsumosExpedienteDTO()) {
            if (insumoExpedienteDTO.getFecetInsumo().getIdInsumo().equals(getCrearInsumoDTO().getPropuesta().getIdInsumo())) {
                continue;
            }
            valida &= getCrearInsumoService().validaExistenciaTemporal(insumoExpedienteDTO.getFecetInsumo(), fecetInsumo);
        }
        return valida;
    }

    private void validarDuplicidadAntecedentes() {
        List<String> listaAntecedentes = verificarAntecedentes(
                getCrearInsumoDTO().getContribuyente().getRfc(),
                getCrearInsumoDTO().getPropuesta().getFechaInicioPeriodo(),
                getCrearInsumoDTO().getPropuesta().getFechaFinPeriodo(),
                getCrearInsumoDTO().getPropuesta().getIdSubprograma(), getCrearInsumoDTO().getPropuesta().getIdUnidadAdministrativa(),
                null);
        if (!listaAntecedentes.isEmpty()) {
            reservarInsumo();
        } else {
            getCrearInsumoDTO().setDesplegarDialogo(Constantes.ENTERO_UNO);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('dialogJustificacionCoincidencias').show();");
        }
    }

    public void reservarInsumo() {
        getCrearInsumoDTO().setContadorRegistros(getCrearInsumoDTO().getContadorRegistros().add(BigDecimal.ONE));
        getCrearInsumoDTO().getPropuesta().setIdInsumo(getCrearInsumoDTO().getContadorRegistros());

        getCrearInsumoDTO().getPropuesta().setListaDocumentos(getCrearInsumoDTO().getListaDocumentosExpediente());
        getCrearInsumoDTO().getPropuesta().setFecetContribuyente(getCrearInsumoDTO().getContribuyente());
        getCrearInsumoDTO().getPropuesta().setSizeListaDocumentos(getCrearInsumoDTO().getListaDocumentosExpediente().size());
        getCrearInsumoDTO().getPropuesta().setFececSector(buscarSectorSeleccionado(getCrearInsumoDTO().getPropuesta().getIdSector()));
        getCrearInsumoDTO().getPropuesta().setFececSubprograma(buscarSubprogramaSeleccionado(getCrearInsumoDTO().getPropuesta().getIdSubprograma()));
        getCrearInsumoDTO().getPropuesta().setRfcCreacion(getRFCSession());
        getCrearInsumoDTO().getPropuesta().setFececTipoInsumo(buscarTipoInsumoSeleccionado(getCrearInsumoDTO().getPropuesta().getIdTipoInsumo()));

        try {
            getCrearInsumoDTO().getPropuesta().setListaDocumentoJustificacion(getCrearInsumoDTO().getListaDocumentosJustificacion());
            getCrearInsumoDTO().getPropuesta().setJustificacion(getCrearInsumoDTO().getJustificacion());
            getCrearInsumoService().agregaInsumo(getCrearInsumoDTO().getPropuesta());
            getCrearInsumoDTO().setListaPropuesta(getSeguimientoInsumosService().getListaSeguimientoInsumoEstatusRFCCreacion(Constantes.INSUMO_AGREGADO, getRFCSession(), obtenerGrupoUnidadesAdminXGeneral((EmpleadoDTO) getSession().getAttribute(ConstantesSesion.EMPLEADO_FIS_INTEGRAL))));
        } catch (NegocioException e) {
            logger.error("No se pudo agregar el insumo [{}]  [{}]", e.getCause(), e);
            addErrorMessage(null, "No se pudo agregar la informacion");
            return;
        }

        getCrearInsumoDTO().setInsumosExpedienteDTO(new ArrayList<InsumoExpedienteDTO>());
        List<FecetInsumo> lista = getSeguimientoInsumosService().getListaSeguimientoInsumoEstatusRFCCreacion(Constantes.INSUMO_AGREGADO, getRFCSession(), obtenerGrupoUnidadesAdminXGeneral((EmpleadoDTO) getSession().getAttribute(ConstantesSesion.EMPLEADO_FIS_INTEGRAL)));
        for (FecetInsumo fecetInsumo : lista) {
            InsumoExpedienteDTO insumoExpedienteDTO = new InsumoExpedienteDTO();
            insumoExpedienteDTO.setFecetInsumo(fecetInsumo);
            insumoExpedienteDTO.setFecetDocExpInsumos(getSeguimientoInsumosService().obtenerDocumentos(fecetInsumo.getIdInsumo()));
            insumoExpedienteDTO.setNumeroExpedientes(insumoExpedienteDTO.getFecetDocExpInsumos().size());
            getCrearInsumoDTO().getInsumosExpedienteDTO().add(insumoExpedienteDTO);

        }
        getCrearInsumoDTO().setInsumosAgregados(getCrearInsumoDTO().getInsumosExpedienteDTO().size());

        getCrearInsumoDTO().setPropuesta(new FecetInsumo());
        getCrearInsumoDTO().setContribuyente(new FecetContribuyente());
        getCrearInsumoDTO().setListaDocumentosExpediente(new ArrayList<FecetDocExpInsumo>());
        getCrearInsumoDTO().setListaDocumentosJustificacion(new ArrayList<FecetDocumento>());
        getCrearInsumoDTO().setJustificacion(null);
        getCrearInsumoDTO().setBloquearPaneles(true);
    }

    public void actualizarInsumo() {
        if (validaCargaInsumo()) {
            if (!validaDuplicidadAntecedentes()) {
                addErrorMessage(null, ConstantesError.ERROR_REGISTRO_DUPLICADO_TEMPORAL);
            } else {
                List<String> listaAntecedentes = verificarAntecedentes(
                        getCrearInsumoDTO().getContribuyente().getRfc(),
                        getCrearInsumoDTO().getPropuesta().getFechaInicioPeriodo(),
                        getCrearInsumoDTO().getPropuesta().getFechaFinPeriodo(),
                        getCrearInsumoDTO().getPropuesta().getIdSubprograma(), getCrearInsumoDTO().getPropuesta().getIdUnidadAdministrativa(),
                        null);
                getCrearInsumoDTO().setDesplegarDialogo(Constantes.ENTERO_DOS);
                getCrearInsumoDTO().setListaDocumentosPorBorrarJustificacion(new ArrayList<FecetDocumento>());
                getCrearInsumoDTO().getListaDocumentosPorBorrarJustificacion().addAll(getCrearInsumoDTO().getListaDocumentosJustificacion() != null ? getCrearInsumoDTO().getListaDocumentosJustificacion() : new ArrayList<FecetDocumento>());
                getCrearInsumoDTO().setListaDocumentosJustificacion(new ArrayList<FecetDocumento>());
                getCrearInsumoDTO().setNumeroDeDocumentos(Constantes.ENTERO_CERO);
                getCrearInsumoDTO().setJustificacion(null);
                getCrearInsumoDTO().setJustificacionDetalle(null);
                if (!listaAntecedentes.isEmpty()) {
                    realizarActualizacion();
                } else {
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.execute("PF('dialogJustificacionCoincidencias').show();");
                }
            }
        }
    }

    private void realizarActualizacion() {
        try {
            getCrearInsumoDTO().getPropuesta().setListaDocumentos(getCrearInsumoDTO().getListaDocumentosExpediente());
            getCrearInsumoDTO().getPropuesta().setFecetContribuyente(getCrearInsumoDTO().getContribuyente());
            getCrearInsumoDTO().getPropuesta().setFececSector(buscarSectorSeleccionado(getCrearInsumoDTO().getPropuesta().getIdSector()));
            getCrearInsumoDTO().getPropuesta().setFececSubprograma(buscarSubprogramaSeleccionado(getCrearInsumoDTO().getPropuesta().getIdSubprograma()));
            getCrearInsumoDTO().getPropuesta().setFececTipoInsumo(buscarTipoInsumoSeleccionado(getCrearInsumoDTO().getPropuesta().getIdTipoInsumo()));

            getCrearInsumoDTO().getPropuesta().setRfcCreacion(getRFCSession());
            getCrearInsumoDTO().getPropuesta().setListaDocumentoJustificacion(getCrearInsumoDTO().getListaDocumentosJustificacion());
            getCrearInsumoDTO().getListaDocumentosPorBorrarJustificacion().addAll(getCrearInsumoDTO().getPropuesta().getListaDocumentoJustificacion());
            getCrearInsumoDTO().getPropuesta().setJustificacion(getCrearInsumoDTO().getJustificacion());
            getCrearInsumoDTO().setNumeroDeDocumentos(getCrearInsumoDTO().getPropuesta().getListaDocumentoJustificacion().size());
            getCrearInsumoDTO().setJustificacionDetalle(getCrearInsumoDTO().getJustificacion());
            getCrearInsumoService().actualizaInsumo(getCrearInsumoDTO().getPropuesta(), getCrearInsumoDTO().getListaDocumentosBorrar(), getCrearInsumoDTO().getListaDocumentosPorBorrarJustificacion());
            getCrearInsumoDTO().setListaPropuesta(getSeguimientoInsumosService().getListaSeguimientoInsumoEstatusRFCCreacion(Constantes.INSUMO_AGREGADO, getRFCSession(), obtenerGrupoUnidadesAdminXGeneral((EmpleadoDTO) getSession().getAttribute(ConstantesSesion.EMPLEADO_FIS_INTEGRAL))));

        } catch (NegocioException e) {
            logger.error("No se pudo actualizar el insumo [{}]  [{}]", e.getCause(), e);
            addErrorMessage(null, "No se pudo actualizar la informacion");
        }
        getCrearInsumoDTO().setInsumosExpedienteDTO(new ArrayList<InsumoExpedienteDTO>());
        List<FecetInsumo> lista = getSeguimientoInsumosService().getListaSeguimientoInsumoEstatusRFCCreacion(Constantes.INSUMO_AGREGADO, getRFCSession(), obtenerGrupoUnidadesAdminXGeneral((EmpleadoDTO) getSession().getAttribute(ConstantesSesion.EMPLEADO_FIS_INTEGRAL)));
        for (FecetInsumo fecetInsumo : lista) {
            InsumoExpedienteDTO insumoExpedienteDTO = new InsumoExpedienteDTO();
            insumoExpedienteDTO.setFecetInsumo(fecetInsumo);
            insumoExpedienteDTO.setFecetDocExpInsumos(getSeguimientoInsumosService().obtenerDocumentos(fecetInsumo.getIdInsumo()));
            insumoExpedienteDTO.setNumeroExpedientes(insumoExpedienteDTO.getFecetDocExpInsumos().size());
            getCrearInsumoDTO().getInsumosExpedienteDTO().add(insumoExpedienteDTO);
        }

        getCrearInsumoDTO().setInsumosAgregados(getCrearInsumoDTO().getInsumosExpedienteDTO().size());
        addMessage(null, "Se modifico el insumo correctamente.");
    }

    public void registrarInsumosAgregados() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        boolean registro = false;

        for (InsumoExpedienteDTO dto : getCrearInsumoDTO().getInsumosExpedienteDTO()) {
            if (dto.getFecetInsumo().isSelected()) {
                registro = true;
                break;
            }
        }

        if (registro) {
            getCrearInsumoDTO().setListaPropuesta(new ArrayList<FecetInsumo>());
            for (InsumoExpedienteDTO dto : getCrearInsumoDTO().getInsumosExpedienteDTO()) {
                if (dto.getFecetInsumo().isSelected()) {
                    dto.getFecetInsumo().setListaDocumentos(dto.getFecetDocExpInsumos());
                    getCrearInsumoDTO().getListaPropuesta().add(dto.getFecetInsumo());
                }
            }
            try {
                getCrearInsumoDTO().setInsumosProcesados(getCrearInsumoService().guardarInsumos(getCrearInsumoDTO().getListaPropuesta()));
                getCrearInsumoDTO().setListaInsumosRegistrados(getCrearInsumoDTO().getInsumosProcesados().getInsumosRegistrados());
                getCrearInsumoDTO().setListaInsumosNoRegistrados(getCrearInsumoDTO().getInsumosProcesados().getInsumosNoRegistrados());
                enviarCorreoCreacionInsumoCentral(getCrearInsumoDTO().getInsumosProcesados());
                enviarCorreoCreacionInsumoAdministrador(getCrearInsumoDTO().getInsumosProcesados());
            } catch (NegocioException e) {
                logger.error("No se pudo registrar los insumos agregados  [{}]  [{}]", e.getCause(), e);
                addErrorMessage(null, "No se pudo guardar la informacion");
            }
            getCrearInsumoDTO().setInsumosExpedienteDTO(new ArrayList<InsumoExpedienteDTO>());
            List<FecetInsumo> lista = getSeguimientoInsumosService().getListaSeguimientoInsumoEstatusRFCCreacion(Constantes.INSUMO_AGREGADO, getRFCSession(), obtenerGrupoUnidadesAdminXGeneral((EmpleadoDTO) getSession().getAttribute(ConstantesSesion.EMPLEADO_FIS_INTEGRAL)));
            for (FecetInsumo fecetInsumo : lista) {
                InsumoExpedienteDTO insumoExpedienteDTO = new InsumoExpedienteDTO();
                insumoExpedienteDTO.setFecetInsumo(fecetInsumo);
                insumoExpedienteDTO.setFecetDocExpInsumos(getSeguimientoInsumosService().obtenerDocumentos(fecetInsumo.getIdInsumo()));
                insumoExpedienteDTO.setNumeroExpedientes(insumoExpedienteDTO.getFecetDocExpInsumos().size());
                getCrearInsumoDTO().getInsumosExpedienteDTO().add(insumoExpedienteDTO);

            }
            getCrearInsumoDTO().setInsumosAgregados(getCrearInsumoDTO().getInsumosExpedienteDTO().size());

            requestContext.execute("PF('dlgInsumosRegistrados').show();");
        } else {
            FacesUtil.addErrorMessage(null, ERROR_NO_SELECCION_ELEMENTO, "");
        }

    }

    private boolean validaCargaInsumo() {
        boolean datosValidos = true;
        if (getCrearInsumoDTO().getContribuyente().getNombre() == null
                || getCrearInsumoDTO().getContribuyente().getNombre().equals("")) {
            addErrorMessage(TEXT_RFC, LEYENDA_CAMPO_OBLIGATORIO);
            datosValidos = false;
        }

        if (getCrearInsumoDTO().getPropuesta().getIdArace().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbUnidadAdministrativa", LEYENDA_CAMPO_OBLIGATORIO, LEYENDA_CAMPO_OBLIGATORIO);
            datosValidos = false;
        }

        if (getCrearInsumoDTO().getPropuesta().getIdSubprograma().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbSubprograma", LEYENDA_CAMPO_OBLIGATORIO, LEYENDA_CAMPO_OBLIGATORIO);
            datosValidos = false;
        }

        if (getCrearInsumoDTO().getPropuesta().getIdSector().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbSector", LEYENDA_CAMPO_OBLIGATORIO, LEYENDA_CAMPO_OBLIGATORIO);
            datosValidos = false;
        }

        if (getCrearInsumoDTO().getPropuesta().getIdTipoInsumo().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbTipoInsumo", LEYENDA_CAMPO_OBLIGATORIO, LEYENDA_CAMPO_OBLIGATORIO);
            datosValidos = false;
        }

        if (getCrearInsumoDTO().getPropuesta().getIdPrioridad().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formInsumo:cmbPrioridad", LEYENDA_CAMPO_OBLIGATORIO, LEYENDA_CAMPO_OBLIGATORIO);
            datosValidos = false;
        }

        if (!validarFechaInicial()) {
            datosValidos = false;
        }

        if (getCrearInsumoDTO().getListaDocumentosExpediente().isEmpty()) {
            addErrorMessage("formInsumo:fulExpediente", LEYENDA_CAMPO_OBLIGATORIO, MSG_NUMERO_DOCTOS);
            datosValidos = false;
        }

        return datosValidos;
    }

    private FececSector buscarSectorSeleccionado(final BigDecimal idSector) {
        FececSector sectorSeleccionado = new FececSector();
        for (FececSector sector : getCrearInsumoDTO().getListaSector()) {
            if (sector.getIdSector().equals(idSector)) {
                sectorSeleccionado = sector;
                break;
            }
        }

        return sectorSeleccionado;
    }

    private Boolean validarFechaInicial() {
        boolean datosValidos = true;

        if (getCrearInsumoDTO().getPropuesta().getFechaInicioPeriodo() == null
                || getCrearInsumoDTO().getPropuesta().getFechaFinPeriodo() == null) {
            addErrorMessage("formInsumo:txtFechaInicial", "Campo(s) Obligatorio(s)", "Campo(s) Obligatorio(s) *");
            datosValidos = false;
        } else {

            if (getCrearInsumoDTO().getPropuesta().getFechaInicioPeriodo().after(getCrearInsumoDTO().getPropuesta().getFechaFinPeriodo())) {
                addErrorMessage("formInsumo:txtFechaInicial", "La fecha de fin no puede ser menor que la inicial", "La fecha de fin no puede ser menor que la inicial");
                datosValidos = false;
            }
        }
        return datosValidos;
    }

    private FececSubprograma buscarSubprogramaSeleccionado(final BigDecimal idSubprograma) {
        FececSubprograma subprogramaSeleccionado = new FececSubprograma();
        for (FececSubprograma subprograma : getCrearInsumoDTO().getListaSubprograma()) {
            if (subprograma.getIdSubprograma().equals(idSubprograma)) {
                subprogramaSeleccionado = subprograma;
                break;
            }
        }

        return subprogramaSeleccionado;
    }

    private FececTipoInsumo buscarTipoInsumoSeleccionado(final BigDecimal idTipoInsumo) {
        FececTipoInsumo tipoInsumoeleccionado = new FececTipoInsumo();
        if (getCrearInsumoDTO().getListTipoInsumo() != null && !getCrearInsumoDTO().getListTipoInsumo().isEmpty()) {
            for (FececTipoInsumo tipoInsumo : getCrearInsumoDTO().getListTipoInsumo()) {
                if (tipoInsumo.getIdTipoInsumo().equals(idTipoInsumo)) {
                    tipoInsumoeleccionado = tipoInsumo;
                    break;
                }
            }
        }
        return tipoInsumoeleccionado;
    }

    public Boolean validaArchivoDuplicadoExpedienteInsumo(
            final List<FecetDocExpInsumo> listaDocumentos, final String nombreArchivo) {
        Boolean archivoDuplicado = false;
        for (FecetDocExpInsumo documento : listaDocumentos) {
            if (documento.getNombre().equals(nombreArchivo)) {
                archivoDuplicado = true;
                FacesUtil.addErrorMessage(null, ERROR_ARCHIVO_REPETIDO_CARGADO, "");
                break;
            }
        }

        return archivoDuplicado;
    }

    public Boolean validaTamanoExpedienteInsumo(
            final List<FecetDocExpInsumo> listaDocumentos) {

        if (listaDocumentos.size() > Constantes.TAM_MAXIMO_ARCHIVOS - Constantes.ENTERO_UNO) {
            FacesUtil.addErrorMessage(null, MSG_NUMERO_DOCTOS, "");
            return false;
        }
        return true;
    }

    public Boolean validaArchivoDuplicadoJustificacionInsumo(
            final List<FecetDocumento> listaDocumentos, final String nombreArchivo) {
        Boolean archivoDuplicado = false;
        for (FecetDocumento documento : listaDocumentos) {
            if (documento.getNombre().equals(nombreArchivo)) {
                archivoDuplicado = true;
                FacesUtil.addErrorMessage(null, ERROR_ARCHIVO_REPETIDO_CARGADO, "");

                break;
            }
        }

        return archivoDuplicado;
    }

    public Boolean validaTamanoJustificacionInsumo(
            final List<FecetDocumento> listaDocumentos) {

        if (listaDocumentos.size() > Constantes.TAM_MAXIMO_ARCHIVOS) {
            FacesUtil.addErrorMessage(null, MSG_NUMERO_DOCTOS, "");
            return false;
        }
        return true;
    }

    public void visualizaDetalle() {
        getCrearInsumoDTO().setListaDocumentosExpedienteDetalle(getCrearInsumoDTO().getListaDocumentosExpediente());
        getCrearInsumoDTO().setListaDocumentosJustificacion(getCrearInsumoService().bucarDocumentoJustificacionById(getCrearInsumoDTO().getInsumoExpendiente().getFecetInsumo().getIdInsumo()));
        getCrearInsumoDTO().setJustificacionDetalle(getCrearInsumoDTO().getInsumoExpendiente().getFecetInsumo().getJustificacion());
        if (getCrearInsumoDTO().getListaDocumentosJustificacion() != null && !getCrearInsumoDTO().getListaDocumentosJustificacion().isEmpty()) {
            getCrearInsumoDTO().setNumeroDeDocumentos(getCrearInsumoDTO().getListaDocumentosJustificacion().size());
        }
        getCrearInsumoDTO().setInsumoDetalleAuxiliar(getCrearInsumoDTO().getPropuesta());
        getCrearInsumoDTO().setContribuyenteDetalle(getCrearInsumoDTO().getContribuyente());
        getCrearInsumoDTO().setBloqueaPanelesDetalle(getCrearInsumoDTO().isBloquearPaneles());
        getCrearInsumoDTO().setListaDocumentosExpediente(getCrearInsumoDTO().getInsumoExpendiente().getFecetDocExpInsumos());
        getCrearInsumoDTO().setPropuesta(getCrearInsumoDTO().getInsumoExpendiente().getFecetInsumo());
        getCrearInsumoDTO().setContribuyente(getCrearInsumoDTO().getInsumoExpendiente().getFecetInsumo().getFecetContribuyente());
        getCrearInsumoDTO().setBloquearPaneles(false);
        getCrearInsumoDTO().setVisualizaDetalle(true);
        getCrearInsumoDTO().setBloquearRFC(true);
    }

    public void regresaDetalle() {
        getCrearInsumoDTO().setListaDocumentosExpediente(getCrearInsumoDTO().getListaDocumentosExpedienteDetalle());
        getCrearInsumoDTO().setPropuesta(getCrearInsumoDTO().getInsumoDetalleAuxiliar());
        getCrearInsumoDTO().setContribuyente(getCrearInsumoDTO().getContribuyenteDetalle());
        getCrearInsumoDTO().setBloquearPaneles(getCrearInsumoDTO().isBloqueaPanelesDetalle());

        getCrearInsumoDTO().setListaDocumentosBorrar(new ArrayList<FecetDocExpInsumo>());
        getCrearInsumoDTO().setVisualizaDetalle(false);
        getCrearInsumoDTO().setBloquearRFC(false);
        init();
    }

    public void visualizaAnexos() {
        logger.info("Inicia visualizacion de Anexos");
    }

    public StreamedContent getDescargarDocumento() {
        FecetDocExpInsumo archivo = getCrearInsumoDTO().getArchivoDescarga();
        StreamedContent descarga = null;
        if (archivo != null && archivo.getArchivo() != null) {
            byte[] archivoBytes = lecturaArchivo(archivo.getArchivo());
            archivo.setArchivo(new ByteArrayInputStream(archivoBytes));
            descarga = new DefaultStreamedContent(new ByteArrayInputStream(archivoBytes),
                    VistaArchivoInsumoUtil.obtenContentTypeArchivoPropuesta(archivo.getNombre()),
                    VistaArchivoInsumoUtil.aplicarCodificacionTexto(archivo.getNombre()));
        } else if (archivo != null && archivo.getRutaArchivo() != null && archivo.getNombre() != null) {
            String ruta = archivo.getRutaArchivo().replace(archivo.getNombre(), "").concat(archivo.getNombre());
            descarga = getDescargaArchivo(ruta, archivo.getNombre());
        }
        return descarga;
    }

    public StreamedContent getDescargaArchivo() {
        return getDescargaArchivo(getCrearInsumoDTO().getRutaArchivoDescargable() + getCrearInsumoDTO().getNombreArchivoDescargable(), getCrearInsumoDTO().getNombreArchivoDescargable());
    }

    public void guardarDocumentoJustificacion() {
        if (getCrearInsumoDTO().getListaDocumentosJustificacion() != null && !getCrearInsumoDTO().getListaDocumentosJustificacion().isEmpty() && getCrearInsumoDTO().getJustificacion() != null && !getCrearInsumoDTO().getJustificacion().trim().equals("")) {
            if (getCrearInsumoDTO().getDesplegarDialogo() == Constantes.ENTERO_UNO) {
                reservarInsumo();
            }
            if (getCrearInsumoDTO().getDesplegarDialogo() == Constantes.ENTERO_DOS) {
                realizarActualizacion();
            }
            getCrearInsumoDTO().setDesplegarDialogo(Constantes.ENTERO_CERO);
            RequestContext.getCurrentInstance().execute("PF('dialogJustificacionCoincidencias').hide();");
            RequestContext.getCurrentInstance().update("formContribuyente:panelContribuyente");
            RequestContext.getCurrentInstance().update("formInsumo");
            RequestContext.getCurrentInstance().update("formAgregados");
        } else {
            if (getCrearInsumoDTO().getListaDocumentosJustificacion() == null || getCrearInsumoDTO().getListaDocumentosJustificacion().isEmpty()) {
                addErrorMessage("formAgregados:justificacionExpediente", "", MSG_NUMERO_DOCTOS);
            }
            if (getCrearInsumoDTO().getJustificacion() == null || getCrearInsumoDTO().getJustificacion().trim().equals("")) {
                addErrorMessage("formAgregados:txtJustificacion", "", "Campo(s) Obligatorio(s) *");
            }
            RequestContext.getCurrentInstance().update("formAgregados:msgTxtJustificacion");
            RequestContext.getCurrentInstance().update("formAgregados:msgTxtJustificacionExpediente");
        }

    }

    public void desplegarJustificacion() {
        RequestContext.getCurrentInstance().execute("PF('dialogJustificacion').show();");
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

}
