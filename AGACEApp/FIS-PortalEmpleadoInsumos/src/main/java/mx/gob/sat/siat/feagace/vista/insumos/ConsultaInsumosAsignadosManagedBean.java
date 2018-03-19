package mx.gob.sat.siat.feagace.vista.insumos;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorAsignadosAdministradorEstado;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.ReglaEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;

@ViewScoped
@ManagedBean(name = "consultaInsumosAsignadosManagedBean")
public class ConsultaInsumosAsignadosManagedBean extends AbstractConsultaInsumosAsignadosMB {

    private static final long serialVersionUID = -7077603637080790171L;

    private boolean mostrarTotalAdministradores;
    private boolean mostrarInsumosAdministrador;
    private boolean mostrarDetalleInsumo;
    private FecetInsumo propuestaSeleccionada;
    private FecetDocExpInsumo documentoSeleccionado;
    private String nombreAdministrador;
    private FecetRetroalimentacionInsumo retroalimentacionInsumo;
    private FecetDocretroinsumo docretroInsumoCargadoSeleccionado;
    private boolean mostrarTablaArchivosRetro;

    @PostConstruct
    public void init() {
        setListaAsignacion(new ArrayList<ContadorAsignadosAdministradorEstado>());
        setListaInsumosAsignados(new ArrayList<FecetInsumo>());

        this.mostrarTotalAdministradores = true;
        this.mostrarInsumosAdministrador = false;
        this.mostrarDetalleInsumo = false;

        this.propuestaSeleccionada = new FecetInsumo();
        this.documentoSeleccionado = new FecetDocExpInsumo();
        this.nombreAdministrador = "";
        this.mostrarTablaArchivosRetro = false;
        setListaInsumosFiltrados(null);
        limpiaFiltros(":formInsumosAdministrador:tablaInsumosadministrador");
        try {
            EmpleadoDTO empleadoDto = obtenerEmpleadoAcceso(getRFCSession(), TipoEmpleadoEnum.CONSULTOR_INSUMOS);

            if (getConsultaInsumosAsignadosService().validarUnidadAdministrativaXRegla(empleadoDto, ReglaEnum.RNA037, null)) {
                setListaAsignacion(getConsultaInsumosAsignadosService().getContadorInsumosAsignados(empleadoDto));
            } else {
                informeErrorSession(new Exception("No se encuentra el perfil del empleado"));
            }
        } catch (NoExisteEmpleadoException e) {
            informeErrorSession(e);
        }
    }

    public void mostrarDetalleAsignacion() {
        this.nombreAdministrador = "";
        //Traer map de session
        if (getMapGrupos() == null) {
            obtenerMapGruposDeUnidades(getEmpleadoSession());
        }
        setListaInsumosAsignados(getConsultaInsumosAsignadosService().getInsumosAsignadosAuditorEntidad(getContadorSeleccionado().getRfcAdministrador(), getContadorSeleccionado().getEntidad(), getMapGrupos()));
        if (getListaInsumosAsignados() != null && !getListaInsumosAsignados().isEmpty()) {
            this.mostrarTotalAdministradores = false;
            this.mostrarInsumosAdministrador = true;
            this.mostrarDetalleInsumo = false;
            if (getContadorSeleccionado().getNombreAdministrador() != null) {
                this.nombreAdministrador = getContadorSeleccionado().getNombreAdministrador();
            }
        } else {
            this.mostrarTotalAdministradores = true;
            this.mostrarInsumosAdministrador = false;
            this.mostrarDetalleInsumo = false;
        }

    }

    public void configuraVistaInsumosAdministrador() {
        this.mostrarTotalAdministradores = false;
        this.mostrarInsumosAdministrador = true;
        this.mostrarDetalleInsumo = false;
        limpiaFiltros(":formInsumosAdministrador:tablaInsumosadministrador");
    }

    public void configuraVistaInsumo() {
        this.mostrarTotalAdministradores = false;
        this.mostrarInsumosAdministrador = false;
        this.mostrarDetalleInsumo = true;
        this.propuestaSeleccionada.setListaDocumentos(getConsultaInsumosAsignadosService().getDocumentosPropuesta(this.propuestaSeleccionada.getIdInsumo()));
        //agregar aqui
        setLstDocJustificacion(getSeguimientoInsumosService().buscarDocumentoJustificacion(this.propuestaSeleccionada));
    }

    public void mostrarHistoricoRetroalimentacion() {
        setInsumosRetroalimentadosContador(new ArrayList<FecetRetroalimentacionInsumo>());
        docretroInsumoCargadoSeleccionado = new FecetDocretroinsumo();
        this.mostrarTablaArchivosRetro = false;
        setInsumosRetroalimentadosContador(getSeguimientoInsumosService().getInsumosRetroalimentados(this.propuestaSeleccionada));
        setListaContadorRechazo(getSeguimientoInsumosService().getContadorRechazo(this.propuestaSeleccionada.getIdInsumo()));
    }

    public void visualizaDocRetroalimentacionAciace() {
        setListaDocRetroInsumo(getConsultaInsumosAsignadosService().getDocumentosRetroalimentadosByIdRetroInsumo(retroalimentacionInsumo.getIdRetroalimentacionInsumo(), Constantes.USUARIO_INSUMOS));
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('DocumentosSolicitudRetroInsumo').show();");
    }

    public void visualizaRetroalimentacionSubAdmin() {
        setInsumosRetroalimentadoCargado(new ArrayList<FecetRetroalimentacionInsumo>());
        getInsumosRetroalimentadoCargado().add(getRetroalimentacionInsumo());
        setListaDocRetroInsumo(getConsultaInsumosAsignadosService().getDocumentosRetroalimentadosByIdRetroInsumo(getRetroalimentacionInsumo().getIdRetroalimentacionInsumo(), Constantes.USUARIO_VALIDADOR_INSUMOS));
        if (getListaDocRetroInsumo() != null && !getListaDocRetroInsumo().isEmpty()) {
            getInsumosRetroalimentadoCargado().get(0).setNumeroSolicitudesRetro(new BigDecimal(getListaDocRetroInsumo().size()));
        } else {
            getInsumosRetroalimentadoCargado().get(0).setNumeroSolicitudesRetro(BigDecimal.ZERO);
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('historicoSolicitudRetroInsumo').show();");
    }

    public void limpiaMostrarArchivosRetroalimentacion() {
        setMostrarTablaArchivosRetro(false);
    }

    public void setMostrarTotalAdministradores(final boolean mostrarTotalAdministradores) {
        this.mostrarTotalAdministradores = mostrarTotalAdministradores;
    }

    public boolean isMostrarTotalAdministradores() {
        return mostrarTotalAdministradores;
    }

    public void setPropuestaSeleccionada(FecetInsumo propuestaSeleccionada) {
        this.propuestaSeleccionada = propuestaSeleccionada;
    }

    public FecetInsumo getPropuestaSeleccionada() {
        return propuestaSeleccionada;
    }

    public void setMostrarDetalleInsumo(boolean mostrarDetalleInsumo) {
        this.mostrarDetalleInsumo = mostrarDetalleInsumo;
    }

    public boolean isMostrarDetalleInsumo() {
        return mostrarDetalleInsumo;
    }

    public void setMostrarInsumosAdministrador(boolean mostrarInsumosAdministrador) {
        this.mostrarInsumosAdministrador = mostrarInsumosAdministrador;
    }

    public boolean isMostrarInsumosAdministrador() {
        return mostrarInsumosAdministrador;
    }

    public StreamedContent getDocumentoSeleccionDescarga() {
        return getDescargaArchivo(this.documentoSeleccionado.getRutaArchivo() + this.documentoSeleccionado.getNombre(),
                this.documentoSeleccionado.getNombre());
    }

    public void setDocumentoSeleccionado(final FecetDocExpInsumo documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    public FecetDocExpInsumo getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public FecetRetroalimentacionInsumo getRetroalimentacionInsumo() {
        return retroalimentacionInsumo;
    }

    public void setRetroalimentacionInsumo(FecetRetroalimentacionInsumo retroalimentacionInsumo) {
        this.retroalimentacionInsumo = retroalimentacionInsumo;
    }

    public boolean isMostrarTablaArchivosRetro() {
        return mostrarTablaArchivosRetro;
    }

    public void setMostrarTablaArchivosRetro(boolean mostrarTablaArchivosRetro) {
        this.mostrarTablaArchivosRetro = mostrarTablaArchivosRetro;
    }

    public FecetDocretroinsumo getDocretroInsumoCargadoSeleccionado() {
        return docretroInsumoCargadoSeleccionado;
    }

    public void setDocretroInsumoCargadoSeleccionado(FecetDocretroinsumo docretroInsumoCargadoSeleccionado) {
        this.docretroInsumoCargadoSeleccionado = docretroInsumoCargadoSeleccionado;
    }

    public StreamedContent getArchivoDescargaRetroalimentacion() {
        return getDescargaArchivo(getDocretroInsumoCargadoSeleccionado().getRutaArchivo()
                + getDocretroInsumoCargadoSeleccionado().getNombreArchivo(), getDocretroInsumoCargadoSeleccionado().getNombreArchivo());
    }

}
