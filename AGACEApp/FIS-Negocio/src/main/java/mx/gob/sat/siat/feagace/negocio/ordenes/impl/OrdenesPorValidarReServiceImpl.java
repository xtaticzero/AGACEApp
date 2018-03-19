package mx.gob.sat.siat.feagace.negocio.ordenes.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetRechazoOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetRechazoOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FeceaPropuestaAccionDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecebAccionPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaPorValidar;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.ordenes.OrdenesPorValidarReService;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

@Service("ordenesPorValidarReService")
public class OrdenesPorValidarReServiceImpl extends OrdenesServiceBase implements OrdenesPorValidarReService {

    private static final long serialVersionUID = 1L;

    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;
    @Autowired
    private transient FecetContribuyenteDao fecetContribuyenteDao;
    @Autowired
    private transient FecetDocOrdenDao fecetDocOrdenDao;
    @Autowired
    private transient FecetOficioDao fecetOficioDao;
    @Autowired
    private transient FeceaPropuestaAccionDao feceaPropuestaAccionDao;
    @Autowired
    private transient FecebAccionPropuestaDao fecebAccionPropuestaDao;
    @Autowired
    private transient NotificacionService notificacionService;
    @Autowired
    private transient FecetRechazoOrdenDao fecetRechazoOrdenDao;
    @Autowired
    private transient FecetRechazoOficioDao fecetRechazoOficioDao;

    /**
     * Regresa una lista de las ordenes con el estatus VALIDAR_IND = 0 y
     * FIRMAR_IND = 0
     *
     * @param idMetodo identificador del metodo
     * @param idEmpleado identificador del empleado
     * @param idEstatus identificador del estatus
     * @return List
     */
    @Override
    public List<PropuestaPorValidar> getOrdenesPorValidar(BigDecimal idEstatus, BigDecimal idEmpleado,
            BigDecimal idMetodo, BigDecimal idAccionOrigen, EmpleadoDTO empleadoFirmante) {
        List<PropuestaPorValidar> propuestasProValidar = new ArrayList<PropuestaPorValidar>();

        List<FecetPropuesta> propuestasList = fecetPropuestaDao.getPropuestasPorValidarPorMetodo(idEstatus, idMetodo,
                empleadoFirmante.getRfc(), idAccionOrigen);
        logger.info("Numero de propuestas por validar: " + propuestasList.size());
        if (!propuestasList.isEmpty()) {
            for (FecetPropuesta prop : propuestasList) {
                PropuestaPorValidar propuesta = new PropuestaPorValidar();
                propuesta.setIdPropuesta(prop.getIdPropuesta().toString());
                propuesta.setPrioridadSugerida(prop.getPrioridadSugerida());
                propuesta.setIdRegistro(prop.getIdRegistro());
                propuesta.setRfcFirmante(prop.getRfcFirmante());

                Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> suboordinados = empleadoFirmante
                        .getSubordinados();
                Map<TipoEmpleadoEnum, List<EmpleadoDTO>> administradorOrdenes = suboordinados
                        .get(TipoEmpleadoEnum.FIRMANTE);
                List<EmpleadoDTO> lstAuditores = administradorOrdenes.get(TipoEmpleadoEnum.AUDITOR);

                for (EmpleadoDTO auditor : lstAuditores) {
                    if (auditor.getRfc().equals(prop.getRfcAuditor())) {
                        propuesta.setNombreAuditor(auditor.getNombre() + " " + auditor.getApellidoPaterno() + " "
                                + auditor.getApellidoMaterno());
                    }
                }
                FecetContribuyente contribuyente = fecetContribuyenteDao.findByPrimaryKey(prop.getIdContribuyente());
                if (contribuyente != null) {
                    propuesta.setRfcContribuyente(contribuyente.getRfc());
                    propuesta.setNombreContribuyente(contribuyente.getNombre());
                }
                AgaceOrden orden = getAgaceOrdenDao().findByIdPropuesta(prop.getIdPropuesta());
                if (orden != null) {
                    List<FecetDocOrden> docOrden = fecetDocOrdenDao.findByFecetOrden(orden.getIdOrden());
                    if (!docOrden.isEmpty()) {
                        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
                        propuesta.setFechaCarga(df.format((docOrden.get(0)).getFechaCreacion()));
                        propuesta.setRutaArchivo((docOrden.get(0)).getRutaArchivo());
                        propuesta.setNombreArchivo((docOrden.get(0)).getNombreArchivo());
                        propuesta.setIdOrden(String.valueOf(docOrden.get(0).getIdOrden().intValue()));
                        propuesta.setIdDocOrden(String.valueOf(docOrden.get(0).getIdDocOrden().intValue()));
                    }
                }
                propuestasProValidar.add(propuesta);
            }
        }
        return propuestasProValidar;
    }

    /**
     * Metodo getListaCadenaOriginal
     *
     * @param ordenesSeleccionadas
     * @return List Metodo que genera una lista de id para formar la cadena
     * original de la orden
     */
    public List<String> getListaCadenaOriginal(List<AgaceOrden> ordenesSeleccionadas) {
        List<String> listaID = new ArrayList<String>();
        if (ordenesSeleccionadas != null && !ordenesSeleccionadas.isEmpty()) {
            for (AgaceOrden lista : ordenesSeleccionadas) {
                listaID.add(lista.getIdOrden().toString());
            }
            return listaID;
        } else {
            listaID = null;
            return listaID;
        }
    }

    @Override
    @PistaAuditoria
    public String enviarValidarOrdenes(PropuestaPorValidar propuesta, FecebAccionPropuesta accionPropuesta,
            FecetPropuesta propuestaOrigen) {

        if (propuestaOrigen.getRfcSubadministrador() != null) {
            fecetPropuestaDao.actualizarEstatus(new BigDecimal(propuesta.getIdPropuesta()), Integer.parseInt(String
                    .valueOf(TipoEstatusEnum.PROPUESTA_REGISTRO_ASIGNADO_SUBADMINISTRADOR_EMISION_ODENES.getId())));
        } else {
            fecetPropuestaDao.actualizarEstatus(new BigDecimal(propuesta.getIdPropuesta()), Integer
                    .parseInt(String.valueOf(TipoEstatusEnum.PROPUESTA_ENVIADA_PARA_VERIFICACION_PROCEDENCIA.getId())));
        }

        feceaPropuestaAccionDao.updateAccionIdPropuesta(new BigDecimal(propuesta.getIdPropuesta()),
                AccionesFuncionarioEnum.APROBACION_VALIDACION.getIdAccion(), null);
        fecebAccionPropuestaDao.insert(accionPropuesta);
        return propuesta.getIdPropuesta();
    }

    /**
     * Metodo que cambia el estatus de la orden firmada, se cambian a los
     * valores VALIDAR_IND=1 FIRMAR_IND = 1
     */
    @Override
    @PistaAuditoria
    public String enviarRechazoOrden(PropuestaPorValidar propuesta, FecebAccionPropuesta accionPropuesta) {
        fecetPropuestaDao.actualizarEstatus(new BigDecimal(propuesta.getIdPropuesta()),
                Integer.parseInt(String.valueOf(TipoEstatusEnum.PROPUESTA_RECHAZADA_PARA_ADECUAR_POR_AUDITOR.getId())));
        fecebAccionPropuestaDao.insert(accionPropuesta);
        feceaPropuestaAccionDao.updateAccionIdPropuesta(new BigDecimal(propuesta.getIdPropuesta()),
                AccionesFuncionarioEnum.NO_APROBACION_VALIDACION.getIdAccion(), null);
        return propuesta.getIdPropuesta();
    }

    public FecetPropuesta getPropuesta(String idPropuesta) {
        List<FecetPropuesta> propuestas = fecetPropuestaDao.findWhereIdPropuestaEquals(new BigDecimal(idPropuesta));
        if (propuestas.size() > 0) {
            return propuestas.get(0);
        } else {
            return new FecetPropuesta();
        }
    }

    @Override
    public void actualizarRfcAdministrador(final BigDecimal idPropuesta, String rfcAdministrador) {
        fecetPropuestaDao.actualizarRfcAdministrador(idPropuesta, rfcAdministrador);
    }

    @Override
    public List<FecetOficio> getOficiosPorFirmar(BigDecimal idOrden) {
        return fecetOficioDao.getOficioByIdOrden(idOrden);
    }

    @Override
    public AgaceOrden obtenerOrdenByIdPropuesta(BigDecimal idPropuesta) {
        return getAgaceOrdenDao().findByIdPropuesta(idPropuesta);
    }

    @Override
    public void inactivaDoctoOrden(FecetRechazoOrden rechazoOrden) {
        fecetDocOrdenDao.updateEstatusDocFechaFin(new Date(), rechazoOrden.getIdDocOrden());
        fecetRechazoOrdenDao.insert(rechazoOrden);
    }

    @Override
    public void inactivaOficio(FecetRechazoOficio rechazoOficio) {
        fecetOficioDao.updateBlnActivoCero(rechazoOficio.getIdOficio());
        fecetRechazoOficioDao.insert(rechazoOficio);
    }

    @Override
    public List<FecetDocOrden> obtenerDocOrden(BigDecimal idOrden) {
        return fecetDocOrdenDao.findByFecetOrdenActivo(idOrden);
    }

    @Override
    public void enviarCorreoAprobar(PropuestaPorValidar propuestaValidar, BigDecimal idLeyenda) {
        Set<String> destinatarios = new TreeSet<String>();
        FecetPropuesta propuesta = fecetPropuestaDao
                .findWhereIdPropuestaEquals(new BigDecimal(propuestaValidar.getIdPropuesta())).get(0);
        // Modificar por la clave de submolulo para ordenes cuando se solicite
        // al SAT
        notificacionService.obtenerCorreoEmpleado(propuesta.getRfcAuditor(), TipoEmpleadoEnum.AUDITOR.getBigId(),
                destinatarios, ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoEmpleado(propuesta.getRfcAdministrador(),
                TipoEmpleadoEnum.ASIGNADOR_INSUMOS.getBigId(), destinatarios, ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoEmpleado(propuesta.getRfcFirmante(), TipoEmpleadoEnum.FIRMANTE.getBigId(),
                destinatarios, ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoEmpleado(TipoEmpleadoEnum.CONSULTOR_INSUMOS.getBigId(), propuesta.getIdArace(),
                destinatarios, ClvSubModulosAgace.PROPUESTAS);

        Map<String, String> data = notificacionService.obtenerDatosCorreo(idLeyenda);
        data.put(Common.ID_REGISTRO.toString(), propuesta.getIdRegistro());
        data.put(Common.ID_REGISTRO_ESPACIO.toString(), propuesta.getIdRegistro());
        enviarNotificacionInterna(data, ReportType.AVISOS_PROPUESTA_GENERICO, destinatarios);
    }

    @Override
    public void enviarCorreoNoAprobar(PropuestaPorValidar propuestaValidar, BigDecimal idLeyenda) {
        Set<String> destinatarios = new TreeSet<String>();
        FecetPropuesta propuesta = fecetPropuestaDao
                .findWhereIdPropuestaEquals(new BigDecimal(propuestaValidar.getIdPropuesta())).get(0);
        notificacionService.obtenerCorreoEmpleado(propuesta.getRfcAuditor(), TipoEmpleadoEnum.AUDITOR.getBigId(),
                destinatarios, ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoEmpleado(propuesta.getRfcFirmante(), TipoEmpleadoEnum.FIRMANTE.getBigId(),
                destinatarios, ClvSubModulosAgace.PROPUESTAS);
        notificacionService.obtenerCorreoEmpleado(TipoEmpleadoEnum.CONSULTOR_INSUMOS.getBigId(), propuesta.getIdArace(),
                destinatarios, ClvSubModulosAgace.PROPUESTAS);

        Map<String, String> data = notificacionService.obtenerDatosCorreo(idLeyenda);
        data.put(Common.ID_REGISTRO.toString(), propuesta.getIdRegistro());
        enviarNotificacionInterna(data, ReportType.AVISOS_PROPUESTA_GENERICO, destinatarios);
    }

    private void enviarNotificacionInterna(Map<String, String> data, ReportType reportType, Set<String> destinatarios) {
        try {
            notificacionService.enviarNotificacionGenerico(data, reportType, destinatarios);
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(), e);
        }
    }

    @Override
    public EmpleadoDTO obtenerDatosFirmanteSuplente(String rfc) {
        EmpleadoDTO firmanteSuplente = null;
        try {
            firmanteSuplente = getEmpleadoCompleto(rfc);
        } catch (EmpleadoServiceException e) {
            logger.error("No se encontro el Empleado solicitado.");
        }
        return firmanteSuplente;
    }

    @Override
    public List<EmpleadoDTO> obtenerAdministradorEmision(BigDecimal idArace) {

        List<EmpleadoDTO> administradores = new ArrayList<EmpleadoDTO>();

        try {
            administradores = getEmpleadosXUnidadAdmva(idArace.intValue(),
                    TipoEmpleadoEnum.ADMINISTRADOR_EMISION_ORDENES.getBigId().intValue(),
                    ClvSubModulosAgace.PROPUESTAS);
        } catch (EmpleadoServiceException e) {
            logger.error("No se encontraron los Empleados solicitados.");
        }

        return administradores;
    }

}
