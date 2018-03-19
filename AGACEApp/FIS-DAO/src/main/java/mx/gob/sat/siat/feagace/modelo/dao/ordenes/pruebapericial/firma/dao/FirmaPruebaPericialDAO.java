package mx.gob.sat.siat.feagace.modelo.dao.ordenes.pruebapericial.firma.dao;

import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;

public interface FirmaPruebaPericialDAO extends FirmaDAO {

    void guardarResolucion(FecetPruebasPericiales pruebasPericiales);

}
