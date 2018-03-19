package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.firma.dao.impl;

import java.util.Date;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.firma.dao.FirmaProrrogaDAO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;

import org.springframework.stereotype.Repository;

@Repository
public class FirmaProrrogaDAOImpl extends BaseJDBCDao<FirmaDTO> implements FirmaProrrogaDAO {

    private static final long serialVersionUID = 3289599135974940461L;

    private static final String SQL_UPDATE_PRORROGA_FIRMA = "UPDATE FECET_PRORROGA_ORDEN SET  CADENA_FIRMANTE = ?, FIRMA_FIRMANTE = ?, FECHA_FIRMA = ?  WHERE ID_PRORROGA_ORDEN = ? ";
    private static final String SQL_UPDATE_PRORROGA_RESOLUCION = "UPDATE FECET_PRORROGA_ORDEN SET NOMBRE_RESOLUCION = ?, RUTA_RESOLUCION = ? WHERE ID_PRORROGA_ORDEN = ? ";

    @Override
    public void guardarFirma(FirmaDTO firma) {
        getJdbcTemplateBase().update(SQL_UPDATE_PRORROGA_FIRMA, firma.getCadena(), firma.getFirma(), new Date(), firma.getId());
    }

    public void guardarResolucion(FecetProrrogaOrden prorroga) {
        getJdbcTemplateBase().update(SQL_UPDATE_PRORROGA_RESOLUCION, prorroga.getNombreResolucion(), prorroga.getRutaResolucion(), prorroga.getIdProrrogaOrden());
    }

}
