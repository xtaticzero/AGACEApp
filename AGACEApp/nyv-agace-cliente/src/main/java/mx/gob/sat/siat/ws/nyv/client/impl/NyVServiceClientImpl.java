/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.nyv.client.impl;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import mx.gob.sat.siat.ws.nyv.client.NyVServiceClient;
import mx.gob.sat.siat.ws.nyv.client.dto.ActoAdministrativoVO;
import mx.gob.sat.siat.ws.nyv.client.dto.ConsultaNumeroReferenciaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.RegistroActoAdministrativo;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseConsultaVO;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseRegistroVO;
import mx.gob.sat.siat.ws.nyv.client.dto.TipoProcesoVO;
import mx.gob.sat.siat.ws.nyv.client.impl.helper.NyVBeanHelper;
import mx.gob.sat.siat.ws.nyv.client.repository.NyVRepositoryMockService;
import mx.gob.sat.siat.ws.nyv.client.repository.WSNyVV1Repository;
import mx.gob.sat.siat.ws.nyv.client.repository.WSNyVV2Repository;
import mx.gob.sat.siat.ws.nyv.constantes.ConstatantesClientNyV;
import mx.gob.sat.siat.ws.nyv.constantes.VersionNyVEnum;
import mx.gob.sat.siat.ws.nyv.exception.NyVClientException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Service("nyvServiceClient")
//@Scope("prototype")
public class NyVServiceClientImpl implements Serializable, NyVServiceClient {

    private static final long serialVersionUID = 957633745650669718L;
    /**
     * Objeto del logger.
     */
    protected final Logger logger;

    public Logger getLogger() {
        return logger;
    }

    @Value("${service.nyv.wsdl}")
    private String urlWsdlNyv;
    @Value("${service.nyv.version.uno}")
    private String version;

    private WSNyVV1Repository wsNyVRepositoryV1;
    private WSNyVV2Repository wsNyVRepositoryV2;
    private NyVRepositoryMockService nyvMockServiceImpl;

    public NyVServiceClientImpl() {
        logger = Logger.getLogger(getClass());        
    }

    public NyVServiceClientImpl(String urlWsdlNyv, String version) {
        this.urlWsdlNyv = urlWsdlNyv;
        this.version = version;
        logger = Logger.getLogger(getClass());        
    }

    private boolean crearProxyNyV() {
        if ((wsNyVRepositoryV1 == null) && (wsNyVRepositoryV2 == null) && (nyvMockServiceImpl == null)) {
            logger.info("crearProxyNyV()");
            if (VersionNyVEnum.FASE_II.equals(getVrsionNyV())) {
                try {
                    WSNyVV2Sistema wsNyVSistema = new WSNyVV2Sistema(new URL(urlWsdlNyv));
                    wsNyVRepositoryV2 = wsNyVSistema.getWSNyVSistemaPort();
                } catch (MalformedURLException ex) {
                    logger.error(MSG_ERR_URL, ex.getCause());
                    logger.error(ex);
                    return false;
                } catch (Exception ex) {
                    logger.error(MSG_ERR_SERVICIO, ex.getCause());
                    logger.error(ex);
                    return false;
                }
            } else if (VersionNyVEnum.FASE_I.equals(getVrsionNyV())) {
                try {
                    WSNyVV1Sistema wsNyVSistema = new WSNyVV1Sistema(new URL(urlWsdlNyv));
                    wsNyVRepositoryV1 = wsNyVSistema.getWSNyVSistemaPort();
                } catch (MalformedURLException ex) {
                    logger.error(MSG_ERR_URL, ex.getCause());
                    logger.error(ex);
                    return false;
                } catch (Exception ex) {
                    logger.error(MSG_ERR_SERVICIO, ex.getCause());
                    logger.error(ex);
                    return false;
                }
            } else {
                nyvMockServiceImpl = new NyVRepositoryMockServiceImpl();
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    @Override
    public boolean fueraHorarioLaboral() throws NyVClientException {
        try {
            crearProxyNyV();
            int tipo = getVrsionNyV().getIdVersion();
            switch (tipo) {
                case ConstatantesClientNyV.FASE_I:
                    return wsNyVRepositoryV1.fueraHorarioLaboral();
                case ConstatantesClientNyV.FASE_II:
                    return wsNyVRepositoryV2.fueraHorarioLaboral();
                case ConstatantesClientNyV.MOCK:
                    return nyvMockServiceImpl.fueraHorarioLaboral();
            }
            throw new NyVClientException(MSG_ERR_VERSION);
        } catch (Exception e) {
            logger.error("Error en servicio NyV.fueraHorarioLaboral()", e);
            throw new NyVClientException("fueraHorarioLaboral()", e);
        }
    }

    @Override
    public ConsultaNumeroReferenciaVO consultarNumeroReferencia(String numeroReferencia) throws NyVClientException {
        try {
            if (numeroReferencia == null || numeroReferencia.isEmpty()) {
                throw new NyVClientException("numeroReferencia is null");
            } else {
                crearProxyNyV();
                int tipo = getVrsionNyV().getIdVersion();
                switch (tipo) {
                    case ConstatantesClientNyV.FASE_I:
                        mx.gob.sat.siat.ws.nyv.v1.bean.ResponseConsultaNumeroReferencia response
                                = wsNyVRepositoryV1.consultarNumeroReferencia(numeroReferencia);
                        return response != null ? new ConsultaNumeroReferenciaVO(response) : null;
                    case ConstatantesClientNyV.FASE_II:
                        mx.gob.sat.siat.ws.nyv.v2.bean.ResponseConsultaNumeroReferencia responseV2
                                = wsNyVRepositoryV2.consultarNumeroReferencia(numeroReferencia);
                        return responseV2 != null ? new ConsultaNumeroReferenciaVO(responseV2) : null;
                    case ConstatantesClientNyV.MOCK:
                        return nyvMockServiceImpl.consultarNumeroReferencia(numeroReferencia);
                }
                return null;
            }

        } catch (Exception e) {
            logger.error("Error en servicio NyV.consultarNumeroReferencia(" + numeroReferencia + ")", e);
            throw new NyVClientException("consultarNumeroReferencia(" + numeroReferencia + ")", e);
        }
    }

    @Override
    public List<TipoProcesoVO> consultarListaTiposProceso(String unidadAdministrativa) throws NyVClientException {
        try {
            if (unidadAdministrativa == null || unidadAdministrativa.isEmpty()) {
                throw new NyVClientException("unidadAdministrativa is null");
            } else {
                crearProxyNyV();
                int tipo = getVrsionNyV().getIdVersion();
                switch (tipo) {
                    case ConstatantesClientNyV.FASE_I:
                        //return NyVBeanHelper.convertTipo1();
                        mx.gob.sat.siat.ws.nyv.v1.bean.ResponseConsultaTiposProceso response
                                = wsNyVRepositoryV1.consultarListaTiposProceso(unidadAdministrativa);
                        return response != null ? NyVBeanHelper.convertTipo1(response.getTipoProceso()) : null;
                    case ConstatantesClientNyV.FASE_II:
                        mx.gob.sat.siat.ws.nyv.v2.bean.ResponseConsultaTiposProceso responseV2
                                = wsNyVRepositoryV2.consultarListaTiposProceso(unidadAdministrativa);
                        return responseV2 != null ? NyVBeanHelper.convertTipo2(responseV2.getTipoProceso()) : null;
                    case ConstatantesClientNyV.MOCK:
                        return nyvMockServiceImpl.consultarListaTiposProceso(unidadAdministrativa);
                }
                return null;
            }

        } catch (Exception e) {
            logger.error("Error en servicio NyV.consultarListaTiposProceso(" + unidadAdministrativa + ")", e);
            throw new NyVClientException("consultarListaTiposProceso(" + unidadAdministrativa + ")", e);
        }
    }

    @Override
    public List<ActoAdministrativoVO> consultarListaActosAdmin(String unidadAdministrativa) throws NyVClientException {
        try {
            if (unidadAdministrativa == null || unidadAdministrativa.isEmpty()) {
                throw new NyVClientException("unidadAdministrativa is null");
            }
            crearProxyNyV();
            int tipo = getVrsionNyV().getIdVersion();
            switch (tipo) {
                case ConstatantesClientNyV.FASE_I:
                    return NyVBeanHelper.convert(wsNyVRepositoryV1.consultarListaActosAdmin(unidadAdministrativa));
                case ConstatantesClientNyV.FASE_II:
                    return NyVBeanHelper.convert(wsNyVRepositoryV2.consultarListaActosAdmin(unidadAdministrativa));
                case ConstatantesClientNyV.MOCK:
                    return nyvMockServiceImpl.consultarListaActosAdmin(unidadAdministrativa);
            }
            return new ArrayList<ActoAdministrativoVO>();
        } catch (Exception e) {
            logger.error("Error en servicio NyV.consultarListaActosAdmin(" + unidadAdministrativa + ")", e);
            throw new NyVClientException("consultarListaActosAdmin(" + unidadAdministrativa + ")", e);
        }
    }

    @Override
    public ResponseConsultaVO consultarActoAdministrativo(String folioActoAdministrativo) throws NyVClientException {
        try {
            if (folioActoAdministrativo == null || folioActoAdministrativo.isEmpty()) {
                throw new NyVClientException("folioActoAdministrativo is null");
            }
            crearProxyNyV();
            int tipo = getVrsionNyV().getIdVersion();
            switch (tipo) {
                case ConstatantesClientNyV.FASE_I:
                    return NyVBeanHelper.convert(wsNyVRepositoryV1.consultarActoAdministrativo(folioActoAdministrativo));
                case ConstatantesClientNyV.FASE_II:
                    return NyVBeanHelper.convert(wsNyVRepositoryV2.consultarActoAdministrativo(folioActoAdministrativo));
                case ConstatantesClientNyV.MOCK:
                    return nyvMockServiceImpl.consultarActoAdministrativo(folioActoAdministrativo);
            }
            return new ResponseConsultaVO();
        } catch (Exception e) {
            logger.error("Error en servicio NyV.consultarActoAdministrativo(" + folioActoAdministrativo + ")", e);
            throw new NyVClientException("consultarActoAdministrativo(" + folioActoAdministrativo + ")", e);
        }
    }

    @Override
    public ResponseRegistroVO registrarActoAdministrativo(RegistroActoAdministrativo registroActoAdministrativo) throws NyVClientException {
        try {
            if (registroActoAdministrativo == null) {
                throw new NyVClientException("registroActoAdministrativo is null");
            }
            crearProxyNyV();
            int tipo = getVrsionNyV().getIdVersion();            
            switch (tipo) {
                case ConstatantesClientNyV.FASE_I:
                    logger.debug(registroActoAdministrativo.toString());
                    return NyVBeanHelper.convert(wsNyVRepositoryV1.registrarActoAdministrativo(registroActoAdministrativo.getActoAdminV1()));
                case ConstatantesClientNyV.FASE_II:
                    logger.debug(registroActoAdministrativo.toString());
                    return NyVBeanHelper.convert(wsNyVRepositoryV2.registrarActoAdministrativo(registroActoAdministrativo.getActoAdminV2()));
                case ConstatantesClientNyV.MOCK:
                    logger.debug(registroActoAdministrativo.toString());
                    return nyvMockServiceImpl.registrarActoAdministrativo(registroActoAdministrativo);
            }
            return new ResponseRegistroVO();
        } catch (Exception e) {
            logger.error("Error en servicio NyV.registrarActoAdministrativo : ", e);
            logger.error(registroActoAdministrativo);
            throw new NyVClientException("registrarActoAdministrativo", e);
        }
    }

    @Override
    public String getUrlWsdlNyv() {
        return urlWsdlNyv;
    }

    @Override
    public VersionNyVEnum getVrsionNyV() {
        if (version != null) {
            try {
                if (version.equals("mock")) {
                    return VersionNyVEnum.MOCK;
                }
                Boolean ver = Boolean.parseBoolean(version);
                return ver ? VersionNyVEnum.FASE_I : VersionNyVEnum.FASE_II;
            } catch (Exception e) {
                return VersionNyVEnum.MOCK;
            }
        } else {
            return VersionNyVEnum.MOCK;
        }
    }
}
