/**
 * 
 */
package mx.gob.sat.siat.feagace.vista.propuestas;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DocumentoVista;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetCancelacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetContadorPropuestasRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroContador;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferenciaContador;

/**
 * @author sergio.vaca
 *
 */
public class AbstractValidarFirmarDoctoMB extends AbstractValidarFirmarDoctoAtrributes {
    private static final long serialVersionUID = 1L;

    private int idOpcion;
    private boolean mostrarCancelacion;
    private boolean mostrarRechazo;
    private boolean mostrarTransferidas;
    private boolean mostrarRetroalimetadas;

    private List<FecetPropuesta> registrosResumen;
    private List<FecetCancelacion> registrosCancelacion;
    private List<FecetContadorPropuestasRechazados> registrosRechazo;
    private List<FecetTransferenciaContador> registrosTransferencia;
    private List<FecetRetroContador> registrosRetroalimentacion;
    private List<DocumentoVista> listDocumentos;

    private List<AraceDTO> unidadesAdmin;
    private List<FecebAccionPropuesta> listaHistoricoAccion;
    private List<FecetDocOrden> listaHistorialOrden;
    private List<FecetOficio> listaHistorialOficio;
    private List<FecetRetroalimentacion> listaHistoricoRetroalimentar;
    private List<FecetImpuesto> listaImpuestos;
    private List<FecetRetroalimentacion> listaDocumentos;
    private List<FecetRetroalimentacion> listaSolicitudRetroalimentacion;

    protected void validarPresentacion() {
        switch (getEstatusActual()) {
        case CANCELADADAS_NO_APROBAR:
        case CANCELADAS_EMISION:
        case CANCELADAS_VALIDAR:
            mostrarCancelacion = true;
            mostrarRechazo = false;
            mostrarTransferidas = false;
            mostrarRetroalimetadas = false;
            break;
        case RECHAZADAS_EMISION:
        case RECHAZADAS_NO_APROBAR:
        case RECHAZADAS_VALIDAR:
            mostrarCancelacion = false;
            mostrarRechazo = true;
            mostrarTransferidas = false;
            mostrarRetroalimetadas = false;
            break;
        case TRANSFERIDAS_EMISION:
        case TRANSFERIDAS_NO_APROBAR:
        case TRANSFERIDAS_VALIDAR:
            mostrarCancelacion = false;
            mostrarRechazo = false;
            mostrarTransferidas = true;
            mostrarRetroalimetadas = false;
            break;
        case RETROALIMENTADAS_EMISION:
        case RETROALIMENTADAS_NO_APROBAR:
        case RETROALIMENTADAS_VALIDAR:
            mostrarCancelacion = false;
            mostrarRechazo = false;
            mostrarTransferidas = false;
            mostrarRetroalimetadas = true;
            break;
        default:
            break;
        }
    }

    protected void abrirDialogoDocumentos() {
        setLabelHeader("Documentos");
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dialogoDocumentos').show();");
    }

    protected void abrirDialogoFinal() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('confirmarFinal').show();");
    }

    protected void abrirDialogoAceptaProceso() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('confirmarProceso').show();");
    }

    protected void abrirDialogoRechazarProceso() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('rechazarProceso').show();");
    }

    protected void abrirDialogoRechazarProcesoObservaciones() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dialogNoAprobacion').show();");
    }

    public StreamedContent getExpedienteDescarga() {
        FecetDocExpediente expediente = getExpendieteActual();
        StreamedContent archivoDescarga = null;
        if (expediente != null && expediente.getNombre() != null && expediente.getRutaArchivo() != null) {
            String ruta = expediente.getRutaArchivo().replace(expediente.getNombre(), "")
                    .concat(expediente.getNombre());
            archivoDescarga = getDescargaArchivo(ruta, expediente.getNombre());
        }
        return archivoDescarga;
    }

    public StreamedContent getDocumentoDescarga() {
        DocumentoVista documento = getDocumentoActual();
        StreamedContent archivoDescarga = null;
        if (documento != null && documento.getNombre() != null && documento.getRuta() != null) {
            String ruta = documento.getRuta().replace(documento.getNombre(), "").concat(documento.getNombre());
            archivoDescarga = getDescargaArchivo(ruta, documento.getNombre());
        }
        return archivoDescarga;
    }
    
    public StreamedContent getDescargaOrden() {
        StreamedContent archivoDescarga = null;
        if (getRutaArchivo() != null && getNombreArchivo() != null) {
            String ruta = getRutaArchivo().replace(getNombreArchivo(), "").concat(getNombreArchivo());
            archivoDescarga = getDescargaArchivo(ruta, getNombreArchivo());   
        }
        return archivoDescarga;
    }
    
    protected List<DocumentoVista> obtenerDocumentosHistorial(List<FecetDocExpediente> listaDocumentos) {
        
        List<DocumentoVista> listaDoc = new ArrayList<DocumentoVista>(); 
        
        for (FecetDocExpediente documento : listaDocumentos) {
            DocumentoVista documentoHistorico = new DocumentoVista();
            documentoHistorico.setNombre(documento.getNombre());
            documentoHistorico.setFechaCreacion(documento.getFechaCreacion());
            documentoHistorico.setRuta(documento.getRutaArchivo());
            listaDoc.add(documentoHistorico);
        }
        
        return listaDoc;
        
    }

    public final int getIdOpcion() {
        return idOpcion;
    }

    public final void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
    }

    public final boolean isMostrarCancelacion() {
        return mostrarCancelacion;
    }

    public final void setMostrarCancelacion(boolean mostrarCancelacion) {
        this.mostrarCancelacion = mostrarCancelacion;
    }

    public final boolean isMostrarRechazo() {
        return mostrarRechazo;
    }

    public final void setMostrarRechazo(boolean mostrarRechazo) {
        this.mostrarRechazo = mostrarRechazo;
    }

    public final boolean isMostrarTransferidas() {
        return mostrarTransferidas;
    }

    public final void setMostrarTransferidas(boolean mostrarTransferidas) {
        this.mostrarTransferidas = mostrarTransferidas;
    }

    public final boolean isMostrarRetroalimetadas() {
        return mostrarRetroalimetadas;
    }

    public final void setMostrarRetroalimetadas(boolean mostrarRetroalimetadas) {
        this.mostrarRetroalimetadas = mostrarRetroalimetadas;
    }

    public final List<FecetPropuesta> getRegistrosResumen() {
        return registrosResumen;
    }

    public final void setRegistrosResumen(List<FecetPropuesta> registrosResumen) {
        this.registrosResumen = registrosResumen;
    }

    public final List<FecetCancelacion> getRegistrosCancelacion() {
        return registrosCancelacion;
    }

    public final void setRegistrosCancelacion(List<FecetCancelacion> registrosCancelacion) {
        this.registrosCancelacion = registrosCancelacion;
    }

    public final List<FecetContadorPropuestasRechazados> getRegistrosRechazo() {
        return registrosRechazo;
    }

    public final void setRegistrosRechazo(List<FecetContadorPropuestasRechazados> registrosRechazo) {
        this.registrosRechazo = registrosRechazo;
    }

    public final List<DocumentoVista> getListDocumentos() {
        return listDocumentos;
    }

    public final void setListDocumentos(List<DocumentoVista> listDocumentos) {
        this.listDocumentos = listDocumentos;
    }

    public final List<FecetTransferenciaContador> getRegistrosTransferencia() {
        return registrosTransferencia;
    }

    public final void setRegistrosTransferencia(List<FecetTransferenciaContador> registrosTransferencia) {
        this.registrosTransferencia = registrosTransferencia;
    }

    public final List<FecetRetroContador> getRegistrosRetroalimentacion() {
        return registrosRetroalimentacion;
    }

    public final void setRegistrosRetroalimentacion(List<FecetRetroContador> registrosRetroalimentacion) {
        this.registrosRetroalimentacion = registrosRetroalimentacion;
    }

    public List<AraceDTO> getUnidadesAdmin() {
        return unidadesAdmin;
    }

    public void setUnidadesAdmin(List<AraceDTO> unidadesAdmin) {
        this.unidadesAdmin = unidadesAdmin;
    }

    public List<FecebAccionPropuesta> getListaHistoricoAccion() {
        return listaHistoricoAccion;
    }

    public void setListaHistoricoAccion(List<FecebAccionPropuesta> listaHistoricoAccion) {
        this.listaHistoricoAccion = listaHistoricoAccion;
    }

    public List<FecetDocOrden> getListaHistorialOrden() {
        return listaHistorialOrden;
    }

    public void setListaHistorialOrden(List<FecetDocOrden> listaHistorialOrden) {
        this.listaHistorialOrden = listaHistorialOrden;
    }

    public List<FecetOficio> getListaHistorialOficio() {
        return listaHistorialOficio;
    }

    public void setListaHistorialOficio(List<FecetOficio> listaHistorialOficio) {
        this.listaHistorialOficio = listaHistorialOficio;
    }

    public List<FecetRetroalimentacion> getListaHistoricoRetroalimentar() {
        return listaHistoricoRetroalimentar;
    }

    public void setListaHistoricoRetroalimentar(List<FecetRetroalimentacion> listaHistoricoRetroalimentar) {
        this.listaHistoricoRetroalimentar = listaHistoricoRetroalimentar;
    }

    public List<FecetImpuesto> getListaImpuestos() {
        return listaImpuestos;
    }

    public void setListaImpuestos(List<FecetImpuesto> listaImpuestos) {
        this.listaImpuestos = listaImpuestos;
    }

    public List<FecetRetroalimentacion> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<FecetRetroalimentacion> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public List<FecetRetroalimentacion> getListaSolicitudRetroalimentacion() {
        return listaSolicitudRetroalimentacion;
    }

    public void setListaSolicitudRetroalimentacion(List<FecetRetroalimentacion> listaSolicitudRetroalimentacion) {
        this.listaSolicitudRetroalimentacion = listaSolicitudRetroalimentacion;
    }
}
