package mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentoelectronico.firma;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.EmpleadoSuplenteDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;

public interface FirmaDocumentoElectronicoDAO extends FirmaDAO {

    List<EmpleadoSuplenteDTO> obtenerDatosSuplente(final BigDecimal idFirmante, final BigDecimal idSuplir);

    void updateFirmante(FecetOficio dto);
}
