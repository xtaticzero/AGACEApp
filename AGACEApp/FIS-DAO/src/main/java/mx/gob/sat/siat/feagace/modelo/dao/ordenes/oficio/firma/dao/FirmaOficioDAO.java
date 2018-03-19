package mx.gob.sat.siat.feagace.modelo.dao.ordenes.oficio.firma.dao;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;

public interface FirmaOficioDAO extends FirmaDAO {

    List<FecetOficio> getOficiosDependientesPorIdEstatus(final BigDecimal idOficio, final BigDecimal idEstatus);

    void updateFirmante(FecetOficio dto);

}
