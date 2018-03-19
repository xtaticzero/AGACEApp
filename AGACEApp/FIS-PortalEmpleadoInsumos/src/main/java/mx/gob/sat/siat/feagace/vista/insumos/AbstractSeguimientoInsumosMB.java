package mx.gob.sat.siat.feagace.vista.insumos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.bean.ManagedProperty;

import org.primefaces.context.RequestContext;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaMediosContactoService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.insumos.CrearInsumoService;
import mx.gob.sat.siat.feagace.negocio.insumos.SeguimientoInsumosService;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.RetroalimentacionInsumo;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.model.insumos.SegInsumosDTO;
import mx.gob.sat.siat.feagace.vista.model.insumos.SegInsumosListDTO;
import org.primefaces.model.StreamedContent;

public class AbstractSeguimientoInsumosMB extends AbstractManagedBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{consultaMediosContactoService}")
    private transient ConsultaMediosContactoService consultaMediosContactoService;

    @ManagedProperty(value = "#{crearInsumoService}")
    private transient CrearInsumoService crearInsumoService;

    @ManagedProperty(value = "#{seguimientoInsumosService}")
    private transient SeguimientoInsumosService seguimientoInsumosService;

    private SegInsumosDTO segInsumosDTO = new SegInsumosDTO();
    private SegInsumosListDTO segInsumosListDTO = new SegInsumosListDTO();

    private List<FecetRetroalimentacionInsumo> insumosRetroalimentadosContador;
    private List<FecetDocretroinsumo> listaDocRetroInsumo;
    private List<FecetRetroalimentacionInsumo> insumosRetroalimentadoCargado;

    private FecetRetroalimentacionInsumo retroalimentacionInsumo;
    private FecetDocretroinsumo docretroInsumoCargadoSeleccionado;
    private boolean mostrarTablaArchivosRetro;
    private int numeroDocumentosJustificacion;
    private String justificacion;
    private String rutaArchivoDescargable;
    private String nombreArchivoDescargable;

    public final void mostrarHistoricoRetroalimentacion() {
        setInsumosRetroalimentadosContador(new ArrayList<FecetRetroalimentacionInsumo>());
        docretroInsumoCargadoSeleccionado = new FecetDocretroinsumo();

        this.mostrarTablaArchivosRetro = false;
        getSegInsumosDTO().setMostrarTablaArchivosRetroRechazos(false);

        setInsumosRetroalimentadosContador(seguimientoInsumosService.getInsumosRetroalimentados(this.segInsumosDTO.getInsumoSeleccionado()));
    }

    public final void visualizaDocRetroalimentacionAciace() {
        setListaDocRetroInsumo(seguimientoInsumosService.getDocumentosRetroalimentadosByIdRetroInsumo(retroalimentacionInsumo.getIdRetroalimentacionInsumo(), Constantes.USUARIO_INSUMOS));
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('DocumentosSolicitudRetroInsumo').show();");
    }

    public final void visualizaRetroalimentacionSubAdmin() {
        setInsumosRetroalimentadoCargado(new ArrayList<FecetRetroalimentacionInsumo>());
        getInsumosRetroalimentadoCargado().add(getRetroalimentacionInsumo());
        setListaDocRetroInsumo(seguimientoInsumosService.getDocumentosRetroalimentadosByIdRetroInsumo(getRetroalimentacionInsumo().getIdRetroalimentacionInsumo(), Constantes.USUARIO_VALIDADOR_INSUMOS));
        if (getListaDocRetroInsumo() != null && !getListaDocRetroInsumo().isEmpty()) {
            getInsumosRetroalimentadoCargado().get(0).setNumeroSolicitudesRetro(new BigDecimal(getListaDocRetroInsumo().size()));
        } else {
            getInsumosRetroalimentadoCargado().get(0).setNumeroSolicitudesRetro(BigDecimal.ZERO);
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('historicoSolicitudRetroInsumo').show();");
    }

    public final void visualizaDocRetroalimentacionSubAdmin() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('DocumentosSolicitudRetroInsumo').show();");
    }

    public final void limpiaMostrarArchivosRetroalimentacion() {
        setMostrarTablaArchivosRetro(false);
    }

    protected void enviarCorreoCreacionInsumo(FecetInsumo insumo) throws NegocioException {
        Set<String> destinatarios = new TreeSet<String>();

        getNotificacionService().obtenerCorreoEmpleado(insumo.getRfcCreacion(), Constantes.USUARIO_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(insumo.getRfcAdministrador(), Constantes.USUARIO_ASIGNADOR_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(insumo.getRfcSubadministrador(), Constantes.USUARIO_VALIDADOR_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);

        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, insumo.getIdUnidadAdministrativa(), destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_ASIGNADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_VALIDADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);

        Map<String, String> data = getNotificacionService().obtenerDatosCorreo(Constantes.RETRO_ATENDIDA);
        data.put(Common.ID_REGISTRO.toString(), insumo.getIdRegistro());
        data.put(RetroalimentacionInsumo.RFC.toString(), insumo.getFecetContribuyente().getRfc());
        try {
            getNotificacionService().enviarNotificacionGenerico(data, ReportType.AVISOS_INSUMO_GENERICO_RFC, destinatarios);
        } catch (BusinessException ex) {
            logger.error("Error al enviar el correo [{}]", ex.getCause());
        }
    }

    public final List<FecetRetroalimentacionInsumo> getInsumosRetroalimentadosContador() {
        return insumosRetroalimentadosContador;
    }

    public final void setInsumosRetroalimentadosContador(
            List<FecetRetroalimentacionInsumo> insumosRetroalimentadosContador) {
        this.insumosRetroalimentadosContador = insumosRetroalimentadosContador;
    }

    public final List<FecetDocretroinsumo> getListaDocRetroInsumo() {
        return listaDocRetroInsumo;
    }

    public final void setListaDocRetroInsumo(List<FecetDocretroinsumo> listaDocRetroInsumo) {
        this.listaDocRetroInsumo = listaDocRetroInsumo;
    }

    public final List<FecetRetroalimentacionInsumo> getInsumosRetroalimentadoCargado() {
        return insumosRetroalimentadoCargado;
    }

    public final void setInsumosRetroalimentadoCargado(List<FecetRetroalimentacionInsumo> insumosRetroalimentadoCargado) {
        this.insumosRetroalimentadoCargado = insumosRetroalimentadoCargado;
    }

    public final FecetRetroalimentacionInsumo getRetroalimentacionInsumo() {
        return retroalimentacionInsumo;
    }

    public final void setRetroalimentacionInsumo(FecetRetroalimentacionInsumo retroalimentacionInsumo) {
        this.retroalimentacionInsumo = retroalimentacionInsumo;
    }

    public final FecetDocretroinsumo getDocretroInsumoCargadoSeleccionado() {
        return docretroInsumoCargadoSeleccionado;
    }

    public final void setDocretroInsumoCargadoSeleccionado(FecetDocretroinsumo docretroInsumoCargadoSeleccionado) {
        this.docretroInsumoCargadoSeleccionado = docretroInsumoCargadoSeleccionado;
    }

    public final boolean isMostrarTablaArchivosRetro() {
        return mostrarTablaArchivosRetro;
    }

    public final void setMostrarTablaArchivosRetro(boolean mostrarTablaArchivosRetro) {
        this.mostrarTablaArchivosRetro = mostrarTablaArchivosRetro;
    }

    public final SeguimientoInsumosService getSeguimientoInsumosService() {
        return seguimientoInsumosService;
    }

    public final SegInsumosDTO getSegInsumosDTO() {
        return segInsumosDTO;
    }

    public final void setSegInsumosDTO(SegInsumosDTO segInsumosDTO) {
        this.segInsumosDTO = segInsumosDTO;
    }

    public final SegInsumosListDTO getSegInsumosListDTO() {
        return segInsumosListDTO;
    }

    public final void setSegInsumosListDTO(SegInsumosListDTO segInsumosListDTO) {
        this.segInsumosListDTO = segInsumosListDTO;
    }

    public final void setSeguimientoInsumosService(SeguimientoInsumosService seguimientoInsumosService) {
        this.seguimientoInsumosService = seguimientoInsumosService;
    }

    public final ConsultaMediosContactoService getConsultaMediosContactoService() {
        return consultaMediosContactoService;
    }

    public final void setConsultaMediosContactoService(ConsultaMediosContactoService consultaMediosContactoService) {
        this.consultaMediosContactoService = consultaMediosContactoService;
    }

    public final CrearInsumoService getCrearInsumoService() {
        return crearInsumoService;
    }

    public final void setCrearInsumoService(CrearInsumoService crearInsumoService) {
        this.crearInsumoService = crearInsumoService;
    }

    public int getNumeroDocumentosJustificacion() {
        return numeroDocumentosJustificacion;
    }

    public void setNumeroDocumentosJustificacion(int numeroDocumentosJustificacion) {
        this.numeroDocumentosJustificacion = numeroDocumentosJustificacion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getRutaArchivoDescargable() {
        return rutaArchivoDescargable;
    }

    public void setRutaArchivoDescargable(String rutaArchivoDescargable) {
        this.rutaArchivoDescargable = rutaArchivoDescargable;
    }

    public String getNombreArchivoDescargable() {
        return nombreArchivoDescargable;
    }

    public void setNombreArchivoDescargable(String nombreArchivoDescargable) {
        this.nombreArchivoDescargable = nombreArchivoDescargable;
    }

    public StreamedContent getDescargaArchivo() {
        return getDescargaArchivo(rutaArchivoDescargable + nombreArchivoDescargable, nombreArchivoDescargable);
    }

}
