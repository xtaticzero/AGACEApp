package mx.gob.sat.siat.feagace.negocio.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dto.common.DocumentoVista;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocCancelacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocTransferencia;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;

@Component
public class ValidarFirmarDoctoHelper implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public List<DocumentoVista> obtenerDocumentos(List<FecetDocCancelacion> elementos) {
        List<DocumentoVista> documentos = new ArrayList<DocumentoVista>();
        if (elementos != null && !elementos.isEmpty()) {
            DocumentoVista vista = null;
            for (FecetDocCancelacion fecetDocCancelacion : elementos) {
                vista = new DocumentoVista();
                vista.setFechaCreacion(fecetDocCancelacion.getFechaCreacion());
                vista.setNombre(fecetDocCancelacion.getNombreArchivo());
                vista.setRuta(fecetDocCancelacion.getRutaArchivo());
                documentos.add(vista);
            }
        }
        return documentos;
    }

    public List<DocumentoVista> obtenerDocumentosExpediente(List<FecetDocExpediente> elementos) {
        List<DocumentoVista> documentos = new ArrayList<DocumentoVista>();
        if (elementos != null && !elementos.isEmpty()) {
            DocumentoVista vista = null;
            for (FecetDocExpediente expediente : elementos) {
                vista = new DocumentoVista();
                vista.setFechaCreacion(expediente.getFechaCreacion());
                vista.setNombre(expediente.getNombre());
                vista.setRuta(expediente.getRutaArchivo());
                documentos.add(vista);
            }
        }
        return documentos;
    }

    public List<DocumentoVista> obtenerDocumentosTransferencia(List<FecetDocTransferencia> elementos) {
        List<DocumentoVista> documentos = new ArrayList<DocumentoVista>();
        if (elementos != null && !elementos.isEmpty()) {
            DocumentoVista vista = null;
            for (FecetDocTransferencia transferencia : elementos) {
                vista = new DocumentoVista();
                vista.setFechaCreacion(transferencia.getFechaCreacion());
                vista.setNombre(transferencia.getNombreArchivo());
                vista.setRuta(transferencia.getRutaArchivo());
                documentos.add(vista);
            }
        }
        return documentos;
    }
    
    public FecebAccionPropuesta creaAccionPropuesta(FecetPropuesta propuesta, BigDecimal idAccion, String observaciones, BigDecimal idEmpleado){
        FecebAccionPropuesta accionPropuesta = new FecebAccionPropuesta();
        accionPropuesta.setIdAccion(idAccion);
        accionPropuesta.setIdAccionOrigen(null);
        accionPropuesta.setIdPropuesta(propuesta.getIdPropuesta());
        accionPropuesta.setIdDetalleAccion(null);
        accionPropuesta.setObservaciones(observaciones);
        accionPropuesta.setIdEmpleado(idEmpleado);
        accionPropuesta.setFechaHora(new Date());
        return accionPropuesta;
    }
    
    public FecetRetroalimentacion creaAsociativaRetro(BigDecimal idPropuesta, BigDecimal idRetroalimentacion){
        FecetRetroalimentacion retroalimentacion = new FecetRetroalimentacion();
        retroalimentacion.setIdPropuesta(idPropuesta);
        retroalimentacion.setIdRetroalimentacion(idRetroalimentacion);
        retroalimentacion.setIdRetroalimentacionOrigen(idRetroalimentacion);
        return retroalimentacion;
    }
}
