package mx.gob.sat.siat.feagace.vista.insumos;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececTipoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumos;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumosRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilInsumos;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

@ViewScoped
@ManagedBean(name = "seguimientoInsumoManagedBean")
public class SeguimientoInsumosMB extends AbstractSeguimientoInsumosMB {

    private static final long serialVersionUID = 1L;
    private static final BigDecimal ESTATUS_RETROALIMENTADO = BigDecimal.valueOf(3);

    public SeguimientoInsumosMB() {
        super();
    }

    @PostConstruct
    public void init() {
        getSegInsumosDTO().setMuestraInsumosRetroalimentados(false);
        getSegInsumosDTO().setAtenderRetroalimentacion(false);
        getSegInsumosDTO().setDocumentoSeleccionadoRetro(new FecetContadorInsumos());
        getSegInsumosDTO().setDocumentoSeleccionadoRetroRechazo(new FecetContadorInsumos());
        getSegInsumosDTO().setDocretroInsumoCargadoSeleccionado(new FecetDocretroinsumo());
        getSegInsumosDTO().setDocretroInsumoCargadoSeleccionadoPendiente(new FecetDocretroinsumo());
        getSegInsumosDTO().setDocrechazoCargadoSeleccionado(new FecetDocrechazoinsumo());
        getSegInsumosDTO().setDocumentoSeleccionadoRechazo(new FecetContadorInsumosRechazados());

        getSegInsumosDTO().setMostrarTablaArchivosRetroRechazos(false);
        try {
            getSegInsumosDTO().setFececEmpleado(obtenerEmpleadoAcceso(getRFCSession(), TipoEmpleadoEnum.USUARIO_INSUMOS));
            //Id de General
            getSegInsumosListDTO().setListaSubprograma(getCrearInsumoService().getCatalogoSubprograma(new BigDecimal(getSegInsumosDTO().getFececEmpleado().getIdAdmGral())));
            getSegInsumosListDTO().setListaSector(getCrearInsumoService().getCatalogoSector(new BigDecimal(getSegInsumosDTO().getFececEmpleado().getIdAdmGral())));
            getSegInsumosListDTO().setListaTipoInsumo(getCrearInsumoService().getCatalogoTipoInsumo(new BigDecimal(getSegInsumosDTO().getFececEmpleado().getIdAdmGral())));
            cargaInsumosPorSeguimiento();
            getSegInsumosListDTO().setListaDocumentoJustificacion(new ArrayList<FecetDocumento>());
            setNumeroDocumentosJustificacion(Constantes.ENTERO_CERO);
            setJustificacion("");
        } catch (NoExisteEmpleadoException e) {
            logger.error("No se pudo cargar la informacion basica de la pagina", e);
            informeErrorSession(e);
        }

    }

    public void reseteaDataTables() {
        limpiaFiltros("formPrincipal:tabView:tablaInsumosSeguimiento");
        limpiaFiltros("formPrincipal:tabView:tablaInsumosSeguimiento1");
        limpiaFiltros("formPrincipal:tabView:tablaInsumosSeguimiento2");
        getSegInsumosListDTO().setFiltrados(null);
        init();
    }

    protected void cargaInsumosPorSeguimiento() {
        getSegInsumosListDTO().setInsumos(new ArrayList<List<FecetInsumo>>());
        getSegInsumosListDTO().setInsumoCreado(getSeguimientoInsumosService().getListaSeguimientoInsumoRFCCreacion(
                getSegInsumosDTO().getFececEmpleado().getRfc(), obtenerGrupoUnidadesAdminXGeneral(getSegInsumosDTO().getFececEmpleado()), Constantes.INSUMO_CREADO,
                Constantes.INSUMO_ASIGNADO_A_SUBADMINISTRADOR, Constantes.ACEPTACION_REASIGNACION_A_ADMINISTRADOR,
                Constantes.RECHAZO_REASIGNACION_A_ADMINISTRADOR, Constantes.INSUMO_ACEPTADO));
        getSegInsumosListDTO().getInsumos().add(getSegInsumosListDTO().getInsumoCreado());
        getSegInsumosListDTO().setInsumoPorRetroAlimentar(getSeguimientoInsumosService().getListaSeguimientoInsumoEstatusRFCCreacion(Constantes.INSUMO_POR_RETROALIMENTAR, getSegInsumosDTO().getFececEmpleado().getRfc(), obtenerGrupoUnidadesAdminXGeneral(getSegInsumosDTO().getFececEmpleado())));
        getSegInsumosListDTO().getInsumos().add(getSegInsumosListDTO().getInsumoPorRetroAlimentar());
        getSegInsumosListDTO().setInsumoRechazado(getSeguimientoInsumosService().getListaSeguimientoInsumoEstatusRFCCreacion(Constantes.INSUMO_RECHAZADO, getSegInsumosDTO().getFececEmpleado().getRfc(), obtenerGrupoUnidadesAdminXGeneral(getSegInsumosDTO().getFececEmpleado())));
        getSegInsumosListDTO().getInsumos().add(getSegInsumosListDTO().getInsumoRechazado());

    }

    /**
     * Metodo utilizado para guardar el documento dentro del file system y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event Evento de carga temporal de archivo.
     */
    public void cargaArchivoExpediente(final FileUploadEvent event) {
        if (validaArchivoCargaInsumoPropuesta(event.getFile())) {
            try {
                FecetDocretroinsumo documento = new FecetDocretroinsumo();
                documento.setIdRetroalimentacionInsumo(
                        getSegInsumosDTO().getDocumentoSeleccionadoRetro().getIdRetroalimentacionInsumo());
                documento.setFechaCreacion(new Date());
                documento.setNombreArchivo(VistaArchivoInsumoUtil
                        .limpiarPathArchivo(VistaArchivoInsumoUtil.aplicarCodificacionTexto(event.getFile().getFileName())));
                documento.setRutaArchivo(
                        RutaArchivosUtilInsumos.armarRutaDestinoInsumo(getSegInsumosDTO().getInsumoSeleccionado()));
                documento.setIdTipoEmpleado(BigDecimal.valueOf(TipoEmpleadoEnum.USUARIO_INSUMOS.getId()));
                if (validaNombreArchivo(getSegInsumosListDTO().getListaDocretroInsumoCargados(),
                        documento.getNombreArchivo())) {
                    FacesUtil.addErrorMessage(null, "El archivo ya fue cargado", "Verifique por favor");
                } else {
                    documento.setArchivo(event.getFile().getInputstream());
                    getSegInsumosListDTO().getListaDocretroInsumoCargados().add(documento);
                    FacesUtil.addInfoMessage(null, Constantes.ARCHIVO_CARGADO, "");
                }
            } catch (IOException e) {
                logger.error("No se pudo adjuntar el documento ", e);
            }
        }
    }

    public void descartarDocumento() {
        Iterator<FecetDocretroinsumo> iterador = getSegInsumosListDTO().getListaDocretroInsumoCargados().iterator();
        FecetDocretroinsumo documento;
        while (iterador.hasNext()) {
            documento = iterador.next();
            if (getSegInsumosDTO().getDocretroInsumoCargadoSeleccionado().getNombreArchivo().equals(documento.getNombreArchivo())) {
                iterador.remove();
                break;
            }
        }
        FacesUtil.addInfoMessage(null, "Se descarto el documento correctamente.", "");
    }

    public boolean isMuestraEstatus() {
        getSegInsumosDTO().setMuestraEstatus(false);

        if (getSegInsumosDTO().getInsumoSeleccionado() == null) {
            getSegInsumosDTO().setMuestraEstatus(false);
            return getSegInsumosDTO().isMuestraEstatus();
        }
        BigDecimal estatus = getSegInsumosDTO().getInsumoSeleccionado().getFececEstatus().getIdEstatus();

        if (estatus.equals(Constantes.INSUMO_POR_RETROALIMENTAR)) {
            getSegInsumosDTO().setMuestraEstatus(true);
        } else {
            getSegInsumosDTO().setMuestraEstatus(false);
        }
        return getSegInsumosDTO().isMuestraEstatus();
    }

    private boolean validaNombreArchivo(List<FecetDocretroinsumo> listaDocumento, String nombre) {
        Boolean archivoDuplicado = false;
        for (FecetDocretroinsumo fecetDocExpInsumo : listaDocumento) {
            if (fecetDocExpInsumo.getNombreArchivo().equals(nombre)) {
                archivoDuplicado = true;
                break;
            }
        }
        return archivoDuplicado;
    }

    public void consultaRFC() {
        getSegInsumosDTO().setMuestraSeccion(false);
        getSegInsumosListDTO().setRechazoInsumos(new ArrayList<FecetRechazoInsumo>());
        getSegInsumosListDTO().setListaDocumento(new ArrayList<FecetDocExpInsumo>());
        getSegInsumosListDTO().setListaDocumento(getSeguimientoInsumosService().obtenerDocumentosAsignados(getSegInsumosDTO().getInsumoSeleccionado()));
        getSegInsumosDTO().setFecetRechazoInsumoSeleaccionado(getSeguimientoInsumosService().getFecetRechazoByInsumo(getSegInsumosDTO().getInsumoSeleccionado().getIdInsumo()));
        getSegInsumosListDTO().setListaDocretroRechazoInsumoCargados(getSeguimientoInsumosService()
                .getInsumosRetroalimentadosFlujoPrincipal(getSegInsumosDTO().getInsumoSeleccionado()));
        getSegInsumosListDTO().setListaDocumentoJustificacion(getSeguimientoInsumosService().buscarDocumentoJustificacion(getSegInsumosDTO().getInsumoSeleccionado()));
        setNumeroDocumentosJustificacion(getSegInsumosListDTO().getListaDocumentoJustificacion() != null ? getSegInsumosListDTO().getListaDocumentoJustificacion().size() : Constantes.ENTERO_CERO);
        setJustificacion(getSegInsumosDTO().getInsumoSeleccionado().getJustificacion());
        if (getSegInsumosDTO().getFecetRechazoInsumoSeleaccionado() != null) {
            getSegInsumosListDTO().getRechazoInsumos().add(getSegInsumosDTO().getFecetRechazoInsumoSeleaccionado());
        }
        llenarTipoInsumo();
    }

    public void consultaRFCRechazo() {
        getSegInsumosDTO().setMuestraSeccion(false);
        getSegInsumosListDTO().setRechazoInsumos(new ArrayList<FecetRechazoInsumo>());
        getSegInsumosListDTO().setListaDocumento(new ArrayList<FecetDocExpInsumo>());
        getSegInsumosListDTO().setListaDocumento(getSeguimientoInsumosService().obtenerDocumentosRechazados(getSegInsumosDTO().getInsumoSeleccionado()));
        getSegInsumosDTO().setFecetRechazoInsumoSeleaccionado(getSeguimientoInsumosService().getFecetRechazoByInsumo(getSegInsumosDTO().getInsumoSeleccionado().getIdInsumo()));
        getSegInsumosListDTO().setListaDocretroRechazoInsumoCargados(getSeguimientoInsumosService().getInsumosRetroalimentadosFlujoPrincipal(getSegInsumosDTO().getInsumoSeleccionado()));
        getSegInsumosListDTO().setListaDocumentoJustificacion(getSeguimientoInsumosService().buscarDocumentoJustificacion(getSegInsumosDTO().getInsumoSeleccionado()));
        setNumeroDocumentosJustificacion(getSegInsumosListDTO().getListaDocumentoJustificacion() != null ? getSegInsumosListDTO().getListaDocumentoJustificacion().size() : Constantes.ENTERO_CERO);
        setJustificacion(getSegInsumosDTO().getInsumoSeleccionado().getJustificacion());

        if (getSegInsumosDTO().getFecetRechazoInsumoSeleaccionado() != null) {
            getSegInsumosListDTO().getRechazoInsumos().add(getSegInsumosDTO().getFecetRechazoInsumoSeleaccionado());
        }
        llenarTipoInsumo();
    }

    public void consultaRFCRetroalimentacion() {
        getSegInsumosDTO().setMuestraSeccion(false);
        getSegInsumosListDTO().setRetroalimentacionInsumo(new ArrayList<FecetRetroalimentacionInsumo>());
        getSegInsumosListDTO().setListaDocumento(new ArrayList<FecetDocExpInsumo>());
        getSegInsumosListDTO().setListaDocumento(getSeguimientoInsumosService().obtenerDocumentosRtroalimentados(getSegInsumosDTO().getInsumoSeleccionado().getIdInsumo()));
        getSegInsumosListDTO().setListaContadoresInsumoRetroPendiente(getSeguimientoInsumosService().getInsumosPorRetroalimentar(getSegInsumosDTO().getInsumoSeleccionado().getIdInsumo(), Constantes.INSUMO_ASIGNADO_A_SUBADMINISTRADOR));
        getSegInsumosDTO().setDocumentoSeleccionadoRetro(getSegInsumosListDTO().getListaContadoresInsumoRetroPendiente().get(0));
        getSegInsumosDTO().getDocumentoSeleccionadoRetro().setFechaRetroalimentacion(new Date());
        getSegInsumosListDTO().setListaDocumentoJustificacion(getSeguimientoInsumosService().buscarDocumentoJustificacion(getSegInsumosDTO().getInsumoSeleccionado()));
        setNumeroDocumentosJustificacion(getSegInsumosListDTO().getListaDocumentoJustificacion() != null ? getSegInsumosListDTO().getListaDocumentoJustificacion().size() : Constantes.ENTERO_CERO);
        setJustificacion(getSegInsumosDTO().getInsumoSeleccionado().getJustificacion());

        if (getSegInsumosDTO().getRetroalimentacionSeleaccionada() != null) {
            getSegInsumosListDTO().getRetroalimentacionInsumo().add(getSegInsumosDTO().getRetroalimentacionSeleaccionada());
        }
        llenarTipoInsumo();
    }

    private void llenarTipoInsumo() {
        if (getSegInsumosListDTO().getListaTipoInsumo() != null && !getSegInsumosListDTO().getListaTipoInsumo().isEmpty()) {
            for (FececTipoInsumo fececTipoInsumo : getSegInsumosListDTO().getListaTipoInsumo()) {
                if (fececTipoInsumo.getIdTipoInsumo().equals(getSegInsumosDTO().getInsumoSeleccionado().getIdTipoInsumo())) {
                    getSegInsumosDTO().getInsumoSeleccionado().setFececTipoInsumo(fececTipoInsumo);
                    break;
                }
            }
        }
    }

    public void atenderRetroalimentacion() {
        getSegInsumosDTO().setTxtMotivoRetro("");
        getSegInsumosDTO().setAtenderRetroalimentacion(false);
        if (validaCargaInsumo(true)) {
            getSegInsumosDTO().setAtenderRetroalimentacion(true);
            getSegInsumosListDTO().setListaDocretroInsumoCargados(new ArrayList<FecetDocretroinsumo>());
            getSegInsumosListDTO()
                    .setListaDocretroInsumoCargados(getSeguimientoInsumosService().getDocumentosRetroalimentadosEstatus(getSegInsumosDTO().getDocumentoSeleccionadoRetro().getIdRetroalimentacionInsumo(),
                                    Constantes.USUARIO_INSUMOS));
        }
    }

    public void atenderRetroalimentacionConAntecedentes() {
        getSegInsumosDTO().setTxtMotivoRetro("");
        getSegInsumosDTO().setAtenderRetroalimentacion(true);
        getSegInsumosListDTO().setListaDocretroInsumoCargados(new ArrayList<FecetDocretroinsumo>());
        getSegInsumosListDTO()
                .setListaDocretroInsumoCargados(getSeguimientoInsumosService().getDocumentosRetroalimentadosEstatus(getSegInsumosDTO().getDocumentoSeleccionadoRetro().getIdRetroalimentacionInsumo(),
                                Constantes.USUARIO_INSUMOS));
    }

    public void fechaInicioEvent(SelectEvent event) {
        getSegInsumosDTO().getInsumoSeleccionado().setFechaInicioPeriodo((Date) event.getObject());
    }

    public void fechaFinEvent(SelectEvent event) {
        getSegInsumosDTO().getInsumoSeleccionado().setFechaFinPeriodo((Date) event.getObject());
    }

    public void asignarInsumoConfirm() {
        if (validaCargaInsumo(false)) {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('confirmarRetroalimentacionFinal').show()");
        }
    }

    public void asignarInsumo() {
        try {
            getSegInsumosDTO().getInsumoSeleccionado().setListaDocumentos(getSegInsumosListDTO().getListaDocumento());
            getSegInsumosDTO().getInsumoSeleccionado().setRfcCreacion(getRFCSession());
            getSegInsumosDTO().getInsumoSeleccionado().setIdEstatus(ESTATUS_RETROALIMENTADO);
            getSeguimientoInsumosService().guardarDocumentosFaltantes(getSegInsumosListDTO().getListaDocretroInsumoCargados());
            getSeguimientoInsumosService().actualizaInsumo(getSegInsumosDTO().getInsumoSeleccionado());
            getSeguimientoInsumosService().actualizaEstatusRetroalimentacionInsumo(getSegInsumosDTO().getInsumoSeleccionado(),
                    getSegInsumosDTO().getDocumentoSeleccionadoRetro().getIdRetroalimentacionInsumo(),
                    getSegInsumosDTO().getTxtMotivoRetro(),
                    getSegInsumosDTO().getDocumentoSeleccionadoRetro().getFechaRetroalimentacion());

            enviarCorreoCreacionInsumo(getSegInsumosDTO().getInsumoSeleccionado());

            getSegInsumosDTO().setMuestraSeccion(true);
            FacesUtil.addInfoMessage(null, FacesUtil.getMessageResourceString("exito.guardado"),
                    String.format("%s %s %s",
                            FacesUtil.getMessageResourceString("msg.exito.guardar.retroalimentacion.parte1"),
                            getSegInsumosDTO().getInsumoSeleccionado().getIdRegistro(),
                            FacesUtil.getMessageResourceString("msg.exito.guardar.retroalimentacion.parte2")));

            reseteaDataTables();
        } catch (Exception e) {
            logger.error("No se pudo obtener la lista de Subprogramas " + e.getCause(), e);
        }
    }

    public void mostrarHistoricoRechazo() {
        getSegInsumosListDTO().setInsumosRetroalimentadosRechazosContador(new ArrayList<FecetContadorInsumos>());
        getSegInsumosDTO().setMuestraInsumosRetroalimentados(false);
        getSegInsumosDTO().setMostrarTablaArchivosRetro(false);
        getSegInsumosDTO().setMostrarTablaArchivosRetroRechazos(true);

        getSegInsumosListDTO().setListaContadorRechazo(getSeguimientoInsumosService()
                .getContadorRechazo(getSegInsumosDTO().getInsumoSeleccionado().getIdInsumo()));
        setInsumosRetroalimentadosContador(
                getSeguimientoInsumosService().getInsumosRetroalimentados(getSegInsumosDTO().getInsumoSeleccionado()));

    }

    public List<String> getEstatusRegistrados() {
        List<FecetInsumo> registros = getSegInsumosListDTO().getInsumoCreado();
        List<String> estatus = new ArrayList<String>();
        if (registros != null && !registros.isEmpty()) {
            for (FecetInsumo registro : registros) {
                if (!estatus.contains(registro.getFececEstatus().getDescripcion())) {
                    estatus.add(registro.getFececEstatus().getDescripcion());
                }
            }
        }
        return estatus;
    }

    public void mostrarArchivosRetroPendientes() {
        getSegInsumosListDTO().setListaDocretroPendientes(new ArrayList<FecetDocretroinsumo>());
        getSegInsumosListDTO().setListaDocretroPendientes(getSeguimientoInsumosService().getDocumentosRetroalimentados(
                getSegInsumosDTO().getDocumentoSeleccionadoRetro().getIdRetroalimentacionInsumo()));
    }

    public void mostrarArchivosRechazo() {
        getSegInsumosListDTO().setListaDocRechazoInsumo(new ArrayList<FecetDocrechazoinsumo>());
        getSegInsumosListDTO().setListaDocRechazoInsumo(getSeguimientoInsumosService()
                .getDocumentosRechazados(getSegInsumosDTO().getDocumentoSeleccionadoRechazo().getIdRechazoInsumo()));
    }

    private boolean validaCargaInsumo(boolean faseUno) {
        boolean datosValidos = true;
        if (faseUno) {
            if (getSegInsumosDTO().getInsumoSeleccionado().getIdArace().equals(BigDecimal.valueOf(-1L))) {
                FacesUtil.addErrorMessage(null, "Seleccione Unidad Administrativa");
                datosValidos = false;
            } else if (getSegInsumosDTO().getInsumoSeleccionado().getIdSubprograma().equals(BigDecimal.valueOf(-1L))) {
                FacesUtil.addErrorMessage(null, "Seleccione Subprograma");
                datosValidos = false;
            } else if (getSegInsumosDTO().getInsumoSeleccionado().getIdSector().equals(BigDecimal.valueOf(-1L))) {
                FacesUtil.addErrorMessage(null, "Seleccione Sector");
                datosValidos = false;
            } else if (getSegInsumosDTO().getInsumoSeleccionado().getIdPrioridad().equals(BigDecimal.valueOf(-1L))) {
                FacesUtil.addErrorMessage(null, "Seleccione Prioridad");
                datosValidos = false;
            } else if (getSegInsumosDTO().getInsumoSeleccionado().getFechaInicioPeriodo() == null
                    || getSegInsumosDTO().getInsumoSeleccionado().getFechaFinPeriodo() == null) {
                FacesUtil.addErrorMessage(null, "Seleccione un per\u00edodo");
                datosValidos = false;
            } else if (getSegInsumosDTO().getInsumoSeleccionado().getFechaInicioPeriodo()
                    .after(getSegInsumosDTO().getInsumoSeleccionado().getFechaFinPeriodo())) {
                FacesUtil.addErrorMessage(null, "La fecha final no puede ser menor a la inicial");
                datosValidos = false;
            }
        } else {
            if (getSegInsumosListDTO().getListaDocretroInsumoCargados() == null
                    || getSegInsumosListDTO().getListaDocretroInsumoCargados().isEmpty()) {
                FacesUtil.addErrorMessage(null, "Debe cargar un documento", "");
                datosValidos = false;
            } else if (getSegInsumosDTO().getTxtMotivoRetro() == null
                    || getSegInsumosDTO().getTxtMotivoRetro().trim().isEmpty()) {
                FacesUtil.addErrorMessage(null, "Ingrese un motivo de retroalimentaci\u00f3n", "");
                datosValidos = false;
            }
        }

        return datosValidos;
    }

    public StreamedContent getArchivoDescargaRetroalimentacion() {
        StreamedContent archivoDescargaRetroalimentacion = null;
        if (getDocretroInsumoCargadoSeleccionado().getRutaArchivo() != null
                && getDocretroInsumoCargadoSeleccionado().getNombreArchivo() != null) {
            String ruta = getDocretroInsumoCargadoSeleccionado().getRutaArchivo();
            ruta = ruta.replace(getDocretroInsumoCargadoSeleccionado().getNombreArchivo(), "")
                    .concat(getDocretroInsumoCargadoSeleccionado().getNombreArchivo());
            archivoDescargaRetroalimentacion = getDescargaArchivo(ruta,
                    getDocretroInsumoCargadoSeleccionado().getNombreArchivo());
        }
        return archivoDescargaRetroalimentacion;
    }

    public StreamedContent getArchivoDescargaRechazo() {
        return getDescargaArchivo(
                getSegInsumosDTO().getDocrechazoCargadoSeleccionado().getRutaArchivo()
                + getSegInsumosDTO().getDocrechazoCargadoSeleccionado().getNombreArchivo(),
                getSegInsumosDTO().getDocrechazoCargadoSeleccionado().getNombreArchivo());
    }

    public StreamedContent getArchivoDescargable() {
        return getDescargaArchivo(
                getSegInsumosDTO().getDocSeleccionado().getRutaArchivo()
                .concat(getSegInsumosDTO().getDocSeleccionado().getNombre()),
                getSegInsumosDTO().getDocSeleccionado().getNombre());
    }
}
