package mx.gob.sat.siat.feagace.negocio.common;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececAuditor;

public interface AuditorService {
    FececAuditor getAuditor(final String rfcAuditor);
}
