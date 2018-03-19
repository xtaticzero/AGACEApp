package mx.gob.sat.siat.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.RespuestaOrdenAcuerdoConclusivoDTO;
import mx.gob.sat.siat.feagace.negocio.common.tiempos.helper.TiemposHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.suspension.service.SuspensionService;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;
import mx.gob.sat.siat.model.AcuerdoConclusivoDTO;
import mx.gob.sat.siat.service.AcuerdoConclusivoWS;
import mx.gob.sat.siat.service.AcuerdosConclusivosAbstract;

@Component("acuerdoConclusivoWS")
@WebService(endpointInterface = "mx.gob.sat.siat.service.AcuerdoConclusivoWS")
public class AcuerdoConclusivoWSImpl extends AcuerdosConclusivosAbstract implements AcuerdoConclusivoWS {

    private static final long serialVersionUID = -5433897942905991328L;

    private static final Integer NO_EXISTE_ORDEN = 1;
    private static final Integer ERROR_FECHAS = 2;
    private static final Integer ERROR_PARAMETROS = 3;
    private static final Integer EXITO = 4;

    @Autowired
    private TiemposHelper tiemposHelper;

    @Autowired
    private transient SuspensionService suspensionService;

    @Autowired
    private transient AgaceOrdenDao agaceOrdenDao;
    
    @Override
    public Integer notificaCreacionAcuerdoConclusivo(AcuerdoConclusivoDTO acuerdoConclusivoDTO) {
        boolean nullFechaRegistroAcuerdo = acuerdoConclusivoDTO.getFechaRegistroAcuerdoConclusivo() == null;
        boolean nullFechaFinAcuerdo = acuerdoConclusivoDTO.getFechaFinAcuerdoConclusivo() == null;
        boolean nullNumeroOrden = acuerdoConclusivoDTO.getNumeroOrden() == null;
        boolean nullAcuerdoConclusivo = acuerdoConclusivoDTO.getNumeroAcuerdoConclusivo() == null;
        boolean nullRfcContribuyente = acuerdoConclusivoDTO.getRfcContribuyente() == null;
        boolean valida = nullFechaRegistroAcuerdo || nullNumeroOrden || nullAcuerdoConclusivo;
        valida = valida || nullFechaFinAcuerdo || nullRfcContribuyente;
        if (valida) {
            return ERROR_PARAMETROS;
        }

        if (!tiemposHelper.esIgualFechaHoy(acuerdoConclusivoDTO.getFechaRegistroAcuerdoConclusivo()) || acuerdoConclusivoDTO.getFechaRegistroAcuerdoConclusivo().after(acuerdoConclusivoDTO.getFechaFinAcuerdoConclusivo())) {
            return ERROR_FECHAS;
        }
        List<AgaceOrden> ordenes = agaceOrdenDao.findWhereNumeroOrdenEquals(acuerdoConclusivoDTO.getNumeroOrden());
        AgaceOrden agaceOrden = ordenes == null ? null : ordenes.isEmpty() ? null : ordenes.get(0);

        if (agaceOrden == null) {
            return NO_EXISTE_ORDEN;
        }
        if (!(agaceOrden.getFecetContribuyente() != null && agaceOrden.getFecetContribuyente().getRfc().toUpperCase().equals(acuerdoConclusivoDTO.getRfcContribuyente().toUpperCase()))) {
            return ERROR_PARAMETROS;
        }
        if ((!ValidacionParametrosUtil.esAlfanumerico(acuerdoConclusivoDTO.getNumeroAcuerdoConclusivo())) || suspensionService.existeAcuerdoConclusivo(acuerdoConclusivoDTO.getNumeroAcuerdoConclusivo())) {
            return ERROR_PARAMETROS;
        }
        
        boolean result = suspensionService.iniciaSuspensionOrdenXAcuerdo(agaceOrden, acuerdoConclusivoDTO.getNumeroAcuerdoConclusivo(), acuerdoConclusivoDTO.getFechaRegistroAcuerdoConclusivo());

        if (result) {
            return EXITO;
        } else {
            return ERROR_PARAMETROS;
        }

    }

    @Override
    public Integer notificaCierreAcuerdoConclusivo(AcuerdoConclusivoDTO acuerdoConclusivoDTO) {
        boolean nullFechaRegistroAcuerdo = acuerdoConclusivoDTO.getFechaRegistroAcuerdoConclusivo() == null;
        boolean nullFechaFinAcuerdo = acuerdoConclusivoDTO.getFechaFinAcuerdoConclusivo() == null;
        boolean nullNumeroOrden = acuerdoConclusivoDTO.getNumeroOrden() == null;
        boolean nullAcuerdoConclusivo = acuerdoConclusivoDTO.getNumeroAcuerdoConclusivo() == null;
        boolean nullRfcContribuyente = acuerdoConclusivoDTO.getRfcContribuyente() == null;
        boolean valida = nullFechaRegistroAcuerdo || nullFechaFinAcuerdo || nullNumeroOrden;
        valida = valida || nullAcuerdoConclusivo || nullRfcContribuyente;
        if (valida) {
            return ERROR_PARAMETROS;
        }

        if (!tiemposHelper.esIgualFechaHoy(acuerdoConclusivoDTO.getFechaFinAcuerdoConclusivo()) || acuerdoConclusivoDTO.getFechaRegistroAcuerdoConclusivo().after(acuerdoConclusivoDTO.getFechaFinAcuerdoConclusivo())) {
            return ERROR_FECHAS;
        }

        List<AgaceOrden> ordenes = agaceOrdenDao.findWhereNumeroOrdenEquals(acuerdoConclusivoDTO.getNumeroOrden());
        AgaceOrden agaceOrden = ordenes == null ? null : ordenes.isEmpty() ? null : ordenes.get(0);
        if (agaceOrden == null) {
            return NO_EXISTE_ORDEN;
        }

        if (!(agaceOrden.getFecetContribuyente() != null && agaceOrden.getFecetContribuyente().getRfc().toUpperCase().equals(acuerdoConclusivoDTO.getRfcContribuyente().toUpperCase()))) {
            return ERROR_PARAMETROS;
        }

        boolean result = suspensionService.finalizarSuspensionOrdenXAcuerdo(agaceOrden, acuerdoConclusivoDTO.getNumeroAcuerdoConclusivo(), acuerdoConclusivoDTO.getFechaFinAcuerdoConclusivo());
        if (result) {
            return EXITO;
        } else {
            return ERROR_PARAMETROS;
        }
    }

    @Override
    public RespuestaOrdenAcuerdoConclusivoDTO getOrden(String numeroOrden) {
        boolean nullNumeroOrden = numeroOrden == null || numeroOrden.equalsIgnoreCase("");
        RespuestaOrdenAcuerdoConclusivoDTO respuestaOrdenAcuerdoConclusivoDTO = new RespuestaOrdenAcuerdoConclusivoDTO();
        if (nullNumeroOrden) {
            respuestaOrdenAcuerdoConclusivoDTO.setEstatusConsulta(ERROR_PARAMETROS);
            return respuestaOrdenAcuerdoConclusivoDTO;
        }
        try {
            respuestaOrdenAcuerdoConclusivoDTO = agaceOrdenDao.consultaOrdenAcuerdosConclusivos(numeroOrden);
            fillUnidadAdministrativa(respuestaOrdenAcuerdoConclusivoDTO);
        } catch (Exception e) {
            respuestaOrdenAcuerdoConclusivoDTO.setEstatusConsulta(NO_EXISTE_ORDEN);
            return respuestaOrdenAcuerdoConclusivoDTO;
        }

        if (respuestaOrdenAcuerdoConclusivoDTO == null) {
            respuestaOrdenAcuerdoConclusivoDTO = new RespuestaOrdenAcuerdoConclusivoDTO();
            respuestaOrdenAcuerdoConclusivoDTO.setEstatusConsulta(NO_EXISTE_ORDEN);
            return respuestaOrdenAcuerdoConclusivoDTO;
        }
        respuestaOrdenAcuerdoConclusivoDTO.setEstatusConsulta(EXITO);
        return respuestaOrdenAcuerdoConclusivoDTO;
    }

}
