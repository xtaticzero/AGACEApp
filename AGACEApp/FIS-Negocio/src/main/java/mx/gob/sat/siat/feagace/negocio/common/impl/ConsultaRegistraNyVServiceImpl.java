package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.remoting.soap.SoapFaultException;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetDetalleNyVDAO;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.ActoAdministrativo;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.NotificableNyV;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaRegistraNyVService;
import mx.gob.sat.siat.feagace.negocio.common.CopiarDocumentosService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.helper.ConsultarRegistrarNyVHelper;
import mx.gob.sat.siat.ws.nyv.client.NyVServiceClient;
import mx.gob.sat.siat.ws.nyv.client.dto.ActoAdministrativoVO;
import mx.gob.sat.siat.ws.nyv.client.dto.ConsultaNumeroReferenciaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.RegistroActoAdministrativo;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseConsultaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseRegistroVO;
import mx.gob.sat.siat.ws.nyv.client.impl.NyVRepositoryMockServiceImpl;
import mx.gob.sat.siat.ws.nyv.exception.NyVClientException;

@Service("consultaRegistraNyVService")
public class ConsultaRegistraNyVServiceImpl extends BaseBusinessServices implements ConsultaRegistraNyVService {

    private static final long serialVersionUID = -1213412546869120533L;
    private static final String TXT = ".txt";
    @Autowired
    @Qualifier("nyvServiceClient")
    private transient NyVServiceClient nyvServiceClient;

    @Autowired
    private transient FecetDetalleNyVDAO fecetDetalleNyVDAO;

    @Autowired
    private transient ConsultarRegistrarNyVHelper consultarRegistrarNyVHelper;

    @Autowired
    private transient CopiarDocumentosService copiarDocumentosService;

    @Autowired
    private transient FecetOficioDao fecetOficioDao;

    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;

    @Autowired
    private transient FecetProrrogaOficioDao fecetProrrogaOficioDao;

    @Autowired
    private transient FecetProrrogaOrdenDao fecetProrrogaOrdenDao;

    @Autowired
    private transient FecetPruebasPericialesDao fecetPruebasPericialesDao;

    @Value("${ruta.anexos.orden}")
    private String rutaBaseAnexo;

    private static final String NYV_OK = "OK";

    public ConsultaRegistraNyVServiceImpl() {
        super();
    }

    private FecetDetalleNyV obtenDetalleNyV(FecetDetalleNyV detalleNyV) {
        FecetDetalleNyV detalleNyVresult = null;
        try {
            detalleNyVresult = fecetDetalleNyVDAO.buscaDetalleNyVPorId(detalleNyV);
            if (detalleNyVresult != null && detalleNyVresult.getFecSurteEfectosNyV() == null) {
                ResponseConsultaVO respConsulta = nyvServiceClient.consultarActoAdministrativo(detalleNyVresult.getFolioNyV());
                if (respConsulta != null && NYV_OK.equalsIgnoreCase(respConsulta.getCodigoRespuesta())) {
                    validarLlamadoNyV(detalleNyVresult, respConsulta);
                } else {
                    logger.error(respConsulta != null ? respConsulta.getMensajeRespuesta() : "Respuesta invalida del servicio");
                }
            }
        } catch (SoapFaultException e) {
            logger.error(e.getFaultString(), e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return detalleNyVresult;
    }

    private void validarLlamadoNyV(FecetDetalleNyV detalleNyV, ResponseConsultaVO respConsulta) throws NyVClientException {
        if (consultarRegistrarNyVHelper.validaFechasNyV(respConsulta, detalleNyV)) {
            fecetDetalleNyVDAO.guardaDetalleNyV(detalleNyV);
        } else {
            List<FecetDetalleNyV> detalles = fecetDetalleNyVDAO.obtenerDetalleByIdPadre(detalleNyV);
            if (detalles != null && !detalles.isEmpty()) {
                ResponseConsultaVO respuesta = null;
                for (FecetDetalleNyV detalle : detalles) {
                    respuesta = nyvServiceClient.consultarActoAdministrativo(detalle.getFolioNyV());
                    if (respuesta != null && NYV_OK.equalsIgnoreCase(respuesta.getCodigoRespuesta())) {
                        if (consultarRegistrarNyVHelper.validaFechasNyV(respConsulta, detalleNyV)) {
                            fecetDetalleNyVDAO.guardaDetalleNyV(detalleNyV);
                            break;
                        }
                    } else {
                        logger.error(respConsulta != null ? respConsulta.getMensajeRespuesta() : "Respuesta invalida del servicio");
                    }
                }
            }
        }
    }

    @Override
    public boolean filtrarNotificablePorFecha(NotificableNyV notificable) {
        FecetDetalleNyV detalleNyv = this.obtenDetalleNyV(notificable.getFecetDetalleNyV());
        if (detalleNyv != null) {
            notificable.setFecetDetalleNyV(detalleNyv);
            verificarRuta(notificable, detalleNyv);
            return consultarRegistrarNyVHelper.comparaFechas(detalleNyv.getFecSurteEfectosNyV());
        }
        return false;
    }

    @Override
    public FecetDetalleNyV registrarActoAdministrativo(NotificableNyV notificable, Date fechaDiligencia, FececActosAdm actoBD) throws NegocioException {
        FecetDetalleNyV nYvResult = new FecetDetalleNyV();
        ResponseRegistroVO respRegistro = null;
        List<String> lstRutas = new ArrayList<String>();
        String rutaBase = "";
        try {
            if (rutaBaseAnexo != null && !rutaBaseAnexo.isEmpty()) {
                rutaBase = rutaBaseAnexo;
            } else {
                logger.error("El valor de la propiedad ruta.anexos.orden es nulo");
                rutaBase = Constantes.DIRECTORIO_ANEXOS_ACTO_ADM;
            }
            consultarRegistrarNyVHelper.copiarAnexosDocumentos(copiarDocumentosService, notificable, actoBD, lstRutas, rutaBase);
            RegistroActoAdministrativo rActoAdminVO = consultarRegistrarNyVHelper.fillActoAdministrativo(notificable, fechaDiligencia);
            if (notificable.getDatosNotificable().isInsertaNyV()) {
                registarNumeroReferenciaOrigen(notificable, rActoAdminVO);
            }
            ConsultaNumeroReferenciaVO cNoRef = consultarNumeroReferencia(rActoAdminVO.getNumeroReferencia());
            if (cNoRef != null && cNoRef.getFolioActoAdmin() != null) {

                logger.debug(cNoRef.getFolioActoAdmin());
                nYvResult.setFolioNyV(cNoRef.getFolioActoAdmin());
                nYvResult.setFolioActoAdmvo(String.valueOf(rActoAdminVO.getClaveActoAdmin()));
                nYvResult.setRfcNotifica(rActoAdminVO.getRfcDestinatario());
                if (notificable.getDatosNotificable().isInsertaNyV()) {
                    fecetDetalleNyVDAO.guardaFolioNyV(nYvResult);
                    notificable.getDatosNotificable().setIdNyvPadre(nYvResult.getIdNyV());
                } else {
                    nYvResult.setIdNyVPadre(notificable.getDatosNotificable().getIdNyvPadre());
                    fecetDetalleNyVDAO.guardaFolioNyV(nYvResult);
                }

            } else {
                respRegistro = nyvServiceClient.registrarActoAdministrativo(rActoAdminVO);
                if (respRegistro != null && NYV_OK.equalsIgnoreCase(respRegistro.getCodigoRespuesta())) {
                    logger.debug(respRegistro.getMensajeRespuesta());
                    logger.debug(respRegistro.getFolio());
                    logger.debug(respRegistro.getCodigoRespuesta());
                    nYvResult.setFolioNyV(respRegistro.getFolio());
                    nYvResult.setFolioActoAdmvo(String.valueOf(rActoAdminVO.getClaveActoAdmin()));
                    nYvResult.setRfcNotifica(rActoAdminVO.getRfcDestinatario());
                    if (notificable.getDatosNotificable().isInsertaNyV()) {
                        fecetDetalleNyVDAO.guardaFolioNyV(nYvResult);
                        notificable.getDatosNotificable().setIdNyvPadre(nYvResult.getIdNyV());
                    } else {
                        nYvResult.setIdNyVPadre(notificable.getDatosNotificable().getIdNyvPadre());
                        fecetDetalleNyVDAO.guardaFolioNyV(nYvResult);
                    }
                } else {
                    copiarDocumentosService.eliminarAnexos(lstRutas);
                    throw new NegocioException("Error al registrar acto administrativo: " + respRegistro != null ? respRegistro.getMensajeRespuesta() : "");
                }
            }
        } catch (SoapFaultException e) {
            logger.error(e.getFaultString(), e);
        } catch (Exception e) {
            copiarDocumentosService.eliminarAnexos(lstRutas);
            logger.error(e.getMessage(), e);
            throw new NegocioException(e.getMessage(), e);
        }
        return nYvResult;
    }

    @Override
    public ConsultaNumeroReferenciaVO consultarNumeroReferencia(String numeroReferencia) throws NyVClientException {
        try {
            return nyvServiceClient.consultarNumeroReferencia(numeroReferencia);
        } catch (NyVClientException ex) {
            logger.error("asignarFechaNyV Err");
            logger.error(ex.getMessage());
        }

        return null;
    }

    @Override
    public void asignarFechaNotificable(NotificableNyV notificable) {
        if (notificable.getFecetDetalleNyV() != null) {
            FecetDetalleNyV detalleNyv = this.obtenDetalleNyV(notificable.getFecetDetalleNyV());
            notificable.setFecetDetalleNyV(detalleNyv);
            verificarRuta(notificable, detalleNyv);
        }
    }

    @Override
    public Map<Long, ActoAdministrativo> obtenerListaActoAdministrativo(String claveUnidadAdministrativa) {
        Map<Long, ActoAdministrativo> listaActoAdministrativoDocumento = new HashMap<Long, ActoAdministrativo>();
        try {
            List<ActoAdministrativoVO> respuestaConsulta = nyvServiceClient.consultarListaActosAdmin(claveUnidadAdministrativa);
            listaActoAdministrativoDocumento = consultarRegistrarNyVHelper.llenarListaActoAdministrativo(respuestaConsulta);
        } catch (SoapFaultException e) {
            logger.error(e.getFaultString(), e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return listaActoAdministrativoDocumento;
    }

    @Override
    public ActoAdministrativo obtenerActoAdministrativo(String claveUnidadAdmin, Long idActoNyv) {
        ActoAdministrativo resultado = null;
        try {
            List<ActoAdministrativoVO> response = nyvServiceClient.consultarListaActosAdmin(claveUnidadAdmin);
            ActoAdministrativoVO resultadoAux = null;
            if (response != null && !response.isEmpty()) {
                if (nyvServiceClient instanceof NyVRepositoryMockServiceImpl) {
                    resultadoAux = new ActoAdministrativoVO();
                    resultadoAux.setId(idActoNyv);
                    resultadoAux.setNombre("idActo");
                    resultadoAux.setPrefijoReferencia(claveUnidadAdmin);
                } else {
                    for (ActoAdministrativoVO registro : response) {
                        if (registro.getId() == idActoNyv) {
                            resultadoAux = registro;
                            break;
                        }
                    }
                }
            }
            if (resultadoAux != null) {
                resultado = consultarRegistrarNyVHelper.fillActoAdministrativoVO(resultadoAux);
            }
        } catch (SoapFaultException e) {
            logger.error(e.getFaultString(), e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return resultado;
    }

    private void verificarRuta(NotificableNyV notificable, FecetDetalleNyV detalleNyv) {
        if (detalleNyv != null && detalleNyv.getRutaAcuseNyv() != null && !detalleNyv.getRutaAcuseNyv().isEmpty()) {
            try {
                fecetDetalleNyVDAO.actualizaNotificableRuta(notificable, detalleNyv.getRutaAcuseNyv());
            } catch (Exception ex) {
                logger.error("Error al actualizar ruta NyV : ".concat(notificable.getDescriptor().getNombreTabla()), ex);
            }
        }
    }

    @Override
    public void asignarFechaNyV() {
        List<FecetDetalleNyV> listaDetalleNyV = fecetDetalleNyVDAO.buscaAllDetalleNyV();
        for (FecetDetalleNyV fecetDetalleNyV : listaDetalleNyV) {
            try {
                ResponseConsultaVO respConsulta = nyvServiceClient.consultarActoAdministrativo(fecetDetalleNyV.getFolioNyV());
                if (respConsulta != null && NYV_OK.equalsIgnoreCase(respConsulta.getCodigoRespuesta())) {
                    if (consultarRegistrarNyVHelper.validaFechasNyV(respConsulta, fecetDetalleNyV)) {

                        fecetDetalleNyV.setRutaAcuseNyv(respConsulta.getUrlAvisoNotificacion());
                        fecetDetalleNyVDAO.guardaDetalleNyV(fecetDetalleNyV);
                    }
                } else {
                    logger.error(respConsulta != null ? respConsulta.getMensajeRespuesta() : "Respuesta invalida del servicio");
                }
            } catch (NyVClientException ex) {
                logger.error("asignarFechaNyV Err");
                logger.error(ex.getMessage());
            }

        }
    }

    @Override

    public void registarNumeroReferenciaOrigen(NotificableNyV notificable, RegistroActoAdministrativo registroVO) {
        if (notificable instanceof FecetOficio) {
            consultarRegistrarNyVHelper.crearArchivoTempNyv(Constantes.DIRECTORIO_TEMPORAL_NYV, notificable.getDatosNotificable().getRfcFirmante() + TXT, "AgaceOficio", String.valueOf(((FecetOficio) notificable).getIdOficio().longValue()), registroVO.getNumeroReferencia());
            fecetOficioDao.updateNumeroReferenciaOficio(registroVO.getNumeroReferencia(), ((FecetOficio) notificable).getIdOficio().longValue());
            registroVO.setNumeroReferencia(fecetOficioDao.obtenerNumeroReferenciaOficio(((FecetOficio) notificable).getIdOficio().longValue()));
        }

        if (notificable instanceof AgaceOrden) {
            consultarRegistrarNyVHelper.crearArchivoTempNyv(Constantes.DIRECTORIO_TEMPORAL_NYV, notificable.getDatosNotificable().getRfcFirmante() + TXT, "AgaceOrden", String.valueOf(((AgaceOrden) notificable).getIdOrden().longValue()), registroVO.getNumeroReferencia());
            agaceOrdenDao.updateNumeroReferenciaOrden(registroVO.getNumeroReferencia(), ((AgaceOrden) notificable).getIdOrden().longValue());
            registroVO.setNumeroReferencia(agaceOrdenDao.obtenerNumeroReferenciaOrden(((AgaceOrden) notificable).getIdOrden().longValue()));
        }

        if (notificable instanceof FecetProrrogaOficio) {
            consultarRegistrarNyVHelper.crearArchivoTempNyv(Constantes.DIRECTORIO_TEMPORAL_NYV, notificable.getDatosNotificable().getRfcFirmante() + TXT, "AgaceOficioProrroga", String.valueOf(((FecetProrrogaOficio) notificable).getIdProrrogaOficio().longValue()), registroVO.getNumeroReferencia());
            fecetProrrogaOficioDao.updateNumeroReferenciaProrOfi(registroVO.getNumeroReferencia(), ((FecetProrrogaOficio) notificable).getIdProrrogaOficio().longValue());
            registroVO.setNumeroReferencia(fecetProrrogaOficioDao.obtenerNumeroReferenciaProrOfi(((FecetProrrogaOficio) notificable).getIdProrrogaOficio().longValue()));
        }

        if (notificable instanceof FecetProrrogaOrden) {
            consultarRegistrarNyVHelper.crearArchivoTempNyv(Constantes.DIRECTORIO_TEMPORAL_NYV, notificable.getDatosNotificable().getRfcFirmante() + TXT, "AgaceOrdenProrroga", String.valueOf(((FecetProrrogaOrden) notificable).getIdProrrogaOrden().longValue()), registroVO.getNumeroReferencia());
            fecetProrrogaOrdenDao.updateNumeroReferenciaProrOrden(registroVO.getNumeroReferencia(), ((FecetProrrogaOrden) notificable).getIdProrrogaOrden().longValue());
            fecetProrrogaOrdenDao.obtenerNumeroReferenciaProrOrden(((FecetProrrogaOrden) notificable).getIdProrrogaOrden().longValue());
        }

        if (notificable instanceof FecetPruebasPericiales) {
            consultarRegistrarNyVHelper.crearArchivoTempNyv(Constantes.DIRECTORIO_TEMPORAL_NYV, notificable.getDatosNotificable().getRfcFirmante() + TXT, "AgacePruebasPericiales", String.valueOf(((FecetPruebasPericiales) notificable).getIdPruebasPericiales().longValue()), registroVO.getNumeroReferencia());
            fecetPruebasPericialesDao.updateNumeroReferenciaPruePer(registroVO.getNumeroReferencia(), ((FecetPruebasPericiales) notificable).getIdPruebasPericiales().longValue());
            fecetPruebasPericialesDao.obtenerNumeroReferenciaPruePer(((FecetPruebasPericiales) notificable).getIdPruebasPericiales().longValue());
        }
    }
}
