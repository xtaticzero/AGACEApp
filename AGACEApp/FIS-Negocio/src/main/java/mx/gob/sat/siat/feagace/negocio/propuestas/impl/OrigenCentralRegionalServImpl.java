package mx.gob.sat.siat.feagace.negocio.propuestas.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetRechazoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuestaPk;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaOrigenCentralRegDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.LeyendasPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.RutaArchivosEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.OrigenCentralRegionalServ;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("origenCentralRegionalService")
public class OrigenCentralRegionalServImpl extends BaseBusinessServices implements OrigenCentralRegionalServ {

    private static final Logger LOGGER = Logger.getLogger(OrigenCentralRegionalServImpl.class);

    private static final long serialVersionUID = 1L;

    @Autowired
    private transient FecetPropuestaDao propuestaDao;
    @Autowired
    private transient FecetContribuyenteDao contribuyenteDao;
    @Autowired
    private transient FecetRechazoPropuestaDao rechazoPropuestaDao;
    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;
    @Autowired
    private transient FecetImpuestoDao fecetImpuestoDao;
    @Autowired
    private transient NotificacionService notificacionService;
    @Autowired
    private EmpleadoService empleadoService;

    @Override
    public List<PropuestaOrigenCentralRegDTO> getPropuestasOrigenCentralRegionales(BigDecimal idEmpleado, BigDecimal idAraceEmpleado) throws NegocioException {
        try {
            return propuestaDao.getPropuestaOrigenCentralRegional(idEmpleado, idAraceEmpleado);
        } catch (Exception e) {
            LOGGER.error(ConstantesError.ERROR_DATOS_REQUERIDOS);
            throw new NegocioException(Constantes.FALLO_CONSULTA + e.getCause(), e);
        }
    }

    @Override
    public FecetPropuesta obtienePropuestaByFolio(String folio) throws NegocioException {
        try {
            return propuestaDao.findWhereIdRegistroEquals(folio).get(0);
        } catch (Exception e) {
            LOGGER.error(ConstantesError.ERROR_DATOS_REQUERIDOS);
            throw new NegocioException(Constantes.FALLO_CONSULTA + e.getCause(), e);
        }
    }

    @Override
    public FecetPropuesta obtienePropuestaByidPropuesta(BigDecimal idPropuesta) throws NegocioException {
        try {
            return propuestaDao.findWhereIdPropuestaEquals(idPropuesta).get(0);
        } catch (Exception e) {
            LOGGER.error(ConstantesError.ERROR_DATOS_REQUERIDOS);
            throw new NegocioException(Constantes.FALLO_CONSULTA + e.getCause(), e);
        }
    }

    public FecetContribuyente getContribuyente(BigDecimal rfc) throws NegocioException {
        try {
            return contribuyenteDao.findByPrimaryKey(rfc);
        } catch (Exception e) {
            LOGGER.error(ConstantesError.ERROR_DATOS_REQUERIDOS);
            throw new NegocioException(Constantes.FALLO_CONSULTA + e.getCause(), e);
        }
    }

    // @Transactional
    public void rechazarPropuesta(FecetPropuesta propuesta, FecetRechazoPropuesta fecetRechazo, List<FecetRechazoPropuesta> listaRechazo)
            throws NegocioException {

        rechazoPropuestaDao.insert(fecetRechazo);
        for (FecetRechazoPropuesta fecetRechazoPropuesta : listaRechazo) {
            fecetRechazo.setIdDocRechazo(rechazoPropuestaDao.getConsecutivoDocRechazo());
            fecetRechazo.setRutaArchivo(RutaArchivosUtil.generarRutaArchivoValidaPropuestas(RutaArchivosEnum.RUTA_DOCUMENTOS_RECHAZO_PROPUESTAS, propuesta)
                    + fecetRechazoPropuesta.getNombreArchivo());
            fecetRechazo.setNombreArchivo(fecetRechazoPropuesta.getNombreArchivo());
            fecetRechazo.setArchivo(fecetRechazoPropuesta.getArchivo());
            fecetRechazo.setFechaCreacion(new Date());
            rechazoPropuestaDao.insertDoc(fecetRechazo);
            try {
                CargaArchivoUtil.guardarArchivoPropuestaRechazada(fecetRechazo);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }

    }

    @Override
    @PistaAuditoria
    public BigDecimal actualizaPropuesta(FecetPropuesta propuesta) throws NegocioException {
        try {
            fecetPropuestaDao.updatePropuesta(new FecetPropuestaPk(propuesta.getIdPropuesta()), propuesta, true);
            if (propuesta.getIdEstatus().intValue() != Constantes.ENTERO_SESENTASEIS) {
                enviarCorreoConfirmacion(propuesta, propuesta.getEmpleadoDto().getRfc());
            }
        } catch (Exception e) {
            LOGGER.error(e.getCause(), e);
            throw new NegocioException(e.getCause().toString(), e);
        }
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal actualizaPropuestaRegistrar(FecetPropuesta propuesta) throws NegocioException {
        try {
            fecetPropuestaDao.updatePropuesta(new FecetPropuestaPk(propuesta.getIdPropuesta()), propuesta, true);
            if (propuesta.getIdEstatus().intValue() != Constantes.ENTERO_SESENTASEIS) {
                enviarCorreoConfirmacion(propuesta, propuesta.getEmpleadoDto().getRfc());
            }
        } catch (Exception e) {
            LOGGER.error(e.getCause(), e);
            throw new NegocioException(e.getCause().toString(), e);
        }
        return propuesta.getIdPropuesta();
    }

    @Override
    @PistaAuditoria
    public BigDecimal actualizaPropuestaComplementar(FecetPropuesta propuesta) throws NegocioException {
        try {
            fecetPropuestaDao.updatePropuesta(new FecetPropuestaPk(propuesta.getIdPropuesta()), propuesta, true);
            if (propuesta.getIdEstatus().intValue() != Constantes.ENTERO_SESENTASEIS) {
                enviarCorreoConfirmacion(propuesta, propuesta.getEmpleadoDto().getRfc());
            }
        } catch (Exception e) {
            LOGGER.error(e.getCause(), e);
            throw new NegocioException(e.getCause().toString(), e);
        }
        return propuesta.getIdPropuesta();
    }

    @Override
    public List<FecetImpuesto> getImpuestosByPropuesta(BigDecimal idPropuesta) throws NegocioException {
        try {
            return fecetImpuestoDao.getImpuestosPropuesta(idPropuesta);
        } catch (Exception e) {
            LOGGER.error(e.getCause(), e);
            throw new NegocioException(e.getCause().toString(), e);
        }
    }

    @Override
    public void enviarCorreoConfirmacion(FecetPropuesta propuesta, String rfcProgramador) {
        Set<String> destinatarios = new TreeSet<String>();
        destinatarios.add(propuesta.getEmpleadoDto().getCorreo());
        destinatariosEnviarComite(propuesta, destinatarios);

        Map<String, String> data = notificacionService.obtenerDatosCorreo(LeyendasPropuestasEnum.PROPUESTA_ASIGNADA_VALIDACION.getIdLeyenda());
        data.put(Common.ID_REGISTRO.toString(), propuesta.getIdRegistro());
        data.put(Common.ID_REGISTRO_ESPACIO.toString(), propuesta.getIdRegistro());

        enviarNotificacionInterna(data, ReportType.AVISOS_PROPUESTA_GENERICO, destinatarios);
    }

    private void enviarNotificacionInterna(Map<String, String> data, ReportType reportType, Set<String> destinatarios) {
        try {
            notificacionService.enviarNotificacionGenerico(data, reportType, destinatarios);
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(), e);
        }
    }

    private void destinatariosEnviarComite(FecetPropuesta propuesta, Set<String> destinatarios) {
        List<EmpleadoDTO> lstEmpleados;
        boolean isProgramador = false;
        try {
            lstEmpleados = empleadoService.getEmpleadosXUnidadAdmva(propuesta.getIdArace().intValue(), Constantes.USUARIO_PROGRAMADOR.intValue(),
                    ClvSubModulosAgace.PROPUESTAS);
            if (lstEmpleados != null) {
                for (EmpleadoDTO empleadoDto : lstEmpleados) {
                    if (propuesta.getRfcCreacion() != null && propuesta.getRfcCreacion().equals(empleadoDto.getRfc())) {
                        destinatarios.add(empleadoDto.getCorreo());
                        isProgramador = true;
                        break;
                    }
                }
            }

            if (!isProgramador) {
                lstEmpleados = empleadoService.getEmpleadosXUnidadAdmva(propuesta.getIdArace().intValue(), Constantes.USUARIO_VALIDADOR_INSUMOS.intValue(),
                        ClvSubModulosAgace.PROPUESTAS);
                if (lstEmpleados != null) {
                    for (EmpleadoDTO empleadoDto : lstEmpleados) {
                        if (propuesta.getRfcCreacion() != null && propuesta.getRfcCreacion().equals(empleadoDto.getRfc())) {
                            destinatarios.add(empleadoDto.getCorreo());
                            break;
                        }
                    }
                }

            }
        } catch (EmpleadoServiceException e) {
            logger.error(e.getMessage());
        }

        correoToEnviarComite(Constantes.USUARIO_CONSULTOR_INSUMOS, propuesta.getIdArace(), destinatarios);
        correoToEnviarComite(Constantes.USUARIO_CONSULTOR_INSUMOS, Constantes.ACPPCE, destinatarios);
    }

    private void correoToEnviarComite(BigDecimal tipoEmpleado, BigDecimal idUnidadAdministrativa, Set<String> destinatarios) {
        List<EmpleadoDTO> lstEmpleados = null;
        try {
            lstEmpleados = empleadoService.getEmpleadosXUnidadAdmva(idUnidadAdministrativa.intValue(), tipoEmpleado.intValue(), ClvSubModulosAgace.PROPUESTAS);
        } catch (EmpleadoServiceException ex) {
            logger.error(ex.getMessage(), ex);
        }
        if (lstEmpleados != null && !lstEmpleados.isEmpty()) {
            for (EmpleadoDTO emp : lstEmpleados) {
                destinatarios.add(emp.getCorreo());
            }
        }
    }

    @Override
    public List<FecetRechazoPropuesta> getArchivosRechazoByIdRechazo(BigDecimal idRechazo) {
        return rechazoPropuestaDao.getDocRechazadosByIdRechazo(idRechazo);
    }

    @Override
    public List<FecetRechazoPropuesta> getHistoricoRechazo(BigDecimal idPropuesta) {
        return rechazoPropuestaDao.getDocRechazadosByIdPropuesta(idPropuesta);
    }

    public FecetPropuestaDao getPropuestaDao() {
        return propuestaDao;
    }

    public void setPropuestaDao(FecetPropuestaDao propuestaDao) {
        this.propuestaDao = propuestaDao;
    }

    public FecetContribuyenteDao getContribuyenteDao() {
        return contribuyenteDao;
    }

    public void setContribuyenteDao(FecetContribuyenteDao contribuyenteDao) {
        this.contribuyenteDao = contribuyenteDao;
    }

    public FecetRechazoPropuestaDao getRechazoPropuestaDao() {
        return rechazoPropuestaDao;
    }

    public void setRechazoPropuestaDao(FecetRechazoPropuestaDao rechazoPropuestaDao) {
        this.rechazoPropuestaDao = rechazoPropuestaDao;
    }

    public FecetPropuestaDao getFecetPropuestaDao() {
        return fecetPropuestaDao;
    }

    public void setFecetPropuestaDao(FecetPropuestaDao fecetPropuestaDao) {
        this.fecetPropuestaDao = fecetPropuestaDao;
    }

    public FecetImpuestoDao getFecetImpuestoDao() {
        return fecetImpuestoDao;
    }

    public void setFecetImpuestoDao(FecetImpuestoDao fecetImpuestoDao) {
        this.fecetImpuestoDao = fecetImpuestoDao;
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }
}
