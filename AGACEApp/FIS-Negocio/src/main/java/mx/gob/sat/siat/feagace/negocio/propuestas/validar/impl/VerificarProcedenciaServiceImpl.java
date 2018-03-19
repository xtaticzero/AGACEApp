package mx.gob.sat.siat.feagace.negocio.propuestas.validar.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.buzon.exception.BuzonNoDisponibleException;
import mx.gob.sat.siat.buzon.model.MedioComunicacion;
import mx.gob.sat.siat.buzon.service.BuzonTributarioService;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSubprogramaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececUnidadAdministrativaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetRechazoOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetRechazoOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.ConsecutivoMetodoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FeceaPropuestaAccionDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecebAccionPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyentePk;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaDocumentoElectronicoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ClaveFolioOficioDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasServiceAbstract;
import mx.gob.sat.siat.feagace.negocio.propuestas.carga.CargaDocumentoElectronicoService;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.VerificarProcedenciaService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;
import mx.gob.sat.siat.sise.service.SiseService;

@Service("verificarProcedenciaService")
public class VerificarProcedenciaServiceImpl extends PropuestasServiceAbstract implements VerificarProcedenciaService {

    private static final long serialVersionUID = 1L;

    @Autowired
    private transient FecetPropuestaDao fecetPropuestaDao;

    @Autowired
    private transient FeceaMetodoDao feceaMetodoDao;

    @Autowired
    private transient FecetContribuyenteDao fecetContribuyenteDao;

    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;

    @Autowired
    private transient SiseService siseService;

    @Autowired
    private transient FececSubprogramaDao fececSubprogramaDao;

    @Autowired
    private transient BuzonTributarioService buzonTributarioService;

    @Autowired
    private transient FecetDocOrdenDao fecetDocOrdenDao;

    @Autowired
    private transient NotificacionService notificacionService;

    @Autowired
    private transient CargaDocumentoElectronicoService cargaDocumentoElectronicoService;

    @Autowired
    private transient ConsecutivoMetodoPropuestaDao consecutivoMetodo;
    @Autowired
    private transient FecetOficioDao fecetOficioDao;
    @Autowired
    private transient FeceaPropuestaAccionDao feceaPropuestaAccionDao;
    @Autowired
    private transient FecebAccionPropuestaDao fecebAccionPropuestaDao;
    @Autowired
    private transient FecetRechazoOficioDao fecetRechazoOficioDao;
    /**
     * fececUnidadAdministrativaDao consulta para la generacion de folio de
     * oficio
     *
     */
    @Autowired
    private transient FececUnidadAdministrativaDao fececUnidadAdministrativaDao;
    @Autowired
    private transient FecetRechazoOrdenDao fecetRechazoOrdenDao;

    private static final BigDecimal ESTATUS_ASIGNADO_A_SUBADMINISTRADOR = new BigDecimal(71);

    @Override
    public List<FecetPropuesta> obtenerPropuestasAsignadasASubAdmin(String rfcSubAdmin) {

        List<FecetPropuesta> listaProp = fecetPropuestaDao.findWhereRfcSubadministradorAndIdEstatusEquals(rfcSubAdmin,
                ESTATUS_ASIGNADO_A_SUBADMINISTRADOR);
        for (FecetPropuesta propuesta : listaProp) {
            propuesta.setFececSubprograma(
                    fececSubprogramaDao.findWhereIdSubprogramaEquals(propuesta.getIdSubprograma()).get(0));
            propuesta.setFeceaMetodo(feceaMetodoDao.findWhereIdMetodo(propuesta.getIdMetodo()).get(0));
            propuesta.setFecetContribuyente(fecetContribuyenteDao.findByPrimaryKey(propuesta.getIdContribuyente()));
            try {
                propuesta.setEmpleadoDto(getEmpleadoCompleto(rfcSubAdmin));
            } catch (EmpleadoServiceException e) {
                logger.error(e.getMessage());
            }
        }
        return listaProp;

    }

    public boolean validaPPEE(String rfc) {
        logger.info("--- validaPPEE ---");
        int info;
        try {
            info = siseService.verInformacion(rfc);

        } catch (Exception e) {
            logger.error("No se pudo acceder al servicio SISE:" + e);
            return false;
        }
        logger.info("--- PPEE:" + info);

        return info == 1 ? true : false;
    }

    @Override
    @PistaAuditoria
    public boolean validaEstatusContribuyente(String rfc) {
        logger.info("--- validaEstatusContribuyente ---");
        boolean isAmparado = false;

        List<MedioComunicacion> listaMedios;
        try {
            listaMedios = buzonTributarioService.obtenerMediosComunicacion(rfc);
        } catch (BuzonNoDisponibleException e) {
            logger.error("No se pudo acceder al buzon de notificaciones:" + e);

            return isAmparado;
        }
        if (listaMedios != null) {
            isAmparado = procesaStatusMediosContacto(listaMedios);
            logger.info("--- listaMedios size:" + listaMedios.size());
        }

        return isAmparado;
    }

    private boolean procesaStatusMediosContacto(List<MedioComunicacion> listMedioCom) {
        boolean result = false;
        for (MedioComunicacion medioComunicacion : listMedioCom) {
            if (medioComunicacion.getDescMedio() != null && !medioComunicacion.getDescMedio().trim().equals("")
                    && medioComunicacion.getDescMedio().toUpperCase().contains(ConstantesPropuestas.CORREO)) {
                logger.info("--- Descripcion medio comunicacion:" + medioComunicacion.getDescMedio());
                if (medioComunicacion.getMedio() != null && !medioComunicacion.getMedio().trim().equals("")) {
                    logger.info("--- Amparado:" + medioComunicacion.getAmparado());
                    if (medioComunicacion.getAmparado() == 1) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    private boolean validarMetodo(String metodo) {
        return (metodo.equals("EHO") || metodo.equals("UCA") || metodo.equals("REE") || metodo.equals("MCA"));
    }

    @Override
    @PistaAuditoria
    public BigDecimal aprobarPropuesta(CargaDocumentoElectronicoDTO cargaDocElectronicoDTO) {
        try {
            String numeroOrden = generarNumeroOrden(cargaDocElectronicoDTO.getFecetPropuesta());
            logger.info("Numero Orden...." + numeroOrden);

            String folioOficio = generarFolioOficio(cargaDocElectronicoDTO.getFecetPropuesta());
            logger.info("Numero Oficio...." + folioOficio);
            logger.info("getIdPropuesta...." + cargaDocElectronicoDTO.getFecetPropuesta().getIdPropuesta());
            fecetPropuestaDao.actualizarEstatus(cargaDocElectronicoDTO.getFecetPropuesta().getIdPropuesta(),
                    Constantes.ENTERO_CINCUENTANUEVE);
            agaceOrdenDao.updateNumOrdenAndNumFolio(numeroOrden, folioOficio,
                    cargaDocElectronicoDTO.getFecetPropuesta().getIdPropuesta(), Constantes.ENTERO_CIENTO_UNO);

            for (FecetOficio oficios : obtengoFolios(cargaDocElectronicoDTO)) {
                String numeroOficio = generarNumeroOficio(cargaDocElectronicoDTO.getFecetPropuesta(), oficios);
                logger.info("Numero Oficio para documento Oficio...." + numeroOficio);
                fecetOficioDao.updateNumeroOficio(oficios, numeroOficio);
            }
            String rfcFirmante = cargaDocElectronicoDTO.getFecetPropuesta().getRfcSubadministrador();

            EmpleadoDTO empleado = getEmpleadoCompleto(rfcFirmante);
            FecebAccionPropuesta historialAccion = new FecebAccionPropuesta();
            historialAccion.setFechaHora(new Date());
            historialAccion.setIdAccion(AccionesFuncionarioEnum.APROBACION_ORDEN.getIdAccion());
            historialAccion.setIdPropuesta(cargaDocElectronicoDTO.getFecetPropuesta().getIdPropuesta());
            historialAccion.setIdEmpleado(empleado.getIdEmpleado());
            feceaPropuestaAccionDao.updateAccionIdPropuesta(cargaDocElectronicoDTO.getFecetPropuesta().getIdPropuesta(),
                    AccionesFuncionarioEnum.APROBACION_ORDEN.getIdAccion(), null);
            fecebAccionPropuestaDao.insert(historialAccion);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return cargaDocElectronicoDTO.getFecetPropuesta().getIdPropuesta();
    }

    public List<FecetOficio> obtengoFolios(CargaDocumentoElectronicoDTO cargaDocElectronicoDTO) {
        List<FecetDocOrden> orden = fecetDocOrdenDao
                .findByIdPropuesta(cargaDocElectronicoDTO.getFecetPropuesta().getIdPropuesta());
        return oficiosPorFirmar(orden.get(0).getIdOrden());
    }

    @Override
    @PistaAuditoria
    public BigDecimal rechazarPropuesta(FecetPropuesta propuesta, FecetContribuyente contribuyenteIdc,
            List<FecetDocExpediente> docOrden) {
        fecetPropuestaDao.cambiarEstatusPropuesta(propuesta);
        try {
            EmpleadoDTO empleado;
            empleado = getEmpleadoCompleto(propuesta.getRfcSubadministrador());
            FecebAccionPropuesta historialAccion = new FecebAccionPropuesta();
            historialAccion.setFechaHora(new Date());
            historialAccion.setIdAccion(AccionesFuncionarioEnum.NO_APROBACION_ORDEN.getIdAccion());
            historialAccion.setIdPropuesta(propuesta.getIdPropuesta());
            historialAccion.setIdEmpleado(empleado.getIdEmpleado());
            feceaPropuestaAccionDao.updateAccionIdPropuesta(propuesta.getIdPropuesta(),
                    AccionesFuncionarioEnum.NO_APROBACION_ORDEN.getIdAccion(), null);
            fecebAccionPropuestaDao.insert(historialAccion);

        } catch (EmpleadoServiceException e) {
            logger.error(e.getMessage(), e);
        }
        
        return propuesta.getIdPropuesta();
    }

    private String generarNumeroOrden(FecetPropuesta propuesta) {
        StringBuilder numeroOrden = new StringBuilder();
        numeroOrden.append("E");
        if (validarMetodo(propuesta.getFeceaMetodo().getAbreviatura())) {
            numeroOrden.append(propuesta.getFeceaMetodo().getAbreviatura());
        } else {
            numeroOrden.append(propuesta.getFececRevision().getDescripcion());
        }
        numeroOrden.append(propuesta.getIdArace().intValue() < Constantes.ENTERO_DIEZ
                ? "0".concat(propuesta.getIdArace().toString()) : propuesta.getIdArace());
        numeroOrden.append(obtenerConsecutivo(propuesta.getIdMetodo()));
        numeroOrden.append("-");
        Calendar cal = Calendar.getInstance();
        String anio = String.valueOf(cal.get(Calendar.YEAR));
        numeroOrden.append(anio.substring(anio.length() - 2, anio.length()));

        return numeroOrden.toString();
    }

    private String generarFolioOficio(FecetPropuesta propuesta) {
        ClaveFolioOficioDTO claveDTO = fececUnidadAdministrativaDao.obtenerClavesFolioOficio(propuesta.getIdArace());
        StringBuilder numeroOficio = new StringBuilder();
        numeroOficio.append("E");
        numeroOficio.append("-");
        numeroOficio.append(claveDTO.getClaveAdministracionGeneral());
        numeroOficio.append("-");
        numeroOficio.append(claveDTO.getClaveAdministracionArea());
        numeroOficio.append("-");
        numeroOficio.append(claveDTO.getClaveSubadmistracionArea());
        numeroOficio.append("-");
        numeroOficio.append(claveDTO.getClaveJefaturaDepto());
        numeroOficio.append("-");
        Calendar cal = Calendar.getInstance();
        String anio = String.valueOf(cal.get(Calendar.YEAR));
        numeroOficio.append(anio);
        numeroOficio.append("-");
        numeroOficio.append(propuesta.getIdPropuesta());

        return numeroOficio.toString();
    }

    private String generarNumeroOficio(FecetPropuesta propuesta, FecetOficio oficios) {
        ClaveFolioOficioDTO claveDTO = fececUnidadAdministrativaDao.obtenerClavesFolioOficio(propuesta.getIdArace());
        StringBuilder numeroOficio = new StringBuilder();
        numeroOficio.append("E");
        numeroOficio.append("-");
        numeroOficio.append(claveDTO.getClaveAdministracionGeneral());
        numeroOficio.append("-");
        numeroOficio.append(claveDTO.getClaveAdministracionArea());
        numeroOficio.append("-");
        numeroOficio.append(claveDTO.getClaveSubadmistracionArea());
        numeroOficio.append("-");
        numeroOficio.append(claveDTO.getClaveJefaturaDepto());
        numeroOficio.append("-");
        Calendar cal = Calendar.getInstance();
        String anio = String.valueOf(cal.get(Calendar.YEAR));
        numeroOficio.append(anio);
        numeroOficio.append("-");
        numeroOficio.append(oficios.getIdOficio());

        return numeroOficio.toString();
    }

    public List<FecetDocOrden> obtenerOrden(BigDecimal idPropuesta) {
        return fecetDocOrdenDao.findByIdPropuesta(idPropuesta);

    }

    public List<FecetDocExpediente> obtenerDocsOrden(BigDecimal idPropuesta) {

        return fecetDocOrdenDao.findWhereIdPropuestaEquals(idPropuesta);

    }

    @Override
    public void enviarCorreo(Map<String, String> data, ReportType reportType, Set<String> destinatarios) {

        try {
            getNotificacionService().enviarNotificacionGenerico(data, reportType, destinatarios);
        } catch (BusinessException e) {
            logger.error("No se pudo enviar el correo de notificacion [{}]", e.getCause(), e);
        }
    }

    @Override
    public CargaDocumentoElectronicoDTO validarContribuyenteVersusIdc(String rfc, BigDecimal idContribuyente)
            throws NegocioException {

        FecetContribuyente contribuyenteBd;
        FecetContribuyente contribuyenteIdc;
        CargaDocumentoElectronicoDTO cargaDocumentoElectronicoDTO;
        cargaDocumentoElectronicoDTO = new CargaDocumentoElectronicoDTO();

        try {
            contribuyenteBd = fecetContribuyenteDao.findWhereIdContribuyenteEquals(idContribuyente).get(0);
            contribuyenteIdc = cargaDocumentoElectronicoService.validarIDC(rfc);
            contribuyenteIdc.setRfc(rfc);
            contribuyenteIdc.setIdContribuyente(contribuyenteBd.getIdContribuyente());
            logger.info("Contribuyente BD " + contribuyenteBd);
            logger.info("Contribuyente IDC " + contribuyenteIdc);
            if (!contribuyenteBd.equalsALL(contribuyenteIdc)) {
                cargaDocumentoElectronicoDTO.setExistenCambios(true);
                cargaDocumentoElectronicoDTO.setContribuyenteIdc(contribuyenteIdc);
                cargaDocumentoElectronicoDTO.setContribuyente(contribuyenteBd);
            } else {
                cargaDocumentoElectronicoDTO.setExistenCambios(false);
                cargaDocumentoElectronicoDTO.setContribuyente(contribuyenteBd);
            }

        } catch (Exception e) {
            logger.error(ConstantesError.ERROR_DATOS_REQUERIDOS);
            throw new NegocioException(Constantes.FALLO_CONSULTA + e.getCause(), e);
        }
        return cargaDocumentoElectronicoDTO;
    }

    @Override
    public EmpleadoDTO getDatosEmpleadoSesion(String rfcSesion) {
        EmpleadoDTO empleado = null;
        try {
            empleado = getEmpleadoCompleto(rfcSesion);
        } catch (EmpleadoServiceException e) {
            logger.error(e.getMessage());
        }
        return empleado;
    }

    @Override
    public void actualizaContribuyente(FecetContribuyente contribuyenteIdc) {

        FecetContribuyentePk idContribuyente = new FecetContribuyentePk();
        idContribuyente.setIdContribuyente(contribuyenteIdc.getIdContribuyente());
        fecetContribuyenteDao.update(idContribuyente, contribuyenteIdc);
    }

    private String obtenerConsecutivo(BigDecimal idMetodo) {

        String consecutivo = null;

        switch (idMetodo.intValue()) {

            case Constantes.UNO:
                consecutivo = armaConsecutivo(consecutivoMetodo.getConsecutivoEHO());
                break;
            case Constantes.ENTERO_DOS:
                consecutivo = armaConsecutivo(consecutivoMetodo.getConsecutivoORG());
                break;
            case Constantes.ENTERO_TRES:
                consecutivo = armaConsecutivo(consecutivoMetodo.getConsecutivoUCA());
                break;
            case Constantes.ENTERO_CUATRO:
                consecutivo = armaConsecutivo(consecutivoMetodo.getConsecutivoREE());
                break;
            case Constantes.ENTERO_CINCO:
                consecutivo = armaConsecutivo(consecutivoMetodo.getConsecutivoMCA());
                break;
            default:
                break;
        }

        return consecutivo;
    }

    private String armaConsecutivo(int secuencia) {

        String secuencial = null;
        StringBuilder consecutivo = new StringBuilder();

        secuencial = String.valueOf(secuencia).toString();

        if (secuencial.length() == Constantes.ENTERO_UNO) {
            consecutivo.append("0000").append(secuencial);
        } else if (secuencial.length() == Constantes.ENTERO_DOS) {
            consecutivo.append("000").append(secuencial);
        } else if (secuencial.length() == Constantes.ENTERO_TRES) {
            consecutivo.append("00").append(secuencial);
        } else if (secuencial.length() == Constantes.ENTERO_CUATRO) {
            consecutivo.append("0").append(secuencial);
        } else {
            consecutivo.append(secuencial);
        }

        return consecutivo.toString();
    }

    public List<FecetOficio> obtieneEmpleadoDocOficio(List<FecetOficio> lisOficio) {
        List<FecetOficio> list = new ArrayList<FecetOficio>();
        try {
            for (FecetOficio oficio : lisOficio) {
                EmpleadoDTO empleado = getEmpleadoCompleto(oficio.getIdEmpleado().intValue());
                TipoEmpleadoEnum tipoEmpleadoEnum = TipoEmpleadoEnum
                        .parse(Integer.parseInt(oficio.getDescripcionAccion()));
                oficio.setDescripcionEmpleado(
                        empleado.getNombreCompleto() + " (" + tipoEmpleadoEnum.getDescripcion() + ")");
                list.add(oficio);
            }

        } catch (EmpleadoServiceException e) {
            logger.error(e.getMessage());
        }
        return list;
    }

    public List<FecetDocOrden> obtieneEmpleadoDocOrden(List<FecetDocOrden> lisOrden) {
        List<FecetDocOrden> list = new ArrayList<FecetDocOrden>();
        try {
            for (FecetDocOrden orden : lisOrden) {

                EmpleadoDTO empleado = getEmpleadoCompleto(orden.getIdEmpleado().intValue());
                TipoEmpleadoEnum tipoEmpleadoEnum = TipoEmpleadoEnum
                        .parse(Integer.parseInt(orden.getDescripcionAccion()));

                orden.setDescripcionEmpleado(empleado.getNombre() + " (" + tipoEmpleadoEnum.getDescripcion() + ")");
                list.add(orden);
            }

        } catch (EmpleadoServiceException e) {
            logger.error(e.getMessage());

        }
        return list;
    }

    @Override
    public List<FecetOficio> oficiosPorFirmar(BigDecimal idOrden) {
        return fecetOficioDao.getOficioActivosByIdOrden(idOrden);
    }

    @Override
    public List<FecetOficio> oficiosHistorial(BigDecimal idOrden) {
        return obtieneEmpleadoDocOficio(fecetOficioDao.getOficioIdOrdenHistorial(idOrden));
    }

    public List<FecetDocOrden> obtenerDocsOrdenHistorial(BigDecimal idPropuesta) {
        return obtieneEmpleadoDocOrden(fecetDocOrdenDao.accionIdPropuestaHistorial(idPropuesta));
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    @Override
    public void inactivaDoctoOrden(BigDecimal idDocOrden) {
        fecetDocOrdenDao.updateEstatusDocFechaFin(new Date(), idDocOrden);
    }

    @Override
    public void rechazoDoctoOrden(FecetRechazoOrden rechazoDocOrden) {
        fecetRechazoOrdenDao.insert(rechazoDocOrden);
    }

    @Override
    public void rechazoOficio(FecetRechazoOficio rechazoOficio) {
        fecetRechazoOficioDao.insert(rechazoOficio);
    }

    @Override
    public void inactivaOficio(BigDecimal idOficio) {
        fecetOficioDao.updateBlnActivoCero(idOficio);
    }
}
