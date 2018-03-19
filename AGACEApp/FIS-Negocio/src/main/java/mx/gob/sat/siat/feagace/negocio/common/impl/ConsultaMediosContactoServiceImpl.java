package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.util.List;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.buzon.exception.BuzonNoDisponibleException;
import mx.gob.sat.siat.buzon.model.MedioComunicacion;
import mx.gob.sat.siat.buzon.service.BuzonTributarioService;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetDetalleContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaMediosContactoService;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaSISEService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidaMediosContactoRule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("consultaMediosContactoService")
public class ConsultaMediosContactoServiceImpl extends BaseBusinessServices
        implements ConsultaMediosContactoService {

    /**
     * Serial
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private transient ConsultaSISEService consultaSISEService;

    @Autowired
    private transient FecetDetalleContribuyenteDao fecetDetalleContribuyenteDao;

    @Autowired
    private transient BuzonTributarioService buzonTributarioService;

    @Override
    public List<MedioComunicacion> consultaMediosContacto(final String rfc)
            throws NegocioException {

        try {
            return buzonTributarioService.obtenerMediosComunicacion(rfc);
        } catch (BuzonNoDisponibleException e) {
            logger.error(e.getMessage(), e);
            throw new NegocioException(e.getMessage(), e);
        }
    }

    public ValidaMediosContactoBO validaMediosContacto(final String rfc) {
        ValidaMediosContactoBO validaMediosContactoBO = new ValidaMediosContactoBO();
        try {
            validaMediosContactoBO.setRfc(rfc);
            validaMediosContactoBO.setMediosComunicacion(buzonTributarioService
                    .obtenerMediosComunicacion(rfc));
            validaMediosContactoBO.setPPEE(!consultaPPEE(rfc));
            validaMediosContactoBO
                    .setRule(ValidaMediosContactoRule.CUENTA_MEDIO_CONTACTO);
            validaMediosContactoBO
                    .setFecetDetalleContribuyenteDao(fecetDetalleContribuyenteDao);
            while (validaMediosContactoBO.getRule().process(validaMediosContactoBO)) {
                logger.info("Validando medios de contacto.");
            }
        } catch (BuzonNoDisponibleException e) {
            validaMediosContactoBO
                    .setMessage(Constantes.MESANJE_ERROR_MEDIOS_CONTACTO);
            validaMediosContactoBO.setFlag(false);
            return validaMediosContactoBO;
        }
        return validaMediosContactoBO;

    }

    @Override
    public ValidaMediosContactoBO validaMediosContacto(
            ValidaMediosContactoBO validaMediosContactoBO) {
        return validaMediosContacto(validaMediosContactoBO.getRfc());
    }

    @Override
    public boolean consultaPPEE(String rfc) {
        return consultaSISEService.verInformacion(rfc);   
    }

    public String verificaMediosContacto(final String rfcContribuyente) {
        String tipoMedioDeContacto = null;

        try {

            List<MedioComunicacion> arrayMC = consultaMediosContacto(rfcContribuyente);

            if (arrayMC != null) {
                for (MedioComunicacion medioContacto : arrayMC) {
                    if (!medioContacto.getMedio().equals("")
                            && medioContacto.getAmparado() == 0) {
                        tipoMedioDeContacto = Constantes.CON_MEDIOS_CONTACTO_NO_AMPARADO;
                        break;
                    } else if (!medioContacto.getMedio().equals("")
                            && medioContacto.getAmparado() == 1) {
                        tipoMedioDeContacto = Constantes.CON_MEDIOS_CONTACTO_AMPARADO;
                    }
                }

                if (tipoMedioDeContacto == null) {
                    tipoMedioDeContacto = Constantes.SIN_MEDIOS_CONTACTO;
                }
            }

        } catch (NegocioException e) {
            logger.error("Error [{}]", e);
            tipoMedioDeContacto = ConstantesError.ERROR_MEDIOS_CONTACTO;
        }
        return tipoMedioDeContacto;
    }

}
