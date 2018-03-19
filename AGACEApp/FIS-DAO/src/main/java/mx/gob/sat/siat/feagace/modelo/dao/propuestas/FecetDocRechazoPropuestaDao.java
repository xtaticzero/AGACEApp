package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;

public interface FecetDocRechazoPropuestaDao {

    /**
     * Returns all rows from the FECET_DOC_RECHAZO_PROPUESTA table that match
     * the criteria 'ID_PROPUESTA = :idPropuesta'.
     */
    List<FecetDocExpediente> findWhereIdPropuestaEquals(BigDecimal idPropuesta);

    /**
     * Returns all rows from the FECET_DOC_RECHAZO_PROPUESTA table that match
     * the criteria 'ID_RECHAZO_PROPUESTA = :idRechazoPropuesta'.
     */
    List<FecetDocExpediente> findWhereIdRechazoPropuestaEquals(BigDecimal idRechazoPropuesta);

    List<FecetDocExpediente> obtenerDoctosRechazoByIdPropuesta(BigDecimal idRechazoPropuesta, BigDecimal blnEstatus);
}
