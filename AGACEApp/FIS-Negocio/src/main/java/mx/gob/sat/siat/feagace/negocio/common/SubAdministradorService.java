package mx.gob.sat.siat.feagace.negocio.common;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubadministrador;

public interface SubAdministradorService {
    FececSubadministrador getSubAdministrador(final String rfc);
}
