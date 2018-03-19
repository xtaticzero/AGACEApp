package mx.gob.sat.siat.feagace.negocio.common;

import java.util.List;

import mx.gob.sat.siat.buzon.model.MedioComunicacion;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;

public interface ConsultaMediosContactoService {
    
    ValidaMediosContactoBO validaMediosContacto(final String rfc);

    List<MedioComunicacion> consultaMediosContacto(final String rfc) throws NegocioException;

    boolean consultaPPEE(String rfc) throws NegocioException;

    ValidaMediosContactoBO validaMediosContacto(ValidaMediosContactoBO validaMediosContacto);
    
    String verificaMediosContacto(final String rfcContribuyente);
}
