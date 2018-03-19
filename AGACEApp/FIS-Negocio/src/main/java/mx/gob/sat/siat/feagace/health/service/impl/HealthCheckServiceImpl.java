package mx.gob.sat.siat.feagace.health.service.impl;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.softtek.idc.constants.IDCConstants;
import com.softtek.idc.model.IdCInterno;
import com.softtek.idc.service.IDCService;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.buzon.service.BuzonTributarioService;
import mx.gob.sat.siat.common.buzonSelladora.service.BuzonSelladoraService;
import mx.gob.sat.siat.common.correo.model.MailMessage;
import mx.gob.sat.siat.common.correo.services.MailServices;
import mx.gob.sat.siat.feagace.health.dto.HealthDTO;
import mx.gob.sat.siat.feagace.health.service.HealthCheckService;
import mx.gob.sat.siat.sise.service.SiseService;
import mx.gob.sat.siat.ws.empleado.client.EmpleadoUnitedServiceClient;
import mx.gob.sat.siat.ws.nyv.client.NyVServiceClient;
import mx.gob.sat.siat.ws.nyv.client.dto.ResponseConsultaVO;

@Service("healthCheckService")
public class HealthCheckServiceImpl extends BaseBusinessServices implements HealthCheckService {

    /**
     * Serial
     */
    private static final long serialVersionUID = 2806571434552713909L;

    private static final String MSN_OF_ERROR = "[{}]";

    @Autowired
    private transient SiseService siseService;

    @Autowired
    private MailServices mailServices;

    @Autowired
    private transient IDCService idcService;

    @Autowired
    private transient BuzonSelladoraService buzonSelladoraService;

    @Autowired
    @Qualifier("empleadoUnitedServiceClient")
    private transient EmpleadoUnitedServiceClient empleadoClient;

    @Autowired
    private transient BuzonTributarioService buzonTributarioService;

    @Autowired
    @Qualifier("nyvServiceClient")
    private transient NyVServiceClient nyvServiceClient;

    @Value("${mail_services.from}")
    private String from;

    @Value("${mail_services.personal_from}")
    private String personalFrom;

    @Override
    public HealthDTO validarCorreo(final String correo) {
        HealthDTO healthDTO;
        MailMessage mail = new MailMessage();
        mail.setFrom(from);
        mail.setPersonalFrom(personalFrom);
        Collection<String> to = new LinkedList<String>();
        to.add(correo);
        mail.setTo(to);
        mail.setSubject("Prueba Correo AGACE");
        mail.setBody("Te dije que si furulaba NO ME MANDES A UN BRANCH :(");
        try {
            mailServices.sendHTMLMessage(mail);
            healthDTO = new HealthDTO(true, "OK");
        } catch (Exception e) {
            logger.error(MSN_OF_ERROR, e);
            healthDTO = new HealthDTO(false, e.getMessage());
        }
        return healthDTO;
    }

    @Override
    public HealthDTO validarIDC(final String rfc) {
        HealthDTO healthDTO;
        String secciones[] = {IDCConstants.IDENTIFICACION, IDCConstants.PATENTE, IDCConstants.DATOS_COMPLEMENTARIOS};
        try {
            IdCInterno idCInterno = idcService.obtenerInformacionContribuyente(rfc, secciones);
            healthDTO = new HealthDTO(true, idCInterno.toString());
        } catch (Exception e) {
            logger.error(MSN_OF_ERROR, e);
            healthDTO = new HealthDTO(false, e.getMessage());
        }
        return healthDTO;
    }

    @Override
    public HealthDTO validarSISE(final String rfc) {
        HealthDTO healthDTO;

        try {
            healthDTO = new HealthDTO(true, String.valueOf(siseService.verInformacion(rfc)));
        } catch (Exception e) {
            logger.error(MSN_OF_ERROR, e);
            healthDTO = new HealthDTO(false, e.getMessage());
        }
        return healthDTO;
    }

    @Override
    public HealthDTO validarBuzonSelladora(final String cadenaOriginal) {
        HealthDTO healthDTO;
        try {
            healthDTO = new HealthDTO(true, buzonSelladoraService.getSelloDigital(cadenaOriginal));
        } catch (Exception e) {
            logger.error(MSN_OF_ERROR, e);
            healthDTO = new HealthDTO(false, e.toString());
        }
        return healthDTO;
    }

    @Override
    public HealthDTO validarEmpleadoPorTipoCentralArace(String tipo, String central, String arace) {
        HealthDTO healthDTO;
        try {
            healthDTO = new HealthDTO(true, empleadoClient.getUnidadesAdministrativas(empleadoClient.getTipoFiscalizacion()).toString());
        } catch (Exception e) {
            logger.error(MSN_OF_ERROR, e);
            healthDTO = new HealthDTO(false, e.toString());
        }
        return healthDTO;
    }

    @Override
    public HealthDTO validarMediosComunicacion(final String rfc) {
        HealthDTO healthDTO;
        try {
            healthDTO = new HealthDTO(true, buzonTributarioService.obtenerMediosComunicacion(rfc).toString());
        } catch (Exception e) {
            logger.error(MSN_OF_ERROR, e);
            healthDTO = new HealthDTO(false, e.toString());
        }
        return healthDTO;
    }

    @Override
    public HealthDTO validarNyVConsultarActoAdministrativo(String folioActoAdministrativo) {
        HealthDTO healthDTO;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy mm DD");
            ResponseConsultaVO response = nyvServiceClient.consultarActoAdministrativo(folioActoAdministrativo);
            if (response != null && response.getFechaNotificacionEfectiva() != null) {
                healthDTO = new HealthDTO(true, sdf.format(response.getFechaNotificacionEfectiva()));
            } else {
                healthDTO = new HealthDTO(false, "");
            }
        } catch (Exception e) {
            logger.error(MSN_OF_ERROR, e);
            healthDTO = new HealthDTO(false, e.toString());
        }
        return healthDTO;
    }

}
