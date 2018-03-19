package mx.gob.sat.siat.feagace.modelo.dao.ordenes.pruebapericial.firma.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.pruebapericial.firma.dao.FirmaPruebaPericialDAO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;

@Repository("firmaPruebaPericialDAO")
public class FirmaPruebaPericialDAOImpl extends BaseJDBCDao<FirmaDTO> implements FirmaPruebaPericialDAO {

    private static final long serialVersionUID = 3289599135974940461L;

    private static final String SQL_UPDATE_PRUEBA_PERICIAL_FIRMA = "UPDATE FECET_PRUEBAS_PERICIALES SET  CADENA_FIRMANTE = ?, FIRMA_FIRMANTE = ?, FECHA_FIRMA = ?  WHERE ID_PRUEBAS_PERICIALES = ? ";
    private static final String SQL_UPDATE_PRUEBA_PERICIAL_RESOLUCION = "UPDATE FECET_PRUEBAS_PERICIALES SET NOMBRE_RESOLUCION = ?, RUTA_RESOLUCION = ? WHERE ID_PRUEBAS_PERICIALES = ? ";

    @Override
    public void guardarFirma(FirmaDTO firma) {
        getJdbcTemplateBase().update(SQL_UPDATE_PRUEBA_PERICIAL_FIRMA, firma.getCadena(), firma.getFirma(), new Date(), firma.getId());
    }

    public void guardarResolucion(FecetPruebasPericiales pruebaPericial) {
        getJdbcTemplateBase().update(SQL_UPDATE_PRUEBA_PERICIAL_RESOLUCION, pruebaPericial.getNombreResolucion(), pruebaPericial.getRutaResolucion(), pruebaPericial.getIdPruebasPericiales());
    }
}
