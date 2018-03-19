package mx.gob.sat.siat.feagace.negocio.common;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececFirmante;

public interface FirmanteService {

    FececFirmante getFirmante(final String rfc);
}
