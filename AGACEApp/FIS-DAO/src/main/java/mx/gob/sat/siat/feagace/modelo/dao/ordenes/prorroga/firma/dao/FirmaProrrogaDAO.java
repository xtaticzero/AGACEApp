package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.firma.dao;

import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;

public interface FirmaProrrogaDAO extends FirmaDAO {

    void guardarResolucion(FecetProrrogaOrden prorroga);

}
