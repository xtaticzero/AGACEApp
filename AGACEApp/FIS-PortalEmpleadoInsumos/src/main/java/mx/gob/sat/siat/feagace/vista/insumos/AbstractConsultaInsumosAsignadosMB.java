/**
 *
 */
package mx.gob.sat.siat.feagace.vista.insumos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorAsignadosAdministrador;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorAsignadosAdministradorEstado;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumosRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;
import mx.gob.sat.siat.feagace.negocio.exception.NoSeGeneroReporteException;
import mx.gob.sat.siat.feagace.negocio.insumos.ConsultaInsumosAsignadosService;
import mx.gob.sat.siat.feagace.negocio.insumos.SeguimientoInsumosService;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

/**
 * @author sergio.vaca
 *
 */
public class AbstractConsultaInsumosAsignadosMB extends InsumosAbstractBaseMB {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{consultaInsumosAsignadosService}")
    private transient ConsultaInsumosAsignadosService consultaInsumosAsignadosService;
    @ManagedProperty(value = "#{seguimientoInsumosService}")
    private transient SeguimientoInsumosService seguimientoInsumosService;

    private transient List<ContadorAsignadosAdministradorEstado> listaAsignacion;
    private List<FecetInsumo> listaInsumosAsignados;
    private List<FecetInsumo> listaInsumosFiltrados;
    private List<FecetRetroalimentacionInsumo> insumosRetroalimentadosContador;
    private List<FecetDocretroinsumo> listaDocRetroInsumo;
    private List<FecetRetroalimentacionInsumo> insumosRetroalimentadoCargado;
    private List<FecetContadorInsumosRechazados> listaContadorRechazo;
    private List<FecetDocrechazoinsumo> listaDocRechazoInsumo;
    private transient FecetContadorInsumosRechazados documentoSeleccionadoRechazo;

    private transient ContadorAsignadosAdministrador contadorSeleccionado;
    private FecetDocrechazoinsumo docrechazoCargadoSeleccionado;

    public void mostrarArchivosRechazo() {
        setListaDocRechazoInsumo(getSeguimientoInsumosService().getDocumentosRechazados(getDocumentoSeleccionadoRechazo().getIdRechazoInsumo()));
    }

    public void getReportData() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("application/vnd.ms-excel.12");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"ConsultaAdministrador.xlsx\"");
        try {
            final List<FecetInsumo> registrosReporte = new ArrayList<FecetInsumo>();
            if (getListaInsumosFiltrados() != null && !getListaInsumosFiltrados().isEmpty()) {
                registrosReporte.addAll(getListaInsumosFiltrados());
            } else {
                registrosReporte.addAll(getListaInsumosAsignados());
            }
            getConsultaInsumosAsignadosService().generarReporte(registrosReporte, externalContext.getResponseOutputStream());
        } catch (IOException e) {
            logger.error("Error al exportar el excel.", e);
            FacesUtil.addErrorMessage(null, "Ocurrio un error al generar el reporte", "");
        } catch (NoSeGeneroReporteException e) {
            logger.error("Error al exportar el excel.", e);
            FacesUtil.addErrorMessage(null, "Ocurrio un error al generar el reporte", "");
        }
        facesContext.responseComplete();
    }

    public StreamedContent getArchivoDescargaRechazo() {
        StreamedContent archivoDescargaRechazo = null;
        if (getDocrechazoCargadoSeleccionado().getRutaArchivo() != null && getDocrechazoCargadoSeleccionado().getNombreArchivo() != null) {
            String ruta = getDocrechazoCargadoSeleccionado().getRutaArchivo().replace(getDocrechazoCargadoSeleccionado().getNombreArchivo(), "");
            ruta = ruta.concat(getDocrechazoCargadoSeleccionado().getNombreArchivo());
            archivoDescargaRechazo = getDescargaArchivo(ruta, getDocrechazoCargadoSeleccionado().getNombreArchivo());
        }
        return archivoDescargaRechazo;
    }

    public void visualizaDocRetroalimentacionSubAdmin() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('DocumentosSolicitudRetroInsumo').show();");
    }

    public final List<FecetInsumo> getListaInsumosFiltrados() {
        return listaInsumosFiltrados;
    }

    public final void setListaInsumosFiltrados(List<FecetInsumo> listaInsumosFiltrados) {
        this.listaInsumosFiltrados = listaInsumosFiltrados;
    }

    public SeguimientoInsumosService getSeguimientoInsumosService() {
        return seguimientoInsumosService;
    }

    public void setSeguimientoInsumosService(SeguimientoInsumosService seguimientoInsumosService) {
        this.seguimientoInsumosService = seguimientoInsumosService;
    }

    public final List<FecetContadorInsumosRechazados> getListaContadorRechazo() {
        return listaContadorRechazo;
    }

    public final void setListaContadorRechazo(List<FecetContadorInsumosRechazados> listaContadorRechazo) {
        this.listaContadorRechazo = listaContadorRechazo;
    }

    public final List<FecetDocrechazoinsumo> getListaDocRechazoInsumo() {
        return listaDocRechazoInsumo;
    }

    public final void setListaDocRechazoInsumo(List<FecetDocrechazoinsumo> listaDocRechazoInsumo) {
        this.listaDocRechazoInsumo = listaDocRechazoInsumo;
    }

    public List<FecetDocretroinsumo> getListaDocRetroInsumo() {
        return listaDocRetroInsumo;
    }

    public void setListaDocRetroInsumo(List<FecetDocretroinsumo> listaDocRetroInsumo) {
        this.listaDocRetroInsumo = listaDocRetroInsumo;
    }

    public List<FecetRetroalimentacionInsumo> getInsumosRetroalimentadoCargado() {
        return insumosRetroalimentadoCargado;
    }

    public void setInsumosRetroalimentadoCargado(List<FecetRetroalimentacionInsumo> insumosRetroalimentadoCargado) {
        this.insumosRetroalimentadoCargado = insumosRetroalimentadoCargado;
    }

    public List<FecetRetroalimentacionInsumo> getInsumosRetroalimentadosContador() {
        return insumosRetroalimentadosContador;
    }

    public void setInsumosRetroalimentadosContador(List<FecetRetroalimentacionInsumo> insumosRetroalimentadosContador) {
        this.insumosRetroalimentadosContador = insumosRetroalimentadosContador;
    }

    public void setListaInsumosAsignados(final List<FecetInsumo> listaInsumosAsignados) {
        this.listaInsumosAsignados = listaInsumosAsignados;
    }

    public List<FecetInsumo> getListaInsumosAsignados() {
        return listaInsumosAsignados;
    }

    public void setConsultaInsumosAsignadosService(final ConsultaInsumosAsignadosService consultaInsumosAsignadosService) {
        this.consultaInsumosAsignadosService = consultaInsumosAsignadosService;
    }

    public ConsultaInsumosAsignadosService getConsultaInsumosAsignadosService() {
        return consultaInsumosAsignadosService;
    }

    public void setListaAsignacion(final List<ContadorAsignadosAdministradorEstado> listaAsignacion) {
        this.listaAsignacion = listaAsignacion;
    }

    public List<ContadorAsignadosAdministradorEstado> getListaAsignacion() {
        return listaAsignacion;
    }

    public final FecetContadorInsumosRechazados getDocumentoSeleccionadoRechazo() {
        return documentoSeleccionadoRechazo;
    }

    public final void setDocumentoSeleccionadoRechazo(FecetContadorInsumosRechazados documentoSeleccionadoRechazo) {
        this.documentoSeleccionadoRechazo = documentoSeleccionadoRechazo;
    }

    public void setContadorSeleccionado(final ContadorAsignadosAdministrador contadorSeleccionado) {
        this.contadorSeleccionado = contadorSeleccionado;
    }

    public ContadorAsignadosAdministrador getContadorSeleccionado() {
        return contadorSeleccionado;
    }

    public final FecetDocrechazoinsumo getDocrechazoCargadoSeleccionado() {
        return docrechazoCargadoSeleccionado;
    }

    public final void setDocrechazoCargadoSeleccionado(FecetDocrechazoinsumo docrechazoCargadoSeleccionado) {
        this.docrechazoCargadoSeleccionado = docrechazoCargadoSeleccionado;
    }
}
