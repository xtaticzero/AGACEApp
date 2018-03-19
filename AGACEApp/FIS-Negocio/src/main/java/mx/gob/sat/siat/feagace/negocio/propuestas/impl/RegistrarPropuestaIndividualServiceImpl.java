/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT)
 * Todos los derechos reservados.
 * Este software contiene información confidencial propiedad de
 * la Servicio de Administracion Tributaria (SAT)
 * Por lo cual no puede ser reproducido, distribuido o
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT).
 */
package mx.gob.sat.siat.feagace.negocio.propuestas.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececCausaProgramacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececConceptoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececPrioridadDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSectorDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSubprogramaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.insumos.FececTipoImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececRevisionDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececTipoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececAdministradorDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetDetalleContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetImpuestoDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetCambioMetodoOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocExpedienteDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececPrioridad;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececAdministrador;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleContribuyentePk;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DatosPropuestaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ProgramadorPropuestaAsignadaDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.LeyendasPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.propuestas.RegistrarPropuestaIndividualService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.NombreMes;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

/**
 * Servicios para registrar propuesta individual.
 *
 * @author Alfredo Ramirez - 749972
 * @version 1.0
 *
 */
@Service("registrarPropuestaIndividualService")
public class RegistrarPropuestaIndividualServiceImpl extends PropuestasServiceAbstract
        implements RegistrarPropuestaIndividualService {

    private static final long serialVersionUID = 4884503766418086144L;
    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;
    @Autowired
    private transient FecetDocExpedienteDao fecetDocExpedienteDao;
    @Autowired
    private transient FececSubprogramaDao fececSubprogramaDao;
    @Autowired
    private transient FeceaMetodoDao feceaMetodoDao;
    @Autowired
    private transient FececTipoPropuestaDao fececTipoPropuestaDao;
    @Autowired
    private transient FececCausaProgramacionDao fececCausaProgramacionDao;
    @Autowired
    private transient FececSectorDao fececSectorDao;
    @Autowired
    private transient FececRevisionDao fececRevisionDao;
    @Autowired
    private transient FececTipoImpuestoDao fececTipoImpuestoDao;
    @Autowired
    private transient FecetImpuestoDao fecetImpuestoDao;
    @Autowired
    private transient FecetContribuyenteDao fecetContribuyenteDao;
    @Autowired
    private transient FecetDetalleContribuyenteDao fecetDetalleContribuyenteDao;
    @Autowired
    private transient FececAdministradorDao fececAdministradorDao;
    @Autowired
    private transient FecetCambioMetodoOrdenDao fecetCambioMetodoOrdenDao;
    @Autowired
    private transient NotificacionService notificacionService;
    @Autowired
    private transient FececPrioridadDao fececPrioridadDao;
    @Autowired
    private transient FececConceptoDao fececConceptoDao;

    @Override
    public List<AraceDTO> getCatalogoUnidad() {

        try {
            List<AraceDTO> unidadesAdmin;
            unidadesAdmin = new ArrayList<AraceDTO>();
            List<AraceDTO> unidadAdminNoAplicable = new ArrayList<AraceDTO>();

            for (AraceDTO unidad : unidadesAdmin) {
                if (unidad.getIdArace().equals(Constantes.ACPPCE.intValue())
                        || unidad.getIdArace().equals(Constantes.ACIACE.intValue())
                        || unidad.getIdArace().equals(Constantes.PPCE.intValue())) {

                    unidadAdminNoAplicable.add(unidad);
                }
            }

            unidadesAdmin.removeAll(unidadAdminNoAplicable);

            return unidadesAdmin;
        } catch (Exception e) {
            logger.error("No se pudieron consultar las Unidades Administrativas");
            return null;
        }
    }

    @Override
    public List<FececSubprograma> getCatalogoSubprograma(BigDecimal idGeneral) {
        return fececSubprogramaDao.findActivos(idGeneral);
    }

    @Override
    public List<FececMetodo> getCatalogoMetodo() {
        return feceaMetodoDao.findAll();
    }

    @Override
    public List<FececMetodo> getCatalogoMetodoOrigen() {
        return feceaMetodoDao.findAll();
    }

    @Override
    public List<FececTipoPropuesta> getCatalogoTipoPropuesta() {
        return fececTipoPropuestaDao.findAll();
    }

    @Override
    public List<FececCausaProgramacion> getCatalogoCausaProgramacion() {
        return fececCausaProgramacionDao.findActivos();
    }

    @Override
    public List<FececSector> getCatalogoSector(BigDecimal idGeneral) {
        return fececSectorDao.findActivos(idGeneral);
    }

    @Override
    public List<FececRevision> getCatalogoRevision() {
        return fececRevisionDao.findAll();
    }

    @Override
    public List<FececTipoImpuesto> getCatalogoImpuesto() {
        return fececTipoImpuestoDao.findAll();
    }

    @Override
    public List<FececPrioridad> getCatalogoPrioridad() {
        return fececPrioridadDao.findActivos();
    }

    @Override
    public BigDecimal getConsecutivo() throws NegocioException {
        return fecetPropuestaDao.getConsecutivo();
    }

    @Override
    public BigDecimal getConsecutivoAnio(Date fechaInicio, Date fechaFinal) throws NegocioException {
        return fecetPropuestaDao.getConsecutivoAnio(fechaInicio, fechaFinal);
    }

    @Override
    public BigDecimal insertarContribuyente(FecetContribuyente dto) {
        return fecetContribuyenteDao.insert(dto).getIdContribuyente();
    }

    @Override
    public FecetDetalleContribuyentePk insertarDetalleContribuyente(FecetDetalleContribuyente dto) {
        return fecetDetalleContribuyenteDao.insert(dto);
    }

    @Override
    public FecetDetalleContribuyente findByRfc(String rfc) {
        return fecetDetalleContribuyenteDao.findByRfc(rfc);
    }

    @Override
    public FecetContribuyente findContribuyente(BigDecimal idContribuyente) {
        return fecetContribuyenteDao.findByPrimaryKey(idContribuyente);
    }

    @Override
    @PistaAuditoria
    public BigDecimal insertarPropuestaInsumos(final FecetPropuesta dto) {
        return fecetPropuestaDao.insert(dto);
    }

    @Override
    @PistaAuditoria
    public BigDecimal insertarPropuestaPropuestas(final FecetPropuesta dto) {
        return fecetPropuestaDao.insert(dto);
    }

    @Override
    public List<FececConcepto> getCatalogoConcepto() {
        return fececConceptoDao.findAll();
    }

    @Override
    public List<FececConcepto> getConceptoByImpuesto(BigDecimal idImpuesto) {
        return fececConceptoDao.findByIdTipoImpuesto(idImpuesto);
    }

    @Override
    public List<ProgramadorPropuestaAsignadaDTO> asignarPropuesta(BigDecimal idTipoEmpleado, BigDecimal idCentral) {

        try {
            List<EmpleadoDTO> programadoresUnidad = getEmpleadosXUnidadAdmva(idCentral.intValue(),
                    idTipoEmpleado.intValue(), ClvSubModulosAgace.PROPUESTAS);

            if (programadoresUnidad != null && !programadoresUnidad.isEmpty()) {

                StringBuilder condicionBusqueda = new StringBuilder();

                for (int i = 0; i < programadoresUnidad.size(); i++) {
                    if (i == programadoresUnidad.size() - 1) {
                        condicionBusqueda.append(programadoresUnidad.get(i).getIdEmpleado());
                    } else {
                        condicionBusqueda.append(programadoresUnidad.get(i).getIdEmpleado());
                        condicionBusqueda.append(",");
                    }
                }

                List<ProgramadorPropuestaAsignadaDTO> programadorAsignado = fecetPropuestaDao
                        .buscarProgramadorAsignar(condicionBusqueda.toString(), idCentral);
                List<ProgramadorPropuestaAsignadaDTO> programadorSeleccionado = determinaIdProgramadorAsignado(
                        programadorAsignado, programadoresUnidad);

                if (programadorSeleccionado != null && !programadorSeleccionado.isEmpty()) {
                    return programadorSeleccionado;
                } else {
                    return programadorAsignado;
                }
            }

        } catch (EmpleadoServiceException e) {
            logger.error("No se pudieron obtener los empleados");
            return null;
        }

        return null;
    }

    @Override
    public void insertarImpuesto(FecetImpuesto dto) {
        fecetImpuestoDao.insert(dto).getIdImpuesto();
    }

    @Override
    public FececAdministrador getAdministradorById(BigDecimal id) {
        return fececAdministradorDao.findByPrimaryKey(id);
    }

    @Override
    public void insertarDocumento(FecetDocExpediente dto) {
        fecetDocExpedienteDao.insert(dto);
    }

    @Override
    public void nuevofolioCambioMetodo(BigDecimal folio, BigDecimal orden) {
        fecetCambioMetodoOrdenDao.updateNuevaPropuesta(folio, orden);
    }

    @Override
    public BigDecimal getIdRegistroConsecutivo() {
        return fecetPropuestaDao.getIdRegistroConsecutivo();
    }

    @Override
    public void enviarNotificacionCorreoPropuesta(final FecetPropuesta propuesta, String destinatario) {

        Set<String> destinatarios = new TreeSet<String>();

        destinatarios.add(destinatario);

        List<DatosPropuestaDTO> propuestasSeleccionadas = new ArrayList<DatosPropuestaDTO>();
        DatosPropuestaDTO datosPropuesta = new DatosPropuestaDTO();
        datosPropuesta.setIdRegistro(propuesta.getIdRegistro());
        propuestasSeleccionadas.add(datosPropuesta);

        String tipoAviso = Constantes.ASUNTO_ATENCION_PROPUESTA_INDIVIDUAL;
        ReportType pantalla = ReportType.AVISO_ATENCION_REGISTRO_PROPUESTA_INDIVIDUAL;

        enviarCorreoPropuestas(destinatarios, propuestasSeleccionadas, tipoAviso, pantalla);
    }

    @Override
    public void enviarNotificacionCorreoPropuesta(final FecetPropuesta propuesta, Set<String> destinatarios) {
        List<DatosPropuestaDTO> propuestasSeleccionadas = new ArrayList<DatosPropuestaDTO>();
        DatosPropuestaDTO datosPropuesta = new DatosPropuestaDTO();
        datosPropuesta.setIdRegistro(propuesta.getIdRegistro());
        propuestasSeleccionadas.add(datosPropuesta);

        String tipoAviso = Constantes.ASUNTO_ATENCION_PROPUESTA_INDIVIDUAL;
        ReportType pantalla = ReportType.AVISO_ATENCION_REGISTRO_PROPUESTA_INDIVIDUAL;

        enviarCorreoPropuestas(destinatarios, propuestasSeleccionadas, tipoAviso, pantalla);
    }

    @Override
    public EmpleadoDTO getDatosEmpleado(BigDecimal idEmpleado) {

        EmpleadoDTO programadorAsignado = new EmpleadoDTO();

        try {
            programadorAsignado = getEmpleadoCompleto(idEmpleado.intValue());
        } catch (EmpleadoServiceException e) {
            logger.error("No se enuentra el empleado solicitado");
        }

        return programadorAsignado;
    }

    @Override
    public List<EmpleadoDTO> getDatosEmpleadoCentral(BigDecimal idTipoEmpleado, BigDecimal idArace) {

        List<EmpleadoDTO> usuarioCentral = null;

        try {
            usuarioCentral = getEmpleadosXUnidadAdmva(idArace.intValue(), idTipoEmpleado.intValue(),
                    ClvSubModulosAgace.PROPUESTAS);

            if (usuarioCentral == null || usuarioCentral.isEmpty()) {
                usuarioCentral = new ArrayList<EmpleadoDTO>();
            }

        } catch (EmpleadoServiceException e) {
            logger.error("No se encuetra el empleado solicitado");
        }

        return usuarioCentral;
    }

    private void enviarCorreoPropuestas(Set<String> destinatarios, List<DatosPropuestaDTO> propuestasSeleccionadas,
            String tipoAviso, ReportType pantalla) {

        logger.info("Envia correos de propuestas...");

        for (DatosPropuestaDTO propuesta : propuestasSeleccionadas) {
            Map<String, String> data = new HashMap<String, String>();
            data.put(Common.SUBJECT.toString(), tipoAviso);
            data.put("Id_Registro", propuesta.getIdRegistro().toString());
            try {
                this.enviarNotificacionCorreos(data, pantalla, destinatarios);
            } catch (Exception e) {
                logger.error("No se pudo enviar la notificacion", e);
            }
        }
    }

    private void enviarNotificacionCorreos(Map<String, String> data, ReportType reportType, Set<String> destinatarios) {

        try {
            Calendar date = new GregorianCalendar();
            data.put(Common.YEAR.toString(), "" + date.get(Calendar.YEAR));
            data.put(Common.DAY.toString(), "" + date.get(Calendar.DAY_OF_MONTH));
            data.put(Common.MONTH.toString(), NombreMes.obtenerNombre(date.get(Calendar.MONTH)));
            data.put(Common.HOUR.toString(), new SimpleDateFormat("HH:mm").format(date.getTime()));
            notificacionService.enviarNotificacion(data, reportType, destinatarios);
        } catch (Exception e) {
            logger.error("Error al establecer las fechas", e);
        }
    }

    @Override
    public void enviarNotificacionCorreoPropuesta(FecetPropuesta propuesta, Set<String> destinatarios,
            String rfcSesion) {

        notificacionService.obtenerCorreoCentralAcppce(rfcSesion, TipoEmpleadoEnum.PROGRAMADOR.getId(),
                propuesta.getIdArace(), destinatarios, ClvSubModulosAgace.PROPUESTAS);

        Map<String, String> data = notificacionService
                .obtenerDatosCorreo(LeyendasPropuestasEnum.PROPUESTA_ASIGNADA_VALIDACION.getIdLeyenda());
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

    private List<ProgramadorPropuestaAsignadaDTO> determinaIdProgramadorAsignado(
            List<ProgramadorPropuestaAsignadaDTO> programadoresAsignados, List<EmpleadoDTO> programadoresUnidad) {

        List<ProgramadorPropuestaAsignadaDTO> programadorAsignado = new ArrayList<ProgramadorPropuestaAsignadaDTO>();
        ProgramadorPropuestaAsignadaDTO programador = new ProgramadorPropuestaAsignadaDTO();

        if (programadoresAsignados == null || programadoresAsignados.isEmpty()) {

            programador.setIdEmpleado(programadoresUnidad.get(0).getIdEmpleado());
            programador.setTotalPropuestas(Constantes.ENTERO_CERO);

            programadorAsignado.add(programador);
        } else {
            for (int i = 0; i < programadoresAsignados.size(); i++) {
                for (int j = 0; j < programadoresUnidad.size(); j++) {

                    if (programadoresAsignados.get(i).getIdEmpleado() != null && programadoresUnidad.get(j)
                            .getIdEmpleado().equals(programadoresAsignados.get(i).getIdEmpleado())) {
                        programadoresUnidad.remove(j);
                    }
                }
            }

            if (programadoresUnidad.size() > 0) {

                programador.setIdEmpleado(programadoresUnidad.get(0).getIdEmpleado());
                programador.setTotalPropuestas(Constantes.CERO);

                programadorAsignado.add(programador);
            }
        }

        return programadorAsignado;
    }

    @Override
    public EmpleadoDTO validarUsuarioEntrante(String rfcSesion) {
        EmpleadoDTO programador = new EmpleadoDTO();
        try {
            programador = getEmpleadoCompleto(rfcSesion);
            if (!validarExistenciaTipoEmpleado(programador, TipoEmpleadoEnum.PROGRAMADOR)) {
                programador = null;
            }
        } catch (EmpleadoServiceException e) {
            logger.error("[{}]", e);
        }

        return programador;
    }
}
