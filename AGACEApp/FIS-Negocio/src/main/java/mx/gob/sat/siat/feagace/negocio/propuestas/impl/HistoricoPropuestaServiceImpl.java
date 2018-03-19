package mx.gob.sat.siat.feagace.negocio.propuestas.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetCancelacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocRechazoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocRetroPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocTransferenciaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocRetroalimentacionDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocCancelacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocTransferencia;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.propuestas.HistoricoPropuestaService;

@Service("historicoDocPropuestaService")
public class HistoricoPropuestaServiceImpl extends BaseBusinessServices implements HistoricoPropuestaService {

    private static final long serialVersionUID = 1L;

    @Autowired
    private transient FecetDocRetroPropuestaDao fecetDocRetroPropuestaDao;

    @Autowired
    private transient FecetDocRechazoPropuestaDao fecetDocRechazoPropuestaDao;

    @Autowired
    private transient FecetDocTransferenciaDao fecetDocTransferenciaDao;

    @Autowired
    private transient FecetCancelacionDao fecetCancelacionDao;

    @Autowired
    private transient FecetOficioDao fecetOficioDao;

    @Autowired
    private transient FecetDocOrdenDao fecetDocOrdenDao;

    @Autowired
    private transient EmpleadoService empleadoService;

    @Override
    public List<FecetDocExpediente> buscarDoctosRechazoPorIdRechazo(BigDecimal idRechazo) {
        return fecetDocRechazoPropuestaDao.findWhereIdRechazoPropuestaEquals(idRechazo);
    }

    @Override
    public List<FecetDocExpediente> obtenerDocumentoByIdRetro(BigDecimal idRetro) {
        List<DocRetroalimentacionDTO> listaDocRetroalimentacionDTO = fecetDocRetroPropuestaDao
                .obtenerDocumentoByIdRetro(idRetro);
        List<FecetDocExpediente> listaDocumentos = new ArrayList<FecetDocExpediente>();
        if (listaDocRetroalimentacionDTO != null && !listaDocRetroalimentacionDTO.isEmpty()) {
            for (DocRetroalimentacionDTO doc : listaDocRetroalimentacionDTO) {
                FecetDocExpediente expediente = new FecetDocExpediente();
                expediente.setIdDocExpediente(doc.getIdRetroalimentacion());
                expediente.setIdPropuesta(doc.getIdPropuesta());
                expediente.setNombre(doc.getNombreArchivo());
                expediente.setRutaArchivo(doc.getRutaArchivo());
                expediente.setFechaCreacion(doc.getFechaCreacion());
                listaDocumentos.add(expediente);
            }
        }

        return listaDocumentos;
    }

    @Override
    public List<FecetDocExpediente> obtenerDocumentoByIdTransferencia(BigDecimal idTransferencia) {
        List<FecetDocTransferencia> listaDocTransferencia = fecetDocTransferenciaDao
                .obtenerDocumentosAllByIdTransferencia(idTransferencia);
        List<FecetDocExpediente> listaDocumentos = new ArrayList<FecetDocExpediente>();
        if (listaDocTransferencia != null && !listaDocTransferencia.isEmpty()) {
            for (FecetDocTransferencia doc : listaDocTransferencia) {
                FecetDocExpediente expediente = new FecetDocExpediente();
                expediente.setIdDocExpediente(doc.getIdTrnsferencia());
                expediente.setIdPropuesta(doc.getIdPropuesta());
                expediente.setNombre(doc.getNombreArchivo());
                expediente.setRutaArchivo(doc.getRutaArchivo());
                expediente.setFechaCreacion(doc.getFechaCreacion());
                listaDocumentos.add(expediente);
            }
        }
        return listaDocumentos;

    }

    @Override
    public List<FecetDocExpediente> obtenerDocumentoByIdCancelacion(BigDecimal idCancelacion) {

        List<FecetDocCancelacion> listaDocCancelacion = fecetCancelacionDao
                .obtenerDoctosAllCancelacionById(idCancelacion);
        List<FecetDocExpediente> listaDocumentos = new ArrayList<FecetDocExpediente>();
        if (listaDocCancelacion != null && !listaDocCancelacion.isEmpty()) {
            for (FecetDocCancelacion doc : listaDocCancelacion) {
                FecetDocExpediente expediente = new FecetDocExpediente();
                expediente.setIdDocExpediente(doc.getIdCancelacion());

                expediente.setNombre(doc.getNombreArchivo());
                expediente.setRutaArchivo(doc.getRutaArchivo());
                expediente.setFechaCreacion(doc.getFechaCreacion());
                listaDocumentos.add(expediente);
            }
        }
        return listaDocumentos;

    }

    @Override
    public List<FecetOficio> oficiosHistorial(BigDecimal idOrden) {
        return obtieneEmpleadoDocOficio(fecetOficioDao.getOficioIdOrdenHistorial(idOrden));
    }

    @Override
    public List<FecetDocOrden> obtenerDocsOrdenHistorial(BigDecimal idPropuesta) {
        return obtieneEmpleadoDocOrden(fecetDocOrdenDao.accionIdPropuestaHistorial(idPropuesta));
    }

    private List<FecetOficio> obtieneEmpleadoDocOficio(List<FecetOficio> lisOficio) {
        List<FecetOficio> list = new ArrayList<FecetOficio>();
        for (FecetOficio oficio : lisOficio) {
            EmpleadoDTO empleado = null;
            try {
                empleado = empleadoService.getEmpleadoCompleto(oficio.getIdEmpleado().intValue());
            } catch (EmpleadoServiceException e) {
                logger.error(e.getMessage());
            }
            if (empleado != null) {
                empleado.setNombre(empleado.getNombreCompleto());
                TipoEmpleadoEnum tipoEmpleadoEnum = TipoEmpleadoEnum
                        .parse(Integer.parseInt(oficio.getDescripcionAccion()));
                oficio.setDescripcionEmpleado(empleado.getNombre() + " (" + tipoEmpleadoEnum.getDescripcion() + ")");
            }
            list.add(oficio);
        }
        return list;
    }

    private List<FecetDocOrden> obtieneEmpleadoDocOrden(List<FecetDocOrden> lisOrden) {
        List<FecetDocOrden> list = new ArrayList<FecetDocOrden>();
        for (FecetDocOrden orden : lisOrden) {
            EmpleadoDTO empleado = null;

            try {
                empleado = empleadoService.getEmpleadoCompleto(orden.getIdEmpleado().intValue());
            } catch (EmpleadoServiceException e) {
                logger.error(e.getMessage());
            }
            if (empleado != null) {
                empleado.setNombre(empleado.getNombreCompleto());
                TipoEmpleadoEnum tipoEmpleadoEnum = TipoEmpleadoEnum
                        .parse(Integer.parseInt(orden.getDescripcionAccion()));
                orden.setDescripcionEmpleado(empleado.getNombre() + " (" + tipoEmpleadoEnum.getDescripcion() + ")");
            }
            list.add(orden);
        }
        return list;
    }
}
